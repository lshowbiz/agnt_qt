package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.Constants;
import com.joymain.ng.service.JmiPrizeTourismManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.model.CheckBoxUtil;
import com.joymain.ng.model.JmiPrizeTourism;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/jmiPrizeTourismEdit*")
public class JmiPrizeTourismFormController extends BaseFormController {
    private JmiPrizeTourismManager jmiPrizeTourismManager = null;

    @Autowired
    public void setJmiPrizeTourismManager(JmiPrizeTourismManager jmiPrizeTourismManager) {
        this.jmiPrizeTourismManager = jmiPrizeTourismManager;
    }

    public JmiPrizeTourismFormController() {
        setCancelView("redirect:jmiPrizeTourisms");
        setSuccessView("redirect:jmiPrizeTourisms");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiPrizeTourism showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jmiPrizeTourismManager.get(new String(userCode));
        }

        return new JmiPrizeTourism();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(JmiPrizeTourism jmiPrizeTourism, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if(null == jmiPrizeTourism.getIspeer()){
        	jmiPrizeTourism.setIspeer("0");
        }
    	//执行保存或者是修改操作
    	jmiPrizeTourismManager.saveJmiPrizeTourism(jmiPrizeTourism);
		request.setAttribute("jmiPrizeTourism", jmiPrizeTourism);
		//表单修改成功后给于友好提示：更新成功或者是修改成功
		this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		
		return new ModelAndView("redirect:prizeTourisms?1=1");
    }
}
