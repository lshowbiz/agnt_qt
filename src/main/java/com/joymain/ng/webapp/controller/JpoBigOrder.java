package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ListUtil;

@Controller
@SuppressWarnings({"unused","unchecked"})
public class JpoBigOrder extends BaseFormController {

	Logger log = LoggerFactory.getLogger(JpoBigOrder.class);
	@Autowired
	private JpmProductSaleNewManager jpmProductSaleNewManager;
	@Autowired
	private JmiTeamManager jmiTeamManager;
	@Autowired
	private JmiMemberManager jmiMemberManager;
	@Autowired
	private JsysIdManager sysIdManager;
	@Autowired
	private JsysUserManager jsysUserManager;
	@Autowired
	private JalStateProvinceManager jalStateProvinceManager;
	@Autowired
	private JalCityManager jalCityManager;
	@Autowired
	private JalDistrictManager jalDistrictManager;
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
	@Autowired
	private com.joymain.ng.service.JpoMemberOrderListManager JpoMemberOrderListManager;
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;

	/**
	 * 套餐选择页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showBigPage")
	protected String showForm(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderType", "1");
		map.put("userCode", jsysUser.getUserCode());
		map.put("companyCode", "CN");

		List<JpoMemberOrder> orderList = jpoMemberOrderManager.getOrderByParamStj(map);
		request.setAttribute("orderList", orderList);
		BigDecimal sum = new BigDecimal(0);
		for(JpoMemberOrder order:orderList){
			sum = sum.add(order.getAmount());
		}
		if(sum.compareTo(new BigDecimal(450000))>=0 
				&& sum.compareTo(new BigDecimal(650000))<0){
			request.setAttribute("flag", 45);
		}else {
			request.setAttribute("flag", 65);
		}
		
		String orderIds = "";
		for (int i = 0; i < orderList.size(); i++) {
			if (i == 0)
				orderIds += orderList.get(i).getMoId();
			else
				orderIds += "," + orderList.get(i).getMoId();
		}

		request.setAttribute("orderIds", orderIds);
		request.setAttribute("notFirst", jmiMemberManager.getIsNoFirst(jsysUser.getJmiMember()));
		return "jpoBigPages";
	}

	/**
	 * 45W或65W商品组合选择页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bigOrder")
	protected String forwardBigForm(HttpServletRequest request) throws Exception {

		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String teamCode = jmiTeamManager.teamStr(jsysUser);
		String flag = request.getParameter("flag");
		if (flag != null && !"".equals(flag)) {
			request.setAttribute("flag", flag);
		}

		JpoMemberOrder order = jpoMemberOrderManager.getOrderByType("1", jsysUser.getUserCode());

		log.info("order===" + order);
		if (order != null) {
			saveMessage(request, "已存在首购单！");
			return "redirect:/showBigPage";
		}

		try {
			// 5W套餐产品编码
			LinkedHashMap<String, String> map_5 = ListUtil.getListOptions("CN", "5w_product");
			Set<String> p5_set = map_5.keySet();
			String p5_str = "";
			for (String str : p5_set) {
				p5_str += str + ",";
			}
			log.info("p5str is:[" + p5_str + "]");

			List<JpmProductSaleTeamType> productList_5 = jpmProductSaleNewManager.getJpmProductSaleTeamTypeJtc(teamCode, "1", p5_str, "CN");

			if (productList_5 != null && productList_5.size() > 0) {
				request.setAttribute("productList_5", productList_5);
			} else {
				saveMessage(request, "商品信息未设置！");
				return "redirect:/showBigPage";
			}

			// 20W 套餐产品编码
			LinkedHashMap<String, String> map_20 = ListUtil.getListOptions("CN", "20w_product");
			Set<String> p20_set = map_20.keySet();
			String p20_str = "";
			for (String str : p20_set) {
				p20_str += str + ",";
			}
			log.info("p20str is:[" + p20_str + "]");

			List<JpmProductSaleTeamType> productList_20 = jpmProductSaleNewManager.getJpmProductSaleTeamTypeJtc(teamCode, "1", p20_str, "CN");
			if (productList_20 != null && productList_20.size() > 0) {
				request.setAttribute("productList_20", productList_20);
			} else {
				saveMessage(request, "商品信息未设置！");
				return "redirect:/showBigPage";
			}

		} catch (Exception e) {
			log.error("", e);
			saveMessage(request, "商品未配置！");
			return "redirect:/showBigPage";
		}
		return "jpoBigBuy";
	}

	/**
	 * 确认选择商品组合，根据商品及数量拆单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/confirmorder")
	protected String confirmOrder(HttpServletRequest request) {
		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Map<String, String> map = new HashMap<String, String>();
		map.put("orderType", "1");
		map.put("userCode", jsysUser.getUserCode());
		map.put("companyCode", "CN");

		List<JpoMemberOrder> temp_orderList = jpoMemberOrderManager.getOrderByParamStj(map);
		if (temp_orderList.size() > 0) {
			saveError(request, "已存在生态家套餐!");
			return "redirect:/showBigPage";
		}

		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);
		// 5万商品编号
		String radioValue = request.getParameter("pro_5");
		// 20W 商品编号
		String[] pttids = request.getParameterValues("pttid");
		String[] qtyArr = request.getParameterValues("pro_20");
		BigDecimal flagPirce = new BigDecimal(0);

		log.info("radioValue=" + radioValue + " and pttid" + pttids + " and qty=" + qtyArr);

		/* 20W套餐可以任意选购，且数量可以多个 */
		List<Integer> qtys = new ArrayList<Integer>();
		List<String> pttidList = new ArrayList<String>();
		// 5万商品
		JpmProductSaleTeamType saleTeamType_5 = jpmProductSaleNewManager.getJpmProductSaleTeamType(radioValue);
		flagPirce = saleTeamType_5.getPrice();

