package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.JbdTravelPoint2015Manager;
import com.joymain.ng.service.JbdTravelPointAllManager;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JbdTravelPoint2015Controller {
    private JbdTravelPoint2015Manager jbdTravelPoint2015Manager;
    private JbdTravelPointAllManager jbdTravelPointAllManager;

    @Autowired
    public void setJbdTravelPointAllManager(JbdTravelPointAllManager jbdTravelPointAllManager) {
        this.jbdTravelPointAllManager = jbdTravelPointAllManager;
    }

    @Autowired
    public void setJbdTravelPoint2015Manager(JbdTravelPoint2015Manager jbdTravelPoint2015Manager) {
        this.jbdTravelPoint2015Manager = jbdTravelPoint2015Manager;
    }



    @RequestMapping(value="/jbdTravelPoint2015s",method = RequestMethod.GET)
    public String getJbdTravelPoint2015s(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	request.setAttribute("jbdTravelPoint2015", jbdTravelPoint2015Manager.get(defSysUser.getUserCode()));
    	List jbdTravelPointAllList = jbdTravelPointAllManager.getJbdTravelPointAlls(defSysUser.getUserCode());
    	request.setAttribute("jbdTravelPointAllList", jbdTravelPointAllList);
    	return "jbdTravelPoint2015s";
    }
    
}

