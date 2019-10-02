package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.MiCoinLogManager;
import com.joymain.ng.model.MiCoinLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miCoinLogs/")
public class MiCoinLogController {
    private MiCoinLogManager miCoinLogManager;

    @Autowired
    public void setMiCoinLogManager(MiCoinLogManager miCoinLogManager) {
        this.miCoinLogManager = miCoinLogManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(miCoinLogManager.search(query, MiCoinLog.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(miCoinLogManager.getAll());
        }
        return model;
    }
    
}
