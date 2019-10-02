package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiMovieOrder;
import com.joymain.ng.dao.FiMovieOrderDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiMovieOrderDao")
public class FiMovieOrderDaoHibernate extends GenericDaoHibernate<FiMovieOrder, String> implements FiMovieOrderDao {

    public FiMovieOrderDaoHibernate() {
        super(FiMovieOrder.class);
    }
}
