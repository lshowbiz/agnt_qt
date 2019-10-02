package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiCommonAddrDao;
import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiCommonAddrManager")
@WebService(serviceName = "FiCommonAddrService", endpointInterface = "com.joymain.ng.service.FiCommonAddrManager")
public class FiCommonAddrManagerImpl extends GenericManagerImpl<FiCommonAddr, String> implements FiCommonAddrManager {
    FiCommonAddrDao fiCommonAddrDao;

    @Autowired
    public FiCommonAddrManagerImpl(FiCommonAddrDao fiCommonAddrDao) {
        super(fiCommonAddrDao);
        this.fiCommonAddrDao = fiCommonAddrDao;
    }
	
	public Pager<FiCommonAddr> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiCommonAddrDao.getPager(FiCommonAddr.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}