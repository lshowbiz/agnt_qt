package com.joymain.ng.util.bill99;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import com.joymain.ng.util.PropertiesUtil;

/**
 * EC系统 快钱账户管理配置20140625
 *
 * @author lenovo
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Bill99Constants {
	public static final String localhost = PropertiesUtil.getPropertieByPayJump();//"http://59.41.187.147:7082/jmhg_ht/";//"http://ddzf.jmtop.com/jecs/";

	// public static final String localhost =
	// "http://test.joylifeglobal.net/jecsnew_ht/";

	public static final String encoding = "UTF-8";
	// public static final String memberCode = "10018019544";
	public static final String showUrl = localhost + "jfiPay99billShow.jsp";
	public static final String postUrl = "https://www.99bill.com/gateway/recvMerchantInfoAction.htm";
	// public static final String postUrl =
	// "http://sandbox.99bill.com/gateway/recvMerchantInfoAction.htm";
	public static final String inputCharset = "1";
	public static final String pageUrl = localhost
			+ "jfiPay99billReceivePage.html";
	public static final String bgUrl = localhost + "payReceive";//"jfiPay99billReceive.html";
	public static final String version = "v2.0";
	public static final String language = "1";
	public static final String signType1 = "1";
	public static final String signType4 = "4";
	// public static final String merchantAcctId = memberCode + "01";
	public static final String payerContactType = "1";
	public static final String payType10 = "10";
	public static final String payType13 = "10";
	public static final String redoFlag = "0";
	public static final String pid = "";
	public static final BigDecimal feeP = new BigDecimal("0");
	public static final BigDecimal fee = feeP.add(new BigDecimal("1"));
	public static final String key = "7Q3U392SQ795CB8L";
	public static final String pubKeyName = "99bill.cert.rsa.20340630.cer";
	public static PublicKey publicKey = null;

	public static PrivateKey privateKey = null;// 私钥
	public static final String priKeyName = "keystore_new_110707.pfx";// 私钥文件
	public static final String billPassword = "zmgj2015";// 注册密码，统一

	public static Map<String, Map<String, String>> account = new HashMap<String, Map<String, String>>();;
	static {
		//20160627替换快钱所有商户号 modify by jfoy begin
		Map kq1 = new HashMap();
	//	kq1.put("memberCode", "1019150567301");
		kq1.put("memberCode", "1020990515501");
		kq1.put("address", "快钱");
		kq1.put("merchantName", "安杰玛");

		account.put("kqDefault", kq1);

		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>(Bill99Constants.account);
		for (String key : map.keySet()) {
			account.put(map.get(key).get("memberCode"), map.get(key));
		}

		//Modify By WuCF 20170424 手机端快钱支付商户号添加
		Map app1 = new HashMap();
		app1.put("memberCode", "1020990515501");
		app1.put("merchantName", "手机端商户号");
		app1.put("password", "zmgj2015");
		app1.put("priKeyName", "key/app/99bill-rsa.pfx");
		account.put("1020990515501", app1);
	}

	public static void main(String[] args) {

		Map<String, Map<String, String>> map2 = Bill99Constants.account;
		System.out.println(map2.size());
		for (String key : map2.keySet()) {
			System.out.println(key + "==" + map2.get(key).get("memberCode"));
		}
		//System.out.println(Bill99Constants.account.get("1001776337001"));
	}
}
