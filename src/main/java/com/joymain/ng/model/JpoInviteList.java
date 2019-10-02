package com.joymain.ng.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Table(name="JPO_INVITE_LIST" )
@Entity
@XmlRootElement
public class JpoInviteList extends BaseObject implements Serializable {
    private Long id;
    private Long version;
    private String userCode;
    private String inviteCode;
    private String memberOrderNo;
    private Date createTime;
    private String status;
    private String useUserCode;
    private Date useTime;
    private String inviteType;
    
    
    @Column(name="INVITE_TYPE", length=1)
    public String getInviteType() {
		return inviteType;
	}

	public void setInviteType(String inviteType) {
		this.inviteType = inviteType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PO")
	@SequenceGenerator(name = "SEQ_PO", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="ID", unique=true, nullable=false)  
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    @Version
    @Column(name="VERSION", precision=22, scale=0)
    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="INVITE_CODE", unique=true, length=20)
    public String getInviteCode() {
        return this.inviteCode;
    }
    
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
    
    @Column(name="MEMBER_ORDER_NO", length=20)
    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="STATUS", length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="USE_USER_CODE", length=20)
    public String getUseUserCode() {
        return this.useUserCode;
    }
    
    public void setUseUserCode(String useUserCode) {
        this.useUserCode = useUserCode;
    }
   // @Temporal(TemporalType.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="USE_TIME", length=7)
    public Date getUseTime() {
        return this.useTime;
    }
    
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoInviteList pojo = (JpoInviteList) o;

        if (version != null ? !version.equals(pojo.version) : pojo.version != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (inviteCode != null ? !inviteCode.equals(pojo.inviteCode) : pojo.inviteCode != null) return false;
        if (memberOrderNo != null ? !memberOrderNo.equals(pojo.memberOrderNo) : pojo.memberOrderNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (useUserCode != null ? !useUserCode.equals(pojo.useUserCode) : pojo.useUserCode != null) return false;
        if (useTime != null ? !useTime.equals(pojo.useTime) : pojo.useTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (version != null ? version.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (inviteCode != null ? inviteCode.hashCode() : 0);
        result = 31 * result + (memberOrderNo != null ? memberOrderNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (useUserCode != null ? useUserCode.hashCode() : 0);
        result = 31 * result + (useTime != null ? useTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("version").append("='").append(getVersion()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("inviteCode").append("='").append(getInviteCode()).append("', ");
        sb.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("useUserCode").append("='").append(getUseUserCode()).append("', ");
        sb.append("useTime").append("='").append(getUseTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
