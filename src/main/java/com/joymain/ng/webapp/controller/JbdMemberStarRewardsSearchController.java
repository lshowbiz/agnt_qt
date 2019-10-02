package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdMemberStarRewardsManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.StringUtil;

@Controller
//@RequestMapping("/jbdMemberLinkCalcHists/")
public class JbdMemberStarRewardsSearchController {
    
    @Autowired
    private JbdMemberStarRewardsManager JbdMemberStarRewardsManager;
    
    @Autowired
    private JbdPeriodManager jbdPeriodManager; 
    
    
	/**
	 * 奖衔奖励查询
	 * getJbdMemberStarRewardsSearch
	 *
	 * @param request
	 * @return
	 */
    @RequestMapping(value="/jbdMemberStarRewardsSearch",method = RequestMethod.GET)
    public String getJbdMemberStarRewardsSearch(HttpServletRequest request){
        //获取当前登录用户的信息
        JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前会员的登录账号－－对应其他表的会员编号
        String userCode = defSysUser.getUserCode();
        String fyear = request.getParameter("fyear");
        String rewards = request.getParameter("rewards");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userCode);
        params.put("fyear", fyear);
        params.put("rewards", rewards);
        
        //分页单位：固定写法
        List jbdMemberStarRewards = JbdMemberStarRewardsManager.getJbdMemberStarRewardsPage(params);
        
        //当前财年
        JbdPeriod JbdPeriod = jbdPeriodManager.getBdPeriodByTime(new Date());
        Integer currentFyear = JbdPeriod.getFyear(); //当前财年
        String tempYear=StringUtils.isEmpty(fyear)?currentFyear.toString():fyear;
        request.setAttribute("currentFyear", currentFyear);
        
        //查询条件不为空
        if(StringUtils.isNotEmpty(rewards)) {
            if(null==jbdMemberStarRewards||jbdMemberStarRewards.size()==0){
                Map<String,String> temp = new HashMap<String, String>();
                temp.put("USER_CODE", userCode);
                temp.put("F_YEAR", tempYear);
                temp.put("REWARDS", rewards);
                temp.put("IS_REACH", "0");
                jbdMemberStarRewards.add(temp);
            } 
            request.setAttribute("jbdMemberStarRewards", jbdMemberStarRewards);
            return "jbdMemberStarRewards";
        }
        
        LinkedHashMap<String, String> configMap =null;
        try {
            configMap = ListUtil.getListOptions(defSysUser.getCompanyCode(), "star.rewards.repolicy."+tempYear);
        }catch(Exception e){
            request.setAttribute("errorMsg", "无该财年奖励政策");
            request.setAttribute("jbdMemberStarRewards", jbdMemberStarRewards);
            return "jbdMemberStarRewards";
        }
        
        for(int i=0;i<jbdMemberStarRewards.size();i++){
            Map<String,Object> temp = (Map<String,Object>)jbdMemberStarRewards.get(i);
            configMap.remove(temp.get("REWARDS").toString());
        }
        //模拟数据
        for(String key : configMap.keySet()) {
            Map<String,String> temp = new HashMap<String, String>();
            temp.put("USER_CODE", userCode);
            temp.put("F_YEAR", tempYear);
            temp.put("REWARDS", key);
            temp.put("IS_REACH", "0");
            jbdMemberStarRewards.add(temp);
        }
        request.setAttribute("jbdMemberStarRewards", jbdMemberStarRewards);
        
        return "jbdMemberStarRewards";
    }
    
}
