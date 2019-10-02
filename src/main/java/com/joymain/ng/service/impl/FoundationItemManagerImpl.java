package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FoundationItemDao;
import com.joymain.ng.model.FoundationItem;
import com.joymain.ng.service.FoundationItemManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("foundationItemManager")
@WebService(serviceName = "FoundationItemService", endpointInterface = "com.joymain.ng.service.FoundationItemManager")
public class FoundationItemManagerImpl extends GenericManagerImpl<FoundationItem, Long> implements FoundationItemManager {
    FoundationItemDao foundationItemDao;

    @Autowired
    public FoundationItemManagerImpl(FoundationItemDao foundationItemDao) {
        super(foundationItemDao);
        this.foundationItemDao = foundationItemDao;
    }
	
	public Pager<FoundationItem> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return foundationItemDao.getPager(FoundationItem.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public FoundationItem getFoundationItemByType(String type) {
		// TODO Auto-generated method stub
		return foundationItemDao.getFoundationItemByType(type);
	}

	@Override
	public List<FoundationItem> getFoundationItemsByStatusIsEnable() {
		// TODO Auto-generated method stub
		return foundationItemDao.getFoundationItemsByStatusIsEnable();
	}

	@Override
	public FoundationItem getFoundationItemByFiled1(String field) { 
		// TODO Auto-generated method stub
		return foundationItemDao.getFoundationItemByFiled1(field);
	}
}