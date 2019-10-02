package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.LinkmanDemandManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanDemand;
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

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/linkmanDemandform*")
public class LinkmanDemandFormController extends BaseFormController {
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

	public LinkmanDemandFormController() {
        setCancelView("linkmanDemandform");
        setSuccessView("linkmanDemandform");
    }

    /**
     * 修改或添加初始化查询
     * @author gw 2013-09-26
     * @param request
     * @return linkmanDemand
     * @throws Exception
     */
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected LinkmanDemand showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //查出该会员已经定义的客户分类
	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	    String demandFunction = request.getParameter("demandFunction");
	    request.setAttribute("demandFunction", demandFunction);
	    request.setAttribute("userCode", defUser.getUserCode());
        if (!StringUtils.isBlank(id)) {
            return linkmanDemandManager.get(new Long(id));
        }
        return new LinkmanDemand();
    }

    /**
     * 客户需求表---添加或者修改
     * @author gw 2013-09-26
     * @param linkmanDemand
     * @param errors
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(LinkmanDemand linkmanDemand, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	String demandFunction = request.getParameter("demandFunction");
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	//客户需求添加功能
    	if("demandAdd".equals(demandFunction)){
    		Date date = new Date();
    		linkmanDemand.setRegisterTime(date);
    		linkmanDemand.setUserCode(userCode);
    		boolean checkEmpty = linkmanDemandManager.getLinkmanDemandCheckEmpty(linkmanDemand, errors);
    		if(checkEmpty){
    			 //查出该会员已经定义的客户分类-----校验的同时,让向页面传递之前在初始化进来时必须有的参数---update 2013-12-24
    		    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
    		    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    		    request.setAttribute("demandFunction", demandFunction);
    		    request.setAttribute("userCode", defUser.getUserCode());
        		return "linkmanDemandform";
    		}
    		linkmanDemandManager.updateLinkmanDemand(linkmanDemand);
    		request.setAttribute("saveMark", "saveMark");
    		//查出该会员已经定义的客户分类
    	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
    	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	    request.setAttribute("userCode", userCode);
    	    this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
    		//return getSuccessView();
    		return "redirect:linkmanDemandQuery";
    	}
    	//客户需求修改功能
    	else if("demandUpdate".equals(demandFunction)){
    		boolean checkEmpty = linkmanDemandManager.getLinkmanDemandCheckEmpty(linkmanDemand, errors);
    		if(checkEmpty){
    			//查出该会员已经定义的客户分类-----校验的同时,让向页面传递之前在初始化进来时必须有的参数---update 2013-12-24
    		    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
    		    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    		    request.setAttribute("userCode", defUser.getUserCode());
    		    request.setAttribute("demandFunction", demandFunction);
        		return "linkmanDemandform";
    		}
    		linkmanDemandManager.updateLinkmanDemand(linkmanDemand);
    		request.setAttribute("saveMark", "saveMark");
    		//查出该会员已经定义的客户分类
    	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
    	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	    request.setAttribute("userCode", userCode);
    	    this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
        	//return getCancelView();
        	return "redirect:linkmanDemandQuery";
    	}
    	//客户需求的删除功能
    	else{
    		//linkmanDemandManager.remove(linkmanDemand.getId());
        	return "redirect:linkmanDemandQuery";
    	}
    }
}
