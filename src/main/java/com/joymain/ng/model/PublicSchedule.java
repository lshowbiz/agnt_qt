package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="PUBLIC_SCHEDULE")

@XmlRootElement
public class PublicSchedule extends BaseObject implements Serializable {
    private Long psId;
    private String name;
    private Date startTime;
    private Date endTime;
    private String content;
    private String type;

    @Id      @Column(name="PS_ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getPsId() {
        return this.psId;
    }
    
    public void setPsId(Long psId) {
        this.psId = psId;
    }
    
    @Column(name="NAME", length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="START_TIME", length=7)
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="END_TIME", length=7)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicSchedule pojo = (PublicSchedule) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("psId").append("='").append(getPsId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @Column(name="CONTENT", length=200)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 @Column(name="TYPE", length=20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
