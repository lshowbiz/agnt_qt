package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.model.JmiLinkRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jmiLinkRefs*")
public class JmiLinkRefController {
    private JmiLinkRefManager jmiLinkRefManager;

    @Autowired
    public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
        this.jmiLinkRefManager = jmiLinkRefManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jmiLinkRefManager.search(query, JmiLinkRef.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jmiLinkRefManager.getAll());
        }
        return model;
    }
}
