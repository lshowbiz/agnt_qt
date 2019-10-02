package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.PdLogisticsBaseManager;
import com.joymain.ng.model.PdLogisticsBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pdLogisticsBases/")
public class PdLogisticsBaseController {
    private PdLogisticsBaseManager pdLogisticsBaseManager;

    @Autowired
    public void setPdLogisticsBaseManager(PdLogisticsBaseManager pdLogisticsBaseManager) {
        this.pdLogisticsBaseManager = pdLogisticsBaseManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(pdLogisticsBaseManager.search(query, PdLogisticsBase.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(pdLogisticsBaseManager.getAll());
        }
        return model;
    }
    
}
