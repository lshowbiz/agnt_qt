package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JfiBankbookTemp;

/**
 * An interface that provides a data management interface to the JfiBankbookTemp table.
 */
public interface JfiBankbookTempDao extends GenericDao<JfiBankbookTemp, Long> {

	public long getCountByDate(final String companyCode, final String userCode);
	
	public void saveJfiBankbookTemp(JfiBankbookTemp jfiBankbookTemp);
}