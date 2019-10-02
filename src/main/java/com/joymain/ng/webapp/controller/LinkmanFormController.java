
package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.service.RelationshipRecordManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
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

@Controller
public class LinkmanFormController extends BaseFormController {
    private LinkmanManager linkmanManager = null;
    
    private RelationshipRecordManager relationshipRecordManager ;

    @Autowired
	private JalStateProvinceManager jalStateProvinceManager;
    
    @Autowired
    public void setLinkmanManager(LinkmanManager linkmanManager) {
        this.linkmanManager = linkmanManager;
    }

    @Autowired
	public void setRelationshipRecordManager(RelationshipRecordManager relationshipRecordManager) {
		this.relationshipRecordManager = relationshipRecordManager;
	}


	public LinkmanFormController() {
        setCancelView("redirect:linkmans");
        setSuccessView("redirect:linkmans");
    }

    @ModelAttribute
    public Linkman showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return linkmanManager.get(new Long(id));
        }

        return new Linkman();
    }
    
    /**
     * 会员系统－客户管理－添加客户信息（单独子菜单）-初始化进入添加客户的页面
     * @author gw 2013-07-17 
     * @param linkman,errors,request,response
     * @return string
     */
    @RequestMapping(value="/jadLinkmanAdd")
    public String addLinkmanInit(Linkman linkman,BindingResult errors,HttpServletRequest request,HttpServletResponse response){
    	String returnView = "jadLinkmanAdd";
    	 //定义一个返回的对象
		 JmiMember jmiMember = null;
		 //获取当前登录用户的信息
		 JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 //读取省份－－－－开始
		 List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
	     request.setAttribute("alStateProvinces", alStateProvinces);
         //查出该会员已经定义的客户分类
	     List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
	     request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	     return returnView;
    }
    
    /**
     * 会员系统－客户管理－添加客户信息（单独子菜单）
     * @author gw 2013-07-17 
     * @param linkman,errors,request,response
     * @return string
     */
    @RequestMapping(value="/jadLinkmanAdd",method=RequestMethod.POST)
    public String addLinkman(Linkman linkman,BindingResult errors,HttpServletRequest request,HttpServletResponse response){
    	String returnView = "jadLinkmanAdd";
    	 //定义一个返回的对象
		 JmiMember jmiMember = null;
		 //获取当前登录用户的信息
		 JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 //获取会员的编号
		 String userCode = defSysUser.getUserCode();
		 //读取省份－－－－开始
		 List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
	     request.setAttribute("alStateProvinces", alStateProvinces);
	     //查出该会员已经定义的客户分类
	     List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
	     request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	     //在保存之前做不为空和格式的校验，如果校验通过允许新增，否则不允许新增
		boolean isVerificationThrough = linkmanManager.getlinkmanManagerMark(linkman,errors);
		if(isVerificationThrough){
			return returnView;
		}else{
			//新增用户之前，首先将会员编号放入linkman
			linkman.setUserCode(userCode);
			//新增的同时,将客户设为普通客户
			linkman.setCustomerFocus("N");
			//直接调用新增的方法
	    	linkmanManager.updateOrAddLinkmanDetail(linkman);
	    	//给出友好提示语，表明新增客户添加成功－－提示语是更新成功
	    	this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
	    	request.setAttribute("saveMark","saveMark");
    	    return "redirect:jadLinkmanQuery";
		}
    }
    
    /**
     * 会员系统-客户管理-客户信息修改（在客户信息维护的菜单下）
     * @author gw 2013-07-17
     * @param request
     * @return
     */
    @RequestMapping(value="/jadLinkmanDetailUpdate")
    public String updateLinkmanDetail(Linkman linkman,BindingResult errors,HttpServletRequest request,
    		HttpServletResponse response){
    	String returnView = "jadLinkmanDetailUpdate";
	    	//修改操作的初始化查询已经在linkmanController的getLinkmanDetail查询过
    		//在修改之前进行校验，如不为空校验和特殊格式的校验．如果校验通过，则允许修改，否则不允许修改
    		boolean isVerificationThrough = linkmanManager.getlinkmanManagerMark(linkman,errors);
    		//获取当前登录用户的信息
   		    JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		//查出该会员已经定义的客户分类
   	        List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
   	        request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    		if(isVerificationThrough){
    			//读取省份－－－－开始
				 List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
				 request.setAttribute("alStateProvinces", alStateProvinces);
    			//如果校验不通过，那么直接返回页面，不让修改
    			return returnView;
    		}else{
		    	//直接调用修改的方法
		    	linkmanManager.updateOrAddLinkmanDetail(linkman);
		    	request.setAttribute("saveMark", "saveMark");
	    	    //修改操作成功后，给于友好提示
		    	this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		    	 //获取当前登录用户的信息
				 //读取省份－－－－开始
				 List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
				 request.setAttribute("alStateProvinces", alStateProvinces);
    		     return "redirect:jadLinkmanQuery";
    		}
	 }
}
