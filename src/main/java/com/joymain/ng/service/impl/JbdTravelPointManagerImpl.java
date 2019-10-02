package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdTravelPointDao;
import com.joymain.ng.model.JbdTravelPoint;
import com.joymain.ng.service.JbdTravelPointManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jbdTravelPointManager")
@WebService(serviceName = "JbdTravelPointService", endpointInterface = "com.joymain.ng.service.JbdTravelPointManager")
public class JbdTravelPointManagerImpl extends GenericManagerImpl<JbdTravelPoint, String> implements JbdTravelPointManager {
    JbdTravelPointDao jbdTravelPointDao;

    @Autowired
    public JbdTravelPointManagerImpl(JbdTravelPointDao jbdTravelPointDao) {
        super(jbdTravelPointDao);
        this.jbdTravelPointDao = jbdTravelPointDao;
    }
	
	public Pager<JbdTravelPoint> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdTravelPointDao.getPager(JbdTravelPoint.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}