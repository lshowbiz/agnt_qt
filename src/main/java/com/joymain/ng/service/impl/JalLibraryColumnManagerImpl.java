package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalLibraryColumnDao;
import com.joymain.ng.model.JalLibraryColumn;
import com.joymain.ng.service.JalLibraryColumnManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jalLibraryColumnManager")
@WebService(serviceName = "JalLibraryColumnService", endpointInterface = "com.joymain.ng.service.JalLibraryColumnManager")
public class JalLibraryColumnManagerImpl extends GenericManagerImpl<JalLibraryColumn, Long> implements JalLibraryColumnManager {
    JalLibraryColumnDao jalLibraryColumnDao;

    @Autowired
    public JalLibraryColumnManagerImpl(JalLibraryColumnDao jalLibraryColumnDao) {
        super(jalLibraryColumnDao);
        this.jalLibraryColumnDao = jalLibraryColumnDao;
    }
	
	public Pager<JalLibraryColumn> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalLibraryColumnDao.getPager(JalLibraryColumn.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJalLibraryColumnListByPlateIndex(String plateIndex) {
		// TODO Auto-generated method stub
		return jalLibraryColumnDao.getJalLibraryColumnListByPlateIndex(plateIndex);
	}
}