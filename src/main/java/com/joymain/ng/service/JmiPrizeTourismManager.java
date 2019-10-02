package com.joymain.ng.service;

import java.util.Collection;

import javax.jws.WebService;

import com.joymain.ng.model.JmiPrizeTourism;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JmiPrizeTourismManager extends GenericManager<JmiPrizeTourism, String> {
    
	public Pager<JmiPrizeTourism> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * 根据会员编号查询奖游信息
	 * @param userCode
	 * @return
	 */
	public JmiPrizeTourism getJmiPrizeTourismByUsercode(String userCode);
	/**
	 * 保存
	 * @param jmiPrizeTourism
	 */
	public void saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism);
	
	public String getPassStarByUserCode(String userCode);
	
	public String geIspeerByUserCode(String userCode);
	
}