package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JfiDepositBalanceManager;
import com.joymain.ng.model.JfiDepositBalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jfiDepositBalances/")
public class JfiDepositBalanceController {
    private JfiDepositBalanceManager jfiDepositBalanceManager;

    @Autowired
    public void setJfiDepositBalanceManager(JfiDepositBalanceManager jfiDepositBalanceManager) {
        this.jfiDepositBalanceManager = jfiDepositBalanceManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jfiDepositBalanceManager.search(query, JfiDepositBalance.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jfiDepositBalanceManager.getAll());
        }
        return model;
    }
    
}
