package com.joymain.ng.dao.hibernate;

import java.text.DateFormat;
import java.util.Date;

import com.joymain.ng.model.FiBcoinPayconfig;
import com.joymain.ng.dao.FiBcoinPayconfigDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("fiBcoinPayconfigDao")
public class FiBcoinPayconfigDaoHibernate extends GenericDaoHibernate<FiBcoinPayconfig, Long> implements FiBcoinPayconfigDao {

    public FiBcoinPayconfigDaoHibernate() {
        super(FiBcoinPayconfig.class);
    }

	@Override
	public FiBcoinPayconfig getFiBcoinPayconfigByNowTime() {
		
		//创建格式化的当前时间
		Date d = new Date();
		DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowTime = format1.format(new Date());
		
		//查询当前时间是否在积分换购期间
		String hql = "from FiBcoinPayconfig fiBcoinPayconfig where";
		
		hql += " startTime<='" + nowTime + "'";
		hql += " and endTime>='" + nowTime + "'";
		
		return (FiBcoinPayconfig) this.getObjectByHqlQuery(hql);
	}
}
