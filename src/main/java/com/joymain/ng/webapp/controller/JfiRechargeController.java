package com.joymain.ng.webapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.bill99.Bill99Constants;
import com.joymain.ng.util.yspay.PayBusiness;

/**
 * 充值总控
 *
 * @author hywen
 *
 */
@Controller
@RequestMapping("/jfiRecharge")
public class JfiRechargeController {

	@Autowired
	private FiCommonAddrManager fiCommonAddrManager;// 常用收货地址管理

	@Autowired
	private JsysIdManager sysIdManager;// 生成ID号

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 登入用户对象
		// JfiBankbookBalance jfiBankbookBalance =
		// jfiBankbookBalanceManager.getJfiBankbookBalance(jsysUser.getUserCode());
		// request.setAttribute("bankbook",
		// jfiBankbookBalance.getBalance());//余额
		//判断是否基金支付
		String parameter = request.getParameter("flag");

		JfiPayOrderCompany jpoc = new JfiPayOrderCompany();
		jpoc.setPayAmount(new BigDecimal(request.getParameter("payAmount")));
		jpoc.setOrderNo(sysIdManager.buildIdStr("advicecode_cn")); // 充值许从新生成
		jpoc.setUserCode(jsysUser.getUserCode());
		if ("2".equals(parameter)) {
			jpoc.setPayType("2");// flag(0:充值 1:订单支付2：基金充值)
		}else{
			jpoc.setPayType("0");// flag(0:充值 1:订单支付2：基金充值)
		}
		jpoc.setMerPriv("[" + jsysUser.getUserCode() + ",0]");
		jpoc.setBackUrl(request.getSession().getServletContext().getRealPath(""));
		jpoc.setOrderTime(new Date());
		JfiPayRetMsg jfiPayRetMsg = districtPay(jpoc);

		//环迅支付返回方式变更
		if(true == jfiPayRetMsg.getIsHxPay())
		{
			response.getWriter().write(doBgPostReq(jfiPayRetMsg));
			return null;
		}
		/*
		 * response.getWriter().write(doBgPostReq(jfiPayRetMsg)); return null; 
		 * */
		ModelAndView mv = new ModelAndView("redirect:" + jfiPayRetMsg.getUrl());// redirect模式
		for (Object key : jfiPayRetMsg.getDataMap().keySet()) {
			mv.addObject(String.valueOf(key), jfiPayRetMsg.getDataMap().get(key));
		}
		return mv;
	}

	/**
	 * 按地区支付
	 *
	 * @param jfiPayOrderCompany
	 * @param loginSysUser
	 * @return
	 */
	private JfiPayRetMsg districtPay(JfiPayOrderCompany jfiPayOrderCompany) throws Exception{
		// JfiPayRetMsg payMsg = null;
		PayModeService pm = null;
		// 进行第三方支付平台判断，快钱、畅捷通等
	//	FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(jfiPayOrderCompany.getUserCode());// 获取会员常用收货地址
		FiCommonAddr fiCommonAddr =new FiCommonAddr();
		fiCommonAddr.setProvince("kqDefault");



		if (fiCommonAddr != null) {
			//32省包括港澳台
			//fiCommonAddr.setProvince("163704");
			jfiPayOrderCompany.setBusiness(PayBusiness.account.get("dq" + fiCommonAddr.getProvince()));// 商户号对象
			if(jfiPayOrderCompany.getBusiness()==null){
				jfiPayOrderCompany.setBusiness(Bill99Constants.account.get(fiCommonAddr.getProvince()));
			}
			if(jfiPayOrderCompany.getBusiness()==null){
				throw new AppException("不存在商户和");
			}
			pm = PayBusiness.getPayModeService(fiCommonAddr.getProvince()); //获取支付公司对象
			/*String[] sypay = new String[] { "163702" }; // 顺手付请求地址
			if (StringUtil.contains(sypay, fiCommonAddr.getProvince())) {
				pm = new SypayUtilImpl();
			}*/
		}else{
			//32省包括港澳台,汇付天下
			jfiPayOrderCompany.setBusiness(PayBusiness.account.get("dq163704"));// 商户号对象
			pm = PayBusiness.getPayModeService("163704"); //获取支付公司对象
		}
		return pm.PayCompanyHandle(jfiPayOrderCompany);
	}

	/**
	 * @Description: 后台进行POST请求(请写在代码执行结尾)
	 * @return void 返回类型
	 */
	@SuppressWarnings("unused")
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
		return sb.toString();
	}
}
