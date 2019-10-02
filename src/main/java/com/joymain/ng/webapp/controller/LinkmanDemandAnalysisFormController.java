package com.joymain.ng.webapp.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanDemand;
import com.joymain.ng.service.LinkmanDemandManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.LocaleUtil;

/**
 * 客户需求分析---录入.删除
 * @author gw  2013-09-27
 *
 */
@Controller
public class LinkmanDemandAnalysisFormController extends BaseFormController{
    private LinkmanDemandManager linkmanDemandManager = null;
    
    private LinkmanManager linkmanManager;

    @Autowired
	public void setLinkmanDemandManager(LinkmanDemandManager linkmanDemandManager) {
		this.linkmanDemandManager = linkmanDemandManager;
	}

    @Autowired
	public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}
    
	public LinkmanDemandAnalysisFormController(){
    	setCancelView("linkmanDemandsAnalysisForm");
        setSuccessView("linkmanDemandsAnalysisForm");
    }
    
    /**
     * 客户需求分析--修改(录入)
     * @author gw  2013-09-30
     * @param linkmanDemand
     * @param errors
     * @param request
     * @param response
     * @return string 
     */
    @RequestMapping(value="/linkmanDemandsAnalysisForm",method=RequestMethod.POST)
    public String updateLinkmanDemandAnalysis(LinkmanDemand linkmanDemand,BindingResult errors,HttpServletRequest request,HttpServletResponse response ){
    	String returnView="linkmanDemandsAnalysisForm";
    	//录入客户需求分析之前,先做不为空的校验
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	boolean emptyCheck = linkmanDemandManager.getLinkmanDemandEmptyCheck(userCode,linkmanDemand,errors);
    	if(emptyCheck){
    		//查出该会员已经定义的客户分类
    	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
    	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    		request.setAttribute("userCode", userCode);
    		return returnView;
    	}
    	linkmanDemandManager.updateLinkmanDemand(linkmanDemand);
		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		request.setAttribute("saveMark", "saveMark");
    	//return returnView;
    	return "redirect:linkmanDemandAnalysisQuery";
    	
    }

}
