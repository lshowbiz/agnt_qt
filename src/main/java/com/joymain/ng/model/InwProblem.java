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
@Table(name="INW_PROBLEM")

@XmlRootElement
public class InwProblem extends BaseObject implements Serializable {
    private Long id;
    private String species;
    private String ask;
    private String answer;
    private String other;

    @Id      @Column(name="ID", nullable=false, precision=22, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="SPECIES", nullable=false, length=2)
    public String getSpecies() {
        return this.species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    @Column(name="ASK", length=400)
    public String getAsk() {
        return this.ask;
    }
    
    public void setAsk(String ask) {
        this.ask = ask;
    }
    
    @Column(name="ANSWER", length=900)
    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Column(name="OTHER", length=200)
    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InwProblem pojo = (InwProblem) o;

        if (species != null ? !species.equals(pojo.species) : pojo.species != null) return false;
        if (ask != null ? !ask.equals(pojo.ask) : pojo.ask != null) return false;
        if (answer != null ? !answer.equals(pojo.answer) : pojo.answer != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (species != null ? species.hashCode() : 0);
        result = 31 * result + (ask != null ? ask.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("species").append("='").append(getSpecies()).append("', ");
        sb.append("ask").append("='").append(getAsk()).append("', ");
        sb.append("answer").append("='").append(getAnswer()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
