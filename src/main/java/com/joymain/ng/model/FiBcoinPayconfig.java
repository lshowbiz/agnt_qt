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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="FI_BCOIN_PAYCONFIG")

@XmlRootElement
public class FiBcoinPayconfig extends BaseObject implements Serializable {
    private Long configId;
    private String startTime;
    private String endTime;
    private String title;
    private String limit;//规则： 0:不限, 1：限制部分产品不参加，2：开放部分产品参加
    private String createCode;
    private String createName;
    private Date createTime;
    private Boolean proportion;

    @Id      @Column(name="CONFIG_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getConfigId() {
        return this.configId;
    }
    
    public void setConfigId(Long configId) {
        this.configId = configId;
    }
    
    @Column(name="START_TIME", nullable=false, length=20)
    public String getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    @Column(name="END_TIME", nullable=false, length=20)
    public String getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    @Column(name="TITLE", nullable=false, length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="LIMIT", nullable=false, length=1)
    public String getLimit() {
        return this.limit;
    }
    
    public void setLimit(String limit) {
        this.limit = limit;
    }
    
    @Column(name="CREATE_CODE", length=20)
    public String getCreateCode() {
        return this.createCode;
    }
    
    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }
    
    @Column(name="CREATE_NAME", length=20)
    public String getCreateName() {
        return this.createName;
    }
    
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="PROPORTION", nullable=false, length=1)
    public Boolean getProportion() {
        return this.proportion;
    }
    
    public void setProportion(Boolean proportion) {
        this.proportion = proportion;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiBcoinPayconfig pojo = (FiBcoinPayconfig) o;

        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (title != null ? !title.equals(pojo.title) : pojo.title != null) return false;
        if (limit != null ? !limit.equals(pojo.limit) : pojo.limit != null) return false;
        if (createCode != null ? !createCode.equals(pojo.createCode) : pojo.createCode != null) return false;
        if (createName != null ? !createName.equals(pojo.createName) : pojo.createName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (proportion != null ? !proportion.equals(pojo.proportion) : pojo.proportion != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (limit != null ? limit.hashCode() : 0);
        result = 31 * result + (createCode != null ? createCode.hashCode() : 0);
        result = 31 * result + (createName != null ? createName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (proportion != null ? proportion.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("configId").append("='").append(getConfigId()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("title").append("='").append(getTitle()).append("', ");
        sb.append("limit").append("='").append(getLimit()).append("', ");
        sb.append("createCode").append("='").append(getCreateCode()).append("', ");
        sb.append("createName").append("='").append(getCreateName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("proportion").append("='").append(getProportion()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
