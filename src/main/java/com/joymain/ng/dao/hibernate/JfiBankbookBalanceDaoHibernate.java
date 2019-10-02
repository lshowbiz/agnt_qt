
package com.joymain.ng.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JfiBankbookBalanceDao;
import com.joymain.ng.model.JfiBankbookBalance;

@Repository("jfiBankbookBalanceDao")
public class JfiBankbookBalanceDaoHibernate extends GenericDaoHibernate<JfiBankbookBalance,String> implements JfiBankbookBalanceDao {

    public JfiBankbookBalanceDaoHibernate() {
		super(JfiBankbookBalance.class);
	}
    
	@Override
	public JfiBankbookBalance getJfiBankbookBalanceForUpdate(String userCode) {
		// TODO Auto-generated method stub
		//JfiBankbookBalance jfiBankbookBalance = this.get(userCode);
		
		JfiBankbookBalance jfiBankbookBalance = (JfiBankbookBalance) this.getSession().get(JfiBankbookBalance.class, userCode, LockMode.UPGRADE);
		/*支付改造:还原到之前的锁
		JfiBankbookBalance jfiBankbookBalance = (JfiBankbookBalance) this.getSession().get(JfiBankbookBalance.class, userCode);
		*/
		return jfiBankbookBalance;
	}
	
	@Override
	public JfiBankbookBalance getJfiBankbookBalance(final String userCode) {
        JfiBankbookBalance jfiBankbookBalance = (JfiBankbookBalance) this.get(userCode);
        if (jfiBankbookBalance == null) {
            log.warn("uh oh, jfiBankbookBalance with userCode '" + userCode + "' not found...");
            throw new ObjectRetrievalFailureException(JfiBankbookBalance.class, userCode);
        }

        return jfiBankbookBalance;
    }
	
	public void saveJfiBankbookBalance(final JfiBankbookBalance jfiBankbookBalance) {
	       this.getSession().saveOrUpdate(jfiBankbookBalance);
	}
}
