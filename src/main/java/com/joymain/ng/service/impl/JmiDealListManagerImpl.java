package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiDealListDao;
import com.joymain.ng.model.JmiDealList;
import com.joymain.ng.service.JmiDealListManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jmiDealListManager")
@WebService(serviceName = "JmiDealListService", endpointInterface = "com.joymain.ng.service.JmiDealListManager")
public class JmiDealListManagerImpl extends GenericManagerImpl<JmiDealList, Long> implements JmiDealListManager {
    JmiDealListDao jmiDealListDao;

    @Autowired
    public JmiDealListManagerImpl(JmiDealListDao jmiDealListDao) {
        super(jmiDealListDao);
        this.jmiDealListDao = jmiDealListDao;
    }
	
	public Pager<JmiDealList> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiDealListDao.getPager(JmiDealList.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}