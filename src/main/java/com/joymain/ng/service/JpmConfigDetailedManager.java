package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.model.JpmConfigDetailed;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpmConfigDetailedManager extends GenericManager<JpmConfigDetailed, Long> {
    
	public Pager<JpmConfigDetailed> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public void addJpmConfigDetailed(HttpServletRequest request);
	
	public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed);
	
	public Integer delJpmConfigDetailed(Long detailedId);
	
	public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo);
	
	/**
	 * 根据规格id查询对应配件数量
	 * @param specNo
	 * @return
	 */
	public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo);
	
}