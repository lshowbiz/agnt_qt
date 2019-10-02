

package com.joymain.ng.webapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.webapp.util.RequestUtil;

@Controller
public class JpoMemberOrderStatisticController {
	private PdSendInfoManager pdSendInfoManager;
	private JpmProductSaleNewManager jpmProductSaleNewManager;
    
    @Autowired
    public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}
    
    @Autowired
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}



	/**
     * 会员订单统计
     * @param WuCF 2014-04-28
     * @param request
     * @return string
     */
    @RequestMapping(value="/orderStatistic",method=RequestMethod.GET)
    public String getLinkmanController(HttpServletRequest request){
    	String returnView = "jpoMemberOrderStatistic";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		
		try{
			//查询条件
	    	String orderNo = request.getParameter("orderNo");//订单号
	    	String orderType = request.getParameter("orderType");//订单类型
	    	String isMobile = request.getParameter("isMobile"); //订单来源
	    	String isRetreatOrder = request.getParameter("isRetreatOrder"); //是否退单
	    	String isShipments = request.getParameter("isShipments"); //发货状态
	    	String status = request.getParameter("status"); //订单状态
	    	String logType = request.getParameter("logType"); //日期类型
	    	String startLogTime = request.getParameter("startLogTime"); //起始日期
	    	String endLogTime = request.getParameter("endLogTime"); //结束日期
	    	
	    	//处理字符串
	    	orderNo = StringUtil.dealStr(orderNo);  
	    	orderType = StringUtil.dealStr(orderType); 
	    	isMobile = StringUtil.dealStr(isMobile); 
	    	isRetreatOrder = StringUtil.dealStr(isRetreatOrder); 
	    	isShipments = StringUtil.dealStr(isShipments); 
	    	status = StringUtil.dealStr(status); 
	    	logType = StringUtil.dealStr(logType); 
	    	startLogTime = StringUtil.dealStr(startLogTime); 
	    	endLogTime = StringUtil.dealStr(endLogTime); 
	    	
	    	//存放条件
	    	crm.setValue("userCode", userCode);
	    	crm.setValue("orderNo", orderNo.trim());
	    	crm.setValue("orderType", orderType.trim());
	    	crm.setValue("isMobile", isMobile.trim());
	    	crm.setValue("isRetreatOrder", isRetreatOrder.trim());
	    	crm.setValue("isShipments", isShipments.trim());
	    	crm.setValue("status", status.trim());
	    	crm.setValue("logType", logType.trim());
	    	crm.setValue("startLogTime", startLogTime.trim());
	    	crm.setValue("endLogTime", endLogTime.trim());
	    	
	    	
	    	//----------------------Modify By WuCF 添加分页展示功能 
	    	//分页单位：固定写法
	    	Integer pageSize = StringUtil.dealPageSize(request);
	    	
			//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
			GroupPage page = new GroupPage("","orderStatistic?orderNo="+orderNo+"&orderType="+orderType
					+"&isMobile="+isMobile+"&isRetreatOrder="+isRetreatOrder+"&isShipments="+isShipments
					+"&status="+status+"&logType="+logType+"&startLogTime="+startLogTime+"&endLogTime="+endLogTime
					+"&pageSize="+pageSize,pageSize,request);
			request.setAttribute("page", page);
			
			//1.分页数据
	    	List jpoMemberOrderList = pdSendInfoManager.getJpoMemberOrderPage(page,crm);
	    	request.setAttribute("jpoMemberOrderList", jpoMemberOrderList);
	    	
	    	//2.获得商品编码和名称明细
	    	Map<String,String> jpmProductNewMap = jpmProductSaleNewManager.getJpmProductSaleNoAndName("0,1,2");
	    	request.setAttribute("jpmProductNewMap", jpmProductNewMap);
	    	
	    	//3.总计数据
	    	Map sumMap = pdSendInfoManager.getSumAmountByCrm(crm);
	        request.setAttribute("sumMap", sumMap); 

	        //4.商品数据
	        List products = pdSendInfoManager.getStatisticProductSale(crm);
	        request.setAttribute("products", products);
	        
	        //设置参数条件值
	    	request.setAttribute("orderNo", orderNo);
	    	request.setAttribute("orderType", orderType);
	    	request.setAttribute("isMobile", isMobile);
	    	request.setAttribute("isRetreatOrder2", isRetreatOrder);
	    	request.setAttribute("isShipments", isShipments);
	    	request.setAttribute("logType", logType);
	    	request.setAttribute("startLogTime", startLogTime);
	    	request.setAttribute("endLogTime", endLogTime);
		}catch(Exception e){
			e.printStackTrace();
		}
    	return returnView;
    }
}
