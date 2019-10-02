package com.joymain.ng.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmConfigDetailedDao;
import com.joymain.ng.model.JpmConfigDetailed;
import com.joymain.ng.model.JpmMemberConfig;

@Repository("jpmConfigDetailedDao")
public class JpmConfigDetailedDaoHibernate extends GenericDaoHibernate<JpmConfigDetailed, Long>
    implements JpmConfigDetailedDao
{
    
    public JpmConfigDetailedDaoHibernate()
    {
        super(JpmConfigDetailed.class);
    }
    
    @Override
    public Integer addJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Integer delJpmConfigDetailed(Long detailedId)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo)
    {
        StringBuffer hql =
            new StringBuffer("from JpmConfigDetailed j where j.specNo = " + Long.parseLong(specNo));
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }
    
    @Override
    public void saveJpmConfigDetailedList(Set<JpmConfigDetailed> jpmConfigDetailedList)
    {
        for (JpmConfigDetailed jpmConfigDetailed : jpmConfigDetailedList)
        {
            this.getSession().saveOrUpdate(jpmConfigDetailed);
        }
        
    }
    
    @Override
    public void delJpmConfigDetailedBySpecNo(Long specNo)
    {
        String sql = "delete from jpm_config_detailed j where j.spec_no =" + specNo;
        this.jdbcTemplate.execute(sql);
    }

    @Override
    public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo)
    {
        StringBuffer hql = new StringBuffer("from JpmConfigDetailed where isMainMaterial = '1' and specNo = "+specNo );
        return (JpmConfigDetailed)this.getObjectByHqlQuery(hql.toString());
    }
    
}
