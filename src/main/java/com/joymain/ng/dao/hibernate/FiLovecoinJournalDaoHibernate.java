package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.FiLovecoinJournal;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.FiLovecoinJournalDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiLovecoinJournalDao")
public class FiLovecoinJournalDaoHibernate extends GenericDaoHibernate<FiLovecoinJournal, Long> implements FiLovecoinJournalDao {

    public FiLovecoinJournalDaoHibernate() {
        super(FiLovecoinJournal.class);
    }

	@Override
	public List<FiLovecoinJournal> getFiLovecoinJournalsByUserCode(String userCode,
			String startTime, String endTime) {
		
		String hql="from FiLovecoinJournal fiLovecoinJournal where fiLovecoinJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(startTime)){
			hql+=" and fiLovecoinJournal.dealDate>=to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(endTime)){
			hql+=" and fiLovecoinJournal.dealDate<=to_date('" + endTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		hql+=" order by fiLovecoinJournal.dealDate desc";
	
		return this.getSession().createQuery(hql).list();
	}
	
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	public FiLovecoinJournal getLastFiLovecoinJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiLovecoinJournal) this.getObjectByHqlQuery("from FiLovecoinJournal where uniqueCode='" + uniqueCode
		        + "' and dealType='" + dealType +  "' order by journalId desc");
		}catch(ClassCastException ex){
			return null;
		}
	}
	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	public FiLovecoinJournal getLastFiLovecoinJournal(final String userCode) {
		try{
			return (FiLovecoinJournal) this.getObjectByHqlQuery("from FiLovecoinJournal where userCode='" + userCode + "' order by journalId desc");
		}catch(ClassCastException ex){
			return null;
		}
	}
	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String userCode) {

		return this.jdbcTemplate.queryForLong("select count(*) as totalCount from FI_LOVECOIN_JOURNAL where USER_CODE='" + userCode + "'");
	}
}
