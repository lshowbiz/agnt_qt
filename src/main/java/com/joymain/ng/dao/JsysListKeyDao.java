package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysListKey;

/**
 * An interface that provides a data management interface to the JsysListKey table.
 */
public interface JsysListKeyDao extends GenericDao<JsysListKey, Long> {

	List getSysListBySQL();
	public JsysListKey getListKeyByCode(String listCode);
}