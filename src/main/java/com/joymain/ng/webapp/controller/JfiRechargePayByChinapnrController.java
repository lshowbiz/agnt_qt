package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.util.chinapnr.Chinapnr;

/**
 * 汇付天下充值充值
 * 
 * @author lzg
 * 
 */
@Controller
@RequestMapping("/jfiRechPayByChinapnr*")
public class JfiRechargePayByChinapnrController {

	@Autowired
	private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.setAttribute("jsysUser", jsysUser);

		JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jsysUser.getUserCode());
		request.setAttribute("bankbook", jfiBankbookBalance.getBalance());

		Chinapnr chinapnr = new Chinapnr();
		// flag(0:充值 1:订单支付)
		request.setAttribute("flag", 0);
		return new ModelAndView("jfiRechargePayByChinapnr", "chinapnr", chinapnr);
	}
}
