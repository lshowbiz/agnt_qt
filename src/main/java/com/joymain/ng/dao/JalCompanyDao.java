package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalCompany;

/**
 * An interface that provides a data management interface to the JalCompany table.
 */
public interface JalCompanyDao extends GenericDao<JalCompany, Long> {
	
	public JalCompany getAlCompanyByCode(final String companyCode);
}