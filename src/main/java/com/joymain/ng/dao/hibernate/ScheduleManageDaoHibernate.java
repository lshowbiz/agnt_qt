package com.joymain.ng.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.ScheduleManageDao;
import com.joymain.ng.model.ScheduleManage;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.data.CommonRecord;

@Repository("scheduleManageDao")
public class ScheduleManageDaoHibernate extends GenericDaoHibernate<ScheduleManage, Long> implements ScheduleManageDao {

    public ScheduleManageDaoHibernate() {
        super(ScheduleManage.class);
    }
    public List getScheduleManagesbyCrm(CommonRecord crm) {
    	String hql = "from ScheduleManage scheduleManage where 1=1";
		String scheduleName = crm.getString("scheduleName", "");
		if (!StringUtils.isEmpty(scheduleName)) {
			hql += " and scheduleManage.scheduleName like '%" + scheduleName + "%'";
		}
		String eventType = crm.getString("eventType", "");
		if (!StringUtils.isEmpty(eventType)) {
			hql += " and scheduleManage.eventType = " + eventType;
		}
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and scheduleManage.status = " + status;
		}
		String priority = crm.getString("priority", "");
		if (!StringUtils.isEmpty(priority)) {
			hql += " and scheduleManage.priority = " + priority;
		}
		String loginUserNo = crm.getString("loginUserNo", "");
		if (!StringUtils.isEmpty(loginUserNo)) {
			hql += " and scheduleManage.loginUserNo = '" + loginUserNo + "'";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = crm.getString("startTime","");
		String startTime2 = crm.getString("startTime2","");
		if(StringUtils.isNotEmpty(startTime)){
			try {
				hql += " and scheduleManage.startTime >=to_date('"+ dateFormat.format(dateFormat.parse(startTime) ) +" 00:00:00','yyyy-mm-dd HH24:MI:SS')" ;
						
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(startTime2)){
			try {
				hql += " and scheduleManage.startTime <=to_date('"+ dateFormat.format(dateFormat.parse(startTime2) ) +" 23:59:59','yyyy-mm-dd HH24:MI:SS')" ;
						
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		/*if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by scheduleManage.scheduleName desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}*/
		return this.getSession().createQuery(hql).list();
    }
    
    
    /**
	 * 手机端：得到公共日程数据集合
	 * Add By WuCF 20140324
	 * @param startDate：起始日期
	 * @param endDate：截止日期
	 * @return
	 */
    @Override
	public List getMobilePublicSchedules(String startDate,String endDate) {
    	String hql = "from PublicSchedule publicSchedule where 1=1 ";
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			hql += " and (publicSchedule.startTime >=to_date('"+ startDate +"','yyyy-mm-dd') and publicSchedule.startTime <=to_date('"+endDate +"','yyyy-mm-dd'))  order by publicSchedule.startTime asc,publicSchedule.endTime asc" ;
		}
		return this.getSession().createQuery(hql).list();
	}
    
    /**
	 * 手机端：得到个人日程数据集合
	 * Add By WuCF 20140324
	 * @param userCode：会员编号
	 * @param startDate：起始日期
	 * @param endDate：截止日期
	 * @return
	 */
	@Override
	public List getMobileScheduleManages(String userCode, String startDate,
			String endDate) {
		String hql = "from ScheduleManage scheduleManage where 1=1 ";
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and scheduleManage.loginUserNo = '" + userCode + "'";
		}
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			hql += " and (scheduleManage.startTime >=to_date('"+ startDate +"','yyyy-mm-dd') and scheduleManage.startTime <=to_date('"+endDate +"','yyyy-mm-dd')) order by scheduleManage.startTime asc,scheduleManage.endTime asc" ;
		}
		return this.getSession().createQuery(hql).list();
	}
	
	/**
	 * 分页 
	 * @author:WuCF
	 * @date:2013-11-29
	 * @param page
	 * @param crm
	 * @return
	 */
    
