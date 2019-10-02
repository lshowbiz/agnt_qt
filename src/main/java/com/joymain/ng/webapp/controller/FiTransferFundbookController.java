package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiTransferFundbookManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.FiTransferFundbook;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fiTransferFundbooks*")
public class FiTransferFundbookController {
    private FiTransferFundbookManager fiTransferFundbookManager;

    @Autowired
    public void setFiTransferFundbookManager(FiTransferFundbookManager fiTransferFundbookManager) {
        this.fiTransferFundbookManager = fiTransferFundbookManager;
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
    		GroupPage page = new GroupPage("","fiTransferFundbooks?dealStartTime="+dealStartTime+"&dealEndTime="+dealEndTime+"&pageSize="+pageSize,pageSize,request);
        	
    		List fiTransferFundbookList=fiTransferFundbookManager.getFiTransferFundbookListByUserCodePage(page,jsysUser.getUserCode(),dealStartTime,dealEndTime);
        	
        	request.setAttribute("page", page);//将分页信息加入到request作用域中
        	model.addAttribute("fiTransferFundbookList",fiTransferFundbookList);

        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());

        }
        return model;
    }
}
