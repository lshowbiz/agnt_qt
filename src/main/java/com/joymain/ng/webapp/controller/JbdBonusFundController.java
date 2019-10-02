package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JbdBonusFundManager;
import com.joymain.ng.model.JbdBonusFund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jbdBonusFunds/")
public class JbdBonusFundController {
    private JbdBonusFundManager jbdBonusFundManager;

    @Autowired
    public void setJbdBonusFundManager(JbdBonusFundManager jbdBonusFundManager) {
        this.jbdBonusFundManager = jbdBonusFundManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdBonusFundManager.search(query, JbdBonusFund.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdBonusFundManager.getAll());
        }
        return model;
    }
    
}
