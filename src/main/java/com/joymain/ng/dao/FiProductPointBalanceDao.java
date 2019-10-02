package com.joymain.ng.dao;

import com.joymain.ng.model.FiProductPointBalance;


/**
 * An interface that provides a data management interface to the FiBankbookBalance table.
 */
public interface FiProductPointBalanceDao extends GenericDao<FiProductPointBalance, Long> {

	public FiProductPointBalance getFiProductPoinBalanceByUserCodeAndBankbookType(final String userCode, final String productPointType);
	
	public FiProductPointBalance getFiProductPointBalance(final String userCode,final String productPointType);
	
	public void saveFiProductPointBalance(FiProductPointBalance fiProductPointBalance);
	
	public FiProductPointBalance getFiProductPointBalanceForUpdate(final String userCode);
}