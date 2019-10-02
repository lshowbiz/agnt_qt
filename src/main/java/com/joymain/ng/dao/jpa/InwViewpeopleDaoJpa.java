package com.joymain.ng.dao.jpa;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.InwViewpeopleDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.InwViewpeople;

@Repository("inwViewpeopleDao")
public class InwViewpeopleDaoJpa extends GenericDaoHibernate<InwViewpeople, Long> implements InwViewpeopleDao{

	public InwViewpeopleDaoJpa() {
		super(InwViewpeople.class);
	}

}
