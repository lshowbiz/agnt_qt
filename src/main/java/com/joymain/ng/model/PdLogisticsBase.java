package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

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
@Table(name="PD_LOGISTICS_BASE")

@XmlRootElement
public class PdLogisticsBase extends BaseObject implements Serializable {
    private Long baseId;//base_id
    private String member_order_no;//MEMBER_ORDER_NO member_order_no
    private String si_no;//SI_NO  si_no
    private String status;//-------------------------------status
    private String wms_do;//wms_do
    private Date status_time;//status_time
    private String status_code;//status_code  status_code
    private String status_name;//status_name
    private String operator;//-----------------------------operator
    private String otherOne;
    private String otherTwo;
    private String otherThree;
    private String otherFour;
    private String otherFive;
    
    private List<PdLogisticsBaseNum> mail_list = new ArrayList();//mail_list
       
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
    @Column(name="BASE_ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId  
    public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}
    
    @Column(name="MEMBER_ORDER_NO", length=20)
    public String getMember_order_no() {
        return this.member_order_no;
    }
    
	public void setMember_order_no(String member_order_no) {
        this.member_order_no = member_order_no;
    }
    
    @Column(name="SI_NO", length=20)
    public String getSi_no() {
        return this.si_no;
    }
    
    public void setSi_no(String si_no) {
        this.si_no = si_no;
    }
    
    @Column(name="STATUS", length=10)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="WMS_DO", length=30)
    public String getWms_do() {
        return this.wms_do;
    }
    
    public void setWms_do(String wms_do) {
        this.wms_do = wms_do;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="STATUS_TIME", length=7)
    public Date getStatus_time() {
        return this.status_time;
    }
    
    public void setStatus_time(Date status_time) {
        this.status_time = status_time;
    }
    
    @Column(name="STATUS_CODE", length=30)
    public String getStatus_code() {
        return this.status_code;
    }
    
    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }
    
    @Column(name="STATUS_NAME", length=100)
    public String getStatus_name() {
        return this.status_name;
    }
    
    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
    
    @Column(name="OPERATOR", length=200)
    public String getOperator() {
        return this.operator;
    }
    
    public void setoperator(String operator) {
        this.operator = operator;
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
    
    @Column(name="OTHER_THREE", length=200)
    public String getOtherThree() {
        return this.otherThree;
    }
    
    public void setOtherThree(String otherThree) {
        this.otherThree = otherThree;
    }
    
    @Column(name="OTHER_FOUR", length=200)
    public String getOtherFour() {
        return this.otherFour;
    }
    
    public void setOtherFour(String otherFour) {
        this.otherFour = otherFour;
    }
    
    @Column(name="OTHER_FIVE", length=200)
    public String getOtherFive() {
        return this.otherFive;
    }
    
    public void setOtherFive(String otherFive) {
        this.otherFive = otherFive;
    }

	@OneToMany(mappedBy = "pdLogisticsBase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<PdLogisticsBaseNum> getMail_list() {
		return mail_list;
	}

	public void setMail_list(List<PdLogisticsBaseNum> mail_list) {
		this.mail_list = mail_list;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdLogisticsBase pojo = (PdLogisticsBase) o;

        if (member_order_no != null ? !member_order_no.equals(pojo.member_order_no) : pojo.member_order_no != null) return false;
        if (si_no != null ? !si_no.equals(pojo.si_no) : pojo.si_no != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (wms_do != null ? !wms_do.equals(pojo.wms_do) : pojo.wms_do != null) return false;
        if (status_time != null ? !status_time.equals(pojo.status_time) : pojo.status_time != null) return false;
        if (status_code != null ? !status_code.equals(pojo.status_code) : pojo.status_code != null) return false;
        if (status_name != null ? !status_name.equals(pojo.status_name) : pojo.status_name != null) return false;
        if (operator != null ? !operator.equals(pojo.operator) : pojo.operator != null) return false;
        if (otherOne != null ? !otherOne.equals(pojo.otherOne) : pojo.otherOne != null) return false;
        if (otherTwo != null ? !otherTwo.equals(pojo.otherTwo) : pojo.otherTwo != null) return false;
        if (otherThree != null ? !otherThree.equals(pojo.otherThree) : pojo.otherThree != null) return false;
        if (otherFour != null ? !otherFour.equals(pojo.otherFour) : pojo.otherFour != null) return false;
        if (otherFive != null ? !otherFive.equals(pojo.otherFive) : pojo.otherFive != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (member_order_no != null ? member_order_no.hashCode() : 0);
        result = 31 * result + (si_no != null ? si_no.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (wms_do != null ? wms_do.hashCode() : 0);
        result = 31 * result + (status_time != null ? status_time.hashCode() : 0);
        result = 31 * result + (status_code != null ? status_code.hashCode() : 0);
        result = 31 * result + (status_name != null ? status_name.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (otherOne != null ? otherOne.hashCode() : 0);
        result = 31 * result + (otherTwo != null ? otherTwo.hashCode() : 0);
        result = 31 * result + (otherThree != null ? otherThree.hashCode() : 0);
        result = 31 * result + (otherFour != null ? otherFour.hashCode() : 0);
        result = 31 * result + (otherFive != null ? otherFive.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("baseId").append("='").append(getBaseId()).append("', ");
        sb.append("member_order_no").append("='").append(getMember_order_no()).append("', ");
        sb.append("si_no").append("='").append(getSi_no()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("wms_do").append("='").append(getWms_do()).append("', ");
        sb.append("status_time").append("='").append(getStatus_time()).append("', ");
        sb.append("status_code").append("='").append(getStatus_code()).append("', ");
        sb.append("status_name").append("='").append(getStatus_name()).append("', ");
        sb.append("operator").append("='").append(getOperator()).append("', ");
        sb.append("otherOne").append("='").append(getOtherOne()).append("', ");
        sb.append("otherTwo").append("='").append(getOtherTwo()).append("', ");
        sb.append("otherThree").append("='").append(getOtherThree()).append("', ");
        sb.append("otherFour").append("='").append(getOtherFour()).append("', ");
        sb.append("otherFive").append("='").append(getOtherFive()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
