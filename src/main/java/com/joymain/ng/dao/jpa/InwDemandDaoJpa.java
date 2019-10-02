package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.InwDemand;
import com.joymain.ng.dao.InwDemandDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("inwDemandDao")
public class InwDemandDaoJpa extends GenericDaoHibernate<InwDemand, Long> implements InwDemandDao {

    public InwDemandDaoJpa() {
        super(InwDemand.class);
    }

    /**
     * 创新共赢的需求(合作共赢)的查询
     * @author gw  2013-08-13
     * @param other
     */
	public List getInwDemandList(String other) {
		/*String hql = "from InwDemand inwDemand where 1=1 and verify='Y' and other= :other ";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("other",other);
		return query.list();*/
		
		String sql = "select * from inw_Demand where 1=1 and verify='Y' and other= '"+other+"'";
		
		return this.jdbcTemplate.queryForList(sql);
	}

	/**
	 * 创新共赢的需求（合作共赢）的详细查询
	 * @author gw 2013-08-14
	 * @param id
	 * @return
	 */
	public InwDemand getInwDemandDetail(String id) {
		String hql = " from InwDemand where id = '"+id+"'";
		return (InwDemand) this.getObjectByHqlQuery(hql);
	}

	/**
	 * 创新共赢---通过ID获取需求表的需求名称
	 * @author gw 2013-11-08
	 * @param id
	 * @return string
	 */
	public String getInwDemandById(String id) {
		String hql = " from InwDemand where id = '"+id+"'";
		InwDemand inwDemand =  (InwDemand) this.getObjectByHqlQuery(hql);
		return inwDemand.getName();
	}
}
