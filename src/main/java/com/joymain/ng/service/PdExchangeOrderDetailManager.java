package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface PdExchangeOrderDetailManager extends GenericManager<PdExchangeOrderDetail, Long> {
    
	public Pager<PdExchangeOrderDetail> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	
	List<PdExchangeOrderDetail> getPdExchangeOrderDetails();

	PdExchangeOrderDetail getPdExchangeOrderDetail(final Long uniNo);

    void savePdExchangeOrderDetail(PdExchangeOrderDetail pdExchangeOrderDetail);

    void removePdExchangeOrderDetail(final Long uniNo);


	public PdExchangeOrderDetail getDonationPdExchangeOrderDetail(
			PdExchangeOrderDetail pdExchangeOrderDetail);
}