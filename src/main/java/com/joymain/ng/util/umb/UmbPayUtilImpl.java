package com.joymain.ng.util.umb;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.JfiChanjetLog;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.util.StringUtil;

/**
 * 宝易互通支付
 * @author Administrator
 *
 */
@Service("umbPayUtil")
@WebService(serviceName = "UmbPayUtilService", endpointInterface = "com.joymain.ng.util.umb.UmbPayUtil")
@SuppressWarnings("unused")
public class UmbPayUtilImpl implements UmbPayUtil {

	//private static String POST_URL = "http://netpay.umbpay.com.cn:8086/pay2_1_/paymentImplAction.do";//测试遗留
	
	public static final String POST_URL = "https://www.umbpay.com/pay2_1_/paymentImplAction.do";//请求地址
	public static final String NOTIFY_URL = "http://ddzf.jmtop.com/jecs/jfiUmbpayReceive.html";//后台收款入账通知地址
	//public static final String NOTIFY_URL = "http://test.joylifeglobal.net/jecsnew_ht/jfiUmbpayReceive.html";//后台收款入账通知地址
	public static final String BG_URL = "http://e4.jmtop.com/jecs/jpoMemberOrders/orderAll";//支付完成后跳转页面
	
	@Autowired
    private JsysIdManager sysIdManager;//生成ID号
	
	@Autowired
	private FiCommonAddrManager fiCommonAddrManager;//常用收货地址管理
	
//	@Autowired
//    private FiBillAccountService fiBillAccountService;//获取快钱商户号接口
	
	public UmbPay getUmbPay(UmbPay umbPay, int flag)
			throws UnsupportedEncodingException {
		
		
		//1.在线充值的时候，生成充值单号
		if(flag==0){
			umbPay.setMerorderid(sysIdManager.buildIdStr("advicecode_cn"));
		}
		
		//2.根据会员编号获取商户号
		String userCode = "";
		if(umbPay.getRemark()!=null && umbPay.getRemark().length()>=1){
			 userCode = umbPay.getRemark().substring(1).split(",")[0];
		}
		
		
		Map account = getAccountMap(userCode);
		
		
		umbPay.setMerchantid(account.get("merchantid").toString());//商户号

		//3.对付款金额进行单位换算，以分为单位传递给畅捷通
		BigDecimal OrderAmount = new BigDecimal(umbPay.getAmountsum());//.multiply(new BigDecimal(100));
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		umbPay.setAmountsum(decimalFormat.format(OrderAmount));//金额
		
		umbPay.setSubject(account.get("subject").toString());//4.商品种类：可选  ///1143009
		
		umbPay.setCurrencytype("01");//5.币  种：人民币,编码--01
		umbPay.setAutojump("1");//6.自动调转取货页面:0→不跳转；1→跳转；
		umbPay.setWaittime("0");//7.跳转等待时间：以秒为单位，
		umbPay.setMerurl(BG_URL);//8.商户取货URL:支付完成，直接跳转到的页面地址  返回给前台会员中心地址
		umbPay.setInformmer("1");//9.通知商户 0→不通知；1→通知
		umbPay.setInformurl(NOTIFY_URL);//10.商户通知URL 将订单的状态通知给商户的URL 返回给后台链接地址
		umbPay.setConfirm("1");//11.商户返回确认  商户是否响应平台的确认信息：0→不返回；1→返回
		umbPay.setMerbank("empty");//12.支付银行
		umbPay.setBankInput("0");//14.是否在商户端选择银行
		umbPay.setTradetype("0");//13.支付类型
		umbPay.setInterFace("5.00");//15.接口版本
		umbPay.setBankcardtype("00");//16.支付银行卡类型
		umbPay.setPdtdetailurl(NOTIFY_URL);//17.商品描述地址 
		//umbPay.setRemark("");//19.备  注
		umbPay.setPdtdnm("中脉商品");//20.商品描述 
		umbPay.setPostUrl(POST_URL);//支付跳转页面
		umbPay.setMac(this.getSignMsg(umbPay, account.get("password").toString()));;//18.加密数据
		return umbPay;
	}

	public Map getAccountMap(String userCode){
		
		//获取会员常用收货地址
		FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(userCode);
		
		//如果常用收货地址为空
		if(fiCommonAddr==null || ("").equals(fiCommonAddr.getProvince())){
			
			return UmbConstants.account.get("null");
		}
				
		return UmbConstants.account.get(fiCommonAddr.getProvince());
	}
	
	/**
	 * 生成MD5签名串
	 * @param umbPay
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getSignMsg(UmbPay umbPay, String password) throws UnsupportedEncodingException{
		 //拼接加密的源字符串
		StringBuffer signSb = new StringBuffer();
		
		signSb.append("merchantid=");
		if(!StringUtil.isEmpty(umbPay.getMerchantid())){
			signSb.append(umbPay.getMerchantid());
		}
		
		signSb.append("&merorderid=");
		if(!StringUtil.isEmpty(umbPay.getMerorderid())){
			signSb.append(umbPay.getMerorderid());
		}
		
		signSb.append("&amountsum=");
		if(!StringUtil.isEmpty(umbPay.getAmountsum())){
			signSb.append(umbPay.getAmountsum());
		}
		
		signSb.append("&subject=");
		if(!StringUtil.isEmpty(umbPay.getSubject())){
			signSb.append(umbPay.getSubject());
		}
		
		signSb.append("&currencytype=");
		if(!StringUtil.isEmpty(umbPay.getCurrencytype())){
			signSb.append(umbPay.getCurrencytype());
		}
		
		signSb.append("&autojump=");
		if(!StringUtil.isEmpty(umbPay.getAutojump())){
			signSb.append(umbPay.getAutojump());
		}
		
		signSb.append("&waittime=");
		if(!StringUtil.isEmpty(umbPay.getWaittime())){
			signSb.append(umbPay.getWaittime());
		}
		
		signSb.append("&merurl=");
		if(!StringUtil.isEmpty(umbPay.getMerurl())){
			signSb.append(umbPay.getMerurl());
		}
		
		signSb.append("&informmer=");
		if(!StringUtil.isEmpty(umbPay.getInformmer())){
			signSb.append(umbPay.getInformmer());
		}
		
		signSb.append("&informurl=");
		if(!StringUtil.isEmpty(umbPay.getInformurl())){
			signSb.append(umbPay.getInformurl());
		}
		
		signSb.append("&confirm=");
		if(!StringUtil.isEmpty(umbPay.getConfirm())){
			signSb.append(umbPay.getConfirm());
		}
		
		signSb.append("&merbank=");
		if(!StringUtil.isEmpty(umbPay.getMerbank())){
			signSb.append(umbPay.getMerbank());
		}
		
		signSb.append("&tradetype=");
		if(!StringUtil.isEmpty(umbPay.getTradetype())){
			signSb.append(umbPay.getTradetype());
		}
		
		signSb.append("&bankInput=");
		if(!StringUtil.isEmpty(umbPay.getBankInput())){
			signSb.append(umbPay.getBankInput());
		}
		
		signSb.append("&interface=");
		if(!StringUtil.isEmpty(umbPay.getInterFace())){
			signSb.append(umbPay.getInterFace());
		}
		
		signSb.append("&bankcardtype=");
		if(!StringUtil.isEmpty(umbPay.getBankcardtype())){
			signSb.append(umbPay.getBankcardtype());
		}
		
		signSb.append("&pdtdetailurl=");
		if(!StringUtil.isEmpty(umbPay.getPdtdetailurl())){
			signSb.append(umbPay.getPdtdetailurl());
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
	
	public JfiChanjetLog getJfiChanjetLog(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
