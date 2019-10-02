package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.AmAnnounceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.AmAnnounce;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("amAnnounceDao")
public class AmAnnounceDaoJpa extends GenericDaoHibernate<AmAnnounce, String> implements AmAnnounceDao{

	public AmAnnounceDaoJpa() {
		super(AmAnnounce.class);
	}

	@Override
	public List<AmAnnounce> findAnnounceByDate(String startDate, String endDate) {
		// TODO Auto-generated method stub 
		return null;
	}
	
	
	@Override
	public List<AmAnnounce> findAllAnnounce() {
		String sql = "from AmAnnounce t order by t.createTime desc";
		return this.getSession().createQuery(sql).list();
	}
	
	@Override
	public List<AmAnnounce> findAllAnnounce(String userCode) {
		String sql = "from AmAnnounce a where not exists "+
			         " (select 1 from AmAnnounceRecord b where a.aaNo=b.amAnnounce.aaNo and b.userNo='"+userCode+"')";
		return this.getSession().createQuery(sql).list();
	}

	@Override
	public List<AmAnnounce> findAnnounceByRowNum(int rownum) {
		String hql = "from AmAnnounce a where rownum <="+rownum+" ";
		Query query = this.getSession().createQuery(hql);
		List<AmAnnounce> anList = query.list();
		
		return anList;
	}

	@Override
	public List<AmAnnounce> findAnnounceByColumn(Map<String, String> map) {
		String hql = "from AmAnnounce a where 1=1 ";
		
		if(map.containsKey("annSub")){
			String subject = map.get("annSub");
			hql +=" and a.subject like '%"+subject+"%' ";
		}
		if(map.containsKey("anStatus")){
			String status = map.get("anStatus");
			hql +=" and a.status='"+status+"' ";
		}
		if(map.containsKey("annoClassNo")){
			String annoClassNo = map.get("annoClassNo");
			hql += " and a.annoClassNo='"+annoClassNo+"' ";
		}
		if(map.containsKey("stime")){
			String stime = map.get("stime");
			hql += " and a.createTime >= to_date('"+stime+"','yyyy-MM-dd hh24:mi') ";
		}
		if(map.containsKey("etime")){
			String etime = map.get("etime");
			hql += " and a.createTime <= to_date('"+etime+"','yyyy-MM-dd hh24:mi') ";
		}
		hql +=" order by a.createTime desc";
		
		return getSession().createQuery(hql).list();
	}
	
	public int countNotReadAnnounce(Map map) {
		

		/*String sql = "select distinct am.AA_NO aaNo,am.COMPANY_CODE companyCode,am.SUBJECT subject, " +
		"am.issue_user_no ,am.STATUS status,am.CREATE_TIME, am.issuer_name ,am.end_time " +
			" ,am.check_user_no, am.checker_name from am_announce am,am_announce_permit ap where 1=1  ";

		   	
		Object companyCode = map.get("companyCode");
		if(companyCode!=null){
			sql +=" and am.COMPANY_CODE='"+companyCode+"'";
		}	
		
		Object viewFlag = map.get("viewFlag");
		if("view".equals(viewFlag)){
			sql +=" and (am.END_TIME >= sysdate or am.END_TIME is null)  and (am.start_time <= sysdate or am.start_time is null)";
		}
		
		Object status = map.get("status");
		if(status!=null){
			sql +=" and am.STATUS = "+status;
		}
		Object permitClass = map.get("permitClass");
		if(permitClass!=null){
			sql +=" and am.AA_NO=ap.AA_NO and ap.PERMIT_NO in('"+permitClass+"')";
		}
		sql = "select * from ("+sql+") t1 ,(select * from am_announce_record s where s.user_no='"+map.get("browserNo")+"') t2  where t1.aano = t2.aa_no(+) and t2.user_no is null ";
		
		return this.jdbcTemplate.queryForInt("select count(*) from ("+sql+")");*/
		
		//Modify By WuCF 20140703 绑定变量
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		
		//查询语句
		StringBuffer sql = new StringBuffer("select count(*) from ( ");
		sql.append(" select * from (select distinct am.AA_NO aaNo,am.COMPANY_CODE companyCode,am.SUBJECT subject, ");
		sql.append(" am.issue_user_no ,am.STATUS status,am.CREATE_TIME, am.issuer_name ,am.end_time ");
		sql.append(" ,am.check_user_no, am.checker_name from am_announce am,am_announce_permit ap where 1=1  ");
		
		sql.append(" and (am.OUT_ANNOUNCE!='1' or am.OUT_ANNOUNCE is null) ");//过滤下架的公告
		   	
		Object companyCode = map.get("companyCode");
		if(companyCode!=null){
			sql.append(" and am.COMPANY_CODE=? ");
			paramsBuf.append(","+companyCode);
		}	
		
		Object viewFlag = map.get("viewFlag");
		if("view".equals(viewFlag)){
			sql.append(" and (am.END_TIME >= sysdate or am.END_TIME is null)  and (am.start_time <= sysdate or am.start_time is null)");
		}
		
		Object status = map.get("status");
		if(status!=null){
			sql.append(" and am.STATUS=? ");
			paramsBuf.append(","+status);
		}
		Object permitClass = map.get("permitClass");
		if(permitClass!=null){
			sql.append(" and am.AA_NO=ap.AA_NO and ap.PERMIT_NO in('"+permitClass+"')");
		}
		sql.append(" ) t1 ,(select * from am_announce_record s where s.user_no= ? ");
		paramsBuf.append(","+map.get("browserNo"));
		sql.append(") t2  where t1.aano = t2.aa_no(+) and t2.user_no is null ");
		
		sql.append(" )"); 
		
		//返回时调用分页的查询的方法 
	    Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return jdbcTemplate3.queryForInt(sql.toString(),parameters); 
	}

