package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiBillAccountWarningManager;
import com.joymain.ng.model.FiBillAccountWarning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiBillAccountWarnings/")
public class FiBillAccountWarningController {
    private FiBillAccountWarningManager fiBillAccountWarningManager;

    @Autowired
    public void setFiBillAccountWarningManager(FiBillAccountWarningManager fiBillAccountWarningManager) {
        this.fiBillAccountWarningManager = fiBillAccountWarningManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiBillAccountWarningManager.search(query, FiBillAccountWarning.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiBillAccountWarningManager.getAll());
        }
        return model;
    }
    
}
