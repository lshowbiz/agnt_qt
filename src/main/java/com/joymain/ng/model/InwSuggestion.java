package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

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

import java.io.Serializable;

@Entity
@Table(name="INW_SUGGESTION")

@XmlRootElement
public class InwSuggestion extends BaseObject implements Serializable {
    private Long id;
    private String demandId;
    private String subject;
    private Date createTime;
    private Date replyTime;
    private String replyTontent;
    private String userCode;
    private String replyUserCode;
    private String proposedAdoption;
    //content字段的数量类型发生了改变
    private String content;
    //下面是创新共赢需求表新添加的字段
    private String suggestionAccept;
    private String suggestionSort;
    private String initialAudit;
    private String feasibilityAudit;
    private String status;
    private String phone;
    private String address;
    private String demandsortId;
    //又补加的字段
    private String integration;
    //2013-11-18新加的字段
    private String microMessage;
    private String qq;

    //2014-05-12新加的字段
    //reply_content_second 建议回复(项目建议初次审核回复或第二次回复)
    private String replyContentSecond;
    //reply_content_third 建议回复(项目建议可行性审核回复或第三次回复)
    private String replyContentThird;
    //REPLY_USER_CODE_SECOND VARCHAR2(20)  建议回复(项目建议初次审核回复或第二次回复)的回复人   
    private String replyUserCodeSecond;
    //REPLY_TIME_SECOND      DATE          建议回复(项目建议初次审核回复或第二次回复)的回复时间    
    private Date replyTimeSecond;
    //REPLY_USER_CODE_THIRD  VARCHAR2(20)  建议回复(项目建议可行性审核回复或第三次回复)的回复人     
    private String replyUserCodeThird;
    //REPLY_TIME_THIRD       DATE          建议回复(项目建议可行性审核回复或第三次回复)的回复时间
    private Date replyTimeThird;
    //2014-05-19 新加的字段 add by gw 
    //REPLY_YES_NO           VARCHAR2(2)   是否有建议回复(Y表示有新的建议回复,N或空表示没有新的建议回复)   
    private String replyYesNo;
 
    
    //2014-06-30新加的字段   对建议的回复内容进行多次审核的相关字段   add by gw 
    private String firstReplyAudit;
    private String firseReplyPerson;
    private Date firstReplyTime;
    
    private String secondReplyAudit;
    private String secondReplyPerson;
    private Date secondReplyTime;
    
    private String thirdReplyAudit;
    private String thirdReplyPerson;
    private Date thirdReplyTime;     
    //2014-06-30新加的字段   对建议的回复内容进行多次审核的相关字段   add by gw  
    
    //UPDATE_FLAG 2014-07-15新加的字段  如果对建议进行回复或对建议内容有审核的情况下  add by gw 
    private String updateFlag;
    
