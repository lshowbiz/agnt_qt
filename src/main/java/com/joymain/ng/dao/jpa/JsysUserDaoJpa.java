package com.joymain.ng.dao.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JsysUserDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.StringUtil;

@Repository("jsysUserDao")
public class JsysUserDaoJpa extends GenericDaoHibernate<JsysUser, String>
		implements JsysUserDao {

	public JsysUserDaoJpa() {
		super(JsysUser.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		JsysUser user = (JsysUser) this.getSession().get(JsysUser.class,
				username);

		if (user == null) {
			throw new UsernameNotFoundException("user '" + username
					+ "' not found...");
		} else {
			return user;
		}
	}

	@Override
	public JsysUser getUserByPwd(String userCode, String pwd) {

		/*
		 * Query q =(Query)getSession().createQuery(
		 * "from JsysUser u where u.userCode=? and u.password=? ");
		 * ((org.hibernate.Query) q).setParameter(0, userCode);
		 * ((org.hibernate.Query) q).setParameter(1, pwd); JsysUser user =
		 * (JsysUser) ((org.hibernate.Query) q).list().get(0);
		 */

		// String sql = "from JsysUser u where u.userCode=? and u.password=? ";
		// String loginFlag = Constants.sysConfigMap.get("CN").get(
		// "open.to.member");
		JsysUser user = (JsysUser) this.getSession().get(JsysUser.class,
				userCode);
		if ((user != null)
				&& ("1".equals(user.getState()))
				&& "M".equals(user.getUserType())
				&& ((user.getPassword().equals(StringUtil.encodePassword(pwd,
						"MD5"))))) {
			return user;
		} else {
			return null;
		}

	}

	public List getJsysUserTdec(String userCode) {
		String sqlQuery = "select * from tdec.sys_user where user_code='"
				+ userCode + "'";
		return new ArrayList();
	}

	public List getMobileJsysUser(int pagenum, int pageSize) {
		StringBuffer sqlBuf = new StringBuffer(
				"select jsysUser.state,jsysUser.userCode,jsysUser.userType,jsysUser.honorStar,jsysUser.token from JsysUser jsysUser where jsysUser.token is not null ");

		// 返回分页数据
		Query query = this.getSession().createQuery(sqlBuf.toString());
		query.setFirstResult((pagenum - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	public int getMobileJsysUserSum() {
		String sqlQuery = "select count(1) from jsys_user where token is not null";
		return this.jdbcTemplate.queryForInt(sqlQuery);
	}
	
	public List getMLogGroup() {
		String sqlQuery = "select count(1) as sum,operation_type as device from JSYS_LOGIN_LOG t where t.from_device is not null or t.operation_type='other' group by t.operation_type";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}

	@Override
	public JsysUser getSysUser(String userCode) {

		JsysUser sysUser = (JsysUser) getSession()
				.get(JsysUser.class, userCode);
		return sysUser;
	}

	@Override
	public HashMap getJsysUser(String userCode) {
		String hql = " select t1.user_code userCode,t1.password passWord,t2.active active,t2.exit_date exitDate,t1.state state from jsys_user t1,jmi_member t2 where t1.user_code=t2.user_code and t1.user_code='"
				+ userCode + "' ";
		return this.findOneObjectBySQL(hql);
	}
}
