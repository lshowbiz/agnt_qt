package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpoShippingFeeDao;
import com.joymain.ng.model.JpoShippingFee;
import com.joymain.ng.service.JpoShippingFeeManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jpoShippingFeeManager")
@WebService(serviceName = "JpoShippingFeeService", endpointInterface = "com.joymain.ng.service.JpoShippingFeeManager")
public class JpoShippingFeeManagerImpl extends GenericManagerImpl<JpoShippingFee, Long> implements JpoShippingFeeManager {
    JpoShippingFeeDao jpoShippingFeeDao;

    @Autowired
    public JpoShippingFeeManagerImpl(JpoShippingFeeDao jpoShippingFeeDao) {
        super(jpoShippingFeeDao);
        this.jpoShippingFeeDao = jpoShippingFeeDao;
    }
	
	public Pager<JpoShippingFee> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpoShippingFeeDao.getPager(JpoShippingFee.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public JpoShippingFee getJpoShippingFee(String shippingType,String pro,String cityId, String district,String countrCode) {
		return jpoShippingFeeDao.getJpoShippingFee(shippingType,pro,cityId,district,countrCode) ;
	}
	
	public BigDecimal getFee(JpoShippingFee fee,BigDecimal totle){
		return jpoShippingFeeDao.getFee(fee, totle);
	}
	
	public BigDecimal getFeeV(JpoShippingFee fee,BigDecimal totle){
		return jpoShippingFeeDao.getFeeV(fee, totle);
	}
	
	public BigDecimal getFeeWZ(JpoShippingFee fee,BigDecimal totle){
		return jpoShippingFeeDao.getFeeWZ(fee, totle);
	}
}