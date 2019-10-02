package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderProduct implements Serializable {
	private String productNo;// 商品编码 productNo String
	private String productName;// 商品名称 productName String
	private Integer qty;// 商品数量 qty String
	private BigDecimal price;// 价格

	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
