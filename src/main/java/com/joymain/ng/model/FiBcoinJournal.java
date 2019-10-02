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
 * 欢乐积分条目业务实体
 * @author hywen
 *
 */
@Entity
@Table(name="FI_BCOIN_JOURNAL")

@XmlRootElement
public class FiBcoinJournal extends BaseObject implements Serializable {
    private Long journalId;
    private String userCode;
    private Integer serial;
    private String dealType;
    private Date dealDate;
    private BigDecimal coin;
    private String notes;
    private BigDecimal balance;
    private String remark;
    private String createrCode;
    private String createrName;
    private Date createTime;
    private Integer moneyType;
    private String uniqueCode;
    private Long appId;
    private String dataType;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_point")
	@SequenceGenerator(name = "seq_point", sequenceName = "SEQ_POINT", allocationSize = 1)
    @Column(name="JOURNAL_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getJournalId() {
        return this.journalId;
    }
    
    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="SERIAL", precision=8, scale=0)
    public Integer getSerial() {
        return this.serial;
    }
    
    public void setSerial(Integer serial) {
        this.serial = serial;
    }
    
    @Column(name="DEAL_TYPE", length=1)
    public String getDealType() {
        return this.dealType;
    }
    
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    
    @Column(name="DEAL_DATE", length=7)
    public Date getDealDate() {
        return this.dealDate;
    }
    
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
    
    @Column(name="COIN", precision=18, scale=4)
    public BigDecimal getCoin() {
        return this.coin;
    }
    
    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }
    
    @Column(name="NOTES", length=4000)
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @Column(name="BALANCE", precision=18, scale=4)
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Column(name="REMARK", length=4000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="CREATER_CODE", length=20)
    public String getCreaterCode() {
        return this.createrCode;
    }
    
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }
    
    @Column(name="CREATER_NAME", length=80)
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
    
    @Column(name="MONEY_TYPE", precision=4, scale=0)
    public Integer getMoneyType() {
        return this.moneyType;
    }
    
    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }
    
    @Column(name="UNIQUE_CODE", length=200)
    public String getUniqueCode() {
        return this.uniqueCode;
    }
    
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
    
    @Column(name="APP_ID", precision=12, scale=0)
    public Long getAppId() {
        return this.appId;
    }
    
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    
    //数据来源,1:PC, 2:手机
    @Column(name="DATA_TYPE", length=1)
    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiBcoinJournal pojo = (FiBcoinJournal) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (serial != null ? !serial.equals(pojo.serial) : pojo.serial != null) return false;
        if (dealType != null ? !dealType.equals(pojo.dealType) : pojo.dealType != null) return false;
        if (dealDate != null ? !dealDate.equals(pojo.dealDate) : pojo.dealDate != null) return false;
        if (coin != null ? !coin.equals(pojo.coin) : pojo.coin != null) return false;
        if (notes != null ? !notes.equals(pojo.notes) : pojo.notes != null) return false;
        if (balance != null ? !balance.equals(pojo.balance) : pojo.balance != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (createrCode != null ? !createrCode.equals(pojo.createrCode) : pojo.createrCode != null) return false;
        if (createrName != null ? !createrName.equals(pojo.createrName) : pojo.createrName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (moneyType != null ? !moneyType.equals(pojo.moneyType) : pojo.moneyType != null) return false;
        if (uniqueCode != null ? !uniqueCode.equals(pojo.uniqueCode) : pojo.uniqueCode != null) return false;
        if (appId != null ? !appId.equals(pojo.appId) : pojo.appId != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (serial != null ? serial.hashCode() : 0);
        result = 31 * result + (dealType != null ? dealType.hashCode() : 0);
        result = 31 * result + (dealDate != null ? dealDate.hashCode() : 0);
        result = 31 * result + (coin != null ? coin.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createrCode != null ? createrCode.hashCode() : 0);
        result = 31 * result + (createrName != null ? createrName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (moneyType != null ? moneyType.hashCode() : 0);
        result = 31 * result + (uniqueCode != null ? uniqueCode.hashCode() : 0);
        result = 31 * result + (appId != null ? appId.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("journalId").append("='").append(getJournalId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("serial").append("='").append(getSerial()).append("', ");
        sb.append("dealType").append("='").append(getDealType()).append("', ");
        sb.append("dealDate").append("='").append(getDealDate()).append("', ");
        sb.append("coin").append("='").append(getCoin()).append("', ");
        sb.append("notes").append("='").append(getNotes()).append("', ");
        sb.append("balance").append("='").append(getBalance()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("createrCode").append("='").append(getCreaterCode()).append("', ");
        sb.append("createrName").append("='").append(getCreaterName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("moneyType").append("='").append(getMoneyType()).append("', ");
        sb.append("uniqueCode").append("='").append(getUniqueCode()).append("', ");
        sb.append("appId").append("='").append(getAppId()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
