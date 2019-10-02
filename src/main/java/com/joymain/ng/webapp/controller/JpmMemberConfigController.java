package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpmMemberConfigManager;
import com.joymain.ng.model.JpmMemberConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpmMemberConfigs/")
public class JpmMemberConfigController {
    private JpmMemberConfigManager jpmMemberConfigManager;

    @Autowired
    public void setJpmMemberConfigManager(JpmMemberConfigManager jpmMemberConfigManager) {
        this.jpmMemberConfigManager = jpmMemberConfigManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpmMemberConfigManager.search(query, JpmMemberConfig.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpmMemberConfigManager.getAll());
        }
        return model;
    }
    
}
