package com.joymain.ng.model;


import com.joymain.ng.model.BaseObject;

																			

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="JSYS_RESOURCE")
@XmlRootElement
public class JsysResource extends BaseObject implements Serializable {
    private Long resId;
    private Long parentId;
    private String resCode;
    private String resName;
    private Long resType;
    private String sysType;
    private String resUrl;
    private String resDes;
    private Long orderNo;
    private String active;
    private String validateFlag;
    private String treeIndex;
    private Long treeLevel;

    private Set<JsysRole> jsysRoles = new LinkedHashSet<JsysRole>(0);
    
    @Id  @DocumentId 
    @Column(name = "RES_ID", unique = true, nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_SYS")
    @SequenceGenerator(name="SEQ_SYS", sequenceName="SEQ_SYS")  
    public Long getResId() {
        return this.resId;
    }
    
    public void setResId(Long resId) {
        this.resId = resId;
    }
    
    @Column(name="PARENT_ID", nullable=false, precision=22, scale=0)
    
    public Long getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    @Column(name="RES_CODE", length=100)
    
    public String getResCode() {
        return this.resCode;
    }
    
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    
    @Column(name="RES_NAME", length=500)
    
    public String getResName() {
        return this.resName;
    }
    
    public void setResName(String resName) {
        this.resName = resName;
    }
    
    @Column(name="RES_TYPE", nullable=false, precision=4, scale=0)
    
    public Long getResType() {
        return this.resType;
    }
    
    public void setResType(Long resType) {
        this.resType = resType;
    }
    
    @Column(name="SYS_TYPE", nullable=false, length=10)
    
    public String getSysType() {
        return this.sysType;
    }
    
    public void setSysType(String sysType) {
        this.sysType = sysType;
    }
    
    @Column(name="RES_URL", length=500)
    
    public String getResUrl() {
        return this.resUrl;
    }
    
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
    
    @Column(name="RES_DES", length=500)
    
    public String getResDes() {
        return this.resDes;
    }
    
    public void setResDes(String resDes) {
        this.resDes = resDes;
    }
    
    @Column(name="ORDER_NO", precision=22, scale=0)
    
    public Long getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    
    @Column(name="ACTIVE", length=1)
    
    public String getActive() {
        return this.active;
    }
    
    public void setActive(String active) {
        this.active = active;
    }
    
    @Column(name="VALIDATE_FLAG", length=1)
    
    public String getValidateFlag() {
        return this.validateFlag;
    }
    
    public void setValidateFlag(String validateFlag) {
        this.validateFlag = validateFlag;
    }
    
    @Column(name="TREE_INDEX", length=500)
    
    public String getTreeIndex() {
        return this.treeIndex;
    }
    
    public void setTreeIndex(String treeIndex) {
        this.treeIndex = treeIndex;
    }
    
    @Column(name="TREE_LEVEL", precision=10, scale=0)
    
    public Long getTreeLevel() {
        return this.treeLevel;
    }
    
    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysResource pojo = (JsysResource) o;

        if (parentId != null ? !parentId.equals(pojo.parentId) : pojo.parentId != null) return false;
        if (resCode != null ? !resCode.equals(pojo.resCode) : pojo.resCode != null) return false;
        if (resName != null ? !resName.equals(pojo.resName) : pojo.resName != null) return false;
        if (resType != null ? !resType.equals(pojo.resType) : pojo.resType != null) return false;
        if (sysType != null ? !sysType.equals(pojo.sysType) : pojo.sysType != null) return false;
        if (resUrl != null ? !resUrl.equals(pojo.resUrl) : pojo.resUrl != null) return false;
        if (resDes != null ? !resDes.equals(pojo.resDes) : pojo.resDes != null) return false;
        if (orderNo != null ? !orderNo.equals(pojo.orderNo) : pojo.orderNo != null) return false;
        if (active != null ? !active.equals(pojo.active) : pojo.active != null) return false;
        if (validateFlag != null ? !validateFlag.equals(pojo.validateFlag) : pojo.validateFlag != null) return false;
        if (treeIndex != null ? !treeIndex.equals(pojo.treeIndex) : pojo.treeIndex != null) return false;
        if (treeLevel != null ? !treeLevel.equals(pojo.treeLevel) : pojo.treeLevel != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (resCode != null ? resCode.hashCode() : 0);
        result = 31 * result + (resName != null ? resName.hashCode() : 0);
        result = 31 * result + (resType != null ? resType.hashCode() : 0);
        result = 31 * result + (sysType != null ? sysType.hashCode() : 0);
        result = 31 * result + (resUrl != null ? resUrl.hashCode() : 0);
        result = 31 * result + (resDes != null ? resDes.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (validateFlag != null ? validateFlag.hashCode() : 0);
        result = 31 * result + (treeIndex != null ? treeIndex.hashCode() : 0);
        result = 31 * result + (treeLevel != null ? treeLevel.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("resId").append("='").append(getResId()).append("', ");
        sb.append("parentId").append("='").append(getParentId()).append("', ");
        sb.append("resCode").append("='").append(getResCode()).append("', ");
        sb.append("resName").append("='").append(getResName()).append("', ");
        sb.append("resType").append("='").append(getResType()).append("', ");
        sb.append("sysType").append("='").append(getSysType()).append("', ");
        sb.append("resUrl").append("='").append(getResUrl()).append("', ");
        sb.append("resDes").append("='").append(getResDes()).append("', ");
        sb.append("orderNo").append("='").append(getOrderNo()).append("', ");
        sb.append("active").append("='").append(getActive()).append("', ");
        sb.append("validateFlag").append("='").append(getValidateFlag()).append("', ");
        sb.append("treeIndex").append("='").append(getTreeIndex()).append("', ");
        sb.append("treeLevel").append("='").append(getTreeLevel()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="jsysResources")
	public Set<JsysRole> getJsysRoles() {
		return jsysRoles;
	}

	public void setJsysRoles(Set<JsysRole> jsysRoles) {
		this.jsysRoles = jsysRoles;
	}

    
}
