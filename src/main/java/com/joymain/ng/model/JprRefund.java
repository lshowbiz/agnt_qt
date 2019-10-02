package com.joymain.ng.model;


import com.joymain.ng.model.BaseObject;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name="JPR_REFUND")

@XmlRootElement
public class JprRefund extends BaseObject implements Serializable {
    private String roNo;
    private BigDecimal WYear;
    private BigDecimal WMonth;
    private BigDecimal WWeek;
    private String companyCode;
    private BigDecimal amount;
    private BigDecimal pvAmt;
    private Date orderDate;
    private String resendSpNo;
    private String userCode;
    private String intoStatus;
    private Date intoTime;
    private String intoUNo;
    private String intoRemark;
    private String refundStatus;
    private Date refundTime;
    private String refundUNo;
    private String refundRemark;
    private BigDecimal moId;
    private String orderType;
    private String remark;
    private Date createTime;
    private String createUNo;
    private Date editTime;
    private String editUNo;
    private String locked;
    private String returnType;
    
    //modify gw 2014-11-12 结算费用及退货单状态---begin
    private String orderStatus;
	private String repairFee;//维修费
	private String renovationFee;//翻新费
	private String logisticsFee;//物流费
	private String settledBonus;//不能扣回的奖金（已结算）
	private String fillFreight;//回补运费 
	private String logisticsFeeSix;
	private String logisticsFeeSeven;
	private String logisticsFeeEight;
    //modify gw 2014-11-12 结算费用及退货单状态---begin
	/*LOGISTICS_FEE_ONE   VARCHAR2(200)  Y                维修费                                           
	LOGISTICS_FEE_TWO   VARCHAR2(200)  Y                翻新费                                           
	LOGISTICS_FEE_THREE VARCHAR2(200)  Y                物流费                                           
	LOGISTICS_FEE_FOUR  VARCHAR2(200)  Y                不能扣回的奖金（已结算）                         
	LOGISTICS_FEE_FIVE  VARCHAR2(200)  Y                回补运费     */
    
	private Set<JprRefundList> jprRefundList = new HashSet<JprRefundList>(0);
	
	
/*	private JpoMemberOrder jpoMemberOrder;*/

