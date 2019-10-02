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
@Table(name="JMI_SMS_NOTE")

@XmlRootElement
public class JmiSmsNote extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private String verifyCode;
    private Date createTime;
    private String phone;


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
    
    @Column(name="VERIFY_CODE", length=20)
    public String getVerifyCode() {
        return this.verifyCode;
    }
    
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
    
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="PHONE", length=100)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiSmsNote pojo = (JmiSmsNote) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (verifyCode != null ? !verifyCode.equals(pojo.verifyCode) : pojo.verifyCode != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (verifyCode != null ? verifyCode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("verifyCode").append("='").append(getVerifyCode()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
