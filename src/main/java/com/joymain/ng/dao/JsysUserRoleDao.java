package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysUserRole;

/**
 * An interface that provides a data management interface to the JsysUserRole table.
 */
public interface JsysUserRoleDao extends GenericDao<JsysUserRole, Long> {
	public JsysUserRole getSysUserRoleByUserCode(String userCode);
}