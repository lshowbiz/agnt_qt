package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.FoundationItemManager;
import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.bill99.Bill99;
import com.joymain.ng.model.FiCommonAddr;
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
 * 公益基金电子存折支付表单
 * @author shiyh
 *
 */
@Controller
@RequestMapping("/payFoundation*")
public class JfiPayFoundationController{

    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FoundationOrderManager foundationOrderManager = null;
    private FoundationItemManager foundationItemManager = null;
    
    @Autowired
    private FiCommonAddrManager fiCommonAddrManager;//常用收货地址管理
	

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
        
//        //进行第三方支付平台判断，快钱、畅捷通等
//      	FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(loginSysUser.getUserCode());//获取会员常用收货地址
//
//      	if(fiCommonAddr!=null){
//  			
//      		//163730青海,163729甘肃,163702北京,163716山东
//			if(("163730").equals(fiCommonAddr.getProvince()) || ("163729").equals(fiCommonAddr.getProvince()) || ("163702").equals(fiCommonAddr.getProvince()) || ("163716").equals(fiCommonAddr.getProvince())){
//				
//  				return new ModelAndView("redirect:jfiPayFoundationByChanjet?orderId=" + orderId);// 跳转到畅捷支付
//  			}            
//        }
                
        FoundationOrder foundationOrder = foundationOrderManager.get(new Long(orderId));
        request.setAttribute("foundationOrder", foundationOrder);
    	
    	//会员电子存折余额
    	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(loginSysUser.getUserCode());
    	request.setAttribute("bankbook", jfiBankbookBalance.getBalance());

        //快钱支付
        Bill99 jfi99bill = new Bill99();
        jfi99bill.setPayerName(loginSysUser.getUserName());
        jfi99bill.setOrderId(orderId);
        
        return new ModelAndView("jfiPayFoundation", "jfi99bill", jfi99bill);
    }
}
