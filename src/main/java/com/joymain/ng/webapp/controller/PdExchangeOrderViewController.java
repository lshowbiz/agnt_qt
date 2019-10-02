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

import com.google.common.base.Strings;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.PdExchangeOrderManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
@Controller
@RequestMapping("/pdExchangeOrderView/")
public class PdExchangeOrderViewController {
    private final Log log = LogFactory.getLog(PdExchangeOrderViewController.class);
    @Autowired
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    @Autowired
    private JpmProductSaleNewManager  jpmProductSaleNewManager=null;
    @Autowired
    private PdExchangeOrderManager pdExchangeOrderManager = null;
    @Autowired
    private JmiTeamManager jmiTeamManager;

    @RequestMapping(value="orderView",method = RequestMethod.GET)
    public String scAll(Model model,HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	   if (log.isDebugEnabled()) {
               log.debug("entering 'handleRequest' method...");
           }
    	   
    	   String returnView = "pdExchangeOrderView";
    	   JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           String eoNo = request.getParameter("eoNo");
           PdExchangeOrder pdExchangeOrder = new PdExchangeOrder();
           if(!Strings.isNullOrEmpty(eoNo)){
        	   pdExchangeOrder = pdExchangeOrderManager.getPdExchangeOrder(eoNo);
           }
           
           request.setAttribute("pdExchangeOrder", pdExchangeOrder);
           
           //2.获得商品编码和名称明细
	    	Map<String,String> jpmProductNewMap = jpmProductSaleNewManager.getJpmProductSaleNoAndName("0,1,2");
	    	request.setAttribute("jpmProductNewMap", jpmProductNewMap);
           
           return returnView;
           
    } 
    
    
    @RequestMapping(value="queryNewProduct",method=RequestMethod.GET)
    public String queryNewProduct(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String returnView = "pdProductSelect";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		
		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		//原订单编号
		String orderNo = request.getParameter("orderNo");
		String orderType = "";
		String teamCode = "";
		if(!Strings.isNullOrEmpty(orderNo)){
			//从原订单中获取的team_code和order_type
			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderNo);
			if(jpoMemberOrder != null){
				//以前的团队编号从原订单里面获取
				//teamCode = jpoMemberOrder.getTeamCode();
				//需求变更：团队产品换货设置以会员所属最新团队的规则为准
				teamCode = jmiTeamManager.teamStr(defSysUser);
				orderType = jpoMemberOrder.getOrderType();
			}
		}
		
		//换货单号
    	String eoNo = request.getParameter("eoNo");
    	//商品编号和名称
    	String productNo = request.getParameter("productNo");
    	String productName = request.getParameter("productName");
    	//处理字符串
    	orderNo = StringUtil.dealStr(orderNo);
    	productNo = StringUtil.dealStr(productNo);
    	productName = StringUtil.dealStr(productName);
    	if(!Strings.isNullOrEmpty(productNo)){
    		productNo = productNo.trim();
    	}
    	if(!Strings.isNullOrEmpty(productName)){
    		//解决查询条件productName中文乱码问题
    		productName = productName.trim();
    	}
    	
    	String parama = "&productNo=" + productNo + "&productName=" + productName;
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("pdExchangeOrderView","queryNewProduct?orderNo="+orderNo+parama+"&pageSize="+pageSize,pageSize,request);
		request.setAttribute("page", page);
		
		CommonRecord crm = new CommonRecord();
		crm.addField("orderNo", orderNo);
		crm.addField("productNo",productNo);
		crm.addField("productName", productName);
		crm.addField("teamCode",teamCode);
		crm.addField("orderType", orderType);
		
    	//1.分页数据
		//查询该订单下可以换货的商品信息
	    List<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListByCrm(page,crm);
	    request.setAttribute("jpmProductSaleTeamTypeList", jpmProductSaleTeamTypeList);
    	
	  //像页面传递值－这里应该是能分页的集合,因为客户人数不是很多，暂时不给分页
    	request.setAttribute("orderNo", orderNo);
    	request.setAttribute("productNo", productNo);
    	request.setAttribute("productName", productName);
	    return returnView;
    }
           
}
