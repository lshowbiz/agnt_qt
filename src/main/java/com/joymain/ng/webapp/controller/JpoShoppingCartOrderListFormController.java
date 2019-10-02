package com.joymain.ng.webapp.controller;

import com.joymain.ng.util.MeteorUtil;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.JpoShoppingCartOrderDao;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.JmiLevelLock;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiValidLevelList;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoProductNumLimit;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JmiLevelLockManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JmiValidLevelListManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoProductNumLimitManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;

@Controller
@RequestMapping("/jpoShoppingCartOrderListform*")
public class JpoShoppingCartOrderListFormController extends BaseFormController {
    private JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager = null;
    @Autowired
    public JmiAddrBookManager jmiAddrBookManager; 
    @Autowired
    public JpoShoppingCartOrderManager jpoShoppingCartOrderManager;
    @Autowired
    public JmiMemberManager jmiMemberManager;
    @Autowired
    public JpoMemberOrderManager jpoMemberOrderManager;
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
    @Autowired
    public JsysUserManager jsysUserManager;
    @Autowired
    JpmProductSaleNewManager jpmProductSaleNewManager;
    @Autowired
	public JpoShoppingCartOrderDao jpoShoppingCartOrderDao;
    @Autowired
    private JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
    private JmiTeamManager jmiTeamManager;
    @Autowired
    JbdPeriodManager jbdPeriodManager;
    @Autowired
    JmiValidLevelListManager jmiValidLevelListManager;
    @Autowired
    JmiLevelLockManager jmiLevelLockManager;
    @Autowired
    protected JsysUserRoleManager jsysUserRoleManager;
    @Autowired
    protected JsysRoleManager jsysRoleManager;
    @Autowired
    private JmiMemberDao jmiMemberDao;
    
    @Autowired
    private JpoProductNumLimitManager jpoProductNumLimitManager;
    @Autowired
    public void setJpoShoppingCartOrderListManager(JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager) {
        this.jpoShoppingCartOrderListManager = jpoShoppingCartOrderListManager;
    }
    public JpoShoppingCartOrderListFormController() {
        setCancelView("redirect:jpoShoppingCartOrderLists/scAll");
        setSuccessView("jpoShoppingCartOrderListform");
    }
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoMemberOrder showForm(HttpServletRequest request)
    throws Exception{
   
        return new JpoMemberOrder();
    }
	@ModelAttribute
	public JpoMemberOrder getJpoMemberOrder(HttpServletRequest request) {

		 JpoMemberOrder jpoMemberOrder=null;
		   if(!"mobile".equals(request.getParameter("mobile")))
		   {
			   JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			    //获取会员地址
		     	JpoShoppingCart jpoShoppingCart=new JpoShoppingCart();
		    	jpoShoppingCart.setCompanyCode(loginSysUser.getCompanyCode());
		    	jpoShoppingCart.setUserCode(loginSysUser.getUserCode());
		    	jpoShoppingCart.setIsCheck("1");//查询选中会员选中的订单
		    	List<JpoShoppingCartOrder> scList=jpoShoppingCartOrderManager.getJpoScOrderList(jpoShoppingCart);
		    	if(scList!=null && scList.size()>0)
		    	{  
		    		request.setAttribute("scList", scList);
		    		List addressList=jmiAddrBookManager.getJmiAddrBookByUserCode(loginSysUser.getUserCode());  
		 	    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(loginSysUser.getCompanyCode());
		 	    	request.setAttribute("alStateProvinces", alStateProvinces);
		 	        request.setAttribute("addressList", addressList);
		    	}
		    	
		    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    	List<Map<String, Object>> list = null;//得到数据值 Modify By WuCF 20150202  解决IE8浏览器展示价格PV为0的情况
		    	list = jpoMemberOrderDao.getTatalPrice(jsysUser.getUserCode());
		    	Map<String,BigDecimal[]> pricePvMap = new HashMap<String,BigDecimal[]>();
		    	BigDecimal priceTotal = new BigDecimal(0);//累加总价格
		    	BigDecimal pvTotal = new BigDecimal(0);//累加总PV
		    	BigDecimal priceTemp = new BigDecimal(0);//价格取值
		    	BigDecimal pvTemp = new BigDecimal(0);//pv取值
		    	BigDecimal[] strTemp = null;
		    	for(Map<String,Object> map : list){
		    		strTemp = new BigDecimal[2];
		    		priceTemp = new BigDecimal(String.valueOf(map.get("PRICE_PV")).split("-")[0]);//价格
		    		pvTemp = new BigDecimal(String.valueOf(map.get("PRICE_PV")).split("-")[1]);//PV
		    		strTemp[0] = priceTemp;
		    		strTemp[1] = pvTemp;
		    		pricePvMap.put(String.valueOf(map.get("ORDER_TYPE")), strTemp);
		    		priceTotal = priceTotal.add(priceTemp);//累加总价格
		    		pvTotal = pvTotal.add(pvTemp);//累加总PV
		    	}
		    	request.setAttribute("pricePvMap", pricePvMap);
		    	request.setAttribute("priceTotal", priceTotal);
		    	request.setAttribute("pvTotal", pvTotal);
		   }
		return jpoMemberOrder;
	}
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoShoppingCartOrderList jpoShoppingCartOrderList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	Map<String, List<String>> msgMap=new HashMap<String, List<String>>();//获取错误信息
    	List<String>   msgList=new ArrayList<String>();
    	//获取会员登录信息
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	ListUtil listUtil=new ListUtil();
        String sclId = request.getParameter("sclId");
       /* //获取会员地址
        List addressList=jmiAddrBookManager.getJmiAddrBookByUserCode(jsysUser.getUserCode());  */
        //  订单的验证
    	JpoShoppingCart jpoShoppingCart=new JpoShoppingCart();
    	jpoShoppingCart.setCompanyCode(jsysUser.getCompanyCode());
    	jpoShoppingCart.setUserCode(jsysUser.getUserCode());
    	jpoShoppingCart.setIsCheck("1");//查询选中会员选中的订单
    	List<JpoShoppingCartOrder> scList=jpoShoppingCartOrderManager.getJpoScOrderList(jpoShoppingCart);
    	
