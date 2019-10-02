package com.joymain.ng.webapp.controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;

import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.StringUtil;
@Controller
@RequestMapping("/jpoMemberOrderView/")
public class JpoMemberOrderViewController {
    private final Log log = LogFactory.getLog(JpoMemberOrderViewController.class);
    @Autowired
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    @Autowired
    private JpmProductSaleNewManager  jpmProductSaleNewManager=null;

    @RequestMapping(value="orderView",method = RequestMethod.GET)
    public String scAll(Model model,HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	   if (log.isDebugEnabled()) {
               log.debug("entering 'handleRequest' method...");
           }
    	   JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           String moId = request.getParameter("moId");
           String moCode = request.getParameter("moCode");
           
           if(StringUtils.isNotEmpty(moId) || StringUtils.isNotEmpty(moCode)){
    	   JpoMemberOrder jpoMemberOrder = null;
           
           if(StringUtils.isEmpty(moId)){
        	   jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(moCode);
           }else{
        	   jpoMemberOrder = jpoMemberOrderManager.get(new Long(moId));
           }
           Map<String, List<Map<String, Object>>> pdsendMap=null;
           //获取发货单信息
           if(jpoMemberOrder!=null)
           {
        	  pdsendMap = jpmProductSaleNewManager.getPdSendinfoMap(jpoMemberOrder.getMemberOrderNo());
          	  Iterator it=pdsendMap.keySet().iterator();   
          	  String key="";   
              List value=null;   
          	  while(it.hasNext()){   
          	    key=(String)it.next();   
          	    value=pdsendMap.get(key);   
          	       System.out.println(key+":"+value);   
          	   
            	}
          	 String[] keyString=key.split("#");
          	 int count=0;
          	 if(Integer.parseInt(keyString[0])!=-1 && Integer.parseInt(keyString[1])!=-1 && Integer.parseInt(keyString[2])!=-1)
          	 {
          		count=Integer.parseInt(keyString[0])+Integer.parseInt(keyString[1])+Integer.parseInt(keyString[2]);
          	 }
          	 if( Integer.parseInt(keyString[0])==count)
          	 {
          		request.setAttribute("pdsendType", "未发货");
          	 }else if(Integer.parseInt(keyString[2])==count)
          	 {
          		request.setAttribute("pdsendType", "已收货");
          	 }else if(Integer.parseInt(keyString[1])==count)
          	 {
          		request.setAttribute("pdsendType", "已发货");
          	 }else
          	 {
          		request.setAttribute("pdsend", value);
          	 }
           }
           
           //物流跟踪号查询的数据特殊处理   modify gw 2015-08-06----开始
        	 Set jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
        	 if(null!=jpoMemberOrderList){
        		Iterator iterator = jpoMemberOrderList.iterator();
        		while(iterator.hasNext()){
        			JpoMemberOrderList jpoMemberOrderL = (JpoMemberOrderList) iterator.next();
        			String trackingSingle = jpoMemberOrderL.getTrackingSingle();
        			List<String> trackingSingleList = new ArrayList();
        			if(!StringUtil.isEmpty(trackingSingle)){
        				String[] b = trackingSingle.split("</br>");
        		    	List<String> list = new ArrayList<String>();
        		    	for(int i=0;i<b.length;i++){
        		    		trackingSingleList.add(b[i]);
        		    	}
        		    	jpoMemberOrderL.setTrackingSingleList(trackingSingleList);
        			}
        		}
        	 }
        	//物流跟踪号查询的数据特殊处理   modify gw 2015-08-06----结束
           
           
           
             //会员查询发货单信息
   
             List<String[]>  urlList= jpmProductSaleNewManager.getUrlsByOrderno(jpoMemberOrder.getMemberOrderNo());    
       
           	if("M".equals(loginSysUser.getUserType())){
           		if(jpoMemberOrder.getSysUser().getUserCode().equals(loginSysUser.getUserCode())){
           		  //获取物流费信息
                 
           			request.setAttribute("jpoMemberOrder", jpoMemberOrder);
           			request.setAttribute("urlList", urlList);
           	    	return "jpoMemberOrderView";
           		}else{
           			
           			request.setAttribute("jpoMemberOrder", jpoMemberOrder);
           			request.setAttribute("urlList", urlList);
           	    	return "jpoMemberOrderView";
           		}
           	}
           }
           return null;
    }
}
