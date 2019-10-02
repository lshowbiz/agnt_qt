package com.joymain.ng.dao.jpa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdPeriodDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.util.GroupPage;

@Repository("jbdPeriodDao")
public class JbdPeriodDaoJpa extends GenericDaoHibernate<JbdPeriod, Long> implements JbdPeriodDao {

    public JbdPeriodDaoJpa() {
        super(JbdPeriod.class);
    }
	/**
	 * 获取距某一时间最近的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getLatestBdPeriodsFweek(final String theDate, int latelyCount){

		return this.jdbcTemplate.queryForList("select * from (select concat(w_year, Lpad(w_week, 2, 0)) as long_w_week," +
				"concat(f_year, Lpad(f_week, 2, 0)) as long_f_week, a.*,to_char(a.start_time,'yyyy-MM-dd') as std,to_char(a.end_time,'yyyy-MM-dd') as edd from jbd_period a where a.start_time <= " +
				"to_date('"+theDate+" 12:00:00', 'yyyy-MM-dd hh:mi:ss') and DAY_STATUS = 1 and a.bonus_send = 0 order by long_w_week desc)  where rownum <= "+latelyCount);
	}
	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @----使用到的模块：新旧期别对比用到这个方法－－gw
	 * @param theTime 检查的时候
	 * @param wid 排除的ID
	 * @return
	 */
	public JbdPeriod getBdPeriodByTime(final Date theTime){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String hqlQuery="from JbdPeriod where startTime <= to_date('"+dateFormat.format(theTime)+"','yyyy-mm-dd hh24:mi:ss') and endTime > to_date('"+dateFormat.format(theTime)+"','yyyy-mm-dd hh24:mi:ss')";
//		return (JbdPeriod)this.getObjectByHqlQuery(hqlQuery);
		//Modify By WuCF 20140630 查询语句修改成绑定变量
		Query q=getSession().createQuery("from JbdPeriod where startTime <= to_date(:startTime,'yyyy-mm-dd hh24:mi:ss') and endTime > to_date(:endTime,'yyyy-mm-dd hh24:mi:ss') ");
		q.setParameter("startTime",dateFormat.format(theTime));
	    q.setParameter("endTime",dateFormat.format(theTime));
	    List<JbdPeriod> list = q.list();
	    if(list!=null && list.size()>=1){	    	   
	    	return (JbdPeriod)list.get(0);
	    } 
		return null;
	}	
	/**
	 * 获取最后的已发放奖金的周
	 * @return
	 */
	public JbdPeriod getLatestSendBonus(){
		return (JbdPeriod)this.getObjectByHqlQuery("from JbdPeriod where bonusSend=1 order by wyear desc, wweek desc");
	}
	/**
	 * 传入财政期别，返回期别对象
	 * @param formatedFweek
	 * @return
	 */
	public JbdPeriod getBdPeriodByFormatedWeek(String formatedFweek){
		return (JbdPeriod)this.getObjectByHqlQuery("from JbdPeriod where concat(fyear, Lpad(fweek, 2, 0))='"+formatedFweek+"'");
	}
	
	@Override
	public List<JbdPeriod> findPeriodByYearMon(String year, String month) {
		String sql ="select * from jbd_period where w_year='"+year+"' and w_month='"+month+"' order by w_week asc";
		return this.getJdbcTemplate().query(sql, new JbdPeriod());
	}
	
	/**
	 * 会员信息系统－查询新旧期别对比的数据
	 * @author gw 2013-07-11
	 * @param wyear
	 * @return　list
	 */
	public List<JbdPeriod> getJbdPeriodOldAndNewWweekCom(int wyear){
		String sql = " select * from ( " +
		                     "  select *  from  "+
		                             " ( select * from jbd_period t  where concat(w_year, Lpad(w_week, 2, 0)) > " +
		                                     " (select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)" +
		                                          " order by t.end_time asc )  where rownum <= 104 "+
		                 " union all "+
		                    "  select * from "+
		                             " ( select *  from jbd_period t where concat(w_year, Lpad(w_week, 2, 0)) <= " +
		                                     "(select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)  " +
		                                          "order by t.end_time desc )  where rownum <= 70 "+
		             " ) order by end_time desc ";

		return this.getJdbcTemplate().query(sql, new JbdPeriod());
	}
	
