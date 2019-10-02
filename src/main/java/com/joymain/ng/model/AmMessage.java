package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="AM_MESSAGE")

@XmlRootElement
public class AmMessage extends BaseObject implements RowMapper<AmMessage>, Serializable {
    private Long uniNo;
    private Date issueTime;
    private Date replyTime;
    private String msgClassNo;
    private String subject;
    private String senderNo;
    private String agentNo;
    private String content;
    private String receiverNo;
    private String replyContent;
    private String checkUserNo;
    private Date checkMsgTime;
    private String companyCode;
    private Integer status;
    private String sendRoute;
    private String agentName;
    private String receiverName;
    private String senderName;
    private String discuss;
    private String isMobile;
    

	
//  @GeneratedValue(generator="system-uuid")
//  @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Id      
    @Column(name="UNI_NO", unique=true, nullable=false, length=13) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
   	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    @Column(name="ISSUE_TIME",  length=7)
    public Date getIssueTime() {
        return this.issueTime;
    }
    
    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    @Column(name="IS_MOBILE")
	public String getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	
    
    
    @Column(name="REPLY_TIME", length=7 )
    public Date getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    
    @Column(name="MSG_CLASS_NO", length=2)
    public String getMsgClassNo() {
        return this.msgClassNo;
    }
    
    public void setMsgClassNo(String msgClassNo) {
        this.msgClassNo = msgClassNo;
    }
    
