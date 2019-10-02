package com.joymain.ng.model;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="PD_SEND_INFO")

@XmlRootElement
public class PdSendInfo extends BaseObject implements Serializable {
    private String siNo;
    private Long version;
    private String companyCode;
    private Long amount;
    private String warehouseNo;
    //private String customCode;
    private String recipientName;
    private String recipientCaNo;
    private String city;
    private String recipientAddr;
    private String recipientZip;
    private String recipientPhone;
    private String email;
    private String shNo;
    private String shipType;
    private String createUsrCode;
    private Date createTime;
    private String remark;
    private Date checkTime;
    private String checkUsrCode;
    private String checkRemark;
    private Date okTime;
    private String okUsrCode;
    private String okRemark;
    private String editUsrCode;
    private Date editTime;
    private String trackingNo;
    private String orderNo;
    private Long orderFlag;
    private String countryCode;
    private String recipientMobile;
    private String stockFlag;
    private Long weight;
    private Long volume;
    private String orderType;
    private String subOrderType;
    private Long townId;
    private String district;
    private String shipmentIdentificationNumber;
    private String shipmentDigest;
    private String recipientFirstName;
    private String recipientLastName;
    private String cityName;
    private String codFlag;
    private Date recipientTime;
    private String hurryFlag;
    
    //Modify By WuCF 20140711 条形码
    private String barCode;
    
    //会员和商品详细
    private JsysUser sysUser=new JsysUser();
    private Set<PdSendInfoDetail> pdSendInfoDetail = new TreeSet(); 
    
    @Id      @Column(name="SI_NO", unique=true, nullable=false, length=20) @DocumentId    
    public String getSiNo() {
        return this.siNo;
    }
    
    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }
    @Version
    @Column(name="VERSION", precision=10, scale=0)
    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="AMOUNT", nullable=false, precision=22, scale=0)
    public Long getAmount() {
        return this.amount;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    @Column(name="WAREHOUSE_NO", nullable=false, length=10)
    public String getWarehouseNo() {
        return this.warehouseNo;
    }
    
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }
    
