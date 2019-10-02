package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.LinkmanMaintainManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.LinkmanMaintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkmanMaintainController {
	public static final String MESSAGES_KEY = "successMessages";

	private LinkmanMaintainManager linkmanMaintainManager;

	private LinkmanManager linkmanManager;

	@Autowired
	public void setLinkmanMaintainManager(LinkmanMaintainManager linkmanMaintainManager) {
		this.linkmanMaintainManager = linkmanMaintainManager;
	}

	@Autowired
	public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}



	/**
	 * 客户管理-客户信息维护查询(初始化查询/有条件查询)
	 * @author gw 2013-10-12
	 * @param request
	 * @return
	 */
	@RequestMapping("/linkmanMaintains")
	public String getLinkmanMaintainList(HttpServletRequest request){
		String returnView = "linkmanMaintains";
		JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userCode = defUser.getUserCode();
		String name = request.getParameter("name");
		String maintenanceTopic = request.getParameter("maintenanceTopic");
		String maintenanceMode = request.getParameter("maintenanceMode");
		if("0".equals(maintenanceMode)){
			maintenanceMode ="";
		}
		String maintenanceTimeBegin = request.getParameter("maintenanceTimeBegin");
		String maintenanceTimeEnd = request.getParameter("maintenanceTimeEnd");


		//处理字符串：处理如果传递过来的字符串为“null”的情况
		name = StringUtil.dealStr(name);
		maintenanceTopic = StringUtil.dealStr(maintenanceTopic);
		maintenanceMode = StringUtil.dealStr(maintenanceMode);
		maintenanceTimeBegin = StringUtil.dealStr(maintenanceTimeBegin);
		maintenanceTimeEnd = StringUtil.dealStr(maintenanceTimeEnd);  
		//----------------------Modify By WuCF 添加分页展示功能 
		//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","linkmanMaintains?name="+name+"&maintenanceTopic="+maintenanceTopic+
				"&maintenanceMode="+maintenanceMode+"&maintenanceTimeBegin="+maintenanceTimeBegin+
				"&maintenanceTimeEnd="+maintenanceTimeEnd+"&pageSize="+pageSize,pageSize,request);
		//   	   List linkmanMaintainList = linkmanMaintainManager.getLinkmanMaintainList(userCode,name,maintenanceTopic,maintenanceMode,maintenanceTimeBegin,maintenanceTimeEnd);
		List linkmanMaintainList = linkmanMaintainManager.getLinkmanMaintainListPage(page,userCode,name,maintenanceTopic,maintenanceMode,maintenanceTimeBegin,maintenanceTimeEnd);


		request.setAttribute("page", page);
		request.setAttribute("linkmanMaintainList", linkmanMaintainList);

		request.setAttribute("name",name);
		request.setAttribute("maintenanceTopic",maintenanceTopic);
		request.setAttribute("maintenanceMode",maintenanceMode);
		request.setAttribute("maintenanceTimeBegin",maintenanceTimeBegin);
		request.setAttribute("maintenanceTimeEnd",maintenanceTimeEnd);
		return returnView;
	}

	/**
	 * 客户管理-客户维护-详细查询
	 * @author gw 2013-10-12
	 * @param request
	 * @return
	 */
	@RequestMapping("/linkmanMaintainQueryDetail")
	public String getLinkmanMaintainDetail(HttpServletRequest request){
		String returnView = "linkmanMaintainQueryDetail";
		String id = request.getParameter("id");
		JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
		request.setAttribute("linkmanCategoryList", linkmanCategoryList);
		request.setAttribute("userCode", defUser.getUserCode());
		LinkmanMaintain linkmanMaintain = linkmanMaintainManager.getLinkmanMaintain(id);
		request.setAttribute("linkmanMaintain", linkmanMaintain);	
		return returnView;
	}


	/**
	 * 客户维护-删除
	 * @author gw 2013-10-12
	 * @param request
	 * @return String
	 */
	@RequestMapping("/linkmanMaintainDelete")
	public String linkmanMaintainDelete(HttpServletRequest request){
		String returnView = "linkmanMaintains";
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			Long idl = Long.parseLong(id);
			linkmanMaintainManager.remove(idl);
		}
		this.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
		
        /*		
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userCode = defUser.getUserCode();
		List linkmanMaintainList = linkmanMaintainManager.getLinkmanMaintainList(userCode,"","","","","");
		request.setAttribute("linkmanMaintainList", linkmanMaintainList);
		request.setAttribute("userCode", defUser.getUserCode());
		return returnView;
        */		
		
		return this.getLinkmanMaintainList(request);
	}

	/**
	 * 提示语
	 * @author gw 2013-10-22
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
