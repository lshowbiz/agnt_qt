package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JalDistrictManager extends GenericManager<JalDistrict, Long> {
    
	public Pager<JalDistrict> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	public JalDistrict get(String districtId);
	public List getAlDistrictByCityId(Long cityId);
}