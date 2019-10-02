package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.FiCoinLog;
import com.joymain.ng.dao.FiCoinLogDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiCoinLogDao")
public class FiCoinLogDaoHibernate extends GenericDaoHibernate<FiCoinLog, Long> implements FiCoinLogDao {

    public FiCoinLogDaoHibernate() {
        super(FiCoinLog.class);
    }
    
    public List getFiCoinLogs(final FiCoinLog fiCoinLog) {
        
    	return this.getFiCoinLogs(fiCoinLog);
    }
}
