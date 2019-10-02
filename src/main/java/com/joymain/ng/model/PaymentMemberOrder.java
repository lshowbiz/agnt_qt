package com.joymain.ng.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
public class PaymentMemberOrder implements Serializable {
	private String code;   //	标识	code
	private String msg;   //	具体描述	msg
	private String userCode;   //	会员编号	userCode
	private String memberOrderNo;   //	订单编号	memberOrderNo
	private String orderType;   //	订单类型	orderType
	private BigDecimal amount;   //	总金额	amount
	private BigDecimal jjAmount;   //	基金总额	jjAmount
	private BigDecimal bankbookAmount;   //	电子存折总额	bankbookAmount
	private BigDecimal fjBalanceAmount;   //	基金账户总额	fjBalanceAmount
	private BigDecimal discounAmount;   //	抵扣积分	discounAmount
	private BigDecimal pvAmt;   //	总PV	pvAmt
	private String orderTime;   //	创建时间	orderTime
	private String status;   //	订单状态	status
	private String isShipping;   //	发货状态	isShipping
	private String isMobile;   //	    订单来源	isMobile
	private List<PaymentOrderList> orderLists;   //	  list<商品明细>	orderLists<orderList>
	private String remark;   //	备注	remark

	public BigDecimal getFjBalanceAmount() {
		return fjBalanceAmount;
	}

	public void setFjBalanceAmount(BigDecimal fjBalanceAmount) {
		this.fjBalanceAmount = fjBalanceAmount;
	}

	public BigDecimal getBankbookAmount() {
		return bankbookAmount;
	}

	public void setBankbookAmount(BigDecimal bankbookAmount) {
		this.bankbookAmount = bankbookAmount;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMemberOrderNo() {
		return memberOrderNo;
	}

	public void setMemberOrderNo(String memberOrderNo) {
		this.memberOrderNo = memberOrderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getJjAmount() {
		return jjAmount;
	}

	public void setJjAmount(BigDecimal jjAmount) {
		this.jjAmount = jjAmount;
	}

	public BigDecimal getDiscounAmount() {
		return discounAmount;
	}

	public void setDiscounAmount(BigDecimal discounAmount) {
		this.discounAmount = discounAmount;
	}

	public BigDecimal getPvAmt() {
		return pvAmt;
	}

	public void setPvAmt(BigDecimal pvAmt) {
		this.pvAmt = pvAmt;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(String isShipping) {
		this.isShipping = isShipping;
	}

	public String getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

	public List<PaymentOrderList> getOrderLists() {
		return orderLists;
	}

	public void setOrderLists(List<PaymentOrderList> orderLists) {
		this.orderLists = orderLists;
	}
	
	@JsonIgnore
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
