package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.dao.JalStateProvinceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("jalStateProvinceDao")
public class JalStateProvinceDaoJpa extends GenericDaoHibernate<JalStateProvince, Long> implements JalStateProvinceDao {

    public JalStateProvinceDaoJpa() {
        super(JalStateProvince.class);
    }

	public List getJalStateProvinceByCountryCode(String countryCode) {
		return this.getSession().createQuery("select a from JalStateProvince a where jalCountry.countryCode='"+countryCode+"'").list();
	}

	@Override
	public JalStateProvince getJalStateProvinceByProvinceCode(
			String stateProvinceCode) {
		// TODO Auto-generated method stub
		return (JalStateProvince) this.getObjectByHqlQuery("select a from JalStateProvince a where stateProvinceCode='"+stateProvinceCode+"'");
	}
}