    	if(scList!=null && scList.size()>0)
    	{
    		boolean ismovie=false, isFuXiao=false;
    		boolean isA=false, isB=false;
    		boolean isOnly = false, isOther=false;
  
	    	for(int i=0;i<scList.size();i++)
	    	{    		
	    		JpoShoppingCartOrder cartOrder = scList.get(i);
	    		//jun 电影票
	    		Iterator<JpoShoppingCartOrderList> iter = cartOrder.
	    				getJpoShoppingCartOrderList().iterator();
	   
	        	while(iter.hasNext()){
	        		
	        		JpoShoppingCartOrderList carOrderList = iter.next();
	        		String carProNo = carOrderList.getJpmProductSaleTeamType().
	        				getJpmProductSaleNew().getProductNo();
	        		
	        		if("N07060100101CN0".equalsIgnoreCase(carProNo)&&carOrderList.getQty()>1){
	        			List<String> strList = new ArrayList<String>();
		        		strList.add("云店年费单商品只能订购一个");
		        		msgMap.put("温馨提示",strList);
	        		}
	        		
	        		if(carProNo.equalsIgnoreCase(Constants.MOVIE_PRONO)){
	        			ismovie=true;
	        		}else if((!carProNo.startsWith("N")) && 
	        				(! carProNo.equalsIgnoreCase("P08520100101CN0"))){
	        			isFuXiao=true;
	        		}
	        		
	        		if(carProNo.equalsIgnoreCase("P21030100101CN0")) isA = true;
	        		if(carProNo.equalsIgnoreCase("P21040100101CN0")) isB = true;
	        		
	        		if(Constants.PRONO.equalsIgnoreCase(carProNo) || 
	        				Constants.PRONO1.equalsIgnoreCase(carProNo)||
	        				Constants.PRONO2.equalsIgnoreCase(carProNo)){
	        			
	        			isOnly =true;
	        		}
	        		if(! Constants.PRONO.equalsIgnoreCase(carProNo) && 
	        				!Constants.PRONO1.equalsIgnoreCase(carProNo)&& 
	        				!Constants.PRONO2.equalsIgnoreCase(carProNo)){
	        			
	        			isOther =true;
	        		}
	        	}
	        
	        	if(isOnly && isOther){
	        		List<String> strList = new ArrayList<String>();
	        		strList.add(LocaleUtil.getLocalText(jsysUser.getDefCharacterCoding(),"duanwuinfo"));
	        		msgMap.put("温馨提示",strList);
	        	}
	        	
	        	if(ismovie && isFuXiao){
	        		List<String> strList = new ArrayList<String>();
	        		strList.add(LocaleUtil.getLocalText(jsysUser.getDefCharacterCoding(),"movieinfo"));
	        		msgMap.put("温馨提示:",strList);
	        	}
	        	
	        	if(isA && isB){
	        		List<String> strList = new ArrayList<String>();
	        		strList.add(LocaleUtil.getLocalText(jsysUser.getDefCharacterCoding(),"abinfo"));
	        		msgMap.put("温馨提示:",strList);
	        	}
	        	
	        	List<String> list = proIsOnly(cartOrder);
	        	if(list !=null && list.size()>0){
	        		msgMap.put("温馨提示", list);
	        	}
	        	
	    		//jun end 
	    		
	        	if("1".equals(scList.get(i).getOrderType()))
	    		{
	    			List<String> fMsg=validOrderTypeF(scList.get(i),request);
	    			if(fMsg!=null && fMsg.size()>0)
	    			{
	    			 msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "1"), fMsg);
	    			}
	    			
	    			
	    		}else if("41".equals(scList.get(i).getOrderType())) {
	    			String ydzgd = "0";
		 	       	JmiMember jmiMember = jmiMemberDao.getJmiMember(jsysUser.getUserCode());
	 	       		List<String> uMsg=new ArrayList<String>();
	 	       	
		 	       	Map<String,String> orderMap=new HashMap<String,String >();
	 	       		orderMap.put("userCode", jsysUser.getUserCode());
	 	       		orderMap.put("companyCode", jsysUser.getCompanyCode());
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
	//	 	 			else{
	//	 	 				ydzgd = "1";
	//	 	 			 }
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
    				uMsg.add(LocaleUtil.getLocalText(jsysUser.getDefCharacterCoding(),"order41Repeat"));
	    			msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "41"), uMsg);
	    			}
	    			
	    		}else if("2".equals(scList.get(i).getOrderType()))
	    		{
	    			List<String> uMsg=validOrderTypeU(scList.get(i));
	    			if(uMsg!=null && uMsg.size()>0)
	    			{
	    			msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "2"), uMsg);
	    			}
	    			
	    		}else if("3".equals(scList.get(i).getOrderType()))
	    		{
	    			List<String> rsMsg=validOrderTypeRs(scList.get(i));
	    			if(rsMsg!=null && rsMsg.size()>0)
	    			{
	    				msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "3"), rsMsg);
	    			}		
	    		}else if("4".equals(scList.get(i).getOrderType()))
	    		{
	    			List<String> rMsg=validOrderTypeR(scList.get(i));
	    			if(rMsg!=null && rMsg.size()>0){
    					msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "4"), rMsg);
	    			}
	    			
	    		}else if("5".equals(scList.get(i).getOrderType()))
	    		{
	    		  List<String> aMsg=this.validOrderTypeA(scList.get(i));
	    		  if(aMsg!=null && aMsg.size()>0)
	    		  {
	    			  msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "5"), aMsg);
	    		  }
	    		}else if("6".equals(scList.get(i).getOrderType()))
	    		{
	    			 List<String> shopfMsg=validOrderTypeShopF(scList.get(i));
	    			 if(shopfMsg!=null && shopfMsg.size()>0)
	    			 {
	    			  msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "6"), shopfMsg);
	    			 }	
	    		}else if("9".equals(scList.get(i).getOrderType()))//店铺重消单
	    		{
	    			List<String> shoprMsg=validOrderTypeShopR(scList.get(i));
	    			if(shoprMsg!=null && shoprMsg.size()>0)
	    			{
	    				msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "9"), shoprMsg);
	    			}
	    		}else if("11".equals(scList.get(i).getOrderType()))//二级店铺首购
	    		{
	    			
	    			List<String> sshopFMsg=validOrderTypeSShopF(scList.get(i));
	    			if(sshopFMsg!=null && sshopFMsg.size()>0)
	    			{
	    				 msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "11"), sshopFMsg);
	    			}
	    			
	    		}else if("12".equals(scList.get(i).getOrderType()))//二级店铺升级单
	    		{
	    			List<String> sshopUMsg=validOrderTypeSShopU(scList.get(i));
	    			if(sshopUMsg!=null && sshopUMsg.size()>0)
	    			{
	    				 msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "12"), sshopUMsg);
	    			}
	    			
	    		}else if("14".equals(scList.get(i).getOrderType()))
	    		{
	    			List<String> sshopRMsg=validOrderTypeSShopR(scList.get(i));
	    			if(sshopRMsg!=null && sshopRMsg.size()>0)
	    			{
	    		       msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "14"), sshopRMsg);
	    			}
	    		}else if("40".equals(scList.get(i).getOrderType()))
	    		{
	    			List<String> rYearMsg=validOrderRYear(scList.get(i));
	    			if(rYearMsg!=null && rYearMsg.size()>0)
	    			{
	    		       msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "40"), rYearMsg);
	    			}
	    		}else if("42".equals(scList.get(i).getOrderType())){
	    			JmiMember jmiMember = jmiMemberDao.getJmiMember(jsysUser.getUserCode());
	 	       		List<String> uMsg=new ArrayList<String>();
	 	       	
		 	       	Map<String,String> orderMap=new HashMap<String,String >();
	 	       		orderMap.put("userCode", jsysUser.getUserCode());
	 	       		orderMap.put("companyCode", jsysUser.getCompanyCode());
	 	       		orderMap.put("orderType", "42");
	 	       		orderMap.put("status", "1");//是否存在未审核顾客首单单
	 	       		List jpoMemberOrders1 = jpoMemberOrderManager.getOrderByParam(orderMap);
	 	       		if(jpoMemberOrders1!=null&&jpoMemberOrders1.size()>0){
	 	       		uMsg.add("存在未支付的订单");
	 	       		msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "42"), uMsg);
	 	       		}
	    		}
	    	 }
    	}else
    	{
    		List<String> msgLists=new ArrayList<String>();
    		msgLists.add("您的订单已经确认或没有选中要购买的订单类型");
    		msgMap.put("提示", msgLists);
    	}
    	//错误的提示
    	StringBuffer msgBuf=new StringBuffer();
    	if(msgMap!=null ){	
    		Iterator iter =msgMap.keySet().iterator(); 
    		String key=null;
    		while( iter.hasNext() ){
    			key=(String)iter.next();
    			List list=(List)msgMap.get(key);
    			
    			msgBuf.append( key).append(":") ;
    			msgBuf.append( StringUtils.join( list , "|") ).append("?");       		
        	}
    		
    	}
    	this.saveError( request, msgBuf.toString() );
    //	System.out.println("订单错误信息==="+msgBuf.toString());
    	if(msgBuf.length()>0)
    	{
    	  return  this.getCancelView();
    	}else
    	{
    		for(int i=0;i<scList.size();i++)
    		{
    			JpoShoppingCartOrder jpoShoppingCartOrder=new JpoShoppingCartOrder();
    			jpoShoppingCartOrder=scList.get(i);
    			jpoShoppingCartOrderListManager.editMoAndCheck(null, "order", jpoShoppingCartOrder);
    		}
    		  return   getSuccessView();
    	}

      
    }
    
    @RequestMapping(value="api/mobileEditCartOrderNew")
	@ResponseBody
    public Map<String, List<String>> mobileEditCartOrder(String userId,String token,HttpServletRequest request)//修改购物选择跟状态字段
    {
    	Map<String, List<String>> successMap = new HashMap<String, List<String>>();
    	try{
    		JsysUser jsysUser =jsysUserManager.getUserByToken(userId,token);
    		Object object = jsysUserManager.getAuthErrorCode(jsysUser, "Map");
    		if(null!=object){
    			return (Map)object;
    		}
    		
    		//订单的验证
        	JpoShoppingCart jpoShoppingCart = new JpoShoppingCart();
        	jpoShoppingCart.setCompanyCode(jsysUser.getCompanyCode());
        	jpoShoppingCart.setUserCode(jsysUser.getUserCode());
        	jpoShoppingCart.setIsCheck("1");
    		List<JpoShoppingCartOrder> scList = jpoShoppingCartOrderDao.
    				getJpoScOrderList(jpoShoppingCart);
    		//获取产品
    		List jpoMemberList=jpoMemberOrderDao.getJpoMemberMark(
    				jsysUser.getJmiMember().getPapernumber(), "P08520100101CN0","1");
    		
        	if(jpoMemberList!=null && jpoMemberList.size()>0){
            	//购买过事业锦囊.不强制购买事业锦囊
        		request.setAttribute("effectiveValid","true");	
            } else {
            	//没购买过事业锦囊，强制购买事业锦囊
         		request.setAttribute("effectiveValid","false");
            }
    		
    		Map<String, List<String>> msgMap = checkOrder(scList,jsysUser,request);
    		
    		if(msgMap.isEmpty()){
        		List<String> list = new ArrayList<String>();
        		list.add("1");
        		successMap.put("success", list);
    		   return successMap;//修改过数据成功
    		} else {
    			return msgMap;
    		}
    		
    	}catch (Exception e) {
    		log.error("mobile editCart failed.",e);
    		return new HashMap<String, List<String>>();//修改数据失败
		}
    }
	
   
   //会员首购单
    private List<String>  validOrderTypeF(JpoShoppingCartOrder jpoShoppingCartOrder,HttpServletRequest request)
    {
    	
    	
    	List<String> msgList=new ArrayList<String>();
		 //会员数据验证包括会员是否存在判断，卡别判断，金额pv的判断
    	List<String> msgL=this.checkUser(jpoShoppingCartOrder, msgList);
        //判断是否入会费
        //香港强制绑定商品2012-6-1
//		if( "HK".equals(jpoShoppingCartOrder.getCompanyCode()) && "1".equals(jpoShoppingCartOrder.getOrderType()))
//		{
//		     int result1=this.verificationProduct(request,jpoShoppingCartOrder);
//			 if(result1==1)
//			 {
//				 //没有购买入会费
//				 msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"erro.po.shNo.isEmpty"));
//			 }
//		}	
		
		
     	int result = this.calcOrderAmount(request,jpoShoppingCartOrder);//判断会员是否购买了指定的产品
     	
		if(result==1){
			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough0"));
		}
		if(result==2){//必须购买资料包
			msgL.add(MessageFormat.format(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough5"),new Object[] {"P08520100101CN0"}));
		}
		if(result==3){
			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough"));
		}
		if(result==20){//购买事业锦囊或是辅销品
			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough20"));
		}
		return msgL;
    }
    //会员升级单
    private List<String> validOrderTypeU(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	List<String> msgList=new ArrayList<String>();
        //会员数据验证
    	 List<String> msgL= this.checkUser(jpoShoppingCartOrder,msgList);
    	 return msgL;
    }
    //会员重消单
    private List<String> validOrderTypeR(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	List<String> msgList=new ArrayList<String>();
    	  //会员数据验证
  	   List<String> msgL=  this.checkUser(jpoShoppingCartOrder,msgList);
  		int  preferentialOrderNumber=this.getPreferentialOrderR(jpoShoppingCartOrder.getJpoShoppingCartOrderList());//判断订单种是不是只存在优惠产品,如果是组合产品则不允许通过	
  		if(preferentialOrderNumber==2)
		{
			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberOrder.tip"));
		}
  		if(!jpoIsOnly(jpoShoppingCartOrder,GlobalVar.jpoList)){ //是否是9种单独订购产品 
  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"nineonly"));
  		}
  		if(!jpoIsOnly(jpoShoppingCartOrder,GlobalVar.zqList)){ //是否是中秋礼盒单独订购产品 
  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"zqonly"));
  		}
  		
  		//20151227
  		String startDateS = ConfigUtil.getConfigValue("CN", "201601cx.startdate");
    	String endDateS = ConfigUtil.getConfigValue("CN", "201601cx.enddate");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	Date now = Calendar.getInstance().getTime();
    	try {
    		if(startDateS != null && endDateS != null){
    			if (now.after(sdf.parse(startDateS)) && now.before(sdf.parse(endDateS))) {

    		    	//20151227
    		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160101) ){ //是否是换购规则一的单独订购 (3个商品)
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
    		  		}
    		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160102)){ //是否是换购 枸杞汁 的单独订购
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160102only"));
    		  		}
    		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160103)){ //是否是换购 旋磁椅 的单独订购
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160103only"));
    		  		}
    			}
    		}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.info("201601cx.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
		
		//圣诗蔓积分换购
		String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
    	String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
    	try {
	    	if(startCoin != null && endCoin != null){
				if(now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160524) ){
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160105ssmonly"));
    		  		}
				}
	    	}
    	} catch (ParseException e) {
    		log.info("201605coincx.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
  	   
  	   	//旋磁椅积分换购
  	    String startxcy = ConfigUtil.getConfigValue("CN", "201606xcy.startdate");
	   	String endxcy = ConfigUtil.getConfigValue("CN", "201606xcy.enddate");
	   	try {
	    	if(startxcy != null && endxcy != null){
				if(now.after(sdf.parse(startxcy)) && now.before(sdf.parse(endxcy))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201606xcy) ){
   		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"201606xcyonly"));
   		  		}
				}
	    	}
	   	} catch (ParseException e) {
   		log.info("201606xcy.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
	   	
		//床垫+酒积分换购
  	    String start06 = ConfigUtil.getConfigValue("CN", "201606coincx.startdate");
	   	String end06 = ConfigUtil.getConfigValue("CN", "201606coincx.enddate");
	   	try {
	    	if(start06 != null && end06 != null){
				if(now.after(sdf.parse(start06)) && now.before(sdf.parse(end06))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201606) ){
   		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
   		  		}
				}
	    	}
	   	} catch (ParseException e) {
   		log.info("201606.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
	   	
		//酒积分换购
	   	String start07 = ConfigUtil.getConfigValue("CN", "201805coincx.startdate");
	   	String end07 = ConfigUtil.getConfigValue("CN", "201805coincx.enddate");
	   	try {
	   		if(start07 != null && end07 != null){
	   			if(now.after(sdf.parse(start07)) && now.before(sdf.parse(end07))){
	   				if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201805) ){
	   					msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
	   				}
	   			}
	   		}
	   	} catch (ParseException e) {
	   		log.info("201805.startdate:..."+"dateformaterror");
	   		e.printStackTrace();
	   	}
	   	
	    return msgL;
    }
   //会员辅销单
    private List<String> validOrderTypeA(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	List<String> msgList=new ArrayList<String>();
		  //会员数据验证
    	List<String> msgL=this.checkUser(jpoShoppingCartOrder,msgList);
    	int  preferentialOrderNumber=this.getPreferentialOrderF(jpoShoppingCartOrder.getJpoShoppingCartOrderList());//判断订单种是不是只存在优惠产品,如果是组合产品则不允许通过
		if(preferentialOrderNumber==0)
		{
			
			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"order.noCombination"));
		}

		//20150520库存限制提示
		Collection carProList= new  ArrayList();
		Iterator its   = jpoShoppingCartOrder.getJpoShoppingCartOrderList().iterator();
		
		while (its.hasNext()) {
			JpoShoppingCartOrderList jpoCatrList = (JpoShoppingCartOrderList) its.next();
			carProList.add(jpoCatrList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
		}
		
		return msgL;
    }
    //会员续约单
    private List<String> validOrderTypeRs(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	List<String> msgList=new ArrayList<String>();
        List<String>	msgL=this.checkUser(jpoShoppingCartOrder,msgList);
    	/*
    	Set<JpoShoppingCartOrderList>	scolSet=jpoShoppingCartOrder.getJpoShoppingCartOrderList();
    	Iterator<JpoShoppingCartOrderList> scolIter=scolSet.iterator();
    	BigDecimal orderPv = new BigDecimal(0);
    	BigDecimal sumPrice = new BigDecimal(0);
    	while(scolIter.hasNext())
    	{
    		JpoShoppingCartOrderList  scol=scolIter.next();
    		orderPv=orderPv.add(scol.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(scol.getQty())));
    		sumPrice = sumPrice.add(scol.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(scol.getQty())));
    	}
    	
		if(jpoShoppingCartOrder.getSysUser().getJmiMember().getFreezeStatus()==0){//1代表冻结，0代表正常

		}else{			
			if("CN".equals(jpoShoppingCartOrder.getCompanyCode()) ){
				BigDecimal rspv =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "rsorder.order.pv")); //252
				if(orderPv.compareTo(rspv)==-1){
					msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough"));
    			}
			}
			if(!"CN".equals(jpoShoppingCartOrder.getCompanyCode()) && orderPv.compareTo(new BigDecimal("126"))==-1){//其他国别的
               	msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough"));
			}
		}*/
		return msgL;
    }
    //一级店铺首单
    private List<String> validOrderTypeShopF(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	    List<String> msgList=new ArrayList<String>();
		    List<String> msgL=	this.checkUser(jpoShoppingCartOrder,msgList);
			int result = this.calcOrderAmount(null,jpoShoppingCartOrder);//判断会员是否购买了指定的产品
			if(result==1){
				
				//msgL.add(MessageFormat.format("pv.notEnough0",new Object[] {"N01200200101CN0"},LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough0")));		
				msgL.add(MessageFormat.format(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnoughs"),new Object[] {"N01200200101CN0"}));
			}
			return msgL;
    	
    }
    //一级店铺重消单
    private List<String> validOrderTypeShopR(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
      List<String> msgList=new ArrayList<String>();
      List<String> msgL=this.checkUser(jpoShoppingCartOrder,msgList);
      
    //20151227  
    String startDateS = ConfigUtil.getConfigValue("CN", "201601cx.startdate");
  	String endDateS = ConfigUtil.getConfigValue("CN", "201601cx.enddate");
  	
  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  	
  	Date now = Calendar.getInstance().getTime();
  	try {
  		if(startDateS != null && endDateS != null){
  			if (now.after(sdf.parse(startDateS)) && now.before(sdf.parse(endDateS))) {

		    	//20151227
		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160101) ){ //是否是换购规则一的单独订购 (3个商品)
		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
		  		}
		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160102)){ //是否是换购 枸杞汁 的单独订购
		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160102only"));
		  		}
		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160103)){ //是否是换购 旋磁椅 的单独订购
		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160103only"));
		  		}
			}
  		}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.info("201601cx.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
		
		//圣诗蔓积分换购
		String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
    	String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
    	try {
	    	if(startCoin != null && endCoin != null){
				if(now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160524) ){ 
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160105ssmonly"));
    		  		}
				}
	    	}
    	} catch (ParseException e) {
    		log.info("201605coincx.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
    	
     	//旋磁椅积分换购
  	    String startxcy = ConfigUtil.getConfigValue("CN", "201606xcy.startdate");
	   	String endxcy = ConfigUtil.getConfigValue("CN", "201606xcy.enddate");
	   	try {
	    	if(startxcy != null && endxcy != null){
				if(now.after(sdf.parse(startxcy)) && now.before(sdf.parse(endxcy))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201606xcy) ){
   		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"201606xcyonly"));
   		  		}
				}
	    	}
	   	} catch (ParseException e) {
   		log.info("201606xcy.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
	   	
		//床垫+酒积分换购
  	    String start06 = ConfigUtil.getConfigValue("CN", "201606coincx.startdate");
	   	String end06 = ConfigUtil.getConfigValue("CN", "201606coincx.enddate");
	   	try {
	    	if(start06 != null && end06 != null){
				if(now.after(sdf.parse(start06)) && now.before(sdf.parse(end06))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201606) ){
   		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
   		  		}
				}
	    	}
	   	} catch (ParseException e) {
   		log.info("201606.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
	   	
		//酒积分换购
	   	String start07 = ConfigUtil.getConfigValue("CN", "201805coincx.startdate");
	   	String end07 = ConfigUtil.getConfigValue("CN", "201805coincx.enddate");
	   	try {
	   		if(start07 != null && end07 != null){
	   			if(now.after(sdf.parse(start07)) && now.before(sdf.parse(end07))){
	   				if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201805) ){
	   					msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
	   				}
	   			}
	   		}
	   	} catch (ParseException e) {
	   		log.info("201805.startdate:..."+"dateformaterror");
	   		e.printStackTrace();
	   	}
	   	
      return msgL;
    	
    }
    //二级店铺首购单
    private List<String> validOrderTypeSShopF(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	List<String> msgList=new ArrayList<String>();
        List<String>  msgL=this.checkUser(jpoShoppingCartOrder,msgList);
    	int result = this.calcOrderAmount(null,jpoShoppingCartOrder);//判断会员是否购买了指定的产品
	 	if(result==1){
	 
	 		msgL.add(MessageFormat.format(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnoughs"),new Object[] {"N01190100101CN0"}));
	 		
    	}
	 	return msgL;
    	
    }
    //二级店铺升级单
    private List<String> validOrderTypeSShopU(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	List<String> msgList=new ArrayList<String>();
    	List<String> msgL=this.checkUser(jpoShoppingCartOrder,msgList);
    	int result = this.calcOrderAmount(null,jpoShoppingCartOrder);//判断会员是否购买了指定的产品
    	if(result==1){	
    	
    		msgL.add(MessageFormat.format(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnoughs"),new Object[] {"N02330200101CN0"}));
		}
    	return  msgL;
    }
    private List<String> validOrderTypeSShopR(JpoShoppingCartOrder jpoShoppingCartOrder)
    {
    	List<String> msgList=new ArrayList<String>();
    	List<String> msgL=this.checkUser(jpoShoppingCartOrder,msgList);
    	
    	String startDateS = ConfigUtil.getConfigValue("CN", "201601cx.startdate");
    	String endDateS = ConfigUtil.getConfigValue("CN", "201601cx.enddate");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	
    	Date now = Calendar.getInstance().getTime();
    	try {
    		if(startDateS != null && endDateS != null){
    			if (now.after(sdf.parse(startDateS)) && now.before(sdf.parse(endDateS))) {

    		    	//20151227
    		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160101) ){ //是否是换购规则一的单独订购 (3个商品)
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
    		  		}
    		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160102)){ //是否是换购 枸杞汁 的单独订购
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160102only"));
    		  		}
    		  		if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160103)){ //是否是换购 旋磁椅 的单独订购
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160103only"));
    		  		}
    			}
    		}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.info("201601cx.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
    	
		//圣诗蔓积分换购
		String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
    	String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
    	try {
	    	if(startCoin != null && endCoin != null){
				if(now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList20160524) ){ 
    		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160105ssmonly"));
    		  		}
				}
	    	}
    	} catch (ParseException e) {
    		log.info("201605coincx.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
    	//旋磁椅积分换购
  	    String startxcy = ConfigUtil.getConfigValue("CN", "201606xcy.startdate");
	   	String endxcy = ConfigUtil.getConfigValue("CN", "201606xcy.enddate");
	   	try {
	    	if(startxcy != null && endxcy != null){
				if(now.after(sdf.parse(startxcy)) && now.before(sdf.parse(endxcy))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201606xcy) ){
   		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"201606xcyonly"));
   		  		}
				}
	    	}
	   	} catch (ParseException e) {
   		log.info("201606xcy.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
	   	
		//床垫+酒积分换购
  	    String start06 = ConfigUtil.getConfigValue("CN", "201606coincx.startdate");
	   	String end06 = ConfigUtil.getConfigValue("CN", "201606coincx.enddate");
	   	try {
	    	if(start06 != null && end06 != null){
				if(now.after(sdf.parse(start06)) && now.before(sdf.parse(end06))){
					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201606) ){
   		  			msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
   		  		}
				}
	    	}
	   	} catch (ParseException e) {
   		log.info("201606.startdate:..."+"dateformaterror");
			e.printStackTrace();
		}
	   	
	   	//酒积分换购
	   	String start07 = ConfigUtil.getConfigValue("CN", "201805coincx.startdate");
	   	String end07 = ConfigUtil.getConfigValue("CN", "201805coincx.enddate");
	   	try {
	   		if(start07 != null && end07 != null){
	   			if(now.after(sdf.parse(start07)) && now.before(sdf.parse(end07))){
	   				if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpoList201805) ){
	   					msgL.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"20160101only"));
	   				}
	   			}
	   		}
	   	} catch (ParseException e) {
	   		log.info("201805.startdate:..."+"dateformaterror");
	   		e.printStackTrace();
	   	}
	   	
    	return msgL;
    }
    /**
     * 全年重消单
     * @param jpoShoppingCartOrder
     * @return
     */
    public List validOrderRYear(JpoShoppingCartOrder jpoShoppingCartOrder){
    	List<String> msgList=new ArrayList<String>();
  	  //会员数据验证
	   List<String> msgL=  this.checkUser(jpoShoppingCartOrder,msgList);
	   
	   return msgL;
    }
    
    //会员条件验证
    private List<String> checkUser(JpoShoppingCartOrder  jpoShoppingCartOrder,List<String>  msgList)
    {
    	
//    	if(jpoShoppingCartOrder.getOrderType().equals("4") || jpoShoppingCartOrder.getOrderType().equals("14") ||
//    			jpoShoppingCartOrder.getOrderType().equals("9") || jpoShoppingCartOrder.getOrderType().equals("40")){
//    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        	Date now = Calendar.getInstance().getTime();
//        	//201611月不参与换购的需要单独订购
//    	    String start11 = ConfigUtil.getConfigValue("CN", "201611coincx.startdate");
//    	   	String end11 = ConfigUtil.getConfigValue("CN", "201611coincx.enddate");
//    		try {
//    	    	if(start11 != null && end11 != null){
//    				if(now.after(sdf.parse(start11)) && now.before(sdf.parse(end11))){
//    					if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.jpocoincxList201611) ){
//    						msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"201611only"));
//       		  			}
//    				}
//    	    	}
//    	   	} catch (ParseException e) {
//       		log.info("201611.startdate:..."+"dateformaterror");
//    			e.printStackTrace();
//    		}
//    	}
    	
    	//韩美旅游积分产品 单独订购
//    	if(!jpoIsOnly(jpoShoppingCartOrder, GlobalVar.KoralTravel201601)){
//    		msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"KoralTravel201601only"));
//    	}
    	
		//20150520库存限制提示
		Collection carProList= new  ArrayList();
		Iterator its   = jpoShoppingCartOrder.getJpoShoppingCartOrderList().iterator();
		
		while (its.hasNext()) {
			JpoShoppingCartOrderList jpoCatrList = (JpoShoppingCartOrderList) its.next();
			carProList.add(jpoCatrList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
		}
		
    	String ts; 
		List<JpoProductNumLimit> plimits = jpoProductNumLimitManager.getAllPros();
		
		for (JpoProductNumLimit jpoProductNumLimit : plimits) {
			String productNo = jpoProductNumLimit.getProductNo();
			String productName = jpoProductNumLimit.getProductName();
			if(carProList.contains(productNo) ){
				if(isBuyProNumLimt(jpoShoppingCartOrder, productNo) != -1){
					ts = LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"stockPros");
					ts = ts.replace("abc", productNo+productName);
					ts = ts.replace("def", isBuyProNumLimt(jpoShoppingCartOrder, productNo).toString());
					msgList.add(ts);
				}
			}
		}
    	
   	 if(StringUtils.isEmpty(jpoShoppingCartOrder.getSysUser().getUserCode())){//判断会员是否存在
   		  msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"miMember.notFound"));
	  }
				
   	    JmiMember jmiMember = jmiMemberManager.get(jpoShoppingCartOrder.getSysUser().getUserCode());
		if(jmiMember==null){
    	
			msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"miMember.notFound"));
		}
		if(jmiMember.getFreezeStatus()==1)//会员为冻结状态
		{
			
			if("3".equals(jpoShoppingCartOrder.getOrderType()))//会员续约
			{
				Map<String,String> orderMap=new HashMap<String,String >();
				orderMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
				orderMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
				orderMap.put("orderType", "3");
				orderMap.put("status", "1");
				List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
				if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){//会员续约单存在
					msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberRSOrder.isExist"));//会员续约已经存在，不允许再下
				}
			}
			if(!"3".equals(jpoShoppingCartOrder.getOrderType()))
			{
				msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberRSOrder.isNotExist"));//您目前为冻结状态，必须先下续约单，解冻后才能下其他类型的订单
			}
			
		}else//正常会员
		{
//		if("0".equals(jmiMember.getCardType()) && !"1".equals(jpoShoppingCartOrder.getOrderType())){//待审会员不允许除首单之外的单
			
		//当前期别有没有手工定级	
		if( isLevel(jpoShoppingCartOrder.getSysUser().getUserCode())==true && !"1".equals(jpoShoppingCartOrder.getOrderType())){
			
			Map<String,String> orderMap=new HashMap<String,String >();
			orderMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
			orderMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
			orderMap.put("orderType", "1");
			orderMap.put("status", "2");
			List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
			if(jpoMemberOrders!=null && jpoMemberOrders.size()==0){
				msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberFOrder.isNotExist"));//会员首单不存在
			}
		}
		
		if(Constants.ORDER_TYPE_1.equals(jpoShoppingCartOrder.getOrderType()))//会员首单
		{
			Map<String,String> orderMap=new HashMap<String,String >();
			orderMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
			orderMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
			orderMap.put("orderType", "1");
			List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
			if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){//会员首单存在
				msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberFOrder.isExist"));//会员首单已经存在，不允许在下首单
			}

			String[] limit =ConfigUtil.getConfigValue("CN", "first.order.limit").split(":");
			Integer  numLimit = new Integer(limit[0]);
			Integer  kindLimit = new Integer(limit[1]);
			if(kindLimit > -1){
				Set<JpoShoppingCartOrderList> jpoShoppingCartOrderListSet =jpoShoppingCartOrder.getJpoShoppingCartOrderList();
				if(jpoShoppingCartOrderListSet.size()>kindLimit){
					msgList.add("只能买"+kindLimit+"种商品");
				}else{
					for (JpoShoppingCartOrderList jpoShoppingCartOrderList : jpoShoppingCartOrderListSet) {
						if(numLimit > -1){
							if(jpoShoppingCartOrderList.getQty()>numLimit){
								msgList.add("该商品只能买"+numLimit+"个");
							}
						}
					}
				}
			}
		}
		
		if(Constants.ORDER_TYPE_2.equals(jpoShoppingCartOrder.getOrderType())){
			
			if(! upGradeInTime(jpoShoppingCartOrder.getSysUser())){
				msgList.add(LocaleUtil.getLocalText(
						jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),
						"miMember.isNotUpGrade"));
			}
		   	String upgrade_Month_on = ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode(),
		   	 		"member_upgrade_num"); //有期限
			if(upgrade_Month_on.equals("1")){
				Map<String,String> orderMap=new HashMap<String,String >();
				orderMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
				orderMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
				orderMap.put("orderType", "2");
				List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
				if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){//会员升级单存在
					msgList.add(LocaleUtil.getLocalText(
							jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),
							"miMember.isNotUpGrade2"));
				}
			}
			String[] limit =ConfigUtil.getConfigValue("CN", "upgrade.order.limit").split(":");
			Integer  numLimit = new Integer(limit[0]);
			Integer  kindLimit = new Integer(limit[1]);
			if(kindLimit > -1){
				Set<JpoShoppingCartOrderList> jpoShoppingCartOrderListSet =jpoShoppingCartOrder.getJpoShoppingCartOrderList();
				if(jpoShoppingCartOrderListSet.size()>kindLimit){
					msgList.add("只能买"+kindLimit+"种商品");
				}else{
					for (JpoShoppingCartOrderList jpoShoppingCartOrderList : jpoShoppingCartOrderListSet) {
						if(numLimit > -1){
							if(jpoShoppingCartOrderList.getQty()>numLimit){
								msgList.add("该商品只能买"+numLimit+"个");
							}
						}
					}
				}
			}
		}
		
		if("6".equals(jpoShoppingCartOrder.getOrderType()))//一级店铺首单
		{
			//判断店铺首单是否存在，只允许下一张店铺首单
			Map<String,String> orderMap=new HashMap<String,String >();
			orderMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
			orderMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
			orderMap.put("orderType", "6");
			List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);;
			if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){
				msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberSFOrder.isExist"));//店铺首单已经存在
			}
		}
		if("9".equals(jpoShoppingCartOrder.getOrderType())){//店铺重消
			if(!"1".equals(jmiMember.getIsstore())){//是否为一级店铺
				msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"member.isNotStore"));//会员必须为一级店铺
			}
		}
		
		if("11".equals(jpoShoppingCartOrder.getOrderType()))//二级店铺首单  
		{
			Map<String,String> orderMap=new HashMap<String,String >();
			orderMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
			orderMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
			orderMap.put("orderType","11");	
			List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
			if(jpoMemberOrders!=null &&jpoMemberOrders.size()!=0){
				msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberSSubFOrder.isExist"));//二级店铺首单已经存在
			}
		}
		if("12".equals(jpoShoppingCartOrder.getOrderType()))//二级店铺升级单
		{
			Map<String,String> orderMap=new HashMap<String,String >();
			orderMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
			orderMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
			orderMap.put("orderType", "12");
			List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);;
			if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){
       		   	msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"poMemberSSubUOrder.isExist"));//二级店铺升级单已经存在
				}
				if(!"2".equals(jmiMember.getIsstore())){//为二级店铺；会员必须为二级店铺才能下升级单
	       		  msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"member.isNotSubStore"));//会员必须为二级店铺
	   		}
		}
		if("14".equals(jpoShoppingCartOrder.getOrderType()))//二级店铺重消
		{
			if(!"2".equals(jmiMember.getIsstore())){//必须为二级店铺才能购买
				msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"member.isNotSubStore"));//会员必须为二级店铺
				
			}
		}
	}
		
		String enoughtAmount = this.checkAmount(jpoShoppingCartOrder);//购买的产品是否满足规定的总金额
		String vals[]=enoughtAmount.split(";");
		String specific="";
		 if(!"-100".equals(vals[1]))
   		 {
			 specific=ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), vals[1]);
			 if(specific ==null) specific = LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(), vals[1]);
			 
   		 }
		if("-1".equals(vals[0])){
   	
			msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"samount.notEnough")+""+specific);
			
		}
		if("1".equals(vals[0])){
			msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough")+""+specific);
			
		}
		if("2".equals(vals[0])){
			msgList.add(LocaleUtil.getLocalText(jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),"pv.notEnough1")+""+specific);
			
		}
		
		return msgList;
	

    }
    
    private boolean isLevel(String userCode){
    	
    	//当前时间所在的期别
		JmiValidLevelList validLevel = new JmiValidLevelList();
		JmiLevelLock jmiLevelLock = new JmiLevelLock();
		String curPeriod = jbdPeriodManager.getBdPeriodcurrent(); //eg:201511
		//当前期别和当前会员的手工定级记录
		validLevel = jmiValidLevelListManager.getValidLevel(curPeriod, userCode);
		
		//是否有手工定死
		jmiLevelLock = jmiLevelLockManager.getJmiLevelLock(userCode);
		
		System.out.println(validLevel+"  fff  "+jmiLevelLock);
		
		 boolean b = false;
		 if(null == validLevel && null == jmiLevelLock){
			 b = true;
		 }else{
			 if(validLevel !=null ){
				 if(validLevel.getNewMemberLevel() == 0 ){
					 b = true;
				 }
			 }
			 if(jmiLevelLock != null){
				 if(jmiLevelLock.getMemberLevel() == 0){
					 b = true;
				 }
			 }
		 } 
		 System.out.println(b);
    	return b;
    }
    
    //辅销单
	private int getPreferentialOrderF(Set poShoppingCartOrderList) {
		int booleanValue=0;
		int number=0;
		Iterator its1 = poShoppingCartOrderList.iterator();
    	while (its1.hasNext()) {
    		JpoShoppingCartOrderList jpoShoppingCartOrderList = (JpoShoppingCartOrderList) its1.next();
			if("N06010100101CN0".equals(jpoShoppingCartOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())) {
				 booleanValue=1;//代表订单通过并且不计算物流费，不允许用积分换购
				 if(number==1)
				 {
					 booleanValue = 0;
					 break;
				 }else
				 {
					 number=2;
					 
				 }		
		    }else
		    {
		    	 booleanValue=2;//代表订单通过 需要计算物流费，允许用积分换购
		    	 if(number==2)
		    	 { 
		    		 booleanValue = 0;
		    		 break;
		    	 }else
		    	 {
		    		 number=1;
		    		 
		    	 }
		    	 
		    }
		}
		return booleanValue;

	}
	//重消单
	private int getPreferentialOrderR(Set poShoppingCartOrderList) {
		int booleanValue=0;
		int number=0;
		Iterator its1 = poShoppingCartOrderList.iterator();
    	while (its1.hasNext()) {
    		JpoShoppingCartOrderList jpoShoppingCartOrderList = (JpoShoppingCartOrderList) its1.next();
			//JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			if("N07010100101CN0".equals(jpoShoppingCartOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())) {
				
				 if(number==1)
				 {
					 booleanValue = 2;
					 break;
				 }else
				 {
					 number=2;
					 
				 }		
		    }else
		    {
		    	
		    	 if(number==2)
		    	 { 
		    		 booleanValue = 2;
		    		 break;
		    	 }else
		    	 {
		    		 
		    		 number=1;
		    		 
		    	 }
		    	 
		    }
		}
	return booleanValue;

}
	
   /**
     * 检查订单总价钱
     * @param poMemberOrder
     * @return
     */
    private String checkAmount(JpoShoppingCartOrder jpoShoppingCartOrder){
    	Set<JpoShoppingCartOrderList>	scolSet=jpoShoppingCartOrder.getJpoShoppingCartOrderList();
    	Iterator<JpoShoppingCartOrderList> scolIter=scolSet.iterator();
    	BigDecimal orderPv = new BigDecimal(0);
    	BigDecimal sumPrice = new BigDecimal(0);
    	
    	while(scolIter.hasNext())
    	{
    		JpoShoppingCartOrderList  scol=scolIter.next();
    		orderPv=orderPv.add(scol.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(scol.getQty())));
    		sumPrice = sumPrice.add(scol.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(scol.getQty())));
    	}
    	
    	if(Constants.ORDER_TYPE_5.equals(jpoShoppingCartOrder.getOrderType()))//辅销单
    	{
	    	BigDecimal amount =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "member.a.order.amount"));
	    	if(amount.compareTo(sumPrice)==1){
	    		return -1+";member.a.order.amount";//表示成功
	    	}
	    	return 0+";-100";
    	}else if(Constants.ORDER_TYPE_2.equals(jpoShoppingCartOrder.getOrderType()))//升级单
    	{
    		JmiMember jmiMember = jmiMemberManager.get(jpoShoppingCartOrder.getSysUser().getUserCode());
        	
    		int memberlever = jmiMember.getMemberLevel();
			String startDateStr = ConfigUtil.getConfigValue("CN", "orderupgrade.sales.promotion.startdate");
			String endDateStr = ConfigUtil.getConfigValue("CN", "orderupgrade.sales.promotion.enddate");

			boolean salesPromotion =false;
			try {
				if(MeteorUtil.checkTime(startDateStr,endDateStr)){
					salesPromotion =true;
                }
			} catch (Exception e) {
				log.error("orderupgrade.sales.promotion.startdate or  orderupgrade.sales.promotion.enddate 参数 出错");
			}

			if(salesPromotion){
				if(jmiMemberManager.upgradePVByOrderMemberLevelCofig(memberlever, orderPv)){
					return 1+";uplever.one";
				}
			}else {
				if(jmiMemberManager.upgradePV(memberlever, orderPv)){
					return 1+";uplever.one";
				}
			}


    		return 0+";-100";
        	
    	}else if(Constants.ORDER_TYPE_4.equals(jpoShoppingCartOrder.getOrderType()))//重消单
    	{
    		      return 0+";-100";
    	}else if(Constants.ORDER_TYPE_3.equals(jpoShoppingCartOrder.getOrderType()))
    	{
    		if(jpoShoppingCartOrder.getSysUser().getJmiMember().getFreezeStatus()==0){//1代表冻结，0代表正常

    		}else{			
    			if("CN".equals(jpoShoppingCartOrder.getCompanyCode()) ){
    				BigDecimal rspv =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "rsorder.order.pv")); //252
    				if(orderPv.compareTo(rspv)==-1){
    					return 1+";rsorder.order.pv";
        			}
    			}
    			if(!"CN".equals(jpoShoppingCartOrder.getCompanyCode()) && orderPv.compareTo(new BigDecimal("126"))==-1){//其他国别的
    				BigDecimal rspv =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "rsorder.order.pv.other")); //126
    				if(orderPv.compareTo(rspv)==-1){
    					return 1+";rsorder.order.pv.other";
    				}
    			}
    			return 0+";-100";
    		}
    	}
    	else if(Constants.ORDER_TYPE_1.equals(jpoShoppingCartOrder.getOrderType()))//会员首单
    	{
    		String fpvFlag = ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(),"first.order.pv.flag");
    		//fpvFlag：on限制，off不限制首单PV
    		if(fpvFlag!=null&&"on".equals(fpvFlag)){
        		BigDecimal pv = new BigDecimal(ConfigUtil.getConfigValue(
    					jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), 
    					Constants.CARD7_PV));
    		//首单PV限制
    		if(pv.compareTo(orderPv)==1){
        		return 1+";"+Constants.CARD7_PV;
        	}
    	}	
        	return 0+";-100";//成功
    		
    	}else if(Constants.ORDER_TYPE_6.equals(jpoShoppingCartOrder.getOrderType()))//一级店铺首购
    	{
    		BigDecimal amount  =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "store.f.order.amount"));//250000
        	if(amount.compareTo(sumPrice)==1){
        		return -1+";store.f.order.amount";
        	}
        	return 0+";-100";
    	}else if("9".equals(jpoShoppingCartOrder.getOrderType()))//一级店铺重消单
    	{
    	 	BigDecimal amount =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "store.r.order.amount")); //0
        	if(amount.compareTo(sumPrice)==1){
        		return -1+";store.r.order.amount";
        	}
        	return 0+";-100";//总金额不足
    	}else if("11".equals(jpoShoppingCartOrder.getOrderType())) //二级店铺首购
    	{
    		String language = jpoShoppingCartOrder.getSysUser().
    				getCompanyCode().toUpperCase();
    		
    		BigDecimal amount = new BigDecimal(ConfigUtil.getConfigValue(
    				language, "store.f2.order.amount"));//87000
    		 
//    		String temCode = jmiTeamManager.teamStr(
//    				jpoShoppingCartOrder.getSysUser());
    		
//    		String am_ygd = ConfigUtil.getConfigValue(
//    				language, "store.f2.order.amount_ygd");
    		
//    		log.info("temCode is:["+temCode+"] and amygd =["+am_ygd+"]");
    		
//    		if(temCode.equalsIgnoreCase("CN12365064") && 
//    				StringUtils.isNotBlank(am_ygd)){
//    			amount = new BigDecimal(am_ygd);//85000
//    			if(amount.compareTo(sumPrice)==1){
//            		return -1+";store.f2.order.amount_ygd";//总金额不足
//            	}
//    		} else {
    			if(amount.compareTo(sumPrice)==1){
            		return -1+";store.f2.order.amount";//总金额不足
            	}
//    		}
        	
        	if("HK".equals(jpoShoppingCartOrder.getCompanyCode())){
        		BigDecimal pv = new BigDecimal(ConfigUtil.
        				getConfigValue(language, "store.f2.order.pv"));//3360
            	if(pv.compareTo(orderPv)==1){
            		return 1+"store.f2.order.pv";//总pv不足
            	}
        	}
        	return 0+";-100";
    	}else if("12".equals(jpoShoppingCartOrder.getOrderType())) //二级店铺升级单
    	{
        	BigDecimal pv =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "store.u2.order.pv"));//0pv
        	if(pv.compareTo(orderPv)==1){
        		return 1+";store.u2.order.pv";
        	}
        	BigDecimal amount =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "store.u2.order.amount"));//165000
        	if(amount.compareTo(sumPrice)==1){
        		return -1+";store.u2.order.amount";
        	}
        	return 0+";-100";
    	}else if("14".equals(jpoShoppingCartOrder.getOrderType())) //二级店铺重消
    	{
    		BigDecimal amount =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "store.r2.order.amount")); //0
        	if(amount.compareTo(sumPrice )==1){
        		return -1+";store.r2.order.amount";
        	}
        	return 0+";-100";
    	}else if("40".equals(jpoShoppingCartOrder.getOrderType())) //全年重消单
    	{
    		BigDecimal amount =new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "rorderyear.order.amount")); //3024
        	if(amount.compareTo(orderPv)==1){
        		return 1+";rorderyear.order.amount";
        	}
        	return 0+";-100";
    	}
    	return -1+";-100";
    }
   
  
     /**
      * 首单判断是否购买了某个产品
      * @param request
      * @param jpoMemberOrderSet
      * @param jpoMemberOrder
      */
     private int calcOrderAmount(HttpServletRequest request, JpoShoppingCartOrder  jpoShoppingCartOrder){	
   	    BigDecimal sumPrice = new BigDecimal(0);
     	BigDecimal sumPV = new BigDecimal(0);
        BigDecimal sumPriceF=new BigDecimal(0);//辅销品价格总计
     	Set<JpoShoppingCartOrderList>	scolSet=jpoShoppingCartOrder.getJpoShoppingCartOrderList();
	    Iterator<JpoShoppingCartOrderList> scolIter=scolSet.iterator();
	   
    	String temCode = jmiTeamManager.teamStr(
    			jpoShoppingCartOrder.getSysUser());
    	
    	if("1".equals(jpoShoppingCartOrder.getOrderType()))
	    {
    		scolIter = scolSet.iterator();
	    	while (scolIter.hasNext()) {
	    		JpoShoppingCartOrderList scol = (JpoShoppingCartOrderList) scolIter.next();
				sumPrice = sumPrice.add(scol.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(scol.getQty())));
				sumPV =sumPV.add(scol.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(scol.getQty())));
	    	}
			//安杰玛 团队:CN98708037
	    	if(temCode.equals("CN98708037")){
	    		BigDecimal pv = new BigDecimal(ConfigUtil.
	    				getConfigValue("CN", "cn98708037.pv"));
	    		if(sumPV.compareTo(pv) < 0){
	    			return 3;
	    		}
	    	}
     	if("CN".equals(jpoShoppingCartOrder.getCompanyCode()))
     		
     	{
     		java.util.Calendar endc=java.util.Calendar.getInstance();
     		endc.set(2012, 8, 28, 23, 59, 59);
     		java.util.Date endDate=endc.getTime();
     		Date curDate=new Date();
     		if((curDate.after(endDate))){
     		boolean isPass = false;
     		boolean validTeam=false;
     		boolean validF=false;//判断是否购买了辅销品
     		boolean vaildS=false;//判断是否购买了事业锦囊
     		String  effectiveValid=request.getParameter("effectiveValid");//判断是否需要强制绑定事业锦囊，true不强制，false强制	
            if(effectiveValid==null){
            	effectiveValid = (String)request.getAttribute("effectiveValid");
            }
            
            
            
     		
     		// 广州排毒  云南团队(推荐网)  骆天团队(推荐网) 太极养生团队(宋自甫团队)(推荐网)  任伟团队（口服液）
     		Map<String,Object> map=jpmProductSaleNewManager.getJmiMemberTeamMap("0");  //判断团队是否需要绑定事业锦囊，判断不绑定事业锦囊的团队
     		  Iterator it = map.entrySet().iterator();
     	        while (it.hasNext()) {
     	            Map.Entry entry = (Map.Entry) it.next();
     	            Object key = entry.getKey();
     	            Boolean value =(Boolean)entry.getValue();
     	            if(!value)
     	            {
     	            	if(key.equals(jpoShoppingCartOrder.getTeamType()))
     	            	{
     	            		isPass = true;
     	 	    			validTeam=true;
     	 	    			validF=true;
     	 	    			break;
     	            	}
     	            }
     	            
     	        }
     	        
 	  	    if(!validTeam)//判断是否购买了事业锦囊
 	  	    {
 	  	    	   log.info("effectiveValid is:["+effectiveValid+"]");
 		    			if(effectiveValid.equals("true"))//是否需要强制绑定事业锦囊true为否，false为是
 		    			{
 		    				validF = true;
    	    		    	vaildS=true;
	    	    		    	
 		    				scolIter=scolSet.iterator();
 		    				   while (scolIter.hasNext()) {
 		    					  JpoShoppingCartOrderList scol = (JpoShoppingCartOrderList) scolIter.next();	
 			   	    	    		    if("P08520100101CN0".equals(scol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo()))
 			   	    	    		    {
   
 			   	    	    		    	validF = true;
 			   	    	    		    	vaildS=true;
 			   	        	    			break;
 			   	    	    		    }
 			   	    	    		    else if("A".equals(scol.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getSmNo())){
 			   	        	    			sumPriceF=sumPriceF.add(scol.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(scol.getQty())));
 			   	        	    		}
 			   	    	           }

 		    			}else //强制购买事业锦囊
 		    			{
 		    				scolIter=scolSet.iterator();
 		    	           while (scolIter.hasNext()) {
 		    	        	 JpoShoppingCartOrderList scol = (JpoShoppingCartOrderList) scolIter.next();	
 		        	    		if("P08520100101CN0".equals(scol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())){
 		        	    			isPass = true;
 		        	    			break;
 		        	    		}	
 		    	          }
 		    		 }	
 	    	}
 	  	    	
 			  	  if(effectiveValid.equals("true"))
 			  	  { 
	 			  		if(!vaildS)//没有购买事业锦囊
	 			  		{
	 			  		 if(sumPriceF.compareTo(new BigDecimal("200"))==-1)
	    	    			 {
	    	    			  validF = false;
	    	    			 }else
	    	    			 {
	    	    				validF=true;
	    	    			 }
	 			  		}
	 			  		 
	 			  		 if(validF==false)
	 			   		  {
	 			   			return 20;// 必须购买事业锦囊或者辅销品
	 			   		  } 	  
 			  	  }else
 			  	  {
 			    	  	 if(isPass==false)
 				   		  {
 				   			return 2;// 必须订购资料包
 				   		  }   		
 		    	  }
     		}
     		
     	}else if("TW".equals(jpoShoppingCartOrder.getCompanyCode()) || "PH".equals(jpoShoppingCartOrder.getCompanyCode())){//台湾，菲律宾
     		boolean isPass = false;
     		java.util.Calendar startc=java.util.Calendar.getInstance();
     		startc.set(2010, 6, 8, 00, 00, 00);
     		java.util.Calendar endc=java.util.Calendar.getInstance();
     		endc.set(2010, 11, 31, 23, 59, 59);
     		java.util.Date startDate=startc.getTime();
     		java.util.Date endDate=endc.getTime();
     		Date curDate=new Date();
     		BigDecimal pv = new BigDecimal(ConfigUtil.getConfigValue(jpoShoppingCartOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.1.value"));
     		if(sumPV.compareTo(pv)!=-1 && "PH".equals(jpoShoppingCartOrder.getCompanyCode())){//菲律宾会员订单pv大于银级pv值，则判断是否购买了产品N02010100101EN0
     			scolIter = scolSet.iterator();
     	        while (scolIter.hasNext()) {
     	        	JpoShoppingCartOrderList jpoMemberOrderList = (JpoShoppingCartOrderList) scolIter.next();
     	    		if("PH".equals(jpoShoppingCartOrder.getCompanyCode())){
         	    		if("N02010100101EN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct())){
         	    			isPass = true;
         	    			break;
         	    		}
     	    			isPass = true;
     	    			break;
     	    		}
     	        }
     		}else if(sumPV.compareTo(new BigDecimal(70))!=-1 && "TW".equals(jpoShoppingCartOrder.getCompanyCode())){//台湾会员订单pv大于银级pv值，则判断是否购买了产品P09090900909TW0
     			scolIter = scolSet.iterator();
     	        while (scolIter.hasNext()) {
     	        	JpoShoppingCartOrderList scol = (JpoShoppingCartOrderList) scolIter.next();
     	    		if("TW".equals(jpoShoppingCartOrder.getCompanyCode())){
         	    		if("P09090900909TW0".equals(scol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())){
         	    			//100702簽呈
         	    			if((curDate.after(startDate))&&(curDate.before(endDate))){
         	    	    		if(sumPV.compareTo(new BigDecimal(700))!=-1){
     	        	    			sumPrice = sumPrice.subtract(scol.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(scol.getQty())));
     	        	    			scol.getJpmProductSaleTeamType().setPrice(new BigDecimal("0"));
         	    	    		}
         	    	    	}
         	    			isPass = true;
         	    			break;
         	    		}
     	    		}
     	        }
     	    }else{
     	     	scolIter = scolSet.iterator();
     	        while (scolIter.hasNext()) {
     	        	JpoShoppingCartOrderList scol = (JpoShoppingCartOrderList) scolIter.next();
     	    		if("TW".equals(jpoShoppingCartOrder.getCompanyCode())){
         	    		if("P09090900909TW0".equals(scol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())){
         	    			isPass = true;
         	    			break;
         	    		}
     	    		}
     	    		if("PH".equals(jpoShoppingCartOrder.getCompanyCode())){
         	    		if("N02010100101EN0".equals(scol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())){
         	    			isPass = true;
         	    			break;
         	    		}
     	    		}
     	        }
     	    }
     		if(isPass == false){
     			//入会费没有
         		return 1;
     		}
     	}
	    }else if("6".equals(jpoShoppingCartOrder.getOrderType()))
	    {
	    	
	    	boolean isPass = false;
	    	scolIter = scolSet.iterator();
	    	
	    	while (scolIter.hasNext()) {
	    		
	    		JpoShoppingCartOrderList scol = 
	    				(JpoShoppingCartOrderList) scolIter.next();
				sumPrice = sumPrice.add(
						scol.getJpmProductSaleTeamType().getPrice().
						multiply(new BigDecimal(scol.getQty())));
				sumPV =sumPV.add(
						scol.getJpmProductSaleTeamType().getPv().
						multiply(new BigDecimal(scol.getQty())));
				
				String proNo = scol.getJpmProductSaleTeamType().
	    				getJpmProductSaleNew().getProductNo();
				//TODO YGD 团队:CN12365064
//				if(temCode.equalsIgnoreCase("CN12365064")){
//					if("N01200200201CN0".equalsIgnoreCase(proNo)){
//		    			isPass = true;
//		    		}
//				} else {
					//一级生活馆辅销品+保证金+生活馆展示产品
		    		if("N01200200101CN0".equalsIgnoreCase(proNo)){
		    			isPass = true;
		    		}
//				}
			}
			if(isPass == false){//判断是否购买了保证金
				//入会费没有
	    		return 1;
			}
	    }else if("11".equals(jpoShoppingCartOrder.getOrderType()))
	    {
	      	boolean isPass = false;
	      	scolIter = scolSet.iterator();
	    	while (scolIter.hasNext()) {
	    		JpoShoppingCartOrderList scol = (JpoShoppingCartOrderList) scolIter.next();
				sumPrice = sumPrice.add(scol.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(scol.getQty())));
				sumPV =sumPV.add(scol.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(scol.getQty())));
				
				String proNo = scol.getJpmProductSaleTeamType().
						getJpmProductSaleNew().getProductNo();
				
