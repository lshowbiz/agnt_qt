package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.FoundationItemManager;
import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.bill99.Bill99;
import com.joymain.ng.util.chanjet.Chanjet;
import com.joymain.ng.model.FoundationItem;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;
import com.joymain.ng.webapp.util.SessionLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 公益基金畅捷通支付表单
 * @author shiyh
 *
 */
@Controller
@RequestMapping("/jfiPayFoundationByChanjet*")
public class JfiPayFoundationByChanjetController{

    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FoundationOrderManager foundationOrderManager = null;
    private FoundationItemManager foundationItemManager = null;

    @Autowired
    public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
        this.foundationItemManager = foundationItemManager;
    }
    @Autowired
    public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager;
    }
    @Autowired
	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	      
        //当前用户
    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	request.setAttribute("jsysUser", loginSysUser);
        
        //基金订单
        String orderId = request.getParameter("orderId");
                
        FoundationOrder foundationOrder = foundationOrderManager.get(new Long(orderId));
        request.setAttribute("foundationOrder", foundationOrder);
    	
    	//会员电子存折余额
    	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(loginSysUser.getUserCode());
    	request.setAttribute("bankbook", jfiBankbookBalance.getBalance());

    	//畅捷支付
        Chanjet chanjet = new Chanjet();
        chanjet.setOrderId(orderId);
        
        return new ModelAndView("jfiPayFoundationbyChanjet", "chanjet", chanjet);
    }
}
