package com.joymain.ng.service;

import java.util.Collection;

import javax.jws.WebService;

import com.joymain.ng.model.JmiValidLevelList;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JmiValidLevelListManager extends GenericManager<JmiValidLevelList, Long> {
    
	public Pager<JmiValidLevelList> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * 当前会员当前期别的手工定级记录
	 * @param bdPeriod
	 * @param userCode
	 * @return
	 */
	public JmiValidLevelList getValidLevel(String bdPeriod, String userCode);
	
}
