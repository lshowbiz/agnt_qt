package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiFundbookBalanceManager;
import com.joymain.ng.service.FiFundbookJournalManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.model.FiFundbookJournal;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 会员产业化基金查询控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/fiFundbookJournals*")
public class FiFundbookJournalController {
    private FiFundbookJournalManager fiFundbookJournalManager;
    private FiFundbookBalanceManager fiFundbookBalanceManager;

    @Autowired
    public void setFiFundbookBalanceManager(FiFundbookBalanceManager fiFundbookBalanceManager) {
        this.fiFundbookBalanceManager = fiFundbookBalanceManager;
    }
    @Autowired
    public void setFiFundbookJournalManager(FiFundbookJournalManager fiFundbookJournalManager) {
        this.fiFundbookJournalManager = fiFundbookJournalManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="dealStartTime", required=false) String dealStartTime,@RequestParam(value="dealEndTime", required=false) String dealEndTime,
    		HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	
        	//当前用户
        	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
        	//基金类型：1，分红基金；2，定向基金
        	String bankbookType ="1";
        	//List fiFundbookJournalList=fiFundbookJournalManager.search(query, FiFundbookJournal.class);
        	
        	if("null".equals(dealStartTime)){
        		dealStartTime = "";
        	}
        	if("null".equals(dealEndTime)){
        		dealEndTime = "";
        	}
        	//----------------------Modify By WuCF 添加分页展示功能 
        	//分页单位：固定写法
        	Integer pageSize = StringUtil.dealPageSize(request);
        	
    		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
    		GroupPage page = new GroupPage("","fiFundbookJournals?dealStartTime="+dealStartTime+"&dealEndTime="+dealEndTime+"&pageSize="+pageSize,pageSize,request);
        	
//        	List<FiFundbookJournal> fiFundbookJournalList=fiFundbookJournalManager.getFiFundbookJournalListByUser(jsysUser.getUserCode(), dealStartTime, dealEndTime);
        	List<FiFundbookJournal> fiFundbookJournalList=fiFundbookJournalManager.getFiFundbookJournalListByUserPage(page,jsysUser.getUserCode(), bankbookType, dealStartTime, dealEndTime);

        	
        	request.setAttribute("page", page);//将分页信息加入到request作用域中
            model.addAttribute("fiFundbookJournalList",fiFundbookJournalList);
            
            //查询存折余额对象 
        	FiFundbookBalance fiFundbookBalance = fiFundbookBalanceManager.getFiFundbookBalance(jsysUser.getUserCode(), bankbookType);
        	model.addAttribute("fiFundbookBalance",fiFundbookBalance);
        	
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            //model.addAttribute(fiFundbookJournalManager.getAll());
        }
        return model;
    }
}
