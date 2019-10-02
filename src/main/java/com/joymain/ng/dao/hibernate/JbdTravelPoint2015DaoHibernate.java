package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JbdTravelPoint2015;
import com.joymain.ng.dao.JbdTravelPoint2015Dao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdTravelPoint2015Dao")
public class JbdTravelPoint2015DaoHibernate extends GenericDaoHibernate<JbdTravelPoint2015, String> implements JbdTravelPoint2015Dao {

    public JbdTravelPoint2015DaoHibernate() {
        super(JbdTravelPoint2015.class);
    }
}
