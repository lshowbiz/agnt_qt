package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.PdLogisticsBaseDetail;

/**
 * An interface that provides a data management interface to the PdLogisticsBaseDetail table.
 */
public interface PdLogisticsBaseDetailDao extends GenericDao<PdLogisticsBaseDetail, Long> {

	/**
	 * 查询套餐商品在这个物流跟踪单号下的子商品
	 * @author gw 2015-05-04
	 * @param numId PdLogisticsBaseNum所关联表的主键
	 * @param productNo
	 * @return list
	 */
	List<PdLogisticsBaseDetail> getlistpdLogisticsBaseDetail(Long numId,String productNo);

	/**
	 * 套餐物流信息展示-根据商品编码获取商品名称
	 * @author gw 2015-05-08
	 * @param productNo 商品编码
	 * @return string
	 */
	String getProductName(String productNo);

}