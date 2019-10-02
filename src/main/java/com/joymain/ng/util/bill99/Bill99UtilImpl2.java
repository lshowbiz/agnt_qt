package com.joymain.ng.util.bill99;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.pay.PayUtils;

public class Bill99UtilImpl2 implements PayModeService {
	public static final String post_url = "https://www.99bill.com/gateway/recvMerchantInfoAction.htm";
	public static final String mob_post_url = "https://www.99bill.com/mobilegateway/recvMerchantInfoAction.htm";

	/**
	 * pc端支付配置
	 */
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany company) {
		JfiPayRetMsg retMsg = null;
		if ("1".equals(company.getDataType())) {
			retMsg = payPcHadle(company);
		} else if ("2".equals(company.getDataType())) {
		//	company.setBusiness(Bill99Constants.account.get("1019150567301"));
			company.setBusiness(Bill99Constants.account.get("1020990515501"));
			retMsg = payMobilHadle(company);
		}
		return retMsg;
	}

	/**
	 * PC端支付构建块钱请求对象
	 * @param company
	 * @return
	 */
	private JfiPayRetMsg payPcHadle(JfiPayOrderCompany company) {
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		Map<String, String> tmp = new HashMap<String, String>(getDefaultMap(company)); // 有序排序
		tmp.put("version", "v2.0");// 网关版本，固定值：v2.0,该参数必填。
		tmp.put("payType", "10-1");// 支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。 10-1：充值不能用信用卡
		if("0".equals(company.getPayType())){//充值：不能用信用卡
			tmp.put("payType", "10-1");
		}else if("1".equals(company.getPayType())){//支付什么都可以用
			tmp.put("payType", "00");
		}
		tmp.put("signMsg", getSign(tmp)); // signMsg 签名字符串不可空,生成加密签名串
		retMsg.setUrl(post_url);
		retMsg.setDataMap(tmp);
		return retMsg;
	}

	/**
	 * 手机端端支付构建块钱请求对象
	 * @param company
	 * @return
	 */
	private JfiPayRetMsg payMobilHadle(JfiPayOrderCompany company) {
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		Map<String, String> tmp = new HashMap<String, String>(getDefaultMap(company));
		tmp.put("version", "mobile1.0");// 网关版本，固定值：v2.0,该参数必填。
		tmp.put("mobileGateway", "phone");
		tmp.put("payerIdType", "3");// 支付人姓名,可以为空
		tmp.put("payerId", company.getUserCode());// 支付人姓名,可以为空
		tmp.put("payType", "21-1");// 支付方式 21表示网银，信用卡支付，21-1表示只允许网银
		String mobil_bgUrl = PayModeService.BASE+"payReceive?zmType=kqpayMobile";
		tmp.put("pageUrl", mobil_bgUrl);// 接收支付结果的页面地址，该参数一般置为空即可。
		tmp.put("bgUrl", mobil_bgUrl);// 服务器接收支付结果的后台地址，该参数务必填写，不能为空。
		tmp.put("signMsg", getSign(tmp)); // signMsg 签名字符串不可空,生成加密签名串
		tmp.put("signMsgVal", getStringToMap(tmp));
		retMsg.setUrl(mob_post_url);
		retMsg.setDataMap(tmp);
		return retMsg;

	}

	/**
	 * 构建相同属性的请求对象
	 * @param company
	 * @return
	 */
	private Map<String, String> getDefaultMap(JfiPayOrderCompany company) {
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		BigDecimal orderFee = company.getPayAmount().multiply(Bill99Constants.feeP).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal orderAmount = company.getPayAmount().add(orderFee).multiply(new BigDecimal(100));

		Map<String, String> business = company.getBusiness();// 根据商户号获取商户对象
		Map<String, String> tmp = new HashMap<String, String>(); // 有序排序
		tmp.put("inputCharset", "1");// 编码方式，1代表 UTF-8; 2 代表 GBK; 3代表
										// GB2312默认为1,该参数必填。
		tmp.put("pageUrl", PayModeService.PAGE_URL);// 接收支付结果的页面地址，该参数一般置为空即可。
		tmp.put("bgUrl", PayModeService.NOTIFY_URL+"?zmType=kqpay");// 服务器接收支付结果的后台地址，该参数务必填写，不能为空。
		tmp.put("language", "1");// 语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
		tmp.put("signType", "4");// 签名类型,该值为4，代表PKI加密方式,该参数必填。
		tmp.put("merchantAcctId", business.get("memberCode"));
		tmp.put("orderId", company.getOrderNo());
		tmp.put("orderAmount", decimalFormat.format(orderAmount));// 订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试。该参数必填。
		tmp.put("payerName", "");// 支付人姓名,可以为空。
		tmp.put("payerContactType", "1");// 支付人联系类型，1 代表电子邮件方式；2 代表手机联系方式。可以为空
		tmp.put("payerContact", "");// 支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
		tmp.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));// 订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
		tmp.put("productName", "");// 商品名称，可以为空。
		tmp.put("productNum", "");// 商品数量，可以为空。
		tmp.put("productId", "");// 商品代码，可以为空。
		tmp.put("productDesc", "");// 商品描述，可以为空。
		tmp.put("ext1", "");//PayUtils.getRemarkBean(company, orderFee, "kqpay"));
		tmp.put("ext2", PayUtils.getRemarkBean(company, orderFee, "kqpay"));// 扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
		tmp.put("bankId", "");// 银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
		tmp.put("redoFlag", "0");// 同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
		tmp.put("pid", ""); // 快钱合作伙伴的帐户号，即商户编号，可为空。
		return tmp;
	}

	
	/**
	 * 签名
	 * 
	 * @param map
	 * @return
	 */
	private String getSign(Map<String, String> map) {
		String signMsgVal = "";
		signMsgVal = appendParam(signMsgVal, "inputCharset", map.get("inputCharset"));
		signMsgVal = appendParam(signMsgVal, "pageUrl", map.get("pageUrl"));
		signMsgVal = appendParam(signMsgVal, "bgUrl", map.get("bgUrl"));
		signMsgVal = appendParam(signMsgVal, "version", map.get("version"));
		signMsgVal = appendParam(signMsgVal, "language", map.get("language"));
		signMsgVal = appendParam(signMsgVal, "signType", map.get("signType"));
		signMsgVal = appendParam(signMsgVal, "merchantAcctId", map.get("merchantAcctId"));
		signMsgVal = appendParam(signMsgVal, "payerName", map.get("payerName"));
		signMsgVal = appendParam(signMsgVal, "payerContactType", map.get("payerContactType"));
		signMsgVal = appendParam(signMsgVal, "payerContact", map.get("payerContact"));
		if (StringUtils.isNotEmpty(map.get("mobileGateway"))) { // 手机端增加字段
			signMsgVal = appendParam(signMsgVal, "payerIdType", map.get("payerIdType"));
			signMsgVal = appendParam(signMsgVal, "payerId", map.get("payerId"));
		}
		signMsgVal = appendParam(signMsgVal, "orderId", map.get("orderId"));
		signMsgVal = appendParam(signMsgVal, "orderAmount", map.get("orderAmount"));
		signMsgVal = appendParam(signMsgVal, "orderTime", map.get("orderTime"));
		signMsgVal = appendParam(signMsgVal, "productName", map.get("productName"));
		signMsgVal = appendParam(signMsgVal, "productNum", map.get("productNum"));
		signMsgVal = appendParam(signMsgVal, "productId", map.get("productId"));
		signMsgVal = appendParam(signMsgVal, "productDesc", map.get("productDesc"));
		signMsgVal = appendParam(signMsgVal, "ext1", map.get("ext1"));
		signMsgVal = appendParam(signMsgVal, "ext2", map.get("ext2"));
		signMsgVal = appendParam(signMsgVal, "payType", map.get("payType"));
		signMsgVal = appendParam(signMsgVal, "bankId", map.get("bankId"));
		signMsgVal = appendParam(signMsgVal, "redoFlag", map.get("redoFlag"));
		signMsgVal = appendParam(signMsgVal, "pid", map.get("pid"));
		if (StringUtils.isNotEmpty(map.get("mobileGateway"))) {// 手机端增加字段
			signMsgVal = appendParam(signMsgVal, "mobileGateway", map.get("mobileGateway"));
		}
		String signMsg = "";
		if (Bill99Constants.signType4.equals(map.get("signType"))) {
			PKIUtil pkiUtil = new PKIUtil();
			signMsg = pkiUtil.generateSign(signMsgVal, StringUtils.isEmpty(map.get("mobileGateway")) ? null :map.get("merchantAcctId"));// 商户注册密码
		}
		return signMsg;
	}

	// 功能函数。将变量值不为空的参数组成字符串
	private String appendParam(String returnStr, String paramId, String paramValue) {
		if (!returnStr.equals("")) {
			if (!paramValue.equals("")) {
				returnStr = returnStr + "&" + paramId + "=" + paramValue;
			}
		} else {
			if (StringUtils.isNotEmpty(paramValue)) {
				returnStr = paramId + "=" + paramValue;
			}
		}
		return returnStr;
	}

	private String appendParam2(String returnStr, String paramId, String paramValue) {
		if (!returnStr.equals("")) {
			if (StringUtils.isNotEmpty(paramId)) {
				returnStr = returnStr + "&" + paramId + "=" + paramValue;
			}
		} else {
			if (StringUtils.isNotEmpty(paramId)) {
				returnStr = paramId + "=" + paramValue;
			}
		}
		return returnStr;
	}
	
	private String getStringToMap(Map<String, String> map) {
		String signMsgVal = "";
		try {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String _val = entry.getValue();
				if (StringUtils.isNotEmpty(_val)) {
					signMsgVal = appendParam2(signMsgVal, entry.getKey(), URLEncoder.encode(entry.getValue(), "utf-8"));
				} else {
					signMsgVal = appendParam2(signMsgVal, entry.getKey(), entry.getValue());
				}

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signMsgVal;
	}

	public static void main(String[] args) {

		// [用户编号,支付类型,手续费,支付平台(1:pc,2:手机)]
		// String s =
		// "{userCode:%s,payType:%s,payFee:%s,dataType:%s,isFull:%s}";

		// System.out.println(String.format(s, new
		// String[]{"1","2","3","4","5"}));

		Map<String, String> map = new HashMap<String, String>();
		map.put("pid", "1");
		System.out.println(new Bill99UtilImpl2().getSign(map));
		System.out.println(map);
	}
}
