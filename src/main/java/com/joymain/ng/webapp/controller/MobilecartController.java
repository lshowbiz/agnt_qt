package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.dao.JmiMemberTeamDao;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.model.InwViewpeople;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiMemberTeam;
import com.joymain.ng.model.JpmProductSaleImage;
import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.InwSuggestionManager;
import com.joymain.ng.service.InwViewpeopleManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("mobileCart")
public class MobilecartController extends BaseOrderController{
	private final Log log = LogFactory.getLog(MobilecartController.class);
	 @Autowired
	 private JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager;
	 @Autowired
	 private JpmProductSaleNewManager jpmProductSaleNewManager;
	 @Autowired
	 private JsysUserManager jsysUserManager;
	 @Autowired
	 private JpoShoppingCartOrderManager jpoShoppingCartOrderManager; 
	 @Autowired
	 private JpoMemberOrderDao jpoMemberOrderDao;
	 @Autowired
	 private JpmProductSaleNewDao jpmProductSaleNewDao;
	 @Autowired
	 private JsysUserRoleManager jsysUserRoleManager;
	 @Autowired
	 private JsysRoleManager jsysRoleManager;
    @Autowired
    private JmiMemberDao jmiMemberDao;   
    @Autowired
    private JmiMemberTeamDao jmiMemberTeamDao;
    @Autowired
    private JmiTeamManager jmiTeamManager;

	 private InwSuggestionManager inwSuggestionManager;
	 private InwViewpeopleManager inwViewpeopleManager;
	 
	 
	 //获取团队系列 
	 @RequestMapping(value="api/getProductListType")
	 @ResponseBody
	 public List getProductListType(String userId, String token,String teamCode,String orderType){
		 JsysUser user = jsysUserManager.getUserByToken(userId, token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "List");
 		 if(null!=object){
 			return (List)object;
 		  }
 		 log.info("teamCode is:["+teamCode+"] and orderType is:["+orderType+"] " +
 		 		"and user is:"+user.getUserCode());
 		 
 		//注释By WuCF 20140126 
// 		List<Map<String, Object>> list= jpmProductSaleNewManager.
// 				getProductCategoryByOrderType(null, teamCode, 
// 						orderType,user.getCompanyCode(),null,null,null);

 		//Modify By WuCF 20140126  商品类别展示  修改
 		//获取系列类型
    	String categoryIds = "";
    	List<Map<String, Object>> list= null;
    	if("1".equals(orderType))
    	{
    		List jpoMemberList=jpoMemberOrderDao.getJpoMemberMark(user.getJmiMember().getPapernumber(), "P08520100101CN0",orderType);
	    	if(jpoMemberList!=null && jpoMemberList.size()>0)
	    	//获取产品
	    	{
	    		//购买过事业锦囊,展示辅销品跟事业锦囊
	    		list = jpmProductSaleNewManager.getProductCategoryByOrderType(null, teamCode, 
						orderType,user.getCompanyCode(),null,null,"1");
	    	}
	    	else
	    	{
	    		//没有购买事业锦囊,不展示事业锦囊跟辅销品
	    		list = jpmProductSaleNewManager.getProductCategoryByOrderType(null, teamCode, 
						orderType,user.getCompanyCode(),null,null,"0");
	    	}
    	}else
    	{
    		list = jpmProductSaleNewManager.getProductCategoryByOrderType(null, teamCode, 
					orderType,user.getCompanyCode(),null,null,null);
    	}
 		
		return list;
	 }
	 
	 //获取团队系列 商品
	 @RequestMapping(value="api/getProductListNew")
	 @ResponseBody
	 public List getProductByOrderTypeNew(String userId, String token,String orderType,String categoryIds,int  pageNum, int pageSize){
		 JsysUser user = jsysUserManager.getUserByToken(userId, token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "List");
 		 if(null!=object){
 			return (List)object;
 		  }
 		 List list=jpoShoppingCartOrderListManager.getJpmProductSaleTeamTypeListPage(orderType, user, categoryIds,null, pageNum, pageSize);
		 return list;
		 
	 }
	 
