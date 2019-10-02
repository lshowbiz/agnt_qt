package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiFundbookTempManager;
import com.joymain.ng.model.FiFundbookTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiFundbookTemps/")
public class FiFundbookTempController {
    private FiFundbookTempManager fiFundbookTempManager;

    @Autowired
    public void setFiFundbookTempManager(FiFundbookTempManager fiFundbookTempManager) {
        this.fiFundbookTempManager = fiFundbookTempManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiFundbookTempManager.search(query, FiFundbookTemp.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiFundbookTempManager.getAll());
        }
        return model;
    }
    
}
