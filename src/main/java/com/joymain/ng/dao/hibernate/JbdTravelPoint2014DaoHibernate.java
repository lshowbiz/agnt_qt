package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JbdTravelPoint2014;
import com.joymain.ng.dao.JbdTravelPoint2014Dao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdTravelPoint2014Dao")
public class JbdTravelPoint2014DaoHibernate extends GenericDaoHibernate<JbdTravelPoint2014, String> implements JbdTravelPoint2014Dao {

    public JbdTravelPoint2014DaoHibernate() {
        super(JbdTravelPoint2014.class);
    }
}
