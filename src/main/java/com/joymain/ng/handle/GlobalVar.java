package com.joymain.ng.handle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class GlobalVar {
	
	 /**
	  * US Tax setting
	  */
	 public static Map<String,BigDecimal> taxMap=new HashMap<String, BigDecimal>();
	 /**单独购买产品列表*/
	 public static List<String> proList=new ArrayList<String>();
	 /**单独购买产品列表（9个）*/
	 public static List<String> jpoList=new ArrayList<String>();
	 /**中秋礼盒单独购买产品列表（2个）*/
	 public static List<String> zqList=new ArrayList<String>();
	 /**购买数量控制*/
	 public static List<String> proNumList=new ArrayList<String>();
	 
	 /**3財月促销**/
	 public static List<String> jpoList20160301 = new ArrayList<String>();
	 
	 /**
	  * 201512月非套餐包促销    
	  * 201601月非套餐包促销
	  */
	 public static List<String> jpoList20151201 = new ArrayList<String>();
	 
	 /**
	  * 201601财月积分换购产品(>=5:5) 201603
	  */
	 public static List<String> jpoList20160101 = new ArrayList<String>();
	 
	 /**
	  * 201601财月积分换购产品(>=5:5)
	  */
	 public static List<String> jpoList20160104 = new ArrayList<String>();
	 
	 /**
	  * 201601财月积分换购产品(>=5:5)
	  */
	 public static List<String> jpoList20160105 = new ArrayList<String>();
	 
	 /**
	  * 201601财月积分换购产品  ( 枸杞汁 >=5:5,除冯波团队外，其他团队必需使用积分)
	  */
	 public static List<String> jpoList20160102 = new ArrayList<String>();
	 
	 /**
	  * 201601财月积分换购产品 ( 旋磁椅  必须使用现金>=25000)
	  */
	 public static List<String> jpoList20160103 = new ArrayList<String>();
	 
	 /**
	  * 冯波团队
	  */
	 public static String teamCodeFB = "CN32985884";
	 
	 /**
	  * 枸杞汁，除冯波团队外，其他团队必需使用积分
	  */
	 public static String jpo20160102 = "P01310200201CN0";
	 
	 /**
	  * 韩美旅游积分产品开发
	  */
	 public static List<String> KoralTravel201601 = new ArrayList<String>();
	 //促销编码
	 public static String[] goods = new String[] { "P04060100201CN0","P04080100201CN0", "P04090100201CN0", "P01310200201CN0", "P1508010101CN0","P21571300101CN0" };
	 
	 /**
	  * 2016-05-24圣诗蔓积分换购(>=5:5) 必需使用积分
	  */
	 public static List<String> jpoList20160524 = new ArrayList<String>();
	 
	 /**
	  * 201606财月积分换购产品 ( 旋磁椅  必须使用现金>=25000)
	  */
	 public static List<String> jpoList201606xcy = new ArrayList<String>();
	 
	 /**
	  * 201606财月积分换购产品 ( 5:5)
	  */
	 public static List<String> jpoList201606 = new ArrayList<String>();
	 /**
	  * 201805财月积分换购产品(>=5:5) 必需使用积分
	  */
	 public static List<String> jpoList201805 = new ArrayList<String>();

	 /**
	  * 抗抑菌男士内裤加入购物车提示语 20160801
	  */
	 public static List<String> underwearReminder = new ArrayList<String>();
	 
	 /**
	  * 烹饪油与赠品一同发货提示语 20160825
	  */
	 public static List<String> cookingOilReminder = new ArrayList<String>();
	 

	 /**
	  * 11月份重消单不参与积分换购的产品
	  */
	 public static List<String> jpocoincxList201611 = new ArrayList<String>();
	 
	 static{
		 
		taxMap.put("20008144P01010100101EN0", new BigDecimal("0.03"));//Utah,Galaxy 
		taxMap.put("20008144P05010300101EN0", new BigDecimal("0.0625"));//Utah,Energy Cup
		taxMap.put("20008144P06010100101EN0", new BigDecimal("0.0625"));//Utah,Intense
		taxMap.put("20008104P01010100101EN0", new BigDecimal("0"));//CA,Galaxy 
		taxMap.put("20008104P05010300101EN0", new BigDecimal("0.0875"));//CA,Energy Cup
		taxMap.put("20008104P06010100101EN0", new BigDecimal("0.0875"));//CA,Intense
		taxMap.put("20008128P01010100101EN0", new BigDecimal("0"));//Nevada,Galaxy 
		taxMap.put("20008128P05010300101EN0", new BigDecimal("0.0685"));//Nevada,Energy Cup
		taxMap.put("20008128P06010100101EN0", new BigDecimal("0.0685"));//Nevada,Intense
		
		proList.add("P24010100101CN0");
		
		jpoList.add("P25060100101CN0");  // P09070100101CN0
		jpoList.add("P25060200101CN0");  // P03080100101CN0
		jpoList.add("P25060300101CN0");  // P03150100101CN0
		jpoList.add("P25060400101CN0");
		jpoList.add("P25060500101CN0");
		jpoList.add("P25070100101CN0");
		jpoList.add("P25070200101CN0");
		jpoList.add("P25070300101CN0");
		jpoList.add("P25010100101CN0");
		
		zqList.add("P23030100101CN0");
		zqList.add("P23030200101CN0");
		
		proNumList.add("P24010100101CN0");
		
//		jpoList20151201.add("P21560100101CN0");
//		jpoList20151201.add("P21560200101CN0");
//		jpoList20151201.add("P21560300101CN0");
//		jpoList20151201.add("P21560400101CN0");
//		jpoList20151201.add("P21560500101CN0");
//		jpoList20151201.add("P21560600101CN0");
//		jpoList20151201.add("P21560700101CN0");
//		jpoList20151201.add("P21560800101CN0");
//		jpoList20151201.add("P21560900101CN0");
//		jpoList20151201.add("P21561000101CN0");
//		jpoList20151201.add("P21561100101CN0");
//		jpoList20151201.add("P21561200101CN0");
//		jpoList20151201.add("P21561300101CN0");
//		jpoList20151201.add("P21561400101CN0");
		
		//201601追加
		jpoList20151201.add("P21570100101CN0");
		jpoList20151201.add("P21570200101CN0");
		jpoList20151201.add("P21570300101CN0");
		jpoList20151201.add("P21570400101CN0");
		jpoList20151201.add("P21570500101CN0");
		jpoList20151201.add("P21570600101CN0");
		jpoList20151201.add("P21570700101CN0");
		//事业部
		jpoList20151201.add("P21570800101CN0");
		jpoList20151201.add("P21570900101CN0");
		jpoList20151201.add("P21571000101CN0");
		jpoList20151201.add("P21571100101CN0");
		jpoList20151201.add("P21571200101CN0");
		
		jpoList20151201.add("P21571300101CN0"); //追加空气净化器套餐
		jpoList20151201.add("P21571400101CN0"); //追加颐萃茸聚糖压片糖果套餐包
		
		//201602追加
//		jpoList20151201.add("P21580100101CN0");
//		jpoList20151201.add("P21580200101CN0");
//		jpoList20151201.add("P21580300101CN0");
//		jpoList20151201.add("P21580400101CN0");
//		jpoList20151201.add("P21580500101CN0");
//		jpoList20151201.add("P21580600101CN0");
//		jpoList20151201.add("P21580700101CN0");
//		jpoList20151201.add("P21580800101CN0");
//		jpoList20151201.add("P21580900101CN0");
//		jpoList20151201.add("P21581000101CN0");
//		jpoList20151201.add("P21581100101CN0");
//		jpoList20151201.add("P21581200101CN0");
//		jpoList20151201.add("P21581300101CN0");
//		jpoList20151201.add("P21581400101CN0");
//		jpoList20151201.add("P21581500101CN0");
//		jpoList20151201.add("P21581600101CN0");

		//201604
//		jpoList20151201.add("P21590100101CN0");
//		jpoList20151201.add("P21590200101CN0");
//		jpoList20151201.add("P21590300101CN0");
//		jpoList20151201.add("P21590400101CN0");
//		jpoList20151201.add("P21590500101CN0");
//		jpoList20151201.add("P21590600101CN0");
//		jpoList20151201.add("P21590700101CN0");
//		jpoList20151201.add("P21590800101CN0");
//		jpoList20151201.add("P21590900101CN0");
//		jpoList20151201.add("P21591000101CN0");
//		jpoList20151201.add("P21591100101CN0");
//		jpoList20151201.add("P21591200101CN0");
//		jpoList20151201.add("P21591300101CN0");
//		jpoList20151201.add("P21591400101CN0");
//		jpoList20151201.add("P21591500101CN0");
//		jpoList20151201.add("P21591600101CN0");
//		jpoList20151201.add("P21591700101CN0");
		
		//201605
//		jpoList20151201.add("P21600100101CN0");
//		jpoList20151201.add("P21600200101CN0");
//		jpoList20151201.add("P21600300101CN0");
//		jpoList20151201.add("P21600400101CN0");
//		jpoList20151201.add("P21600500101CN0");
//		jpoList20151201.add("P21600600101CN0");
//		jpoList20151201.add("P21600700101CN0");
//		jpoList20151201.add("P21600800101CN0");
//		jpoList20151201.add("P21600900101CN0");
//		jpoList20151201.add("P21601000101CN0");
//		jpoList20151201.add("P21601100101CN0");
//		jpoList20151201.add("P21601200101CN0");
//		jpoList20151201.add("P21601300101CN0");
//		jpoList20151201.add("P21601400101CN0");
//		jpoList20151201.add("P21601500101CN0");
//		jpoList20151201.add("P21601600101CN0");

		//201606
		jpoList20151201.add("P21610100101CN0");
		jpoList20151201.add("P21610200101CN0");
		jpoList20151201.add("P21610300101CN0");
		jpoList20151201.add("P21610400101CN0");
		jpoList20151201.add("P21610500101CN0");
		jpoList20151201.add("P21610600101CN0");
		jpoList20151201.add("P21610700101CN0");
		jpoList20151201.add("P21610800101CN0");
		jpoList20151201.add("P21610900101CN0");
		jpoList20151201.add("P21611000101CN0");
		jpoList20151201.add("P21611100101CN0");
		jpoList20151201.add("P21611200101CN0");
		jpoList20151201.add("P21611300101CN0");
		jpoList20151201.add("P21611400101CN0");
		jpoList20151201.add("P21611500101CN0");
		jpoList20151201.add("P21611600101CN0");
		
		
		jpoList20151201.add("P21611700101CN0");
		jpoList20151201.add("P21611800101CN0");
		jpoList20151201.add("P21611900101CN0");
		jpoList20151201.add("P21612000101CN0");
		jpoList20151201.add("P21612100101CN0");
		jpoList20151201.add("P21612200101CN0");
		jpoList20151201.add("P21612300101CN0");
		jpoList20151201.add("P21612400101CN0");
		jpoList20151201.add("P21613200101CN0");
		jpoList20151201.add("P21613300101CN0");
		jpoList20151201.add("P21613400101CN0");
		jpoList20151201.add("P21612500101CN0");
		jpoList20151201.add("P21612600101CN0");
		jpoList20151201.add("P21612700101CN0");
		jpoList20151201.add("P21612800101CN0");
		
		jpoList20151201.add("P21613500101CN0");
		
		//201607
		jpoList20151201.add("P21620100101CN0");
		jpoList20151201.add("P21620200101CN0");
		jpoList20151201.add("P21620300101CN0");
		jpoList20151201.add("P21620400101CN0");
		jpoList20151201.add("P21620500101CN0");
		jpoList20151201.add("P21620600101CN0");
		jpoList20151201.add("P21620700101CN0");
		jpoList20151201.add("P21620800101CN0");
		jpoList20151201.add("P21620900101CN0");
		jpoList20151201.add("P21621000101CN0");
		jpoList20151201.add("P21621100101CN0");
		jpoList20151201.add("P21621200101CN0");
		jpoList20151201.add("P21621300101CN0");
		jpoList20151201.add("P21623100101CN0");
		jpoList20151201.add("P21621400101CN0");
		jpoList20151201.add("P21621500101CN0");
		jpoList20151201.add("P21621600101CN0");
		jpoList20151201.add("P21621700101CN0");
		jpoList20151201.add("P21621800101CN0");
		jpoList20151201.add("P21621900101CN0");
		jpoList20151201.add("P21622000101CN0");
		jpoList20151201.add("P21622100101CN0");
		jpoList20151201.add("P21622200101CN0");
		jpoList20151201.add("P21622300101CN0");
		jpoList20151201.add("P21622400101CN0");
		jpoList20151201.add("P21622500101CN0");
		jpoList20151201.add("P21622600101CN0");
		jpoList20151201.add("P21622700101CN0");
		jpoList20151201.add("P21622900101CN0");
		jpoList20151201.add("P21622800101CN0");
		jpoList20151201.add("P21623000101CN0");
		
		jpoList20151201.add("P21623200101CN0");
		jpoList20151201.add("P21623300101CN0");
		jpoList20151201.add("P21623400101CN0");
		jpoList20151201.add("P21623500101CN0");
		jpoList20151201.add("P21623600101CN0");


		jpoList20160101.add("P1508010101CN0"); //道和国韵1993 （1箱-体验装）
		jpoList20160101.add("P04080100201CN0");//3D云舒床垫
		jpoList20160101.add("P04090100201CN0");//3D云舒床垫
		jpoList20160101.add("P03190200101CN0");// 3月云南颐爱保养贴
		
		jpoList20160102.add("P01310200201CN0"); //枸杞汁
		
		jpoList20160103.add("P04060100201CN0");// 旋磁椅
		
		KoralTravel201601.add("N07030100101CN0");
		
		jpoList20160301.add("P03190200101CN0");
		
		
		jpoList20160524.add("P06160100101CN0");
		jpoList20160524.add("P06150100101CN0");
		jpoList20160524.add("P06120100101CN0");
		jpoList20160524.add("P06130100101CN0");
		jpoList20160524.add("N03570100101CN0");
		
		
		jpoList201606xcy.add("P04060100201CN0");// 旋磁椅
		jpoList201606.add("P04090100201CN0");//	【积分换购】中脉远红3D云舒床垫(1.5*1.9m)	
		jpoList201606.add("P04080100201CN0");//	【积分换购】中脉远红3D云舒床垫(1.8*2.0m)	
		jpoList201606.add("P01110700201CN0"); //	【积分换购】有乐米之力糙米米粉	
		jpoList201606.add("P01170100402CN0"); //	【积分换购】Cellight单细胞海藻（粉剂）	
		jpoList201606.add("P08420300401CN0"); //	【积分换购】有乐混合果蔬汁饮料（4瓶装）	
		jpoList201606.add("P16330500201CN0"); //	【积分换购】颐丽臻皙套装	
		jpoList201606.add("P16330400201CN0"); //	【积分换购】颐丽柔皙洁颜粉	
		jpoList201606.add("P16330300201CN0"); //	【积分换购】颐丽妍皙精华液	
		jpoList201606.add("P16330200301CN0"); //	【积分换购】颐丽凝皙生物纤维面膜	
		jpoList201606.add("P16330100301CN0"); //	【积分换购】颐丽润皙面膜	
		jpoList201606.add("P03080100201CN0"); //	【积分换购】颐佳食物蔬果洁净乳	
		jpoList201606.add("P03090100201CN0"); //	【积分换购】颐佳餐具洁净乳	
		jpoList201606.add("P03100100201CN0"); //	【积分换购】颐佳多功能环境洁净乳	
		jpoList201606.add("P03110100201CN0"); //	【积分换购】颐佳衣物洁净乳	
		jpoList201606.add("P03170100201CN0"); //	【积分换购】家用装颐佳漱爽露	
		jpoList201606.add("P03210100201CN0"); //	【积分换购】颐佳舒爽露便携装	
		jpoList201606.add("P01280100201CN0"); //	【积分换购】颐萃久久延年烹饪油（500ml*2瓶）	
		jpoList201606.add("P01290100201CN0"); //	【积分换购】颐萃久久延年烹饪油礼盒（500ml*6瓶）	
		jpoList201606.add("P1508010101CN0"); //	【积分换购】道和国韵1993 （1箱-体验装）只限积分换购	
		jpoList201606.add("P30070100201CN0"); //	【积分换购】West bend 唯食帮套装	
		jpoList201606.add("P15110200101CN0"); //	接市场部OA，道和国韵系列调整价格PV、包装，旧编码将不再销售使用，目前新包装编码已新建完毕。		新编码产品将于25日开放销售，其中【P15110200101CN0道和国韵1993（白瓷瓶-1箱装）】将继续用于积分换购销售，请IT同事按积分换购产品开发，谢谢。

		jpoList201805.add("P15110100102CN0"); //	【积分换购】道和国韵1993（黑瓷瓶-1箱装）	
		
		underwearReminder.add("P02130100302CN0");
		underwearReminder.add("P02130100102CN0");
		underwearReminder.add("P02130100202CN0");
		underwearReminder.add("P02130100302CN0");
		underwearReminder.add("P02130100402CN0");
		underwearReminder.add("P02130100502CN0");
		
		cookingOilReminder.add("P01280100101CN0");
		cookingOilReminder.add("P01290100101CN0"); 
		

		jpocoincxList201611.add("P16324900201CN0");
		jpocoincxList201611.add("P16324900101CN0");
		jpocoincxList201611.add("P16324800401CN0");
		jpocoincxList201611.add("P16324800301CN0");
		jpocoincxList201611.add("P16324800201CN0");
		jpocoincxList201611.add("P16324700101CN0");
		jpocoincxList201611.add("P16324600101CN0");
		jpocoincxList201611.add("P16324500101CN0");
		jpocoincxList201611.add("P16324400101CN0");
		jpocoincxList201611.add("P16324300101CN0");
		jpocoincxList201611.add("P16324200101CN0");
		jpocoincxList201611.add("P16324100101CN0");
		jpocoincxList201611.add("P16323900101CN0");
		jpocoincxList201611.add("P16323800101CN0");
		jpocoincxList201611.add("P16323700101CN0");
		jpocoincxList201611.add("P16323600101CN0");
		jpocoincxList201611.add("P16323500101CN0");
		jpocoincxList201611.add("P16321900101CN0");
		jpocoincxList201611.add("P16321800101CN0");
		jpocoincxList201611.add("P16321600101CN0");
		jpocoincxList201611.add("P16321500101CN0");
		jpocoincxList201611.add("P16321400101CN0");
		jpocoincxList201611.add("P16321300101CN0");
		jpocoincxList201611.add("P16321200101CN0");
		jpocoincxList201611.add("P16320900101CN0");
		jpocoincxList201611.add("P16320800101CN0");
		jpocoincxList201611.add("P16320700101CN0");
		jpocoincxList201611.add("P16320600101CN0");
		jpocoincxList201611.add("P16320500101CN0");
		jpocoincxList201611.add("P16320400101CN0");
		jpocoincxList201611.add("P16320300101CN0");
		jpocoincxList201611.add("P16320200101CN0");
		jpocoincxList201611.add("P16320100101CN0");
		jpocoincxList201611.add("P01390100101CN0");
		jpocoincxList201611.add("P31010100201CN0");
		jpocoincxList201611.add("P31010100101CN0");
		jpocoincxList201611.add("P04060100101CN0");
		jpocoincxList201611.add("P1501010101CN0");
		jpocoincxList201611.add("P02140100101CN0");
		jpocoincxList201611.add("P02140200101CN0");
		jpocoincxList201611.add("P02140500101CN0");
		jpocoincxList201611.add("P1501010101CN0");
		jpocoincxList201611.add("P16010100101CN0");
		jpocoincxList201611.add("P16010100102CN0");
		jpocoincxList201611.add("P16010100107CN0");
		jpocoincxList201611.add("P16010100108CN0");
		jpocoincxList201611.add("P16010100109CN0");
		jpocoincxList201611.add("P16010100110CN0");
		jpocoincxList201611.add("P16010100111CN0");
		jpocoincxList201611.add("P16010100112CN0");
		jpocoincxList201611.add("P16010100113CN0");
		jpocoincxList201611.add("P16010100114CN0");
		jpocoincxList201611.add("P16010100115CN0");
		jpocoincxList201611.add("P16010100201CN0");
		jpocoincxList201611.add("P16010100202CN0");
		jpocoincxList201611.add("P16010100206CN0");
		jpocoincxList201611.add("P16010100207CN0");
		jpocoincxList201611.add("P16010100208CN0");
		jpocoincxList201611.add("P16010100209CN0");
		jpocoincxList201611.add("P16010100210CN0");
		jpocoincxList201611.add("P16010100211CN0");
		jpocoincxList201611.add("P16010100212CN0");
		jpocoincxList201611.add("P16010100213CN0");
		jpocoincxList201611.add("P16010100214CN0");
		jpocoincxList201611.add("P16010100215CN0");
		jpocoincxList201611.add("P16010100301CN0");
		jpocoincxList201611.add("P16010100302CN0");
		jpocoincxList201611.add("P16010100307CN0");
		jpocoincxList201611.add("P16010100308CN0");
		jpocoincxList201611.add("P16010100309CN0");
		jpocoincxList201611.add("P16010100310CN0");
		jpocoincxList201611.add("P16010100311CN0");
		jpocoincxList201611.add("P16010100312CN0");
		jpocoincxList201611.add("P16010100313CN0");
		jpocoincxList201611.add("P16010100314CN0");
		jpocoincxList201611.add("P16010100315CN0");
		jpocoincxList201611.add("P16010100401CN0");
		jpocoincxList201611.add("P16010100402CN0");
		jpocoincxList201611.add("P16010100406CN0");
		jpocoincxList201611.add("P16010100407CN0");
		jpocoincxList201611.add("P16010100408CN0");
		jpocoincxList201611.add("P16010100409CN0");
		jpocoincxList201611.add("P16010100410CN0");
		jpocoincxList201611.add("P16010100411CN0");
		jpocoincxList201611.add("P16010100412CN0");
		jpocoincxList201611.add("P16010100413CN0");
		jpocoincxList201611.add("P16010100414CN0");
		jpocoincxList201611.add("P16010100415CN0");
		jpocoincxList201611.add("P16010100501CN0");
		jpocoincxList201611.add("P16010100502CN0");
		jpocoincxList201611.add("P16010100507CN0");
		jpocoincxList201611.add("P16010100508CN0");
		jpocoincxList201611.add("P16010100509CN0");
		jpocoincxList201611.add("P16010100510CN0");
		jpocoincxList201611.add("P16010100511CN0");
		jpocoincxList201611.add("P16010100512CN0");
		jpocoincxList201611.add("P16010100513CN0");
		jpocoincxList201611.add("P16010100514CN0");
		jpocoincxList201611.add("P16010100515CN0");
		jpocoincxList201611.add("P16010100601CN0");
		jpocoincxList201611.add("P16010100602CN0");
		jpocoincxList201611.add("P16010100606CN0");
		jpocoincxList201611.add("P16010100607CN0");
		jpocoincxList201611.add("P16010100608CN0");
		jpocoincxList201611.add("P16010100609CN0");
		jpocoincxList201611.add("P16010100610CN0");
		jpocoincxList201611.add("P16010100611CN0");
		jpocoincxList201611.add("P16010100612CN0");
		jpocoincxList201611.add("P16010100613CN0");
		jpocoincxList201611.add("P16010100614CN0");
		jpocoincxList201611.add("P16010100615CN0");
		jpocoincxList201611.add("P16010100701CN0");
		jpocoincxList201611.add("P16010100702CN0");
		jpocoincxList201611.add("P16010100707CN0");
		jpocoincxList201611.add("P16010100708CN0");
		jpocoincxList201611.add("P16010100709CN0");
		jpocoincxList201611.add("P16010100710CN0");
		jpocoincxList201611.add("P16010100711CN0");
		jpocoincxList201611.add("P16010100712CN0");
		jpocoincxList201611.add("P16010100713CN0");
		jpocoincxList201611.add("P16010100714CN0");
		jpocoincxList201611.add("P16010100715CN0");
		jpocoincxList201611.add("P16050150134CN0");
		jpocoincxList201611.add("P16050150135CN0");
		jpocoincxList201611.add("P16050150136CN0");
		jpocoincxList201611.add("P16050150137CN0");
		jpocoincxList201611.add("P16050150138CN0");
		jpocoincxList201611.add("P16050150139CN0");
		jpocoincxList201611.add("P16050150140CN0");
		jpocoincxList201611.add("P16330900101CN0");
		jpocoincxList201611.add("P16331000101CN0");
		jpocoincxList201611.add("P16331100101CN0");

		
		//2016-11-17
		jpocoincxList201611.add("P01450100101CN0");
		jpocoincxList201611.add("P01450100201CN0");
		jpocoincxList201611.add("P15100100101CN0");
		jpocoincxList201611.add("P15100200101CN0");
		jpocoincxList201611.add("P01440100101CN0");
		jpocoincxList201611.add("P01440100201CN0");
		jpocoincxList201611.add("P16324901701CN0");
		jpocoincxList201611.add("P16324801401CN0");
		jpocoincxList201611.add("P16324901101CN0");
		jpocoincxList201611.add("P16324900801CN0");
		jpocoincxList201611.add("P16324800501CN0");
		jpocoincxList201611.add("P16324700201CN0");
		jpocoincxList201611.add("P16324800101CN0");
		jpocoincxList201611.add("P16324000101CN0");
		jpocoincxList201611.add("P16323400101CN0");
		jpocoincxList201611.add("P16323300101CN0");
		jpocoincxList201611.add("P16323200101CN0");
		jpocoincxList201611.add("P16323100101CN0");
		jpocoincxList201611.add("P16323000101CN0");
		jpocoincxList201611.add("P16322900101CN0");
		jpocoincxList201611.add("P16322800101CN0");
		jpocoincxList201611.add("P16322700101CN0");
		jpocoincxList201611.add("P16322600101CN0");
		jpocoincxList201611.add("P16322500101CN0");
		jpocoincxList201611.add("P16322400101CN0");
		jpocoincxList201611.add("P16322300101CN0");
		jpocoincxList201611.add("P16322200101CN0");
		jpocoincxList201611.add("P16322100101CN0");
		jpocoincxList201611.add("P16322000101CN0");
		jpocoincxList201611.add("P16321700101CN0");
		jpocoincxList201611.add("P16321100101CN0");
		jpocoincxList201611.add("P16321000101CN0");
		jpocoincxList201611.add("P01380100101CN0");
		jpocoincxList201611.add("P01380100201CN0");
		
		
		jpocoincxList201611.add("P16331700101CN0");
		jpocoincxList201611.add("P16331700201CN0");
		jpocoincxList201611.add("P16331700301CN0");
		jpocoincxList201611.add("P16331700401CN0");
		jpocoincxList201611.add("P16331700501CN0");
		jpocoincxList201611.add("P16331700601CN0");
		jpocoincxList201611.add("P16331700701CN0");
		jpocoincxList201611.add("P16331700801CN0");
	 }

}
