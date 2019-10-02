package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JbdTravelPoint2014Manager;
import com.joymain.ng.model.JbdTravelPoint2014;
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
public class JbdTravelPoint2014Controller {
    private JbdTravelPoint2014Manager jbdTravelPoint2014Manager;

    @Autowired
    public void setJbdTravelPoint2014Manager(JbdTravelPoint2014Manager jbdTravelPoint2014Manager) {
        this.jbdTravelPoint2014Manager = jbdTravelPoint2014Manager;
    }


    @RequestMapping(value="/jbdTravelPoint2014s",method = RequestMethod.GET)
    public String getJbdTravelPoint2014s(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	request.setAttribute("jbdTravelPoint2014", jbdTravelPoint2014Manager.get(defSysUser.getUserCode()));
    	return "jbdTravelPoint2014s";
    }
}
