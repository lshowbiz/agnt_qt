package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.InwDemand;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface InwDemandManager extends GenericManager<InwDemand, Long> {
    
	public Pager<InwDemand> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	/**
     * 创新共赢的需求(合作共赢)的查询
     * @author gw  2013-08-13
     * @param other
     */
	public List getInwDemandList(String other);
 
	/**
	 * 创新共赢的需求（合作共赢）的详细查询
	 * @author gw 2013-08-14
	 * @param id
	 * @return
	 */
	public InwDemand getInwDemandDetail(String id);
	
	public String getFileURL(String companyCode);
	
	
	/**
	 * 创新共赢---通过ID获取需求表的需求名称
	 * @author gw 2013-11-08
	 * @param id
	 * @return string
	 */
	public String getInwDemandById(String id);
}