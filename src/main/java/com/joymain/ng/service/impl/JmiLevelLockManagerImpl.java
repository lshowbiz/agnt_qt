package com.joymain.ng.service.impl;

import java.util.Collection;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JmiLevelLockDao;
import com.joymain.ng.model.JmiLevelLock;
import com.joymain.ng.service.JmiLevelLockManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jmiLevelLockManager")
@WebService(serviceName = "JmiLevelLockService", endpointInterface = "com.joymain.ng.service.JmiLevelLockManager")
public class JmiLevelLockManagerImpl extends GenericManagerImpl<JmiLevelLock, Long> implements JmiLevelLockManager {
    JmiLevelLockDao jmiLevelLockDao;

    @Autowired
    public JmiLevelLockManagerImpl(JmiLevelLockDao jmiLevelLockDao) {
        super(jmiLevelLockDao);
        this.jmiLevelLockDao = jmiLevelLockDao;
    }
	
	public Pager<JmiLevelLock> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiLevelLockDao.getPager(JmiLevelLock.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public JmiLevelLock getJmiLevelLock(String userCode){
		return jmiLevelLockDao.getJmiLevelLock(userCode);
	}
}