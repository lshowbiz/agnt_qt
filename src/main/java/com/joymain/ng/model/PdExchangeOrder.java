package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="PD_EXCHANGE_ORDER")

@XmlRootElement
public class PdExchangeOrder extends BaseObject implements Serializable {
    private String eoNo;
    private String companyCode;
    private String warehouseNo;
//    private String customerCode;
    private Date createTime;
    private String createUsrCode;
    private String remark;
    private Date checkTime;
    private String checkUsrCode;
    private String checkRemark;
    private Date okTime;
    private String okUsrCode;
    private String okRemark;
    private Date editTime;
    private String editUsrCode;
    private Long orderFlag;
    private String stockFlag;
    private String firstName;
    private String lastName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String postalcode;
    private String phone;
    private String email;
    private String mobiletele;
    private String orderNo;
    private String shipType;
    private String selfReplacement;
    private String isPay;
    private BigDecimal pvAmtEx;
    private BigDecimal amountEx;
    private String needPay;//是否需要支付:Y表示需要支付，N或空表示不需要支付 
    private String whetherPd;
    //工具生成的类缺少3个属性
    private BigDecimal needPayAmount;//自助换货单需要支付的金额 
    private String selfRemark;
    private String selfCheckStatus;
   
    //modify by fu 2016-04-26 是否取消自助换货单：Y取消，空恢复
    private String cancelExchange;
    
    // 一张换货单中的原订单商品信息和换货商品信息
    private JsysUser sysUser=new JsysUser();
    private Set<PdExchangeOrderBack> pdExchangeOrderBacks = new HashSet<PdExchangeOrderBack>(0);
    private Set<PdExchangeOrderDetail> pdExchangeOrderDetails = new HashSet<PdExchangeOrderDetail>(0); 
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_CODE")
	@JsonIgnore
	public JsysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(JsysUser sysUser) {
		this.sysUser = sysUser;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="pdExchangeOrder",fetch = FetchType.LAZY)
	public Set<PdExchangeOrderBack> getPdExchangeOrderBacks() {
		return pdExchangeOrderBacks;
	}
	public void setPdExchangeOrderBacks(
			Set<PdExchangeOrderBack> pdExchangeOrderBacks) {
		this.pdExchangeOrderBacks = pdExchangeOrderBacks;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="pdExchangeOrder",fetch=FetchType.LAZY)
	public Set<PdExchangeOrderDetail> getPdExchangeOrderDetails() {
		return pdExchangeOrderDetails;
	}
	public void setPdExchangeOrderDetails(
			Set<PdExchangeOrderDetail> pdExchangeOrderDetails) {
		this.pdExchangeOrderDetails = pdExchangeOrderDetails;
	}
	@Id
	/*
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)	*/
	@Column(name="EO_NO", unique=true, nullable=false, precision=10, scale=0)   @DocumentId  
    public String getEoNo() {
        return this.eoNo;
    }
	@Column(name="NEED_PAY_AMOUNT", precision=18)
    public BigDecimal getNeedPayAmount() {
		return needPayAmount;
	}

	public void setNeedPayAmount(BigDecimal needPayAmount) {
		this.needPayAmount = needPayAmount;
	}
	@Column(name="SELF_REMARK", length=2)
	public String getSelfRemark() {
		return selfRemark;
	}

	public void setSelfRemark(String selfRemark) {
		this.selfRemark = selfRemark;
	}
	@Column(name="SELF_CHECK_STATUS", length=2)
	public String getSelfCheckStatus() {
		return selfCheckStatus;
	}

	public void setSelfCheckStatus(String selfCheckStatus) {
		this.selfCheckStatus = selfCheckStatus;
	}

