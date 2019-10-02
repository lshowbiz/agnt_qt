package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FiBcoinPayconfig;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FiBcoinPayconfigManager extends GenericManager<FiBcoinPayconfig, Long> {
    
	public Pager<FiBcoinPayconfig> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public FiBcoinPayconfig getFiBcoinPayconfigByNowTime();
	
	public boolean getCanUseCoinPayByOrder(FiBcoinPayconfig fiBcoinPayconfig, JpoMemberOrder jpoMemberOrder);
	
	/**
	 * 积分混合支付成功，需要对限购的数量做计算当前剩余量
	 * @param jpoMemberOrder
	 * @param fiBcoinPayconfig
	 */
	public void comNowQuantity(JpoMemberOrder jpoMemberOrder, FiBcoinPayconfig fiBcoinPayconfig);
}