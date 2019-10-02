package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

@Controller
@RequestMapping("/fiCommonAddrAddform*")
public class FiCommonAddrAddFormController extends BaseFormController {
    private FiCommonAddrManager fiCommonAddrManager = null;

    @Autowired
    public void setFiCommonAddrManager(FiCommonAddrManager fiCommonAddrManager) {
        this.fiCommonAddrManager = fiCommonAddrManager;
    }

    public FiCommonAddrAddFormController() {
        setCancelView("redirect:fiCommonAddrs");
        setSuccessView("redirect:fiCommonAddrs");
    }

    @RequestMapping(method = RequestMethod.GET)
    private void showForm(HttpServletRequest request){
    
    }
    

    @ModelAttribute("fiCommonAddr")
    private FiCommonAddr getFiCommonAddr( HttpServletRequest request){

    	return new FiCommonAddr();
    }
    
    
    
    

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(FiCommonAddr fiCommonAddr, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
   	 	ModelAndView mav = new ModelAndView();
    	
    	
    	
        return mav;
    }
}
