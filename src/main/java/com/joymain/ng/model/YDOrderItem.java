package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="yd_order_item")
@XmlRootElement
public class YDOrderItem  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long molId;
	private YDOrder ydOrder;
	private String productNo;
	private String productName;
	private BigDecimal price;
	private BigDecimal pv;
	private BigDecimal qty;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pm")
	@SequenceGenerator(name = "seq_pm", sequenceName = "seq_pm", allocationSize = 1)
    @Column(name="MOL_ID", unique=true, nullable=false, precision=10, scale=0)   
	public Long getMolId() {
		return molId;
	}
	public void setMolId(Long molId) {
		this.molId = molId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "MO_ID")
	@JsonIgnore
	public YDOrder getYdOrder() {
		return ydOrder;
	}
	public void setYdOrder(YDOrder ydOrder) {
		this.ydOrder = ydOrder;
	}
	
	@Column(name="PRODUCTNO")
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	@Column(name="PRODUCTNAME")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="PRICE")
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name="PV")
	public BigDecimal getPv() {
		return pv;
	}
	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}
	
	@Column(name="QTY")
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	   
}
