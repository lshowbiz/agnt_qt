package com.joymain.ng.service.impl;

import com.joymain.ng.dao.AmNewDao;
import com.joymain.ng.model.AmNew;
import com.joymain.ng.service.AmNewManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("amNewManager")
@WebService(serviceName = "AmNewService", endpointInterface = "com.joymain.ng.service.AmNewManager")
public class AmNewManagerImpl extends GenericManagerImpl<AmNew, String> implements AmNewManager {
    AmNewDao amNewDao;

    @Autowired
    public AmNewManagerImpl(AmNewDao amNewDao) {
        super(amNewDao);
        this.amNewDao = amNewDao;
    }
	
	public Pager<AmNew> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return amNewDao.getPager(AmNew.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List<AmNew> findNewByDate(String sDate, String eDate) {
		return  amNewDao.findNewByDate(sDate, eDate);
	}
}