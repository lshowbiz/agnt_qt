package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JbdTravelPointManager;
import com.joymain.ng.model.JbdTravelPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jbdTravelPoints*")
public class JbdTravelPointController {
    private JbdTravelPointManager jbdTravelPointManager;

    @Autowired
    public void setJbdTravelPointManager(JbdTravelPointManager jbdTravelPointManager) {
        this.jbdTravelPointManager = jbdTravelPointManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdTravelPointManager.search(query, JbdTravelPoint.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdTravelPointManager.getAll());
        }
        return model;
    }
}
