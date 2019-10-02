package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.FiFundbookJournal;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.FiFundbookJournalDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("fiFundbookJournalDao")
public class FiFundbookJournalDaoHibernate extends GenericDaoHibernate<FiFundbookJournal, Long> implements FiFundbookJournalDao {

    public FiFundbookJournalDaoHibernate() {
        super(FiFundbookJournal.class);
    }

	@Override
	public List<FiFundbookJournal> getFiFundbookJournalListByUserPage(GroupPage page, String userCode, String bankbookType, String dealStartTime,String dealEndTime) {
		
		//记录条数查询
		String totalHql="select count(*) from FiFundbookJournal fiFundbookJournal where fiFundbookJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(bankbookType)){
			totalHql+=" and fiFundbookJournal.bankbookType='"+ bankbookType +"'";
		}
		if(!StringUtil.isEmpty(dealStartTime)){
			totalHql+=" and fiFundbookJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			totalHql+=" and fiFundbookJournal.dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		totalHql+=" order by fiFundbookJournal.journalId desc";
		
		//数据查询语句
		String hql="from FiFundbookJournal fiFundbookJournal where fiFundbookJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(bankbookType)){
			hql+=" and fiFundbookJournal.bankbookType='"+ bankbookType +"'";
		}
		if(!StringUtil.isEmpty(dealStartTime)){
			hql+=" and fiFundbookJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			hql+=" and fiFundbookJournal.dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		hql+=" order by fiFundbookJournal.journalId desc";
	
		return findObjectsByHQL(totalHql,hql, page);
	}
	
	/**
	 * 获取验证ID对应的最后一条产业化基金记录
	 * @param uniqueCode
	 * @return
	 */
	@Override
	public FiFundbookJournal getLastFiFundbookJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiFundbookJournal) this.getObjectByHqlQuery("from FiFundbookJournal where uniqueCode='" + uniqueCode + "' and dealType='" + dealType + "' order by journalId desc");
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
	@Override
	public long getCountByDate(final String companyCode, final String userCode) {
		return this.getJdbcTemplate().queryForLong("select count(*) as totalCount from FI_FUNDBOOK_JOURNAL where COMPANY_CODE='" + companyCode
		        + "' and USER_CODE='" + userCode + "'");
	}
	
	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	@Override
	public FiFundbookJournal getLastFiFundbookJournal(final String userCode,final String dealType) {
		return (FiFundbookJournal) this.getObjectByHqlQuery("from FiFundbookJournal where userCode='" + userCode + "' and bankbookType = '" + dealType + "' order by journalId desc");
	}
}
