package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiCoinLogManager;
import com.joymain.ng.model.FiCoinLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiCoinLogs/")
public class FiCoinLogController {
    private FiCoinLogManager fiCoinLogManager;

    @Autowired
    public void setFiCoinLogManager(FiCoinLogManager fiCoinLogManager) {
        this.fiCoinLogManager = fiCoinLogManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiCoinLogManager.search(query, FiCoinLog.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiCoinLogManager.getAll());
        }
        return model;
    }
    
}
