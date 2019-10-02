package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysListValue;

/**
 * An interface that provides a data management interface to the JsysListValue table.
 */
public interface JsysListValueDao extends GenericDao<JsysListValue, Long> {
	/**
	 * 根据List编码获取对应的List值列表
	 * @param listCode
	 * @param companyCode
	 * @return
	 */
	public List getSysListValuesByCode(final String listCode, final String companyCode);

}