package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.PublicScheduleManager;
import com.joymain.ng.model.PublicSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/publicSchedules/")
public class PublicScheduleController {
    private PublicScheduleManager publicScheduleManager;

    @Autowired
    public void setPublicScheduleManager(PublicScheduleManager publicScheduleManager) {
        this.publicScheduleManager = publicScheduleManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(publicScheduleManager.search(query, PublicSchedule.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(publicScheduleManager.getAll());
        }
        return model;
    }
    
}
