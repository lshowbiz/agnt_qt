
package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiBcoinJournalDao;
import com.joymain.ng.model.FiBcoinJournal;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("fiBcoinJournalDao")
public class FiBcoinJournalDaoHibernate extends GenericDaoHibernate<FiBcoinJournal, Long> implements FiBcoinJournalDao {


    public FiBcoinJournalDaoHibernate() {
		super(FiBcoinJournal.class);
	}
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
    @Override
	public FiBcoinJournal getLastFiBcoinJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (FiBcoinJournal) this.getObjectByHqlQuery("from FiBcoinJournal where uniqueCode='" + uniqueCode
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
	@Override
	public FiBcoinJournal getLastFiBcoinJournal(final String userCode) {
		return (FiBcoinJournal) this.getObjectByHqlQuery("from FiBcoinJournal where userCode='" + userCode
		        + "' order by journalId desc");
	}
	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	@Override
	public long getCountByDate(final String userCode) {
		return this.jdbcTemplate.queryForLong("select count(*) as totalCount from FI_BCOIN_JOURNAL where USER_CODE='" + userCode + "'");
	}

	@Override
	public List<FiBcoinJournal> getFiBcoinJournalListByUser(String userCode,
			String dealStartTime, String dealEndTime,int pageNum,int pageSize) {
		
		String sql="select f from FiBcoinJournal f where userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and dealDate<=to_date('" + dealEndTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by journalId desc";
		
		//返回分页数据
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@Override
	public List<FiBcoinJournal> getFiBcoinJournalListByUserPage(GroupPage page,String userCode,
			String dealStartTime, String dealEndTime) {
		
		String totalHql="select count(*) from FiBcoinJournal f where userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			totalHql+=" and dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			totalHql+=" and dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		totalHql+=" order by journalId desc";
		
		String sql="select f from FiBcoinJournal f where userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by journalId desc";
		
//		return this.getSession().createQuery(sql).list();
		return findObjectsByHQL(totalHql,sql, page);
	}
	
	@Override
	public void saveFiBcoinJournal(FiBcoinJournal fiBcoinJournal) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(fiBcoinJournal);
	}
}
