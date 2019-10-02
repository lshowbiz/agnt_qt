package com.joymain.ng.webapp.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.joymain.ng.model.AmMessage;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JsysListKey;
import com.joymain.ng.model.JsysListValue;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.AmMessageManager;
import com.joymain.ng.service.JsysListKeyManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/amMessageDiscussform*")
public class AmMessageDiscussFormController extends BaseFormController{
	private final Log log = LogFactory.getLog(AmMessageDiscussFormController.class);

    @Autowired
    private AmMessageManager amMessageManager;

    

	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
		
	}


    @ModelAttribute("amMessage")
    private AmMessage getAmMessage( HttpServletRequest request){
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uniNo = request.getParameter("uniNo");
        AmMessage amMessage = null;
        if (!StringUtils.isEmpty(uniNo)) {
            amMessage = amMessageManager.get(new Long(uniNo));
        } else {
            amMessage = new AmMessage();
        }

        
        
        return amMessage;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(AmMessage amMessage, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String strAction = request.getParameter("strAction");
        

    	amMessageManager.save(amMessage);
    	this.saveMessage(request, "评价成功");
    	
        return new ModelAndView("redirect:showMessage");
    }
}
