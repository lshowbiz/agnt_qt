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
@Table(name="FI_GETBILLACCOUNT_LOG")

@XmlRootElement
public class FiGetbillaccountLog extends BaseObject implements Serializable {
    private Long logId;
    private String userCode;
    private String accountCode;
    private String status;
    private Date createTime;
    private String logDes;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="LOG_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId 
    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="ACCOUNT_CODE", length=50)
    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    
    @Column(name="STATUS", length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="LOG_DES", length=200)
    public String getLogDes() {
        return this.logDes;
    }
    
    public void setLogDes(String logDes) {
        this.logDes = logDes;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiGetbillaccountLog pojo = (FiGetbillaccountLog) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (accountCode != null ? !accountCode.equals(pojo.accountCode) : pojo.accountCode != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (logDes != null ? !logDes.equals(pojo.logDes) : pojo.logDes != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (accountCode != null ? accountCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (logDes != null ? logDes.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("logId").append("='").append(getLogId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("accountCode").append("='").append(getAccountCode()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("logDes").append("='").append(getLogDes()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
