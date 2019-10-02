package com.joymain.ng.webapp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;

@SuppressWarnings("unchecked")
public class PromotionsUtils {
	
	public static Map<String, String> checkCX201603(JpoMemberOrder jpoMemberOrder) throws Exception{
		/*List<String> rule = new ArrayList<String>();
		rule.add("5:5");
		String jsonStr = integralRedemption(jpoMemberOrder, rule, GlobalVar.jpoList20160301);
		Map<String, String> resultMap = parseJSON2MapString(jsonStr);
		String[] teamCode = new String[] { "CN18645446" };// 团队编码
		if (!StringUtil.contains(teamCode, jpoMemberOrder.getTeamCode())) {
			resultMap.put("code", "false");
			resultMap.put("msg", "该团队不允许购买!");
		}*/
		return null;
	}
	
	/**
	 * 因1月开启韩美旅游报名，需对【N07030100101CN0美韩旅游报名产品】做如下系统设置：
	 * 1、只允许单独订购
	 * 2、不生成发货单
	 * 3、只允许存折或现金支付，不允许基金支付
	 * 4、TT2、5、XX团队无订购权限
	 * 5、18号开始，请协助开发。
	 */
	public static Map<String, String> checkCX20160118(JpoMemberOrder jpoMemberOrder) throws Exception {
		String fmtJson = "{code:\"%s\",msg:\"%s\"}";
		String json = String.format(fmtJson, "0", "非韩美旅游报名允许通过!");
		String[] teamCode = new String[] { "CN16481747", "CN18728599", "CN12365064" };// 团队编码
		int only = jpoIsOnly(jpoMemberOrder, GlobalVar.KoralTravel201601.toArray(new String[] {}));
		if (only == 1) {
			if (StringUtil.contains(teamCode, jpoMemberOrder.getTeamCode())) { // 团队编码jpoMemberOrder.getTeamCode()
				json = String.format(fmtJson, "2", "该团队不允许购买此商品!");
			} else {
				json = String.format(fmtJson, "1", "允许单独订购韩美旅游商品!");
			}
		} else if (only == 2) {
			json = String.format(fmtJson, "2", "该商品必须单独订购!");
		}
		return parseJSON2MapString(json);
	}
	
