package com.joymain.ng.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.YDOrderDao;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.YDOrder;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/jpoMemberOrders/")
public class JpoMemberOrderController {
	
	private org.apache.commons.logging.Log log = 
			LogFactory.getLog(JpoMemberOrderController.class);
	private JpoMemberOrderManager jpoMemberOrderManager;
	private JsysUserManager jsysUserManager;
	private JsysUserRoleManager jsysUserRoleManager;
	private PdSendInfoManager pdSendInfoManager;//Add By WuCF 20141219 发货单获得跟踪单号 
	@Autowired
	private YDOrderDao yDOrderDao;
	
	@Autowired
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	@Autowired
	private JalStateProvinceManager jalStateProvinceManager;
	@Autowired
	private JalCityManager jalCityManager;
	@Autowired
	private JalDistrictManager jalDistrictManager;

	@Autowired
	private JpmProductSaleNewManager jpmProductSaleNewManager;
	
	

	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}

	@Autowired
	public void setJpoMemberOrderManager(
			JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	@Autowired
	public void setJsysUserRoleManager(JsysUserRoleManager jsysUserRoleManager) {
		this.jsysUserRoleManager = jsysUserRoleManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Model handleRequest(
			@RequestParam(required = false, value = "q") String query)
			throws Exception {
		Model model = new ExtendedModelMap();
		return model;
	}

	@RequestMapping(value = "orderAll", method = RequestMethod.GET)
	public String scAll(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String memberOrderNo = request.getParameter("memberOrderNo");// 订单编号
		String orderTime = request.getParameter("orderTime");// 历史订单
		String status = request.getParameter("status");// 订单状态
		String orderType = request.getParameter("orderType");// 订单类型
		String isShipments = request.getParameter("isShipments");// 订单发货状态	
		String productNo = request.getParameter("productNo");// 商品编号------------暂时注释
		String productName = request.getParameter("productName");// 商品名称
		
		//Modify By WuCF 新增查询条件   
		String logType = request.getParameter("logType");//日期类型
		String startLogTime = request.getParameter("startLogTime");//开始时间
		String endLogTime = request.getParameter("endLogTime");//截止时间
		String isRetreatOrder = request.getParameter("isRetreatOrder");//退单状态
		String payByCoin = request.getParameter("payByCoin");//使用积分支付
		String payByJJ = request.getParameter("payByJJ");//基金支付
		
		//日期格式判断
		Date startLogTim = null;
		Date endLogTim = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
		try{
			startLogTim = sf.parse(startLogTime);
			endLogTim = sf.parse(endLogTime);
		}catch (Exception e) {
//			e.printStackTrace();
		}
		
		if(startLogTim != null ){
			startLogTime = sf.format(startLogTim);
		}else{
			startLogTime = "";
		}
		if(endLogTim != null){
			endLogTime = sf.format(endLogTim);
		}else{
			endLogTime = "";
		}
		
		if(StringUtils.isEmpty(productNo) || "null".equals(productNo)){
			productNo = "";
		}else{
			productNo = productNo.trim();
		}
		
		if(StringUtils.isEmpty(productName) || "null".equals(productName)){
			productName = "";
		}else{
			productName = productName.trim();
		}
		
		//Modify By WuCF 20140214冻结会员的取法修改，通过实时查询会员的角色判断 
		/*if(jsysUser.getJmiMember().getFreezeStatus()==1)
		{
			orderType="3";
		}*/
		Long userRoleId = jsysUserRoleManager.getSysUserRoleByUserCode(jsysUser.getUserCode()).getRoleId();
		request.setAttribute("userRoleId", userRoleId);
		if(userRoleId==878014)
		{
			orderType="3";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberOrderNo", memberOrderNo);
		map.put("orderTime", orderTime);
		map.put("status", status);
		map.put("orderType", orderType);
		map.put("isShipments", isShipments);
		map.put("userCode", jsysUser.getUserCode());
		map.put("companyCode", jsysUser.getCompanyCode());
		map.put("productNo", productNo);
		map.put("productName", productName);
		
		//Modify By WuCF 20140120 新增查询条件
		map.put("logType", logType);
		map.put("startLogTime", startLogTime);
		map.put("endLogTime", endLogTime);
		map.put("isRetreatOrder", isRetreatOrder);
		map.put("payByCoin", payByCoin);
		map.put("payByJJ", payByJJ);
		
		//modify by fu 20160418 自助制换货单-------------begin
		String strAction =  request.getParameter("strAction");
		String orderselfHelpExchange =  request.getParameter("orderselfHelpExchange");
		if(((!StringUtil.isEmpty(strAction))&&("orderselfHelpExchange".equals(strAction)))||((!StringUtil.isEmpty(orderselfHelpExchange))&&("orderselfHelpExchange".equals(orderselfHelpExchange)))){
			map.put("selfHelpExchange", "Y");
			request.setAttribute("orderselfHelpExchange","orderselfHelpExchange");
		}
		//modify by fu 20160418  自助制换货单-------------begin

		request.setAttribute("model", new HashMap<String, String>(map));
		
		List<JpoMemberOrder> orderList = jpoMemberOrderManager.getOrderByParam(map);
		/*Set<JpoMemberOrder> tempOrderList = new HashSet<JpoMemberOrder>();
		// 
		//过滤商品编码和商品名称 
		String productNoStr = "";
		String productNameStr = "";
		//商品编码和商品名称只能用一个查询
		if(orderList!=null){
			for(JpoMemberOrder jmo : orderList){
				Set<JpoMemberOrderList> set = jmo.getJpoMemberOrderList();
				for(JpoMemberOrderList jmol : set){
					productNoStr = jmol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
					productNameStr = jmol.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductName();
					if("".equals(productNo)){
						if("".equals(productName)){
							tempOrderList.add(jmo);
							continue;
						}else{
							if(productNameStr.contains(productName)){
								tempOrderList.add(jmo);
								continue;
							}
						}
					}else{
						if(productNoStr.contains(productNo)){
							tempOrderList.add(jmo);
							continue;
						}
					}
				}
			}
		}*/
//		System.out.println("======"+orderList.size()+"====="+tempOrderList.size());
		request.setAttribute("orderList", orderList);//
		
		if((!StringUtil.isEmpty(strAction))&&("orderselfHelpExchange".equals(strAction))){
		    return "jpoMemberOrderSelfHelpExchange";
		}else{
		    return "jpoMemberOrders";
		}
	}

	@RequestMapping(value = "api/orderAll")
	@ResponseBody
	public List scAllForM(Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		
		JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
		
		Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List");
		if(null!=object){
			return (List)object;
		}
		String pageNumStr = request.getParameter("pageNum");
		String pageSizeStr = request.getParameter("pageSize");
		int pageNum=1;
		int pageSize=1;
		try{
			if(!StringUtil.isEmpty(pageNumStr)){
				pageNum=Integer.parseInt(pageNumStr);
			}
			if(!StringUtil.isEmpty(pageSizeStr)){
				pageSize=Integer.parseInt(pageSizeStr);
			}
		}catch(Exception e){
			pageNum=1;
			pageSize=1;
		}
		String memberOrderNo = request.getParameter("memberOrderNo");// 订单编号
		String orderTime = request.getParameter("orderTime");// 历史订单
		String status = request.getParameter("status");// 订单状态
		String orderType = request.getParameter("orderType");// 订单类型
		String isShipments = request.getParameter("isShipments");// 订单发货状态
		String productNo = request.getParameter("productNo");// 商品编号
		String productName = request.getParameter("productName");// 商品名称
		if(jsysUser.getJmiMember().getFreezeStatus()==1)
		{
			orderType="3";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberOrderNo", memberOrderNo);
		map.put("orderTime", orderTime);
		map.put("status", status);
		map.put("orderType", orderType);
		map.put("isShipments", isShipments);
		map.put("userCode", jsysUser.getUserCode());
		map.put("companyCode", jsysUser.getCompanyCode());
		map.put("productNo", productNo);
		map.put("productName", productName);
		
		if(StringUtils.isEmpty(productName) || "null".equals(productName)){
			productNo = "";
		}else{
			productName = productName.trim();
		}
		List<JpoMemberOrder> orderList = jpoMemberOrderManager
				.getOrderByParamPage(map,pageNum,pageSize);
		
		if (null != orderList) {
			List<JpoMemberOrder> orderList2 = new ArrayList<JpoMemberOrder>();
			for (int i = 0; i < orderList.size(); i++) {
				JpoMemberOrder jpoMemberOrder = orderList.get(i);
				jpoMemberOrder.setBdPeriod(getPeriod(jpoMemberOrder
						.getCheckTime()));
				jpoMemberOrder.setAddress(getRegion(jpoMemberOrder
						.getProvince(), "P")
						+ getRegion(jpoMemberOrder.getCity(), "C")
						+ getRegion(jpoMemberOrder.getDistrict(), "D")
						+ jpoMemberOrder.getAddress());
				orderList2.add(jpoMemberOrder);
			}
			return orderList2;
		}
		return orderList;
	}

	public String getPeriod(Date date) throws Exception {
		String strPeriod = "";
		if (null == date) {
			return strPeriod;
		}
		try {
			long dateLong = date.getTime();
			for (int i = 0; i < Constants.periodList.size(); i++) {
				JbdPeriod bdPeriod = (JbdPeriod) Constants.periodList.get(i);
				long startTime = bdPeriod.getStartTime().getTime();
				long endTime = bdPeriod.getEndTime().getTime();
				if (dateLong >= startTime && dateLong < endTime) {
					if (bdPeriod.getFweek().toString().length() == 2) {
						strPeriod = bdPeriod.getFyear().toString()
								+ bdPeriod.getFweek().toString();
					} else {
						strPeriod = bdPeriod.getFyear().toString() + "0"
								+ bdPeriod.getFweek().toString();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strPeriod;
	}

	public String getRegion(String regionId, String regionType)
			throws Exception {
		if (StringUtil.isEmpty(regionId)) {
			return "";
		}
		try {
			if ("P".equalsIgnoreCase(regionType)) {
				JalStateProvince alStateProvince = jalStateProvinceManager
						.get(regionId);
				if (alStateProvince != null) {
					return alStateProvince.getStateProvinceName();
				}
			} else if ("C".equalsIgnoreCase(regionType)) {
				JalCity alCity = jalCityManager.get(regionId);
				if (alCity != null) {
					return alCity.getCityName();
				}
			} else if ("D".equalsIgnoreCase(regionType)) {
				JalDistrict alDistrict = jalDistrictManager.get(regionId);
				if (alDistrict != null) {
					return alDistrict.getDistrictName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
     * 查询个人日程集合数据-----手机端接口
     * @userId：会员编码
     * @token：手机端验证
     * @param userCode：会员编码
     * @param startDate：起始日期
     * @param endDate：截止日期
     * @return 个人日程数据集合
     * Add By WuCF 20150805 
     */
    @RequestMapping(value = "api/getTrackingNoInfo")
	@ResponseBody
    public Map<String,Object> getTrackingNoInfo(String orderNo, String productNo){
    	Log.info("在类JpoMemberOrderController的方法中getTrackingNoInfo运行:手机接口根据订单号和商品编号获取物流单号");
    	Log.info("在类JpoMemberOrderController的方法中getTrackingNoInfo运行:手机接口根据订单号和商品编号获取物流单号.订单号:"+orderNo+"商品编号:"+productNo);

    	List<String> list = new ArrayList<String>();
    	Map<String,Object> map = new HashMap<String,Object>();
    	try{
    		list = pdSendInfoManager.getTrackingNoInfo(orderNo, productNo);
    	}catch(Exception e){
    		Log.info("在类JpoMemberOrderController的方法中getTrackingNoInfo运行异常"+e);
    		e.printStackTrace();
    	}
    	map.put("code",0);//键
    	map.put("result",list);//值
    	Log.info("在类JpoMemberOrderController的方法中getTrackingNoInfo运行:手机接口返回值"+map.toString());
    	return map;    	
    }
    
    @RequestMapping(value = "api/ydorder")
	@ResponseBody
	public List<YDOrder> queryYDOrder(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		
		JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
		
		Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List");
		if(null!=object){
			return (List)object;
		}
		
		String pageNumStr = request.getParameter("pageNum");
		String pageSizeStr = request.getParameter("pageSize");
		int pageNum=1;
		int pageSize=1;
		try{
			if(!StringUtil.isEmpty(pageNumStr)){
				pageNum=Integer.parseInt(pageNumStr);
			}
			if(!StringUtil.isEmpty(pageSizeStr)){
				pageSize=Integer.parseInt(pageSizeStr);
			}
			
			String userNo = request.getParameter("userNo");
			List<YDOrder> ydOrderList = yDOrderDao.getYDOrder(userNo,pageNum,pageSize);
			
			return ydOrderList;
		}catch(Exception e){
			e.printStackTrace();
			pageNum=1;
			pageSize=1; 
		}
		
		return null;
		
	}
    
    @RequestMapping(value="/getYDorder",method=RequestMethod.GET)
    public String getYDorder(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
    	try{
    		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext()
    				.getAuthentication().getPrincipal();
	    	String userNo = jsysUser.getUserCode();
	    	String orderNo = request.getParameter("orderNo");
	    	
	    	Integer pageSize = StringUtil.dealPageSize(request);
	    	
	    	GroupPage page = new GroupPage("","getYDorder?pageSize="+pageSize,pageSize,request);
	    	model.addAttribute("page",page);
    	
	    	List<YDOrder> yd_orderList = yDOrderDao.getYDOrder(userNo,orderNo,page);
			model.addAttribute("yd_orderList", yd_orderList);
		}catch(Exception e){
			log.error("exception",e);
		}
    	
    	return "ydorderinfo";
    }
    
    /**
	 * 微商城手机端接口-根据订单号获取物流公司、物流跟踪号
	 * @author fu 2016-05-17 
	 * @param memberOrderNo 订单编号
	 * @return map
	 */
	@RequestMapping(value="api/getLogisticsByMobile")
	@ResponseBody
	public List<Map<String,String>> getLogisticsByMobile(String memberOrderNo){
    	Log.info("在类JpoMemberOrderController的方法中getLogisticsByMobile运行:手机接口根据订单号获取物流公司、物流跟踪号");
        List<Map<String,String>> listResult = new ArrayList<Map<String,String>>();
	    if(!StringUtil.isEmpty(memberOrderNo)){
	    	try{
	    		listResult = pdSendInfoManager.getLogisticsByMobile(memberOrderNo);
		    	Log.info("在类JpoMemberOrderController的方法中getLogisticsByMobile运行,物流信息返回为："+listResult.toString());
		        return listResult;
		    }catch(Exception e){
		    	Log.info("在类JpoMemberOrderController的方法中getLogisticsByMobile运行异常:"+e.toString());
		    	e.printStackTrace();
	            return listResult;
		    }
	    }else{
	    	Log.info("在类JpoMemberOrderController的方法中getLogisticsByMobile运行,订单号为空");
	    	return listResult;
	    }
	}
}
