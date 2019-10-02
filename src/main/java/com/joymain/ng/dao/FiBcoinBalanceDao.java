package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiBcoinBalance;

/**
 * An interface that provides a data management interface to the FiBcoinBalance table.
 */
public interface FiBcoinBalanceDao extends GenericDao<FiBcoinBalance, String> {

	public FiBcoinBalance getFiBcoinBalanceForUpdate(final String userCode);
	
	public void saveFiBcoinBalance(final FiBcoinBalance fiBcoinBalance);
}