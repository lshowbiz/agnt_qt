package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.InwIntegrationTotalManager;
import com.joymain.ng.model.InwIntegrationTotal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/inwIntegrationTotals/")
public class InwIntegrationTotalController {
    private InwIntegrationTotalManager inwIntegrationTotalManager;

    @Autowired
    public void setInwIntegrationTotalManager(InwIntegrationTotalManager inwIntegrationTotalManager) {
        this.inwIntegrationTotalManager = inwIntegrationTotalManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(inwIntegrationTotalManager.search(query, InwIntegrationTotal.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(inwIntegrationTotalManager.getAll());
        }
        return model;
    }
    
}
