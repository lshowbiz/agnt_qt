package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.FiBcoinJournal;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the FiBcoinJournal table.
 */
public interface FiBcoinJournalDao extends GenericDao<FiBcoinJournal, Long> {

	public List<FiBcoinJournal> getFiBcoinJournalListByUser(String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize);
	
	public List<FiBcoinJournal> getFiBcoinJournalListByUserPage(GroupPage page,String userCode, String dealStartTime, String dealEndTime);
	
	public FiBcoinJournal getLastFiBcoinJournalByUnique(final String uniqueCode,final String dealType);
	
	public FiBcoinJournal getLastFiBcoinJournal(final String userCode);
	
	public long getCountByDate(final String userCode);
	
	public void saveFiBcoinJournal(final FiBcoinJournal fiBcoinJournal);
}