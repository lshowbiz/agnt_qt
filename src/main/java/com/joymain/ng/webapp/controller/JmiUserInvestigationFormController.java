package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.Locale;

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

import com.joymain.ng.model.JmiUserInvestigation;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiUserInvestigationManager;

@Controller
@RequestMapping("/jmiUserInvestigationform*")
public class JmiUserInvestigationFormController extends BaseFormController {
    private JmiUserInvestigationManager jmiUserInvestigationManager = null;

    @Autowired
    public void setJmiUserInvestigationManager(JmiUserInvestigationManager jmiUserInvestigationManager) {
        this.jmiUserInvestigationManager = jmiUserInvestigationManager;
    }

    public JmiUserInvestigationFormController() {
        setCancelView("redirect:jmiUserInvestigationform");
        setSuccessView("redirect:jmiUserInvestigationform");
    }
    
    @RequestMapping(value="/form",method=RequestMethod.GET)
    protected ModelAndView investigationForm(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	//获取当前登录用户的信息
  		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  		String sucNo = "";
  		String savemsg = "";
        if (null!=defSysUser) {
        	JmiUserInvestigation jmiUserInvestigation = jmiUserInvestigationManager.getJmiUserInvestigationByUserCode(defSysUser.getUserCode());
        	if(null!=jmiUserInvestigation){
        		//已经提交过调查文件，不能再次作答
        		sucNo = "sucNo";
        		savemsg = String.valueOf(request.getParameter("savemsg"));
        	}
        }

        ModelAndView mav=new ModelAndView("jmiUserInvestigationform");
	    mav.addObject("sucflag", sucNo);
	    mav.addObject("savemsg", savemsg);
        return mav;
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiUserInvestigation showForm(HttpServletRequest request)
    throws Exception {
    	//获取当前登录用户的信息
  		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (null!=defSysUser) {
        	JmiUserInvestigation jmiUserInvestigation = jmiUserInvestigationManager.getJmiUserInvestigationByUserCode(defSysUser.getUserCode());
        	if(null!=jmiUserInvestigation){
        		//已经提交过调查文件，不能再次作答
        		request.setAttribute("sucflag", "sucNo");
        		request.setAttribute("savemsg", String.valueOf(request.getParameter("savemsg")));
        	}
        }

        return new JmiUserInvestigation();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiUserInvestigation jmiUserInvestigation, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiUserInvestigation, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiUserInvestigationform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiUserInvestigation.getInverid() == null);
        String success = getSuccessView()+"?savemsg=savesuc";
        Locale locale = request.getLocale();
        
        if (request.getParameter("delete") != null) {
            jmiUserInvestigationManager.remove(jmiUserInvestigation.getInverid());
            saveMessage(request, getText("jmiUserInvestigation.deleted", locale));
        } else {
        	//获取当前登录用户的信息
      		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	jmiUserInvestigation.setCrateTime(new Date());
        	jmiUserInvestigation.setUsercode(defSysUser.getUserCode());
            jmiUserInvestigationManager.save(jmiUserInvestigation);
            String key = (isNew) ? "您的宝贵意见已经提交，谢谢参与！" : "您的宝贵意见已经提交，谢谢参与！";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiUserInvestigationform?inverid=" + jmiUserInvestigation.getInverid();
            }
        }

        return success;
    }
}
