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
@Table(name="AM_ANNOUNCE_PERMIT")

@XmlRootElement
public class AmAnnouncePermit extends BaseObject implements Serializable {
    private AmAnnouncePermitId id;

    @EmbeddedId     @AttributeOverrides( {
        @AttributeOverride(name="aaNo", column=@Column(name="AA_NO", length=13) ), 
        @AttributeOverride(name="permitNo", column=@Column(name="PERMIT_NO", length=3) ) } )  
    public AmAnnouncePermitId getId() {
        return this.id;
    }
    
    public void setId(AmAnnouncePermitId id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmAnnouncePermit pojo = (AmAnnouncePermit) o;


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
