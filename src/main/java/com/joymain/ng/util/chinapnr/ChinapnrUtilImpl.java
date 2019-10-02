package com.joymain.ng.util.chinapnr;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinapnr.SecureLink;

import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.util.yspay.PayBusiness;

@Service("chinapnrUtil")
@WebService(serviceName = "ChinapnrUtilService", endpointInterface = "com.joymain.ng.util.chinapnr.ChinapnrUtil")
public class ChinapnrUtilImpl implements ChinapnrUtil {
	private final Log log = LogFactory.getLog(super.getClass());
	private static String zsUrl = "https://mas.chinapnr.com/gar/RecvMerchant.do";
	public static final String POST_URL = zsUrl;
	public static final String BG_URL = "http://e4.jmtop.com/jecs/jpoMemberOrders/orderAll";
	public static final String NOTIFY_URL = "http://59.41.187.147:7082/jmhg_ht/jfiChinapnrReceive.html";
	public static final String subject = "1183000";

	@Autowired
	private JsysIdManager sysIdManager;

	@Autowired
	private FiCommonAddrManager fiCommonAddrManager;

	public Chinapnr getChinapnr(Chinapnr entity, int flag) {
		String userCode = "";
		if ((entity.getMerPriv() != null) && (entity.getMerPriv().length() >= 1)) {
			userCode = entity.getMerPriv().substring(1).split(",")[0];
		}

		if (flag == 0) {
			entity.setOrdId(this.sysIdManager.buildIdStr("advicecode_cn"));
			entity.setMerId(getMerchantByUserCode(userCode).get("merchantid"));
		}

		BigDecimal OrderAmount = new BigDecimal(entity.getOrdAmt());

		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		entity.setOrdAmt(decimalFormat.format(OrderAmount));
		entity.setVersion("10");
		entity.setCmdId("Buy");
		entity.setCurCode("RMB");
		entity.setPid("1183000");
		entity.setRetUrl("http://e4.jmtop.com/jecs/jpoMemberOrders/orderAll");
		entity.setBgRetUrl("http://59.41.187.147:7082/jmhg_ht/jfiChinapnrReceive.html");
		entity.setGateId("");
		entity.setUsrMp("");
		entity.setDivDetails("");
		entity.setPayUsrId("");
		entity.setChkValue(getSignMsg(entity, entity.getMerId()));
		entity.setPostUrl(POST_URL);
		return entity;
	}

	public String getSignMsg(Chinapnr entity, String merId) {
		String KEY_NAME;
		try {
			KEY_NAME = "key/hftxPay/MerPrK" + merId + ".key";

			String MerKeyFile = super.getClass().getClassLoader().getResource(KEY_NAME).getPath();

			String MerData = entity.getVersion() + entity.getCmdId() + entity.getMerId() + entity.getOrdId() + entity.getOrdAmt() + entity.getCurCode() + entity.getPid()
					+ entity.getRetUrl() + entity.getMerPriv() + entity.getGateId() + entity.getUsrMp() + entity.getDivDetails() + entity.getPayUsrId() + entity.getBgRetUrl();
			SecureLink sl = new SecureLink();
			int ret = sl.SignMsg(entity.getMerId(), MerKeyFile, MerData);
			if (ret != 0) {
				throw new UnsupportedEncodingException("签名错误 ret=" + ret);
			}
			return sl.getChkValue();
		} catch (UnsupportedEncodingException ue) {
			ue.printStackTrace();
			this.log.info("=============汇付天下ue=============" + ue.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			this.log.info("=============汇付天下e=============" + e.getMessage());
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println();
	}

	public Map<String, String> getMerchantByUserCode(String userCode) {
		FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(userCode);// 获取会员常用收货地址
		return PayBusiness.account.get(fiCommonAddr != null ? "dq"+fiCommonAddr.getProvince() : "hfDefault");
	}

	public String getMerchantIdByUserCode(String userCode) {
		FiCommonAddr fiCommonAddr = (FiCommonAddr) this.fiCommonAddrManager.get(userCode);
		if (fiCommonAddr != null) {
			if ("163713".equals(fiCommonAddr.getProvince())) {
				return "873369";
			}

			if ("163728".equals(fiCommonAddr.getProvince())) {
				return "873506";
			}

			if ("163724".equals(fiCommonAddr.getProvince())) {
				return "873488";
			}

			if ("163723".equals(fiCommonAddr.getProvince())) {
				return "873490";
			}

			if ("163711".equals(fiCommonAddr.getProvince())) {
				return "873495";
			}

			if ("163704".equals(fiCommonAddr.getProvince()))
				return "873500";
		}

		return "873360";
	}


	public void setSysIdManager(JsysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
}