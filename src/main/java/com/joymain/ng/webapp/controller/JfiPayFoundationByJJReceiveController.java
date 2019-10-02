package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公益基金使用发展基金支付接收处理
 * 
 * @author shiyh
 * 
 */
@Controller
@RequestMapping("/payFoundationReceiveByJJ*")
public class JfiPayFoundationByJJReceiveController extends BaseFormController {

	private FoundationOrderManager foundationOrderManager = null;

	@Autowired
	public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
		this.foundationOrderManager = foundationOrderManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String success = getSuccessView();

		JsysUser operatorSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// 操作用户

		String orderId = request.getParameter("orderId");
		FoundationOrder foundationOrder = foundationOrderManager.get(new Long(orderId));
		
		//计算发展基金、电子存折应该扣款的金额
		BigDecimal jJamount = new BigDecimal(request.getParameter("amount"));//发展基金扣款部分
		BigDecimal cZamount = foundationOrder.getAmount().subtract(jJamount);//电子存折扣款部分
		
		try {

			// 电子存折+发展基金支付审单
			foundationOrderManager.checkFoundationOrderByJJ(foundationOrder, jJamount, cZamount, operatorSysUser);
		} catch (AppException app) {

			// 支付失败
			saveMessage(request, LocaleUtil.getLocalText("opration.pay.fail"));
			return "redirect:payFoundation?orderId=" + orderId;
		} catch (Exception e) {
			
			// 支付失败
			saveMessage(request, LocaleUtil.getLocalText("opration.pay.fail"));
			return "redirect:payFoundation?orderId=" + orderId;
		}

		// 支付成功
		saveMessage(request, LocaleUtil.getLocalText("opration.pay.success"));
		return "redirect:foundationOrders";
	}
}
