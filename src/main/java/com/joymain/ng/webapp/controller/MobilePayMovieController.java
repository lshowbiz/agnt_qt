package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.FiMovieOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.FiMovieOrderManager;
import com.joymain.ng.service.JsysUserManager;

/**
 * 手机电影票支付服务器端接口
 * @author hywen
 *
 */
@Controller
@RequestMapping("mobilePayMovie")
public class MobilePayMovieController extends BaseFormController {
	
	private Log log = LogFactory.getLog(MobilePayMovieController.class);
	
	@Autowired
	private FiBankbookJournalManager fiBankbookJournalManager;
	
	@Autowired
	private JsysUserManager jsysUserManager;
	
	@Autowired
	private FiMovieOrderManager fiMovieOrderManager;
	
	/**
	 * 查询电影票订单状态
	 * @param orderId
	 * @param token
	 * @return
	 */
    @RequestMapping(value="api/getMovieOrderStatus")
	@ResponseBody
	public String getMovieOrderStatus(String orderId,String token){
    	
 		//查询并返回状态
    	FiMovieOrder fiMovieOrder = fiMovieOrderManager.get(orderId);
    	if(fiMovieOrder == null){
    		
    		return "0";//订单不存在
    	}
    	    	
    	return fiMovieOrder.getStatus();
    }
    
    /**
     * 修改电影票订单状态
     * @param orderId
     * @param token
     * @return
     */
    @RequestMapping(value="api/updateMovieOrderStatus")
	@ResponseBody
	public String updateMovieOrderStatus(String orderId,String token){
    	
    	 //查询订单是否存在
    	FiMovieOrder fiMovieOrder = fiMovieOrderManager.get(orderId);
    	if(fiMovieOrder == null){
    		
    		return "0";//订单不存在
    	}
 		 
    	//修改订单状态
    	fiMovieOrder.setStatus("1");
    	fiMovieOrderManager.save(fiMovieOrder);
    	
    	return "1";//修改成功标识
    }

    /**
     * 支付电影票订单
     * @param userId
     * @param orderId
     * @param orderAmount
     * @param token
     * @return
     */
    @RequestMapping(value="api/payMovieOrder")
	@ResponseBody
    public String payMovieOrder(String userId, String orderId, String orderAmount, String token){
    	
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
    	
    	if(null!=object){
			return (String)object;
		}

    	//查询订单是否已经支付过
    	FiMovieOrder fiMovieOrder = fiMovieOrderManager.get(orderId);
    	if(fiMovieOrder != null){
    		
    		return "0";//支付失败标识,订单已经支付过
    	}
    	
    	try{
    	
    		//支付订单
    		fiMovieOrderManager.payMovieOrder(user, orderId, new BigDecimal(orderAmount));
    		
    		return "1";//支付成功标识
    	}catch(Exception e){
    		
    		return "0";//支付失败标识
    	}
    }
}
