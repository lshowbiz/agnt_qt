package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PdExchangeOrderDetailDao;
import com.joymain.ng.dao.PdNotChangeProductDao;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.service.PdExchangeOrderDetailManager;
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

@Service("pdExchangeOrderDetailManager")
@WebService(serviceName = "PdExchangeOrderDetailService", endpointInterface = "com.joymain.ng.service.PdExchangeOrderDetailManager")
public class PdExchangeOrderDetailManagerImpl extends GenericManagerImpl<PdExchangeOrderDetail, Long> implements PdExchangeOrderDetailManager {
    PdExchangeOrderDetailDao pdExchangeOrderDetailDao;

    @Autowired
    public PdExchangeOrderDetailManagerImpl(PdExchangeOrderDetailDao pdExchangeOrderDetailDao) {
        super(pdExchangeOrderDetailDao);
        this.pdExchangeOrderDetailDao = pdExchangeOrderDetailDao;
    }
    
	
	public Pager<PdExchangeOrderDetail> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdExchangeOrderDetailDao.getPager(PdExchangeOrderDetail.class, searchBeans, OrderBys, currentPage, pageSize);
	}


	@Override
	public List<PdExchangeOrderDetail> getPdExchangeOrderDetails() {
		return pdExchangeOrderDetailDao.getPdExchangeOrderDetails();
	}


	@Override
	public PdExchangeOrderDetail getPdExchangeOrderDetail(Long uniNo) {
		// TODO Auto-generated method stub
		return pdExchangeOrderDetailDao.getPdExchangeOrderDetail(uniNo);
	}


	@Override
	public void savePdExchangeOrderDetail(
			PdExchangeOrderDetail pdExchangeOrderDetail) {
		pdExchangeOrderDetailDao.savePdExchangeOrderDetail(pdExchangeOrderDetail);
		
	}


	@Override
	public void removePdExchangeOrderDetail(Long uniNo) {
		pdExchangeOrderDetailDao.removePdExchangeOrderDetail(uniNo); 
		
	}
	@Override
	public PdExchangeOrderDetail getDonationPdExchangeOrderDetail(
			PdExchangeOrderDetail pdExchangeOrderDetail) {
		String eoNo = pdExchangeOrderDetail.getPdExchangeOrder().getEoNo();
		String productNo = pdExchangeOrderDetail.getProductNo();
		if("P05080100101CN0".equals(productNo)){
			productNo = "P05090100101CN0";
			return pdExchangeOrderDetailDao.getDonationPdExchangeOrderDetail(eoNo, productNo, new BigDecimal(0), new BigDecimal(0));
		}
		return null;
	}
}