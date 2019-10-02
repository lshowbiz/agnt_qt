package com.joymain.ng.webapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.Constants;
import com.joymain.ng.model.FiBillAccount;
import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.model.JfiQuota;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PayAccount;
import com.joymain.ng.service.FiBillAccountService;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JfiQuotaManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.bill99.Bill99Constants;
import com.joymain.ng.util.bill99.Bill99UtilImpl2;
import com.joymain.ng.util.chinapnr.ChinapnrUtilImpl2;
import com.joymain.ng.util.sypay.SypayUtilImpl;
import com.joymain.ng.util.umb.UmbPayUtilImpl2;
import com.joymain.ng.util.yeepay.YeePayUtilImpl2;
import com.joymain.ng.util.yspay.PayBusiness;
import com.joymain.ng.util.yspay.YspayUtilImpl;
import com.joymain.ng.webapp.util.OrderCheckUtils;

/**
 *
 * 支付总控制器
 *
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("unused")
@RequestMapping("/jfiPayCompanyOrder")
public class JfiPayOrderCompanyController extends BaseFormController {

	@Autowired
	private FiCommonAddrManager fiCommonAddrManager;// 常用收货地址管理

	@Autowired
	FiBillAccountService fiBillAccountService;// 获取支付商户对象
	@Autowired
	JfiQuotaManager jfiQuotaManager;// 商户限额值定制表

	@Autowired
	JpoMemberOrderManager jpoMemberOrderManager;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// 当前用户
			JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			request.setAttribute("jsysUser", loginSysUser);
			JfiPayOrderCompany jpoc = new JfiPayOrderCompany();
			jpoc.setOrderAmount(BigDecimal.valueOf(Double.valueOf((request.getParameter("orderAmount")))));
			jpoc.setPayAmount(BigDecimal.valueOf(Double.valueOf((request.getParameter("payAmount")))));
			jpoc.setOrderNo(request.getParameter("orderNo"));
			jpoc.setPayType(request.getParameter("payType"));
			jpoc.setUserCode(request.getParameter("userCode"));
			jpoc.setFlag(request.getParameter("flag"));
			jpoc.setMerPriv(request.getParameter("merPriv"));
			jpoc.setBackUrl(request.getSession().getServletContext().getRealPath(""));
			SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
			jpoc.setOrderTime(new Date());
			// ===================================订单审核==================================start
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.valueOf(jpoc.getOrderNo()));
			if (isOverQty(jpoMemberOrder)) {
				this.saveMessage(request, getText("商品已售完或者库存不足！"));
				response.getWriter().write(closeParent());
				return null;
			}

			if (!OrderCheckUtils.checkMemberOrder(JfiPayOrderCompanyController.class, jpoMemberOrder, request)) {
				response.getWriter().write(closeParent());
				return null;
				// return new ModelAndView(reUrl);
			}

//			if(validateOrder(jpoMemberOrder,loginSysUser)){
//				log.info(jpoMemberOrder.getMemberOrderNo() + 
//						LocaleUtil.getLocalText("user.validate"));
//				//saveMessage(request,LocaleUtil.getLocalText("user.validate"));
//				
//				 return new ModelAndView("payValidate");
//			 }  
			// ===================================订单审核==================================end

			JfiPayRetMsg jfiPayRetMsg = new JfiPayRetMsg();

			jpoc.setOrderAmount(BigDecimal.valueOf(jpoc.getOrderAmount().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
			jpoc.setPayAmount(BigDecimal.valueOf(jpoc.getPayAmount().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
			// 是否全额支付，分流
			/*if (0 == jpoc.getOrderAmount().compareTo(jpoc.getPayAmount())) {
				jpoc.setIsFull(true);
				jfiPayRetMsg = dealerPay(jpoc, loginSysUser);
			}*/
			if (jfiPayRetMsg == null || StringUtils.isEmpty(jfiPayRetMsg.getUrl())) {
				jpoc.setIsFull(false);
				jfiPayRetMsg = districtPay(jpoc, loginSysUser);
			}
			/*
			 * if(jfiPayRetMsg.getUrl().equals(SypayUtilImpl.POST_URL )){
			 * response.getWriter().write(doBgPostReq(jfiPayRetMsg)); return
			 * null; }
			 */

			//环迅支付返回方式变更
			if(true == jfiPayRetMsg.getIsHxPay())
			{
				response.getWriter().write(doBgPostReq(jfiPayRetMsg));
				return null;
			}

			ModelAndView mv = new ModelAndView("redirect:" + jfiPayRetMsg.getUrl());// redirect模式
			for (Object key : jfiPayRetMsg.getDataMap().keySet()) {
				mv.addObject(String.valueOf(key), jfiPayRetMsg.getDataMap().get(key));
				log.info("fullpay controller key is :" + String.valueOf(key) + ",value is :" + jfiPayRetMsg.getDataMap().get(key));
			}
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, getText((StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : "第三方支付出现异常,请联系管理员!")));
			response.getWriter().write(closeParent());
		}
		return null;
	}

	/**
	 * 经销商模式的支付方式
	 *
	 * @return 请求第三方支付地址和数据格式
	 */
	public JfiPayRetMsg dealerPay(JfiPayOrderCompany jfiPayOrderCompany, JsysUser loginSysUser) throws Exception {
		JfiPayRetMsg jfiPayRetMsg = null;
		PayModeService pm = null;// 支付公司逻辑处理公共接口
		try {
			// 获取商户对象
			//FiBillAccount payAccount = fiBillAccountService.getFiBillAccountByUserCode(loginSysUser.getUserCode(), "1");
			PayAccount payAccount = fiBillAccountService.getPayAccountByUserCode(loginSysUser.getUserCode(), "1");
			if (payAccount == null) {
				return null;// throw new AppException("数据库中获取不到商户号");
			}
			//限额控制======================================start
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			JfiQuota quota = new JfiQuota();
			quota.setAccountId(payAccount.getAccountId());
			quota.setStatus("0");
			quota.setValidityPeriod(sdf.format(new Date()));
			Map<String, BigDecimal> map = jfiQuotaManager.getSumMoney(quota);
			if (map.get("balance") != null && map.get("balance").compareTo(new BigDecimal(0)) != 1) {
				return null;
				// throw new AppException("该商户号的当前財月所存入金额已达标:"
				// +map.get("balance"));
			}
			//限额控制======================================end
			log.info("payAccount is :" + payAccount.getAccountCode() + ", payUser is : " + payAccount.getUserCode());
			// 回写订单卖家信息
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.valueOf(jfiPayOrderCompany.getOrderNo()));
			jpoMemberOrder.setSaleNo(payAccount.getUserCode());
			jpoMemberOrderManager.save(jpoMemberOrder);

			jfiPayOrderCompany.setPayAccount(payAccount.getAccountCode());

			getBusiness(jfiPayOrderCompany, "sh" + payAccount.getAccountCode());

			//Modify By WuCF 20170118 安杰玛只用快钱
			payAccount.setProviderType("1");//设置为1：快钱

			if (Constants.PAY_KUAIQIAN_COMPANY.equals(payAccount.getProviderType())) {// 快钱支付
				pm = new Bill99UtilImpl2();
			} /*else if (Constants.PAY_BYHT_COMPANY.equals(payAccount.getProviderType())) {// 宝易互通
				log.info("pay is baoyihutong ");
				pm = new UmbPayUtilImpl2();
			} else if (Constants.PAY_HFTX_COMPANY.equals(payAccount.getProviderType())) {// 汇付天下
				log.info("pay is hftx ");
				pm = new ChinapnrUtilImpl2();
			} else if (Constants.PAY_YIBAO_COMPANY.equals(payAccount.getProviderType())) {// 易宝
				log.info("pay is yibao ");
				pm = new YeePayUtilImpl2();
			} else if (Constants.PAY_SSF_COMPANY.equals(payAccount.getProviderType())) { // 顺手付
				pm = new SypayUtilImpl();
				jfiPayRetMsg = pm.PayCompanyHandle(jfiPayOrderCompany);
			} else if (Constants.PAY_YS_COMPANY.equals(payAccount.getProviderType())) { // 银盛
				pm = new YspayUtilImpl();
			}*/
			if (pm != null) {
				jfiPayRetMsg = pm.PayCompanyHandle(jfiPayOrderCompany);
				jfiPayRetMsg.setUrl(jfiPayRetMsg.getUrl());
				// //String para = (jfiPayRetMsg.getUrl().indexOf("?") == -1 ?
				// "?" : "&") + "isFull=true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			//throw new AppException("第三方支付出现异常,请联系管理员(全额)!");// + (StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : ""));
		}
		return jfiPayRetMsg;
	}

	/**
	 * 按地区支付
	 *
	 * @param jfiPayOrderCompany
	 * @param loginSysUser
	 * @return
	 */
	public JfiPayRetMsg districtPay(JfiPayOrderCompany jfiPayOrderCompany, JsysUser loginSysUser) throws Exception {
		PayModeService pm = null;// 支付公司逻辑处理公共接口
		JfiPayRetMsg jfiPayRetMsg = null;
		try {
			FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(loginSysUser.getUserCode());// 获取会员常用收货地址
			// fiCommonAddr.setProvince("163716");
			fiCommonAddr = null;
			if (fiCommonAddr != null) {
				//32省包括港澳台,汇付天下
				fiCommonAddr.setProvince("163704");
				getBusiness(jfiPayOrderCompany, "dq" + fiCommonAddr.getProvince());
				pm = PayBusiness.getPayModeService(fiCommonAddr.getProvince()); //获取支付公司对象
			} else {
				getBusiness(jfiPayOrderCompany, "dqkqDefault");
			}
			//getBusiness(jfiPayOrderCompany, "dqkqDefault");
			pm = new Bill99UtilImpl2();
/*			if (pm == null) {
				//pm = new Bill99UtilImpl2();
				//32省包括港澳台,汇付天下
				jfiPayOrderCompany.setBusiness(PayBusiness.account.get("dq163704"));// 商户号对象
				pm = PayBusiness.getPayModeService("163704"); //获取支付公司对象
			}*/
			jfiPayRetMsg = pm.PayCompanyHandle(jfiPayOrderCompany);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("第三方支付出现异常,请联系管理员(非全额)!");// + (StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : ""));
		}
		return jfiPayRetMsg;
	}

	/**
	 * 绑定商户
	 *
	 * @param jfiPayOrderCompany
	 *            商户对象
	 * @param searchCode
	 *            查询的商户的id{"sh商户和","dq地区编码"}
	 * @throws Exception
	 */
	private void getBusiness(JfiPayOrderCompany jfiPayOrderCompany, String searchCode) throws Exception {
		if (StringUtils.isNotEmpty(searchCode)) {
			jfiPayOrderCompany.setBusiness(PayBusiness.account.get(searchCode));
			if (jfiPayOrderCompany.getBusiness() == null) {
				jfiPayOrderCompany.setBusiness(Bill99Constants.account.get(searchCode.substring(2)));
			}
			if (jfiPayOrderCompany.getBusiness() != null) {
				jfiPayOrderCompany.setPayAccount(StringUtils.isEmpty(jfiPayOrderCompany.getBusiness().get("merchantid")) ? jfiPayOrderCompany.getBusiness().get("memberCode")
						: jfiPayOrderCompany.getBusiness().get("merchantid"));
			} else if (jfiPayOrderCompany.getBusiness() == null) {
				throw new AppException("不存在商户在");
			}
		} else {
			throw new AppException("不存在的searchCode:" + searchCode);
		}
	}

	private void getBusiness(JfiPayOrderCompany jfiPayOrderCompany,FiBillAccount payAccount) throws Exception{
		try{
			Map<String, String> bsMap = new HashMap<String, String>();
			bsMap.put("merchantid", payAccount.getBillAccountCode());// ##商户号
			bsMap.put("keyPassword", payAccount.getBillAccountPassword());// ##商户号密码
			bsMap.put("password", payAccount.getPassword());//密码或者密钥地址
			jfiPayOrderCompany.setBusiness(bsMap);
		}catch (Exception e) {
			e.printStackTrace();
			throw new AppException("不存在的payAccount:" + payAccount);
		}
		throw new AppException("不存在的payAccount:" + payAccount);
	}

	/**
	 * @Description: 后台进行POST请求(请写在代码执行结尾)
	 * @return void 返回类型
	 */
	private String doBgPostReq(JfiPayRetMsg payMsg) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("\n").append("<html>");
		sb.append("\n").append("<head><meta http-equiv='Content-Type' content='text/html; charset=GBK'></head>");
		sb.append("\n").append("<body>");
		sb.append("\n").append("<form name='postSubmit' method='post' action='" + payMsg.getUrl() + "' >");
//		Map<?, ?> paramMap = payMsg.getDataMap();
//		for (Object key : paramMap.keySet()) {
//			// System.out.println(key + "=" + paramMap.get(key));
//			sb.append("\n").append("<input type='hidden' name='" + key + "' value='" + paramMap.get(key) + "".trim() + "'>");
//		}
		sb.append("\n").append("<input type='hidden' name='pGateWayReq' value='" + payMsg.getXmlStr() + "".trim() + "'>");
		sb.append("\n").append("</form>");
		sb.append("\n").append("<script>");
		sb.append("\n").append("  document.postSubmit.submit()");
		sb.append("\n").append("</script>");
		sb.append("\n").append("</body>");
		sb.append("\n").append("</html>");
		System.err.println(sb);
		return sb.toString();
	}

	private String closeParent() throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("\n").append("<script>");
		sb.append("\n").append("  self.opener.location.reload();");
		sb.append("\n").append("  window.close();");
		sb.append("\n").append("</script>");
		System.err.println(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
		String url = ("sdfsdafd?dfadsf".indexOf("?") == -1 ? "?" : "&") + "isFull=true";
		System.out.println(url);
	}
}
