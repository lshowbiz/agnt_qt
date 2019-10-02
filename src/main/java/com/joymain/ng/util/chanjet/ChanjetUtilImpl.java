package com.joymain.ng.util.chanjet;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.JfiChanjetLog;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JfiChanjetLogManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.util.StringUtil;
/**
 * 新奖金制度畅捷通支付工具类
 * @author Administrator
 *
 */
@Service("chanjetUtil")
@WebService(serviceName = "ChanjetUtilService", endpointInterface = "com.joymain.ng.util.chanjet.ChanjetUtil")
public class ChanjetUtilImpl implements ChanjetUtil {
	
	//正式地址：
	public static final String posturl = "http://59.151.72.11:9272/chanpay-trading/gatewayManage/zmcashier";
	public static final String notifyurl = "http://ddzf.jmtop.com/jecs/jfiPayChanjetReceive.html";//后台收款入账通知地址	
	public static final String bgurl     = "http://e4.jmtop.com/jecs/jpoMemberOrders/orderAll";//支付完成后跳转页面
	
	//测试地址：http://172.20.6.132:8080/
//	public static final String posturl = "http://123.103.9.196:9012/chanpay-trading/gatewayManage/zmcashier";
//	public static final String notifyurl = "http://test.joylifeglobal.net/jecsnew_ht/jfiPayChanjetReceive.html";//后台收款入账通知地址
//	public static final String bgurl     = "http://test.joylifeglobal.net/jecsnew_vip/jpoMemberOrders/orderAll";//支付完成后跳转页面
	 
	

	
//	public static final String bgurl = "http://test.joylifeglobal.net/jecsnew_vip/jpoMemberOrders/orderAll";//支付完成后跳转页面
	
	//测试遗留数据
//	public static final String bgurl = "http://test.joylifeglobal.net/jecsnew_vip/jpoMemberOrders/orderAll";//会员前台支付结果通知地址
//	public static final String notifyurl = "http://test.joylifeglobal.net/jecsnew_ht/jfiPayChanjetReceive.html";//后台通知地址
	//public static final String posturl = "http://59.151.72.42:2917/gateway/gatewayManage.do?action=paymentManage";//畅捷通支付请求地址
	
	
	@Autowired
    private JsysIdManager sysIdManager;//生成ID号
	
	@Autowired
	private JfiChanjetLogManager jfiChanjetLogManager;//支付记录
	
	@Autowired
    private FiCommonAddrManager fiCommonAddrManager;//常用收货地址管理
	
