package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JSYS_USER_ROLE")

@XmlRootElement
public class JsysUserRole extends BaseObject implements Serializable {
    private Long ruId;
    private Long roleId;
    private String userCode;

    @Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys")
	@SequenceGenerator(name = "seq_sys", sequenceName = "SEQ_SYS", allocationSize = 1)
    @Column(name="RU_ID", unique=true, nullable=false, precision=22, scale=0)   
    public Long getRuId() {
        return this.ruId;
    }
    
    public void setRuId(Long ruId) {
        this.ruId = ruId;
    }
    
    @Column(name="ROLE_ID", precision=22, scale=0)
    
    public Long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    @Column(name="USER_CODE", length=20)
    
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysUserRole pojo = (JsysUserRole) o;

        if (roleId != null ? !roleId.equals(pojo.roleId) : pojo.roleId != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("ruId").append("='").append(getRuId()).append("', ");
        sb.append("roleId").append("='").append(getRoleId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
