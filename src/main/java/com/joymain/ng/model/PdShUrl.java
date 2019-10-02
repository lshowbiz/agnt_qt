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
@Table(name="PD_SH_URL")

@XmlRootElement
public class PdShUrl extends BaseObject implements Serializable {
    private Long id;
    private String valueCode;
    private String valueTitle;
    private String exCompanyCode;
    private Date createTime;
    private String createUserCode;
    private String shUrl;
    private String other;

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) 
    @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="VALUE_CODE", length=50)
    public String getValueCode() {
        return this.valueCode;
    }
    
    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }
    
    @Column(name="VALUE_TITLE", length=150)
    public String getValueTitle() {
        return this.valueTitle;
    }
    
    public void setValueTitle(String valueTitle) {
        this.valueTitle = valueTitle;
    }
    
    @Column(name="EX_COMPANY_CODE", length=200)
    public String getExCompanyCode() {
        return this.exCompanyCode;
    }
    
    public void setExCompanyCode(String exCompanyCode) {
        this.exCompanyCode = exCompanyCode;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_USER_CODE", length=20)
    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    
    @Column(name="SH_URL", length=200)
    public String getShUrl() {
        return this.shUrl;
    }
    
    public void setShUrl(String shUrl) {
        this.shUrl = shUrl;
    }
    
    @Column(name="OTHER", length=100)
    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdShUrl pojo = (PdShUrl) o;

        if (valueCode != null ? !valueCode.equals(pojo.valueCode) : pojo.valueCode != null) return false;
        if (valueTitle != null ? !valueTitle.equals(pojo.valueTitle) : pojo.valueTitle != null) return false;
        if (exCompanyCode != null ? !exCompanyCode.equals(pojo.exCompanyCode) : pojo.exCompanyCode != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null) return false;
        if (shUrl != null ? !shUrl.equals(pojo.shUrl) : pojo.shUrl != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (valueCode != null ? valueCode.hashCode() : 0);
        result = 31 * result + (valueTitle != null ? valueTitle.hashCode() : 0);
        result = 31 * result + (exCompanyCode != null ? exCompanyCode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);
        result = 31 * result + (shUrl != null ? shUrl.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("valueCode").append("='").append(getValueCode()).append("', ");
        sb.append("valueTitle").append("='").append(getValueTitle()).append("', ");
        sb.append("exCompanyCode").append("='").append(getExCompanyCode()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUserCode").append("='").append(getCreateUserCode()).append("', ");
        sb.append("shUrl").append("='").append(getShUrl()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
