package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FiFundbookBalanceManager extends GenericManager<FiFundbookBalance, Long> {
    
	public Pager<FiFundbookBalance> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public FiFundbookBalance getFiFundbookBalance(final String userCode,final String backbookType);
}