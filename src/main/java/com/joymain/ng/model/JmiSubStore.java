package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JMI_SUB_STORE")

@XmlRootElement
public class JmiSubStore extends BaseObject implements Serializable {
    private Long id;
    private JmiMember jmiMember;
    private Long province;
    private Long city;
    private Long district;
    private String address;
    private String postalcode;
    private String phone;
    private String mobiletele;
    private String email;
    private Long personQty;
    private String storePhone;
    private String businessArea;
    private Long averageIncome;
    private Long investAmount;
    private Date startDate;
    private String isdeal;
    private String specificBusiness;
    private String checkRemark;
    private String confirmRemark;
    private String confirmStatus;
    private String checkUser;
    private String confirmUser;
    private Date confirmTime;
    private Date createTime;
    private Date checkTime;
    private Date noticeTime;
    private String businessLicense;
    private String contract;
    private String storePic;
    private String addrConfirm;
    private String addrCheck;
    private Date editTime;
    private String addrConfirmUser;
    private Date addrConfirmTime;
    private String addrCheckUser;
    private Date addrCheckTime;

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
    
    @Column(name="ADDRESS", length=500)
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="POSTALCODE", length=20)
    
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
    
    @Column(name="MOBILETELE", length=30)
    
    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    
    @Column(name="EMAIL", length=30)
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="PERSON_QTY", precision=12, scale=0)
    
    public Long getPersonQty() {
        return this.personQty;
    }
    
    public void setPersonQty(Long personQty) {
        this.personQty = personQty;
    }
    
    @Column(name="STORE_PHONE", length=30)
    
    public String getStorePhone() {
        return this.storePhone;
    }
    
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }
    
    @Column(name="BUSINESS_AREA", length=100)
    
    public String getBusinessArea() {
        return this.businessArea;
    }
    
    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
    
    @Column(name="AVERAGE_INCOME", precision=12, scale=0)
    
    public Long getAverageIncome() {
        return this.averageIncome;
    }
    
    public void setAverageIncome(Long averageIncome) {
        this.averageIncome = averageIncome;
    }
    
    @Column(name="INVEST_AMOUNT", precision=12, scale=0)
    
    public Long getInvestAmount() {
        return this.investAmount;
    }
    
    public void setInvestAmount(Long investAmount) {
        this.investAmount = investAmount;
    }
    
    @Column(name="START_DATE", length=7)
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Column(name="ISDEAL", length=1)
    
    public String getIsdeal() {
        return this.isdeal;
    }
    
    public void setIsdeal(String isdeal) {
        this.isdeal = isdeal;
    }
    
    @Column(name="SPECIFIC_BUSINESS", length=500)
    
    public String getSpecificBusiness() {
        return this.specificBusiness;
    }
    
    public void setSpecificBusiness(String specificBusiness) {
        this.specificBusiness = specificBusiness;
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
    
    @Column(name="CONFIRM_STATUS", length=1)
    
    public String getConfirmStatus() {
        return this.confirmStatus;
    }
    
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
    
    @Column(name="CHECK_USER", length=20)
    
    public String getCheckUser() {
        return this.checkUser;
    }
    
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    
    @Column(name="CONFIRM_USER", length=20)
    
    public String getConfirmUser() {
        return this.confirmUser;
    }
    
    public void setConfirmUser(String confirmUser) {
        this.confirmUser = confirmUser;
    }
    
    @Column(name="CONFIRM_TIME", length=7)
    
    public Date getConfirmTime() {
        return this.confirmTime;
    }
    
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }
    
    @Column(name="CREATE_TIME", length=7)
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CHECK_TIME", length=7)
    
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    
    @Column(name="NOTICE_TIME", length=7)
    
    public Date getNoticeTime() {
        return this.noticeTime;
    }
    
    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }
    
    @Column(name="BUSINESS_LICENSE", length=1)
    
    public String getBusinessLicense() {
        return this.businessLicense;
    }
    
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }
    
    @Column(name="CONTRACT", length=1)
    
    public String getContract() {
        return this.contract;
    }
    
    public void setContract(String contract) {
        this.contract = contract;
    }
    
    @Column(name="STORE_PIC", length=1)
    
    public String getStorePic() {
        return this.storePic;
    }
    
    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }
    
    @Column(name="ADDR_CONFIRM", length=1)
    
    public String getAddrConfirm() {
        return this.addrConfirm;
    }
    
    public void setAddrConfirm(String addrConfirm) {
        this.addrConfirm = addrConfirm;
    }
    
    @Column(name="ADDR_CHECK", length=1)
    
    public String getAddrCheck() {
        return this.addrCheck;
    }
    
    public void setAddrCheck(String addrCheck) {
        this.addrCheck = addrCheck;
    }
    
    @Column(name="EDIT_TIME", length=7)
    
    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
    
    @Column(name="ADDR_CONFIRM_USER", length=20)
    
    public String getAddrConfirmUser() {
        return this.addrConfirmUser;
    }
    
    public void setAddrConfirmUser(String addrConfirmUser) {
        this.addrConfirmUser = addrConfirmUser;
    }
    
    @Column(name="ADDR_CONFIRM_TIME", length=7)
    
    public Date getAddrConfirmTime() {
        return this.addrConfirmTime;
    }
    
    public void setAddrConfirmTime(Date addrConfirmTime) {
        this.addrConfirmTime = addrConfirmTime;
    }
    
    @Column(name="ADDR_CHECK_USER", length=20)
    
    public String getAddrCheckUser() {
        return this.addrCheckUser;
    }
    
    public void setAddrCheckUser(String addrCheckUser) {
        this.addrCheckUser = addrCheckUser;
    }
    
    @Column(name="ADDR_CHECK_TIME", length=7)
    
    public Date getAddrCheckTime() {
        return this.addrCheckTime;
    }
    
    public void setAddrCheckTime(Date addrCheckTime) {
        this.addrCheckTime = addrCheckTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiSubStore pojo = (JmiSubStore) o;

        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (postalcode != null ? !postalcode.equals(pojo.postalcode) : pojo.postalcode != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (mobiletele != null ? !mobiletele.equals(pojo.mobiletele) : pojo.mobiletele != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (personQty != null ? !personQty.equals(pojo.personQty) : pojo.personQty != null) return false;
        if (storePhone != null ? !storePhone.equals(pojo.storePhone) : pojo.storePhone != null) return false;
        if (businessArea != null ? !businessArea.equals(pojo.businessArea) : pojo.businessArea != null) return false;
        if (averageIncome != null ? !averageIncome.equals(pojo.averageIncome) : pojo.averageIncome != null) return false;
        if (investAmount != null ? !investAmount.equals(pojo.investAmount) : pojo.investAmount != null) return false;
        if (startDate != null ? !startDate.equals(pojo.startDate) : pojo.startDate != null) return false;
        if (isdeal != null ? !isdeal.equals(pojo.isdeal) : pojo.isdeal != null) return false;
        if (specificBusiness != null ? !specificBusiness.equals(pojo.specificBusiness) : pojo.specificBusiness != null) return false;
        if (checkRemark != null ? !checkRemark.equals(pojo.checkRemark) : pojo.checkRemark != null) return false;
        if (confirmRemark != null ? !confirmRemark.equals(pojo.confirmRemark) : pojo.confirmRemark != null) return false;
        if (confirmStatus != null ? !confirmStatus.equals(pojo.confirmStatus) : pojo.confirmStatus != null) return false;
        if (checkUser != null ? !checkUser.equals(pojo.checkUser) : pojo.checkUser != null) return false;
        if (confirmUser != null ? !confirmUser.equals(pojo.confirmUser) : pojo.confirmUser != null) return false;
        if (confirmTime != null ? !confirmTime.equals(pojo.confirmTime) : pojo.confirmTime != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (noticeTime != null ? !noticeTime.equals(pojo.noticeTime) : pojo.noticeTime != null) return false;
        if (businessLicense != null ? !businessLicense.equals(pojo.businessLicense) : pojo.businessLicense != null) return false;
        if (contract != null ? !contract.equals(pojo.contract) : pojo.contract != null) return false;
        if (storePic != null ? !storePic.equals(pojo.storePic) : pojo.storePic != null) return false;
        if (addrConfirm != null ? !addrConfirm.equals(pojo.addrConfirm) : pojo.addrConfirm != null) return false;
        if (addrCheck != null ? !addrCheck.equals(pojo.addrCheck) : pojo.addrCheck != null) return false;
        if (editTime != null ? !editTime.equals(pojo.editTime) : pojo.editTime != null) return false;
        if (addrConfirmUser != null ? !addrConfirmUser.equals(pojo.addrConfirmUser) : pojo.addrConfirmUser != null) return false;
        if (addrConfirmTime != null ? !addrConfirmTime.equals(pojo.addrConfirmTime) : pojo.addrConfirmTime != null) return false;
        if (addrCheckUser != null ? !addrCheckUser.equals(pojo.addrCheckUser) : pojo.addrCheckUser != null) return false;
        if (addrCheckTime != null ? !addrCheckTime.equals(pojo.addrCheckTime) : pojo.addrCheckTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalcode != null ? postalcode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobiletele != null ? mobiletele.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (personQty != null ? personQty.hashCode() : 0);
        result = 31 * result + (storePhone != null ? storePhone.hashCode() : 0);
        result = 31 * result + (businessArea != null ? businessArea.hashCode() : 0);
        result = 31 * result + (averageIncome != null ? averageIncome.hashCode() : 0);
        result = 31 * result + (investAmount != null ? investAmount.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (isdeal != null ? isdeal.hashCode() : 0);
        result = 31 * result + (specificBusiness != null ? specificBusiness.hashCode() : 0);
        result = 31 * result + (checkRemark != null ? checkRemark.hashCode() : 0);
        result = 31 * result + (confirmRemark != null ? confirmRemark.hashCode() : 0);
        result = 31 * result + (confirmStatus != null ? confirmStatus.hashCode() : 0);
        result = 31 * result + (checkUser != null ? checkUser.hashCode() : 0);
        result = 31 * result + (confirmUser != null ? confirmUser.hashCode() : 0);
        result = 31 * result + (confirmTime != null ? confirmTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (noticeTime != null ? noticeTime.hashCode() : 0);
        result = 31 * result + (businessLicense != null ? businessLicense.hashCode() : 0);
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (storePic != null ? storePic.hashCode() : 0);
        result = 31 * result + (addrConfirm != null ? addrConfirm.hashCode() : 0);
        result = 31 * result + (addrCheck != null ? addrCheck.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        result = 31 * result + (addrConfirmUser != null ? addrConfirmUser.hashCode() : 0);
        result = 31 * result + (addrConfirmTime != null ? addrConfirmTime.hashCode() : 0);
        result = 31 * result + (addrCheckUser != null ? addrCheckUser.hashCode() : 0);
        result = 31 * result + (addrCheckTime != null ? addrCheckTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("postalcode").append("='").append(getPostalcode()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("', ");
        sb.append("mobiletele").append("='").append(getMobiletele()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("personQty").append("='").append(getPersonQty()).append("', ");
        sb.append("storePhone").append("='").append(getStorePhone()).append("', ");
        sb.append("businessArea").append("='").append(getBusinessArea()).append("', ");
        sb.append("averageIncome").append("='").append(getAverageIncome()).append("', ");
        sb.append("investAmount").append("='").append(getInvestAmount()).append("', ");
        sb.append("startDate").append("='").append(getStartDate()).append("', ");
        sb.append("isdeal").append("='").append(getIsdeal()).append("', ");
        sb.append("specificBusiness").append("='").append(getSpecificBusiness()).append("', ");
        sb.append("checkRemark").append("='").append(getCheckRemark()).append("', ");
        sb.append("confirmRemark").append("='").append(getConfirmRemark()).append("', ");
        sb.append("confirmStatus").append("='").append(getConfirmStatus()).append("', ");
        sb.append("checkUser").append("='").append(getCheckUser()).append("', ");
        sb.append("confirmUser").append("='").append(getConfirmUser()).append("', ");
        sb.append("confirmTime").append("='").append(getConfirmTime()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("noticeTime").append("='").append(getNoticeTime()).append("', ");
        sb.append("businessLicense").append("='").append(getBusinessLicense()).append("', ");
        sb.append("contract").append("='").append(getContract()).append("', ");
        sb.append("storePic").append("='").append(getStorePic()).append("', ");
        sb.append("addrConfirm").append("='").append(getAddrConfirm()).append("', ");
        sb.append("addrCheck").append("='").append(getAddrCheck()).append("', ");
        sb.append("editTime").append("='").append(getEditTime()).append("', ");
        sb.append("addrConfirmUser").append("='").append(getAddrConfirmUser()).append("', ");
        sb.append("addrConfirmTime").append("='").append(getAddrConfirmTime()).append("', ");
        sb.append("addrCheckUser").append("='").append(getAddrCheckUser()).append("', ");
        sb.append("addrCheckTime").append("='").append(getAddrCheckTime()).append("'");
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