    @Column(name="SUBJECT", length=250)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name="SENDER_NO", length=20)
    public String getSenderNo() {
        return this.senderNo;
    }
    
    public void setSenderNo(String senderNo) {
        this.senderNo = senderNo;
    }
    
    @Column(name="AGENT_NO", length=20)
    public String getAgentNo() {
        return this.agentNo;
    }
    
    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }
    
    @Column(name="CONTENT")
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="RECEIVER_NO", length=20)
    public String getReceiverNo() {
        return this.receiverNo;
    }
    
    public void setReceiverNo(String receiverNo) {
        this.receiverNo = receiverNo;
    }
    
    @Column(name="REPLY_CONTENT")
    public String getReplyContent() {
        return this.replyContent;
    }
    
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    
    @Column(name="CHECK_USER_NO", length=20)
    public String getCheckUserNo() {
        return this.checkUserNo;
    }
    
    public void setCheckUserNo(String checkUserNo) {
        this.checkUserNo = checkUserNo;
    }
    @Column(name="CHECK_MSG_TIME", length=7)
    public Date getCheckMsgTime() {
        return this.checkMsgTime;
    }
    
    public void setCheckMsgTime(Date checkMsgTime) {
        this.checkMsgTime = checkMsgTime;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="STATUS", precision=2, scale=0)
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="SEND_ROUTE", length=1)
    public String getSendRoute() {
        return this.sendRoute;
    }
    
    public void setSendRoute(String sendRoute) {
        this.sendRoute = sendRoute;
    }
    
    @Column(name="AGENT_NAME", length=300)
    public String getAgentName() {
        return this.agentName;
    }
    
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    
    @Column(name="RECEIVER_NAME", length=300)
    public String getReceiverName() {
        return this.receiverName;
    }
    
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    
    @Column(name="SENDER_NAME", length=300)
    public String getSenderName() {
        return this.senderName;
    }
    
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    
    @Column(name="DISCUSS", length=1)
    public String getDiscuss() {
        return this.discuss;
    }
    
    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmMessage pojo = (AmMessage) o;

        if (issueTime != null ? !issueTime.equals(pojo.issueTime) : pojo.issueTime != null) return false;
        if (replyTime != null ? !replyTime.equals(pojo.replyTime) : pojo.replyTime != null) return false;
        if (msgClassNo != null ? !msgClassNo.equals(pojo.msgClassNo) : pojo.msgClassNo != null) return false;
        if (subject != null ? !subject.equals(pojo.subject) : pojo.subject != null) return false;
        if (senderNo != null ? !senderNo.equals(pojo.senderNo) : pojo.senderNo != null) return false;
        if (agentNo != null ? !agentNo.equals(pojo.agentNo) : pojo.agentNo != null) return false;
        if (content != null ? !content.equals(pojo.content) : pojo.content != null) return false;
        if (receiverNo != null ? !receiverNo.equals(pojo.receiverNo) : pojo.receiverNo != null) return false;
        if (replyContent != null ? !replyContent.equals(pojo.replyContent) : pojo.replyContent != null) return false;
        if (checkUserNo != null ? !checkUserNo.equals(pojo.checkUserNo) : pojo.checkUserNo != null) return false;
        if (checkMsgTime != null ? !checkMsgTime.equals(pojo.checkMsgTime) : pojo.checkMsgTime != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (sendRoute != null ? !sendRoute.equals(pojo.sendRoute) : pojo.sendRoute != null) return false;
        if (agentName != null ? !agentName.equals(pojo.agentName) : pojo.agentName != null) return false;
        if (receiverName != null ? !receiverName.equals(pojo.receiverName) : pojo.receiverName != null) return false;
        if (senderName != null ? !senderName.equals(pojo.senderName) : pojo.senderName != null) return false;
        if (discuss != null ? !discuss.equals(pojo.discuss) : pojo.discuss != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (issueTime != null ? issueTime.hashCode() : 0);
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        result = 31 * result + (msgClassNo != null ? msgClassNo.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (senderNo != null ? senderNo.hashCode() : 0);
        result = 31 * result + (agentNo != null ? agentNo.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (receiverNo != null ? receiverNo.hashCode() : 0);
        result = 31 * result + (replyContent != null ? replyContent.hashCode() : 0);
        result = 31 * result + (checkUserNo != null ? checkUserNo.hashCode() : 0);
        result = 31 * result + (checkMsgTime != null ? checkMsgTime.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sendRoute != null ? sendRoute.hashCode() : 0);
        result = 31 * result + (agentName != null ? agentName.hashCode() : 0);
        result = 31 * result + (receiverName != null ? receiverName.hashCode() : 0);
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (discuss != null ? discuss.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("uniNo").append("='").append(getUniNo()).append("', ");
        sb.append("issueTime").append("='").append(getIssueTime()).append("', ");
        sb.append("replyTime").append("='").append(getReplyTime()).append("', ");
        sb.append("msgClassNo").append("='").append(getMsgClassNo()).append("', ");
        sb.append("subject").append("='").append(getSubject()).append("', ");
        sb.append("senderNo").append("='").append(getSenderNo()).append("', ");
        sb.append("agentNo").append("='").append(getAgentNo()).append("', ");
        sb.append("content").append("='").append(getContent()).append("', ");
        sb.append("receiverNo").append("='").append(getReceiverNo()).append("', ");
        sb.append("replyContent").append("='").append(getReplyContent()).append("', ");
        sb.append("checkUserNo").append("='").append(getCheckUserNo()).append("', ");
        sb.append("checkMsgTime").append("='").append(getCheckMsgTime()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("sendRoute").append("='").append(getSendRoute()).append("', ");
        sb.append("agentName").append("='").append(getAgentName()).append("', ");
        sb.append("receiverName").append("='").append(getReceiverName()).append("', ");
        sb.append("senderName").append("='").append(getSenderName()).append("', ");
        sb.append("discuss").append("='").append(getDiscuss()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

	@Override
	public AmMessage mapRow(ResultSet rs, int arg1) throws SQLException {
		AmMessage am = new AmMessage();
		
		am.setAgentName(rs.getString("AGENT_NAME"));
		am.setAgentNo(rs.getString("AGENT_NO"));
		am.setCheckMsgTime(rs.getDate("CHECK_MSG_TIME"));
		am.setCheckUserNo(rs.getString("CHECK_USER_NO"));
		am.setCompanyCode(rs.getString("COMPANY_CODE"));
		am.setContent(rs.getString("CONTENT"));
		am.setDiscuss(rs.getString("DISCUSS"));
		am.setIssueTime(rs.getDate("ISSUE_TIME"));
		am.setMsgClassNo(rs.getString("MSG_CLASS_NO"));
		am.setReceiverName(rs.getString("RECEIVER_NAME"));
		am.setReceiverNo(rs.getString("RECEIVER_NO"));
		am.setReplyContent(rs.getString("REPLY_CONTENT"));
		am.setReplyTime(rs.getDate("REPLY_TIME"));
		am.setSenderName(rs.getString("SENDER_NAME"));
		am.setSenderNo(rs.getString("SENDER_NO"));
		am.setSendRoute(rs.getString("SEND_ROUTE"));
		am.setStatus(rs.getInt("STATUS"));
		am.setSubject(rs.getString("SUBJECT"));
		am.setUniNo(rs.getLong("UNI_NO"));
		return am;
	}

}
