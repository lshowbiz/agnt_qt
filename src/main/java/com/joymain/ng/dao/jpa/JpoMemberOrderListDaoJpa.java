package com.joymain.ng.dao.jpa;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JpoMemberOrderListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JpoMemberOrderList;

@Repository("jpoMemberOrderListDao")
public class JpoMemberOrderListDaoJpa extends GenericDaoHibernate<JpoMemberOrderList, Long> implements JpoMemberOrderListDao {

    public JpoMemberOrderListDaoJpa() {
        super(JpoMemberOrderList.class);
    }

    @Override
    public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String, Long> map)
    {
        StringBuffer hql = new StringBuffer("from JpoMemberOrderList j where j.molId = "+map.get("molId"));
        return (JpoMemberOrderList)this.getObjectByHqlQuery(hql.toString());
    }

	@Override
	public Long getProSumByProNo(String proNo) {
		
		String hql ="";
		
		if(proNo.equalsIgnoreCase(Constants.PRONO)){
			hql = "select sum(l.qty) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.status=2 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.productNo=?";
		} else if(proNo.equalsIgnoreCase(Constants.PRONO1)){
			hql = "select sum(l.qty*3) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.status=2 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.productNo=?";
		} else if(proNo.equalsIgnoreCase(Constants.PRONO2)){
			hql = "select sum(l.qty*5) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.status=2 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.productNo=?";
		} else {
			hql = "select sum(l.qty) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.status=2 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.productNo=?";
		}
		
		Query query = getSession().createQuery(hql);
		query.setParameter(0, proNo);
		//query.setParameter(1, Constants.PRONO1);
		//query.setParameter(2, Constants.PRONO2);
		List list = query.list();
		if(list !=null && list.size()>0){
			return (Long)list.get(0);
		} else {
			return null;
		}
	}

public Long getProSumByProNo(String proNo,String statetime,String endtime) {
		
		String hql = "select sum(l.qty) from JpoMemberOrderList l " +
					"where l.jpoMemberOrder.isPay=1 " +
					"and l.jpmProductSaleTeamType.jpmProductSaleNew.productNo= '" + proNo +"' " ;
		if (!StringUtils.isEmpty(statetime)) {
			hql += "and l.jpoMemberOrder.submitTime>=to_date('"+statetime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (!StringUtils.isEmpty(endtime)) {
			hql += "and l.jpoMemberOrder.submitTime<=to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		
		Query query = getSession().createQuery(hql);
		
		List list = query.list();
		if(list !=null && list.size()>0){
			return (Long)list.get(0);
		} else {
			return null;
		}
	}
}
