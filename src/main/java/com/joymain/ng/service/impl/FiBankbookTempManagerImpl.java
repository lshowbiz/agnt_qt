package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiBankbookTempDao;
import com.joymain.ng.model.FiBankbookTemp;
import com.joymain.ng.service.FiBankbookTempManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiBankbookTempManager")
@WebService(serviceName = "FiBankbookTempService", endpointInterface = "com.joymain.ng.service.FiBankbookTempManager")
public class FiBankbookTempManagerImpl extends GenericManagerImpl<FiBankbookTemp, Long> implements FiBankbookTempManager {
    FiBankbookTempDao fiBankbookTempDao;

    @Autowired
    public FiBankbookTempManagerImpl(FiBankbookTempDao fiBankbookTempDao) {
        super(fiBankbookTempDao);
        this.fiBankbookTempDao = fiBankbookTempDao;
    }
	
	public Pager<FiBankbookTemp> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBankbookTempDao.getPager(FiBankbookTemp.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}