package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JAL_COMPANY")

@XmlRootElement
public class JalCompany extends BaseObject implements Serializable {
    private Long companyId;
    private String companyCode;
    private String companyName;
    private String characterCode;
    private String currencyCode;
    private String preAgentCode;
    private String regName;
    private Double taxRate;
    private String taxType;
    private String zipCode;
    private String countryCode;
    private String stateProvinceCode;
    private String city;
    private String address;
    private String phone;
    private Long timeZone;
    private String dateFormat;
    private String timeFormat;
    private Long exchangeRate;
    private String productNo;
    private Long startWeek;
    private Long endWeek;

    @Id      @Column(name="COMPANY_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="COMPANY_NAME", nullable=false, length=250)
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    @Column(name="CHARACTER_CODE", nullable=false, length=10)
    
    public String getCharacterCode() {
        return this.characterCode;
    }
    
    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }
    
    @Column(name="CURRENCY_CODE", length=10)
    
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    @Column(name="PRE_AGENT_CODE", length=30)
    
    public String getPreAgentCode() {
        return this.preAgentCode;
    }
    
    public void setPreAgentCode(String preAgentCode) {
        this.preAgentCode = preAgentCode;
    }
    
    @Column(name="REG_NAME", length=250)
    
    public String getRegName() {
        return this.regName;
    }
    
    public void setRegName(String regName) {
        this.regName = regName;
    }
    
    @Column(name="TAX_RATE", precision=126, scale=0)
    
    public Double getTaxRate() {
        return this.taxRate;
    }
    
    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
    
    @Column(name="TAX_TYPE", length=2)
    
    public String getTaxType() {
        return this.taxType;
    }
    
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
    
    @Column(name="ZIP_CODE", length=10)
    
    public String getZipCode() {
        return this.zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    @Column(name="COUNTRY_CODE", length=2)
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    @Column(name="STATE_PROVINCE_CODE", length=50)
    
    public String getStateProvinceCode() {
        return this.stateProvinceCode;
    }
    
    public void setStateProvinceCode(String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }
    
    @Column(name="CITY", length=50)
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="ADDRESS", length=250)
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="PHONE", length=30)
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="TIME_ZONE", precision=22, scale=0)
    
    public Long getTimeZone() {
        return this.timeZone;
    }
    
    public void setTimeZone(Long timeZone) {
        this.timeZone = timeZone;
    }
    
    @Column(name="DATE_FORMAT", length=50)
    
    public String getDateFormat() {
        return this.dateFormat;
    }
    
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    
    @Column(name="TIME_FORMAT", length=50)
    
    public String getTimeFormat() {
        return this.timeFormat;
    }
    
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }
    
    @Column(name="EXCHANGE_RATE", precision=18, scale=4)
    
    public Long getExchangeRate() {
        return this.exchangeRate;
    }
    
    public void setExchangeRate(Long exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    
    @Column(name="PRODUCT_NO", length=20)
    
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="START_WEEK", precision=22, scale=0)
    
    public Long getStartWeek() {
        return this.startWeek;
    }
    
    public void setStartWeek(Long startWeek) {
        this.startWeek = startWeek;
    }
    
    @Column(name="END_WEEK", precision=22, scale=0)
    
    public Long getEndWeek() {
        return this.endWeek;
    }
    
    public void setEndWeek(Long endWeek) {
        this.endWeek = endWeek;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalCompany pojo = (JalCompany) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (companyName != null ? !companyName.equals(pojo.companyName) : pojo.companyName != null) return false;
        if (characterCode != null ? !characterCode.equals(pojo.characterCode) : pojo.characterCode != null) return false;
        if (currencyCode != null ? !currencyCode.equals(pojo.currencyCode) : pojo.currencyCode != null) return false;
        if (preAgentCode != null ? !preAgentCode.equals(pojo.preAgentCode) : pojo.preAgentCode != null) return false;
        if (regName != null ? !regName.equals(pojo.regName) : pojo.regName != null) return false;
        if (taxRate != null ? !taxRate.equals(pojo.taxRate) : pojo.taxRate != null) return false;
        if (taxType != null ? !taxType.equals(pojo.taxType) : pojo.taxType != null) return false;
        if (zipCode != null ? !zipCode.equals(pojo.zipCode) : pojo.zipCode != null) return false;
        if (countryCode != null ? !countryCode.equals(pojo.countryCode) : pojo.countryCode != null) return false;
        if (stateProvinceCode != null ? !stateProvinceCode.equals(pojo.stateProvinceCode) : pojo.stateProvinceCode != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (timeZone != null ? !timeZone.equals(pojo.timeZone) : pojo.timeZone != null) return false;
        if (dateFormat != null ? !dateFormat.equals(pojo.dateFormat) : pojo.dateFormat != null) return false;
        if (timeFormat != null ? !timeFormat.equals(pojo.timeFormat) : pojo.timeFormat != null) return false;
        if (exchangeRate != null ? !exchangeRate.equals(pojo.exchangeRate) : pojo.exchangeRate != null) return false;
        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (startWeek != null ? !startWeek.equals(pojo.startWeek) : pojo.startWeek != null) return false;
        if (endWeek != null ? !endWeek.equals(pojo.endWeek) : pojo.endWeek != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (characterCode != null ? characterCode.hashCode() : 0);
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        result = 31 * result + (preAgentCode != null ? preAgentCode.hashCode() : 0);
        result = 31 * result + (regName != null ? regName.hashCode() : 0);
        result = 31 * result + (taxRate != null ? taxRate.hashCode() : 0);
        result = 31 * result + (taxType != null ? taxType.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (stateProvinceCode != null ? stateProvinceCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        result = 31 * result + (dateFormat != null ? dateFormat.hashCode() : 0);
        result = 31 * result + (timeFormat != null ? timeFormat.hashCode() : 0);
        result = 31 * result + (exchangeRate != null ? exchangeRate.hashCode() : 0);
        result = 31 * result + (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (startWeek != null ? startWeek.hashCode() : 0);
        result = 31 * result + (endWeek != null ? endWeek.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("companyId").append("='").append(getCompanyId()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("companyName").append("='").append(getCompanyName()).append("', ");
        sb.append("characterCode").append("='").append(getCharacterCode()).append("', ");
        sb.append("currencyCode").append("='").append(getCurrencyCode()).append("', ");
        sb.append("preAgentCode").append("='").append(getPreAgentCode()).append("', ");
        sb.append("regName").append("='").append(getRegName()).append("', ");
        sb.append("taxRate").append("='").append(getTaxRate()).append("', ");
        sb.append("taxType").append("='").append(getTaxType()).append("', ");
        sb.append("zipCode").append("='").append(getZipCode()).append("', ");
        sb.append("countryCode").append("='").append(getCountryCode()).append("', ");
        sb.append("stateProvinceCode").append("='").append(getStateProvinceCode()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("', ");
        sb.append("timeZone").append("='").append(getTimeZone()).append("', ");
        sb.append("dateFormat").append("='").append(getDateFormat()).append("', ");
        sb.append("timeFormat").append("='").append(getTimeFormat()).append("', ");
        sb.append("exchangeRate").append("='").append(getExchangeRate()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("startWeek").append("='").append(getStartWeek()).append("', ");
        sb.append("endWeek").append("='").append(getEndWeek()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
