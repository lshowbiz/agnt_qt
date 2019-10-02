package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiSubStoreManager;
import com.joymain.ng.util.MeteorUtil;

@Controller
public class JmiSubStoreController {
    private JmiSubStoreManager jmiSubStoreManager;

    @Autowired
    public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
        this.jmiSubStoreManager = jmiSubStoreManager;
    }


    @RequestMapping(value="/jmiSubStores",method = RequestMethod.GET)
    public String getJmiSubStores(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
	    Map map=new HashMap();
	    map.put("jmiMember.userCode", defSysUser.getUserCode());
    	List jmiSubStores=jmiSubStoreManager.getJmiSubStoreList(map);
    	if(!MeteorUtil.isBlankByList(jmiSubStores)){
    		request.setAttribute("jmiSubStoresExist", "jmiSubStoresExist");
		}
    	request.setAttribute("jmiSubStores", jmiSubStores);
    	
    	return "jmiSubStores";
    }

    @RequestMapping(value="/jmiSubStoreAddrs",method = RequestMethod.GET)
    public String getJmiSubStoreAdds(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
	    Map map=new HashMap();
	    map.put("jmiMember.userCode", defSysUser.getUserCode());
    	List jmiSubStoreAddrs=jmiSubStoreManager.getJmiSubStoreList(map);
    	
    	request.setAttribute("jmiSubStoreAddrs", jmiSubStoreAddrs);
    	
    	return "jmiSubStoreAddrs";
    }
}
