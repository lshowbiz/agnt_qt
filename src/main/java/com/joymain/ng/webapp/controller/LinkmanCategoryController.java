package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.LinkmanCategoryManager;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 客户分类的controller类
 * @author gw 2013-07-24
 */
@Controller
public class LinkmanCategoryController {
    private LinkmanCategoryManager linkmanCategoryManager;

    @Autowired
    public void setLinkmanCategoryManager(LinkmanCategoryManager linkmanCategoryManager) {
        this.linkmanCategoryManager = linkmanCategoryManager;
    }

    /**
     * 客户分类－查询的方法
     * @author gw 2013-07-24
     * @param request
     * @return string
     */
    @RequestMapping(value="/jadlinkmanCategoryQuery",method=RequestMethod.GET)
    public String getLinkmanCategoryQuery(HttpServletRequest request){
    	String returnView = "jadlinkmanCategoryQuery";
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defSysUser.getUserCode();
    	String name = request.getParameter("name");
    	List linkmanCategoryList = linkmanCategoryManager.getLinkmanCategoryByName(name, userCode);
    	request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	return returnView;
    }
    
    /**
     * 客户分类添加功能-初始化
     * @author gw 2013-07-24
     * @param reuqest
     * @return string
     */
    @RequestMapping(value="/jadAddLinkmanCategory")
    public String getAddLinkmanCategoryInit(HttpServletRequest request,LinkmanCategory linkmanCategory){
    	String returnView = "jadAddLinkmanCategory";
    	return returnView;
    }
    
    /**
     * 客户分类－修改的初始化方法
     * @author gw 2013-07-24
     * @param request
     * @return
     */
    @RequestMapping(value="/jadUpdateLinkmanCategory")
    public String getUpdateLinkmanCategoryInit(HttpServletRequest request){
    	String returnView = "jadUpdateLinkmanCategory";
    	String id = request.getParameter("id");
    	LinkmanCategory linkmanCategory = linkmanCategoryManager.getLinkmanCategoryById(id);
    	request.setAttribute("linkmanCategory", linkmanCategory);
    	return returnView;
    }
    
}
