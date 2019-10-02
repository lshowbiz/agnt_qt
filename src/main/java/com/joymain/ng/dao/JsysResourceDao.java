package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysResource;

/**
 * An interface that provides a data management interface to the JsysResource table.
 */
public interface JsysResourceDao extends GenericDao<JsysResource, Long> {
	public List getGrantedAuthorityResource(String resType);
	public List getMenusByUserCode(String userCode);
	public List getMenusByUserCodeAndLayer(String userCode,String layer);
	public List getSubMenusByUserCodeAndParent(String userCode,Long parentId);
	public List getCommonMenu(String parmcode);
	public List getTLMenu(String resCode);
}