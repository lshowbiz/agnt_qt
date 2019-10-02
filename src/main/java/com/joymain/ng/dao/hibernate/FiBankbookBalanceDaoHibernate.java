
package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiBankbookBalanceDao;
import com.joymain.ng.model.FiBankbookBalance;

@Repository("fiBankbookBalanceDao")
public class FiBankbookBalanceDaoHibernate extends GenericDaoHibernate<FiBankbookBalance,Long> implements FiBankbookBalanceDao {

	public FiBankbookBalanceDaoHibernate() {
        super(FiBankbookBalance.class);
    }
    
    public FiBankbookBalance getFiBankbookBalance(final String userCode,final String backbookType) {
        FiBankbookBalance fiBankbookBalance = (FiBankbookBalance)this.getObjectByHqlQuery("from FiBankbookBalance where userCode = '" +userCode+ "' and bankbookType='"+backbookType+"'");
        if (fiBankbookBalance == null) {
            log.warn("uh oh, fiBankbookBalance not found...");
        }
        return fiBankbookBalance;
    }

    /**
     * 获取银行记录
     * @param UserCode
     * @param bankbookType
     * @return
     */
    public FiBankbookBalance getFiBankbookBalanceByUserCodeAndBankbookType(final String userCode, final String bankbookType){
    	return (FiBankbookBalance)this.getObjectByHqlQuery("from FiBankbookBalance where userCode='" + userCode + "' and bankbookType='" + bankbookType + "'");
    }

	@Override
	public void saveFiBankbookBalance(FiBankbookBalance fiBankbookBalance) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(fiBankbookBalance);
	}
	
	public FiBankbookBalance getFiBankbookBalanceForUpdate(final Long fbbId) {
    	FiBankbookBalance fiBankbookBalance = (FiBankbookBalance) getSession().get(FiBankbookBalance.class, fbbId, LockMode.UPGRADE);

        return fiBankbookBalance;
    }
}
