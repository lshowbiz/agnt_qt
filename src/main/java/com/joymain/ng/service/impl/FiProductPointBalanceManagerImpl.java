package com.joymain.ng.service.impl;

import java.util.Collection;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.FiProductPointBalanceDao;
import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.service.FiProductPointBalanceManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("fiProductPointBalanceManager")
@WebService(serviceName = "FiProductPointBalanceService", endpointInterface = "com.joymain.ng.service.FiProductPointBalanceManager")
public class FiProductPointBalanceManagerImpl extends GenericManagerImpl<FiProductPointBalance, Long> implements FiProductPointBalanceManager {
	
	FiProductPointBalanceDao fiProductPointBalanceDao;

    @Autowired
    public FiProductPointBalanceManagerImpl(FiProductPointBalanceDao fiProductPointBalanceDao) {
        super(fiProductPointBalanceDao);
        this.fiProductPointBalanceDao = fiProductPointBalanceDao;
    }
	
	public Pager<FiProductPointBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return fiProductPointBalanceDao.getPager(FiProductPointBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public FiProductPointBalance getProductPointBalance(String userCode,String productPointType) {
		return fiProductPointBalanceDao.getFiProductPointBalance(userCode, productPointType);
	}
}