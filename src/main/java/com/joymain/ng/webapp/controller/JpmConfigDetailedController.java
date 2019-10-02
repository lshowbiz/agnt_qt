package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpmConfigDetailedManager;
import com.joymain.ng.model.JpmConfigDetailed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpmConfigDetaileds/")
public class JpmConfigDetailedController {
    private JpmConfigDetailedManager jpmConfigDetailedManager;

    @Autowired
    public void setJpmConfigDetailedManager(JpmConfigDetailedManager jpmConfigDetailedManager) {
        this.jpmConfigDetailedManager = jpmConfigDetailedManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpmConfigDetailedManager.search(query, JpmConfigDetailed.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpmConfigDetailedManager.getAll());
        }
        return model;
    }
    
}
