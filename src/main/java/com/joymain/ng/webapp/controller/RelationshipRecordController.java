package com.joymain.ng.webapp.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.RelationshipRecord;
import com.joymain.ng.service.RelationshipRecordManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
public class RelationshipRecordController {
    private RelationshipRecordManager relationshipRecordManager;

    @Autowired
    public void setRelationshipRecordManager(RelationshipRecordManager relationshipRecordManager) {
        this.relationshipRecordManager = relationshipRecordManager;
    }
    
    /**
     * 联系记录查询
     * @author gw 2013-07-29
     * @param request
     * @return String
     */
    @RequestMapping(value="/jadRelationshipRecordQuery")
    public String getRelationshipRecordByQuery(HttpServletRequest request){
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defSysUser.getUserCode(); 
    	String returnView = "jadRelationshipRecordQuery";
    	String subject = request.getParameter("subject");
    	String contactTimeBegin = request.getParameter("contactTimeBegin");
    	String contactTimeEnd = request.getParameter("contactTimeEnd");
    	//对查询的开始日期和结束日期做格式的处理
    	/*if(!StringUtil.isEmpty(contactTimeBegin)){
    		contactTimeBegin = this.getFormatDate(contactTimeBegin);
    	}if(!StringUtil.isEmpty(contactTimeEnd)){
    		contactTimeEnd = this.getFormatDate(contactTimeEnd);
    	}*/
    	String contactType = request.getParameter("contactType");
    	//如果连续类型的值为０，代表没有选择类型－－其实选择的是＂请选择＂项
    	if("0".equals(contactType)){
    		//这样定义让他们查询所有的
    		contactType = "";
    	}
    	//获取联系人的姓名：
    	String name = request.getParameter("name");
    	String linkmanId = "";
    	if(!StringUtil.isEmpty(name)){
    	    //根据姓名和当前的登录用户查询联系人姓名的Id
    	    Linkman linkman = relationshipRecordManager.getLinkmanId(userCode,name);
    	    if(null==linkman || "".equals(linkman)){
    	    	//如果为空的话，表面数据库中并没有该联系人，未了表面没有该人，那么写死这个时候的查询的ＩＤ，让其查询结果为空
    	    	linkmanId = "9";
    	    }else{
    	    	Long linkmanIdL = linkman.getId();
    	    	linkmanId = linkmanIdL.toString();
    	    }
    	    /*Long linkmanIdL = linkman.getId();
    	    boolean isEmptyIdL = ((null==linkmanIdL)||("".equals(linkmanIdL)));
    	    if(!isEmptyIdL){
    	       linkmanId = linkmanIdL.toString();
    	    }*/
    	}
    	 
    	returnView = StringUtil.dealStr(returnView);
    	subject = StringUtil.dealStr(subject);
    	contactTimeBegin = StringUtil.dealStr(contactTimeBegin);
    	contactTimeEnd = StringUtil.dealStr(contactTimeEnd);
    	contactType = StringUtil.dealStr(contactType);
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","jadRelationshipRecordQuery?returnView="+returnView+"&subject="+subject+
									   "&contactTimeBegin="+contactTimeBegin+"&contactTimeEnd="+contactTimeEnd+
									   "&contactType="+contactType+"&pageSize="+pageSize,pageSize,request);
    	
		List relationshipRecordList = relationshipRecordManager.getRelationshipRecordByQueryPage(page,subject,contactTimeBegin,contactTimeEnd,contactType,linkmanId,userCode);

//    	List relationshipRecordList = relationshipRecordManager.getRelationshipRecordByQuery(subject,contactTimeBegin,contactTimeEnd,contactType,linkmanId,userCode);
    	
    	request.setAttribute("page", page);
    	
        request.setAttribute("relationshipRecordList", relationshipRecordList);
    	return returnView;
    }
    
    /**
     * 联系记录－修改之前进行的查询
     * @author gw 2013-07-29
     * @param relationshipRecord
     * @param request
     * @return　string
     */
    @RequestMapping(value="/jadRelationshipRecordUpdate")
    public String getRelationshipRecordByUpdateInit(HttpServletRequest request){
    	String returnView ="jadRelationshipRecordUpdate";
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取客户分类名称的集合
        List categoryNameList = relationshipRecordManager.getCategoryNameList(defUser.getUserCode());
        request.setAttribute("categoryNameList", categoryNameList);
        String userCode = defUser.getUserCode();
        request.setAttribute("userCode",userCode);
    	String id = request.getParameter("id");
    	RelationshipRecord relationship= relationshipRecordManager.getRelationshipRecordByUpdateInit(id);
    	request.setAttribute("relationshipRecord", relationship);
    	return returnView;
    }
    
    /**
     * 联系记录－添加功能初始化
     * @author gw 2013-07-29
     * @param request
     * @param relationshipRecord
     * @return String
     */
    @RequestMapping(value="/jadRelationshipRecordAdd")
    public String getRalationshipRecordAddInit(HttpServletRequest request,RelationshipRecord relationshipRecord){
        String returnView = "jadRelationshipRecordAdd";
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userCode = defUser.getUserCode();
        request.setAttribute("userCode", userCode);
        //获取客户分类名称的集合
        List categoryNameList = relationshipRecordManager.getCategoryNameList(defUser.getUserCode());
        request.setAttribute("categoryNameList", categoryNameList);
        return returnView;
    }
    
    /**
     * 对查询的时间做一下格式的处理　使日期变成2013-12-12 或者2013*-1-1 或者2013-2-25 或者2013-12-1
     * @author gw 2013-08-06
     * @param date
     * @return String
     */
    private String getFormatDate(String date){
    	String dateNew = "";
    	String sixMonth = date.substring(5, 6);
    	if("0".equals(sixMonth)){
    		dateNew = date.substring(0, 5)+date.substring(6, 7);
    	}else{
    		dateNew = date.substring(0, 7);
    	}
    	String eightDay = date.substring(8, 9);
    	if("0".equals(eightDay)){
    		dateNew += date.substring(7, 8)+date.substring(9, date.length());
    	}else{
    		dateNew += date.substring(7, date.length());
    	}
    	return dateNew;
    }
}
