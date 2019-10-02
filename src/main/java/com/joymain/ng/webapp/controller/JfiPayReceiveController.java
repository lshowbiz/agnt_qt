package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.bill99.Bill99Constants;
import com.joymain.ng.util.bill99.Bill99Util;
/**
 * 支付 
 * @author hywen
 *
 */
@Controller
@RequestMapping("/payReceive*")
public class JfiPayReceiveController extends BaseFormController{
	
	//private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private Bill99Util bill99Util = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private JsysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    @Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

    @Autowired
	public void setSysUserManager(JsysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

    @Autowired
	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

    @Autowired
	public void setBill99Util(Bill99Util bill99Util) {
		this.bill99Util = bill99Util;
	}

    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {

    	try{
        	//判断是否采用电子存折
            String isAllPay = request.getParameter("isAllPay");//全部用电子存折支付
            JsysUser operatorSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//操作用户
            
            if(!StringUtils.isEmpty(isAllPay)){
            	
            	//JsysUser operatorSysUser = SessionLogin.getOperatorUser(request);
            	//JsysUser loginSysUser = SessionLogin.getLoginUser(request);
            	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//当前用户
            	String orderId = request.getParameter("orderId");
            	boolean isCheck = this.checkFlagOne(orderId, operatorSysUser);
            	boolean b = false;
            	if(isCheck){
            		b = true;
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
        			
            	}else{
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poOrder.editedFail"));
            	}
            	/*
            	 * 支付改造
            	if(b){
            		try{
            			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
                		jpoMemberOrder.setStatus("3");
            			jpoMemberOrderManager.save(jpoMemberOrder);
            			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
            			log.info("=======审单MQ：JfiPayReceiveController");
            		}catch (Exception e) {
						log.info("发送mq消息失败：" + e);
						e.printStackTrace();
					}
            	}
            	*/
            	return new ModelAndView("redirect:jfiPay99bill.html?strAction=jfiPay99bill&orderId=" + orderId + "&flag=1");
            }
            
            //获得快钱返回信息
            String[] ext2 = request.getParameter("ext2").split(",");
            String userCode = ext2[0];
            String flag = ext2[1];
            BigDecimal fee = new BigDecimal(ext2[2]);
            
            JsysUser sysUser = sysUserManager.get(userCode);

            //获得快钱返回信息
            Jfi99billLog jfi99billLog = bill99Util.getJfi99billLog(request, sysUser.getUserCode(), "CN");
            
            //返回信息 支付成功
            if("10".equals(jfi99billLog.getReturnMsg())){
            	
            	//快钱数据进存折
            	jfiBankbookJournalManager.saveJfiPayDataVerifyByBill99("CN", sysUser, new BigDecimal(jfi99billLog.getPayAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfi99billLog.getDealId(),jfi99billLog,fee);
            	BigDecimal[] moneyArray = {fee};
            	Integer[] moneyType = {30};
            	String[] notes = {"手续费"};
            	//从用户帐户扣取金额
            	jfiBankbookJournalManager.saveJfiBankbookDeduct("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), "0", notes,"1");
            	
            	if("1".equals(flag)){//订单审核
            		String orderId = request.getParameter("orderId");
            		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
            		
            		boolean b = false;
                	boolean isCheck = this.checkFlagOne(orderId, sysUser);
                	if(isCheck){
                		
                		/* 支付改造
                		//支付完成，自动审核订单
                		jpoMemberOrder.setIsPay("1");
                		jpoMemberOrder.setPayCode(jfi99billLog.getMerchantAcctId());
                		//jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
                		jpoMemberOrderManager.save(jpoMemberOrder);
                		*/
            			b = true;
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
                	}else{
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
                	}
                	/*支付改造
                	if(b){
                		try{
                			jpoMemberOrder.setStatus("3");
                			jpoMemberOrderManager.save(jpoMemberOrder);
                			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
                			log.info("=======审单MQ：JfiPayReceiveController");
                		}catch (Exception e) {
							log.info("发送mq消息失败：" + e);
							e.printStackTrace();
						}
                	}
                	*/
            	}
            	return new ModelAndView("fi/jfiPay99billReceive").addObject("showUrl", Bill99Constants.showUrl);
            }else{
            	//不成功
            	switch(Integer.parseInt(jfi99billLog.getReturnMsg())){
            		case 0://数据被篡改
            			//MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:0");
            			saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:0");
            			break;
            		case 1://扣款失败
            			//MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:1");
            			saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:1");
            			break;
            		case 2://自定义MD5签名被篡改(快钱签名被破解)
            			//MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:2");
            			saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:2");
            			break;
            		case 3://数据重新发送
            			return new ModelAndView("fi/jfiPay99billReceive").addObject("showUrl", Bill99Constants.showUrl);
            	}
            }
            
            return new ModelAndView("fi/jfiPay99billReceive").addObject("returnMsg",jfi99billLog.getReturnMsg());
        }catch(Exception e){
        	e.printStackTrace();
        	return new ModelAndView("redirect:403.jsp");
        }
    }
	
    /**
	 * 业务判断 + 扣款
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderId, JsysUser operatorSysUser){
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		try{
			JpoMemberOrder order =jpoMemberOrderManager.newOrder(jpoMemberOrder);
			log.info("checkJpoMemberOrder start:"+order.getMemberOrderNo());
			jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"1");
			log.info("checkJpoMemberOrder stop:"+order.getMemberOrderNo());
			//jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
		}catch(Exception app){
			log.error("check order Error orderId is:"+orderId,app);
			return false;
		}
		return true;
	}

}
