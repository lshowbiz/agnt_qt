package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmProductCombinationDao;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.PdExchangeOrderBackDao;
import com.joymain.ng.dao.PdNotChangeProductDao;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.service.PdExchangeOrderBackManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("pdExchangeOrderBackManager")
@WebService(serviceName = "PdExchangeOrderBackService", endpointInterface = "com.joymain.ng.service.PdExchangeOrderBackManager")
public class PdExchangeOrderBackManagerImpl extends GenericManagerImpl<PdExchangeOrderBack, Long> implements PdExchangeOrderBackManager {
    PdExchangeOrderBackDao pdExchangeOrderBackDao;
    
    @Autowired
    public PdExchangeOrderBackManagerImpl(PdExchangeOrderBackDao pdExchangeOrderBackDao) {
        super(pdExchangeOrderBackDao);
        this.pdExchangeOrderBackDao = pdExchangeOrderBackDao;
        
    }
    
	public Pager<PdExchangeOrderBack> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdExchangeOrderBackDao.getPager(PdExchangeOrderBack.class, searchBeans, OrderBys, currentPage, pageSize);
	}


	@Override
	public List<PdExchangeOrderBack> getPdExchangeOrderBacks() {
		// TODO Auto-generated method stub
		return pdExchangeOrderBackDao.getPdExchangeOrderBacks();
	}

	@Override
	public PdExchangeOrderBack getPdExchangeOrderBack(Long uniNo) {
		// TODO Auto-generated method stub
		return pdExchangeOrderBackDao.getPdExchangeOrderBack(uniNo);
	}

	@Override
	public void savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack) {
		// TODO Auto-generated method stub
		pdExchangeOrderBackDao.savePdExchangeOrderBack(pdExchangeOrderBack);
	}

	@Override
	public void removePdExchangeOrderBack(Long uniNo) {
		// TODO Auto-generated method stub
		pdExchangeOrderBackDao.removePdExchangeOrderBack(uniNo);
	}

}