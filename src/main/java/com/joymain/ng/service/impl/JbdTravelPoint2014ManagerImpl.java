package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdTravelPoint2014Dao;
import com.joymain.ng.model.JbdTravelPoint2014;
import com.joymain.ng.service.JbdTravelPoint2014Manager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jbdTravelPoint2014Manager")
@WebService(serviceName = "JbdTravelPoint2014Service", endpointInterface = "com.joymain.ng.service.JbdTravelPoint2014Manager")
public class JbdTravelPoint2014ManagerImpl extends GenericManagerImpl<JbdTravelPoint2014, String> implements JbdTravelPoint2014Manager {
    JbdTravelPoint2014Dao jbdTravelPoint2014Dao;

    @Autowired
    public JbdTravelPoint2014ManagerImpl(JbdTravelPoint2014Dao jbdTravelPoint2014Dao) {
        super(jbdTravelPoint2014Dao);
        this.jbdTravelPoint2014Dao = jbdTravelPoint2014Dao;
    }
	
	public Pager<JbdTravelPoint2014> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdTravelPoint2014Dao.getPager(JbdTravelPoint2014.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}