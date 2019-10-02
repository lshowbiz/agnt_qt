package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JmiRecommendRefManager extends GenericManager<JmiRecommendRef, String> {
	
	/**
	 * 获取某推荐下的会员
	 * @param linkNo
	 */
	public List getJmiRecommendRefsByRecommendNo(String recommendNo);
    
	public Pager<JmiRecommendRef> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
}