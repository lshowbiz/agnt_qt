package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpmCardSeqManager;
import com.joymain.ng.model.JpmCardSeq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpmCardSeqs/")
public class JpmCardSeqController {
    private JpmCardSeqManager jpmCardSeqManager;

    @Autowired
    public void setJpmCardSeqManager(JpmCardSeqManager jpmCardSeqManager) {
        this.jpmCardSeqManager = jpmCardSeqManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpmCardSeqManager.search(query, JpmCardSeq.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpmCardSeqManager.getAll());
        }
        return model;
    }
    
}
