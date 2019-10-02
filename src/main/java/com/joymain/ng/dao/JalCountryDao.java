package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalCountry;

/**
 * An interface that provides a data management interface to the JalCountry table.
 */
public interface JalCountryDao extends GenericDao<JalCountry, Long> {

	public List getAlCountrysByCompany(final String companyCode);
}