package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JbdBounsDeduct;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JbdBounsDeductManager extends GenericManager<JbdBounsDeduct, Long> {
    
	public Pager<JbdBounsDeduct> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

    public List getJbdBounsDeduct(Map map);
}