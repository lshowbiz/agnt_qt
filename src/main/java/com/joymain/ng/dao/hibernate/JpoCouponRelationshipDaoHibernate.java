package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JpoCouponRelationship;
import com.joymain.ng.dao.JpoCouponRelationshipDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jpoCouponRelationshipDao")
public class JpoCouponRelationshipDaoHibernate extends GenericDaoHibernate<JpoCouponRelationship, Long> implements JpoCouponRelationshipDao {

    public JpoCouponRelationshipDaoHibernate() {
        super(JpoCouponRelationship.class);
    }
}
