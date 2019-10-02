package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JbdSendRecordNoteManager;
import com.joymain.ng.model.JbdSendRecordNote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jbdSendRecordNotes/")
public class JbdSendRecordNoteController {
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;

    @Autowired
    public void setJbdSendRecordNoteManager(JbdSendRecordNoteManager jbdSendRecordNoteManager) {
        this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdSendRecordNoteManager.search(query, JbdSendRecordNote.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdSendRecordNoteManager.getAll());
        }
        return model;
    }
    
}
