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
@Table(name="TABLE_TEST_WCF")

@XmlRootElement
public class TableTestWcf extends BaseObject implements Serializable {
    private Long TId;
    private String TName;
    private String TDesc;

    @Id      @Column(name="T_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getTId() {
        return this.TId;
    }
    
    public void setTId(Long TId) {
        this.TId = TId;
    }
    
    @Column(name="T_NAME", length=20)
    public String getTName() {
        return this.TName;
    }
    
    public void setTName(String TName) {
        this.TName = TName;
    }
    
    @Column(name="T_DESC", length=200)
    public String getTDesc() {
        return this.TDesc;
    }
    
    public void setTDesc(String TDesc) {
        this.TDesc = TDesc;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableTestWcf pojo = (TableTestWcf) o;

        if (TName != null ? !TName.equals(pojo.TName) : pojo.TName != null) return false;
        if (TDesc != null ? !TDesc.equals(pojo.TDesc) : pojo.TDesc != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (TName != null ? TName.hashCode() : 0);
        result = 31 * result + (TDesc != null ? TDesc.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("TId").append("='").append(getTId()).append("', ");
        sb.append("TName").append("='").append(getTName()).append("', ");
        sb.append("TDesc").append("='").append(getTDesc()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
