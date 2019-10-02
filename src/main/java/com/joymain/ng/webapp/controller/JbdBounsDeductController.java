package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JbdBounsDeductManager;
import com.joymain.ng.model.JbdBounsDeduct;
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
public class JbdBounsDeductController {
    private JbdBounsDeductManager jbdBounsDeductManager;

    @Autowired
    public void setJbdBounsDeductManager(JbdBounsDeductManager jbdBounsDeductManager) {
        this.jbdBounsDeductManager = jbdBounsDeductManager;
    }

    

    @RequestMapping(value="/jbdBounsDeducts",method = RequestMethod.GET)
    public String getJbdBounsDeduct(HttpServletRequest request){

    	JsysUser defSysUser = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	Map map=new HashMap();
    	map.put("userCode", defSysUser.getUserCode());
    	List jbdBounsDeducts=jbdBounsDeductManager.getJbdBounsDeduct(map);
    	
    	request.setAttribute("jbdBounsDeducts", jbdBounsDeducts);
    	
    	return "jbdBounsDeducts";
    }
    
}
