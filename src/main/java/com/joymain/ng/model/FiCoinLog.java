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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="FI_COIN_LOG")

@XmlRootElement
public class FiCoinLog extends BaseObject implements Serializable {
    private Long fclId;
    private String userCode;
    private String coinType;
    private String dealType;
    private Date createTime;
    private String createUser;
    private String receiveCoin;
    private Long coin;
    private String uniqueCode;
    private String status;

    @Id      @Column(name="FCL_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getFclId() {
        return this.fclId;
    }
    
    public void setFclId(Long fclId) {
        this.fclId = fclId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="COIN_TYPE", nullable=false, length=2)
    public String getCoinType() {
        return this.coinType;
    }
    
    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }
    
    @Column(name="DEAL_TYPE", nullable=false, length=2)
    public String getDealType() {
        return this.dealType;
    }
    
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_USER", nullable=false, length=20)
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    @Column(name="RECEIVE_COIN", nullable=false, length=4000)
    public String getReceiveCoin() {
        return this.receiveCoin;
    }
    
    public void setReceiveCoin(String receiveCoin) {
        this.receiveCoin = receiveCoin;
    }
    
    @Column(name="COIN", nullable=false, precision=18, scale=4)
    public Long getCoin() {
        return this.coin;
    }
    
    public void setCoin(Long coin) {
        this.coin = coin;
    }
    
    @Column(name="UNIQUE_CODE", nullable=false, length=4000)
    public String getUniqueCode() {
        return this.uniqueCode;
    }
    
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
    
    @Column(name="STATUS", nullable=false, length=2)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiCoinLog pojo = (FiCoinLog) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (coinType != null ? !coinType.equals(pojo.coinType) : pojo.coinType != null) return false;
        if (dealType != null ? !dealType.equals(pojo.dealType) : pojo.dealType != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUser != null ? !createUser.equals(pojo.createUser) : pojo.createUser != null) return false;
        if (receiveCoin != null ? !receiveCoin.equals(pojo.receiveCoin) : pojo.receiveCoin != null) return false;
        if (coin != null ? !coin.equals(pojo.coin) : pojo.coin != null) return false;
        if (uniqueCode != null ? !uniqueCode.equals(pojo.uniqueCode) : pojo.uniqueCode != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (coinType != null ? coinType.hashCode() : 0);
        result = 31 * result + (dealType != null ? dealType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (receiveCoin != null ? receiveCoin.hashCode() : 0);
        result = 31 * result + (coin != null ? coin.hashCode() : 0);
        result = 31 * result + (uniqueCode != null ? uniqueCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fclId").append("='").append(getFclId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("coinType").append("='").append(getCoinType()).append("', ");
        sb.append("dealType").append("='").append(getDealType()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUser").append("='").append(getCreateUser()).append("', ");
        sb.append("receiveCoin").append("='").append(getReceiveCoin()).append("', ");
        sb.append("coin").append("='").append(getCoin()).append("', ");
        sb.append("uniqueCode").append("='").append(getUniqueCode()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
