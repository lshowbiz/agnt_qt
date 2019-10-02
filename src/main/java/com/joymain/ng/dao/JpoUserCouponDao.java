package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JpmCouponInfo;
import com.joymain.ng.model.JpoUserCoupon;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JpoUserCoupon table.
 */
public interface JpoUserCouponDao extends GenericDao<JpoUserCoupon, Long> {
	
	public List getJpoUserCouponPageToSql(Map<String, String> params, GroupPage page);
	
	public List getJpoUserCouponToSql(Map<String, String> params);
	
	/**
	 * Add By WuCF 20170523
	 * 查询代金券数据
	 * @param userCode
	 * @return
	 */
	public List getJpoUserCoupons(final String userCode);
	
	public JpoUserCoupon getJpoUserCouponById(String id);
}