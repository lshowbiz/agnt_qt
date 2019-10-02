package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.PdSendInfoManager;

/**
 * 手机接口开发综合Controller类
 * 
 * @author WuCF
 * 
 */
@Controller
@RequestMapping("mobileFunction")
@SuppressWarnings({"rawtypes","unused"})
public class MobilePerfectFunctionController extends BaseFormController
{
    
    private Log log = LogFactory.getLog(MobilePerfectFunctionController.class);
    
    @Autowired
    private JsysUserManager jsysUserManager;//会员相关
    
    @Autowired
    private JpoMemberOrderManager jpoMemberOrderManager;//订单相关
    
    @Autowired
    private PdSendInfoManager pdSendInfoManager;//物流相关
    
    
    
    /**
     * 找回密码，发送短信到指定手机号码
     * 
     * @param userCode 用户编号
     * @param cardNumber 身份证号
     * @param mobilePhone 手机号
     * @return 发送短信操作的结果
     */
    @RequestMapping(value = "api/getRetrievePassword")
    @ResponseBody
    public List getRetrievePassword(String userCode, String cardNumber,String mobilePhone, String token)
    {
    	List list = jsysUserManager.getMobileJsysUser(1, 8);
    	System.out.println("list:"+list);
        
        return new ArrayList();
    }
    
}
