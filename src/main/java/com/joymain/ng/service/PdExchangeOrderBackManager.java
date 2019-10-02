package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface PdExchangeOrderBackManager extends GenericManager<PdExchangeOrderBack, Long> {
    
	public Pager<PdExchangeOrderBack> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	List<PdExchangeOrderBack> getPdExchangeOrderBacks();
	
	PdExchangeOrderBack getPdExchangeOrderBack(final Long uniNo);

    void savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack);

    void removePdExchangeOrderBack(final Long uniNo);

	//public boolean isNotExchangeProduct(PdExchangeOrderBack pdExchangeOrderBack);
	
}