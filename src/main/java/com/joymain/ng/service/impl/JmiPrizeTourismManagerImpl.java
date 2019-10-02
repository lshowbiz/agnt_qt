package com.joymain.ng.service.impl;

import java.util.Collection;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JmiPrizeTourismDao;
import com.joymain.ng.model.JmiPrizeTourism;
import com.joymain.ng.service.JmiPrizeTourismManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jmiPrizeTourismManager")
@WebService(serviceName = "JmiPrizeTourismService", endpointInterface = "com.joymain.ng.service.JmiPrizeTourismManager")
public class JmiPrizeTourismManagerImpl extends GenericManagerImpl<JmiPrizeTourism, String> implements JmiPrizeTourismManager {
    JmiPrizeTourismDao jmiPrizeTourismDao;

    @Autowired
    public JmiPrizeTourismManagerImpl(JmiPrizeTourismDao jmiPrizeTourismDao) {
        super(jmiPrizeTourismDao);
        this.jmiPrizeTourismDao = jmiPrizeTourismDao;
    }
	
	public Pager<JmiPrizeTourism> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiPrizeTourismDao.getPager(JmiPrizeTourism.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public JmiPrizeTourism getJmiPrizeTourismByUsercode(String userCode) {
		// TODO Auto-generated method stub
		return jmiPrizeTourismDao.getJmiPrizeTourismByUsercode(userCode);
	}

	@Override
	public void saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism) {
		// TODO Auto-generated method stub
		jmiPrizeTourismDao.saveJmiPrizeTourism(jmiPrizeTourism);
	}

	@Override
	public String getPassStarByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return jmiPrizeTourismDao.getPassStarByUserCode(userCode);
	}

	@Override
	public String geIspeerByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return jmiPrizeTourismDao.geIspeerByUserCode(userCode);
	}
}