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
@Table(name="SCHEDULE_MANAGE")

@XmlRootElement
public class ScheduleManage extends BaseObject implements Serializable {
    private Long id;
    private String scheduleName;
    private Date startTime;
    private Date endTime;
    private Long priority;
    private Long status;
    private Long linkmanId;
    private String remark;
    private Long remind;
    private Long repeat;
    private String loginUserNo;
    private Long eventType;
    private String place;

    @Id      @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId  
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_al")
    @SequenceGenerator(name = "gen_al", sequenceName = "SEQ_sd", allocationSize = 1)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="SCHEDULE_NAME", length=20)
    public String getScheduleName() {
        return this.scheduleName;
    }
    
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
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
    
    @Column(name="PRIORITY", precision=2, scale=0)
    public Long getPriority() {
        return this.priority;
    }
    
    public void setPriority(Long priority) {
        this.priority = priority;
    }
    
    @Column(name="STATUS", precision=2, scale=0)
    public Long getStatus() {
        return this.status;
    }
    
    public void setStatus(Long status) {
        this.status = status;
    }
    
    @Column(name="LINKMAN_ID", precision=22, scale=0)
    public Long getLinkmanId() {
        return this.linkmanId;
    }
    
    public void setLinkmanId(Long linkmanId) {
        this.linkmanId = linkmanId;
    }
    
    @Column(name="REMARK", length=500)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="REMIND", precision=2, scale=0)
    public Long getRemind() {
        return this.remind;
    }
    
    public void setRemind(Long remind) {
        this.remind = remind;
    }
    
    @Column(name="REPEAT", precision=2, scale=0)
    public Long getRepeat() {
        return this.repeat;
    }
    
    public void setRepeat(Long repeat) {
        this.repeat = repeat;
    }
    
    @Column(name="LOGIN_USER_NO", length=50)
    public String getLoginUserNo() {
        return this.loginUserNo;
    }
    
    public void setLoginUserNo(String loginUserNo) {
        this.loginUserNo = loginUserNo;
    }
    
    @Column(name="EVENT_TYPE", precision=2, scale=0)
    public Long getEventType() {
        return this.eventType;
    }
    
    public void setEventType(Long eventType) {
        this.eventType = eventType;
    }
    
    @Column(name="PLACE", length=500)
    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleManage pojo = (ScheduleManage) o;

        if (scheduleName != null ? !scheduleName.equals(pojo.scheduleName) : pojo.scheduleName != null) return false;
        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (priority != null ? !priority.equals(pojo.priority) : pojo.priority != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (linkmanId != null ? !linkmanId.equals(pojo.linkmanId) : pojo.linkmanId != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (remind != null ? !remind.equals(pojo.remind) : pojo.remind != null) return false;
        if (repeat != null ? !repeat.equals(pojo.repeat) : pojo.repeat != null) return false;
        if (loginUserNo != null ? !loginUserNo.equals(pojo.loginUserNo) : pojo.loginUserNo != null) return false;
        if (eventType != null ? !eventType.equals(pojo.eventType) : pojo.eventType != null) return false;
        if (place != null ? !place.equals(pojo.place) : pojo.place != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (scheduleName != null ? scheduleName.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (linkmanId != null ? linkmanId.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (remind != null ? remind.hashCode() : 0);
        result = 31 * result + (repeat != null ? repeat.hashCode() : 0);
        result = 31 * result + (loginUserNo != null ? loginUserNo.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("scheduleName").append("='").append(getScheduleName()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("priority").append("='").append(getPriority()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("linkmanId").append("='").append(getLinkmanId()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("remind").append("='").append(getRemind()).append("', ");
        sb.append("repeat").append("='").append(getRepeat()).append("', ");
        sb.append("loginUserNo").append("='").append(getLoginUserNo()).append("', ");
        sb.append("eventType").append("='").append(getEventType()).append("', ");
        sb.append("place").append("='").append(getPlace()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
