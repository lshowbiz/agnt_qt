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

import com.joymain.ng.model.JbdSendNote;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdSendNoteManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jbdSendNoteform*")
public class JbdSendNoteFormController extends BaseFormController {
    private JbdSendNoteManager jbdSendNoteManager = null;
    @Autowired
	private JmiMemberManager jmiMemberManager;

    @Autowired
    public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
        this.jbdSendNoteManager = jbdSendNoteManager;
    }

    @Autowired
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    public JbdSendNoteFormController() {
        setCancelView("redirect:jbdSendNotes");
        setSuccessView("redirect:jbdSendNotes");
    }


	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}

	

	
    @ModelAttribute("jbdSendNote")
    private JbdSendNote getJbdSendNote( HttpServletRequest request){
    	request.setAttribute("time", new Date().getTime());

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JfiBankbookBalance jfiBankbookBalance=jfiBankbookBalanceManager.get(defSysUser.getUserCode());
		
		request.setAttribute("amountTotal", jfiBankbookBalance.getBalance().setScale(2,BigDecimal.ROUND_DOWN));
    	return new JbdSendNote();
    }
	

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JbdSendNote jbdSendNote, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView mav = new ModelAndView();
		
    	if(getCheckJbdSendNote(jbdSendNote, errors, request)){
    		return mav;
    	}
    	

    	String amount1 = request.getParameter("amount1");
		String amount2 = request.getParameter("amount2");
		String amount3 = request.getParameter("amount3");
		String time = request.getParameter("time");

		BigDecimal amount = new BigDecimal(0);
		BigDecimal amount1Big = new BigDecimal(amount1);
		BigDecimal amount2Big = new BigDecimal(amount2);
		BigDecimal amount3Big = new BigDecimal(amount3);
		
		amount = amount1Big.add(amount2Big.divide(new BigDecimal("10")));
		amount = amount.add(amount3Big.divide(new BigDecimal("100")));
    	
		JmiMember jmiMember = jmiMemberManager.getJmiMemberBankInformation(defSysUser.getUserCode());
		if(null!=jmiMember){
			String fs = "0";
			String userCode = jmiMember.getUserCode();
			//20160706  特殊名单 不做银行修改信息限制
			String tshy = "CN13234245,CN21305849,CN10111767,CN85606100,CN92504790,CN32448960,CN35436309,CN64084584,CN33177159,CN33964954,CN45149899,CN21736826,CN12898280,CN13731865,CN60337189,CN13767892,CN15127332,CN17969214,CN19506487,CN12420821,CN79744255,CN46053558,CN20474358,CN38323488,CN13717634,CN18310026,CN18243407,CN15090165,CN96233029,CN83900978,CN18660277,CN14608168,";
			if(tshy.indexOf(userCode+",") <= -1) {
				fs = "1";
			}
			if(MeteorUtil.isBlank(jmiMember.getBankcard()) && "1".equals(fs)){//如果银行卡信息字段为空
	 		     request.setAttribute("bankcardModify", "1");//为1时页面跳转到添加银行卡信息界面
	 		     return new ModelAndView("jbdSendNoteform");
            }else if("1".equals(fs) && jmiMember.getBankcard().length()<12  ){//如果银行卡信息字段长度不为12
	 		     request.setAttribute("bankcardModify", "2");//为2时页面给出提示
	 		     return new ModelAndView("jbdSendNoteform");
            }else{
            	jbdSendNoteManager.saveJbdSendNoteAndDeductBankbook(defSysUser.getCompanyCode(), defSysUser, defSysUser.getUserCode(), defSysUser.getUserName(), amount, time,"1");
    	    	saveMessage(request, LocaleUtil.getLocalText("fiMoney.success"));
            }
			
		}
    	
        return new ModelAndView("redirect:jbdSendNotes");
    }
    
    private boolean getCheckJbdSendNote(JbdSendNote jbdSendNote, BindingResult errors, HttpServletRequest request){
    	boolean flag=false;
    	String amount1 = request.getParameter("amount1");
		String amount2 = request.getParameter("amount2");
		String amount3 = request.getParameter("amount3");
		String password = request.getParameter("password");

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	

		BigDecimal amount = new BigDecimal(0);
		
		if(!StringUtil.isInteger(amount1) || !StringUtil.isInteger(amount2) || !StringUtil.isInteger(amount3)){
			StringUtil.getErrors(errors, "请输入数字", "");
			flag=true;
		}else{
			
			int amount1Int = StringUtil.formatInt(amount1);
			int amount2Int = StringUtil.formatInt(amount2);
			int amount3Int = StringUtil.formatInt(amount3);
			
			if(amount1Int < 0 || amount2Int < 0 || amount2Int > 9 || amount3Int < 0 || amount3Int > 9){
				StringUtil.getErrors(errors, "请输入数字大于0的数字", "");
				flag=true;
			}
		}
		
		if(!flag){

			BigDecimal amount1Big = new BigDecimal(amount1);
			BigDecimal amount2Big = new BigDecimal(amount2);
			BigDecimal amount3Big = new BigDecimal(amount3);
			
			amount = amount1Big.add(amount2Big.divide(new BigDecimal("10")));
			amount = amount.add(amount3Big.divide(new BigDecimal("100")));
			
			if(amount.compareTo(new BigDecimal("3"))!=1){
				StringUtil.getErrors(errors, "fiMoney.fail.amount", "");
				flag=true;
			}
			
		}
		
		if(StringUtil.isEmpty(password)){
			StringUtil.getErrors(errors, "fiMoney.fail.password", "");
			flag=true;
			
		}else{
			if(!StringUtil.encodePassword(password, "MD5").equals(defSysUser.getPassword2())){
				StringUtil.getErrors(errors, "fiMoney.fail.password", "");
				flag=true;
			}
		}

		if(!flag){
			
			JfiBankbookBalance jfiBankbookBalance=jfiBankbookBalanceManager.get(defSysUser.getUserCode());
			if(amount.compareTo(jfiBankbookBalance.getBalance())==1){
				StringUtil.getErrors(errors, "fiMoney.fail.amount.notEnough", "");
				flag=true;
			}
		}
		
		
		
    	return flag;
    }
}
