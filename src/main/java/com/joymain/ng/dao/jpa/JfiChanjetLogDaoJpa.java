package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JfiChanjetLogDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JfiChanjetLog;

@Repository("jfiChanjetLogDao")
public class JfiChanjetLogDaoJpa extends GenericDaoHibernate<JfiChanjetLog, Long> implements JfiChanjetLogDao {

    public JfiChanjetLogDaoJpa() {
        super(JfiChanjetLog.class);
    }

    @Override
	public List getJfiChanjetLogsByDealId(String dealId) {

		String sql="select t from JfiChanjetLog t where t.detailId='"+ dealId +"' and t.inc='1'";
		
		Query q = this.getSession().createQuery(sql);
        return q.list();
	}
}
