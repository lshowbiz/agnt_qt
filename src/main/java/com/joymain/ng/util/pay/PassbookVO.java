package com.joymain.ng.util.pay;

import java.math.BigDecimal;

import com.joymain.ng.model.JsysUser;

/**
 * 存折操作对象 三个数组必须 相同长度
 * 
 * @author lzg
 * 
 */
public class PassbookVO {

	String companyCode; // 公司编码
	JsysUser jsysUser; // 用户编码
	String operaterCode; // 操作人编码
	String operaterName; // 操作人姓名
	String uniqueCode; // 业务唯一码,0为没有
	Integer[] moneyTypes; // 资金类别
	BigDecimal[] moneys; // 资金金额
	String[] notes; // 存款说明
	String dataType; // 数据来源,1:PC, 2:手机
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public JsysUser getJsysUser() {
		return jsysUser;
	}
	public void setJsysUser(JsysUser jsysUser) {
		this.jsysUser = jsysUser;
	}
	public String getOperaterCode() {
		return operaterCode;
	}
	public void setOperaterCode(String operaterCode) {
		this.operaterCode = operaterCode;
	}
	public String getOperaterName() {
		return operaterName;
	}
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public Integer[] getMoneyTypes() {
		return moneyTypes;
	}
	public void setMoneyTypes(Integer[] moneyTypes) {
		this.moneyTypes = moneyTypes;
	}
	public BigDecimal[] getMoneys() {
		return moneys;
	}
	public void setMoneys(BigDecimal[] moneys) {
		this.moneys = moneys;
	}
	public String[] getNotes() {
		return notes;
	}
	public void setNotes(String[] notes) {
		this.notes = notes;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	

}
