package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.model.InwViewpeople;
import com.joymain.ng.service.InwSuggestionManager;
import com.joymain.ng.service.InwViewpeopleManager;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("suggest")
public class ApiSuggestController {
	private final Log log = LogFactory.getLog(ApiSuggestController.class);
	 private InwSuggestionManager inwSuggestionManager;
	 private InwViewpeopleManager inwViewpeopleManager;

		@RequestMapping(value="api/list")
		@ResponseBody
	    public Object list(HttpServletRequest request){
		    String userCode = request.getParameter("userId"); //会员编号
		    String token = request.getParameter("token"); //会员token
	        String suggestionUserCode = request.getParameter("suggestionUserCode"); //建议的建议人
	        String subject = request.getParameter("subject");//建议主题
	        String createTimeBegin = request.getParameter("startTime");//建议时间--开始
	        String createTimeEnd = request.getParameter("endTime");  //建议时间--结束
	        String lookStatus = request.getParameter("viewstatus");    //建议查看状态的查询情况 //0代表未阅，1代表已阅，2全部（已阅＋未阅）
	 
	        //查询状态为空,就表明是初始化查询,初始化查询默认让其查未阅的
	        if(StringUtil.isEmpty(lookStatus)){
	        	lookStatus = "0";
	        }
	        
	        Map<String,Object> param = new HashMap<String,Object>();
	        param.put("userCode", userCode);
	        param.put("suggestionUserCode", suggestionUserCode);
	        param.put("subject", subject);
	        param.put("createTimeBegin", createTimeBegin );
	        param.put("createTimeEnd", createTimeEnd);
	        param.put("lookStatus", lookStatus);

	        
	       	//分页单位：固定写法
	        Integer pageSize = StringUtil.dealPageSize(request);

			//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
			GroupPage page = new GroupPage("suggest","list",pageSize,request);
	        
	        return inwSuggestionManager.list(param,page);
	    }

		
		@RequestMapping(value="api/detail")
		@ResponseBody
	    public Object detail(HttpServletRequest request,@RequestParam("id") long id,@RequestParam("userId") String userId){
			 //先去数据库表inw_viewPeople中查,看该人对该条建议是否已阅
			 List inwViewpeopleIsExist =  inwSuggestionManager.getInwViewpeopleIsExist(id+"",userId);
			 if(!(null != inwViewpeopleIsExist && inwViewpeopleIsExist.size() > 0)){
				 //表明当前登录用户没有查看这条建议，因此就向inw_viewPeople中插入数据了
			     //获取系统当前的登录时间
				 InwViewpeople inwViewpeople = new InwViewpeople();
				 inwViewpeople.setSuggestionid(id+"");
		   		 Date date = new Date();
		   		 inwViewpeople.setViewTime(date);
		   		 inwViewpeople.setUserCode(userId);
		   		 //viewStatus字段暂时不用,作扩展字段,INW_VIEWPEOPLE该表里面有关于相关建议的信息,则表明该建议已阅;
		   		 inwViewpeopleManager.save(inwViewpeople);
			 }
			 InwSuggestion o =  inwSuggestionManager.get(id);
			 Map<String,Object> param = new HashMap<String,Object>();
			 if(null != o ){
				 param.put("subject", StringUtil.dealStr(o.getSubject()));
				 param.put("content", StringUtil.dealStr(o.getContent()));
				 param.put("createTime", DateUtil.getDate(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				 param.put("userCode", o.getUserCode()); 
			 }
			return param;
			
		}
	 
	public InwSuggestionManager getInwSuggestionManager() {
		return inwSuggestionManager;
	}

	@Autowired
	public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
		this.inwSuggestionManager = inwSuggestionManager;
	}
	  public InwViewpeopleManager getInwViewpeopleManager() {
		return inwViewpeopleManager;
	}

	  @Autowired
	public void setInwViewpeopleManager(InwViewpeopleManager inwViewpeopleManager) {
		this.inwViewpeopleManager = inwViewpeopleManager;
	}
}
