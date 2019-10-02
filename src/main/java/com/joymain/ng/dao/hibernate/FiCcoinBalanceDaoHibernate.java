package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiCcoinBalance;
import com.joymain.ng.dao.FiCcoinBalanceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiCcoinBalanceDao")
public class FiCcoinBalanceDaoHibernate extends GenericDaoHibernate<FiCcoinBalance, String> implements FiCcoinBalanceDao {

    public FiCcoinBalanceDaoHibernate() {
        super(FiCcoinBalance.class);
    }
}
