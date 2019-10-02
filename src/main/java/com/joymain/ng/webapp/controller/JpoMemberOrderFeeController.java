package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpoMemberOrderFeeManager;
import com.joymain.ng.model.JpoMemberOrderFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpoMemberOrderFees/")
public class JpoMemberOrderFeeController {
    private JpoMemberOrderFeeManager jpoMemberOrderFeeManager;

    @Autowired
    public void setJpoMemberOrderFeeManager(JpoMemberOrderFeeManager jpoMemberOrderFeeManager) {
        this.jpoMemberOrderFeeManager = jpoMemberOrderFeeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpoMemberOrderFeeManager.search(query, JpoMemberOrderFee.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpoMemberOrderFeeManager.getAll());
        }
        return model;
    }
    
}
