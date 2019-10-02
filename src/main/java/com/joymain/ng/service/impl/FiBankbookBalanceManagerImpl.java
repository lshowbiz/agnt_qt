package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiBankbookBalanceDao;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiBankbookBalanceManager")
@WebService(serviceName = "FiBankbookBalanceService", endpointInterface = "com.joymain.ng.service.FiBankbookBalanceManager")
public class FiBankbookBalanceManagerImpl extends GenericManagerImpl<FiBankbookBalance, Long> implements FiBankbookBalanceManager {
    FiBankbookBalanceDao fiBankbookBalanceDao;

    @Autowired
    public FiBankbookBalanceManagerImpl(FiBankbookBalanceDao fiBankbookBalanceDao) {
        super(fiBankbookBalanceDao);
        this.fiBankbookBalanceDao = fiBankbookBalanceDao;
    }
	
	public Pager<FiBankbookBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBankbookBalanceDao.getPager(FiBankbookBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public FiBankbookBalance getFiBankbookBalance(String userCode,
			String backbookType) {
		// TODO Auto-generated method stub
		return fiBankbookBalanceDao.getFiBankbookBalance(userCode, backbookType);
	}
}