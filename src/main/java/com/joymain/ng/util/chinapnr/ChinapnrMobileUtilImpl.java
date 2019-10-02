package com.joymain.ng.util.chinapnr;

import com.huifu.npay.master.domain.CfcaInfoBo;
import com.huifu.npay.master.pay.AppPayService;
import com.huifu.npay.master.util.constant.Constants;
import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.service.PayModeService;
import com.joymain.ng.util.pay.PayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChinapnrMobileUtilImpl implements PayModeService {
	private final Log log = LogFactory.getLog(super.getClass());

	@Override
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany) {
		JfiPayRetMsg retMsg = new JfiPayRetMsg();
		try {
			//取配置参数
			jfiPayOrderCompany.setBusiness(ChinapnrConstants.account.get("hfmobile"));


			//组装支付+请求参数
			Map<String, String> payParams = new HashMap<String, String>();
			payParams=setDefaultMap(payParams,jfiPayOrderCompany);

			//加解签证书参数
			CfcaInfoBo cfcaInfoBo = new CfcaInfoBo();
			cfcaInfoBo=setDefaultCfcaInfoBo(cfcaInfoBo,jfiPayOrderCompany);

			for(Object key : payParams.keySet()){
				System.out.println(key+":"+payParams.get(key));
			}
			String resultStr = AppPayService.pay(payParams,cfcaInfoBo);

			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("signMsg", "");
			if(!"验签失败".equals(resultStr)){
				resultMap.put("signMsg", resultStr);
			}
			//resultMap.put("signMsgVal", resultStr);
			retMsg.setDataMap(resultMap);

		} catch (Exception e) {
			this.log.info("=============汇付天下 mobile=============" + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return retMsg;
	}

	public String getSignMsg(Map<String, String> map) throws Exception {

		return "";
	}

	private Map<String, String> setDefaultMap(Map<String, String> payParams,JfiPayOrderCompany company) {

		Map<String, String> business = company.getBusiness();// 根据商户号获取商户对象

		payParams.put(Constants.DEVICE_INFO, "");
		payParams.put(Constants.IP_ADDR,"");
		payParams.put(Constants.LOCATION_VAL, "");
		payParams.put(Constants.BUYER_ID, company.getUserCode());
		payParams.put(Constants.EXTENSION, "");

		payParams.put(Constants.PAY_TYPE,company.getPlatformPayType());
		payParams.put(Constants.URL, ChinapnrConstants.url);
		payParams.put(Constants.MER_CUST_ID, business.get("memberCode"));
		payParams.put(Constants.ORDER_ID, company.getOrderNo());
		Date orderTime=company.getOrderTime();
		if(orderTime==null){
			orderTime=new Date(System.currentTimeMillis());
		}
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyyMMdd");
		String orderTimeStr=myFmt.format(orderTime);
		payParams.put(Constants.ORDER_DATE, orderTimeStr);
		//payParams.put(Constants.USER_CUST_ID,"");
		payParams.put(Constants.IN_CUST_ID, business.get("memberCode"));
		payParams.put(Constants.IN_ACCT_ID, business.get("inAcctId"));

		//微信方式
		if (Constants.WX_APP_PAY.equals(company.getPlatformPayType())){
				payParams.put(Constants.APP_ID, business.get("appId"));
		}
		//从selfParamInfo这个中获取支付信息
		DecimalFormat df = new DecimalFormat("0.00");
		payParams.put(Constants.TRANS_AMT, df.format(company.getPayAmount()));

		payParams.put(Constants.GOODS_DESC, company.getGoodsDesc());
		payParams.put(Constants.RET_URL, "");
		payParams.put(Constants.BG_RET_URL,PayModeService.NOTIFY_URL+"?zmType=hfmobile");
		payParams.put(Constants.MER_PRIV,PayUtils.getRemarkBean(company, new BigDecimal("0.00"), "hfmobile"));// 商户私有数据项
		return  payParams;
	}

	private CfcaInfoBo setDefaultCfcaInfoBo(CfcaInfoBo cfcaInfoBo,JfiPayOrderCompany company){

		Map<String, String> business = company.getBusiness();// 根据商户号获取商户对象
		String cerFile = this.getClass().getClassLoader().getResource(business.get("cerFile")).getPath().replaceAll("%20", " ");

		String pfxFile = this.getClass().getClassLoader().getResource(business.get("pfxFile")).getPath().replaceAll("%20", " ");
		// 解签证书
		cfcaInfoBo.setCerFile(cerFile);
		//cfcaInfoBo.setCerFile("F:\\hftest\\CFCA_ACS_TEST_OCA31.cer");
		// 加签证书
		cfcaInfoBo.setPfxFile(pfxFile);
		//cfcaInfoBo.setPfxFile("F:\\hftest\\mulan.pfx");
		// 加签密码
		cfcaInfoBo.setPfxFilePwd(business.get("password"));
		//商户ID
		cfcaInfoBo.setNpayMerId(business.get("hfMerId"));
		return cfcaInfoBo;
	}

}