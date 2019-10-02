package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

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
import java.math.BigDecimal;

@Entity
@Table(name="JBD_BONUS_BALANCE")

@XmlRootElement
public class JbdBonusBalance extends BaseObject implements Serializable {
    private String userCode;
    private BigDecimal bonusBalance;
    private BigDecimal assignedBonus;
    private String flag;
    private String status;
    private String checkUser;
    private Date checkTime;
    private Date flagTime;

    @Id      @Column(name="USER_CODE", unique=true, nullable=false, length=20) @DocumentId    
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="BONUS_BALANCE", precision=22, scale=0)
    
    public BigDecimal getBonusBalance() {
        return this.bonusBalance;
    }
    
    public void setBonusBalance(BigDecimal bonusBalance) {
        this.bonusBalance = bonusBalance;
    }
    
    @Column(name="ASSIGNED_BONUS", precision=22, scale=0)
    
    public BigDecimal getAssignedBonus() {
        return this.assignedBonus;
    }
    
    public void setAssignedBonus(BigDecimal assignedBonus) {
        this.assignedBonus = assignedBonus;
    }
    
    @Column(name="FLAG", length=1)
    
    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    @Column(name="STATUS", length=1)
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="CHECK_USER", length=20)
    
    public String getCheckUser() {
        return this.checkUser;
    }
    
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    @Column(name="CHECK_TIME", length=7)
    
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    @Column(name="FLAG_TIME", length=7)
    
    public Date getFlagTime() {
        return this.flagTime;
    }
    
    public void setFlagTime(Date flagTime) {
        this.flagTime = flagTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdBonusBalance pojo = (JbdBonusBalance) o;

        if (bonusBalance != null ? !bonusBalance.equals(pojo.bonusBalance) : pojo.bonusBalance != null) return false;
        if (assignedBonus != null ? !assignedBonus.equals(pojo.assignedBonus) : pojo.assignedBonus != null) return false;
        if (flag != null ? !flag.equals(pojo.flag) : pojo.flag != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (checkUser != null ? !checkUser.equals(pojo.checkUser) : pojo.checkUser != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (flagTime != null ? !flagTime.equals(pojo.flagTime) : pojo.flagTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (bonusBalance != null ? bonusBalance.hashCode() : 0);
        result = 31 * result + (assignedBonus != null ? assignedBonus.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (checkUser != null ? checkUser.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (flagTime != null ? flagTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("bonusBalance").append("='").append(getBonusBalance()).append("', ");
        sb.append("assignedBonus").append("='").append(getAssignedBonus()).append("', ");
        sb.append("flag").append("='").append(getFlag()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("checkUser").append("='").append(getCheckUser()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("flagTime").append("='").append(getFlagTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
