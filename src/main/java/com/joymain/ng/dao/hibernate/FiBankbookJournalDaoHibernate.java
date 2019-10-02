
package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiBankbookJournalDao;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("fiBankbookJournalDao")
public class FiBankbookJournalDaoHibernate extends GenericDaoHibernate<FiBankbookJournal, Long> implements FiBankbookJournalDao {

	public FiBankbookJournalDaoHibernate() {
	        super(FiBankbookJournal.class);
	 }
	 
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	@Override
	public FiBankbookJournal getLastFiBankbookJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiBankbookJournal) this.getObjectByHqlQuery("from FiBankbookJournal where uniqueCode='" + uniqueCode
		        + "' and dealType='" + dealType + "' order by journalId desc");
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
		return this.getJdbcTemplate().queryForLong("select count(*) as totalCount from FI_BANKBOOK_JOURNAL where COMPANY_CODE='" + companyCode
		        + "' and USER_CODE='" + userCode + "'");
	}



	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	@Override
	public FiBankbookJournal getLastFiBankbookJournal(final String userCode,final String dealType) {
		return (FiBankbookJournal) this.getObjectByHqlQuery("from FiBankbookJournal where userCode='" + userCode
		        + "' and bankbookType = '" + dealType + "' order by journalId desc");
	}
	
	@Override
	public List<FiBankbookJournal> getFiBankbookJournalListByUser(String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize) {
		
		String sql="from FiBankbookJournal fiBankbookJournal where fiBankbookJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and fiBankbookJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and fiBankbookJournal.dealDate<=to_date('" + dealEndTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by fiBankbookJournal.dealDate desc";
		
		//返回分页数据
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@Override
	public List<FiBankbookJournal> getFiBankbookJournalListByUserPage(GroupPage page,String userCode, String dealStartTime, String dealEndTime) {
		//记录条数查询
		String totalHql="select count(*) from FiBankbookJournal fiBankbookJournal where fiBankbookJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			totalHql+=" and fiBankbookJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			totalHql+=" and fiBankbookJournal.dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		totalHql+=" order by fiBankbookJournal.journalId desc";
		
		//数据查询语句
		String hql="from FiBankbookJournal fiBankbookJournal where fiBankbookJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			hql+=" and fiBankbookJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			hql+=" and fiBankbookJournal.dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		hql+=" order by fiBankbookJournal.journalId desc";
	
		return findObjectsByHQL(totalHql,hql, page);
//		return this.getSession().createQuery(sql).list();
	}

	@Override
	public void saveFiBankbookJournal(FiBankbookJournal fiBankbookJournal) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(fiBankbookJournal);
	}
}
