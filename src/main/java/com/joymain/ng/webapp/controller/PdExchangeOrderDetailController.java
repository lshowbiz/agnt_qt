package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.PdExchangeOrderDetailManager;
import com.joymain.ng.model.PdExchangeOrderDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pdExchangeOrderDetails/")
public class PdExchangeOrderDetailController {
    private PdExchangeOrderDetailManager pdExchangeOrderDetailManager;

    @Autowired
    public void setPdExchangeOrderDetailManager(PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
        this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(pdExchangeOrderDetailManager.search(query, PdExchangeOrderDetail.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(pdExchangeOrderDetailManager.getAll());
        }
        return model;
    }
    
}
