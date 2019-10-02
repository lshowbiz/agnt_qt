


package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdCalcDayFbManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
public class JbdCalcDayFbController extends BaseFormController{
    //日志
    private final Log log = LogFactory.getLog(JbdCalcDayFbController.class);
    
    @Autowired
    private JbdCalcDayFbManager jbdCalcDayFbManager;
    @Autowired
	private JsysUserManager jsysUserManager;
    
    /**
     * 创业保障奖查询
     */
    @RequestMapping(value="/jbdCalcDayFbs",method=RequestMethod.GET)
    public String getJbdCalcDayFbs(HttpServletRequest request){
    	String returnView = "jbdCalcDayFbs";
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
        
        GroupPage page = new GroupPage("","jbdCalcDayFbs?startweek="+StringUtil.dealStr(startweek)+"&endweek="+StringUtil.dealStr(endweek)+"&pageSize="+pageSize,pageSize,request);
                
        List jbdCalcDayFbs = jbdCalcDayFbManager.getJbdCalcDayFbsPage(params,page);
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jbdCalcDayFbs", jbdCalcDayFbs);
	   return returnView;
    }
    
    /**
     * 创业保障奖查询
     */
    @RequestMapping(value="/mobileJbdCalcDayFb/api/jbdCalcDayFbs")
    @ResponseBody
    public List getJbdCalcDayFbFroM(String userId,String token,int pageNum,int pageSize,String startWeek,String endWeek,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userId);
        params.put("startweek", startWeek);
        params.put("endweek", endWeek);
        GroupPage page = new GroupPage("","jbdCalcDayFbs?startweek="+StringUtil.dealStr(startWeek)+"&endweek="+StringUtil.dealStr(endWeek)+"&pageSize="+pageSize,pageSize,request);
		List list = jbdCalcDayFbManager.getJbdCalcDayFbsPage(params,page);
		return list;
    }
}
