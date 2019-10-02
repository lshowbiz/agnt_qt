package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.PdNotChangeProductManager;
import com.joymain.ng.model.PdNotChangeProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pdNotChangeProducts/")
public class PdNotChangeProductController {
    private PdNotChangeProductManager pdNotChangeProductManager;

    @Autowired
    public void setPdNotChangeProductManager(PdNotChangeProductManager pdNotChangeProductManager) {
        this.pdNotChangeProductManager = pdNotChangeProductManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(pdNotChangeProductManager.search(query, PdNotChangeProduct.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(pdNotChangeProductManager.getAll());
        }
        return model;
    }
    
}
