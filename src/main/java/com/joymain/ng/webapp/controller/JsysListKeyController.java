package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JsysListKeyManager;
import com.joymain.ng.model.JsysListKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jsysListKeys*")
public class JsysListKeyController {
    private JsysListKeyManager jsysListKeyManager;

    @Autowired
    public void setJsysListKeyManager(JsysListKeyManager jsysListKeyManager) {
        this.jsysListKeyManager = jsysListKeyManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jsysListKeyManager.search(query, JsysListKey.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jsysListKeyManager.getAll());
        }
        return model;
    }
}
