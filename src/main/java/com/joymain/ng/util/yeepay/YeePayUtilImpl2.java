package com.joymain.ng.util.yeepay;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.pay.PayUtils;

public class YeePayUtilImpl2 implements PayModeService {
	public static final String post_url = "https://www.yeepay.com/app-merchant-proxy/node";
	public static final String notify_url = BASE + "jfiYeePayReceive.html";

	@Override
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany) {
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		Map<String, String> map = jfiPayOrderCompany.getBusiness();// 根据商户号获取商户对象
		try {
			Map<String, String> tmp = new LinkedHashMap<String, String>(); // 有序排序
			tmp.put("p0_Cmd", "Buy");
			tmp.put("p1_MerId", map.get("merchantid"));
			tmp.put("p2_Order", jfiPayOrderCompany.getOrderNo());
			tmp.put("p3_Amt", jfiPayOrderCompany.getPayAmount() + "");
			tmp.put("p4_Cur", "CNY");
			tmp.put("p5_Pid", "");
			tmp.put("p8_Url", NOTIFY_URL+"?zmType=ybpay"); 
			tmp.put("post_url", post_url);
			tmp.put("pa_MP", PayUtils.getRemarkBean(jfiPayOrderCompany,  new BigDecimal("0.00"), "ybpay"));
			tmp.put("hmac", getSign(tmp, map.get("password")));
			retMsg.setUrl(post_url);
			retMsg.setDataMap(tmp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return retMsg;
	}

	public String getSign(Map<String, String> map, String password) {
		String p0_Cmd = formatString(map.get("p0_Cmd"));
		String p1_MerId = formatString(map.get("p1_MerId"));
		String p2_Order = formatString(map.get("p2_Order"));
		String p3_Amt = formatString(map.get("p3_Amt"));
		String p4_Cur = formatString(map.get("p4_Cur"));
		String p5_Pid = formatString(map.get("p5_Pid"));
		String p6_Pcat = formatString(map.get("p6_Pcat"));
		String p7_Pdesc = formatString(map.get("p7_Pdesc"));
		String p8_Url = formatString(map.get("p8_Url"));
		String p9_SAF = formatString(map.get("p9_SAF"));
		String pa_MP = formatString(map.get("pa_MP"));
		String pd_FrpId = formatString(map.get("pd_FrpId"));
		String pr_NeedResponse = formatString(map.get("pr_NeedResponse"));

		String[] strArr = { p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse };
		return DigestUtil.getHmac(strArr, password);
	}

	public static String formatString(String text) {
		return ((text == null) ? "" : text.trim());
	}

}