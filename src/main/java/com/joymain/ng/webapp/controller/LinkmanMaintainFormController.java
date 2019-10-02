package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.LinkmanMaintainManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.LinkmanMaintain;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/linkmanMaintainform*")
public class LinkmanMaintainFormController extends BaseFormController {
    private LinkmanMaintainManager linkmanMaintainManager = null;
    
    private LinkmanManager linkmanManager;

    @Autowired
    public void setLinkmanMaintainManager(LinkmanMaintainManager linkmanMaintainManager) {
        this.linkmanMaintainManager = linkmanMaintainManager;
    }
    
    @Autowired
    public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}

	public LinkmanMaintainFormController() {
        setCancelView("linkmanMaintainform");
        setSuccessView("linkmanMaintainform");
    }

	/**
	 * 客户维护-客户录入或修改之前的查询
	 * @author gw 2013-10-12
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected LinkmanMaintain showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        String linkmanId = request.getParameter("linkmanId");
        String linkmanMaintainFunction = request.getParameter("linkmanMaintainFunction");
        request.setAttribute("linkmanMaintainFunction",linkmanMaintainFunction);
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
 	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	    request.setAttribute("userCode", defUser.getUserCode());
	    LinkmanMaintain  linkmanMaintain= new LinkmanMaintain();
	    //编辑
        if (!StringUtils.isBlank(id)) {
        	    //编辑时联系人发生了变化
	        	if(!StringUtil.isEmpty(linkmanId)){
	                Linkman linkman = linkmanManager.get(Long.parseLong(linkmanId));
	                linkmanMaintain= linkmanMaintainManager.get(new Long(id));
	                linkmanMaintain.setLinkmanId(linkman.getId().toString());
	                linkmanMaintain.setOther(linkman.getName());
	                return linkmanMaintain;
	            }
	        	//编辑时联系人没有发生变化
	        	else{
	               return linkmanMaintainManager.get(new Long(id));
	           }
        }
       
        //录入初始化选择了联系人
        if(!StringUtil.isEmpty(linkmanId)){
              Linkman linkman = linkmanManager.get(Long.parseLong(linkmanId));
              linkmanMaintain.setLinkmanId(linkman.getId().toString());
              linkmanMaintain.setOther(linkman.getName());
        }
        
        return linkmanMaintain;
    }

    /**
     * 客户维护-录入或修改
     * @param linkmanMaintain
     * @param errors
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(LinkmanMaintain linkmanMaintain, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        String linkmanMaintainFunction = request.getParameter("linkmanMaintainFunction");
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	    request.setAttribute("userCode", defUser.getUserCode());
	    //在维护方式的选择列表中,0表示请选择,因此0是不能保存在数据库中的
	    if("0".equals(linkmanMaintain.getMaintenanceMode())){
	    	linkmanMaintain.setMaintenanceMode("");
	    }
        //在录入或修改之前做不为空的校验
        boolean checkEmpty = linkmanMaintainManager.getLinkmanMaintainEmptyCheck(linkmanMaintain,errors);
        if(checkEmpty){
        	request.setAttribute("linkmanMaintainFunction",linkmanMaintainFunction);
        	return "linkmanMaintainform";
        }
        
        	linkmanMaintain.setUserCode(defUser.getUserCode());
        	linkmanMaintainManager.updateOrAddLinkmanMaintain(linkmanMaintain);
        	request.setAttribute("saveMark","saveMark");
        	this.saveMessage(request, LocaleUtil.getLocalText("update.success"));
        	//return getSuccessView();
        	return "redirect:linkmanMaintains";
    }
   
}
