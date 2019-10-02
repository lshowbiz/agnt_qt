package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysConfigValue;

/**
 * An interface that provides a data management interface to the JsysConfigValue table.
 */
public interface JsysConfigValueDao extends GenericDao<JsysConfigValue, Long> {

	List getSysConfigValuesByCodeSQL(String companyCode);

}