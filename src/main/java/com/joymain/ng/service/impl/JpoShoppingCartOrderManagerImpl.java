package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.JpoShoppingCartOrderDao;
import com.joymain.ng.dao.JpoShoppingCartOrderListDao;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.data.CommonRecord;

@Service("jpoShoppingCartOrderManager")
@WebService(serviceName = "JpoShoppingCartOrderService", endpointInterface = "com.joymain.ng.service.JpoShoppingCartOrderManager")
public class JpoShoppingCartOrderManagerImpl extends GenericManagerImpl<JpoShoppingCartOrder, Long> implements JpoShoppingCartOrderManager {
	

    JpoShoppingCartOrderDao jpoShoppingCartOrderDao;
    @Autowired
    JpoShoppingCartOrderListDao jpoShoppingCartOrderListDao; 
    @Autowired
    JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
    JpmProductSaleNewManager jpmProductSaleNewManager;
    @Autowired
    JsysUserManager jsysUserManager;
    @Autowired
    JmiTeamManager jmiTeamManager;
	@Autowired
	PdSendInfoManager pdSendInfoManager;

    @Autowired
    public JpoShoppingCartOrderManagerImpl(JpoShoppingCartOrderDao jpoShoppingCartOrderDao) {
        super(jpoShoppingCartOrderDao);
        this.jpoShoppingCartOrderDao = jpoShoppingCartOrderDao;
      
    }
	
