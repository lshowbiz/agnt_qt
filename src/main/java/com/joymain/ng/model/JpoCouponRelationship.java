package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JPO_COUPON_RELATIONSHIP" )

@XmlRootElement
public class JpoCouponRelationship extends BaseObject implements Serializable {
    private Long id;
    private Long moId;
    private Long cpId;
    private Date createTime;
    private String createUserCode;
    private String remark;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PO")
	@SequenceGenerator(name = "SEQ_PO", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="ID", unique=true, nullable=false)  
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="MO_ID", nullable=false, precision=22, scale=0)
    public Long getMoId() {
        return this.moId;
    }
    
    public void setMoId(Long moId) {
        this.moId = moId;
    }
    
    @Column(name="CP_ID", nullable=false, precision=22, scale=0)
    public Long getCpId() {
        return this.cpId;
    }
    
    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
	@Column(name="CREATE_USER_CODE", nullable=false, length=50)
    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    
    @Column(name="REMARK", length=1000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoCouponRelationship pojo = (JpoCouponRelationship) o;

        if (moId != null ? !moId.equals(pojo.moId) : pojo.moId != null) return false;
        if (cpId != null ? !cpId.equals(pojo.cpId) : pojo.cpId != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (moId != null ? moId.hashCode() : 0);
        result = 31 * result + (cpId != null ? cpId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("moId").append("='").append(getMoId()).append("', ");
        sb.append("cpId").append("='").append(getCpId()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUserCode").append("='").append(getCreateUserCode()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
