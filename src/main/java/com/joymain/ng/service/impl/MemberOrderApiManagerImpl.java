package com.joymain.ng.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.HttpMsg;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.MemberOrder;
import com.joymain.ng.model.MemberOrderList;
import com.joymain.ng.model.OrderProduct;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.MemberOrderApiManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.webapp.util.RequestUtil;

@Service("MemberOrderApiService")
public class MemberOrderApiManagerImpl implements MemberOrderApiManager{

	protected final transient Log log = LogFactory.getLog(MemberOrderApiManagerImpl.class);
	
	private PdSendInfoManager pdSendInfoManager;
	private JpmProductSaleNewManager jpmProductSaleNewManager;
	private JsysUserManager jsysUserManager;
    
    @Autowired
    public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}
    
    @Autowired
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}
    
    @Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}
    
    private void setHttpMsg(String code,String msg, HttpServletResponse response) throws Exception{
    	HttpMsg hm = new HttpMsg();
    	hm.setCode(code);
		hm.setMsg(msg);
		writeGridDataModelJson(response,hm);
    }
	
	public void OrderStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");// 登录用户
//		String token = request.getParameter("token");
		
			
			JsysUser jsysUser = jsysUserManager.get(userId);
//			JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
//			
//			Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List");
//			if(null!=object){
//				//鉴权失败、、直接返回错误信息
//				setHttpMsg("e001","token鉴权失败!",response);
//				return ;
//			}
			
			CommonRecord crm = RequestUtil.toCommonRecord(request);
			//查询条件
	    	String orderNo = request.getParameter("memberOrderNo");//订单号
	    	String orderType = request.getParameter("orderType");//订单类型
	    	String isMobile = request.getParameter("isMobile"); //订单来源
	    	String isRetreatOrder = request.getParameter("isRetreatOrder2"); //是否退单
	    	String isShipments = request.getParameter("isShipping"); //发货状态
	    	String status = request.getParameter("status"); //订单状态
	    	String logType = request.getParameter("logType"); //日期类型
	    	String startLogTime = request.getParameter("startLogTime"); //起始日期
	    	String endLogTime = request.getParameter("endLogTime"); //结束日期

	    	String orderTime = request.getParameter("orderTime"); //历史订单
	    	String isPay = request.getParameter("isPay"); //支付状态
	    	String returnType = request.getParameter("returnType"); //返回统计类型
	    	
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
	    	isPay = StringUtil.dealStr(isPay); 
	    	orderTime = StringUtil.dealStr(orderTime); 
	    	
	    	if(MeteorUtil.isBlank(logType)){
	    		if(!MeteorUtil.isBlank(startLogTime) || !MeteorUtil.isBlank(endLogTime)){
	    			logType = "A";//默认订购日期
	    		}
	    	}
	    	//存放条件
	    	crm.setValue("userCode", userId);
	    	crm.setValue("orderNo", orderNo.trim());
	    	crm.setValue("orderType", orderType.trim());
	    	crm.setValue("isMobile", isMobile.trim());
	    	crm.setValue("isRetreatOrder", isRetreatOrder.trim());
	    	crm.setValue("isShipments", isShipments.trim());
	    	crm.setValue("status", status.trim());
	    	crm.setValue("logType", logType.trim());
	    	crm.setValue("startLogTime", startLogTime.trim());
	    	crm.setValue("endLogTime", endLogTime.trim());
	    	crm.setValue("orderTime", orderTime.trim());
	    	crm.setValue("isPay", isPay.trim());
	    	
	    	OrderProduct op = null;
	        List<OrderProduct> orderLists = null;
	    	
	        if("0".equals(returnType)){
				
		    	List jpoMemberOrderList = pdSendInfoManager.getJpoMemberOrderList(crm);
		    	request.setAttribute("jpoMemberOrderList", jpoMemberOrderList);
		    	
		    	//3.总计数据
		    	Map sumMap = pdSendInfoManager.getSumAmountByCrm(crm);
		        
		        MemberOrderList memberOrderList = new MemberOrderList();
		        MemberOrder memberOrder = null;
		        JpoMemberOrder jmo = null;
		        List<MemberOrder> memberOrderLists = new ArrayList<MemberOrder>();
	        	
	        	
	        	memberOrderList.setAmt(new BigDecimal(String.valueOf(sumMap.get("amount"))));
	 	        memberOrderList.setJjAmt(new BigDecimal(String.valueOf(sumMap.get("jjAmount"))));
	 	        memberOrderList.setDisAmt(new BigDecimal(String.valueOf(sumMap.get("discountAmount"))));
	 	        memberOrderList.setPvAmt(new BigDecimal(String.valueOf(sumMap.get("pvAmt"))));
	 	        if(!MeteorUtil.isBlankByList(jpoMemberOrderList)){
	 	        	for (int i=0;i<jpoMemberOrderList.size();i++){
	 	        		jmo = (JpoMemberOrder)jpoMemberOrderList.get(i);
	 	        		memberOrder = new MemberOrder();
	 	        		memberOrder.setMemberOrderNo(jmo.getMemberOrderNo());
	 	        		memberOrder.setOrderType(jmo.getOrderType());
	 	        		memberOrder.setIsMobile(jmo.getIsMobile());
	 	        		memberOrder.setAmount(jmo.getAmount());
	 	        		memberOrder.setJjAmount(jmo.getJjAmount());
	 	        		memberOrder.setDiscountAmount(jmo.getDiscountAmount());
	 	        		memberOrder.setPvAmount(jmo.getPvAmt());
	 	        		memberOrder.setStatus(jmo.getStatus());
	 	        		memberOrder.setIsShipping(jmo.getIsShipping());
	 	        		memberOrder.setIsRetreatOrder2(jmo.getIsRetreatOrder2());
	 	        		if(null==jmo.getOrderTime()){
	 	        			memberOrder.setOrderTime("");
	 	        		}else{
	 	        			memberOrder.setOrderTime(MeteorUtil.doDateToConvert(jmo.getOrderTime()));
	 	        		}
	 	        		if(null==jmo.getCheckDate()){
	 	        			memberOrder.setCheckDate("");
	 	        		}else{
	 	        			memberOrder.setCheckDate(MeteorUtil.doDateToConvert(jmo.getCheckDate()));
	 	        		}
	 	        		
	 	        		orderLists = new ArrayList<OrderProduct>();
	 	        		if(null!=jmo.getJpoMemberOrderList() && jmo.getJpoMemberOrderList().size()>0){
	 	        			for(JpoMemberOrderList jmodetail : jmo.getJpoMemberOrderList()){
	 	        				op = new OrderProduct();
	 	        				op.setProductNo(jmodetail.getProNo());
	 	        				op.setProductName(jmodetail.getProductName());
	 	        				op.setPrice(jmodetail.getPrice());
	 	        				op.setQty(jmodetail.getQty());
	 	        				orderLists.add(op);
	 	        			}
	 	        			memberOrder.setOrderLists(orderLists);
	 	        		}
	 	        		
	 	        		memberOrderLists.add(memberOrder);
	 	        	}
	 	        	memberOrderList.setJpoMemberorders(memberOrderLists);
	 	        }else{
	 	        	setHttpMsg("e005","没有数据,请检查参数!",response);
					return ;
	 	        }

	 	        writeGridDataModelJson(response,memberOrderList);
	        }else {
	        	//4.商品数据
	        	orderLists = new ArrayList<OrderProduct>();
		        List products = pdSendInfoManager.getStatisticProductSale(crm);
		        Map<String, Object> data = null;
		        if(!MeteorUtil.isBlankByList(products)){
		        	for(int i =0;i<products.size();i++){
		        		data = (Map<String, Object>)products.get(i);
		        		op = new OrderProduct();
		 				op.setProductNo(String.valueOf(data.get("product_no")));
		 				op.setProductName(String.valueOf(data.get("product_name")));
		 				op.setPrice(new BigDecimal(String.valueOf(data.get("price"))));
		 				op.setQty(Integer.valueOf(String.valueOf(data.get("qty"))));
		 				orderLists.add(op);
		        	}
		        	writeGridDataModelJson(response,orderLists);
		        }else{
		        	setHttpMsg("e005","没有数据,请检查参数!",response);
					return ;
		        }
		        
	        }
		
	}
	
	/**
	 * 模块单对象页面输出格式
	 * @param response
	 * @param m 结果集
	 * @throws IOException
	 */
	public void writeGridDataModelJson(HttpServletResponse response,List list) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String writeJson = mapper.writeValueAsString(list);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().print(writeJson);
    }
	
	/**
	 * 模块单对象页面输出格式
	 * @param response
	 * @param m 结果集
	 * @throws IOException
	 */
	public <T> void writeGridDataModelJson(HttpServletResponse response,T t) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String writeJson = mapper.writeValueAsString(t);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(writeJson);
    }
	
}