//				if(temCode.equalsIgnoreCase("CN12365064")){
//					if("N01190100201CN0".equalsIgnoreCase(proNo)){ 
//		    			isPass = true;
//		    		}
//				} else {
					//二级生活馆辅销品+保证金
					if("N01190100101CN0".equalsIgnoreCase(proNo)){ 	
		    			isPass = true;
		    		}
//				}
			}	    	
			if(isPass == false && "CN".equals(jpoShoppingCartOrder.getCompanyCode())){
				//入会费没有
	    		return 1;
			}
	    	
	    	return 0;
	    }else if("12".equals(jpoShoppingCartOrder.getOrderType()))
	    {
	    	boolean isPass = false;
	    	scolIter = scolSet.iterator();
	    	while (scolIter.hasNext()) {
	    		JpoShoppingCartOrderList scol = (JpoShoppingCartOrderList) scolIter.next();
				sumPrice = sumPrice.add(scol.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(scol.getQty())));
				sumPV =sumPV.add(scol.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(scol.getQty())));
	    		
				String proNo = scol.getJpmProductSaleTeamType().
						getJpmProductSaleNew().getProductNo();
				
//				if(temCode.equalsIgnoreCase("CN12365064")){
//					if("N02330200201CN0".equalsIgnoreCase(proNo)){
//		    			isPass = true;
//		    		}
//				} else {
					//二级晋升一级保证金+生活馆展示产品
					if("N02330200101CN0".equalsIgnoreCase(proNo)){
		    			isPass = true;
		    		}
