package com.joymain.ng.dao;

import org.hibernate.LockMode;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JfiBankbookBalance;

/**
 * An interface that provides a data management interface to the JfiBankbookBalance table.
 */
public interface JfiBankbookBalanceDao extends GenericDao<JfiBankbookBalance, String> {

	public JfiBankbookBalance getJfiBankbookBalanceForUpdate(final String userCode);
	
	public JfiBankbookBalance getJfiBankbookBalance(final String userCode);
	
	public void saveJfiBankbookBalance(final JfiBankbookBalance jfiBankbookBalance);
}