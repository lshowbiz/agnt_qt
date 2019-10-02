package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the FiBankbookJournal table.
 */
public interface FiBankbookJournalDao extends GenericDao<FiBankbookJournal, Long> {
	
	public List<FiBankbookJournal> getFiBankbookJournalListByUser(String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize);
	
	public List<FiBankbookJournal> getFiBankbookJournalListByUserPage(GroupPage page,String userCode, String dealStartTime, String dealEndTime);

	
	//获取验证ID对应的最后一条存折记录
	public FiBankbookJournal getLastFiBankbookJournalByUnique(final String uniqueCode,final String dealType);
	
	//获取用户对应的最后一条存折记录
	public FiBankbookJournal getLastFiBankbookJournal(final String userCode,final String dealType);
	
	public long getCountByDate(final String companyCode, final String userCode);
	
	public void saveFiBankbookJournal(FiBankbookJournal fiBankbookJournal);
	
}