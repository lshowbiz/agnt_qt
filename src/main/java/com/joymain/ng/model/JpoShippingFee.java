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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="JPO_SHIPPING_FEE")

@XmlRootElement
public class JpoShippingFee extends BaseObject implements Serializable {
    private Long jpsId;
    private String province;
    private String city;
    private String district;
    private String countryCode;
    private String shippingType;
    private BigDecimal shippingFee;
    private Date createTime;
    private BigDecimal shippingRefee;
    private BigDecimal shippingWeight;
    private BigDecimal shippingReweight;

    @Id      @Column(name="JPS_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getJpsId() {
        return this.jpsId;
    }
    
    public void setJpsId(Long jpsId) {
        this.jpsId = jpsId;
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
    
    @Column(name="COUNTRY_CODE", nullable=false, length=2)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    @Column(name="SHIPPING_TYPE", nullable=false, length=10)
    public String getShippingType() {
        return this.shippingType;
    }
    
    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }
    
    @Column(name="SHIPPING_FEE", nullable=false, precision=18)
    public BigDecimal getShippingFee() {
        return this.shippingFee;
    }
    
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="SHIPPING_REFEE", precision=18)
    public BigDecimal getShippingRefee() {
        return this.shippingRefee;
    }
    
    public void setShippingRefee(BigDecimal shippingRefee) {
        this.shippingRefee = shippingRefee;
    }
    
    @Column(name="SHIPPING_WEIGHT", precision=18)
    public BigDecimal getShippingWeight() {
        return this.shippingWeight;
    }
    
    public void setShippingWeight(BigDecimal shippingWeight) {
        this.shippingWeight = shippingWeight;
    }
    
    @Column(name="SHIPPING_REWEIGHT", precision=18)
    public BigDecimal getShippingReweight() {
        return this.shippingReweight;
    }
    
    public void setShippingReweight(BigDecimal shippingReweight) {
        this.shippingReweight = shippingReweight;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoShippingFee pojo = (JpoShippingFee) o;
        if (jpsId != null ? !jpsId.equals(pojo.jpsId) : pojo.jpsId != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (countryCode != null ? !countryCode.equals(pojo.countryCode) : pojo.countryCode != null) return false;
        if (shippingType != null ? !shippingType.equals(pojo.shippingType) : pojo.shippingType != null) return false;
        if (shippingFee != null ? !shippingFee.equals(pojo.shippingFee) : pojo.shippingFee != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (shippingRefee != null ? !shippingRefee.equals(pojo.shippingRefee) : pojo.shippingRefee != null) return false;
        if (shippingWeight != null ? !shippingWeight.equals(pojo.shippingWeight) : pojo.shippingWeight != null) return false;
        if (shippingReweight != null ? !shippingReweight.equals(pojo.shippingReweight) : pojo.shippingReweight != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (jpsId != null ? jpsId.hashCode() : 0);
        result = (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (shippingType != null ? shippingType.hashCode() : 0);
        result = 31 * result + (shippingFee != null ? shippingFee.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (shippingRefee != null ? shippingRefee.hashCode() : 0);
        result = 31 * result + (shippingWeight != null ? shippingWeight.hashCode() : 0);
        result = 31 * result + (shippingReweight != null ? shippingReweight.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("jpsId").append("='").append(getJpsId()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("countryCode").append("='").append(getCountryCode()).append("', ");
        sb.append("shippingType").append("='").append(getShippingType()).append("', ");
        sb.append("shippingFee").append("='").append(getShippingFee()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("shippingRefee").append("='").append(getShippingRefee()).append("', ");
        sb.append("shippingWeight").append("='").append(getShippingWeight()).append("', ");
        sb.append("shippingReweight").append("='").append(getShippingReweight()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
