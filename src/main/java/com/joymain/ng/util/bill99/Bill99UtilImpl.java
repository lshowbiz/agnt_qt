package com.joymain.ng.util.bill99;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.Jfi99billLogManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.util.StringUtil;

@Service("bill99Util")
@WebService(serviceName = "Bill99UtilService", endpointInterface = "com.joymain.ng.util.bill99.Bill99Util")
public class Bill99UtilImpl implements Bill99Util {
	
	@Autowired
	private Jfi99billLogManager jfi99billLogManager;//快钱支付记录
	
	@Autowired
    private JsysIdManager sysIdManager;//生成ID号

    
    @Autowired
    private FiCommonAddrManager fiCommonAddrManager;//常用收货地址管理
    
   

	/**
	 * 根据会员编号获取收款快钱账户对象
	 * @param userCode 会员编号
	 * @return 快钱商户号
	 */
	public String getFiBillAccountByUserCode(String userCode){
		
		//获取会员常用收货地址
		FiCommonAddr fiCommonAddr = fiCommonAddrManager.get(userCode);
		
		//如果常用收货地址为空
		if(fiCommonAddr==null || ("").equals(fiCommonAddr.getProvince())){
			
			return Bill99Constants.account.get("null").get("memberCode");//按照旧规则
		}
		
		//按照旧规则
		return Bill99Constants.account.get(fiCommonAddr.getProvince()).get("memberCode");
		
//		if(flag==1){//订单支付全额支付
//			
//			//浙江省,试运行
//			if(("163712").equals(fiCommonAddr.getProvince())){
//				
//				FiBillAccount fiBillAccount = fiBillAccountManager.getFiBillAccountByUserCode(userCode);
//				
//				//找到浙江省的商户号
//				if(fiBillAccount!=null && !("").equals(fiBillAccount.getBillAccountCode())){
//										
//					return fiBillAccount.getBillAccountCode();
//				}else{
//										
//					//找不到，则按照旧规则
//					return Bill99Constants.account.get(fiCommonAddr.getProvince()).get("memberCode");
//				}
//			}else{
//			
//				//按照旧规则
//				return Bill99Constants.account.get(fiCommonAddr.getProvince()).get("memberCode");
//			}	
//		}else{
//			
//			//其他，如：非全额支付、充值、公益基金订单等，按照旧规则
//			return Bill99Constants.account.get(fiCommonAddr.getProvince()).get("memberCode");
//		}
	}
	
//	private String getMemberCodeByUserCode1(String userCode){
//		String memberCode = "";
//		JmiMember jmiMember = jmiMemberManager.get(userCode);
//		if(jmiMember!=null){
//			String treeIndex = jmiMember.getJmiRecommendRef().getTreeIndex();
//			Iterator ite = Bill99Constants.account.keySet().iterator();
//			while(ite.hasNext()){
//				String memberCodeTmp = (String)ite.next();
//				String treeIndexTmp = Bill99Constants.account.get(memberCodeTmp).get("treeIndex");
//				if(treeIndex.length()>=treeIndexTmp.length()){
//					String userCodeIndex = treeIndex.substring(0, treeIndexTmp.length());
//					if(userCodeIndex.toLowerCase().equals(treeIndexTmp.toLowerCase())){
//						if("".equals(memberCode)){
//							memberCode = memberCodeTmp;
//						}else{
//							if(Bill99Constants.account.get(memberCode).get("treeIndex").length()<treeIndexTmp.length()){
//								memberCode = memberCodeTmp;
//							}
//						}
//					}
//				}
//				
//			}
//		}
//		return memberCode;
//	}
	
//	//用于根据省份获取账户
//	private String getMemberCodeByMoIdOrUserCode(String str,int flag){
//		String province = "";
//		if(flag==0 || flag==2 || flag==3){
//			JmiMember jmiMember = jmiMemberManager.get(str);
//			if(jmiMember.getProvince()==null){
//				province = "null";
//			}else{
//				province = jmiMember.getProvince();
//			}
//		}else{
//			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(str));
//			province = jpoMemberOrder.getProvince();
//		}
//		String memberCode = province;
//		if(Bill99Constants.account.get(province)==null){
//			memberCode = "null";
//		}
//		return memberCode;
//	}

