package com.joymain.ng.util.pay;

import java.math.BigDecimal;

import net.sf.json.JSONArray;

/**
 * 备注字段所需要填写的内容
 * 
 * @author lzg
 * 
 */
@SuppressWarnings("static-access")
public class RemarkBean {
	// [用户编号,支付类型(0：为电子存折； 1：订单； 2：公益基金； 3：酒业配置),支付公司,支付平台,商户号，是否全额支付，手续费]
	// String s = "{userCode:%s,payType:%s,payFee:%s,dataType:%s,isFull:%s}";
	String userCode;// 用户编号
	String payType = "1";// 支付类型0：充值，1支付
	String zmType = "";// 用于判断为那家支付公司
	String dataType = "1";// 支付平台1:pc,2:手机
	String merchantId = "";// 商户号
	Boolean isFull = false;// 是否全额支付
	BigDecimal payFee = new BigDecimal("0.00"); // 手续费

	public RemarkBean() {
	}

	public RemarkBean(String userCode, String payType, String zmType, String dataType, Boolean isFull, BigDecimal payFee) {
		super();
		this.userCode = userCode;
		this.payType = payType;
		this.zmType = zmType;
		this.dataType = dataType;
		this.isFull = isFull;
		this.payFee = payFee;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getPayFee() {
		return payFee;
	}

	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Boolean getIsFull() {
		return isFull;
	}

	public void setIsFull(Boolean isFull) {
		this.isFull = isFull;
	}

	public String getZmType() {
		return zmType;
	}

	public void setZmType(String zmType) {
		this.zmType = zmType;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String toString() {
		return "RemarkBean [userCode=" + userCode + ", payType=" + payType + ", zmType=" + zmType + ", dataType=" + dataType + ", merchantId=" + merchantId + ", isFull=" + isFull
				+ ", payFee=" + payFee + "]";
	}
	public String toArrayString(){
		String fmt = "%s,%s,%s,%s,%s,%s,%s";
		return new String().format(fmt, new Object[]{userCode,payType,zmType,dataType,merchantId,isFull,payFee});
	}
	public static void main(String[] args) {
		RemarkBean bean = new RemarkBean("sadf", "asf", "", "", true, new BigDecimal("0.00"));
		JSONArray json = JSONArray.fromObject(bean);
		System.out.println(json);
		
		
		System.out.println();
	}
}
