package com.joymain.ng.security;

import java.util.Collection;
import java.util.Iterator;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JsysUserManager;

@Component("accessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {
	
	JsysUserManager jsysUserManager = null;
	
	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}

	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
		if(configAttributes == null){
//            return ;
            throw new AccessDeniedException("no resource");
        }
//        System.out.println(object.toString());  //object is a URL.
		Object obj = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if(obj.getClass().getName().indexOf("com.joymain.ng.model.JsysUser")>=0){
			 JsysUser sysUser =jsysUserManager.get(authentication.getName());
			 authorities = sysUser.getAuthorities();
			 Log.info("user-roles>>>"+sysUser.getJsysRoles());
		}
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authorities){
            	//Log.info("authorities>>>"+authorities);
                if(needRole.equals(ga.getAuthority())){  //ga is user's role.
                    return;
                }
            }
        }
        Log.info("object>>"+object.toString());
        Log.info("configAttributes>>"+configAttributes);
        throw new AccessDeniedException("no right");
    
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
