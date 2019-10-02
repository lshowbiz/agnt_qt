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
public class AmAnnouncePermitId extends BaseObject implements Serializable {
    private String aaNo;
    private String permitNo;


    @Column(name="AA_NO", length=13)
    public String getAaNo() {
        return this.aaNo;
    }
    
    public void setAaNo(String aaNo) {
        this.aaNo = aaNo;
    }

    @Column(name="PERMIT_NO", length=3)
    public String getPermitNo() {
        return this.permitNo;
    }
    
    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmAnnouncePermitId pojo = (AmAnnouncePermitId) o;

        if (aaNo != null ? !aaNo.equals(pojo.aaNo) : pojo.aaNo != null) return false;
        if (permitNo != null ? !permitNo.equals(pojo.permitNo) : pojo.permitNo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (aaNo != null ? aaNo.hashCode() : 0);
        result = (permitNo != null ? permitNo.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("aaNo").append("='").append(getAaNo()).append("', ");
        sb.append("permitNo").append("='").append(getPermitNo()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