	/**
	 * 生成初始Jfi99billLog
	 */
	private Jfi99billLog setJfi99billLog(HttpServletRequest request,String userCode,String companyCode){
		Jfi99billLog jfi99billLog = new Jfi99billLog();
        jfi99billLog.setInc("0");
        jfi99billLog.setCompanyCode(companyCode);
        jfi99billLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
        jfi99billLog.setUserCode(userCode);
        jfi99billLog.setReferer(request.getHeader("referer"));
        
        jfi99billLog.setBankDealId(request.getParameter("bankDealId"));
        jfi99billLog.setBankId(request.getParameter("bankId"));
        jfi99billLog.setBillLanguage(request.getParameter("language"));
        jfi99billLog.setDealId(request.getParameter("dealId"));
        jfi99billLog.setDealTime(request.getParameter("dealTime"));
        jfi99billLog.setErrCode(request.getParameter("errCode"));
        jfi99billLog.setExt1(request.getParameter("ext1"));
        jfi99billLog.setExt2(request.getParameter("ext2"));
        jfi99billLog.setFee(request.getParameter("fee"));
        jfi99billLog.setMerchantAcctId(request.getParameter("merchantAcctId"));
        jfi99billLog.setOrderAmount(request.getParameter("orderAmount"));
        jfi99billLog.setOrderId(request.getParameter("orderId"));
        jfi99billLog.setOrderTime(request.getParameter("orderTime"));
        jfi99billLog.setPayAmount(request.getParameter("payAmount"));
        jfi99billLog.setPayResult(request.getParameter("payResult"));
        jfi99billLog.setPayType(request.getParameter("payType"));
        jfi99billLog.setSignMsg(request.getParameter("signMsg"));
        jfi99billLog.setSignType(request.getParameter("signType"));
        jfi99billLog.setVerifySignResult(request.getParameter("verifySignResult"));
        jfi99billLog.setVersion(request.getParameter("version"));
        jfi99billLog.setIp(request.getRemoteAddr());
        return jfi99billLog;
	}
	
	/**
	 * 0表示数据被篡改
	 * 1表示扣款失败
	 * 2自定义MD5签名被篡改(快钱签名被破解)
	 * 3支付数据重新发送
	 * 10表示成功校验
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public Jfi99billLog getJfi99billLog(HttpServletRequest request,String userCode,String companyCode){
		Jfi99billLog jfi99billLog = this.setJfi99billLog(request, userCode, companyCode);
		jfi99billLog = this.checkJfi99billLog(jfi99billLog);
		return jfi99billLog;
	}

	/**
	 * jfi99billLog.getReturnMsg()
	 * 0表示数据被篡改
	 * 1表示扣款失败
	 * 2自定义MD5签名被篡改(快钱签名被破解)
	 * 3支付数据重新发送
	 * 10表示成功校验
	 */
	private Jfi99billLog checkJfi99billLog(Jfi99billLog jfi99billLog){
		//生成加密串。必须保持如下顺序。
		String merchantSignMsgVal="";
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"merchantAcctId",jfi99billLog.getMerchantAcctId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"version",jfi99billLog.getVersion());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"language",jfi99billLog.getBillLanguage());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"signType",jfi99billLog.getSignType());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payType",jfi99billLog.getPayType());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankId",jfi99billLog.getBankId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderId",jfi99billLog.getOrderId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderTime",jfi99billLog.getOrderTime());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderAmount",jfi99billLog.getOrderAmount());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealId",jfi99billLog.getDealId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankDealId",jfi99billLog.getBankDealId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealTime",jfi99billLog.getDealTime());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payAmount",jfi99billLog.getPayAmount());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"fee",jfi99billLog.getFee());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext1",jfi99billLog.getExt1());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext2",jfi99billLog.getExt2());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payResult",jfi99billLog.getPayResult());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"errCode",jfi99billLog.getErrCode());

		int rtnMsg = 0;
		jfi99billLog.setVerifySignResult("false");
		
