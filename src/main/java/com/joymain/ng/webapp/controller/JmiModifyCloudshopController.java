package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/jmiCloudshopOperation")
public class JmiModifyCloudshopController {

	private JmiMemberManager jmiMemberManager = null;

	@Autowired
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	@RequestMapping("/findActivateOrModify")
	public ModelAndView findToAddOrUpdate(HttpServletRequest request) {
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		JmiMember jmiMember = jmiMemberManager.get(defSysUser.getUserCode());//获取用户信息，判断是否绑定云店
		if (StringUtil.isEmpty(jmiMember.getCloudshopPhone())) {//如果云店手机为空，则未绑定
			return new ModelAndView("redirect:/jmiActivateCloudshopform?1=1");
		}else{
			return new ModelAndView("redirect:/jmiModifyCloudshopform?1=1");
			
		}
	}

}
