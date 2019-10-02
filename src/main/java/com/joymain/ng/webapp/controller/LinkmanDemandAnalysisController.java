
package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanDemand;
import com.joymain.ng.service.LinkmanDemandManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

/**
 * 客户需求分析 --查询
 * @author gw  2013-09-27
 *
 */
@Controller
public class LinkmanDemandAnalysisController {
	public static final String MESSAGES_KEY = "successMessages";
	
	private LinkmanDemandManager linkmanDemandManager;
	
    private LinkmanManager linkmanManager;


	@Autowired
	public void setLinkmanDemandManager(LinkmanDemandManager linkmanDemandManager) {
		this.linkmanDemandManager = linkmanDemandManager;
	}
	
	@Autowired
	public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}


	/**
	 * 客户需求分析--初始化或有条件查询
	 * @author gw 2013-09-27
	 * @param request
	 * @return String
	 */
	@RequestMapping("/linkmanDemandAnalysisQuery")
	public String getLinkmanDemandAanlysis(HttpServletRequest request){
		String returnView = "linkmanDemandsAnalysis";
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	
    	String linkmanName = request.getParameter("name");
    	String registerTimeBegin = request.getParameter("registerTimeBegin");
    	String registerTimeEnd = request.getParameter("registerTimeEnd");
    	
    	//处理字符串：处理如果传递过来的字符串为“null”的情况
    	linkmanName = StringUtil.dealStr(linkmanName);
    	registerTimeBegin = StringUtil.dealStr(registerTimeBegin);
    	registerTimeEnd = StringUtil.dealStr(registerTimeEnd);
    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","linkmanDemandAnalysisQuery?name="+linkmanName+"&registerTimeBegin="+registerTimeBegin+
									   "&registerTimeEnd="+registerTimeEnd+"&pageSize="+pageSize,pageSize,request);
    	
//    	List linkmanDemandList = linkmanDemandManager.getLinkmanDemandList(userCode,linkmanName,registerTimeBegin,registerTimeEnd);
    	List linkmanDemandList = linkmanDemandManager.getLinkmanDemandListPage(page,userCode,linkmanName,registerTimeBegin,registerTimeEnd);
    	
    	request.setAttribute("page", page);
    	request.setAttribute("linkmanDemandList", linkmanDemandList);
    	
    	request.setAttribute("name",linkmanName);
    	request.setAttribute("registerTimeBegin",registerTimeBegin);
    	request.setAttribute("registerTimeEnd",registerTimeEnd);
    	return returnView;
	}
	
	/**
	 * 客户需求分析---修改初始化查询
	 * @author gw 2013-09-27
	 * @param request
	 * @return string
	 */
	@RequestMapping(value="/linkmanDemandAnalysisUpdateInit", method=RequestMethod.GET)
	public String getLinkmanDemandAanlysisUpdateInit(HttpServletRequest request){
		String returnView = "linkmanDemandsAnalysisForm";
    	String id = request.getParameter("id");
    	LinkmanDemand linkmanDemands = linkmanDemandManager.getLinkmanDemand(id);
    	request.setAttribute("linkmanDemand", linkmanDemands);
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//查出该会员已经定义的客户分类
	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	String userCode = defUser.getUserCode();
	    request.setAttribute("userCode", userCode);
    	return returnView;
	}
	
	/**
	 * 客户需求分析--初始化或有条件查询
	 * @author gw 2013-09-27
	 * @param request
	 * @return String
	 */
	@RequestMapping("/linkmanDemandAnalysisDelete")
	public String getLinkmanDemandAanlysisDelete(HttpServletRequest request){
		String returnView = "linkmanDemandsAnalysis";
		String id = request.getParameter("id");
		linkmanDemandManager.remove(Long.parseLong(id));
		this.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	String linkmanName = request.getParameter("name");
    	String registerTimeBegin = request.getParameter("registerTimeBegin");
    	String registerTimeEnd = request.getParameter("registerTimeEnd");
    	List linkmanDemandList = linkmanDemandManager.getLinkmanDemandList(userCode,linkmanName,registerTimeBegin,registerTimeEnd);
    	request.setAttribute("linkmanDemandList", linkmanDemandList);
    	return returnView;
	}
	
	/**
	 * 提示语
	 * @author gw 2013-10-10
	 * @param request
	 * @param msg
	 */
	public void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add(msg);
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}
}
