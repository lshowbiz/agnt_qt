package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JbdUserCardList;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.Date;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JbdUserCardListManager extends GenericManager<JbdUserCardList, Long> {
    
	public Pager<JbdUserCardList> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	public void saveJbdUserCardList(String userCode,Date operatDate,String newCard,String updateType,String operaterType);

	/**
	 * operaterType 1 审核订单 2.更改审核日期
	 */
	public void saveJbdUserCardList(String userCode, Integer wweek, String newCard,String updateType,String operaterType) ;
}