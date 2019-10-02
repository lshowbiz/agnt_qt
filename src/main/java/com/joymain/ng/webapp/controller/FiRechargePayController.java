package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;

/**
 * 快钱支付充值
 * 
 * @author hywen
 * 
 */
@Controller
@RequestMapping("/fiRechPay*")
public class FiRechargePayController {

	@Autowired
	private FiBankbookBalanceManager fiBankbookBalanceManager = null;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.setAttribute("jsysUser", jsysUser);

		FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jsysUser.getUserCode(),"1");
		request.setAttribute("bankbook", fiBankbookBalance.getBalance());

		// flag(0:充值 1:订单支付2:基金支付)
		request.setAttribute("flag", 2);

		// if(jsysUser!=null){
		//
		// //融宝支付
		// Reapal yeePay = new Reapal();
		// return new ModelAndView("jfiRechargePayByReapal", "yeePay", yeePay);
		// }

		// if(jsysUser!=null){
		//
		// //易宝支付
		// YeePay yeePay = new YeePay();
		// return new ModelAndView("jfiRechargePayByYeePay", "yeePay", yeePay);
		// }
		//
		if (jsysUser != null) {
			// return new ModelAndView("jfiRecharge");
		}

		return new ModelAndView("fiRecharge");

	}
}
