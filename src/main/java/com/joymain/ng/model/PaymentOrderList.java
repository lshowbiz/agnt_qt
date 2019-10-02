package com.joymain.ng.model;


import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentOrderList implements Serializable {
	private String imageLink;   //	    商品图片rul	imageLink
	private String productNo;   //	商品编码	productNo
	private String productName;   //	商品名称	productName
	private BigDecimal Price;   //	单价	Price
	private BigDecimal Pv;   //	PV	Pv
	private Integer Qty;   //	数量	Qty
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	public BigDecimal getPv() {
		return Pv;
	}
	public void setPv(BigDecimal pv) {
		Pv = pv;
	}
	public Integer getQty() {
		return Qty;
	}
	public void setQty(Integer qty) {
		Qty = qty;
	}
}
