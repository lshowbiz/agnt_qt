package com.joymain.ng.webapp.controller;

import java.util.List;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/foundationOrders*")
public class FoundationOrderController {
    private FoundationOrderManager foundationOrderManager;

    @Autowired
    public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager;
    }

    @RequestMapping(method = RequestMethod.GET)
   public Model handleRequest(@RequestParam(value="dealStartTime", required=false) String dealStartTime,@RequestParam(value="dealEndTime", required=false) String dealEndTime)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	
        	//当前用户
        	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
        	List<FoundationOrder> foundationOrderList = foundationOrderManager.getFoundationOrdersByUserCode(jsysUser.getUserCode(), dealStartTime, dealEndTime);
        	model.addAttribute("foundationOrderList", foundationOrderList);
        	
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());

        }
        return model;
    }
    
}
