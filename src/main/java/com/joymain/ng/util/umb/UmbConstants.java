package com.joymain.ng.util.umb;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 宝易互通账户管理配置
 * 
 * @author
 * 
 */
public class UmbConstants {

	public static Map<String, Map<String, String>> account = new HashMap<String, Map<String, String>>();
	static {

		//淮安卓雅健康科技有限公司, 收款地区：天津
		Map account1 = new HashMap();
		account1.put("merchantid", "1194");
		account1.put("subject", "1194000");
		account1.put("password", "Dh8S1CF7zfi");
		account.put("163703", account1);// 天津

		//淮安宏萱健康科技有限公司， 收款地区：吉林
		Map account2 = new HashMap();
		account2.put("merchantid", "1195");
		account2.put("subject", "1195000");
		account2.put("password", "HF2Z1fH0Jt");
		account.put("163708", account2);// 吉林
		account.put("null", account2);// 吉林
	}
}
