package com.joymain.ng.webapp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.ng.Constants;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.JsysUserRole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseOrderController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected JpoShoppingCartOrderManager jpoShoppingCartOrderManager;
    @Autowired
    protected JpoMemberOrderManager jpoMemberOrderManager;
    @Autowired
    protected JsysUserRoleManager jsysUserRoleManager;
    @Autowired
    protected JsysRoleManager jsysRoleManager;
   
    /**
     * 判断是否满足升级条件
     * @param sysUser
     * @return
     */
    protected String getUpGradeValue(JsysUser sysUser){
    	try{
    		int member_upgrade_time = Integer.parseInt(ConfigUtil.
        			getConfigValue(sysUser.getCompanyCode().toUpperCase(),
        					"member_upgrade_time"));//会员在规定时间里只能升几次
            boolean isUpGrade1 = checkMiMemberIsUpGraded(sysUser,member_upgrade_time);
            if(!isUpGrade1){
        		return "0";
            }
            
            int days = Integer.parseInt(ConfigUtil.
            		getConfigValue(sysUser.getCompanyCode().toUpperCase(),
            				"member_upgrade_day"));//会员的是否满级和是否超过60天
        	boolean isUpGrade2 = checkMiMemberIsGrade(sysUser,days);
        	if(!isUpGrade2){
        		return "0";//不允许下升级单
        	}
        	return "1";//允许下升级单
    	}catch(Exception e){
    		log.error("getUpGradeValue method error.",e);
    		return null;
    	}
    }
    /**
     * 会员在MEMBER_UPGRADE_DAY时间内可几次
     * @param sysUser
     * @param member_upgrade_time
     * @return
     */
    protected boolean checkMiMemberIsUpGraded(JsysUser sysUser, int member_upgrade_time){
    	try{
    		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
            jpoMemberOrder.setSysUser(sysUser);
            jpoMemberOrder.setOrderType("2");//升级单
            jpoMemberOrder.setStatus("1");//待审单
            List poMemberOrderList1 = jpoMemberOrderManager.
            		getJpoMemberOrdersByMiMember(jpoMemberOrder);
            if(poMemberOrderList1.size() > 0){//不允许在下升级单
            	return false;
            }
            jpoMemberOrder.setSysUser(sysUser);
            jpoMemberOrder.setOrderType("2");
            jpoMemberOrder.setStatus("2");
            List poMemberOrderList2 = jpoMemberOrderManager.
            		getJpoMemberOrdersByMiMember(jpoMemberOrder);
        	if(poMemberOrderList2.size() < member_upgrade_time){//审核过的订单总数
        		return true;
        	}
    	}catch(Exception e){
    		log.error("checkMiMemberIsUpGraded method error.",e);
    	}
    	return false;
    }
    
    /**
     * 检查会员的是否满级和是否超过28天
     * @param sysUser
     * @param days
     * @return
     */
    protected boolean checkMiMemberIsGrade(JsysUser sysUser,int days){
    	try{
    		String checkTime = jpoMemberOrderManager.
        			getMemberFirstOrderStatusTime(sysUser.getUserCode());
        	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        	Date date = null;
        	try {
    			date = format1.parse(checkTime);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}	
        	if(DateUtil.daysBetweenDates(new Date(),date)<days){
        		return true;
        	}
    	}catch(Exception e){
    		log.error("checkMiMemberIsGrade method error.",e);
    	}
    	return false;
    }
    /**
     * 根据订单信息确认是否存在订单
     * @param order
     * @return if not exist return true
     */
    protected boolean isBindOrderType(JpoMemberOrder order){
		List<JpoMemberOrder> orderList = jpoMemberOrderManager.
				getJpoMemberOrdersByMiMember(order);
		if(orderList.isEmpty())
			return true;
		else
			return false;
	}
    
    /**
     * 升级单升级期限
     * @return  true 可以下升级单 
     */
    public boolean upGradeInTime(JsysUser sysUser){
    	
    	String upgrade_Month_on = ConfigUtil.getConfigValue(sysUser.getCompanyCode(),
		"member_upgrade_on"); //有期限
    	String upgrade_Month = ConfigUtil.getConfigValue(sysUser.getCompanyCode(),
				"member_upgrade");  // 天
		
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
		
//		endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH)+Integer.parseInt(upgrade_Month));
// 		endDate.set(Calendar.DAY_OF_MONTH, 1);
		
 		
 		
 		log.info("............userCode is:"+userCode+" roleCode is:"+role.getRoleCode());
		log.info("优惠顾客:.."+role.getRoleCode().equalsIgnoreCase(Constants.ROLEID1));
		
		if(role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_1000)){
 			//优惠顾客升级截止日期
 			String startDateS = ConfigUtil.getConfigValue("CN", "member.roid.1.upgradedate");
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 		    try {
 		    	if(startDateS != null){
 		    		Calendar up_DATE = Calendar.getInstance();
 	 		    	up_DATE.setTime(sdf.parse(startDateS));
 		    		if(curDate.after(up_DATE)){
 	 					return false;
 	 				}
 		    	}else{
 		    		log.info("member.roid.1.upgradedate 时间参数未设置.");
 		    	}
 				
 			} catch (ParseException e) {
 				log.info("member.roid.1.upgradedate 时间参数有误.");
 				e.printStackTrace();
 			}
 		} else {
 			
 			if(upgrade_Month_on.equals("1") && curDate.after(endDate)){
 				log.info("");
 	 			return  false;
 	 		}
 		}
		
		/*if(!role.getRoleCode().equalsIgnoreCase(Constants.ROLEID1) && upgrade_Month_on.equals("1") && curDate.after(endDate)){
		return  false;
		//已过升级时间
//		msgList.add(LocaleUtil.getLocalText(
//				jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),
//				"miMember.isNotUpGrade"));
	}
	
    //优惠顾客升级截止日期
    String startDateS = ConfigUtil.getConfigValue("CN", "member.roid.1.upgradedate");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    try {
		Date up_DATE = sdf.parse(startDateS);
		if(role.getRoleCode().equalsIgnoreCase(Constants.ROLEID1) && curDate.after(up_DATE)){
			return false;
		}
		
	} catch (ParseException e) {
		log.info("member.roid.1.upgradedate 时间参数有误.");
		e.printStackTrace();
	}*/
		return true; 
    	
    }
    
    
}
