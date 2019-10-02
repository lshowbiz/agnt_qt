package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JmiStoreManager;
import com.joymain.ng.model.JmiStore;
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
public class JmiStoreController {
    private JmiStoreManager jmiStoreManager;

    @Autowired
    public void setJmiStoreManager(JmiStoreManager jmiStoreManager) {
        this.jmiStoreManager = jmiStoreManager;
    }

    @RequestMapping(value="/jmiStores",method = RequestMethod.GET)
    public String getJmiStores(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	JmiStore jmiStore=jmiStoreManager.getJmiStoreByUserCode(defSysUser.getUserCode());
		if(jmiStore!=null){
    		request.setAttribute("jmiStoreExist", "jmiStoreExist");
		}
	    Map map=new HashMap();
	    map.put("jmiMember.userCode", defSysUser.getUserCode());
	    
	    List jmiStores=jmiStoreManager.getJmiStoreList(map);

    	request.setAttribute("jmiStores", jmiStores);
	    

    	return "jmiStores";
    }
    @RequestMapping(value="/jmiStoreApplys",method = RequestMethod.GET)
    public String getJmiStoresApply(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	JmiStore jmiStore=jmiStoreManager.getJmiStoreByUserCode(defSysUser.getUserCode());
		if(jmiStore!=null){
    		request.setAttribute("jmiStoreExist", "jmiStoreExist");
		}
	    Map map=new HashMap();
	    map.put("jmiMember.userCode", defSysUser.getUserCode());
	    
	    List jmiStores=jmiStoreManager.getJmiStoreList(map);

    	request.setAttribute("jmiStores", jmiStores);
	    

    	return "jmiStoreApplys";
    }
}
