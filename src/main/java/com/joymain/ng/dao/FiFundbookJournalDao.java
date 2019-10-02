package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FiFundbookJournal;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the FiFundbookJournal table.
 */
public interface FiFundbookJournalDao extends GenericDao<FiFundbookJournal, Long> {
	
	public List<FiFundbookJournal> getFiFundbookJournalListByUserPage(GroupPage page, String userCode, String bankbookType, String dealStartTime,String dealEndTime);
	
	/**
	 * 获取验证ID对应的最后一条产业化基金记录
	 * @param uniqueCode
	 * @return
	 */
	public FiFundbookJournal getLastFiFundbookJournalByUnique(final String uniqueCode,final String dealType);
	
	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode);
	
	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	public FiFundbookJournal getLastFiFundbookJournal(final String userCode,final String dealType);
}