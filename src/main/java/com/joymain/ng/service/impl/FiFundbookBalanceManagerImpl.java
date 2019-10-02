package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiFundbookBalanceDao;
import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.service.FiFundbookBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("fiFundbookBalanceManager")
@WebService(serviceName = "FiFundbookBalanceService", endpointInterface = "com.joymain.ng.service.FiFundbookBalanceManager")
public class FiFundbookBalanceManagerImpl extends GenericManagerImpl<FiFundbookBalance, Long> implements FiFundbookBalanceManager {
    FiFundbookBalanceDao fiFundbookBalanceDao;

    @Autowired
    public FiFundbookBalanceManagerImpl(FiFundbookBalanceDao fiFundbookBalanceDao) {
        super(fiFundbookBalanceDao);
        this.fiFundbookBalanceDao = fiFundbookBalanceDao;
    }
	
	public Pager<FiFundbookBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiFundbookBalanceDao.getPager(FiFundbookBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public FiFundbookBalance getFiFundbookBalance(String userCode,
			String backbookType) {
		// TODO Auto-generated method stub
		return fiFundbookBalanceDao.getFiFundbookBalance(userCode, backbookType);
	}
}