package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.sql.Clob;
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
import java.math.BigDecimal;
/**
 * 会员转账
 * @author hywen
 *
 */
@Entity
@Table(name="FI_TRANSFER_ACCOUNT")

@XmlRootElement
public class FiTransferAccount extends BaseObject implements Serializable {
    private Long taId;
    private String transferUserCode;
    private String destinationUserCode;
    private BigDecimal money;
    private String notes;
    private Integer status;
    private String createrCode;
    private String createrName;
    private Date createTime;
    private String checkerCode;
    private String checkerName;
    private Date checkeTime;
    private Date dealDate;
    
    private String paymentType;//转账平台类型 1：手机端  空：移动端

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="TA_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getTaId() {
        return this.taId;
    }
    
    public void setTaId(Long taId) {
        this.taId = taId;
    }
    
    @Column(name="TRANSFER_USER_CODE", nullable=false, length=20)
    public String getTransferUserCode() {
        return this.transferUserCode;
    }
    
    public void setTransferUserCode(String transferUserCode) {
        this.transferUserCode = transferUserCode;
    }
    
    @Column(name="DESTINATION_USER_CODE", nullable=false, length=20)
    public String getDestinationUserCode() {
        return this.destinationUserCode;
    }
    
    public void setDestinationUserCode(String destinationUserCode) {
        this.destinationUserCode = destinationUserCode;
    }
    
    @Column(name="MONEY", precision=18, scale=4)
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    @Column(name="NOTES")
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @Column(name="STATUS", precision=2, scale=0)
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="CREATER_CODE", length=20)
    public String getCreaterCode() {
        return this.createrCode;
    }
    
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }
    
    @Column(name="CREATER_NAME", length=300)
    public String getCreaterName() {
        return this.createrName;
    }
    
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
    
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CHECKER_CODE", length=20)
    public String getCheckerCode() {
        return this.checkerCode;
    }
    
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }
    
    @Column(name="CHECKER_NAME", length=300)
    public String getCheckerName() {
        return this.checkerName;
    }
    
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    
    @Column(name="CHECKE_TIME", length=7)
    public Date getCheckeTime() {
        return this.checkeTime;
    }
    
    public void setCheckeTime(Date checkeTime) {
        this.checkeTime = checkeTime;
    }
    
    @Column(name="DEAL_DATE", length=7)
    public Date getDealDate() {
        return this.dealDate;
    }
    
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiTransferAccount pojo = (FiTransferAccount) o;

        if (transferUserCode != null ? !transferUserCode.equals(pojo.transferUserCode) : pojo.transferUserCode != null) return false;
        if (destinationUserCode != null ? !destinationUserCode.equals(pojo.destinationUserCode) : pojo.destinationUserCode != null) return false;
        if (money != null ? !money.equals(pojo.money) : pojo.money != null) return false;
        if (notes != null ? !notes.equals(pojo.notes) : pojo.notes != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (createrCode != null ? !createrCode.equals(pojo.createrCode) : pojo.createrCode != null) return false;
        if (createrName != null ? !createrName.equals(pojo.createrName) : pojo.createrName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (checkerCode != null ? !checkerCode.equals(pojo.checkerCode) : pojo.checkerCode != null) return false;
        if (checkerName != null ? !checkerName.equals(pojo.checkerName) : pojo.checkerName != null) return false;
        if (checkeTime != null ? !checkeTime.equals(pojo.checkeTime) : pojo.checkeTime != null) return false;
        if (dealDate != null ? !dealDate.equals(pojo.dealDate) : pojo.dealDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (transferUserCode != null ? transferUserCode.hashCode() : 0);
        result = 31 * result + (destinationUserCode != null ? destinationUserCode.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createrCode != null ? createrCode.hashCode() : 0);
        result = 31 * result + (createrName != null ? createrName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (checkerCode != null ? checkerCode.hashCode() : 0);
        result = 31 * result + (checkerName != null ? checkerName.hashCode() : 0);
        result = 31 * result + (checkeTime != null ? checkeTime.hashCode() : 0);
        result = 31 * result + (dealDate != null ? dealDate.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("taId").append("='").append(getTaId()).append("', ");
        sb.append("transferUserCode").append("='").append(getTransferUserCode()).append("', ");
        sb.append("destinationUserCode").append("='").append(getDestinationUserCode()).append("', ");
        sb.append("money").append("='").append(getMoney()).append("', ");
        sb.append("notes").append("='").append(getNotes()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("createrCode").append("='").append(getCreaterCode()).append("', ");
        sb.append("createrName").append("='").append(getCreaterName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("checkerCode").append("='").append(getCheckerCode()).append("', ");
        sb.append("checkerName").append("='").append(getCheckerName()).append("', ");
        sb.append("checkeTime").append("='").append(getCheckeTime()).append("', ");
        sb.append("dealDate").append("='").append(getDealDate()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @Column(name="PAYMENT_TYPE", length=3)
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
