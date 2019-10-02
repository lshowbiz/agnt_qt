package com.joymain.ng.util.umb;

@SuppressWarnings("serial")
public class UmbPay implements java.io.Serializable {
	
	private String merchantid;//商户编号
	private String merorderid;//订单编号
	private String amountsum;//金  额
	private String subject;//商品种类
	private String currencytype;//币  种
	
	private String autojump;//自动调转取货页面
	private String waittime;//跳转等待时间
	private String merurl;//商户取货URL
	private String informmer;//通知商户
	private String informurl;//商户通知URL
	
	private String confirm;//商户返回确认
	private String merbank;//支付银行
	private String tradetype;//支付类型
	private String bankInput;//是否在商户端选择银行
	private String interFace;//接口版本
	
	private String bankcardtype;//支付银行卡类型
	private String pdtdetailurl;//商品描述地址 
	private String mac;//加密数据
	private String remark;//备  注
	private String pdtdnm;//商品描述
	
	private String postUrl;//支付跳转页面地址
	
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public String getMerorderid() {
		return merorderid;
	}
	public void setMerorderid(String merorderid) {
		this.merorderid = merorderid;
	}
	public String getAmountsum() {
		return amountsum;
	}
	public void setAmountsum(String amountsum) {
		this.amountsum = amountsum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCurrencytype() {
		return currencytype;
	}
	public void setCurrencytype(String currencytype) {
		this.currencytype = currencytype;
	}
	public String getAutojump() {
		return autojump;
	}
	public void setAutojump(String autojump) {
		this.autojump = autojump;
	}
	public String getWaittime() {
		return waittime;
	}
	public void setWaittime(String waittime) {
		this.waittime = waittime;
	}
	public String getMerurl() {
		return merurl;
	}
	public void setMerurl(String merurl) {
		this.merurl = merurl;
	}
	public String getInformmer() {
		return informmer;
	}
	public void setInformmer(String informmer) {
		this.informmer = informmer;
	}
	public String getInformurl() {
		return informurl;
	}
	public void setInformurl(String informurl) {
		this.informurl = informurl;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getMerbank() {
		return merbank;
	}
	public void setMerbank(String merbank) {
		this.merbank = merbank;
	}
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	public String getBankInput() {
		return bankInput;
	}
	public void setBankInput(String bankInput) {
		this.bankInput = bankInput;
	}
	public String getInterFace() {
		return interFace;
	}
	public void setInterFace(String interFace) {
		this.interFace = interFace;
	}
	public String getBankcardtype() {
		return bankcardtype;
	}
	public void setBankcardtype(String bankcardtype) {
		this.bankcardtype = bankcardtype;
	}
	public String getPdtdetailurl() {
		return pdtdetailurl;
	}
	public void setPdtdetailurl(String pdtdetailurl) {
		this.pdtdetailurl = pdtdetailurl;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPdtdnm() {
		return pdtdnm;
	}
	public void setPdtdnm(String pdtdnm) {
		this.pdtdnm = pdtdnm;
	}
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
}
