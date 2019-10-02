package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.model.JsysUserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jsysUserRoles/")
public class JsysUserRoleController {
    private JsysUserRoleManager jsysUserRoleManager;

    @Autowired
    public void setJsysUserRoleManager(JsysUserRoleManager jsysUserRoleManager) {
        this.jsysUserRoleManager = jsysUserRoleManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jsysUserRoleManager.search(query, JsysUserRole.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jsysUserRoleManager.getAll());
        }
        return model;
    }
    
}
