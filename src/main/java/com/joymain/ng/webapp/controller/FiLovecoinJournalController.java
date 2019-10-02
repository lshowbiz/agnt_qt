package com.joymain.ng.webapp.controller;

import java.util.List;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiLovecoinBalanceManager;
import com.joymain.ng.service.FiLovecoinJournalManager;
import com.joymain.ng.model.FiLovecoinBalance;
import com.joymain.ng.model.FiLovecoinJournal;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiLovecoinJournals*")
public class FiLovecoinJournalController {
    private FiLovecoinJournalManager fiLovecoinJournalManager;
    private FiLovecoinBalanceManager fiLovecoinBalanceManager;

    @Autowired
    public void setFiLovecoinBalanceManager(FiLovecoinBalanceManager fiLovecoinBalanceManager) {
        this.fiLovecoinBalanceManager = fiLovecoinBalanceManager;
    }
    
    @Autowired
    public void setFiLovecoinJournalManager(FiLovecoinJournalManager fiLovecoinJournalManager) {
        this.fiLovecoinJournalManager = fiLovecoinJournalManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="dealStartTime", required=false) String dealStartTime,@RequestParam(value="dealEndTime", required=false) String dealEndTime)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	
        	//当前用户
        	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
        	List<FiLovecoinJournal> fiLovecoinJournalList = fiLovecoinJournalManager.getFiLovecoinJournalsByUserCode(jsysUser.getUserCode(),dealStartTime,dealEndTime);
        	model.addAttribute("fiLovecoinJournalList", fiLovecoinJournalList);
        	
        	FiLovecoinBalance fiLovecoinBalance=fiLovecoinBalanceManager.get(jsysUser.getUserCode());
        	model.addAttribute("fiLovecoinBalance", fiLovecoinBalance);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            
        }
        return model;
    }
    
}
