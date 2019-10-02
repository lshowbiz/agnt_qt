package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JmiLinkRef;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MessageUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;
import com.joymain.ng.webapp.util.SessionLogin;

@Controller
public class JbdOrganiseStatusController {
    private JmiMemberManager jmiMemberManager;
    
    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    @Autowired
    public JbdPeriodManager jbdPeriodManager;
    @Autowired
    private JmiLinkRefManager jmiLinkRefManager;
    @Autowired
    public JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;

    @Autowired
    private JsysUserManager jsysUserManager;
    

    @RequestMapping(value="/JmiMember/api/jbdOrganiseStatus",method = RequestMethod.GET)
    @ResponseBody
    public List getJmiMembersJson(HttpServletRequest request,String userId,String token,String userCode,String wweek){
    	
     	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		
		boolean isUnlimitUser=StringUtil.getCheckIsUnlimitUser(user.getUserCode());

		String code="organiseonoff.mobile.zc";
		if(!isUnlimitUser){
			code="organiseonoff.mobile";
			//userCode = user.getUserCode();
		}
    	JmiMember jmiMember=jmiMemberManager.get(userCode);
		
		if(StringUtil.isEmpty(userCode)){
			return (List)object;
		}else if(null==jmiMember){
			return (List)object;
		}
		
    	List list=new ArrayList();		
    	String layerNum="99999999";
		if(isUnlimitUser){
			request.setAttribute("isUnlimitUser", isUnlimitUser);
		}else{
			layerNum="1";
		}
		String organiseonoffMobile= ConfigUtil.getConfigValue(user.getCompanyCode().toUpperCase(), code);
		if("0".equals(organiseonoffMobile)){
			return list;
		}
		if(!StringUtil.getIsSeachTime(user.getCompanyCode())){
			return list;
		}
    	
		
		
		Integer wweekInt=StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",wweek));
		//获取对应的日结算资料
		Map bdDayBounsCalcMap = jmiMemberManager.getJbdDayBounsCalcByUserCode(userCode, wweekInt);

		list.add(bdDayBounsCalcMap);
		if(!getIsUnderLine(user, jmiMember, layerNum, "link_no")){
			return list;
		}

		if (bdDayBounsCalcMap == null) {
			list.clear();
			return list;
		}
		Map bdSendRecord = getLastBonus(request, userCode);

		list.addAll(jmiMemberManager.getChildJbdDayBounsCalcBySql(userCode,"link_no",wweekInt));
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			
			if(map.get("user_code")!=null && bdSendRecord!=null && bdSendRecord.get("keep_user_code")!=null ){
				if(map.get("user_code").equals(bdSendRecord.get("keep_user_code"))){
					map.put("keep_pv", bdSendRecord.get("keep_pv"));
				}
			}
			
		}
		
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			String  card_type=(String) map.get("card_type");
			if(!StringUtil.isEmpty(card_type)){
				map.put("card_type", ListUtil.getListValue(user.getDefCharacterCoding(), "bd.cardtype", card_type));
			}
			
		}
    	
    	return list;
    }
    
    @RequestMapping(value="/JmiMember/api/jbdOrganiseStatusWeek",method = RequestMethod.GET)
    @ResponseBody
    public List getJbdOrganiseStatusWeek(HttpServletRequest request,String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		String organiseNum="6";
		
		boolean isUnlimitUser=StringUtil.getCheckIsUnlimitUser(user.getUserCode());
		String layerNum="99999999";
		if(isUnlimitUser){
			request.setAttribute("isUnlimitUser", isUnlimitUser);
		}else{
			organiseNum="1";
			layerNum="1";
		}
		
		List latesBdPeriods = this.jbdPeriodManager.getLatestBdPeriodsFweek(DateUtil.convertDateToString(new Date()), StringUtil.formatInt(organiseNum));
		request.setAttribute("latesBdPeriods", latesBdPeriods);
		
    	return latesBdPeriods;
    }
    
    @RequestMapping(value="/jbdOrganiseStatus",method = RequestMethod.GET)
    public String getJmiMembers(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	String returnView="jbdOrganiseStatus";

		String userCode = request.getParameter("userCode");
		String wweek = request.getParameter("wweek");
		
		Integer wweekInt=StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",wweek));
		String organiseNum="6";

		


		List latesBdPeriods = this.jbdPeriodManager.getLatestBdPeriodsFweek(DateUtil.convertDateToString(new Date()), StringUtil.formatInt(organiseNum));
		request.setAttribute("latesBdPeriods", latesBdPeriods);
		if(!StringUtil.getIsSeachTime(defSysUser.getCompanyCode())){
			return returnView;
		}
    	
		if(StringUtil.isEmpty(userCode)){
			return returnView;
		}else if(null==jmiMemberManager.get(userCode)){
			return returnView;
		}
		
		
    	boolean isUnlimitUser=StringUtil.getCheckIsUnlimitUser(defSysUser.getUserCode());
		String layerNum="99999999";
		if(isUnlimitUser){
			request.setAttribute("isUnlimitUser", isUnlimitUser);
		}else{
			userCode = defSysUser.getUserCode();
			organiseNum="1";
			layerNum="1";
		}
		
		
		
    	JmiMember jmiMember=jmiMemberManager.get(userCode);
		
		if(!getIsUnderLine(defSysUser, jmiMember, layerNum, "link_no")){
			return returnView;
		}
		
		
		
		
		
		
		
		if ("bdOrganiseStatusQuery".equals(request.getParameter("strAction"))) {

			
			//JmiMember topMiMember=jmiMemberManager.get(userCode);

			if ("showLastMember".equalsIgnoreCase(request.getParameter("doType"))) {
				//跳转至最后一个会员
				String lastMemberNo = userCode;
				List tmpList= jmiLinkRefManager.getLinkRefbyLinkNo(userCode,"jmiMember.createTime","");
				
				if (tmpList.size() > 0) {
					JmiLinkRef miLinkRefTmp1 = (JmiLinkRef) tmpList.get(0);
					tmpList = jmiLinkRefManager.getLinkRefbyLinkNo(userCode,"jmiMember.createTime","");
					while (tmpList.size() > 0) {
						miLinkRefTmp1 = (JmiLinkRef) tmpList.get(0);
						lastMemberNo = miLinkRefTmp1.getUserCode();
						
			
		    			tmpList = jmiLinkRefManager.getLinkRefbyLinkNo(userCode,"jmiMember.createTime","");
					}
				}
				
				ModelAndView mv = new ModelAndView("redirect:bd_organise_status.html");
				mv.addObject("strAction", "bdOrganiseStatusQuery");
				mv.addObject("firstMemberNo", request.getParameter("firstMemberNo"));
				mv.addObject("memberNo", lastMemberNo);
				mv.addObject("wweek", wweek);
				return returnView;
			}
			//获取对应的日结算资料
			Map bdDayBounsCalcMap = jmiMemberManager.getJbdDayBounsCalcByUserCode(userCode, wweekInt);
	
			if (bdDayBounsCalcMap == null) {
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("opration.notice.js.bdDayBounsCalc.noExists"));
				return returnView;
			}
		

			request.setAttribute("bdDayBounsCalcMap", bdDayBounsCalcMap);

			
			getLastBonus(request, userCode);
			
			
			List childBdDayBonusCalcs = jmiMemberManager.getChildJbdDayBounsCalcBySql(userCode,"link_no",wweekInt);
			request.setAttribute("childBdDayBonusCalcs", childBdDayBonusCalcs);

			//如果当前所显示的最高层会员不是当前登录会员
			String firstMemberNo = request.getParameter("firstMemberNo");
			if (StringUtils.isEmpty(firstMemberNo)) {
				firstMemberNo = userCode;
			}
			JmiMember firstMiMember = jmiMemberManager.get(firstMemberNo);
			if (!firstMemberNo.equals(userCode)) {
				List upMiLinkRefs = new ArrayList();
				JmiMember miMember = jmiMemberManager.get(userCode);
				String upMemberNo = miMember.getUserCode();

				while (!upMemberNo.equals(firstMemberNo)) {

					JmiLinkRef miLinkRef = jmiLinkRefManager.get(upMemberNo);
					upMiLinkRefs.add(miLinkRef);

					upMemberNo = miLinkRef.getLinkJmiMember().getUserCode();
				}
				Collections.reverse(upMiLinkRefs);

				request.setAttribute("upMiLinkRefs", upMiLinkRefs);
			}

			request.setAttribute("firstMiMember", firstMiMember);
		}

		
		
    	return returnView;
    }
    
    private Map getLastBonus(HttpServletRequest request,String userCode){
		//获取最近一次发放奖金的保留业绩
		JbdPeriod lastCacBdPeriod = this.jbdPeriodManager.getLatestSendBonus();
		request.setAttribute("lastCacBdPeriod", lastCacBdPeriod);
		if (lastCacBdPeriod != null) {
			String formatedLastCacWeek = lastCacBdPeriod.getWyear().toString()+ StringUtils.leftPad(lastCacBdPeriod.getWweek().toString(), 2, '0');
			
			Map bdSendRecord = jbdMemberLinkCalcHistManager.getBdSendRecordMap(userCode,formatedLastCacWeek);
			request.setAttribute("bdSendRecord", bdSendRecord);
			return bdSendRecord;
		}
		return null;
    }
    private boolean getIsUnderLine(JsysUser defSysUser,JmiMember jmiMember,String defLayerID,String netType){
    	
    	
    	Integer perLayer=0;
    		perLayer=2;

    	if(jmiMember==null){
    		return false;
    	}else{
    		String topTreeIndex="";
    		String underTreeIndex="";
    		
    		if("link_no".equals(netType)){
    			topTreeIndex=defSysUser.getJmiMember().getJmiLinkRef().getTreeIndex();
        		underTreeIndex=jmiMember.getJmiLinkRef().getTreeIndex();
    		}else if("recommend_no".equals(netType)){
    			topTreeIndex=defSysUser.getJmiMember().getJmiRecommendRef().getTreeIndex();
        		underTreeIndex=jmiMember.getJmiRecommendRef().getTreeIndex();
    		}
    		
    		if(StringUtil.getCheckIsDownline(topTreeIndex,underTreeIndex)){
    			
    			String overTreeIndex=StringUtils.substring(underTreeIndex, topTreeIndex.length());
    			
    			Integer layer=overTreeIndex.length()/perLayer;
    			if(layer>=StringUtil.formatInt(defLayerID)){
    				return false;
    			}else{
    				return true;
    			}
    		}else{
    			return false;
    		}
    	}
    }
   
    
    
    
    
