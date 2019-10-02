package com.joymain.ng.webapp.controller;

import com.joymain.ng.util.ListUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.OrderCheckUtils;

/**
 * 支付他人订单控制器
 * 
 * @author 石义宏 会员系统
 */
@Controller
@SuppressWarnings({"rawtypes"})
@RequestMapping("/jpoMemberOrderPayOtherForm*")
public class JpoMemberOrderPayOtherFormController extends BaseFormController {

	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private JmiRecommendRefManager jmiRecommendRefManager;
	private FiBankbookBalanceManager fiBankbookBalanceManager = null;
	private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
	@Autowired
	private JsysUserManager jsysUserManager;
	@Autowired
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	@Autowired
	public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}

	@Autowired
	public void setFiBankbookBalanceManager(FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}

	@Autowired
	public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

	// @ModelAttribute
	// @RequestMapping(method = RequestMethod.GET)
	// protected JpoMemberOrder showForm(HttpServletRequest request)
	// throws Exception {
	//
	//
	// return new JpoMemberOrder();
	// }

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isFund = true; //是否有基金按钮
		String memberOrderNo = request.getParameter("memberOrderNo");
		String userCode = request.getParameter("userCode");
		String stepNext = request.getParameter("stepNext");

		JpoMemberOrder jpoMemberOrder = null;
		if (!StringUtils.isEmpty(memberOrderNo)) {
			memberOrderNo = memberOrderNo.trim();
			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
		}
		System.out.println("stepNext========"+stepNext);
		if ("stepNext".equals(stepNext)) {
			if(jpoMemberOrder == null){
//				response.sendRedirect("jpoMemberOrderPayOtherForm");
				this.saveMessage(request, getText("订单不存在!"));
				return  new ModelAndView("redirect:jpoMemberOrderPayOtherForm?1=1");
//				return null;
			}
			if (!StringUtil.isEmpty(userCode)) {
				userCode = userCode.trim();
				if(!checkUnderMember(request, userCode)){
					response.sendRedirect("jpoMemberOrderPayOtherForm");
					this.saveMessage(request, getText("会员不存在或不在推荐网络下"));
					return null;
				}
			}

			if (isOverQty(jpoMemberOrder)) {
				response.sendRedirect("jpoMemberOrderPayOtherForm");
				this.saveMessage(request, getText("商品已售完或者库存不足！"));
				return null;
			}
			
			//===================================订单审核==================================start
			if(!OrderCheckUtils.checkMemberOrder(this.getClass(),jpoMemberOrder,request)){
				return new ModelAndView("redirect:jpoMemberOrderPayOtherForm");
			}
			//===================================订单审核==================================end
			/*if ("93".equals(jpoMemberOrder.getOrderType())) {// || "42".equals(jpoMemberOrder.getOrderType()) || "43".equals(jpoMemberOrder.getOrderType())
				response.sendRedirect("jpoMemberOrderPayOtherForm");
				this.saveMessage(request, getText("代金券换购单类型不能使用他人支付方式！"));
				return null;
			}*/

			if(OrderCheckUtils.checkOrderType(jpoMemberOrder)){
				response.sendRedirect("jpoMemberOrderPayOtherForm");
				String displayValues=ListUtil.getListValue(jpoMemberOrder.getCompanyCode(), "orderpayother.limit",jpoMemberOrder.getOrderType());
				this.saveMessage(request,getText(displayValues)+ getText("类型不能使用他人支付方式！"));
				return null;
			}

			// Modify By WuCF 20150411 生态家套餐不能使用替他人支付
			if (null != jpoMemberOrder && !StringUtil.isEmpty(jpoMemberOrder.getProductFlag())) {
				response.sendRedirect("jpoMemberOrderPayOtherForm");
				this.saveMessage(request, getText("生态家套餐订购不能使用此支付方式！"));
				return null;
			}
			
			
			//============================2016-05-24圣诗蔓积分换购(>=5:5)================================================start
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date now = Calendar.getInstance().getTime();
			String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
	    	String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
	    	try {
		    	if(startCoin != null && endCoin != null){
					if(now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))){
						if(jpoIsOnly(jpoMemberOrder,GlobalVar.jpoList20160524)){
							response.sendRedirect("jpoMemberOrderPayOtherForm");
							this.saveMessage(request, getText("圣诗蔓积分换购促销商品订购只能本人支付！"));
							return null;
						}
					}
				}
		    }catch(Exception e){
		    	log.info("支付跳转失败!",e);
	    	}

	        //============================2016-05-24圣诗蔓积分换购(>=5:5)================================================end
			

			// 若为：已核消订单，自己的订单，公益基金
			if (StringUtils.isEmpty(memberOrderNo) || jpoMemberOrder == null || "2".equals(jpoMemberOrder.getStatus())
					|| !jpoMemberOrder.getSysUser().getUserCode().equals(userCode) ) {
				response.sendRedirect("jpoMemberOrderPayOtherForm");
				this.saveMessage(request, getText("error.poMemberOrder.null")); // 会员订单不存在
				return null;
			} else {

				JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				request.setAttribute("viewOrder", "viewOrder");

				FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(defSysUser.getUserCode(), "1");
				if(null == fiBankbookBalance){
					request.setAttribute("coin", 0);
				}else{
					request.setAttribute("coin", fiBankbookBalance.getBalance());
				}

				JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(defSysUser.getUserCode());
				if(null == jfiBankbookBalance){
					request.setAttribute("bankbook", 0);
				}else{
					request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
				}
			}
			request.setAttribute("isFund", isFund);//是否有基金按钮
			if (!StringUtils.isEmpty(memberOrderNo)) {
				// 查看是否有前台停售d商品
				Set<JpoMemberOrderList> jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
				Iterator<JpoMemberOrderList> iterator = jpoMemberOrderList.iterator();
				boolean pay = true;
				while (iterator.hasNext()) {
					JpoMemberOrderList memberOrder = iterator.next();
					String status = memberOrder.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
					// 0.停售 1.销售 2前台停售
					if (!"1".equals(status)) {
						pay = false;
						String productNo = memberOrder.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
						response.sendRedirect("jpoMemberOrderPayOtherForm");
						this.saveMessage(request, getText("编号为 " + productNo + " 的商品为前台停售状态，不能完成支付！"));
						return null;
					}
				}
				if (pay) {
					return new ModelAndView("jpoMemberOrderPayOtherForm", "jpoMemberOrder", jpoMemberOrder);
				}
			}
		}
		jpoMemberOrder = new JpoMemberOrder();
		//request.setAttribute("isFund", isFund);//是否有基金按钮
		request.setAttribute("jpoMemberOrder", jpoMemberOrder);
		return new ModelAndView("jpoMemberOrderPayOtherForm");
	}

	// protected Object formBackingObject(HttpServletRequest request)
	// throws Exception {
	// String memberOrderNo = request.getParameter("memberOrderNo");
	// JpoMemberOrder jpoMemberOrder = null;
	//
	// if (!StringUtils.isEmpty(memberOrderNo)) {
	// jpoMemberOrder =
	// jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
	// }
	// if(jpoMemberOrder==null) {
	// jpoMemberOrder = new JpoMemberOrder();
	// JsysUser sysUser=new JsysUser();
	// jpoMemberOrder.setSysUser(sysUser);
	// }
	//
	// return jpoMemberOrder;
	// }

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// JsysUser operatorSysUser = SessionLogin.getOperatorUser(request);
		// JsysUser defSysUser=SessionLogin.getLoginUser(request);
		JsysUser operatorSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// 操作用户
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// 登录用户

		String memberOrderNo = request.getParameter("memberOrderNo");
		log.info("PayOther start onsubmit orderNo is:"+memberOrderNo);
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
			if (!"1".equals(status)) {
				this.saveMessage(request, "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo() + ")已售完!");
				return "redirect:jpoMemberOrderPayOtherForm";
			}
		}
		
		String userCode = request.getParameter("userCode");
		if (!StringUtil.isEmpty(userCode) && !checkUnderMember(request, userCode)) {
			this.saveMessage(request, getText("opration.pay.fail"));
			return "redirect:jpoMemberOrderPayOtherForm";
		}
		
		//=====================Modify By WuCF 20170417 升级单针对期限90天的控制
		if(!jpoMemberOrderManager.upGradeInTime(userCode,String.valueOf(jpoMemberOrder.getMoId()))){
			this.saveMessage(request, "此订单已过升级单支付期限!");
			return "redirect:jpoMemberOrderPayOtherForm";
		}

		if (StringUtils.isEmpty(memberOrderNo) || jpoMemberOrder == null
				|| !defSysUser.getCompanyCode().equals(jpoMemberOrder.getCompanyCode()) || "2".equals(jpoMemberOrder.getStatus())
				|| !jpoMemberOrder.getSysUser().getUserCode().equals(userCode)) {
			this.saveMessage(request, getText("opration.pay.fail"));
			return "redirect:jpoMemberOrderPayOtherForm";
		}

		String password = request.getParameter("password");
		String passwordMd5 = StringUtil.encodePassword(password, "md5");
		if (!passwordMd5.equals(defSysUser.getPassword2())) {
			saveMessage(request, LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "fiMoney.fail.password"));
			return "redirect:jpoMemberOrderPayOtherForm";
		}
		
		String usercode = jpoMemberOrder.getSysUser().getUserCode();
		JsysUser order_user = jsysUserManager.get(usercode);
		if(validateOrder(jpoMemberOrder,order_user)){
			log.info(jpoMemberOrder.getMemberOrderNo() + 
					LocaleUtil.getLocalText("user.validate"));
			this.saveMessage(request, getText("user.validate"));
			return "redirect:jpoMemberOrderPayOtherForm";
		}
		
		if (isOverQty(jpoMemberOrder)) {
			this.saveMessage(request, getText("商品已售完或者库存不足！"));
			return "redirect:jpoMemberOrderPayOtherForm";
		}
		if(OrderCheckUtils.checkOrderType(jpoMemberOrder)){
			response.sendRedirect("jpoMemberOrderPayOtherForm");
			String displayValues=ListUtil.getListValue(jpoMemberOrder.getCompanyCode(), "orderpayother.limit",jpoMemberOrder.getOrderType());
			this.saveMessage(request,getText(displayValues)+ getText("类型不能使用他人支付方式！"));
			return null;
		}
		/*if ("93".equals(jpoMemberOrder.getOrderType())) {// || "42".equals(jpoMemberOrder.getOrderType()) || "43".equals(jpoMemberOrder.getOrderType())
			response.sendRedirect("jpoMemberOrderPayOtherForm");
			this.saveMessage(request, getText("亲亲订单、宝宝订单、代金券换购单类型不能使用他人支付方式！"));
			return null;
		}*/
		
		JpoMemberOrder order = new JpoMemberOrder();
		boolean b = false;
		try {
			order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
			order.setUserSpCode(defSysUser.getUserCode());
			
			String ramount = request.getParameter("amount");
			//===========================Modify By WuCF 20160808
			//(1)输入的基金金额要小于当前支付会员的基金余额
			//(2)输入的基金余额+会员存折余额>=订单金额
			FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(defSysUser.getUserCode(), "1");
			JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(defSysUser.getUserCode());
			if(new BigDecimal(ramount).compareTo(fiBankbookBalance.getBalance())==1){
				saveMessage(request, LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "基金余额不足，支付失败！"));
				return "redirect:jpoMemberOrderPayOtherForm";
			}
			
			BigDecimal yuE = fiBankbookBalance.getBalance().add(jfiBankbookBalance.getBalance());//存折+基金总余额
			if(jpoMemberOrder.getAmount2().compareTo(new BigDecimal(0))==0 
			   || jpoMemberOrder.getAmount2().compareTo(yuE)==1){
				saveMessage(request, LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "支付订单金额异常！存折和基金余额不足，支付失败！"));
				return "redirect:jpoMemberOrderPayOtherForm";
			}
			
			
			if(StringUtils.isEmpty(ramount)){
				
				log.info("checkJpoMemberOrder method"+order.getMemberOrderNo());
				jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser, "1");
				log.info("checkJpoMemberOrder method stop:"+order.getMemberOrderNo());
			}else{
				BigDecimal amount = new BigDecimal(ramount);
				if (amount.compareTo(new BigDecimal("0")) == 1) {
					order.setPayByJj("1");
					if (amount.compareTo(jpoMemberOrder.getAmount()) != -1) {
						order.setJjAmount(jpoMemberOrder.getAmount());
//						order.setAmount(new BigDecimal("0"));
					} else {
						order.setJjAmount(amount);
//						order.setAmount(jpoMemberOrder.getAmount().subtract(amount));
					}
					log.info("checkJpoMemberOrderByJJ method start:"+order.getMemberOrderNo());
					jpoMemberOrderManager.checkJpoMemberOrderByJJ(order, operatorSysUser, "1");
					log.info("checkJpoMemberOrderByJJ method stop:"+order.getMemberOrderNo());
				} else if (amount.compareTo(new BigDecimal("0")) == -1) {
					this.saveMessage(request, getText("opration.pay.fail"));
					return "redirect:jpoMemberOrderPayOtherForm";
				} else {
					log.info("checkJpoMemberOrder method"+order.getMemberOrderNo());
					jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser, "1");
					log.info("checkJpoMemberOrder method stop:"+order.getMemberOrderNo());
				}
			}
			// jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder,
			// operatorSysUser);
			// 支付成功提示
			this.saveMessage(request, getText("opration.pay.success"));
			b = true;

		} catch (AppException app) {
			app.printStackTrace();
			this.saveMessage(
					request,
					LocaleUtil.getLocalText("poMemberOrder.memberOrderNo") + ":" + jpoMemberOrder.getMemberOrderNo() + LocaleUtil.getLocalText("opration.pay.fail") + ","
							+ app.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, LocaleUtil.getLocalText("checkError.Code.111") + ":" + jpoMemberOrder.getMemberOrderNo() + LocaleUtil.getLocalText("opration.pay.fail"));
		}
		/*
		if (b) {
			try {
				jpoMemberOrder.setStatus("3");
				jpoMemberOrderManager.save(jpoMemberOrder);
				jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
				log.info(jpoMemberOrder.getMemberOrderNo() + "=======审单MQ：JpoMemberOrderPayOtherFormController");
			} catch (Exception e) {
				log.error(jpoMemberOrder.getMemberOrderNo() + "发送mq消息失败：", e);
			}
		}
		 */
		// 支付成功跳转到订单详情页面
		return "redirect:jpoMemberOrderView/orderView?moId=" + jpoMemberOrder.getMoId();
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		String[] allowedFields = { "asdfgrtgbghg" };
		binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}

	private boolean checkUnderMember(HttpServletRequest request, String userCode) {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// 登录用户
		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(defSysUser.getUserCode());

		JmiRecommendRef miRecommendRef = jmiRecommendRefManager.get(userCode);

		if (defRecommendRef == null || miRecommendRef == null) {
			return false;
		}

		// 判断推荐人是否在当前会员下
		String rdefIndex = defRecommendRef.getTreeIndex();
		String rIndex = miRecommendRef.getTreeIndex();
		if (rIndex.length() < rdefIndex.length() || !rdefIndex.equals(StringUtil.getLeft(rIndex, rdefIndex.length()))) {
			return false;
		}
		return true;
	}

}