	/**
	 * 2016年1月促销审批
	 * @param jpoMemberOrder
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> checkCX201601(JpoMemberOrder jpoMemberOrder) throws Exception{
		String startDateS = ConfigUtil.getConfigValue("CN", "201601cx.startdate");
		String endDateS = ConfigUtil.getConfigValue("CN", "201601cx.enddate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = Calendar.getInstance().getTime();
		if (startDateS != null && endDateS != null) {
			if (now.after(sdf.parse(startDateS)) && now.before(sdf.parse(endDateS))) {
				List<String> rule = new ArrayList<String>();
				rule.add("5:5");
				rule.add("5:5");
				rule.add("25000:0");
				String jsonStr = integralRedemption(jpoMemberOrder, rule, GlobalVar.jpoList20160101, GlobalVar.jpoList20160102, GlobalVar.jpoList20160103);
				Map<String, String> resultMap = parseJSON2MapString(jsonStr);
				if (!resultMap.get("index").equals("-1")) {
					String[] orderType = new String[] { "4", "9", "14" }; // 订单类型
					String[] teamCode = new String[] { "CN16481747", "CN18728599", "CN12365064" };// 团队编码
					if (StringUtil.contains(orderType, jpoMemberOrder.getOrderType())) { // 订单类型
						//resultMap.get("listsCode").equals(StringUtils.join(GlobalVar.jpoList20160301, ","));
						if(!resultMap.get("carProList").equals(StringUtils.join(GlobalVar.jpoList20160301, ","))){
							if (StringUtil.contains(teamCode, jpoMemberOrder.getTeamCode())) { // 团队编码jpoMemberOrder.getTeamCode()
								resultMap.put("code", "false");
								resultMap.put("msg", "该团队不允许购买!");
							}
						}
					}else{
						resultMap.put("code", "false");
						resultMap.put("msg", "订单类型错误!");
					}
				}
				return resultMap;
			}
		}
		return null;
	}
	
	//JfiRechargeController.verifyOrder(jpoMemberOrderManager.get(Long.parseLong(entity.getOrdId())))
	
	/**
	 * 判断订单是否存在已售完的商品
	 * @param jpoMemberOrder
	 * @return
	 */
	public static String verifyOrder(JpoMemberOrder jpoMemberOrder) {
		String str = "";
		try {
			//JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(memberOrderNo));
			Iterator<JpoMemberOrderList> its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = its1.next();
				String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
				if (!"1".equals(status)) {
					str = "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo() + ")已售完!";
				}
			}
		} catch (Exception e) {
			str ="订单错误!";
			e.printStackTrace();
		}
		return str;
	}
	
	
	
	/**
	 * 促销通用方法
	 * @param jpoMemberOrder 订单号
	 * @param rule 积分支付规则与lists 1：1  // 金额：积分
	 * @param lists 相同积分规则的一组
	 * @return{code:是否允许通过,index:第几组规则商品,maxJF:最大积分,minJB:最新金额,msg:消息,listsCode:商品编码}
	 */
	public static String integralRedemption(JpoMemberOrder jpoMemberOrder, List<String> rule, List<String>... lists) {
		int xz = -1; // 调用了那套促销规则
		String json = "{code:\"%s\",index:\"%s\",maxJF:\"%s\",minJB:\"%s\",msg:\"%s\",listsCode:\"%s\",carProList:\"%s\"}";
		if (lists.length == rule.size()) {
			List<String> carProList = new ArrayList<String>();// 商品编码
			int qty = 0;// 商品数量
			Iterator<JpoMemberOrderList> iter = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (iter.hasNext()) {
				JpoMemberOrderList carOrderList = iter.next();
				String carProNo = carOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
				carProList.add(carProNo);
				qty += carOrderList.getQty();
			}
			for (int i = 0; i < lists.length; i++) {
				if (!Collections.disjoint(carProList, lists[i])) {
					xz = i;
					break;
				}
			}
			if (xz != -1) {
				boolean isOnly = true;
				List<String> codes = lists[xz];
				if (carProList.size() <= codes.size()) {// 说明订单中没有超过限定的商品数量
					for (String s : carProList) {
						if (!codes.contains(s)) {
							isOnly = false;
							break;
						}
					}
					if (isOnly) {
						String[] rules = (rule.get(xz) != null ? rule.get(xz) : "5:5").split(":");// 金额：积分
						BigDecimal a = new BigDecimal(rules[0]);
						BigDecimal b = new BigDecimal(rules[1]);
						BigDecimal maxJF = new BigDecimal(0);// 最大积分
						BigDecimal minJB = new BigDecimal(0);// 最小金额
						if (a.compareTo(new BigDecimal(10)) != -1) {// 说明用了最大金额
							minJB = a.multiply(new BigDecimal(qty));
						}
						if (a.add(b).compareTo(new BigDecimal(10)) == 0) { // 表示按照比例计算
							BigDecimal scale = a.divide(new BigDecimal(10)).setScale(2, RoundingMode.HALF_UP);
							minJB = jpoMemberOrder.getAmount().multiply(scale);
						}
						maxJF = jpoMemberOrder.getAmount().subtract(minJB);
						System.out.println("金币:" + minJB + "===积分" + maxJF);
						json = String.format(json, true, xz, maxJF, minJB, "订单允许通过", StringUtils.join(codes, ","), StringUtils.join(carProList, ","));
					} else {
						json = String.format(json, false, xz, 0, 0, "商品不能与其它商品同时订购!", StringUtils.join(codes, ","), StringUtils.join(carProList, ","));
					}
				} else {
					json = String.format(json, false, xz, 0, 0, "商品不能与其它商品同时订购!", StringUtils.join(codes, ","), StringUtils.join(carProList, ","));
				}
			}
			json = String.format(json, true, xz, 0, 0, "====非促销====!", "",StringUtils.join(carProList, ","));
		} else {
			json = String.format(json, false, xz, 0, 0, "====参数错误====!", "","");
		}
		return json;
	}
	
	/**
	 * 促销商品是否单独订购
	 * @param jpoMemberOrder 订单
	 * @param goods 商品编码
	 * @return 0:表示普通商品 ，1:表示促销商品单独订购，2:促销商品非单独订购
	 */
	public static int jpoIsOnly(JpoMemberOrder jpoMemberOrder, String[] goods) {
		int isOnly = 0;
		Iterator<JpoMemberOrderList> its2 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its2.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = its2.next();
			String carProNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();// 商品
			if (StringUtil.contains(goods, carProNo)) {// 只要有促销商品在其中就表示 需要单独订购
				if (jpoMemberOrder.getJpoMemberOrderList().size() == 1) { // 单独订购
					isOnly = 1; // 允许通过
				} else {
					isOnly = 2; // 不允许通过
				}
				break;
			}
		}
		return isOnly; // 不允许通过
	}
	
	/**
	 * 函数注释：parseJSON2MapString()<br>
	 * 用途：该方法用于json数据转换为<Map<String, String><br>
	 * 备注：***<br>
	 */
	public static Map<String, String> parseJSON2MapString(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if (null != v) {
				map.put(k.toString(), v.toString());
			}
		}
		return map;
	}
	
	
	
	/**
	 * @Description 验证是否有促销商品，促销期内有促销商品则返回true,与其他促销商品混合订购时返回false
	 * @author houxyu
	 * @date 2016-5-25
	 * @param jpoMemberOrder
	 * @param lists
	 * @return
	 * @throws Exception 
	 */
	public static boolean integralCheck(JpoMemberOrder jpoMemberOrder, List<String> lists,String startTimeConfig,String endTimeConfig) throws Exception {
		boolean isOnly = false;
		String startTime = ConfigUtil.getConfigValue("CN", startTimeConfig);
		String endTime = ConfigUtil.getConfigValue("CN", endTimeConfig);
		if(MeteorUtil.checkTime(startTime,endTime)){
//			List<String> carProList = new ArrayList<String>();// 商品编码
			if(null!=jpoMemberOrder && (OrderCheckUtils.checkCoinPayOrderType(jpoMemberOrder))) {
				Iterator<JpoMemberOrderList> iter = jpoMemberOrder.getJpoMemberOrderList().iterator();
				while (iter.hasNext()) {//循环取出订单中所有的商品
					JpoMemberOrderList carOrderList = iter.next();
					String carProNo = carOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
					//				carProList.add(carProNo);
					//验证商品是否为促销
					if (lists.contains(carProNo)) {
						isOnly = true;
					}
					if (!lists.contains(carProNo)) {
						isOnly = false;
						break;
					}
				}
			}
		}
		
		return isOnly;
	}
	
	/**
	 * @Description:	201611月重消单开放积分换购促销验证,金柏格团队特殊处理
	 * @author:			侯忻宇
	 * @date:		    2016-10-24
	 * @param jpoMemberOrder
	 * @param coincxPnoConfig
	 * @param startTimeConfig
	 * @param endTimeConfig
	 * @param teamCode
	 * @return
	 * @throws Exception:
	 * @exception:
	 * @return:
	 */
	public static boolean coincxCheck(JpoMemberOrder jpoMemberOrder, List<String> lists,String startTimeConfig,String endTimeConfig) throws Exception {
		boolean isOnly = false;
		String startTime = ConfigUtil.getConfigValue("CN", startTimeConfig);
		String endTime = ConfigUtil.getConfigValue("CN", endTimeConfig);
		if(MeteorUtil.checkTime(startTime,endTime)){
			
			if(null!=jpoMemberOrder && (OrderCheckUtils.checkCoinPayOrderType(jpoMemberOrder))){
				Iterator<JpoMemberOrderList> iter = jpoMemberOrder.getJpoMemberOrderList().iterator();
				while (iter.hasNext()) {//循环取出订单中所有的商品
					JpoMemberOrderList carOrderList = iter.next();
					String carProNo = carOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
					//验证商品是否为特殊商品,不做积分换购
					if(lists.contains(carProNo)){
						isOnly = false;
						break;
					}
					if(!lists.contains(carProNo)){
						isOnly = true;
					}
					if("P04060100201CN0".equals(carProNo)){
						isOnly = false;
						break;
					}
				}
			}
		}
		return isOnly;
	}
	
	/**
	 * @Description 积分支付跳转时，确认是否符合促销策略和返回最多使用积分
	 * @author houxyu
	 * @date 2016-5-25
	 * @param jpoMemberOrder 订单
	 * @param lists	促销商品编码集合
	 * @param startTimeConfig	开始时间配置参数名
	 * @param endTimeConfig		结束时间配置参数名
	 * @param fiBcoinBalance	积分余额对象
	 * @param cxType			积分试算类型，0为按最低使用金额计算，1为比例计算比例为0.1-0.9，表示最多可使用多少积分
	 * @param cxb				积分试算 值(金额或比例  根据类型设置)
	 * @return
	 * @throws Exception
	 */
	public static String checkJfPay(JpoMemberOrder jpoMemberOrder,
			List<String> lists, String startTimeConfig,String endTimeConfig,
			FiBcoinBalance fiBcoinBalance,String cxType,String cxb) throws Exception {
		String json = "{coin:\"%s\",sumCoin:\"%s\"}";//coin 个人拥有积分，sumCoin 订单可使用最大积分
		BigDecimal coin = new BigDecimal(0);
		BigDecimal sumCoin = new BigDecimal(0);
		int qty = 0;
		if(integralCheck(jpoMemberOrder, lists,startTimeConfig,endTimeConfig)){
			if(null != fiBcoinBalance){
				coin = fiBcoinBalance.getBalance();//积分余额
			}
			if("0".equals(cxType)){// 旋磁椅  一个最低25000金额   减去2W5之后最多能有多少积分可以使用   需与订单中的商品数量关系
				Iterator<JpoMemberOrderList> iter = jpoMemberOrder.getJpoMemberOrderList().iterator();
				while (iter.hasNext()) {
					JpoMemberOrderList carOrderList = iter.next();
					qty += carOrderList.getQty();
				}
				if(jpoMemberOrder.getAmount().subtract(new BigDecimal(cxb).multiply(new BigDecimal(qty))).compareTo(coin)==1){
					sumCoin = coin;
				}else{
					sumCoin = jpoMemberOrder.getAmount().subtract(new BigDecimal(cxb).multiply(new BigDecimal(qty)));
				}
			}else if ("1".equals(cxType)){// 其他促销按照比例   按比例得出有多少积分可以使用
				if(jpoMemberOrder.getAmount().multiply(new BigDecimal(cxb)).compareTo(coin)==1){
					sumCoin = coin;
				}else{
					sumCoin = jpoMemberOrder.getAmount().multiply(new BigDecimal(cxb));
				}
			}
			sumCoin = new BigDecimal(sumCoin.intValue());
			
			json = String.format(json, coin,sumCoin);
		}else{
			json = "-1";
		}
		return json;
	}
	
	
	/**
	 * @Description:	积分换购促销的积分试算
	 * @author:			侯忻宇
	 * @date:		    2016-10-25
	 * @param jpoMemberOrder
	 * @param lists
	 * @param startTimeConfig
	 * @param endTimeConfig
	 * @param fiBcoinBalance
	 * @param cxb
	 * @return
	 * @throws Exception:
	 * @exception:
	 * @return:
	 */ 
	public static String checkCoincxPay(JpoMemberOrder jpoMemberOrder,
			List<String> lists, String startTimeConfig,String endTimeConfig,
			FiBcoinBalance fiBcoinBalance,String cxb) throws Exception {
		String json = "{coin:\"%s\",sumCoin:\"%s\"}";//coin 个人拥有积分，sumCoin 订单可使用最大积分
		BigDecimal coin = new BigDecimal(0);
		BigDecimal sumCoin = new BigDecimal(0);
		if(coincxCheck(jpoMemberOrder, lists,startTimeConfig,endTimeConfig)){
			if(null != fiBcoinBalance){
				coin = fiBcoinBalance.getBalance();//积分余额
			}
			// 其他促销按照比例   按比例得出有多少积分可以使用
			if(jpoMemberOrder.getAmount().multiply(new BigDecimal(cxb)).compareTo(coin)==1){
				sumCoin = coin;
			}else{
				sumCoin = jpoMemberOrder.getAmount().multiply(new BigDecimal(cxb));
			}
			
			sumCoin = new BigDecimal(sumCoin.intValue());
			
			json = String.format(json, coin,sumCoin);
		}else{
			json = "-1";
		}
		return json;
	}
	
}
