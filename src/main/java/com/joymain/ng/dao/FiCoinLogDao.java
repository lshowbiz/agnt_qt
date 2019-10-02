package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiCoinLog;

/**
 * An interface that provides a data management interface to the FiCoinLog table.
 */
public interface FiCoinLogDao extends GenericDao<FiCoinLog, Long> {
	
	public List getFiCoinLogs(final FiCoinLog fiCoinLog);
}