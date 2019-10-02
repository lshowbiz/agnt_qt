package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalStateProvinceDao;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("jalStateProvinceManager")
@WebService(serviceName = "JalStateProvinceService", endpointInterface = "com.joymain.ng.service.JalStateProvinceManager")
public class JalStateProvinceManagerImpl extends GenericManagerImpl<JalStateProvince, Long> implements JalStateProvinceManager {
    JalStateProvinceDao jalStateProvinceDao;

    @Autowired
    public JalStateProvinceManagerImpl(JalStateProvinceDao jalStateProvinceDao) {
        super(jalStateProvinceDao);
        this.jalStateProvinceDao = jalStateProvinceDao;
    }
	
	public Pager<JalStateProvince> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalStateProvinceDao.getPager(JalStateProvince.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public JalStateProvince get(String stateProvinceId) {
		return jalStateProvinceDao.get(new Long(stateProvinceId));
	}

	public List getJalStateProvinceByCountryCode(String countryCode) {
		return jalStateProvinceDao.getJalStateProvinceByCountryCode(countryCode);
	}
}