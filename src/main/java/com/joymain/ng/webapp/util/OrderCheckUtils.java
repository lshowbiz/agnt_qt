package com.joymain.ng.webapp.util;

import com.joymain.ng.util.ListUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.webapp.controller.BaseFormController;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderCheckUtils {

	/**
	 * 支付促销，订单控制
	 */
	public static boolean checkMemberOrder(Class clazz, JpoMemberOrder jpoMemberOrder, HttpServletRequest request) {
		// =======================是否停售================
		String str = PromotionsUtils.verifyOrder(jpoMemberOrder);
		if (StringUtils.isNotEmpty(str)) {
			saveMessage(request, str);
			return false;
		}
		// =======================促销====================
		try {
			Map<String, String> resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder);
			if (resultMap == null) {
				resultMap = PromotionsUtils.checkCX201603(jpoMemberOrder);
			}
			if (resultMap != null) {// 是促销的商品
				if (!"-1".equals(resultMap.get("index"))) {
					if ("false".equals(resultMap.get("code"))) {
						saveMessage(request, resultMap.get("msg"));
						return false;
					}
					if ("1".equals(resultMap.get("index")) && resultMap.get("listsCode").equals(StringUtils.join(GlobalVar.jpoList20160102, ","))) { // 枸杞子
						if (!jpoMemberOrder.getTeamCode().equals(GlobalVar.teamCodeFB)) {// 冯波团队
							saveMessage(request, getText("该单必需使用积分支付！"));
							return false;
						}
					}
				}
			}
			resultMap = PromotionsUtils.checkCX20160118(jpoMemberOrder);
			if (resultMap != null) {// 是促销的商品
				if ("2".equals(resultMap.get("code"))) {
					saveMessage(request, resultMap.get("msg"));
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			saveMessage(request, "数据异常,请联系管理员!");
			return false;
		}
		return true;
	}

	public static Map<String, String> checkMemberOrder(JpoMemberOrder jpoMemberOrder) {
		String fmt = "{code:%s,msg:\"%s\"}";
		String jsonStr = String.format(fmt, new Object[] { 0, "成功" });
		while (true) {
			if (jpoMemberOrder == null) {
				jsonStr = String.format(fmt, new Object[] { 1, "订单不存在!" });
				break;
			}
			// =======================是否停售================
			String str = PromotionsUtils.verifyOrder(jpoMemberOrder);
			if (StringUtils.isNotEmpty(str)) {
				jsonStr = String.format(fmt, new Object[] { 1, str });
				break;
			}
			// =======================促销====================
			try {
				Map<String, String> resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder);
				if (resultMap == null) {
					resultMap = PromotionsUtils.checkCX20160118(jpoMemberOrder);
				}
				if (resultMap != null) {// 是促销的商品
					if (!"-1".equals(resultMap.get("index"))) {
						if ("false".equals(resultMap.get("code")) || "2".equals(resultMap.get("code"))) {
							jsonStr = String.format(fmt, new Object[] { 1, resultMap.get("msg") });
							break;
						}
						if ("1".equals(resultMap.get("index")) && resultMap.get("listsCode").equals(StringUtils.join(GlobalVar.jpoList20160102, ","))) { // 枸杞子
							if (!jpoMemberOrder.getTeamCode().equals(GlobalVar.teamCodeFB)) {// 冯波团队
								jsonStr = String.format(fmt, new Object[] { 1, "该单必需使用积分支付！" });
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonStr = String.format(fmt, new Object[] { 2, "数据异常,请联系管理员!" + e.getMessage() });
				break;
			}
			break;
		}
		return PromotionsUtils.parseJSON2MapString(jsonStr);
	}


	private static void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute(BaseFormController.MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add(msg);
		request.getSession().setAttribute(BaseFormController.MESSAGES_KEY, messages);
	}

	private static String getText(String msgKey) {
		return LocaleUtil.getLocalText(msgKey);
	}
	public static boolean checkOrderType(JpoMemberOrder jpoMemberOrder){
		boolean result=false;
		LinkedHashMap<String, String> configMap =null;
		try {
			configMap = ListUtil.getListOptions(jpoMemberOrder.getCompanyCode(), "orderpayother.limit");
			if(configMap!=null){
				if(configMap.keySet().contains(jpoMemberOrder.getOrderType())){
					result=true;
				}
			}
		}catch(Exception e){
			configMap = null;
		}
		return result;
	}

	/**
	 * 积分，只有特定的订单类型能用,参数配置 4：重消单
	 * @param jpoMemberOrder
	 * @return
	 */
	public static boolean checkCoinPayOrderType(JpoMemberOrder jpoMemberOrder){
		boolean result=false;
		LinkedHashMap<String, String> configMap =null;
		try {
			configMap = ListUtil.getListOptions(jpoMemberOrder.getCompanyCode(), "coin.pay.order.allow");
			if(configMap!=null){
				if(configMap.keySet().contains(jpoMemberOrder.getOrderType())){
					result=true;
				}
			}
		}catch(Exception e){
			configMap = null;
		}
		return result;
	}
}
