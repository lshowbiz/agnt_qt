package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmConfigSpecDetailedDao;
import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.model.JpoMemberOrderList;

@Repository("jpmConfigSpecDetailedDao")
public class JpmConfigSpecDetailedDaoHibernate extends GenericDaoHibernate<JpmConfigSpecDetailed, Long> implements JpmConfigSpecDetailedDao {

    public JpmConfigSpecDetailedDaoHibernate() {
        super(JpmConfigSpecDetailed.class);
    }

    @Override
    public void insertJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed)
    {
        this.getSession().saveOrUpdate(jpmConfigSpecDetailed);
    }

    @Override
    public void modifyJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed)
    {
        this.getSession().update(jpmConfigSpecDetailed);
    }

    @Override
    public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        StringBuffer hql = new StringBuffer("from JpmConfigSpecDetailed j where j.specNo = "+ specNo);
        return (JpmConfigSpecDetailed)this.getObjectByHqlQuery(hql.toString());
    }

    @Override
    public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo)
    {
        StringBuffer hql = new StringBuffer("select sum(j.complateWeight) from JpmConfigSpecDetailed j where j.jpmMemberConfig.configNo = " + configNo);
        return (Long)getSession().createQuery(hql.toString()).uniqueResult();
    }

    @Override
    public void delJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        StringBuffer sql = new StringBuffer("delete from jpm_config_detailed where spec_no = " + specNo);
        this.getJdbcTemplate().execute(sql.toString());
        sql = new StringBuffer("delete from jpm_config_spec_detailed where spec_no = " +specNo);
        this.getJdbcTemplate().execute(sql.toString());
    }

    @Override
    public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo)
    {
        StringBuffer hql = new StringBuffer("from  JpmConfigSpecDetailed j where j.jpmMemberConfig.configNo = " + configNo);
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public Long getPriceByConfigNo(String configNo)
    {
        StringBuffer hql = new StringBuffer("select sum(j.price) from JpmConfigSpecDetailed j where j.jpmMemberConfig.configNo = " + configNo);
        return (Long)getSession().createQuery(hql.toString()).uniqueResult();
    }
}
