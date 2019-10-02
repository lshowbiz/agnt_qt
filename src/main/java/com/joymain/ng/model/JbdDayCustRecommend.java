package com.joymain.ng.model;

import java.util.Date;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_DAY_CUST_RECOMMEND"
 *     
 */

public class JbdDayCustRecommend {

	private Long id;
	private Integer wweek;
	private String userCode;
	private String userName;
	private Integer memberLevel;
	private Integer memberStar;
	private Integer recommendPv;
	private Integer sendMoney;
	private Integer rax;
	private Integer deductMoney;
	private Integer freezeStatus;
	private Integer status;
	private Date sendDate;
	private Date comDate;
	
	
	public JbdDayCustRecommend() {
		super();
	}

	public JbdDayCustRecommend(Long id, Integer wweek, String userCode, String userName, Integer memberLevel, Integer memberStar,
			Integer recommendPv, Integer sendMoney, Integer rax, Integer deductMoney, Integer freezeStatus,
			Integer status, Date sendDate, Date comDate) {
		super();
		this.id = id;
		this.wweek = wweek;
		this.userCode = userCode;
		this.userName = userName;
		this.memberLevel = memberLevel;
		this.memberStar = memberStar;
		this.recommendPv = recommendPv;
		this.sendMoney = sendMoney;
		this.rax = rax;
		this.deductMoney = deductMoney;
		this.freezeStatus = freezeStatus;
		this.status = status;
		this.sendDate = sendDate;
		this.comDate = comDate;
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
	 *             column="W_WEEK"
	 *             length="8"
	 *         
	 */
	public Integer getWweek() {
		return wweek;
	}
	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="USER_CODE"
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
	 *             column="USER_NAME"
	 *         
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="MEMBER_LEVEL"
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
	 *             column="MEMBER_STAR"
	 *         
	 */
	public Integer getMemberStar() {
		return memberStar;
	}
	
	public void setMemberStar(Integer memberStar) {
		this.memberStar = memberStar;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="RECOMMEND_PV"
	 *         
	 */
	public Integer getRecommendPv() {
		return recommendPv;
	}

	public void setRecommendPv(Integer recommendPv) {
		this.recommendPv = recommendPv;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SEND_MONEY"
	 *         
	 */
	public Integer getSendMoney() {
		return sendMoney;
	}

	public void setSendMoney(Integer sendMoney) {
		this.sendMoney = sendMoney;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="RAX"
	 *         
	 */
	public Integer getRax() {
		return rax;
	}

	public void setRax(Integer rax) {
		this.rax = rax;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="DEDUCT_MONEY"
	 *         
	 */
	public Integer getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(Integer deductMoney) {
		this.deductMoney = deductMoney;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="FREEZE_STATUS"
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
	 *             column="STATUS"
	 *         
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SEND_DATE"
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
	 *             column="COM_DATE"
	 *         
	 */
	public Date getComDate() {
		return comDate;
	}
	
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deductMoney == null) ? 0 : deductMoney.hashCode());
		result = prime * result + ((freezeStatus == null) ? 0 : freezeStatus.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberLevel == null) ? 0 : memberLevel.hashCode());
		result = prime * result + ((memberStar == null) ? 0 : memberStar.hashCode());
		result = prime * result + ((rax == null) ? 0 : rax.hashCode());
		result = prime * result + ((recommendPv == null) ? 0 : recommendPv.hashCode());
		result = prime * result + ((sendDate == null) ? 0 : sendDate.hashCode());
		result = prime * result + ((sendMoney == null) ? 0 : sendMoney.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((wweek == null) ? 0 : wweek.hashCode());
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
		JbdDayCustRecommend other = (JbdDayCustRecommend) obj;
		if (deductMoney == null) {
			if (other.deductMoney != null)
				return false;
		} else if (!deductMoney.equals(other.deductMoney))
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
		if (memberStar == null) {
			if (other.memberStar != null)
				return false;
		} else if (!memberStar.equals(other.memberStar))
			return false;
		if (rax == null) {
			if (other.rax != null)
				return false;
		} else if (!rax.equals(other.rax))
			return false;
		if (recommendPv == null) {
			if (other.recommendPv != null)
				return false;
		} else if (!recommendPv.equals(other.recommendPv))
			return false;
		if (sendDate == null) {
			if (other.sendDate != null)
				return false;
		} else if (!sendDate.equals(other.sendDate))
			return false;
		if (sendMoney == null) {
			if (other.sendMoney != null)
				return false;
		} else if (!sendMoney.equals(other.sendMoney))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (wweek == null) {
			if (other.wweek != null)
				return false;
		} else if (!wweek.equals(other.wweek))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JbdDayCustRecommend [id=" + id + ", wweek=" + wweek + ", userCode=" + userCode + ", userName="
				+ userName + ", memberLevel=" + memberLevel + ",memberStar=" + memberStar + ", recommendPv=" + recommendPv + ", sendMoney="
				+ sendMoney + ", rax=" + rax + ", deductMoney=" + deductMoney + ", freezeStatus=" + freezeStatus
				+ ", status=" + status + ", sendDate=" + sendDate + ", comDate=" + comDate + "]";
	}

    
}