	 @RequestMapping(value="api/getProductByOrderTypeNewAdd")
	 @ResponseBody
	 public List getProductByOrderTypeNewAdd(String userId, String token,String orderType,String categoryIds,Integer  pageNum, Integer pageSize,
			 Integer minPrice,Integer maxPrice,Integer minPv,Integer maxPv,String productNameStr){
		 System.out.println("======1");
		 JsysUser user = jsysUserManager.getUserByToken(userId, token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "List");
 		 if(null!=object){
 			return (List)object;
 		 }
 		 System.out.println("object:"+object);
 		 List list=jpoShoppingCartOrderListManager.getJpmProductSaleTeamTypeListPage2(orderType, user, categoryIds,null, pageNum, pageSize,
 				minPrice,maxPrice,minPv,maxPv,productNameStr);
		 return list;
		 
	 }
	 
	/**
	 * 获取购物车列表数据
	 * @param 必须传 userCode token 
	 * @param 可选 orderType,isCheck
	 * @return 备注 返回 list数据， 商品中的 productStatus;//0购买，1强制购买，2失效
	 */
	 @RequestMapping(value="api/getCartOrderList")
	 @ResponseBody
	 public List getJpoScOrderList (JpoShoppingCart jpoShoppingCart,String token){
		 // orderType获取系列类型  重消 4   续约单 3    
		 JsysUser user = jsysUserManager.getUserByToken(jpoShoppingCart.getUserCode(), token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "List");
 		 if(null!=object){
 			return (List)object;
 		  }
 		jpoShoppingCart.setCompanyCode(user.getCompanyCode());
		return getCartInfo( jpoShoppingCart, token);
		 
	 }
	 // 购物车商品  
	 public List getCartInfo(JpoShoppingCart jpoShoppingCart,String token){
		 List <Map> listmap=new ArrayList<Map>();
		 List<JpoShoppingCartOrder>  orderList=jpoShoppingCartOrderManager.getJpoScOrderList(jpoShoppingCart);
		 if(orderList!=null){
			 for (JpoShoppingCartOrder order:orderList ){
				 listmap.add(getProductList(order));
			 }
		 }
		 return listmap;
	 }
	 //得到购物商品
	 public Map getProductList(JpoShoppingCartOrder order){
		//购物车  购物类型信息
		 Map cartList=Maps.newHashMap();
		 cartList.put("scId",order.getScId() );
		 cartList.put("orderType",order.getOrderType() );
		 cartList.put("isShipments",order.getIsShipments() );
		 cartList.put("teamType",order.getTeamType() );
		 cartList.put("companyCode",order.getCompanyCode() );
		 cartList.put("isCheck",order.getIsCheck());
		 cartList.put("isMobile",order.getIsMobile());
		 List<Map> proList=new ArrayList<Map>();
		Iterator<JpoShoppingCartOrderList> iter=   order.getJpoShoppingCartOrderList().iterator();
		while(iter.hasNext()){
			//购物车 商品详细
			Map temPcartProduct=Maps.newHashMap();
			JpoShoppingCartOrderList jcartProduct=iter.next();
			temPcartProduct.put("scId",order.getScId() );
			temPcartProduct.put("orderType",order.getOrderType() );
			temPcartProduct.put("sclId",jcartProduct.getSclId());
			temPcartProduct.put("qty",jcartProduct.getQty());
			temPcartProduct.put("productStatus",jcartProduct.getProductStatus());
			JpmProductSaleTeamType jProTeam=jcartProduct.getJpmProductSaleTeamType();
			temPcartProduct.put("pttId",jProTeam.getPttId());
			temPcartProduct.put("price",jProTeam.getPrice());
			temPcartProduct.put("pv",jProTeam.getPv());
			JpmProductSaleNew jPro=jProTeam.getJpmProductSaleNew();
			temPcartProduct.put("uniNo",jPro.getUniNo());
			temPcartProduct.put("productNo",jPro.getProductNo());
			temPcartProduct.put("productDesc",jPro.getProductDesc());
			temPcartProduct.put("productName",jPro.getProductName());
			Iterator<JpmProductSaleImage> jpimage = jPro.getJpmProductSaleImages().iterator();
			List<Map> imageList=new ArrayList<Map>();
			while(jpimage.hasNext()){
				Map jp=Maps.newHashMap();
				JpmProductSaleImage imagep=jpimage.next();
				jp.put("imageId", imagep.getImageId());
				jp.put("imageLink", imagep.getImageLink());
				jp.put("imageType", imagep.getImageOrder());
				jp.put("status", imagep.getStatus());
				imageList.add(jp);
			}
			temPcartProduct.put("imageList",imageList);//图片列表
			proList.add(temPcartProduct);
		}
		cartList.put("cartProduct",proList );
		return cartList;
	 }
	 @RequestMapping(value="api/editCartpty")
	 @ResponseBody
	 public  String editQty( Long sclId,int qty,String userId,String token){
		 String flag="1";
		 JsysUser user = jsysUserManager.getUserByToken(userId, token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "String");
 		 if(null!=object){
 			return (String)object;
 		  }
		 if(log.isDebugEnabled())
		 log.debug(" api/editCartpty usercode is:"+user.getUserCode());
		 try{
			 jpoShoppingCartOrderListManager.editQty( sclId, qty);
		 }catch(Exception e){
			 flag= "0";
		 }
		 return flag;
	 }
	 @RequestMapping(value="api/deleCartproduct")
	 @ResponseBody
	 public  String delecartProduct( Long sclId,Long scId,String userId,String token){
		 String flag="1";
		 JsysUser user = jsysUserManager.getUserByToken(userId, token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "String");
 		 if(null!=object){
 			return (String)object;
 		  }
		 try{
			 jpoShoppingCartOrderListManager.deleShoppingCartList(sclId, scId);
		 }catch(Exception e){
			 flag= "0";
		 }
		 return flag;
	 }
	 
