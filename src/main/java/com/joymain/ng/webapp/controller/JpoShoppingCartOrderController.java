package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JsysUser;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpoShoppingCartOrders/")
public class JpoShoppingCartOrderController {
	
	private Log log = LogFactory.getLog(JpoShoppingCartOrderController.class);
	
    private JpoShoppingCartOrderManager jpoShoppingCartOrderManager;
    @Autowired
    private JpoMemberOrderManager jpoMemberOrderManager;
    @Autowired
    private JmiMemberDao jmiMemberDao;
    @Autowired
    private JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager;
    
    @Autowired
    private JmiTeamManager jmiTeamManager;
    
    @Autowired
    public void setJpoShoppingCartOrderManager(JpoShoppingCartOrderManager jpoShoppingCartOrderManager) {
        this.jpoShoppingCartOrderManager = jpoShoppingCartOrderManager;
    }
    
    @RequestMapping(value="orderAll",method = RequestMethod.GET)
    public String scAll(Model model,HttpServletRequest request,HttpServletResponse response)
    throws Exception {
     	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //判断是否能升级
     	JmiMember jmiMember = jmiMemberDao.getJmiMember(jsysUser.getUserCode());
     	Map<String,String> mapUp=new HashMap<String,String >();
     	mapUp.put("userCode", jsysUser.getUserCode());
     	mapUp.put("companyCode", jsysUser.getCompanyCode());
     	mapUp.put("orderType", Constants.ORDER_TYPE_2);
     	mapUp.put("status", "2");
		List orderup = jpoMemberOrderManager.getOrderByParam(mapUp);
		//0:可多次升级 1:只可以升一次
		String upNum = ConfigUtil.getConfigValue("CN", "member_upgrade_num");
		String upGrade ="1";
		log.info("member_upgrade_num is:"+upNum);
		if(upNum.equals("1")){
			if(orderup != null && orderup.size()>0){
				upGrade="0";
	    		//jsysUser.setUpGrade("0");//1可以下升级单，0表示不能下升级单
	    	}
//			else{
//	            jsysUser.setUpGrade(this.getUpGradeValue(jsysUser));
//	           
//	    	}
		}
		
		if(Constants.CARDTYPE_5000.equals(jmiMember.getMemberLevel().toString())){
			upGrade="0";
			//jsysUser.setUpGrade("0");//1可以下升级单，0表示不能下升级单
		}
		/*
        try{
        	
    		 * TODO:6.7号－9.26号 促销
    		
            BigDecimal pv1 = new BigDecimal(70);
    		String str_startDate = LocaleUtil.getLocalText("zh_CN", "start.date");
    		//2014-09-26 23:59:59
    		String str_endDate = LocaleUtil.getLocalText("zh_CN", "end.date");
    		
    		log.info("str_startDate is["+str_startDate+"] " +
    				"str_endDate is:"+str_endDate);
    		
    		Date starDate = DateUtil.convertStringToDate(
    				"yyyy-MM-dd hh:mm:ss", str_startDate);
    		Date endDate = DateUtil.convertStringToDate(
    				"yyyy-MM-dd hh:mm:ss", str_endDate);
    		
    		Date curDate = Calendar.getInstance().getTime();
    		
    		log.info("starDate is["+starDate+"] " +
    				" and endDate is:["+endDate+"] " +
    				" and curDate is:["+curDate+"]");
    		
    		if(curDate.after(starDate) && curDate.before(endDate)){
    			pv1 = new BigDecimal("0");
    		}
            request.setAttribute("orderPv", pv1);
            
        }catch(Exception e){
        	log.error("",e);
        }
         */
      
        request.setAttribute("upGrade", upGrade);
        request.setAttribute("member", jmiMember);
        
        //modify by fu 2016-09-19 查询出对应订单类型的推荐商品----begin
   	     String isRecommand = "1";
   	     String teamType=jmiTeamManager.teamStr(jsysUser);//获取团队编号
   	     request.setAttribute("teamType", teamType);
   	     //续约单
   	     if(1==jmiMember.getFreezeStatus()){
               List<JpmProductSaleTeamType> recommendProductListXYD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "3");
               request.setAttribute("recommendProductListXYD", recommendProductListXYD);
   	      }
   	     
