package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 统一传递支付公司转换类
 * 
 * @author jfoy
 * 
 */
public class JfiPayOrderCompany extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3596308354234276553L;
	/**
	 * 支付金额
	 */
	private BigDecimal payAmount;

	/**
	 * 用户帐号
	 */
	private String userCode;

	/**
	 * 支付类型 0：充值，1支付
	 */
	private String payType;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 订单金额
	 */
	private BigDecimal orderAmount;
	/**
	 * flag(0：为电子存折； 1：订单； 2：公益基金； 3：酒业配置)
	 */
	private String flag;

	private String payAccount;

	/**
	 * 汇付天下特殊字段 用户id+支付类型:充值和订单
	 */
	private String merPriv;
	
	private Date orderTime;

	private Boolean isFull = false;
	private String dataType = "1"; //数据来源1:PC,2:手机

	private String platformPayType;
	private String goodsDesc;
	/**
	 * 商户对象
	 */
	private Map<String, String> business;
	
	private String backUrl;

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	public Map<String, String> getBusiness() {
		return business;
	}

	public void setBusiness(Map<String, String> business) {
		this.business = business;
	}

	public String getMerPriv() {
		return merPriv;
	}

	public void setMerPriv(String merPriv) {
		this.merPriv = merPriv;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Boolean getIsFull() {
		return isFull;
	}

	public void setIsFull(Boolean isFull) {
		this.isFull = isFull;
	}
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPlatformPayType() {
		return platformPayType;
	}

	public void setPlatformPayType(String platformPayType) {
		this.platformPayType = platformPayType;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((business == null) ? 0 : business.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((isFull == null) ? 0 : isFull.hashCode());
		result = prime * result + ((merPriv == null) ? 0 : merPriv.hashCode());
		result = prime * result + ((orderAmount == null) ? 0 : orderAmount.hashCode());
		result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
		result = prime * result + ((payAccount == null) ? 0 : payAccount.hashCode());
		result = prime * result + ((payAmount == null) ? 0 : payAmount.hashCode());
		result = prime * result + ((payType == null) ? 0 : payType.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((platformPayType == null) ? 0 : platformPayType.hashCode());
		result = prime * result + ((goodsDesc == null) ? 0 : goodsDesc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JfiPayOrderCompany other = (JfiPayOrderCompany) obj;
		if (business == null) {
			if (other.business != null)
				return false;
		} else if (!business.equals(other.business))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (isFull == null) {
			if (other.isFull != null)
				return false;
		} else if (!isFull.equals(other.isFull))
			return false;
		if (merPriv == null) {
			if (other.merPriv != null)
				return false;
		} else if (!merPriv.equals(other.merPriv))
			return false;
		if (orderAmount == null) {
			if (other.orderAmount != null)
				return false;
		} else if (!orderAmount.equals(other.orderAmount))
			return false;
		if (orderNo == null) {
			if (other.orderNo != null)
				return false;
		} else if (!orderNo.equals(other.orderNo))
			return false;
		if (payAccount == null) {
			if (other.payAccount != null)
				return false;
		} else if (!payAccount.equals(other.payAccount))
			return false;
		if (payAmount == null) {
			if (other.payAmount != null)
				return false;
		} else if (!payAmount.equals(other.payAmount))
			return false;
		if (payType == null) {
			if (other.payType != null)
				return false;
		} else if (!payType.equals(other.payType))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (platformPayType == null) {
			if (other.platformPayType != null)
				return false;
		} else if (!platformPayType.equals(other.platformPayType))
			return false;
		if (goodsDesc == null) {
			if (other.goodsDesc != null)
				return false;
		} else if (!goodsDesc.equals(other.goodsDesc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JfiPayOrderCompany [payAmount=" + payAmount + ", userCode=" + userCode + ", payType=" + payType + ", orderNo=" + orderNo + ", orderAmount=" + orderAmount
				+ ", flag=" + flag + ", payAccount=" + payAccount + ", merPriv=" + merPriv + ", isFull=" + isFull + ", business=" + business + ", platformPayType=" + platformPayType + ", goodsDesc=" + goodsDesc+"]";
	}

}
