package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JsysConfigKeyManager;
import com.joymain.ng.model.JsysConfigKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jsysConfigKeys*")
public class JsysConfigKeyController {
    private JsysConfigKeyManager jsysConfigKeyManager;

    @Autowired
    public void setJsysConfigKeyManager(JsysConfigKeyManager jsysConfigKeyManager) {
        this.jsysConfigKeyManager = jsysConfigKeyManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jsysConfigKeyManager.search(query, JsysConfigKey.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jsysConfigKeyManager.getAll());
        }
        return model;
    }
}