	/**
	 * 生成MD5签名串
	 * @param chanjet
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getSignMsg(Chanjet chanjet) throws UnsupportedEncodingException{
		

		StringBuffer signSb = new StringBuffer();
		
		signSb.append("bgUrl=");
		if(!StringUtil.isEmpty(chanjet.getBgUrl()))
			signSb.append(chanjet.getBgUrl());
		
		signSb.append("&notifyUrl=");
		if(!StringUtil.isEmpty(chanjet.getNotifyUrl()))
			signSb.append(chanjet.getNotifyUrl());
		
		signSb.append("&businessId=");
		if(!StringUtil.isEmpty(chanjet.getBusinessId()))
			signSb.append(chanjet.getBusinessId());
		
		signSb.append("&platIdtfy=");
		if(!StringUtil.isEmpty(chanjet.getPlatIdtfy()))
			signSb.append(chanjet.getPlatIdtfy());
		
		signSb.append("&merchantId=");
		if(!StringUtil.isEmpty(chanjet.getMerchantId()))
		signSb.append(chanjet.getMerchantId());
		
		signSb.append("&orderId=");
		if(!StringUtil.isEmpty(chanjet.getOrderId()))
			signSb.append(chanjet.getOrderId());
		
		signSb.append("&orderDate=");
		if(!StringUtil.isEmpty(chanjet.getOrderDate()))
			signSb.append(chanjet.getOrderDate());
		
		signSb.append("&bankType=");
		if(!StringUtil.isEmpty(chanjet.getBankType()))
			signSb.append(chanjet.getBankType());
		
		signSb.append("&payeeBankAccount=");
		if(!StringUtil.isEmpty(chanjet.getPayeeBankAccount()))
			signSb.append(chanjet.getPayeeBankAccount());
		
		signSb.append("&payeeBankType="); 
		if(!StringUtil.isEmpty(chanjet.getPayeeBankType()))
			signSb.append(chanjet.getPayeeBankType());
		
		signSb.append("&payeeBankName="); 
		if(!StringUtil.isEmpty(chanjet.getPayeeBankName()))
			signSb.append(chanjet.getPayeeBankName());
		
		signSb.append("&payeeName="); 
		if(!StringUtil.isEmpty(chanjet.getPayeeName()))
			signSb.append(chanjet.getPayeeName());
		
		signSb.append("&deviceId="); 
		if(!StringUtil.isEmpty(chanjet.getDeviceId()))
			signSb.append(chanjet.getDeviceId());
		
//		signSb.append("&payerName=");
//		if(!StringUtil.isEmpty(chanjet.getPayerName()))
//			signSb.append(chanjet.getPayerName());
		
		signSb.append("&payerCardType="); 
		if(!StringUtil.isEmpty(chanjet.getPayerCardType()))
			signSb.append(chanjet.getPayerCardType());
		
		signSb.append("&payerContactMbl="); 
		if(!StringUtil.isEmpty(chanjet.getPayerContactMbl()))
			signSb.append(chanjet.getPayerContactMbl());
		
		signSb.append("&payerContactMal="); 
		if(!StringUtil.isEmpty(chanjet.getPayerContactMal()))
			signSb.append(chanjet.getPayerContactMal());
		
		signSb.append("&orderAmount="); 
		if(!StringUtil.isEmpty(chanjet.getOrderAmount()))
			signSb.append(chanjet.getOrderAmount());
		
		signSb.append("&amtType="); 
		if(!StringUtil.isEmpty(chanjet.getAmtType()))
			signSb.append(chanjet.getAmtType());
		
		signSb.append("&orderTime="); 
		if(!StringUtil.isEmpty(chanjet.getOrderTime()))
			signSb.append(chanjet.getOrderTime());
		
		signSb.append("&expireTime="); 
		if(!StringUtil.isEmpty(chanjet.getExpireTime()))
			signSb.append(chanjet.getExpireTime());
		
		signSb.append("&goodsId="); 
		if(!StringUtil.isEmpty(chanjet.getGoodsId()))
			signSb.append(chanjet.getGoodsId());
		
		signSb.append("&productName="); 
		if(!StringUtil.isEmpty(chanjet.getProductName()))
			signSb.append(chanjet.getProductName());
		
		signSb.append("&productNum="); 
		if(!StringUtil.isEmpty(chanjet.getProductNum()))
			signSb.append(chanjet.getProductNum());
		
		signSb.append("&productDesc="); 
		if(!StringUtil.isEmpty(chanjet.getProductDesc()))
			signSb.append(chanjet.getProductDesc());
		
		signSb.append("&redoFlag="); 
		if(!StringUtil.isEmpty(chanjet.getRedoFlag()))
			signSb.append(chanjet.getRedoFlag());
		
		signSb.append("&merPriv="); 
		if(!StringUtil.isEmpty(chanjet.getMerPriv()))
			signSb.append(chanjet.getMerPriv());
		
		signSb.append("&expand="); 
		if(!StringUtil.isEmpty(chanjet.getExpand()))
			signSb.append(chanjet.getExpand());
		
		signSb.append("&expand2="); 
		if(!StringUtil.isEmpty(chanjet.getExpand2()))
			signSb.append(chanjet.getExpand2());
		
		//MD5签名	
		//String key="&key=12356780Poi)(*";//MD5商户密钥
		
		String key="G08C17J21R90P3QF";//MD5商户密钥
		signSb.append("&key="+key); 
		
		String plain = signSb.toString();//这个是为了在本DEMO中显示签名原串
		
		
		System.out.println("======/////////////====="+plain);
		
		plain=new String(plain.getBytes("UTF-8"));
		
		String sign=Md5.encodeMD5(plain);
		
		System.out.println("==========="+sign);
		
		return sign;
	}
	
	/**
	 * 生成ChanjetUtil实例
	 * @param
	 * @param flag 0代表充值， 1表示订单支付，2代表公益基金订单支付
	 * @return
	 */
	@Override
	public Chanjet getChanjet(Chanjet chanjet, int flag) throws UnsupportedEncodingException {

		//在线充值的时候，生成充值单号
		if(flag==0){
			chanjet.setOrderId(sysIdManager.buildIdStr("advicecode_cn"));
		}
		
		chanjet.setMerchantId(this.getMerchantIdByUserCode(chanjet.getExpand()));//根据会员编号获取商户号

		//对付款金额进行单位换算，以分为单位传递给畅捷通
		BigDecimal OrderAmount = new BigDecimal(chanjet.getOrderAmount()).multiply(new BigDecimal(100));
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		chanjet.setOrderAmount(decimalFormat.format(OrderAmount));
		
		chanjet.setOrderDate(new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date()));
		chanjet.setOrderTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
		chanjet.setVersion("v1.0");
		chanjet.setSignType("0");//固定值：2 :机构证书签名，0: md5
		chanjet.setBgUrl(bgurl);//接收支付结果通知地址
		chanjet.setNotifyUrl(notifyurl);//后台通知地址
		chanjet.setBusinessId("00WGFK210016");//业务号,00WGFK210016（间连）
		chanjet.setPlatIdtfy("t3");//T系列哪个产品
		chanjet.setAmtType("01");//货币类型格式：01（人民币）
		chanjet.setRedoFlag("0");//0：没有支付成功，可以多次提交；1：仅1次提交；建议虚拟产品选择0
		chanjet.setExpand(chanjet.getExpand() + "," + String.valueOf(flag));
		
