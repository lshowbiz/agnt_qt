package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.YDOrderDao;
import com.joymain.ng.model.YDOrder;
import com.joymain.ng.util.GroupPage;

@Repository("yDOrderDao")
public class YDOrderDaoHibernage extends GenericDaoHibernate<YDOrder, Long> implements YDOrderDao{

	public YDOrderDaoHibernage() {
		super(YDOrder.class);
	}

	@Override
	public List<YDOrder> getYDOrder(String userNo,String orderNo,GroupPage page) {
		String str = "select count(*) from YDOrder t "
				+ "where t.userCode='"+userNo+"' ";
		String hql = "from YDOrder t where t.userCode='"+userNo+"'";
				
		if(StringUtils.isNotBlank(orderNo)){
			str +=  " and t.orderNo='"+orderNo+"' ";
			hql += " and t.orderNo='"+orderNo+"' ";
		}
		
		return this.findObjectsByHQL(str, hql, page);
	}

	@Override
	public List<YDOrder> getYDOrder(String userNo,int pageNum,int pageSize) {
		String str = "from YDOrder t where t.userCode=?";
		Query query = getSession().createQuery(str);
		query.setString(0, userNo);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

}
