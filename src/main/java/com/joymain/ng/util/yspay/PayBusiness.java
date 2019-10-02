package com.joymain.ng.util.yspay;

import java.util.HashMap;
import java.util.Map;

import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.bill99.Bill99UtilImpl2;
import com.joymain.ng.util.chinapnr.ChinapnrUtilImpl2;
import com.joymain.ng.util.huanxun.HxUtilImpl;
import com.joymain.ng.util.umb.UmbPayUtilImpl2;
import com.joymain.ng.util.yeepay.YeePayUtilImpl2;

@SuppressWarnings("unchecked")
public class PayBusiness {

	public static Map<String, Map<String, String>> account = new HashMap<String, Map<String,String>>();
	private static final String ysPath = "key/ysPay/";

	//20303220:[TW] 台湾,20000466:香港,163704:河北省,163705:山西省,163706:内蒙古自治区,163707:辽宁省,163708:吉林省,
	//163709:黑龙江省,163710:上海市,163711:江苏省,163712:浙江省,163713:安徽省,163714:福建省,163715:江西省,
	//163716:山东省,163717:河南省,163718:湖北省,163719:湖南省,163720:广东省,163721:广西壮族自治区,163722:海南省,
	//163723:重庆市,163724:四川省,163725:贵州省,163726:云南省,163727:西藏自治区,163728:陕西省,
	//163729:甘肃省,163730:青海省,163731:宁夏回族自治区,163732:新疆维吾尔自治区,163702:北京市,163703:天津市,
	private static String[] hxpay = new String[] { };// 环迅
	private static String[] bypay = new String[] { };// 163721宝易互通
	private static String[] yspay = new String[] { };
	private static String[] ybpay = new String[] { };//Modify By WuCF 20170118 安杰玛目前只有快钱支付
	//private static String[] hfpay = new String[] {"163704","163705","163706","163707","163708","163709","163710","163711","163712","163713","163714","163715","163716","163717","163718","163719","163720","163721","163722","163723","163724","163725","163726","163727","163728","163729","163730","163731","163732","163702","163703"};
	private static String[] hfpay = new String[] {};
	//"163704","163705","163706","163707","163708","163709","163710","163711","163712","163713","163714","163715","163716","163717","163718","163719","163720","163721","163722","163723","163724","163725","163726","163727","163728","163729","163730","163731","163732","163702","163703"
	//public static String[] kqpay = new String[] { "163704", "163724", "163728", "163713", "163703" ,"163710", "163714", "163725", "163719", "163726"};

	//Modify By WuCF 20160826 替换汇付:陕西(163728)、贵州(163725)、云南(163726)、3个省份到对应的易宝支付
	//private static String[] hfpay = new String[] { "163704", "163724", "163728", "163713", "163703" ,"163710", "163714", "163725", "163719", "163726", "163718"};

