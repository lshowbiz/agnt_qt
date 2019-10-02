package com.joymain.ng.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FoundationOrderDao;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.util.StringUtil;

@Repository("foundationOrderDao")
public class FoundationOrderDaoHibernate extends GenericDaoHibernate<FoundationOrder, Long> implements FoundationOrderDao {

    public FoundationOrderDaoHibernate() {
        super(FoundationOrder.class);
    }

	@Override
	public List<FoundationOrder> getFoundationOrdersByUserCode(String userCode,
			String startTime, String endTime) {
		
		/*String sql="from FoundationOrder foundationOrder where foundationOrder.userCode='"+userCode+"'";
		
		if(!StringUtil.isEmpty(startTime)){
			sql+=" and foundationOrder.createTime>=to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(endTime)){
			sql+=" and foundationOrder.createTime<=to_date('" + endTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by foundationOrder.createTime desc";
	
		return this.getSession().createQuery(sql).list();*/
		//Modify By WuCF 20140701 绑定变量
		StringBuffer hql = new StringBuffer("from FoundationOrder foundationOrder where foundationOrder.userCode = :userCode ");

		if(!StringUtil.isEmpty(startTime)){
			hql.append(" and foundationOrder.createTime>=to_date(:startTime,'yyyy-mm-dd hh24:mi:ss')");
		}
		if(!StringUtil.isEmpty(endTime)){
			hql.append(" and foundationOrder.createTime<=to_date(:endTime,'yyyy-mm-dd hh24:mi:ss')");
		}
		hql.append(" order by foundationOrder.createTime desc");
		//查询语句，准备绑定变量
		Query query = getSession().createQuery(hql.toString());
		StringUtil.dealSetParameter(query,"userCode",userCode);
		if(!StringUtils.isEmpty(startTime) && !"null".equals(startTime)){
			StringUtil.dealSetParameter(query,"startTime",startTime+" 00:00:00");
		}
		if(!StringUtils.isEmpty(endTime) && !"null".equals(endTime)){
			StringUtil.dealSetParameter(query,"endTime",endTime+" 23:59:59");
		}
		return query.list();
	}

	
	@Override
	public List getOrdersByItemTypeAndTime(String userCode) {
		
		/*String sql = "from FoundationOrder foundationOrder where  userCode='"+userCode+"'";
    	
    	sql += " and foundationItem.fiId=(select fiId from FoundationItem where type='1') ";//项目类型为爱心365
    	
    	//往前推一年的时间
    	Calendar cal=Calendar.getInstance();//使用日历类
    	int year=cal.get(Calendar.YEAR);//得到年
    	int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
    	int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
    	year--;
    	
    	String lastYearToday=year+"-"+month+"-"+day;
    	
    	sql += " and createTime >= to_date ('" + lastYearToday + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
    	
    	return this.getSession().createQuery(sql).list();*/
		//Modify By WuCF 20140701 绑定变量
		StringBuffer hql = new StringBuffer("from FoundationOrder foundationOrder where  userCode= :userCode ");
		hql.append(" and foundationItem.fiId=(select fiId from FoundationItem where type='1') ");//项目类型为爱心365

		//往前推一年的时间
		Calendar cal=Calendar.getInstance();//使用日历类
		int year=cal.get(Calendar.YEAR);//得到年
		int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
		int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		year--;

		String lastYearToday=year+"-"+month+"-"+day;

		hql.append(" and createTime >= to_date (:lastYearToday,'yyyy-mm-dd hh24:mi:ss')");

		//查询语句，准备绑定变量
		Query query = getSession().createQuery(hql.toString());
		StringUtil.dealSetParameter(query,"userCode",userCode);
		StringUtil.dealSetParameter(query,"lastYearToday",lastYearToday+" 00:00:00");
		return query.list();
	}
	
	/**
	 * 创建订单，返回订单号
	 * @param foundationOrder
	 * @return
	 */
	@Override
	public Long saveFoundationOrder(final FoundationOrder foundationOrder) {
        return (Long)this.getSession().save(foundationOrder);
    }
}
