package com.joymain.ng.util.huanxun;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.bill99.Bill99Constants;
import com.joymain.ng.util.chanjet.Md5;
import com.joymain.ng.util.pay.RemarkBean;

public class HxUtilImpl implements PayModeService {
	public static final String post_url = "https://newpay.ips.com.cn/psfp-entry/gateway/payment.do";
	
	@Override
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany company){
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		retMsg.setUrl(post_url);
		retMsg.setXmlStr(getHx(company));
		retMsg.setIsHxPay(true);
		return retMsg;
	}
	/**
	 * 构建相同属性的请求对象
	 * @param company
	 * @return
	 */
	private String getHx(JfiPayOrderCompany company) {
		Map<String, String> map = company.getBusiness();// 根据商户号获取商户对象
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		BigDecimal orderFee = company.getPayAmount().multiply(Bill99Constants.feeP).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal orderAmount = company.getPayAmount().add(orderFee).multiply(new BigDecimal(100));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	
		Document document = DocumentHelper.createDocument();  
		//ips的root标签
		Element ips=document.addElement("Ips");     
		Element gateWayReq=ips.addElement("GateWayReq"); 
		//二级head头部文件范围begin
		Element head= gateWayReq.addElement("head");     
		Element version=head.addElement("Version");     
		version.setText("v1.0.0");     
		Element merCode=head.addElement("MerCode");  
		merCode.setText(map.get("merchantid"));//商户号
		Element merName=head.addElement("MerName");     
		Element account=head.addElement("Account");
		account.setText(map.get("account"));
		Element msgId=head.addElement("MsgId");     
		Element reqDate=head.addElement("ReqDate");     
		reqDate.setText(format.format(new Date()));
		Element signature=head.addElement("Signature");  
		//封装body的参数并md5后设值入signature
		//二级head头部文件范围end
		//二级body文件范围begin
		format = new SimpleDateFormat("yyyyMMdd");
		Element body= gateWayReq.addElement("body");
		Element merBillNo=body.addElement("MerBillNo");     
		merBillNo.setText(company.getOrderNo());//商户订单号
		Element amount=body.addElement("Amount");    
		amount.setText(String.valueOf(company.getPayAmount()));//交易金额，待定，查看传递的数据格式，要保留两位小数;
		Element date=body.addElement("Date");  
		date.setText(format.format(company.getOrderTime()));//订单交易时间
		Element currencyType=body.addElement("CurrencyType");    
		currencyType.setText("156");//rmb
		Element gatewayType=body.addElement("GatewayType");     
		gatewayType.setText("01");//借记卡
		Element lang=body.addElement("Lang");   
		lang.setText("GB");//中文
		Element merchanturl=body.addElement("Merchanturl");  
		merchanturl.addText(company.getBackUrl() + "/jpoMemberOrders/orderAll");
		Element failUrl=body.addElement("FailUrl");   
		Element attach=body.addElement("Attach");   //自定义参数
		//封装自定义参数
		RemarkBean remarkBean = new RemarkBean();
		remarkBean.setUserCode(company.getUserCode());
		remarkBean.setPayType(company.getPayType());
		remarkBean.setPayFee(company.getPayAmount());
		remarkBean.setMerchantId(map.get("merchantid"));
		JSONObject jsonObj = JSONObject.fromObject(remarkBean);
		attach.addText(jsonObj.toString());
		Element orderEncodeType=body.addElement("OrderEncodeType");   
		orderEncodeType.setText("5");//md5
		Element retEncodeType=body.addElement("RetEncodeType");   
		retEncodeType.setText("17");//return md5
		Element retType=body.addElement("RetType");   
		retType.setText("1");//s2s
		Element serverUrl=body.addElement("ServerUrl");   
		serverUrl.setText(Bill99Constants.bgUrl + "?zmType=ipspay");//待定，测试时用测试机地址
		Element billEXP=body.addElement("BillEXP");   
		Element goodsName=body.addElement("GoodsName");   
		goodsName.setText("0001");
		Element isCredit=body.addElement("IsCredit"); 
		isCredit.setText("0");//非直连
		Element bankCode=body.addElement("BankCode");   
		Element productType=body.addElement("ProductType");   
		productType.setText("1");//个人
		String str = body.asXML();	
//		System.out.println(str);
		//加密
		str += map.get("merchantid") + map.get("password");
//		System.out.println(str);
		String signStr = Md5.encodeMD5(str); 
//		String str = "<body><MerBillNo>16544979</MerBillNo><Amount>667.0</Amount><Date>20160825</Date><CurrencyType>156</CurrencyType><GatewayType>01</GatewayType><Lang>GB</Lang><Merchanturl>http://www.baidu.com</Merchanturl><FailUrl/><Attach/><OrderEncodeType>5</OrderEncodeType><RetEncodeType>17</RetEncodeType><RetType>1</RetType><ServerUrl>http://59.41.187.148:8085/jecs_wbht/jfipayReceiveController.html</ServerUrl><BillEXP/><GoodsName>0001</GoodsName><IsCredit>0</IsCredit><BankCode/><ProductType>1</ProductType></body>";
//		str = str + "181513" + "PAAGVkfRryXs6hlzDZPByve98vGtfQRvLzRzFN8YodLXppoa8YXPtuxUVJK20cUxjZvjVjo7kQu03eC0aQNe3AwWtztYGjFuLO8dMvFMFKN8dYfRS9jef7476X3k8uEJ";
//		String signStr=StringUtil.encodePassword(str.toString(), "md5");
		signature.setText(signStr);
		return ips.asXML();
	}
	
	public static void main(String args[])
	{
		String str = "<body><MerBillNo>16544994</MerBillNo><CurrencyType>156</CurrencyType><Amount>0.01</Amount><Date>20160829</Date><Status>Y</Status><Msg><![CDATA[支付成功！]]></Msg><Attach><![CDATA[{\"dataType\":\"1\",\"isFull\":false,\"merchantId\":\"181513\",\"payFee\":0.01,\"payType\":\"1\",\"userCode\":\"CN22758105\",\"zmType\":\"\"}]]></Attach><IpsBillNo>BO20160829182158051632</IpsBillNo><IpsTradeNo>2016082906081586297</IpsTradeNo><RetEncodeType>17</RetEncodeType><BankBillNo>7108887965</BankBillNo><ResultType>0</ResultType><IpsBillTime>20160829182256</IpsBillTime></body>";
		str = str + "181513" + "PAAGVkfRryXs6hlzDZPByve98vGtfQRvLzRzFN8YodLXppoa8YXPtuxUVJK20cUxjZvjVjo7kQu03eC0aQNe3AwWtztYGjFuLO8dMvFMFKN8dYfRS9jef7476X3k8uEJ";
		System.out.println(str);
		
		System.out.println(str.toLowerCase());
		String sign=Md5.encodeMD5(str);//Md5.encodeMD5(str);
		SimpleDateFormat  format = new SimpleDateFormat("yyyyMMddHHmmss");
//		format.format(new Date());
//		System.out.println(format.format(new Date()));
//		BigDecimal bgd = new BigDecimal("1");
//		BigDecimal orderFee = bgd.multiply(Bill99Constants.feeP).setScale(2, BigDecimal.ROUND_UP);
//		BigDecimal orderAmount = bgd.add(orderFee).multiply(new BigDecimal(100));
		
		System.out.println(sign);
	}

	
}
