package com.joymain.ng.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MemberOrderList implements Serializable {
	
	private List<MemberOrder> jpoMemberorders;//订单
	private BigDecimal amt;//	总金额合计	amt	bigDecimal
	private BigDecimal jjAmt;//	基金总额合计	jjAmt	bigDecimal
	private BigDecimal disAmt;//	抵扣积分合计	disAmt	bigDecimal
	private BigDecimal pvAmt;//	总PV合计	pvAmt	bigDecimal
	public List<MemberOrder> getJpoMemberorders() {
		return jpoMemberorders;
	}
	public void setJpoMemberorders(List<MemberOrder> jpoMemberorders) {
		this.jpoMemberorders = jpoMemberorders;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public BigDecimal getJjAmt() {
		return jjAmt;
	}
	public void setJjAmt(BigDecimal jjAmt) {
		this.jjAmt = jjAmt;
	}
	public BigDecimal getDisAmt() {
		return disAmt;
	}
	public void setDisAmt(BigDecimal disAmt) {
		this.disAmt = disAmt;
	}
	public BigDecimal getPvAmt() {
		return pvAmt;
	}
	public void setPvAmt(BigDecimal pvAmt) {
		this.pvAmt = pvAmt;
	}

	
	
	
}
