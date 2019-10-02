package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JpoShippingFee;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import javax.jws.WebService;

import java.math.BigDecimal;
import java.util.Collection;
@WebService
public interface JpoShippingFeeManager extends GenericManager<JpoShippingFee, Long> {
    
	public Pager<JpoShippingFee> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	/**
	 * get shippingFee by type and district
	 * @param shippingType
	 * @param district
	 * @return JpoShippingFee
	 */
	public JpoShippingFee getJpoShippingFee(String shippingType,String province,String cityId,String district,String countrCode);
	
	/**
	 * 快递策略
	 * @param fee
	 * @param totle
	 * @return
	 */
	public BigDecimal getFee(JpoShippingFee fee, BigDecimal totle);
	
	/**
	 * 零担策略
	 * @param fee
	 * @param totle
	 * @return
	 */
	public BigDecimal getFeeV(JpoShippingFee fee, BigDecimal totle);
	
	/**
	 * 零担重货策略
	 * @param fee
	 * @param totle
	 * @return
	 */
	public BigDecimal getFeeWZ(JpoShippingFee fee, BigDecimal totle);
}