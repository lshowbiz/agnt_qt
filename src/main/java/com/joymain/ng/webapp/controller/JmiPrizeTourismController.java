package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.SearchException;
import com.joymain.ng.model.CheckBoxUtil;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiPrizeTourism;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiPrizeTourismManager;
import com.joymain.ng.util.MeteorUtil;

@Controller
public class JmiPrizeTourismController {
    private JmiPrizeTourismManager jmiPrizeTourismManager;
    @Autowired
	private JmiMemberManager jmiMemberManager;
	 
	@Autowired
	private JalStateProvinceManager jalStateProvinceManager;

    @Autowired
    public void setJmiPrizeTourismManager(JmiPrizeTourismManager jmiPrizeTourismManager) {
        this.jmiPrizeTourismManager = jmiPrizeTourismManager;
    }
    
    @RequestMapping(value="/prizeTourisms",method = RequestMethod.GET)
    protected ModelAndView prizeTourisms(HttpServletRequest request)throws Exception {
		String returnView = "jmiPrizeTourisms";
		JmiPrizeTourism jmiPrizeTourism = null;
		JmiMember jmiMember = null;
		//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String companyCode = defSysUser.getCompanyCode();
		// 读取省份－－－－开始，关于城市和地区的在ｊｓｐ页面做了处理
		List alStateProvinces = jalStateProvinceManager
				.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
		request.setAttribute("alStateProvinces", alStateProvinces);
		String userCode = defSysUser.getUserCode();
		// 从数据库中获取jmiPrizeTourism对象
		jmiPrizeTourism = jmiPrizeTourismManager
				.getJmiPrizeTourismByUsercode(userCode);
		
		String passStar = jmiPrizeTourismManager.getPassStarByUserCode(userCode);
        if(StringUtils.isEmpty(passStar)){
        	passStar = "0";
        }
        
        String ispeer = jmiPrizeTourismManager.geIspeerByUserCode(userCode);
        if(StringUtils.isEmpty(ispeer)){
        	ispeer = "0";
        }

		List avoidcertainfoodList = new ArrayList();
	    List peerAvoidcertainfoodList = new ArrayList();
	    CheckBoxUtil checkBoxUtil = null;
	    CheckBoxUtil peerCheckBoxUtil = null;
	   
	    Map<String, String[]> map = Constants.sysListMap.get("jmiprizetourism.avoidcertainfood");//餐饮忌口  复选框
		if(map==null || map.isEmpty()){
			avoidcertainfoodList = null;
			peerAvoidcertainfoodList = null;
		}else{
			Set codes = map.entrySet();
			if (codes != null) {
				Iterator iter = codes.iterator();
				while (iter.hasNext()) {
					Map.Entry entry=(Map.Entry)iter.next();
					String[] values = (String[])entry.getValue();
					String key = (String)entry.getKey();
					checkBoxUtil  = new CheckBoxUtil();
					checkBoxUtil.setcId(key);
					checkBoxUtil.setcValue(values[0]);
					peerCheckBoxUtil  = new CheckBoxUtil();
					peerCheckBoxUtil.setcId(key);
					peerCheckBoxUtil.setcValue(values[0]);
					
					if(null!=jmiPrizeTourism && null!=jmiPrizeTourism.getAvoidcertainfood()){
						if(MeteorUtil.useList(jmiPrizeTourism.getAvoidcertainfood().split(","), key)){
							checkBoxUtil.setChecked("1");
						}
					}
					if(null!=jmiPrizeTourism && null!=jmiPrizeTourism.getPeeravoidcertainfood()){
						if(MeteorUtil.useList(jmiPrizeTourism.getPeeravoidcertainfood().split(","), key)){
							peerCheckBoxUtil.setChecked("1");
						}
					}
					
					avoidcertainfoodList.add(checkBoxUtil);
					peerAvoidcertainfoodList.add(peerCheckBoxUtil);
				}
			}
		}
	
		request.setAttribute("avoidcertainfoodList", avoidcertainfoodList);
		request.setAttribute("peerAvoidcertainfoodList", peerAvoidcertainfoodList);
		if(null == jmiPrizeTourism){
			returnView = "jmiPrizeTourismform";
			jmiPrizeTourism = new JmiPrizeTourism();
			jmiPrizeTourism.setUserCode(userCode);
			jmiPrizeTourism.setPassStar(Long.valueOf(passStar));
			jmiPrizeTourism.setIspeer(ispeer);
			
			jmiMember = jmiMemberManager.getJmiMemberBankInformation(userCode);
			if(null != jmiMember){
				jmiPrizeTourism.setProvince(jmiMember.getProvince());
				jmiPrizeTourism.setCity(jmiMember.getCity());
				jmiPrizeTourism.setArea(jmiMember.getDistrict());
				jmiPrizeTourism.setAddress(jmiMember.getAddress());
				jmiPrizeTourism.setPhone(jmiMember.getMobiletele());
				jmiPrizeTourism.setCardname(jmiMember.getMiUserName());
				jmiPrizeTourism.setCardid(jmiMember.getPapernumber());
			}
			
			return new ModelAndView(returnView).addObject(jmiPrizeTourism);
		}
		jmiPrizeTourism.setPassStar(Long.valueOf(passStar));
	    request.setAttribute("jmiPrizeTourism", jmiPrizeTourism);
	    return new ModelAndView(returnView);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jmiPrizeTourismManager.search(query, JmiPrizeTourism.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jmiPrizeTourismManager.getAll());
        }
        return model;
    }
    
}
