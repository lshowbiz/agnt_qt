package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiGetbillaccountLog;
import com.joymain.ng.dao.FiGetbillaccountLogDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiGetbillaccountLogDao")
public class FiGetbillaccountLogDaoHibernate extends GenericDaoHibernate<FiGetbillaccountLog, Long> implements FiGetbillaccountLogDao {

    public FiGetbillaccountLogDaoHibernate() {
        super(FiGetbillaccountLog.class);
    }
}