	public void setEoNo(String eoNo) {
        this.eoNo = eoNo;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="WAREHOUSE_NO", length=20)
    public String getWarehouseNo() {
        return this.warehouseNo;
    }
    
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }
    
    /*
    @Column(name="CUSTOMER_CODE", nullable=false, length=20)
    public String getCustomerCode() {
        return this.customerCode;
    }
    
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    */
    //@Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_USR_CODE", nullable=false, length=20)
    public String getCreateUsrCode() {
        return this.createUsrCode;
    }
    
    public void setCreateUsrCode(String createUsrCode) {
        this.createUsrCode = createUsrCode;
    }
    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    //@Temporal(TemporalType.DATE)
    @Column(name="CHECK_TIME", length=7)
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    
    @Column(name="CHECK_USR_CODE", length=20)
    public String getCheckUsrCode() {
        return this.checkUsrCode;
    }
    
    public void setCheckUsrCode(String checkUsrCode) {
        this.checkUsrCode = checkUsrCode;
    }
    
    @Column(name="CHECK_REMARK", length=200)
    public String getCheckRemark() {
        return this.checkRemark;
    }
    
    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }
    //@Temporal(TemporalType.DATE)
    @Column(name="OK_TIME", length=7)
    public Date getOkTime() {
        return this.okTime;
    }
    
    public void setOkTime(Date okTime) {
        this.okTime = okTime;
    }
    
    @Column(name="OK_USR_CODE", length=20)
    public String getOkUsrCode() {
        return this.okUsrCode;
    }
    
    public void setOkUsrCode(String okUsrCode) {
        this.okUsrCode = okUsrCode;
    }
    
    @Column(name="OK_REMARK", length=200)
    public String getOkRemark() {
        return this.okRemark;
    }
    
    public void setOkRemark(String okRemark) {
        this.okRemark = okRemark;
    }
    //@Temporal(TemporalType.DATE)
    @Column(name="EDIT_TIME", length=7)
    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
    
    @Column(name="EDIT_USR_CODE", length=20)
    public String getEditUsrCode() {
        return this.editUsrCode;
    }
    
    public void setEditUsrCode(String editUsrCode) {
        this.editUsrCode = editUsrCode;
    }
    
    @Column(name="ORDER_FLAG", precision=2, scale=0)
    public Long getOrderFlag() {
        return this.orderFlag;
    }
    
    public void setOrderFlag(Long orderFlag) {
        this.orderFlag = orderFlag;
    }
    
    @Column(name="STOCK_FLAG", length=1)
    public String getStockFlag() {
        return this.stockFlag;
    }
    
    public void setStockFlag(String stockFlag) {
        this.stockFlag = stockFlag;
    }
    
    @Column(name="FIRST_NAME", length=100)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name="LAST_NAME", length=100)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name="PROVINCE", length=500)
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=500)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="DISTRICT", length=20)
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    @Column(name="ADDRESS", length=500)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="POSTALCODE", length=10)
    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    
    @Column(name="PHONE", length=30)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="EMAIL", length=30)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="MOBILETELE", length=20)
    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    
    @Column(name="ORDER_NO", nullable=false, length=20)
    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    @Column(name="SHIP_TYPE", length=4)
    public String getShipType() {
        return this.shipType;
    }
    
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
    
    @Column(name="SELF_REPLACEMENT", length=20)
    public String getSelfReplacement() {
        return this.selfReplacement;
    }
    
    public void setSelfReplacement(String selfReplacement) {
        this.selfReplacement = selfReplacement;
    }
    
    @Column(name="IS_PAY", length=20)
    public String getIsPay() {
        return this.isPay;
    }
    
    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }
    
    @Column(name="PV_AMT_EX", precision=18)
    public BigDecimal getPvAmtEx() {
        return this.pvAmtEx;
    }
    
    public void setPvAmtEx(BigDecimal pvAmtEx) {
        this.pvAmtEx = pvAmtEx;
    }
    
    @Column(name="AMOUNT_EX", precision=18)
    public BigDecimal getAmountEx() {
        return this.amountEx;
    }
    
    public void setAmountEx(BigDecimal amountEx) {
        this.amountEx = amountEx;
    }
    
    @Column(name="NEED_PAY", length=20)
    public String getNeedPay() {
        return this.needPay;
    }
    
    public void setNeedPay(String needPay) {
        this.needPay = needPay;
    }
    
    @Column(name="WHETHER_PD", length=20)
    public String getWhetherPd() {
        return this.whetherPd;
    }
    
    public void setWhetherPd(String whetherPd) {
        this.whetherPd = whetherPd;
    }
    
    @Column(name="CANCEL_EXCHANGE", length=20)
    public String getCancelExchange() {
		return cancelExchange;
	}
    
	public void setCancelExchange(String cancelExchange) {
		this.cancelExchange = cancelExchange;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdExchangeOrder pojo = (PdExchangeOrder) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (warehouseNo != null ? !warehouseNo.equals(pojo.warehouseNo) : pojo.warehouseNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUsrCode != null ? !createUsrCode.equals(pojo.createUsrCode) : pojo.createUsrCode != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (checkUsrCode != null ? !checkUsrCode.equals(pojo.checkUsrCode) : pojo.checkUsrCode != null) return false;
        if (checkRemark != null ? !checkRemark.equals(pojo.checkRemark) : pojo.checkRemark != null) return false;
        if (okTime != null ? !okTime.equals(pojo.okTime) : pojo.okTime != null) return false;
        if (okUsrCode != null ? !okUsrCode.equals(pojo.okUsrCode) : pojo.okUsrCode != null) return false;
        if (okRemark != null ? !okRemark.equals(pojo.okRemark) : pojo.okRemark != null) return false;
        if (editTime != null ? !editTime.equals(pojo.editTime) : pojo.editTime != null) return false;
        if (editUsrCode != null ? !editUsrCode.equals(pojo.editUsrCode) : pojo.editUsrCode != null) return false;
        if (orderFlag != null ? !orderFlag.equals(pojo.orderFlag) : pojo.orderFlag != null) return false;
        if (stockFlag != null ? !stockFlag.equals(pojo.stockFlag) : pojo.stockFlag != null) return false;
        if (firstName != null ? !firstName.equals(pojo.firstName) : pojo.firstName != null) return false;
        if (lastName != null ? !lastName.equals(pojo.lastName) : pojo.lastName != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (postalcode != null ? !postalcode.equals(pojo.postalcode) : pojo.postalcode != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (mobiletele != null ? !mobiletele.equals(pojo.mobiletele) : pojo.mobiletele != null) return false;
        if (orderNo != null ? !orderNo.equals(pojo.orderNo) : pojo.orderNo != null) return false;
        if (shipType != null ? !shipType.equals(pojo.shipType) : pojo.shipType != null) return false;
        if (selfReplacement != null ? !selfReplacement.equals(pojo.selfReplacement) : pojo.selfReplacement != null) return false;
        if (isPay != null ? !isPay.equals(pojo.isPay) : pojo.isPay != null) return false;
        if (pvAmtEx != null ? !pvAmtEx.equals(pojo.pvAmtEx) : pojo.pvAmtEx != null) return false;
        if (amountEx != null ? !amountEx.equals(pojo.amountEx) : pojo.amountEx != null) return false;
        if (needPay != null ? !needPay.equals(pojo.needPay) : pojo.needPay != null) return false;
        if (whetherPd != null ? !whetherPd.equals(pojo.whetherPd) : pojo.whetherPd != null) return false;
        if (cancelExchange != null ? !cancelExchange.equals(pojo.cancelExchange) : pojo.cancelExchange != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (warehouseNo != null ? warehouseNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUsrCode != null ? createUsrCode.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (checkUsrCode != null ? checkUsrCode.hashCode() : 0);
        result = 31 * result + (checkRemark != null ? checkRemark.hashCode() : 0);
        result = 31 * result + (okTime != null ? okTime.hashCode() : 0);
        result = 31 * result + (okUsrCode != null ? okUsrCode.hashCode() : 0);
        result = 31 * result + (okRemark != null ? okRemark.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        result = 31 * result + (editUsrCode != null ? editUsrCode.hashCode() : 0);
        result = 31 * result + (orderFlag != null ? orderFlag.hashCode() : 0);
        result = 31 * result + (stockFlag != null ? stockFlag.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalcode != null ? postalcode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobiletele != null ? mobiletele.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (shipType != null ? shipType.hashCode() : 0);
        result = 31 * result + (selfReplacement != null ? selfReplacement.hashCode() : 0);
        result = 31 * result + (isPay != null ? isPay.hashCode() : 0);
        result = 31 * result + (pvAmtEx != null ? pvAmtEx.hashCode() : 0);
        result = 31 * result + (amountEx != null ? amountEx.hashCode() : 0);
        result = 31 * result + (needPay != null ? needPay.hashCode() : 0);
        result = 31 * result + (whetherPd != null ? whetherPd.hashCode() : 0);
        
        result = 31 * result + (needPayAmount != null ? needPayAmount.hashCode() : 0);
        result = 31 * result + (selfRemark != null ? selfRemark.hashCode() : 0);
        result = 31 * result + (selfCheckStatus != null ? selfCheckStatus.hashCode() : 0);
        result = 31 * result + (cancelExchange != null ? cancelExchange.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("eoNo").append("='").append(getEoNo()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("warehouseNo").append("='").append(getWarehouseNo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUsrCode").append("='").append(getCreateUsrCode()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("checkUsrCode").append("='").append(getCheckUsrCode()).append("', ");
        sb.append("checkRemark").append("='").append(getCheckRemark()).append("', ");
        sb.append("okTime").append("='").append(getOkTime()).append("', ");
        sb.append("okUsrCode").append("='").append(getOkUsrCode()).append("', ");
        sb.append("okRemark").append("='").append(getOkRemark()).append("', ");
        sb.append("editTime").append("='").append(getEditTime()).append("', ");
        sb.append("editUsrCode").append("='").append(getEditUsrCode()).append("', ");
        sb.append("orderFlag").append("='").append(getOrderFlag()).append("', ");
        sb.append("stockFlag").append("='").append(getStockFlag()).append("', ");
        sb.append("firstName").append("='").append(getFirstName()).append("', ");
        sb.append("lastName").append("='").append(getLastName()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("postalcode").append("='").append(getPostalcode()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("mobiletele").append("='").append(getMobiletele()).append("', ");
        sb.append("orderNo").append("='").append(getOrderNo()).append("', ");
        sb.append("shipType").append("='").append(getShipType()).append("', ");
        sb.append("selfReplacement").append("='").append(getSelfReplacement()).append("', ");
        sb.append("isPay").append("='").append(getIsPay()).append("', ");
        sb.append("pvAmtEx").append("='").append(getPvAmtEx()).append("', ");
        sb.append("amountEx").append("='").append(getAmountEx()).append("', ");
        sb.append("needPay").append("='").append(getNeedPay()).append("', ");
        sb.append("pdExchangeOrderBacks").append("=").append(getPdExchangeOrderBacks()).append(",");
        sb.append("pdExchangeOrderDetails").append("=").append(getPdExchangeOrderDetails()).append(",");
        sb.append("whetherPd").append("='").append(getWhetherPd()).append(",");
        sb.append("needPayAmount").append("='").append(getNeedPayAmount()).append(",");
        sb.append("selfRemark").append("='").append(getSelfRemark()).append(",");
        sb.append("selfCheckStatus").append("='").append(getSelfCheckStatus()).append("'");
        sb.append("cancelExchange").append("='").append(getCancelExchange()).append("'");

        sb.append("]");
      
        return sb.toString();
    }

}
