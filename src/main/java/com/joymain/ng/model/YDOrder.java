package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="YD_ORDER")
@XmlRootElement
public class YDOrder  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long moid;
	private String userCode;
	private String orderNo;
	private BigDecimal amount;
	private BigDecimal pvamt;
	private Date orderTime;
	private Date orderPayTime;
	//add by lihao 
	private Date operateTime;
	private Date createTime;
	private BigDecimal ydPV;
	private Set<YDOrderItem> ydOrderItem = new HashSet<YDOrderItem>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_po")
	@SequenceGenerator(name = "seq_po", sequenceName = "seq_po", allocationSize = 1)
    @Column(name="MO_ID", unique=true, nullable=false, precision=10, scale=0)   
	public Long getMoid() {
		return moid;
	}
	
	public void setMoid(Long moid) {
		this.moid = moid;
	}
	
	@Column(name = "USERCODE")
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name="OPERATETIME")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "ORDERNO")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Column(name="PVAMT")
	public BigDecimal getPvamt() {
		return pvamt;
	}
	public void setPvamt(BigDecimal pvamt) {
		this.pvamt = pvamt;
	}
	
	@Column(name="ORDERTIME")
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	@Column(name="ORDERPAYTIME")
	public Date getOrderPayTime() {
		return orderPayTime;
	}
	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}
	
	@Column(name="CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="ydOrder")
	public Set<YDOrderItem> getYdOrderItem() {
		return ydOrderItem;
	}

	public void setYdOrderItem(Set<YDOrderItem> ydOrderItem) {
		this.ydOrderItem = ydOrderItem;
	}
	
	@Column(name="yd_pv")
	public BigDecimal getYdPV() {
		return ydPV;
	}

	public void setYdPV(BigDecimal ydPV) {
		this.ydPV = ydPV;
	}
	
	 
}
