package com.joymain.ng.util.umb;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

import sun.rmi.runtime.Log;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.pay.PayUtils;

/**
 * 宝易互通
 * 
 * @author lzg
 * 
 */
public class UmbPayUtilImpl2 implements PayModeService {
	
	public static final String POST_URL = "https://www.umbpay.com/pay2_1_/paymentImplAction.do";// 请求地址
	//public static final String NOTIFY_URL = "http://ddzf.jmtop.com/jecs/jfiUmbpayReceive.html";// 后台收款入账通知地址
	//public static final String BG_URL = "http://e4.jmtop.com/jecs/jpoMemberOrders/orderAll";// 支付完成后跳转页面
	


	@Override
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany) {

		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		Map<String, String> tmp = new LinkedHashMap<String, String>();
		retMsg.setUrl(POST_URL);
		Map<String, String> map = jfiPayOrderCompany.getBusiness(); //account.get(jfiPayOrderCompany.getPayAccount()); // 根据商户号获取商户对象
		if(map == null){
			return null;
		}
		tmp.put("merchantid", map.get("merchantid"));// 商户号
		tmp.put("merorderid", jfiPayOrderCompany.getOrderNo()); // 订单号
		BigDecimal payAmount = jfiPayOrderCompany.getPayAmount();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		tmp.put("amountsum", decimalFormat.format(payAmount));// 金额
		tmp.put("subject", map.get("subject"));// 4.商品种类：可选///1143009
		tmp.put("currencytype", "01");// 5.币 种：人民币,编码--01
		tmp.put("autojump", "1");// 6.自动调转取货页面:0→不跳转;1→跳转;
		tmp.put("waittime", "0");// 7.跳转等待时间：以秒为单位，
		tmp.put("merurl", PAGE_URL);// 8.商户取货URL:支付完成，直接跳转到的页面地址
		tmp.put("informmer", "1");// 9.通知商户 0→不通知;1→通知 
		tmp.put("informurl", NOTIFY_URL+"?zmType=umbpay&");// 10.商户通知URL将订单的状态通知给商户的URL返回给后台链接地址
		tmp.put("confirm", "1");// 11.商户返回确认商户是否响应平台的确认信息：0→不返回;1→返回
		tmp.put("merbank", "empty");// 12.支付银行
		tmp.put("tradetype", "0");// 14.是否在商户端选择银行
		tmp.put("bankInput", "0");// 13.支付类型
		tmp.put("interface", "5.00");// 15.接口版本
		tmp.put("bankcardtype", "00");// 16.支付银行卡类型
		tmp.put("pdtdetailurl", NOTIFY_URL+"?zmType=umbpay&");// 17.商品描述地址
		tmp.put("mac", getSignMsg(tmp, map.get("password")));// 18.加密数据
		tmp.put("pdtdnm", "中脉商品");// 20.商品描述
//		tmp.put("remark", "");//PayUtils.getRemarkBean(jfiPayOrderCompany,  new BigDecimal("0.00"), "umbpay"));
		tmp.put("remark", "[" + PayUtils.getRemarkBean2(jfiPayOrderCompany, new BigDecimal("0.00"), "umbpay", map.get("merchantid")).toArrayString() + "]");
		retMsg.setDataMap(tmp); 
		return retMsg;
	}

	public String getSignMsg(Map<String, String> map, String password) {
		StringBuffer signSb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			signSb.append("&").append(entry.getKey()).append("=");
			if (!StringUtil.isEmpty(entry.getValue())) {
				signSb.append(entry.getValue());
			}
		}
		signSb.append("&merkey=").append(password);
		System.out.println("====sign:" + signSb + ";加密后：" + "sign:" + Crypto.GetMessageDigest(Crypto.GetMessageDigest(signSb.toString().substring(1))));
		// plain=new String(signSb.toString().substring(1).getBytes("UTF-8"));
		return Crypto.GetMessageDigest(signSb.toString().substring(1));
	}
	
	
	/**
	 * 生成MD5签名串
	 * @param umbPay
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getSignMsg1(Map<String, String> map, String password) {
		 //拼接加密的源字符串
		StringBuffer signSb = new StringBuffer();
		
		signSb.append("merchantid=");
		if(!StringUtil.isEmpty(map.get("merchantid"))){
			signSb.append(map.get("merchantid"));
		}
		
		signSb.append("&merorderid=");
		if(!StringUtil.isEmpty(map.get("merorderid"))){
			signSb.append(map.get("merorderid"));
		}
		
		signSb.append("&amountsum=");
		if(!StringUtil.isEmpty(map.get("amountsum"))){
			signSb.append(map.get("amountsum"));
		}
		
		signSb.append("&subject=");
		if(!StringUtil.isEmpty(map.get("subject"))){
			signSb.append(map.get("subject"));
		}
		
		signSb.append("&currencytype=");
		if(!StringUtil.isEmpty(map.get("currencytype"))){
			signSb.append(map.get("currencytype"));
		}
		
		signSb.append("&autojump=");
		if(!StringUtil.isEmpty(map.get("autojump"))){
			signSb.append(map.get("autojump"));
		}
		
		signSb.append("&waittime=");
		if(!StringUtil.isEmpty(map.get("waittime"))){
			signSb.append(map.get("waittime"));
		}
		
		signSb.append("&merurl=");
		if(!StringUtil.isEmpty(map.get("merurl"))){
			signSb.append(map.get("merurl"));
		}
		
		signSb.append("&informmer=");
		if(!StringUtil.isEmpty(map.get("informmer"))){
			signSb.append(map.get("informmer"));
		}
		
		signSb.append("&informurl=");
		if(!StringUtil.isEmpty(map.get("informurl"))){
			signSb.append(map.get("informurl"));
		}
		
		signSb.append("&confirm=");
		if(!StringUtil.isEmpty(map.get("confirm"))){
			signSb.append(map.get("confirm"));
		}
		
		signSb.append("&merbank=");
		if(!StringUtil.isEmpty(map.get("merbank"))){
			signSb.append(map.get("merbank"));
		}
		
		signSb.append("&tradetype=");
		if(!StringUtil.isEmpty(map.get("tradetype"))){
			signSb.append(map.get("tradetype"));
		}
		
		signSb.append("&bankInput=");
		if(!StringUtil.isEmpty(map.get("bankInput"))){
			signSb.append(map.get("bankInput"));
		}
		
		signSb.append("&interface=");
		if(!StringUtil.isEmpty(map.get("interface"))){
			signSb.append(map.get("interface"));
		}
		
		signSb.append("&bankcardtype=");
		if(!StringUtil.isEmpty(map.get("bankcardtype"))){
			signSb.append(map.get("bankcardtype"));
		}
		
		signSb.append("&pdtdetailurl=");
		if(!StringUtil.isEmpty(map.get("pdtdetailurl"))){
			signSb.append(map.get("pdtdetailurl"));
		}
		
		signSb.append("&merkey=");
		signSb.append(password);
		
		String plain = signSb.toString();//原串
		//plain=new String(plain.getBytes("UTF-8"));
		String sign=Crypto.GetMessageDigest(plain);
		System.out.println("mac_src:"+plain);
		System.out.println("mac："+sign);
		return sign;
	}

}
