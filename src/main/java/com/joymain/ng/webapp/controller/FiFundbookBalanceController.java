package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiFundbookBalanceManager;
import com.joymain.ng.model.FiFundbookBalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiFundbookBalances/")
public class FiFundbookBalanceController {
    private FiFundbookBalanceManager fiFundbookBalanceManager;

    @Autowired
    public void setFiFundbookBalanceManager(FiFundbookBalanceManager fiFundbookBalanceManager) {
        this.fiFundbookBalanceManager = fiFundbookBalanceManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiFundbookBalanceManager.search(query, FiFundbookBalance.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiFundbookBalanceManager.getAll());
        }
        return model;
    }
    
}
