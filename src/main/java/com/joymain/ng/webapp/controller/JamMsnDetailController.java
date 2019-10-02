package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JamMsnDetailManager;
import com.joymain.ng.model.JamMsnDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jamMsnDetails*")
public class JamMsnDetailController {
    private JamMsnDetailManager jamMsnDetailManager;

    @Autowired
    public void setJamMsnDetailManager(JamMsnDetailManager jamMsnDetailManager) {
        this.jamMsnDetailManager = jamMsnDetailManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jamMsnDetailManager.search(query, JamMsnDetail.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jamMsnDetailManager.getAll());
        }
        return model;
    }
}
