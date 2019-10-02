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
@Table(name="INW_DEMAND")

@XmlRootElement
public class InwDemand extends BaseObject implements Serializable {
    private Long id;
    private String name;
    private String summary;
    private String detailExplanation;
    private String demandImage;
    private String showOrHide;
    private String verify;
    private String other;
    private String postUserCode;
    private Date postTime;
    private String reviewedUserCode;
    private Date reviewedTime;
    //新添加的字段
    private String demandsortId;

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
    
    @Column(name="NAME", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="SUMMARY", nullable=false, length=300)
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    @Column(name="DETAIL_EXPLANATION")
    public String getDetailExplanation() {
        return this.detailExplanation;
    }
    
    public void setDetailExplanation(String detailExplanation) {
        this.detailExplanation = detailExplanation;
    }
    
    @Column(name="DEMAND_IMAGE", length=300)
    public String getDemandImage() {
        return this.demandImage;
    }
    
    public void setDemandImage(String demandImage) {
        this.demandImage = demandImage;
    }
    
    @Column(name="SHOW_OR_HIDE", length=2)
    public String getShowOrHide() {
        return this.showOrHide;
    }
    
    public void setShowOrHide(String showOrHide) {
        this.showOrHide = showOrHide;
    }
    
    @Column(name="VERIFY", length=200)
    public String getVerify() {
        return this.verify;
    }
    
    public void setVerify(String verify) {
        this.verify = verify;
    }
    
    @Column(name="OTHER", length=200)
    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
    
    @Column(name="POST_USER_CODE", length=20)
	public String getPostUserCode() {
		return postUserCode;
	}

	public void setPostUserCode(String postUserCode) {
		this.postUserCode = postUserCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="POST_TIME", length=7)
	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	@Column(name="REVIEWED_USER_CODE", length=20)
	public String getReviewedUserCode() {
		return reviewedUserCode;
	}

	public void setReviewedUserCode(String reviewedUserCode) {
		this.reviewedUserCode = reviewedUserCode;
	}

	@Column(name="REVIEWED_TIME", length=7)
	public Date getReviewedTime() {
		return reviewedTime;
	}

	public void setReviewedTime(Date reviewedTime) {
		this.reviewedTime = reviewedTime;
	}
	
	@Column(name="DEMANDSORT_ID", length=32)
	public String getDemandsortId() {
		return demandsortId;
	}

	public void setDemandsortId(String demandsortId) {
		this.demandsortId = demandsortId;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InwDemand pojo = (InwDemand) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (summary != null ? !summary.equals(pojo.summary) : pojo.summary != null) return false;
        if (detailExplanation != null ? !detailExplanation.equals(pojo.detailExplanation) : pojo.detailExplanation != null) return false;
        if (demandImage != null ? !demandImage.equals(pojo.demandImage) : pojo.demandImage != null) return false;
        if (showOrHide != null ? !showOrHide.equals(pojo.showOrHide) : pojo.showOrHide != null) return false;
        if (verify != null ? !verify.equals(pojo.verify) : pojo.verify != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;
        if (postUserCode != null ? !postUserCode.equals(pojo.postUserCode) : pojo.postUserCode != null) return false;
        if (postTime != null ? !postTime.equals(pojo.postTime) : pojo.postTime != null) return false;
        if (reviewedUserCode != null ? !reviewedUserCode.equals(pojo.reviewedUserCode) : pojo.reviewedUserCode != null) return false;
        if (reviewedTime != null ? !reviewedTime.equals(pojo.reviewedTime) : pojo.reviewedTime != null) return false;
        if (demandsortId != null ? !demandsortId.equals(pojo.demandsortId) : pojo.demandsortId != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (detailExplanation != null ? detailExplanation.hashCode() : 0);
        result = 31 * result + (demandImage != null ? demandImage.hashCode() : 0);
        result = 31 * result + (showOrHide != null ? showOrHide.hashCode() : 0);
        result = 31 * result + (verify != null ? verify.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        result = 31 * result + (postUserCode != null ? postUserCode.hashCode() : 0);
        result = 31 * result + (postTime != null ? postTime.hashCode() : 0);
        result = 31 * result + (reviewedUserCode != null ? reviewedUserCode.hashCode() : 0);
        result = 31 * result + (reviewedTime != null ? reviewedTime.hashCode() : 0);
        result = 31 * result + (demandsortId != null ? demandsortId.hashCode() : 0);
        
        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("summary").append("='").append(getSummary()).append("', ");
        sb.append("detailExplanation").append("='").append(getDetailExplanation()).append("', ");
        sb.append("demandImage").append("='").append(getDemandImage()).append("', ");
        sb.append("showOrHide").append("='").append(getShowOrHide()).append("', ");
        sb.append("verify").append("='").append(getVerify()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("postUserCode").append("='").append(getPostUserCode()).append("'");
        sb.append("postTime").append("='").append(getPostTime()).append("'");
        sb.append("reviewedUserCode").append("='").append(getReviewedUserCode()).append("'");
        sb.append("reviewedTime").append("='").append(getReviewedTime()).append("'");
        sb.append("demandsortId").append("='").append(getDemandsortId()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
