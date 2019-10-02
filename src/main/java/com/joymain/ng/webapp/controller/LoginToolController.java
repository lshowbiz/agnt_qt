package com.joymain.ng.webapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.webapp.util.RequestUtil;



@Controller
@RequestMapping("/loginTool")
public class LoginToolController {
	private JsysUserManager jsysUserManager = null;
	private JsysLoginLogManager jsysLoginLogManager = null;
	public  final String ENCRYPT_PASSWORD = "if4#$%^&Ujnds7r";
	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}
	@Autowired
	public void setJsysLoginLogManager(JsysLoginLogManager jsysLoginLogManager) {
		this.jsysLoginLogManager = jsysLoginLogManager;
	}

	Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value="api/{token}/{optCode}")
	public String loginBackOffice(@PathVariable(value = "token") String token,@PathVariable(value = "optCode") String optCode,HttpServletRequest request){
		
		String text="";
		token = new String(Base64.decodeBase64(token));
		log.info("token="+token);
	
		Map map = new HashMap();
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(ENCRYPT_PASSWORD);                     // we HAVE TO set a password
		encryptor.setAlgorithm("PBEWithMD5AndDES");    // optionally set the algorithm
		text=encryptor.decrypt(token);
		
		String userCode = "" ;
		Long time = 0l;
		if(StringUtils.isNotBlank(text)){
			String[] req = text.split("~");
			if(req.length == 2){
				userCode = req[0];
				time = new Long(req[1]);
			}

		}
		Calendar cal = Calendar.getInstance();
		Long now = cal.getTimeInMillis();
//		String userCode = (String) map.get("userCode");
//		String operationCode = (String) map.get("operationCode");
//		Long time =new Long((String) map.get("time"));
//		log.info("logintool-authentication 1>"+SecurityContextHolder.getContext().getAuthentication());
		JsysUser jsysUser= jsysUserManager.get(userCode);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jsysUser,
				jsysUser);
//		log.info("logintool-authentication 2>"+SecurityContextHolder.getContext().getAuthentication());
//		log.info("now - time="+(now - time));
		if((now - time)<=1200000){//
			
			 authentication = new UsernamePasswordAuthenticationToken(jsysUser,
						jsysUser, jsysUser.getAuthorities());
		}
		
		authentication.setDetails(new WebAuthenticationDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
//		log.info("logintool-authentication 3>"+SecurityContextHolder.getContext().getAuthentication());
		request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
		try {
			JsysLoginLog jsysLoginLog = new JsysLoginLog();
			jsysLoginLog.setUserCode(userCode);
			jsysLoginLog.setOperaterCode(optCode);
			jsysLoginLog.setOperationType("1");
			jsysLoginLog.setOperateTime(new Date());
			jsysLoginLog.setIpAddr(RequestUtil.getIpAddr(request));
			jsysLoginLogManager.save(jsysLoginLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("e>>"+e.getMessage());
		}
		return "redirect:/app/loginform/showuserinfo";
	}
	
	public static void main(String[] args){
		String token = "PAY%2BYbUu3XKIG6skoU3jEs6bcamFJNn68i4oGCIgKH7LKw%2FHxlnJHQ%3D%3D";
		String text="root~1385460931955";
		token = URLDecoder.decode(token);
		String e = Base64.encodeBase64String("PAY+YbUu3XKIG6skoU3jEs6bcamFJNn68i4oGCIgKH7LKw/HxlnJHQ==".getBytes());
//		String e =new String(Base64.encode("PAY+YbUu3XKIG6skoU3jEs6bcamFJNn68i4oGCIgKH7LKw/HxlnJHQ==".getBytes()));
		System.out.println(e);
		token = new String(Base64.decodeBase64(e));
//		System.out.println("d="+new String(Base64.decodeBase64(e)));
		/*try {
			token = URLDecoder.decode(token, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}*/
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		
		encryptor.setPassword("if4#$%^&Ujnds7r");                     // we HAVE TO set a password
		encryptor.setAlgorithm("PBEWithMD5AndDES");    // optionally set the algorithm
		
//		token  = encryptor.encrypt(text);
		text=encryptor.decrypt(token);
		System.out.println(token);
		System.out.println(text);
	}
	
}
