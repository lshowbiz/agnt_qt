package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JfiDepositBalance;
import com.joymain.ng.dao.JfiDepositBalanceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jfiDepositBalanceDao")
public class JfiDepositBalanceDaoHibernate extends GenericDaoHibernate<JfiDepositBalance, Long> implements JfiDepositBalanceDao {

    public JfiDepositBalanceDaoHibernate() {
        super(JfiDepositBalance.class);
    }
}
