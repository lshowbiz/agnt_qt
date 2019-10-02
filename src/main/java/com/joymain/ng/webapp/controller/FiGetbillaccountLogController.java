package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiGetbillaccountLogManager;
import com.joymain.ng.model.FiGetbillaccountLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiGetbillaccountLogs/")
public class FiGetbillaccountLogController {
    private FiGetbillaccountLogManager fiGetbillaccountLogManager;

    @Autowired
    public void setFiGetbillaccountLogManager(FiGetbillaccountLogManager fiGetbillaccountLogManager) {
        this.fiGetbillaccountLogManager = fiGetbillaccountLogManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiGetbillaccountLogManager.search(query, FiGetbillaccountLog.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiGetbillaccountLogManager.getAll());
        }
        return model;
    }
    
}
