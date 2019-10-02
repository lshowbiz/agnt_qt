package com.joymain.ng.log.interceptor;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.joymain.ng.log.util.LogUtil;
import com.joymain.ng.model.JsysOperationLog;
import com.joymain.ng.util.ContextUtil;
import com.joymain.ng.util.DateUtil;







public class VisitInterceptor extends HandlerInterceptorAdapter {

	private String extentUrls;
	
	public String getExtentUrls() {
		return extentUrls;
	}

	public void setExtentUrls(String extentUrls) {
		this.extentUrls = extentUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String requestPath = request.getServletPath();
        JsysOperationLog sysOperationLog = LogUtil.initSysOperationLog(request,
				requestPath);
        /** 分表日志 **/
		// 获取月份,以定位对应的表
		String month = DateUtil.getDateTime("yyyyMM", new Date());
		// sysOperationLogManager.saveSysOperationLogBySql(sysOperationLog,
		// month);
		if(StringUtils.contains(extentUrls, requestPath)){
			
			String username = ""; 
			sysOperationLog.setUserCode(username);
			sysOperationLog.setUserName(username);
			sysOperationLog.setOperaterCode(username);
			ContextUtil.bindResource("operationUserCode", username);
		}else{
			String username="";
			Principal principal = (Principal)request.getUserPrincipal();   
			if(principal !=null){
				 username = principal.getName(); 
			}
			
			sysOperationLog.setUserCode(username);
			sysOperationLog.setUserName(username);
			sysOperationLog.setOperaterCode(username);
			ContextUtil.bindResource("operationUserCode", username);
		}
		
		
		Long logId = LogUtil.saveSysOperationLogBySql(
				sysOperationLog, month);
		
		ContextUtil.bindResource("operationLogId", logId);
		
		return true;
	}

}
