package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JpmProductCombination;

/**
 * An interface that provides a data management interface to the JpmProductCombination table.
 */
public interface JpmProductCombinationDao extends GenericDao<JpmProductCombination, Long> {
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