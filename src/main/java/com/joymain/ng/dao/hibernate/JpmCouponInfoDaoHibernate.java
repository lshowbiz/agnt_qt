package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JpmCouponInfo;
import com.joymain.ng.dao.JpmCouponInfoDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jpmCouponInfoDao")
public class JpmCouponInfoDaoHibernate extends GenericDaoHibernate<JpmCouponInfo, Long> implements JpmCouponInfoDao {

    public JpmCouponInfoDaoHibernate() {
        super(JpmCouponInfo.class);
    }
}
