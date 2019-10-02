package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="JPO_USER_COUPON")

@XmlRootElement
public class JpoUserCoupon extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private Long cpId;
    private Boolean status;
    private Date startTime;
    private Date endTime;
    private String remark;
    
    private String ableStatus;
    
    /*private JmiMember jmimember;
    private JpmCouponInfo jpmCouponInfo;
    	
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=JmiMember.class)
    @JoinColumn(name="userCode",nullable=false,updatable=false)
    public JmiMember getJmimember() {
		return jmimember;
	}

	public void setJmimember(JmiMember jmimember) {
		this.jmimember = jmimember;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=JpmCouponInfo.class)
    @JoinColumn(name="cpId",nullable=false,updatable=false)
	public JpmCouponInfo getJpmCouponInfo() {
		return jpmCouponInfo;
	}

	public void setJpmCouponInfo(JpmCouponInfo jpmCouponInfo) {
		this.jpmCouponInfo = jpmCouponInfo;
	}*/

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
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="CP_ID", precision=22, scale=0)
    public Long getCpId() {
        return this.cpId;
    }
    
    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }
    
    @Column(name="STATUS", length=1)
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
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
    
    @Column(name="REMARK", length=4000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="ABLE_STATUS", length=4000)
    public String getAbleStatus() {
		return ableStatus;
	}


	public void setAbleStatus(String ableStatus) {
		this.ableStatus = ableStatus;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoUserCoupon pojo = (JpoUserCoupon) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (cpId != null ? !cpId.equals(pojo.cpId) : pojo.cpId != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (cpId != null ? cpId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("cpId").append("='").append(getCpId()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
