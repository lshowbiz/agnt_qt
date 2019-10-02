package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.InwDemandsort;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface InwDemandsortManager extends GenericManager<InwDemandsort, Long> {
    
	public Pager<InwDemandsort> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
     * 需求分类初始化查询(这个查询时没有任何查询条件的)
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	public List getDemandsortList();

	
    
	/**
     * 查询该需求大类上的所有小类的集合
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	public List getDemandsortDetailList(String id);

	

	/**
	 * 创新共赢---需求分类-----获取需求分类的分类名称
	 * @author gw  2013-11-08
	 * @param demandsort_id
	 * @return string
	 */
    public String getInwDemandsortById(String demandsort_id);
    
}