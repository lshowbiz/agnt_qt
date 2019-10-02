package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
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
@Table(name="JSYS_BANK")

@XmlRootElement
public class JsysBank extends BaseObject implements Serializable {
    private Long bankId;
    private String bankKey;
    private String bankValue;
    private String companyCode;
    private Long orderNo;
    private String bankNo;
    private String bankKana;

    @Id      @Column(name="BANK_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getBankId() {
        return this.bankId;
    }
    
    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
    
    @Column(name="BANK_KEY", nullable=false, length=300)
    public String getBankKey() {
        return this.bankKey;
    }
    
    public void setBankKey(String bankKey) {
        this.bankKey = bankKey;
    }
    
    @Column(name="BANK_VALUE", nullable=false, length=300)
    public String getBankValue() {
        return this.bankValue;
    }
    
    public void setBankValue(String bankValue) {
        this.bankValue = bankValue;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="ORDER_NO", precision=8, scale=0)
    public Long getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    
    @Column(name="BANK_NO", length=10)
    public String getBankNo() {
        return this.bankNo;
    }
    
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
    
    @Column(name="BANK_KANA", length=80)
    public String getBankKana() {
        return this.bankKana;
    }
    
    public void setBankKana(String bankKana) {
        this.bankKana = bankKana;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysBank pojo = (JsysBank) o;

        if (bankKey != null ? !bankKey.equals(pojo.bankKey) : pojo.bankKey != null) return false;
        if (bankValue != null ? !bankValue.equals(pojo.bankValue) : pojo.bankValue != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (orderNo != null ? !orderNo.equals(pojo.orderNo) : pojo.orderNo != null) return false;
        if (bankNo != null ? !bankNo.equals(pojo.bankNo) : pojo.bankNo != null) return false;
        if (bankKana != null ? !bankKana.equals(pojo.bankKana) : pojo.bankKana != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (bankKey != null ? bankKey.hashCode() : 0);
        result = 31 * result + (bankValue != null ? bankValue.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (bankNo != null ? bankNo.hashCode() : 0);
        result = 31 * result + (bankKana != null ? bankKana.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("bankId").append("='").append(getBankId()).append("', ");
        sb.append("bankKey").append("='").append(getBankKey()).append("', ");
        sb.append("bankValue").append("='").append(getBankValue()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("orderNo").append("='").append(getOrderNo()).append("', ");
        sb.append("bankNo").append("='").append(getBankNo()).append("', ");
        sb.append("bankKana").append("='").append(getBankKana()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
