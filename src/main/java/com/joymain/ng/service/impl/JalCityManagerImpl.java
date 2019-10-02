package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalCityDao;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jalCityManager")
@WebService(serviceName = "JalCityService", endpointInterface = "com.joymain.ng.service.JalCityManager")
public class JalCityManagerImpl extends GenericManagerImpl<JalCity, Long> implements JalCityManager {
    JalCityDao jalCityDao;

    @Autowired
    public JalCityManagerImpl(JalCityDao jalCityDao) {
        super(jalCityDao);
        this.jalCityDao = jalCityDao;
    }
	
	public Pager<JalCity> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalCityDao.getPager(JalCity.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public JalCity get(String cityId) {
		return jalCityDao.get(new Long(cityId));
	}

	public List getAlCityByProvinceId(Long provinceId) {
		return jalCityDao.getAlCityByProvinceId(provinceId);
	}
}