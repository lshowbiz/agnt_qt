package com.joymain.ng.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpoBankBookPayListDao;
import com.joymain.ng.model.JpoBankBookPayList;

@Repository("jpoBankBookPayListDao")
public class JpoBankBookPayListDaoHibernate extends GenericDaoHibernate<JpoBankBookPayList, Long> implements JpoBankBookPayListDao {

	public JpoBankBookPayListDaoHibernate() {
		super(JpoBankBookPayList.class);
	}

	public void savePayList(JpoBankBookPayList paylist){
		getSession().save(paylist);
	}

}
