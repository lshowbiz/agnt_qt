package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JmiTicketManager;
import com.joymain.ng.model.JmiTicket;
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
public class JmiTicketController {
    private JmiTicketManager jmiTicketManager;

    @Autowired
    public void setJmiTicketManager(JmiTicketManager jmiTicketManager) {
        this.jmiTicketManager = jmiTicketManager;
    }



    @RequestMapping(value="/jmiTickets",method = RequestMethod.GET)
    public String getJmiTickets(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	request.setAttribute("jmiTickets", jmiTicketManager.getJmiTicketByUserCode(defSysUser.getUserCode()));
    	
    	return "jmiTickets";
    }
    
}
