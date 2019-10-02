package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.LinkmanEventManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanEvent;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/linkmanEventform*")
public class LinkmanEventFormController extends BaseFormController {
    private LinkmanEventManager linkmanEventManager = null;
    
    private LinkmanManager linkmanManager;

    @Autowired
    public void setLinkmanEventManager(LinkmanEventManager linkmanEventManager) {
        this.linkmanEventManager = linkmanEventManager;
    }

    @Autowired
    public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}

	public LinkmanEventFormController() {
        setCancelView("linkmanEventform");
        setSuccessView("linkmanEventform");
    }

	/**
	 * 客户管理-事件管理-客户录入或修改之前的查询
	 * @author gw 2013-10-14
	 * @param request
	 * @return LinkmanEvent
	 * @throws Exception
	 */
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected LinkmanEvent showForm(HttpServletRequest request)
    throws Exception {
    	
    	LinkmanEvent linkmanEvent=null;
       String id = request.getParameter("id");
       if(!StringUtil.isEmpty(id)){
    	   linkmanEvent=linkmanEventManager.get(new Long(id));
       }else{
    	   linkmanEvent=new LinkmanEvent();
       }
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
 	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	    request.setAttribute("userCode", defUser.getUserCode());
	    return linkmanEvent;
    }

    /**
     * 客户管理-事件管理-录入或修改
     * @author 2013-10-14
     * @param linkmanMaintain
     * @param errors
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(LinkmanEvent linkmanEvent, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {

        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        linkmanEvent.setUserCode(defUser.getUserCode());
        
	    //在事件类型的选择列表中,0表示请选择,因此0是不能保存在数据库中的
	    if("0".equals(linkmanEvent.getEventType())){
	    	linkmanEvent.setEventType("");
	    }
        
        linkmanEventManager.save(linkmanEvent);
        
        this.saveMessage(request, "保存成功!");
        return "redirect:linkmanEvents";
    }
    
}
