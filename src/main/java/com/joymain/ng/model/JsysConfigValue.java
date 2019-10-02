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
@Table(name="JSYS_CONFIG_VALUE")

@XmlRootElement
public class JsysConfigValue extends BaseObject implements Serializable {
    private Long valueId;
    private Long keyId;
    private String companyCode;
    private String configValue;

    @Id      @Column(name="VALUE_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getValueId() {
        return this.valueId;
    }
    
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
    
    @Column(name="KEY_ID", precision=12, scale=0)
    
    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="CONFIG_VALUE", length=200)
    
    public String getConfigValue() {
        return this.configValue;
    }
    
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysConfigValue pojo = (JsysConfigValue) o;

        if (keyId != null ? !keyId.equals(pojo.keyId) : pojo.keyId != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (configValue != null ? !configValue.equals(pojo.configValue) : pojo.configValue != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (keyId != null ? keyId.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (configValue != null ? configValue.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("valueId").append("='").append(getValueId()).append("', ");
        sb.append("keyId").append("='").append(getKeyId()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("configValue").append("='").append(getConfigValue()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
