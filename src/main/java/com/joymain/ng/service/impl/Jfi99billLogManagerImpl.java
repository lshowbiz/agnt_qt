package com.joymain.ng.service.impl;

import com.joymain.ng.dao.Jfi99billLogDao;
import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.service.Jfi99billLogManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("jfi99billLogManager")
@WebService(serviceName = "Jfi99billLogService", endpointInterface = "com.joymain.ng.service.Jfi99billLogManager")
public class Jfi99billLogManagerImpl extends GenericManagerImpl<Jfi99billLog, Long> implements Jfi99billLogManager {
    Jfi99billLogDao jfi99billLogDao;

    @Autowired
    public Jfi99billLogManagerImpl(Jfi99billLogDao jfi99billLogDao) {
        super(jfi99billLogDao);
        this.jfi99billLogDao = jfi99billLogDao;
    }
	
	public Pager<Jfi99billLog> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jfi99billLogDao.getPager(Jfi99billLog.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJfi99billLogs(Jfi99billLog jfi99billLog) {
		// TODO Auto-generated method stub
		return jfi99billLogDao.getJfi99billLogs(jfi99billLog);
	}

	@Override
	public List getSuccessJfi99billLogsByConditions(String dealId) {
		// TODO Auto-generated method stub
		return jfi99billLogDao.getSuccessJfi99billLogsByConditions(dealId);
	}
	
	
}