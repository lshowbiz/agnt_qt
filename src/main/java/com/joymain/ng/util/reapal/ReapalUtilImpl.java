package com.joymain.ng.util.reapal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JfiChanjetLogManager;
import com.joymain.ng.service.JsysIdManager;

/**
 * 融宝支付
 * @author Administrator
 *
 */
@Service("reapalUtil")
@WebService(serviceName = "ReapalUtilService", endpointInterface = "com.joymain.ng.util.reapal.ReapalUtil")
public class ReapalUtilImpl implements ReapalUtil {

	//交易安全检验码，由数字和字母组成的32位字符串
	public static String key = "4ca781f941bfccb591285b70a3g000c99bd0586dce0d4375ba279f8a7gd85571";
	
	//签约融宝支付账号或卖家收款融宝支付帐户
	public static String seller_email = "604258288@qq.com";
		
	//notify_url 交易过程中服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	//Modify By WuCF 20151019 测试地址
	public static String notify_url = "http://59.188.183.199:8082/jmhg_ht/jfiReapalPayReceive.html";
	
	//付完款后跳转的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	public static String return_url = "http://test.reapal.com/return_url.jsp";
	
	//收款方名称，如：公司名称、网站名称、收款人姓名等
	public static String mainname = "融宝支付";
	
	//接口服务名称，目前固定值：online_pay（网上支付）
	public static String service="online_pay";
	
	
	//支付类型，目前固定值：1
	public static String payment_type="1";
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	//支付提交地址
	//public static String rongpay_url="http://192.168.0.58:18170/portal";
	public static String rongpay_url="https://epay.reapal.com/portal";
	//返回验证订单地址
	public static String verify_url="http://interface.reapal.com/verify/notify?";
	//public static String verify_url="http://192.168.0.79:8080/mapi/verify/notify?";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String charset = "GBK";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	
	//访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
	public static String transport = "http";
	
	@Autowired
    private JsysIdManager sysIdManager;//生成ID号
	
	@Autowired
    private FiCommonAddrManager fiCommonAddrManager;//常用收货地址管理

	/**
	 * 商户号
	 * @return
	 */
	public String getMerId(){
		
		return "100000000001486";
	}
	
	/**
	 * 获取融宝支付实体bean
	 */
	@Override
	public Reapal getReapal(Reapal reapal, int flag) {
	
		reapal.setService("online_pay");//online_pay，表示网上支付
		reapal.setPost_url(rongpay_url);
		reapal.setMerchant_ID(this.getMerId());
		reapal.setPayment_type(payment_type);
		reapal.setSeller_email(seller_email);
		reapal.setReturn_url(return_url);
		reapal.setNotify_url(notify_url);		
		reapal.setKey(key);
		reapal.setSign_type(sign_type);
				
		if(flag==0){
			reapal.setOrder_no(sysIdManager.buildIdStr("advicecode_cn"));//商户订单号
		}
		
		//reapal.setTitle(title);
		reapal.setBody(reapal.getBody() + "," + String.valueOf(flag));
//		reapal.setTotal_fee(total_fee);
//		reapal.setBuyer_email(buyer_email);
//		reapal.setPaymethod(paymethod);
		
		reapal.setSign(BuildForm(reapal));
		
		return reapal;
	}
	
	public static String BuildForm(Reapal reapal){
		
		Map sPara = new HashMap();
		
		sPara.put("service",service);
		sPara.put("payment_type",payment_type);
		sPara.put("merchant_ID", reapal.getMerchant_ID());
		sPara.put("seller_email", seller_email);
		sPara.put("return_url", return_url);
		sPara.put("notify_url", notify_url);
		sPara.put("charset", charset);
		sPara.put("order_no", reapal.getOrder_no());
		sPara.put("title", reapal.getTitle());
		sPara.put("body", reapal.getBody());
		sPara.put("total_fee", reapal.getTotal_fee());
		sPara.put("buyer_email", reapal.getBuyer_email());
		sPara.put("paymethod", reapal.getPaymethod());
		sPara.put("defaultbank", reapal.getDefaultbank());
				
		String mysign = BuildMysign(sPara, key);//生成签名结果
		return mysign;
	}
	
	public static String BuildMysign(Map sArray, String key) {
		if(sArray!=null && sArray.size()>0){
			StringBuilder prestr = CreateLinkString(sArray);  //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
			return Md5Encrypt.md5(prestr.append(key).toString());//把拼接后的字符串再与安全校验码直接连接起来,并且生成加密串
		}
		return null;
	}
	
	/** 
	 * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static StringBuilder CreateLinkString(Map params){
			List keys = new ArrayList(params.keySet());
			Collections.sort(keys);
	
			StringBuilder prestr = new StringBuilder();
			String key="";
			String value="";
			for (int i = 0; i < keys.size(); i++) {
				key=(String) keys.get(i);
				value = (String) params.get(key);
				if("".equals(value) || value == null || 
						key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
					continue;
				}
				prestr.append(key).append("=").append(value).append("&");
			}
			return prestr.deleteCharAt(prestr.length()-1);
	}
}