	    @Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
		@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1)
		@Column(name="RO_NO", precision=10, scale=0) 
    public String getRoNo() {
        return this.roNo;
    }
    
    public void setRoNo(String roNo) {
        this.roNo = roNo;
    }
    
    @Column(name="W_YEAR", precision=8, scale=0)
    public BigDecimal getWYear() {
        return this.WYear;
    }
    
    public void setWYear(BigDecimal WYear) {
        this.WYear = WYear;
    }
    
    @Column(name="W_MONTH", precision=8, scale=0)
    public BigDecimal getWMonth() {
        return this.WMonth;
    }
    
    public void setWMonth(BigDecimal WMonth) {
        this.WMonth = WMonth;
    }
    
    @Column(name="W_WEEK", precision=8, scale=0)
    public BigDecimal getWWeek() {
        return this.WWeek;
    }
    
    public void setWWeek(BigDecimal WWeek) {
        this.WWeek = WWeek;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="AMOUNT", nullable=false, precision=18)
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Column(name="PV_AMT", nullable=false, precision=18)
    public BigDecimal getPvAmt() {
        return this.pvAmt;
    }
    
    public void setPvAmt(BigDecimal pvAmt) {
        this.pvAmt = pvAmt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="ORDER_DATE", nullable=false, length=7)
    public Date getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    @Column(name="RESEND_SP_NO", nullable=false, length=20)
    public String getResendSpNo() {
        return this.resendSpNo;
    }
    
    public void setResendSpNo(String resendSpNo) {
        this.resendSpNo = resendSpNo;
    }
    
    @Column(name="USER_CODE", nullable=false, length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="INTO_STATUS", nullable=false, length=1)
    public String getIntoStatus() {
        return this.intoStatus;
    }
    
    public void setIntoStatus(String intoStatus) {
        this.intoStatus = intoStatus;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="INTO_TIME", length=7)
    public Date getIntoTime() {
        return this.intoTime;
    }
    
    public void setIntoTime(Date intoTime) {
        this.intoTime = intoTime;
    }
    
    @Column(name="INTO_U_NO", length=20)
    public String getIntoUNo() {
        return this.intoUNo;
    }
    
    public void setIntoUNo(String intoUNo) {
        this.intoUNo = intoUNo;
    }
    
    @Column(name="INTO_REMARK", length=200)
    public String getIntoRemark() {
        return this.intoRemark;
    }
    
    public void setIntoRemark(String intoRemark) {
        this.intoRemark = intoRemark;
    }
    
    @Column(name="REFUND_STATUS", nullable=false, length=1)
    public String getRefundStatus() {
        return this.refundStatus;
    }
    
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="REFUND_TIME", length=7)
    public Date getRefundTime() {
        return this.refundTime;
    }
    
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
    
    @Column(name="REFUND_U_NO", length=20)
    public String getRefundUNo() {
        return this.refundUNo;
    }
    
    public void setRefundUNo(String refundUNo) {
        this.refundUNo = refundUNo;
    }
    
    @Column(name="REFUND_REMARK", length=200)
    public String getRefundRemark() {
        return this.refundRemark;
    }
    
    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }
    
    @Column(name="MO_ID", precision=10, scale=0)
    public BigDecimal getMoId() {
        return this.moId;
    }
    
    public void setMoId(BigDecimal moId) {
        this.moId = moId;
    }
    
    @Column(name="ORDER_TYPE", length=2)
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    @Column(name="REMARK", length=4000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_U_NO", nullable=false, length=20)
    public String getCreateUNo() {
        return this.createUNo;
    }
    
    public void setCreateUNo(String createUNo) {
        this.createUNo = createUNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EDIT_TIME", length=7)
    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
    
    @Column(name="EDIT_U_NO", length=20)
    public String getEditUNo() {
        return this.editUNo;
    }
    
    public void setEditUNo(String editUNo) {
        this.editUNo = editUNo;
    }
    
    @Column(name="LOCKED", length=1)
    public String getLocked() {
        return this.locked;
    }
    
    public void setLocked(String locked) {
        this.locked = locked;
    }
    
    @Column(name="RETURN_TYPE", length=1)
    public String getReturnType() {
        return this.returnType;
    }
    
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Column(name="ORDER_STATUS", length=20)
    public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name="REPAIR_FEE", length=200)
	public String getRepairFee() {
		return repairFee;
	}

	public void setRepairFee(String repairFee) {
		this.repairFee = repairFee;
	}

	@Column(name="RENOVATION_FEE", length=200)
	public String getRenovationFee() {
		return renovationFee;
	}

	public void setRenovationFee(String renovationFee) {
		this.renovationFee = renovationFee;
	}

	@Column(name="LOGISTICS_FEE", length=200)
	public String getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(String logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	@Column(name="SETTLED_BONUS", length=200)
	public String getSettledBonus() {
		return settledBonus;
	}

	public void setSettledBonus(String settledBonus) {
		this.settledBonus = settledBonus;
	}

	@Column(name="FILL_FREIGHT", length=200)
	public String getFillFreight() {
		return fillFreight;
	}

	public void setFillFreight(String fillFreight) {
		this.fillFreight = fillFreight;
	}

	@Column(name="LOGISTICS_FEE_SIX", length=200)
	public String getLogisticsFeeSix() {
		return logisticsFeeSix;
	}

	public void setLogisticsFeeSix(String logisticsFeeSix) {
		this.logisticsFeeSix = logisticsFeeSix;
	}

	@Column(name="LOGISTICS_FEE_SEVEN", length=200)
	public String getLogisticsFeeSeven() {
		return logisticsFeeSeven;
	}

	public void setLogisticsFeeSeven(String logisticsFeeSeven) {
		this.logisticsFeeSeven = logisticsFeeSeven;
	}

	@Column(name="LOGISTICS_FEE_EIGHT", length=200)
	public String getLogisticsFeeEight() {
		return logisticsFeeEight;
	}

	public void setLogisticsFeeEight(String logisticsFeeEight) {
		this.logisticsFeeEight = logisticsFeeEight;
	}

	/*    @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name = "RO_NO")
	public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}

*/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jprRefund", fetch = FetchType.EAGER)
	public Set<JprRefundList> getJprRefundList() {
		return jprRefundList;
	}

	public void setJprRefundList(Set<JprRefundList> jprRefundList) {
		this.jprRefundList = jprRefundList;
	}

	
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JprRefund pojo = (JprRefund) o;

        if (WYear != null ? !WYear.equals(pojo.WYear) : pojo.WYear != null) return false;
        if (WMonth != null ? !WMonth.equals(pojo.WMonth) : pojo.WMonth != null) return false;
        if (WWeek != null ? !WWeek.equals(pojo.WWeek) : pojo.WWeek != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (pvAmt != null ? !pvAmt.equals(pojo.pvAmt) : pojo.pvAmt != null) return false;
        if (orderDate != null ? !orderDate.equals(pojo.orderDate) : pojo.orderDate != null) return false;
        if (resendSpNo != null ? !resendSpNo.equals(pojo.resendSpNo) : pojo.resendSpNo != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (intoStatus != null ? !intoStatus.equals(pojo.intoStatus) : pojo.intoStatus != null) return false;
        if (intoTime != null ? !intoTime.equals(pojo.intoTime) : pojo.intoTime != null) return false;
        if (intoUNo != null ? !intoUNo.equals(pojo.intoUNo) : pojo.intoUNo != null) return false;
        if (intoRemark != null ? !intoRemark.equals(pojo.intoRemark) : pojo.intoRemark != null) return false;
        if (refundStatus != null ? !refundStatus.equals(pojo.refundStatus) : pojo.refundStatus != null) return false;
        if (refundTime != null ? !refundTime.equals(pojo.refundTime) : pojo.refundTime != null) return false;
        if (refundUNo != null ? !refundUNo.equals(pojo.refundUNo) : pojo.refundUNo != null) return false;
        if (refundRemark != null ? !refundRemark.equals(pojo.refundRemark) : pojo.refundRemark != null) return false;
        if (moId != null ? !moId.equals(pojo.moId) : pojo.moId != null) return false;
        if (orderType != null ? !orderType.equals(pojo.orderType) : pojo.orderType != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUNo != null ? !createUNo.equals(pojo.createUNo) : pojo.createUNo != null) return false;
        if (editTime != null ? !editTime.equals(pojo.editTime) : pojo.editTime != null) return false;
        if (editUNo != null ? !editUNo.equals(pojo.editUNo) : pojo.editUNo != null) return false;
        if (locked != null ? !locked.equals(pojo.locked) : pojo.locked != null) return false;
        if (returnType != null ? !returnType.equals(pojo.returnType) : pojo.returnType != null) return false;

        if (orderStatus != null ? !orderStatus.equals(pojo.orderStatus) : pojo.orderStatus != null) return false;
        if (repairFee != null ? !repairFee.equals(pojo.repairFee) : pojo.repairFee != null) return false;
        if (renovationFee != null ? !renovationFee.equals(pojo.renovationFee) : pojo.renovationFee != null) return false;
        if (logisticsFee != null ? !logisticsFee.equals(pojo.logisticsFee) : pojo.logisticsFee != null) return false;
        if (settledBonus != null ? !settledBonus.equals(pojo.settledBonus) : pojo.settledBonus != null) return false;
        if (fillFreight != null ? !fillFreight.equals(pojo.fillFreight) : pojo.fillFreight != null) return false;
        if (logisticsFeeSix != null ? !logisticsFeeSix.equals(pojo.logisticsFeeSix) : pojo.logisticsFeeSix != null) return false;
        if (logisticsFeeSeven != null ? !logisticsFeeSeven.equals(pojo.logisticsFeeSeven) : pojo.logisticsFeeSeven != null) return false;
        if (logisticsFeeEight != null ? !logisticsFeeEight.equals(pojo.logisticsFeeEight) : pojo.logisticsFeeEight != null) return false;
     
        return true;
    }

    
	public int hashCode() {
        int result = 0;
        result = (WYear != null ? WYear.hashCode() : 0);
        result = 31 * result + (WMonth != null ? WMonth.hashCode() : 0);
        result = 31 * result + (WWeek != null ? WWeek.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (pvAmt != null ? pvAmt.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (resendSpNo != null ? resendSpNo.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (intoStatus != null ? intoStatus.hashCode() : 0);
        result = 31 * result + (intoTime != null ? intoTime.hashCode() : 0);
        result = 31 * result + (intoUNo != null ? intoUNo.hashCode() : 0);
        result = 31 * result + (intoRemark != null ? intoRemark.hashCode() : 0);
        result = 31 * result + (refundStatus != null ? refundStatus.hashCode() : 0);
        result = 31 * result + (refundTime != null ? refundTime.hashCode() : 0);
        result = 31 * result + (refundUNo != null ? refundUNo.hashCode() : 0);
        result = 31 * result + (refundRemark != null ? refundRemark.hashCode() : 0);
        result = 31 * result + (moId != null ? moId.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUNo != null ? createUNo.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        result = 31 * result + (editUNo != null ? editUNo.hashCode() : 0);
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (returnType != null ? returnType.hashCode() : 0);

        
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (repairFee != null ? repairFee.hashCode() : 0);
        result = 31 * result + (renovationFee != null ? renovationFee.hashCode() : 0);
        result = 31 * result + (logisticsFee != null ? logisticsFee.hashCode() : 0);
        result = 31 * result + (settledBonus != null ? settledBonus.hashCode() : 0);
        result = 31 * result + (fillFreight != null ? fillFreight.hashCode() : 0);
        result = 31 * result + (logisticsFeeSix != null ? logisticsFeeSix.hashCode() : 0);
        result = 31 * result + (logisticsFeeSeven != null ? logisticsFeeSeven.hashCode() : 0);
        result = 31 * result + (logisticsFeeEight != null ? logisticsFeeEight.hashCode() : 0);

        
        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("roNo").append("='").append(getRoNo()).append("', ");
        sb.append("WYear").append("='").append(getWYear()).append("', ");
        sb.append("WMonth").append("='").append(getWMonth()).append("', ");
        sb.append("WWeek").append("='").append(getWWeek()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("pvAmt").append("='").append(getPvAmt()).append("', ");
        sb.append("orderDate").append("='").append(getOrderDate()).append("', ");
        sb.append("resendSpNo").append("='").append(getResendSpNo()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("intoStatus").append("='").append(getIntoStatus()).append("', ");
        sb.append("intoTime").append("='").append(getIntoTime()).append("', ");
        sb.append("intoUNo").append("='").append(getIntoUNo()).append("', ");
        sb.append("intoRemark").append("='").append(getIntoRemark()).append("', ");
        sb.append("refundStatus").append("='").append(getRefundStatus()).append("', ");
        sb.append("refundTime").append("='").append(getRefundTime()).append("', ");
        sb.append("refundUNo").append("='").append(getRefundUNo()).append("', ");
        sb.append("refundRemark").append("='").append(getRefundRemark()).append("', ");
        sb.append("moId").append("='").append(getMoId()).append("', ");
        sb.append("orderType").append("='").append(getOrderType()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUNo").append("='").append(getCreateUNo()).append("', ");
        sb.append("editTime").append("='").append(getEditTime()).append("', ");
        sb.append("editUNo").append("='").append(getEditUNo()).append("', ");
        sb.append("locked").append("='").append(getLocked()).append("', ");
        sb.append("returnType").append("='").append(getReturnType()).append("'");
        
        sb.append("orderStatus").append("='").append(getOrderStatus()).append("'");
        sb.append("repairFee").append("='").append(getRepairFee()).append("'");
        sb.append("renovationFee").append("='").append(getRenovationFee()).append("'");
        sb.append("logisticsFee").append("='").append(getLogisticsFee()).append("'");
        sb.append("settledBonus").append("='").append(getSettledBonus()).append("'");
        sb.append("fillFreight").append("='").append(getFillFreight()).append("'");
        sb.append("logisticsFeeSix").append("='").append(getLogisticsFeeSix()).append("'");
        sb.append("logisticsFeeSeven").append("='").append(getLogisticsFeeSeven()).append("'");
        sb.append("logisticsFeeEight").append("='").append(getLogisticsFeeEight()).append("'");
        
        sb.append("]");
      
        return sb.toString();
    }


}
