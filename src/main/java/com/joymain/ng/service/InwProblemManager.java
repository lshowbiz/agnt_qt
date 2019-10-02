package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.InwProblem;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface InwProblemManager extends GenericManager<InwProblem, Long> {
    
	public Pager<InwProblem> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	/**
	 * 创新共赢的共赢帮助的详细查询
	 * @author gw 2013-08-30
	 * @param species
	 * @return  List
	 */ 
	public List getInwProblemDetail(String species);
}