	public Pager<JpoShoppingCartOrder> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return jpoShoppingCartOrderDao.getPager(JpoShoppingCartOrder.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public int underwearReminder(String unino){
		//商品编码
		JpmProductSaleNew jpmProductSaleNew = jpmProductSaleNewManager.get(new Long(unino));
		String productNo = jpmProductSaleNew.getProductNo();
		if(GlobalVar.underwearReminder.contains(productNo)){
			return 1;
		}
		if(GlobalVar.cookingOilReminder.contains(productNo)){
			return 2;
		}
		return 0;
		
	}
	/**
	 * 检查购买商品数量 订单类型 为42的 只能购买套餐数量为1
	 * @param jpoShoppingCart
	 * @return
	 */
	public boolean checkCartProduct(JpoShoppingCart jpoShoppingCart){
		//根据会员编号获取该会员身份证
		String paperNumber = jpoMemberOrderDao.getPaperNumber(jpoShoppingCart.getUserCode());
		//根据身份证号码 查询所有的会员
		List<JmiMember> jmimemberList = jpoMemberOrderDao.getJmimemberList(paperNumber);
		//判断该身份证下的会员是否已经购买过398会员首单了 
		for (JmiMember jmiMember : jmimemberList) {
			String userCode=jmiMember.getUserCode();
			Map map=new HashMap<>();
			map.put("userCode", userCode);
			map.put("companyCode", "CN");
			map.put("orderType", "42");
			List<JpoMemberOrder> order=jpoMemberOrderDao.getOrderByParam(map);
			if(order!=null&&order.size()>0){
				return false;
			}
			
		}
		if(jpoShoppingCart.getQty()>1){
			return false;
		}
		JpoShoppingCartOrder shoppingCartOrder = jpoShoppingCartOrderDao.
				getJpoShoppingCartOrder(jpoShoppingCart);
		if(shoppingCartOrder!=null){
			Set<JpoShoppingCartOrderList> jpoShoppingCartOrderList = shoppingCartOrder.getJpoShoppingCartOrderList();
			//只能买一个商品
			if(jpoShoppingCartOrderList!=null&&jpoShoppingCartOrderList.size()>0){
				return false;
			}
			//商品的数量只能为1
			for (JpoShoppingCartOrderList cartList : jpoShoppingCartOrderList) {
				if(cartList.getJpmProductSaleTeamType().getPttId().equals(jpoShoppingCart.getProductId())){
					
					if(cartList.getQty()>0){
						return false;
					}
				}
			}
		}
		return true;
	}

	public synchronized String addProductToShoppingCart(JpoShoppingCart jpoShoppingCart,String token) {
		try{
			if(jpoShoppingCart.getOrderType().equals("42")){
				boolean isPass = this.checkCartProduct(jpoShoppingCart);
				if(!isPass){
					return "0:该商品只能购买一个且同一个身份证下的会员只能订购一次";
				}
			}
			JpoShoppingCartOrder shoppingCartOrder = jpoShoppingCartOrderDao.
					getJpoShoppingCartOrder(jpoShoppingCart);
			
			//根据主键id获取商品
			JpmProductSaleTeamType  proSN=jpmProductSaleNewManager.
					getJpmProductSaleTeamType(jpoShoppingCart.getProductId().toString());
			if(proSN==null) return "0:商品不存在";
			
			boolean  validUser=false;//没有购买过事业锦囊
			boolean  shoppingCartUser=false;//购物车中没有强制购买过指定产品
			boolean teamBoolean=false;// 需要强制绑定事业锦囊的团队 
			int qty=0;
			boolean validQty=false;
			
			//查询会员身份证号码
			JsysUser user = new JsysUser();
			if(StringUtils.isNotBlank(token)){
				user = jsysUserManager.getUserByToken(jpoShoppingCart.getUserCode(), token);
			 	Object object = jsysUserManager.getAuthErrorCode(user, "String");
			 	if(null != object){
			 		log.error("user login error!");
			 		return (String)object;
			 	}
			} else{
				user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
			log.info("userCode is:"+user.getUserCode());
			// 判断购物车中指定的订单团队，是否已经强制绑定过事业锦囊
			 List shoppingCartList=null;
			if("1".equals(jpoShoppingCart.getOrderType()) || "6".equals(jpoShoppingCart.getOrderType()) || "11".equals(jpoShoppingCart.getOrderType())|| "12".equals(jpoShoppingCart.getOrderType()))
			{
				   shoppingCartList=jpoShoppingCartOrderDao.getJpoShoppingCartBindingProduct(jpoShoppingCart);
					if(shoppingCartList!=null && shoppingCartList.size()>0)
					{
						shoppingCartUser=true;//购物车中强制绑定了指定的产品
						
					}
					if("1".equals(jpoShoppingCart.getOrderType()))
					{
			            //已经生成的订单中获取是否强制绑定过事业锦囊,判断是否同一身份证已经购买过事业锦囊
					    List jpoMemberList=jpoMemberOrderDao.getJpoMemberMark(user.getJmiMember().getPapernumber(), "P08520100101CN0",jpoShoppingCart.getOrderType());
					    if(jpoMemberList!=null && jpoMemberList.size()>0)
					    {
					    	validUser=true;//购买过事业锦囊
					    	shoppingCartUser=true;//购物车中强制绑定了指定的产品
					    }
					
						 //需要参与事业锦囊的团队
						 Map<String,Object> map=this.getTeam();
						 teamBoolean=BooleanUtils.toBoolean((Boolean)map.get(jpoShoppingCart.getTeamType()));
					 }
			
		   }
			if(shoppingCartOrder!=null){ //修改产品
				JpoShoppingCartOrderList  shoppingCartOrderList=new JpoShoppingCartOrderList();
				JpoShoppingCartOrderList  shoppingCartOrderList2=new JpoShoppingCartOrderList();
				JpoShoppingCartOrderList	scl=new JpoShoppingCartOrderList();
				Set shoppingCartOrderListSet = new HashSet(0);
				
				Iterator<JpoShoppingCartOrderList> iterator=shoppingCartOrder.getJpoShoppingCartOrderList().iterator();
				while(iterator.hasNext()){
						scl=iterator.next();
					if(jpoShoppingCart.getProductId().equals(scl.getJpmProductSaleTeamType().getPttId()))
					{
						qty=scl.getQty(); //如果是购买了同一个产品，则数量进行累计
						validQty=true;
						break;
					}
				}
				if(validQty)//购买的是同一个商品
				{
					if(Constants.MOVIE_PRONO.equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo()) || 
							Constants.MOVIE_PRONO2.equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo()) ||
							"P21030100101CN0".equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo()) ||
							"P21040100101CN0".equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo())){
						return "0:此商品只能订购一个";
					}
					scl.setJpmProductSaleTeamType(scl.getJpmProductSaleTeamType());
					if(jpoShoppingCart.getQty()+qty >99999){
						return "0:商品超过最大订购数量";
					}else{
						scl.setQty((jpoShoppingCart.getQty()+qty));
					}
					
			    	scl.setProductStatus("0");
					scl.setJpoShoppingCartOrder(shoppingCartOrder);
					shoppingCartOrderListSet.add(scl);
					shoppingCartOrder.setJpoShoppingCartOrderList(shoppingCartOrderListSet);
					
				}else
				{
					   //强制购买商品
					Map<String,Object> mapSet=new HashMap<String,Object>();
					mapSet=this.getshoppingCart(teamBoolean, validUser, 
							shoppingCartUser, jpoShoppingCart,  shoppingCartOrderList2, 
							shoppingCartOrderListSet, shoppingCartOrder);	       
					Set<String> set = mapSet.keySet();// 取得里面的key的集合
					for (String key : set) {// 遍历set去出里面的的Key
		                if("0".equals(key))//代表有错误
		                {
		                	return "0:"+(String)mapSet.get(key);//返回错误信息
		                	
		                }else//代表成功
		                {
		                	shoppingCartOrderListSet=(Set)mapSet.get(key);
		                }
					}
					
			    	shoppingCartOrderList.setJpmProductSaleTeamType(proSN);
			    	shoppingCartOrderList.setQty((jpoShoppingCart.getQty()));
					shoppingCartOrderList.setProductStatus("0");
					shoppingCartOrderList.setJpoShoppingCartOrder(shoppingCartOrder);
					shoppingCartOrderListSet.add(shoppingCartOrderList);
					shoppingCartOrder.setJpoShoppingCartOrderList(shoppingCartOrderListSet);
				}
		
			}else//新增产品
			{
				//电影票
				if((Constants.MOVIE_PRONO.equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo()) && jpoShoppingCart.getQty()>1) ||
						(Constants.MOVIE_PRONO2.equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo()) && jpoShoppingCart.getQty()>1) ||
						("P21030100101CN0".equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo()) && jpoShoppingCart.getQty()>1) ||
						("P21040100101CN0".equalsIgnoreCase(proSN.getJpmProductSaleNew().getProductNo()) && jpoShoppingCart.getQty()>1)){
					return "0:此商品只能订购一个";
				}
				shoppingCartOrder=new JpoShoppingCartOrder();
				JsysUser sysUser=new JsysUser();
				JpoShoppingCartOrderList  shoppingCartOrderList=new JpoShoppingCartOrderList();
				JpoShoppingCartOrderList  shoppingCartOrderList2=new JpoShoppingCartOrderList();
				Set shoppingCartOrderListSet = new HashSet(0);
				Map<String,Object> mapSet=new HashMap<String,Object>();
				
				shoppingCartOrder.setIsShipments("0");
				shoppingCartOrder.setIsCheck("1");//默认为购买
				//0是网页进行添加的商品,1是手机
				shoppingCartOrder.setIsMobile(jpoShoppingCart.getIsMobile());
				shoppingCartOrder.setOrderType(jpoShoppingCart.getOrderType());
				shoppingCartOrder.setTeamType(jpoShoppingCart.getTeamType());
				shoppingCartOrder.setCompanyCode(jpoShoppingCart.getCompanyCode());
				
				sysUser.setUserCode(jpoShoppingCart.getUserCode());
				shoppingCartOrder.setSysUser(sysUser);
		         //强制购买商品
				mapSet=this.getshoppingCart(teamBoolean, validUser, shoppingCartUser, jpoShoppingCart,  shoppingCartOrderList2, shoppingCartOrderListSet, shoppingCartOrder);	       
				Set<String> set = mapSet.keySet();// 取得里面的key的集合

				for (String key : set) {// 遍历set去出里面的的Key
	                if("0".equals(key))
	                {
	                	return "0:"+(String)mapSet.get(key);//返回错误信息
	                	
	                }else
	                {
	                	shoppingCartOrderListSet=(Set)mapSet.get(key);
	                	
	                }
				}
				shoppingCartOrderList.setJpmProductSaleTeamType(proSN);
				shoppingCartOrderList.setQty(jpoShoppingCart.getQty());
				shoppingCartOrderList.setJpoShoppingCartOrder(shoppingCartOrder);
				shoppingCartOrderList.setProductStatus("0");
				shoppingCartOrderListSet.add(shoppingCartOrderList);
				shoppingCartOrder.setJpoShoppingCartOrderList(shoppingCartOrderListSet);	
			}

			// 限制2个套餐，数量限制分别是5000和8000
			String productNo = proSN.getJpmProductSaleNew().getProductNo();
			if ("P21811700201CN0".equalsIgnoreCase(productNo)) {
				CommonRecord crm = new CommonRecord();
				crm.setValue("po", "P21811700201CN0");
				int num = pdSendInfoManager.getStatisticProductSale2(crm);
				if (num > 5000) {
					return "0:此商品已经售罄";
				}
			}
			if ("P21811700101CN0".equalsIgnoreCase(productNo)) {
				CommonRecord crm = new CommonRecord();
				crm.setValue("po", "P21811700101CN0");
				int num = pdSendInfoManager.getStatisticProductSale2(crm);
				if (num > 8000) {
					return "0:此商品已经售罄";
				}
			}
		
			jpoShoppingCartOrderDao.save(shoppingCartOrder);
			return  "1:"+jpoShoppingCart.getQty();//代表成功,返回新增的数量
		}catch(Exception e){
			log.error("",e);
		}
		return "0:error";//返回错误信息
			
	}
	//需要参与强制绑定事业锦囊的团队
	private Map<String,Object> getTeam()
	{
		
		Map<String,Object> map=jpmProductSaleNewManager.getJmiMemberTeamMap("0");	
		return map;
	}
	
	private Map<String,Object>  getshoppingCart(boolean teamBoolean,
			boolean validUser,boolean shoppingCartUser,
			JpoShoppingCart jpoShoppingCart,JpoShoppingCartOrderList  
			shoppingCartOrderList2,Set shoppingCartOrderListSet,
			JpoShoppingCartOrder shoppingCartOrder) {
		
		String temCode = jmiTeamManager.teamStr(
				shoppingCartOrder.getSysUser());
		log.info("temCode ="+temCode);
		
		  //TODO 强制绑定商品
		  if(!shoppingCartUser) {
	    	   String productNO="";
	    	   String orderType="";
	    	   switch(Integer.parseInt(jpoShoppingCart.getOrderType())){
	    	   case  1://会员首单
	    		   productNO="P08520100101CN0";
	    		   orderType="1";
	    		 break;
	    	   case 6://一级店铺首单
	    		   productNO="N01200200101CN0";
	    		   orderType="6";
	    		   //雅阁丹团队CN12365064
//	    		   if(temCode.equalsIgnoreCase("CN12365064"))
//	    			   productNO = "N01200200201CN0";
	    		   
	    		   break;
	    	   case 11://二级店铺首单
	    		   productNO="N01190100101CN0";
	    		   orderType="11";
	    		   
//	    		   if(temCode.equalsIgnoreCase("CN12365064")) //ygd
//	    			   productNO = "N01190100201CN0";
	    		   
	    		   
	    		   break;
	    	   case 12://二级店铺升级单
	    		   productNO="N02330200101CN0";
	    		   orderType="12";
	    		   
//	    		   if(temCode.equalsIgnoreCase("CN12365064"))
//	    			   productNO = "N02330200201CN0";
	    		   
	    		   break;
	    	   }
	    	 if(StringUtils.isNotEmpty(orderType))
	    	    {
	    	      if(StringUtils.isNotEmpty(productNO))
	    	      {
	    	    	  
		    	      JpmProductSaleTeamType  proSN2=jpmProductSaleNewManager.getJpmProductSaleTeamType(productNO,jpoShoppingCart.getTeamType(),orderType,jpoShoppingCart.getCompanyCode());
		    	      if(("1".equals(jpoShoppingCart.getOrderType()) && !teamBoolean) || ("1".equals(jpoShoppingCart.getOrderType()) && validUser))
		  			  {
		  			    // return shoppingCartOrderListSet;	
		  			     Map<String,Object> map=new HashMap<String, Object>();
		  			     map.put("1", shoppingCartOrderListSet);
		  			     return  map;
		  	          }
		    	      
		    	      if(proSN2!=null)
		    	      {
                        //会员首购单
				    	   if(!validUser)//购物车中强制绑定事业锦囊或是保证金产品
				  	       {
				    		   if("1".equals(jpoShoppingCart.getOrderType()) && teamBoolean)
				    		   {
				  	    		   shoppingCartOrderList2.setJpmProductSaleTeamType(proSN2);
				  	    		   shoppingCartOrderList2.setQty(1);
				  	    		   shoppingCartOrderList2.setProductStatus("1");
				  	    		   shoppingCartOrderList2.setJpoShoppingCartOrder(shoppingCartOrder);
				  	    		   shoppingCartOrderListSet.add(shoppingCartOrderList2);
				    		   }
				    		   else if("6".equals(jpoShoppingCart.getOrderType()))//一级店铺首单
					          {
					    	 
				    		   shoppingCartOrderList2.setJpmProductSaleTeamType(proSN2);
				    		   shoppingCartOrderList2.setQty(1);
				    		   shoppingCartOrderList2.setProductStatus("1");
				    		   shoppingCartOrderList2.setJpoShoppingCartOrder(shoppingCartOrder);
				    		   shoppingCartOrderListSet.add(shoppingCartOrderList2);
					         }else if("11".equals(jpoShoppingCart.getOrderType()))
					         {
					    	 
				    		   shoppingCartOrderList2.setJpmProductSaleTeamType(proSN2);
				    		   shoppingCartOrderList2.setQty(1);
				    		   shoppingCartOrderList2.setProductStatus("1");
				    		   shoppingCartOrderList2.setJpoShoppingCartOrder(shoppingCartOrder);
				    		   shoppingCartOrderListSet.add(shoppingCartOrderList2);
					        }else if("12".equals(jpoShoppingCart.getOrderType()))
					       {
					    	  
				    		   shoppingCartOrderList2.setJpmProductSaleTeamType(proSN2);
				    		   shoppingCartOrderList2.setQty(1);
				    		   shoppingCartOrderList2.setProductStatus("1");
				    		   shoppingCartOrderList2.setJpoShoppingCartOrder(shoppingCartOrder);
				    		   shoppingCartOrderListSet.add(shoppingCartOrderList2);
					       }
				  	     }
		    	      }else
		    	      {
		    	    	  Map<String,Object> map=new HashMap<String, Object>();
			  			  map.put("0", "订单类型指定要购买的商品添加失败");
			  			  return  map;
		    	    	
		    	    	   
		    	      }
	    	   
	    	   }else
	    	   {
	    		   Map<String,Object> map=new HashMap<String, Object>();
	  			   map.put("0", "此订单类型的商品编号为空");
	  			   return  map;
	    		  
	    		 
	    	   }
	     }
			    	   
   	   }
		     Map<String,Object> map=new HashMap<String, Object>();
		     map.put("1", shoppingCartOrderListSet);
		     return  map;
		 
	}

	// 获取会员所有购物车中的总数据
	public int getShoppinigCartSum(JpoShoppingCart JpoShoppingCart) {//查询购物车中的总数量
		return jpoShoppingCartOrderDao.getShoppinigCartSum(JpoShoppingCart);
		
	}

	public List<JpoShoppingCartOrder> getJpoScOrderList(JpoShoppingCart jpoShoppingCart) {
		List<JpoShoppingCartOrder> jscOrderList=jpoShoppingCartOrderDao.getJpoScOrderList(jpoShoppingCart);
		return jscOrderList;
	}
	
	public JpoShoppingCartOrder getJpoShoppingCartOrder(JpoShoppingCart JpoShoppingCart)
	{
		return jpoShoppingCartOrderDao.getJpoShoppingCartOrder(JpoShoppingCart);
	}
	
	//手机端的 购物车
	@Override
	public List<JpoShoppingCartOrder> getMobileJpoShoppingCartOrder(JpoShoppingCart JpoShoppingCart){
		
		return jpoShoppingCartOrderDao.getMoilbeJpoShoppingCartOrder(JpoShoppingCart);
		
	}
	//手机端的清掉与当前状态不一致的购物车中的商品
	@Override
	public void deleMobileJpoShoppingCartOrder(long sc_id) {
		jpoShoppingCartOrderDao.deleMobileJpoShoppingCartOrder(sc_id);
	}

}