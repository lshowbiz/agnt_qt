package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JpmSalePromoter;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JpmSalePromoterManager extends GenericManager<JpmSalePromoter, Long> {
    
	public Pager<JpmSalePromoter> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
     * 获取当前已激活的促销策略
     * @param stime
     * @param isActiva
     * @return list
     */
    public List<JpmSalePromoter> getSaleByDate(String stime, String isActiva);
}