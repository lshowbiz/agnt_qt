package com.joymain.ng.webapp.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;

/**
 * 电子存折支付审单处理控制器
 * @author syh
 *
 */
@Controller
@RequestMapping("/jfiPayReceive*")
public class JfiPay99billReceiveController extends BaseFormController{
	
	protected final transient Log log = LogFactory.getLog(getClass());
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    @Autowired
    private JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
    private JsysUserManager jsysUserManager;
    @Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

    @RequestMapping(method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {

        	//判断是否采用电子存折
            String isAllPay = request.getParameter("isAllPay");//全部用电子存折支付
            if(!StringUtils.isEmpty(isAllPay)){
            	
            	boolean b = false;
            	String orderId = request.getParameter("orderId");
    			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
    			JsysUser operatorSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//操作用户
    			
    			Iterator<JpoMemberOrderList> its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        		if("CN".equals(jpoMemberOrder.getCompanyCode())){
                    while (its1.hasNext()) {
                    	JpoMemberOrderList jpoMemberOrderList =  its1.next();
                    	String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
                    	if(!"1".equals(status)){
                    		saveMessage(request,"产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo() + ")已售完!");
                			return "redirect:jpoMemberOrders/orderAll?needReload=1";
                    	}
                    }
                }
    			
    			
            	try{
            		operatorSysUser = jsysUserManager.get(operatorSysUser.getUserCode());
        			if(validateOrder(jpoMemberOrder,operatorSysUser)){
        				log.info(jpoMemberOrder.getMemberOrderNo()+ LocaleUtil.getLocalText("user.validate"));
        				saveMessage(request, LocaleUtil.getLocalText("user.validate"));
        				throw new AppException("user roles or status error.");
        			}
        			
        			JpoMemberOrder order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
        			log.info("JfiPay99billReceiveController start:"+order.getMemberOrderNo());
        			jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"1");
        			log.info("JfiPay99billReceiveController stop:"+order.getMemberOrderNo());
        			//扣完款
//        			b = true;
        			
//        			log.info("call function start. "+jpoMemberOrder.getStatus()+" "
//        					+ "and "+jpoMemberOrder.getIsPay());
//        			
//        			jpoMemberOrderDao.callSTJFunction(jpoMemberOrder.getMemberOrderNo(), 1);
//        			
//        			log.info("call function END. ");
        			
        		}catch(AppException e){
        			
        			log.error("check Order failed.",e);
        			// 支付失败
    				saveMessage(request, LocaleUtil.getLocalText("order.pay.fail"));
        			return "redirect:jpoMemberOrders/orderAll?needReload=1";
        		}catch(Exception e){
        			
        			log.error("check Order failed.",e);
        			// 支付失败
    				saveMessage(request, LocaleUtil.getLocalText("order.pay.fail"));
        			return "redirect:jpoMemberOrders/orderAll?needReload=1";
        		}
        		/* 支付改造
        		if(b){
	        		try{	
	        			jpoMemberOrder.setStatus("3");    
	        			jpoMemberOrderManager.save(jpoMemberOrder);
	        			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
	        			log.info("=======审单MQ：JfiPay99billReceiveController,  orderNo:" + jpoMemberOrder.getMemberOrderNo());
	        			
	        		}catch (Exception e) {
						log.error(jpoMemberOrder.getMemberOrderNo() +" 发送mq消息失败：", e);
					}
        		}
        		*/
        		// 支付成功
    			saveMessage(request, LocaleUtil.getLocalText("order.pay.success"));
        		//审单结束
        		return "redirect:jpoMemberOrders/orderAll?needReload=1";
            }
            
            return "redirect:jpoMemberOrders/orderAll?needReload=1";
    }

	public JpoMemberOrderDao getJpoMemberOrderDao() {
		return jpoMemberOrderDao;
	}

	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
    
}
