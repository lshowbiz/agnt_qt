package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiGetbillaccountLogDao;
import com.joymain.ng.model.FiGetbillaccountLog;
import com.joymain.ng.service.FiGetbillaccountLogManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiGetbillaccountLogManager")
@WebService(serviceName = "FiGetbillaccountLogService", endpointInterface = "com.joymain.ng.service.FiGetbillaccountLogManager")
public class FiGetbillaccountLogManagerImpl extends GenericManagerImpl<FiGetbillaccountLog, Long> implements FiGetbillaccountLogManager {
    FiGetbillaccountLogDao fiGetbillaccountLogDao;

    @Autowired
    public FiGetbillaccountLogManagerImpl(FiGetbillaccountLogDao fiGetbillaccountLogDao) {
        super(fiGetbillaccountLogDao);
        this.fiGetbillaccountLogDao = fiGetbillaccountLogDao;
    }
	
	public Pager<FiGetbillaccountLog> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiGetbillaccountLogDao.getPager(FiGetbillaccountLog.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}