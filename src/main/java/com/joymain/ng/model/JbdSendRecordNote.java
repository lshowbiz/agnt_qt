package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="JBD_SEND_RECORD_NOTE")

@XmlRootElement
public class JbdSendRecordNote extends BaseObject implements Serializable {
    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String companyCode;
    private String userCode;
    private String recommendNo;
    private String linkNo;
    private String name;
    private String petName;
    private String cardType;
    private String bank;
    private String bankaddress;
    private String bankbook;
    private String bankcard;
    private Date exitDate;
    private String sendLateCause;
    private String sendLateRemark;
    private BigDecimal remittanceMoney;
    private String remittanceBNum;
    private String operaterCode;
    private Date operaterTime;
    private Date sendDate;
    private String registerStatus;
    private String reissueStatus;
    private Date supplyTime;
    private String sendTrace;
    private String sendRemark;
    private BigDecimal sendMoney;
    private String active;
    private Integer status;
    private String memberType;
    private Integer startWeek;
    private Integer validWeek;
    private Integer freezeStatus;
    private Integer beforeFreezeStatus;
    private BigDecimal totalDev;
    private BigDecimal deductedDev;
    private BigDecimal currentDev;
    private BigDecimal leaderDev;
    private BigDecimal leaderDevPv;
    private String sendStatusDev;
    private Date sendDateDev;
    private String sendUserDev;
    private Integer memberLevel;

