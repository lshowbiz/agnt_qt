package com.joymain.ng.util.yspay;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yspay.util.DateUtil;
import yspay.util.SignUtil;
import yspay.util.base64.Base64;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;

public class YspayUtilImpl implements PayModeService {
	private final Log log = LogFactory.getLog(YspayUtilImpl.class);

	// 银盛支付请求地址   //
	public static final String POST_URL = "http://pay.ysepay.com/businessgate/yspay.do";//"http://113.106.160.201:889/businessgate/yspay.do";


	@Override
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany company) {
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
	
		Map<String, String> map = company.getBusiness();//account.get(jfiPayOrderCompany.getPayAccount()); // 根据商户号获取商户对象
		if (map == null) {
			return null;
		}
		String msgCode = "S3001";
		String msgId = DateUtil.getMsgId();
		Map<String, String> maps =map;
		maps.put("msgCode", msgCode);
		maps.put("msgId", msgId);
		maps.put("orderNo", company.getOrderNo());
		//用户编码，支付方式，支付商户号
		//maps.put("remark", PayUtils.getRemarkBean(company, new BigDecimal("0.00"), "yspay",map.get("merchantid")));
		StringBuffer sb = new StringBuffer();
		sb.append(company.getUserCode()+",").append(company.getPayType()+",yspay,").append(company.getDataType()+",").
		append(map.get("merchantid")+",").append(company.getIsFull()+",0.00");
		maps.put("remark", "["+sb+"]");
		
		
		BigDecimal payAmount = company.getPayAmount();
		DecimalFormat decimalFormat = new DecimalFormat("0");
		maps.put("amount", decimalFormat.format(payAmount.multiply(new BigDecimal(100))));// 支付金额
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		maps.put("orderBeginTime", sf.format(new Date()));// 订单发起时间
		String msg = GetXmlString(maps);
		log.info(msg);
		try {
			Map<String, String> tmp = new LinkedHashMap<String, String>(); // 有序排序 "D:/cert/baokao360.pfx"
			String path = YspayUtilImpl.class.getClassLoader().getResource(map.get("merchantPath")).getPath();//.replaceAll("%20", " ");
			path= URLDecoder.decode(path, "utf-8");
			//String path = "F:/cert/ys/cqpy.pfx";
			String check = SignUtil.signXml(msg, new File(path).getPath(), map.get("keyPassword"));
			//path = "F:/cert/ys/cqpy.pfx";
			tmp.put("src", map.get("merchantid"));// 商户号 [src域]
			tmp.put("msgCode", msgCode);// [msgCode域]	
			tmp.put("msgId", msgId);// [msgId域]
			tmp.put("check", check);// [check域]
			tmp.put("msg", new String(Base64.encode(msg.getBytes("GBK"))));// [msg域(xml报文)]
			retMsg.setUrl(POST_URL);
			retMsg.setDataMap(tmp);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return retMsg;
	}

	/**
	 * 生成报文
	 * 
	 * @param merchantName商户名称
	 * @param msgCode报文类型
	 * @param msgId
	 * @return
	 */
	private String GetXmlString(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GBK'?>");
		sb.append("<yspay>");
		sb.append("<head>");
		//版本号，[商户号]，[报文编号]，[发送时间]
		sb.append("<Ver>1.0</Ver>").append("<Src>"+map.get("merchantid")+"</Src>").append("<MsgCode>"+map.get("msgCode")+"</MsgCode><Time>"+DateUtil.getCurrentDate("yyyyMMddHHmmss")+"</Time>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<Order>");
		sb.append("   <OrderId>"+map.get("orderNo")+"</OrderId>");// 商家订单号
		sb.append("   <BusiCode>"+map.get("busicode")+"</BusiCode>"); // 业务代码（商户需申请）
		sb.append("   <ShopDate>"+DateUtil.getCurrentDate("yyyyMMdd")+"</ShopDate>");// 商户日期
		sb.append("   <Cur>CNY</Cur>");// 币种（CNY）
		sb.append("   <Amount>"+map.get("amount")+"</Amount>");// 交易金额
		sb.append("   <Note>"+map.get("remark")+"</Note>");// 订单说明
		sb.append("   <ExtraData>"+map.get("remark")+"</ExtraData>");// 附加数据
		sb.append("   <Remark>"+map.get("remark")+"</Remark>");// 备注 （原单返回商户）
		sb.append("   <Timeout>99999</Timeout>");// 订单有效时间（单位：分）
		sb.append("   <SupportCards>YY</SupportCards>");// 支持卡（为空时，全支持。第一位代表信用卡，第二位代表预付费卡。Y代表支持，N代表不支持。）
		sb.append("</Order>");
		sb.append("<Payee>");
		sb.append("   <UserCode>"+map.get("merchantid")+"</UserCode>");// 收款方用户号（收款方在银盛支付平台的用户号）
		sb.append("   <Name>"+map.get("merchantName")+"</Name>");// 收款方客户名（收款方在银盛支付平台的客户名）
		sb.append("</Payee>");
		sb.append("<Notice>");
		sb.append("   <PgUrl>"+PAGE_URL+"</PgUrl>");
		sb.append("   <BgUrl>"+NOTIFY_URL+"?zmType=yspay</BgUrl>"); 
		sb.append("</Notice>");
		sb.append("</body>");
		sb.append("</yspay>");
		return sb.toString();
	}


}
