package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

/**
 * 记录短信内容实体对象，和表无关联
 * @author www
 *
 */
public class JpmSmssendInfo implements Serializable {
	private Long smsId;
	private String smsType;//短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
	private String smsRecipient;//短信接收人
	private String smsMessage;//短信内容
	private Date smsTime;//发送时间
	private String smsOperator;//操作人
	private String smsStatus;//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
	private String remark;//备注
	private String phoneNum;//接收短信的手机号码
	
	public Long getSmsId() {
		return smsId;
	}
	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getSmsRecipient() {
		return smsRecipient;
	}
	public void setSmsRecipient(String smsRecipient) {
		this.smsRecipient = smsRecipient;
	}
	public String getSmsMessage() {
		return smsMessage;
	}
	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}
	public Date getSmsTime() {
		return smsTime;
	}
	public void setSmsTime(Date smsTime) {
		this.smsTime = smsTime;
	}
	public String getSmsOperator() {
		return smsOperator;
	}
	public void setSmsOperator(String smsOperator) {
		this.smsOperator = smsOperator;
	}
	public String getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
