package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.joymain.ng.service.AmNewManager;
import com.joymain.ng.model.AmNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/amNews/")
public class AmNewController {
    private AmNewManager amNewManager;

    @Autowired
    public void setAmNewManager(AmNewManager amNewManager) {
        this.amNewManager = amNewManager;
    }

    @RequestMapping("/showNews")
    public String showNewList(HttpServletRequest request){
    	List<AmNew> amNews = amNewManager.findNewByDate("2012-03-01 00:00","2012-03-29 23:59");
        
        request.setAttribute("amNews", amNews);
    	return "showNews";
    }
   
}
