package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiLovecoinBalance;

/**
 * An interface that provides a data management interface to the FiLovecoinBalance table.
 */
public interface FiLovecoinBalanceDao extends GenericDao<FiLovecoinBalance, String> {
	public FiLovecoinBalance getFiLovecoinBalanceForUpdate(final String userCode);
}