		for (int n = 0; n < qtyArr.length; n++) {
			if (qtyArr[n] != null && !"".equals(qtyArr[n])) {
				int qtyInt = Integer.parseInt(qtyArr[n].trim());
				if (qtyInt > 0) {
					pttidList.add(pttids[n]);
					qtys.add(qtyInt);
					JpmProductSaleTeamType st_20 = jpmProductSaleNewManager.getJpmProductSaleTeamType(pttids[n]);
					flagPirce = flagPirce.add(st_20.getPrice().multiply(new BigDecimal(qtyInt)));
				}
			}
		}
		log.info("flagPrice is:" + flagPirce);
		// 金额在45W 到65W之间，算45W套餐
		if (flag.equals("45")) {

			if (flagPirce.compareTo(new BigDecimal(450000)) >= 0 && flagPirce.compareTo(new BigDecimal(650000)) < 0) {

			} else {
				saveMessage(request, "金额错误!");
				return "redirect:/showBigPage";
			}
		} else if (flag.equals("65")) {
			if (flagPirce.compareTo(new BigDecimal(650000)) < 0) {
				saveMessage(request, "金额错误!");
				return "redirect:/showBigPage";
			}
		}

		List<JpoMemberOrder> orderList = new ArrayList<JpoMemberOrder>();

