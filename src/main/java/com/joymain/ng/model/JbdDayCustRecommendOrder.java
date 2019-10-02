package com.joymain.ng.model;

import java.util.Date;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_DAY_CUST_RECOMMEND_ORDER"
 *     
 */

public class JbdDayCustRecommendOrder {

	private Long id;
	private Integer wweek;
	private String userCode;
	private String reuserCode;
	private Integer recommendLvl;
	private Integer memberOrderNno;
	private Integer orderType;
	private Integer orderMoney;
	private Integer recommendPv;
	
	
	public JbdDayCustRecommendOrder() {
		super();
	}
	public JbdDayCustRecommendOrder(Long id, Integer wweek, String userCode, String reuserCode, Integer recommendLvl,
			Integer memberOrderNno, Integer orderType, Integer orderMoney, Integer recommendPv) {
		super();
		this.id = id;
		this.wweek = wweek;
		this.userCode = userCode;
		this.reuserCode = reuserCode;
		this.recommendLvl = recommendLvl;
		this.memberOrderNno = memberOrderNno;
		this.orderType = orderType;
		this.orderMoney = orderMoney;
		this.recommendPv = recommendPv;
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
	 *             column="REUSER_CODE"
	 *         
	 */
	public String getReuserCode() {
		return reuserCode;
	}
	public void setReuserCode(String reuserCode) {
		this.reuserCode = reuserCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="RECOMMEND_LVL"
	 *         
	 */
	public Integer getRecommendLvl() {
		return recommendLvl;
	}
	public void setRecommendLvl(Integer recommendLvl) {
		this.recommendLvl = recommendLvl;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="MEMBER_ORDER_NO"
	 *         
	 */
	public Integer getMemberOrderNno() {
		return memberOrderNno;
	}
	public void setMemberOrderNno(Integer memberOrderNno) {
		this.memberOrderNno = memberOrderNno;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="ORDER_TYPE"
	 *         
	 */
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="ORDER_MONEY"
	 *         
	 */
	public Integer getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Integer orderMoney) {
		this.orderMoney = orderMoney;
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberOrderNno == null) ? 0 : memberOrderNno.hashCode());
		result = prime * result + ((orderMoney == null) ? 0 : orderMoney.hashCode());
		result = prime * result + ((orderType == null) ? 0 : orderType.hashCode());
		result = prime * result + ((recommendLvl == null) ? 0 : recommendLvl.hashCode());
		result = prime * result + ((recommendPv == null) ? 0 : recommendPv.hashCode());
		result = prime * result + ((reuserCode == null) ? 0 : reuserCode.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
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
		JbdDayCustRecommendOrder other = (JbdDayCustRecommendOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memberOrderNno == null) {
			if (other.memberOrderNno != null)
				return false;
		} else if (!memberOrderNno.equals(other.memberOrderNno))
			return false;
		if (orderMoney == null) {
			if (other.orderMoney != null)
				return false;
		} else if (!orderMoney.equals(other.orderMoney))
			return false;
		if (orderType == null) {
			if (other.orderType != null)
				return false;
		} else if (!orderType.equals(other.orderType))
			return false;
		if (recommendLvl == null) {
			if (other.recommendLvl != null)
				return false;
		} else if (!recommendLvl.equals(other.recommendLvl))
			return false;
		if (recommendPv == null) {
			if (other.recommendPv != null)
				return false;
		} else if (!recommendPv.equals(other.recommendPv))
			return false;
		if (reuserCode == null) {
			if (other.reuserCode != null)
				return false;
		} else if (!reuserCode.equals(other.reuserCode))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
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
		return "JbdDayCustRecommendOrder [id=" + id + ", wweek=" + wweek + ", userCode=" + userCode + ", reuserCode="
				+ reuserCode + ", recommendLvl=" + recommendLvl + ", memberOrderNno=" + memberOrderNno + ", orderType="
				+ orderType + ", orderMoney=" + orderMoney + ", recommendPv=" + recommendPv + "]";
	}
	
	
	
	
}
