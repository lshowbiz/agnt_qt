package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.util.StringUtil;

@Controller
public class jmiPersonalDataMaintenanceController{

	@Autowired
	private JmiMemberManager jmiMemberManager;
	 
	@Autowired
	private JalStateProvinceManager jalStateProvinceManager;

//	@Autowired
//	private JpoMemberOrderManager jpoMemberOrderManager;
	
	@RequestMapping("/jmiPersonalDataMaintenance")
    protected String showForm(HttpServletRequest request)throws Exception {
		String returnView = "jmiPersonalDataMaintenance";
		JmiMember jmiMember = null;
		//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String companyCode = defSysUser.getCompanyCode();
		//读取省份－－－－开始，关于城市和地区的在ｊｓｐ页面做了处理 
		 List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
	     request.setAttribute("alStateProvinces", alStateProvinces);
	     String userCode = defSysUser.getUserCode();
	     //从数据库中获取jmiMember对象
	     jmiMember = jmiMemberManager.getJmiMemberBankInformation(userCode);
		 //查询升级所需的天数－－这个是在架构中定义的（在数据库的某个表中定义的）
		 request.setAttribute("days", jmiMemberManager.getRemainUpdateDays(defSysUser));

         //判断身份证号是否有值,如果有值的话,那么不让输入,否则的话,让 输入.这个是在jsp页面中显示的
         if(!StringUtil.isEmpty(jmiMember.getPapernumber())){
     		request.setAttribute("papernumberModify", "true");
     	 }
         
			   //在保存之前,先看数据库中该人的身份证号
			   String papernumberCheck = jmiMemberManager.getPapernumberCheck(userCode);
			   request.setAttribute("papernumberCheck", papernumberCheck);
			   request.setAttribute("jmiMember", jmiMember);
        return returnView;
    }
	
    
}
