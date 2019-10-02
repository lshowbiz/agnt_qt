package com.joymain.ng.dao.jpa;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JfiAmountDetailsDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JfiAmountDetails;

@Repository("jfiAmountDetailsDaoJpa")
public class JfiAmountDetailsDaoJpa extends GenericDaoHibernate<JfiAmountDetails, Long> implements JfiAmountDetailsDao {

    public JfiAmountDetailsDaoJpa() {
        super(JfiAmountDetails.class);
    }
    
    /**
	 * 1.获得商户号支付明细对象
	 * @author gw 2013-08-14
	 * @param id
	 * @return
	 */
    public JfiAmountDetails getJfiAmountDetails(String id){
		String hql = " from JfiAmountDetails where id = '"+id+"'";
		return (JfiAmountDetails) this.getObjectByHqlQuery(hql);
	}
    
    /**
	 * 2.保存商户号支付明细对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
    public void saveJfiAmountDetails(JfiAmountDetails jfiAmountDetails){
    	this.save(jfiAmountDetails);
	}
}
