package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="JBD_CALC_DAY_FB")

@XmlRootElement
public class JbdCalcDayFb extends BaseObject implements Serializable  {
    private Long id;
    private Integer wweek;
    private String userCode;
    private String userName;
    private BigDecimal memberLevel;
    private BigDecimal fbMoney;
    private BigDecimal sendMoney;
    private BigDecimal deductVolume;
    private BigDecimal rax;
    private BigDecimal deductMoney;
    private BigDecimal freezeStatus;
    private Integer status;
    private Date sendDate;

    @Id
   	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bd")
   	@SequenceGenerator(name = "seq_bd", sequenceName = "SEQ_BD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)      
    public Long getId() {
    	return this.id;
    }
   
    public void setId(Long id) {
    	this.id = id;
    }
   
    @Column(name="W_WEEK", length=22)
    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

    @Column(name="USER_NAME", length=200)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
	@Column(name="MEMBER_LEVEL", length=22)
    public BigDecimal getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(BigDecimal memberLevel) {
        this.memberLevel = memberLevel;
    }
    
    @Column(name="FB_MONEY", length=22)
    public BigDecimal getFbMoney() {
        return this.fbMoney;
    }
    
    public void setFbMoney(BigDecimal fbMoney) {
        this.fbMoney = fbMoney;
    }
    
    @Column(name="SEND_MONEY", length=22)
    public BigDecimal getSendMoney() {
        return this.sendMoney;
    }
    
    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }
    
    @Column(name="DEDUCT_VOLUME", length=22)
    public BigDecimal getDeductVolume() {
        return this.deductVolume;
    }
    
    public void setDeductVolume(BigDecimal deductVolume) {
        this.deductVolume = deductVolume;
    }
   
    @Column(name="RAX", length=22)
    public BigDecimal getRax() {
        return this.rax;
    }
    
    public void setRax(BigDecimal rax) {
        this.rax = rax;
    }
   
    @Column(name="DEDUCT_MONEY", length=22)
    public BigDecimal getDeductMoney() {
        return this.deductMoney;
    }
    
    public void setDeductMoney(BigDecimal deductMoney) {
        this.deductMoney = deductMoney;
    }
   
    @Column(name="FREEZE_STATUS", length=22)
    public BigDecimal getFreezeStatus() {
        return this.freezeStatus;
    }
    
    public void setFreezeStatus(BigDecimal freezeStatus) {
        this.freezeStatus = freezeStatus;
    }
    
    @Column(name="STATUS", length=22)
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="SEND_DATE", length=7)
    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("memberLevel").append("='").append(getMemberLevel()).append("', ");
        sb.append("fbMoney").append("='").append(getFbMoney()).append("'");
        sb.append("sendMoney").append("='").append(getSendMoney()).append("', ");
        sb.append("deductVolume").append("='").append(getDeductVolume()).append("', ");
        sb.append("rax").append("='").append(getRax()).append("', ");
        sb.append("deductMoney").append("='").append(getDeductMoney()).append("', ");
        sb.append("freezeStatus").append("='").append(getFreezeStatus()).append("'");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("sendDate").append("='").append(getSendDate()).append("'");
        sb.append("]");
      
        return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdCalcDayFb pojo = (JbdCalcDayFb) o;

        if (wweek != null ? !wweek.equals(pojo.wweek) : pojo.wweek != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;
        if (memberLevel != null ? !memberLevel.equals(pojo.memberLevel) : pojo.memberLevel != null) return false;
        
        if (fbMoney != null ? !fbMoney.equals(pojo.fbMoney) : pojo.fbMoney != null) return false;
        if (sendMoney != null ? !sendMoney.equals(pojo.sendMoney) : pojo.sendMoney != null) return false;
        if (deductVolume != null ? !deductVolume.equals(pojo.deductVolume) : pojo.deductVolume != null) return false;
        if (rax != null ? !rax.equals(pojo.rax) : pojo.rax != null) return false;
        
        if (deductMoney != null ? !deductMoney.equals(pojo.deductMoney) : pojo.deductMoney != null) return false;
        if (freezeStatus != null ? !freezeStatus.equals(pojo.freezeStatus) : pojo.freezeStatus != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (sendDate != null ? !sendDate.equals(pojo.sendDate) : pojo.sendDate != null) return false;

        return true;
	}

	@Override
	public int hashCode() {
		int result = 0;
        result = (wweek != null ? wweek.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (memberLevel != null ? memberLevel.hashCode() : 0);
        result = 31 * result + (fbMoney != null ? fbMoney.hashCode() : 0);
        result = 31 * result + (sendMoney != null ? sendMoney.hashCode() : 0);
        result = 31 * result + (deductVolume != null ? deductVolume.hashCode() : 0);
        result = 31 * result + (rax != null ? rax.hashCode() : 0);
        result = 31 * result + (deductMoney != null ? deductMoney.hashCode() : 0);
        result = 31 * result + (freezeStatus != null ? freezeStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);

        return result;
	}

}
