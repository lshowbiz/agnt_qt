package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiBcoinPayconfig;

/**
 * An interface that provides a data management interface to the FiBcoinPayconfig table.
 */
public interface FiBcoinPayconfigDao extends GenericDao<FiBcoinPayconfig, Long> {

	public FiBcoinPayconfig getFiBcoinPayconfigByNowTime();
}