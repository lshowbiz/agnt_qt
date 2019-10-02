package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiLevelLock;

/**
 * An interface that provides a data management interface to the JmiLevelLock table.
 */
public interface JmiLevelLockDao extends GenericDao<JmiLevelLock, Long> {

	public JmiLevelLock getJmiLevelLock(String userCode);
}