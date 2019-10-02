package com.joymain.ng.dao.hibernate;


import com.joymain.ng.model.InwIntegration;
import com.joymain.ng.dao.InwIntegrationDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("inwIntegrationDao")
public class InwIntegrationDaoHibernate extends GenericDaoHibernate<InwIntegration, Long> implements InwIntegrationDao {

    public InwIntegrationDaoHibernate() {
        super(InwIntegration.class);
    }

    /**
	 * 在扣除积分之前,首先进行放重复提交的校验
	 * @author 2014-06-10
	 * @param uniqueCode 唯一码
	 * @return boolean
	 */
	public boolean getCheckExist(String uniqueCode) {
		String hql = " from InwIntegration inwIntegration where other='"+uniqueCode+"'";
		InwIntegration inwIntegration = (InwIntegration) this.getObjectByHqlQuery(hql);
		if(null!=inwIntegration){
			 return false;
		}else{
		     return true;
		}
	}
	
}
