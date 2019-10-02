package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FiBillAccountWarning;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FiBillAccountWarningManager extends GenericManager<FiBillAccountWarning, String> {
    
	public Pager<FiBillAccountWarning> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	

	/**
	 * 进账记录叠加
	 * @param amout
	 * @param billAccountCode
	 */
	public void addTotalAmount(BigDecimal amout ,String billAccountCode);
}