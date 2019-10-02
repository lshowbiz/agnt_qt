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
import java.math.BigDecimal;

@Entity
@Table(name="JFI_DEPOSIT_BALANCE")

@XmlRootElement
public class JfiDepositBalance extends BaseObject implements Serializable {
    private Long fdbId;
    private String userCode;
    private BigDecimal balance;
    private String depositType;

 
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="FDB_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId
    public Long getFdbId() {
        return this.fdbId;
    }
    
    public void setFdbId(Long fdbId) {
        this.fdbId = fdbId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=20)
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
    
    @Column(name="DEPOSIT_TYPE", length=1)
    public String getDepositType() {
        return this.depositType;
    }
    
    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JfiDepositBalance pojo = (JfiDepositBalance) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (balance != null ? !balance.equals(pojo.balance) : pojo.balance != null) return false;
        if (depositType != null ? !depositType.equals(pojo.depositType) : pojo.depositType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (depositType != null ? depositType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fdbId").append("='").append(getFdbId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("balance").append("='").append(getBalance()).append("', ");
        sb.append("depositType").append("='").append(getDepositType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
