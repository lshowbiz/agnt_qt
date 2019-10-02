package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.LinkmanEventManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkmanEventController {
	public static final String MESSAGES_KEY = "successMessages";

	private LinkmanEventManager linkmanEventManager;

	private LinkmanManager linkmanManager;

	@Autowired
	public void setLinkmanEventManager(LinkmanEventManager linkmanEventManager) {
		this.linkmanEventManager = linkmanEventManager;
	}

	@Autowired
	public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}

	/**
	 * 客户管理-事件管理(初始化查询/有条件查询)
	 * 
	 * @author gw 2013-10-14
	 * @param request
	 * @return
	 */
	@RequestMapping("/linkmanEvents")
	public String getLinkmanMaintainList(HttpServletRequest request) {
		String returnView = "linkmanEvents";
		JsysUser defUser = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String userCode = defUser.getUserCode();
		
		String mCode= request.getParameter("mCode")==null?"":request.getParameter("mCode");
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String title = request.getParameter("title")==null?"":request.getParameter("title");
		String eventType = request.getParameter("eventType")==null?"":request.getParameter("eventType");
		if ("0".equals(eventType)) {
			eventType = "";
		}
		String timeBegin = request.getParameter("timeBegin")==null?"":request.getParameter("timeBegin");
		String timeEnd = request.getParameter("timeEnd")==null?"":request.getParameter("timeEnd");

		// 处理字符串：处理如果传递过来的字符串为“null”的情况
		name = StringUtil.dealStr(name);
		title = StringUtil.dealStr(title);
		eventType = StringUtil.dealStr(eventType);
		timeBegin = StringUtil.dealStr(timeBegin);
		timeEnd = StringUtil.dealStr(timeEnd);
		// ----------------------Modify By WuCF 添加分页展示功能
		// 分页单位：固定写法
		Integer pageSize = StringUtil.dealPageSize(request);

		// 创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("", "linkmanEvents?mCode="+mCode+"&name=" + name
				+ "&title=" + title + "&eventType=" + eventType + "&timeBegin="
				+ timeBegin + "&timeEnd=" + timeEnd + "&pageSize=" + pageSize,
				pageSize, request);

		// List linkmanEventList =
		// linkmanEventManager.getLinkmanEventList(userCode,name,title,eventType,timeBegin,timeEnd);
		List linkmanEventList = linkmanEventManager.getLinkmanEventListPage(
				page, userCode, mCode, name, title, eventType, timeBegin, timeEnd);

		request.setAttribute("page", page);
		request.setAttribute("linkmanEventList", linkmanEventList);
		request.setAttribute("mCode", mCode);
		request.setAttribute("name", name);
		request.setAttribute("title", title);
		request.setAttribute("eventType", eventType);
		request.setAttribute("timeBegin", timeBegin);
		request.setAttribute("timeEnd", timeEnd);
		return returnView;
	}

	/**
	 * 客户管理-事件管理-详细查询
	 * 
	 * @author gw 2013-10-14
	 * @param request
	 * @return String
	 */
	@RequestMapping("/linkmanEventQueryDetail")
	public String getLinkmanMaintainDetail(HttpServletRequest request) {

		LinkmanEvent linkmanEvent = null;
		String id = request.getParameter("id");
		if (!StringUtil.isEmpty(id)) {
			linkmanEvent = linkmanEventManager.get(new Long(id));
		} else {
			linkmanEvent = new LinkmanEvent();
		}
		
		request.setAttribute("linkmanEvent", linkmanEvent);
		return "linkmanEventQueryDetail";
	}

	/**
	 * 客户管理-事件管理-删除
	 * 
	 * @author gw 2013-10-14
	 * @param request
	 * @return String
	 */
	@RequestMapping("/linkmanEventDelete")
	public String linkmanMaintainDelete(HttpServletRequest request) {
		String returnView = "linkmanEvents";
		String id = request.getParameter("id");
		if (!StringUtil.isEmpty(id)) {
			Long idl = Long.parseLong(id);
			linkmanEventManager.remove(idl);
		}
		this.saveMessage(request,
				LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));

		/*
		 * JsysUser defUser = (JsysUser)
		 * SecurityContextHolder.getContext().getAuthentication
		 * ().getPrincipal(); String userCode = defUser.getUserCode(); List
		 * linkmanEventList =
		 * linkmanEventManager.getLinkmanEventList(userCode,"","","","","");
		 * request.setAttribute("linkmanEventList", linkmanEventList);
		 * request.setAttribute("userCode", defUser.getUserCode()); return
		 * returnView;
		 */

		return this.getLinkmanMaintainList(request);
	}

	/**
	 * 提示语
	 * 
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
