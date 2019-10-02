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
@Table(name="JMI_ECMALL_NOTE")

@XmlRootElement
public class JmiEcmallNote extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private String url;
    private String info;
    private Date createTime;
    private String createNo;
    private String code;
    private String remark;
    private String noteTyoe;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="URL", length=1000)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="INFO", length=1000)
    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_NO", length=20)
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    
    @Column(name="CODE", precision=22, scale=0)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="REMARK", length=4000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="NOTE_TYOE", length=1)
    public String getNoteTyoe() {
        return this.noteTyoe;
    }
    
    public void setNoteTyoe(String noteTyoe) {
        this.noteTyoe = noteTyoe;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiEcmallNote pojo = (JmiEcmallNote) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (url != null ? !url.equals(pojo.url) : pojo.url != null) return false;
        if (info != null ? !info.equals(pojo.info) : pojo.info != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;
        if (code != null ? !code.equals(pojo.code) : pojo.code != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (noteTyoe != null ? !noteTyoe.equals(pojo.noteTyoe) : pojo.noteTyoe != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (noteTyoe != null ? noteTyoe.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("url").append("='").append(getUrl()).append("', ");
        sb.append("info").append("='").append(getInfo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("', ");
        sb.append("code").append("='").append(getCode()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("noteTyoe").append("='").append(getNoteTyoe()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
