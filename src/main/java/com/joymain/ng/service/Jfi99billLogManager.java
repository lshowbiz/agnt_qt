package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface Jfi99billLogManager extends GenericManager<Jfi99billLog, Long> {
    
	public Pager<Jfi99billLog> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List getJfi99billLogs(Jfi99billLog jfi99billLog);
	
	public List getSuccessJfi99billLogsByConditions(String dealId);
}