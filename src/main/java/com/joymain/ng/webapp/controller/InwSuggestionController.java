package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.InwSuggestionManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 需求共赢的建议的controller
 * @author gw 2013-08-14
 *
 */
@Controller
public class InwSuggestionController {
    private InwSuggestionManager inwSuggestionManager;

    @Autowired
    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
        this.inwSuggestionManager = inwSuggestionManager;
    }
    
    /**
     * 创新共赢-提交标准
     * @author gw 2013-08-26
     * @param request
     * @return string
     */
    @RequestMapping("/inwSubmitStandard")
    public String getInwSubmitStandard(HttpServletRequest request){
    	String returnView = "inwSubmitStandard";   	
        return returnView;
    }

    
    //--------------------------------------------下面是创新共赢新的需求 变更--------------------------新的代码
    /**
     * 创新&发展新的需求变更----提交用户的方案或建议的初始化
     * @author gw 2013-11-07 
     * @param request
     * @return string
     */
    @RequestMapping("/inwSuggestionformNew")
    public String getInwSuggestionNewInit(HttpServletRequest request,InwSuggestion inwSuggestion){
    	String returnView = "inwSuggestionformNew";
    	//获得需求（合作共赢）表的主键
    	String demandId = request.getParameter("demandId");
    	//获取需求分类表的主键
    	String demandsortId = request.getParameter("demandsortId");
    	inwSuggestion.setDemandId(demandId);
    	inwSuggestion.setDemandsortId(demandsortId);
    	//向页面发送需求（合作共赢）表的主键------------------这个地方需要注意
    	request.getSession().setAttribute("demandId",demandId);
    	request.getSession().setAttribute("demandsortId",demandsortId);
    	return returnView;
    }

    
    /**
     * 创新共赢---您的提交记录--初始化查询(新需求)
     * @author gw 2013-11-08
     * @param request
     * @return string
     */
    @RequestMapping("/inwSuggestionReplyNew")
    public String getInwSuggestionQueryNewInit(HttpServletRequest request){
    	String returnView = "inwSuggestionReplyNew";
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","inwSuggestionReplyNew?pageSize="+pageSize,pageSize,request);
    	
    	
//    	List suggestionReplyNewList = inwSuggestionManager.getInwSuggestionReply("",userCode);
    	List suggestionReplyNewList = inwSuggestionManager.getInwSuggestionReplyPage(page,"",userCode);
    	
    	request.setAttribute("page", page);
    	request.setAttribute("suggestionReplyNewList", suggestionReplyNewList);
    	return returnView;   
    	
    }

    
    /**
     * 创新共赢---您的提交记录--初始化查询(新需求)--详细查询(新需求)
     * @author gw 2013-11-08
     * @param request
     * @return string
     */
    @RequestMapping("/inwSuggestionReplyDetailNew")
    public String getInwSuggestionQueryDetailNewInit(HttpServletRequest request){
    	String returnView = "inwSuggestionReplyDetailNew";
    	String id = request.getParameter("id");
    	InwSuggestion inwSuggestion = inwSuggestionManager.getInwSuggestionById(id);
    	// add by gw 2014-05-19
    	//REPLY_YES_NO 是否有建议回复(Y表示有新的建议回复,N或空表示没有新的建议回复) 
    	//REPLY_YES_NO""表示已经查看了建议回复(也就是没有新的建议回复)                           
    	inwSuggestion.setReplyYesNo("");
    	inwSuggestionManager.save(inwSuggestion);
    	
    	request.setAttribute("inwSuggestion", inwSuggestion);
    	String demandId = inwSuggestion.getDemandId();
    	request.setAttribute("demandId", demandId);
    	String demandsortId = inwSuggestion.getDemandsortId();
    	request.setAttribute("demandsortId", demandsortId);
    	return returnView;
    	
    }
    
    
}
