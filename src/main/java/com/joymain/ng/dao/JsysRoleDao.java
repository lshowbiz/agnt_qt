package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysRole;

/**
 * An interface that provides a data management interface to the JsysRole table.
 */
public interface JsysRoleDao extends GenericDao<JsysRole, Long> {

    /**
     * 根据角色编码获取对应的角色记录
     * @param roleCode
     * @return
     */
    public JsysRole getSysRoleByCode(final String roleCode);
}