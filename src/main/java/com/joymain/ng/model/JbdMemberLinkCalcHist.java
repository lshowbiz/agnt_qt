package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JBD_MEMBER_LINK_CALC_HIST")

public class JbdMemberLinkCalcHist extends BaseObject implements Serializable {
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
    private String isstore;
    private String cardType;
    private String bank;
    private String bankaddress;
    private String bankbook;
    private String bankcard;
    private Date exitDate;
    private Integer passStatus;
    private Integer honorStar;
    private Integer passStar;
    private Integer honorGrade;
    private Integer passGrade;
    private BigDecimal honorPosition;
    private BigDecimal monthConsumerPv;
    private BigDecimal monthAreaTotalPv;
    private BigDecimal weekGroupPv;
    private BigDecimal monthGroupPv;
    private BigDecimal successSalesRate;
    private BigDecimal monthRecommendGroupPv;
    private BigDecimal franchisePv;
    private BigDecimal franchiseMoney;
    private BigDecimal consumerAmount;
    private BigDecimal ventureSalesPv;
    private BigDecimal ventureLeaderPv;
    private BigDecimal successSalesPv;
    private BigDecimal successLeaderPv;
    private BigDecimal deductMoney;
    private BigDecimal deductTax;
    private BigDecimal exchangeRate;
    private BigDecimal keepPv;
    private String keepUserCode;
    private BigDecimal lastKeepPv;
    private String lastKeepUserCode;
    private BigDecimal departmenKeepPv;
    private BigDecimal passStarGroupPv;
    private BigDecimal passGradeGroupPv;
    private BigDecimal firstMonth;
    private BigDecimal bounsPv;
    private BigDecimal bounsMoney;
    private BigDecimal sendMoney;
    private String active;
    private Integer status;
    private String province;
    private String city;
    private BigDecimal recommendBonusPv;
    private String memberType;
    private BigDecimal storeExpandPv;
    private BigDecimal storeServePv;
    private BigDecimal storeRecommendPv;
    private BigDecimal startWeek;
    private BigDecimal validWeek;
    private Integer freezeStatus;
    private Integer beforeFreezeStatus;
    private String identityType;
    private BigDecimal areaConsumption;
    private BigDecimal ventureFund;
    private BigDecimal algebra;
    private BigDecimal networkMoney;
    private Date checkDate;
    private BigDecimal consumerStatus;
    private BigDecimal totalDev;
    private BigDecimal deductedDev;
    private BigDecimal currentDev;
    private BigDecimal leaderDev;
    private BigDecimal leaderDevPv;
    private BigDecimal salesStatus;
    private BigDecimal devFund;
    private BigDecimal monthMaxPv;
    private BigDecimal monthDoubleAreaPv;
    private BigDecimal monthDoubleMaxPv;
    private BigDecimal doublePassStar;

    private String honorStarStr;

    private String passStarStr;
    
    private String statusStr;
    
    private Integer qualifyStar;//资格奖衔
    private String qualifyRemark;//资格奖衔备注

    private Integer memberLevel;
    private Integer retainLevel;
    private BigDecimal retainGroup;
    private BigDecimal monthOwnPv;
    private String memberLevelStr;
    
    private BigDecimal totalGroup;
    private String retainLevelStr;
    private BigDecimal firstPv;
    private Integer cioType;
    
    private String cioTypeStr;
    private Integer zyType;
    private String zyTypeStr;
    
    private String lastMaxpartPv;
    private String lastareaPv;
    private String maxpartPv;
    private String areaPv;

    private Integer gradeType;
    private String gradeTypeStr;
    
    


	@Transient
	public String getGradeTypeStr() {
		return gradeTypeStr;
	}

	public void setGradeTypeStr(String gradeTypeStr) {
		this.gradeTypeStr = gradeTypeStr;
	}

	@Column(name="GRADE_TYPE")
	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	@Transient
	public String getLastMaxpartPv() {
		return lastMaxpartPv;
	}

	public void setLastMaxpartPv(String lastMaxpartPv) {
		this.lastMaxpartPv = lastMaxpartPv;
	}

	@Transient
	public String getLastareaPv() {
		return lastareaPv;
	}

	public void setLastareaPv(String lastareaPv) {
		this.lastareaPv = lastareaPv;
	}

	@Transient
	public String getMaxpartPv() {
		return maxpartPv;
	}

	public void setMaxpartPv(String maxpartPv) {
		this.maxpartPv = maxpartPv;
	}

	@Transient
	public String getAreaPv() {
		return areaPv;
	}

	public void setAreaPv(String areaPv) {
		this.areaPv = areaPv;
	}

	@Column(name="ZY_TYPE")
	public Integer getZyType() {
		return zyType;
	}

	public void setZyType(Integer zyType) {
		this.zyType = zyType;
	}

	@Transient
	public String getZyTypeStr() {
		return zyTypeStr;
	}

	public void setZyTypeStr(String zyTypeStr) {
		this.zyTypeStr = zyTypeStr;
	}

	@Transient
	public String getCioTypeStr() {
		return cioTypeStr;
	}

	public void setCioTypeStr(String cioTypeStr) {
		this.cioTypeStr = cioTypeStr;
	}

	@Column(name="CIO_TYPE")
	public Integer getCioType() {
		return cioType;
	}

	public void setCioType(Integer cioType) {
		this.cioType = cioType;
	}

	@Column(name="FIRST_PV")
	public BigDecimal getFirstPv() {
		return firstPv;
	}

	public void setFirstPv(BigDecimal firstPv) {
		this.firstPv = firstPv;
	}

	@Transient
	public String getRetainLevelStr() {
		return retainLevelStr;
	}

