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
@Table(name="JMI_MEMBER_LOG")

@XmlRootElement
public class JmiMemberLog extends BaseObject implements Serializable {
    private Long logId;
    private String userCode;
    private String userName;
    private String beforeBank;
    private String beforeBankaddress;
    private String beforeBankbook;
    private String beforeBankcard;
    private String beforeBankprovince;
    private String beforeBankcity;
    private Date logTime;
    private String logType;
    private String logUserCode;
    private String afterBank;
    private String afterBankaddress;
    private String afterBankbook;
    private String afterBankcard;
    private String afterBankprovince;
    private String afterBankcity;

    @Id  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="LOG_ID", unique=true, nullable=false, precision=10, scale=0) 
    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="USER_NAME", length=100)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="BEFORE_BANK", length=80)
    public String getBeforeBank() {
        return this.beforeBank;
    }
    
    public void setBeforeBank(String beforeBank) {
        this.beforeBank = beforeBank;
    }
    
    @Column(name="BEFORE_BANKADDRESS", length=300)
    public String getBeforeBankaddress() {
        return this.beforeBankaddress;
    }
    
    public void setBeforeBankaddress(String beforeBankaddress) {
        this.beforeBankaddress = beforeBankaddress;
    }
    
    @Column(name="BEFORE_BANKBOOK", length=100)
    public String getBeforeBankbook() {
        return this.beforeBankbook;
    }
    
    public void setBeforeBankbook(String beforeBankbook) {
        this.beforeBankbook = beforeBankbook;
    }
    
    @Column(name="BEFORE_BANKCARD", length=100)
    public String getBeforeBankcard() {
        return this.beforeBankcard;
    }
    
    public void setBeforeBankcard(String beforeBankcard) {
        this.beforeBankcard = beforeBankcard;
    }
    
    @Column(name="BEFORE_BANKPROVINCE", length=20)
    public String getBeforeBankprovince() {
        return this.beforeBankprovince;
    }
    
    public void setBeforeBankprovince(String beforeBankprovince) {
        this.beforeBankprovince = beforeBankprovince;
    }
    
    @Column(name="BEFORE_BANKCITY", length=20)
    public String getBeforeBankcity() {
        return this.beforeBankcity;
    }
    
    public void setBeforeBankcity(String beforeBankcity) {
        this.beforeBankcity = beforeBankcity;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="LOG_TIME", length=7)
    public Date getLogTime() {
        return this.logTime;
    }
    
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
    
    @Column(name="LOG_TYPE", length=20)
    public String getLogType() {
        return this.logType;
    }
    
    public void setLogType(String logType) {
        this.logType = logType;
    }
    
    @Column(name="LOG_USER_CODE", length=50)
    public String getLogUserCode() {
        return this.logUserCode;
    }
    
    public void setLogUserCode(String logUserCode) {
        this.logUserCode = logUserCode;
    }
    
    @Column(name="AFTER_BANK", length=80)
    public String getAfterBank() {
        return this.afterBank;
    }
    
    public void setAfterBank(String afterBank) {
        this.afterBank = afterBank;
    }
    
    @Column(name="AFTER_BANKADDRESS", length=300)
    public String getAfterBankaddress() {
        return this.afterBankaddress;
    }
    
    public void setAfterBankaddress(String afterBankaddress) {
        this.afterBankaddress = afterBankaddress;
    }
    
    @Column(name="AFTER_BANKBOOK", length=100)
    public String getAfterBankbook() {
        return this.afterBankbook;
    }
    
    public void setAfterBankbook(String afterBankbook) {
        this.afterBankbook = afterBankbook;
    }
    
    @Column(name="AFTER_BANKCARD", length=100)
    public String getAfterBankcard() {
        return this.afterBankcard;
    }
    
    public void setAfterBankcard(String afterBankcard) {
        this.afterBankcard = afterBankcard;
    }
    
    @Column(name="AFTER_BANKPROVINCE", length=20)
    public String getAfterBankprovince() {
        return this.afterBankprovince;
    }
    
    public void setAfterBankprovince(String afterBankprovince) {
        this.afterBankprovince = afterBankprovince;
    }
    
    @Column(name="AFTER_BANKCITY", length=20)
    public String getAfterBankcity() {
        return this.afterBankcity;
    }
    
    public void setAfterBankcity(String afterBankcity) {
        this.afterBankcity = afterBankcity;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiMemberLog pojo = (JmiMemberLog) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;
        if (beforeBank != null ? !beforeBank.equals(pojo.beforeBank) : pojo.beforeBank != null) return false;
        if (beforeBankaddress != null ? !beforeBankaddress.equals(pojo.beforeBankaddress) : pojo.beforeBankaddress != null) return false;
        if (beforeBankbook != null ? !beforeBankbook.equals(pojo.beforeBankbook) : pojo.beforeBankbook != null) return false;
        if (beforeBankcard != null ? !beforeBankcard.equals(pojo.beforeBankcard) : pojo.beforeBankcard != null) return false;
        if (beforeBankprovince != null ? !beforeBankprovince.equals(pojo.beforeBankprovince) : pojo.beforeBankprovince != null) return false;
        if (beforeBankcity != null ? !beforeBankcity.equals(pojo.beforeBankcity) : pojo.beforeBankcity != null) return false;
        if (logTime != null ? !logTime.equals(pojo.logTime) : pojo.logTime != null) return false;
        if (logType != null ? !logType.equals(pojo.logType) : pojo.logType != null) return false;
        if (logUserCode != null ? !logUserCode.equals(pojo.logUserCode) : pojo.logUserCode != null) return false;
        if (afterBank != null ? !afterBank.equals(pojo.afterBank) : pojo.afterBank != null) return false;
        if (afterBankaddress != null ? !afterBankaddress.equals(pojo.afterBankaddress) : pojo.afterBankaddress != null) return false;
        if (afterBankbook != null ? !afterBankbook.equals(pojo.afterBankbook) : pojo.afterBankbook != null) return false;
        if (afterBankcard != null ? !afterBankcard.equals(pojo.afterBankcard) : pojo.afterBankcard != null) return false;
        if (afterBankprovince != null ? !afterBankprovince.equals(pojo.afterBankprovince) : pojo.afterBankprovince != null) return false;
        if (afterBankcity != null ? !afterBankcity.equals(pojo.afterBankcity) : pojo.afterBankcity != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (beforeBank != null ? beforeBank.hashCode() : 0);
        result = 31 * result + (beforeBankaddress != null ? beforeBankaddress.hashCode() : 0);
        result = 31 * result + (beforeBankbook != null ? beforeBankbook.hashCode() : 0);
        result = 31 * result + (beforeBankcard != null ? beforeBankcard.hashCode() : 0);
        result = 31 * result + (beforeBankprovince != null ? beforeBankprovince.hashCode() : 0);
        result = 31 * result + (beforeBankcity != null ? beforeBankcity.hashCode() : 0);
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (logType != null ? logType.hashCode() : 0);
        result = 31 * result + (logUserCode != null ? logUserCode.hashCode() : 0);
        result = 31 * result + (afterBank != null ? afterBank.hashCode() : 0);
        result = 31 * result + (afterBankaddress != null ? afterBankaddress.hashCode() : 0);
        result = 31 * result + (afterBankbook != null ? afterBankbook.hashCode() : 0);
        result = 31 * result + (afterBankcard != null ? afterBankcard.hashCode() : 0);
        result = 31 * result + (afterBankprovince != null ? afterBankprovince.hashCode() : 0);
        result = 31 * result + (afterBankcity != null ? afterBankcity.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("logId").append("='").append(getLogId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("beforeBank").append("='").append(getBeforeBank()).append("', ");
        sb.append("beforeBankaddress").append("='").append(getBeforeBankaddress()).append("', ");
        sb.append("beforeBankbook").append("='").append(getBeforeBankbook()).append("', ");
        sb.append("beforeBankcard").append("='").append(getBeforeBankcard()).append("', ");
        sb.append("beforeBankprovince").append("='").append(getBeforeBankprovince()).append("', ");
        sb.append("beforeBankcity").append("='").append(getBeforeBankcity()).append("', ");
        sb.append("logTime").append("='").append(getLogTime()).append("', ");
        sb.append("logType").append("='").append(getLogType()).append("', ");
        sb.append("logUserCode").append("='").append(getLogUserCode()).append("', ");
        sb.append("afterBank").append("='").append(getAfterBank()).append("', ");
        sb.append("afterBankaddress").append("='").append(getAfterBankaddress()).append("', ");
        sb.append("afterBankbook").append("='").append(getAfterBankbook()).append("', ");
        sb.append("afterBankcard").append("='").append(getAfterBankcard()).append("', ");
        sb.append("afterBankprovince").append("='").append(getAfterBankprovince()).append("', ");
        sb.append("afterBankcity").append("='").append(getAfterBankcity()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
