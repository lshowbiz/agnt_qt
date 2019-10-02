package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.Jfi99billLogManager;
import com.joymain.ng.model.Jfi99billLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jfi99billLogs/")
public class Jfi99billLogController {
    private Jfi99billLogManager jfi99billLogManager;

    @Autowired
    public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
        this.jfi99billLogManager = jfi99billLogManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jfi99billLogManager.search(query, Jfi99billLog.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jfi99billLogManager.getAll());
        }
        return model;
    }
    
}
