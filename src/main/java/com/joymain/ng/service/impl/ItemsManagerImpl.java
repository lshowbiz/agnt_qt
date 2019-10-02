package com.joymain.ng.service.impl;

import com.joymain.ng.dao.ItemsDao;
import com.joymain.ng.model.Items;
import com.joymain.ng.service.ItemsManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("itemsManager")
@WebService(serviceName = "ItemsService", endpointInterface = "com.joymain.ng.service.ItemsManager")
public class ItemsManagerImpl extends GenericManagerImpl<Items, Long> implements ItemsManager {
    ItemsDao itemsDao;

    @Autowired
    public ItemsManagerImpl(ItemsDao itemsDao) {
        super(itemsDao);
        this.itemsDao = itemsDao;
    }
	
	public Pager<Items> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return itemsDao.getPager(Items.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}