package com.joymain.ng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



@Entity
@Table(name="JBD_YD_REBATE_LIST")

@XmlRootElement
public class JbdYdRebateList extends BaseObject implements Serializable {


	  /** default constructor */
    public JbdYdRebateList() {
    }

    private Long id;
    private String userCode;
    private String miUserName;
    private Integer memberLevel;
    private Date calcTime;
    private String rebateUserCode;
    private String rebateOrderNo;
	private BigDecimal rebateRatio;
    private BigDecimal rebateAmount;
    private BigDecimal sendAmount;
    private String memberType;
    private Integer freezeStatus;
    private Integer sendStatus;
    private Date sendDate;
    private String sendOperator;
   
    private Integer ydStoreLevel;
    

	@Column(name="YD_STORE_LEVEL")
    public Integer getYdStoreLevel() {
		return ydStoreLevel;
	}

	public void setYdStoreLevel(Integer ydStoreLevel) {
		this.ydStoreLevel = ydStoreLevel;
	}
     // Property accessors
	  @Id
      @Column(name="ID")

     public Long getId() {
         return this.id;
     }
     
     public void setId(Long id) {
         this.id = id;
     }
	@Column(name="USER_CODE")
  	public String getUserCode() {
  		return userCode;
  	}
  	
  	
  	public void setUserCode(String userCode) {
  		this.userCode = userCode;
  	}

     @Column(name="MI_USER_NAME")
     public String getMiUserName() {
 		return miUserName;
 	}

 	public void setMiUserName(String miUserName) {
 		this.miUserName = miUserName;
 	}

     @Column(name="MEMBER_LEVEL")
 	public Integer getMemberLevel() {
 		return memberLevel;
 	}

 	public void setMemberLevel(Integer memberLevel) {
 		this.memberLevel = memberLevel;
 	}

     @Column(name="CALC_TIME")
 	public Date getCalcTime() {
 		return calcTime;
 	}
 	public void setCalcTime(Date calcTime) {
 		this.calcTime = calcTime;
 	}

     @Column(name="REBATE_USER_CODE")
 	public String getRebateUserCode() {
 		return rebateUserCode;
 	}
 	public void setRebateUserCode(String rebateUserCode) {
 		this.rebateUserCode = rebateUserCode;
 	}

     @Column(name="REBATE_ORDER_NO")
 	public String getRebateOrderNo() {
 		return rebateOrderNo;
 	}

 	public void setRebateOrderNo(String rebateOrderNo) {
 		this.rebateOrderNo = rebateOrderNo;
 	}

     @Column(name="REBATE_RATIO")
 	public BigDecimal getRebateRatio() {
 		return rebateRatio;
 	}

 	public void setRebateRatio(BigDecimal rebateRatio) {
 		this.rebateRatio = rebateRatio;
 	}

     @Column(name="REBATE_AMOUNT")
 	public BigDecimal getRebateAmount() {
 		return rebateAmount;
 	}

 	public void setRebateAmount(BigDecimal rebateAmount) {
 		this.rebateAmount = rebateAmount;
 	}

     @Column(name="SEND_AMOUNT")
 	public BigDecimal getSendAmount() {
 		return sendAmount;
 	}

 	public void setSendAmount(BigDecimal sendAmount) {
 		this.sendAmount = sendAmount;
 	}
 	

     @Column(name="MEMBER_TYPE")
 	public String getMemberType() {
 		return memberType;
 	}

 	public void setMemberType(String memberType) {
 		this.memberType = memberType;
 	}
 	

     @Column(name="FREEZE_STATUS")
 	public Integer getFreezeStatus() {
 		return freezeStatus;
 	}

 	public void setFreezeStatus(Integer freezeStatus) {
 		this.freezeStatus = freezeStatus;
 	}

     @Column(name="SEND_STATUS")
 	public Integer getSendStatus() {
 		return sendStatus;
 	}
 	public void setSendStatus(Integer sendStatus) {
 		this.sendStatus = sendStatus;
 	}
     @Column(name="SEND_DATE")
 	public Date getSendDate() {
 		return sendDate;
 	}

 	public void setSendDate(Date sendDate) {
 		this.sendDate = sendDate;
 	}
 	
     @Column(name="SEND_OPERATOR")
    public String getSendOperator() {
 		return sendOperator;
 	}


 	public void setSendOperator(String sendOperator) {
 		this.sendOperator = sendOperator;
 	}


	public JbdYdRebateList(JmiMember jmiMember, String miUserName, Integer memberLevel, Date calcTime,
			String rebateUserCode, String rebateOrderNo, BigDecimal rebateRatio, BigDecimal rebateAmount,
			BigDecimal sendAmount, String memberType, Integer freezeStatus, Integer sendStatus, Date sendDate,
			String sendOperator) {
		super();
		this.userCode = userCode;
		this.miUserName = miUserName;
		this.memberLevel = memberLevel;
		this.calcTime = calcTime;
		this.rebateUserCode = rebateUserCode;
		this.rebateOrderNo = rebateOrderNo;
		this.rebateRatio = rebateRatio;
		this.rebateAmount = rebateAmount;
		this.sendAmount = sendAmount;
		this.memberType = memberType;
		this.freezeStatus = freezeStatus;
		this.sendStatus = sendStatus;
		this.sendDate = sendDate;
		this.sendOperator = sendOperator;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("id").append("='").append(getId()).append("' ");			
      buffer.append("miUserName").append("='").append(getMiUserName()).append("' ");
      buffer.append("memberLevel").append("='").append(getMemberLevel()).append("' ");	
      buffer.append("calcTime").append("='").append(getCalcTime()).append("' ");	
      buffer.append("rebateUserCode").append("='").append(getRebateUserCode()).append("' ");	
      buffer.append("rebateOrderNo").append("='").append(getRebateOrderNo()).append("' ");	
      buffer.append("rebateRatio").append("='").append(getRebateRatio()).append("' ");	
      buffer.append("rebateAmount").append("='").append(getRebateAmount()).append("' ");	
      buffer.append("memberType").append("='").append(getMemberType()).append("' ");	
      buffer.append("freezeStatus").append("='").append(getFreezeStatus()).append("' ");	
      buffer.append("sendStatus").append("='").append(getSendStatus()).append("' ");	
      buffer.append("sendDate").append("='").append(getSendDate()).append("' ");	
      buffer.append("sendOperator").append("='").append(getSendOperator()).append("' ");	
      
      buffer.append("]");
      
      return buffer.toString();
     }

