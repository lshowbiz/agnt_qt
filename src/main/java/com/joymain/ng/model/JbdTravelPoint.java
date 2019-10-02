package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

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
@Table(name="JBD_TRAVEL_POINT")

@XmlRootElement
public class JbdTravelPoint extends BaseObject implements Serializable {
    private String userCode;
    private Integer passStar;
    private BigDecimal total;

    @Id     
    @Column(name="USER_CODE", unique=true, nullable=false, length=20) 
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="PASS_STAR", precision=2, scale=0)
    
    public Integer getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Integer passStar) {
        this.passStar = passStar;
    }
    
    @Column(name="TOTAL", precision=22, scale=0)
    
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdTravelPoint pojo = (JbdTravelPoint) o;

        if (passStar != null ? !passStar.equals(pojo.passStar) : pojo.passStar != null) return false;
        if (total != null ? !total.equals(pojo.total) : pojo.total != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (passStar != null ? passStar.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("passStar").append("='").append(getPassStar()).append("', ");
        sb.append("total").append("='").append(getTotal()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
