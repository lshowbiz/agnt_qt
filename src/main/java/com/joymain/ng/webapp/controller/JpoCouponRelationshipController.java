package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpoCouponRelationshipManager;
import com.joymain.ng.model.JpoCouponRelationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpoCouponRelationships/")
public class JpoCouponRelationshipController {
    private JpoCouponRelationshipManager jpoCouponRelationshipManager;

    @Autowired
    public void setJpoCouponRelationshipManager(JpoCouponRelationshipManager jpoCouponRelationshipManager) {
        this.jpoCouponRelationshipManager = jpoCouponRelationshipManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpoCouponRelationshipManager.search(query, JpoCouponRelationship.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpoCouponRelationshipManager.getAll());
        }
        return model;
    }
    
}
