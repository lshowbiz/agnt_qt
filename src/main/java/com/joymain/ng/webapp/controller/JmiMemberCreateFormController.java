package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.JmiLinkRefDao;
import com.joymain.ng.model.JmiLinkRef;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jmiMemberCreateform*")

public class JmiMemberCreateFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    private static final String fileName="/home/jeeu/workspace/logs/jecs/jmiMemberCreateform.txt";
    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
    @Autowired
    public JbdPeriodManager jbdPeriodManager;

    @Autowired
    public JmiLinkRefDao jmiLinkRefDao;
    public JmiMemberCreateFormController() {
        setCancelView("redirect:jmiMembers");
        setSuccessView("redirect:jmiMembers");
    }


	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {  
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
/*    	FileUtil.appendToFile("["+defSysUser.getUserCode()+"][get]", fileName);*/
	}
	
    @ModelAttribute("jmiMember")
    private JmiMember getJmiMember( HttpServletRequest request){

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
		JmiLinkRef miLinkRef = new JmiLinkRef();
    	miLinkRef.setLinkJmiMember(new JmiMember());
/*    	FileUtil.appendToFile("["+defSysUser.getUserCode()+"][ModelAttribute]["+request.getHeader("User-Agent")+"]", fileName);*/
    	
    	JmiRecommendRef miRecommendRef = new JmiRecommendRef();
    	miRecommendRef.setRecommendJmiMember(new JmiMember());
    	JmiMember jmiMember=new JmiMember();
    	jmiMember.setCompanyCode(defSysUser.getCompanyCode());
    	jmiMember.setJmiRecommendRef(miRecommendRef);
    	jmiMember.setJmiLinkRef(miLinkRef);
    	jmiMember.setLinkNum((long)0);
		jmiMember.setPendingLinkNum((long)0);
		jmiMember.setRecommendNum((long)0);
		jmiMember.setPendingRecommendNum((long)0);
        jmiMember.setSysUser(new JsysUser());
        return jmiMember;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	 ModelAndView mav = new ModelAndView();
 		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 	
 		  
 		/*		java.util.Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			FileUtil.appendToFile("["+defSysUser.getUserCode()+"]"+"[post1]["+request.getHeader("User-Agent")+"]"+name+":"+request.getParameter(name), fileName);
		}*/
 		
		
 		jmiMember.getSysUser().setPassword("888888");
 		jmiMember.getSysUser().setPassword2("888888");
       if (jmiMemberManager.getCheckMiMember(jmiMember, errors, request, "1",defSysUser)) {
/*	   		while (names.hasMoreElements()) {
	   			String name = (String) names.nextElement();
				FileUtil.appendToFile("["+defSysUser.getUserCode()+"]"+"[post2]["+request.getHeader("User-Agent")+"]"+name+":"+request.getParameter(name), fileName);
	   		}*/
	   		//FileUtil.appendToFile("["+defSysUser.getUserCode()+"]"+"[post2_1]["+request.getHeader("User-Agent")+"]"+mav, fileName);
          return mav;
       }
       
       
       if("AJ34272972".equals(request.getSession().getAttribute("teamCode"))){

           List miLinkRefs =jmiLinkRefDao.getLinkRefbyLinkNo( jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode(), "userCode", "");

    		if (miLinkRefs.size() >= 2) {
    			StringUtil.getErrorsCode(errors, "miLinkRef.isFull", "jmiLinkRef.linkJmiMember.userCode",defSysUser.getDefCharacterCoding());
    			 return mav;
    		}
       }
       
       try {
/*	   		while (names.hasMoreElements()) {
	   			String name = (String) names.nextElement();
	   			FileUtil.appendToFile("["+defSysUser.getUserCode()+"]"+"[post3]["+request.getHeader("User-Agent")+"]"+name+":"+request.getParameter(name), fileName);
	   		}*/	
    	   	String userCode=jmiMemberManager.saveNewMiMember(jmiMember, defSysUser,null);
    	   	JmiMember miMember=this.jmiMemberManager.get(userCode);
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			request.getSession().setAttribute("mi_reg_success", miMember);
			return new ModelAndView("redirect:jmiMemberCreateform");
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, LocaleUtil.getLocalText(e.getMessage()));
		}
       
       
       
		return new ModelAndView("redirect:jmiMemberCreateform?1=1");
    }
    
 
}
