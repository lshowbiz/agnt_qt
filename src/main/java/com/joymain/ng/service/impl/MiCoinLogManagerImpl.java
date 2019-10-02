package com.joymain.ng.service.impl;

import com.joymain.ng.dao.MiCoinLogDao;
import com.joymain.ng.model.MiCoinLog;
import com.joymain.ng.service.MiCoinLogManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("miCoinLogManager")
@WebService(serviceName = "MiCoinLogService", endpointInterface = "com.joymain.ng.service.MiCoinLogManager")
public class MiCoinLogManagerImpl extends GenericManagerImpl<MiCoinLog, Long> implements MiCoinLogManager {
    MiCoinLogDao miCoinLogDao;

    @Autowired
    public MiCoinLogManagerImpl(MiCoinLogDao miCoinLogDao) {
        super(miCoinLogDao);
        this.miCoinLogDao = miCoinLogDao;
    }
	
	public Pager<MiCoinLog> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return miCoinLogDao.getPager(MiCoinLog.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public void saveMiCoinLog(MiCoinLog miCoinLog) {
		miCoinLogDao.saveMiCoinLog(miCoinLog);
		
	}
}