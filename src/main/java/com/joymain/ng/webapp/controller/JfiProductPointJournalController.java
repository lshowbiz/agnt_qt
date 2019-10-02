package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.FiProductPointJournalManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.FiProductPointJournal;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JfiBankbookJournal;
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
 * 存折查询
 * @author hywen
 *
 */
@Controller
@RequestMapping("/jfiProductPointJournals*")
public class JfiProductPointJournalController {
	
    private FiProductPointJournalManager fiProductPointJournalManager;
    
    public FiProductPointJournalManager getFiProductPointJournalManager() {
		return fiProductPointJournalManager;
	}

    @Autowired
	public void setFiProductPointJournalManager(
			FiProductPointJournalManager fiProductPointJournalManager) {
		this.fiProductPointJournalManager = fiProductPointJournalManager;
	}


	@RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="dealStartTime", required=false) String dealStartTime,@RequestParam(value="dealEndTime", required=false) String dealEndTime,
    		HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
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
    		GroupPage page = new GroupPage("","jfiProductPointJournals?dealStartTime="+dealStartTime+"&dealEndTime="+dealEndTime+"&pageSize="+pageSize,pageSize,request);
        	 
        	//查询存折
//        	List<JfiBankbookJournal> jfiBankbookJournalList=jfiBankbookJournalManager.getJfiBankbookJournalListByUserCode(jsysUser.getUserCode(),dealStartTime,dealEndTime);
//    		List<JfiBankbookJournal> jfiBankbookJournalList=jfiBankbookJournalManager.getJfiBankbookJournalListByUserCodePage(page,jsysUser.getUserCode(),dealStartTime,dealEndTime);
    		
    		//查询产品积分流水表
    		List<FiProductPointJournal> jfiProductPointJournalList = fiProductPointJournalManager.getJfiProductPointJournalListByUserCodePage(page,jsysUser.getUserCode(),dealStartTime,dealEndTime);
        	request.setAttribute("page", page);//将分页信息加入到request作用域中
        	model.addAttribute("jfiProductPointJournalList",jfiProductPointJournalList);
/*        	
        	//查询存折余额对象 
        	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.get(jsysUser.getUserCode());
        	model.addAttribute("jfiBankbookBalance",jfiBankbookBalance);
*/
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
           
        }
        return model;
    }
    
}
