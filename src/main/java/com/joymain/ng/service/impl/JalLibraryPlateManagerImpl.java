package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalLibraryPlateDao;
import com.joymain.ng.model.JalLibraryPlate;
import com.joymain.ng.service.JalLibraryPlateManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jalLibraryPlateManager")
@WebService(serviceName = "JalLibraryPlateService", endpointInterface = "com.joymain.ng.service.JalLibraryPlateManager")
public class JalLibraryPlateManagerImpl extends GenericManagerImpl<JalLibraryPlate, Long> implements JalLibraryPlateManager {
    JalLibraryPlateDao jalLibraryPlateDao;

    @Autowired
    public JalLibraryPlateManagerImpl(JalLibraryPlateDao jalLibraryPlateDao) {
        super(jalLibraryPlateDao);
        this.jalLibraryPlateDao = jalLibraryPlateDao;
    }
	
	public Pager<JalLibraryPlate> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalLibraryPlateDao.getPager(JalLibraryPlate.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJalLibraryPlatesOrderByIndex() {
		// TODO Auto-generated method stub
		return jalLibraryPlateDao.getJalLibraryPlatesOrderByIndex();
	}
}