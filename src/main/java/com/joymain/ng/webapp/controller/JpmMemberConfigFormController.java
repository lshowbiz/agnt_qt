package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.service.JpmConfigDetailedManager;
import com.joymain.ng.service.JpmMemberConfigManager;

@Controller
@RequestMapping("/jpmMemberConfigform*")
public class JpmMemberConfigFormController extends BaseFormController {
    private JpmMemberConfigManager jpmMemberConfigManager = null;
    
    @Autowired
    private JpmConfigDetailedManager jpmConfigDetailedManager;
    
    @Autowired
    public void setJpmMemberConfigManager(JpmMemberConfigManager jpmMemberConfigManager) {
        this.jpmMemberConfigManager = jpmMemberConfigManager;
    }

    public JpmMemberConfigFormController() {
        setCancelView("redirect:jpmMemberConfigs");
        setSuccessView("redirect:jpmMemberConfigs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmMemberConfig showForm(HttpServletRequest request)
    throws Exception {
        String configNo = request.getParameter("configNo");

        if (!StringUtils.isBlank(configNo)) {
            return jpmMemberConfigManager.get(new Long(configNo));
        }

        return new JpmMemberConfig();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmMemberConfig jpmMemberConfig, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
       String molId = request.getParameter("molId");
       String productNo = request.getParameter("productNo");
       jpmConfigDetailedManager.addJpmConfigDetailed(request);
       Map<String,String> map = new HashMap<String,String>();
       request.setAttribute("model", new HashMap<String, String>(map));
       return  "redirect:/jpoWineMemberOrders/orderConfigList?molId="+molId+"&productNo="+productNo;
    }
    
    /**
     * 增加商品配置
     * 
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addConfig", method = RequestMethod.POST)
    public String addConfig(Model model, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
//        String molId = request.getParameter("molId");
//        jpmConfigDetailedManager.addJpmConfigDetailed(request);
        return "redirect:jpoWineMemberOrders/orderConfigList?molId=1";
    }
}