   	     //云店资格单
    	 List<JpmProductSaleTeamType> recommendProductList41 = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "41");
         request.setAttribute("recommendProductList41", recommendProductList41);
         String ydzgd = "0";
        //待审会员允许下360年费单 //398会员允许下360年费单
         if(jmiMember.getMemberLevel()==0 || jmiMember.getMemberLevel()==398){
        	 ydzgd = "1";
         }
         
   	     //是云店类型会员，但还没达成资格，说明还没下单
   	     if("2".equals(jmiMember.getMemberUserType()) && (null==jmiMember.getCloudEnddate() || "".equals(jmiMember.getCloudEnddate()))){
   	    	ydzgd = "1";
   	     }else{
   	    	 //除了优惠顾客级别，判断达成云客或脉客的时间为结束期前一个月，即可下云店资格单	COMMENT ON COLUMN JMI_MEMBER.CLOUD_ENDDATE IS '云店资格有效期，结束时间';
    	    Calendar cal = Calendar.getInstance();
 			cal.add(Calendar.MONTH, 1);
 			Date d1 = jmiMember.getCloudEnddate();
 			if(d1!=null){
 				 if(cal.getTime().after(d1)){	
 					ydzgd = "1";
 				 }
 			 }
   	     }
   	     request.setAttribute("ydzgd", ydzgd);
   	     
         //首购单
   	     if((0==jmiMember.getFreezeStatus())&&(0==jmiMember.getNotFirst())){
               List<JpmProductSaleTeamType> recommendProductListSGD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "1");
               request.setAttribute("recommendProductListSGD", recommendProductListSGD);
               
               //如果已经有首购单存在了，也不给再重复下单，需要删除首购单再给下单
               Map<String,String> orderMap=new HashMap<String,String >();
	       		orderMap.put("userCode", jsysUser.getUserCode());
	       		orderMap.put("companyCode", jsysUser.getCompanyCode());
	       		orderMap.put("orderType", "1");
	       		List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
	       		if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){//会员首单存在,不能再下首购单
	       			request.setAttribute("noSGD", 1);
	       		}
   	     }
   	     //398首购单
   	     if((0==jmiMember.getFreezeStatus())&&(0==jmiMember.getNotFirst())&&!jmiMember.getMemberLevel().equals("500")){
   	    	 List<JpmProductSaleTeamType> recommendProductListSGD398 = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "42");
   	    	 request.setAttribute("recommendProductListSGD398", recommendProductListSGD398);
   	    	 
   	    	 //如果已经有398首购单存在了，也不给再重复下单，需要删除首购单再给下单
   	    	 Map<String,String> orderMap=new HashMap<String,String >();
   	    	 orderMap.put("userCode", jsysUser.getUserCode());
   	    	 orderMap.put("companyCode", jsysUser.getCompanyCode());
   	    	 orderMap.put("orderType", "42");
   	    	 List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
   	    	 if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){//会员398首单存在,不能再下398首购单
   	    		 request.setAttribute("noSGD398", 1);
   	    	 }
   	     }
   	     
   	     
   	  //代金券换购单
   	     List<JpmProductSaleTeamType> recommendProductListDJQ = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "93");
   	     request.setAttribute("recommendProductListDJQ", recommendProductListDJQ);
   	     
   	     //顾客重消单
   	   //  if(jmiMember.getMemberLevel()==398){
   	    	 List<JpmProductSaleTeamType> recommendProductListGC398 = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "43");
             request.setAttribute("recommendProductListGC398", recommendProductListGC398);
   	     //}
         
   	     if((0==jmiMember.getFreezeStatus())&&(1==jmiMember.getNotFirst())){
   	    	  //升级单
 	    	   List<JpmProductSaleTeamType> recommendProductListSJD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, Constants.ORDER_TYPE_2);
 	    	   request.setAttribute("recommendProductListSJD", recommendProductListSJD);
   	    	 	
   	           //重消单
   	    	   List<JpmProductSaleTeamType> recommendProductListCXD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "4");
   	    	   //List<JpmProductSaleTeamType> recommendProductListCXD = null;
   	    	   request.setAttribute("recommendProductListCXD", recommendProductListCXD);
   	    	   if(!MeteorUtil.isBlankByList(recommendProductListCXD)){
   	    		   if(recommendProductListCXD.size()<6){
   	    			request.setAttribute("recommendProductListTDbfb", (6-recommendProductListCXD.size()+1)*(100/6));
   	    		   }
   	    	   }
               
               //辅消单
   	    	   List<JpmProductSaleTeamType> recommendProductListFXD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "5");
               request.setAttribute("recommendProductListFXD", recommendProductListFXD);
               
               //二级店铺首购单
   	    	   List<JpmProductSaleTeamType> recommendProductListEJDPSGD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "11");
               request.setAttribute("recommendProductListEJDPSGD", recommendProductListEJDPSGD);
             
               //二级店铺升级单
   	    	   List<JpmProductSaleTeamType> recommendProductListEJDPSJD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "12");
               request.setAttribute("recommendProductListEJDPSJD", recommendProductListEJDPSJD);
               
               //二级店铺重消单
   	    	   List<JpmProductSaleTeamType> recommendProductListEJDPCXD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "14");
               request.setAttribute("recommendProductListEJDPCXD", recommendProductListEJDPCXD);
               
               //一级店铺首购单
   	    	   List<JpmProductSaleTeamType> recommendProductListYJDPSGD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "6");
               request.setAttribute("recommendProductListYJDPSGD", recommendProductListYJDPSGD);
               
               //一级店铺重消单
   	    	   List<JpmProductSaleTeamType> recommendProductListYJDPCXD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "9");
               request.setAttribute("recommendProductListYJDPCXD", recommendProductListYJDPCXD);
               
               //全年重消单
   	    	   List<JpmProductSaleTeamType> recommendProductListQNCXD = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "40");
               request.setAttribute("recommendProductListQNCXD", recommendProductListQNCXD);

			  //抵用券
			  List<JpmProductSaleTeamType> recommendProductListP = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "16");
			  request.setAttribute("recommendProductListP", recommendProductListP);
			  //360年费单
			 /* List<JpmProductSaleTeamType> recommendProductList360 = jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, "36");
			  request.setAttribute("recommendProductList360", recommendProductList360);*/
   	     }
         //modify by fu 2016-09-19 查询出对应订单类型的推荐商品----end
        
        
