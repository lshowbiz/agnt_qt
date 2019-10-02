package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JbdUserCardListManager;
import com.joymain.ng.model.JbdUserCardList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jbdUserCardLists/")
public class JbdUserCardListController {
    private JbdUserCardListManager jbdUserCardListManager;

    @Autowired
    public void setJbdUserCardListManager(JbdUserCardListManager jbdUserCardListManager) {
        this.jbdUserCardListManager = jbdUserCardListManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdUserCardListManager.search(query, JbdUserCardList.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdUserCardListManager.getAll());
        }
        return model;
    }
    
}
