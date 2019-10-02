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

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jmiMemberProfileform*")

public class JmiMemberProfileFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;

    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
    
    public JmiMemberProfileFormController() {
        setCancelView("redirect:jmiMembers");
        setSuccessView("redirect:jmiMembers");
    }


	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}
	
    @ModelAttribute("jmiMember")
    private JmiMember getJmiMember(HttpServletRequest request){

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode("CN");
    	request.setAttribute("alStateProvinces", alStateProvinces);
    	
    	
    	if(StringUtil.getCheckIsUnlimitUser(defSysUser.getUserCode())){
			request.setAttribute("bankViewUnLimit", "bankViewUnLimit");
    	}
    
    	 JmiMember jmiMember=null;
    	
 		
 		jmiMember = jmiMemberManager.get(defSysUser.getUserCode());

 		return jmiMember;
 		
    }
    
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	 ModelAndView mav = new ModelAndView();

       
       try {

			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			return new ModelAndView("");
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, LocaleUtil.getLocalText(e.getMessage()));
		}
       
       
       
		 return mav;
    }
    
 
}
