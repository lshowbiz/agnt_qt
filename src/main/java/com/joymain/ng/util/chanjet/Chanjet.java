package com.joymain.ng.util.chanjet;


public class Chanjet implements java.io.Serializable {
    
	private String merchantId;//商户号
	private String payerContactMbl;
	private String payerContactMal;
	private String goodsId;
	private String productName;//商品名称
	private String productNum;
	private String orderId;//商品号
	private String productDesc;
	private String orderDate;//下单日期
	private String orderTime;//下单详细时间
	private String orderAmount;//金额
	private String amtType;//金额类型,人民币:01
	private String platIdtfy;//平台标识,如：T3(T系列哪个产品)
	private String bankType;//选定的银行标识,可空
	private String businessId;
	private String gateId;//网银网关
	private String bgUrl;//页面返回地址
	private String notifyUrl;//结果通讯地址
	private String merPriv;
	private String expand;//会员编号
	private String expand2;
	private String payerName;
	private String payerCardType;
	private String deviceId;
	private String version;
	private String redoFlag;
	private String signType;
	private String expireTime;
	private String signMsg;
	private String plain;
	private String payeeBankAccount;
	private String payeeBankType;
	private String payeeBankName;
	private String payeeName;
	private String postUrl;
	
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeBankName() {
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}
	public String getPayeeBankAccount() {
		return payeeBankAccount;
	}
	public void setPayeeBankAccount(String payeeBankAccount) {
		this.payeeBankAccount = payeeBankAccount;
	}
	public String getPayeeBankType() {
		return payeeBankType;
	}
	public void setPayeeBankType(String payeeBankType) {
		this.payeeBankType = payeeBankType;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPayerContactMbl() {
		return payerContactMbl;
	}
	public void setPayerContactMbl(String payerContactMbl) {
		this.payerContactMbl = payerContactMbl;
	}
	public String getPayerContactMal() {
		return payerContactMal;
	}
	public void setPayerContactMal(String payerContactMal) {
		this.payerContactMal = payerContactMal;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getAmtType() {
		return amtType;
	}
	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}
	public String getPlatIdtfy() {
		return platIdtfy;
	}
	public void setPlatIdtfy(String platIdtfy) {
		this.platIdtfy = platIdtfy;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getGateId() {
		return gateId;
	}
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public String getBgUrl() {
		return bgUrl;
	}
	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getMerPriv() {
		return merPriv;
	}
	public void setMerPriv(String merPriv) {
		this.merPriv = merPriv;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public String getExpand2() {
		return expand2;
	}
	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerCardType() {
		return payerCardType;
	}
	public void setPayerCardType(String payerCardType) {
		this.payerCardType = payerCardType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRedoFlag() {
		return redoFlag;
	}
	public void setRedoFlag(String redoFlag) {
		this.redoFlag = redoFlag;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getPlain() {
		return plain;
	}
	public void setPlain(String plain) {
		this.plain = plain;
	}
}