    public List getScheduleManagesbyCrmPage(GroupPage page,CommonRecord crm) {
    	String totalHql = "select count(*) from ScheduleManage scheduleManage where 1=1";
		String scheduleName = crm.getString("scheduleName", "");
		if (!StringUtils.isEmpty(scheduleName)) {
			totalHql += " and scheduleManage.scheduleName like '%" + scheduleName + "%'";
		}
		String eventType = crm.getString("eventType", "");
		if (!StringUtils.isEmpty(eventType)) {
			totalHql += " and scheduleManage.eventType = " + eventType;
		}
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			totalHql += " and scheduleManage.status = " + status;
		}
		String priority = crm.getString("priority", "");
		if (!StringUtils.isEmpty(priority)) {
			totalHql += " and scheduleManage.priority = " + priority;
		}
		String loginUserNo = crm.getString("loginUserNo", "");
		if (!StringUtils.isEmpty(loginUserNo)) {
			totalHql += " and scheduleManage.loginUserNo = '" + loginUserNo + "'";
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = crm.getString("startTime","");
		String startTime2 = crm.getString("startTime2","");
		if(StringUtils.isNotEmpty(startTime)){
			try {
				totalHql += " and scheduleManage.startTime >=to_date('"+ dateFormat.format(dateFormat.parse(startTime) ) +" 00:00:00','yyyy-mm-dd HH24:MI:SS')" ;
						
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(startTime2)){
			try {
				totalHql += " and scheduleManage.startTime <=to_date('"+ dateFormat.format(dateFormat.parse(startTime2) ) +" 23:59:59','yyyy-mm-dd HH24:MI:SS')" ;
						
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
    	
    	String hql = "from ScheduleManage scheduleManage where 1=1";
//		String scheduleName = crm.getString("scheduleName", "");
		if (!StringUtils.isEmpty(scheduleName)) {
			hql += " and scheduleManage.scheduleName like '%" + scheduleName + "%'";
		}
//		String eventType = crm.getString("eventType", "");
		if (!StringUtils.isEmpty(eventType)) {
			hql += " and scheduleManage.eventType = " + eventType;
		}
//		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and scheduleManage.status = " + status;
		}
//		String priority = crm.getString("priority", "");
		if (!StringUtils.isEmpty(priority)) {
			hql += " and scheduleManage.priority = " + priority;
		}
//		String loginUserNo = crm.getString("loginUserNo", "");
		if (!StringUtils.isEmpty(loginUserNo)) {
			hql += " and scheduleManage.loginUserNo = '" + loginUserNo + "'";
		}
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String startTime = crm.getString("startTime","");
//		String startTime2 = crm.getString("startTime2","");
		if(StringUtils.isNotEmpty(startTime)){
			try {
				hql += " and scheduleManage.startTime >=to_date('"+ dateFormat.format(dateFormat.parse(startTime) ) +" 00:00:00','yyyy-mm-dd HH24:MI:SS')" ;
						
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(startTime2)){
			try {
				hql += " and scheduleManage.startTime <=to_date('"+ dateFormat.format(dateFormat.parse(startTime2) ) +" 23:59:59','yyyy-mm-dd HH24:MI:SS')" ;
						
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		/*if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by scheduleManage.scheduleName desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}*/
		return this.findObjectsByHQL(totalHql, hql, page);
    }
    
    public ScheduleManage saveSm(ScheduleManage scheduleManage) {
    	return this.save(scheduleManage);
    }
	@Override
	public List getScheduleManagesbyCrm(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List getScheduleByUserCode(String userCode,String today){  
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Query q = this.getSession().createQuery("from ScheduleManage s where s.loginUserNo = ? " +
				" and (s.startTime between  to_date(? ,'yyyy-mm-dd hh24:mi:ss') and to_date(? ,'yyyy-mm-dd hh24:mi:ss')" +
				" or s.endTime between to_date(? ,'yyyy-mm-dd hh24:mi:ss') and to_date(? ,'yyyy-mm-dd hh24:mi:ss'))");
		System.out.println(q);
		Date dath;
		try {
			dath = sdf.parse(today);
			cal.set(Calendar.YEAR, dath.getYear()+1900);
			cal.set(Calendar.MONTH, dath.getMonth());
			cal.set(Calendar.DATE, dath.getDate()+7);
			Date dath2 = cal.getTime();
	
			System.out.println(sdf.format(dath2));
			q.setParameter(0, userCode);
			q.setParameter(1, today);
			q.setParameter(2, sdf.format(dath2));
			q.setParameter(3, today);
			q.setParameter(4, sdf.format(dath2));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q.list();
		
		
	}
	
	/**
	 * Add By WuCF 20131209 
	 * 获得指定行的数据
	 * @param userCode
	 * @param today
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List getScheduleByUserCode(String userCode,String today,Integer startIndex,Integer endIndex){  
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//Modify By WuCF 2013-12-13 修改首页的只有未完成 status!=3
		Query q = this.getSession().createQuery("from ScheduleManage s where s.status!=3 and s.loginUserNo = ? " +
				" and (s.startTime between  to_date(? ,'yyyy-mm-dd hh24:mi:ss') and to_date(? ,'yyyy-mm-dd hh24:mi:ss')" +
				" or s.endTime between to_date(? ,'yyyy-mm-dd hh24:mi:ss') and to_date(? ,'yyyy-mm-dd hh24:mi:ss'))"+
				" and rownum between "+startIndex+" and "+endIndex);
		System.out.println(q);
		Date dath;
		try {
			dath = sdf.parse(today);
			cal.set(Calendar.YEAR, dath.getYear()+1900);
			cal.set(Calendar.MONTH, dath.getMonth());
			cal.set(Calendar.DATE, dath.getDate()+7);
			Date dath2 = cal.getTime();
	
			System.out.println(sdf.format(dath2));
			q.setParameter(0, userCode);
			q.setParameter(1, today);
			q.setParameter(2, sdf.format(dath2));
			q.setParameter(3, today);
			q.setParameter(4, sdf.format(dath2));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q.list();
		
		
	}
}
