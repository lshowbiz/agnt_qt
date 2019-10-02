package com.joymain.ng.webapp.controller;


import org.apache.commons.lang.StringUtils;









import com.joymain.ng.Constants;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.handle.shipping.ShippingHandel;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/jpoMemberOrderform*")
public class JpoMemberOrderFormController extends BaseFormController {
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    @Autowired
    private JmiMemberManager jmiMemberManager=null;
    @Autowired
    private JpoShoppingCartOrderManager jpoShoppingCartOrderManager=null;
    @Autowired
    private JpmProductSaleNewManager jpmProductSaleNewManager=null;
    @Autowired
    JmiAddrBookManager  jmiAddrBookManager=null;
    @Autowired
    private ShippingHandel shippingHandel;
    @Autowired
    private JsysUserManager jsysUserManager;
    @Autowired
    private JmiMemberDao jmiMemberDao;
	@Autowired
	PdSendInfoManager pdSendInfoManager;
    
    @Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    
    public JpoMemberOrderFormController() {
        setCancelView("redirect:jpoShoppingCartOrderListform");
        setSuccessView("redirect:jpoMemberOrders");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoMemberOrder showForm(HttpServletRequest request)
    throws Exception {
        String moId = request.getParameter("moId");
       
        if (!StringUtils.isBlank(moId)) {
            return jpoMemberOrderManager.get(new Long(moId));
        }

        return new JpoMemberOrder();
    }

	@ModelAttribute
	public JpoMemberOrder getJpoMemberOrder(HttpServletRequest request) {
		  JpoMemberOrder jpoMemberOrder=null;
		String mobile=request.getParameter("mobile");
		if(!StringUtils.isEmpty(mobile))
	    {
			   return jpoMemberOrder;
		}
		else
		{
		   JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   ModelAndView mav = new ModelAndView();
		   String strAction=request.getParameter("strAction");
		   String moId=request.getParameter("order_mo_id");
		   if("delOrder".equals(strAction))
		   {
			      if (!StringUtils.isEmpty(moId)){
			            jpoMemberOrder = jpoMemberOrderManager.get(new Long(moId));
			            if("1".equals(jpoMemberOrder.getIsPay())){
			            	throw new AppException("订单已支付!");
			            	//log.error("订单已支付!");
			            }
			            if("M".equals(loginSysUser.getUserType())){
			                if(!jpoMemberOrder.getSysUser().getUserCode().equals(loginSysUser.getUserCode())){
			                	throw new AppException("订单不属于当前登录者!");
			                	//log.error("订单不属于当前登录者!");
			                }
			            }else{
			                if(!jpoMemberOrder.getCompanyCode().equals(loginSysUser.getCompanyCode())){
			                	throw new AppException("订单不属于当前登录者!");
			                	//log.error("订单不属于当前登录者!");
			                }
			            }
			            if(!"1".equals(jpoMemberOrder.getStatus()) || !"1".equals(jpoMemberOrder.getSubmitStatus())){
			            	throw new AppException("订单不能修改或删除!");
			            	//log.error("订单不能删除!");
			            
			            }
			        }
			      
		   }
		  
		}
		 return jpoMemberOrder;
	}
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoMemberOrder jpoMemberOrder, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	String success=null;
    	try{
	        if (request.getParameter("cancel") != null) {
	            return getCancelView();
	        }
	
	        /*if (validator != null) { // validator is null during testing
	            validator.validate(jpoMemberOrder, errors);
	
	            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
	                return "jpoMemberOrderform";
	            }
	        }*/
	
	        log.debug("entering 'onSubmit' method...");
	        success = getSuccessView();
	        Locale locale = request.getLocale();
	        String strAction=request.getParameter("strAction");
	        log.info("strAction is:"+strAction+" and cancelView is:"+this.getCancelView());
	        
	        if ("delOrder".equals(strAction)) {//删除订单
	        	String moId=request.getParameter("order_mo_id");
//	            jpoMemberOrderManager.remove(new Long(moId));
	        	//Modify By WuCF 20160824 删除订单，调用新方法，删除的订单数据备份到临时表中
	        	jpoMemberOrderManager.removeJpoMemberOrder(moId);
	            saveMessage(request, getText("zh_CN","jpoMemberOrder.deleted"));
	            success="redirect:jpoMemberOrders/orderAll";;
	            
	        }else if ("delOrderJtc".equals(strAction)) {//Modify By WuCF 删除家套餐订单
	        	String moId=request.getParameter("order_mo_id");
	            jpoMemberOrderManager.deleteOrderByMoids(moId);
	            saveMessage(request, getText("zh_CN","jpoMemberOrder.deleted"));
	            success="redirect:showBigPage?1=1";;
	            
	        } else if("addOrder".equals(strAction)) {//创建新订单
	        	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        	/*bug 2920*/
	        	loginSysUser = jsysUserManager.get(loginSysUser.getUserCode());
	            //保存地址
	       	      String fabId=request.getParameter("fabName");
	       	      
	    	      Map<String,List<String>>  msgMap=new HashMap<String, List<String>>();
	    	      /*支付改造 添加验证*/
	    	      if(loginSysUser.getJmiMember().getActive().equals("1")){
	    	       
	        		String error_msg = (String) Constants.localLanguageMap.
	        				get(loginSysUser.getDefCharacterCoding()).
	        				get("checkError.Code.member");
	        		
	        		List<String> msgLists=new ArrayList<String>();
	        		msgLists.add(error_msg);
	        		msgMap.put("提示", msgLists);
	        		this.errorTip(msgMap,request);
	        		return this.getCancelView();
	    	      }
	    	      
	    	     //保存地址
	             if(StringUtils.isEmpty(fabId))
	              {       
	       	    
	       	            List<String> msgLists=new ArrayList<String>();
		        		msgLists.add("地址不允许空！点击右边的【使用新地址】进行地址添加或是点击【收货地址管理】进入个人中心进行地址添加");
		        		msgMap.put("提示", msgLists);
		        		this.errorTip(msgMap,request);
		        		return this.getCancelView();
	             }
	             JmiAddrBook  addrBook=jmiAddrBookManager.get(new Long(fabId));
	             if(addrBook==null)
	             {
	       	          List<String> msgLists=new ArrayList<String>();
		        		msgLists.add("地址不允许空！");
		        		msgMap.put("提示", msgLists);
		        		this.errorTip(msgMap,request);
		        		return this.getCancelView();
	             }
	            msgMap=this.addPoMemberOrder(request,loginSysUser,addrBook,"0","0");
	        	StringBuffer msgBuf=this.errorTip(msgMap,request); 
	        	if(msgBuf.length()>0)
	        	{
	        	  return  this.getCancelView();
	        	}
	        	 
	          success="jpoMemberOrderform";
	         }else{	                
	        }
    	}catch(Exception e){
    		log.error("",e);
    	}
    	log.info("success is:"+success);
    	return success;
    }
    //移动用
    @RequestMapping(value="api/addJpoMemberOrder")
	@ResponseBody
   public Map<String,List<String>> MobileAddPoMemberOrder(HttpServletRequest request,String userId,String token,Long fabId,String cartBuyType,String orderBuyTppe){
    	  Map<String,List<String>>  msgMap=new HashMap<String, List<String>>();
    	try
    	{
	    	 JsysUser loginSysUser = jsysUserManager.getUserByToken(userId, token);
	    	 Object object = jsysUserManager.getAuthErrorCode(loginSysUser, "Map");
	 		if(null!=object){
	 			return (Map)object;
	 		}
	    	 JmiAddrBook  addrBook=jmiAddrBookManager.get(new Long(fabId));
	    	 if(addrBook!=null && loginSysUser!=null)
	    	 {
	    		 msgMap=this.addPoMemberOrder(request,loginSysUser, addrBook,cartBuyType,orderBuyTppe);
	    	 }else
	    	 {
	    		 List<String>  errTip=new ArrayList<String>();
	    		 errTip.add("用户名为空或是地址不存在");
	    		 msgMap.put("提示",errTip);
	    	 }
		     
    	}catch (Exception e) {  
    		log.error("",e);
		}
	     return  msgMap;
   }
 
