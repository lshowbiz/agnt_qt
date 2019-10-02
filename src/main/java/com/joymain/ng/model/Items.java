package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.CascadeType;
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
@Table(name="ITEMS")

@XmlRootElement
public class Items extends BaseObject implements Serializable {
    private Long itemsId;
    //private String statusId;
    //private Date acceptTime;//acceptTime  acceptTime
	//modify fu 2015-09-07  
    //虽然接口文档中该字段类型是date。但为解决json串转对象（InterfaceJsonUtil类的方法returnnoJsonToModel）时，将时间字段acceptTime转换成了系统当天时间，而不是json串原有的时间，特意将date类型的字段acceptTime修改成string类型
    private String acceptTime;
    private String acceptAddress;//acceptAddress 
    private String remark;
    
    private MailStatus mailStatus;

    //modify by fu 2016-03-02 添加pd_logistics_base_num表的主键
    private String numId;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
    @Column(name="ITEMS_ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId
    public Long getItemsId() {
        return this.itemsId;
    }
    
    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }
    
   /* @Column(name="STATUS_ID", length=20)
    public String getStatusId() {
        return this.statusId;
    }
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }*/
    
  
    @Column(name="ACCEPTTIME", length=200)
    public String getAcceptTime() {
        return this.acceptTime;
    }
    
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
    
    @Column(name="ACCEPTADDRESS", length=200)
    public String getAcceptAddress() {
        return this.acceptAddress;
    }
    
    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }
    
    @Column(name="EVENT", length=500)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "STATUS_ID")
    @JsonIgnore
    public MailStatus getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(MailStatus mailStatus) {
		this.mailStatus = mailStatus;
	}
	
	@Column(name="NUM_ID", length=50)
	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Items pojo = (Items) o;

       // if (statusId != null ? !statusId.equals(pojo.statusId) : pojo.statusId != null) return false;
        if (acceptTime != null ? !acceptTime.equals(pojo.acceptTime) : pojo.acceptTime != null) return false;
        if (acceptAddress != null ? !acceptAddress.equals(pojo.acceptAddress) : pojo.acceptAddress != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (numId != null ? !numId.equals(pojo.remark) : pojo.numId != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
       // result = (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (acceptTime != null ? acceptTime.hashCode() : 0);
        result = 31 * result + (acceptAddress != null ? acceptAddress.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (numId != null ? numId.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("itemsId").append("='").append(getItemsId()).append("', ");
       // sb.append("statusId").append("='").append(getStatusId()).append("', ");
        sb.append("acceptTime").append("='").append(getAcceptTime()).append("', ");
        sb.append("acceptAddress").append("='").append(getAcceptAddress()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("'");
        sb.append("numId").append("='").append(getNumId()).append("'");

        sb.append("]");
      
        return sb.toString();
    }

}
