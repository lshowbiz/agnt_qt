package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpmProductWineTemplateManager;
import com.joymain.ng.model.JpmProductWineTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpmProductWineTemplates/")
public class JpmProductWineTemplateController {
    private JpmProductWineTemplateManager jpmProductWineTemplateManager;

    @Autowired
    public void setJpmProductWineTemplateManager(JpmProductWineTemplateManager jpmProductWineTemplateManager) {
        this.jpmProductWineTemplateManager = jpmProductWineTemplateManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpmProductWineTemplateManager.search(query, JpmProductWineTemplate.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpmProductWineTemplateManager.getAll());
        }
        return model;
    }
    
}
