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
import java.math.BigDecimal;
/**
 * 基金临时条目业务实体
 * @author hywen
 *
 */
@Entity
@Table(name="FI_BANKBOOK_TEMP")

@XmlRootElement
public class FiBankbookTemp extends BaseObject implements Serializable {
    private Long tempId;
    private String companyCode;
    private String userCode;
    private Long serial;
    private String dealType;
    private Integer moneyType;
    private BigDecimal money;
    private String notes;
    private Integer status;
    private String createrCode;
    private String createrName;
    private Date createTime;
    private String lastUpdaterCode;
    private String lastUpdaterName;
    private Date lastUpdateTime;
    private String checkerCode;
    private String checkerName;
    private Date checkeTime;
    private Integer checkType;
    private Date dealDate;
    private String checkMsg;
    private String createNo;
    private String bankbookType;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="TEMP_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getTempId() {
        return this.tempId;
    }
    
    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="USER_CODE", nullable=false, length=200)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="SERIAL", precision=8, scale=0)
    public Long getSerial() {
        return this.serial;
    }
    
    public void setSerial(Long serial) {
        this.serial = serial;
    }
    
    @Column(name="DEAL_TYPE", length=1)
    public String getDealType() {
        return this.dealType;
    }
    
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    
    @Column(name="MONEY_TYPE", precision=4, scale=0)
    public Integer getMoneyType() {
        return this.moneyType;
    }
    
    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }
    
    @Column(name="MONEY", precision=18, scale=4)
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    @Column(name="NOTES", length=4000)
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @Column(name="STATUS", length=2)
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="CREATER_CODE", length=200)
    public String getCreaterCode() {
        return this.createrCode;
    }
    
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }
    
    @Column(name="CREATER_NAME", length=500)
    public String getCreaterName() {
        return this.createrName;
    }
    
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
 
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="LAST_UPDATER_CODE", length=200)
    public String getLastUpdaterCode() {
        return this.lastUpdaterCode;
    }
    
    public void setLastUpdaterCode(String lastUpdaterCode) {
        this.lastUpdaterCode = lastUpdaterCode;
    }
    
    @Column(name="LAST_UPDATER_NAME", length=500)
    public String getLastUpdaterName() {
        return this.lastUpdaterName;
    }
    
    public void setLastUpdaterName(String lastUpdaterName) {
        this.lastUpdaterName = lastUpdaterName;
    }
   
    @Column(name="LAST_UPDATE_TIME", length=7)
    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }
    
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    
    @Column(name="CHECKER_CODE", length=200)
    public String getCheckerCode() {
        return this.checkerCode;
    }
    
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }
    
    @Column(name="CHECKER_NAME", length=500)
    public String getCheckerName() {
        return this.checkerName;
    }
    
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    
    @Column(name="CHECKE_TIME", length=7)
    public Date getCheckeTime() {
        return this.checkeTime;
    }
    
    public void setCheckeTime(Date checkeTime) {
        this.checkeTime = checkeTime;
    }
    
    @Column(name="CHECK_TYPE", length=2)
    public Integer getCheckType() {
        return this.checkType;
    }
    
    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }
    
    @Column(name="DEAL_DATE", length=7)
    public Date getDealDate() {
        return this.dealDate;
    }
    
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
    
    @Column(name="CHECK_MSG", length=30)
    public String getCheckMsg() {
        return this.checkMsg;
    }
    
    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }
    
    @Column(name="CREATE_NO", length=200)
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    
    @Column(name="BANKBOOK_TYPE", nullable=false, length=10)
    public String getBankbookType() {
        return this.bankbookType;
    }
    
    public void setBankbookType(String bankbookType) {
        this.bankbookType = bankbookType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiBankbookTemp pojo = (FiBankbookTemp) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (serial != null ? !serial.equals(pojo.serial) : pojo.serial != null) return false;
        if (dealType != null ? !dealType.equals(pojo.dealType) : pojo.dealType != null) return false;
        if (moneyType != null ? !moneyType.equals(pojo.moneyType) : pojo.moneyType != null) return false;
        if (money != null ? !money.equals(pojo.money) : pojo.money != null) return false;
        if (notes != null ? !notes.equals(pojo.notes) : pojo.notes != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (createrCode != null ? !createrCode.equals(pojo.createrCode) : pojo.createrCode != null) return false;
        if (createrName != null ? !createrName.equals(pojo.createrName) : pojo.createrName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (lastUpdaterCode != null ? !lastUpdaterCode.equals(pojo.lastUpdaterCode) : pojo.lastUpdaterCode != null) return false;
        if (lastUpdaterName != null ? !lastUpdaterName.equals(pojo.lastUpdaterName) : pojo.lastUpdaterName != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(pojo.lastUpdateTime) : pojo.lastUpdateTime != null) return false;
        if (checkerCode != null ? !checkerCode.equals(pojo.checkerCode) : pojo.checkerCode != null) return false;
        if (checkerName != null ? !checkerName.equals(pojo.checkerName) : pojo.checkerName != null) return false;
        if (checkeTime != null ? !checkeTime.equals(pojo.checkeTime) : pojo.checkeTime != null) return false;
        if (checkType != null ? !checkType.equals(pojo.checkType) : pojo.checkType != null) return false;
        if (dealDate != null ? !dealDate.equals(pojo.dealDate) : pojo.dealDate != null) return false;
        if (checkMsg != null ? !checkMsg.equals(pojo.checkMsg) : pojo.checkMsg != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;
        if (bankbookType != null ? !bankbookType.equals(pojo.bankbookType) : pojo.bankbookType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (serial != null ? serial.hashCode() : 0);
        result = 31 * result + (dealType != null ? dealType.hashCode() : 0);
        result = 31 * result + (moneyType != null ? moneyType.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createrCode != null ? createrCode.hashCode() : 0);
        result = 31 * result + (createrName != null ? createrName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastUpdaterCode != null ? lastUpdaterCode.hashCode() : 0);
        result = 31 * result + (lastUpdaterName != null ? lastUpdaterName.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (checkerCode != null ? checkerCode.hashCode() : 0);
        result = 31 * result + (checkerName != null ? checkerName.hashCode() : 0);
        result = 31 * result + (checkeTime != null ? checkeTime.hashCode() : 0);
        result = 31 * result + (checkType != null ? checkType.hashCode() : 0);
        result = 31 * result + (dealDate != null ? dealDate.hashCode() : 0);
        result = 31 * result + (checkMsg != null ? checkMsg.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);
        result = 31 * result + (bankbookType != null ? bankbookType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("tempId").append("='").append(getTempId()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("serial").append("='").append(getSerial()).append("', ");
        sb.append("dealType").append("='").append(getDealType()).append("', ");
        sb.append("moneyType").append("='").append(getMoneyType()).append("', ");
        sb.append("money").append("='").append(getMoney()).append("', ");
        sb.append("notes").append("='").append(getNotes()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("createrCode").append("='").append(getCreaterCode()).append("', ");
        sb.append("createrName").append("='").append(getCreaterName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("lastUpdaterCode").append("='").append(getLastUpdaterCode()).append("', ");
        sb.append("lastUpdaterName").append("='").append(getLastUpdaterName()).append("', ");
        sb.append("lastUpdateTime").append("='").append(getLastUpdateTime()).append("', ");
        sb.append("checkerCode").append("='").append(getCheckerCode()).append("', ");
        sb.append("checkerName").append("='").append(getCheckerName()).append("', ");
        sb.append("checkeTime").append("='").append(getCheckeTime()).append("', ");
        sb.append("checkType").append("='").append(getCheckType()).append("', ");
        sb.append("dealDate").append("='").append(getDealDate()).append("', ");
        sb.append("checkMsg").append("='").append(getCheckMsg()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("', ");
        sb.append("bankbookType").append("='").append(getBankbookType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
