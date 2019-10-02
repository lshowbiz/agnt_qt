package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiSecurityDepositJournal;

/**
 * An interface that provides a data management interface to the FiSecurityDepositJournal table.
 */
public interface FiSecurityDepositJournalDao extends GenericDao<FiSecurityDepositJournal, Long> {

	public List<FiSecurityDepositJournal> getFiSecurityDepositJournalsByUserCode(String userCode);
}