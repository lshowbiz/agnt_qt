package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

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
@Table(name="JAM_MSN_DETAIL")

@XmlRootElement
public class JamMsnDetail extends BaseObject implements Serializable {
    private Long mdId;
    private String userCode;
    private JamMsnType jamMsnType;

    private String status;

    @Id  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)  
    @Column(name="MD_ID", unique=true, nullable=false, precision=10, scale=0)      
    public Long getMdId() {
        return this.mdId;
    }
    
    public void setMdId(Long mdId) {
        this.mdId = mdId;
    }
    
    @Column(name="USER_CODE", length=20)
    
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    
    @Column(name="STATUS", length=1)
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JamMsnDetail pojo = (JamMsnDetail) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("mdId").append("='").append(getMdId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToOne
    @JoinColumn(name = "MT_ID", nullable = false,  updatable = false)
	public JamMsnType getJamMsnType() {
		return jamMsnType;
	}

	public void setJamMsnType(JamMsnType jamMsnType) {
		this.jamMsnType = jamMsnType;
	}

}
