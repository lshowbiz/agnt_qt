package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JbdTravelPoint;
import com.joymain.ng.dao.JbdTravelPointDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdTravelPointDao")
public class JbdTravelPointDaoJpa extends GenericDaoHibernate<JbdTravelPoint, String> implements JbdTravelPointDao {

    public JbdTravelPointDaoJpa() {
        super(JbdTravelPoint.class);
    }
}
