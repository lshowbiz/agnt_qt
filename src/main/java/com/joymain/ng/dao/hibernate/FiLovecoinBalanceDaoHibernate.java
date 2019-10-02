package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiLovecoinBalance;
import com.joymain.ng.dao.FiLovecoinBalanceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

@Repository("fiLovecoinBalanceDao")
public class FiLovecoinBalanceDaoHibernate extends GenericDaoHibernate<FiLovecoinBalance, String> implements FiLovecoinBalanceDao {

    public FiLovecoinBalanceDaoHibernate() {
        super(FiLovecoinBalance.class);
    }
    
    public FiLovecoinBalance getFiLovecoinBalanceForUpdate(final String userCode){
    	FiLovecoinBalance fiLovecoinBalance = (FiLovecoinBalance) getSession().get(FiLovecoinBalance.class, userCode, LockMode.UPGRADE);

        return fiLovecoinBalance;
    }
}
