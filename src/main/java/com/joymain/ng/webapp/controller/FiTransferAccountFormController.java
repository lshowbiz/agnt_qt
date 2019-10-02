package com.joymain.ng.webapp.controller;

import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.service.FiTransferAccountManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.model.FiTransferAccount;
import com.joymain.ng.model.JfiBankbookBalance;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/fiTransferAccountform*")
public class FiTransferAccountFormController extends BaseFormController {
    private FiTransferAccountManager fiTransferAccountManager = null;
    private JsysUserManager sysUserManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    
    @Autowired
	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}
    
    @Autowired
    public void setSysUserManager(JsysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	@Autowired
    public void setFiTransferAccountManager(FiTransferAccountManager fiTransferAccountManager) {
        this.fiTransferAccountManager = fiTransferAccountManager;
    }

    public FiTransferAccountFormController() {
        setCancelView("redirect:fiTransferAccounts");
        setSuccessView("redirect:fiTransferAccounts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiTransferAccount showForm(HttpServletRequest request)
    throws Exception {
    	
    	//当前用户
       	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       	request.setAttribute("jsysUser", jsysUser);
       	
       	//会员电子存折余额
    	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jsysUser.getUserCode());
    	request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
    	
        return new FiTransferAccount();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiTransferAccount fiTransferAccount, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { 
            validator.validate(fiTransferAccount, errors);

            if (errors.hasErrors() && request.getParameter("save") == null) { // don't validate when deleting
                return "redirect:fiTransferAccountform";
            }
        }

        log.debug("进入addFiTransferAccount");

		String success = getSuccessView();
        Locale locale = request.getLocale();

		if (request.getParameter("save") != null) {
	       	//当前用户
	       	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	       	
	       	//验证转账目标用户是否存在
			JsysUser destinationUser=this.sysUserManager.get(fiTransferAccount.getDestinationUserCode());
			if(destinationUser==null || !destinationUser.getUserType().equals("M")){
				saveMessage(request, getText(LocaleUtil.getLocalText("error.destination.not.existed"), locale));
				return "redirect:fiTransferAccountform";
			}
			//验证转账目标用户不能为自己
			if(destinationUser.getUserCode().equals(jsysUser.getUserCode())){
				saveMessage(request, getText(LocaleUtil.getLocalText("error.destinationuser.error"), locale));
				return "redirect:fiTransferAccountform";
			}
			
			//单笔转账交易额度控制，采用自动化配置方式			
			String limitNum = ConfigUtil.getConfigValue(jsysUser.getCompanyCode().toUpperCase(), "transferaccount.max.value");
			BigDecimal limit = new BigDecimal(limitNum);
			//如果单笔交易额度不小于5000
			if(limit.compareTo(fiTransferAccount.getMoney()) == -1 || limit.compareTo(fiTransferAccount.getMoney()) == 0){
				saveMessage(request, getText(LocaleUtil.getLocalText("fiTransferAccount.paymax.error2")+limitNum, locale));
				return "redirect:fiTransferAccountform";
			}
	       	
			//单日转账交易额度控制，采用自动化配置方式
			String limitDayNum = ConfigUtil.getConfigValue(jsysUser.getCompanyCode().toUpperCase(), "transferaccount.daymax.value");
			BigDecimal limitDay = new BigDecimal(limitDayNum);
			
			//获取当前用户当日转账总额
			BigDecimal sumDayTransferValue = fiTransferAccountManager.getSumTransferValueByTransferCode(jsysUser.getUserCode());
			BigDecimal sumDayTransferValues=sumDayTransferValue.add(fiTransferAccount.getMoney());
			
			//如果单日最高额度不小于2W
			if(limitDay.compareTo(sumDayTransferValues) == -1){
				saveMessage(request, getText(LocaleUtil.getLocalText("fiTransferAccount.paydaymax.error")+limitDayNum, locale));
				return "redirect:fiTransferAccountform";
			}
			
			//前台输入支付密码
			String transferPayPwd = request.getParameter("paypassword");
			//验证转账支付密码(提现密码)
			if(transferPayPwd==null || !StringUtil.encodePassword(transferPayPwd, "md5").equalsIgnoreCase(jsysUser.getPassword2())){
				saveMessage(request, getText(LocaleUtil.getLocalText("fiTransferAccount.paypwd.error"), locale));
				return "redirect:fiTransferAccountform";
			}
			
	       	//设置当前转账用户
	   		fiTransferAccount.setTransferUserCode(jsysUser.getUserCode());
	   		fiTransferAccount.setCreaterCode(jsysUser.getUserCode());
	   		fiTransferAccount.setCreaterName(jsysUser.getUserName());
	   		fiTransferAccount.setCreateTime(new Date());
	   		
	   		//创建转账单
	   		try{
				
				fiTransferAccountManager.addTransferAccountsNew(fiTransferAccount, jsysUser,"1");
	   		}catch(Exception e){
	   			
	   			saveMessage(request, getText(LocaleUtil.getLocalText("fiTransferAccount.add.error"), locale));
	   			log.debug("error:"+e.getMessage());
				return "redirect:fiTransferAccountform";
	   		}
	   		//页面跳转和执行结果提示
	   		saveMessage(request, getText(LocaleUtil.getLocalText("fiTransferAccount.add.success"), locale));	   		
	   		request.getSession().setAttribute("MESSAGE", LocaleUtil.getLocalText("fiTransferAccount.add.success"));
	   		return "redirect:fiTransferAccounts";
       }
		
        return success;
    }
}
