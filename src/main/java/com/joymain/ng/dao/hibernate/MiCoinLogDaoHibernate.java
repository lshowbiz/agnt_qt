package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.MiCoinLog;
import com.joymain.ng.dao.MiCoinLogDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("miCoinLogDao")
public class MiCoinLogDaoHibernate extends GenericDaoHibernate<MiCoinLog, Long> implements MiCoinLogDao {

    public MiCoinLogDaoHibernate() {
        super(MiCoinLog.class);
    }

	public void saveMiCoinLog(MiCoinLog miCoinLog) {
		this.getSession().save(miCoinLog);
		this.getSession().flush();
		
	}
}