		chanjet.setSignMsg(this.getSignMsg(chanjet));//加密串
		
		chanjet.setPostUrl(posturl);//畅捷通支付请求地址
		
		
		
		return chanjet;
	}
	
	/**
	 * 根据会员编号获取商户号
	 * @param userCode
	 * @return
	 */
	public String getMerchantIdByUserCode(String userCode){
		
		String merchantId="CP008021";
		FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(userCode);//获取会员常用收货地址

		if(fiCommonAddr!=null){
			//163730青海,163729甘肃
			if(StringUtil.contains(new String[]{"163730","163729"}, fiCommonAddr.getProvince())){
				merchantId = "CP008033";
			}
			//163702北京
			if(("163702").equals(fiCommonAddr.getProvince())){
				
				merchantId = "CP008032";
			}
			//163716山东
			if(("163716").equals(fiCommonAddr.getProvince())){
				
				merchantId = "CP008031";
			}
			//广东
			if(("163720").equals(fiCommonAddr.getProvince())){
				
				merchantId = "CP008037";
			}
			//江苏
			if(("163711").equals(fiCommonAddr.getProvince())){
				
				merchantId = "CP008040";
			}
		}
		
		return merchantId;
	}

	/**
	 * 付款通知验签
	 * @param request
	 * @return
	 */
	@Override
	public JfiChanjetLog getJfiChanjetLog(HttpServletRequest request) {
		
		
		boolean check = this.checkJfiChanjetLog(request);
			
		JfiChanjetLog jfiChanjetLog = this.setJfiChanjetLog(request);
			
		if(check){
			//重复
			if(jfiChanjetLogManager.getJfiChanjetLogsByDealId(jfiChanjetLog.getDetailId()).size()>0){
				
				jfiChanjetLog.setReturnMsg("3");//重发数据
			}else{
				
				jfiChanjetLog.setReturnMsg("10");//验签成功
			}
		}else{
			
			jfiChanjetLog.setReturnMsg("4");//验签失败
		}
		
		jfiChanjetLog.setCreateTime(new Date());
		jfiChanjetLogManager.save(jfiChanjetLog);
		
		return jfiChanjetLog;
	}
	
	public JfiChanjetLog setJfiChanjetLog(HttpServletRequest request) {
		
		JfiChanjetLog jfiChanjetLog = new JfiChanjetLog();
		
		String[] ext2 = request.getParameter("expand").split(",");
        String userCode = ext2[0];//获取付款会员身份
        
		jfiChanjetLog.setUserCode(userCode);//会员编号
		jfiChanjetLog.setMerchantId(request.getParameter("merchantId"));//商户号
		
		jfiChanjetLog.setOrderId(request.getParameter("orderId"));//订单ID
		jfiChanjetLog.setOrderAmount(request.getParameter("orderAmount"));//商户订单金额
	    jfiChanjetLog.setOrderTime(request.getParameter("orderTime"));//商户订单提交时间,格式：yyyy-MM-ddhh:mm:ss		
        
	    jfiChanjetLog.setDetailId(request.getParameter("detailId"));//畅捷通平台流水
        jfiChanjetLog.setDetailTime(request.getParameter("detailTime"));//畅捷通平台交易时间
        jfiChanjetLog.setBankDealId(request.getParameter("bankDealId"));//银行处理流水
        jfiChanjetLog.setBankId(request.getParameter("bankId"));//银行编号

        jfiChanjetLog.setAmount(request.getParameter("amount"));//付款金额,以分为单位
        jfiChanjetLog.setAmtType(request.getParameter("amtType"));//付款币种01为人民币
        jfiChanjetLog.setPayResult(request.getParameter("payResult"));//支付结果, 00：为成功； 01：为失败；
        jfiChanjetLog.setSignMsg(request.getParameter("signMsg"));//签名字符串
        jfiChanjetLog.setErrCode(request.getParameter("errCode"));//错误代码
        jfiChanjetLog.setErrMsg(request.getParameter("errMsg"));//错误描述

        jfiChanjetLog.setVersion(request.getParameter("version"));//网关版本
        jfiChanjetLog.setSigntype(request.getParameter("signType"));//签名类型2机构证书签名, 0 MD5签名
        jfiChanjetLog.setBusinessId(request.getParameter("businessId"));//业务号
        jfiChanjetLog.setPlatidtfy(request.getParameter("platIdtfy"));//平台身份标识
        
        jfiChanjetLog.setCompanyCode("CN");//
        jfiChanjetLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());//
        jfiChanjetLog.setDataType("1");//数据来源，1：PC，2：手机终端
        jfiChanjetLog.setInc("0");//初始化为未进存折
		
		return jfiChanjetLog;
	}
	
	/**
	 * 付款通知验签
	 * @param request
	 * @return
	 */
	public boolean checkJfiChanjetLog(HttpServletRequest request){
		
    	StringBuffer signSb = new StringBuffer();
    	signSb.append("businessId="); 
    	String businessId = ""; 
    	if(request.getParameter("businessId")!=null) 
    	  businessId = request.getParameter("businessId");
    	signSb.append(businessId);  

    	signSb.append("&platIdtfy="); 
    	String platIdtfy = ""; 
    	if(request.getParameter("platIdtfy")!=null) 
    	  platIdtfy = request.getParameter("platIdtfy"); 
    	signSb.append(platIdtfy);  
    	  
    		
    	signSb.append("&merchantId="); 
    	String merchantId = ""; 
    	if(request.getParameter("merchantId")!=null) 
    	  merchantId = request.getParameter("merchantId"); 
    	signSb.append(merchantId);  
    	 
    	signSb.append("&orderId="); 
    	String orderId = ""; 
    	if(request.getParameter("orderId")!=null) 
    	  orderId = request.getParameter("orderId"); 
    	signSb.append(orderId);  
    	 
    	
    	signSb.append("&orderDate="); 
    	String orderDate = ""; 
    	if(request.getParameter("orderDate")!=null) 
    	  orderDate = request.getParameter("orderDate"); 
    	signSb.append(orderDate);  
    	 
    	
    	
    	signSb.append("&orderAmount="); 
    	String orderAmount = ""; 
    	if(request.getParameter("orderAmount")!=null) 
    	  orderAmount = request.getParameter("orderAmount");
    	signSb.append(orderAmount);  
    	
    	 
    	signSb.append("&orderTime="); 
    	String orderTime = ""; 
    	if(request.getParameter("orderTime")!=null) 
    	  orderTime = request.getParameter("orderTime");
    	signSb.append(orderTime);  
    	
    	
    	signSb.append("&expireTime="); 
    	String expireTime = ""; 
    	if(request.getParameter("expireTime")!=null) 
    	  expireTime = request.getParameter("expireTime");
    	signSb.append(expireTime);  
    	
    	
    	signSb.append("&payeeBankAccount="); 
    	String payeeBankAccount = ""; 
    	if(request.getParameter("payeeBankAccount")!=null) 
    	  payeeBankAccount = request.getParameter("payeeBankAccount");
    	signSb.append(payeeBankAccount);  
    	

    	signSb.append("&payeeBankType="); 
    	String payeeBankType = ""; 
    	if(request.getParameter("payeeBankType")!=null) 
    	  payeeBankType = request.getParameter("payeeBankType");
    	signSb.append(payeeBankType);  
    	
    	signSb.append("&payeeBankName="); 
    	String payeeBankName = ""; 
    	if(request.getParameter("payeeBankName")!=null) 
    	  payeeBankName = request.getParameter("payeeBankName");
    	signSb.append(payeeBankName);  
    	
    	signSb.append("&payeeName="); 
    	String payeeName = ""; 
    	if(request.getParameter("payeeName")!=null) 
    	  payeeName = request.getParameter("payeeName");
    	signSb.append(payeeName);  
    	 
    	
    	signSb.append("&deviceId="); 
    	String deviceId = ""; 
    	if(request.getParameter("deviceId")!=null) 
    	  deviceId = request.getParameter("deviceId");
    	signSb.append(deviceId);  
    	
    	signSb.append("&detailId="); 
    	String detailId = ""; 
    	if(request.getParameter("detailId")!=null) 
    	  detailId = request.getParameter("detailId");
    	signSb.append(detailId);  
    	
    	signSb.append("&detailTime="); 
    	String detailTime = ""; 
    	if(request.getParameter("detailTime")!=null) 
    	  detailTime = request.getParameter("detailTime");
    	signSb.append(detailTime);  
    	
    	signSb.append("&bankId="); 
    	String bankId = ""; 
    	if(request.getParameter("bankId")!=null) 
    	  bankId = request.getParameter("bankId");
    	signSb.append(bankId);  
    	
    	
    	signSb.append("&bankDealId="); 
    	String bankDealId = ""; 
    	if(request.getParameter("bankDealId")!=null) 
    	  bankDealId = request.getParameter("bankDealId");
    	signSb.append(bankDealId);  
    	
    	
    	
    	signSb.append("&amount="); 
    	String amount = ""; 
    	if(request.getParameter("amount")!=null) 
    	  amount = request.getParameter("amount");
    	signSb.append(amount);  
    	
    	 
    	
    	signSb.append("&amtType="); 
    	String amtType = ""; 
    	if(request.getParameter("amtType")!=null) 
    	  amtType = request.getParameter("amtType");
    	signSb.append(amtType);  
    	
    	signSb.append("&payResult="); 
    	String payResult = ""; 
    	if(request.getParameter("payResult")!=null) 
    	  payResult = request.getParameter("payResult");
    	signSb.append(payResult);  

    	
    	
    	signSb.append("&errCode="); 
    	String errCode = ""; 
    	if(request.getParameter("errCode")!=null) 
    	  errCode = request.getParameter("errCode");
    	signSb.append(errCode);  
    	

    	
    	signSb.append("&errMsg="); 
    	String errMsg = ""; 
    	if(request.getParameter("errMsg")!=null) 
    	  errMsg = request.getParameter("errMsg");
    	signSb.append(errMsg);  
    	
    	
    	signSb.append("&payeeBankAccount="); 
    	signSb.append(payeeBankAccount);  
    	 
    	
    	signSb.append("&payeeBankType="); 
    	signSb.append(payeeBankType);  
    	
    	signSb.append("&payeeBankName="); 
    	signSb.append(payeeBankName);  
    	
    	signSb.append("&payeeName="); 
    	signSb.append(payeeName);  
        
        String signMsg="";
    	if(request.getParameter("signMsg")!=null){
    		signMsg=request.getParameter("signMsg");
    	}
    	
    	boolean check=false;//签名状态
    	/*
    	 *
    	 *MD5加密验签*/
    	
    	String key="G08C17J21R90P3QF";//MD5商户密钥
		signSb.append("&key="+key); 
		
    	String plain=signSb.toString();
    	String sign=Md5.encodeMD5(plain);
    	//String re_sign=Md5.encodeMD5(plain);
    	check=signMsg.equals(sign);
    	
    	System.out.println("接收的MD5密文串:"+signMsg);
    	System.out.println("MD5签名串:"+sign);
    	System.out.println("接受的明文串:"+plain);
    	System.out.println("验签结果:"+check);
    	
		return check;
	}
}
