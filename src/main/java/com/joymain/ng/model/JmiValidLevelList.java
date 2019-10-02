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
@Table(name="JMI_VALID_LEVEL_LIST")

@XmlRootElement
public class JmiValidLevelList extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private Long WWeek;
    private Long oldMemberLevel;
    private Long newMemberLevel;
    private String isLock;
    private String createNo;

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
    
    @Column(name="W_WEEK", precision=22, scale=0)
    public Long getWWeek() {
        return this.WWeek;
    }
    
    public void setWWeek(Long WWeek) {
        this.WWeek = WWeek;
    }
    
    @Column(name="OLD_MEMBER_LEVEL", precision=22, scale=0)
    public Long getOldMemberLevel() {
        return this.oldMemberLevel;
    }
    
    public void setOldMemberLevel(Long oldMemberLevel) {
        this.oldMemberLevel = oldMemberLevel;
    }
    
    @Column(name="NEW_MEMBER_LEVEL", precision=22, scale=0)
    public Long getNewMemberLevel() {
        return this.newMemberLevel;
    }
    
    public void setNewMemberLevel(Long newMemberLevel) {
        this.newMemberLevel = newMemberLevel;
    }
    
    @Column(name="IS_LOCK", length=2)
    public String getIsLock() {
        return this.isLock;
    }
    
    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }
    
    @Column(name="CREATE_NO", length=20)
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiValidLevelList pojo = (JmiValidLevelList) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (WWeek != null ? !WWeek.equals(pojo.WWeek) : pojo.WWeek != null) return false;
        if (oldMemberLevel != null ? !oldMemberLevel.equals(pojo.oldMemberLevel) : pojo.oldMemberLevel != null) return false;
        if (newMemberLevel != null ? !newMemberLevel.equals(pojo.newMemberLevel) : pojo.newMemberLevel != null) return false;
        if (isLock != null ? !isLock.equals(pojo.isLock) : pojo.isLock != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (WWeek != null ? WWeek.hashCode() : 0);
        result = 31 * result + (oldMemberLevel != null ? oldMemberLevel.hashCode() : 0);
        result = 31 * result + (newMemberLevel != null ? newMemberLevel.hashCode() : 0);
        result = 31 * result + (isLock != null ? isLock.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("WWeek").append("='").append(getWWeek()).append("', ");
        sb.append("oldMemberLevel").append("='").append(getOldMemberLevel()).append("', ");
        sb.append("newMemberLevel").append("='").append(getNewMemberLevel()).append("', ");
        sb.append("isLock").append("='").append(getIsLock()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
