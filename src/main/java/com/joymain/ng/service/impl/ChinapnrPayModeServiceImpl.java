package com.joymain.ng.service.impl;

import java.beans.IntrospectionException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.bill99.BeanToMapUtil;
import com.joymain.ng.util.chinapnr.Chinapnr;
import com.joymain.ng.util.chinapnr.ChinapnrUtil;
import com.joymain.ng.util.chinapnr.ChinapnrUtilImpl;

public class ChinapnrPayModeServiceImpl implements PayModeService
{
    
    @Override
    public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany)
    {
        Chinapnr chinapnr = new Chinapnr();
        chinapnr.setOrdAmt(String.valueOf(jfiPayOrderCompany.getPayAmount()));
        chinapnr.setOrdId(jfiPayOrderCompany.getOrderNo());
        chinapnr.setMerPriv(jfiPayOrderCompany.getMerPriv());
        chinapnr.setMerId(jfiPayOrderCompany.getPayAccount());
        ChinapnrUtilImpl ChinapnrUtil = new ChinapnrUtilImpl();
        
        chinapnr = ChinapnrUtil.getChinapnr(chinapnr,
                Integer.parseInt(jfiPayOrderCompany.getFlag()));
        
        Map<String, Object> chinapnrMap = new HashMap<String, Object>();
        //            chinapnrMap = BeanToMapUtil.convertBean(chinapnr);
        chinapnrMap.put("Version", chinapnr.getVersion());//.attr("value",valid.version);
        chinapnrMap.put("CmdId", chinapnr.getCmdId());//.attr("value",valid.cmdId);
        chinapnrMap.put("MerId", chinapnr.getMerId());//.attr("value",valid.merId);
        chinapnrMap.put("OrdId", chinapnr.getOrdId());//.attr("value",valid.ordId);
        chinapnrMap.put("OrdAmt", chinapnr.getOrdAmt());//.attr("value",valid.ordAmt);
        chinapnrMap.put("CurCode", chinapnr.getCurCode());//.attr("value",valid.curCode);
        chinapnrMap.put("Pid", chinapnr.getPid());//.attr("value",valid.pid);
        chinapnrMap.put("RetUrl", chinapnr.getRetUrl());//.attr("value",valid.retUrl);
        chinapnrMap.put("BgRetUrl", chinapnr.getBgRetUrl());//.attr("value",valid.bgRetUrl);
        chinapnrMap.put("MerPriv", chinapnr.getMerPriv());//.attr("value",valid.merPriv);
        chinapnrMap.put("GateId", chinapnr.getGateId());//.attr("value",valid.gateId);
        chinapnrMap.put("UsrMp", chinapnr.getUsrMp());//.attr("value",valid.usrMp);
        chinapnrMap.put("DivDetails", chinapnr.getDivDetails());//.attr("value",valid.divDetails);
        chinapnrMap.put("PayUsrId", chinapnr.getPayUsrId());//.attr("value",valid.payUsrId);
        chinapnrMap.put("ChkValue", chinapnr.getChkValue());//.attr("value",valid.chkValue);
        JfiPayRetMsg jfiPayRetMsg = new JfiPayRetMsg();
        jfiPayRetMsg.setUrl(chinapnr.getPostUrl());
        jfiPayRetMsg.setDataMap(chinapnrMap);
        return jfiPayRetMsg;
    }
    
}
