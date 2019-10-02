package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmSendConsignmentDao;
import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JpmSendConsignment;

@Repository("jpmSendConsignmentDao")
public class JpmSendConsignmentDaoHibernate extends GenericDaoHibernate<JpmSendConsignment, Long> implements JpmSendConsignmentDao {

    public JpmSendConsignmentDaoHibernate() {
        super(JpmSendConsignment.class);
    }

    @Override
    public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo)
    {
        StringBuffer hql = new StringBuffer("from JpmSendConsignment j where j.specNo = " + specNo);
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        StringBuffer sql = new StringBuffer("delete from jpm_send_consignment where CONSIGNMENT_NO = " + consignmentNo);
        this.getJdbcTemplate().execute(sql.toString());
    }

    @Override
    public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        StringBuffer hql = new StringBuffer("from JpmSendConsignment j where j.consignmentNo = " + consignmentNo);
        return (JpmSendConsignment)this.getObjectByHqlQuery(hql.toString());
    }
}
