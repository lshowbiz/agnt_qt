package com.joymain.ng.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.service.PdExchangeOrderBackManager;
import com.joymain.ng.service.PdExchangeOrderDetailManager;
import com.joymain.ng.service.PdExchangeOrderManager;

@Controller
@RequestMapping("/pdExchangeOrders/")
public class PdExchangeOrderController {
	private JsysUserRoleManager jsysUserRoleManager;
	private PdExchangeOrderManager pdExchangeOrderManager;
    private PdExchangeOrderBackManager pdExchangeOrderBackManager;
    private PdExchangeOrderDetailManager pdExchangeOrderDetailManager;
    
    @Autowired
    public void setPdExchangeOrderManager(PdExchangeOrderManager pdExchangeOrderManager) {
        this.pdExchangeOrderManager = pdExchangeOrderManager;
    }

    public void setJsysUserRoleManager(JsysUserRoleManager jsysUserRoleManager) {
		this.jsysUserRoleManager = jsysUserRoleManager;
	}
    
    public void setPdExchangeOrderBackManager(
			PdExchangeOrderBackManager pdExchangeOrderBackManager) {
		this.pdExchangeOrderBackManager = pdExchangeOrderBackManager;
	}

	public void setPdExchangeOrderDetailManager(
			PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
		this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
	}

	//换货单提交
	@RequestMapping(value = "orderView", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String strAction = request.getParameter("strAction");
		
		//得到页面传递过去的原订单编号orderNo
		String orderNo = request.getParameter("orderNo");
		String view = "pd/pdExchangeOrderList";
		PdExchangeOrder pdExchangeOrder = new PdExchangeOrder();
		
		//根据原订单编号获得所有的原订单产品
		List<PdExchangeOrderBack> pdExchangeOrderBacks = pdExchangeOrderBackManager.getPdExchangeOrderBacks();
		request.setAttribute("pdExchangeOrderBacks", pdExchangeOrderBacks);
		
		if ("newPdExchangeOrder".equals(strAction)) {
			view = "pd/pdExchangeOrder.jsp";
//			view = "redirect:/pdExchangeOrders/submitPdExchangeOrder?strAction=newPdExchangeOrder&moId="+ moId;
			return new ModelAndView(view);
		} else if ("savePdExchangeOrder".equals(strAction)) {
			
			String moId = request.getParameter("moId");
			view = "redirect:/editPdExchangeOrder.html?strAction=addPdExchangeOrder&moId="+ moId;
			
			return new ModelAndView(view);


		}else if ("statisticPdExchangeOrder".equals(strAction)) {
			return null;
		}
		return null;
	}
    
}
