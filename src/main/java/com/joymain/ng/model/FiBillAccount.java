package com.joymain.ng.model;

import java.io.Serializable;
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

import org.hibernate.search.annotations.DocumentId;

/**
 * 快钱支付商户号
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "FI_BILL_ACCOUNT")
@XmlRootElement
public class FiBillAccount extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long accountId;

	private String billAccountCode;// 快钱商户号

	private String accountName;// 注册商户名称

	private String registerEmail;// 注册邮箱

	private String billAccountPassword;// 商户密码

	private String remark;

	private String status;// 启用状态，默认为启用1，停用：0

	private String isDefault;// 是否是默认，如果需要设默认，值为1。当经销商找不到商户号的时候，选用默认值

	private Date createTime;

	private String createUserCode;

	private String createUserName;

	private String field;// 所属大区（1.东北区，2.华北区，3.华东区，4.华南区）

	private String province;// 所属省

	private String userCode;// 经销商编码

	private Long sortIndex;// 排序码

	private Long incomeLimit;// 每月进账额度

	private String accountType;// 终端类型：1，PC；2，移动终端

	private String providerType;// 平台：1.快钱、2.畅捷通

	private Long maxMoney;// 支付最大金额

	private String password;// 前端验签的密钥，或者证书存储路径
	private String password2;// 后端验签的密钥，或者证书存储路径
	
	private String businessType;//1非全额  2全额

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mfi")
	@SequenceGenerator(name = "seq_mfi", sequenceName = "SEQ_MFI", allocationSize = 1)
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false, precision = 12, scale = 0)
	@DocumentId
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "ACCOUNT_TYPE", nullable = false, length = 2)
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Column(name = "PROVIDER_TYPE", nullable = false, length = 2)
	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	@Column(name = "BILL_ACCOUNT_CODE", nullable = false, length = 50)
	public String getBillAccountCode() {
		return this.billAccountCode;
	}

	public void setBillAccountCode(String billAccountCode) {
		this.billAccountCode = billAccountCode;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "IS_DEFAULT", length = 1)
	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PASSWORD2", length = 100)
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER_CODE", length = 20)
	public String getCreateUserCode() {
		return this.createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	@Column(name = "CREATE_USER_NAME", length = 20)
	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Column(name = "ACCOUNT_NAME", nullable = false, length = 50)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "REGISTER_EMAIL", length = 50)
	public String getRegisterEmail() {
		return this.registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	@Column(name = "BILL_ACCOUNT_PASSWORD", nullable = false, length = 50)
	public String getBillAccountPassword() {
		return this.billAccountPassword;
	}

	public void setBillAccountPassword(String billAccountPassword) {
		this.billAccountPassword = billAccountPassword;
	}

	@Column(name = "FIELD", length = 20)
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Column(name = "USER_CODE", nullable = false, length = 50)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "SORT_INDEX", precision = 10, scale = 0)
	public Long getSortIndex() {
		return this.sortIndex;
	}

	public void setSortIndex(Long sortIndex) {
		this.sortIndex = sortIndex;
	}

	@Column(name = "PROVINCE", length = 20)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "MAX_MONEY", precision = 18, scale = 0)
	public Long getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(Long maxMoney) {
		this.maxMoney = maxMoney;
	}

	@Column(name = "INCOME_LIMIT", precision = 18, scale = 0)
	public Long getIncomeLimit() {
		return this.incomeLimit;
	}

	public void setIncomeLimit(Long incomeLimit) {
		this.incomeLimit = incomeLimit;
	}
	
	@Column(name = "BUSINESS_TYPE", precision = 2, scale = 0)
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		FiBillAccount pojo = (FiBillAccount) o;

		if (billAccountCode != null ? !billAccountCode.equals(pojo.billAccountCode) : pojo.billAccountCode != null)
			return false;
		if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null)
			return false;
		if (status != null ? !status.equals(pojo.status) : pojo.status != null)
			return false;
		if (isDefault != null ? !isDefault.equals(pojo.isDefault) : pojo.isDefault != null)
			return false;
		if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null)
			return false;
		if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null)
			return false;
		if (createUserName != null ? !createUserName.equals(pojo.createUserName) : pojo.createUserName != null)
			return false;
		if (accountName != null ? !accountName.equals(pojo.accountName) : pojo.accountName != null)
			return false;
		if (registerEmail != null ? !registerEmail.equals(pojo.registerEmail) : pojo.registerEmail != null)
			return false;
		if (billAccountPassword != null ? !billAccountPassword.equals(pojo.billAccountPassword) : pojo.billAccountPassword != null)
			return false;
		if (field != null ? !field.equals(pojo.field) : pojo.field != null)
			return false;
		if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null)
			return false;
		if (sortIndex != null ? !sortIndex.equals(pojo.sortIndex) : pojo.sortIndex != null)
			return false;
		if (province != null ? !province.equals(pojo.province) : pojo.province != null)
			return false;
		if (incomeLimit != null ? !incomeLimit.equals(pojo.incomeLimit) : pojo.incomeLimit != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		result = (billAccountCode != null ? billAccountCode.hashCode() : 0);
		result = 31 * result + (remark != null ? remark.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (isDefault != null ? isDefault.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);
		result = 31 * result + (createUserName != null ? createUserName.hashCode() : 0);
		result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
		result = 31 * result + (registerEmail != null ? registerEmail.hashCode() : 0);
		result = 31 * result + (billAccountPassword != null ? billAccountPassword.hashCode() : 0);
		result = 31 * result + (field != null ? field.hashCode() : 0);
		result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
		result = 31 * result + (sortIndex != null ? sortIndex.hashCode() : 0);
		result = 31 * result + (province != null ? province.hashCode() : 0);
		result = 31 * result + (incomeLimit != null ? incomeLimit.hashCode() : 0);

		return result;
	}

	@Override
	public String toString() {
		return "FiBillAccount [accountId=" + accountId + ", accountName=" + accountName + ", accountType=" + accountType + ", billAccountCode=" + billAccountCode
				+ ", billAccountPassword=" + billAccountPassword + ", createTime=" + createTime + ", createUserCode=" + createUserCode + ", createUserName=" + createUserName
				+ ", field=" + field + ", incomeLimit=" + incomeLimit + ", isDefault=" + isDefault + ", providerType=" + providerType + ", province=" + province
				+ ", registerEmail=" + registerEmail + ", remark=" + remark + ", sortIndex=" + sortIndex + ", status=" + status + ", userCode=" + userCode + "]";
	}

}
