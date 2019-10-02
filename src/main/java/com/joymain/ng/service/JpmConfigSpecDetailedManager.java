package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpmConfigSpecDetailedManager extends GenericManager<JpmConfigSpecDetailed, Long> {
    
	public Pager<JpmConfigSpecDetailed> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo);
	
	public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo);
	
	public void delJpmConfigSpecDetailedBySpecNo(Long specNo);
	
	public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo);
	
	public Long getPriceByConfigNo(String configNo);
}