package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.InwDemandManager;
import com.joymain.ng.service.InwSuggestionManager;
import com.joymain.ng.model.InwDemand;
import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joymain.ng.util.ConfigUtil;

@Controller
public class InwDemandController {
    private InwDemandManager inwDemandManager;
    
    private InwSuggestionManager inwSuggestionManager;

    @Autowired
    public void setInwDemandManager(InwDemandManager inwDemandManager) {
        this.inwDemandManager = inwDemandManager;
    }

    @Autowired
    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager){
    	this.inwSuggestionManager = inwSuggestionManager;
    }
    
    /**
     * 创新共赢的需求(合作共赢)的查询
     * @author gw  2013-08-13
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemands")
    public String getInwDemandList(HttpServletRequest request){
    	String returnView = "inwDemands";
    	//other 1 代表需求 2 代表活动
    	String other = "1";
    	List inwDemandList = inwDemandManager.getInwDemandList(other);
    	request.setAttribute("inwDemandList",inwDemandList);
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//String FILE_URL = ListUtil.getListValue(defUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "3");
    	/*String companyCode = defUser.getCompanyCode();
    	String FILE_URL = inwDemandManager.getFileURL(companyCode);*/
    	//读取图片的路径
        String FILE_URL = ConfigUtil.getConfigValue(defUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
        request.setAttribute("FILE_URL", FILE_URL);
    	System.out.println(request.getSession().getServletContext().getRealPath("/")); 
    	return returnView;
    }
    
    /**
     * 创新共赢的系统需求的查询
     * @author gw  2013-10-24
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemandsSystem")
    public String getInwDemandSystemList(HttpServletRequest request){
    	String returnView = "inwDemandSystem";
    	//other 1 代表需求 2 代表活动  3 代表针对系统(当前运行的系统)的需求
    	String other = "3";
    	List inwDemandList = inwDemandManager.getInwDemandList(other);
    	request.setAttribute("inwDemandList",inwDemandList);
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//读取图片的路径
        String FILE_URL = ConfigUtil.getConfigValue(defUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
        request.setAttribute("FILE_URL", FILE_URL);
    	System.out.println(request.getSession().getServletContext().getRealPath("/")); 
    	return returnView;
    }
    
    /**
     * 创新共赢的活动(合作共赢)的查询
     * @author gw  2013-08-13
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemandsActivity")
    public String getInwDemandActivityList(HttpServletRequest request){
    	String returnView = "inwDemandsActivity";
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//other 1 代表需求 2 代表活动
    	String other = "2";
    	List inwDemandList = inwDemandManager.getInwDemandList(other);
    	request.setAttribute("inwDemandList",inwDemandList);
    	//读取图片的路径
        String FILE_URL = ConfigUtil.getConfigValue(defUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
        request.setAttribute("FILE_URL", FILE_URL);
    	return returnView;
    }
    
    /**
     * 创新共赢的需求（合作共赢)的查询详细
     * @author gw 2013-08-14
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemandDetail")
    public String getInwDemandDetail(HttpServletRequest request){
    	String returnView = "inwDemandDetail";
    	String id = request.getParameter("id");
    	InwDemand inwDemand = inwDemandManager.getInwDemandDetail(id);
    	request.setAttribute("inwDemand", inwDemand);
    	return returnView;
    }
    
    /**
     * 创新共赢的会员查看自己所提的建议(包括公司对该建议的回复)
     * @author gw 2013-09-05
     * @param request
     * @return string
     */
    @RequestMapping("/inwSuggestionReply")
    public String getInwSuggestionReply(HttpServletRequest request){
    	String returnView = "inwSuggestionReply";
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	//获取创新共赢的需求(合作共赢)表的主键
    	String id = request.getParameter("id");
    	List suggestionReplyList = inwSuggestionManager.getInwSuggestionReply(id,userCode);
    	request.setAttribute("suggestionReplyList", suggestionReplyList);
    	String differenceInw = request.getParameter("differenceInw");
    	request.setAttribute("differenceInw", differenceInw);
    	return returnView;
    }
    
    /**
     * 创新共赢的会员查看自己所提建议的详细(包括公司对该建议的回复)
     * @author gw 2013-09-05
     * @param request
     * @return string
     */
    @RequestMapping("/inwSuggestionReplyDetail")
    public String getInwSuggestionReplyDetail(HttpServletRequest request){
    	String returnView = "inwSuggestionReplyDetail";
    	String id = request.getParameter("id");
    	InwSuggestion inwSuggestion = inwSuggestionManager.getInwSuggestionById(id);
    	request.setAttribute("inwSuggestion", inwSuggestion);
    	String differenceInw = request.getParameter("differenceInw");
    	request.setAttribute("differenceInw", differenceInw);
    	return returnView;
    }
}