		try {
			/* 5W 套餐订单生成 */

			// teamType5_List.add(saleTeamType_5);
			JpoMemberOrder order_5 = jpoMemberOrderManager.mergeOrder(getOrder(jsysUser, jsysUser, saleTeamType_5, 1, flag));
			order_5.setStj_price(5);
			orderList.add(order_5);

			log.info("orderNo is:" + order_5.getMemberOrderNo());

			int point = 0;
			if (flag.equals("45"))
				point = 2;
			else
				point = 3;

			List<String> codelist = jmiMemberManager.generateMultiMember(jsysUser.getJmiMember(), point, jsysUser);

			for (int i = 0; i < qtys.size(); i++) {

				if (qtys.get(i) == 1) {
					List<String> temList = new ArrayList<String>();
					for (int j = 0; j < codelist.size(); j++) {
						JsysUser user = jsysUserManager.get(codelist.get(j));
						JpoMemberOrder order_20 = new JpoMemberOrder();
						JpmProductSaleTeamType saleTeamType_20 = jpmProductSaleNewManager.getJpmProductSaleTeamType(pttidList.get(i));
						order_20 = jpoMemberOrderManager.mergeOrder(getOrder(user, jsysUser, saleTeamType_20, 1, flag));

						log.info("orderNo=" + order_20.getMemberOrderNo() + " " + "orderNo20 id is:" + order_20.getMoId());

						flagPirce.add(saleTeamType_20.getPrice());

						temList.add(user.getUserCode());
						orderList.add(order_20);
						if (temList.size() == 1)
							break;
					}
					codelist.removeAll(temList);

				} else if (qtys.get(i) == 2) {
					List<String> temList = new ArrayList<String>();
					for (int j = 0; j < codelist.size(); j++) {
						JsysUser user = jsysUserManager.get(codelist.get(j));
						JpoMemberOrder order_20 = new JpoMemberOrder();
						JpmProductSaleTeamType saleTeamType_20 = jpmProductSaleNewManager.getJpmProductSaleTeamType(pttidList.get(i));
						order_20 = jpoMemberOrderManager.mergeOrder(getOrder(user, jsysUser, saleTeamType_20, 1, flag));

						log.info("orderNo=" + order_20.getMemberOrderNo() + " " + "orderNo20 id is:" + order_20.getMoId());

						flagPirce.add(saleTeamType_20.getPrice());

						temList.add(user.getUserCode());
						orderList.add(order_20);
						if (temList.size() == 2)
							break;
					}
					codelist.removeAll(temList);
				} else if (qtys.get(i) == 3) {
					for (int j = 0; j < codelist.size(); j++) {
						JsysUser user = jsysUserManager.get(codelist.get(j));
						JpoMemberOrder order_20 = new JpoMemberOrder();
						JpmProductSaleTeamType saleTeamType_20 = jpmProductSaleNewManager.getJpmProductSaleTeamType(pttidList.get(i));
						order_20 = jpoMemberOrderManager.mergeOrder(getOrder(user, jsysUser, saleTeamType_20, 1, flag));

						log.info("orderNo=" + order_20.getMemberOrderNo() + " " + "orderNo20 id is:" + order_20.getMoId());

						flagPirce.add(saleTeamType_20.getPrice());
						order_20.setStj_price(20);
						orderList.add(order_20);
					}
				}
			}

		} catch (Exception e) {
			log.error("", e);
			saveMessage(request, e.getMessage());
			return "redirect:/showBigPage";
		}
		request.setAttribute("orderList", orderList);

		List<JalStateProvince> provinceList = jalStateProvinceManager.getJalStateProvinceByCountryCode("CN");
		request.setAttribute("alStateProvinces", provinceList);

		String teamChar = jmiTeamManager.teamStr(jsysUser);

		// 团队是否需要绑定事业锦囊
		boolean isBind = jpoMemberOrderManager.isBindProduct(teamChar);
		if (isBind) {

			List jpoMemberList = jpoMemberOrderDao.getJpoMemberMark(jsysUser.getJmiMember().getPapernumber(), "P08520100101CN0", "1");
			if (jpoMemberList != null && jpoMemberList.size() > 0) {
				// 当前用户购买过事业锦囊，需要购买辅销品
				request.setAttribute("userCode_sy", jsysUser.getUserCode());
				if (flag.equals(45)) {
					request.setAttribute("price_fx", 600);
				} else if (flag.equals(65)) {
					request.setAttribute("price_fx", 800);
				}
			} else {
				if (flag.equals(45)) {
					request.setAttribute("price_fx", 400);
				} else if (flag.equals(65)) {
					request.setAttribute("price_fx", 600);
				}
			}

			// 获取辅销品
			HashMap<String, ArrayList<JpmProductSaleTeamType>> productList = jpmProductSaleNewManager.
					getJpmProductSaleTeamTypeMap(null, teamChar, "5", jsysUser.getCompanyCode(), null, null, "5");
			
			ArrayList<JpmProductSaleTeamType> syList = new ArrayList<JpmProductSaleTeamType>();
			
			JpmProductSaleTeamType stt = jpmProductSaleNewManager.getJpmProductSaleTeamType("P08520100101CN0", 
					teamChar, "1", jsysUser.getCompanyCode());
			
			if(stt==null){
				saveMessage(request, "事业锦囊产品信息未设置！");
				return "redirect:/showBigPage";
			}
			syList.add(stt);
			productList.put("7", syList);
			
			request.setAttribute("productList", productList);

		} else {
			log.info("curUser not bind product, temCode is:" + teamChar);
		}
		request.setAttribute("isBind", isBind);