	 /**
	  * add product to shoppingCart
	  * @param jpoShoppingCart
	  * @param token user
	  * @return 1 is success ,0 is failed
	  */
	 @RequestMapping(value="api/addToCart")
	 @ResponseBody
		public Object  addProductToShoppingCart(JpoShoppingCart jpoShoppingCart,String token) {
		 	JsysUser user = jsysUserManager.getUserByToken(jpoShoppingCart.getUserCode(), token);
		 	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		    Map<String,Object> result = new HashMap<String,Object>();
		 	if(null!=object){
				result.put("code","0");
				result.put("msg","登录有误!");
		 		return result;
		 	}
		 	try{
		 		String isSuccess = jpoShoppingCartOrderManager.addProductToShoppingCart(jpoShoppingCart,token);
			 	log.info("addShoppingCart info:"+isSuccess);
			 	if(isSuccess.startsWith("autherror")){
					result.put("code","0");
					result.put("msg","登录有误!");
			 		return result;
			 	}
			 	if(isSuccess.startsWith("1")){
					result.put("code","1");
					result.put("msg","添加成功！");
			 		return result;
			 	}
				if(isSuccess.startsWith("0")){
					result.put("code","0");
					String[] msgs=isSuccess.split(":");
					String msg="";
					if(msgs.length>=2){
						msg=msgs[1];
					}
					result.put("msg",msg);
					return result;
				}
		 	}catch(Exception e){
		 		log.error("add product to shoppingCart error.",e);
		 	}
		 result.put("code","0");
		 result.put("msg","添加购物车出错！");
		 return result;
		}
	 /**
	  * 获取购物车数量  
	  * @param userId
	  * @param token
	  * @param orderType 不传值 默认 返回  所有订单类型的购物车数量
	  * @return
	  */
	 @RequestMapping(value="api/getMobileCartNums")
	 @ResponseBody
	 public String mobileCartNums(String userId,String token,HttpServletRequest request){
		 JsysUser user = jsysUserManager.getUserByToken( userId, token); 
		 Object object = jsysUserManager.getAuthErrorCode(user, "String");
			if(null!=object){
				return (String)object;
			}
			JpoShoppingCart jpoShoppingCart=new JpoShoppingCart();
			jpoShoppingCart.setUserCode(user.getUserCode());
			jpoShoppingCart.setCompanyCode(user.getCompanyCode());
			String orderType=request.getParameter("orderType");
			if(!StringUtil.isEmpty(orderType)){
				jpoShoppingCart.setOrderType(orderType);
			}
			int count=0;
			count=jpoShoppingCartOrderManager.getShoppinigCartSum(jpoShoppingCart);
			return String.valueOf(count);
	 }
	 /**
	  * 
	  * 获取手机端订单类型
	  * @param jpoShoppingCart
	  * @param userCode ,token,teamType,isMobile=1
	  * @return
	  */
	 @RequestMapping(value="api/getMoibleOrderTypeAndNums")
	 @ResponseBody
	 public Set ishadMoibleCartProducts(JpoShoppingCart jpoShoppingCart,String token){
		 JsysUser user = jsysUserManager.getUserByToken(jpoShoppingCart.getUserCode(), token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "Set");
			if(null!=object){
				return (Set)object;
			}
		Set<Integer> set=Sets.newHashSet();
		getOrderType(user,set);
/*		//获取团队定点编码
		String teamStr = jmiTeamManager.teamStr(user);
		//获取团队
		JmiMemberTeam team = jmiMemberTeamDao.getJmiMemberTeamByUserCode(teamStr);
		Set<Integer> resultSet=Sets.newHashSet();
		//如果团队是 安杰玛团队 屏蔽屏蔽辅消单 如果是木兰团队 屏蔽升级单
		for (Integer orderType : set) {
			if(team.getName().equals("AJM")&&orderType==5){
				//set.remove(orderType);
				continue ;
			}else if("AJ34272972".equals(teamStr)&&orderType==2){
				continue ;
			}
			resultSet.add(orderType);
		}*/
		return set;
	 }
	 
