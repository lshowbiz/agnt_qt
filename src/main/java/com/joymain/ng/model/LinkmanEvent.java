package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="LINKMAN_EVENT")

@XmlRootElement
public class LinkmanEvent extends BaseObject implements Serializable {
    private Long id;
    private String mName;//linkmanId;客户姓名
    private String mCode;//lcId;客户编号
    private String title;
    private String eventType;
    private Date time;
    private String description;
    private String userCode;
    // 联系人姓名
    private String other;

    @Id    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) 
    @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }   
    
    
    @Column(name="M_NAME", length=32)
    public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	 @Column(name="M_CODE", length=32)
	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	@Column(name="TITLE", length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="EVENT_TYPE", length=5)
    public String getEventType() {
        return this.eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIME", length=7)
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }
    
    @Column(name="DESCRIPTION", length=1000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

        LinkmanEvent pojo = (LinkmanEvent) o;

        if (mName != null ? !mName.equals(pojo.mName) : pojo.mName != null) return false;
        if (mCode != null ? !mCode.equals(pojo.mCode) : pojo.mCode != null) return false;
        if (title != null ? !title.equals(pojo.title) : pojo.title != null) return false;
        if (eventType != null ? !eventType.equals(pojo.eventType) : pojo.eventType != null) return false;
        if (time != null ? !time.equals(pojo.time) : pojo.time != null) return false;
        if (description != null ? !description.equals(pojo.description) : pojo.description != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mCode != null ? mCode.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("mName").append("='").append(getmName()).append("', ");
        sb.append("mCode").append("='").append(getmCode()).append("', ");
        sb.append("title").append("='").append(getTitle()).append("', ");
        sb.append("eventType").append("='").append(getEventType()).append("', ");
        sb.append("time").append("='").append(getTime()).append("', ");
        sb.append("description").append("='").append(getDescription()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
