package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JalStateProvinceManager extends GenericManager<JalStateProvince, Long> {
    
	public Pager<JalStateProvince> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	public JalStateProvince get(String stateProvinceId);
	public List getJalStateProvinceByCountryCode(String countryCode);
	
	
}