	/**
	 * 获取月结算周期
	 * @param wyear
	 * @return
	 */
	public List<JbdPeriod> getJbdPeriodsByMonthStatus(int wyear){
		String sql = " select * from ( " +
		                     "  select *  from  "+
		                             " ( select * from jbd_period t  where concat(w_year, Lpad(w_week, 2, 0)) > " +
		                                     " (select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)" +
		                                          " order by t.end_time asc )  where rownum <= 104 "+
		                 " union all "+
		                    "  select * from "+
		                             " ( select *  from jbd_period t where concat(w_year, Lpad(w_week, 2, 0)) <= " +
		                                     "(select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)  " +
		                                          "order by t.end_time desc )  where rownum <= 70 "+
		             " ) where MONTH_STATUS=1 order by end_time desc ";

		return this.getJdbcTemplate().query(sql, new JbdPeriod());
	}
	
	/**
	 * 会员信息系统－查询新旧期别对比的数据
	 * @author WuCF 2013-11-29
	 * @param wyear
	 * @return　list
	 */
	public List<JbdPeriod> getJbdPeriodOldAndNewWweekComPage(GroupPage page,int wyear){
		String sql = " select * from ( " +
		                     "  select *  from  "+
		                             " ( select * from jbd_period t  where concat(w_year, Lpad(w_week, 2, 0)) > " +
		                                     " (select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)" +
		                                          " order by t.end_time asc )  where rownum <= 104 "+
		                 " union all "+
		                    "  select * from "+
		                             " ( select *  from jbd_period t where concat(w_year, Lpad(w_week, 2, 0)) <= " +
		                                     "(select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)  " +
		                                          "order by t.end_time desc )  where rownum <= 70 "+
		             " ) order by end_time desc ";

//		return this.getJdbcTemplate().query(sql, new JbdPeriod());
		return findObjectsBySQL(sql, page); 
	}

	/**
	 * 获取未来的后几期
	 * @param Year
	 * @param Month
	 * @param latelyCount
	 * @return
	 */
	public String getFutureBdYearMonth(final String year,final String month,final int latelyCount){
		String sql = "Select Max(w_Year || Lpad(w_Month, 2, 0)) period From (Select Distinct w_year,w_month From Jbd_Period Order By w_Year, w_Month) Where Concat(w_Year, Lpad(w_Month, 2, 0)) >= " + year + " || Lpad(" + month + ", 2, 0)And Rownum <= " +latelyCount + "";
		return this.findOneObjectBySQL(sql).get("period").toString();
	}
	public String getFutureBdYearWeek(final String year,final String week,final int latelyCount){
		String sql = "Select Max(w_Year || Lpad(w_week, 2, 0)) period From (Select Distinct w_year,w_week From Jbd_Period Order By w_Year, w_week) Where Concat(w_Year, Lpad(w_week, 2, 0)) >= " + year + " || Lpad(" + week + ", 2, 0)And Rownum <= " +latelyCount + "";
		return this.findOneObjectBySQL(sql).get("period").toString();
	}
	public JbdPeriod getBdPeriodByTime(final Date theTime, final Long wid){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String hqlQuery="from JbdPeriod where startTime <= to_date('"+dateFormat.format(theTime)+"','yyyy-mm-dd hh24:mi:ss') and endTime > to_date('"+dateFormat.format(theTime)+"','yyyy-mm-dd hh24:mi:ss')";
//		return (JbdPeriod)this.getObjectByHqlQuery(hqlQuery);
		//老系统更改-特将该部分注释掉２０１３－０８－０７修改时注释
		/*if(wid!=null){
			hqlQuery+=" and id<>'"+wid+"'";
		}*/
		//Modify By WuCF 20140630 查询语句修改成绑定变量
		Query q=getSession().createQuery("from JbdPeriod where startTime <= to_date(:startTime,'yyyy-mm-dd hh24:mi:ss') and endTime > to_date(:endTime,'yyyy-mm-dd hh24:mi:ss') ");
		q.setParameter("startTime",dateFormat.format(theTime));
	    q.setParameter("endTime",dateFormat.format(theTime));
	    List<JbdPeriod> list = q.list();
	    if(list!=null && list.size()>=1){	    	   
	    	return (JbdPeriod)list.get(0);
	    } 
		return null;
	}
	
	public String getBdPeriodcurrent(){
		String sql = "SELECT concat(b.w_year, Lpad(b.w_week, 2, 0)) period from jbd_period b WHERE b.start_time<=SYSDATE AND SYSDATE <=b.end_time";
		return this.findOneObjectBySQL(sql).get("period").toString();
	}
}
