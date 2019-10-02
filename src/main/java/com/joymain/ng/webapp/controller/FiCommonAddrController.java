package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.model.FiCommonAddr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiCommonAddrs/")
public class FiCommonAddrController {
    private FiCommonAddrManager fiCommonAddrManager;

    @Autowired
    public void setFiCommonAddrManager(FiCommonAddrManager fiCommonAddrManager) {
        this.fiCommonAddrManager = fiCommonAddrManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiCommonAddrManager.search(query, FiCommonAddr.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiCommonAddrManager.getAll());
        }
        return model;
    }
    
}