//				}
				
			}
			if(isPass == false){
				//入会费没有
	    		return 1;
			}
	    	return 0;
	    }
     	return 0;
     }
     //香港入会费
     private int verificationProduct(HttpServletRequest request,JpoShoppingCartOrder  jpoShoppingCartOrder){
    	Set<JpoShoppingCartOrderList>	scolSet=jpoShoppingCartOrder.getJpoShoppingCartOrderList();
 	    Iterator<JpoShoppingCartOrderList> scolIter=scolSet.iterator();
     	BigDecimal pvY = new BigDecimal(ConfigUtil.getConfigValue("HK", "cardtype.2.value"));	//金级会员
     	BigDecimal sumPV=new BigDecimal("0");
 		while(scolIter.hasNext()){
 			JpoShoppingCartOrderList  scol = (JpoShoppingCartOrderList)scolIter.next();
 			sumPV = sumPV.add(scol.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(scol.getQty()))) ;
 			if(sumPV.compareTo(pvY)!=-1){//金级以下会员必须购买入会费
 			if(scol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo().equals("N01010100101HK0")){	
 			   return 0;
 			}
 			}else
 			{
 				return 0;
 			}
 		}
 		return 1;
 	} 
     
     private Map<String, List<String>> checkOrder(List<JpoShoppingCartOrder> scList,JsysUser jsysUser,HttpServletRequest request){
    	 Map<String, List<String>> msgMap = new HashMap<String, List<String>>(); 
    	 try{
    		 ListUtil listUtil=new ListUtil();
    		 if(scList!=null && scList.size()>0){
    			 	
    	 	    	for(int i=0;i<scList.size();i++){   
    	 	    		//端午促销商品控制 start
    	 	    		boolean isOnly=false,isOther=false;
    	 	    		JpoShoppingCartOrder carOrder =scList.get(i);
    	 	    		Iterator<JpoShoppingCartOrderList> ite = carOrder.
    	 	    				getJpoShoppingCartOrderList().iterator();
    	 	    		
    	 	    		while(ite.hasNext()){
    	 	    			JpoShoppingCartOrderList carOrderList = ite.next();
    	 	    			String carProNo = carOrderList.getJpmProductSaleTeamType().
    	 	    					getJpmProductSaleNew().getProductNo();
    	 	    			
    	 	    			if(Constants.PRONO.equalsIgnoreCase(carProNo) || 
    		        				Constants.PRONO1.equalsIgnoreCase(carProNo)||
    		        				Constants.PRONO2.equalsIgnoreCase(carProNo)){
    		        			
    		        			isOnly =true;
    		        		}
    		        		if(! Constants.PRONO.equalsIgnoreCase(carProNo) && 
    		        				!Constants.PRONO1.equalsIgnoreCase(carProNo)&& 
    		        				!Constants.PRONO2.equalsIgnoreCase(carProNo)){
    		        			
    		        			isOther =true;
    		        		}
    		        	}
    		        	
    	 	    		if(isOnly && isOther){
    		        		List<String> strList = new ArrayList<String>();
    		        		strList.add(LocaleUtil.getLocalText(jsysUser.getDefCharacterCoding(),"duanwuinfo"));
    		        		msgMap.put("温馨提示",strList);
    		        	}
    	 	    		
    	 	    		List<String> list = proIsOnly(carOrder);
    		        	if(list !=null && list.size()>0){
    		        		msgMap.put("温馨提示",list) ;
    		        	}
    	 	    		
    	 	    		//end
    	 	    		
    	 	    		if("1".equals(scList.get(i).getOrderType()))
    		    		{
    		    			List<String> fMsg=validOrderTypeF(scList.get(i),request);
    		    			if(fMsg!=null && fMsg.size()>0)
    		    			{
    		    			 msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "1"), fMsg);
    		    			}
    		    			
    		    			
    		    		}else if("2".equals(scList.get(i).getOrderType()))
    		    		{
    		    			List<String> uMsg=validOrderTypeU(scList.get(i));
    		    			if(uMsg!=null && uMsg.size()>0)
    		    			{
    		    			msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "2"), uMsg);
    		    			}
    		    			
    		    		}else if("3".equals(scList.get(i).getOrderType()))
    		    		{
    		    			List<String> rsMsg=validOrderTypeRs(scList.get(i));
    		    			if(rsMsg!=null && rsMsg.size()>0)
    		    			{
    		    				msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "3"), rsMsg);
    		    			}		
    		    		}else if("4".equals(scList.get(i).getOrderType()))
    		    		{
    		    			List<String> rMsg=validOrderTypeR(scList.get(i));
    		    			if(rMsg!=null && rMsg.size()>0){
    		    			msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "4"), rMsg);
    		    			}	
    		    		}else if("5".equals(scList.get(i).getOrderType()))
    		    		{
    		    		  List<String> aMsg=this.validOrderTypeA(scList.get(i));
    		    		  if(aMsg!=null && aMsg.size()>0)
    		    		  {
    		    			  msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "5"), aMsg);
    		    		  }
    		    		}else if("6".equals(scList.get(i).getOrderType()))
    		    		{
    		    			 List<String> shopfMsg=validOrderTypeShopF(scList.get(i));
    		    			 if(shopfMsg!=null && shopfMsg.size()>0)
    		    			 {
    		    			  msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "6"), shopfMsg);
    		    			 }	
    		    		}else if("9".equals(scList.get(i).getOrderType()))//店铺重消单
    		    		{
    		    			List<String> shoprMsg=validOrderTypeShopR(scList.get(i));
    		    			if(shoprMsg!=null && shoprMsg.size()>0)
    		    			{
    		    			 msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "9"), shoprMsg);
    		    			}
    		    		}else if("11".equals(scList.get(i).getOrderType()))//二级店铺首购
    		    		{
    		    			
    		    			List<String> sshopFMsg=validOrderTypeSShopF(scList.get(i));
    		    			if(sshopFMsg!=null && sshopFMsg.size()>0)
    		    			{
    		    				 msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "11"), sshopFMsg);
    		    			}
    		    			
    		    		}else if("12".equals(scList.get(i).getOrderType()))//二级店铺升级单
    		    		{
    		    			List<String> sshopUMsg=validOrderTypeSShopU(scList.get(i));
    		    			if(sshopUMsg!=null && sshopUMsg.size()>0)
    		    			{
    		    				 msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "12"), sshopUMsg);
    		    			}
    		    			
    		    		}else if("14".equals(scList.get(i).getOrderType()))
    		    		{
    		    			List<String> sshopRMsg=validOrderTypeSShopR(scList.get(i));
    		    			if(sshopRMsg!=null && sshopRMsg.size()>0)
    		    			{
    		    		       msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "14"), sshopRMsg);
    		    			}
    		    		}else if("40".equals(scList.get(i).getOrderType()))
    		    		{
    		    			List<String> rYearMsg=validOrderRYear(scList.get(i));
    		    			if(rYearMsg!=null && rYearMsg.size()>0)
    		    			{
    		    		       msgMap.put(listUtil.getListValue(jsysUser.getDefCharacterCoding(), "po.all.order_type", "40"), rYearMsg);
    		    			}
    		    		}
    	 	    		
    	 	    		
    	 	    	 }
    	     	}
    	 }catch(Exception e){
    		 log.error("check order error.",e);
    	 }
    	return msgMap;
     }
     
     /**
      * 升级单升级期限
      * @return  true 可以下升级单 
      */
     public boolean upGradeInTime(JsysUser sysUser){
     	
     	String upgrade_Month_on = ConfigUtil.getConfigValue(sysUser.getCompanyCode(),
 		"member_upgrade_on"); //有期限
     	String upgrade_Month = ConfigUtil.getConfigValue(sysUser.getCompanyCode(),
 				"member_upgrade");  //月份期限
 		
     	String userCode = sysUser.getUserCode();
		JsysUserRole userRole = jsysUserRoleManager.getSysUserRoleByUserCode(userCode);
		JsysRole role = jsysRoleManager.get(userRole.getRoleId());
		
 		Map<String,String> orderFirstMap = new HashMap<String,String >();
 		orderFirstMap.put("userCode", sysUser.getUserCode());
 		orderFirstMap.put("companyCode", sysUser.getCompanyCode());
 		orderFirstMap.put("orderType", Constants.ORDER_TYPE_1);
 		orderFirstMap.put("status", "2");
 		List orderList = jpoMemberOrderManager.getOrderByParam(orderFirstMap);
 		
 		Calendar curDate = Calendar.getInstance();
 		Calendar endDate = Calendar.getInstance();
 		
 		JpoMemberOrder orderF = new JpoMemberOrder();
		if(orderList == null){
			endDate.setTime(sysUser.getJmiMember().getCheckDate()); 
		}else{
			orderF = (JpoMemberOrder)orderList.get(0);
			endDate.setTime(orderF.getCheckTime());
		}
		
		endDate.add(Calendar.DAY_OF_MONTH, 1);
		endDate.set(Calendar.HOUR_OF_DAY, 0);
		endDate.set(Calendar.MINUTE, 0);
		endDate.set(Calendar.SECOND, 0);
		endDate.set(Calendar.DATE, endDate.get(Calendar.DATE)+Integer.parseInt(upgrade_Month));
// 		endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH)+Integer.parseInt(upgrade_Month));
// 		endDate.set(Calendar.DAY_OF_MONTH, 1);
 		
 		log.info("userNo is:"+userCode+" roleCode is:"+role.getRoleCode());
 		String cn1000 = ConfigUtil.getConfigValue("CN", "member_role_id.1");
 		//之前的会员可能会有升级单,升级截止时间为:2017-4-30号之前,不受三个月限制
 		if(role.getRoleCode().equalsIgnoreCase(cn1000)){
 			//优惠顾客升级截止日期
 			String startDateS = ConfigUtil.getConfigValue("CN", "member.roid.1.upgradedate");
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 		    try {
 		    	log.info("startDateS is:"+startDateS);
 		    	Calendar up_DATE = Calendar.getInstance();
 		    	up_DATE.setTime(sdf.parse(startDateS));
 				if(curDate.after(up_DATE)){
 					return false;
 				}
 				
 			} catch (ParseException e) {
 				log.info("member.roid.1.upgradedate 时间参数有误.");
 				e.printStackTrace();
 			}
 		} else {
 			log.info("year :"+endDate.get(Calendar.YEAR)+" month:"+endDate.get(Calendar.MONTH));
 			if(upgrade_Month_on.equals("1") && curDate.after(endDate)){
 				log.info("");
 	 			return  false;
 	 		}
 		}
 	
 		return true; 
     	
     }
}
