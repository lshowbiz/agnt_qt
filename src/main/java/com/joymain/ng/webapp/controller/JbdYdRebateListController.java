


package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdYdRebateListManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JbdYdRebateListController extends BaseFormController{
    //日志
    private final Log log = LogFactory.getLog(JbdYdRebateListController.class);
    
    @Autowired
    private JbdYdRebateListManager jbdYdRebateListManager;
    @Autowired
	private JsysUserManager jsysUserManager;
    
    /**
     * 云店返利奖
     */
    @RequestMapping(value="/jbdYdRebateList",method=RequestMethod.GET)
    public String getJbdCalcDayFbs(HttpServletRequest request){
    	String returnView = "jbdYdRebateLists";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		
        String startCalcTime = request.getParameter("startCalcTime");
        String endCalcTime = request.getParameter("endCalcTime");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userCode);
        params.put("startCalcTime", startCalcTime);
        params.put("endCalcTime", endCalcTime);
        
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);
        
        GroupPage page = new GroupPage("","jbdYdRebateList?startCalcTime="+StringUtil.dealStr(startCalcTime)+"&endCalcTime="+StringUtil.dealStr(endCalcTime)+"&pageSize="+pageSize,pageSize,request);
                
        List jbdYdRebateLists = jbdYdRebateListManager.getJbdYdRebateListsPage(params,page);
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jbdYdRebateLists", jbdYdRebateLists);
	   return returnView;
    }
    
    /**
     * 云店返利奖查询
     */
    @RequestMapping(value="/mobileJbdYdRebateList/api/jbdYdRebateLists")
    @ResponseBody
    public Object getJbdCalcDayFbFroM(String userId,String token,int pageNum,int pageSize,String startCalcTime,String endCalcTime,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userId);
        params.put("startCalcTime", startCalcTime);
        params.put("endCalcTime", endCalcTime);

        GroupPage page = new GroupPage("","jbdYdRebateList?startCalcTime="+StringUtil.dealStr(startCalcTime)+"&endCalcTime="+StringUtil.dealStr(endCalcTime)+"&pageSize="+pageSize,pageSize,request);
		List list = jbdYdRebateListManager.getJbdYdRebateListsPage(params,page);

        Map map = new HashMap();
        map.put("pageCount",String.valueOf(page.getPagecount()));
        map.put("pages", String.valueOf(page.getPages()));
        map.put("pageSize", String.valueOf(page.getPagesize()));
        map.put("pageData", list);
		return map;
    }
}
