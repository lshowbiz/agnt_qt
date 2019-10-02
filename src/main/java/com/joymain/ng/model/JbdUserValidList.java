package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JBD_USER_VALID_LIST")

@XmlRootElement
public class JbdUserValidList extends BaseObject implements Serializable {
    private Long id;
    private JmiMember jmiMember;
    private Integer oldFreezeStatus;
    private Integer newFreezeStatus;
    private Integer wweek;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bd")
	@SequenceGenerator(name = "seq_bd", sequenceName = "SEQ_BD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)      
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    
    @Column(name="OLD_FREEZE_STATUS", precision=22, scale=0)
    public Integer getOldFreezeStatus() {
        return this.oldFreezeStatus;
    }
    
    public void setOldFreezeStatus(Integer oldFreezeStatus) {
        this.oldFreezeStatus = oldFreezeStatus;
    }
    
    @Column(name="NEW_FREEZE_STATUS", precision=22, scale=0)
    public Integer getNewFreezeStatus() {
        return this.newFreezeStatus;
    }
    
    public void setNewFreezeStatus(Integer newFreezeStatus) {
        this.newFreezeStatus = newFreezeStatus;
    }
    


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdUserValidList pojo = (JbdUserValidList) o;

        if (oldFreezeStatus != null ? !oldFreezeStatus.equals(pojo.oldFreezeStatus) : pojo.oldFreezeStatus != null) return false;
        if (newFreezeStatus != null ? !newFreezeStatus.equals(pojo.newFreezeStatus) : pojo.newFreezeStatus != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (oldFreezeStatus != null ? oldFreezeStatus.hashCode() : 0);
        result = 31 * result + (newFreezeStatus != null ? newFreezeStatus.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("oldFreezeStatus").append("='").append(getOldFreezeStatus()).append("', ");
        sb.append("newFreezeStatus").append("='").append(getNewFreezeStatus()).append("', ");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToOne
    @JoinColumn(name = "USER_CODE", nullable = false,  updatable = false)
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

    @Column(name="W_WEEK", precision=22, scale=0)
	public Integer getWweek() {
		return wweek;
	}

	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}

}
