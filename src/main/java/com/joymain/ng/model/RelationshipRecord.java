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
@Table(name="RELATIONSHIP_RECORD")

@XmlRootElement
public class RelationshipRecord extends BaseObject implements Serializable {
    private Long id;
    private Long linkmanId;
    private String subject;
    private String content;
    private Date contactTime;
    private String contactType;
    private String loginUserNo;
    private String userCode;
    private String lcId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
    @SequenceGenerator(name = "seq_mi",sequenceName = "SEQ_MI",allocationSize = 1 )
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) 
    @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="LINKMAN_ID", nullable=false, precision=22, scale=0)
    public Long getLinkmanId() {
        return this.linkmanId;
    }
    
    public void setLinkmanId(Long linkmanId) {
        this.linkmanId = linkmanId;
    }
    
    @Column(name="SUBJECT", length=200)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name="CONTENT", length=500)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CONTACT_TIME", length=7)
    public Date getContactTime() {
        return this.contactTime;
    }
    
    public void setContactTime(Date contactTime) {
        this.contactTime = contactTime;
    }
    
    @Column(name="CONTACT_TYPE", precision=22, scale=0)
    public String getContactType() {
        return this.contactType;
    }
    
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    
    @Column(name="LOGIN_USER_NO", length=50)
    public String getLoginUserNo() {
        return this.loginUserNo;
    }
    
    public void setLoginUserNo(String loginUserNo) {
        this.loginUserNo = loginUserNo;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="LC_ID", length=32)
    public String getLcId() {
        return this.lcId;
    }
    
    public void setLcId(String lcId) {
        this.lcId = lcId;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationshipRecord pojo = (RelationshipRecord) o;

        if (linkmanId != null ? !linkmanId.equals(pojo.linkmanId) : pojo.linkmanId != null) return false;
        if (subject != null ? !subject.equals(pojo.subject) : pojo.subject != null) return false;
        if (content != null ? !content.equals(pojo.content) : pojo.content != null) return false;
        if (contactTime != null ? !contactTime.equals(pojo.contactTime) : pojo.contactTime != null) return false;
        if (contactType != null ? !contactType.equals(pojo.contactType) : pojo.contactType != null) return false;
        if (loginUserNo != null ? !loginUserNo.equals(pojo.loginUserNo) : pojo.loginUserNo != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (lcId != null ? !lcId.equals(pojo.lcId) : pojo.lcId != null) return false;
        
        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (linkmanId != null ? linkmanId.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (contactTime != null ? contactTime.hashCode() : 0);
        result = 31 * result + (contactType != null ? contactType.hashCode() : 0);
        result = 31 * result + (loginUserNo != null ? loginUserNo.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (lcId != null ? lcId.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("linkmanId").append("='").append(getLinkmanId()).append("', ");
        sb.append("subject").append("='").append(getSubject()).append("', ");
        sb.append("content").append("='").append(getContent()).append("', ");
        sb.append("contactTime").append("='").append(getContactTime()).append("', ");
        sb.append("contactType").append("='").append(getContactType()).append("', ");
        sb.append("loginUserNo").append("='").append(getLoginUserNo()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("lcId").append("='").append(getLcId()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
