package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiFundbookTemp;
import com.joymain.ng.dao.FiFundbookTempDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiFundbookTempDao")
public class FiFundbookTempDaoHibernate extends GenericDaoHibernate<FiFundbookTemp, Long> implements FiFundbookTempDao {

    public FiFundbookTempDaoHibernate() {
        super(FiFundbookTemp.class);
    }
}
