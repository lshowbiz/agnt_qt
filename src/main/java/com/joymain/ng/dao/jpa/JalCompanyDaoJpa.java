package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JalCompany;
import com.joymain.ng.dao.JalCompanyDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalCompanyDao")
public class JalCompanyDaoJpa extends GenericDaoHibernate<JalCompany, Long> implements JalCompanyDao {

    public JalCompanyDaoJpa() {
        super(JalCompany.class);
    }

	@Override
	public JalCompany getAlCompanyByCode(String companyCode) {
		// TODO Auto-generated method stub
		return (JalCompany) this.getObjectByHqlQuery("from AlCompany where companyCode='" + companyCode + "'");
	}
}
