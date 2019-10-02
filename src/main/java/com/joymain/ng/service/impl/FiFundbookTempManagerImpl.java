package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiFundbookTempDao;
import com.joymain.ng.model.FiFundbookTemp;
import com.joymain.ng.service.FiFundbookTempManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiFundbookTempManager")
@WebService(serviceName = "FiFundbookTempService", endpointInterface = "com.joymain.ng.service.FiFundbookTempManager")
public class FiFundbookTempManagerImpl extends GenericManagerImpl<FiFundbookTemp, Long> implements FiFundbookTempManager {
    FiFundbookTempDao fiFundbookTempDao;

    @Autowired
    public FiFundbookTempManagerImpl(FiFundbookTempDao fiFundbookTempDao) {
        super(fiFundbookTempDao);
        this.fiFundbookTempDao = fiFundbookTempDao;
    }
	
	public Pager<FiFundbookTemp> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiFundbookTempDao.getPager(FiFundbookTemp.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}