package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.model.JsysUser;

import java.util.Collection;
import java.util.List;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@WebService
public interface JsysUserManager extends GenericManager<JsysUser, String> {
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException;
	public Pager<JsysUser> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	public JsysUser getUserByPwd(String userCode,String pwd) throws Exception;
	
	public void saveUser(JsysUser jsysUser);
	public JsysUser getUserByToken(String id,String token);
	public List getMobileJsysUser(int pagenum, int pageSize);
	public int getMobileJsysUserSum();
	public List getMLogGroup();
	public Object getAuthErrorCode(JsysUser jsysUser,String objectType);
	public String setPassword(String passwordType,JsysUser jsysUser,String newPassword,String  oldPassword,String type,HttpServletRequest request);
}