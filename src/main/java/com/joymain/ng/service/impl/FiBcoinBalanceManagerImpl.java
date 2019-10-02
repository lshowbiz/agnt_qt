package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiBcoinBalanceDao;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiBcoinBalanceManager")
@WebService(serviceName = "FiBcoinBalanceService", endpointInterface = "com.joymain.ng.service.FiBcoinBalanceManager")
public class FiBcoinBalanceManagerImpl extends GenericManagerImpl<FiBcoinBalance, String> implements FiBcoinBalanceManager {
    FiBcoinBalanceDao fiBcoinBalanceDao;

    @Autowired
    public FiBcoinBalanceManagerImpl(FiBcoinBalanceDao fiBcoinBalanceDao) {
        super(fiBcoinBalanceDao);
        this.fiBcoinBalanceDao = fiBcoinBalanceDao;
    }
	
	public Pager<FiBcoinBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBcoinBalanceDao.getPager(FiBcoinBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}