/*    private List setList(String userCode,Integer wweek,Integer limitLevel){
    	
		List list=new ArrayList();
		Stack myStack = new Stack();
		Map mapTemp=null;
		Map topMap=jmiMemberManager.getJbdDayBounsCalcByUserCode(userCode, wweek);
		
		if(topMap!=null){
			list.add(topMap);
			List topList=jmiMemberManager.getChildJbdDayBounsCalcBySql(userCode, "link_no", wweek);
			topMap.put("level", 1);
			String netType="link_no";
			operation(topList,1,myStack,wweek,netType,list);
			while (!myStack.empty()) {
				mapTemp = (Map) myStack.pop();
				
				if( (Integer)mapTemp.get("level")>limitLevel){
					continue;
				}else{
					List curList=jmiMemberManager.getChildJbdDayBounsCalcBySql(mapTemp.get("user_code").toString(),netType, wweek);
					if (!curList.isEmpty()) {
							this.operation(curList,(Integer)mapTemp.get("level"),myStack,wweek,netType,list);
					} 
				}
				
			}
		}
		
	
		myStack.clear();
		return list;
    }
    
    
    
    
	private void operation(List list,int level,Stack myStack,Integer wweek,String netType,List returnList) {
		level++;
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			map.put("level", level);
			String user_code=map.get("user_code").toString();
			List childs=jmiMemberManager.getChildJbdDayBounsCalcBySql(user_code, netType, wweek);
			if(!childs.isEmpty()) {
				myStack.push(map);
			}
			returnList.add(map);
		}
	}*/
}
