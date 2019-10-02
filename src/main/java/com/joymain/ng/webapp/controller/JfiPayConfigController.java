package com.joymain.ng.webapp.controller;

import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpmMemberConfigManager;
import com.joymain.ng.util.bill99.Bill99;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JsysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 酒业配置支付表单
 * @author shiyh
 *
 */
@Controller
@RequestMapping("/payMemberConfig*")
public class JfiPayConfigController{

    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private JpmMemberConfigManager jpmMemberConfigManager = null;

    @Autowired
    public void setJpmMemberConfigManager(
			JpmMemberConfigManager jpmMemberConfigManager) {
		this.jpmMemberConfigManager = jpmMemberConfigManager;
	}

	@Autowired
	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	      
        //当前用户
    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	request.setAttribute("jsysUser", loginSysUser);
        
        //酒业配置单
        String orderId = request.getParameter("orderId");
        JpmMemberConfig jpmMemberConfig = jpmMemberConfigManager.get(new Long(orderId));
        request.setAttribute("jpmMemberConfig", jpmMemberConfig);
    	
    	//会员电子存折余额
    	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(loginSysUser.getUserCode());
    	request.setAttribute("jfiBankbookBalance", jfiBankbookBalance);

        //快钱支付
        Bill99 jfi99bill = new Bill99();
        jfi99bill.setPayerName(loginSysUser.getUserName());
        jfi99bill.setOrderId(orderId);
        
        return new ModelAndView("jfiPayConfig", "jfi99bill", jfi99bill);
    }
}
