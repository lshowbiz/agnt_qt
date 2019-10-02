package com.joymain.ng.util.reapal;

public class Reapal implements java.io.Serializable {

	private String service;//接口服务名称，目前固定值：online_pay（网上支付）
	private String payment_type;//支付类型，目前固定值：1
	private String merchant_ID;//合作身份者ID商户号
	private String seller_email;//签约融宝支付账号或卖家收款融宝支付帐户 ==
	private String return_url;//付完款后跳转的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	private String notify_url;//notify_url 交易过程中服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	private String charset;//字符编码格式 目前支持 gbk 或 utf-8
	private String order_no;//订单编号
	private String title;//订单名称，显示在融宝支付收银台里的“商品名称”里，显示在融宝支付的交易管理的“商品名称”的列表里
	private String body;//订单描述、订单详细、订单备注，显示在融宝支付收银台里的“商品描述”里
	private String total_fee;//订单总金额，显示在融宝支付收银台里的“交易金额”里
	private String buyer_email;//默认买家融宝支付账号
	private String paymethod;//支付方式
	private String defaultbank;//网银代码
	private String sign;//
	private String sign_type;//签名方式 不需修改	
	private String key;//	
	private String post_url;//

	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getMerchant_ID() {
		return merchant_ID;
	}

	public void setMerchant_ID(String merchant_ID) {
		this.merchant_ID = merchant_ID;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getDefaultbank() {
		return defaultbank;
	}

	public void setDefaultbank(String defaultbank) {
		this.defaultbank = defaultbank;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getPost_url() {
		return post_url;
	}

	public void setPost_url(String post_url) {
		this.post_url = post_url;
	}
	
	
}
