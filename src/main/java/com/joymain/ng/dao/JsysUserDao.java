package com.joymain.ng.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.joymain.ng.model.JsysUser;

/**
 * An interface that provides a data management interface to the JsysUser table.
 */
public interface JsysUserDao extends GenericDao<JsysUser, String> {

	UserDetails loadUserByUsername(String username);
	public JsysUser getUserByPwd(String userCode,String pwd);
	public List getJsysUserTdec(String userCode);
	public List getMobileJsysUser(int pagenum, int pageSize);
	public int getMobileJsysUserSum();
	public List getMLogGroup();
	/**
	 * 根据用户编号获取用户信息
	 * @param userCode
	 * @return
	 */
	public JsysUser getSysUser(final String userCode);
	
	public HashMap getJsysUser(String userCode);
}