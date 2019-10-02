package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.InwIntegrationManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 创新共赢----新需求变更----------2013-11月新需求
 * @author gw 2013-11-18
 *
 */
@Controller
public class InwIntegrationController {
    private InwIntegrationManager inwIntegrationManager;

    @Autowired
    public void setInwIntegrationManager(InwIntegrationManager inwIntegrationManager) {
        this.inwIntegrationManager = inwIntegrationManager;
    }
    /**
     * 创新共赢---服务流程指引
     * @author gw  2013-11-18
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemandService")
    public String getInwDemandService(HttpServletRequest request){
    	String returnView = "inwDemandService";
    	return returnView;
    }
    
    /**
     * 创新共赢---激励政策
     * @author gw  2013-11-18
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemandIncentives")
    public String getInwDemandIncentives(HttpServletRequest request){
    	String returnView = "inwDemandIncentives";
    	return returnView;
    }
    
    /**
     * 创新共赢---联系我们
     * @author gw  2013-11-18
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemandContact")
    public String getInwDemandContact(HttpServletRequest request){
    	String returnView = "inwDemandContact";
    	return returnView;
    }
    
    /**
     * 创新共赢---隐私与知识产权
     * @author gw  2013-11-18
     * @param request
     * @return string
     */
    @RequestMapping("/inwDemandPrivacy")
    public String getInwDemandPrivacy(HttpServletRequest request){
    	String returnView = "inwDemandPrivacy";
    	return returnView;
    }
    
}
