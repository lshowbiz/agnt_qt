
package com.joymain.ng.webapp.controller;

import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.LinkmanCategoryManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.service.RelationshipRecordManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.LinkmanCategory;
import com.joymain.ng.model.RelationshipRecord;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RelationshipRecordFormController extends BaseFormController {
    private RelationshipRecordManager relationshipRecordManager = null;
    private LinkmanManager linkmanManager;
    
    @Autowired
	private JalStateProvinceManager jalStateProvinceManager;
    
    @Autowired
    private LinkmanCategoryManager linkmanCategoryManager;

    @Autowired
    public void setLinkmanManager(LinkmanManager linkmanManager) {
        this.linkmanManager = linkmanManager;
    }
    @Autowired
    public void setRelationshipRecordManager(RelationshipRecordManager relationshipRecordManager) {
        this.relationshipRecordManager = relationshipRecordManager;
    }

    public RelationshipRecordFormController() {
        setCancelView("redirect:relationshipRecords");
        setSuccessView("redirect:relationshipRecords");
    }
    
    /**
     * 联系记录－添加功能
     * @author gw 2013-07-29
     * @param request
     * @param errors
     * @param relationshipRecord
     * @return　string
     */
    @RequestMapping(value="/jadRelationshipRecordAdd",method=RequestMethod.POST)
    public String getRelationshipRecordAdd(RelationshipRecord relationshipRecord,HttpServletRequest request,BindingResult errors){
    	String returnView = "jadRelationshipRecordAdd";
    	//获取当前登录用户－会员的信息
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	request.setAttribute("userCode", userCode);
    	//获取客户分类名称的集合
        List categoryNameList = relationshipRecordManager.getCategoryNameList(userCode);
        request.setAttribute("categoryNameList", categoryNameList);
    	//在保存之前，做相关的校验
    	boolean isCheckPass = relationshipRecordManager.getRelationshipRecordCheckPass(relationshipRecord,errors);
    	//如果相关的校验没有通过，那么不让其进行保存操作
    	if(isCheckPass){
            return returnView;      		
    	}else{
    		relationshipRecord.setUserCode(userCode);
    		String lcId = relationshipRecord.getLcId();
    		if("ungrouped".equals(lcId)){
    			relationshipRecord.setLcId("");
    		}
    	    relationshipRecordManager.saveOrUpdateRelationshpRecord(relationshipRecord);
    	    //保存成功后，给出友好的提示
    	    this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
    	    //在添加成功后，让保存按钮隐藏起来
    	    request.setAttribute("saveMark", "saveMark");
    	    return "redirect:jadRelationshipRecordQuery";
    	}
    }
    
    /**
     * 联系记录－修改
     * @author gw 2013-07-29
     * @param relationshipRecord
     * @param request
     * @param errors
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/jadRelationshipRecordUpdate",method=RequestMethod.POST)
    public String relationshipRecordUpdate(RelationshipRecord relationshipRecord,HttpServletRequest request,BindingResult errors){
    	String returnView = "jadRelationshipRecordUpdate";
    	//获取当前登录用户－会员的信息
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	//获取客户分类名称的集合
        List categoryNameList = relationshipRecordManager.getCategoryNameList(userCode);
        request.setAttribute("categoryNameList", categoryNameList);
    	//在保存之前，做相关的校验
    	boolean isCheckPass = relationshipRecordManager.getRelationshipRecordCheckPass(relationshipRecord,errors);
    	if(isCheckPass){
    	     return returnView;
    	}else{
    		 relationshipRecordManager.saveOrUpdateRelationshpRecord(relationshipRecord);
     	    //保存成功后，给出友好的提示
     	    this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
     	    //在添加成功后，让保存按钮隐藏起来
     	    request.setAttribute("saveMark", "saveMark");
    	    return "redirect:jadRelationshipRecordQuery";
    	}
    }
    
    /**
     * 联系记录－删除
     * @author gw 2013-07-29
     * @param request
     * @param errors
     * @return　string
     */
    @RequestMapping(value="/jadRelationshipRecordDelete")
    public String relationshipRecordDelete(HttpServletRequest request){
    	String returnView = "jadRelationshipRecordQuery";
    	String id = request.getParameter("id");
    	//执行删除操作
    	relationshipRecordManager.deleteRelationshipRecordById(id);
    	//删除成功后给出提示
    	this.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defSysUser.getUserCode();
    	String subject = "";
    	String contactTimeBegin = "";
    	String contactTimeEnd = "";
    	String contactType = "";
    	String linkmanId = "";
    	//删除成功后，再进行一次查询
        List relationshipRecordList = relationshipRecordManager.getRelationshipRecordByQuery(subject,contactTimeBegin,contactTimeEnd,contactType,linkmanId,userCode);
        request.setAttribute("relationshipRecordList", relationshipRecordList);
    	return returnView;
    }
    
    /**
     * 客户跟踪-联系记录－添加
     * @author gw 2013-07-29
     * @param request
     * @param errors
     * @param relationshipRecord
     * @return　string
     */
    @RequestMapping(value="/addJadRelationshipRecord",method=RequestMethod.POST)
    public String addRelationshipRecord(RelationshipRecord relationshipRecord, HttpServletRequest request){
    	
    	//获取当前登录用户－会员的信息
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	
    	relationshipRecord.setUserCode(userCode);

    	relationshipRecordManager.saveOrUpdateRelationshpRecord(relationshipRecord);

    	return "redirect:jadLinkmanDetailQueryGz?1=1&id="+relationshipRecord.getLinkmanId();
    }
    
    /**
     * 客户跟踪-联系记录－删除
     * @author gw 2013-07-29
     * @param request
     * @param errors
     * @param relationshipRecord
     * @return　string
     */
    @RequestMapping(value="/delJadRelationshipRecord",method=RequestMethod.GET)
    public String delRelationshipRecord(HttpServletRequest request){
    	
    	//获取当前登录用户－会员的信息
    	String relationId=request.getParameter("relationId");//记录ID
    	String linkmanId=request.getParameter("linkmanId");//客户ID

    	relationshipRecordManager.remove(new Long(relationId));

    	return "redirect:jadLinkmanDetailQueryGz?1=1&id="+linkmanId;
    }
    
    /**
     * 客户跟踪-联系记录－修改
     * @author gw 2013-07-29
     * @param request
     * @param errors
     * @param relationshipRecord
     * @return　string
     */
    @RequestMapping(value="/toUpdateRelationshipRecord",method=RequestMethod.GET)
    public String toUpdateRelationshipRecord(HttpServletRequest request){
    	
    	//获取当前登录用户－会员的信息
    	String relationId=request.getParameter("relationId");//记录ID
    	String linkmanId=request.getParameter("linkmanId");//客户ID
    	
    	RelationshipRecord relationshipRecord =relationshipRecordManager.get(new Long(relationId));
    	request.setAttribute("relationshipRecord", relationshipRecord);
    	
    	//获取联系记录
    	List relationshipRecordList=relationshipRecordManager.getRelationshipRecordsByLinkManId(linkmanId);
    	request.setAttribute("relationshipRecordList", relationshipRecordList);
    	
    	//复制过来的---
    	
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String companyCode = defSysUser.getCompanyCode();
		 //读取省份－－－－开始，关于城市和地区的在ｊｓｐ页面做了处理
		List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
		request.setAttribute("alStateProvinces", alStateProvinces);
		//查出该会员已经定义的客户分类
	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	//获取客户信息表的ＩＤ
    	String id = request.getParameter("linkmanId");
    	Linkman linkman = linkmanManager.getLinkmanDetail(id);
    	request.setAttribute("linkman", linkman);
    	
    	//如果省有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getProvince())){
    		request.setAttribute("provinceRemark", "true");
    	}
    	//如果市有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getCity())){
    	   request.setAttribute("cityRemark","true");
    	}
    	//如果地区有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getDistrict())){
    	   request.setAttribute("districtRemark","true");
    	}
    	
    	if(linkman!=null&&!StringUtil.isEmpty(linkman.getLcId())){
	    	LinkmanCategory linkmanCategorymd = linkmanCategoryManager.getLinkmanCategoryById(linkman.getLcId());
	    	String linkmanCategory = "";
	    	if(linkmanCategorymd!=null){
	    		linkmanCategory = linkmanCategorymd.getName();
	    	}
	    	request.setAttribute("linkmanCategory", linkmanCategory);
    	}

    	return "jadLinkmanDetailQueryGz";
    }
}
