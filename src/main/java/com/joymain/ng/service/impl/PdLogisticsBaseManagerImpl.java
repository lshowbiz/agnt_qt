package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PdLogisticsBaseDao;
import com.joymain.ng.model.PdLogisticsBase;
import com.joymain.ng.service.PdLogisticsBaseManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("pdLogisticsBaseManager")
@WebService(serviceName = "PdLogisticsBaseService", endpointInterface = "com.joymain.ng.service.PdLogisticsBaseManager")
public class PdLogisticsBaseManagerImpl extends GenericManagerImpl<PdLogisticsBase, Long> implements PdLogisticsBaseManager {
    PdLogisticsBaseDao pdLogisticsBaseDao;

    @Autowired
    public PdLogisticsBaseManagerImpl(PdLogisticsBaseDao pdLogisticsBaseDao) {
        super(pdLogisticsBaseDao);
        this.pdLogisticsBaseDao = pdLogisticsBaseDao;
    }
	
	public Pager<PdLogisticsBase> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdLogisticsBaseDao.getPager(PdLogisticsBase.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}