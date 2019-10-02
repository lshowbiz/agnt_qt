package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JpmSalePromoter;
import com.joymain.ng.dao.JpmSalePromoterDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("jpmSalePromoterDao")
public class JpmSalePromoterDaoHibernate extends GenericDaoHibernate<JpmSalePromoter, Long> implements JpmSalePromoterDao {

    public JpmSalePromoterDaoHibernate() {
        super(JpmSalePromoter.class);
    }

	public List<JpmSalePromoter> getSaleByDate(String stime, String isActiva) {
		String hql = "from JpmSalePromoter t where t.startime <= to_date(?,'yyyy-MM-dd hh24:mi:ss') " +
				"and t.endtime >=to_date(?,'yyyy-MM-dd hh24:mi:ss') and isactiva=? order by t.ordertype";
		
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, stime);
		query.setParameter(1, stime);
		query.setParameter(2, isActiva);
		
		return query.list();
	}
}
