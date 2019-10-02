package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiLovecoinJournal;

/**
 * An interface that provides a data management interface to the FiLovecoinJournal table.
 */
public interface FiLovecoinJournalDao extends GenericDao<FiLovecoinJournal, Long> {

	public List<FiLovecoinJournal> getFiLovecoinJournalsByUserCode(String userCode, String startTime, String endTime);
	
	 /**
     * 获取验证ID对应的最后一条存折记录
     */
    public FiLovecoinJournal getLastFiLovecoinJournalByUnique(final String uniqueCode,final String dealType);
    
    /**
     * 获取用户对应的最后一条存折记录
     */
    public FiLovecoinJournal getLastFiLovecoinJournal(final String userCode);
   
    /**
     * 获取某用户的存折流水条数
     */
    public long getCountByDate(final String userCode);
}