	static {
		// -------------------------宝易互通----------------------
		Map<String, String> byht1 = new HashMap<String, String>();
		byht1.put("merchantid", "1200");// ##商户号
		byht1.put("password", "333Era08eN79b");// ##商户私钥证书密码
		byht1.put("address", "宁夏回族自治区,甘肃省");
		byht1.put("subject", "1200000");
		byht1.put("payName", "宝易互通");
		byht1.put("merchantName", "淮安五行健康科技有限公司");// ##商户名称
		loadAccount(new Map[] { byht1,byht1 }, bypay);


		// -------------------------环迅支付----------------------
		Map<String, String> hx1 = new HashMap<String, String>();
		hx1.put("merchantid", "183971");// ##商户号
		hx1.put("password", "HOpslHd8fctJhZFueaUNEyZ4wWqFDkQaZNcF9BPRZCANvBLXPyBwlD8JY5p77KGhw9zAt1P9Jj8Sk8bUsUdHVe3RpMDZswH5yZLdF2hoTHH1VNduJpekLUxxNFs4T7l3");// ##商户私钥证书密码
		hx1.put("address", "江苏省");
		hx1.put("account", "1839710017");
		hx1.put("payName", "环迅支付");
		loadAccount(new Map[] { hx1 }, hxpay);

		// -----------------------------银盛-------------------------------start
//		Map<String, String> ys1 = new HashMap<String, String>();
//		ys1.put("merchantid", "cqpy");// ##商户号
//		ys1.put("merchantName", "重庆市盘阳商贸有限公司");// ##商户名称
//		ys1.put("merchantPath", ysPath + "cqpy.pfx");// 商户私钥证书路径(发送交易签名使用)
//		ys1.put("keyPassword", "py1359");// ##商户私钥证书密码
//		ys1.put("busicode", "01000010");// 业务代码（商户需申请）
//		ys1.put("address", "宁夏回族自治区,甘肃省");

		Map<String, String> ys2 = new HashMap<String, String>();
		ys2.put("merchantid", "djyjlsm");// ##商户号
		ys2.put("merchantName", "都江堰聚龙商贸有限公司");// ##商户名称
		ys2.put("merchantPath", ysPath + "djyjlsm.pfx");// 商户私钥证书路径(发送交易签名使用)
		ys2.put("keyPassword", "jl1359");// ##商户私钥证书密码
		ys2.put("busicode", "01000010");// 业务代码（商户需申请）
		ys2.put("address", "重庆");
		Map<String, String> ys3 = new HashMap<String, String>();
		ys3.put("merchantid", "ldysm");// ##商户号
		ys3.put("merchantName", "乌鲁木齐市丽道蕴商贸有限公司");// ##商户名称
		ys3.put("merchantPath", ysPath + "ldysm.pfx");// 商户私钥证书路径(发送交易签名使用)
		ys3.put("keyPassword", "dy1359");// ##商户私钥证书密码
		ys3.put("busicode", "01000010");// 业务代码（商户需申请）
		ys3.put("address", "新疆");
		Map<String, String> ys4 = new HashMap<String, String>();
		ys4.put("merchantid", "qhzjsm");// ##商户号
		ys4.put("merchantName", "齐河子俊商贸有限公司");// ##商户名称
		ys4.put("merchantPath", ysPath + "qhzjsm.pfx");// 商户私钥证书路径(发送交易签名使用)
		ys4.put("keyPassword", "zj1359");// ##商户私钥证书密码
		ys4.put("busicode", "01000010");// 业务代码（商户需申请）
		ys4.put("address", "山东");
		Map<String, String> ys5 = new HashMap<String, String>();
		ys5.put("merchantid", "wmmy");// ##商户号
		ys5.put("merchantName", "厦门旺民贸易有限公司");// ##商户名称
		ys5.put("merchantPath", ysPath + "wmmy.pfx");// 商户私钥证书路径(发送交易签名使用)
		ys5.put("keyPassword", "wm1359");// ##商户私钥证书密码
		ys5.put("busicode", "01000010");// 业务代码（商户需申请）
		ys5.put("address", "广东");
		Map<String, String> ys6 = new HashMap<String, String>();
		ys6.put("merchantid", "tsldsm");// ##商户号
		ys6.put("merchantName", "唐山乐达商贸有限公司");// ##商户名称
		ys6.put("merchantPath", ysPath + "tsldsm.pfx");// 商户私钥证书路径(发送交易签名使用)
		ys6.put("keyPassword", "ld1359");// ##商户私钥证书密码
		ys6.put("busicode", "01000010");// 业务代码（商户需申请）
		ys6.put("address", "河南");
		//modify by jfoy 新增支付商户号 20160628 begin 
		Map<String, String> ys7 = new HashMap<String, String>();
		ys7.put("merchantid", "dlxysm");// ##商户号
		ys7.put("merchantName", "大连轩远商贸有限公司");// ##商户名称
		ys7.put("merchantPath", ysPath + "dlxysm.pfx");// 商户私钥证书路径(发送交易签名使用)
		ys7.put("keyPassword", "xy1359");// ##商户私钥证书密码
		ys7.put("busicode", "01000010");// 业务代码（商户需申请）
		ys7.put("address", "浙江");
		Map<String, String> ys8 = new HashMap<String, String>();
		ys8.put("merchantid", "ymqymy");// ##商户号
		ys8.put("merchantName", "武汉市赢脉群英贸易有限公司");// ##商户名称
		ys8.put("merchantPath", ysPath + "ymqymy.pfx");// 商户私钥证书路径(发送交易签名使用)
		ys8.put("keyPassword", "ym1359");// ##商户私钥证书密码
		ys8.put("busicode", "01000010");// 业务代码（商户需申请）
		ys8.put("address", "辽宁");
		loadAccount(new Map[] { ys2, ys3, ys4, ys5, ys6 ,ys7 ,ys8 }, yspay);
		//modify by jfoy 新增支付商户号 20160628 end 
		// -----------------------------银盛-------------------------------end

		// -----------------------------汇付天下-------------------------------start
		// String hftxPath = "MerPrK%s.key";
		Map<String, String> hf1 = new HashMap<String, String>();
		hf1.put("merchantid", "873983");
		hf1.put("address", "AJM");
		hf1.put("merchantName", "商户名称");// ##商户名称

		Map<String, String> hf2 = new HashMap<String, String>();
		hf2.put("merchantid", "873496");
		hf2.put("address", "四川");
		hf2.put("merchantName", "四川唐杨芮茜贸易有限公司");// ##商户名称
		/*Map<String, String> hf3 = new HashMap<String, String>();
		hf3.put("merchantid", "873857");
		hf3.put("address", "陕西");
		hf3.put("merchantName", "嘉祥易祥商贸有限公司");*/// ##商户名称
		Map<String, String> hf4 = new HashMap<String, String>();
		hf4.put("merchantid", "873773");
		hf4.put("address", "安徽");
		hf4.put("merchantName", "扬州源爱商贸有限公司");// ##商户名称
		Map<String, String> hf5 = new HashMap<String, String>();
		hf5.put("merchantid", "873795");
		hf5.put("address", "天津");
		hf5.put("merchantName", "沧州鼎辉商贸有限公司");// ##商户名称
		Map<String, String> hf6 = new HashMap<String, String>();
		hf6.put("merchantid", "873789");
		hf6.put("address", "上海");
		hf6.put("merchantName", "扬州大昌健康科技有限公司");// ##商户名称
		Map<String, String> hf7 = new HashMap<String, String>();
		hf7.put("merchantid", "873831");
		hf7.put("address", "福建");
		hf7.put("merchantName", "广州董南贸易有限公司");// ##商户名称
		/*Map<String, String> hf8 = new HashMap<String, String>();
		hf8.put("merchantid", "873796");
		hf8.put("address", "贵州");
		hf8.put("merchantName", "昆明乐劳商贸有限公司");*/// ##商户名称
		Map<String, String> hf9 = new HashMap<String, String>();
		hf9.put("merchantid", "873718");
		hf9.put("address", "湖南");
		hf9.put("merchantName", "成都市秋实慧源贸易有限公司");// ##商户名称
		/*Map<String, String> hf10 = new HashMap<String, String>();
		hf10.put("merchantid", "873794");
		hf10.put("address", "云南");
		hf10.put("merchantName", "昆明阳遐商贸有限公司");*/// ##商户名称
		Map<String, String> hf11 = new HashMap<String, String>();
		hf11.put("merchantid", "873790");
		hf11.put("address", "湖北");
		hf11.put("merchantName", "扬州润佳欣生态家居商贸有限公司");// ##商户名称

		//loadAccount(new Map[] { hf1, hf2, hf4, hf5, hf6, hf7, hf9, hf11 }, hfpay);
		loadAccount(new Map[] { hf1,hf1,hf1,hf1,hf1,hf1,hf1,hf1,
				hf1,hf1,hf1,hf1,hf1,hf1,hf1,hf1,
				hf1,hf1,hf1,hf1,hf1,hf1,hf1,hf1,
				hf1,hf1,hf1,hf1,hf1,hf1,hf1,hf1
		}, hfpay);
//		loadAccount(new Map[] { hf1, hf2, hf3, hf4, hf5, hf6, hf7, hf8, hf9, hf10 ,hf11 }, hfpay);


		Map<String, String> hfqe1 = new HashMap<String, String>();
		hfqe1.put("merchantid", "873369");
		hfqe1.put("merchantName", "南京内方外圆保健品贸易有限公司");// ##商户名称


		Map<String, String> hfqe2 = new HashMap<String, String>();
		hfqe2.put("merchantid", "873602");
		hfqe2.put("merchantName", "重庆市持续商贸有限公司");// ##商户名称
		Map<String, String> hfqe3 = new HashMap<String, String>();
		hfqe3.put("merchantid", "873603");
		hfqe3.put("merchantName", "济南浩雪菲阳商贸有限公司");// ##商户名称
		Map<String, String> hfqe4 = new HashMap<String, String>();
		hfqe4.put("merchantid", "873706");
		hfqe4.put("merchantName", "重庆市洁聚商贸有限公司");// ##商户名称
		Map<String, String> hfqe5 = new HashMap<String, String>();
		hfqe5.put("merchantid", "873802");
		hfqe5.put("merchantName", "宁波鸿彖德贸易有限公司");// ##商户名称
		Map<String, String> hfqe6 = new HashMap<String, String>();
		hfqe6.put("merchantid", "873666");
		hfqe6.put("merchantName", "重庆市小龙女商贸有限公司");// ##商户名称
		Map<String, String> hfqe7 = new HashMap<String, String>();
		hfqe7.put("merchantid", "873679");
		hfqe7.put("merchantName", "南京齐满点存保健品贸易有限公司");// ##商户名称
		Map<String, String> hfqe8 = new HashMap<String, String>();
		hfqe8.put("merchantid", "873716");
		hfqe8.put("merchantName", "大连圣峻翔商贸有限公司");// ##商户名称
		Map<String, String> hfqe9 = new HashMap<String, String>();
		hfqe9.put("merchantid", "873741");
		hfqe9.put("merchantName", "济南宝时通商贸有限公司");// ##商户名称
		Map<String, String> hfqe10 = new HashMap<String, String>();
		hfqe10.put("merchantid", "873840");
		hfqe10.put("merchantName", "上海坤脉商贸有限公司");// ##商户名称
		Map<String, String> hfqe11 = new HashMap<String, String>();
		hfqe11.put("merchantid", "873843");
		hfqe11.put("merchantName", "成都黄雁成商贸有限公司");// ##商户名称
		Map<String, String> hfqe12 = new HashMap<String, String>();
		hfqe12.put("merchantid", "873841");
		hfqe12.put("merchantName", "济南春辰商贸有限公司");// ##商户名称
		Map<String, String> hfqe13 = new HashMap<String, String>();
		hfqe13.put("merchantid", "873845");
		hfqe13.put("merchantName", "宁波晶然立和贸易有限公司");// ##商户名称
		Map<String, String> hfqe14 = new HashMap<String, String>();
		hfqe14.put("merchantid", "873844");
		hfqe14.put("merchantName", "宁波运脉贸易有限公司");// ##商户名称
		Map<String, String> hfqe15 = new HashMap<String, String>();
		hfqe15.put("merchantid", "873842");
		hfqe15.put("merchantName", "成都市沁灵贸易有限责任公司");// ##商户名称

		loadAccount(new Map[] { hfqe1, hfqe2, hfqe3, hfqe4, hfqe5, hfqe6, hfqe7, hfqe8, hfqe9, hfqe10, hfqe11, hfqe12, hfqe13, hfqe14, hfqe15});

		// -----------------------------汇付天下-------------------------------end

		// -----------------------------易宝-----------------------------------start
		Map<String, String> yb1 = new HashMap<String, String>();
		yb1.put("merchantid", "10012530568");
		yb1.put("address", "江西");
		yb1.put("merchantName", "重庆丽翔贸易有限公司");// ##商户名称
		yb1.put("password", "q9z762Qa260z35121tU9UI7668nTut1mp75WUf474jAsz09oC16w5TG89220");// ##商户名称
		Map<String, String> yb2 = new HashMap<String, String>();
		yb2.put("merchantid", "10012562338");
		yb2.put("address", "重庆");
		yb2.put("merchantName", "重庆荷亭贸易有限责任公司");// ##商户名称
		yb2.put("password", "92j716Vu3cX62b2b79z1PS787Asr392Y81B97V5Y7jw2f6690y433F9xy92U");// ##商户名称
		Map<String, String> yb3 = new HashMap<String, String>();
		yb3.put("merchantid", "10012562222");
		yb3.put("address", "新疆");
		yb3.put("merchantName", "嘉祥易祥商贸有限公司");// ##商户名称
		yb3.put("password", "4Z49WX140x30t5q01T0vT8Vol72qIM1y1Q1JFH05m8978r73Z5urtfsnVq10");// ##商户名称
		Map<String, String> yb4 = new HashMap<String, String>();
		yb4.put("merchantid", "10012562279");
		yb4.put("address", "北京");
		yb4.put("merchantName", "济南比拉迦商贸有限公司");// ##商户名称
		yb4.put("password", "2VI46B90e1Esf103ZEy8p5hK3G9Z5728476U6sV226n8T8j98kj0z4C0i2w4");// ##商户名称
		Map<String, String> yb5 = new HashMap<String, String>();
		yb5.put("merchantid", "10012562324");
		yb5.put("address", "山东");
		yb5.put("merchantName", "济宁市万维电子科技有限公司");// ##商户名称
		yb5.put("password", "JJ0r270IVF9Je58pP245tY66JL8B8lP9av555604c0MZ3s90NQ7Thn42u477");// ##商户名称
		Map<String, String> yb6 = new HashMap<String, String>();
		yb6.put("merchantid", "10012562193");
		yb6.put("address", "广东");
		yb6.put("merchantName", "济宁道和贸易有限公司");// ##商户名称
		yb6.put("password", "2vBuu845Rq84Wl33Yt8939G3oz4H087QoEl5Fv5G0B221lh001v3N49214i5");// ##商户名称
		Map<String, String> yb7 = new HashMap<String, String>();
		yb7.put("merchantid", "10012562366");
		yb7.put("address", "河南");
		yb7.put("merchantName", "烟台胜俊商贸有限公司");// ##商户名称
		yb7.put("password", "24S0kbu8jm7o50yJ8N1726pqUjjPJ6046358802lUf1g554136742p03W9o6");// ##商户名称
		Map<String, String> yb8 = new HashMap<String, String>();
		yb8.put("merchantid", "10012530614");
		yb8.put("address", "吉林");
		yb8.put("merchantName", "武汉吉康福贸易有限公司");// ##商户名称
		yb8.put("password", "4m35T3y50a02Dd6ZdGc0r7Lu865NQ4c5wJp1283f91Be7p4tr6g3L3Fh0oh0");// ##商户名称

		Map<String, String> yb9 = new HashMap<String, String>();
		yb9.put("merchantid", "10012563284");
		yb9.put("address", "陕西");
		yb9.put("merchantName", "大连欧德玛贸易有限公司");// ##商户名称
		yb9.put("password", "M1j8N7HW6gT9uU746t477495bzV23D724iV3Ij0tN42056782J1HS4F3o926");// ##商户名称
		Map<String, String> yb10 = new HashMap<String, String>();
		yb10.put("merchantid", "10012530575");
		yb10.put("address", "贵州");
		yb10.put("merchantName", "四川省智能生态家商贸有限责任公司");// ##商户名称
		yb10.put("password", "0l80Ja8V04n2YHP096y2J6ob94LiA5i7Ys41X9lyt4tl49S8BU5T545wZ5h8");// ##商户名称
		Map<String, String> yb11 = new HashMap<String, String>();
		yb11.put("merchantid", "10012530553");
		yb11.put("address", "云南");
		yb11.put("merchantName", "厦门鑫诚栢梌贸易有限公司");// ##商户名称
		yb11.put("password", "0vG6K118rBjEAfiZe5D83pn9y2qst99T8R2iSf6F7S5UQ0g22235mjtp8661");// ##商户名称

		loadAccount(new Map[] { yb1, yb8, yb9, yb10, yb11}, ybpay);
//		陕西(163728)、贵州(163725)、云南(163726)
		//Modify By WuCF 20160826 易宝新增3个省份
		// -----------------------------易宝-----------------------------------end


		// ---------------------------------------------------------------测试机器

		Map<String, String> cs1 = new HashMap<String, String>();
		cs1.put("merchantid", "baokao360");// ##商户号
		cs1.put("merchantName", "中国报考信息网");// ##商户名称
		cs1.put("merchantPath", "D:/cert/baokao360.pfx");// PayBusiness.class.getClassLoader().getResource("key/ysPay/baokao360.pfx").getPath());//
		// 商户私钥证书路径(发送交易签名使用)
		cs1.put("password", "111111");// ##商户私钥证书密码
		cs1.put("busicode", "01000010");// 业务代码（商户需申请）

		Map<String, String> cs2 = new HashMap<String, String>();
		cs2.put("merchantid", "2000120162");
		cs2.put("password", "a0db0b0f0e0c43ad1cd99231ffc3c860");

		//account.put("dqbaokao360", cs1);// 商户号(测试用)
		//account.put("shbaokao360", cs1);// 商户号(测试用)
		//account.put("sh2000120162", cs2);// 商户号(测试用)

	}

