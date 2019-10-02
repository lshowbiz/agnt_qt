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
@Table(name="JSYS_CONFIG_KEY")

@XmlRootElement
public class JsysConfigKey extends BaseObject implements Serializable {
    private Long keyId;
    private String configCode;
    private String keyDesc;
    private String defaultValue;
    private String usedFlag;

    @Id      @Column(name="KEY_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    
    @Column(name="CONFIG_CODE", nullable=false, length=50)
    
    public String getConfigCode() {
        return this.configCode;
    }
    
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }
    
    @Column(name="KEY_DESC", length=150)
    
    public String getKeyDesc() {
        return this.keyDesc;
    }
    
    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }
    
    @Column(name="DEFAULT_VALUE", length=100)
    
    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    @Column(name="USED_FLAG", length=1)
    
    public String getUsedFlag() {
        return this.usedFlag;
    }
    
    public void setUsedFlag(String usedFlag) {
        this.usedFlag = usedFlag;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysConfigKey pojo = (JsysConfigKey) o;

        if (configCode != null ? !configCode.equals(pojo.configCode) : pojo.configCode != null) return false;
        if (keyDesc != null ? !keyDesc.equals(pojo.keyDesc) : pojo.keyDesc != null) return false;
        if (defaultValue != null ? !defaultValue.equals(pojo.defaultValue) : pojo.defaultValue != null) return false;
        if (usedFlag != null ? !usedFlag.equals(pojo.usedFlag) : pojo.usedFlag != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (configCode != null ? configCode.hashCode() : 0);
        result = 31 * result + (keyDesc != null ? keyDesc.hashCode() : 0);
        result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
        result = 31 * result + (usedFlag != null ? usedFlag.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("keyId").append("='").append(getKeyId()).append("', ");
        sb.append("configCode").append("='").append(getConfigCode()).append("', ");
        sb.append("keyDesc").append("='").append(getKeyDesc()).append("', ");
        sb.append("defaultValue").append("='").append(getDefaultValue()).append("', ");
        sb.append("usedFlag").append("='").append(getUsedFlag()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
