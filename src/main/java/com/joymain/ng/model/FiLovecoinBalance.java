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
/**
 * 爱心积分余额账户对象
 * @author Administrator
 *
 */
@Entity
@Table(name="FI_LOVECOIN_BALANCE")

@XmlRootElement
public class FiLovecoinBalance extends BaseObject implements Serializable {
    private String userCode;
    private BigDecimal balance;

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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiLovecoinBalance pojo = (FiLovecoinBalance) o;

        if (balance != null ? !balance.equals(pojo.balance) : pojo.balance != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (balance != null ? balance.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("balance").append("='").append(getBalance()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
