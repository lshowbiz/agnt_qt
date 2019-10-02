package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.JbdBonusFund;
import com.joymain.ng.model.JbdSendRecordHist;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdBonusFundManager;
import com.joymain.ng.service.JbdSendRecordHistManager;

@Controller
@RequestMapping("/jbdBonusFundform*")
public class JbdBonusFundFormController extends BaseFormController {
    private JbdBonusFundManager jbdBonusFundManager = null;
    @Autowired
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    @Autowired
    public void setJbdBonusFundManager(JbdBonusFundManager jbdBonusFundManager) {
        this.jbdBonusFundManager = jbdBonusFundManager;
    }

    public JbdBonusFundFormController() {
        setCancelView("redirect:jbdBonusFunds");
        setSuccessView("redirect:jbdBonusFunds");
    }

	
	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}

	@ModelAttribute
	public JbdBonusFund getJbdBonusFund(HttpServletRequest request) {


			JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   
			
			JbdBonusFund jbdBonusFund=jbdBonusFundManager.getJbdBonusFundByUserCode(defSysUser.getUserCode());

	        JbdSendRecordHist jbdSendRecordHist=jbdSendRecordHistManager.getJbdSendRecordHistByUserCodeByWeek(defSysUser.getUserCode(), "201516");
	        
	        if(jbdSendRecordHist==null){
	        	 request.setAttribute("noBonus", "noBonus");
			 }
	        
	        boolean isApply=false;
	        
	        if(jbdSendRecordHist!=null){

		        
		        BigDecimal totalBonus=jbdSendRecordHist.getRemittanceMoney().add(jbdSendRecordHist.getCurrentDev());
		        request.setAttribute("totalBonus", totalBonus);
		        
		        if(totalBonus.compareTo(new BigDecimal(0))==1){
		        	isApply=true;
		        }else{
		        	isApply=false;
		        }
		        
		        
	        }else{
	        	isApply=false;
	        }
	        
	        if(jbdBonusFund!=null){
	        	BigDecimal jbdBonusFundMoney=jbdBonusFund.getMoney();
	        	jbdBonusFundMoney=jbdBonusFundMoney.multiply(new BigDecimal(1.2));
	        	jbdBonusFundMoney =  jbdBonusFundMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	        	 request.setAttribute("jbdBonusFundMoney", jbdBonusFundMoney);
	        	isApply=false;
	        }
	        
	        if(jbdBonusFund==null){
	        	jbdBonusFund= new JbdBonusFund();
	        }
	        request.setAttribute("isApply", isApply);
	        request.setAttribute("jbdBonusFund", jbdBonusFund);
	        return jbdBonusFund;
	}
	


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(JbdBonusFund jbdBonusFund, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	

		ModelAndView mav = new ModelAndView();

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JbdBonusFund resjbdBonusFund=jbdBonusFundManager.getJbdBonusFundByUserCode(defSysUser.getUserCode());
		 if(resjbdBonusFund!=null){
			 this.saveMessage(request, "已经申请");
			 return  new ModelAndView("redirect:jbdBonusFundform?1=1");
    	}
    	
		  JbdSendRecordHist jbdSendRecordHist=jbdSendRecordHistManager.getJbdSendRecordHistByUserCodeByWeek(defSysUser.getUserCode(), "201516");
		  
		 
		  
		  
		  BigDecimal totalBonus=jbdSendRecordHist.getRemittanceMoney().add(jbdSendRecordHist.getCurrentDev());
		  
		  
		  	String amount1 = request.getParameter("amount1");
			String amount2 = request.getParameter("amount2");
			String amount3 = request.getParameter("amount3");

			BigDecimal amount = new BigDecimal(0);
			BigDecimal amount1Big = new BigDecimal(amount1);
			BigDecimal amount2Big = new BigDecimal(amount2);
			BigDecimal amount3Big = new BigDecimal(amount3);
			
			amount = amount1Big.add(amount2Big.divide(new BigDecimal("10")));
			amount = amount.add(amount3Big.divide(new BigDecimal("100")));

			amount =  amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		  
			jbdBonusFund.setMoney(amount);
		  
		  
		  if(jbdBonusFund.getMoney().compareTo(new BigDecimal(0))!=1){

				 this.saveMessage(request, "请填入大于零的金额");
				 return new ModelAndView("redirect:jbdBonusFundform?1=1");
		  }
		  
	       if(jbdBonusFund.getMoney().compareTo(totalBonus)==1){
				 this.saveMessage(request, "申请金额大于总奖金金额");
				 return  new ModelAndView("redirect:jbdBonusFundform?1=1");
	    	   
	       }
		 
		 
		 
		 jbdBonusFund.setUserCode(defSysUser.getUserCode());
		 jbdBonusFund.setCreateTime(new Date());

		 
		 
		 jbdBonusFundManager.save(jbdBonusFund);
		 this.saveMessage(request, "申请成功");

        return new ModelAndView("redirect:jbdBonusFundform?1=1");
    }
}
