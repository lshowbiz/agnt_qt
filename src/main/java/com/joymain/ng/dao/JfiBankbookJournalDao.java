package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JfiBankbookJournal table.
 */
public interface JfiBankbookJournalDao extends GenericDao<JfiBankbookJournal, Long> {

	public void saveJfiBankbookJournal(final JfiBankbookJournal jfiBankbookJournal);
	
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCode(String userCode,String dealStartTime,String dealEndTime);
	
	/**
	 * web分页
	 * @param userCode
	 * @param dealStartTime
	 * @param dealEndTime
	 * @return
	 */
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime);

	
	/**
	 * Add By WuCF 2013-11-25
	 * 手机分页展示数据   
	 * @param userCode
	 * @param dealStartTime
	 * @param dealEndTime
	 * @return
	 */
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(String userCode,String dealStartTime,String dealEndTime,int pageNum,int pageSize);
	
	public JfiBankbookJournal getLastJfiBankbookJournalByUnique(final String uniqueCode,final String dealType);
	
	public JfiBankbookJournal getLastJfiBankbookJournal(final String userCode);
	
	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode);
}