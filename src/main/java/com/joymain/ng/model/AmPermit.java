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

@Entity
@Table(name="AM_PERMIT")

@XmlRootElement
public class AmPermit extends BaseObject implements Serializable {
    private String permitNo;
    private String permitName;
    private String permitClass;

    @Id      @Column(name="PERMIT_NO", unique=true, nullable=false, length=3)   
    public String getPermitNo() {
        return this.permitNo;
    }
    
    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }
    
    @Column(name="PERMIT_NAME", length=50)
    public String getPermitName() {
        return this.permitName;
    }
    
    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }
    
    @Column(name="PERMIT_CLASS", length=2)
    public String getPermitClass() {
        return this.permitClass;
    }
    
    public void setPermitClass(String permitClass) {
        this.permitClass = permitClass;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmPermit pojo = (AmPermit) o;

        if (permitName != null ? !permitName.equals(pojo.permitName) : pojo.permitName != null) return false;
        if (permitClass != null ? !permitClass.equals(pojo.permitClass) : pojo.permitClass != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (permitName != null ? permitName.hashCode() : 0);
        result = 31 * result + (permitClass != null ? permitClass.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("permitNo").append("='").append(getPermitNo()).append("', ");
        sb.append("permitName").append("='").append(getPermitName()).append("', ");
        sb.append("permitClass").append("='").append(getPermitClass()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