		return "jpoBigPageDetail";
	}

	/**
	 * 保存订单并转向支付页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bigOrderBuy")
	private String bigOrderBuyPage(HttpServletRequest request) {
		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String teamChar = jmiTeamManager.teamStr(jsysUser);

		String flag = request.getParameter("flag");
		String[] orderNos = request.getParameterValues("orderNo");
		String[] provinces = request.getParameterValues("province");
		String[] citys = request.getParameterValues("city");
		String[] districts = request.getParameterValues("district");
		String[] addressVs = request.getParameterValues("addressV");
		String[] firtnames = request.getParameterValues("firtname");
		String[] lastnames = request.getParameterValues("lastname");
		String[] postalcodes = request.getParameterValues("postalcode");
		String[] mobileteles = request.getParameterValues("mobiletele");
		String[] phones = request.getParameterValues("phone");
		String[] moids = request.getParameterValues("moId");
		String isBind = request.getParameter("isBind");
		String userCode_sy = request.getParameter("userCode_sy");
		String orderIds = "";
		BigDecimal temsumPrice = new BigDecimal(0);
		boolean bind = Boolean.parseBoolean(isBind);
		List<JpoMemberOrder> orderArr = new ArrayList<JpoMemberOrder>();
		
		for (int k = 0; k < moids.length; k++) {

			if (k == 0)
				orderIds += moids[k];
			else
				orderIds += "," + moids[k];
		}

		for (int i = 0; i < orderNos.length; i++) {
			JpoMemberOrder memberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderNos[i]);
			memberOrder.setProvince(provinces[i]);
			memberOrder.setCity(citys[i]);
			memberOrder.setDistrict(districts[i]);
			memberOrder.setAddress(addressVs[i]);
			memberOrder.setFirstName(firtnames[i]);
			memberOrder.setLastName(lastnames[i]);
			memberOrder.setPostalcode(postalcodes[i]);
			memberOrder.setMobiletele(mobileteles[i]);
			memberOrder.setPhone(phones[i]);

			BigDecimal sumprice = memberOrder.getAmount();
			BigDecimal sumpv = memberOrder.getPvAmt();

			// 辅销品绑定
			String orderUser = memberOrder.getSysUser().getUserCode();
			String[] pttids = request.getParameterValues("pttid_" + orderUser);
			String[] fxqtys = request.getParameterValues("fxqty_" + orderUser);
			List<Integer> qytList = new ArrayList<Integer>();
			List<String> pttIdList = new ArrayList<String>();

			log.info("isBind :[" + isBind + "] and userCode_sy is:[" + userCode_sy + "]");
			if(bind){
				if (fxqtys == null) {
					saveMessage(request, "请选购辅销品！");
					return "redirect:/eidtbigOrder?orderIds=" + orderIds + "&flag=" + flag + " ";
				}
			}
			// 如果未绑定过事业锦囊，则单独绑定事业锦囊，否则根据用户所选绑定辅销品
			if (memberOrder.getAmount().compareTo(new BigDecimal(60000)) <= 0) {
				if (bind && (userCode_sy != null && !"".equals(userCode_sy))) {
					try{
						for (int n = 0; n < fxqtys.length; n++) {
							if (fxqtys[n].trim() != null && !"".equals(fxqtys[n].trim())) {
								if (Integer.parseInt(fxqtys[n]) > 0) {
									qytList.add(Integer.parseInt(fxqtys[n]));
									pttIdList.add(pttids[n]);
								}
							}
						}
					}catch(NumberFormatException e){
						log.error("",e);
						saveMessage(request, "请输入正确的数字.");
						return "redirect:/eidtbigOrder?orderIds=" + orderIds + "&flag=" + flag + " ";
					}
					
					// TODO 绑定辅销品
					Set<JpoMemberOrderList> itemSet = new HashSet<JpoMemberOrderList>();
					for (int j = 0; j < pttIdList.size(); j++) {
						JpoMemberOrderList itemList = JpoMemberOrderListManager.bingProduct(pttIdList.get(j), qytList.get(j));
						temsumPrice = temsumPrice.add(itemList.getPrice().multiply(new BigDecimal(itemList.getQty())));
						itemList.setJpoMemberOrder(memberOrder);
						itemSet.add(itemList);
					}
					memberOrder.setJpoMemberOrderList(itemSet);
				} else {
					if(bind){
						boolean sybind = true;
						Set<JpoMemberOrderList> orderItem_set = memberOrder.getJpoMemberOrderList();
						for(JpoMemberOrderList orderitem: orderItem_set){
							String proNo = orderitem.getJpmProductSaleTeamType().
									getJpmProductSaleNew().getProductNo();
							if(proNo.equalsIgnoreCase("P08520100101CN0")){ 
								sybind=false;
								break;
							}
						}
						if(sybind){
							Set<JpoMemberOrderList> itemSet = new HashSet<JpoMemberOrderList>();
							// 事业锦囊
							JpmProductSaleTeamType stt = jpmProductSaleNewManager.getJpmProductSaleTeamType("P08520100101CN0", teamChar, "1", jsysUser.getCompanyCode());
							if(stt==null){
								saveMessage(request, "事业锦囊产品信息未设置！");
								return "redirect:/showBigPage";
							}
							JpoMemberOrderList itemList = new JpoMemberOrderList();
							itemList.setPrice(stt.getPrice());
							itemList.setQty(1);
							itemList.setJpmProductSaleTeamType(stt);
							itemList.setJpoMemberOrder(memberOrder);
							itemList.setPv(stt.getPv());
							itemSet.add(itemList);

							temsumPrice = temsumPrice.add(itemList.getPrice().multiply(new BigDecimal(itemList.getQty())));

							memberOrder.setJpoMemberOrderList(itemSet);
						}
						
					}
				}
			} else {
				if(bind){
					try{
						for (int n = 0; n < fxqtys.length; n++) {
							if (fxqtys[n].trim() != null && !"".equals(fxqtys[n])) {
								if (Integer.parseInt(fxqtys[n]) > 0) {
									qytList.add(Integer.parseInt(fxqtys[n]));
									pttIdList.add(pttids[n]);
								}
							}
						}
					}catch(NumberFormatException e){
						log.error("",e);
						saveMessage(request, "请输入正确的数字.");
						return "redirect:/eidtbigOrder?orderIds=" + orderIds + "&flag=" + flag + " ";
					}
					if(qytList.isEmpty()){
						saveMessage(request, "点位("+i+")需要选购辅销品.");
						return "redirect:/eidtbigOrder?orderIds=" + orderIds + "&flag=" + flag + " ";
					}
					// TODO 绑定辅销品
					Set<JpoMemberOrderList> itemSet = new HashSet<JpoMemberOrderList>();
					for (int j = 0; j < pttIdList.size(); j++) {
						JpoMemberOrderList itemList = JpoMemberOrderListManager.bingProduct(pttIdList.get(j), qytList.get(j));
						itemList.setJpoMemberOrder(memberOrder);
						itemSet.add(itemList);

						temsumPrice = temsumPrice.add(itemList.getPrice().multiply(new BigDecimal(itemList.getQty())));
					}
					memberOrder.setJpoMemberOrderList(itemSet);
				}
			}
			if(bind){
				if(temsumPrice.compareTo(new BigDecimal(200)) < 0){
					saveMessage(request, "点位("+i+")辅销品金额必须大于等于200.");
					return "redirect:/eidtbigOrder?orderIds=" + orderIds + "&flag=" + flag + " ";
				}
				// 订单总金额及PV
				Set<JpoMemberOrderList> orderSet = memberOrder.getJpoMemberOrderList();
				for (JpoMemberOrderList orderList : orderSet) {
					if (orderList.getPrice().compareTo(new BigDecimal(0)) > 0) {
						sumprice = sumprice.add(orderList.getPrice().multiply(new BigDecimal(orderList.getQty())));
					}
					if (orderList.getPv().compareTo(new BigDecimal(0)) > 0) {
						sumpv = sumpv.add(orderList.getPv().multiply(new BigDecimal(orderList.getQty())));
					}
				}
				memberOrder.setAmount(sumprice);
				memberOrder.setPvAmt(sumpv);
			}

			
			orderArr.add(memberOrder);
		}
		
		jpoMemberOrderManager.mergeOrder(orderArr);
		return "redirect:/jfiPayEcology?orderIds=" + orderIds;
	}

	public JpoBigOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 实例化订单对象，不包括收货地址
	 * 
	 * @param jsysUser
	 *            订单所属人
	 * @param cureateUser
	 *            创建订单人
	 * @param saleTeamTypeList
	 *            商品明细
	 * @param qty
	 *            商品数量
	 * @return JpoMemberOrder
	 */
	private JpoMemberOrder getOrder(JsysUser curUser, JsysUser createUser, JpmProductSaleTeamType saleTeamType, int qty, String flag)throws Exception {

		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		JpoMemberOrderList jpoMemberOrderList = null;
		Set<JpoMemberOrderList> orderItemSet = new HashSet<JpoMemberOrderList>();
		BigDecimal sumPrice = new BigDecimal(0);
		BigDecimal sumPv = new BigDecimal(0);

		String memberOrderNo = sysIdManager.buildIdStr("pono");// 生成订单编号
		jpoMemberOrder.setMemberOrderNo(memberOrderNo);
		jpoMemberOrder.setSysUser(curUser);
		jpoMemberOrder.setCompanyCode(curUser.getCompanyCode());
		jpoMemberOrder.setUserSpCode(createUser.getUserCode());
		// jpoMemberOrder.setIsShipping("0");

		jpoMemberOrder.setCountryCode(curUser.getCompanyCode());
		jpoMemberOrder.setOrderUserCode(createUser.getUserCode());
		jpoMemberOrder.setLocked("0");
		jpoMemberOrder.setOrderType("1");
		jpoMemberOrder.setPickup("0");// 是否自动提货
		jpoMemberOrder.setStatus("1");
		jpoMemberOrder.setSubmitStatus("1");
		jpoMemberOrder.setIsPay("0");
		jpoMemberOrder.setIsMobile("0");

		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
		jpoMemberOrder.setPayMode("0");// 付款方式
		jpoMemberOrder.setIsSpecial("0");// 是否为特殊订单
		jpoMemberOrder.setOrderTime(new Date());
		String teamCode = jmiTeamManager.teamStr(createUser);
		jpoMemberOrder.setTeamCode(teamCode);
		jpoMemberOrder.setProductFlag(flag);

		jpoMemberOrderList = new JpoMemberOrderList();
		jpoMemberOrderList.setPrice(saleTeamType.getPrice());
		jpoMemberOrderList.setJpmProductSaleTeamType(saleTeamType);
		jpoMemberOrderList.setPv(saleTeamType.getPv());
		jpoMemberOrderList.setQty(1);
		jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
		jpoMemberOrderList.setVolume(saleTeamType.getJpmProductSaleNew().getVolume());
		jpoMemberOrderList.setWeight(saleTeamType.getJpmProductSaleNew().getWeight());

		sumPrice = sumPrice.add(saleTeamType.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		sumPv = sumPv.add(saleTeamType.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));

		orderItemSet.add(jpoMemberOrderList);

		// 电影票
		// String moviePro = ConfigUtil.getConfigValue(curUser.getCompanyCode(),
		// "product.movie");
//		JpmProductSaleTeamType pro_sst = jpmProductSaleNewManager.getJpmProductSaleTeamTypeList("N05650100101CN0", teamCode, "1", curUser.getCompanyCode(), "0,2");
//		if(pro_sst ==null){
//			throw new AppException("电影票商品未配置！");
//		}
//		boolean priceFalg = false;
//		if (sumPrice.compareTo(new BigDecimal(50000)) >= 0 && sumPrice.compareTo(new BigDecimal(200000)) < 0) {
//			priceFalg = true;
//		}
//
//		if (flag.equals("45") && priceFalg) {
//			JpoMemberOrderList OrderItem = new JpoMemberOrderList();
//			OrderItem.setPrice(pro_sst.getPrice());
//			OrderItem.setJpmProductSaleTeamType(pro_sst);
//			OrderItem.setPv(pro_sst.getPv());
//			OrderItem.setQty(30);
//			OrderItem.setJpoMemberOrder(jpoMemberOrder);
//			OrderItem.setVolume(pro_sst.getJpmProductSaleNew().getVolume());
//			OrderItem.setWeight(pro_sst.getJpmProductSaleNew().getWeight());
//			orderItemSet.add(OrderItem);
//		} else if (flag.equals("65") && priceFalg) {
//			JpoMemberOrderList OrderItem = new JpoMemberOrderList();
//			OrderItem.setPrice(pro_sst.getPrice());
//			OrderItem.setJpmProductSaleTeamType(pro_sst);
//			OrderItem.setPv(pro_sst.getPv());
//			OrderItem.setQty(60);
//			OrderItem.setJpoMemberOrder(jpoMemberOrder);
//			OrderItem.setVolume(pro_sst.getJpmProductSaleNew().getVolume());
//			OrderItem.setWeight(pro_sst.getJpmProductSaleNew().getWeight());
//			
//			orderItemSet.add(OrderItem);
//		}
		log.info("sumPrice is:[" + sumPrice + "] and sumPv is:[" + sumPv + "]");

		jpoMemberOrder.setAmount(sumPrice);
		jpoMemberOrder.setPvAmt(sumPv);
		jpoMemberOrder.setJpoMemberOrderList(orderItemSet);

		return jpoMemberOrder;
	}

	/**
	 * 修改订单收货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/eidtbigOrder")
	private String editBigOrder(HttpServletRequest request) {
		JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String teamChar = jmiTeamManager.teamStr(jsysUser);
		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);
		String orderIds = request.getParameter("orderIds");
		List<JpoMemberOrder> orderList = new ArrayList<JpoMemberOrder>();
		for (String moid : orderIds.split(",")) {
			JpoMemberOrder memberOrder = jpoMemberOrderManager.get(new Long(moid));
			orderList.add(memberOrder);
		}
		request.setAttribute("orderList", orderList);

		List<JalStateProvince> provinceList = jalStateProvinceManager.getJalStateProvinceByCountryCode("CN");
		request.setAttribute("alStateProvinces", provinceList);
		// 团队是否需要绑定事业锦囊
		boolean isBind = jpoMemberOrderManager.isBindProduct(teamChar);
		request.setAttribute("isBind", isBind);
		if (isBind) {

			List jpoMemberList = jpoMemberOrderDao.getJpoMemberMark(jsysUser.getJmiMember().getPapernumber(), "P08520100101CN0", "1");
			if (jpoMemberList != null && jpoMemberList.size() > 0) {
				// 当前用户购买过事业锦囊，需要购买辅销品
				request.setAttribute("userCode_sy", jsysUser.getUserCode());
			} 
			// 获取辅销品
			HashMap<String, ArrayList<JpmProductSaleTeamType>> productList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeMap(null, teamChar, "5", jsysUser.getCompanyCode(), null, null, "5");
			
			
			ArrayList<JpmProductSaleTeamType> syList = new ArrayList<JpmProductSaleTeamType>();
			JpmProductSaleTeamType stt = jpmProductSaleNewManager.getJpmProductSaleTeamType("P08520100101CN0", 
					teamChar, "1", jsysUser.getCompanyCode());
			
			if(stt==null){
				saveMessage(request, "事业锦囊产品信息未设置！");
				return "redirect:/showBigPage";
			}
			syList.add(stt);
			productList.put("7", syList);
			request.setAttribute("productList", productList);
			
		} else {
			log.info("curUser not bind product, temCode is:" + teamChar);
		}
		return "jpoBigPageDetail";
	}

	// @RequestMapping(value ="/bigorderBind")
	// private String bigorderBind(HttpServletRequest request){
	// return
	// }
}
