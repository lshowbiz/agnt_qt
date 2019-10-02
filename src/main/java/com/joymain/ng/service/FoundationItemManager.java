package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FoundationItem;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface FoundationItemManager extends GenericManager<FoundationItem, Long> {
    
	public Pager<FoundationItem> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public FoundationItem getFoundationItemByType(String type);
	
	public List<FoundationItem> getFoundationItemsByStatusIsEnable();
	
	public FoundationItem getFoundationItemByFiled1(String field); 
}