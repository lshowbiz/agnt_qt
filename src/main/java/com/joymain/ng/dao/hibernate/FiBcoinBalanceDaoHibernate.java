
package com.joymain.ng.dao.hibernate;

import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiBcoinBalanceDao;
import com.joymain.ng.model.FiBcoinBalance;

@Repository("fiBcoinBalanceDao")
public class FiBcoinBalanceDaoHibernate extends GenericDaoHibernate<FiBcoinBalance, String> implements FiBcoinBalanceDao {

    public FiBcoinBalanceDaoHibernate() {
		super(FiBcoinBalance.class);
		// TODO Auto-generated constructor stub
	}
    
    public FiBcoinBalance getFiBcoinBalanceForUpdate(final String userCode){
    	FiBcoinBalance fiBcoinBalance = (FiBcoinBalance)this.getSession().get(FiBcoinBalance.class, userCode, LockMode.UPGRADE);

        return fiBcoinBalance;
    }

	@Override
	public void saveFiBcoinBalance(FiBcoinBalance fiBcoinBalance) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(fiBcoinBalance);
	}
}
