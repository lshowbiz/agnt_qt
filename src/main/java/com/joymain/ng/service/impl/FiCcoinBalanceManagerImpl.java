package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiCcoinBalanceDao;
import com.joymain.ng.model.FiCcoinBalance;
import com.joymain.ng.service.FiCcoinBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiCcoinBalanceManager")
@WebService(serviceName = "FiCcoinBalanceService", endpointInterface = "com.joymain.ng.service.FiCcoinBalanceManager")
public class FiCcoinBalanceManagerImpl extends GenericManagerImpl<FiCcoinBalance, String> implements FiCcoinBalanceManager {
    FiCcoinBalanceDao fiCcoinBalanceDao;

    @Autowired
    public FiCcoinBalanceManagerImpl(FiCcoinBalanceDao fiCcoinBalanceDao) {
        super(fiCcoinBalanceDao);
        this.fiCcoinBalanceDao = fiCcoinBalanceDao;
    }
	
	public Pager<FiCcoinBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiCcoinBalanceDao.getPager(FiCcoinBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}