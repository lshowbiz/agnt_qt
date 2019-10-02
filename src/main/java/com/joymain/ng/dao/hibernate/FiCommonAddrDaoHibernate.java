package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.dao.FiCommonAddrDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("fiCommonAddrDao")
public class FiCommonAddrDaoHibernate extends GenericDaoHibernate<FiCommonAddr, String> implements FiCommonAddrDao {

    public FiCommonAddrDaoHibernate() {
        super(FiCommonAddr.class);
    }
}
