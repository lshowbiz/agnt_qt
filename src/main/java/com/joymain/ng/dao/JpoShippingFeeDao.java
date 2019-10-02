package com.joymain.ng.dao;

import java.math.BigDecimal;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpoShippingFee;

/**
 * An interface that provides a data management interface to the JpoShippingFee table.
 */
public interface JpoShippingFeeDao extends GenericDao<JpoShippingFee, Long> {
	
	/**
	 * get JpoShippingFee By type and district
	 * @param shippingType
	 * @param district
	 * @return JpoShippingFee
	 */
	JpoShippingFee getJpoShippingFee(String shippingType,String pro,String cityId, String district,String countryCode);
	
	/**
	 * 快递策略
	 * @param fee
	 * @param totle
	 * @return
	 */
	public BigDecimal getFee(JpoShippingFee fee,BigDecimal totle);

	/**
	 * 零担策略
	 * @param fee
	 * @param totle
	 * @return
	 */
	public BigDecimal getFeeV(JpoShippingFee fee,BigDecimal totle);
	
	/**
	 * 零担重货策略
	 * @param fee
	 * @param totle
	 * @return
	 */
	public BigDecimal getFeeWZ(JpoShippingFee fee,BigDecimal totle);
}