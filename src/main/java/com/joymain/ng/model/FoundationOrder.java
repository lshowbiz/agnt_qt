package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="FOUNDATION_ORDER")

@XmlRootElement
public class FoundationOrder extends BaseObject implements Serializable {
    private Long orderId;
    private String userCode;
    //private String fiId;
    private String payType;
    private Integer fiNum;
    private Date createTime;
    private String status;
    private String field1;
    private String field2;//捐赠附言
    private BigDecimal amount;
    private Date endTime;
    private Date startTime;
    
    private FoundationItem foundationItem;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="ORDER_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=10)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
//    @Column(name="FI_ID", nullable=false, length=12)
//    public String getFiId() {
//        return this.fiId;
//    }
//    
//    public void setFiId(String fiId) {
//        this.fiId = fiId;
//    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FI_ID")
    public FoundationItem getFoundationItem() {
		return foundationItem;
	}

	public void setFoundationItem(FoundationItem foundationItem) {
		this.foundationItem = foundationItem;
	}
    
    @Column(name="PAY_TYPE", length=1)
    public String getPayType() {
        return this.payType;
    }

	public void setPayType(String payType) {
        this.payType = payType;
    }
    
    @Column(name="FI_NUM", precision=12, scale=0)
    public Integer getFiNum() {
        return this.fiNum;
    }
    
    public void setFiNum(Integer fiNum) {
        this.fiNum = fiNum;
    }
    
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="STATUS", length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="FIELD1", length=50)
    public String getField1() {
        return this.field1;
    }
    
    public void setField1(String field1) {
        this.field1 = field1;
    }
    
    @Column(name="FIELD2", length=300)
    public String getField2() {
        return this.field2;
    }
    
    public void setField2(String field2) {
        this.field2 = field2;
    }
    
    @Column(name="AMOUNT", precision=18, scale=4)
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Column(name="END_TIME", length=7)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Column(name="START_TIME", length=7)
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoundationOrder pojo = (FoundationOrder) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        //if (fiId != null ? !fiId.equals(pojo.fiId) : pojo.fiId != null) return false;
        if (payType != null ? !payType.equals(pojo.payType) : pojo.payType != null) return false;
        if (fiNum != null ? !fiNum.equals(pojo.fiNum) : pojo.fiNum != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (field1 != null ? !field1.equals(pojo.field1) : pojo.field1 != null) return false;
        if (field2 != null ? !field2.equals(pojo.field2) : pojo.field2 != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        //result = 31 * result + (fiId != null ? fiId.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (fiNum != null ? fiNum.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (field1 != null ? field1.hashCode() : 0);
        result = 31 * result + (field2 != null ? field2.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("orderId").append("='").append(getOrderId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        //sb.append("fiId").append("='").append(getFiId()).append("', ");
        sb.append("payType").append("='").append(getPayType()).append("', ");
        sb.append("fiNum").append("='").append(getFiNum()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("field1").append("='").append(getField1()).append("', ");
        sb.append("field2").append("='").append(getField2()).append("', ");
        sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
