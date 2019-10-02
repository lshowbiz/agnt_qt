package com.joymain.ng.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpoShoppingCartOrderManager;

@Controller
public class JpoMemberShopCartController {
    @Autowired
    private JpoShoppingCartOrderManager jpoShoppingCartOrderManager;


    @RequestMapping(value="/jpoMemberShopCartNum",method = RequestMethod.GET)
    @ResponseBody
    public int getJpoMemberShopCartNum(){
    	int num=0;
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	JpoShoppingCart jpoShoppingCart=new JpoShoppingCart();
		jpoShoppingCart.setUserCode(defSysUser.getUserCode());
		jpoShoppingCart.setCompanyCode(defSysUser.getCompanyCode());
		num=jpoShoppingCartOrderManager.getShoppinigCartSum(jpoShoppingCart);
    	
    	return num;
    	
    }
    
}
