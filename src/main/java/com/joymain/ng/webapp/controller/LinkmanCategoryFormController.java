package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.LinkmanCategoryManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.service.RelationshipRecordManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanCategory;
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

import java.util.List;
import java.util.Locale;

/**
 * 
 * @author gw 2013-07-24
 * 客户分类的formController类
 *
 */
@Controller
public class LinkmanCategoryFormController extends BaseFormController{
    private LinkmanCategoryManager linkmanCategoryManager = null;
    
    private LinkmanManager linkmanManager ;
    
    private RelationshipRecordManager relationshipRecordManager;

    @Autowired
    public void setLinkmanCategoryManager(LinkmanCategoryManager linkmanCategoryManager) {
        this.linkmanCategoryManager = linkmanCategoryManager;
    }

    @Autowired
	public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}
    
    @Autowired
	public void setRelationshipRecordManager(
			RelationshipRecordManager relationshipRecordManager) {
		this.relationshipRecordManager = relationshipRecordManager;
	}

	public LinkmanCategoryFormController() {
        setCancelView("redirect:linkmanCategorys");
        setSuccessView("redirect:linkmanCategorys");
    }


    /*@ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected LinkmanCategory showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return linkmanCategoryManager.get(new Long(id));
        }
z
        return new LinkmanCategory();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(LinkmanCategory linkmanCategory, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(linkmanCategory, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "linkmanCategoryform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (linkmanCategory.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            linkmanCategoryManager.remove(linkmanCategory.getId());
            saveMessage(request, getText("linkmanCategory.deleted", locale));
        } else {
            linkmanCategoryManager.save(linkmanCategory);
            String key = (isNew) ? "linkmanCategory.added" : "linkmanCategory.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:linkmanCategoryform?id=" + linkmanCategory.getId();
            }
        }

        return success;
    }*/
    
    /**
     * 客户分类－添加功能
     *@author gw 2016-07-24
     *@param linkmanCategory
     *@param request
     *@param response
     *@param errors
     *@return string
     */
    @RequestMapping(value="/jadAddLinkmanCategory",method=RequestMethod.POST)
    public String addLinkmanCategory(LinkmanCategory linkmanCategory,HttpServletRequest request,HttpServletResponse response,BindingResult errors){
    	String returnView = "jadAddLinkmanCategory";
    	//获取当前登录用户（会员）的对象信息
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defSysUser.getUserCode();
    	//分类名称不为空的校验和分类名称不一致的校验
    	boolean isEmptyLinkmanCategoryName = linkmanCategoryManager.getLinkmanCategoryCheck(linkmanCategory,errors,userCode);
    	if(isEmptyLinkmanCategoryName){
    		return returnView;
    	}else{
    		linkmanCategory.setUserCode(userCode);
    		linkmanCategoryManager.saveOrUpdateLinkmanCategory(linkmanCategory);
    		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
    		//保存成功的标志－此标志用来隐藏保存按钮
    		request.setAttribute("saveMark","saveMark");
    		//return returnView;
    		return "redirect:jadlinkmanCategoryQuery";
    	}
    }
    
    /**
     * 客户分类－修改的方法
     * @author gw 2013-07-24
     * @param request
     * @param linkmanCategory
     * @return　String
     */
    @RequestMapping(value="/jadUpdateLinkmanCategory",method=RequestMethod.POST)
    public String updateLinkmanCategory(HttpServletRequest request,LinkmanCategory linkmanCategory,BindingResult errors){
    	String returnView = "jadUpdateLinkmanCategory";
    	//获取当前登录用户（会员）的对象信息
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defSysUser.getUserCode();
    	linkmanCategory.setUserCode(userCode);
    	String id = request.getParameter("id");
    	Long idL= Long.valueOf(id);
    	linkmanCategory.setId(idL);
    	//分类名称不为空的校验和分类名称不一致的校验-修改的时候校验不一样
    	boolean isEmptyLinkmanCategoryName = linkmanCategoryManager.getLinkmanCategoryCheckById(linkmanCategory,errors,userCode);
    	if(isEmptyLinkmanCategoryName){
    		return returnView;
    	}else{
	    	linkmanCategoryManager.saveOrUpdateLinkmanCategory(linkmanCategory);
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			//修改成功的标志－此标志用来隐藏修改按钮
			request.setAttribute("updateMark","updateMark");
	    	//return returnView;
			return "redirect:jadlinkmanCategoryQuery";
    	}
    }
    
    /**
     * 客户分类－删除的方法
     * @author gw 2013-07-24
     * @param request
     * @param linkmanCategory
     * @return String
     */
    @RequestMapping(value="/jadDeleteLinkmanCategory")
    public String deleteLinkmanCategory(HttpServletRequest request){
    	String returnView = "jadlinkmanCategoryQuery";
    	//获取当前登录用户（会员）的对象信息
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defSysUser.getUserCode();
    	String id = request.getParameter("id");
    	linkmanCategoryManager.deleteLinkmanCategoryById(id);
    	//删除操作成功后，给于友好提示
    	this.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
    	//删除 之后,将该分类下的客户的分类置为"未分组"
    	linkmanManager.setLinkmanUngrouped(userCode,id);
    	//删除之后,将摆放记录中的已经删除的分类的lc_id也置为空
    	relationshipRecordManager.setRelationshipRecordLcIdNull(userCode,id);
    	
		//修改成功的标志－此标志用来隐藏修改按钮
		request.setAttribute("updateMark","updateMark");
    	String name = "";
    	List linkmanCategoryList = linkmanCategoryManager.getLinkmanCategoryByName(name,userCode);
    	request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	return returnView;
    }
}
