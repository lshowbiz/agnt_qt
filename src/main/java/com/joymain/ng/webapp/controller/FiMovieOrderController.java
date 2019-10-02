package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiMovieOrderManager;
import com.joymain.ng.model.FiMovieOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiMovieOrders/")
public class FiMovieOrderController {
    private FiMovieOrderManager fiMovieOrderManager;

    @Autowired
    public void setFiMovieOrderManager(FiMovieOrderManager fiMovieOrderManager) {
        this.fiMovieOrderManager = fiMovieOrderManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiMovieOrderManager.search(query, FiMovieOrder.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiMovieOrderManager.getAll());
        }
        return model;
    }
    
}
