package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpmProductCombinationManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.util.MeteorUtil;

@Controller
@SuppressWarnings({  "rawtypes" })
@RequestMapping("/jpoShoppingCartOrderView/")
public class JpoShoppingCartOrderViewController {
	private final Log log = LogFactory.getLog(JpoMemberOrderViewController.class);

	@Autowired
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	@Autowired
	private JpmProductCombinationManager jpmProductCombinationManager = null;
	@Autowired
	private JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager;
	@Autowired
	private JpoShoppingCartOrderManager jpoShoppingCartOrderManager;
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;

	/*
	 * @Autowired private JalCityManager alCityManager;
	 * 
	 * @Autowired private JalDistrictManager alDistrictManager;
	 * 
	 * @Autowired private JalCompanyManager alCountryManager;
	 */

	@RequestMapping(value = "cartView", method = RequestMethod.GET)
	public String scAll(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String uniNo = request.getParameter("uniNo");
		String teamCode = request.getParameter("teamCode");
		String orderType = request.getParameter("orderType");
		String pttId = request.getParameter("pptId");
		String categoryIds = request.getParameter("categoryIds");

		JpoShoppingCart jpoShoppingCart = new JpoShoppingCart();
		HashMap<String, ArrayList<JpmProductSaleTeamType>> productList = null;
		// 会员首单判断是否购买了事业锦囊
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if ("1".equals(orderType)) {
			List jpoMemberList = jpoMemberOrderDao.getJpoMemberMark(defSysUser.getJmiMember().getPapernumber(), "P08520100101CN0", jpoShoppingCart.getOrderType());
			if (jpoMemberList != null && jpoMemberList.size() > 0)
			// 获取产品
			{
				// 购买过事业锦囊,展示辅销品跟事业锦囊
				request.setAttribute("showProduct", true);
				productList = jpoShoppingCartOrderListManager.getProductByOrderType(orderType, loginSysUser, categoryIds, "1");
			} else {
				// 没有购买事业锦囊,不展示事业锦囊跟辅销品
				request.setAttribute("showProduct", false);
				productList = jpoShoppingCartOrderListManager.getProductByOrderType(orderType, loginSysUser, categoryIds, "0");
			}
		} else {
			productList = jpoShoppingCartOrderListManager.getProductByOrderType(orderType, loginSysUser, categoryIds, null);
		}

		JpmProductSaleTeamType pstt = null;
		
		if (StringUtils.isNotEmpty(pttId)) {
			pstt = jpmProductSaleNewManager.getJpmProductSaleTeamType(pttId);
			if(StringUtils.isNotEmpty(pstt.getJpmProductSaleNew().getProductNo())){//获取商品的子商品
				List<Map<String, Object>> listMap = jpmProductCombinationManager.getJpmProductList(pstt.getJpmProductSaleNew().getProductNo());//P08110100101CN0
				String subProduct = getFlagProduct(listMap,  jpmProductCombinationManager,"");
				request.setAttribute("subProduct", subProduct);// 商品的子商品 递归获取
			}
		}

		if (StringUtils.isNotEmpty(uniNo) && StringUtils.isNotEmpty(teamCode) && StringUtils.isNotEmpty(orderType)) {
			List<JpmProductSaleTeamType> Rtts = jpmProductSaleNewManager.getRelatedTeamTypeList(uniNo, teamCode, orderType);// 获取相关联的产品
			if ("M".equals(loginSysUser.getUserType())) {
				if(!MeteorUtil.isBlankByList(Rtts) && Rtts.size()>5){
					request.setAttribute("rttLists", Rtts.subList(0, 5));
				}else{
					request.setAttribute("rttLists", Rtts);
				}
				
				request.setAttribute("productList", productList);
				if (pstt != null) {
					request.setAttribute("pstt", pstt);
				}
				// 获取购物车中的数量
				jpoShoppingCart.setCompanyCode(loginSysUser.getCompanyCode());
				jpoShoppingCart.setUserCode(loginSysUser.getUserCode());
				int total = jpoShoppingCartOrderManager.getShoppinigCartSum(jpoShoppingCart);
				request.setAttribute("teamCode", teamCode);// 保存团队顶点类型
				request.setAttribute("orderType", orderType);// 保存订单类型
				request.setAttribute("sysUser", loginSysUser);// 保存登录会员的信息
				request.setAttribute("total", total);// 购物车中的数量
				return "jpoShoppingCartOrderView";
			}
		}
		return null;
	}

	/**
	 * 递归出商品的子商品
	 * @param listMap 查询出来的结果集合
	 * @param ser JpmProductCombinationManager对象
	 * @param suNm 空位符号 
	 * @return
	 */
	static String getFlagProduct(List<Map<String, Object>> listMap, JpmProductCombinationManager ser, String suNm) {
		StringBuffer sb = new StringBuffer();
		for (Map<String, Object> map : listMap) {
			sb.append(suNm);
			if ("1".equals(map.get("FLAG") + "")) {
				if ("&nbsp;&nbsp;&nbsp;&nbsp;".equals(suNm)){
					break; // 什么时候跳出循环
				}
				String _str = getFlagProduct(ser.getJpmProductList(map.get("SUBNO") + ""), ser, suNm+"&nbsp;&nbsp;");
				if(StringUtils.isNotEmpty(_str)){
					sb.append("<font style='font-weight:bold'>"+map.get("NAME")+"</font>").append("(").append(map.get("QTY")).append("<span name='spSubNum' style='color:red'></span>").append(")\r\n</br>");
					sb.append(_str);
				}else{
					sb.append(map.get("NAME")).append("(").append(map.get("QTY")).append("<span name='spSubNum' style='color:red'></span>").append(")\r\n</br>");
				}
			}else{
				sb.append(map.get("NAME")).append("(").append(map.get("QTY")).append("<span name='spSubNum' style='color:red'></span>").append(")\r\n</br>");
			}
		}
		return sb.toString();
	}

}
