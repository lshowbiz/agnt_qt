package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.AmAnnouncePermitDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.AmAnnouncePermit;


@Repository("amAnnouncePermitDao")
public class AmAnnouncePermitDaoJpa extends GenericDaoHibernate<AmAnnouncePermit, String> implements AmAnnouncePermitDao {
	
	public AmAnnouncePermitDaoJpa() {
		super(AmAnnouncePermit.class);
	}

	public List<AmAnnouncePermit> findAnnouncePermitInAnnounce(String aaNo,String permitClass) {
		String hql = "from AmAnnouncePermit ap where exists " +
				"(from AmAnnounce a where a.aaNo=ap.id.aaNo and a.aaNo=? " +
				"and ap.id.permitNo in (?))";
		
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, aaNo);
		query.setParameter(1, permitClass);
		List<AmAnnouncePermit> list = query.list();
		
		return list;
	}

}
