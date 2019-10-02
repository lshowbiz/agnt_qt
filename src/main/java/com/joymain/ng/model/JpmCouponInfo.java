package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="JPM_COUPON_INFO")

@XmlRootElement
public class JpmCouponInfo extends BaseObject implements Serializable {
    private Long cpId;
    private String cpName;
    private Long cpValue;
    private String status;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private String createUserCode;
    private Date updateTime;
    private String updateUserCode;
    private String remark;

    private Set<JpmCouponRelationship> jpmCouponRelationships = new HashSet(); 
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PM")
	@SequenceGenerator(name = "SEQ_PM", sequenceName = "SEQ_PM", allocationSize = 1)
	@Column(name="CP_ID", unique=true, nullable=false)  
    public Long getCpId() {
        return this.cpId;
    }
    
    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }
    
    @Column(name="CP_NAME", nullable=false, length=50)
    public String getCpName() {
        return this.cpName;
    }
    
    public void setCpName(String cpName) {
        this.cpName = cpName;
    }
    
    @Column(name="CP_VALUE", nullable=false, precision=22, scale=0)
    public Long getCpValue() {
        return this.cpValue;
    }
    
    public void setCpValue(Long cpValue) {
        this.cpValue = cpValue;
    }
    
    @Column(name="STATUS", nullable=false, length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="START_TIME", length=7)
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="END_TIME", length=7)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_USER_CODE", nullable=false, length=20)
    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="UPDATE_TIME", length=7)
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    @Column(name="UPDATE_USER_CODE", length=20)
    public String getUpdateUserCode() {
        return this.updateUserCode;
    }
    
    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }
    
    @Column(name="REMARK", length=2000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmCouponInfo pojo = (JpmCouponInfo) o;

        if (cpName != null ? !cpName.equals(pojo.cpName) : pojo.cpName != null) return false;
        if (cpValue != null ? !cpValue.equals(pojo.cpValue) : pojo.cpValue != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null) return false;
        if (updateTime != null ? !updateTime.equals(pojo.updateTime) : pojo.updateTime != null) return false;
        if (updateUserCode != null ? !updateUserCode.equals(pojo.updateUserCode) : pojo.updateUserCode != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (cpName != null ? cpName.hashCode() : 0);
        result = 31 * result + (cpValue != null ? cpValue.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateUserCode != null ? updateUserCode.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("cpId").append("='").append(getCpId()).append("', ");
        sb.append("cpName").append("='").append(getCpName()).append("', ");
        sb.append("cpValue").append("='").append(getCpValue()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUserCode").append("='").append(getCreateUserCode()).append("', ");
        sb.append("updateTime").append("='").append(getUpdateTime()).append("', ");
        sb.append("updateUserCode").append("='").append(getUpdateUserCode()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @OneToMany(mappedBy = "jpmCouponInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<JpmCouponRelationship> getJpmCouponRelationships() {
		return jpmCouponRelationships;
	}

	public void setJpmCouponRelationships(
			Set<JpmCouponRelationship> jpmCouponRelationships) {
		this.jpmCouponRelationships = jpmCouponRelationships;
	}
}
