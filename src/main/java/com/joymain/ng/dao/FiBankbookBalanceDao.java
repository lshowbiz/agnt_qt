package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FiBankbookBalance;

/**
 * An interface that provides a data management interface to the FiBankbookBalance table.
 */
public interface FiBankbookBalanceDao extends GenericDao<FiBankbookBalance, Long> {

	public FiBankbookBalance getFiBankbookBalanceByUserCodeAndBankbookType(final String userCode, final String bankbookType);
	
	public FiBankbookBalance getFiBankbookBalance(final String userCode,
			final String backbookType);
	
	public void saveFiBankbookBalance(FiBankbookBalance fiBankbookBalance);
	
	public FiBankbookBalance getFiBankbookBalanceForUpdate(final Long fbbId);
}