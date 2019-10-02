package com.joymain.ng.dao;

import java.util.Map;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysId;

/**
 * An interface that provides a data management interface to the JsysId table.
 */
public interface JsysIdDao extends GenericDao<JsysId, Long> {

	public Map callProcBuildIdStr(final String idCode);
}