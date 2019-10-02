package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.JfiBankbookBalance;
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
 * 基金条目管理
 * @author hywen
 *
 */
@Controller
@RequestMapping("/fiBankbookJournals*")
public class FiBankbookJournalController {
    private FiBankbookJournalManager fiBankbookJournalManager;
    private FiBankbookBalanceManager fiBankbookBalanceManager;

    @Autowired
    public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
        this.fiBankbookJournalManager = fiBankbookJournalManager;
    }
    
    @Autowired
    public void setFiBankbookBalanceManager(
			FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}

	@RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="dealStartTime", required=false) String dealStartTime,@RequestParam(value="dealEndTime", required=false) String dealEndTime,
    		HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	//当前用户
        	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	//List fiBankbookJournalList=fiBankbookJournalManager.search(query, FiBankbookJournal.class);
        	
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
    		GroupPage page = new GroupPage("","fiBankbookJournals?dealStartTime="+dealStartTime+"&dealEndTime="+dealEndTime+"&pageSize="+pageSize,pageSize,request);
        	
//        	List<FiBankbookJournal> fiBankbookJournalList=fiBankbookJournalManager.getFiBankbookJournalListByUser(jsysUser.getUserCode(), dealStartTime, dealEndTime);
        	List<FiBankbookJournal> fiBankbookJournalList=fiBankbookJournalManager.getFiBankbookJournalListByUserPage(page,jsysUser.getUserCode(), dealStartTime, dealEndTime);

        	
        	request.setAttribute("page", page);//将分页信息加入到request作用域中
            model.addAttribute("fiBankbookJournalList",fiBankbookJournalList);
            
            //查询存折余额对象 
        	FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jsysUser.getUserCode(), "1");
        	model.addAttribute("fiBankbookBalance",fiBankbookBalance);
        	
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            //model.addAttribute(fiBankbookJournalManager.getAll());
        }
        return model;
    }
    
}
