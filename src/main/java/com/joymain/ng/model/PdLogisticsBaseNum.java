package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="PD_LOGISTICS_BASE_NUM")

@XmlRootElement
public class PdLogisticsBaseNum extends BaseObject implements Serializable {
    private Long numId;
    private String baseId;
    private String pdLogisticsBaseNum_no;//PdLogisticsBaseNum_no   
    private String name;//---------------------------------name
    private String status;//-------------------------------status
    private Date mailTime;
    private String otherOne;
    private String otherTwo;
    
    private PdLogisticsBase pdLogisticsBase;
    
    private List<PdLogisticsBaseDetail> pdLogisticsBaseDetail_items = new ArrayList();//PdLogisticsBaseDetail_items

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
    @Column(name="NUM_ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId 
    public Long getNumId() {
        return this.numId;
    }
    
    public void setNumId(Long numId) {
        this.numId = numId;
    }
    
    @Column(name="BASE_ID", length=20)
    public String getBaseId() {
        return this.baseId;
    }
    
    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }
    
    @Column(name="PDLOGISTICSBASENUM_NO", length=100)
    public String getPdLogisticsBaseNum_no() {
        return this.pdLogisticsBaseNum_no;
    }
    
    public void setPdLogisticsBaseNum_no(String pdLogisticsBaseNum_no) {
        this.pdLogisticsBaseNum_no = pdLogisticsBaseNum_no;
    }
    
    @Column(name="NAME", length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="STATUS", length=100)
    public String getStatus() {
        return this.status;
    }
    
    public void setstatus(String status) {
        this.status = status;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="MAIL_TIME", length=7)
    public Date getMailTime() {
        return this.mailTime;
    }
    
    public void setMailTime(Date mailTime) {
        this.mailTime = mailTime;
    }
    
    @Column(name="OTHER_ONE", length=200)
    public String getOtherOne() {
        return this.otherOne;
    }
    
    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }
    
    @Column(name="OTHER_TWO", length=200)
    public String getOtherTwo() {
        return this.otherTwo;
    }
    
    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }
    

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "base_id")
    @JsonIgnore
    public PdLogisticsBase getPdLogisticsBase() {
		return pdLogisticsBase;
	}

	public void setPdLogisticsBase(PdLogisticsBase pdLogisticsBase) {
		this.pdLogisticsBase = pdLogisticsBase;
	}
	
	@OneToMany(mappedBy = "pdLogisticsBaseNum", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<PdLogisticsBaseDetail> getPdLogisticsBaseDetail() {
		return pdLogisticsBaseDetail_items;
	}

	public void setPdLogisticsBaseDetail(
			List<PdLogisticsBaseDetail> pdLogisticsBaseDetail_items) {
		this.pdLogisticsBaseDetail_items = pdLogisticsBaseDetail_items;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdLogisticsBaseNum pojo = (PdLogisticsBaseNum) o;

        if (baseId != null ? !baseId.equals(pojo.baseId) : pojo.baseId != null) return false;
        if (pdLogisticsBaseNum_no != null ? !pdLogisticsBaseNum_no.equals(pojo.pdLogisticsBaseNum_no) : pojo.pdLogisticsBaseNum_no != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (mailTime != null ? !mailTime.equals(pojo.mailTime) : pojo.mailTime != null) return false;
        if (otherOne != null ? !otherOne.equals(pojo.otherOne) : pojo.otherOne != null) return false;
        if (otherTwo != null ? !otherTwo.equals(pojo.otherTwo) : pojo.otherTwo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (baseId != null ? baseId.hashCode() : 0);
        result = 31 * result + (pdLogisticsBaseNum_no != null ? pdLogisticsBaseNum_no.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (mailTime != null ? mailTime.hashCode() : 0);
        result = 31 * result + (otherOne != null ? otherOne.hashCode() : 0);
        result = 31 * result + (otherTwo != null ? otherTwo.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("numId").append("='").append(getNumId()).append("', ");
        sb.append("baseId").append("='").append(getBaseId()).append("', ");
        sb.append("PdLogisticsBaseNum_no").append("='").append(getPdLogisticsBaseNum_no()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("mailTime").append("='").append(getMailTime()).append("', ");
        sb.append("otherOne").append("='").append(getOtherOne()).append("', ");
        sb.append("otherTwo").append("='").append(getOtherTwo()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
