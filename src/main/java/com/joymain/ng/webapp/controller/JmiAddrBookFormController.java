package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/jmiAddrBookform*")
public class JmiAddrBookFormController extends BaseFormController {
    private JmiAddrBookManager jmiAddrBookManager = null;

    @Autowired
    public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
        this.jmiAddrBookManager = jmiAddrBookManager;
    }
    @Autowired
    public JalStateProvinceManager jalStateProvinceManager;
    

    public JmiAddrBookFormController() {
        setCancelView("redirect:jmiAddrBooks");
        setSuccessView("redirect:jmiAddrBooks");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiAddrBook showForm(HttpServletRequest request)
    throws Exception {
        String fabId = request.getParameter("fabId");

        if (!StringUtils.isBlank(fabId)) {
            return jmiAddrBookManager.get(new Long(fabId));
        }

        return new JmiAddrBook();
    }

    @ModelAttribute("jmiAddrBook")
    private JmiAddrBook getJmiAddrBook(JmiAddrBook jmiAddrBook,HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
    	request.setAttribute("alStateProvinces", alStateProvinces);

 		return jmiAddrBook;
 		
    }
    

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView onSubmit(JmiAddrBook jmiAddrBook, BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
    	 ModelAndView mav = new ModelAndView();
       if (jmiAddrBookManager.getCheckJmiAddrBook(errors, jmiAddrBook)) {
          return mav;
       }
       
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
