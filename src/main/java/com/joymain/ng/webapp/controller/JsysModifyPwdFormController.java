package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiSubStoreManager;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JmiSubStore;
import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;
import com.joymain.ng.webapp.util.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/sysModifyPwd")
public class JsysModifyPwdFormController extends BaseFormController {
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	@Autowired 
	private JsysUserManager jsysUserManager;
	@Autowired 
	private JsysLoginLogManager jsysLoginLogManager;
	public JsysModifyPwdFormController() {
		setCancelView("redirect:sysModifyPwd");
		setSuccessView("redirect:sysModifyPwd");
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}

	
	@ModelAttribute
	public JsysUser getJsysUsere(HttpServletRequest request) {
		JsysUser defSysUser=null;
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
	
	
		return defSysUser;
	}


	
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JsysUser jsysUser, BindingResult errors, HttpServletRequest request,HttpServletResponse response) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	

		String passwordType = request.getParameter("passwordType");

		//JsysUser oldSysUser = this.jsysUserManager.get(jsysUser.getUserCode());

		String newPassword=request.getParameter("newPassword");
		String oldPassword=request.getParameter("oldPassword");
	
    	String res=jsysUserManager.setPassword(passwordType, jsysUser, newPassword, oldPassword, "c", request);
    	
    	String returnUrl=request.getParameter("returnUrl");
    	
    	this.saveMessage(request, res);
    	
    	if("loginform/showuserinfo".equals(returnUrl)){
    		response.sendRedirect(request.getContextPath()+"/"+returnUrl);
    	}
    	
    	
    	return mav;
    }
    
}
