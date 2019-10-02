package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JSYS_STOCK_ACCOUNT")
public class JsysStockAccount extends BaseObject implements Serializable {
	private static final long serialVersionUID = -8571148041795384849L;

	private Long id;
	private String userCode;
	private String userName;
	private String stockAccount;
	private String feeStatus;
	private Date createDate;
	private Date updateDate;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 22)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_CODE", nullable = false, length = 20)
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_NAME", nullable = false, length = 50)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "STOCK_ACCOUNT", nullable = false, length = 100)
	public String getStockAccount() {
		return stockAccount;
	}

	public void setStockAccount(String stockAccount) {
		this.stockAccount = stockAccount;
	}
	
	@Column(name = "FEE_STATUS", nullable = false, length = 4)
	public String getFeeStatus() {
		return feeStatus;
	}

	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}

	@Column(name="CREATE_DATE", length=11)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="UPDATE_DATE", length=11)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "JsysStockAccount [id=" + id + ", userCode=" + userCode
				+ ", userName=" + userName + ", stockAccount=" + stockAccount
				+ ", feeStatus=" + feeStatus + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((feeStatus == null) ? 0 : feeStatus.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((stockAccount == null) ? 0 : stockAccount.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		JsysStockAccount other = (JsysStockAccount) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (feeStatus == null) {
			if (other.feeStatus != null)
				return false;
		} else if (!feeStatus.equals(other.feeStatus))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stockAccount == null) {
			if (other.stockAccount != null)
				return false;
		} else if (!stockAccount.equals(other.stockAccount))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
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
		return true;
	}
}