	public int findAnnounceCount(Map map) {
		int count = 0;//记录条数
    	
    	String sql = "select count(*) from am_announce am " +
    			"left join am_announce_record r on r.aa_no = am.aa_no and r.user_no='"+map.get("browserNo")+"'  where 1=1  ";

	   	
		Object companyCode = map.get("companyCode");
		if(companyCode!=null){
			sql +=" and am.COMPANY_CODE='"+companyCode+"'";
		}	
		
		Object viewFlag = map.get("viewFlag");
		if("view".equals(viewFlag)){
			sql +=" and (am.END_TIME >= sysdate or am.END_TIME is null)  and (am.start_time <= sysdate or am.start_time is null)";
		}
		
		Object status = map.get("status");
		if(status!=null){
			sql +=" and am.STATUS = "+status;
		}



		Object permitClass = map.get("permitClass");
		if(permitClass!=null){
			sql +=" and am.AA_NO in (select ap.aa_no from am_announce_permit ap where ap.PERMIT_NO in ('"+permitClass+"'))";
		}
		//分页展示
		Query query = this.getSession().createSQLQuery(sql);
		List list = this.jdbcTemplate.queryForList(sql);	
		if(list!=null && list.size()>=1){
			count = Integer.parseInt(list.get(0).toString().split("=")[1].replace("}", ""));
		}
		return count;
	}
	
