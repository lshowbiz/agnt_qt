package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name="JBD_SUMMARY_LIST")

@XmlRootElement
public class JbdSummaryList extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private String cardType;
    private Integer inType;
    private Date createTime;
    private String orderType;
    private Date oldCheckDate;
    private Date newCheckDate;
    private BigDecimal pvAmt;
    private String oldLinkNo;
    private String newLinkNo;
    private String oldRecommendNo;
    private String newRecommendNo;
    private String newCompanyCode;
    private Date userCreateTime;
    private Integer wweek;
    private String orderNo; //订单mo_id 、退单编号

    @Column(name="W_WEEK", precision=22, scale=0)
    
    public Integer getWweek() {
		return wweek;
	}

	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jd_seq")
	@SequenceGenerator(name = "jd_seq", sequenceName = "JD_SEQ", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)  
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="USER_CODE", length=20)
    
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="CARD_TYPE", length=20)
    
    public String getCardType() {
        return this.cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    @Column(name="IN_TYPE", precision=22, scale=0)
    
    public Integer getInType() {
        return this.inType;
    }
    
    public void setInType(Integer inType) {
        this.inType = inType;
    }
    
    @Column(name="CREATE_TIME", length=7)
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="ORDER_TYPE", length=2)
    
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    @Column(name="OLD_CHECK_DATE", length=7)
    
    public Date getOldCheckDate() {
        return this.oldCheckDate;
    }
    
    public void setOldCheckDate(Date oldCheckDate) {
        this.oldCheckDate = oldCheckDate;
    }
    
    @Column(name="NEW_CHECK_DATE", length=7)
    
    public Date getNewCheckDate() {
        return this.newCheckDate;
    }
    
    public void setNewCheckDate(Date newCheckDate) {
        this.newCheckDate = newCheckDate;
    }
    
    @Column(name="PV_AMT", precision=22, scale=0)
    
    public BigDecimal getPvAmt() {
        return this.pvAmt;
    }
    
    public void setPvAmt(BigDecimal pvAmt) {
        this.pvAmt = pvAmt;
    }
    
    @Column(name="OLD_LINK_NO", length=20)
    
    public String getOldLinkNo() {
        return this.oldLinkNo;
    }
    
    public void setOldLinkNo(String oldLinkNo) {
        this.oldLinkNo = oldLinkNo;
    }
    
    @Column(name="NEW_LINK_NO", length=20)
    
    public String getNewLinkNo() {
        return this.newLinkNo;
    }
    
    public void setNewLinkNo(String newLinkNo) {
        this.newLinkNo = newLinkNo;
    }
    
    @Column(name="OLD_RECOMMEND_NO", length=20)
    
    public String getOldRecommendNo() {
        return this.oldRecommendNo;
    }
    
    public void setOldRecommendNo(String oldRecommendNo) {
        this.oldRecommendNo = oldRecommendNo;
    }
    
    @Column(name="NEW_RECOMMEND_NO", length=20)
    
    public String getNewRecommendNo() {
        return this.newRecommendNo;
    }
    
    public void setNewRecommendNo(String newRecommendNo) {
        this.newRecommendNo = newRecommendNo;
    }
    
    @Column(name="NEW_COMPANY_CODE", length=2)
    
    public String getNewCompanyCode() {
        return this.newCompanyCode;
    }
    
    public void setNewCompanyCode(String newCompanyCode) {
        this.newCompanyCode = newCompanyCode;
    }
    
    @Column(name="USER_CREATE_TIME", length=7)
    
    public Date getUserCreateTime() {
        return this.userCreateTime;
    }
    
    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }
    
    @Column(name="ORDER_NO")
    public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdSummaryList pojo = (JbdSummaryList) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (cardType != null ? !cardType.equals(pojo.cardType) : pojo.cardType != null) return false;
        if (inType != null ? !inType.equals(pojo.inType) : pojo.inType != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (orderType != null ? !orderType.equals(pojo.orderType) : pojo.orderType != null) return false;
        if (oldCheckDate != null ? !oldCheckDate.equals(pojo.oldCheckDate) : pojo.oldCheckDate != null) return false;
        if (newCheckDate != null ? !newCheckDate.equals(pojo.newCheckDate) : pojo.newCheckDate != null) return false;
        if (pvAmt != null ? !pvAmt.equals(pojo.pvAmt) : pojo.pvAmt != null) return false;
        if (oldLinkNo != null ? !oldLinkNo.equals(pojo.oldLinkNo) : pojo.oldLinkNo != null) return false;
        if (newLinkNo != null ? !newLinkNo.equals(pojo.newLinkNo) : pojo.newLinkNo != null) return false;
        if (oldRecommendNo != null ? !oldRecommendNo.equals(pojo.oldRecommendNo) : pojo.oldRecommendNo != null) return false;
        if (newRecommendNo != null ? !newRecommendNo.equals(pojo.newRecommendNo) : pojo.newRecommendNo != null) return false;
        if (newCompanyCode != null ? !newCompanyCode.equals(pojo.newCompanyCode) : pojo.newCompanyCode != null) return false;
        if (userCreateTime != null ? !userCreateTime.equals(pojo.userCreateTime) : pojo.userCreateTime != null) return false;
        if (wweek != null ? !wweek.equals(pojo.wweek) : pojo.wweek != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (inType != null ? inType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (oldCheckDate != null ? oldCheckDate.hashCode() : 0);
        result = 31 * result + (newCheckDate != null ? newCheckDate.hashCode() : 0);
        result = 31 * result + (pvAmt != null ? pvAmt.hashCode() : 0);
        result = 31 * result + (oldLinkNo != null ? oldLinkNo.hashCode() : 0);
        result = 31 * result + (newLinkNo != null ? newLinkNo.hashCode() : 0);
        result = 31 * result + (oldRecommendNo != null ? oldRecommendNo.hashCode() : 0);
        result = 31 * result + (newRecommendNo != null ? newRecommendNo.hashCode() : 0);
        result = 31 * result + (newCompanyCode != null ? newCompanyCode.hashCode() : 0);
        result = 31 * result + (userCreateTime != null ? userCreateTime.hashCode() : 0);
        result = 31 * result + (wweek != null ? wweek.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("cardType").append("='").append(getCardType()).append("', ");
        sb.append("inType").append("='").append(getInType()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("orderType").append("='").append(getOrderType()).append("', ");
        sb.append("oldCheckDate").append("='").append(getOldCheckDate()).append("', ");
        sb.append("newCheckDate").append("='").append(getNewCheckDate()).append("', ");
        sb.append("pvAmt").append("='").append(getPvAmt()).append("', ");
        sb.append("oldLinkNo").append("='").append(getOldLinkNo()).append("', ");
        sb.append("newLinkNo").append("='").append(getNewLinkNo()).append("', ");
        sb.append("oldRecommendNo").append("='").append(getOldRecommendNo()).append("', ");
        sb.append("newRecommendNo").append("='").append(getNewRecommendNo()).append("', ");
        sb.append("newCompanyCode").append("='").append(getNewCompanyCode()).append("', ");
        sb.append("userCreateTime").append("='").append(getUserCreateTime()).append("', ");
        sb.append("wweek").append("='").append(getWweek()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
