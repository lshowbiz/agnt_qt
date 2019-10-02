package com.joymain.ng.util.chinapnr;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import chinapnr.SecureLink;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.pay.PayUtils;

public class ChinapnrUtilImpl2 implements PayModeService {
	private final Log log = LogFactory.getLog(super.getClass());
	public static final String POST_URL = "https://mas.chinapnr.com/gar/RecvMerchant.do";
	public static final String BG_URL = BASE + "/jpoMemberOrders/orderAll";
//	public static final String NOTIFY_URL =BASE + "jfiChinapnrReceive.html";
	public static final String subject = "1183000";

	@Override
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany) {
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		Map<String, String> map = jfiPayOrderCompany.getBusiness();// 根据商户号获取商户对象
		try {
			Map<String, String> tmp = new LinkedHashMap<String, String>(); // 有序排序
			tmp.put("OrdId", jfiPayOrderCompany.getOrderNo());// 订单号
			tmp.put("MerId", map.get("merchantid"));// 商户号
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			tmp.put("OrdAmt", decimalFormat.format(jfiPayOrderCompany.getPayAmount()));// 订单金额
			tmp.put("Version", "10");// 版本号
			tmp.put("CmdId", "PreBuy");// 消息类型
			tmp.put("CurCode", "RMB");// 币种
			tmp.put("Pid", subject);// 商品编号
			tmp.put("RetUrl", BG_URL);// 页面返回地址
			tmp.put("BgRetUrl", NOTIFY_URL+"?zmType=hfpay");// 后台返回地址
			tmp.put("MerPriv",PayUtils.getRemarkBean(jfiPayOrderCompany, new BigDecimal("0.00"), "hfpay"));// 商户私有数据项
			tmp.put("GateId", "");// 网关号 银行 ID
			tmp.put("UsrMp", "");// 购买者手机号
			tmp.put("DivDetails", "");// 分账明细
			tmp.put("PayUsrId", "");// 付款人用户号
			tmp.put("ChkValue", getSignMsg(tmp));// 数字签名
			tmp.put("postUrl", POST_URL);// 支付跳转页面地址
			retMsg.setUrl(POST_URL);
			retMsg.setDataMap(tmp);
		} catch (Exception e) {
			this.log.info("=============汇付天下ue=============" + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return retMsg;
	}

	public String getSignMsg(Map<String, String> map) throws Exception {
		String KEY_NAME = "key/hftxPay/MerPrK" + map.get("MerId") + ".key";
		String MerKeyFile = super.getClass().getClassLoader().getResource(KEY_NAME).getPath();
		StringBuffer sb = new StringBuffer();
		sb.append(map.get("Version")).append(map.get("CmdId")).append(map.get("MerId"));
		sb.append(map.get("OrdId")).append(map.get("OrdAmt")).append(map.get("CurCode"));
		sb.append(map.get("Pid")).append(map.get("RetUrl")).append(map.get("MerPriv"));
		sb.append(map.get("GateId")).append(map.get("UsrMp")).append(map.get("DivDetails"));
		sb.append(map.get("PayUsrId")).append(map.get("BgRetUrl"));
		SecureLink sl = new SecureLink();
		int ret = sl.SignMsg(map.get("merchantid"), MerKeyFile, sb.toString());
		if (ret != 0) {
			throw new UnsupportedEncodingException("签名错误 ret=" + ret);
		}
		return sl.getChkValue();
	}

}