package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Embeddable

@XmlRootElement
public class JbdTravelPointAllId extends BaseObject implements Serializable {
    private Long FYear;
    private String userCode;
    private Long passStar;
    private Long total;


    @Column(name="F_YEAR", nullable=false, precision=22, scale=0)
    public Long getFYear() {
        return this.FYear;
    }
    
    public void setFYear(Long FYear) {
        this.FYear = FYear;
    }

    @Column(name="USER_CODE", nullable=false, length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(name="PASS_STAR", precision=2, scale=0)
    public Long getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Long passStar) {
        this.passStar = passStar;
    }

    @Column(name="TOTAL", precision=22, scale=0)
    public Long getTotal() {
        return this.total;
    }
    
    public void setTotal(Long total) {
        this.total = total;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdTravelPointAllId pojo = (JbdTravelPointAllId) o;

        if (FYear != null ? !FYear.equals(pojo.FYear) : pojo.FYear != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (passStar != null ? !passStar.equals(pojo.passStar) : pojo.passStar != null) return false;
        if (total != null ? !total.equals(pojo.total) : pojo.total != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (FYear != null ? FYear.hashCode() : 0);
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (passStar != null ? passStar.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("FYear").append("='").append(getFYear()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("passStar").append("='").append(getPassStar()).append("', ");
        sb.append("total").append("='").append(getTotal()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
