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

@Entity
@Table(name="MI_COIN_LOG")

@XmlRootElement
public class MiCoinLog extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private String coinType;
    private String dealType;
    private Date createTime;
    private String createNo;
    private BigDecimal coin;
    private String status;
    private Date sendDate;
    private String sendNo;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)  
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
    
    @Column(name="COIN_TYPE", length=20)
    public String getCoinType() {
        return this.coinType;
    }
    
    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }
    
    @Column(name="DEAL_TYPE", length=2)
    public String getDealType() {
        return this.dealType;
    }
    
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_NO", length=20)
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    
    @Column(name="COIN", precision=22, scale=0)
    public BigDecimal getCoin() {
        return this.coin;
    }
    
    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }
    
    @Column(name="STATUS", length=2)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name="SEND_DATE", length=7)
    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
    @Column(name="SEND_NO", length=20)
    public String getSendNo() {
        return this.sendNo;
    }
    
    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiCoinLog pojo = (MiCoinLog) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (coinType != null ? !coinType.equals(pojo.coinType) : pojo.coinType != null) return false;
        if (dealType != null ? !dealType.equals(pojo.dealType) : pojo.dealType != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;
        if (coin != null ? !coin.equals(pojo.coin) : pojo.coin != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (sendDate != null ? !sendDate.equals(pojo.sendDate) : pojo.sendDate != null) return false;
        if (sendNo != null ? !sendNo.equals(pojo.sendNo) : pojo.sendNo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (coinType != null ? coinType.hashCode() : 0);
        result = 31 * result + (dealType != null ? dealType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);
        result = 31 * result + (coin != null ? coin.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (sendNo != null ? sendNo.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("coinType").append("='").append(getCoinType()).append("', ");
        sb.append("dealType").append("='").append(getDealType()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("', ");
        sb.append("coin").append("='").append(getCoin()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("sendDate").append("='").append(getSendDate()).append("', ");
        sb.append("sendNo").append("='").append(getSendNo()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
