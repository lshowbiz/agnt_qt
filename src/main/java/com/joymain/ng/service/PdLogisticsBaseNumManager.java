package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PdLogisticsBaseNum;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface PdLogisticsBaseNumManager extends GenericManager<PdLogisticsBaseNum, Long> {
    
	public Pager<PdLogisticsBaseNum> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	/**
	 * @author gw 2014-11-11
	 * 报单中心前台-物流跟踪查询 
	 * @param moId
	 * @return
	 */
	public List getPdLogisticsBaseNumAndDetail(String moId);

	/**
	 * 根据物流单号获取物流信息
	 * @author gw 2015-02-11
	 * @param mailNo
	 * @return pdLogisticsBaseNum
	 */
	public PdLogisticsBaseNum getPdLogisticsBaseNumForMailNo(String mailNo);
	
}