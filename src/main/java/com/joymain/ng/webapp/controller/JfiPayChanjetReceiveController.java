package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;

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

import com.joymain.ng.model.JfiChanjetLog;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.chanjet.ChanjetUtil;

/**
 * 畅捷通付款通知处理控制器
 * @author syh
 *
 */
@Controller
@RequestMapping("/jfiPayChanjetReceive*")
public class JfiPayChanjetReceiveController extends BaseFormController{
	
	protected final transient Log log = LogFactory.getLog(getClass());
	
	@Autowired
    private JpoMemberOrderManager jpoMemberOrderManager = null;
	
	@Autowired
    private ChanjetUtil chanjetUtil;
	
	@Autowired
	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	
	@Autowired
	private JsysUserManager jsysUserManager = null;

    @RequestMapping(method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	// 当前用户
        JsysUser loginSysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	//验签、保存通知数据
    	JfiChanjetLog jfiChanjetLog = chanjetUtil.getJfiChanjetLog(request);
    	
    	//验签成功
    	if(("10").equals(jfiChanjetLog.getReturnMsg())){
    		
    		String[] ext2 = request.getParameter("expand").split(",");
            String userCode = ext2[0];//获取付款会员身份
            String flag = ext2[1];//标识：1订单支付，0充值
    		
    		JsysUser sysUser = jsysUserManager.get(userCode);
    		
    		//付款数据进存折
    		jfiBankbookJournalManager.saveJfiPayDataVerifyByChanjet("CN", sysUser, new BigDecimal(jfiChanjetLog.getAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfiChanjetLog.getDetailId(),jfiChanjetLog);
        	 
    		boolean check = false;
    		boolean b = false;
    	
    		//订单编号
    		String orderId = request.getParameter("orderId");
    		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
            if("1".equals(flag)){//订单审核
	    		
	    		//审单、扣款
	    		check = this.checkFlagOne(orderId, sysUser);
	    		
//	    		//检查订单审核状况,决定是否回改ispay
//	    		JpoMemberOrder jpoMemberOrderCheck = jpoMemberOrderManager.get(new Long(orderId));
//	        	if(("1").equals(jpoMemberOrderCheck.getStatus())){    		
//	        		
//	        		jpoMemberOrderCheck.setIsPay("0");
//	        		jpoMemberOrderManager.save(jpoMemberOrderCheck);
//	        		log.info("畅捷支付审核订单失败！修改支付标示为0！ 订单号："+jpoMemberOrderCheck.getMemberOrderNo());
//	        	}
            }
            /*
             *支付改造
    		if(check){
    			if(b){
            		//发送mq队列
            		try{
            			jpoMemberOrder.setStatus("3");
            			jpoMemberOrderManager.save(jpoMemberOrder);
            			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, loginSysUser, "1");
            			log.info("=======审单MQ：JfiPayChanjetReceiveController"); 
            			
            		}catch (Exception e) {
            			log.info("发送mq消息失败：" + e);
    					e.printStackTrace();
					}
            	}
    			return "redirect:jfiPayChanjetReceive?returnMsg=000000&returnMsgDesc=接收成功";
    		}
    		*/
    	}

    	if(("3").equals(jfiChanjetLog.getReturnMsg())){//数据重发
    		
    		return "redirect:jfiPayChanjetReceive?returnMsg=000000&returnMsgDesc=数据重发";
    	}

    	if(("4").equals(jfiChanjetLog.getReturnMsg())){//验签失败
    		
    		return "redirect:jfiPayChanjetReceive?returnMsg=600002&returnMsgDesc=验签失败";
    	}

    	
    	return "redirect:jfiPayChanjetReceive?returnMsg=600000&returnMsgDesc=未知错误";
    }
    
    /**
     * 审单
     * @param orderId
     * @param operatorSysUser
     */
    private boolean checkFlagOne(String orderId, JsysUser operatorSysUser){
    	
    	try{
    		
    		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));   
    		JpoMemberOrder order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
			//扣款
    		log.info("checkJpoMemberOrder start:"+order.getMemberOrderNo());
			jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"1");	
			log.info("checkJpoMemberOrder stop:"+order.getMemberOrderNo());
		}catch(AppException app){
			
			log.info("畅捷支付审核订单抛出异常,订单ID："+orderId+"---err:"+app);
			return false;
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
