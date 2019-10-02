package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="JSYS_ROLE")
@XmlRootElement
public class JsysRole extends BaseObject implements Serializable,GrantedAuthority {
    private Long roleId;
    private String roleName;
    private String roleDes;
    private String companyCode;
    private Long available;
    private String userType;
    private String roleCode;

    private Set<JsysResource> jsysResources = new HashSet<JsysResource>();
    @Id  @DocumentId 
    @Column(name = "ROLE_ID", unique = true, nullable = false, length = 20)
    public Long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    @Column(name="ROLE_NAME", nullable=false, length=100)
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Column(name="ROLE_DES", length=100)
    
    public String getRoleDes() {
        return this.roleDes;
    }
    
    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="AVAILABLE", nullable=false, precision=2, scale=0)
    
    public Long getAvailable() {
        return this.available;
    }
    
    public void setAvailable(Long available) {
        this.available = available;
    }
    
    @Column(name="USER_TYPE", nullable=false, length=2)
    
    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Column(name="ROLE_CODE", nullable=false, length=30)
    
    public String getRoleCode() {
        return this.roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysRole pojo = (JsysRole) o;

        if (roleName != null ? !roleName.equals(pojo.roleName) : pojo.roleName != null) return false;
        if (roleDes != null ? !roleDes.equals(pojo.roleDes) : pojo.roleDes != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (available != null ? !available.equals(pojo.available) : pojo.available != null) return false;
        if (userType != null ? !userType.equals(pojo.userType) : pojo.userType != null) return false;
        if (roleCode != null ? !roleCode.equals(pojo.roleCode) : pojo.roleCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleDes != null ? roleDes.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (roleCode != null ? roleCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("roleId").append("='").append(getRoleId()).append("', ");
        sb.append("roleName").append("='").append(getRoleName()).append("', ");
        sb.append("roleDes").append("='").append(getRoleDes()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("available").append("='").append(getAvailable()).append("', ");
        sb.append("userType").append("='").append(getUserType()).append("', ");
        sb.append("roleCode").append("='").append(getRoleCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @Transient
	public String getAuthority() {
		// TODO Auto-generated method stub
		return "ROLE_"+getRoleCode();
	}

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="JSYS_RES_ROLE",  joinColumns = { 
        @JoinColumn(name="ROLE_ID", updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="RES_ID", updatable=false) })
	public Set<JsysResource> getJsysResources() {
		return jsysResources;
	}

	public void setJsysResources(Set<JsysResource> jsysResources) {
		this.jsysResources = jsysResources;
	}

    
}
