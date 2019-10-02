package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalCountryDao;
import com.joymain.ng.model.JalCountry;
import com.joymain.ng.service.JalCountryManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jalCountryManager")
@WebService(serviceName = "JalCountryService", endpointInterface = "com.joymain.ng.service.JalCountryManager")
public class JalCountryManagerImpl extends GenericManagerImpl<JalCountry, Long> implements JalCountryManager {
    JalCountryDao jalCountryDao;

    @Autowired
    public JalCountryManagerImpl(JalCountryDao jalCountryDao) {
        super(jalCountryDao);
        this.jalCountryDao = jalCountryDao;
    }
	
	public Pager<JalCountry> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalCountryDao.getPager(JalCountry.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	/**
	 * 获取分公司所管辖的国家/地区列表
	 * @param companyCode
	 * @return
	 */
	public List getAlCountrysByCompany(final String companyCode){
		return jalCountryDao.getAlCountrysByCompany(companyCode);
	}
}