	public List findAnnouncePage(GroupPage page,Map map) {
		/*select am.AA_NO         aaNo, --str[0]
            am.COMPANY_CODE  companyCode,--str[1]
            am.SUBJECT       subject,--str[2]
            am.issue_user_no,--str[3]
            am.STATUS        status,--str[4]
            am.CREATE_TIME,--str[5]
            am.issuer_name,--str[6]
            am.end_time,--str[7]
            am.check_user_no,--str[8]
            am.checker_name,--str[9]
            am.anno_Class_No,--str[10]
            am.highlight,--str[11]
            r.uni_no,--str[12]
            am.content--str[13]*/
    	
    	String sql = "select am.AA_NO aaNo,am.COMPANY_CODE companyCode,am.SUBJECT subject, " +
    		"am.issue_user_no ,am.STATUS status,am.CREATE_TIME, am.issuer_name ,am.end_time " +
    			" ,am.check_user_no, am.checker_name ,am.anno_Class_No,am.highlight,r.uni_no,am.content from am_announce am " +
    			"left join am_announce_record r on r.aa_no = am.aa_no and r.user_no='"+map.get("browserNo")+"'  where 1=1  ";

	   	sql += " and (am.OUT_ANNOUNCE!='1' or am.OUT_ANNOUNCE is null) ";//过滤下架的公告
    	
		Object companyCode = map.get("companyCode");
		if(companyCode!=null){
			sql +=" and am.COMPANY_CODE='"+companyCode+"'";
		}	
		
		Object viewFlag = map.get("viewFlag");
		if("view".equals(viewFlag)){
			sql +=" and (am.END_TIME >= sysdate or am.END_TIME is null)  and (am.start_time <= sysdate or am.start_time is null)";
		}
		
		Object status = map.get("status");
		if(status!=null){
			sql +=" and am.STATUS = "+status;
		}



		Object permitClass = map.get("permitClass");
		if(permitClass!=null){
			sql +=" and am.AA_NO in (select ap.aa_no from am_announce_permit ap where ap.PERMIT_NO in ('"+permitClass+"'))";
		}
		
//		sql += " order by r.uni_no desc,am.CREATE_TIME desc ";
		sql += " order by am.CREATE_TIME desc,r.uni_no desc ";
		

		Object reply_status = map.get("reply_status");
    	if("2".equals(reply_status)){
    		sql = "select * from ( "+
			sql+" ) t1 ,(select s.aa_no as aa_no_t2,s.user_no as user_no_t2 from am_announce_record s where s.user_no='"+map.get("browserNo")+"') t2  where t1.aano = t2.aa_no_t2(+) and t2.user_no_t2 is null ";
    	}
    	
    	if("3".equals(reply_status)){
    		sql = "select * from ( "+
    			sql+" ) t1 ,(select s.aa_no as aa_no_t2,s.user_no as user_no_t2 from am_announce_record s where s.user_no='"+map.get("browserNo")+"') t2 where t1.aano = t2.aa_no_t2 and t2.user_no_t2='"+map.get("browserNo")+"'";
    	}
    	
		
		
		
		
		return findObjectsBySQL3(sql, page);
		//分页展示
/*		Query query = this.getSession().createSQLQuery(sql);
		query.setFirstResult(pageNum*pageSize);
		query.setMaxResults(pageSize);
		
		
		return query.list();*/
		
		
		/*StringBuffer sqlBuf = new StringBuffer("select am from AmAnnounce am left join am.amAnnounceRecords r where 1=2 ");
		sqlBuf.append(" and r.userNo='");
		sqlBuf.append(map.get("browserNo"));
		sqlBuf.append("' ");
		
		Object companyCode = map.get("companyCode");
		if(companyCode!=null){
			sqlBuf.append(" and am.companyCode='");
			sqlBuf.append(companyCode);
			sqlBuf.append("' ");
		}	
		
		Object viewFlag = map.get("viewFlag");
		if("view".equals(viewFlag)){
			sqlBuf.append(" and (am.endTime >= sysdate or am.endTime is null)  and (am.startTime <= sysdate or am.startTime is null)");
		}
		
		Object status = map.get("status");
		if(status!=null){
			sqlBuf.append(" and am.status = '");
			sqlBuf.append(status);
			sqlBuf.append("' ");
		}

		Object permitClass = map.get("permitClass");
		if(permitClass!=null){
			sqlBuf.append(" and am.aaNo in (select ap.aaNo from AmAnnouncePermit ap where ap.permitNo in ('"+permitClass+"'))");
		}
		
		Query query = this.getSession().createQuery(sqlBuf.toString());
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();*/
//		return this.jdbcTemplate.queryForList(sql);	
	}

	@Override
	public AmAnnounce findAnnounceByaaNo(String aaNo) {
		String hql = "from AmAnnounce a where a.aaNo=:aaNo ";
		Query query =this.getSession().createQuery(hql);
		query.setParameter("aaNo", aaNo);
		List list=query.list();
		AmAnnounce aa=null;
		if(list!=null){
			aa=(AmAnnounce) list.get(0);
		}
		return aa;
	}
	
	public List getTeamLeader(String userCode){
		List list=jdbcTemplate3.queryForList("select Fn_Get_Team_No_Am('"+userCode+"') as team_no from dual");
		return list;
	}
}
