package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiCoinLogDao;
import com.joymain.ng.model.FiCoinLog;
import com.joymain.ng.service.FiCoinLogManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiCoinLogManager")
@WebService(serviceName = "FiCoinLogService", endpointInterface = "com.joymain.ng.service.FiCoinLogManager")
public class FiCoinLogManagerImpl extends GenericManagerImpl<FiCoinLog, Long> implements FiCoinLogManager {
    FiCoinLogDao fiCoinLogDao;

    @Autowired
    public FiCoinLogManagerImpl(FiCoinLogDao fiCoinLogDao) {
        super(fiCoinLogDao);
        this.fiCoinLogDao = fiCoinLogDao;
    }
	
	public Pager<FiCoinLog> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiCoinLogDao.getPager(FiCoinLog.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public List getFiCoinLogs(final FiCoinLog fiCoinLog) {
        return fiCoinLogDao.getFiCoinLogs(fiCoinLog);
    }
}