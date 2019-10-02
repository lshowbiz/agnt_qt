package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FoundationOrderManager extends GenericManager<FoundationOrder, Long> {
    
	public Pager<FoundationOrder> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List<FoundationOrder> getFoundationOrdersByUserCode(String userCode, String startTime, String endTime);

	public List getOrdersByItemTypeAndTime(String userCode);
	
	public void checkFoundationOrder(FoundationOrder foundationOrder,JsysUser sysUser) throws AppException;
	
	public void checkFoundationOrderByJJ(FoundationOrder foundationOrder, BigDecimal jJamount, BigDecimal cZamount, JsysUser sysUser) throws Exception;
	
	public Long saveFoundationOrder(final FoundationOrder foundationOrder);
}