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
import com.joymain.ng.service.JbdYd399RecommendListManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
public class JbdYd399RecommendListController {
    //日志　
    private final Log log = LogFactory.getLog(JbdYd399RecommendListController.class);
    @Autowired
    private JbdYd399RecommendListManager jbdYd399RecommendListManager;
    
    @Autowired
    public void setJbdYd399RecommendListManager(JbdYd399RecommendListManager jbdYd399RecommendListManager) {
        this.jbdYd399RecommendListManager = jbdYd399RecommendListManager;
    }

    @Autowired
    private JsysUserManager jsysUserManager;
    
    @RequestMapping(value="/getJbdYd399RecommendList",method = RequestMethod.GET)
    public String getJbdYd399RecommendList(HttpServletRequest request){
    	String returnView = "jbdYd399RecommendLists";
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
        GroupPage page = new GroupPage("","getJbdYd399RecommendList?startweek="+StringUtil.dealStr(startweek)+"&endweek="+StringUtil.dealStr(endweek)+"&pageSize="+pageSize,pageSize,request);
        List jbdYd399RecommendList = jbdYd399RecommendListManager.getJbdYd399RecommendListByUserCodeWeekPage(params,page);
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jbdYd399RecommendList", jbdYd399RecommendList);
	   return returnView;
    }
    
    /**
	 *　判断字符串是否为空
	 * @author Administrator
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}
    
    /**
     * 查询手机端接口
     * getMobilePublicSchedules
     * @param userId
     * @param token
     * @param wweek
     * @return
     */
    @RequestMapping(value="/mobileJbdYdRebateList/api/getJbdYd399RecommendList")
    @ResponseBody
    public Object getJbdMemberStarListForMobile(String userId,String token,int pageNum,int pageSize,String startweek,String endweek,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userId);
		params.put("startweek", startweek);
		params.put("endweek", endweek);
		GroupPage page = new GroupPage("", "getJbdYd399RecommendList?startweek=" + StringUtil.dealStr(startweek)
				+ "&endweek=" + StringUtil.dealStr(endweek) + "&pageSize=" + pageSize, pageSize, request);
		List jbdYd399RecommendList = jbdYd399RecommendListManager.getJbdYd399RecommendListByUserCodeWeekPage(params, page);
        Map map = new HashMap();
        map.put("pageCount",String.valueOf(page.getPagecount()));
        map.put("pages", String.valueOf(page.getPages()));
        map.put("pageSize", String.valueOf(page.getPagesize()));
        map.put("pageData", jbdYd399RecommendList);
		return map;
	}

}