	 private void getOrderType(JsysUser sysUser,Set<Integer> set){
		 String userCode = sysUser.getUserCode();
		 JsysUserRole userRole = jsysUserRoleManager.
				 getSysUserRoleByUserCode(userCode);
		 JsysRole role = jsysRoleManager.get(userRole.getRoleId());
		
		 int freeStatus = sysUser.getJmiMember().getFreezeStatus();
		 
		 //是否可下首单0需要下首单,1不需要下首单
		 Integer notFirst = sysUser.getJmiMember().getNotFirst();
		 
		 log.info("freeStatus is:["+freeStatus+"] "
		 		+ "and userCode is:"+sysUser.getUserCode() +" "
		 		+ "and role is:"+role.getRoleCode());
		 
		 if(role.getRoleCode().equalsIgnoreCase(Constants.JOCS_FREEZE)){
			 //冻结后,只可以有续约单
			 set.add(3);
		 } else {
			 JmiMember jmiMember = jmiMemberDao.getJmiMember(userCode);
//				   store: 0是会员，1一级店铺 2 二级店铺
//				   userLever: 0.待审会员 1.银卡 2.金卡 3.白金卡 4.钻石卡 5 优惠顾客 6 VIP
//				   orderType: 1.首购单 2.升级单 3、续约单,5 辅销品 4、重消 6、一级店铺首购 8、店铺返单 
//				   9、一级店铺重消 11 二级店铺首购单 12二级店铺升级单  14二级店铺重消单 93代金券换购单
			 if( notFirst == 0){
				 if(role.getRoleCode().equalsIgnoreCase(Constants.ROLEID0)){
					 set.add(1);
					 set.add(41);
				 }
				 if(role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_398)){
					 set.add(1);
				 }

				 //cn.member.41	待审云客
				 if(role.getRoleCode().equalsIgnoreCase(Constants.ROLEID41)){
//					 set.add(41);
				 }
				 //cn.member.41.0	云客
				 if(role.getRoleCode().equalsIgnoreCase(Constants.ROLEID410)){
					 Calendar cal = Calendar.getInstance();
					 cal.add(Calendar.MONTH, 1);
					 Date d1 = jmiMember.getCloudEnddate();
					 if(d1!=null){
						 if(cal.getTime().after(d1)){
//							 set.add(41);
						 }
					 }
					 set.add(1);
				 }
			 } else { 
				 
				 	//JmiMember jmiMember = jmiMemberDao.getJmiMember(userCode);
			     	Map<String,String> mapUp=new HashMap<String,String >();
			     	mapUp.put("userCode", userCode);
			     	mapUp.put("companyCode", jmiMember.getCompanyCode());
			     	mapUp.put("orderType", Constants.ORDER_TYPE_2);
			     	mapUp.put("status", "2");
					List orderup = jpoMemberOrderManager.getOrderByParam(mapUp);
					
					//0:可多次升级 1:只可以升一次
					String upNum = ConfigUtil.getConfigValue("CN", "member_upgrade_num");
					String upGrade ="0";
					log.info("member_upgrade is:"+upNum);
					if(upNum.equals("1")){
						if(orderup == null &&
								!Constants.CARDTYPE_5000.equals(jmiMember.getMemberLevel().toString())){
							upGrade="1";  //可以
							//jsysUser.setUpGrade("0");//1可以下升级单，0表示不能下升级单
						}
					}
					
				 if(upGradeInTime(sysUser)&&	upGrade.equals("1")){
					 set.add(2);
				 }
				 if(     role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_1000)||
						 role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_1500)||
						 role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_2000)||
						 role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_3000)||
						 role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_4000)||
						 role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_5000)){
					 set.add(4);set.add(5);
					 
				 } else if(role.getRoleCode().equalsIgnoreCase(Constants.ROLE_STORE2_X)){
					 set.add(4);set.add(5);set.add(11);
				 } else if(role.getRoleCode().equalsIgnoreCase(Constants.ROLE_STORE21)){
					 set.add(4);set.add(5);set.add(12);
				 } else if(role.getRoleCode().equalsIgnoreCase(Constants.ROLE_STORE2)){
					 set.add(4);set.add(5);set.add(14);
				 } else if(role.getRoleCode().equalsIgnoreCase(Constants.ROLE_STORE1_X)){
					 set.add(4);set.add(5);set.add(6);
				 } else if(role.getRoleCode().equalsIgnoreCase(Constants.ROLE_STORE1)){
					 set.add(4);set.add(5);set.add(9);
				 }/* else {
					 set.add(4);set.add(5);
				 }*/
				 //set.add(16);
				// set.add(40);
				 set.add(93);
				 //一级店铺首购单
				 hasRoles( sysUser,set,"/jpoMemberOrderSF.html",6);
				 //一级店铺重消单
				 hasRoles( sysUser,set,"/jpoMemberOrderSR.html",9);

				 //二级店铺首购单
				 hasRoles( sysUser,set,"/jpoMemberOrderSSubF.html",11);
				 //二级店铺升级单
				 hasRoles( sysUser,set,"/jpoMemberOrderSSubU.html",12);
				 //二级店铺重消单
				 hasRoles( sysUser,set,"/jpoMemberOrderSSubR.html",14);

				 //抵用券购物单
				 hasRoles( sysUser,set,"/jpoMemberOrderP.html",16);
				 //会员辅消单
				 //会员辅消单
				 hasRoles( sysUser,set,"/jpoMemberOrderA.html",5);
				 //会员重消单
				 hasRoles( sysUser,set,"/jpoMemberOrderR.html",4);
				 //代金券换购单
				 hasRoles( sysUser,set,"/jpoMemberOrderDJQ.html",93);


				 
			 }
			 
		}
		 JmiMember jmiMember = jmiMemberDao.getJmiMember(userCode);
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.MONTH, 1);
		 Date d1 = jmiMember.getCloudEnddate();
		 if(d1!=null){
			 if(cal.getTime().after(d1)){
				 set.add(41);
			 }
		 }

		//顾问首单
		 hasRoles( sysUser,set,"/jpoMemberOrderF398.html",42);
		 //顾问重消
		 hasRoles( sysUser,set,"/jpoMemberOrderGC398.html",43);

		log.info("set size is:"+set.size());
	}

	 private Set<Integer> hasRoles(JsysUser sysUser,Set<Integer> set,String roleUrl,Integer orderType){
		 if(Constants.resourceMap!=null &&Constants.resourceMap.size()>0){
			 //顾问重消
			 if(roleUrl!=null && Constants.resourceMap.get(roleUrl)!=null){
				 ArrayList<ConfigAttribute> resouces = (ArrayList) Constants.resourceMap.get(roleUrl);
				 Set<JsysRole> roles=sysUser.getJsysRoles();
				 Iterator iterator = roles.iterator();
				 while (iterator.hasNext()) {
					 String key="ROLE_"+ ((JsysRole) iterator.next()).getRoleCode();
					 for(ConfigAttribute configAttribute:resouces){
						 if(key.equals(configAttribute.getAttribute())){
							 set.add(orderType);
						 }
					 }
				 }
			 }
		 }
	 	return set;
	 }

	 /**
	  * 
	  * @param sysUser
	  * @param set
	
	private void getOrderType(JsysUser sysUser,Set<Integer> set){
		//是否店铺
		 String store = sysUser.getJmiMember().getIsstore();
		 String userLever =  sysUser.getJmiMember().getCardType();
		 int freeStatus = sysUser.getJmiMember().getFreezeStatus();
		 
		 log.info("userLever is:["+userLever+"] and store is:["+store+"] " +
		 		"and freeStatus is:["+freeStatus+"] and userCode is:"+sysUser.getUserCode());
		 if(freeStatus == 1){
			 //冻结后,只可以有续约单
			 set.add(3);
		 } else if("0".equals(store)){
			  
//			   store: 0是会员，1一级店铺 2 二级店铺
//			   userLever: 0.待审会员 1.银卡 2.金卡 3.白金卡 4.钻石卡 5 优惠顾客 6 VIP
//			   orderType: 1.首购单 2.升级单 3、续约单,5 辅销品 4、重消 6、一级店铺首购 8、店铺返单 
//			   9、一级店铺重消 11 二级店铺首购单 12二级店铺升级单  14二级店铺重消单 
			  
			switch (Integer.parseInt(userLever)) {
			case 0:
				set.add(1);
				break;
			default:
				set.add(4);
				set.add(5);
//				if(! userLever.equals("6")){
//					if("1".equals(getUpGradeValue(sysUser))) set.add(2);
//				}
				
				break;
			}
		 } else if("1".equals(store)){
			// 0是会员，1一级店铺 2 二级店铺
			set.add(4);
			set.add(5);
			set.add(9);
			
			JpoMemberOrder order = new JpoMemberOrder();
			order.setSysUser(sysUser);
			order.setOrderType("6");
			
			if(isBindOrderType(order))
				set.add(6);//不存在一级店铺首购单
			
		 } else if("2".equals(store)){
			set.add(4);
			set.add(5);
			 set.add(14);
			 
			 JpoMemberOrder order = new JpoMemberOrder();
			 order.setSysUser(sysUser);
			 order.setOrderType("11");
			
			 if(isBindOrderType(order))
				 set.add(11);//不存在二级店铺首购
			 
			 order.setSysUser(sysUser);
			 order.setOrderType("12");
			 
			 if(isBindOrderType(order))
				 set.add(12);
		 }
		 log.info("set size is:"+set.size());
	}  */
	
	/**
	  * 
	  * 修改购物车的订单是否被选中或者是否发货的状态
	  * @param type =isCheck调用修改是否选中，type =isShipments调用修改是否发货的状态，
	  * @param editVale ：修改的值，scId ：购物车id
	  * @return
	  */
	@RequestMapping(value="api/editOrderCartStatus")
	 @ResponseBody
	 public  String editOrderCartStatus(String userId,String token,Long scId,String type,String editVale){
		 String flag="1";
		 JsysUser user = jsysUserManager.getUserByToken(userId, token);
		 Object object = jsysUserManager.getAuthErrorCode(user, "String");
		 if(null!=object){
			return (String)object;
		  }
		 try{
			JpoShoppingCartOrder shoppingCart = jpoShoppingCartOrderManager.get(scId);
			if(type!=null){
				if(type.equalsIgnoreCase("isCheck")){
					//修改订单是否选中
					shoppingCart.setIsCheck(editVale);
				}else if(type.equalsIgnoreCase("isShipments")){
					//修改订单是否发货
					shoppingCart.setIsShipments(editVale);
				}
				log.info("shopping car is:"+shoppingCart);
				jpoShoppingCartOrderManager.save(shoppingCart);
			}
		 }catch(Exception e){
			 flag= "0";
		 }
		 return flag;
	 }
	
	/**
	 * 提供手机物流跟踪
	 * @param memberOrderNo 订单编号
	 * @return
	 */
	@RequestMapping(value="api/getLogisticsByMobile")
	@ResponseBody
	public List getLogisticsByMobile(String userId,String token,String memberOrderNo){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		
		return jpmProductSaleNewDao.getWLByOrdernoforMobile(memberOrderNo);
	}
	
	@RequestMapping(value="api/getLogisticsByMobiles")
	@ResponseBody
	public String getLogisticsByMobiles(){
		return "333";
	}
	
	
	@RequestMapping(value="api/suggestList")
	@ResponseBody
    public Object list(HttpServletRequest request){
	    String userCode = request.getParameter("userCode"); //会员编号
	    String token = request.getParameter("token"); //会员token
        String suggestionUserCode = request.getParameter("suggestionUserCode"); //建议的建议人
        String subject = request.getParameter("subject");//建议主题
        String createTimeBegin = request.getParameter("startTime");//建议时间--开始
        String createTimeEnd = request.getParameter("endTime");  //建议时间--结束
        String lookStatus = request.getParameter("viewstatus");    //建议查看状态的查询情况 //0代表未阅，1代表已阅，2全部（已阅＋未阅）
 
        //查询状态为空,就表明是初始化查询,初始化查询默认让其查未阅的
        if(StringUtil.isEmpty(lookStatus)){
        	lookStatus = "0";
        }
        
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userCode", userCode);
        param.put("suggestionUserCode", suggestionUserCode);
        param.put("subject", subject);
        param.put("createTimeBegin", createTimeBegin );
        param.put("createTimeEnd", createTimeEnd);
        param.put("lookStatus", lookStatus);

        
       	//分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);

		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("suggest","list",pageSize,request);
        
        return inwSuggestionManager.list(param,page);
    }

	
	@RequestMapping(value="api/suggestDetail")
	@ResponseBody
    public Object detail(HttpServletRequest request,@RequestParam("id") long id,@RequestParam("userCode") String userCode){
		 //先去数据库表inw_viewPeople中查,看该人对该条建议是否已阅
		 List inwViewpeopleIsExist =  inwSuggestionManager.getInwViewpeopleIsExist(id+"",userCode);
		 if(!(null != inwViewpeopleIsExist && inwViewpeopleIsExist.size() > 0)){
			 //表明当前登录用户没有查看这条建议，因此就向inw_viewPeople中插入数据了
		     //获取系统当前的登录时间
			 InwViewpeople inwViewpeople = new InwViewpeople();
			 inwViewpeople.setSuggestionid(id+"");
	   		 Date date = new Date();
	   		 inwViewpeople.setViewTime(date);
	   		 inwViewpeople.setUserCode(userCode);
	   		 //viewStatus字段暂时不用,作扩展字段,INW_VIEWPEOPLE该表里面有关于相关建议的信息,则表明该建议已阅;
	   		 inwViewpeopleManager.save(inwViewpeople);
		 }
		 InwSuggestion o =  inwSuggestionManager.get(id);
		 Map<String,Object> param = new HashMap<String,Object>();
		 if(null != o ){
			 param.put("subject", StringUtil.dealStr(o.getSubject()));
			 param.put("content", StringUtil.dealStr(o.getContent()));
			 param.put("createTime", DateUtil.getDate(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			 param.put("userCode", o.getUserCode()); 
		 }
		return param;
		
	}
	
	public InwSuggestionManager getInwSuggestionManager() {
		return inwSuggestionManager;
	}

	@Autowired
	public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
		this.inwSuggestionManager = inwSuggestionManager;
	}
	  public InwViewpeopleManager getInwViewpeopleManager() {
		return inwViewpeopleManager;
	}

	  @Autowired
	public void setInwViewpeopleManager(InwViewpeopleManager inwViewpeopleManager) {
		this.inwViewpeopleManager = inwViewpeopleManager;
	}
}
