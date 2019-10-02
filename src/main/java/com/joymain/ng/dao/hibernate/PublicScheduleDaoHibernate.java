package com.joymain.ng.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.joymain.ng.model.PublicSchedule;
import com.joymain.ng.dao.PublicScheduleDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("publicScheduleDao")
public class PublicScheduleDaoHibernate extends GenericDaoHibernate<PublicSchedule, Long> implements PublicScheduleDao {

    public PublicScheduleDaoHibernate() {
        super(PublicSchedule.class);
    }
    
    public List getScheduleByUserCode( String today){
    	
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	Query q=getSession().createQuery("from PublicSchedule p where  " +
    			" p.startTime between  to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')" +
    			" or " +
    			" p.endTime between  to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')");
    	try {
			Date dath = sdf.parse(today);
			int year = dath.getYear()+1900;
			int month = dath.getMonth()+1;
			int date = dath.getDate();
			if(month > 12){
				month = 1;
				year = year+1;
			}
			cal.set(year, month, date);
			Date dath2 = cal.getTime();
			
			q.setParameter(0, today);
	    	q.setParameter(1, sdf.format(dath2));
	    	q.setParameter(2, today);
	    	q.setParameter(3, sdf.format(dath2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return q.list();
    }
    
    /**
	 * Add By WuCF 20131209 
	 * 查询指定行数的数据
	 * @param today
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
    public List getScheduleByUserCode( String today,Integer startIndex,Integer endIndex){
    	
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	Query q=getSession().createQuery("from PublicSchedule p where  " +
    			" p.startTime between  to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')" +
    			" or " +
    			" p.endTime between  to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')"+
    			" and rownum between "+startIndex+" and "+endIndex);
    	try {
			Date dath = sdf.parse(today);
			int year = dath.getYear()+1900;
			int month = dath.getMonth()+1;
			int date = dath.getDate();
			if(month > 12){
				month = 1;
				year = year+1;
			}
			cal.set(year, month, date);
			Date dath2 = cal.getTime();
			
			q.setParameter(0, today);
	    	q.setParameter(1, sdf.format(dath2));
	    	q.setParameter(2, today);
	    	q.setParameter(3, sdf.format(dath2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return q.list();
    }
}
