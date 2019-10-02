package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

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
@Table(name="JMI_BLACKLIST")

@XmlRootElement
public class JmiBlacklist extends BaseObject implements Serializable {
    private Long blId;
	private String papertype;
    private String papernumber;
    private String blackType;
    private String companyCode;
    private String createNo;
    private Date createTime;
    private String remark;
    private String status;
    @Id  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="BL_ID", unique=true, nullable=false, precision=10, scale=0) 
    public Long getBlId() {
        return this.blId;
    }
    
    public void setBlId(Long blId) {
        this.blId = blId;
    }
    
    @Column(name="PAPERTYPE", length=20)
    
    public String getPapertype() {
        return this.papertype;
    }
    
    public void setPapertype(String papertype) {
        this.papertype = papertype;
    }
    
    @Column(name="PAPERNUMBER", length=100)
    
    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }
    
    @Column(name="BLACK_TYPE", length=20)
    
    public String getBlackType() {
        return this.blackType;
    }
    
    public void setBlackType(String blackType) {
        this.blackType = blackType;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="CREATE_NO", length=20)
    
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    @Column(name="CREATE_TIME", length=7)
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="REMARK", length=500)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name="STATUS", length=1)
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiBlacklist pojo = (JmiBlacklist) o;

        if (papertype != null ? !papertype.equals(pojo.papertype) : pojo.papertype != null) return false;
        if (papernumber != null ? !papernumber.equals(pojo.papernumber) : pojo.papernumber != null) return false;
        if (blackType != null ? !blackType.equals(pojo.blackType) : pojo.blackType != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (papertype != null ? papertype.hashCode() : 0);
        result = 31 * result + (papernumber != null ? papernumber.hashCode() : 0);
        result = 31 * result + (blackType != null ? blackType.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("blId").append("='").append(getBlId()).append("', ");
        sb.append("papertype").append("='").append(getPapertype()).append("', ");
        sb.append("papernumber").append("='").append(getPapernumber()).append("', ");
        sb.append("blackType").append("='").append(getBlackType()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
