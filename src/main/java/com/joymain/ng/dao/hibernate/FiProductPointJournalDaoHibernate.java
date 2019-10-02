
package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiProductPointJournalDao;
import com.joymain.ng.model.FiProductPointJournal;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("fiProductPointJournalDao")
public class FiProductPointJournalDaoHibernate extends GenericDaoHibernate<FiProductPointJournal, Long> implements FiProductPointJournalDao {

	public FiProductPointJournalDaoHibernate() {
	        super(FiProductPointJournal.class);
	 }
	 
	/**
	 * 获取验证ID对应的最后一条产品积分记录
	 * @param uniqueCode
	 * @return
	 */
	@Override
	public FiProductPointJournal getLastFiProductPointJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiProductPointJournal) this.getObjectByHqlQuery("from FiProductPointJournal where uniqueCode='" + uniqueCode
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
		return this.getJdbcTemplate().queryForLong("select count(*) as totalCount from FI_ProductPoint_JOURNAL where COMPANY_CODE='" + companyCode
		        + "' and USER_CODE='" + userCode + "'");
	}

	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	@Override
	public FiProductPointJournal getLastFiProductPointJournal(final String userCode,final String dealType) {
		return (FiProductPointJournal) this.getObjectByHqlQuery("from FiProductPointJournal where userCode='" + userCode
		        + "' and dealType = '" + dealType + "' order by journalId desc");
	}
	
	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	@Override
	public FiProductPointJournal getLastFiProductPointJournal(final String userCode) {
		return (FiProductPointJournal) this.getObjectByHqlQuery(
				"from FiProductPointJournal where userCode='" + userCode + "' order by journalId desc");
	}

	@Override
	public List<FiProductPointJournal> getFiProductPointJournalListByUser(String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize) {
		
		String sql="from FiProductPointJournal fiProductPointJournal where fiProductPointJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and fiProductPointJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and fiProductPointJournal.dealDate<=to_date('" + dealEndTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by fiProductPointJournal.dealDate desc";
		
		//返回分页数据
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@Override
	public List<FiProductPointJournal> getFiProductPointJournalListByUserPage(GroupPage page,String userCode, String dealStartTime, String dealEndTime) {
		//记录条数查询
		String totalHql="select count(*) from FiProductPointJournal fiProductPointJournal where fiProductPointJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			totalHql+=" and fiProductPointJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			totalHql+=" and fiProductPointJournal.dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		totalHql+=" order by fiProductPointJournal.journalId desc";
		
		//数据查询语句
		String hql="from FiProductPointJournal fiProductPointJournal where fiProductPointJournal.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			hql+=" and fiProductPointJournal.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			hql+=" and fiProductPointJournal.dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		hql+=" order by fiProductPointJournal.journalId desc";
	
		return findObjectsByHQL(totalHql,hql, page);
//		return this.getSession().createQuery(sql).list();
	}

	@Override
	public void saveFiProductPointJournal(FiProductPointJournal fiProductPointJournal) {
		this.getSession().saveOrUpdate(fiProductPointJournal);
	}

	@Override
	public List<FiProductPointJournal> getJfiProductPointJournalListByUserCodePage(
			GroupPage page, String userCode, String dealStartTime,
			String dealEndTime) {
		// TODO Auto-generated method stub
		String sql="select * from fi_product_point_journal where USER_CODE='"+userCode+"'";
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and DEAL_DATE>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and DEAL_DATE<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by JOURNAL_ID desc";
		

//		List list=this.jdbcTemplate.queryForList(sql);
		List list = findObjectsBySQL(sql, page);
		return list;
	}
}
