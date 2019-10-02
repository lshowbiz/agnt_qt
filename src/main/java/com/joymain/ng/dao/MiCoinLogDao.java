package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.MiCoinLog;

/**
 * An interface that provides a data management interface to the MiCoinLog table.
 */
public interface MiCoinLogDao extends GenericDao<MiCoinLog, Long> {
	public void saveMiCoinLog(MiCoinLog miCoinLog);
}