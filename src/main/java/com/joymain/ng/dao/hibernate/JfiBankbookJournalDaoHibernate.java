
package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JfiBankbookJournalDao;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("jfiBankbookJournalDao")
public class JfiBankbookJournalDaoHibernate extends GenericDaoHibernate<JfiBankbookJournal,Long> implements JfiBankbookJournalDao {

	public JfiBankbookJournalDaoHibernate() {
        super(JfiBankbookJournal.class);
    }
	
	@Override
	public void saveJfiBankbookJournal(final JfiBankbookJournal jfiBankbookJournal) {
        this.getSession().saveOrUpdate(jfiBankbookJournal);
    }
	
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	@Override
	public JfiBankbookJournal getLastJfiBankbookJournalByUnique(final String uniqueCode,final String dealType) {
		try{
			return (JfiBankbookJournal) this.getObjectByHqlQuery("from JfiBankbookJournal where uniqueCode='" + uniqueCode
		        + "' and dealType='" + dealType + "' order by journalId desc");
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
	public JfiBankbookJournal getLastJfiBankbookJournal(final String userCode) {
		return (JfiBankbookJournal) this.getObjectByHqlQuery("from JfiBankbookJournal where userCode='" + userCode
		        + "' order by journalId desc");
	}

	@Override
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCode(
			String userCode, String dealStartTime, String dealEndTime) {
		
		String sql="select * from JFI_BANKBOOK_JOURNAL where USER_CODE='"+userCode+"'";
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and DEAL_DATE>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and DEAL_DATE<=to_date('" + dealEndTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by JOURNAL_ID desc";
		

		List list=this.jdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(
			GroupPage page,String userCode, String dealStartTime, String dealEndTime) {
		
		String sql="select * from JFI_BANKBOOK_JOURNAL where USER_CODE='"+userCode+"'";
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
	
	/**
	 * 分页查询
	 */
	@Override
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(
			String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize) {
		
		String sql="from JfiBankbookJournal jbj where jbj.userCode='"+userCode+"'";
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and jbj.dealDate>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and jbj.dealDate<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by jbj.journalId desc";
		

//		List list=this.jdbcTemplate.queryForList(sql);
//		return list;
		//返回分页数据
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	@Override
	public long getCountByDate(final String companyCode, final String userCode) {
		return this.getJdbcTemplate().queryForLong("select count(*) as totalCount from JFI_BANKBOOK_JOURNAL where COMPANY_CODE='" + companyCode
		        + "' and USER_CODE='" + userCode + "'");
	}
}
