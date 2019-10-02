package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JalFileLibarary;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JalFileLibararyManager extends GenericManager<JalFileLibarary, Long> {
    
	public Pager<JalFileLibarary> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List getFileSearchType();
	
	public List<JalFileLibarary> getJalFileLibararyListByConditions(String typeId);
}