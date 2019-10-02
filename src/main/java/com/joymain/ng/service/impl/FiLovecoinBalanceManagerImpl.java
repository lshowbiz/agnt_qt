package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiLovecoinBalanceDao;
import com.joymain.ng.model.FiLovecoinBalance;
import com.joymain.ng.service.FiLovecoinBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiLovecoinBalanceManager")
@WebService(serviceName = "FiLovecoinBalanceService", endpointInterface = "com.joymain.ng.service.FiLovecoinBalanceManager")
public class FiLovecoinBalanceManagerImpl extends GenericManagerImpl<FiLovecoinBalance, String> implements FiLovecoinBalanceManager {
    FiLovecoinBalanceDao fiLovecoinBalanceDao;

    @Autowired
    public FiLovecoinBalanceManagerImpl(FiLovecoinBalanceDao fiLovecoinBalanceDao) {
        super(fiLovecoinBalanceDao);
        this.fiLovecoinBalanceDao = fiLovecoinBalanceDao;
    }
	
	public Pager<FiLovecoinBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiLovecoinBalanceDao.getPager(FiLovecoinBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}