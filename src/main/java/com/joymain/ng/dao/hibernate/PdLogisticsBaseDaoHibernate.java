package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.PdLogisticsBase;
import com.joymain.ng.dao.PdLogisticsBaseDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("pdLogisticsBaseDao")
public class PdLogisticsBaseDaoHibernate extends GenericDaoHibernate<PdLogisticsBase, Long> implements PdLogisticsBaseDao {

    public PdLogisticsBaseDaoHibernate() {
        super(PdLogisticsBase.class);
    }
}
