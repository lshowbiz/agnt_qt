package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PdNotChangeProductDao;
import com.joymain.ng.model.PdNotChangeProduct;
import com.joymain.ng.service.PdNotChangeProductManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("pdNotChangeProductManager")
@WebService(serviceName = "PdNotChangeProductService", endpointInterface = "com.joymain.ng.service.PdNotChangeProductManager")
public class PdNotChangeProductManagerImpl extends GenericManagerImpl<PdNotChangeProduct, Long> implements PdNotChangeProductManager {
    PdNotChangeProductDao pdNotChangeProductDao;

    @Autowired
    public PdNotChangeProductManagerImpl(PdNotChangeProductDao pdNotChangeProductDao) {
        super(pdNotChangeProductDao);
        this.pdNotChangeProductDao = pdNotChangeProductDao;
    }
	
	public Pager<PdNotChangeProduct> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdNotChangeProductDao.getPager(PdNotChangeProduct.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public void savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct) {
		pdNotChangeProductDao.savePdNotChangeProduct(pdNotChangeProduct);
		
	}

	@Override
	public List<PdNotChangeProduct> getPdNotChangeProducts() {
		// TODO Auto-generated method stub
		return pdNotChangeProductDao.getNotChangedProducts();
	}

	@Override
	public PdNotChangeProduct getPdNotChangeProduct(String productNo) {
		// TODO Auto-generated method stub
		return pdNotChangeProductDao.getPdNotChangeProduct(productNo);
	}
}