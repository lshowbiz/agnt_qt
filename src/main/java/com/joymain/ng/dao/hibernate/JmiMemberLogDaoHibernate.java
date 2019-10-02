package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JmiMemberLog;
import com.joymain.ng.dao.JmiMemberLogDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jmiMemberLogDao")
public class JmiMemberLogDaoHibernate extends GenericDaoHibernate<JmiMemberLog, Long> implements JmiMemberLogDao {

    public JmiMemberLogDaoHibernate() {
        super(JmiMemberLog.class);
    }
}
