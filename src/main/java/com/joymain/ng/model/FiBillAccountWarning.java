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
@Table(name="FI_BILL_ACCOUNT_WARNING",schema="JECS_CN")

@XmlRootElement
public class FiBillAccountWarning extends BaseObject implements Serializable {
    private String billAccountCode;
    private BigDecimal nowTotalAmount;

    @Id      @Column(name="BILL_ACCOUNT_CODE", unique=true, nullable=false, length=50) @DocumentId    
    public String getBillAccountCode() {
        return this.billAccountCode;
    }
    
    public void setBillAccountCode(String billAccountCode) {
        this.billAccountCode = billAccountCode;
    }
    
    @Column(name="NOW_TOTAL_AMOUNT", nullable=false, precision=18, scale=4)
    public BigDecimal getNowTotalAmount() {
        return this.nowTotalAmount;
    }
    
    public void setNowTotalAmount(BigDecimal nowTotalAmount) {
        this.nowTotalAmount = nowTotalAmount;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiBillAccountWarning pojo = (FiBillAccountWarning) o;

        if (nowTotalAmount != null ? !nowTotalAmount.equals(pojo.nowTotalAmount) : pojo.nowTotalAmount != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (nowTotalAmount != null ? nowTotalAmount.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("billAccountCode").append("='").append(getBillAccountCode()).append("', ");
        sb.append("nowTotalAmount").append("='").append(getNowTotalAmount()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
