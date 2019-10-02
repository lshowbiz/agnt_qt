package com.joymain.ng.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.JpoShoppingCartOrderDao;
import com.joymain.ng.dao.JpoShoppingCartOrderListDao;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jpoShoppingCartOrderListManager")
@WebService(serviceName = "JpoShoppingCartOrderListService", endpointInterface = "com.joymain.ng.service.JpoShoppingCartOrderListManager")
public class JpoShoppingCartOrderListManagerImpl extends GenericManagerImpl<JpoShoppingCartOrderList, Long> implements JpoShoppingCartOrderListManager {
	@Autowired
    public JpoShoppingCartOrderListDao jpoShoppingCartOrderListDao;
	@Autowired
	public JpoShoppingCartOrderDao  jpoShoppingCartOrderDao;
	
	@Autowired
	private JpmProductSaleNewManager jpmProductSaleNewManager;
	@Autowired
	private JmiTeamManager jmiTeamManager;
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;
	@Autowired
	private JpmProductSaleNewDao jpmProductSaleNewDao;
	
	
	public String teamChar="";
	public Pager<JpoShoppingCartOrderList> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return jpoShoppingCartOrderListDao.getPager(JpoShoppingCartOrderList.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public boolean deleShoppingCartList(Long sclId,Long scId) {
		try
		{
			JpoShoppingCartOrderList scl=jpoShoppingCartOrderListDao.get(sclId);
			List sc=jpoShoppingCartOrderListDao.getsclList(scId);
			boolean validP=false;
		
			JpoShoppingCartOrderList jpoShoppingCartOrderList=null;
			int number=0;
			if("1".equals(scl.getJpoShoppingCartOrder().getOrderType()) || "6".equals(scl.getJpoShoppingCartOrder().getOrderType()) 
			|| "11".equals(scl.getJpoShoppingCartOrder().getOrderType()) || "12".equals(scl.getJpoShoppingCartOrder().getOrderType()))
			{
				for(int i=0;i<sc.size();i++)
				{
					jpoShoppingCartOrderList=(JpoShoppingCartOrderList)sc.get(i);
					if("1".equals(jpoShoppingCartOrderList.getProductStatus()))
					{
						validP=true;//判断是否存在强制绑定的商品
						break;
					}
				}
			
				if(sc.size()==2 && validP)
				{
					number=2;
				}else if("1".equals(scl.getJpoShoppingCartOrder().getOrderType()))
				{
					if(sc.size()==1)
					{
						number=1;
					}
				}
			}else
			{
				if(sc.size()==1)
				{
					number=1;
				}
			}
			
			if(number==1 || number==2){
				
				 jpoShoppingCartOrderDao.remove(scl.getJpoShoppingCartOrder());//如果购物车指定的商品明细不存在，则删除订单
				 
			}else{
				jpoShoppingCartOrderListDao.remove(sclId);
			}
		}catch (Exception e) {
			
			return false;
		}
		return true;
	}
	public void editStatus(Long scId,String value,String type)//修改购物车的发货状态，跟数量
	{
		JpoShoppingCartOrder  sc=jpoShoppingCartOrderDao.get(scId);
		
		
		if("checked".equals(value))
		{
			if("checkBuy".equals(type))
			   {
		           sc.setIsCheck("1");//购物车内的商品生成订单
			   }else 
			   {
					sc.setIsShipments("1");//设置为暂不发货
			   }
		}else
		{
			if("checkBuy".equals(type))
			   {
		           sc.setIsCheck("0");//商品在购物车内，不生成订单
			   }else
			   {
			     sc.setIsShipments("0");//及时发货
			   }
		}
		
		jpoShoppingCartOrderDao.save(sc);
	}
	
	
	//修改购物车的数量
	public void editQty(Long sclId,int qty) 
	{
		 JpoShoppingCartOrderList scl=jpoShoppingCartOrderListDao.get(sclId);
		 //顾问首单 商品数量只能买一个
		 if(scl.getJpoShoppingCartOrder().getOrderType().equals("42")){
			 return;
		 }
		 if(validateProNo(scl)){
			 return;
		 } else{
			 scl.setQty(qty);
			 jpoShoppingCartOrderListDao.save(scl);
		 }
		 
	}
	//修改购物中订单来源跟订单状态
	public void editMoAndCheck(Long scId,String type,JpoShoppingCartOrder jpoShoppingCartOrder)
	{
		if("mobile".equals(type))
		{
			JpoShoppingCartOrder  scMobile=jpoShoppingCartOrderDao.get(scId);
			scMobile.setIsCheck("1");
			scMobile.setIsMobile("1");
			scMobile.setIsShipments(jpoShoppingCartOrder.getIsShipments());//暂不发货标识   0表示正常发货  1表示暂不发货
			jpoShoppingCartOrderDao.save(scMobile);
		}else
		{
			jpoShoppingCartOrder.setIsMobile("0");
			jpoShoppingCartOrderDao.save(jpoShoppingCartOrder);
			
		}	
	}

	@Override
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getProductByOrderType(
			String orderType, JsysUser user, String categoryIds,String type) {
		
		String teamChar=jmiTeamManager.teamStr(user);
		/* if(GlobalVar.MEMBER.equals(user.getUserType())){
			 	Iterator<String> ite = GlobalVar.teamMap.keySet().iterator();
	        	while(ite.hasNext()){
	        		JmiMember lcMiMember =jmiMemberManager.get(ite.next());
	        		if(lcMiMember!=null &&  lcMiMember.getJmiRecommendRef()!=null){
		        		String teamIndex=lcMiMember.getJmiRecommendRef().getTreeIndex();
		        		String loginTreeIndex=user.getJmiMember().getJmiRecommendRef().getTreeIndex();
		        		String rmemberIndexTmp = StringUtil.getLeft(loginTreeIndex, teamIndex.length());
		        		if(loginTreeIndex.length() >= teamIndex.length() && rmemberIndexTmp.equals(teamIndex)){
		        			teamChar = GlobalVar.teamMap.get(ite.next());
		        			break;
		        		}
		        	 }
	        	}
	     }*/
//		System.out.print("team code is:"+teamChar);
		log.info("team code is:"+teamChar);
		return jpmProductSaleNewManager.getJpmProductSaleTeamTypeMap(
				null, teamChar, orderType, user.getCompanyCode(), categoryIds,null,type);
	}
	
	/**
	 * Add By WuCF 20160428 新增商品价格&PV&名称
	 * get product by orderType
	 * @param orderType
	 * @param user 
	 * @return HashMap
	 */
	@Override
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getProductByOrderTypeProNew(
			String orderType, JsysUser user, String categoryIds,String type,
			String minPrice,String maxPrice,String minPv,String maxPv,String productNameStr) {
		
		String teamChar=jmiTeamManager.teamStr(user);
		
		log.info("team code is:"+teamChar);
		return jpmProductSaleNewManager.getJpmProductSaleTeamTypeMapProNew(
				null, teamChar, orderType, user.getCompanyCode(), categoryIds,null,type,
				minPrice,maxPrice,minPv,maxPv,productNameStr);
	}


	public String getTeamChar() {
		return teamChar;
	}

	/**
	 * Add By WuCF
	 * 分页展示商品团队类型数据：手机端
	 * @param orderType：订单类型
	 * @param user：用户
	 * @param categoryIds：类型集合，用逗号“,”隔开
	 * @param type：类型
	 * @param pagenum：当前页码
	 * @param pageSize：分页单位
	 * @return：返回的HashMap中的List集合只有一个键值对     Key：商品类别编码   Value：此商品类别的满足条件的商品集合
	 */
	@Override
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getProductByOrderTypePage(
			String orderType, JsysUser user, String categoryIds, String type,
			int pagenum, int pageSize) {
		return jpmProductSaleNewManager.getJpmProductSaleTeamTypeMapPage(
				null, teamChar, orderType, user.getCompanyCode(), categoryIds,null,type,pagenum,pageSize);
	}
	
	/**
	 * Add By WuCF
	 * 分页展示商品团队类型数据：手机端
	 * @param orderType：订单类型
	 * @param user：用户
	 * @param categoryIds：类型集合，用逗号“,”隔开
	 * @param type：类型
	 * @param pagenum：当前页码
	 * @param pageSize：分页单位
	 * @return：返回的HashMap中的List集合只有一个键值对     Key：商品类别编码   Value：此商品类别的满足条件的商品集合
	 */
	@Override
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPage(
			String orderType, JsysUser user, String categoryIds, String type,
			int pagenum, int pageSize) {
		
		List<JpmProductSaleTeamType> teamTypeList = new ArrayList<JpmProductSaleTeamType>();
		String teamChar=jmiTeamManager.teamStr(user);
		if("1".equals(orderType)){
			//获取产品
			List jpoMemberList=jpoMemberOrderDao.getJpoMemberMark(
					user.getJmiMember().getPapernumber(), "P08520100101CN0",orderType);
			
			log.info("jpoMemberList size is:"+jpoMemberList.size());
	    	if(jpoMemberList!=null && jpoMemberList.size()>0){
	    		//购买过事业锦囊,展示辅销品跟事业锦囊
	    		teamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListPage(
	    				null, teamChar, orderType, user.getCompanyCode(), 
	    				categoryIds,null,"1",pagenum,pageSize);
	    		
	    		for(JpmProductSaleTeamType teamType: teamTypeList){
	    			teamType.setIsHidden("0");
	    		}
	    	}else{
	    		//没有购买事业锦囊,不展示事业锦囊跟辅销品
				teamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListPage(
	    				null, teamChar, orderType, user.getCompanyCode(), 
	    				categoryIds,null,"0",pagenum,pageSize);
	    	}
		}else{
			teamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListPage(
    				null, teamChar, orderType, user.getCompanyCode(), 
    				categoryIds,null,type,pagenum,pageSize);
		}
		
		return teamTypeList;
	}
	
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
	@Override
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPage2(
			String orderType, JsysUser user, String categoryIds, String type,
			int pagenum, int pageSize,Integer minPrice,Integer maxPrice,Integer minPv,Integer maxPv,String productNameStr) {
		
		List<JpmProductSaleTeamType> teamTypeList = new ArrayList<JpmProductSaleTeamType>();
		String teamChar=jmiTeamManager.teamStr(user);
		if("1".equals(orderType)){
			//获取产品
			List jpoMemberList=jpoMemberOrderDao.getJpoMemberMark(
					user.getJmiMember().getPapernumber(), "P08520100101CN0",orderType);
			
			log.info("jpoMemberList size is:"+jpoMemberList.size());
	    	if(jpoMemberList!=null && jpoMemberList.size()>0){
	    		//购买过事业锦囊,展示辅销品跟事业锦囊
	    		teamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListPage2(
	    				null, teamChar, orderType, user.getCompanyCode(), 
	    				categoryIds,null,"1",pagenum,pageSize,
	    				minPrice,maxPrice,minPv,maxPv,productNameStr);
	    		
	    		for(JpmProductSaleTeamType teamType: teamTypeList){
	    			teamType.setIsHidden("0");
	    		}
	    	}else{
	    		//没有购买事业锦囊,不展示事业锦囊跟辅销品
				teamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListPage2(
	    				null, teamChar, orderType, user.getCompanyCode(), 
	    				categoryIds,null,"0",pagenum,pageSize,
	    				minPrice,maxPrice,minPv,maxPv,productNameStr);
	    	}
		}else{
			teamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListPage2(
    				null, teamChar, orderType, user.getCompanyCode(), 
    				categoryIds,null,type,pagenum,pageSize,
    				minPrice,maxPrice,minPv,maxPv,productNameStr);
		}
		
		return teamTypeList;
	}
	
	/**
	 * 根据订单类型和团队编号获取推荐商品  add by hdg 2016-09-14
	 */
	@Override
	public List<JpmProductSaleTeamType> getRecommendProductList(JsysUser jsysUser, String isRecommend, String orderType) {
		
		String teamType=jmiTeamManager.teamStr(jsysUser);//获取团队编号
		List<JpmProductSaleTeamType> result = new ArrayList<JpmProductSaleTeamType>();
		
		//先查询有没有推荐的商品
		List<JpmProductSaleTeamType> recommendProductList = 
				jpmProductSaleNewDao.getRecommendProductList(teamType, isRecommend, orderType,null);
		
		if(recommendProductList != null && recommendProductList.size() >= 6) {
			recommendProductList = recommendProductList.subList(0, 6);
			return recommendProductList;
		}
		//查询不够5条记录
		if(recommendProductList != null) {
			result.addAll(recommendProductList);
			if(recommendProductList.size() < 6) {
				int rownum = 6-(recommendProductList.size());
				recommendProductList = 
						jpmProductSaleNewDao.getRecommendProductList(teamType, "", orderType,rownum);
				result.addAll(recommendProductList);
			}
		} else {
			//如果查询到0条推荐商品，那就查询不是推荐的
			result = jpmProductSaleNewDao.getRecommendProductList(teamType, "", orderType,6);
		}
		return result;
	}
	
	/**
	 * 获得指定查询条件查询的商品类别集合
	 * @param orderType
	 * @param user
	 * @param categoryIds
	 * @param type
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getProductCategoryByOrderType(
			String orderType, JsysUser user, String categoryIds,String type) {
		
		String teamChar=jmiTeamManager.teamStr(user);
		System.out.print("team code is:"+teamChar);
		log.info("team code is:"+teamChar);
		return jpmProductSaleNewManager.getProductCategoryByOrderType(
				null, teamChar, orderType, user.getCompanyCode(), categoryIds,null,type);
	}
	/**
	 * 电影票验证
	 * @param carItem
	 * @return
	 */
	public boolean validateProNo(JpoShoppingCartOrderList carItem){
		String proNo = carItem.getJpmProductSaleTeamType().
				 getJpmProductSaleNew().getProductNo();
		log.info("carItem ProductNo is:"+proNo);
		if(proNo.equalsIgnoreCase(Constants.MOVIE_PRONO) || 
		   proNo.equalsIgnoreCase(Constants.MOVIE_PRONO2) ||
		   proNo.equalsIgnoreCase("P21030100101CN0") ||
		   proNo.equalsIgnoreCase("P21040100101CN0"))
			return true;
		else
			return false;
	}
}