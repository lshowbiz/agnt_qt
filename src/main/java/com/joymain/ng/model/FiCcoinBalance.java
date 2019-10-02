package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="FI_CCOIN_BALANCE")

@XmlRootElement
public class FiCcoinBalance extends BaseObject implements Serializable {
    private String userCode;
    private BigDecimal balance;
    private Long lastJournalId;

    @Id      @Column(name="USER_CODE", unique=true, nullable=false, length=20) @DocumentId    
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="BALANCE", precision=18, scale=4)
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Column(name="LAST_JOURNAL_ID", precision=12, scale=0)
    public Long getLastJournalId() {
        return this.lastJournalId;
    }
    
    public void setLastJournalId(Long lastJournalId) {
        this.lastJournalId = lastJournalId;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiCcoinBalance pojo = (FiCcoinBalance) o;

        if (balance != null ? !balance.equals(pojo.balance) : pojo.balance != null) return false;
        if (lastJournalId != null ? !lastJournalId.equals(pojo.lastJournalId) : pojo.lastJournalId != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (lastJournalId != null ? lastJournalId.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("balance").append("='").append(getBalance()).append("', ");
        sb.append("lastJournalId").append("='").append(getLastJournalId()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
