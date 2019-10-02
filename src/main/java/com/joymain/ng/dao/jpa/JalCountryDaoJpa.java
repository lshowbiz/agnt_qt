package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;

import com.joymain.ng.model.JalCountry;
import com.joymain.ng.dao.JalCountryDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalCountryDao")
public class JalCountryDaoJpa extends GenericDaoHibernate<JalCountry, Long> implements JalCountryDao {

    public JalCountryDaoJpa() {
        super(JalCountry.class);
    }

	@Override
	public List getAlCountrysByCompany(String companyCode) {
		// TODO Auto-generated method stub
		
		Query q = getSession().createQuery("from JalCountry where companyCode='"+companyCode+"' order by countryCode");
        return q.list();
	}
}
