package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.FiProductPointJournal;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the FiBankbookJournal table.
 */
public interface FiProductPointJournalDao extends GenericDao<FiProductPointJournal, Long> {
	
	public List<FiProductPointJournal> getFiProductPointJournalListByUser(String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize);
	
	public List<FiProductPointJournal> getFiProductPointJournalListByUserPage(GroupPage page,String userCode, String dealStartTime, String dealEndTime);

	
	//获取验证ID对应的最后一条存折记录
	public FiProductPointJournal getLastFiProductPointJournalByUnique(final String uniqueCode,final String dealType);
	
	//获取用户对应的最后一条存折记录
	public FiProductPointJournal getLastFiProductPointJournal(final String userCode,final String dealType);
	
	public long getCountByDate(final String companyCode, final String userCode);
	
	public void saveFiProductPointJournal(FiProductPointJournal fiProductPointJournal);
	
	public FiProductPointJournal getLastFiProductPointJournal(final String userCode);

	public List<FiProductPointJournal> getJfiProductPointJournalListByUserCodePage(
			GroupPage page, String userCode, String dealStartTime,
			String dealEndTime);

	
}