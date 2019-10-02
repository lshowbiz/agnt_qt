package com.joymain.ng.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.bill99.BeanToMapUtil;
import com.joymain.ng.util.yeepay.YeePay;
import com.joymain.ng.util.yeepay.YeePayUtil;
import com.joymain.ng.util.yeepay.YeePayUtilImpl;

public class YeePayModeServiceImpl implements PayModeService
{
    
    @Override
    public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany)
    {
        YeePay yeePay =  new YeePay();

        yeePay.setP3_Amt(String.valueOf(jfiPayOrderCompany.getPayAmount()));
        yeePay.setP2_Order(jfiPayOrderCompany.getOrderNo());
        yeePay.setPa_MP(jfiPayOrderCompany.getUserCode());
        yeePay.setP1_MerId(jfiPayOrderCompany.getPayAccount());
        yeePay.setPd_FrpId("");
        yeePay.setP9_SAF("0");
        yeePay.setPr_NeedResponse("1");
        YeePayUtilImpl yeePayUtil = new YeePayUtilImpl();
        yeePay = yeePayUtil.getYeePay(yeePay, Integer.parseInt(jfiPayOrderCompany.getFlag()));
        Map<String, Object> yeePayMap = new HashMap<String,Object>();
        try
        {
            yeePayMap = BeanToMapUtil.convertBean(yeePay);
        }
        catch (IllegalAccessException e)
        {
            Log.debug("Bean to Map is error");
        }
        catch (InvocationTargetException e)
        {
            Log.debug("Bean to Map is error");
        }
        catch (IntrospectionException e)
        {
            Log.debug("Bean to Map is error");
        }
        JfiPayRetMsg jfiPayRetMsg = new JfiPayRetMsg();
        jfiPayRetMsg.setUrl(yeePay.getPost_url());
        jfiPayRetMsg.setDataMap(yeePayMap);
        return jfiPayRetMsg;
    }
    
}
