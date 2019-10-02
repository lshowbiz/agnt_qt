package com.joymain.ng.webapp.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.log.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

  import com.joymain.ng.Constants;
import com.joymain.ng.model.JsysUser;





/**
 * 当前用户Session类
 * 
 * @author Aidy.Q
 * 
 */
public class SessionLogin implements Serializable{
	public SessionLogin() {
	}

	public static void resetLogin(JsysUser jsysUser){
		String userCode = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication authentication = new UsernamePasswordAuthenticationToken(jsysUser,
				jsysUser, jsysUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	/**
	 * 获取当前登录人信息
	 * 
	 * @param request
	 * @return
	 */
	public static JsysUser getLoginUser(HttpServletRequest request) {
//		JsysUser sysUser = (JsysUser) request.getSession().getAttribute(Constants.SESSION_CURRENT_USER);
		JsysUser sysUser  = new JsysUser();
		Object obj =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(obj.getClass().getName().indexOf("com.joymain.ng.model.JsysUser")>=0){
			 sysUser =(JsysUser) obj;
		}else{

			String defLang=request.getLocale().toString();
			if(Constants.localLanguageMap.get(request.getLocale().toString())==null){
				defLang="zh_CN";
			}
			//instance = new SessionLogin();
			sysUser = new JsysUser();
			sysUser.setDefCharacterCoding(defLang);
			//instance.setSysUser(sysUser);
//			sysUser.setIsAdmin(false);
//			sysUser.setAuthorized(false);
			
			//SysUser operatorSysUser=new SysUser();
			//operatorSysUser.setDefCharacterCoding(defLang);
			//sysUser.setOperatorSysUser(operatorSysUser);
			
//			request.getSession().setAttribute(Constants.SESSION_CURRENT_USER, sysUser);
		
		}
		
	
		
		return sysUser;
	}
	
	/**
	 * 设置当前登录人
	 * @param request
	 * @param sysUser
	 */
	public static void setLoginUser(HttpServletRequest request, JsysUser sysUser){
		request.getSession().setAttribute(Constants.SESSION_CURRENT_USER, sysUser);
	}
	
	/**
	 * 获取当前登录人信息
	 * 
	 * @param request
	 * @return
	 */
	public static JsysUser getOperatorUser(HttpServletRequest request) {
		return getLoginUser(request);
//		JsysUser sysUser = (JsysUser) request.getSession().getAttribute(Constants.SESSION_CURRENT_OPERATOR);
/*		
		if (sysUser == null) {
			String defLang=request.getLocale().toString();
			if(Constants.localLanguageMap.get(request.getLocale().toString())==null){
				defLang="en_US";
			}
			sysUser = new JsysUser();
			sysUser.setDefCharacterCoding(defLang);
//			sysUser.setIsAdmin(false);
//			sysUser.setAuthorized(false);
			
			request.getSession().setAttribute(Constants.SESSION_CURRENT_OPERATOR, sysUser);
		}
		return sysUser;*/
	}
	
	/**
	 * 设置当前登录人
	 * @param request
	 * @param sysUser
	 */
	public static void setOperatorUser(HttpServletRequest request, JsysUser sysUser){
		request.getSession().setAttribute(Constants.SESSION_CURRENT_OPERATOR, sysUser);
	}
}