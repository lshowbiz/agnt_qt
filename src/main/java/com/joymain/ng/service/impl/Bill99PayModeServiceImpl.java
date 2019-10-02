package com.joymain.ng.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.mortbay.log.Log;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.bill99.BeanToMapUtil;
import com.joymain.ng.util.bill99.Bill99;
import com.joymain.ng.util.bill99.Bill99UtilImpl;
public class Bill99PayModeServiceImpl implements PayModeService
{
    
    @Override
    public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany)
    {
        Log.info("Bill99PayModeServiceImpl is come in");
        Bill99 bill99 = new Bill99();
        bill99.setBankId("");
        bill99.setPayerName(jfiPayOrderCompany.getUserCode());
        bill99.setOrderAmount(String.valueOf(jfiPayOrderCompany.getOrderAmount()));
        bill99.setPayerContact("");
        bill99.setExt2(jfiPayOrderCompany.getUserCode());
        bill99.setOrderId(jfiPayOrderCompany.getOrderNo());
        bill99.setMerchantAcctId(jfiPayOrderCompany.getPayAccount());
        Bill99UtilImpl bill99Util = new Bill99UtilImpl();
        bill99 = bill99Util.getBill99(bill99,Integer.parseInt(jfiPayOrderCompany.getFlag()));
        Map<String, Object> bill99Map = new HashMap<String,Object>();
        try
        {
            bill99Map = BeanToMapUtil.convertBean(bill99);
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IntrospectionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JfiPayRetMsg jfiPayRetMsg = new JfiPayRetMsg();
        jfiPayRetMsg.setUrl(bill99.getPostUrl());
        jfiPayRetMsg.setDataMap(bill99Map);
        Log.info("return entity is :" + jfiPayRetMsg.toString());
        return jfiPayRetMsg;
    }
    
}
