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
@Table(name="FI_FUNDBOOK_BALANCE")

@XmlRootElement
public class FiFundbookBalance extends BaseObject implements Serializable {
	
    private Long fbbId;
    private String userCode;
    private BigDecimal balance;
    private String bankbookType;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bankbook")
	@SequenceGenerator(name = "seq_bankbook", sequenceName = "SEQ_BANKBOOK", allocationSize = 1)
    @Column(name="FBB_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId      
    public Long getFbbId() {
        return this.fbbId;
    }
    
    public void setFbbId(Long fbbId) {
        this.fbbId = fbbId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="BALANCE", nullable=false, precision=18, scale=4)
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Column(name="BANKBOOK_TYPE", nullable=false, length=1)
    public String getBankbookType() {
        return this.bankbookType;
    }
    
    public void setBankbookType(String bankbookType) {
        this.bankbookType = bankbookType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiFundbookBalance pojo = (FiFundbookBalance) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (balance != null ? !balance.equals(pojo.balance) : pojo.balance != null) return false;
        if (bankbookType != null ? !bankbookType.equals(pojo.bankbookType) : pojo.bankbookType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (bankbookType != null ? bankbookType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fbbId").append("='").append(getFbbId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("balance").append("='").append(getBalance()).append("', ");
        sb.append("bankbookType").append("='").append(getBankbookType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
