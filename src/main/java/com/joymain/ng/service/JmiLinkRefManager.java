package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiLinkRef;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface JmiLinkRefManager extends GenericManager<JmiLinkRef, String> {
    
	public Pager<JmiLinkRef> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

    public List getLinkRefbyLinkNo(String linkNo,String orderByName,String sort);
    
    public List getJmiLinkRefByNo(String userCode);
	public String getLinkNo(String recommendNo);
}