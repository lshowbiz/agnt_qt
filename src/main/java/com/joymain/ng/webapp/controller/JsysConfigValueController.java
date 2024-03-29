package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JsysConfigValueManager;
import com.joymain.ng.model.JsysConfigValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jsysConfigValues*")
public class JsysConfigValueController {
    private JsysConfigValueManager jsysConfigValueManager;

    @Autowired
    public void setJsysConfigValueManager(JsysConfigValueManager jsysConfigValueManager) {
        this.jsysConfigValueManager = jsysConfigValueManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jsysConfigValueManager.search(query, JsysConfigValue.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jsysConfigValueManager.getAll());
        }
        return model;
    }
}
