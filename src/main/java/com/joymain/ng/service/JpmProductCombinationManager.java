package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JpmProductCombination;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpmProductCombinationManager extends GenericManager<JpmProductCombination, Long> {
    
	public Pager<JpmProductCombination> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	
	/**
	 * 通过商品编码编码获取商品的组合信息
	 * @param productNo:商品编码
	 * @return 商品的一级信息
	 */
	public List<Map<String, Object>> getJpmProductList(String productNo);


	/**
	 * 根据商品编号判断商品是否是套餐商品
	 * @author fx 2015-05-04
	 * @param productNo
	 * @return boolean
	 */
	public boolean getIsisCombinationProduct(String productNo);
	
}