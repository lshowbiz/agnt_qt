package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiMemberManager;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JmiYkSearchListController {
	//日志
    private final Log log = LogFactory.getLog(JmiYkSearchListController.class);
    @Autowired
    private JmiMemberManager jmiMemberManager;
    @Autowired
	private JsysUserManager jsysUserManager;
    
    /**
     * 推荐云客
     */
    @RequestMapping(value="/jmiYkSearchList",method=RequestMethod.GET)
    public String getJbdYkJiandianList(HttpServletRequest request){
    	String returnView = "jmiYkSearchList"; 
		List jmiYkSearchList =new ArrayList<Map>();  
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		//会员下推荐云客编号
        String ykUserCode=request.getParameter("ykUserCode");
        String mobiletele=request.getParameter("mobiletele");
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);
        GroupPage page = new GroupPage("","jmiYkSearchList?ykUserCode="+ StringUtil.dealStr(ykUserCode)+"&pageSize="+pageSize,pageSize,request);
        Map<String,String> params =new HashMap<String, String>();
        params.put("userCode", userCode);
        params.put("ykUserCode", ykUserCode);
        params.put("mobiletele", mobiletele);
        List tempList = jmiMemberManager.getjmiYkSearchList(params,page);
        for(int i=0;tempList.size()>i;i++){
        	Map tempMap=(Map) tempList.get(i);
        	Map map=new HashMap<String, String>(); 
    		map.put("userCode", tempMap.get("User_Code"));
    		map.put("mobiletele", tempMap.get("MOBILETELE"));
            map.put("inviteCode", tempMap.get("invite_code"));
    		String paperNumber=   tempMap.get("Papernumber")==null?"": (String) tempMap.get("Papernumber");
    		String paper="";
            if(StringUtil.isObjectNotBlank(paperNumber)&&paperNumber.length()>=8) {
                //身份证号码屏蔽
                paper = paperNumber.replaceFirst(paperNumber.substring(1, 4), "***");
                paper = paper.replaceFirst(paper.substring(paper.length() - 4, paper.length()), "****");
            }
    		map.put("paperNumber",paper );
    		jmiYkSearchList.add(map);
        }
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jmiYkSearchList", jmiYkSearchList);
	   return returnView;
    }
}
