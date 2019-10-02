package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;
import com.joymain.ng.util.data.CustomDateSerializer;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="JBD_SEND_NOTE")

@XmlRootElement
public class JbdSendNote extends BaseObject implements Serializable {
    private Long id;
    private String companyCode;
    private JmiMember jmiMember;
    private String remittanceBNum;
    private String operaterCode;
    private Date operaterTime;
    private Date sendDate;
    private String registerStatus;
    private String reissueStatus;
    private Date supplyTime;
    private String sendRemark;
    private String createNo;
    private Date createTime;
    private BigDecimal remittanceMoney;
    private BigDecimal fee;
    private String sendLateCause;
    private String sendLateRemark;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bd")
	@SequenceGenerator(name = "seq_bd", sequenceName = "SEQ_BD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)     
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
 
    
    @Column(name="REMITTANCE_B_NUM", length=100)
    public String getRemittanceBNum() {
        return this.remittanceBNum;
    }
    
    public void setRemittanceBNum(String remittanceBNum) {
        this.remittanceBNum = remittanceBNum;
    }
    
    @Column(name="OPERATER_CODE", length=100)
    public String getOperaterCode() {
        return this.operaterCode;
    }
    
    public void setOperaterCode(String operaterCode) {
        this.operaterCode = operaterCode;
    }
    @Column(name="OPERATER_TIME", length=7)
    public Date getOperaterTime() {
        return this.operaterTime;
    }
    
    public void setOperaterTime(Date operaterTime) {
        this.operaterTime = operaterTime;
    }
    @Column(name="SEND_DATE", length=7)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
    @Column(name="REGISTER_STATUS", length=1)
    public String getRegisterStatus() {
        return this.registerStatus;
    }
    
    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }
    
    @Column(name="REISSUE_STATUS", length=1)
    public String getReissueStatus() {
        return this.reissueStatus;
    }
    
    public void setReissueStatus(String reissueStatus) {
        this.reissueStatus = reissueStatus;
    }
    @Column(name="SUPPLY_TIME", length=7)
    public Date getSupplyTime() {
        return this.supplyTime;
    }
    
    public void setSupplyTime(Date supplyTime) {
        this.supplyTime = supplyTime;
    }
    
    @Column(name="SEND_REMARK", length=500)
    public String getSendRemark() {
        return this.sendRemark;
    }
    
    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }
    
    @Column(name="CREATE_NO", length=20)
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    @Column(name="CREATE_TIME", length=7)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="REMITTANCE_MONEY", precision=22, scale=0)
    public BigDecimal getRemittanceMoney() {
        return this.remittanceMoney;
    }
    
    public void setRemittanceMoney(BigDecimal remittanceMoney) {
        this.remittanceMoney = remittanceMoney;
    }
    
    @Column(name="FEE", precision=22, scale=0)
    public BigDecimal getFee() {
        return this.fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    
    @Column(name="SEND_LATE_CAUSE", length=10)
    public String getSendLateCause() {
        return this.sendLateCause;
    }
    
    public void setSendLateCause(String sendLateCause) {
        this.sendLateCause = sendLateCause;
    }
    
    @Column(name="SEND_LATE_REMARK", length=500)
    public String getSendLateRemark() {
        return this.sendLateRemark;
    }
    
    public void setSendLateRemark(String sendLateRemark) {
        this.sendLateRemark = sendLateRemark;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdSendNote pojo = (JbdSendNote) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (remittanceBNum != null ? !remittanceBNum.equals(pojo.remittanceBNum) : pojo.remittanceBNum != null) return false;
        if (operaterCode != null ? !operaterCode.equals(pojo.operaterCode) : pojo.operaterCode != null) return false;
        if (operaterTime != null ? !operaterTime.equals(pojo.operaterTime) : pojo.operaterTime != null) return false;
        if (sendDate != null ? !sendDate.equals(pojo.sendDate) : pojo.sendDate != null) return false;
        if (registerStatus != null ? !registerStatus.equals(pojo.registerStatus) : pojo.registerStatus != null) return false;
        if (reissueStatus != null ? !reissueStatus.equals(pojo.reissueStatus) : pojo.reissueStatus != null) return false;
        if (supplyTime != null ? !supplyTime.equals(pojo.supplyTime) : pojo.supplyTime != null) return false;
        if (sendRemark != null ? !sendRemark.equals(pojo.sendRemark) : pojo.sendRemark != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (remittanceMoney != null ? !remittanceMoney.equals(pojo.remittanceMoney) : pojo.remittanceMoney != null) return false;
        if (fee != null ? !fee.equals(pojo.fee) : pojo.fee != null) return false;
        if (sendLateCause != null ? !sendLateCause.equals(pojo.sendLateCause) : pojo.sendLateCause != null) return false;
        if (sendLateRemark != null ? !sendLateRemark.equals(pojo.sendLateRemark) : pojo.sendLateRemark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (remittanceBNum != null ? remittanceBNum.hashCode() : 0);
        result = 31 * result + (operaterCode != null ? operaterCode.hashCode() : 0);
        result = 31 * result + (operaterTime != null ? operaterTime.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (registerStatus != null ? registerStatus.hashCode() : 0);
        result = 31 * result + (reissueStatus != null ? reissueStatus.hashCode() : 0);
        result = 31 * result + (supplyTime != null ? supplyTime.hashCode() : 0);
        result = 31 * result + (sendRemark != null ? sendRemark.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (remittanceMoney != null ? remittanceMoney.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (sendLateCause != null ? sendLateCause.hashCode() : 0);
        result = 31 * result + (sendLateRemark != null ? sendLateRemark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("remittanceBNum").append("='").append(getRemittanceBNum()).append("', ");
        sb.append("operaterCode").append("='").append(getOperaterCode()).append("', ");
        sb.append("operaterTime").append("='").append(getOperaterTime()).append("', ");
        sb.append("sendDate").append("='").append(getSendDate()).append("', ");
        sb.append("registerStatus").append("='").append(getRegisterStatus()).append("', ");
        sb.append("reissueStatus").append("='").append(getReissueStatus()).append("', ");
        sb.append("supplyTime").append("='").append(getSupplyTime()).append("', ");
        sb.append("sendRemark").append("='").append(getSendRemark()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("remittanceMoney").append("='").append(getRemittanceMoney()).append("', ");
        sb.append("fee").append("='").append(getFee()).append("', ");
        sb.append("sendLateCause").append("='").append(getSendLateCause()).append("', ");
        sb.append("sendLateRemark").append("='").append(getSendLateRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToOne
    @JoinColumn(name = "USER_CODE", nullable = false,  updatable = false)
    @JsonIgnore
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

}
