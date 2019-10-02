package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.dao.FiFundbookBalanceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

@Repository("fiFundbookBalanceDao")
public class FiFundbookBalanceDaoHibernate extends GenericDaoHibernate<FiFundbookBalance, Long> implements FiFundbookBalanceDao {

    public FiFundbookBalanceDaoHibernate() {
        super(FiFundbookBalance.class);
    }

	@Override
	public FiFundbookBalance getFiFundbookBalance(String userCode, String bankbookType) {
			
		FiFundbookBalance fiFundbookBalance = (FiFundbookBalance)this.getObjectByHqlQuery("from FiFundbookBalance where userCode = '" +userCode+ "' and bankbookType='"+bankbookType+"'");
        if (fiFundbookBalance == null) {
            return null;
        }
        return fiFundbookBalance;
	}
	
	/**
     * 获取银行记录
     * @param UserCode
     * @param FundbookType
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceByUserCodeAndFundbookType(final String userCode, final String bankbookType){
    	
    	return (FiFundbookBalance)this.getObjectByHqlQuery("from FiFundbookBalance where userCode='" + userCode + "' and bankbookType='" + bankbookType + "'");
    }
    
    /**
     * 锁定一行表记录
     * @param fbbId
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceForUpdate(final Long fbbId) {
    	
    	FiFundbookBalance fiFundbookBalance = (FiFundbookBalance) getSession().get(FiFundbookBalance.class, fbbId, LockMode.UPGRADE);

        return fiFundbookBalance;
    }
}
