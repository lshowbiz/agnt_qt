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
@Table(name="INW_INTEGRATION")

@XmlRootElement
public class InwIntegration extends BaseObject implements Serializable {
    private Long id;
    private String suggestionUserCode;
    private String integrationAdd;
    private Date integrationAddTime;
    private String suggestionid;
    private String integrationMinus;
    private String integrationActivity;
    private Date integrationMinusTime;
    private String operatorUserCode;
    private String other;

    @Id      
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="SUGGESTION_USER_CODE", length=20)
    public String getSuggestionUserCode() {
        return this.suggestionUserCode;
    }
    
    public void setSuggestionUserCode(String suggestionUserCode) {
        this.suggestionUserCode = suggestionUserCode;
    }
    
    @Column(name="INTEGRATION_ADD", length=5)
    public String getIntegrationAdd() {
        return this.integrationAdd;
    }
    
    public void setIntegrationAdd(String integrationAdd) {
        this.integrationAdd = integrationAdd;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="INTEGRATION_ADD_TIME", length=7)
    public Date getIntegrationAddTime() {
        return this.integrationAddTime;
    }
    
    public void setIntegrationAddTime(Date integrationAddTime) {
        this.integrationAddTime = integrationAddTime;
    }
    
    @Column(name="SUGGESTIONID", length=13)
    public String getSuggestionid() {
        return this.suggestionid;
    }
    
    public void setSuggestionid(String suggestionid) {
        this.suggestionid = suggestionid;
    }
    
    @Column(name="INTEGRATION_MINUS", length=5)
    public String getIntegrationMinus() {
        return this.integrationMinus;
    }
    
    public void setIntegrationMinus(String integrationMinus) {
        this.integrationMinus = integrationMinus;
    }
    
    @Column(name="INTEGRATION_ACTIVITY", length=13)
    public String getIntegrationActivity() {
        return this.integrationActivity;
    }
    
    public void setIntegrationActivity(String integrationActivity) {
        this.integrationActivity = integrationActivity;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="INTEGRATION_MINUS_TIME", length=7)
    public Date getIntegrationMinusTime() {
        return this.integrationMinusTime;
    }
    
    public void setIntegrationMinusTime(Date integrationMinusTime) {
        this.integrationMinusTime = integrationMinusTime;
    }
    
    @Column(name="OPERATOR_USER_CODE", length=20)
    public String getOperatorUserCode() {
        return this.operatorUserCode;
    }
    
    public void setOperatorUserCode(String operatorUserCode) {
        this.operatorUserCode = operatorUserCode;
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

        InwIntegration pojo = (InwIntegration) o;

        if (suggestionUserCode != null ? !suggestionUserCode.equals(pojo.suggestionUserCode) : pojo.suggestionUserCode != null) return false;
        if (integrationAdd != null ? !integrationAdd.equals(pojo.integrationAdd) : pojo.integrationAdd != null) return false;
        if (integrationAddTime != null ? !integrationAddTime.equals(pojo.integrationAddTime) : pojo.integrationAddTime != null) return false;
        if (suggestionid != null ? !suggestionid.equals(pojo.suggestionid) : pojo.suggestionid != null) return false;
        if (integrationMinus != null ? !integrationMinus.equals(pojo.integrationMinus) : pojo.integrationMinus != null) return false;
        if (integrationActivity != null ? !integrationActivity.equals(pojo.integrationActivity) : pojo.integrationActivity != null) return false;
        if (integrationMinusTime != null ? !integrationMinusTime.equals(pojo.integrationMinusTime) : pojo.integrationMinusTime != null) return false;
        if (operatorUserCode != null ? !operatorUserCode.equals(pojo.operatorUserCode) : pojo.operatorUserCode != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (suggestionUserCode != null ? suggestionUserCode.hashCode() : 0);
        result = 31 * result + (integrationAdd != null ? integrationAdd.hashCode() : 0);
        result = 31 * result + (integrationAddTime != null ? integrationAddTime.hashCode() : 0);
        result = 31 * result + (suggestionid != null ? suggestionid.hashCode() : 0);
        result = 31 * result + (integrationMinus != null ? integrationMinus.hashCode() : 0);
        result = 31 * result + (integrationActivity != null ? integrationActivity.hashCode() : 0);
        result = 31 * result + (integrationMinusTime != null ? integrationMinusTime.hashCode() : 0);
        result = 31 * result + (operatorUserCode != null ? operatorUserCode.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("suggestionUserCode").append("='").append(getSuggestionUserCode()).append("', ");
        sb.append("integrationAdd").append("='").append(getIntegrationAdd()).append("', ");
        sb.append("integrationAddTime").append("='").append(getIntegrationAddTime()).append("', ");
        sb.append("suggestionid").append("='").append(getSuggestionid()).append("', ");
        sb.append("integrationMinus").append("='").append(getIntegrationMinus()).append("', ");
        sb.append("integrationActivity").append("='").append(getIntegrationActivity()).append("', ");
        sb.append("integrationMinusTime").append("='").append(getIntegrationMinusTime()).append("', ");
        sb.append("operatorUserCode").append("='").append(getOperatorUserCode()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
