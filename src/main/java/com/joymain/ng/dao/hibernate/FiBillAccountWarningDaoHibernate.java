package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiBillAccountWarning;
import com.joymain.ng.dao.FiBillAccountWarningDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiBillAccountWarningDao")
public class FiBillAccountWarningDaoHibernate extends GenericDaoHibernate<FiBillAccountWarning, String> implements FiBillAccountWarningDao {

    public FiBillAccountWarningDaoHibernate() {
        super(FiBillAccountWarning.class);
    }
}
