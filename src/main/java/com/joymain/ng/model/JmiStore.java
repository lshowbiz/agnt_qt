package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;



@Entity
@Table(name="JMI_STORE")

@XmlRootElement
public class JmiStore extends BaseObject implements Serializable {
    private Long id;
    private JmiMember jmiMember;
    private String postalcode;
    private String mailAddr;
    private String mobiletele;
    private String faxtele;
    private String email;
    private String subStoreAddr;
    private Long province;
    private Long city;
    private Long district;
    private BigDecimal businessArea;
    private BigDecimal personTotal;
    private String cityType;
    private BigDecimal averageIncome;
    private String isdeal;
    private String business;
    private Date createTime;
    private String createUser;
    private Date checkTime;
    private String checkUser;
    private Date confirmTime;
    private String confirmUser;
    private String checkStatus;
    private String confirmStatus;
    private String checkRemark;
    private String confirmRemark;
    private Date editTime;
    private Date orderTime;
    private Integer honorStar;
    private String address;
    private Date orderDate;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    
    @Column(name="POSTALCODE", length=10)
    
    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    
    @Column(name="MAIL_ADDR", length=500)
    
    public String getMailAddr() {
        return this.mailAddr;
    }
    
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }
    
    @Column(name="MOBILETELE", length=30)
    
    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    
    @Column(name="FAXTELE", length=30)
    
    public String getFaxtele() {
        return this.faxtele;
    }
    
    public void setFaxtele(String faxtele) {
        this.faxtele = faxtele;
    }
    
    @Column(name="EMAIL", length=40)
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="SUB_STORE_ADDR", length=500)
    
    public String getSubStoreAddr() {
        return this.subStoreAddr;
    }
    
    public void setSubStoreAddr(String subStoreAddr) {
        this.subStoreAddr = subStoreAddr;
    }
    
    @Column(name="PROVINCE", precision=12, scale=0)
    
    public Long getProvince() {
        return this.province;
    }
    
    public void setProvince(Long province) {
        this.province = province;
    }
    
    @Column(name="CITY", precision=12, scale=0)
    
    public Long getCity() {
        return this.city;
    }
    
    public void setCity(Long city) {
        this.city = city;
    }
    
    @Column(name="DISTRICT", precision=12, scale=0)
    
    public Long getDistrict() {
        return this.district;
    }
    
    public void setDistrict(Long district) {
        this.district = district;
    }
    
    @Column(name="BUSINESS_AREA", precision=22, scale=0)
    
    public BigDecimal getBusinessArea() {
        return this.businessArea;
    }
    
    public void setBusinessArea(BigDecimal businessArea) {
        this.businessArea = businessArea;
    }
    
    @Column(name="PERSON_TOTAL", precision=22, scale=0)
    
    public BigDecimal getPersonTotal() {
        return this.personTotal;
    }
    
    public void setPersonTotal(BigDecimal personTotal) {
        this.personTotal = personTotal;
    }
    
    @Column(name="CITY_TYPE", length=1)
    
    public String getCityType() {
        return this.cityType;
    }
    
    public void setCityType(String cityType) {
        this.cityType = cityType;
    }
    
    @Column(name="AVERAGE_INCOME", precision=22, scale=0)
    
    public BigDecimal getAverageIncome() {
        return this.averageIncome;
    }
    
    public void setAverageIncome(BigDecimal averageIncome) {
        this.averageIncome = averageIncome;
    }
    
    @Column(name="ISDEAL", length=1)
    
    public String getIsdeal() {
        return this.isdeal;
    }
    
    public void setIsdeal(String isdeal) {
        this.isdeal = isdeal;
    }
    
    @Column(name="BUSINESS", length=100)
    
    public String getBusiness() {
        return this.business;
    }
    
    public void setBusiness(String business) {
        this.business = business;
    }
    
    @Column(name="CREATE_TIME", length=7)
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_USER", length=20)
    
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    @Column(name="CHECK_TIME", length=7)
    
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    
    @Column(name="CHECK_USER", length=20)
    
    public String getCheckUser() {
        return this.checkUser;
    }
    
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    
    @Column(name="CONFIRM_TIME", length=7)
    
    public Date getConfirmTime() {
        return this.confirmTime;
    }
    
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }
    
    @Column(name="CONFIRM_USER", length=20)
    
    public String getConfirmUser() {
        return this.confirmUser;
    }
    
    public void setConfirmUser(String confirmUser) {
        this.confirmUser = confirmUser;
    }
    
    @Column(name="CHECK_STATUS", length=1)
    
    public String getCheckStatus() {
        return this.checkStatus;
    }
    
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    
    @Column(name="CONFIRM_STATUS", length=1)
    
    public String getConfirmStatus() {
        return this.confirmStatus;
    }
    
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
    
    @Column(name="CHECK_REMARK", length=500)
    
    public String getCheckRemark() {
        return this.checkRemark;
    }
    
    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }
    
    @Column(name="CONFIRM_REMARK", length=500)
    
    public String getConfirmRemark() {
        return this.confirmRemark;
    }
    
    public void setConfirmRemark(String confirmRemark) {
        this.confirmRemark = confirmRemark;
    }
    
    @Column(name="EDIT_TIME", length=7)
    
    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
    
    @Column(name="ORDER_TIME", length=7)
    
    public Date getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    
    @Column(name="HONOR_STAR", precision=2, scale=0)
    
    public Integer getHonorStar() {
        return this.honorStar;
    }
    
    public void setHonorStar(Integer honorStar) {
        this.honorStar = honorStar;
    }
    
    @Column(name="ADDRESS", length=300)
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="ORDER_DATE", length=7)
    
    public Date getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiStore pojo = (JmiStore) o;

        if (postalcode != null ? !postalcode.equals(pojo.postalcode) : pojo.postalcode != null) return false;
        if (mailAddr != null ? !mailAddr.equals(pojo.mailAddr) : pojo.mailAddr != null) return false;
        if (mobiletele != null ? !mobiletele.equals(pojo.mobiletele) : pojo.mobiletele != null) return false;
        if (faxtele != null ? !faxtele.equals(pojo.faxtele) : pojo.faxtele != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (subStoreAddr != null ? !subStoreAddr.equals(pojo.subStoreAddr) : pojo.subStoreAddr != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (businessArea != null ? !businessArea.equals(pojo.businessArea) : pojo.businessArea != null) return false;
        if (personTotal != null ? !personTotal.equals(pojo.personTotal) : pojo.personTotal != null) return false;
        if (cityType != null ? !cityType.equals(pojo.cityType) : pojo.cityType != null) return false;
        if (averageIncome != null ? !averageIncome.equals(pojo.averageIncome) : pojo.averageIncome != null) return false;
        if (isdeal != null ? !isdeal.equals(pojo.isdeal) : pojo.isdeal != null) return false;
        if (business != null ? !business.equals(pojo.business) : pojo.business != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUser != null ? !createUser.equals(pojo.createUser) : pojo.createUser != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (checkUser != null ? !checkUser.equals(pojo.checkUser) : pojo.checkUser != null) return false;
        if (confirmTime != null ? !confirmTime.equals(pojo.confirmTime) : pojo.confirmTime != null) return false;
        if (confirmUser != null ? !confirmUser.equals(pojo.confirmUser) : pojo.confirmUser != null) return false;
        if (checkStatus != null ? !checkStatus.equals(pojo.checkStatus) : pojo.checkStatus != null) return false;
        if (confirmStatus != null ? !confirmStatus.equals(pojo.confirmStatus) : pojo.confirmStatus != null) return false;
        if (checkRemark != null ? !checkRemark.equals(pojo.checkRemark) : pojo.checkRemark != null) return false;
        if (confirmRemark != null ? !confirmRemark.equals(pojo.confirmRemark) : pojo.confirmRemark != null) return false;
        if (editTime != null ? !editTime.equals(pojo.editTime) : pojo.editTime != null) return false;
        if (orderTime != null ? !orderTime.equals(pojo.orderTime) : pojo.orderTime != null) return false;
        if (honorStar != null ? !honorStar.equals(pojo.honorStar) : pojo.honorStar != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (orderDate != null ? !orderDate.equals(pojo.orderDate) : pojo.orderDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (postalcode != null ? postalcode.hashCode() : 0);
        result = 31 * result + (mailAddr != null ? mailAddr.hashCode() : 0);
        result = 31 * result + (mobiletele != null ? mobiletele.hashCode() : 0);
        result = 31 * result + (faxtele != null ? faxtele.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (subStoreAddr != null ? subStoreAddr.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (businessArea != null ? businessArea.hashCode() : 0);
        result = 31 * result + (personTotal != null ? personTotal.hashCode() : 0);
        result = 31 * result + (cityType != null ? cityType.hashCode() : 0);
        result = 31 * result + (averageIncome != null ? averageIncome.hashCode() : 0);
        result = 31 * result + (isdeal != null ? isdeal.hashCode() : 0);
        result = 31 * result + (business != null ? business.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (checkUser != null ? checkUser.hashCode() : 0);
        result = 31 * result + (confirmTime != null ? confirmTime.hashCode() : 0);
        result = 31 * result + (confirmUser != null ? confirmUser.hashCode() : 0);
        result = 31 * result + (checkStatus != null ? checkStatus.hashCode() : 0);
        result = 31 * result + (confirmStatus != null ? confirmStatus.hashCode() : 0);
        result = 31 * result + (checkRemark != null ? checkRemark.hashCode() : 0);
        result = 31 * result + (confirmRemark != null ? confirmRemark.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (honorStar != null ? honorStar.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("postalcode").append("='").append(getPostalcode()).append("', ");
        sb.append("mailAddr").append("='").append(getMailAddr()).append("', ");
        sb.append("mobiletele").append("='").append(getMobiletele()).append("', ");
        sb.append("faxtele").append("='").append(getFaxtele()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("subStoreAddr").append("='").append(getSubStoreAddr()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("businessArea").append("='").append(getBusinessArea()).append("', ");
        sb.append("personTotal").append("='").append(getPersonTotal()).append("', ");
        sb.append("cityType").append("='").append(getCityType()).append("', ");
        sb.append("averageIncome").append("='").append(getAverageIncome()).append("', ");
        sb.append("isdeal").append("='").append(getIsdeal()).append("', ");
        sb.append("business").append("='").append(getBusiness()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUser").append("='").append(getCreateUser()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("checkUser").append("='").append(getCheckUser()).append("', ");
        sb.append("confirmTime").append("='").append(getConfirmTime()).append("', ");
        sb.append("confirmUser").append("='").append(getConfirmUser()).append("', ");
        sb.append("checkStatus").append("='").append(getCheckStatus()).append("', ");
        sb.append("confirmStatus").append("='").append(getConfirmStatus()).append("', ");
        sb.append("checkRemark").append("='").append(getCheckRemark()).append("', ");
        sb.append("confirmRemark").append("='").append(getConfirmRemark()).append("', ");
        sb.append("editTime").append("='").append(getEditTime()).append("', ");
        sb.append("orderTime").append("='").append(getOrderTime()).append("', ");
        sb.append("honorStar").append("='").append(getHonorStar()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("orderDate").append("='").append(getOrderDate()).append("'");
        sb.append("]");
      
        return sb.toString();
    }


    @ManyToOne
    @JoinColumn(name = "USER_CODE", nullable = false,  updatable = false)
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

}
