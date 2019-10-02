
package com.joymain.ng.dao.hibernate;

import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiProductPointBalanceDao;
import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.model.JfiBankbookBalance;

@Repository("fiProductPointBalanceDao")
public class FiProductPointBalanceDaoHibernate extends GenericDaoHibernate<FiProductPointBalance,Long> implements FiProductPointBalanceDao {

	public FiProductPointBalanceDaoHibernate() {
        super(FiProductPointBalance.class);
    }
    
    public FiProductPointBalance getFiProductPointBalance(final String userCode,final String productPointType) {
    	FiProductPointBalance fiProductPointBalance = (FiProductPointBalance)this.getObjectByHqlQuery("from FiProductPointBalance where userCode = '" +userCode+ "' and productPointType='"+productPointType+"'");
        if (fiProductPointBalance == null) {
            log.warn("uh oh, FiProductPointBalance not found...");
        }
        return fiProductPointBalance;
    }

    /**
     * 获取银行记录
     * @param UserCode
     * @param electronicType
     * @return
     */
    public FiProductPointBalance getFiProductPoinBalanceByUserCodeAndBankbookType(final String userCode, final String productPointType){
    	return (FiProductPointBalance)this.getObjectByHqlQuery("from FiProductPointBalance where userCode='" + userCode + "' and productPointType='" + productPointType + "'");
    }

	@Override
	public void saveFiProductPointBalance(FiProductPointBalance fiProductPointBalance) {
		this.getSession().saveOrUpdate(fiProductPointBalance);
	}
	
	@Override
	public FiProductPointBalance getFiProductPointBalanceForUpdate(String userCode) {
		FiProductPointBalance fiProductPointBalance = (FiProductPointBalance)this.getSession().get(FiProductPointBalance.class, userCode, LockMode.UPGRADE);
        return fiProductPointBalance;
	}
}
