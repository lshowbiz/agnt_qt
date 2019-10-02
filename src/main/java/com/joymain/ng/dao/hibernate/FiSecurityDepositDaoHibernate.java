package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiSecurityDeposit;
import com.joymain.ng.dao.FiSecurityDepositDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiSecurityDepositDao")
public class FiSecurityDepositDaoHibernate extends GenericDaoHibernate<FiSecurityDeposit, Long> implements FiSecurityDepositDao {

    public FiSecurityDepositDaoHibernate() {
        super(FiSecurityDeposit.class);
    }

	@Override
	public FiSecurityDeposit getFiSecurityDepositByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return (FiSecurityDeposit) this.getObjectByHqlQuery("from FiSecurityDeposit where userCode='"+userCode+"'");
	}
}
