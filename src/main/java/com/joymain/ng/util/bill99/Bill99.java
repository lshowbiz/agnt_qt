package com.joymain.ng.util.bill99;


public class Bill99 implements java.io.Serializable {
    /**
	 * 
	 */
	private String postUrl; //快钱支付请求地址
	private String encoding;
	private String inputCharset;
	private String pageUrl;
	private String bgUrl; //快钱回调地址
    private String version;
    private String language;
    private String signType; //证书
    private String memberCode; //商户号
	private String merchantAcctId; //账户编号
    private String payerName;
    private String payerContactType;
    private String payerContact;//手续费
    private String orderId; //订单ID
    private String orderAmount; //金额
    private String orderTime;
    private String productName;
    private String productNum;
    private String productId;
    private String productDesc;
    private String ext1;
    private String ext2; //会员编号
    private String payType;
    private String bankId;//银行ID
    private String redoFlag;
    private String pid = "";
    private String signMsg;
    private String key;

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public void setMerchantAcctId(String merchantAcctId) {
		this.merchantAcctId = merchantAcctId;
	}
	public void setPayerContactType(String payerContactType) {
		this.payerContactType = payerContactType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public void setRedoFlag(String redoFlag) {
		this.redoFlag = redoFlag;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPostUrl() {
		return postUrl;
	}
	public String getEncoding() {
		return encoding;
	}
	public String getInputCharset() {
		return inputCharset;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public String getBgUrl() {
		return bgUrl;
	}
	public String getVersion() {
		return version;
	}
	public String getLanguage() {
		return language;
	}
	public String getSignType() {
		return signType;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public String getMerchantAcctId() {
		return merchantAcctId;
	}
	public String getPayerContactType() {
		return payerContactType;
	}
	public String getPayType() {
		return payType;
	}
	public String getRedoFlag() {
		return redoFlag;
	}
	public String getPid() {
		return pid;
	}
	public String getSignMsg() {
		return this.signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerContact() {
		return payerContact;
	}
	public void setPayerContact(String payerContact) {
		this.payerContact = payerContact;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
