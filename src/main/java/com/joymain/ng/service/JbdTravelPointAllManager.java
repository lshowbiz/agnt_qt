package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JbdTravelPointAll;
import com.joymain.ng.model.JbdTravelPointAllId;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JbdTravelPointAllManager extends GenericManager<JbdTravelPointAll, JbdTravelPointAllId> {
    
	public Pager<JbdTravelPointAll> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List getJbdTravelPointAlls(String userCode);
}