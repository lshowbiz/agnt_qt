package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.model.JfiBankbookBalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jfiBankbookBalances/")
public class JfiBankbookBalanceController {
    private JfiBankbookBalanceManager jfiBankbookBalanceManager;

    @Autowired
    public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
        this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jfiBankbookBalanceManager.search(query, JfiBankbookBalance.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jfiBankbookBalanceManager.getAll());
        }
        return model;
    }
    
}