//    	Integer notFirst = jmiMemberDao.getIsNoFirst(jsysUser.getJmiMember());
//     	if(notFirst == 1){
//     		jsysUser.getJmiMember().setNotFirst(1);
//     	}
//        request.setAttribute("member", jsysUser.getJmiMember());
        
    	return "jpoShoppingCartOrders";
    }
    
    
    /**
     * 判断是否满足升级条件
     * @param sysUser
     * @return
     */
    private String getUpGradeValue(JsysUser sysUser){
    	
    	int member_upgrade_time = Integer.parseInt(ConfigUtil.getConfigValue(sysUser.getCompanyCode().toUpperCase(),"member_upgrade_time"));//会员在规定时间里只能升几次
        boolean isUpGrade1 = checkMiMemberIsUpGraded(sysUser,member_upgrade_time);
        log.info("isUpGrade1="+isUpGrade1);
        if(!isUpGrade1){
    		return "0";
        }
        
        int days = Integer.parseInt(ConfigUtil.getConfigValue(sysUser.getCompanyCode().toUpperCase(),"member_upgrade_day"));//会员的是否满级和是否超过60天
    	boolean isUpGrade2 = checkMiMemberIsGrade(sysUser,days);
    	log.info("isUpGrade2="+isUpGrade2);
    	if(!isUpGrade2){
    		return "0";//不允许下升级单
    	}
    	return "1";//允许下升级单
    }
    /**
     * 会员在MEMBER_UPGRADE_DAY时间内可几次
     * @param sysUser
     * @param member_upgrade_time
     * @return
     */
    private boolean checkMiMemberIsUpGraded(JsysUser sysUser, int member_upgrade_time){
        JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
        jpoMemberOrder.setSysUser(sysUser);
        jpoMemberOrder.setOrderType("2");//升级单
        jpoMemberOrder.setStatus("1");//待审单
        List poMemberOrderList1 = jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
        if(poMemberOrderList1.size() > 0){//不允许在下升级单
        	return false;
        }
        jpoMemberOrder.setSysUser(sysUser);
        jpoMemberOrder.setOrderType("2");
        jpoMemberOrder.setStatus("2");
        List poMemberOrderList2 = jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
    	if(poMemberOrderList2.size() < member_upgrade_time){//审核过的订单总数
    		return true;
    	}
    	return false;
    }
    
    /**
     * 检查会员的是否满级和是否超过28天
     * @param sysUser
     * @param days
     * @return
     */
    private boolean checkMiMemberIsGrade(JsysUser sysUser,int days){
    	String checkTime = jpoMemberOrderManager.getMemberFirstOrderStatusTime(sysUser.getUserCode());
    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
    	try {
			date = format1.parse(checkTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	if(DateUtil.daysBetweenDates(new Date(),date)<days){
    		return true;
    	}
    	return false;
    }
}
