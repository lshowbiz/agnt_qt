package com.joymain.ng.webapp.controller;

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
 * 公益基金电子存折支付接收处理
 * 
 * @author shiyh
 * 
 */
@Controller
@RequestMapping("/payFoundationReceive*")
public class JfiPayFoundationReceiveController extends BaseFormController {

	private FoundationOrderManager foundationOrderManager = null;

	@Autowired
	public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
		this.foundationOrderManager = foundationOrderManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String success = getSuccessView();

		// 1、全部采用电子存折支付
		String isAllPay = request.getParameter("isAllPay");// isAllPay代表全部用电子存折支付
		// 判断是否全部采用电子存折支付
		if (!StringUtils.isEmpty(isAllPay)) {

			// 电子存折支付审单
			JsysUser operatorSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// 操作用户

			String orderId = request.getParameter("orderId");
			FoundationOrder foundationOrder = foundationOrderManager.get(new Long(orderId));
			
			try {

				foundationOrderManager.checkFoundationOrder(foundationOrder,operatorSysUser);
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

		return "redirect:foundationOrders";
	}
}
