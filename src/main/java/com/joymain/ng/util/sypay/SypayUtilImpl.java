package com.joymain.ng.util.sypay;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.StringUtil;

/**
 * 顺手付支付
 * 
 * @author lizg date:2015-10-29
 */
public class SypayUtilImpl implements PayModeService {
	private final Log log = LogFactory.getLog(SypayUtilImpl.class);

	// 顺手付支付请求地址 https://uat-sypay.sf-pay.com/gatprx/payment(测试地址)
	public static final String POST_URL = "https://www.sf-pay.com/gatprx/payment";// ;

	@Override
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany) {
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		Map<String, String> tmp = new LinkedHashMap<String, String>(); // 有序排序
		// 根据商户号获取商户对象account.get(jfiPayOrderCompany.getPayAccount());
		Map<String, String> map = jfiPayOrderCompany.getBusiness();//
		if (map == null) {
			return null;
		}
		tmp.put("merchantId", map.get("merchantid"));// 商户号
		tmp.put("orderId", jfiPayOrderCompany.getOrderNo());// 订单号
		BigDecimal payAmount = jfiPayOrderCompany.getPayAmount();
		DecimalFormat decimalFormat = new DecimalFormat("0");
		tmp.put("amount", decimalFormat.format(payAmount.multiply(new BigDecimal(100))));// 支付金额
		tmp.put("ccy", "RMB");// 金额币种
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		tmp.put("orderBeginTime", sf.format(new Date()));// 订单发起时间
		tmp.put("notifyUrl", NOTIFY_URL + "?zmType=sypay");// 通知URL 
		tmp.put("sign", getSignMsg(tmp, map.get("password"))); // 计算签名

		// ----协议参数
		tmp.put("serviceName", "SFP_B2C_PAY");// 接口名称
		tmp.put("serviceVersion", "V1.0.0");// 接口名称
		tmp.put("charset", "UTF-8");// 接口名称
		tmp.put("signType", "MD5");// 接口名称

		// 业务参数
		tmp.put("orderExpireTime", "");// SFP_B2C_PAY 订单失效时间
		tmp.put("orderName", "中脉商品");// 商品名称
		tmp.put("orderUrl", "");// 商品URL
		tmp.put("orderDesc", "MD5");// 商品描述
		tmp.put("reserved", "[" + jfiPayOrderCompany.getUserCode() + "," + jfiPayOrderCompany.getPayType() + "]");// 商户附加信息
		tmp.put("returnUrl", PAGE_URL);// 返回URL
		tmp.put("connBankCode", "");// 直连银行编码
		tmp.put("channelType", "");// 接口渠道类型
		tmp.put("clientIp", "");// 用户IP
		tmp.put("visa", "");// 是否VISA

		retMsg.setUrl(POST_URL);
		retMsg.setDataMap(tmp);
		return retMsg;
	}

	public String getSignMsg(Map<String, String> map, String password) {
		StringBuffer sign = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sign.append("&").append(entry.getKey()).append("=");
			if (!StringUtil.isEmpty(entry.getValue())) {
				sign.append(entry.getValue());
			}
		}
		log.info("顺手付===sign:" + sign.deleteCharAt(0) + ";加密后：" + "sign:" + SignatureUtil.sign(sign, password));
		return SignatureUtil.sign(sign, password);
	}

	public static void main(String[] args) {
		StringBuffer signSb = new StringBuffer("1234567890");
		System.out.println(signSb.deleteCharAt(0));
	}

}
