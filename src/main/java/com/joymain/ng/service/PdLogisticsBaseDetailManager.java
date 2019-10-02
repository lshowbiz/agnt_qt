package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PdLogisticsBaseDetail;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface PdLogisticsBaseDetailManager extends GenericManager<PdLogisticsBaseDetail, Long> {
    
	public Pager<PdLogisticsBaseDetail> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	/**
	 * 查询套餐商品在这个物流跟踪单号下的子商品
	 * @author gw 2015-05-04
	 * @param numId PdLogisticsBaseNum所关联表的主键
	 * @param productNo
	 * @return list
	 */
	public List<PdLogisticsBaseDetail> getlistpdLogisticsBaseDetail(Long numId,String productNo);

	/**
	 * 套餐物流信息展示-根据商品编码获取商品名称
	 * @author gw 2015-05-08
	 * @param productNo 商品编码
	 * @return string
	 */
	public String getProductName(String productNo);
	
}