package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JMI_MEMBER_UPGRADE_NOTE")

@XmlRootElement
public class JmiMemberUpgradeNote extends BaseObject implements Serializable {
    private Long munId;
    private JmiMember jmiMember;
    private String newCard;
    private String oldCard;
    private String companyCode;
    private String memberOrderNo;
    private Date updateDate;
    private String updateType;
    private String remark;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="MUN_ID", unique=true, nullable=false, precision=10, scale=0)    
    public Long getMunId() {
        return this.munId;
    }
    
    public void setMunId(Long munId) {
        this.munId = munId;
    }

    
    @Column(name="NEW_CARD", nullable=false, length=1)
    
    public String getNewCard() {
        return this.newCard;
    }
    
    public void setNewCard(String newCard) {
        this.newCard = newCard;
    }
    
    @Column(name="OLD_CARD", nullable=false, length=1)
    
    public String getOldCard() {
        return this.oldCard;
    }
    
    public void setOldCard(String oldCard) {
        this.oldCard = oldCard;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="MEMBER_ORDER_NO", length=20)
    
    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }
    
    @Column(name="UPDATE_DATE", length=7)
    
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    @Column(name="UPDATE_TYPE", length=1)
    
    public String getUpdateType() {
        return this.updateType;
    }
    
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
    
    @Column(name="REMARK", length=200)
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiMemberUpgradeNote pojo = (JmiMemberUpgradeNote) o;

        if (newCard != null ? !newCard.equals(pojo.newCard) : pojo.newCard != null) return false;
        if (oldCard != null ? !oldCard.equals(pojo.oldCard) : pojo.oldCard != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (memberOrderNo != null ? !memberOrderNo.equals(pojo.memberOrderNo) : pojo.memberOrderNo != null) return false;
        if (updateDate != null ? !updateDate.equals(pojo.updateDate) : pojo.updateDate != null) return false;
        if (updateType != null ? !updateType.equals(pojo.updateType) : pojo.updateType != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (newCard != null ? newCard.hashCode() : 0);
        result = 31 * result + (oldCard != null ? oldCard.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (memberOrderNo != null ? memberOrderNo.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (updateType != null ? updateType.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("munId").append("='").append(getMunId()).append("', ");
        sb.append("newCard").append("='").append(getNewCard()).append("', ");
        sb.append("oldCard").append("='").append(getOldCard()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("', ");
        sb.append("updateDate").append("='").append(getUpdateDate()).append("', ");
        sb.append("updateType").append("='").append(getUpdateType()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToOne
    @JoinColumn(name = "USER_CODE", nullable = false,  updatable = false)
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

}