	public void setRetainLevelStr(String retainLevelStr) {
		this.retainLevelStr = retainLevelStr;
	}

	@Column(name="TOTAL_GROUP")
	public BigDecimal getTotalGroup() {
		return totalGroup;
	}

	public void setTotalGroup(BigDecimal totalGroup) {
		this.totalGroup = totalGroup;
	}

	@Transient
    public String getMemberLevelStr() {
		return memberLevelStr;
	}

	public void setMemberLevelStr(String memberLevelStr) {
		this.memberLevelStr = memberLevelStr;
	}

	@Column(name="MEMBER_LEVEL")
	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

    @Column(name="RETAIN_LEVEL")
	public Integer getRetainLevel() {
		return retainLevel;
	}

	public void setRetainLevel(Integer retainLevel) {
		this.retainLevel = retainLevel;
	}

    @Column(name="RETAIN_GROUP")
	public BigDecimal getRetainGroup() {
		return retainGroup;
	}

	public void setRetainGroup(BigDecimal retainGroup) {
		this.retainGroup = retainGroup;
	}

    @Column(name="MONTH_OWN_PV")
	public BigDecimal getMonthOwnPv() {
		return monthOwnPv;
	}

	public void setMonthOwnPv(BigDecimal monthOwnPv) {
		this.monthOwnPv = monthOwnPv;
	}

	@Transient
	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	@Transient
    public String getHonorStarStr() {
		return honorStarStr;
	}

	public void setHonorStarStr(String honorStarStr) {
		this.honorStarStr = honorStarStr;
	}

	@Transient
	public String getPassStarStr() {
		return passStarStr;
	}

	public void setPassStarStr(String passStarStr) {
		this.passStarStr = passStarStr;
	}

