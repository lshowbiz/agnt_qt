package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.dao.JalDistrictDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalDistrictDao")
public class JalDistrictDaoJpa extends GenericDaoHibernate<JalDistrict, Long> implements JalDistrictDao {

    public JalDistrictDaoJpa() {
        super(JalDistrict.class);
    }

	public List getAlDistrictByCityId(Long cityId) {
		return this.getSession().createQuery("select a from JalDistrict a where jalCity.cityId="+cityId).list();
	}
}
