package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiBlacklistDao;
import com.joymain.ng.model.JmiBlacklist;
import com.joymain.ng.service.JmiBlacklistManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jmiBlacklistManager")
@WebService(serviceName = "JmiBlacklistService", endpointInterface = "com.joymain.ng.service.JmiBlacklistManager")
public class JmiBlacklistManagerImpl extends GenericManagerImpl<JmiBlacklist, Long> implements JmiBlacklistManager {
    JmiBlacklistDao jmiBlacklistDao;

    @Autowired
    public JmiBlacklistManagerImpl(JmiBlacklistDao jmiBlacklistDao) {
        super(jmiBlacklistDao);
        this.jmiBlacklistDao = jmiBlacklistDao;
    }
	
	public Pager<JmiBlacklist> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiBlacklistDao.getPager(JmiBlacklist.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}