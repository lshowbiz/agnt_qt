package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiBillAccountManager;
import com.joymain.ng.model.FiBillAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiBillAccounts/")
public class FiBillAccountController {
    private FiBillAccountManager fiBillAccountManager;

    @Autowired
    public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
        this.fiBillAccountManager = fiBillAccountManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiBillAccountManager.search(query, FiBillAccount.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiBillAccountManager.getAll());
        }
        return model;
    }
    
}