    public  Map<String,List<String>> addPoMemberOrder(HttpServletRequest request,JsysUser loginSysUser,JmiAddrBook addrBook,String cartBuyType,String orderBuyType)
    {
    	 JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
    	 Map<String,List<String>>  msgMap=new HashMap<String, List<String>>();
    	 Map<String,List<String>>  jpoMemberMap=new HashMap<String, List<String>>();
    	 String bingProduct = request.getParameter("bingProduct");
    	 if(bingProduct == null) bingProduct = "";
    	 
    	 
          if("M".equals(loginSysUser.getUserType())){
              jpoMemberOrder.setSysUser(loginSysUser);
              jpoMemberOrder.setCompanyCode(loginSysUser.getCompanyCode());
              jpoMemberOrder.setUserSpCode(loginSysUser.getUserCode());
          	 JmiMember jmiMember = jmiMemberManager.get(loginSysUser.getUserCode());
              jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());
          }
          
          jpoMemberOrder.setOrderUserCode(loginSysUser.getUserCode());
          jpoMemberOrder.setLocked("0");
          jpoMemberOrder.setOrderType("1");
          jpoMemberOrder.setPickup("0");//是否自动提货
          jpoMemberOrder.setStatus("1");
          jpoMemberOrder.setSubmitStatus("1");
          jpoMemberOrder.setIsPay("0");
          
          jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
          jpoMemberOrder.setAmount(new BigDecimal(0)); 
          jpoMemberOrder.setPvAmt(new BigDecimal(0));
          jpoMemberOrder.setPayMode("0");//付款方式
          jpoMemberOrder.setIsSpecial("0");//是否为特殊订单
  
          jpoMemberOrder.setProvince(addrBook.getProvince());
          jpoMemberOrder.setCity(addrBook.getCity());
          jpoMemberOrder.setDistrict(addrBook.getDistrict());
          jpoMemberOrder.setAddress(addrBook.getAddress());
          jpoMemberOrder.setFirstName(addrBook.getFirstName());
          jpoMemberOrder.setLastName(addrBook.getLastName());
          jpoMemberOrder.setPostalcode(addrBook.getPostalcode());
          jpoMemberOrder.setMobiletele(addrBook.getMobiletele());
          jpoMemberOrder.setPhone(addrBook.getPhone());
          jpoMemberOrder.setIsMobile(orderBuyType);
          
          
      	JpoShoppingCart jpoShoppingCart=new JpoShoppingCart();
     	jpoShoppingCart.setCompanyCode(jpoMemberOrder.getSysUser().getCompanyCode());
     	jpoShoppingCart.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
     	jpoShoppingCart.setIsCheck("1");//查询选中会员选中的订单
     	jpoShoppingCart.setIsMobile(cartBuyType);
     	jpoMemberOrder.setOrderTime(new Date());
     	
     	List<JpoMemberOrder> jpoMemberOrders=new ArrayList<JpoMemberOrder>();//订单
     	List<Set> jpoMemberOrderListSets= new ArrayList<Set>();//订单明细
     	List<Set> jpoMemberOrderFeeSets=new ArrayList<Set>();//物流费
     	List<JpoShoppingCartOrder> scList=jpoShoppingCartOrderManager.getJpoScOrderList(jpoShoppingCart);
     	Set jpoMemberOrderSet=null;
 		Set jpoMemberOrderFeeSet=null;
     	JpoMemberOrder joMemberOrderSave=null;
     
