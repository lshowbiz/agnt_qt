package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiCcoinJournalManager;
import com.joymain.ng.model.FiCcoinJournal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiCcoinJournals/")
public class FiCcoinJournalController {
    private FiCcoinJournalManager fiCcoinJournalManager;

    @Autowired
    public void setFiCcoinJournalManager(FiCcoinJournalManager fiCcoinJournalManager) {
        this.fiCcoinJournalManager = fiCcoinJournalManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(fiCcoinJournalManager.search(query, FiCcoinJournal.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());

        }
        return model;
    }
    
}
