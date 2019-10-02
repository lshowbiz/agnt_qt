package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.FiBcoinJournalManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.FiBcoinJournal;
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
/**
 * 积分条目管理
 * @author hywen
 *
 */
@Controller
@RequestMapping("/fiBcoinJournals*")
public class FiBcoinJournalController {
    private FiBcoinJournalManager fiBcoinJournalManager;
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;
    
    @Autowired
    public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
		this.fiBcoinBalanceManager = fiBcoinBalanceManager;
	}
    
    @Autowired
    public void setFiBcoinJournalManager(FiBcoinJournalManager fiBcoinJournalManager) {
        this.fiBcoinJournalManager = fiBcoinJournalManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="dealStartTime", required=false) String dealStartTime,@RequestParam(value="dealEndTime", required=false) String dealEndTime,
    		HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            //model.addAttribute(fiBcoinJournalManager.search(query, FiBcoinJournal.class));
        	
        	//当前用户
        	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	
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
    		GroupPage page = new GroupPage("","fiBcoinJournals?dealStartTime="+dealStartTime+"&dealEndTime="+dealEndTime+"&pageSize="+pageSize,pageSize,request);
        	
//        	List<FiBcoinJournal> fiBcoinJournalList = fiBcoinJournalManager.getFiBcoinJournalListByUser(jsysUser.getUserCode(), dealStartTime, dealEndTime);
    		List<FiBcoinJournal> fiBcoinJournalList = fiBcoinJournalManager.getFiBcoinJournalListByUserPage(page,jsysUser.getUserCode(), dealStartTime, dealEndTime);

        	request.setAttribute("page", page);//将分页信息加入到request作用域中
        	model.addAttribute("fiBcoinJournalList",fiBcoinJournalList);
        	
        	FiBcoinBalance bcoinBalance = fiBcoinBalanceManager.get(jsysUser.getUserCode());
        	model.addAttribute("bcoinBalance",bcoinBalance);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(fiBcoinJournalManager.getAll());
        }
        return model;
    }
    
}
