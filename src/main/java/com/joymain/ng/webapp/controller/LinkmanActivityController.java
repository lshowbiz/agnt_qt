package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.LinkmanActivityManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.LinkmanActivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkmanActivityController {
	public static final String MESSAGES_KEY = "successMessages";

	private LinkmanActivityManager linkmanActivityManager;

	@Autowired
	public void setLinkmanActivityManager(LinkmanActivityManager linkmanActivityManager) {
		this.linkmanActivityManager = linkmanActivityManager;
	}

	/**
	 * 客户管理－活动管理－初始化查询或有条件查询
	 * @param gw 2013-10-16
	 * @param request
	 * @return string
	 */
	@RequestMapping("/linkmanActivities")
	public String getLinkmaActivity(HttpServletRequest request){
		String returnView = "linkmanActivities";
		//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();

		String topic = request.getParameter("topic");
		String eventName = request.getParameter("eventName");
		String eventType = request.getParameter("eventType");
		if("0".equals(eventType)){
			eventType = "";
		}
		String beginTimeBegin = request.getParameter("beginTimeBegin");
		String beginTimeEnd = request.getParameter("beginTimeEnd");
		String endTimeBegin = request.getParameter("endTimeBegin");
		String endTimeEnd = request.getParameter("endTimeEnd");

		//----------------------Modify By WuCF 添加分页展示功能 
		//处理字符串
		topic = StringUtil.dealStr(topic);
		eventName = StringUtil.dealStr(eventName);
		eventType = StringUtil.dealStr(eventType);
		beginTimeBegin = StringUtil.dealStr(beginTimeBegin);
		beginTimeEnd = StringUtil.dealStr(beginTimeEnd);  
		endTimeBegin = StringUtil.dealStr(endTimeBegin);
		endTimeEnd = StringUtil.dealStr(endTimeEnd);
		
		//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);

		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","linkmanActivities?topic="+topic+"&eventName="+eventName+"&eventType="+eventType+
				"&beginTimeBegin="+beginTimeBegin+"&beginTimeEnd="+beginTimeEnd+
				"&endTimeBegin="+endTimeBegin+"&endTimeEnd="+endTimeEnd+"&pageSize="+pageSize,pageSize,request);

		//List linkmanActivityList = linkmanActivityManager.getLinkmanActivityList(userCode,topic,eventName,eventType,beginTimeBegin,beginTimeEnd,endTimeBegin,endTimeEnd);
		List linkmanActivityList = linkmanActivityManager.getLinkmanActivityListPage(page,userCode,topic,eventName,eventType,beginTimeBegin,beginTimeEnd,endTimeBegin,endTimeEnd);

		request.setAttribute("page",page);
		request.setAttribute("linkmanActivityList", linkmanActivityList);
		request.setAttribute("topic",topic);
		request.setAttribute("eventName",eventName);
		request.setAttribute("eventType",eventType);
		request.setAttribute("beginTimeBegin",beginTimeBegin);
		request.setAttribute("beginTimeEnd",beginTimeEnd);
		request.setAttribute("endTimeBegin",endTimeBegin);
		request.setAttribute("endTimeEnd",endTimeEnd);
		return returnView;
	}

	/**
	 * 客户管理－活动管理－详细查询
	 * @author gw 2013-10-16
	 * @param request
	 * @return　String
	 */
	@RequestMapping("/linkmanActivitiesDetailQuery")
	public String getLinkmanActivityDetail(HttpServletRequest request){
		String returnView = "linkmanActivitiesDetailQuery";
		//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String companyCode = defSysUser.getCompanyCode();
		String id = request.getParameter("id");
		LinkmanActivity linkmanActivity = linkmanActivityManager.getLinkmanActivityDetail(id);
		request.setAttribute("linkmanActivity", linkmanActivity);
		return returnView;
	}


	/**
	 * 会员系统－活动管理－删除
	 * @author gw 2013-10-23
	 * @param request
	 * @return　String
	 */
	@RequestMapping("/linkmanActivityDelete")
	public String LinkmanActivityDelete(HttpServletRequest request){
		String returnView = "linkmanActivities";
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			Long idl = Long.parseLong(id);
			linkmanActivityManager.remove(idl);
			this.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
		}
		
		/*JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userCode = defSysUser.getUserCode();
		List linkmanActivityList = linkmanActivityManager.getLinkmanActivityList(userCode,"","","","","","","");
		request.setAttribute("linkmanActivityList", linkmanActivityList);
		return returnView;*/
		
		return this.getLinkmaActivity(request);
		
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
