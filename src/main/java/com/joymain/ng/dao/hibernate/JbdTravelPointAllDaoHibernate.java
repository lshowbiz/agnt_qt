package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdTravelPointAllDao;
import com.joymain.ng.model.JbdTravelPointAll;
import com.joymain.ng.model.JbdTravelPointAllId;

@Repository("jbdTravelPointAllDao")
public class JbdTravelPointAllDaoHibernate extends GenericDaoHibernate<JbdTravelPointAll, JbdTravelPointAllId> implements JbdTravelPointAllDao {

    public JbdTravelPointAllDaoHibernate() {
        super(JbdTravelPointAll.class);
    }
    
    @Override
	public List getJbdTravelPointAlls(String userCode) {
		
		StringBuffer hql=new StringBuffer("from JbdTravelPointAll f where ");
		
		hql.append(" f.id.userCode='"+userCode+"'");
		hql.append(" and f.id.FYear>=2015 ");
		hql.append(" order by f.id.FYear ");
			
		return this.getSession().createQuery(hql.toString()).list();
	}
}
