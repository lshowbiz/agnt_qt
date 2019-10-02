package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalDistrictDao;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jalDistrictManager")
@WebService(serviceName = "JalDistrictService", endpointInterface = "com.joymain.ng.service.JalDistrictManager")
public class JalDistrictManagerImpl extends GenericManagerImpl<JalDistrict, Long> implements JalDistrictManager {
    JalDistrictDao jalDistrictDao;

    @Autowired
    public JalDistrictManagerImpl(JalDistrictDao jalDistrictDao) {
        super(jalDistrictDao);
        this.jalDistrictDao = jalDistrictDao;
    }
	
	public Pager<JalDistrict> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalDistrictDao.getPager(JalDistrict.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public JalDistrict get(String districtId) {
		return jalDistrictDao.get(new Long(districtId));
	}

	public List getAlDistrictByCityId(Long cityId) {
		return jalDistrictDao.getAlDistrictByCityId(cityId);
	}
}