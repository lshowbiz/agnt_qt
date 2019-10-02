package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JmiDealList;
import com.joymain.ng.dao.JmiDealListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jmiDealListDao")
public class JmiDealListDaoHibernate extends GenericDaoHibernate<JmiDealList, Long> implements JmiDealListDao {

    public JmiDealListDaoHibernate() {
        super(JmiDealList.class);
    }
}
