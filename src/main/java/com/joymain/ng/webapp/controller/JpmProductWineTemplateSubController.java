package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpmProductWineTemplateSubManager;
import com.joymain.ng.model.JpmProductWineTemplateSub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpmProductWineTemplateSubs/")
public class JpmProductWineTemplateSubController {
    private JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager;

    @Autowired
    public void setJpmProductWineTemplateSubManager(JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager) {
        this.jpmProductWineTemplateSubManager = jpmProductWineTemplateSubManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpmProductWineTemplateSubManager.search(query, JpmProductWineTemplateSub.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpmProductWineTemplateSubManager.getAll());
        }
        return model;
    }
    
}
