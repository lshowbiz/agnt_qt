package com.joymain.ng.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JsysUserDao;
import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.RequestUtil;

@Service("jsysUserManager")
@WebService(serviceName = "JsysUserService", endpointInterface = "com.joymain.ng.service.JsysUserManager")
public class JsysUserManagerImpl extends GenericManagerImpl<JsysUser, String>
		implements JsysUserManager, UserDetailsService {
	JsysUserDao jsysUserDao;

	@Autowired
	public JsysUserManagerImpl(JsysUserDao jsysUserDao) {
		super(jsysUserDao);
		this.jsysUserDao = jsysUserDao;
	}

	@Autowired
	private JsysLoginLogManager jsysLoginLogManager;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		return jsysUserDao.loadUserByUsername(username);
	}

	@Override
	public Pager<JsysUser> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jsysUserDao.getPager(JsysUser.class, searchBeans, OrderBys,
				currentPage, pageSize);
	}

	@Override
	public JsysUser getUserByPwd(String userCode, String pwd) throws Exception {
		return jsysUserDao.getUserByPwd(userCode, pwd);
	}

	@Override
	public void saveUser(JsysUser jsysUser) {
		// TODO Auto-generated method stub
		jsysUserDao.save(jsysUser);
		try {
			log.info("saveUser>>>xxx");
			throw new RuntimeException("xxx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("xxx");
		}
	}

	@Override
	public JsysUser getUserByToken(String id, String token) {
		// TODO Auto-generated method stub
		JsysUser user = jsysUserDao.get(id);
		if (user != null&&"M".equals(user.getUserType())&&token.equals(user.getToken())) {
			return user;
		}
		return null;
	}

	public String setPassword(String passwordType, JsysUser jsysUser,
			String newPassword, String oldPassword, String type,
			HttpServletRequest request) {
		String returnFlag = "1";

		String md5NewPassword = StringUtil.encodePassword(newPassword, "md5");

		String md5OldPassword = StringUtil.encodePassword(oldPassword, "md5");

		if ("userPassword".equals(passwordType)) {
			// 更改密码
			boolean flag = false;
			if ("M".equals(jsysUser.getUserType())) {
				List list = jsysUserDao.getJsysUserTdec(jsysUser.getUserCode());
				if (!list.isEmpty()) {
					Map map = (Map) list.get(0);
					String curPassword = (String) map.get("password");
					if (curPassword.equals(md5NewPassword)) {
						flag = true;
					}
				}
			}
			if (!md5OldPassword.equals(jsysUser.getPassword())) {
				if ("c".equals(type)) {
					returnFlag = LocaleUtil.getLocalText("error.oldPassword");
				} else {
					returnFlag = "0";
				}

			} else if (flag) {
				if ("c".equals(type)) {
					returnFlag = "密码过于简单，请重新输入";
				} else {
					returnFlag = "0";
				}
			} else {
				jsysUser.setPassword(md5NewPassword);
				jsysUser.setToken("");
				this.save(jsysUser);
				this.saveLogin(request, jsysUser);
				if ("c".equals(type)) {
					returnFlag = LocaleUtil
							.getLocalText("sysUser.password.updated");
				} else {
					returnFlag = "1";
				}
			}
		} else if ("password2".equals(passwordType)) {
			// 更改高级密码
			if (!md5OldPassword.equals(jsysUser.getPassword2())) {
				if ("c".equals(type)) {
					returnFlag = LocaleUtil.getLocalText("error.oldPassword");
				}else{
					returnFlag = "0";
				}
				
			} else {
				jsysUser.setPassword2(md5NewPassword);
				this.save(jsysUser);
				this.saveLogin(request, jsysUser);
				if ("c".equals(type)) {
					returnFlag = LocaleUtil
					.getLocalText("sysUser.password2.updated");
				} else {
					returnFlag = "1";
				}
				
			}
		}
		return returnFlag;
	}

	private void saveLogin(HttpServletRequest request, JsysUser sysUser) {
		String ipAddress = "";
		if (request != null) {
			ipAddress = RequestUtil.getIpAddr(request);
		}
		JsysLoginLog sysLoginLog = new JsysLoginLog();
		sysLoginLog.setUserCode(sysUser.getUserCode());
		sysLoginLog.setIpAddr(ipAddress);
		sysLoginLog.setOperaterCode(sysUser.getUserCode());
		sysLoginLog.setOperateTime(new Date());
		sysLoginLog.setOperationType("2");
		jsysLoginLogManager.save(sysLoginLog);
	}

	/**
	 * 手机登录出错代码
	 * 
	 * @param sysUser
	 * @param objectType
	 * 
	 * 
	 */
	public Object getAuthErrorCode(JsysUser sysUser, String objectType) {

		Map map = new HashMap();
		String errorCode = "";
		String loginFlag = Constants.sysConfigMap.get("CN").get(
				"open.to.mobile");

		if (!"1".equals(loginFlag)) {
			// 系统禁止用户登录
			map.put("autherror", "2");
			errorCode = "autherror:2";
		}

		if (null != sysUser && "1".equals(sysUser.getState())) {
			return null;
		}

		if (null != sysUser) {
			// 禁用用户
			map.put("autherror", "0");
			errorCode = "autherror:0";
		} else {
			// 密码错误或用户名不存在
			map.put("autherror", "1");
			errorCode = "autherror:1";
		}
		if ("map".equalsIgnoreCase(objectType)) {
			return map;
		} else if ("list".equalsIgnoreCase(objectType)) {
			List list = new ArrayList();
			list.add(map);
			return list;
		} else if ("set".equalsIgnoreCase(objectType)) {
			Set set = new HashSet();
			set.add(map);
			return set;
		} else if ("string".equalsIgnoreCase(objectType)) {
			return errorCode;
		}
		return null;
	}
	
	public List getMobileJsysUser(int pagenum, int pageSize){
		return jsysUserDao.getMobileJsysUser(pagenum, pageSize);
	}
	public int getMobileJsysUserSum(){
		return jsysUserDao.getMobileJsysUserSum();
	}
	public List getMLogGroup() {
		return jsysUserDao.getMLogGroup();
	}
}