package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="PD_NOT_CHANGE_PRODUCT")

@XmlRootElement
public class PdNotChangeProduct extends BaseObject implements Serializable {
    private Long id;
    private String productNo;
    private String teamCode;
    private String orderType;
    private String companyCode;
    private String createUserCode;
    private Date createTime;
    private String isAvailable;
    private String other;

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId 
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="PRODUCT_NO", length=20)
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="TEAM_CODE", length=10)
    public String getTeamCode() {
        return this.teamCode;
    }
    
    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }
    
    @Column(name="ORDER_TYPE", length=3)
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="CREATE_USER_CODE", length=20)
    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="IS_AVAILABLE", length=20)
    public String getIsAvailable() {
        return this.isAvailable;
    }
    
    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    @Column(name="OTHER", length=200)
    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdNotChangeProduct pojo = (PdNotChangeProduct) o;

        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (teamCode != null ? !teamCode.equals(pojo.teamCode) : pojo.teamCode != null) return false;
        if (orderType != null ? !orderType.equals(pojo.orderType) : pojo.orderType != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (isAvailable != null ? !isAvailable.equals(pojo.isAvailable) : pojo.isAvailable != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (teamCode != null ? teamCode.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (isAvailable != null ? isAvailable.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("teamCode").append("='").append(getTeamCode()).append("', ");
        sb.append("orderType").append("='").append(getOrderType()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("createUserCode").append("='").append(getCreateUserCode()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("isAvailable").append("='").append(getIsAvailable()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
