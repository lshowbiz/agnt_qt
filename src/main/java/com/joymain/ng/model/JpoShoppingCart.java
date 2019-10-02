package com.joymain.ng.model;

public class JpoShoppingCart {
	private int qty;//数量
	private Long productId; //产品id
	private String orderType;//订单类型
	private String isShipments;//是否为暂不发货，默认为0（正常发货）
	private String teamType;//团队类型，0表示老中脉
	private String companyCode;//会员国别
	private String userCode;//会员编号
	private String isCheck;//是否购买
	private String isMobile;//是否为手机端购买，1为是，0为否
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getIsShipments() {
		return isShipments;
	}
	public void setIsShipments(String isShipments) {
		this.isShipments = isShipments;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public String getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	

}
