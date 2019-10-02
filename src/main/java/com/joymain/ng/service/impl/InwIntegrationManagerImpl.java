package com.joymain.ng.service.impl;

import com.joymain.ng.dao.InwIntegrationDao;
import com.joymain.ng.model.InwIntegration;
import com.joymain.ng.service.InwIntegrationManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("inwIntegrationManager")
@WebService(serviceName = "InwIntegrationService", endpointInterface = "com.joymain.ng.service.InwIntegrationManager")
public class InwIntegrationManagerImpl extends GenericManagerImpl<InwIntegration, Long> implements InwIntegrationManager {
    InwIntegrationDao inwIntegrationDao;

    @Autowired
    public InwIntegrationManagerImpl(InwIntegrationDao inwIntegrationDao) {
        super(inwIntegrationDao);
        this.inwIntegrationDao = inwIntegrationDao;
    }
	
	public Pager<InwIntegration> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return inwIntegrationDao.getPager(InwIntegration.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}