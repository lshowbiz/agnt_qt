package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PublicSchedule;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface PublicScheduleManager extends GenericManager<PublicSchedule, Long> {
    
	public Pager<PublicSchedule> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List getScheduleByUserCode( String today);
	
	/**
	 * Add By WuCF 20131209 
	 * 查询指定行数的数据
	 * @param today
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List getScheduleByUserCode( String today,Integer startIndex,Integer endIndex);
}