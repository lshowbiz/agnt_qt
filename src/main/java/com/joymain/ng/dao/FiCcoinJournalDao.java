package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiCcoinJournal;

/**
 * An interface that provides a data management interface to the FiCcoinJournal table.
 */
public interface FiCcoinJournalDao extends GenericDao<FiCcoinJournal, Long> {
	public FiCcoinJournal getLastFiCcoinJournal(final String userCode);
}