    //2014-08-19 添加字段
    private Date initialAuditTime;
    private String initialAuditUser;
    
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="DEMAND_ID", length=13)
    public String getDemandId() {
        return this.demandId;
    }
    
    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }
    
    @Column(name="SUBJECT", length=100)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="REPLY_TIME", length=7)
    public Date getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    
    @Column(name="REPLY_TONTENT", length=1000)
    public String getReplyTontent() {
        return this.replyTontent;
    }
    
    public void setReplyTontent(String replyTontent) {
        this.replyTontent = replyTontent;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="REPLY_USER_CODE", length=20)
    public String getReplyUserCode() {
        return this.replyUserCode;
    }
    
    public void setReplyUserCode(String replyUserCode) {
        this.replyUserCode = replyUserCode;
    }
    
    @Column(name="PROPOSED_ADOPTION", length=2)
    public String getProposedAdoption() {
        return this.proposedAdoption;
    }
    
    public void setProposedAdoption(String proposedAdoption) {
        this.proposedAdoption = proposedAdoption;
    }
    
    @Column(name="CONTENT")
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="SUGGESTION_ACCEPT", length=2)
    public String getSuggestionAccept() {
        return this.suggestionAccept;
    }
    
    public void setSuggestionAccept(String suggestionAccept) {
        this.suggestionAccept = suggestionAccept;
    }
    
    @Column(name="SUGGESTION_SORT", length=2)
    public String getSuggestionSort() {
        return this.suggestionSort;
    }
    
    public void setSuggestionSort(String suggestionSort) {
        this.suggestionSort = suggestionSort;
    }
    
    @Column(name="INITIAL_AUDIT", length=2)
    public String getInitialAudit() {
        return this.initialAudit;
    }
    
    public void setInitialAudit(String initialAudit) {
        this.initialAudit = initialAudit;
    }
    
    @Column(name="FEASIBILITY_AUDIT", length=2)
    public String getFeasibilityAudit() {
        return this.feasibilityAudit;
    }
    
    public void setFeasibilityAudit(String feasibilityAudit) {
        this.feasibilityAudit = feasibilityAudit;
    }
    
    @Column(name="STATUS", length=2)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="PHONE", length=30)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="ADDRESS", length=200)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="DEMANDSORT_ID", length=32)
    public String getDemandsortId() {
        return this.demandsortId;
    }
    
    public void setDemandsortId(String demandsortId) {
        this.demandsortId = demandsortId;
    }

    @Column(name="INTEGRATION", length=5)
    public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}

    @Column(name="MICRO_MESSAGE", length=80)
	public String getMicroMessage() {
		return microMessage;
	}

	public void setMicroMessage(String microMessage) {
		this.microMessage = microMessage;
	}

    @Column(name="QQ", length=11)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
    @Column(name="REPLY_CONTENT_SECOND", length=1000)
	public String getReplyContentSecond() {
		return replyContentSecond;
	}

	public void setReplyContentSecond(String replyContentSecond) {
		this.replyContentSecond = replyContentSecond;
	}

    @Column(name="REPLY_CONTENT_THIRD", length=1000)
	public String getReplyContentThird() {
		return replyContentThird;
	}

	public void setReplyContentThird(String replyContentThird) {
		this.replyContentThird = replyContentThird;
	}

    @Column(name="REPLY_USER_CODE_SECOND", length=20)
	public String getReplyUserCodeSecond() {
		return replyUserCodeSecond;
	}

	public void setReplyUserCodeSecond(String replyUserCodeSecond) {
		this.replyUserCodeSecond = replyUserCodeSecond;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REPLY_TIME_SECOND", length=7)
	public Date getReplyTimeSecond() {
		return replyTimeSecond;
	}

	public void setReplyTimeSecond(Date replyTimeSecond) {
		this.replyTimeSecond = replyTimeSecond;
	}

    @Column(name="REPLY_USER_CODE_THIRD", length=20)
	public String getReplyUserCodeThird() {
		return replyUserCodeThird;
	}

	public void setReplyUserCodeThird(String replyUserCodeThird) {
		this.replyUserCodeThird = replyUserCodeThird;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REPLY_TIME_THIRD", length=7)
	public Date getReplyTimeThird() {
		return replyTimeThird;
	}

	public void setReplyTimeThird(Date replyTimeThird) {
		this.replyTimeThird = replyTimeThird;
	}

    @Column(name="REPLY_YES_NO", length=2)
	public String getReplyYesNo() {
		return replyYesNo;
	}

	public void setReplyYesNo(String replyYesNo) {
		this.replyYesNo = replyYesNo;
	}
	
    @Column(name="FIRST_REPLY_AUDIT", length=2)
	public String getFirstReplyAudit() {
		return firstReplyAudit;
	}

	public void setFirstReplyAudit(String firstReplyAudit) {
		this.firstReplyAudit = firstReplyAudit;
	}

    @Column(name="FIRST_REPLY_PERSON", length=20)
	public String getFirseReplyPerson() {
		return firseReplyPerson;
	}

	public void setFirseReplyPerson(String firseReplyPerson) {
		this.firseReplyPerson = firseReplyPerson;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FIRST_REPLY_TIME", length=7)
	public Date getFirstReplyTime() {
		return firstReplyTime;
	}

	public void setFirstReplyTime(Date firstReplyTime) {
		this.firstReplyTime = firstReplyTime;
	}

    @Column(name="SECOND_REPLY_AUDIT", length=2)
	public String getSecondReplyAudit() {
		return secondReplyAudit;
	}

	public void setSecondReplyAudit(String secondReplyAudit) {
		this.secondReplyAudit = secondReplyAudit;
	}

    @Column(name="SECOND_REPLY_PERSON", length=20)
	public String getSecondReplyPerson() {
		return secondReplyPerson;
	}

	public void setSecondReplyPerson(String secondReplyPerson) {
		this.secondReplyPerson = secondReplyPerson;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SECOND_REPLY_TIME", length=7)
	public Date getSecondReplyTime() {
		return secondReplyTime;
	}

	public void setSecondReplyTime(Date secondReplyTime) {
		this.secondReplyTime = secondReplyTime;
	}

    @Column(name="THIRD_REPLY_AUDIT", length=2)
	public String getThirdReplyAudit() {
		return thirdReplyAudit;
	}

	public void setThirdReplyAudit(String thirdReplyAudit) {
		this.thirdReplyAudit = thirdReplyAudit;
	}

    @Column(name="THIRD_REPLY_PERSON", length=20)
	public String getThirdReplyPerson() {
		return thirdReplyPerson;
	}

	public void setThirdReplyPerson(String thirdReplyPerson) {
		this.thirdReplyPerson = thirdReplyPerson;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="THIRD_REPLY_TIME", length=7)
	public Date getThirdReplyTime() {
		return thirdReplyTime;
	}

	public void setThirdReplyTime(Date thirdReplyTime) {
		this.thirdReplyTime = thirdReplyTime;
	}
	
	@Column(name="UPDATE_FLAG", length=2)
	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INITIAL_AUDIT_TIME", length=7)
	public Date getInitialAuditTime() {
		return initialAuditTime;
	}

	public void setInitialAuditTime(Date initialAuditTime) {
		this.initialAuditTime = initialAuditTime;
	}
	
	
	@Column(name="INITIAL_AUDIT_USER", length=30)
	public String getInitialAuditUser() {
		return initialAuditUser;
	}

	public void setInitialAuditUser(String initialAuditUser) {
		this.initialAuditUser = initialAuditUser;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InwSuggestion pojo = (InwSuggestion) o;

        if (demandId != null ? !demandId.equals(pojo.demandId) : pojo.demandId != null) return false;
        if (subject != null ? !subject.equals(pojo.subject) : pojo.subject != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (replyTime != null ? !replyTime.equals(pojo.replyTime) : pojo.replyTime != null) return false;
        if (replyTontent != null ? !replyTontent.equals(pojo.replyTontent) : pojo.replyTontent != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (replyUserCode != null ? !replyUserCode.equals(pojo.replyUserCode) : pojo.replyUserCode != null) return false;
        if (proposedAdoption != null ? !proposedAdoption.equals(pojo.proposedAdoption) : pojo.proposedAdoption != null) return false;
        if (content != null ? !content.equals(pojo.content) : pojo.content != null) return false;
        if (suggestionAccept != null ? !suggestionAccept.equals(pojo.suggestionAccept) : pojo.suggestionAccept != null) return false;
        if (suggestionSort != null ? !suggestionSort.equals(pojo.suggestionSort) : pojo.suggestionSort != null) return false;
        if (initialAudit != null ? !initialAudit.equals(pojo.initialAudit) : pojo.initialAudit != null) return false;
        if (feasibilityAudit != null ? !feasibilityAudit.equals(pojo.feasibilityAudit) : pojo.feasibilityAudit != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (demandsortId != null ? !demandsortId.equals(pojo.demandsortId) : pojo.demandsortId != null) return false;
        if (integration != null ? !integration.equals(pojo.integration) : pojo.integration != null) return false;
        if (microMessage != null ? !microMessage.equals(pojo.microMessage) : pojo.microMessage != null) return false;
        if (qq != null ? !qq.equals(pojo.qq) : pojo.qq != null) return false;
        if (replyContentSecond != null ? !replyContentSecond.equals(pojo.replyContentSecond) : pojo.replyContentSecond != null) return false;
        if (replyContentThird != null ? !replyContentThird.equals(pojo.replyContentThird) : pojo.replyContentThird != null) return false;
        if (replyUserCodeSecond != null ? !replyUserCodeSecond.equals(pojo.replyUserCodeSecond) : pojo.replyUserCodeSecond != null) return false;
        if (replyTimeSecond != null ? !replyTimeSecond.equals(pojo.replyTimeSecond) : pojo.replyTimeSecond != null) return false;
        if (replyUserCodeThird != null ? !replyUserCodeThird.equals(pojo.replyUserCodeThird) : pojo.replyUserCodeThird != null) return false;
        if (replyTimeThird != null ? !replyTimeThird.equals(pojo.replyTimeThird) : pojo.replyTimeThird != null) return false;
        if (replyYesNo != null ? !replyYesNo.equals(pojo.replyYesNo) : pojo.replyYesNo != null) return false;
        
        if (firstReplyAudit != null ? !firstReplyAudit.equals(pojo.firstReplyAudit) : pojo.firstReplyAudit != null) return false;
        if (firseReplyPerson != null ? !firseReplyPerson.equals(pojo.firseReplyPerson) : pojo.firseReplyPerson != null) return false;
        if (firstReplyTime != null ? !firstReplyTime.equals(pojo.firstReplyTime) : pojo.firstReplyTime != null) return false;
        if (secondReplyAudit != null ? !secondReplyAudit.equals(pojo.secondReplyAudit) : pojo.secondReplyAudit != null) return false;
        if (secondReplyPerson != null ? !secondReplyPerson.equals(pojo.secondReplyPerson) : pojo.secondReplyPerson != null) return false;
        if (secondReplyTime != null ? !secondReplyTime.equals(pojo.secondReplyTime) : pojo.secondReplyTime != null) return false;
        if (thirdReplyAudit != null ? !thirdReplyAudit.equals(pojo.thirdReplyAudit) : pojo.thirdReplyAudit != null) return false;
        if (thirdReplyPerson != null ? !thirdReplyPerson.equals(pojo.thirdReplyPerson) : pojo.thirdReplyPerson != null) return false;
        if (thirdReplyTime != null ? !thirdReplyTime.equals(pojo.thirdReplyTime) : pojo.thirdReplyTime != null) return false;

        if (updateFlag != null ? !updateFlag.equals(pojo.updateFlag) : pojo.updateFlag != null) return false;
        if (initialAuditTime != null ? !initialAuditTime.equals(pojo.initialAuditTime) : pojo.initialAuditTime != null) return false;
        if (initialAuditUser != null ? !initialAuditUser.equals(pojo.initialAuditUser) : pojo.initialAuditUser != null) return false;



        
        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (demandId != null ? demandId.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        result = 31 * result + (replyTontent != null ? replyTontent.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (replyUserCode != null ? replyUserCode.hashCode() : 0);
        result = 31 * result + (proposedAdoption != null ? proposedAdoption.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (suggestionAccept != null ? suggestionAccept.hashCode() : 0);
        result = 31 * result + (suggestionSort != null ? suggestionSort.hashCode() : 0);
        result = 31 * result + (initialAudit != null ? initialAudit.hashCode() : 0);
        result = 31 * result + (feasibilityAudit != null ? feasibilityAudit.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (demandsortId != null ? demandsortId.hashCode() : 0);
        result = 31 * result + (integration != null ? integration.hashCode() : 0);
        result = 31 * result + (microMessage != null ? microMessage.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (replyContentSecond != null ? replyContentSecond.hashCode() : 0);
        result = 31 * result + (replyContentThird != null ? replyContentThird.hashCode() : 0);
        result = 31 * result + (replyUserCodeSecond != null ? replyUserCodeSecond.hashCode() : 0);
        result = 31 * result + (replyTimeSecond != null ? replyTimeSecond.hashCode() : 0);
        result = 31 * result + (replyUserCodeThird != null ? replyUserCodeThird.hashCode() : 0);
        result = 31 * result + (replyTimeThird != null ? replyTimeThird.hashCode() : 0);
        result = 31 * result + (replyYesNo != null ? replyYesNo.hashCode() : 0);
        
        result = 31 * result + (firstReplyAudit != null ? firstReplyAudit.hashCode() : 0);
        result = 31 * result + (firseReplyPerson != null ? firseReplyPerson.hashCode() : 0);
        result = 31 * result + (firstReplyTime != null ? firstReplyTime.hashCode() : 0);
        result = 31 * result + (secondReplyAudit != null ? secondReplyAudit.hashCode() : 0);
        result = 31 * result + (secondReplyPerson != null ? secondReplyPerson.hashCode() : 0);
        result = 31 * result + (secondReplyTime != null ? secondReplyTime.hashCode() : 0);
        result = 31 * result + (thirdReplyAudit != null ? thirdReplyAudit.hashCode() : 0);
        result = 31 * result + (thirdReplyPerson != null ? thirdReplyPerson.hashCode() : 0);
        result = 31 * result + (thirdReplyTime != null ? thirdReplyTime.hashCode() : 0);

        result = 31 * result + (updateFlag != null ? updateFlag.hashCode() : 0);
        result = 31 * result + (initialAuditTime != null ? initialAuditTime.hashCode() : 0);
        result = 31 * result + (initialAuditUser != null ? initialAuditUser.hashCode() : 0);
   

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("demandId").append("='").append(getDemandId()).append("', ");
        sb.append("subject").append("='").append(getSubject()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("replyTime").append("='").append(getReplyTime()).append("', ");
        sb.append("replyTontent").append("='").append(getReplyTontent()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("replyUserCode").append("='").append(getReplyUserCode()).append("', ");
        sb.append("proposedAdoption").append("='").append(getProposedAdoption()).append("', ");
        sb.append("content").append("='").append(getContent()).append("', ");
        sb.append("suggestionAccept").append("='").append(getSuggestionAccept()).append("', ");
        sb.append("suggestionSort").append("='").append(getSuggestionSort()).append("', ");
        sb.append("initialAudit").append("='").append(getInitialAudit()).append("', ");
        sb.append("feasibilityAudit").append("='").append(getFeasibilityAudit()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("demandsortId").append("='").append(getDemandsortId()).append("'");
        sb.append("integration").append("='").append(getIntegration()).append("'");
        sb.append("microMessage").append("='").append(getMicroMessage()).append("'");
        sb.append("qq").append("='").append(getQq()).append("'");
        sb.append("replyContentSecond").append("='").append(getReplyContentSecond()).append("'");
        sb.append("replyContentThird").append("='").append(getReplyContentThird()).append("'");
        sb.append("replyUserCodeSecond").append("='").append(getReplyUserCodeSecond()).append("'");
        sb.append("replyTimeSecond").append("='").append(getReplyTimeSecond()).append("'");
        sb.append("replyUserCodeThird").append("='").append(getReplyUserCodeThird()).append("'");
        sb.append("replyTimeThird").append("='").append(getReplyTimeThird()).append("'");
        sb.append("replyYesNo").append("='").append(getReplyYesNo()).append("'");
        
        sb.append("firstReplyAudit").append("='").append(getFirstReplyAudit()).append("'");
        sb.append("firseReplyPerson").append("='").append(getFirseReplyPerson()).append("'");
        sb.append("firstReplyTime").append("='").append(getFirstReplyTime()).append("'");
        sb.append("secondReplyAudit").append("='").append(getSecondReplyAudit()).append("'");
        sb.append("secondReplyPerson").append("='").append(getSecondReplyPerson()).append("'");
        sb.append("secondReplyTime").append("='").append(getSecondReplyTime()).append("'");
        sb.append("thirdReplyAudit").append("='").append(getThirdReplyAudit()).append("'");
        sb.append("thirdReplyPerson").append("='").append(getThirdReplyPerson()).append("'");
        sb.append("thirdReplyTime").append("='").append(getThirdReplyTime()).append("'");

        sb.append("updateFlag").append("='").append(getUpdateFlag()).append("'");
        sb.append("initialAuditTime").append("='").append(getInitialAuditTime()).append("'");
        sb.append("initialAuditUser").append("='").append(getInitialAuditUser()).append("'");


        sb.append("]");
      
        return sb.toString();
    }

}
