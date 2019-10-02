package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiBillAccountRelationManager;
import com.joymain.ng.model.FiBillAccountRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiBillAccountRelations/")
public class FiBillAccountRelationController {
    private FiBillAccountRelationManager fiBillAccountRelationManager;

    @Autowired
    public void setFiBillAccountRelationManager(FiBillAccountRelationManager fiBillAccountRelationManager) {
        this.fiBillAccountRelationManager = fiBillAccountRelationManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiBillAccountRelationManager.search(query, FiBillAccountRelation.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiBillAccountRelationManager.getAll());
        }
        return model;
    }
    
}
