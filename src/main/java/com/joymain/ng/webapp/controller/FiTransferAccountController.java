package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiTransferAccountManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiTransferAccounts*")
public class FiTransferAccountController {
    private FiTransferAccountManager fiTransferAccountManager;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager;
    private JmiMemberManager jmiMemberManager;
    
    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    @Autowired
    public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
        this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
    }

    @Autowired
    public void setFiTransferAccountManager(FiTransferAccountManager fiTransferAccountManager) {
        this.fiTransferAccountManager = fiTransferAccountManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="dealStartTime", required=false) String dealStartTime,@RequestParam(value="dealEndTime", required=false) String dealEndTime,
    		HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	//当前用户
        	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
        	dealStartTime = StringUtil.dealStr(dealStartTime);
        	dealEndTime = StringUtil.dealStr(dealEndTime);
        	//----------------------Modify By WuCF 添加分页展示功能 
        	//分页单位：固定写法
        	Integer pageSize = StringUtil.dealPageSize(request);
        	
    		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
    		GroupPage page = new GroupPage("","fiTransferAccounts?dealStartTime="+dealStartTime+"&dealEndTime="+dealEndTime+"&pageSize="+pageSize,pageSize,request);
        	
//        	List fiTransferAccountList=fiTransferAccountManager.getFiTransferAccountListByUserCode(jsysUser.getUserCode(),dealStartTime,dealEndTime);
    		List fiTransferAccountList=fiTransferAccountManager.getFiTransferAccountListByUserCodePage(page,jsysUser.getUserCode(),dealStartTime,dealEndTime);
        	
        	JmiMember jmiMember = jmiMemberManager.getJmiMember(jsysUser.getUserCode());
        	Integer memberLevel = jmiMember.getMemberLevel();
        	String ml = memberLevel+"";
        	model.addAttribute("memberLevel",ml);
    		
        	request.setAttribute("page", page);//将分页信息加入到request作用域中
        	model.addAttribute("fiTransferAccountList",fiTransferAccountList);

        	//查询存折余额对象 
        	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.get(jsysUser.getUserCode());
        	model.addAttribute("jfiBankbookBalance",jfiBankbookBalance);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());

        }
        return model;
    }
    
}
