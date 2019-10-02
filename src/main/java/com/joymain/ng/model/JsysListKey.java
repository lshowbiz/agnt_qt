package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="JSYS_LIST_KEY")

@XmlRootElement
public class JsysListKey extends BaseObject implements Serializable {
    private Long keyId;
    private String listCode;
    private String listName;
    private Long isReadOnly;
    private String remark;
    private String usedFlag;
    private Set<JsysListValue> sysListValues;
    
    @Id @Column(name="KEY_ID")  @DocumentId    
    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    
    @Column(name="LIST_CODE", nullable=false, length=50)
    
    public String getListCode() {
        return this.listCode;
    }
    
    public void setListCode(String listCode) {
        this.listCode = listCode;
    }
    
    @Column(name="LIST_NAME", nullable=false, length=150)
    
    public String getListName() {
        return this.listName;
    }
    
    public void setListName(String listName) {
        this.listName = listName;
    }
    
    @Column(name="IS_READ_ONLY", precision=2, scale=0)
    
    public Long getIsReadOnly() {
        return this.isReadOnly;
    }
    
    public void setIsReadOnly(Long isReadOnly) {
        this.isReadOnly = isReadOnly;
    }
    
    @Column(name="REMARK", length=500)
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="USED_FLAG", length=1)
    
    public String getUsedFlag() {
        return this.usedFlag;
    }
    
    public void setUsedFlag(String usedFlag) {
        this.usedFlag = usedFlag;
    }
    
    @OneToMany(mappedBy="jsysListKey",fetch=FetchType.LAZY)
    public Set<JsysListValue> getSysListValues() {
		return sysListValues;
	}

	public void setSysListValues(Set<JsysListValue> sysListValues) {
		this.sysListValues = sysListValues;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysListKey pojo = (JsysListKey) o;

        if (listCode != null ? !listCode.equals(pojo.listCode) : pojo.listCode != null) return false;
        if (listName != null ? !listName.equals(pojo.listName) : pojo.listName != null) return false;
        if (isReadOnly != null ? !isReadOnly.equals(pojo.isReadOnly) : pojo.isReadOnly != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (usedFlag != null ? !usedFlag.equals(pojo.usedFlag) : pojo.usedFlag != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (listCode != null ? listCode.hashCode() : 0);
        result = 31 * result + (listName != null ? listName.hashCode() : 0);
        result = 31 * result + (isReadOnly != null ? isReadOnly.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (usedFlag != null ? usedFlag.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("keyId").append("='").append(getKeyId()).append("', ");
        sb.append("listCode").append("='").append(getListCode()).append("', ");
        sb.append("listName").append("='").append(getListName()).append("', ");
        sb.append("isReadOnly").append("='").append(getIsReadOnly()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("usedFlag").append("='").append(getUsedFlag()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