	@Override
	public int hashCode() {
        int result = 17;
        
        result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
        result = 37 * result + ( getMiUserName() == null ? 0 : this.getMiUserName().hashCode() );
        result = 37 * result + ( getMemberLevel() == null ? 0 : this.getMemberLevel().hashCode() );
        result = 37 * result + ( getCalcTime() == null ? 0 : this.getCalcTime().hashCode() );
        result = 37 * result + ( getRebateUserCode() == null ? 0 : this.getRebateUserCode().hashCode() );
        result = 37 * result + ( getRebateOrderNo() == null ? 0 : this.getRebateOrderNo().hashCode() );
        result = 37 * result + ( getRebateRatio() == null ? 0 : this.getRebateRatio().hashCode() );
        result = 37 * result + ( getRebateAmount() == null ? 0 : this.getRebateAmount().hashCode() );
        result = 37 * result + ( getSendAmount() == null ? 0 : this.getSendAmount().hashCode() );
        result = 37 * result + ( getMemberType() == null ? 0 : this.getMemberType().hashCode() );
        result = 37 * result + ( getFreezeStatus() == null ? 0 : this.getFreezeStatus().hashCode() );
        result = 37 * result + ( getSendStatus() == null ? 0 : this.getSendStatus().hashCode() );
        result = 37 * result + ( getSendDate() == null ? 0 : this.getSendDate().hashCode() );
        result = 37 * result + ( getSendOperator() == null ? 0 : this.getSendOperator().hashCode() );
        return result;
	}
	 public boolean equals(Object other) {
	       
	        if ( (this == other ) ) return true;
			if ( (other == null ) ) return false;
			if ( !(other instanceof JbdYdRebateList ) ) return false;
			JbdYdRebateList castOther = ( JbdYdRebateList ) other; 
	         
			 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
			 && ( (this.getMiUserName()==castOther.getMiUserName()) || ( this.getMiUserName()!=null && castOther.getMiUserName()!=null && this.getMiUserName().equals(castOther.getMiUserName()) ) )
			 && ( (this.getMemberLevel()==castOther.getMemberLevel()) || ( this.getMemberLevel()!=null && castOther.getMemberLevel()!=null && this.getMemberLevel().equals(castOther.getMemberLevel()) ) )
			 && ( (this.getCalcTime()==castOther.getCalcTime()) || ( this.getCalcTime()!=null && castOther.getCalcTime()!=null && this.getCalcTime().equals(castOther.getCalcTime()) ) )
			 && ( (this.getRebateUserCode()==castOther.getRebateUserCode()) || ( this.getRebateUserCode()!=null && castOther.getRebateUserCode()!=null && this.getRebateUserCode().equals(castOther.getRebateUserCode()) ) )
			 && ( (this.getRebateOrderNo()==castOther.getRebateOrderNo()) || ( this.getRebateOrderNo()!=null && castOther.getRebateOrderNo()!=null && this.getRebateOrderNo().equals(castOther.getRebateOrderNo()) ) )
			 && ( (this.getRebateRatio()==castOther.getRebateRatio()) || ( this.getRebateRatio()!=null && castOther.getRebateRatio()!=null && this.getRebateRatio().equals(castOther.getRebateRatio()) ) )
			 && ( (this.getRebateAmount()==castOther.getRebateAmount()) || ( this.getRebateAmount()!=null && castOther.getRebateAmount()!=null && this.getRebateAmount().equals(castOther.getRebateAmount()) ) )
			 && ( (this.getSendAmount()==castOther.getSendAmount()) || ( this.getSendAmount()!=null && castOther.getSendAmount()!=null && this.getSendAmount().equals(castOther.getSendAmount()) ) )
			 && ( (this.getMemberType()==castOther.getMemberType()) || ( this.getMemberType()!=null && castOther.getMemberType()!=null && this.getMemberType().equals(castOther.getMemberType()) ) )
			 && ( (this.getFreezeStatus()==castOther.getFreezeStatus()) || ( this.getFreezeStatus()!=null && castOther.getFreezeStatus()!=null && this.getFreezeStatus().equals(castOther.getFreezeStatus()) ) )
			 && ( (this.getSendStatus()==castOther.getSendStatus()) || ( this.getSendStatus()!=null && castOther.getSendStatus()!=null && this.getSendStatus().equals(castOther.getSendStatus()) ) )
			 && ( (this.getSendOperator()==castOther.getSendOperator()) || ( this.getSendOperator()!=null && castOther.getSendOperator()!=null && this.getSendOperator().equals(castOther.getSendOperator()) ) )
			 && ( (this.getSendDate()==castOther.getSendDate()) || ( this.getSendDate()!=null && castOther.getSendDate()!=null && this.getSendDate().equals(castOther.getSendDate()) ) );
	   
   }
	


	






}
