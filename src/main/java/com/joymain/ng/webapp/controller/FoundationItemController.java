package com.joymain.ng.webapp.controller;

import java.util.List;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FoundationItemManager;
import com.joymain.ng.model.FoundationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/foundationItems*")
public class FoundationItemController {
    private FoundationItemManager foundationItemManager;

    @Autowired
    public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
        this.foundationItemManager = foundationItemManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	
        	List<FoundationItem> foundationItemList=foundationItemManager.getFoundationItemsByStatusIsEnable();
        	model.addAttribute("foundationItemList", foundationItemList);
            
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());

        }
        return model;
    }
    
}
