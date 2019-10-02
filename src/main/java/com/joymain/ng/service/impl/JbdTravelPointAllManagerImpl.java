package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdTravelPointAllDao;
import com.joymain.ng.model.JbdTravelPointAll;
import com.joymain.ng.model.JbdTravelPointAllId;
import com.joymain.ng.service.JbdTravelPointAllManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
 
@Service("jbdTravelPointAllManager")
@WebService(serviceName = "JbdTravelPointAllService", endpointInterface = "com.joymain.ng.service.JbdTravelPointAllManager")
public class JbdTravelPointAllManagerImpl extends GenericManagerImpl<JbdTravelPointAll, JbdTravelPointAllId> implements JbdTravelPointAllManager {
    JbdTravelPointAllDao jbdTravelPointAllDao;

    @Autowired
    public JbdTravelPointAllManagerImpl(JbdTravelPointAllDao jbdTravelPointAllDao) {
        super(jbdTravelPointAllDao);
        this.jbdTravelPointAllDao = jbdTravelPointAllDao;
    }
	
	public Pager<JbdTravelPointAll> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdTravelPointAllDao.getPager(JbdTravelPointAll.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJbdTravelPointAlls(String userCode) {
		// TODO Auto-generated method stub
		return jbdTravelPointAllDao.getJbdTravelPointAlls(userCode);
	}
}