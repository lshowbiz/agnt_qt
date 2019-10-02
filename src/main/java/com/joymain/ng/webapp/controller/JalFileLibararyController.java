package com.joymain.ng.webapp.controller;

import java.util.List;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalFileLibararyManager;
import com.joymain.ng.model.JalFileLibarary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jalFileLibararies*")
public class JalFileLibararyController {
    private JalFileLibararyManager jalFileLibararyManager;

    @Autowired
    public void setJalFileLibararyManager(JalFileLibararyManager jalFileLibararyManager) {
        this.jalFileLibararyManager = jalFileLibararyManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="typeId", required=false) String typeId)
    throws Exception {
    	    	
        Model model = new ExtendedModelMap();
        try {
        	
        	List<JalFileLibarary> jList=jalFileLibararyManager.getJalFileLibararyListByConditions(typeId);
            model.addAttribute(jList);
            
            List fileTypeList=jalFileLibararyManager.getFileSearchType();
            model.addAttribute("fileTypeList",fileTypeList);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jalFileLibararyManager.getAll());
        }
        return model;
    }
    
}
