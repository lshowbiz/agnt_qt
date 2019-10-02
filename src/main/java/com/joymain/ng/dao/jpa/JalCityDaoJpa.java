package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JalCity;
import com.joymain.ng.dao.JalCityDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalCityDao")
public class JalCityDaoJpa extends GenericDaoHibernate<JalCity, Long> implements JalCityDao {

    public JalCityDaoJpa() {
        super(JalCity.class);
    }
    
    public List getAlCityByProvinceId(Long provinceId){
    	return this.getSession().createQuery("select a from JalCity a where jalStateProvince.stateProvinceId="+provinceId).list();
    }
}
