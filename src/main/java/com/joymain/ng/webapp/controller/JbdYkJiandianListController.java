


package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdYkJiandianListManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
public class JbdYkJiandianListController extends BaseFormController{
    //日志
    private final Log log = LogFactory.getLog(JbdYkJiandianListController.class);
    
    @Autowired
    private JbdYkJiandianListManager jbdYkJiandianListManager;
    @Autowired
	private JsysUserManager jsysUserManager;
    
    /**
     * 云客推荐奖励查询
     */
    @RequestMapping(value="/jbdYkJiandianList",method=RequestMethod.GET)
    public String getJbdYkJiandianList(HttpServletRequest request){
    	String returnView = "jbdYkJiandianList";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		
        String startweek = request.getParameter("startweek");
        String endweek = request.getParameter("endweek");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userCode);
        params.put("startweek", startweek);
        params.put("endweek", endweek);
        
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);
        
        GroupPage page = new GroupPage("","jbdYkJiandianList?startweek="+StringUtil.dealStr(startweek)+"&endweek="+StringUtil.dealStr(endweek)+"&pageSize="+pageSize,pageSize,request);
                
        List jbdYkJiandianList = jbdYkJiandianListManager.getJbdYkJiandianList(params,page);
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jbdYkJiandianList", jbdYkJiandianList);
	   return returnView;
    }
    
    /**
     * 云客推荐奖励查询
     */
    @RequestMapping(value="/mobileJbdYkJiandianList/api/jbdYkJiandianList")
    @ResponseBody
    public List getJbdCalcDayFbFroM(String userId,String token,int pageNum,int pageSize,String startweek,String endweek,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userId);
        params.put("startweek", startweek);
        params.put("endweek", endweek);
        GroupPage page = new GroupPage("","jbdYkJiandianList?startComTime="+StringUtil.dealStr(startweek)+"&endComTime="+StringUtil.dealStr(endweek)+"&pageSize="+pageSize,pageSize,request);
		List list = jbdYkJiandianListManager.getJbdYkJiandianList(params,page);
		return list;
    }
}
