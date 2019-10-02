package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalLibraryPlateManager;
import com.joymain.ng.model.JalLibraryPlate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jalLibraryPlates/")
public class JalLibraryPlateController {
    private JalLibraryPlateManager jalLibraryPlateManager;

    @Autowired
    public void setJalLibraryPlateManager(JalLibraryPlateManager jalLibraryPlateManager) {
        this.jalLibraryPlateManager = jalLibraryPlateManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jalLibraryPlateManager.search(query, JalLibraryPlate.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jalLibraryPlateManager.getAll());
        }
        return model;
    }
    
}
