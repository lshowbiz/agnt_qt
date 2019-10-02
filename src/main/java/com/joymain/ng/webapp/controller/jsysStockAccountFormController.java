package com.joymain.ng.webapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JsysStockAccount;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JsysStockAccountManager;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jsysStockAccount")
public class jsysStockAccountFormController extends BaseFormController{
	private JsysStockAccountManager jsysStockAccountManager;
	
	@Autowired
	public void setJsysStockAccountManager(
			JsysStockAccountManager jsysStockAccountManager) {
		this.jsysStockAccountManager = jsysStockAccountManager;
	}

	/**
	 * 跳转到港股信息页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/jsysStockAccountForm",method = RequestMethod.GET)
	public String jsysStockAccountForm(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取当前用户
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JsysStockAccount jsysStockAccount = new JsysStockAccount();
		if(jsysUser!=null){
			jsysStockAccount = jsysStockAccountManager.getJsysStockAccountByUserCode(jsysUser.getUserCode());
		}
		model.addAttribute("stockAccount", jsysStockAccount);
		return "jsysStockAccountForm";
	}
	
	/**
	 * 保存和修改港股信息
	 * @param request
	 * @param response
	 * @param jsysStockAccount
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveStockAccount",method = RequestMethod.POST)
	public String saveStockAccount(HttpServletRequest request,HttpServletResponse response,Model model,JsysStockAccount jsysStockAccount) throws Exception {
		if(StringUtil.isEmpty(jsysStockAccount.getStockAccount())){
			this.saveMessage(request, "港股账号不能为空");
			return "jsysStockAccountForm";
		}
		if(StringUtil.isEmpty(jsysStockAccount.getFeeStatus())){
			this.saveMessage(request, "是否汇入手续费不能为空");
			return "jsysStockAccountForm";
		}
		JsysStockAccount stockAccount = jsysStockAccountManager.getJsysStockAccountByUserCode(jsysStockAccount.getUserCode());
		if(stockAccount!=null){
			stockAccount.setStockAccount(jsysStockAccount.getStockAccount());
			stockAccount.setFeeStatus(jsysStockAccount.getFeeStatus());
			if(stockAccount.getCreateDate()==null){
				stockAccount.setCreateDate(new Date());
			}else{
				stockAccount.setUpdateDate(new Date());
			}
			jsysStockAccountManager.saveOrUpdate(stockAccount);
		}
		model.addAttribute("stockAccount", stockAccount);
		model.addAttribute("status", "success");
		return "jsysStockAccountForm";
	}
}
