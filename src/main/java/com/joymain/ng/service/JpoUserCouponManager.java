package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JpmCouponInfo;
import com.joymain.ng.model.JpoUserCoupon;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpoUserCouponManager extends GenericManager<JpoUserCoupon, Long> {
    
	public Pager<JpoUserCoupon> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List getJpoUserCouponPageToSql(Map<String, String> params, GroupPage page);
	
	public List getJpoUserCouponToSql(Map<String, String> params);
	
	/**
	 * Add By WuCF 20170523
	 *  查询代金券数据
	 * @param userCode
	 * @return
	 */
	public List getJpoUserCoupons(final String userCode);
	
	/**
	 * 根据用户代金券查询代金券
	 * @param id
	 * @return
	 */
	public JpoUserCoupon getJpoUserCouponById(String id);
}