	public static PayModeService getPayModeService(String province){
		PayModeService pm = null;
		if (StringUtil.contains(PayBusiness.bypay, province)) {
			pm = new UmbPayUtilImpl2();
		}else if (StringUtil.contains(PayBusiness.yspay, province)) {
			pm = new YspayUtilImpl();
		}else if (StringUtil.contains(PayBusiness.ybpay, province)) {
			pm = new YeePayUtilImpl2();
		}else if (StringUtil.contains(PayBusiness.hfpay,province)) {
			pm = new ChinapnrUtilImpl2();
		}else if (StringUtil.contains(PayBusiness.hxpay, province)){
			pm = new HxUtilImpl();
		}
		else {
			pm = new Bill99UtilImpl2();//Modify By WuCF 20170120 只保留快钱
		}
		return pm;
	}

	private static void loadAccount(Map<String, String>[] mapAr, String[] provinces) {
		for (int i = 0; i < provinces.length; i++) {
			if(!"-9999".equals(provinces[i])){
				//account.put("sh" + mapAr[i].get("merchantid"), mapAr[i]);
				account.put("dq" + provinces[i], mapAr[i]);
			}
		}
	}
	private static void loadAccount(Map<String, String>[] mapAr) {
		for (int i = 0; i < mapAr.length; i++) {
			account.put("sh" + mapAr[i].get("merchantid"), mapAr[i]);
		}
	}


	public static void main(String[] args) {
		Map<String, Map<String, String>> map2 = PayBusiness.account;
		System.out.println(map2.size());
		for (String key : map2.keySet()) {
			System.out.println(key + "==" + map2.get(key).get("address") + ";" + map2.get(key).get("merchantid"));
		}
	}

}