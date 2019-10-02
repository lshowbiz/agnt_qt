package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MessageUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;

@Controller
public class JmiRecommendRefController {
    private JmiRecommendRefManager jmiRecommendRefManager;

    @Autowired
    public JbdPeriodManager jbdPeriodManager;
    @Autowired
    private JmiMemberManager jmiMemberManager;
    
    @Autowired
    public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
        this.jmiRecommendRefManager = jmiRecommendRefManager;
    }
    
  
    @RequestMapping(value="/jmiList",method = RequestMethod.GET)
    public String getJmiRecommendRefs(){

    	return "jmiRecommendRefs";
    }

    /**
     * 推荐组织业绩首方法
     * @param request
     * @return
     */
    @RequestMapping(value="/jbdRecommendBonusTree", method = RequestMethod.GET)
    public String getJbdRecommendBonusTree(HttpServletRequest request){
    	

		request.setAttribute("curBdPeriod", getCurBdPeriod());
    	
    	return "jbdRecommendBonusTree";
    }
    /**
     * 推荐组织业绩首方法
     * @param request
     * @return
     */
    @RequestMapping(value="/jbdRecommendBonusTreeSelect", method = RequestMethod.GET)
    public String getJbdRecommendBonusTreeSelect(HttpServletRequest request){
    	String returnView = "jbdRecommendBonusTreeSelect";
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = request.getParameter("userCode");
		String formatedWeek = request.getParameter("formatedWeek");

        String doubleView = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "double.view");
        request.setAttribute("doubleView", doubleView);
		request.setAttribute("curBdPeriod", getCurBdPeriod());
		

    	if(!StringUtil.getIsSeachTime(defSysUser.getCompanyCode())){
			return returnView;
		}
		
		
		if ("bdRecommendBonusTreeQuery".equals(request.getParameter("strAction"))) {
			//判断用户所输入的期数是否存在
			JbdPeriod bdPeriod = this.jbdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
			if (bdPeriod == null) {
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return returnView;
			}
			//判断是否为下限
			JmiRecommendRef LoginMember = jmiRecommendRefManager.get(defSysUser.getUserCode());
			JmiRecommendRef searchMember = jmiRecommendRefManager.get(userCode);
			if(searchMember!=null && !StringUtil.getCheckIsDownline(LoginMember.getTreeIndex(), searchMember.getTreeIndex())){
				return returnView;
			}
			if(userCode.equals("") || userCode == null){
				userCode = defSysUser.getUserCode();
			}
			Integer wweekInt=StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek));
			List jbdDayBonusList=new ArrayList();
			Map topMap=jmiMemberManager.getJbdDayBounsCalcByUserCode(userCode, wweekInt);
			if(topMap==null){
				//MessageUtil.saveMessage(request, "查询结果");
				return returnView;
			}
			jbdDayBonusList.add(topMap);
			getBdBonusTree("recommend_no", jbdDayBonusList, topMap, 5, 0, wweekInt);
			
			Map<String, Integer> rowMap = new HashMap<String, Integer>();
			if (jbdDayBonusList != null && jbdDayBonusList.size() > 0) {
				for (int i = 0; i < jbdDayBonusList.size(); i++) {
					Map jbdDayBonusMap = (HashMap) jbdDayBonusList.get(i);
					rowMap.put(jbdDayBonusMap.get("user_code").toString(), new Integer(i + 1));
					if (userCode.equals(jbdDayBonusMap.get("user_code").toString())) {
						jbdDayBonusMap.put("parentId", new Integer(0));
					} else {
						jbdDayBonusMap.put("parentId", new Integer(rowMap.get(jbdDayBonusMap.get("recommend_no").toString()).toString()));
					}
				}
			}
			
			getUpNet(request, defSysUser.getUserCode(), userCode, wweekInt, "upRecommendList");
			
			request.setAttribute("jbdDayBonusList", jbdDayBonusList);
			
		}
    	return returnView;

    }

    /** 
     * 查登录会员的推荐组织业绩
     * @param request
     * @param loginUserCode
     * @param searchUserCode
     * @param wweekInt
     * @param netTypeList
     */
    private void getUpNet(HttpServletRequest request,String loginUserCode,String searchUserCode,Integer wweekInt,String netTypeList){
    	List list=new ArrayList();
		while(!searchUserCode.equals(loginUserCode)){
			Map curMap=jmiMemberManager.getJbdDayBounsCalcByUserCode(searchUserCode, wweekInt);
			
			if("upLinkList".equals(netTypeList)){
				searchUserCode=curMap.get("link_no").toString();
			}else if("upRecommendList".equals(netTypeList)){
				searchUserCode=curMap.get("recommend_no").toString();
			}
			list.add(curMap);
		}
		Collections.reverse(list);
		request.setAttribute(netTypeList, list);
    }
    
    /**
     * 推荐网络
     * @param netType
     * @param jbdDayBonusList
     * @param dayMap
     * @param maxLevel
     * @param level
     * @param formatedWeek
     */
    private void getBdBonusTree(String netType,final List jbdDayBonusList,final Map dayMap, final int maxLevel, int level,Integer formatedWeek){
		List curList = jmiMemberManager.getChildJbdDayBounsCalcBySql(dayMap.get("user_code").toString(),netType, formatedWeek);
    	if(maxLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}
    	if(curList!=null && level <= maxLevel){
    		for(int i=0;i<curList.size();i++){
    			Map temp = (Map) curList.get(i);
    			temp.put("level", level);
    			jbdDayBonusList.add(temp);
    			getBdBonusTree(netType, jbdDayBonusList, temp, maxLevel, level, formatedWeek);
    		}
    	}
    }
    private Integer getCurBdPeriod(){
    	Integer bdWeek=jbdPeriodManager.getBdPeriodByTimeFormated(new Date());
    	return WeekFormatUtil.getFormatWeek("w",bdWeek);
    	
    }
}
