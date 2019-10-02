package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiLovecoinBalanceManager;
import com.joymain.ng.model.FiLovecoinBalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiLovecoinBalances/")
public class FiLovecoinBalanceController {
    private FiLovecoinBalanceManager fiLovecoinBalanceManager;

    @Autowired
    public void setFiLovecoinBalanceManager(FiLovecoinBalanceManager fiLovecoinBalanceManager) {
        this.fiLovecoinBalanceManager = fiLovecoinBalanceManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiLovecoinBalanceManager.search(query, FiLovecoinBalance.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiLovecoinBalanceManager.getAll());
        }
        return model;
    }
    
}
