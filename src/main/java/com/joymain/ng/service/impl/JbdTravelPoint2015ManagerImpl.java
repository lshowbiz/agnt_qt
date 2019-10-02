package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdTravelPoint2015Dao;
import com.joymain.ng.model.JbdTravelPoint2015;
import com.joymain.ng.service.JbdTravelPoint2015Manager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jbdTravelPoint2015Manager")
@WebService(serviceName = "JbdTravelPoint2015Service", endpointInterface = "com.joymain.ng.service.JbdTravelPoint2015Manager")
public class JbdTravelPoint2015ManagerImpl extends GenericManagerImpl<JbdTravelPoint2015, String> implements JbdTravelPoint2015Manager {
    JbdTravelPoint2015Dao jbdTravelPoint2015Dao;

    @Autowired
    public JbdTravelPoint2015ManagerImpl(JbdTravelPoint2015Dao jbdTravelPoint2015Dao) {
        super(jbdTravelPoint2015Dao);
        this.jbdTravelPoint2015Dao = jbdTravelPoint2015Dao;
    }
	
	public Pager<JbdTravelPoint2015> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdTravelPoint2015Dao.getPager(JbdTravelPoint2015.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}