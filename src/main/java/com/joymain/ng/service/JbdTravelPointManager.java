package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JbdTravelPoint;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JbdTravelPointManager extends GenericManager<JbdTravelPoint, String> {
    
	public Pager<JbdTravelPoint> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
}