/*		if(Bill99Constants.signType1.equals(jfi99billLog.getSignType())){
			//MD5校验签名
			merchantSignMsgVal=appendParam(merchantSignMsgVal,"key",Bill99Constants.key);
			String merchantSignMsg = "";
			try {
				merchantSignMsg=MD5Util.md5Hex(merchantSignMsgVal.getBytes("gb2312")).toUpperCase();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(jfi99billLog.getSignMsg().toUpperCase().equals(merchantSignMsg.toUpperCase())){
				jfi99billLog.setVerifySignResult("true");
			}else{
				jfi99billLog.setVerifySignResult("false");
			}
		}*/
		
		if(Bill99Constants.signType4.equals(jfi99billLog.getSignType())){
			//证书校验签名
			PKIUtil pkiUtil=new PKIUtil();
			boolean verifyData = pkiUtil.verifyData(merchantSignMsgVal,jfi99billLog.getSignMsg());
			jfi99billLog.setVerifySignResult(String.valueOf(verifyData));
		}
		
		if("true".equals(jfi99billLog.getVerifySignResult().toLowerCase())){
			///接着进行支付结果判断
			if("10".equals(jfi99billLog.getPayResult())){
				// 商户网站逻辑处理，比方更新订单支付状态为成功
				// 特别注意：只有signMsg.toUpperCase().equals(merchantSignMsg.toUpperCase())，且payResult=10，才表示支付成功！同时将订单金额与提交订单前的订单金额进行对比校验。
				//*
				//报告给快钱处理结果，并提供将要重定向的地址。
				String ext1MD5 = getExt1MD5(jfi99billLog);
				
				//特殊处理这两笔款，自定义MD5签名有误
				//处理号825332515，ext1MD5=2bbe8e0a52993b1881ea5d5ccfd174c2
				//处理号825131364，ext1MD5=547efa40e8482fe693ee17095551fe5a
				//处理号857772829，ext1MD5=76befd936fac5e3e166b192fcfc3a6f9
				if("825332515".equals(jfi99billLog.getDealId()) || "825131364".equals(jfi99billLog.getDealId()) || "857772829".equals(jfi99billLog.getDealId())){
					ext1MD5 = jfi99billLog.getExt1();
				}
				//2013-1-18取消自定义MD5判断
				ext1MD5 = jfi99billLog.getExt1();
				
				if(!jfi99billLog.getExt1().equals(ext1MD5)){
					rtnMsg = 2;//自定义MD5签名被篡改(快钱签名被篡改)
				}else{
					Jfi99billLog jfi99billLogTmp = new Jfi99billLog();
					jfi99billLogTmp.setDealId(jfi99billLog.getDealId());
					jfi99billLogTmp.setInc("1");
					if(jfi99billLogManager.getJfi99billLogs(jfi99billLogTmp).size()>0){
						rtnMsg = 3;//重发信息
					}else{
						rtnMsg = 10;//成功校验
					}
				}
			}else{
				rtnMsg = 2;//扣款失败
			}
		}else{
			// 商户网站逻辑处理，
			rtnMsg = 0;//被篡改
		}
		jfi99billLog.setReturnMsg(String.valueOf(rtnMsg));
		jfi99billLog.setCreateTime(new Date());
		jfi99billLogManager.save(jfi99billLog);
		return jfi99billLog;//被篡改
	}
	
	/**
	 * 生成签名
	 * @param bill99
	 * @return
	 */
	private String getSignMsg(Bill99 bill99){
		//生成加密签名串
		///请务必按照如下顺序和规则组成加密串！
		String signMsgVal="";
		signMsgVal=appendParam(signMsgVal,"inputCharset",Bill99Constants.inputCharset);
		signMsgVal=appendParam(signMsgVal,"pageUrl",Bill99Constants.pageUrl);
		signMsgVal=appendParam(signMsgVal,"bgUrl",Bill99Constants.bgUrl);
		signMsgVal=appendParam(signMsgVal,"version",Bill99Constants.version);
		signMsgVal=appendParam(signMsgVal,"language",Bill99Constants.language);
		
		signMsgVal=appendParam(signMsgVal,"signType",bill99.getSignType());
		
		signMsgVal=appendParam(signMsgVal,"merchantAcctId",bill99.getMerchantAcctId());
		signMsgVal=appendParam(signMsgVal,"payerName",bill99.getPayerName());
		signMsgVal=appendParam(signMsgVal,"payerContactType",Bill99Constants.payerContactType);
		signMsgVal=appendParam(signMsgVal,"payerContact",bill99.getPayerContact());
 		signMsgVal=appendParam(signMsgVal,"orderId",bill99.getOrderId());
		signMsgVal=appendParam(signMsgVal,"orderAmount",bill99.getOrderAmount());
		signMsgVal=appendParam(signMsgVal,"orderTime",bill99.getOrderTime());
		signMsgVal=appendParam(signMsgVal,"productName",bill99.getProductName());
		signMsgVal=appendParam(signMsgVal,"productNum",bill99.getProductNum());
		signMsgVal=appendParam(signMsgVal,"productId",bill99.getProductId());
		signMsgVal=appendParam(signMsgVal,"productDesc",bill99.getProductDesc());
		signMsgVal=appendParam(signMsgVal,"ext1",bill99.getExt1());
		signMsgVal=appendParam(signMsgVal,"ext2",bill99.getExt2());
		if(StringUtils.isEmpty(bill99.getBankId())){
			signMsgVal=appendParam(signMsgVal,"payType",Bill99Constants.payType13);
		}else{
			signMsgVal=appendParam(signMsgVal,"payType",Bill99Constants.payType10);
		}
		signMsgVal=appendParam(signMsgVal,"bankId",bill99.getBankId());
		signMsgVal=appendParam(signMsgVal,"redoFlag",Bill99Constants.redoFlag);
		signMsgVal=appendParam(signMsgVal,"pid",Bill99Constants.pid);
		String signMsg = "";
		
		//String[] ext2 = bill99.getExt2().split(",");
        //String userCode = ext2[0];
		
		if(Bill99Constants.signType1.equals(bill99.getSignType())){
			//MD5签名
			//signMsgVal=appendParam(signMsgVal,"key",Bill99Constants.account.get(this.getMemberCodeByUserCode(userCode)).get("key"));
			signMsgVal=appendParam(signMsgVal,"key",Bill99Constants.key);///改造：获取KEY
//	        int flag = Integer.parseInt(ext2[1]);
//			if(flag==0){
//				signMsgVal=appendParam(signMsgVal,"key",Bill99Constants.key);///改造：获取KEY
//			}else{
//				signMsgVal=appendParam(signMsgVal,"key",Bill99Constants.key);///改造：获取KEY
//			}
			
			try {
				signMsg = MD5Util.md5Hex(signMsgVal.getBytes(Bill99Constants.encoding)).toUpperCase();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(Bill99Constants.signType4.equals(bill99.getSignType())){
			//证书签名
			PKIUtil pkiUtil=new PKIUtil();
			//signMsg=pkiUtil.generateSign(signMsgVal,this.getMemberCodeByUserCode(userCode));
			
//	        int flag = Integer.parseInt(ext2[1]);
//			if(flag==0){
//				signMsg=pkiUtil.generateSign(signMsgVal,this.getMemberCodeByMoIdOrUserCode(userCode,flag));//账户编号
//			}else{
//				signMsg=pkiUtil.generateSign(signMsgVal,this.getMemberCodeByMoIdOrUserCode(bill99.getOrderId(),flag));//账户编号
//			}
	        signMsg=pkiUtil.generateSign(signMsgVal,"");//商户注册密码
		}
		
		return signMsg;
	}
	Log log = LogFactory.getLog(getClass());
	
	/**
	 * 生成Bill99对像
	 * flag(0：为电子存折；  1：订单； 2：公益基金； 3：酒业配置)
	 */
	public Bill99 getBill99(Bill99 bill99,int flag ){
		log.info("get bill99util getbill99 is come in");
		bill99.setInputCharset(Bill99Constants.inputCharset);
		bill99.setPageUrl(Bill99Constants.pageUrl);
		bill99.setBgUrl(Bill99Constants.bgUrl);
		log.info("get bill99util getbill99 bgUrl is :" + Bill99Constants.bgUrl);
		bill99.setVersion(Bill99Constants.version);
		bill99.setLanguage(Bill99Constants.language);
		
		String[] ext2 = bill99.getExt2().split(",");
		String userCode = ext2[0];
				
		bill99.setPayerContactType(Bill99Constants.payerContactType);
		if(StringUtils.isEmpty(bill99.getBankId())){
			bill99.setPayType(Bill99Constants.payType13);
		}else{
			bill99.setPayType(Bill99Constants.payType10);
		}
		bill99.setRedoFlag(Bill99Constants.redoFlag);
		bill99.setPid(Bill99Constants.pid);
		
		//在线充值的时候，生成充值单号
		if(flag==0){
			bill99.setOrderId(sysIdManager.buildIdStr("advicecode_cn"));
			//设置账户编号
	      bill99.setMerchantAcctId(this.getFiBillAccountByUserCode(userCode));//调用获取快钱商户号
		}
		BigDecimal orderFee = new BigDecimal(bill99.getOrderAmount()).multiply(Bill99Constants.feeP).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal OrderAmount = (new BigDecimal(bill99.getOrderAmount()).add(orderFee)).multiply(new BigDecimal(100));
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		bill99.setOrderAmount(decimalFormat.format(OrderAmount));
		bill99.setOrderTime(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
		bill99.setProductName("");
		bill99.setProductDesc("");
		bill99.setProductId("");
		bill99.setProductNum("");
		bill99.setExt2(bill99.getExt2() + "," + String.valueOf(flag) + "," + orderFee);
		
		//证书
		bill99.setSignType(Bill99Constants.signType4);
		
		//MD5校验
		//bill99.setSignType(Bill99Constants.signType1);
		
		bill99.setExt1(getExt1MD5(bill99));
		bill99.setPostUrl(Bill99Constants.postUrl);
		bill99.setSignMsg(getSignMsg(bill99));
		return bill99;
	}
	
	//功能函数。将变量值不为空的参数组成字符串
	private String appendParam(String returnStr,String paramId,String paramValue)
	{
		if(!returnStr.equals(""))
		{
			if(!paramValue.equals(""))
			{
				returnStr=returnStr+"&"+paramId+"="+paramValue;
			}
		}
		else
		{
			if(!paramValue.equals(""))
			{
			returnStr=paramId+"="+paramValue;
			}
		}	
		return returnStr;
	}
	
	/**
	 * 自己编写的MD5校验位
	 * @param obj
	 * @return
	 */
	private String getExt1MD5(Object obj){
		String rtnStr = "";
		if("com.joymain.jecs.util.bill99.Bill99".equals(obj.getClass().getName())){
			String signMsgVal="";
			Bill99 bill99 = (Bill99)obj;
			signMsgVal += bill99.getBankId();
			signMsgVal += bill99.getOrderAmount();
			signMsgVal += bill99.getOrderTime();
			signMsgVal += bill99.getPayType();
			signMsgVal += bill99.getLanguage();
			signMsgVal += bill99.getVersion();
			signMsgVal += bill99.getSignType();
			signMsgVal += bill99.getOrderId();
			signMsgVal += bill99.getMerchantAcctId();
			signMsgVal += bill99.getExt2();
			signMsgVal += "10";
			signMsgVal = StringUtil.encodePassword(signMsgVal, "md5");
			signMsgVal += ".joymain168";
			signMsgVal = StringUtil.encodePassword(signMsgVal, "md5");
			signMsgVal = StringUtil.encodePassword(signMsgVal, "md5");
			rtnStr = signMsgVal;
		}
		if("com.joymain.jecs.fi.model.Jfi99billLog".equals(obj.getClass().getName())){
			String signMsgVal="";
			Jfi99billLog jfi99billLog = (Jfi99billLog)obj;
			signMsgVal += jfi99billLog.getBankId();
			signMsgVal += jfi99billLog.getOrderAmount();
			signMsgVal += jfi99billLog.getOrderTime();
			signMsgVal += jfi99billLog.getPayType();
			signMsgVal += jfi99billLog.getBillLanguage();
			signMsgVal += jfi99billLog.getVersion();
			signMsgVal += jfi99billLog.getSignType();
			signMsgVal += jfi99billLog.getOrderId();
			signMsgVal += jfi99billLog.getMerchantAcctId();
			signMsgVal += jfi99billLog.getExt2();
			signMsgVal += jfi99billLog.getPayResult();
			signMsgVal = StringUtil.encodePassword(signMsgVal, "md5");
			signMsgVal += ".joymain168";
			signMsgVal = StringUtil.encodePassword(signMsgVal, "md5");
			signMsgVal = StringUtil.encodePassword(signMsgVal, "md5");
			rtnStr = signMsgVal;
		}
		return rtnStr;
	}
	
	/**
	 * 生成Bill99对像
	 * flag(0：为电子存折；  1：订单； 2：公益基金； 3：酒业配置)
	 */
	public Bill99 getBill99ForMobil(Bill99 bill99,int flag ){
		
		String mobil_localhost = "http://ddzf.jmtop.com/jecs/";
		//String mobil_localhost = "http://test.joylifeglobal.net/jecsnew_ht/";//奖金制度测试机
		String mobil_pageUrl = mobil_localhost + "mobilWebPayReceive.html";
		String mobil_bgUrl = mobil_localhost + "mobilWebPayReceive.html";
		
		bill99.setInputCharset(Bill99Constants.inputCharset);
		bill99.setPageUrl(mobil_pageUrl);
		bill99.setBgUrl(mobil_bgUrl);
		bill99.setVersion(Bill99Constants.version);
		bill99.setLanguage(Bill99Constants.language);
		
		//String[] ext2 = bill99.getExt2().split(",");
		String userCode = bill99.getExt2();
				
		//设置账户编号
		bill99.setMerchantAcctId(this.getFiBillAccountByUserCode(userCode));//调用获取快钱商户号

		bill99.setPayerContactType(Bill99Constants.payerContactType);
		if(StringUtils.isEmpty(bill99.getBankId())){
			bill99.setPayType(Bill99Constants.payType13);
		}else{
			bill99.setPayType(Bill99Constants.payType10);
		}
		bill99.setRedoFlag(Bill99Constants.redoFlag);
		bill99.setPid(Bill99Constants.pid);
		
		//在线充值的时候，生成充值单号
		if(flag==0){
			bill99.setOrderId(sysIdManager.buildIdStr("advicecode_cn"));
		}
		BigDecimal orderFee = new BigDecimal(bill99.getOrderAmount()).multiply(Bill99Constants.feeP).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal OrderAmount = (new BigDecimal(bill99.getOrderAmount()).add(orderFee)).multiply(new BigDecimal(100));
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		bill99.setOrderAmount(decimalFormat.format(OrderAmount));
		bill99.setOrderTime(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
		bill99.setProductName("");
		bill99.setProductDesc("");
		bill99.setProductId("");
		bill99.setProductNum("");
		bill99.setExt2(bill99.getExt2() + "," + String.valueOf(flag) + "," + 2);
		
		//证书
		bill99.setSignType(Bill99Constants.signType4);
		
		//MD5校验
		//bill99.setSignType(Bill99Constants.signType1);
		
		bill99.setExt1(getExt1MD5(bill99));
		bill99.setPostUrl(Bill99Constants.postUrl);
		bill99.setSignMsg(getSignMsg(bill99));
		return bill99;
	}
}
