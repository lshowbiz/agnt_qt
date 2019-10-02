package com.joymain.ng.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MemberOrder implements Serializable {
	private String memberOrderNo;//	订单编号	 memberOrderNo 	String	
	private String orderType;//	属性	orderType	String	
	private String isMobile;//	来源	isMobile	String	
	private BigDecimal amount;//	总金额	amount	bigDecimal	
	private BigDecimal jjAmount;//	基金总额	jjAmount	bigDecimal	
	private BigDecimal discountAmount;//	抵扣积分	discountAmount	bigDecimal	
	private BigDecimal pvAmount;//	总PV	pvAmount	bigDecimal	
	private String status;//	状态	Status	String	
	private String isShipping;//	发货状态	isShipping	String	
	private String isRetreatOrder2;//	退单状态	isRetreatOrder2	String	
	private String orderTime;//	下单时间	orderTime	Date	
	private String checkDate;//	审核日期	checkDate	Date	
	private List<OrderProduct> orderLists;//	商品列表	orderLists<orderList>	List集合	
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
	public String getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
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
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public BigDecimal getPvAmount() {
		return pvAmount;
	}
	public void setPvAmount(BigDecimal pvAmount) {
		this.pvAmount = pvAmount;
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
	public String getIsRetreatOrder2() {
		return isRetreatOrder2;
	}
	public void setIsRetreatOrder2(String isRetreatOrder2) {
		this.isRetreatOrder2 = isRetreatOrder2;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public List<OrderProduct> getOrderLists() {
		return orderLists;
	}
	public void setOrderLists(List<OrderProduct> orderLists) {
		this.orderLists = orderLists;
	}

	
}
