package com.joymain.ng.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机端发货单号查询一实体类PdPhoneSendInfo
 * @author fu 2016-04-22
 *
 */
public class PdPhoneSendInfo {
      private String siNo;//发货单号
      private String memberOrderNo;//订单编号
      private String userCode;//会员编号
      private String recipientName;//会员姓名
      
      //moify by fu 2016-04-27 
      //新加字段：confirmReceipt Y表示已经确认收货，N表示未确认收货
      private String confirmReceipt;
      private List<PdPhoneProduct> PdPhoneProductList= new ArrayList();
      
	public String getSiNo() {
		return siNo;
	}
	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}
	public String getMemberOrderNo() {
		return memberOrderNo;
	}
	public void setMemberOrderNo(String memberOrderNo) {
		this.memberOrderNo = memberOrderNo;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getConfirmReceipt() {
		return confirmReceipt;
	}
	public void setConfirmReceipt(String confirmReceipt) {
		this.confirmReceipt = confirmReceipt;
	}
	public List<PdPhoneProduct> getPdPhoneProductList() {
		return PdPhoneProductList;
	}
	public void setPdPhoneProductList(List<PdPhoneProduct> pdPhoneProductList) {
		PdPhoneProductList = pdPhoneProductList;
	}

}
