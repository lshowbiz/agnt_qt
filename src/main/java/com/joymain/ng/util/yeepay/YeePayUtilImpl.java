package com.joymain.ng.util.yeepay;

import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.util.yspay.PayBusiness;

@Service("yeePayUtil")
@WebService(serviceName = "YeePayUtilService", endpointInterface = "com.joymain.ng.util.yeepay.YeePayUtil")
public class YeePayUtilImpl implements YeePayUtil {
	public static final String post_url = "https://www.yeepay.com/app-merchant-proxy/node";
	public static final String notify_url = "http://59.41.187.147:7082/jmhg_ht/jfiYeePayReceive.html";

	@Autowired
	private JsysIdManager sysIdManager;

	@Autowired
	private FiCommonAddrManager fiCommonAddrManager;

	public Map<String, String> getMerId(String userCode) {
		FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(userCode);// 获取会员常用收货地址
		if (fiCommonAddr != null) {
			return PayBusiness.account.get("dq"+fiCommonAddr.getProvince());
		}
		return PayBusiness.account.get("ybDefault");
	}

	public String getKeyByMerId(String merId) {
		return PayBusiness.account.get("sh"+merId).get("password");
	}

	public YeePay getYeePay(YeePay yeePay, int flag) {
		yeePay.setP0_Cmd("Buy");

		if (flag == 0) {
			yeePay.setP2_Order(this.sysIdManager.buildIdStr("advicecode_cn"));
			 yeePay.setP1_MerId(getMerId(yeePay.getPa_MP()).get("merchantid"));
			// yeePay.setP1_MerId(getMerId(yeePay.getPa_MP()));
		}
		yeePay.setP4_Cur("CNY");
		yeePay.setP5_Pid("");
		yeePay.setPost_url(post_url);
		yeePay.setP8_Url(notify_url);
		yeePay.setPa_MP(yeePay.getPa_MP() + "," + String.valueOf(flag));

		yeePay.setHmac(getSign(yeePay));

		return yeePay;
	}

	public String getSign(YeePay yeePay) {
		String p0_Cmd = formatString(yeePay.getP0_Cmd());
		String p1_MerId = formatString(yeePay.getP1_MerId());
		String p2_Order = formatString(yeePay.getP2_Order());
		String p3_Amt = formatString(yeePay.getP3_Amt());
		String p4_Cur = formatString(yeePay.getP4_Cur());
		String p5_Pid = formatString(yeePay.getP5_Pid());
		String p6_Pcat = formatString(yeePay.getP6_Pcat());
		String p7_Pdesc = formatString(yeePay.getP7_Pdesc());
		String p8_Url = formatString(yeePay.getP8_Url());
		String p9_SAF = formatString(yeePay.getP9_SAF());
		String pa_MP = formatString(yeePay.getPa_MP());
		String pd_FrpId = formatString(yeePay.getPd_FrpId());
		String pr_NeedResponse = formatString(yeePay.getPr_NeedResponse());

		String[] strArr = { p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse };

		return DigestUtil.getHmac(strArr, getKeyByMerId(p1_MerId));
	}

	public static String formatString(String text) {
		return ((text == null) ? "" : text.trim());
	}
}