package com.joymain.ng.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="V_JBD_YD_399_RECOMMEND_LIST"
 *     
 */

public class VJbdYd399RecommendList {

	private Long id;
	private String userCode;  // 会员编号'
	private String miUserName;  //会员昵称
	private Integer memberLevel;  //会员级别
	private Date calcTime;  //结算时间
	private String recommendNo;  //推荐399店主编号
	private BigDecimal recommendAmount;  //399店主推荐奖励
	private BigDecimal sendAmount;  //应 发奖金
	private Integer memberType;  //团队标识
	private Integer freezeStatus;  //冻结状态:1冻结；0不冻结
	private Integer sendStatus;  //发放状态:1-已发放；0-为发放
	private Date sendDate;  //发放日期
	private Integer ydStoreLevel; //云店店主级别
	private String sendOperator;  //奖金发放操作人 
	
	
	public VJbdYd399RecommendList() {
		super();
	}

	public VJbdYd399RecommendList(Long id, String userCode, String miUserName, Integer memberLevel, Date calcTime,
			String recommendNo, BigDecimal recommendAmount, BigDecimal sendAmount, Integer memberType,
			Integer freezeStatus, Integer sendStatus, Date sendDate, Integer ydStoreLevel, String sendOperator) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.miUserName = miUserName;
		this.memberLevel = memberLevel;
		this.calcTime = calcTime;
		this.recommendNo = recommendNo;
		this.recommendAmount = recommendAmount;
		this.sendAmount = sendAmount;
		this.memberType = memberType;
		this.freezeStatus = freezeStatus;
		this.sendStatus = sendStatus;
		this.sendDate = sendDate;
		this.ydStoreLevel = ydStoreLevel;
		this.sendOperator = sendOperator;
	}
	
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ID"
     *         
     */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**       
	 *      *            @hibernate.property
	 *             column="USER_CODE"
	 *             length="8"
	 *         
	 */
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="MI_USER_NAME"
	 *             length="8"
	 *         
	 */
	public String getMiUserName() {
		return miUserName;
	}

	public void setMiUserName(String miUserName) {
		this.miUserName = miUserName;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="MEMBER_LEVEL"
	 *             length="8"
	 *         
	 */
	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CALC_TIME"
	 *             length="8"
	 *         
	 */
	public Date getCalcTime() {
		return calcTime;
	}

	public void setCalcTime(Date calcTime) {
		this.calcTime = calcTime;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="RECOMMEND_NO"
	 *             length="8"
	 *         
	 */
	public String getRecommendNo() {
		return recommendNo;
	}

	public void setRecommendNo(String recommendNo) {
		this.recommendNo = recommendNo;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="RECOMMEND_AMOUNT"
	 *             length="8"
	 *         
	 */
	public BigDecimal getRecommendAmount() {
		return recommendAmount;
	}

	public void setRecommendAmount(BigDecimal recommendAmount) {
		this.recommendAmount = recommendAmount;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SEND_AMOUNT"
	 *             length="8"
	 *         
	 */
	public BigDecimal getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(BigDecimal sendAmount) {
		this.sendAmount = sendAmount;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="MEMBER_TYPE"
	 *             length="8"
	 *         
	 */
	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="FREEZE_STATUS"
	 *             length="8"
	 *         
	 */
	public Integer getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(Integer freezeStatus) {
		this.freezeStatus = freezeStatus;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SEND_STATUS"
	 *             length="8"
	 *         
	 */
	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SEND_DATE"
	 *             length="8"
	 *         
	 */
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="YD_STORE_LEVEL"
	 *             length="8"
	 *         
	 */
	public Integer getYdStoreLevel() {
		return ydStoreLevel;
	}

	public void setYdStoreLevel(Integer ydStoreLevel) {
		this.ydStoreLevel = ydStoreLevel;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SEND_OPERATOR"
	 *             length="8"
	 *         
	 */
	public String getSendOperator() {
		return sendOperator;
	}

	public void setSendOperator(String sendOperator) {
		this.sendOperator = sendOperator;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calcTime == null) ? 0 : calcTime.hashCode());
		result = prime * result + ((freezeStatus == null) ? 0 : freezeStatus.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberLevel == null) ? 0 : memberLevel.hashCode());
		result = prime * result + ((memberType == null) ? 0 : memberType.hashCode());
		result = prime * result + ((miUserName == null) ? 0 : miUserName.hashCode());
		result = prime * result + ((recommendAmount == null) ? 0 : recommendAmount.hashCode());
		result = prime * result + ((recommendNo == null) ? 0 : recommendNo.hashCode());
		result = prime * result + ((sendAmount == null) ? 0 : sendAmount.hashCode());
		result = prime * result + ((sendDate == null) ? 0 : sendDate.hashCode());
		result = prime * result + ((sendOperator == null) ? 0 : sendOperator.hashCode());
		result = prime * result + ((sendStatus == null) ? 0 : sendStatus.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((ydStoreLevel == null) ? 0 : ydStoreLevel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VJbdYd399RecommendList other = (VJbdYd399RecommendList) obj;
		if (calcTime == null) {
			if (other.calcTime != null)
				return false;
		} else if (!calcTime.equals(other.calcTime))
			return false;
		if (freezeStatus == null) {
			if (other.freezeStatus != null)
				return false;
		} else if (!freezeStatus.equals(other.freezeStatus))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memberLevel == null) {
			if (other.memberLevel != null)
				return false;
		} else if (!memberLevel.equals(other.memberLevel))
			return false;
		if (memberType == null) {
			if (other.memberType != null)
				return false;
		} else if (!memberType.equals(other.memberType))
			return false;
		if (miUserName == null) {
			if (other.miUserName != null)
				return false;
		} else if (!miUserName.equals(other.miUserName))
			return false;
		if (recommendAmount == null) {
			if (other.recommendAmount != null)
				return false;
		} else if (!recommendAmount.equals(other.recommendAmount))
			return false;
		if (recommendNo == null) {
			if (other.recommendNo != null)
				return false;
		} else if (!recommendNo.equals(other.recommendNo))
			return false;
		if (sendAmount == null) {
			if (other.sendAmount != null)
				return false;
		} else if (!sendAmount.equals(other.sendAmount))
			return false;
		if (sendDate == null) {
			if (other.sendDate != null)
				return false;
		} else if (!sendDate.equals(other.sendDate))
			return false;
		if (sendOperator == null) {
			if (other.sendOperator != null)
				return false;
		} else if (!sendOperator.equals(other.sendOperator))
			return false;
		if (sendStatus == null) {
			if (other.sendStatus != null)
				return false;
		} else if (!sendStatus.equals(other.sendStatus))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (ydStoreLevel == null) {
			if (other.ydStoreLevel != null)
				return false;
		} else if (!ydStoreLevel.equals(other.ydStoreLevel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VJbdYd399RecommendList [id=" + id + ", userCode=" + userCode + ", miUserName=" + miUserName
				+ ", memberLevel=" + memberLevel + ", calcTime=" + calcTime + ", recommendNo=" + recommendNo
				+ ", recommendAmount=" + recommendAmount + ", sendAmount=" + sendAmount + ", memberType=" + memberType
				+ ", freezeStatus=" + freezeStatus + ", sendStatus=" + sendStatus + ", sendDate=" + sendDate
				+ ", ydStoreLevel=" + ydStoreLevel + ", sendOperator=" + sendOperator + "]";
	}


}
