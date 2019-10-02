package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JmiMemberLogManager;
import com.joymain.ng.model.JmiMemberLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jmiMemberLogs/")
public class JmiMemberLogController {
    private JmiMemberLogManager jmiMemberLogManager;

    @Autowired
    public void setJmiMemberLogManager(JmiMemberLogManager jmiMemberLogManager) {
        this.jmiMemberLogManager = jmiMemberLogManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jmiMemberLogManager.search(query, JmiMemberLog.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jmiMemberLogManager.getAll());
        }
        return model;
    }
    
}
