package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.PdLogisticsBaseDetailManager;
import com.joymain.ng.model.PdLogisticsBaseDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pdLogisticsBaseDetails/")
public class PdLogisticsBaseDetailController {
    private PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager;

    @Autowired
    public void setPdLogisticsBaseDetailManager(PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager) {
        this.pdLogisticsBaseDetailManager = pdLogisticsBaseDetailManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(pdLogisticsBaseDetailManager.search(query, PdLogisticsBaseDetail.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(pdLogisticsBaseDetailManager.getAll());
        }
        return model;
    }
    
}
