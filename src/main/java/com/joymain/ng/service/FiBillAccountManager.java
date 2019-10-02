package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FiBillAccount;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FiBillAccountManager extends GenericManager<FiBillAccount, String> {
    
	public Pager<FiBillAccount> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public FiBillAccount getFiBillAccountByUserCode(String userCode);
	
	/**
	 * 根据商户号获取商户对象
	 * @param billAccountCode
	 * @return
	 */
	public FiBillAccount getFiBillAccountByBillAccountCode(
			String billAccountCode) ;
}