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
@Table(name="JFI_QUOTA")

@XmlRootElement
public class JfiQuota extends BaseObject implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long quotaId;
    private String validityPeriod;
    private Long accountId;
    private String status;
    private Long maxMoney;
    private String operateName;
    private String operateTime;
    private String remark;

    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="QUOTA_ID", unique=true, nullable=false, precision=12, scale=0)
    public Long getQuotaId() {
        return this.quotaId;
    }
    
    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
    }
    
    @Column(name="VALIDITY_PERIOD", length=256)
    public String getValidityPeriod() {
        return this.validityPeriod;
    }
    
    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
    
    @Column(name="ACCOUNT_ID", precision=12, scale=0)
    public Long getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    
    @Column(name="STATUS", length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="MAX_MONEY", precision=18, scale=0)
    public Long getMaxMoney() {
        return this.maxMoney;
    }
    
    public void setMaxMoney(Long maxMoney) {
        this.maxMoney = maxMoney;
    }
    
    @Column(name="OPERATE_NAME", length=256)
    public String getOperateName() {
        return this.operateName;
    }
    
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }
    
    @Column(name="OPERATE_TIME", length=256)
    public String getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
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

        JfiQuota pojo = (JfiQuota) o;

        if (validityPeriod != null ? !validityPeriod.equals(pojo.validityPeriod) : pojo.validityPeriod != null) return false;
        if (accountId != null ? !accountId.equals(pojo.accountId) : pojo.accountId != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (maxMoney != null ? !maxMoney.equals(pojo.maxMoney) : pojo.maxMoney != null) return false;
        if (operateName != null ? !operateName.equals(pojo.operateName) : pojo.operateName != null) return false;
        if (operateTime != null ? !operateTime.equals(pojo.operateTime) : pojo.operateTime != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (validityPeriod != null ? validityPeriod.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (maxMoney != null ? maxMoney.hashCode() : 0);
        result = 31 * result + (operateName != null ? operateName.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("quotaId").append("='").append(getQuotaId()).append("', ");
        sb.append("validityPeriod").append("='").append(getValidityPeriod()).append("', ");
        sb.append("accountId").append("='").append(getAccountId()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("maxMoney").append("='").append(getMaxMoney()).append("', ");
        sb.append("operateName").append("='").append(getOperateName()).append("', ");
        sb.append("operateTime").append("='").append(getOperateTime()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
