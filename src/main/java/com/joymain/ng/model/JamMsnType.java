package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JAM_MSN_TYPE")

@XmlRootElement
public class JamMsnType extends BaseObject implements Serializable {
    private Long mtId;
    private String msnKey;
    private String msnName;
    private String msnDesc;
    private String msnStatus;

    @Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)  
	@Column(name="MT_ID", unique=true, nullable=false, precision=10, scale=0)      
    public Long getMtId() {
        return this.mtId;
    }
    
    public void setMtId(Long mtId) {
        this.mtId = mtId;
    }
    
    @Column(name="MSN_KEY", length=50)
    
    public String getMsnKey() {
        return this.msnKey;
    }
    
    public void setMsnKey(String msnKey) {
        this.msnKey = msnKey;
    }
    
    @Column(name="MSN_NAME", length=150)
    
    public String getMsnName() {
        return this.msnName;
    }
    
    public void setMsnName(String msnName) {
        this.msnName = msnName;
    }
    
    @Column(name="MSN_DESC", length=250)
    
    public String getMsnDesc() {
        return this.msnDesc;
    }
    
    public void setMsnDesc(String msnDesc) {
        this.msnDesc = msnDesc;
    }
    
    @Column(name="MSN_STATUS", length=1)
    
    public String getMsnStatus() {
        return this.msnStatus;
    }
    
    public void setMsnStatus(String msnStatus) {
        this.msnStatus = msnStatus;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JamMsnType pojo = (JamMsnType) o;

        if (msnKey != null ? !msnKey.equals(pojo.msnKey) : pojo.msnKey != null) return false;
        if (msnName != null ? !msnName.equals(pojo.msnName) : pojo.msnName != null) return false;
        if (msnDesc != null ? !msnDesc.equals(pojo.msnDesc) : pojo.msnDesc != null) return false;
        if (msnStatus != null ? !msnStatus.equals(pojo.msnStatus) : pojo.msnStatus != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (msnKey != null ? msnKey.hashCode() : 0);
        result = 31 * result + (msnName != null ? msnName.hashCode() : 0);
        result = 31 * result + (msnDesc != null ? msnDesc.hashCode() : 0);
        result = 31 * result + (msnStatus != null ? msnStatus.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("mtId").append("='").append(getMtId()).append("', ");
        sb.append("msnKey").append("='").append(getMsnKey()).append("', ");
        sb.append("msnName").append("='").append(getMsnName()).append("', ");
        sb.append("msnDesc").append("='").append(getMsnDesc()).append("', ");
        sb.append("msnStatus").append("='").append(getMsnStatus()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
