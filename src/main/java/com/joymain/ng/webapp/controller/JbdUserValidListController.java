package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JbdUserValidListManager;
import com.joymain.ng.model.JbdUserValidList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jbdUserValidLists/")
public class JbdUserValidListController {
    private JbdUserValidListManager jbdUserValidListManager;

    @Autowired
    public void setJbdUserValidListManager(JbdUserValidListManager jbdUserValidListManager) {
        this.jbdUserValidListManager = jbdUserValidListManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdUserValidListManager.search(query, JbdUserValidList.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdUserValidListManager.getAll());
        }
        return model;
    }
    
}
