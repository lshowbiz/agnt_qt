package com.joymain.ng.util.channel;

public class ChannelBean implements java.io.Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;//版本名称,默认属性值为:B2CPayment
	private String version;//版本号,默认属性值为: V4.1.1.1.1
	private String charset;//字符集,支持GBK、UTF-8、GB2312,默认属性值为:UTF-8
	private String msgSender;//由盛付通提供,默认为:商户号(由盛付通提供的6位正整数),用于盛付通判别请求方的身份
	private String sendTime;//非必填，防钓鱼时间戳，获取方式见6.1.8，非必填参数
	private String orderNo;//商户订单号,50个字符内、只允许使用数字、字母,确保在商户系统唯一
	private String orderAmount;//支付金额,必须大于0,包含2位小数    如：OrderAmount=1.00
	private String orderTime;//商户提交用户订单时间,必须为14位正整数数字,格式为:yyyyMMddHHmmss,如:OrderTime=20110808112233
	private String payType;//非必填，见附录7.1.1支付类型  如:PayType=PT001   为空时默认显示合同规定的全部渠道
	private String payChannel;//非必填，支付渠道，当指定PayType 为 PT001网银直连支付模式时有效（19 储蓄卡，20 信用卡 12企业网银）
	private String instCode;//非必填，银行编码，见附录7.1.2综合网银编码列表,机构代码列表以逗号分隔,如：InstCode=ICBC
	private String pageUrl;//客户端浏览器回调地址,支付成功后,将附带回调数据跳转到此页面,商户可以进行相关处理并显示给终端用户,如:http://www.testpay.com/testpay.jsp
	private String backUrl;//非必填，在收银台跳转到商户指定的地址
	private String notifyUrl;//服务端通知地址,支付成功后,盛付通将发送“支付成功”信息至该地址
	private String productName;//非必填，商品名称
	private String buyerContact;//非必填，支付人联系方式
	private String buyerIp;//买家IP地址
	private String ext1;//非必填，扩展1
	private String signType;//签名类型,如：MD5
	private String signMsg;//签名串
	
	private String postUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getMsgSender() {
		return msgSender;
	}

	public void setMsgSender(String msgSender) {
		this.msgSender = msgSender;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBuyerContact() {
		return buyerContact;
	}

	public void setBuyerContact(String buyerContact) {
		this.buyerContact = buyerContact;
	}

	public String getBuyerIp() {
		return buyerIp;
	}

	public void setBuyerIp(String buyerIp) {
		this.buyerIp = buyerIp;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

}
