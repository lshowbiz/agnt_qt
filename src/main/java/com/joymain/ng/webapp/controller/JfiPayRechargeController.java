package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.FiCommonAddr;
//import com.joymain.ng.model.FiPayAccount;
//import com.joymain.ng.model.FiPayAccountConfig;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiCommonAddrManager;
//import com.joymain.ng.service.FiPayAccountConfigManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.util.bill99.Bill99;
import com.joymain.ng.util.chanjet.Chanjet;
import com.joymain.ng.util.channel.ChannelBean;
import com.joymain.ng.util.chinapnr.Chinapnr;
import com.joymain.ng.util.umb.UmbPay;
/**
 * 在线充值请求核心控制器(第三方支付充值)
 * @author 007
 *
 */
@Controller
@RequestMapping("/jfiPayRechPay*")
public class JfiPayRechargeController {

//	@Autowired
//	private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
//	
//	@Autowired
//    private FiCommonAddrManager fiCommonAddrManager;//常用收货地址管理
//	
//	@Autowired
//    private FiPayAccountConfigManager fiPayAccountConfigManager;//非全额支付规则管理
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
               
//		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        request.setAttribute("jsysUser", jsysUser);
//        
//        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jsysUser.getUserCode());
//    	request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
//    	
//    	//flag(0:充值  1:订单支付)
//        request.setAttribute("flag", 0);
//    	
//    	//进行第三方支付平台判断，快钱、畅捷通等 .示例程序，以上海为例
//        FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(jsysUser.getUserCode());//获取会员常用收货地址
//
//        if(fiCommonAddr!=null){
//			
//        	FiPayAccountConfig fiPayAccountConfig = fiPayAccountConfigManager.get(fiCommonAddr.getProvince());
//
//        	if(fiPayAccountConfig != null){
//        		
//        		//平台：1：快钱、2：畅捷通、3：盛付通、4：宝易互通、5平安橙E、6：汇付天下
//        		FiPayAccount fiPayAccount = fiPayAccountConfig.getFiPayAccount();
//        		
//        		//快钱
//            	if("1".equals(fiPayAccount.getProviderType())){
//            		
//            		Bill99 jfi99bill = new Bill99();
//                    jfi99bill.setPayerName(jsysUser.getUserName());
//                    jfi99bill.setMerchantAcctId(fiPayAccount.getAccountCode());
//                    return new ModelAndView("jfiRechargePay", "jfi99bill", jfi99bill);
//            	}
//            	
//            	//畅捷通
//            	if("2".equals(fiPayAccount.getProviderType())){
//            		
//            		Chanjet chanjet = new Chanjet();
//            		chanjet.setMerchantId(fiPayAccount.getAccountCode());
//                    return new ModelAndView("jfiRechargePayByChanjet", "chanjet", chanjet);
//            	}
//
//            	//盛付通
//            	if("3".equals(fiPayAccount.getProviderType())){
//            		
//            		ChannelBean channel = new ChannelBean();
//            		channel.setMsgSender(fiPayAccount.getAccountCode());
//                    return new ModelAndView("jfiRechargePayByChannel", "channel", channel);
//            	} 
//            	
//            	//宝易互通
//            	if("4".equals(fiPayAccount.getProviderType())){
//            		
//                    UmbPay umbPay = new UmbPay();
//                    umbPay.setMerchantid(fiPayAccount.getAccountCode());
//                    return new ModelAndView("jfiRechargePayByUmb", "umb", umbPay);
//            	}
//            	
//            	//汇付天下
//            	if("6".equals(fiPayAccount.getProviderType())){
//            		
//            		Chinapnr entity = new Chinapnr();
//            		entity.setMerId(fiPayAccount.getAccountCode());
//            		return new ModelAndView("jfiRechargePayByChinapnr", "entity", entity);
//            	}
//        	}
//        }
//        
//        //默认走快钱支付
//        Bill99 jfi99bill = new Bill99();
//        jfi99bill.setPayerName(jsysUser.getUserName());        
//        return new ModelAndView("jfiRechargePay", "jfi99bill", jfi99bill);
		return new ModelAndView("jfiRechargePay", "jfi99bill", null);
	}
}
