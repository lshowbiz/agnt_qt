package com.joymain.ng.webapp.controller;

import com.joymain.ng.service.FiFundbookBalanceManager;
import com.joymain.ng.service.FiTransferFundbookManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.model.FiTransferFundbook;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/fiTransferFundbookform*")
public class FiTransferFundbookFormController extends BaseFormController {
    private FiTransferFundbookManager fiTransferFundbookManager = null;
    private JsysUserManager sysUserManager = null;
    private FiFundbookBalanceManager fiFundbookBalanceManager;

    @Autowired
    public void setFiFundbookBalanceManager(FiFundbookBalanceManager fiFundbookBalanceManager) {
        this.fiFundbookBalanceManager = fiFundbookBalanceManager;
    }
    
    @Autowired
    public void setSysUserManager(JsysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
    
    @Autowired
    public void setFiTransferFundbookManager(FiTransferFundbookManager fiTransferFundbookManager) {
        this.fiTransferFundbookManager = fiTransferFundbookManager;
    }

    public FiTransferFundbookFormController() {
        setCancelView("redirect:fiTransferFundbooks");
        setSuccessView("redirect:fiTransferFundbooks");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiTransferFundbook showForm(HttpServletRequest request)
    throws Exception {
    	
    	FiTransferFundbook fiTransferFundbook = new FiTransferFundbook();
    	//当前用户
       	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       	request.setAttribute("jsysUser", jsysUser);
       	
       	//获取产业基金类型
    	String bankbookType = request.getParameter("bankbookType");
    	
    	//目标帐户类型  1：分红基金，2：定向基金，3：发展基金.
    	String transferType = request.getParameter("transferType");
    	
    	//转入自己帐户还是他人帐户，1：转入他人帐户,   0：代表转入自己帐户
    	String destOther = request.getParameter("destOther");
    	request.setAttribute("destOther", destOther);
    	
    	if(("0").equals(destOther)){
    		
    		fiTransferFundbook.setDestinationUserCode(jsysUser.getUserCode());
    	}
    	
    	fiTransferFundbook.setBankbookType(bankbookType);
    	fiTransferFundbook.setTransferType(transferType);
    	fiTransferFundbook.setTransferUserCode(jsysUser.getUserCode());
       	
       	//会员产业化基金余额
       	FiFundbookBalance fiFundbookBalance = fiFundbookBalanceManager.getFiFundbookBalance(jsysUser.getUserCode(), bankbookType);
    	request.setAttribute("fiFundbookBalance", fiFundbookBalance);
    	
    	return fiTransferFundbook;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiTransferFundbook fiTransferFundbook, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

//        if (validator != null) {
//            validator.validate(fiTransferFundbook, errors);
//
//            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
//                return "fiTransferFundbookform";
//            }
//        }

        log.debug("进入addFiTransferFundbook");

		String success = getSuccessView();
		String destOther = request.getParameter("destOther");
        //Locale locale = request.getLocale();

		if (request.getParameter("save") != null) {
	       	//当前用户
	       	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	       	
	       	//验证转账目标用户是否存在
			JsysUser destinationUser=this.sysUserManager.get(fiTransferFundbook.getDestinationUserCode());
			if(destinationUser==null || !destinationUser.getUserType().equals("M")){
				saveMessage(request, "对不起！您输入的收款会员编号不正确，请核对后再重试！");
				
				return "redirect:fiTransferFundbookform?bankbookType="+fiTransferFundbook.getBankbookType()+"&transferType="+fiTransferFundbook.getTransferType()+"&destOther="+destOther;
			}
						
			//前台输入支付密码
			String transferPayPwd = request.getParameter("paypassword");
			//验证转账支付密码(提现密码)
			if(transferPayPwd==null || !StringUtil.encodePassword(transferPayPwd, "md5").equalsIgnoreCase(jsysUser.getPassword2())){
				saveMessage(request, "对不起！您输入的支付密码不正确！");
				return "redirect:fiTransferFundbookform?bankbookType="+fiTransferFundbook.getBankbookType()+"&transferType="+fiTransferFundbook.getTransferType()+"&destOther="+destOther;
			}
			
	       	//设置当前转账用户
	   		fiTransferFundbook.setCreaterCode(jsysUser.getUserCode());
	   		fiTransferFundbook.setCreaterName(jsysUser.getUserName());
	   		fiTransferFundbook.setCreateTime(new Date());
   			fiTransferFundbook.setStatus(1);//转账单状态 ： 新单

	   		try{
	   			
	   			//保存转账单、从产业基金帐户扣钱 同步执行
	   			fiTransferFundbookManager.doTransferFundbookDeduct(fiTransferFundbook, jsysUser);
	   		}catch(Exception e){
	   			
	   			saveMessage(request, "转账申请失败！请检查帐户余额是否不足！");
	   			log.debug("error:"+e.getMessage());
	   			return "redirect:fiTransferFundbookform?bankbookType="+fiTransferFundbook.getBankbookType()+"&transferType="+fiTransferFundbook.getTransferType()+"&destOther="+destOther;
			}
	   		//页面跳转和执行结果提示
	   		saveMessage(request, "转账申请成功，待系统审核确认之后将转账金额转入指定的帐户！");	   		
	   		//request.getSession().setAttribute("MESSAGE", "转账申请成功，待系统审核确认之后将转账金额转入指定的帐户！");
	   		return "redirect:fiTransferFundbooks";
       }

        return success;
    }
}