     	if(scList!=null && scList.size()>0)
     	{
	        	for(int i = 0 ; i < scList.size() ; i++){
	        		JpoShoppingCartOrder jpoSOC = scList.get(i);
	        		List<String> msgList=new ArrayList<String>();//保存错误信息
					joMemberOrderSave = new JpoMemberOrder();
					
					
		             //限制2个套餐，数量限制分别是5000和8000,已加入购物车后不允许下单
					 List<String> proS = fillInJpoProduct2(jpoSOC);
					 for (String proNo : proS) {
						if ("P21811700201CN0".equalsIgnoreCase(proNo)) {
							CommonRecord crm = new CommonRecord();
							try {
								crm.setValue("po", "P21811700201CN0");
							} catch (Exception e) {
								e.printStackTrace();
							}
							int num = pdSendInfoManager.getStatisticProductSale2(crm);
							int qty = 0;
							Set<JpoShoppingCartOrderList> set = jpoSOC.getJpoShoppingCartOrderList();
							for (JpoShoppingCartOrderList jpoShoppingCartOrderList : set) {
								qty = jpoShoppingCartOrderList.getQty();
							if (num > 5000) {
				        		List<String> msgLists=new ArrayList<String>();
				        		String msg ="面膜组合已售罄,无法添加进订单";
				        		msgLists.add(msg);
				        		msgMap.put("提示", msgLists);
				        //		this.errorTip(msgMap,request);
				        		return msgMap;
							}
							int allNum = num+qty;
							if (num < 5000 && allNum > 5000) {
					    		List<String> msgLists=new ArrayList<String>();
				        		String msg ="面膜组合即将售罄,请减少购物车商品数量";
				        		msgLists.add(msg);
				        		msgMap.put("提示", msgLists);
				        	//	this.errorTip(msgMap,request);
				        		return msgMap;
							}
							}
						}
						if ("P21811700101CN0".equalsIgnoreCase(proNo)) {
							CommonRecord crm = new CommonRecord();
							try {
								crm.setValue("po", "P21811700101CN0");
							} catch (Exception e) {
								e.printStackTrace();
							}
							int num = pdSendInfoManager.getStatisticProductSale2(crm);
							int qty = 0;
							Set<JpoShoppingCartOrderList> set = jpoSOC.getJpoShoppingCartOrderList();
							for (JpoShoppingCartOrderList jpoShoppingCartOrderList : set) {
								qty = jpoShoppingCartOrderList.getQty();
							if (num > 8000) {
								List<String> msgLists=new ArrayList<String>();
								String msg ="多莓组合已售罄,无法添加进订单";
								msgLists.add(msg);
								msgMap.put("提示", msgLists);
							//	this.errorTip(msgMap,request);
								return msgMap;
							}
							int allNum = num+qty;
							if (num < 8000 && allNum > 8000) {
								List<String> msgLists=new ArrayList<String>();
								String msg ="多莓组合即将售罄,请减少购物车商品数量";
								msgLists.add(msg);
								msgMap.put("提示", msgLists);
							//	this.errorTip(msgMap,request);
								return msgMap;
							}
							}
						}
					 }


					if("42".equals(scList.get(i).getOrderType())) {
						boolean result=jpoMemberOrderManager.checkCartProduct(loginSysUser.getUserCode());
						//根据会员编号获取该会员身份证
						if (!result) {
							List<String> msgLists=new ArrayList<String>();
							String msg ="顾问首单只能购买一个且同一个身份证下的会员只能订购一次";
							msgLists.add(msg);
							msgMap.put("提示", msgLists);
							//	this.errorTip(msgMap,request);
							return msgMap;
						}
					}

					if("41".equals(scList.get(i).getOrderType())) {
		    			String ydzgd = "0";
			 	       	JmiMember jmiMember = jmiMemberDao.getJmiMemberBankInformation(loginSysUser.getUserCode());
			 	       	 
		                Map<String,String> orderMap=new HashMap<String,String >();
		 	       		orderMap.put("userCode", loginSysUser.getUserCode());
		 	       		orderMap.put("companyCode", loginSysUser.getCompanyCode());
		 	       		orderMap.put("orderType", "41");
		 	       		orderMap.put("status", "1");//是否是360年费订单
		 	       		List jpoMemberOrders1 = jpoMemberOrderManager.getOrderByParam(orderMap);
		 	       		
		 	       		if(jpoMemberOrders1==null || jpoMemberOrders1.size()==0){ //会员已存在订单，且会员的云店有效期未到结束前一个月
		 	       			//除了优惠顾客级别，判断达成云客或脉客的时间为结束期前一个月，即可下云店资格单	COMMENT ON COLUMN JMI_MEMBER.CLOUD_ENDDATE IS '云店资格有效期，结束时间';
			 	    	    Calendar cal = Calendar.getInstance();
			 	 			cal.add(Calendar.MONTH, 1);
			 	 			Date d1 = jmiMember.getCloudEnddate();
			 	 			if(d1!=null){
			 	 				 if(cal.getTime().after(d1)){	
			 	 					ydzgd = "1";
			 	 				 }
			 	 			 }
			 	 			//只做续费用
//			 	 			else{
//			 	 				ydzgd = "1";
//			 	 			 }
		 	       		}



		 	       	//待审会员可以下云店资格单
		 	     	     if("0".equals(jmiMember.getMemberLevel().toString()) && jpoMemberOrders1 == null){
				       			ydzgd = "1";
		 	     	     }
		 	     	     //398会员可以下云店资格单
		 	     	     if("398".equals(jmiMember.getMemberLevel().toString()) && jpoMemberOrders1 == null){
		 	     	    	 ydzgd = "1";
		 	     	     }
		    			if("0".equals(ydzgd))
		    			{
		    				msgList.add(LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"order41Repeat"));
		    			}
		    			
		    		}
	        		
					BeanUtils.copyProperties(jpoMemberOrder,joMemberOrderSave);
	        		joMemberOrderSave.setOrderType(jpoSOC.getOrderType());
					joMemberOrderSave.setTeamCode(jpoSOC.getTeamType());//添加团队
					joMemberOrderSave.setIsShipments(jpoSOC.getIsShipments());
					
					//非冻结状态的会员，不允许生成续约单
					JmiMember user = loginSysUser.getJmiMember();
					if(user.getFreezeStatus().equals(Constants.FREEZE_STATUS_ZERO) && 
							(jpoSOC.getOrderType().equals("3"))){
						
						msgList.add(LocaleUtil.getLocalText(
								loginSysUser.getDefCharacterCoding(),
								"miMemberrs.notFreeze"));
						msgMap.put(ListUtil.getListValue(loginSysUser.getDefCharacterCoding(), 
								"po.all.order_type", joMemberOrderSave.getOrderType()),msgList);
						return  msgMap;
					}
					
					JmiMember member =  jmiMemberManager.
							 getJmiMemberBankInformation(loginSysUser.getUserCode());
					 /*支付改造 添加验证 bug:2511*/
		    	      if(jpoSOC.getOrderType().equals("1") && member.getNotFirst()==1){
		    	    	  String error_msg = (String) Constants.localLanguageMap.
			        				get(loginSysUser.getDefCharacterCoding()).
			        				get("poMemberFOrder.isExist");
			        		
			        		List<String> msgLists=new ArrayList<String>();
			        		msgLists.add(error_msg);
			        		msgMap.put("提示", msgLists);
			        		this.errorTip(msgMap,request);
			        		return msgMap;
		    	      }
					
		    	      if(validateOrder(joMemberOrderSave, loginSysUser)){
		    	    	  String error_msg = (String) Constants.localLanguageMap.
			        				get(loginSysUser.getDefCharacterCoding()).
			        				get("user.validate");
			        		
			        		List<String> msgLists=new ArrayList<String>();
			        		msgLists.add(error_msg);
			        		msgMap.put("提示", msgLists);
			        		//this.errorTip(msgMap,request);注释，否则页面会出现两个相同的错误提示
			        		return msgMap;
		    	      }
		    	      
		    	    /*支付改造bug:2866 */
		    	    String noSale = fillInJpoProduct(jpoSOC);
					if (!noSale.equals("")) {
						msgList.add("产品("+noSale+")"+LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pmOnsale.isNotFount"));
					}
					
					try {
						jpoMemberOrderSet = fillInJfoMemberOrderList(jpoSOC,joMemberOrderSave,bingProduct);//生成订单明细
						if(jpoMemberOrderSet==null && jpoMemberOrderSet.size()==0)
		                {
							msgList.add(LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pd.shNo.isNotFount"));
		                }else{
		                	jpoMemberOrderFeeSet = fillInJfoMemberOrderFee(request,joMemberOrderSave,loginSysUser,jpoMemberOrderSet);
		                }
					
						
						//获取物流费
						if(jpoMemberOrderFeeSet == null || jpoMemberOrderFeeSet.size()==0){//没有算物流费
					        //没有指定物流公司  		
					    	msgList.add(LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pd.shippingFee.isEmpty"));
					    	msgMap.put(ListUtil.getListValue(loginSysUser.getDefCharacterCoding(), "po.all.order_type", joMemberOrderSave.getOrderType()),msgList);
							return  msgMap;
					    }else{
					    	int result = this.calcOrderAmount(jpoMemberOrderSet, joMemberOrderSave,jpoMemberOrderFeeSet);// 计算总pv总金额	
							if(result == 1){
								msgList.add(LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"ec.add.amount.notEnough"));
								msgMap.put(ListUtil.getListValue(loginSysUser.getDefCharacterCoding(), "po.all.order_type", joMemberOrderSave.getOrderType()),msgList);
								//log.info(msgMap.get("po.all.order_type").get(0));
								return  msgMap;
							}
							if(result == 2){
								msgList.add(LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough"));// 计算总PV
								msgMap.put(ListUtil.getListValue(loginSysUser.getDefCharacterCoding(), "po.all.order_type", joMemberOrderSave.getOrderType()),msgList);
								return  msgMap;
							}
							
					    }
					
					} catch (Exception e) {}
					
					if(msgList!=null && msgList.size()>0)
					{
					  msgMap.put(ListUtil.getListValue(loginSysUser.getDefCharacterCoding(), "po.all.order_type", joMemberOrderSave.getOrderType()),msgList);
					  return  msgMap;
					}
					joMemberOrderSave.setOrderAmount(joMemberOrderSave.getAmount());
					
					jpoMemberOrders.add(joMemberOrderSave);
					jpoMemberOrderListSets.add(jpoMemberOrderSet);
					jpoMemberOrderFeeSets.add(jpoMemberOrderFeeSet);
	  			     
	        	}
	        	
	       List<String> ls=jpoMemberOrderManager.editJpoMemberOrderBatch(jpoMemberOrders, jpoMemberOrderListSets,jpoMemberOrderFeeSets,scList);
          //查询确认后的订单
           List<JpoMemberOrder> orderList=jpoMemberOrderManager.getJpoMemberOrder(ls);
           request.setAttribute("orderList", orderList);
           if(!MeteorUtil.isBlankByList(orderList)){
        	   request.setAttribute("moid", orderList.get(0).getMoId());
           }
           //移动用到，返回订单编号
           String mobile=request.getParameter("mobile");
   	       if(!StringUtils.isEmpty(mobile))
   	        {
   	    	   jpoMemberMap.put("memberOrderNo", ls);
   			   return jpoMemberMap;
   		    }
           
          
	        	
     	}else
     	{
     		List<String> msgLists=new ArrayList<String>();
     		msgLists.add("您的订单已经确认或没有选中要购买的订单类型");
     		msgMap.put("提示", msgLists);
     	}
     	return msgMap;
    }
    
    private String fillInJpoProduct(JpoShoppingCartOrder jpoSOC){
        String noSale = "";
    	  Iterator<JpoShoppingCartOrderList> it=jpoSOC.getJpoShoppingCartOrderList().iterator();
  		  while(it.hasNext())
  		  {
  			  JpoShoppingCartOrderList  jpoSCOL=it.next();
  					List<Map<String, Object>> list = jpmProductSaleNewManager.getIsOnSale(jpoSCOL.getJpmProductSaleTeamType().getPttId().toString());
  					if(list.size()!=0){
  						Map<String, Object> map = list.get(0);
  						Object o = map.get("PRODUCT_NO");
  						noSale = o.toString();
  						return noSale;
  					}
  			}
  		return noSale;
    }
    private List fillInJpoProduct2(JpoShoppingCartOrder jpoSOC){
    	String noSale = "";
    	Iterator<JpoShoppingCartOrderList> it=jpoSOC.getJpoShoppingCartOrderList().iterator();
    	List<String> s = new ArrayList<String>();
    	while(it.hasNext())
    	{
    		JpoShoppingCartOrderList  jpoSCOL=it.next();
    		List<Map<String, Object>> list = jpmProductSaleNewManager.getIsOnSale2(jpoSCOL.getJpmProductSaleTeamType().getPttId().toString());
    		if(list.size() != 0){
    			for (Map<String, Object> map : list) {
    				Object o = map.get("PRODUCT_NO");
    				noSale = o.toString();
    				s.add(noSale);
				}
    		}
    	}
    	return s;
    }
    
    private Set fillInJfoMemberOrderList(JpoShoppingCartOrder jpoSOC,JpoMemberOrder jpoMemberOrder,String bindPro){
	    	Set jpoMemberOrderListSet = new HashSet(0);
		    BigDecimal sumPrice = new BigDecimal(0);
		    BigDecimal sumPV=new BigDecimal(0);
              
              try{
            	  Iterator<JpoShoppingCartOrderList> it=jpoSOC.getJpoShoppingCartOrderList().iterator();
    			  while(it.hasNext())
    			  {
    				  JpoShoppingCartOrderList  jpoSCOL=it.next();
    				  if(!"0".equals(jpoSCOL.getQty())){
    						if(jpoSCOL.getQty()<0){
    							return null;
    						}
    						JpmProductSaleTeamType productSTType = jpmProductSaleNewManager.getJpmProductSaleTeamType(jpoSCOL.getJpmProductSaleTeamType().getPttId().toString());
    						if(productSTType==null){
    							return null;
    						}
    						/*支付改造 bug:2519*/
    						if(productSTType.getJpmProductSaleNew().getStatus().equals("2")){
    							return null;
    						}
    						JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();   			
    						jpoMemberOrderList.setPrice(productSTType.getPrice());
    						jpoMemberOrderList.setJpmProductSaleTeamType(productSTType);
    						jpoMemberOrderList.setPv(productSTType.getPv());
    						jpoMemberOrderList.setProNo(productSTType.getJpmProductSaleNew().getProductNo());
    						jpoMemberOrderList.setQty(jpoSCOL.getQty());
    		    			jpoMemberOrderList.setVolume(productSTType.getJpmProductSaleNew().getVolume());
    		    			jpoMemberOrderList.setWeight(productSTType.getJpmProductSaleNew().getWeight());	
    		    			jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
    		    			jpoMemberOrderListSet.add(jpoMemberOrderList);
    		    			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
    		    			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    		    			//45万套餐商品 2016-6-8 
    		    			/*if (jpoSCOL.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo().equals("")) {
    		    				jpoMemberOrder.setProductFlag("45");
							}*/
    		    			
    				  }
    			  }
    			  
    			  if(bindPro.equalsIgnoreCase("Y")){
    				  JpoMemberOrderList orList = new JpoMemberOrderList();
    	              //P26010100101CN0 爱心365
    	              JpmProductSaleTeamType stt = jpmProductSaleNewManager.
    	            		  getJpmProductSaleTeamTypeList("P26010100101CN0", jpoMemberOrder.getTeamCode(),
    	            				  jpoMemberOrder.getOrderType(), jpoMemberOrder.getCompanyCode(),null);
    	              orList.setJpmProductSaleTeamType(stt);
    	              orList.setJpoMemberOrder(jpoMemberOrder);
    	              orList.setPrice(stt.getPrice());
    	              orList.setQty(1);
    	              orList.setPv(new BigDecimal(0));
    	              jpoMemberOrderListSet.add(orList);
    	              sumPV = sumPV.add(orList.getPv()) ;
    	              sumPrice = sumPrice.add(orList.getPrice());
    			  }
    		
    			Calendar start = Calendar.getInstance();
  				start.set(2016, 3, 1, 00, 00, 00); //2016-4-1 
  		    	Date startDat=start.getTime();
  		    	Date curDat=new Date();
  		    	if(curDat.before(startDat)){
  		    		
  		    		 //全年重消满3276pv 打9折取消
  					if("CN".equals(jpoMemberOrder.getCompanyCode())  &&  "4".equals(jpoSOC.getOrderType())){//会员重消单
  						BigDecimal allyearRorder =new BigDecimal(ConfigUtil.getConfigValue(jpoSOC.getSysUser().getCompanyCode().toUpperCase(), "allyear.r.order"));//原先1638 全年重消新财年3276
  				    	if(sumPV.compareTo(allyearRorder)!=-1){
  				    		Iterator its1 = jpoMemberOrderListSet.iterator();
  				    		while (its1.hasNext()) {
  				    			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
  				    			if(jpoMemberOrderList.getPv().compareTo(new BigDecimal("0"))==1){
  				    				jpoMemberOrderList.setPrice(jpoMemberOrderList.getPrice().multiply(new BigDecimal("0.9")));
  				    			}
  				    		}
  				    	}
  					}
  		    	}
   			  
    				
              }catch(Exception e){
            	log.error("",e);  
            	throw new AppException("P26010100101CN0爱心365产品未设置.");
              }
			  return jpoMemberOrderListSet;
		}
    
    
    private Set fillInJfoMemberOrderFee(HttpServletRequest request,JpoMemberOrder jpoMemberOrder,JsysUser sysUser,Set jpoMemberOrderSet) throws Exception
    {
    	Set jpoMemberOrderFeeSet = null;
    	try{ 
    		BigDecimal b = shippingHandel.getShippingSum(request, jpoMemberOrder, sysUser,jpoMemberOrderSet);//物流费
    		if(b.compareTo(new BigDecimal(-1))!=0){
    			jpoMemberOrderFeeSet=shippingHandel.getOrderFeeSet();
    			log.info("orderFeeSet size :"+jpoMemberOrderFeeSet.size());
    		}
    	}catch(Exception e){
    		log.error("",e);
    		throw new AppException(e.getMessage());
    	}
    	return jpoMemberOrderFeeSet;
    }
    /**
     * 计算费用
     * @param request
     * @param jpoMemberOrderSet
     * @param jpoMemberOrder
     */
    private int calcOrderAmount( Set jpoMemberOrderSet,JpoMemberOrder jpoMemberOrder,Set jpoMemberOrderFeeSet){
    	
    	int valid=0;
    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	LinkedHashMap<String, String> map_5 = ListUtil.getListOptions("CN", "5w_product");
    	LinkedHashMap<String, String> map_20 = ListUtil.getListOptions("CN", "20w_product");
    	boolean stjflag = true;
    	
    	Iterator its1 = jpoMemberOrderSet.iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			
			//TODO 生态家
			String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().
					getJpmProductSaleNew().getProductNo();
			Set<String> p5_set = map_5.keySet();
			for(String pno :p5_set){
				if(proNo.equalsIgnoreCase(pno)){
					jpoMemberOrder.setStj_price(5);
					stjflag = false;
					break;
				}
			}
			if(stjflag){
				Set<String> p20_set = map_20.keySet();
				for(String pno :p20_set){
					if(proNo.equalsIgnoreCase(pno)){
						jpoMemberOrder.setStj_price(20);
						break;
					}
				}
			}
			
		}
    	log.info("sumPrice="+sumPrice);
    	if(sumPrice.compareTo(new BigDecimal(0))<=0){
    		valid=1;
    	}
    	String type = jpoMemberOrder.getOrderType();
    	if("40".equals(type)){
    		BigDecimal amount =new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "rorderyear.order.amount")); //3024
    		if(sumPV.compareTo(amount) < 0){
        		valid=2;
        	}
    	}
    	if("3".equals(type)){
    		BigDecimal amount =new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "rsorder.order.pv")); //252
    		if(sumPV.compareTo(amount) < 0){
        		valid=2;
        	}
    	}
    	
    	Iterator its2 = jpoMemberOrderFeeSet.iterator();
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			if(jpoMemberOrderFee.getFee()!=null){
				sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
			} else {
				jpoMemberOrderFee.setFee(new BigDecimal(0));
			}
		}
    	jpoMemberOrder.setAmount(sumPrice);
    	jpoMemberOrder.setAmount2(sumPrice);//Modify By WuCF 20160811 amount2的值不能修改，注释掉
    	jpoMemberOrder.setOrderAmount(sumPrice);
    	jpoMemberOrder.setPvAmt(sumPV);
    	jpoMemberOrder.setOrderAmount(sumPrice);
    	log.info("valid="+valid);
    	return valid;
    }
    //错误提示
    private StringBuffer errorTip(Map<String,List<String>> msgMap,HttpServletRequest request){
	//错误的提示
	StringBuffer msgBuf=new StringBuffer();
	if(msgMap!=null ){	
		Iterator iter =msgMap.keySet().iterator(); 
		String key=null;
		while( iter.hasNext() ){
			key=(String)iter.next();
			List list=(List)msgMap.get(key);
			msgBuf.append( key).append(":") ;
			msgBuf.append( StringUtils.join(list , "|")).append("?");       		
    	}   		
     }
	   this.saveError( request, msgBuf.toString());
	   return msgBuf;
    }
    
    /**
     * 手机端删除订单数据 
     * @userId：会员编码
     * @token：手机端验证
     * @param moId：订单主键
     * @return： 1：成功  0：失败
     * Add By WuCF 20140324
     */
    @RequestMapping(value="api/deleteMobileOrderInfo",method = RequestMethod.GET)
    @ResponseBody
    public String deleteMobileOrderInfo(String userId,String token,Long moId){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	
    	String result = "1";
    	try{
    		jpoMemberOrderManager.remove(moId);
    	}catch(Exception e){
    		result = "0";
    		e.printStackTrace();
    	}
    	return result;
    }
}
