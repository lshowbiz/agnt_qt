package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpoUserCouponDao;
import com.joymain.ng.model.JpmCouponInfo;
import com.joymain.ng.model.JpoUserCoupon;
import com.joymain.ng.service.JpoUserCouponManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jpoUserCouponManager")
@WebService(serviceName = "JpoUserCouponService", endpointInterface = "com.joymain.ng.service.JpoUserCouponManager")
public class JpoUserCouponManagerImpl extends GenericManagerImpl<JpoUserCoupon, Long> implements JpoUserCouponManager {
    JpoUserCouponDao jpoUserCouponDao;

    @Autowired
    public JpoUserCouponManagerImpl(JpoUserCouponDao jpoUserCouponDao) {
        super(jpoUserCouponDao);
        this.jpoUserCouponDao = jpoUserCouponDao;
    }
	
	public Pager<JpoUserCoupon> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpoUserCouponDao.getPager(JpoUserCoupon.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJpoUserCouponPageToSql(Map<String, String> params, GroupPage page) {
		return jpoUserCouponDao.getJpoUserCouponPageToSql(params, page);
	}

	@Override
	public List getJpoUserCouponToSql(Map<String, String> params) {
		return jpoUserCouponDao.getJpoUserCouponToSql(params);
	}

	/**
	 * Add By WuCF 20170523
	 * 查询代金券数据
	 * @param userCode
	 * @return
	 */
	public List getJpoUserCoupons(final String userCode){
		return jpoUserCouponDao.getJpoUserCoupons(userCode);
	}

	@Override
	public JpoUserCoupon getJpoUserCouponById(String id) {
		JpoUserCoupon jpoUserCoupon = jpoUserCouponDao.getJpoUserCouponById(id);
		return jpoUserCoupon;
	}
}