//    @Column(name="CUSTOM_CODE", nullable=false, length=20)
//    public String getCustomCode() {
//        return this.customCode;
//    }
//    
//    public void setCustomCode(String customCode) {
//        this.customCode = customCode;
//    }
    
    @Column(name="RECIPIENT_NAME", nullable=false, length=200)
    public String getRecipientName() {
        return this.recipientName;
    }
    
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    
    @Column(name="RECIPIENT_CA_NO", nullable=false, length=10)
    public String getRecipientCaNo() {
        return this.recipientCaNo;
    }
    
    public void setRecipientCaNo(String recipientCaNo) {
        this.recipientCaNo = recipientCaNo;
    }
    
    @Column(name="CITY", length=200)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="RECIPIENT_ADDR", nullable=false, length=500)
    public String getRecipientAddr() {
        return this.recipientAddr;
    }
    
    public void setRecipientAddr(String recipientAddr) {
        this.recipientAddr = recipientAddr;
    }
    
    @Column(name="RECIPIENT_ZIP", nullable=false, length=10)
    public String getRecipientZip() {
        return this.recipientZip;
    }
    
    public void setRecipientZip(String recipientZip) {
        this.recipientZip = recipientZip;
    }
    
    @Column(name="RECIPIENT_PHONE", nullable=false, length=254)
    public String getRecipientPhone() {
        return this.recipientPhone;
    }
    
    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }
    
    @Column(name="EMAIL", length=200)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="SH_NO", nullable=false, length=10)
    public String getShNo() {
        return this.shNo;
    }
    
    public void setShNo(String shNo) {
        this.shNo = shNo;
    }
    
    @Column(name="SHIP_TYPE", length=4)
    public String getShipType() {
        return this.shipType;
    }
    
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
    
    @Column(name="CREATE_USR_CODE", nullable=false, length=20)
    public String getCreateUsrCode() {
        return this.createUsrCode;
    }
    
    public void setCreateUsrCode(String createUsrCode) {
        this.createUsrCode = createUsrCode;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Temporal(TemporalType.DATE)
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
    @Temporal(TemporalType.DATE)
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
    
    @Column(name="EDIT_USR_CODE", length=20)
    public String getEditUsrCode() {
        return this.editUsrCode;
    }
    
    public void setEditUsrCode(String editUsrCode) {
        this.editUsrCode = editUsrCode;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EDIT_TIME", length=7)
    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
    
    @Column(name="TRACKING_NO", length=200)
    public String getTrackingNo() {
        return this.trackingNo;
    }
    
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
    
    @Column(name="ORDER_NO", length=20)
    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    @Column(name="ORDER_FLAG", precision=2, scale=0)
    public Long getOrderFlag() {
        return this.orderFlag;
    }
    
    public void setOrderFlag(Long orderFlag) {
        this.orderFlag = orderFlag;
    }
    
    @Column(name="COUNTRY_CODE", length=2)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    @Column(name="RECIPIENT_MOBILE", length=20)
    public String getRecipientMobile() {
        return this.recipientMobile;
    }
    
    public void setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
    }
    
    @Column(name="STOCK_FLAG", length=1)
    public String getStockFlag() {
        return this.stockFlag;
    }
    
    public void setStockFlag(String stockFlag) {
        this.stockFlag = stockFlag;
    }
    
    @Column(name="WEIGHT", precision=10)
    public Long getWeight() {
        return this.weight;
    }
    
    public void setWeight(Long weight) {
        this.weight = weight;
    }
    
    @Column(name="VOLUME", precision=15, scale=6)
    public Long getVolume() {
        return this.volume;
    }
    
    public void setVolume(Long volume) {
        this.volume = volume;
    }
    
    @Column(name="ORDER_TYPE", length=4)
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    @Column(name="SUB_ORDER_TYPE", length=5)
    public String getSubOrderType() {
        return this.subOrderType;
    }
    
    public void setSubOrderType(String subOrderType) {
        this.subOrderType = subOrderType;
    }
    
    @Column(name="TOWN_ID", precision=12, scale=0)
    public Long getTownId() {
        return this.townId;
    }
    
    public void setTownId(Long townId) {
        this.townId = townId;
    }
    
    @Column(name="DISTRICT", length=20)
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    @Column(name="SHIPMENT_IDENTIFICATION_NUMBER", length=50)
    public String getShipmentIdentificationNumber() {
        return this.shipmentIdentificationNumber;
    }
    
    public void setShipmentIdentificationNumber(String shipmentIdentificationNumber) {
        this.shipmentIdentificationNumber = shipmentIdentificationNumber;
    }
    
    @Column(name="SHIPMENT_DIGEST")
    public String getShipmentDigest() {
        return this.shipmentDigest;
    }
    
    public void setShipmentDigest(String shipmentDigest) {
        this.shipmentDigest = shipmentDigest;
    }
    
    @Column(name="RECIPIENT_FIRST_NAME", length=100)
    public String getRecipientFirstName() {
        return this.recipientFirstName;
    }
    
    public void setRecipientFirstName(String recipientFirstName) {
        this.recipientFirstName = recipientFirstName;
    }
    
    @Column(name="RECIPIENT_LAST_NAME", length=100)
    public String getRecipientLastName() {
        return this.recipientLastName;
    }
    
    public void setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }
    
    @Column(name="CITY_NAME", length=200)
    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    @Column(name="COD_FLAG", length=2)
    public String getCodFlag() {
        return this.codFlag;
    }
    
    public void setCodFlag(String codFlag) {
        this.codFlag = codFlag;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="RECIPIENT_TIME", length=7)
    public Date getRecipientTime() {
        return this.recipientTime;
    }
    
    public void setRecipientTime(Date recipientTime) {
        this.recipientTime = recipientTime;
    }
    
    @Column(name="HURRY_FLAG", length=5)
    public String getHurryFlag() {
        return this.hurryFlag;
    }
    
    public void setHurryFlag(String hurryFlag) {
        this.hurryFlag = hurryFlag;
    }
    
    @Column(name="BAR_CODE", length=200)
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@OneToMany(mappedBy = "pdSendInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<PdSendInfoDetail> getPdSendInfoDetail() {
		return pdSendInfoDetail;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOM_CODE")
	@JsonIgnore
	public JsysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(JsysUser sysUser) {
		this.sysUser = sysUser;
	}

	public void setPdSendInfoDetail(Set<PdSendInfoDetail> pdSendInfoDetail) {
		this.pdSendInfoDetail = pdSendInfoDetail;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdSendInfo pojo = (PdSendInfo) o;

        if (version != null ? !version.equals(pojo.version) : pojo.version != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (warehouseNo != null ? !warehouseNo.equals(pojo.warehouseNo) : pojo.warehouseNo != null) return false;
        if (recipientName != null ? !recipientName.equals(pojo.recipientName) : pojo.recipientName != null) return false;
        if (recipientCaNo != null ? !recipientCaNo.equals(pojo.recipientCaNo) : pojo.recipientCaNo != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (recipientAddr != null ? !recipientAddr.equals(pojo.recipientAddr) : pojo.recipientAddr != null) return false;
        if (recipientZip != null ? !recipientZip.equals(pojo.recipientZip) : pojo.recipientZip != null) return false;
        if (recipientPhone != null ? !recipientPhone.equals(pojo.recipientPhone) : pojo.recipientPhone != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (shNo != null ? !shNo.equals(pojo.shNo) : pojo.shNo != null) return false;
        if (shipType != null ? !shipType.equals(pojo.shipType) : pojo.shipType != null) return false;
        if (createUsrCode != null ? !createUsrCode.equals(pojo.createUsrCode) : pojo.createUsrCode != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (checkUsrCode != null ? !checkUsrCode.equals(pojo.checkUsrCode) : pojo.checkUsrCode != null) return false;
        if (checkRemark != null ? !checkRemark.equals(pojo.checkRemark) : pojo.checkRemark != null) return false;
        if (okTime != null ? !okTime.equals(pojo.okTime) : pojo.okTime != null) return false;
        if (okUsrCode != null ? !okUsrCode.equals(pojo.okUsrCode) : pojo.okUsrCode != null) return false;
        if (okRemark != null ? !okRemark.equals(pojo.okRemark) : pojo.okRemark != null) return false;
        if (editUsrCode != null ? !editUsrCode.equals(pojo.editUsrCode) : pojo.editUsrCode != null) return false;
        if (editTime != null ? !editTime.equals(pojo.editTime) : pojo.editTime != null) return false;
        if (trackingNo != null ? !trackingNo.equals(pojo.trackingNo) : pojo.trackingNo != null) return false;
        if (orderNo != null ? !orderNo.equals(pojo.orderNo) : pojo.orderNo != null) return false;
        if (orderFlag != null ? !orderFlag.equals(pojo.orderFlag) : pojo.orderFlag != null) return false;
        if (countryCode != null ? !countryCode.equals(pojo.countryCode) : pojo.countryCode != null) return false;
        if (recipientMobile != null ? !recipientMobile.equals(pojo.recipientMobile) : pojo.recipientMobile != null) return false;
        if (stockFlag != null ? !stockFlag.equals(pojo.stockFlag) : pojo.stockFlag != null) return false;
        if (weight != null ? !weight.equals(pojo.weight) : pojo.weight != null) return false;
        if (volume != null ? !volume.equals(pojo.volume) : pojo.volume != null) return false;
        if (orderType != null ? !orderType.equals(pojo.orderType) : pojo.orderType != null) return false;
        if (subOrderType != null ? !subOrderType.equals(pojo.subOrderType) : pojo.subOrderType != null) return false;
        if (townId != null ? !townId.equals(pojo.townId) : pojo.townId != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (shipmentIdentificationNumber != null ? !shipmentIdentificationNumber.equals(pojo.shipmentIdentificationNumber) : pojo.shipmentIdentificationNumber != null) return false;
        if (shipmentDigest != null ? !shipmentDigest.equals(pojo.shipmentDigest) : pojo.shipmentDigest != null) return false;
        if (recipientFirstName != null ? !recipientFirstName.equals(pojo.recipientFirstName) : pojo.recipientFirstName != null) return false;
        if (recipientLastName != null ? !recipientLastName.equals(pojo.recipientLastName) : pojo.recipientLastName != null) return false;
        if (cityName != null ? !cityName.equals(pojo.cityName) : pojo.cityName != null) return false;
        if (codFlag != null ? !codFlag.equals(pojo.codFlag) : pojo.codFlag != null) return false;
        if (recipientTime != null ? !recipientTime.equals(pojo.recipientTime) : pojo.recipientTime != null) return false;
        if (hurryFlag != null ? !hurryFlag.equals(pojo.hurryFlag) : pojo.hurryFlag != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (version != null ? version.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (warehouseNo != null ? warehouseNo.hashCode() : 0);
        result = 31 * result + (recipientName != null ? recipientName.hashCode() : 0);
        result = 31 * result + (recipientCaNo != null ? recipientCaNo.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (recipientAddr != null ? recipientAddr.hashCode() : 0);
        result = 31 * result + (recipientZip != null ? recipientZip.hashCode() : 0);
        result = 31 * result + (recipientPhone != null ? recipientPhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (shNo != null ? shNo.hashCode() : 0);
        result = 31 * result + (shipType != null ? shipType.hashCode() : 0);
        result = 31 * result + (createUsrCode != null ? createUsrCode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (checkUsrCode != null ? checkUsrCode.hashCode() : 0);
        result = 31 * result + (checkRemark != null ? checkRemark.hashCode() : 0);
        result = 31 * result + (okTime != null ? okTime.hashCode() : 0);
        result = 31 * result + (okUsrCode != null ? okUsrCode.hashCode() : 0);
        result = 31 * result + (okRemark != null ? okRemark.hashCode() : 0);
        result = 31 * result + (editUsrCode != null ? editUsrCode.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        result = 31 * result + (trackingNo != null ? trackingNo.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (orderFlag != null ? orderFlag.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (recipientMobile != null ? recipientMobile.hashCode() : 0);
        result = 31 * result + (stockFlag != null ? stockFlag.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (subOrderType != null ? subOrderType.hashCode() : 0);
        result = 31 * result + (townId != null ? townId.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (shipmentIdentificationNumber != null ? shipmentIdentificationNumber.hashCode() : 0);
        result = 31 * result + (shipmentDigest != null ? shipmentDigest.hashCode() : 0);
        result = 31 * result + (recipientFirstName != null ? recipientFirstName.hashCode() : 0);
        result = 31 * result + (recipientLastName != null ? recipientLastName.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (codFlag != null ? codFlag.hashCode() : 0);
        result = 31 * result + (recipientTime != null ? recipientTime.hashCode() : 0);
        result = 31 * result + (hurryFlag != null ? hurryFlag.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("siNo").append("='").append(getSiNo()).append("', ");
        sb.append("version").append("='").append(getVersion()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("warehouseNo").append("='").append(getWarehouseNo()).append("', ");
        sb.append("recipientName").append("='").append(getRecipientName()).append("', ");
        sb.append("recipientCaNo").append("='").append(getRecipientCaNo()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("recipientAddr").append("='").append(getRecipientAddr()).append("', ");
        sb.append("recipientZip").append("='").append(getRecipientZip()).append("', ");
        sb.append("recipientPhone").append("='").append(getRecipientPhone()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("shNo").append("='").append(getShNo()).append("', ");
        sb.append("shipType").append("='").append(getShipType()).append("', ");
        sb.append("createUsrCode").append("='").append(getCreateUsrCode()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("checkUsrCode").append("='").append(getCheckUsrCode()).append("', ");
        sb.append("checkRemark").append("='").append(getCheckRemark()).append("', ");
        sb.append("okTime").append("='").append(getOkTime()).append("', ");
        sb.append("okUsrCode").append("='").append(getOkUsrCode()).append("', ");
        sb.append("okRemark").append("='").append(getOkRemark()).append("', ");
        sb.append("editUsrCode").append("='").append(getEditUsrCode()).append("', ");
        sb.append("editTime").append("='").append(getEditTime()).append("', ");
        sb.append("trackingNo").append("='").append(getTrackingNo()).append("', ");
        sb.append("orderNo").append("='").append(getOrderNo()).append("', ");
        sb.append("orderFlag").append("='").append(getOrderFlag()).append("', ");
        sb.append("countryCode").append("='").append(getCountryCode()).append("', ");
        sb.append("recipientMobile").append("='").append(getRecipientMobile()).append("', ");
        sb.append("stockFlag").append("='").append(getStockFlag()).append("', ");
        sb.append("weight").append("='").append(getWeight()).append("', ");
        sb.append("volume").append("='").append(getVolume()).append("', ");
        sb.append("orderType").append("='").append(getOrderType()).append("', ");
        sb.append("subOrderType").append("='").append(getSubOrderType()).append("', ");
        sb.append("townId").append("='").append(getTownId()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("shipmentIdentificationNumber").append("='").append(getShipmentIdentificationNumber()).append("', ");
        sb.append("shipmentDigest").append("='").append(getShipmentDigest()).append("', ");
        sb.append("recipientFirstName").append("='").append(getRecipientFirstName()).append("', ");
        sb.append("recipientLastName").append("='").append(getRecipientLastName()).append("', ");
        sb.append("cityName").append("='").append(getCityName()).append("', ");
        sb.append("codFlag").append("='").append(getCodFlag()).append("', ");
        sb.append("recipientTime").append("='").append(getRecipientTime()).append("', ");
        sb.append("hurryFlag").append("='").append(getHurryFlag()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
