package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JMI_TICKET")

@XmlRootElement
public class JmiTicket extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private String ticketType;
    private String applyUserCode;
    private String userName;
    private String papernumber;
    private String censusProvince;
    private String censusCity;
    private String censusDistrict;
    private String censusAddress;
    private String province;
    private String city;
    private String district;
    private String address;
    private String mobiletele;
    private String remark;
    private Date createTime;

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
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="TICKET_TYPE", length=2)
    public String getTicketType() {
        return this.ticketType;
    }
    
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    
    @Column(name="APPLY_USER_CODE", length=20)
    public String getApplyUserCode() {
        return this.applyUserCode;
    }
    
    public void setApplyUserCode(String applyUserCode) {
        this.applyUserCode = applyUserCode;
    }
    
    @Column(name="USER_NAME", length=300)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="PAPERNUMBER", length=70)
    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }
    
    @Column(name="CENSUS_PROVINCE", length=20)
    public String getCensusProvince() {
        return this.censusProvince;
    }
    
    public void setCensusProvince(String censusProvince) {
        this.censusProvince = censusProvince;
    }
    
    @Column(name="CENSUS_CITY", length=20)
    public String getCensusCity() {
        return this.censusCity;
    }
    
    public void setCensusCity(String censusCity) {
        this.censusCity = censusCity;
    }
    
    @Column(name="CENSUS_DISTRICT", length=20)
    public String getCensusDistrict() {
        return this.censusDistrict;
    }
    
    public void setCensusDistrict(String censusDistrict) {
        this.censusDistrict = censusDistrict;
    }
    
    @Column(name="CENSUS_ADDRESS", length=500)
    public String getCensusAddress() {
        return this.censusAddress;
    }
    
    public void setCensusAddress(String censusAddress) {
        this.censusAddress = censusAddress;
    }
    
    @Column(name="PROVINCE", length=20)
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=20)
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
    
    @Column(name="MOBILETELE", length=30)
    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    
    @Column(name="REMARK", length=500)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiTicket pojo = (JmiTicket) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (ticketType != null ? !ticketType.equals(pojo.ticketType) : pojo.ticketType != null) return false;
        if (applyUserCode != null ? !applyUserCode.equals(pojo.applyUserCode) : pojo.applyUserCode != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;
        if (papernumber != null ? !papernumber.equals(pojo.papernumber) : pojo.papernumber != null) return false;
        if (censusProvince != null ? !censusProvince.equals(pojo.censusProvince) : pojo.censusProvince != null) return false;
        if (censusCity != null ? !censusCity.equals(pojo.censusCity) : pojo.censusCity != null) return false;
        if (censusDistrict != null ? !censusDistrict.equals(pojo.censusDistrict) : pojo.censusDistrict != null) return false;
        if (censusAddress != null ? !censusAddress.equals(pojo.censusAddress) : pojo.censusAddress != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (mobiletele != null ? !mobiletele.equals(pojo.mobiletele) : pojo.mobiletele != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (ticketType != null ? ticketType.hashCode() : 0);
        result = 31 * result + (applyUserCode != null ? applyUserCode.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (papernumber != null ? papernumber.hashCode() : 0);
        result = 31 * result + (censusProvince != null ? censusProvince.hashCode() : 0);
        result = 31 * result + (censusCity != null ? censusCity.hashCode() : 0);
        result = 31 * result + (censusDistrict != null ? censusDistrict.hashCode() : 0);
        result = 31 * result + (censusAddress != null ? censusAddress.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (mobiletele != null ? mobiletele.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("ticketType").append("='").append(getTicketType()).append("', ");
        sb.append("applyUserCode").append("='").append(getApplyUserCode()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("papernumber").append("='").append(getPapernumber()).append("', ");
        sb.append("censusProvince").append("='").append(getCensusProvince()).append("', ");
        sb.append("censusCity").append("='").append(getCensusCity()).append("', ");
        sb.append("censusDistrict").append("='").append(getCensusDistrict()).append("', ");
        sb.append("censusAddress").append("='").append(getCensusAddress()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("mobiletele").append("='").append(getMobiletele()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
