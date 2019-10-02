package com.joymain.ng.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PayAccount implements Serializable{
	private Long accountId;
	private String accountCode;//商户号
	private String userCode;//经销商编号
	private String accountType;//终端类型：1，PC；2，移动终端    
    private String providerType;//平台：1：快钱、2：畅捷通、3：盛付通、4：宝易互通、5平安橙E、6：汇付天下
    private Long maxMoney;//支付最大金额 
    private String passowrd;//密码
    
    private String key;//密钥
    
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getProviderType() {
		return providerType;
	}
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(Long maxMoney) {
		this.maxMoney = maxMoney;
	}
	public String getPassowrd() {
		return passowrd;
	}
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	
}
