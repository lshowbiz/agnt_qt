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
@Table(name="JMI_LEVEL_LOCK")

@XmlRootElement
public class JmiLevelLock extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private Long memberLevel;
    private String createNo;
    private Boolean isValid;

    @Id      @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
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
    
    @Column(name="MEMBER_LEVEL", precision=22, scale=0)
    public Long getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(Long memberLevel) {
        this.memberLevel = memberLevel;
    }
    
    @Column(name="CREATE_NO", length=20)
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    
    @Column(name="IS_VALID", length=1)
    public Boolean getIsValid() {
        return this.isValid;
    }
    
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiLevelLock pojo = (JmiLevelLock) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (memberLevel != null ? !memberLevel.equals(pojo.memberLevel) : pojo.memberLevel != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;
        if (isValid != null ? !isValid.equals(pojo.isValid) : pojo.isValid != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (memberLevel != null ? memberLevel.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);
        result = 31 * result + (isValid != null ? isValid.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("memberLevel").append("='").append(getMemberLevel()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("', ");
        sb.append("isValid").append("='").append(getIsValid()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
