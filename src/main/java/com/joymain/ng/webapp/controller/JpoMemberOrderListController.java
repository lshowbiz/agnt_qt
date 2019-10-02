package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.model.JpoMemberOrderList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpoMemberOrderLists/")
public class JpoMemberOrderListController {
    private JpoMemberOrderListManager jpoMemberOrderListManager;

    @Autowired
    public void setJpoMemberOrderListManager(JpoMemberOrderListManager jpoMemberOrderListManager) {
        this.jpoMemberOrderListManager = jpoMemberOrderListManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpoMemberOrderListManager.search(query, JpoMemberOrderList.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpoMemberOrderListManager.getAll());
        }
        return model;
    }
    
}
