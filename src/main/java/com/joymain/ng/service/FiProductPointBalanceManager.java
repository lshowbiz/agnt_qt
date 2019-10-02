package com.joymain.ng.service;

import java.util.Collection;

import javax.jws.WebService;

import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@WebService
public interface FiProductPointBalanceManager extends GenericManager<FiProductPointBalance, Long> {
    
	public Pager<FiProductPointBalance> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,int currentPage, int pageSize);
	
	public FiProductPointBalance getProductPointBalance(final String userCode,final String productPointType);
}