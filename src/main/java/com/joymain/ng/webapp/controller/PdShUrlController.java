package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.PdShUrlManager;
import com.joymain.ng.model.PdShUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pdShUrls/")
public class PdShUrlController {
    private PdShUrlManager pdShUrlManager;

    @Autowired
    public void setPdShUrlManager(PdShUrlManager pdShUrlManager) {
        this.pdShUrlManager = pdShUrlManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(pdShUrlManager.search(query, PdShUrl.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(pdShUrlManager.getAll());
        }
        return model;
    }
    
}
