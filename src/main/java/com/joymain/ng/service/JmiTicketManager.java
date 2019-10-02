package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiTicket;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface JmiTicketManager extends GenericManager<JmiTicket, Long> {
    
	public Pager<JmiTicket> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

    public List getJmiTicketByUserCode(String userCode);
}