    @Id      @Column(name="ID", unique=true, nullable=false, precision=18, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    

	@Column(name="W_YEAR", precision=8, scale=0)
    public Integer getWyear() {
		return wyear;
	}

	public void setWyear(Integer wyear) {
		this.wyear = wyear;
	}

    @Column(name="W_MONTH", precision=8, scale=0)
	public Integer getWmonth() {
		return wmonth;
	}

	public void setWmonth(Integer wmonth) {
		this.wmonth = wmonth;
	}

    @Column(name="W_WEEK", precision=8, scale=0)
	public Integer getWweek() {
		return wweek;
	}

	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}

 
    
  
    
 
    @Column(name="COMPANY_CODE", length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="RECOMMEND_NO", length=20)
    public String getRecommendNo() {
        return this.recommendNo;
    }
    
    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }
    
    @Column(name="LINK_NO", length=20)
    public String getLinkNo() {
        return this.linkNo;
    }
    
    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }
    
    @Column(name="NAME", length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="PET_NAME", length=200)
    public String getPetName() {
        return this.petName;
    }
    
    public void setPetName(String petName) {
        this.petName = petName;
    }
    
    @Column(name="CARD_TYPE", length=1)
    public String getCardType() {
        return this.cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    @Column(name="BANK", length=200)
    public String getBank() {
        return this.bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }
    
    @Column(name="BANKADDRESS", length=200)
    public String getBankaddress() {
        return this.bankaddress;
    }
    
    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }
    
    @Column(name="BANKBOOK", length=200)
    public String getBankbook() {
        return this.bankbook;
    }
    
    public void setBankbook(String bankbook) {
        this.bankbook = bankbook;
    }
    
    @Column(name="BANKCARD", length=200)
    public String getBankcard() {
        return this.bankcard;
    }
    
    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }
    
    @Column(name="EXIT_DATE", length=7)
    public Date getExitDate() {
        return this.exitDate;
    }
    
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }
    
    @Column(name="SEND_LATE_CAUSE", length=4000)
    public String getSendLateCause() {
        return this.sendLateCause;
    }
    
    public void setSendLateCause(String sendLateCause) {
        this.sendLateCause = sendLateCause;
    }
    
    @Column(name="SEND_LATE_REMARK", length=4000)
    public String getSendLateRemark() {
        return this.sendLateRemark;
    }
    
    public void setSendLateRemark(String sendLateRemark) {
        this.sendLateRemark = sendLateRemark;
    }
    
    @Column(name="REMITTANCE_MONEY", precision=22, scale=0)
    public BigDecimal getRemittanceMoney() {
        return this.remittanceMoney;
    }
    
    public void setRemittanceMoney(BigDecimal remittanceMoney) {
        this.remittanceMoney = remittanceMoney;
    }
    
    @Column(name="REMITTANCE_B_NUM", length=100)
    public String getRemittanceBNum() {
        return this.remittanceBNum;
    }
    
    public void setRemittanceBNum(String remittanceBNum) {
        this.remittanceBNum = remittanceBNum;
    }
    
    @Column(name="OPERATER_CODE", length=100)
    public String getOperaterCode() {
        return this.operaterCode;
    }
    
    public void setOperaterCode(String operaterCode) {
        this.operaterCode = operaterCode;
    }
    
    @Column(name="OPERATER_TIME", length=7)
    public Date getOperaterTime() {
        return this.operaterTime;
    }
    
    public void setOperaterTime(Date operaterTime) {
        this.operaterTime = operaterTime;
    }
    
    @Column(name="SEND_DATE", length=7)
    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
    @Column(name="REGISTER_STATUS", length=1)
    public String getRegisterStatus() {
        return this.registerStatus;
    }
    
    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }
    
    @Column(name="REISSUE_STATUS", length=1)
    public String getReissueStatus() {
        return this.reissueStatus;
    }
    
    public void setReissueStatus(String reissueStatus) {
        this.reissueStatus = reissueStatus;
    }
    
    @Column(name="SUPPLY_TIME", length=7)
    public Date getSupplyTime() {
        return this.supplyTime;
    }
    
    public void setSupplyTime(Date supplyTime) {
        this.supplyTime = supplyTime;
    }
    
    @Column(name="SEND_TRACE", length=4000)
    public String getSendTrace() {
        return this.sendTrace;
    }
    
    public void setSendTrace(String sendTrace) {
        this.sendTrace = sendTrace;
    }
    
    @Column(name="SEND_REMARK", length=4000)
    public String getSendRemark() {
        return this.sendRemark;
    }
    
    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }
    
    @Column(name="SEND_MONEY")
    public BigDecimal getSendMoney() {
        return this.sendMoney;
    }
    
    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }
    
    @Column(name="ACTIVE", length=1)
    public String getActive() {
        return this.active;
    }
    
    public void setActive(String active) {
        this.active = active;
    }
    
    @Column(name="STATUS")
    public Integer isStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="MEMBER_TYPE", length=2)
    public String getMemberType() {
        return this.memberType;
    }
    
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    
    @Column(name="START_WEEK", precision=22, scale=0)
    public Integer getStartWeek() {
        return this.startWeek;
    }
    
    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }
    
    @Column(name="VALID_WEEK", precision=22, scale=0)
    public Integer getValidWeek() {
        return this.validWeek;
    }
    
    public void setValidWeek(Integer validWeek) {
        this.validWeek = validWeek;
    }
    
    @Column(name="FREEZE_STATUS", precision=22, scale=0)
    public Integer getFreezeStatus() {
        return this.freezeStatus;
    }
    
    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }
    
    @Column(name="BEFORE_FREEZE_STATUS", precision=22, scale=0)
    public Integer getBeforeFreezeStatus() {
        return this.beforeFreezeStatus;
    }
    
    public void setBeforeFreezeStatus(Integer beforeFreezeStatus) {
        this.beforeFreezeStatus = beforeFreezeStatus;
    }
    
    @Column(name="TOTAL_DEV", precision=22, scale=0)
    public BigDecimal getTotalDev() {
        return this.totalDev;
    }
    
    public void setTotalDev(BigDecimal totalDev) {
        this.totalDev = totalDev;
    }
    
    @Column(name="DEDUCTED_DEV", precision=22, scale=0)
    public BigDecimal getDeductedDev() {
        return this.deductedDev;
    }
    
    public void setDeductedDev(BigDecimal deductedDev) {
        this.deductedDev = deductedDev;
    }
    
    @Column(name="CURRENT_DEV", precision=22, scale=0)
    public BigDecimal getCurrentDev() {
        return this.currentDev;
    }
    
    public void setCurrentDev(BigDecimal currentDev) {
        this.currentDev = currentDev;
    }
    
    @Column(name="LEADER_DEV", precision=22, scale=0)
    public BigDecimal getLeaderDev() {
        return this.leaderDev;
    }
    
    public void setLeaderDev(BigDecimal leaderDev) {
        this.leaderDev = leaderDev;
    }
    
    @Column(name="LEADER_DEV_PV")
    public BigDecimal getLeaderDevPv() {
        return this.leaderDevPv;
    }
    
    public void setLeaderDevPv(BigDecimal leaderDevPv) {
        this.leaderDevPv = leaderDevPv;
    }
    
    @Column(name="SEND_STATUS_DEV", length=1)
    public String getSendStatusDev() {
        return this.sendStatusDev;
    }
    
    public void setSendStatusDev(String sendStatusDev) {
        this.sendStatusDev = sendStatusDev;
    }
    
    @Column(name="SEND_DATE_DEV", length=7)
    public Date getSendDateDev() {
        return this.sendDateDev;
    }
    
    public void setSendDateDev(Date sendDateDev) {
        this.sendDateDev = sendDateDev;
    }
    
    @Column(name="SEND_USER_DEV", length=20)
    public String getSendUserDev() {
        return this.sendUserDev;
    }
    
    public void setSendUserDev(String sendUserDev) {
        this.sendUserDev = sendUserDev;
    }
    
    @Column(name="MEMBER_LEVEL", precision=22, scale=0)
    public Integer getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdSendRecordNote pojo = (JbdSendRecordNote) o;

        if (wyear != null ? !wyear.equals(pojo.wyear) : pojo.wyear != null) return false;
        if (wmonth != null ? !wmonth.equals(pojo.wmonth) : pojo.wmonth != null) return false;
        if (wweek != null ? !wweek.equals(pojo.wweek) : pojo.wweek != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (recommendNo != null ? !recommendNo.equals(pojo.recommendNo) : pojo.recommendNo != null) return false;
        if (linkNo != null ? !linkNo.equals(pojo.linkNo) : pojo.linkNo != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (petName != null ? !petName.equals(pojo.petName) : pojo.petName != null) return false;
        if (cardType != null ? !cardType.equals(pojo.cardType) : pojo.cardType != null) return false;
        if (bank != null ? !bank.equals(pojo.bank) : pojo.bank != null) return false;
        if (bankaddress != null ? !bankaddress.equals(pojo.bankaddress) : pojo.bankaddress != null) return false;
        if (bankbook != null ? !bankbook.equals(pojo.bankbook) : pojo.bankbook != null) return false;
        if (bankcard != null ? !bankcard.equals(pojo.bankcard) : pojo.bankcard != null) return false;
        if (exitDate != null ? !exitDate.equals(pojo.exitDate) : pojo.exitDate != null) return false;
        if (sendLateCause != null ? !sendLateCause.equals(pojo.sendLateCause) : pojo.sendLateCause != null) return false;
        if (sendLateRemark != null ? !sendLateRemark.equals(pojo.sendLateRemark) : pojo.sendLateRemark != null) return false;
        if (remittanceMoney != null ? !remittanceMoney.equals(pojo.remittanceMoney) : pojo.remittanceMoney != null) return false;
        if (remittanceBNum != null ? !remittanceBNum.equals(pojo.remittanceBNum) : pojo.remittanceBNum != null) return false;
        if (operaterCode != null ? !operaterCode.equals(pojo.operaterCode) : pojo.operaterCode != null) return false;
        if (operaterTime != null ? !operaterTime.equals(pojo.operaterTime) : pojo.operaterTime != null) return false;
        if (sendDate != null ? !sendDate.equals(pojo.sendDate) : pojo.sendDate != null) return false;
        if (registerStatus != null ? !registerStatus.equals(pojo.registerStatus) : pojo.registerStatus != null) return false;
        if (reissueStatus != null ? !reissueStatus.equals(pojo.reissueStatus) : pojo.reissueStatus != null) return false;
        if (supplyTime != null ? !supplyTime.equals(pojo.supplyTime) : pojo.supplyTime != null) return false;
        if (sendTrace != null ? !sendTrace.equals(pojo.sendTrace) : pojo.sendTrace != null) return false;
        if (sendRemark != null ? !sendRemark.equals(pojo.sendRemark) : pojo.sendRemark != null) return false;
        if (sendMoney != null ? !sendMoney.equals(pojo.sendMoney) : pojo.sendMoney != null) return false;
        if (active != null ? !active.equals(pojo.active) : pojo.active != null) return false;
        if (status != pojo.status) return false;
        if (memberType != null ? !memberType.equals(pojo.memberType) : pojo.memberType != null) return false;
        if (startWeek != null ? !startWeek.equals(pojo.startWeek) : pojo.startWeek != null) return false;
        if (validWeek != null ? !validWeek.equals(pojo.validWeek) : pojo.validWeek != null) return false;
        if (freezeStatus != null ? !freezeStatus.equals(pojo.freezeStatus) : pojo.freezeStatus != null) return false;
        if (beforeFreezeStatus != null ? !beforeFreezeStatus.equals(pojo.beforeFreezeStatus) : pojo.beforeFreezeStatus != null) return false;
        if (totalDev != null ? !totalDev.equals(pojo.totalDev) : pojo.totalDev != null) return false;
        if (deductedDev != null ? !deductedDev.equals(pojo.deductedDev) : pojo.deductedDev != null) return false;
        if (currentDev != null ? !currentDev.equals(pojo.currentDev) : pojo.currentDev != null) return false;
        if (leaderDev != null ? !leaderDev.equals(pojo.leaderDev) : pojo.leaderDev != null) return false;
        if (leaderDevPv != null ? !leaderDevPv.equals(pojo.leaderDevPv) : pojo.leaderDevPv != null) return false;
        if (sendStatusDev != null ? !sendStatusDev.equals(pojo.sendStatusDev) : pojo.sendStatusDev != null) return false;
        if (sendDateDev != null ? !sendDateDev.equals(pojo.sendDateDev) : pojo.sendDateDev != null) return false;
        if (sendUserDev != null ? !sendUserDev.equals(pojo.sendUserDev) : pojo.sendUserDev != null) return false;
        if (memberLevel != null ? !memberLevel.equals(pojo.memberLevel) : pojo.memberLevel != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (wyear != null ? wyear.hashCode() : 0);
        result = 31 * result + (wmonth != null ? wmonth.hashCode() : 0);
        result = 31 * result + (wweek != null ? wweek.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (recommendNo != null ? recommendNo.hashCode() : 0);
        result = 31 * result + (linkNo != null ? linkNo.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (petName != null ? petName.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (bankaddress != null ? bankaddress.hashCode() : 0);
        result = 31 * result + (bankbook != null ? bankbook.hashCode() : 0);
        result = 31 * result + (bankcard != null ? bankcard.hashCode() : 0);
        result = 31 * result + (exitDate != null ? exitDate.hashCode() : 0);
        result = 31 * result + (sendLateCause != null ? sendLateCause.hashCode() : 0);
        result = 31 * result + (sendLateRemark != null ? sendLateRemark.hashCode() : 0);
        result = 31 * result + (remittanceMoney != null ? remittanceMoney.hashCode() : 0);
        result = 31 * result + (remittanceBNum != null ? remittanceBNum.hashCode() : 0);
        result = 31 * result + (operaterCode != null ? operaterCode.hashCode() : 0);
        result = 31 * result + (operaterTime != null ? operaterTime.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (registerStatus != null ? registerStatus.hashCode() : 0);
        result = 31 * result + (reissueStatus != null ? reissueStatus.hashCode() : 0);
        result = 31 * result + (supplyTime != null ? supplyTime.hashCode() : 0);
        result = 31 * result + (sendTrace != null ? sendTrace.hashCode() : 0);
        result = 31 * result + (sendRemark != null ? sendRemark.hashCode() : 0);
        result = 31 * result + (sendMoney != null ? sendMoney.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (memberType != null ? memberType.hashCode() : 0);
        result = 31 * result + (startWeek != null ? startWeek.hashCode() : 0);
        result = 31 * result + (validWeek != null ? validWeek.hashCode() : 0);
        result = 31 * result + (freezeStatus != null ? freezeStatus.hashCode() : 0);
        result = 31 * result + (beforeFreezeStatus != null ? beforeFreezeStatus.hashCode() : 0);
        result = 31 * result + (totalDev != null ? totalDev.hashCode() : 0);
        result = 31 * result + (deductedDev != null ? deductedDev.hashCode() : 0);
        result = 31 * result + (currentDev != null ? currentDev.hashCode() : 0);
        result = 31 * result + (leaderDev != null ? leaderDev.hashCode() : 0);
        result = 31 * result + (leaderDevPv != null ? leaderDevPv.hashCode() : 0);
        result = 31 * result + (sendStatusDev != null ? sendStatusDev.hashCode() : 0);
        result = 31 * result + (sendDateDev != null ? sendDateDev.hashCode() : 0);
        result = 31 * result + (sendUserDev != null ? sendUserDev.hashCode() : 0);
        result = 31 * result + (memberLevel != null ? memberLevel.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("wyear").append("='").append(getWyear()).append("', ");
        sb.append("wmonth").append("='").append(getWmonth()).append("', ");
        sb.append("wweek").append("='").append(getWweek()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("recommendNo").append("='").append(getRecommendNo()).append("', ");
        sb.append("linkNo").append("='").append(getLinkNo()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("petName").append("='").append(getPetName()).append("', ");
        sb.append("cardType").append("='").append(getCardType()).append("', ");
        sb.append("bank").append("='").append(getBank()).append("', ");
        sb.append("bankaddress").append("='").append(getBankaddress()).append("', ");
        sb.append("bankbook").append("='").append(getBankbook()).append("', ");
        sb.append("bankcard").append("='").append(getBankcard()).append("', ");
        sb.append("exitDate").append("='").append(getExitDate()).append("', ");
        sb.append("sendLateCause").append("='").append(getSendLateCause()).append("', ");
        sb.append("sendLateRemark").append("='").append(getSendLateRemark()).append("', ");
        sb.append("remittanceMoney").append("='").append(getRemittanceMoney()).append("', ");
        sb.append("remittanceBNum").append("='").append(getRemittanceBNum()).append("', ");
        sb.append("operaterCode").append("='").append(getOperaterCode()).append("', ");
        sb.append("operaterTime").append("='").append(getOperaterTime()).append("', ");
        sb.append("sendDate").append("='").append(getSendDate()).append("', ");
        sb.append("registerStatus").append("='").append(getRegisterStatus()).append("', ");
        sb.append("reissueStatus").append("='").append(getReissueStatus()).append("', ");
        sb.append("supplyTime").append("='").append(getSupplyTime()).append("', ");
        sb.append("sendTrace").append("='").append(getSendTrace()).append("', ");
        sb.append("sendRemark").append("='").append(getSendRemark()).append("', ");
        sb.append("sendMoney").append("='").append(getSendMoney()).append("', ");
        sb.append("active").append("='").append(getActive()).append("', ");
        sb.append("status").append("='").append(isStatus()).append("', ");
        sb.append("memberType").append("='").append(getMemberType()).append("', ");
        sb.append("startWeek").append("='").append(getStartWeek()).append("', ");
        sb.append("validWeek").append("='").append(getValidWeek()).append("', ");
        sb.append("freezeStatus").append("='").append(getFreezeStatus()).append("', ");
        sb.append("beforeFreezeStatus").append("='").append(getBeforeFreezeStatus()).append("', ");
        sb.append("totalDev").append("='").append(getTotalDev()).append("', ");
        sb.append("deductedDev").append("='").append(getDeductedDev()).append("', ");
        sb.append("currentDev").append("='").append(getCurrentDev()).append("', ");
        sb.append("leaderDev").append("='").append(getLeaderDev()).append("', ");
        sb.append("leaderDevPv").append("='").append(getLeaderDevPv()).append("', ");
        sb.append("sendStatusDev").append("='").append(getSendStatusDev()).append("', ");
        sb.append("sendDateDev").append("='").append(getSendDateDev()).append("', ");
        sb.append("sendUserDev").append("='").append(getSendUserDev()).append("', ");
        sb.append("memberLevel").append("='").append(getMemberLevel()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
