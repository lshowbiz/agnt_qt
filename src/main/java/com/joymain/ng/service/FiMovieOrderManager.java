package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FiMovieOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FiMovieOrderManager extends GenericManager<FiMovieOrder, String> {
    
	public Pager<FiMovieOrder> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * 发展基金支付微信电影票订单
	 * @param user
	 * @param orderId
	 * @param amount
	 * @throws Exception
	 */
	public void payMovieOrder(JsysUser user, String orderId, BigDecimal amount) throws Exception;
}