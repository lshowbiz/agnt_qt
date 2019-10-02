package com.joymain.ng.webapp.controller;

import java.util.List;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiSecurityDepositJournalManager;
import com.joymain.ng.service.FiSecurityDepositManager;
import com.joymain.ng.model.FiSecurityDeposit;
import com.joymain.ng.model.FiSecurityDepositJournal;
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
/**
 * 保证金查询页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/fiSecurityDeposits*")
public class FiSecurityDepositController {
    private FiSecurityDepositManager fiSecurityDepositManager;

    @Autowired
    public void setFiSecurityDepositManager(FiSecurityDepositManager fiSecurityDepositManager) {
        this.fiSecurityDepositManager = fiSecurityDepositManager;
    }
    private FiSecurityDepositJournalManager fiSecurityDepositJournalManager;

    @Autowired
    public void setFiSecurityDepositJournalManager(FiSecurityDepositJournalManager fiSecurityDepositJournalManager) {
        this.fiSecurityDepositJournalManager = fiSecurityDepositJournalManager;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	
        	//当前用户
        	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
        	FiSecurityDeposit fiSecurityDeposit=fiSecurityDepositManager.getFiSecurityDepositByUserCode(jsysUser.getUserCode());
        	model.addAttribute("fiSecurityDeposit", fiSecurityDeposit);
        	
        	//查询明细
        	List<FiSecurityDepositJournal> fiSecurityDepositJournalList=fiSecurityDepositJournalManager.getFiSecurityDepositJournalsByUserCode(jsysUser.getUserCode());
        	model.addAttribute("fiSecurityDepositJournalList", fiSecurityDepositJournalList);
            //model.addAttribute(fiSecurityDepositManager.search(query, FiSecurityDeposit.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
           // model.addAttribute(fiSecurityDepositManager.getAll());
        }
        return model;
    }
    
}
