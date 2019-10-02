package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JpoShoppingCartOrderListManager extends GenericManager<JpoShoppingCartOrderList, Long> {
    
	public Pager<JpoShoppingCartOrderList> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	public boolean deleShoppingCartList(Long sclId,Long scId) ;
	public void editStatus(Long scId,String value,String type);
	public void editQty(Long sclId,int qty);
	/**
	 * get product by orderType
	 * @param orderType
	 * @param user 
	 * @return HashMap
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getProductByOrderType(String orderType,JsysUser user,String categoryIds,String type);
	
	/**
	 * Add By WuCF 20160428 新增商品价格&PV&名称
	 * get product by orderType
	 * @param orderType
	 * @param user 
	 * @return HashMap
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getProductByOrderTypeProNew(String orderType,JsysUser user,String categoryIds,String type,
			String minPrice,String maxPrice,String minPv,String maxPv,String productNameStr);
	
	public void editMoAndCheck(Long scId,String type,JpoShoppingCartOrder jpoShoppingCartOrder);
	
	/**
	 * Add By WuCF
	 * 分页展示商品团队类型表：手机端
	 * @param orderType：订单类型
	 * @param user：用户
	 * @param categoryIds：类型集合，用逗号“,”隔开
	 * @param type：类型
	 * @param pagenum：当前页码
	 * @param pageSize：分页单位
	 * @return：返回的HashMap中的List集合只有一个键值对     Key：商品类别编码   Value：此商品类别的满足条件的商品集合
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getProductByOrderTypePage(String orderType,JsysUser user,String categoryIds,String type,int pagenum,int pageSize);
	
	/**
	 * Add By WuCF
	 * 分页展示商品团队类型表：手机端
	 * @param orderType：订单类型
	 * @param user：用户
	 * @param categoryIds：类型集合，用逗号“,”隔开
	 * @param type：类型
	 * @param pagenum：当前页码
	 * @param pageSize：分页单位
	 * @return：返回的HashMap中的List集合只有一个键值对     Key：商品类别编码   Value：此商品类别的满足条件的商品集合
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPage(String orderType,JsysUser user,String categoryIds,String type,int pagenum,int pageSize);

	/**
	  * Add By WuCF 20160425
	  * 获取商品
	  * @param userId：会员编号
	  * @param orderType：订单类型
	  * @param categoryIds：商品类别集合
	  * @param pageNum：分页起始值
	  * @param pageSize：分页结束值
	  * @param minPrice：起始价格
	  * @param maxPrice：截止价格
	  * @param minPv：起始PV
	  * @param maxPv：截止PV
	  * @param productNameStr：名称(模糊查询)
	  * @return
	  */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPage2(String orderType,JsysUser user,String categoryIds,String type,int pagenum,int pageSize,
			Integer minPrice,Integer maxPrice,Integer minPv,Integer maxPv,String productNameStr);

	
	/**
	 * 获得指定查询条件查询的商品类别集合
	 * @param orderType
	 * @param user
	 * @param categoryIds
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getProductCategoryByOrderType(String orderType,JsysUser user,String categoryIds,String type);

	/**
	 * 根据订单类型和团队编号获取推荐商品
	 * @param jsysUser
	 * @param isRecommend
	 * @param orderType
	 * @return
	 */
	public List<JpmProductSaleTeamType> getRecommendProductList(JsysUser jsysUser,String isRecommend, String orderType);

}