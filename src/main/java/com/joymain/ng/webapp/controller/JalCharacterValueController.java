package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalCharacterValueManager;
import com.joymain.ng.model.JalCharacterValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jalCharacterValues*")
public class JalCharacterValueController {
    private JalCharacterValueManager jalCharacterValueManager;

    @Autowired
    public void setJalCharacterValueManager(JalCharacterValueManager jalCharacterValueManager) {
        this.jalCharacterValueManager = jalCharacterValueManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jalCharacterValueManager.search(query, JalCharacterValue.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jalCharacterValueManager.getAll());
        }
        return model;
    }
}
