package com.joymain.ng.service.impl;

import java.util.Collection;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JmiValidLevelListDao;
import com.joymain.ng.model.JmiValidLevelList;
import com.joymain.ng.service.JmiValidLevelListManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jmiValidLevelListManager")
@WebService(serviceName = "JmiValidLevelListService", endpointInterface = "com.joymain.ng.service.JmiValidLevelListManager")
public class JmiValidLevelListManagerImpl extends GenericManagerImpl<JmiValidLevelList, Long> implements JmiValidLevelListManager {
    JmiValidLevelListDao jmiValidLevelListDao;

    @Autowired
    public JmiValidLevelListManagerImpl(JmiValidLevelListDao jmiValidLevelListDao) {
        super(jmiValidLevelListDao);
        this.jmiValidLevelListDao = jmiValidLevelListDao;
    }
	
	public Pager<JmiValidLevelList> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiValidLevelListDao.getPager(JmiValidLevelList.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public JmiValidLevelList getValidLevel(String bdPeriod, String userCode){
		return jmiValidLevelListDao.getValidLevel(bdPeriod,userCode);
	}
}