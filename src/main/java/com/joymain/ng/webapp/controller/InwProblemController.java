package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.InwProblemManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InwProblemController {
    private InwProblemManager inwProblemManager;

    @Autowired
    public void setInwProblemManager(InwProblemManager inwProblemManager) {
        this.inwProblemManager = inwProblemManager;
    }

   
    /**
     * 创新共赢的共赢帮助的初始化查询
     * @author 2013-08-30 gw
     * @param request
     * @return String
     */
    @RequestMapping("/inwProblems")
    public String getInwProblemInit(HttpServletRequest request){
    	 String returnView = "inwProblems";
    	 return returnView;
    }
    
    /**
     * 创新共赢的共赢帮助的详细查询
     * @author gw 2013-08-30 
     * @param request
     * @return String
     */
    @RequestMapping("/inwProblemDetail")
    public String getInwProblemDetail(HttpServletRequest request){
    	String returnView= "inwProblemDetail";
    	String species = request.getParameter("species");
    	List inwProblemList = inwProblemManager.getInwProblemDetail(species);
    	request.setAttribute("inwProblemList", inwProblemList);
    	return returnView;
    }
    
}
