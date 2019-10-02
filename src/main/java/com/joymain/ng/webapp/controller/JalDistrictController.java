package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.model.JalDistrict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jalDistricts*")
public class JalDistrictController {
    private JalDistrictManager jalDistrictManager;

    @Autowired
    public void setJalDistrictManager(JalDistrictManager jalDistrictManager) {
        this.jalDistrictManager = jalDistrictManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jalDistrictManager.search(query, JalDistrict.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jalDistrictManager.getAll());
        }
        return model;
    }
}
