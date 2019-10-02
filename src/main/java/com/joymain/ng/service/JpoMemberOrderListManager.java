package com.joymain.ng.service;

import java.util.Collection;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpoMemberOrderListManager extends GenericManager<JpoMemberOrderList, Long> {
    
	public Pager<JpoMemberOrderList> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * 根据订单明细流水号查询商品重量等信息　 
	 * @param map
	 * @return
	 */
	public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String,Long> map);
	/**
	 * 端午促销
	 * @param proNo
	 * @return int
	 */
	public Integer getSumQtyByProNo();
	/**
	 * 根据商品编号统计购买数量
	 * @param proNo
	 * @return int
	 */
	public Integer getSumQtyByProNo(String proNo);
	public JpoMemberOrderList bingProduct(String pttId,Integer qty);
	
	 /**
     * 库存清货数量限制
     * @param proNo
     * @param statetime
     * @param endtime
     * @return
     */
    public Integer getProSumByProNo(String proNo,String statetime,String endtime);
    
    /**
     * 判断一张订单中的商品是否是不可换商品
     * @param 商品明细
     * @return 是否为可换货商品
     */
	boolean isNotExchangeProduct(JpoMemberOrderList jpoMemberOrderList);
}