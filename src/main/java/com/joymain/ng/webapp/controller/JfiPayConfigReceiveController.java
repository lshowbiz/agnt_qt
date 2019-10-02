package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.service.JpmMemberConfigManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JpmMemberConfig;
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
 * 酒业电子存折支付处理
 * 
 * @author shiyh
 * 
 */
@Controller
@RequestMapping("/payConfigReceive*")
public class JfiPayConfigReceiveController extends BaseFormController {

	private JpmMemberConfigManager jpmMemberConfigManager = null;

	@Autowired
	public void setJpmMemberConfigManager(
			JpmMemberConfigManager jpmMemberConfigManager) {
		this.jpmMemberConfigManager = jpmMemberConfigManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String isAllPay = request.getParameter("isAllPay");// isAllPay代表全部用电子存折支付
		
		// 判断是否全部采用电子存折支付
		if (!StringUtils.isEmpty(isAllPay)) {

			JsysUser operatorSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();// 操作用户

			String orderId = request.getParameter("orderId");
			JpmMemberConfig jpmMemberConfig = jpmMemberConfigManager.get(new Long(orderId));
			
			try {

				// 电子存折支付审单
				jpmMemberConfigManager.checkJpmMemberConfig(orderId, operatorSysUser);
			} catch (AppException app) {

				// 支付失败
				saveMessage(request, LocaleUtil.getLocalText("opration.pay.fail"));
				return "jpoWineMemberOrders/orderConfigList?molId=" + jpmMemberConfig.getMolId()+"&productNo="+jpmMemberConfig.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
			} catch (Exception e) {
				
				// 支付失败
				saveMessage(request, LocaleUtil.getLocalText("opration.pay.fail"));
				return "jpoWineMemberOrders/orderConfigList?molId=" + jpmMemberConfig.getMolId()+"&productNo="+jpmMemberConfig.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
			}

			// 支付成功
			saveMessage(request, LocaleUtil.getLocalText("opration.pay.success"));
			return "jpoWineMemberOrders/orderConfigList?molId=" + jpmMemberConfig.getMolId()+"&productNo="+jpmMemberConfig.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
		}

		return "jpoWineMemberOrders/orderAll";
	}
}