	@Id      @Column(name="ID", unique=true, nullable=false, precision=18, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    @Column(name="ISSTORE", length=1)
    
    public String getIsstore() {
        return this.isstore;
    }
    
    public void setIsstore(String isstore) {
        this.isstore = isstore;
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
    
    @Column(name="PASS_STATUS", precision=2, scale=0)
    
    public Integer getPassStatus() {
        return this.passStatus;
    }
    
    public void setPassStatus(Integer passStatus) {
        this.passStatus = passStatus;
    }
    
    @Column(name="HONOR_STAR", precision=2, scale=0)
    
    public Integer getHonorStar() {
        return this.honorStar;
    }
    
    public void setHonorStar(Integer honorStar) {
        this.honorStar = honorStar;
    }
    
    @Column(name="PASS_STAR", precision=2, scale=0)
    
    public Integer getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Integer passStar) {
        this.passStar = passStar;
    }
    
    @Column(name="HONOR_GRADE", precision=2, scale=0)
    
    public Integer getHonorGrade() {
        return this.honorGrade;
    }
    
    public void setHonorGrade(Integer honorGrade) {
        this.honorGrade = honorGrade;
    }
    
    @Column(name="PASS_GRADE", precision=2, scale=0)
    
    public Integer getPassGrade() {
        return this.passGrade;
    }
    
    public void setPassGrade(Integer passGrade) {
        this.passGrade = passGrade;
    }
    
    @Column(name="HONOR_POSITION", precision=2, scale=0)
    
    public BigDecimal getHonorPosition() {
        return this.honorPosition;
    }
    
    public void setHonorPosition(BigDecimal honorPosition) {
        this.honorPosition = honorPosition;
    }
    
    @Column(name="MONTH_CONSUMER_PV", precision=22, scale=0)
    
    public BigDecimal getMonthConsumerPv() {
        return this.monthConsumerPv;
    }
    
    public void setMonthConsumerPv(BigDecimal monthConsumerPv) {
        this.monthConsumerPv = monthConsumerPv;
    }
    
    @Column(name="MONTH_AREA_TOTAL_PV", precision=22, scale=0)
    
    public BigDecimal getMonthAreaTotalPv() {
        return this.monthAreaTotalPv;
    }
    
    public void setMonthAreaTotalPv(BigDecimal monthAreaTotalPv) {
        this.monthAreaTotalPv = monthAreaTotalPv;
    }
    
    @Column(name="WEEK_GROUP_PV", precision=22, scale=0)
    
    public BigDecimal getWeekGroupPv() {
        return this.weekGroupPv;
    }
    
    public void setWeekGroupPv(BigDecimal weekGroupPv) {
        this.weekGroupPv = weekGroupPv;
    }
    
    @Column(name="MONTH_GROUP_PV", precision=22, scale=0)
    
    public BigDecimal getMonthGroupPv() {
        return this.monthGroupPv;
    }
    
    public void setMonthGroupPv(BigDecimal monthGroupPv) {
        this.monthGroupPv = monthGroupPv;
    }
    
    @Column(name="SUCCESS_SALES_RATE", precision=22, scale=0)
    
    public BigDecimal getSuccessSalesRate() {
        return this.successSalesRate;
    }
    
    public void setSuccessSalesRate(BigDecimal successSalesRate) {
        this.successSalesRate = successSalesRate;
    }
    
    @Column(name="MONTH_RECOMMEND_GROUP_PV", precision=22, scale=0)
    
    public BigDecimal getMonthRecommendGroupPv() {
        return this.monthRecommendGroupPv;
    }
    
    public void setMonthRecommendGroupPv(BigDecimal monthRecommendGroupPv) {
        this.monthRecommendGroupPv = monthRecommendGroupPv;
    }
    
    @Column(name="FRANCHISE_PV", precision=22, scale=0)
    
    public BigDecimal getFranchisePv() {
        return this.franchisePv;
    }
    
    public void setFranchisePv(BigDecimal franchisePv) {
        this.franchisePv = franchisePv;
    }
    
    @Column(name="FRANCHISE_MONEY", precision=22, scale=0)
    
    public BigDecimal getFranchiseMoney() {
        return this.franchiseMoney;
    }
    
    public void setFranchiseMoney(BigDecimal franchiseMoney) {
        this.franchiseMoney = franchiseMoney;
    }
    
    @Column(name="CONSUMER_AMOUNT", precision=18)
    
    public BigDecimal getConsumerAmount() {
        return this.consumerAmount;
    }
    
    public void setConsumerAmount(BigDecimal consumerAmount) {
        this.consumerAmount = consumerAmount;
    }
    
    @Column(name="VENTURE_SALES_PV", precision=22, scale=0)
    
    public BigDecimal getVentureSalesPv() {
        return this.ventureSalesPv;
    }
    
    public void setVentureSalesPv(BigDecimal ventureSalesPv) {
        this.ventureSalesPv = ventureSalesPv;
    }
    
    @Column(name="VENTURE_LEADER_PV", precision=22, scale=0)
    
    public BigDecimal getVentureLeaderPv() {
        return this.ventureLeaderPv;
    }
    
    public void setVentureLeaderPv(BigDecimal ventureLeaderPv) {
        this.ventureLeaderPv = ventureLeaderPv;
    }
    
    @Column(name="SUCCESS_SALES_PV", precision=22, scale=0)
    
    public BigDecimal getSuccessSalesPv() {
        return this.successSalesPv;
    }
    
    public void setSuccessSalesPv(BigDecimal successSalesPv) {
        this.successSalesPv = successSalesPv;
    }
    
    @Column(name="SUCCESS_LEADER_PV", precision=22, scale=0)
    
    public BigDecimal getSuccessLeaderPv() {
        return this.successLeaderPv;
    }
    
    public void setSuccessLeaderPv(BigDecimal successLeaderPv) {
        this.successLeaderPv = successLeaderPv;
    }
    
    @Column(name="DEDUCT_MONEY", precision=22, scale=0)
    
    public BigDecimal getDeductMoney() {
        return this.deductMoney;
    }
    
    public void setDeductMoney(BigDecimal deductMoney) {
        this.deductMoney = deductMoney;
    }
    
    @Column(name="DEDUCT_TAX", precision=22, scale=0)
    
    public BigDecimal getDeductTax() {
        return this.deductTax;
    }
    
    public void setDeductTax(BigDecimal deductTax) {
        this.deductTax = deductTax;
    }
    
    @Column(name="EXCHANGE_RATE", precision=22, scale=0)
    
    public BigDecimal getExchangeRate() {
        return this.exchangeRate;
    }
    
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    
    @Column(name="KEEP_PV", precision=22, scale=0)
    
    public BigDecimal getKeepPv() {
        return this.keepPv;
    }
    
    public void setKeepPv(BigDecimal keepPv) {
        this.keepPv = keepPv;
    }
    
    @Column(name="KEEP_USER_CODE", length=20)
    
    public String getKeepUserCode() {
        return this.keepUserCode;
    }
    
    public void setKeepUserCode(String keepUserCode) {
        this.keepUserCode = keepUserCode;
    }
    
    @Column(name="LAST_KEEP_PV", precision=22, scale=0)
    
    public BigDecimal getLastKeepPv() {
        return this.lastKeepPv;
    }
    
    public void setLastKeepPv(BigDecimal lastKeepPv) {
        this.lastKeepPv = lastKeepPv;
    }
    
    @Column(name="LAST_KEEP_USER_CODE", length=20)
    
    public String getLastKeepUserCode() {
        return this.lastKeepUserCode;
    }
    
    public void setLastKeepUserCode(String lastKeepUserCode) {
        this.lastKeepUserCode = lastKeepUserCode;
    }
    
    @Column(name="DEPARTMEN_KEEP_PV", precision=22, scale=0)
    
    public BigDecimal getDepartmenKeepPv() {
        return this.departmenKeepPv;
    }
    
    public void setDepartmenKeepPv(BigDecimal departmenKeepPv) {
        this.departmenKeepPv = departmenKeepPv;
    }
    
    @Column(name="PASS_STAR_GROUP_PV", precision=22, scale=0)
    
    public BigDecimal getPassStarGroupPv() {
        return this.passStarGroupPv;
    }
    
    public void setPassStarGroupPv(BigDecimal passStarGroupPv) {
        this.passStarGroupPv = passStarGroupPv;
    }
    
    @Column(name="PASS_GRADE_GROUP_PV", precision=22, scale=0)
    
    public BigDecimal getPassGradeGroupPv() {
        return this.passGradeGroupPv;
    }
    
    public void setPassGradeGroupPv(BigDecimal passGradeGroupPv) {
        this.passGradeGroupPv = passGradeGroupPv;
    }
    
    @Column(name="FIRST_MONTH", precision=22, scale=0)
    
    public BigDecimal getFirstMonth() {
        return this.firstMonth;
    }
    
    public void setFirstMonth(BigDecimal firstMonth) {
        this.firstMonth = firstMonth;
    }
    
    @Column(name="BOUNS_PV", precision=22, scale=0)
    
    public BigDecimal getBounsPv() {
        return this.bounsPv;
    }
    
    public void setBounsPv(BigDecimal bounsPv) {
        this.bounsPv = bounsPv;
    }
    
    @Column(name="BOUNS_MONEY", precision=22, scale=0)
    
    public BigDecimal getBounsMoney() {
        return this.bounsMoney;
    }
    
    public void setBounsMoney(BigDecimal bounsMoney) {
        this.bounsMoney = bounsMoney;
    }
    
    @Column(name="SEND_MONEY", precision=22, scale=0)
    
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
    
    @Column(name="STATUS", precision=1, scale=0)
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="PROVINCE", length=500)
    
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=500)
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="RECOMMEND_BONUS_PV", precision=22, scale=0)
    
    public BigDecimal getRecommendBonusPv() {
        return this.recommendBonusPv;
    }
    
    public void setRecommendBonusPv(BigDecimal recommendBonusPv) {
        this.recommendBonusPv = recommendBonusPv;
    }
    
    @Column(name="MEMBER_TYPE", length=1)
    
    public String getMemberType() {
        return this.memberType;
    }
    
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    
    @Column(name="STORE_EXPAND_PV", precision=22, scale=0)
    
    public BigDecimal getStoreExpandPv() {
        return this.storeExpandPv;
    }
    
    public void setStoreExpandPv(BigDecimal storeExpandPv) {
        this.storeExpandPv = storeExpandPv;
    }
    
    @Column(name="STORE_SERVE_PV", precision=22, scale=0)
    
    public BigDecimal getStoreServePv() {
        return this.storeServePv;
    }
    
    public void setStoreServePv(BigDecimal storeServePv) {
        this.storeServePv = storeServePv;
    }
    
    @Column(name="STORE_RECOMMEND_PV", precision=22, scale=0)
    
    public BigDecimal getStoreRecommendPv() {
        return this.storeRecommendPv;
    }
    
    public void setStoreRecommendPv(BigDecimal storeRecommendPv) {
        this.storeRecommendPv = storeRecommendPv;
    }
    
    @Column(name="START_WEEK", precision=22, scale=0)
    
    public BigDecimal getStartWeek() {
        return this.startWeek;
    }
    
    public void setStartWeek(BigDecimal startWeek) {
        this.startWeek = startWeek;
    }
    
    @Column(name="VALID_WEEK", precision=22, scale=0)
    
    public BigDecimal getValidWeek() {
        return this.validWeek;
    }
    
    public void setValidWeek(BigDecimal validWeek) {
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
    
    @Column(name="IDENTITY_TYPE", length=1)
    
    public String getIdentityType() {
        return this.identityType;
    }
    
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }
    
    @Column(name="AREA_CONSUMPTION", precision=22, scale=0)
    
    public BigDecimal getAreaConsumption() {
        return this.areaConsumption;
    }
    
    public void setAreaConsumption(BigDecimal areaConsumption) {
        this.areaConsumption = areaConsumption;
    }
    
    @Column(name="VENTURE_FUND", precision=22, scale=0)
    
    public BigDecimal getVentureFund() {
        return this.ventureFund;
    }
    
    public void setVentureFund(BigDecimal ventureFund) {
        this.ventureFund = ventureFund;
    }
    
    @Column(name="ALGEBRA", precision=4, scale=0)
    
    public BigDecimal getAlgebra() {
        return this.algebra;
    }
    
    public void setAlgebra(BigDecimal algebra) {
        this.algebra = algebra;
    }
    
    @Column(name="NETWORK_MONEY", precision=22, scale=0)
    
    public BigDecimal getNetworkMoney() {
        return this.networkMoney;
    }
    
    public void setNetworkMoney(BigDecimal networkMoney) {
        this.networkMoney = networkMoney;
    }
    @Column(name="CHECK_DATE", length=7)
    
    public Date getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    
    @Column(name="CONSUMER_STATUS", precision=22, scale=0)
    
    public BigDecimal getConsumerStatus() {
        return this.consumerStatus;
    }
    
    public void setConsumerStatus(BigDecimal consumerStatus) {
        this.consumerStatus = consumerStatus;
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
    
    @Column(name="LEADER_DEV_PV", precision=22, scale=0)
    
    public BigDecimal getLeaderDevPv() {
        return this.leaderDevPv;
    }
    
    public void setLeaderDevPv(BigDecimal leaderDevPv) {
        this.leaderDevPv = leaderDevPv;
    }
    
    @Column(name="SALES_STATUS", precision=2, scale=0)
    
    public BigDecimal getSalesStatus() {
        return this.salesStatus;
    }
    
    public void setSalesStatus(BigDecimal salesStatus) {
        this.salesStatus = salesStatus;
    }
    
    @Column(name="DEV_FUND", precision=22, scale=0)
    
    public BigDecimal getDevFund() {
        return this.devFund;
    }
    
    public void setDevFund(BigDecimal devFund) {
        this.devFund = devFund;
    }
    
    @Column(name="MONTH_MAX_PV", precision=22, scale=0)
    
    public BigDecimal getMonthMaxPv() {
        return this.monthMaxPv;
    }
    
    public void setMonthMaxPv(BigDecimal monthMaxPv) {
        this.monthMaxPv = monthMaxPv;
    }
    
    @Column(name="MONTH_DOUBLE_AREA_PV", precision=22, scale=0)
    
    public BigDecimal getMonthDoubleAreaPv() {
        return this.monthDoubleAreaPv;
    }
    
    public void setMonthDoubleAreaPv(BigDecimal monthDoubleAreaPv) {
        this.monthDoubleAreaPv = monthDoubleAreaPv;
    }
    
    @Column(name="MONTH_DOUBLE_MAX_PV", precision=22, scale=0)
    
    public BigDecimal getMonthDoubleMaxPv() {
        return this.monthDoubleMaxPv;
    }
    
    public void setMonthDoubleMaxPv(BigDecimal monthDoubleMaxPv) {
        this.monthDoubleMaxPv = monthDoubleMaxPv;
    }
    
    @Column(name="DOUBLE_PASS_STAR", precision=22, scale=0)
    
    public BigDecimal getDoublePassStar() {
        return this.doublePassStar;
    }
    
    public void setDoublePassStar(BigDecimal doublePassStar) {
        this.doublePassStar = doublePassStar;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdMemberLinkCalcHist pojo = (JbdMemberLinkCalcHist) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (recommendNo != null ? !recommendNo.equals(pojo.recommendNo) : pojo.recommendNo != null) return false;
        if (linkNo != null ? !linkNo.equals(pojo.linkNo) : pojo.linkNo != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (petName != null ? !petName.equals(pojo.petName) : pojo.petName != null) return false;
        if (isstore != null ? !isstore.equals(pojo.isstore) : pojo.isstore != null) return false;
        if (cardType != null ? !cardType.equals(pojo.cardType) : pojo.cardType != null) return false;
        if (bank != null ? !bank.equals(pojo.bank) : pojo.bank != null) return false;
        if (bankaddress != null ? !bankaddress.equals(pojo.bankaddress) : pojo.bankaddress != null) return false;
        if (bankbook != null ? !bankbook.equals(pojo.bankbook) : pojo.bankbook != null) return false;
        if (bankcard != null ? !bankcard.equals(pojo.bankcard) : pojo.bankcard != null) return false;
        if (exitDate != null ? !exitDate.equals(pojo.exitDate) : pojo.exitDate != null) return false;
        if (passStatus != null ? !passStatus.equals(pojo.passStatus) : pojo.passStatus != null) return false;
        if (honorStar != null ? !honorStar.equals(pojo.honorStar) : pojo.honorStar != null) return false;
        if (passStar != null ? !passStar.equals(pojo.passStar) : pojo.passStar != null) return false;
        if (honorGrade != null ? !honorGrade.equals(pojo.honorGrade) : pojo.honorGrade != null) return false;
        if (passGrade != null ? !passGrade.equals(pojo.passGrade) : pojo.passGrade != null) return false;
        if (honorPosition != null ? !honorPosition.equals(pojo.honorPosition) : pojo.honorPosition != null) return false;
        if (monthConsumerPv != null ? !monthConsumerPv.equals(pojo.monthConsumerPv) : pojo.monthConsumerPv != null) return false;
        if (monthAreaTotalPv != null ? !monthAreaTotalPv.equals(pojo.monthAreaTotalPv) : pojo.monthAreaTotalPv != null) return false;
        if (weekGroupPv != null ? !weekGroupPv.equals(pojo.weekGroupPv) : pojo.weekGroupPv != null) return false;
        if (monthGroupPv != null ? !monthGroupPv.equals(pojo.monthGroupPv) : pojo.monthGroupPv != null) return false;
        if (successSalesRate != null ? !successSalesRate.equals(pojo.successSalesRate) : pojo.successSalesRate != null) return false;
        if (monthRecommendGroupPv != null ? !monthRecommendGroupPv.equals(pojo.monthRecommendGroupPv) : pojo.monthRecommendGroupPv != null) return false;
        if (franchisePv != null ? !franchisePv.equals(pojo.franchisePv) : pojo.franchisePv != null) return false;
        if (franchiseMoney != null ? !franchiseMoney.equals(pojo.franchiseMoney) : pojo.franchiseMoney != null) return false;
        if (consumerAmount != null ? !consumerAmount.equals(pojo.consumerAmount) : pojo.consumerAmount != null) return false;
        if (ventureSalesPv != null ? !ventureSalesPv.equals(pojo.ventureSalesPv) : pojo.ventureSalesPv != null) return false;
        if (ventureLeaderPv != null ? !ventureLeaderPv.equals(pojo.ventureLeaderPv) : pojo.ventureLeaderPv != null) return false;
        if (successSalesPv != null ? !successSalesPv.equals(pojo.successSalesPv) : pojo.successSalesPv != null) return false;
        if (successLeaderPv != null ? !successLeaderPv.equals(pojo.successLeaderPv) : pojo.successLeaderPv != null) return false;
        if (deductMoney != null ? !deductMoney.equals(pojo.deductMoney) : pojo.deductMoney != null) return false;
        if (deductTax != null ? !deductTax.equals(pojo.deductTax) : pojo.deductTax != null) return false;
        if (exchangeRate != null ? !exchangeRate.equals(pojo.exchangeRate) : pojo.exchangeRate != null) return false;
        if (keepPv != null ? !keepPv.equals(pojo.keepPv) : pojo.keepPv != null) return false;
        if (keepUserCode != null ? !keepUserCode.equals(pojo.keepUserCode) : pojo.keepUserCode != null) return false;
        if (lastKeepPv != null ? !lastKeepPv.equals(pojo.lastKeepPv) : pojo.lastKeepPv != null) return false;
        if (lastKeepUserCode != null ? !lastKeepUserCode.equals(pojo.lastKeepUserCode) : pojo.lastKeepUserCode != null) return false;
        if (departmenKeepPv != null ? !departmenKeepPv.equals(pojo.departmenKeepPv) : pojo.departmenKeepPv != null) return false;
        if (passStarGroupPv != null ? !passStarGroupPv.equals(pojo.passStarGroupPv) : pojo.passStarGroupPv != null) return false;
        if (passGradeGroupPv != null ? !passGradeGroupPv.equals(pojo.passGradeGroupPv) : pojo.passGradeGroupPv != null) return false;
        if (firstMonth != null ? !firstMonth.equals(pojo.firstMonth) : pojo.firstMonth != null) return false;
        if (bounsPv != null ? !bounsPv.equals(pojo.bounsPv) : pojo.bounsPv != null) return false;
        if (bounsMoney != null ? !bounsMoney.equals(pojo.bounsMoney) : pojo.bounsMoney != null) return false;
        if (sendMoney != null ? !sendMoney.equals(pojo.sendMoney) : pojo.sendMoney != null) return false;
        if (active != null ? !active.equals(pojo.active) : pojo.active != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (recommendBonusPv != null ? !recommendBonusPv.equals(pojo.recommendBonusPv) : pojo.recommendBonusPv != null) return false;
        if (memberType != null ? !memberType.equals(pojo.memberType) : pojo.memberType != null) return false;
        if (storeExpandPv != null ? !storeExpandPv.equals(pojo.storeExpandPv) : pojo.storeExpandPv != null) return false;
        if (storeServePv != null ? !storeServePv.equals(pojo.storeServePv) : pojo.storeServePv != null) return false;
        if (storeRecommendPv != null ? !storeRecommendPv.equals(pojo.storeRecommendPv) : pojo.storeRecommendPv != null) return false;
        if (startWeek != null ? !startWeek.equals(pojo.startWeek) : pojo.startWeek != null) return false;
        if (validWeek != null ? !validWeek.equals(pojo.validWeek) : pojo.validWeek != null) return false;
        if (freezeStatus != null ? !freezeStatus.equals(pojo.freezeStatus) : pojo.freezeStatus != null) return false;
        if (beforeFreezeStatus != null ? !beforeFreezeStatus.equals(pojo.beforeFreezeStatus) : pojo.beforeFreezeStatus != null) return false;
        if (identityType != null ? !identityType.equals(pojo.identityType) : pojo.identityType != null) return false;
        if (areaConsumption != null ? !areaConsumption.equals(pojo.areaConsumption) : pojo.areaConsumption != null) return false;
        if (ventureFund != null ? !ventureFund.equals(pojo.ventureFund) : pojo.ventureFund != null) return false;
        if (algebra != null ? !algebra.equals(pojo.algebra) : pojo.algebra != null) return false;
        if (networkMoney != null ? !networkMoney.equals(pojo.networkMoney) : pojo.networkMoney != null) return false;
        if (checkDate != null ? !checkDate.equals(pojo.checkDate) : pojo.checkDate != null) return false;
        if (consumerStatus != null ? !consumerStatus.equals(pojo.consumerStatus) : pojo.consumerStatus != null) return false;
        if (totalDev != null ? !totalDev.equals(pojo.totalDev) : pojo.totalDev != null) return false;
        if (deductedDev != null ? !deductedDev.equals(pojo.deductedDev) : pojo.deductedDev != null) return false;
        if (currentDev != null ? !currentDev.equals(pojo.currentDev) : pojo.currentDev != null) return false;
        if (leaderDev != null ? !leaderDev.equals(pojo.leaderDev) : pojo.leaderDev != null) return false;
        if (leaderDevPv != null ? !leaderDevPv.equals(pojo.leaderDevPv) : pojo.leaderDevPv != null) return false;
        if (salesStatus != null ? !salesStatus.equals(pojo.salesStatus) : pojo.salesStatus != null) return false;
        if (devFund != null ? !devFund.equals(pojo.devFund) : pojo.devFund != null) return false;
        if (monthMaxPv != null ? !monthMaxPv.equals(pojo.monthMaxPv) : pojo.monthMaxPv != null) return false;
        if (monthDoubleAreaPv != null ? !monthDoubleAreaPv.equals(pojo.monthDoubleAreaPv) : pojo.monthDoubleAreaPv != null) return false;
        if (monthDoubleMaxPv != null ? !monthDoubleMaxPv.equals(pojo.monthDoubleMaxPv) : pojo.monthDoubleMaxPv != null) return false;
        if (doublePassStar != null ? !doublePassStar.equals(pojo.doublePassStar) : pojo.doublePassStar != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (recommendNo != null ? recommendNo.hashCode() : 0);
        result = 31 * result + (linkNo != null ? linkNo.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (petName != null ? petName.hashCode() : 0);
        result = 31 * result + (isstore != null ? isstore.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (bankaddress != null ? bankaddress.hashCode() : 0);
        result = 31 * result + (bankbook != null ? bankbook.hashCode() : 0);
        result = 31 * result + (bankcard != null ? bankcard.hashCode() : 0);
        result = 31 * result + (exitDate != null ? exitDate.hashCode() : 0);
        result = 31 * result + (passStatus != null ? passStatus.hashCode() : 0);
        result = 31 * result + (honorStar != null ? honorStar.hashCode() : 0);
        result = 31 * result + (passStar != null ? passStar.hashCode() : 0);
        result = 31 * result + (honorGrade != null ? honorGrade.hashCode() : 0);
        result = 31 * result + (passGrade != null ? passGrade.hashCode() : 0);
        result = 31 * result + (honorPosition != null ? honorPosition.hashCode() : 0);
        result = 31 * result + (monthConsumerPv != null ? monthConsumerPv.hashCode() : 0);
        result = 31 * result + (monthAreaTotalPv != null ? monthAreaTotalPv.hashCode() : 0);
        result = 31 * result + (weekGroupPv != null ? weekGroupPv.hashCode() : 0);
        result = 31 * result + (monthGroupPv != null ? monthGroupPv.hashCode() : 0);
        result = 31 * result + (successSalesRate != null ? successSalesRate.hashCode() : 0);
        result = 31 * result + (monthRecommendGroupPv != null ? monthRecommendGroupPv.hashCode() : 0);
        result = 31 * result + (franchisePv != null ? franchisePv.hashCode() : 0);
        result = 31 * result + (franchiseMoney != null ? franchiseMoney.hashCode() : 0);
        result = 31 * result + (consumerAmount != null ? consumerAmount.hashCode() : 0);
        result = 31 * result + (ventureSalesPv != null ? ventureSalesPv.hashCode() : 0);
        result = 31 * result + (ventureLeaderPv != null ? ventureLeaderPv.hashCode() : 0);
        result = 31 * result + (successSalesPv != null ? successSalesPv.hashCode() : 0);
        result = 31 * result + (successLeaderPv != null ? successLeaderPv.hashCode() : 0);
        result = 31 * result + (deductMoney != null ? deductMoney.hashCode() : 0);
        result = 31 * result + (deductTax != null ? deductTax.hashCode() : 0);
        result = 31 * result + (exchangeRate != null ? exchangeRate.hashCode() : 0);
        result = 31 * result + (keepPv != null ? keepPv.hashCode() : 0);
        result = 31 * result + (keepUserCode != null ? keepUserCode.hashCode() : 0);
        result = 31 * result + (lastKeepPv != null ? lastKeepPv.hashCode() : 0);
        result = 31 * result + (lastKeepUserCode != null ? lastKeepUserCode.hashCode() : 0);
        result = 31 * result + (departmenKeepPv != null ? departmenKeepPv.hashCode() : 0);
        result = 31 * result + (passStarGroupPv != null ? passStarGroupPv.hashCode() : 0);
        result = 31 * result + (passGradeGroupPv != null ? passGradeGroupPv.hashCode() : 0);
        result = 31 * result + (firstMonth != null ? firstMonth.hashCode() : 0);
        result = 31 * result + (bounsPv != null ? bounsPv.hashCode() : 0);
        result = 31 * result + (bounsMoney != null ? bounsMoney.hashCode() : 0);
        result = 31 * result + (sendMoney != null ? sendMoney.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (recommendBonusPv != null ? recommendBonusPv.hashCode() : 0);
        result = 31 * result + (memberType != null ? memberType.hashCode() : 0);
        result = 31 * result + (storeExpandPv != null ? storeExpandPv.hashCode() : 0);
        result = 31 * result + (storeServePv != null ? storeServePv.hashCode() : 0);
        result = 31 * result + (storeRecommendPv != null ? storeRecommendPv.hashCode() : 0);
        result = 31 * result + (startWeek != null ? startWeek.hashCode() : 0);
        result = 31 * result + (validWeek != null ? validWeek.hashCode() : 0);
        result = 31 * result + (freezeStatus != null ? freezeStatus.hashCode() : 0);
        result = 31 * result + (beforeFreezeStatus != null ? beforeFreezeStatus.hashCode() : 0);
        result = 31 * result + (identityType != null ? identityType.hashCode() : 0);
        result = 31 * result + (areaConsumption != null ? areaConsumption.hashCode() : 0);
        result = 31 * result + (ventureFund != null ? ventureFund.hashCode() : 0);
        result = 31 * result + (algebra != null ? algebra.hashCode() : 0);
        result = 31 * result + (networkMoney != null ? networkMoney.hashCode() : 0);
        result = 31 * result + (checkDate != null ? checkDate.hashCode() : 0);
        result = 31 * result + (consumerStatus != null ? consumerStatus.hashCode() : 0);
        result = 31 * result + (totalDev != null ? totalDev.hashCode() : 0);
        result = 31 * result + (deductedDev != null ? deductedDev.hashCode() : 0);
        result = 31 * result + (currentDev != null ? currentDev.hashCode() : 0);
        result = 31 * result + (leaderDev != null ? leaderDev.hashCode() : 0);
        result = 31 * result + (leaderDevPv != null ? leaderDevPv.hashCode() : 0);
        result = 31 * result + (salesStatus != null ? salesStatus.hashCode() : 0);
        result = 31 * result + (devFund != null ? devFund.hashCode() : 0);
        result = 31 * result + (monthMaxPv != null ? monthMaxPv.hashCode() : 0);
        result = 31 * result + (monthDoubleAreaPv != null ? monthDoubleAreaPv.hashCode() : 0);
        result = 31 * result + (monthDoubleMaxPv != null ? monthDoubleMaxPv.hashCode() : 0);
        result = 31 * result + (doublePassStar != null ? doublePassStar.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("recommendNo").append("='").append(getRecommendNo()).append("', ");
        sb.append("linkNo").append("='").append(getLinkNo()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("petName").append("='").append(getPetName()).append("', ");
        sb.append("isstore").append("='").append(getIsstore()).append("', ");
        sb.append("cardType").append("='").append(getCardType()).append("', ");
        sb.append("bank").append("='").append(getBank()).append("', ");
        sb.append("bankaddress").append("='").append(getBankaddress()).append("', ");
        sb.append("bankbook").append("='").append(getBankbook()).append("', ");
        sb.append("bankcard").append("='").append(getBankcard()).append("', ");
        sb.append("exitDate").append("='").append(getExitDate()).append("', ");
        sb.append("passStatus").append("='").append(getPassStatus()).append("', ");
        sb.append("honorStar").append("='").append(getHonorStar()).append("', ");
        sb.append("passStar").append("='").append(getPassStar()).append("', ");
        sb.append("honorGrade").append("='").append(getHonorGrade()).append("', ");
        sb.append("passGrade").append("='").append(getPassGrade()).append("', ");
        sb.append("honorPosition").append("='").append(getHonorPosition()).append("', ");
        sb.append("monthConsumerPv").append("='").append(getMonthConsumerPv()).append("', ");
        sb.append("monthAreaTotalPv").append("='").append(getMonthAreaTotalPv()).append("', ");
        sb.append("weekGroupPv").append("='").append(getWeekGroupPv()).append("', ");
        sb.append("monthGroupPv").append("='").append(getMonthGroupPv()).append("', ");
        sb.append("successSalesRate").append("='").append(getSuccessSalesRate()).append("', ");
        sb.append("monthRecommendGroupPv").append("='").append(getMonthRecommendGroupPv()).append("', ");
        sb.append("franchisePv").append("='").append(getFranchisePv()).append("', ");
        sb.append("franchiseMoney").append("='").append(getFranchiseMoney()).append("', ");
        sb.append("consumerAmount").append("='").append(getConsumerAmount()).append("', ");
        sb.append("ventureSalesPv").append("='").append(getVentureSalesPv()).append("', ");
        sb.append("ventureLeaderPv").append("='").append(getVentureLeaderPv()).append("', ");
        sb.append("successSalesPv").append("='").append(getSuccessSalesPv()).append("', ");
        sb.append("successLeaderPv").append("='").append(getSuccessLeaderPv()).append("', ");
        sb.append("deductMoney").append("='").append(getDeductMoney()).append("', ");
        sb.append("deductTax").append("='").append(getDeductTax()).append("', ");
        sb.append("exchangeRate").append("='").append(getExchangeRate()).append("', ");
        sb.append("keepPv").append("='").append(getKeepPv()).append("', ");
        sb.append("keepUserCode").append("='").append(getKeepUserCode()).append("', ");
        sb.append("lastKeepPv").append("='").append(getLastKeepPv()).append("', ");
        sb.append("lastKeepUserCode").append("='").append(getLastKeepUserCode()).append("', ");
        sb.append("departmenKeepPv").append("='").append(getDepartmenKeepPv()).append("', ");
        sb.append("passStarGroupPv").append("='").append(getPassStarGroupPv()).append("', ");
        sb.append("passGradeGroupPv").append("='").append(getPassGradeGroupPv()).append("', ");
        sb.append("firstMonth").append("='").append(getFirstMonth()).append("', ");
        sb.append("bounsPv").append("='").append(getBounsPv()).append("', ");
        sb.append("bounsMoney").append("='").append(getBounsMoney()).append("', ");
        sb.append("sendMoney").append("='").append(getSendMoney()).append("', ");
        sb.append("active").append("='").append(getActive()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("recommendBonusPv").append("='").append(getRecommendBonusPv()).append("', ");
        sb.append("memberType").append("='").append(getMemberType()).append("', ");
        sb.append("storeExpandPv").append("='").append(getStoreExpandPv()).append("', ");
        sb.append("storeServePv").append("='").append(getStoreServePv()).append("', ");
        sb.append("storeRecommendPv").append("='").append(getStoreRecommendPv()).append("', ");
        sb.append("startWeek").append("='").append(getStartWeek()).append("', ");
        sb.append("validWeek").append("='").append(getValidWeek()).append("', ");
        sb.append("freezeStatus").append("='").append(getFreezeStatus()).append("', ");
        sb.append("beforeFreezeStatus").append("='").append(getBeforeFreezeStatus()).append("', ");
        sb.append("identityType").append("='").append(getIdentityType()).append("', ");
        sb.append("areaConsumption").append("='").append(getAreaConsumption()).append("', ");
        sb.append("ventureFund").append("='").append(getVentureFund()).append("', ");
        sb.append("algebra").append("='").append(getAlgebra()).append("', ");
        sb.append("networkMoney").append("='").append(getNetworkMoney()).append("', ");
        sb.append("checkDate").append("='").append(getCheckDate()).append("', ");
        sb.append("consumerStatus").append("='").append(getConsumerStatus()).append("', ");
        sb.append("totalDev").append("='").append(getTotalDev()).append("', ");
        sb.append("deductedDev").append("='").append(getDeductedDev()).append("', ");
        sb.append("currentDev").append("='").append(getCurrentDev()).append("', ");
        sb.append("leaderDev").append("='").append(getLeaderDev()).append("', ");
        sb.append("leaderDevPv").append("='").append(getLeaderDevPv()).append("', ");
        sb.append("salesStatus").append("='").append(getSalesStatus()).append("', ");
        sb.append("devFund").append("='").append(getDevFund()).append("', ");
        sb.append("monthMaxPv").append("='").append(getMonthMaxPv()).append("', ");
        sb.append("monthDoubleAreaPv").append("='").append(getMonthDoubleAreaPv()).append("', ");
        sb.append("monthDoubleMaxPv").append("='").append(getMonthDoubleMaxPv()).append("', ");
        sb.append("doublePassStar").append("='").append(getDoublePassStar()).append("'");
        sb.append("]");
      
        return sb.toString();
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

    @Column(name="W_YEAR", precision=8, scale=0)
    
	public Integer getWyear() {
		return wyear;
	}
	public void setWyear(Integer wyear) {
		this.wyear = wyear;
	}

	@Column(name="QUALIFY_STAR", precision=2, scale=0)
    public Integer getQualifyStar()
    {
        return qualifyStar;
    }

    public void setQualifyStar(Integer qualifyStar)
    {
        this.qualifyStar = qualifyStar;
    }

    @Column(name="QUALIFY_REMARK", length=4000)
    public String getQualifyRemark()
    {
        return qualifyRemark;
    }

    public void setQualifyRemark(String qualifyRemark)
    {
        this.qualifyRemark = qualifyRemark;
    }

    

    
    
}
