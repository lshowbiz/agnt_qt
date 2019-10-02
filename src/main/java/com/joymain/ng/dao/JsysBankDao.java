package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JsysBank;

/**
 * An interface that provides a data management interface to the JsysBank table.
 */
public interface JsysBankDao extends GenericDao<JsysBank, Long> {

	public List getSysBankByCompanyCode(String companyCode);
}