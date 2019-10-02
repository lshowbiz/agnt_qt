package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiSecurityDeposit;

/**
 * An interface that provides a data management interface to the FiSecurityDeposit table.
 */
public interface FiSecurityDepositDao extends GenericDao<FiSecurityDeposit, Long> {

	public FiSecurityDeposit getFiSecurityDepositByUserCode(String userCode);
}