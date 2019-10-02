package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="AM_ANNOUNCE")

@XmlRootElement
public class AmAnnounce extends BaseObject implements RowMapper<AmAnnounce>, Serializable {
   
	private static final long serialVersionUID = 1L;
	private String aaNo;
    private String companyCode;
    private String subject;
    private String issueUserNo;
    private Date createTime;
    private String checkUserNo;
    private Date checkTime;
    private Date startTime;
    private Date endTime;
    private boolean status;
    private String content;
    
    private String issuerName;
    private String checkerName;
    private String annoClassNo;
    private Boolean highlight;
    private Set<AmAnnounceRecord> amAnnounceRecords;
    private boolean already;
    
    private String createTimeStr;
    @Transient
    public String getCreateTimeStr() {
    	if(null!=createTime){
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		createTimeStr =dateFormat.format(createTime);
    	}
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Id      
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    @Column(name="AA_NO", unique=true, nullable=false, length=13)    
    public String getAaNo() {
        return this.aaNo;
    }
    
    public void setAaNo(String aaNo) {
        this.aaNo = aaNo;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="SUBJECT", nullable=false, length=250)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name="ISSUE_USER_NO", nullable=false, length=20)
    public String getIssueUserNo() {
        return this.issueUserNo;
    }
    
    public void setIssueUserNo(String issueUserNo) {
        this.issueUserNo = issueUserNo;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CHECK_USER_NO", length=20)
    public String getCheckUserNo() {
        return this.checkUserNo;
    }
    
    public void setCheckUserNo(String checkUserNo) {
        this.checkUserNo = checkUserNo;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CHECK_TIME", length=7)
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="START_TIME", length=7)
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="END_TIME", length=7)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Column(name="STATUS", nullable=false, precision=1, scale=0)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Lob 
    @Basic(fetch = FetchType.EAGER) 
    @Column(name="CONTENT", columnDefinition="CLOB")  
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="ISSUER_NAME", length=300)
    public String getIssuerName() {
        return this.issuerName;
    }
    
    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }
    
    @Column(name="CHECKER_NAME", length=300)
    public String getCheckerName() {
        return this.checkerName;
    }
    
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    
    @Column(name="ANNO_CLASS_NO", nullable=false, length=2)
    public String getAnnoClassNo() {
        return this.annoClassNo;
    }
    
    public void setAnnoClassNo(String annoClassNo) {
        this.annoClassNo = annoClassNo;
    }
    
    @Column(name="HIGHLIGHT", length=1)
    public Boolean getHighlight() {
        return this.highlight;
    }
    
    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }
    
    @OneToMany(mappedBy="amAnnounce",fetch=FetchType.LAZY)
    @JsonIgnore
    public Set<AmAnnounceRecord> getAmAnnounceRecords() {
		return amAnnounceRecords;
	}

	public void setAmAnnounceRecords(Set<AmAnnounceRecord> amAnnounceRecords) {
		this.amAnnounceRecords = amAnnounceRecords;
	}
	
	@Transient
	public boolean isAlready() {
		return already;
	}

	public void setAlready(boolean already) {
		this.already = already;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmAnnounce pojo = (AmAnnounce) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (subject != null ? !subject.equals(pojo.subject) : pojo.subject != null) return false;
        if (issueUserNo != null ? !issueUserNo.equals(pojo.issueUserNo) : pojo.issueUserNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (checkUserNo != null ? !checkUserNo.equals(pojo.checkUserNo) : pojo.checkUserNo != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (status != pojo.status) return false;
        if (content != null ? !content.equals(pojo.content) : pojo.content != null) return false;
        if (issuerName != null ? !issuerName.equals(pojo.issuerName) : pojo.issuerName != null) return false;
        if (checkerName != null ? !checkerName.equals(pojo.checkerName) : pojo.checkerName != null) return false;
        if (annoClassNo != null ? !annoClassNo.equals(pojo.annoClassNo) : pojo.annoClassNo != null) return false;
        if (highlight != null ? !highlight.equals(pojo.highlight) : pojo.highlight != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (issueUserNo != null ? issueUserNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (checkUserNo != null ? checkUserNo.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (issuerName != null ? issuerName.hashCode() : 0);
        result = 31 * result + (checkerName != null ? checkerName.hashCode() : 0);
        result = 31 * result + (annoClassNo != null ? annoClassNo.hashCode() : 0);
        result = 31 * result + (highlight != null ? highlight.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("aaNo").append("='").append(getAaNo()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("subject").append("='").append(getSubject()).append("', ");
        sb.append("issueUserNo").append("='").append(getIssueUserNo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("checkUserNo").append("='").append(getCheckUserNo()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("status").append("='").append(isStatus()).append("', ");
        sb.append("content").append("='").append(getContent()).append("', ");
        sb.append("issuerName").append("='").append(getIssuerName()).append("', ");
        sb.append("checkerName").append("='").append(getCheckerName()).append("', ");
        sb.append("annoClassNo").append("='").append(getAnnoClassNo()).append("', ");
        sb.append("highlight").append("='").append(getHighlight()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

	@Override
	public AmAnnounce mapRow(ResultSet rs, int arg1) throws SQLException {
		
		AmAnnounce an = new AmAnnounce();
		an.setAaNo(rs.getString("AA_NO"));
		an.setAnnoClassNo(rs.getString("ANNO_CLASS_NO"));
		an.setCheckerName(rs.getString("CHECKER_NAME"));
		an.setCheckTime(rs.getDate("CHECK_TIME"));
		an.setCheckUserNo(rs.getString("CHECK_USER_NO"));
		an.setCompanyCode(rs.getString("COMPANY_CODE"));
		an.setContent(rs.getString("COMPANY_CODE"));
		an.setCreateTime(rs.getDate("CREATE_TIME"));
		an.setEndTime(rs.getDate("END_TIME"));
		an.setHighlight(rs.getBoolean("HIGHLIGHT"));
		an.setIssuerName(rs.getString("ISSUER_NAME"));
		an.setIssueUserNo(rs.getString("ISSUE_USER_NO"));
		an.setStartTime(rs.getDate("START_TIME"));
		an.setStatus(rs.getBoolean("STATUS"));
		an.setSubject(rs.getString("SUBJECT"));
		
		return an;
	}

}
