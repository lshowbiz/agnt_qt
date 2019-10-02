package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="INW_DEMANDSORT")

@XmlRootElement
public class InwDemandsort extends BaseObject implements Serializable {
    private Long id;
    private String name;
    private String requireIntroduction;
    private String image;
    private String other;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="NAME", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="REQUIRE_INTRODUCTION", length=500)
    public String getRequireIntroduction() {
        return this.requireIntroduction;
    }
    
    public void setRequireIntroduction(String requireIntroduction) {
        this.requireIntroduction = requireIntroduction;
    }
    
    @Column(name="IMAGE", length=300)
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    @Column(name="OTHER", length=100)
    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InwDemandsort pojo = (InwDemandsort) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (requireIntroduction != null ? !requireIntroduction.equals(pojo.requireIntroduction) : pojo.requireIntroduction != null) return false;
        if (image != null ? !image.equals(pojo.image) : pojo.image != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (requireIntroduction != null ? requireIntroduction.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("requireIntroduction").append("='").append(getRequireIntroduction()).append("', ");
        sb.append("image").append("='").append(getImage()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
