package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.LinkmanDemandManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.JmiMember;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@Controller
@RequestMapping("/linkmanGoodsform*")
public class LinkmanGoodsFormController extends BaseFormController {
    
    private LinkmanManager linkmanManager;
    
    private LinkmanDemandManager linkmanDemandManager;

    @Autowired
    public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}

    @Autowired
	public void setLinkmanDemandManager(LinkmanDemandManager linkmanDemandManager) {
		this.linkmanDemandManager = linkmanDemandManager;
	}

	public LinkmanGoodsFormController() {
		//-------------客户管理---------客户的商品--------修改------------开始
		setCancelView("linkmanGoodsform");
		setSuccessView("linkmanGoodsform");
		//------------客户管理-----客户的商品----修改----------------结束
    }

    //--------------------------------------------------------------------------------------客户维护-客户的商品----修改----开始

   /* *//**
     * 客户管理-客户的商品-修改初始化查询 或者录入初始化查询(修改)
     * @author gw 2013-10-09
     * @param request
     * @return
     * @throws Exception
     */
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected LinkmanDemand showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	    String linkmanDemandGoodsFunction = request.getParameter("linkmanDemandGoodsFunction");
	    request.setAttribute("linkmanDemandGoodsFunction", linkmanDemandGoodsFunction);
	    //这个userCode(会员编号)隐藏域的时候使用
	    request.setAttribute("userCode", defUser.getUserCode());
        if (!StringUtils.isBlank(id)) {
            return linkmanDemandManager.get(new Long(id));
        }
        return new LinkmanDemand();
    }
    
    
    /**
     * 客户管理-客户的商品-修改或录入
     * @author gw 2013-0-10-09
     * @param linkmanGoods
     * @param errors
     * @param request
     * @param response
     * @return string
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(LinkmanDemand LinkmanDemand, BindingResult errors, HttpServletRequest request,
                HttpServletResponse response) throws Exception{	
    	
    	String linkmanDemandGoodsFunction = request.getParameter("linkmanDemandGoodsFunction");
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userCode = defUser.getUserCode();
        List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	    //-----------?
	    request.setAttribute("linkmanDemandGoodsFunction", linkmanDemandGoodsFunction);
	    //这个userCode(会员编号)隐藏域的时候使用
	    request.setAttribute("userCode", defUser.getUserCode());

    	//录入操作
    	if("linkmanDemandGoodsAdd".equals(linkmanDemandGoodsFunction)){
    		//在做录入之前,先做不为空的校验
    		boolean checkEmpty = linkmanDemandManager.getLinkmanDemandGoodsCheckEmpty(LinkmanDemand,errors);
    		if(checkEmpty){
    			return "redirect:linkmanDemandGoodsform";
    		}else{
    			LinkmanDemand.setUserCode(userCode);
    			linkmanDemandManager.updateLinkmanDemand(LinkmanDemand);
    	    	this.saveMessage(request, LocaleUtil.getLocalText("update.success"));
    	    	request.setAttribute("saveMark", "saveMark");
    	    	//return getSuccessView();
    	    	return "redirect:linkmanDemandGoodsQuery";
    		}
    	}
    	//修改操作
    	else{
    		//在做修改之前,先做不为空的校验
    		boolean checkEmpty = linkmanDemandManager.getLinkmanDemandGoodsCheckEmpty(LinkmanDemand,errors);
    		if(checkEmpty){
    			Long id = LinkmanDemand.getId();
    			return "linkmanGoodsform";
    		}else{
    			linkmanDemandManager.updateLinkmanDemand(LinkmanDemand);
    	    	this.saveMessage(request, LocaleUtil.getLocalText("update.success"));
    		    request.setAttribute("saveMark", "saveMark");
    		    return "redirect:linkmanDemandGoodsQuery";
    		}
    	}
    }

  
    
    
	//---------------------------------------------------------------------客户的商品修改-----------------------------------结束
}