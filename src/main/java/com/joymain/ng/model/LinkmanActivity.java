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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="LINKMAN_ACTIVITY")

@XmlRootElement
public class LinkmanActivity extends BaseObject implements Serializable {
    private Long id;
    private String organizer;
    private String participants;
    private Date beginTime;
    private Date endTime;
    private String preExpenses;
    private String actualExpenditure;
    private String eventType;
    private String topic;
    private String hostVenue;
    private String content;
    private String purpose;
    private String activityResult;
    private String summary;
    private String userCode;
    private String eventName;

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
    
    @Column(name="ORGANIZER", length=100)
    public String getOrganizer() {
        return this.organizer;
    }
    
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
    
    @Column(name="PARTICIPANTS", length=300)
    public String getParticipants() {
        return this.participants;
    }
    
    public void setParticipants(String participants) {
        this.participants = participants;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BEGIN_TIME", length=7)
    public Date getBeginTime() {
        return this.beginTime;
    }
    
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="END_TIME", length=7)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Column(name="PRE_EXPENSES", length=100)
    public String getPreExpenses() {
        return this.preExpenses;
    }
    
    public void setPreExpenses(String preExpenses) {
        this.preExpenses = preExpenses;
    }
    
    @Column(name="ACTUAL_EXPENDITURE", length=100)
    public String getActualExpenditure() {
        return this.actualExpenditure;
    }
    
    public void setActualExpenditure(String actualExpenditure) {
        this.actualExpenditure = actualExpenditure;
    }
    
    @Column(name="EVENT_TYPE", length=2)
    public String getEventType() {
        return this.eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    @Column(name="TOPIC", length=100)
    public String getTopic() {
        return this.topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    @Column(name="HOST_VENUE", length=200)
    public String getHostVenue() {
        return this.hostVenue;
    }
    
    public void setHostVenue(String hostVenue) {
        this.hostVenue = hostVenue;
    }
    
    @Column(name="CONTENT", length=600)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="PURPOSE", length=500)
    public String getPurpose() {
        return this.purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    @Column(name="ACTIVITY_RESULT", length=500)
    public String getActivityResult() {
        return this.activityResult;
    }
    
    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult;
    }
    
    @Column(name="SUMMARY", length=500)
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(name="EVENT_NAME", length=100)
    public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkmanActivity pojo = (LinkmanActivity) o;

        if (organizer != null ? !organizer.equals(pojo.organizer) : pojo.organizer != null) return false;
        if (participants != null ? !participants.equals(pojo.participants) : pojo.participants != null) return false;
        if (beginTime != null ? !beginTime.equals(pojo.beginTime) : pojo.beginTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (preExpenses != null ? !preExpenses.equals(pojo.preExpenses) : pojo.preExpenses != null) return false;
        if (actualExpenditure != null ? !actualExpenditure.equals(pojo.actualExpenditure) : pojo.actualExpenditure != null) return false;
        if (eventType != null ? !eventType.equals(pojo.eventType) : pojo.eventType != null) return false;
        if (topic != null ? !topic.equals(pojo.topic) : pojo.topic != null) return false;
        if (hostVenue != null ? !hostVenue.equals(pojo.hostVenue) : pojo.hostVenue != null) return false;
        if (content != null ? !content.equals(pojo.content) : pojo.content != null) return false;
        if (purpose != null ? !purpose.equals(pojo.purpose) : pojo.purpose != null) return false;
        if (activityResult != null ? !activityResult.equals(pojo.activityResult) : pojo.activityResult != null) return false;
        if (summary != null ? !summary.equals(pojo.summary) : pojo.summary != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (eventName != null ? !eventName.equals(pojo.eventName) : pojo.eventName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (organizer != null ? organizer.hashCode() : 0);
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (preExpenses != null ? preExpenses.hashCode() : 0);
        result = 31 * result + (actualExpenditure != null ? actualExpenditure.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (hostVenue != null ? hostVenue.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (purpose != null ? purpose.hashCode() : 0);
        result = 31 * result + (activityResult != null ? activityResult.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("organizer").append("='").append(getOrganizer()).append("', ");
        sb.append("participants").append("='").append(getParticipants()).append("', ");
        sb.append("beginTime").append("='").append(getBeginTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("preExpenses").append("='").append(getPreExpenses()).append("', ");
        sb.append("actualExpenditure").append("='").append(getActualExpenditure()).append("', ");
        sb.append("eventType").append("='").append(getEventType()).append("', ");
        sb.append("topic").append("='").append(getTopic()).append("', ");
        sb.append("hostVenue").append("='").append(getHostVenue()).append("', ");
        sb.append("content").append("='").append(getContent()).append("', ");
        sb.append("purpose").append("='").append(getPurpose()).append("', ");
        sb.append("activityResult").append("='").append(getActivityResult()).append("', ");
        sb.append("summary").append("='").append(getSummary()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("eventName").append("='").append(getEventName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
