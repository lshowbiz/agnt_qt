package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JBD_TRAVEL_POINT_ALL")

@XmlRootElement
public class JbdTravelPointAll extends BaseObject implements Serializable {
    private JbdTravelPointAllId id;

    @EmbeddedId     @AttributeOverrides( {
        @AttributeOverride(name="FYear", column=@Column(name="F_YEAR", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="userCode", column=@Column(name="USER_CODE", nullable=false, length=20) ), 
        @AttributeOverride(name="passStar", column=@Column(name="PASS_STAR", precision=2, scale=0) ), 
        @AttributeOverride(name="total", column=@Column(name="TOTAL", precision=22, scale=0) ) } ) @DocumentId    
    public JbdTravelPointAllId getId() {
        return this.id;
    }
    
    public void setId(JbdTravelPointAllId id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdTravelPointAll pojo = (JbdTravelPointAll) o;


        return true;
    }

    public int hashCode() {
        int result = 0;

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
