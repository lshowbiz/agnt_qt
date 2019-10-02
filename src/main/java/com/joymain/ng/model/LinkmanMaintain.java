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
@Table(name="LINKMAN_MAINTAIN")

@XmlRootElement
public class LinkmanMaintain extends BaseObject implements Serializable {
    private Long id;
    private String maintenanceTopic;
    private String maintenanceMode;
    private String linkmanId;
    private Date maintenanceTime;
    private String maintenanceContent;
    private String maintenanceEffect;
    private String summary;
    private String userCode;
    //联系人(姓名）
    private String other;
    //新添加的字段
    private String lcId;

    @Id     
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0)
    @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="MAINTENANCE_TOPIC", length=100)
    public String getMaintenanceTopic() {
        return this.maintenanceTopic;
    }
    
    public void setMaintenanceTopic(String maintenanceTopic) {
        this.maintenanceTopic = maintenanceTopic;
    }
    
    @Column(name="MAINTENANCE_MODE", length=20)
    public String getMaintenanceMode() {
        return this.maintenanceMode;
    }
    
    public void setMaintenanceMode(String maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }
    
    @Column(name="LINKMAN_ID", length=32)
    public String getLinkmanId() {
        return this.linkmanId;
    }
    
    public void setLinkmanId(String linkmanId) {
        this.linkmanId = linkmanId;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="MAINTENANCE_TIME", length=7)
    public Date getMaintenanceTime() {
        return this.maintenanceTime;
    }
    
    public void setMaintenanceTime(Date maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }
    
    @Column(name="MAINTENANCE_CONTENT", length=1000)
    public String getMaintenanceContent() {
        return this.maintenanceContent;
    }
    
    public void setMaintenanceContent(String maintenanceContent) {
        this.maintenanceContent = maintenanceContent;
    }
    
    @Column(name="MAINTENANCE_EFFECT", length=1000)
    public String getMaintenanceEffect() {
        return this.maintenanceEffect;
    }
    
    public void setMaintenanceEffect(String maintenanceEffect) {
        this.maintenanceEffect = maintenanceEffect;
    }
    
    @Column(name="SUMMARY", length=1000)
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="OTHER", length=100)
    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }

    
    @Column(name="LC_ID", length=32)
    public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkmanMaintain pojo = (LinkmanMaintain) o;

        if (maintenanceTopic != null ? !maintenanceTopic.equals(pojo.maintenanceTopic) : pojo.maintenanceTopic != null) return false;
        if (maintenanceMode != null ? !maintenanceMode.equals(pojo.maintenanceMode) : pojo.maintenanceMode != null) return false;
        if (linkmanId != null ? !linkmanId.equals(pojo.linkmanId) : pojo.linkmanId != null) return false;
        if (maintenanceTime != null ? !maintenanceTime.equals(pojo.maintenanceTime) : pojo.maintenanceTime != null) return false;
        if (maintenanceContent != null ? !maintenanceContent.equals(pojo.maintenanceContent) : pojo.maintenanceContent != null) return false;
        if (maintenanceEffect != null ? !maintenanceEffect.equals(pojo.maintenanceEffect) : pojo.maintenanceEffect != null) return false;
        if (summary != null ? !summary.equals(pojo.summary) : pojo.summary != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;
        if (lcId != null ? !lcId.equals(pojo.lcId) : pojo.lcId != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (maintenanceTopic != null ? maintenanceTopic.hashCode() : 0);
        result = 31 * result + (maintenanceMode != null ? maintenanceMode.hashCode() : 0);
        result = 31 * result + (linkmanId != null ? linkmanId.hashCode() : 0);
        result = 31 * result + (maintenanceTime != null ? maintenanceTime.hashCode() : 0);
        result = 31 * result + (maintenanceContent != null ? maintenanceContent.hashCode() : 0);
        result = 31 * result + (maintenanceEffect != null ? maintenanceEffect.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        result = 31 * result + (lcId != null ? lcId.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("maintenanceTopic").append("='").append(getMaintenanceTopic()).append("', ");
        sb.append("maintenanceMode").append("='").append(getMaintenanceMode()).append("', ");
        sb.append("linkmanId").append("='").append(getLinkmanId()).append("', ");
        sb.append("maintenanceTime").append("='").append(getMaintenanceTime()).append("', ");
        sb.append("maintenanceContent").append("='").append(getMaintenanceContent()).append("', ");
        sb.append("maintenanceEffect").append("='").append(getMaintenanceEffect()).append("', ");
        sb.append("summary").append("='").append(getSummary()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("lcId").append("='").append(getLcId()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
