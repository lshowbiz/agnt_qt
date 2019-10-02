package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;





@Entity
@Table(name="JMI_MEMBER")

public class JmiMember extends BaseObject implements Serializable {
    private String userCode;
    private String linkNo;
    private String recommendNo;
    private String cardType;
    private String firstName;
    private String lastName;
    private String petName;
    private String sex;
    private Date birthday;
    private String email;
    private String papertype;
    private String papernumber;
    private String bank;
    private String bankaddress;
    private String bankcard;
    private String bankbook;
    private String phone;
    private String faxtele;
    private String mobiletele;
    private String officetele;
    private String countryCode;
    private String province;
    private String city;
    private String address;
    private String postalcode;
    private Date exitDate;
    private String remark;
    private String isstore;
    private String companyCode;
    private String checkNo;
    private Date checkDate;
    private String createNo;
    private Date createTime;
    private Date deadlineDate;
    private String pbNo;
    private String pbNo1;
    private String pbNo2;
    private String active;
    private String miUserName;
    private String spouseName;
    private String spouseIdno;
    private String bankCity;
    private String bankProvince;
    private String district;
    private Long linkNum;
    private Long pendingLinkNum;
    private Long recommendNum;
    private Long pendingRecommendNum;
    private Date activeTime;
    private String memberType;
    private String oriCard;
    private String flag;
    private BigDecimal oriPv;
    private String changeStatus;
    private String subStoreStatus;
    private String subRecommendStore;
    private Date subStoreCheckDate;
    private String identityType;
    private String townAddr;
    private String commPostalcode;
    private String commProvince;
    private String commCity;
    private String commAddr;
    private String villageAddr;
    private String companyName;
    private String personCharge;
    private String companyAddr;
    private String uniteNumber;
    private Integer startWeek;
    private Integer validWeek;
    private Integer freezeStatus;
    private Integer beforeFreezeStatus;
    private String commDistrict;
    private String town;
    private String isDiscount;
    private String isSubStore;
    private String activeStatus;
    private String firstNameKana;
    private String lastNameKana;
    private String bankType;
    private String building;
    private String payType;
    private String bankCode;
    private String bankKana;
    private String customerLevel;
    private String nationality;
    private String recommendStore;
    private String shopType;
    private String titleRemark;
    private String firstNamePy;
    private String lastNamePy;
    private String clAddress;
    private String ecMallPhone;
    private String ecMallStatus;
    private JsysUser sysUser;
    private Integer memberStar;
    
    
    private Set<JmiAddrBook> jmiAddrBooks= new HashSet<JmiAddrBook>(0);
    private JmiLinkRef jmiLinkRef;
    private JmiRecommendRef jmiRecommendRef;

    private Set<JbdUserValidList> jbdUserValidList= new HashSet<JbdUserValidList>(0);
    private String isMobile;
    //会员级别
    private Integer memberLevel;
    
    //可下首单
    private Integer notFirst;
    private Integer gradeType;

    
    private String isCloudshop;
    private String cloudshopPhone;
    private String cloudshopNo;
    private String memberUserType;
    
    private Date cloudStartdate;
    private Date cloudEnddate;
    private Date standardTime;
    private Date standardMkTime;
    
    
    @Column(name="STANDARD_MK_TIME")
    public Date getStandardMkTime() {
		return standardMkTime;
	}

	public void setStandardMkTime(Date standardMkTime) {
		this.standardMkTime = standardMkTime;
	}

	@Column(name="CLOUD_STARTDATE")
    public Date getCloudStartdate() {
		return cloudStartdate;
	}

	public void setCloudStartdate(Date cloudStartdate) {
		this.cloudStartdate = cloudStartdate;
	}

	@Column(name="CLOUD_ENDDATE")
	public Date getCloudEnddate() {
		return cloudEnddate;
	}

	public void setCloudEnddate(Date cloudEnddate) {
		this.cloudEnddate = cloudEnddate;
	}

	@Column(name="STANDARD_TIME")
	public Date getStandardTime() {
		return standardTime;
	}

	public void setStandardTime(Date standardTime) {
		this.standardTime = standardTime;
	}

	@Column(name="MEMBER_USER_TYPE")
    public String getMemberUserType() {
		return memberUserType;
	}

	public void setMemberUserType(String memberUserType) {
		this.memberUserType = memberUserType;
	}
    

	@Column(name="CLOUDSHOP_NO")
	public String getCloudshopNo() {
		return cloudshopNo;
	}

	public void setCloudshopNo(String cloudshopNo) {
		this.cloudshopNo = cloudshopNo;
	}

	@Column(name="CLOUDSHOP_PHONE")
	public String getCloudshopPhone() {
		return cloudshopPhone;
	}

	public void setCloudshopPhone(String cloudshopPhone) {
		this.cloudshopPhone = cloudshopPhone;
	}

	@Column(name="IS_CLOUDSHOP")
	public String getIsCloudshop() {
		return isCloudshop;
	}

	public void setIsCloudshop(String isCloudshop) {
		this.isCloudshop = isCloudshop;
	}
    
	@Column(name="GRADE_TYPE")
    public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	@Column(name="NOT_FIRST")
	public Integer getNotFirst() {
		return notFirst;
	}

	public void setNotFirst(Integer notFirst) {
		this.notFirst = notFirst;
	}

    @Column(name="MEMBER_LEVEL")
    public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

    @Column(name="IS_MOBILE")
	public String getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

	@Id  
	@Column(name="USER_CODE", unique=true, nullable=false, length=20)  
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="LINK_NO", nullable=false, length=20)
    
    public String getLinkNo() {
        return this.linkNo;
    }
    
    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }
    
    @Column(name="RECOMMEND_NO", nullable=false, length=20)
    
    public String getRecommendNo() {
        return this.recommendNo;
    }
    
    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }
    
    @Column(name="CARD_TYPE", length=1)
    
    public String getCardType() {
        return this.cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    @Column(name="FIRST_NAME", length=100)
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name="LAST_NAME", length=100)
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name="PET_NAME", length=100)
    
    public String getPetName() {
        return this.petName;
    }
    
    public void setPetName(String petName) {
        this.petName = petName;
    }
    
    @Column(name="SEX", length=1)
    
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="BIRTHDAY", length=7)
    
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="EMAIL", length=40)
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="PAPERTYPE", length=20)
    
    public String getPapertype() {
        return this.papertype;
    }
    
    public void setPapertype(String papertype) {
        this.papertype = papertype;
    }
    
    @Column(name="PAPERNUMBER", length=100)
    
    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }
    
    @Column(name="BANK", length=80)
    
    public String getBank() {
        return this.bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }
    
    @Column(name="BANKADDRESS", length=300)
    
    public String getBankaddress() {
        return this.bankaddress;
    }
    
    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }
    
    @Column(name="BANKCARD", length=100)
    
    public String getBankcard() {
        return this.bankcard;
    }
    
    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }
    
    @Column(name="BANKBOOK", length=100)
    
    public String getBankbook() {
        return this.bankbook;
    }
    
    public void setBankbook(String bankbook) {
        this.bankbook = bankbook;
    }
    
    @Column(name="PHONE", length=30)
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="FAXTELE", length=30)
    
    public String getFaxtele() {
        return this.faxtele;
    }
    
    public void setFaxtele(String faxtele) {
        this.faxtele = faxtele;
    }
    
    @Column(name="MOBILETELE", length=30)
    
    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    
    @Column(name="OFFICETELE", length=30)
    
    public String getOfficetele() {
        return this.officetele;
    }
    
    public void setOfficetele(String officetele) {
        this.officetele = officetele;
    }
    
    @Column(name="COUNTRY_CODE", length=2)
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    @Column(name="PROVINCE", length=20)
    
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=30)
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="ADDRESS", length=500)
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="POSTALCODE", length=10)
    
    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    
    @Column(name="EXIT_DATE", length=7)
    
    public Date getExitDate() {
        return this.exitDate;
    }
    
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }
    
    @Column(name="REMARK", length=2000)
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="ISSTORE", length=1)
    
    public String getIsstore() {
        return this.isstore;
    }
    
    public void setIsstore(String isstore) {
        this.isstore = isstore;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="CHECK_NO", length=20)
    
    public String getCheckNo() {
        return this.checkNo;
    }
    
    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }
    
    @Column(name="CHECK_DATE", length=7)
    
    public Date getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    
    @Column(name="CREATE_NO", length=20)
    
    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    @Column(name="CREATE_TIME", length=7)
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name="DEADLINE_DATE", length=7)
    
    public Date getDeadlineDate() {
        return this.deadlineDate;
    }
    
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
    
    @Column(name="PB_NO", length=30)
    
    public String getPbNo() {
        return this.pbNo;
    }
    
    public void setPbNo(String pbNo) {
        this.pbNo = pbNo;
    }
    
    @Column(name="PB_NO1", length=30)
    
    public String getPbNo1() {
        return this.pbNo1;
    }
    
    public void setPbNo1(String pbNo1) {
        this.pbNo1 = pbNo1;
    }
    
    @Column(name="PB_NO2", length=30)
    
    public String getPbNo2() {
        return this.pbNo2;
    }
    
    public void setPbNo2(String pbNo2) {
        this.pbNo2 = pbNo2;
    }
    
    @Column(name="ACTIVE", nullable=false, length=1)
    
    public String getActive() {
        return this.active;
    }
    
    public void setActive(String active) {
        this.active = active;
    }
    
    @Column(name="MI_USER_NAME", length=300)
    
    public String getMiUserName() {
        return this.miUserName;
    }
    
    public void setMiUserName(String miUserName) {
        this.miUserName = miUserName;
    }
    
    @Column(name="SPOUSE_NAME", length=300)
    
    public String getSpouseName() {
        return this.spouseName;
    }
    
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }
    
    @Column(name="SPOUSE_IDNO", length=30)
    
    public String getSpouseIdno() {
        return this.spouseIdno;
    }
    
    public void setSpouseIdno(String spouseIdno) {
        this.spouseIdno = spouseIdno;
    }
    
    @Column(name="BANK_CITY", length=20)
    
    public String getBankCity() {
        return this.bankCity;
    }
    
    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }
    
    @Column(name="BANK_PROVINCE", length=20)
    
    public String getBankProvince() {
        return this.bankProvince;
    }
    
    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }
    
    @Column(name="DISTRICT", length=20)
    
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    @Column(name="LINK_NUM", precision=22, scale=0)
    
    public Long getLinkNum() {
        return this.linkNum;
    }
    
    public void setLinkNum(Long linkNum) {
    	if (linkNum == null) {
    		this.linkNum = 0L;
		}else{
			this.linkNum = linkNum;
		}
    }
    
    @Column(name="PENDING_LINK_NUM", precision=22, scale=0)
    
    public Long getPendingLinkNum() {
        return this.pendingLinkNum;
    }
    
    public void setPendingLinkNum(Long pendingLinkNum) {
        this.pendingLinkNum = pendingLinkNum;
    }
    
    @Column(name="RECOMMEND_NUM", precision=22, scale=0)
    
    public Long getRecommendNum() {
        return this.recommendNum;
    }
    
    public void setRecommendNum(Long recommendNum) {
        this.recommendNum = recommendNum;
    }
    
    @Column(name="PENDING_RECOMMEND_NUM", precision=22, scale=0)
    
    public Long getPendingRecommendNum() {
        return this.pendingRecommendNum;
    }
    
    public void setPendingRecommendNum(Long pendingRecommendNum) {
        this.pendingRecommendNum = pendingRecommendNum;
    }
    
    @Column(name="ACTIVE_TIME", length=7)
    
    public Date getActiveTime() {
        return this.activeTime;
    }
    
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }
    
    @Column(name="MEMBER_TYPE", length=2)
    
    public String getMemberType() {
        return this.memberType;
    }
    
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    
    @Column(name="ORI_CARD", length=1)
    
    public String getOriCard() {
        return this.oriCard;
    }
    
    public void setOriCard(String oriCard) {
        this.oriCard = oriCard;
    }
    
    @Column(name="FLAG", length=1)
    
    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    @Column(name="ORI_PV", precision=18)
    
    public BigDecimal getOriPv() {
        return this.oriPv;
    }
    
    public void setOriPv(BigDecimal oriPv) {
        this.oriPv = oriPv;
    }
    
    @Column(name="CHANGE_STATUS", length=1)
    
    public String getChangeStatus() {
        return this.changeStatus;
    }
    
    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }
    
    @Column(name="SUB_STORE_STATUS", length=1)
    
    public String getSubStoreStatus() {
        return this.subStoreStatus;
    }
    
    public void setSubStoreStatus(String subStoreStatus) {
        this.subStoreStatus = subStoreStatus;
    }
    
    @Column(name="SUB_RECOMMEND_STORE", length=20)
    
    public String getSubRecommendStore() {
        return this.subRecommendStore;
    }
    
    public void setSubRecommendStore(String subRecommendStore) {
        this.subRecommendStore = subRecommendStore;
    }
    
    @Column(name="SUB_STORE_CHECK_DATE", length=7)
    
    public Date getSubStoreCheckDate() {
        return this.subStoreCheckDate;
    }
    
    public void setSubStoreCheckDate(Date subStoreCheckDate) {
        this.subStoreCheckDate = subStoreCheckDate;
    }
    
    @Column(name="IDENTITY_TYPE", length=1)
    
    public String getIdentityType() {
        return this.identityType;
    }
    
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }
    
    @Column(name="TOWN_ADDR", length=300)
    
    public String getTownAddr() {
        return this.townAddr;
    }
    
    public void setTownAddr(String townAddr) {
        this.townAddr = townAddr;
    }
    
    @Column(name="COMM_POSTALCODE", length=20)
    
    public String getCommPostalcode() {
        return this.commPostalcode;
    }
    
    public void setCommPostalcode(String commPostalcode) {
        this.commPostalcode = commPostalcode;
    }
    
    @Column(name="COMM_PROVINCE", length=20)
    
    public String getCommProvince() {
        return this.commProvince;
    }
    
    public void setCommProvince(String commProvince) {
        this.commProvince = commProvince;
    }
    
    @Column(name="COMM_CITY", length=20)
    
    public String getCommCity() {
        return this.commCity;
    }
    
    public void setCommCity(String commCity) {
        this.commCity = commCity;
    }
    
    @Column(name="COMM_ADDR", length=300)
    
    public String getCommAddr() {
        return this.commAddr;
    }
    
    public void setCommAddr(String commAddr) {
        this.commAddr = commAddr;
    }
    
    @Column(name="VILLAGE_ADDR", length=300)
    
    public String getVillageAddr() {
        return this.villageAddr;
    }
    
    public void setVillageAddr(String villageAddr) {
        this.villageAddr = villageAddr;
    }
    
    @Column(name="COMPANY_NAME", length=200)
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    @Column(name="PERSON_CHARGE", length=100)
    
    public String getPersonCharge() {
        return this.personCharge;
    }
    
    public void setPersonCharge(String personCharge) {
        this.personCharge = personCharge;
    }
    
    @Column(name="COMPANY_ADDR", length=300)
    
    public String getCompanyAddr() {
        return this.companyAddr;
    }
    
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }
    
    @Column(name="UNITE_NUMBER", length=100)
    
    public String getUniteNumber() {
        return this.uniteNumber;
    }
    
    public void setUniteNumber(String uniteNumber) {
        this.uniteNumber = uniteNumber;
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
    
    @Column(name="COMM_DISTRICT", length=20)
    
    public String getCommDistrict() {
        return this.commDistrict;
    }
    
    public void setCommDistrict(String commDistrict) {
        this.commDistrict = commDistrict;
    }
    
    @Column(name="TOWN", length=20)
    
    public String getTown() {
        return this.town;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    
    @Column(name="IS_DISCOUNT", length=1)
    
    public String getIsDiscount() {
        return this.isDiscount;
    }
    
    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount;
    }
    
    @Column(name="IS_SUB_STORE", length=1)
    
    public String getIsSubStore() {
        return this.isSubStore;
    }
    
    public void setIsSubStore(String isSubStore) {
        this.isSubStore = isSubStore;
    }
    
    @Column(name="ACTIVE_STATUS", length=1)
    
    public String getActiveStatus() {
        return this.activeStatus;
    }
    
    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    @Column(name="FIRST_NAME_KANA", length=100)
    
    public String getFirstNameKana() {
        return this.firstNameKana;
    }
    
    public void setFirstNameKana(String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }
    
    @Column(name="LAST_NAME_KANA", length=100)
    
    public String getLastNameKana() {
        return this.lastNameKana;
    }
    
    public void setLastNameKana(String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }
    
    @Column(name="BANK_TYPE", length=1)
    
    public String getBankType() {
        return this.bankType;
    }
    
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    
    @Column(name="BUILDING", length=500)
    
    public String getBuilding() {
        return this.building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    @Column(name="PAY_TYPE", length=1)
    
    public String getPayType() {
        return this.payType;
    }
    
    public void setPayType(String payType) {
        this.payType = payType;
    }
    
    @Column(name="BANK_CODE", length=100)
    
    public String getBankCode() {
        return this.bankCode;
    }
    
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    
    @Column(name="BANK_KANA", length=100)
    
    public String getBankKana() {
        return this.bankKana;
    }
    
    public void setBankKana(String bankKana) {
        this.bankKana = bankKana;
    }
    
    @Column(name="CUSTOMER_LEVEL", length=3)
    
    public String getCustomerLevel() {
        return this.customerLevel;
    }
    
    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }
    
    @Column(name="NATIONALITY", length=2)
    
    public String getNationality() {
        return this.nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    @Column(name="RECOMMEND_STORE", length=20)
    
    public String getRecommendStore() {
        return this.recommendStore;
    }
    
    public void setRecommendStore(String recommendStore) {
        this.recommendStore = recommendStore;
    }
    
    @Column(name="SHOP_TYPE", length=1)
    
    public String getShopType() {
        return this.shopType;
    }
    
    public void setShopType(String shopType) {
        this.shopType = shopType;
    }
    
    @Column(name="TITLE_REMARK", length=1000)
    
    public String getTitleRemark() {
        return this.titleRemark;
    }
    
    public void setTitleRemark(String titleRemark) {
        this.titleRemark = titleRemark;
    }
    @Column(name="MEMBER_STAR")
    
    public Integer getMemberStar() {
    	return this.memberStar;
    }
    
    public void setMemberStar(Integer memberStar) {
    	this.memberStar = memberStar;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiMember pojo = (JmiMember) o;

        if (linkNo != null ? !linkNo.equals(pojo.linkNo) : pojo.linkNo != null) return false;
        if (recommendNo != null ? !recommendNo.equals(pojo.recommendNo) : pojo.recommendNo != null) return false;
        if (cardType != null ? !cardType.equals(pojo.cardType) : pojo.cardType != null) return false;
        if (firstName != null ? !firstName.equals(pojo.firstName) : pojo.firstName != null) return false;
        if (lastName != null ? !lastName.equals(pojo.lastName) : pojo.lastName != null) return false;
        if (petName != null ? !petName.equals(pojo.petName) : pojo.petName != null) return false;
        if (sex != null ? !sex.equals(pojo.sex) : pojo.sex != null) return false;
        if (birthday != null ? !birthday.equals(pojo.birthday) : pojo.birthday != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (papertype != null ? !papertype.equals(pojo.papertype) : pojo.papertype != null) return false;
        if (papernumber != null ? !papernumber.equals(pojo.papernumber) : pojo.papernumber != null) return false;
        if (bank != null ? !bank.equals(pojo.bank) : pojo.bank != null) return false;
        if (bankaddress != null ? !bankaddress.equals(pojo.bankaddress) : pojo.bankaddress != null) return false;
        if (bankcard != null ? !bankcard.equals(pojo.bankcard) : pojo.bankcard != null) return false;
        if (bankbook != null ? !bankbook.equals(pojo.bankbook) : pojo.bankbook != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (faxtele != null ? !faxtele.equals(pojo.faxtele) : pojo.faxtele != null) return false;
        if (mobiletele != null ? !mobiletele.equals(pojo.mobiletele) : pojo.mobiletele != null) return false;
        if (officetele != null ? !officetele.equals(pojo.officetele) : pojo.officetele != null) return false;
        if (countryCode != null ? !countryCode.equals(pojo.countryCode) : pojo.countryCode != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (postalcode != null ? !postalcode.equals(pojo.postalcode) : pojo.postalcode != null) return false;
        if (exitDate != null ? !exitDate.equals(pojo.exitDate) : pojo.exitDate != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (isstore != null ? !isstore.equals(pojo.isstore) : pojo.isstore != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (checkNo != null ? !checkNo.equals(pojo.checkNo) : pojo.checkNo != null) return false;
        if (checkDate != null ? !checkDate.equals(pojo.checkDate) : pojo.checkDate != null) return false;
        if (createNo != null ? !createNo.equals(pojo.createNo) : pojo.createNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (deadlineDate != null ? !deadlineDate.equals(pojo.deadlineDate) : pojo.deadlineDate != null) return false;
        if (pbNo != null ? !pbNo.equals(pojo.pbNo) : pojo.pbNo != null) return false;
        if (pbNo1 != null ? !pbNo1.equals(pojo.pbNo1) : pojo.pbNo1 != null) return false;
        if (pbNo2 != null ? !pbNo2.equals(pojo.pbNo2) : pojo.pbNo2 != null) return false;
        if (active != null ? !active.equals(pojo.active) : pojo.active != null) return false;
        if (miUserName != null ? !miUserName.equals(pojo.miUserName) : pojo.miUserName != null) return false;
        if (spouseName != null ? !spouseName.equals(pojo.spouseName) : pojo.spouseName != null) return false;
        if (spouseIdno != null ? !spouseIdno.equals(pojo.spouseIdno) : pojo.spouseIdno != null) return false;
        if (bankCity != null ? !bankCity.equals(pojo.bankCity) : pojo.bankCity != null) return false;
        if (bankProvince != null ? !bankProvince.equals(pojo.bankProvince) : pojo.bankProvince != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (linkNum != null ? !linkNum.equals(pojo.linkNum) : pojo.linkNum != null) return false;
        if (pendingLinkNum != null ? !pendingLinkNum.equals(pojo.pendingLinkNum) : pojo.pendingLinkNum != null) return false;
        if (recommendNum != null ? !recommendNum.equals(pojo.recommendNum) : pojo.recommendNum != null) return false;
        if (pendingRecommendNum != null ? !pendingRecommendNum.equals(pojo.pendingRecommendNum) : pojo.pendingRecommendNum != null) return false;
        if (activeTime != null ? !activeTime.equals(pojo.activeTime) : pojo.activeTime != null) return false;
        if (memberType != null ? !memberType.equals(pojo.memberType) : pojo.memberType != null) return false;
        if (oriCard != null ? !oriCard.equals(pojo.oriCard) : pojo.oriCard != null) return false;
        if (flag != null ? !flag.equals(pojo.flag) : pojo.flag != null) return false;
        if (changeStatus != null ? !changeStatus.equals(pojo.changeStatus) : pojo.changeStatus != null) return false;
        if (subStoreStatus != null ? !subStoreStatus.equals(pojo.subStoreStatus) : pojo.subStoreStatus != null) return false;
        if (subRecommendStore != null ? !subRecommendStore.equals(pojo.subRecommendStore) : pojo.subRecommendStore != null) return false;
        if (subStoreCheckDate != null ? !subStoreCheckDate.equals(pojo.subStoreCheckDate) : pojo.subStoreCheckDate != null) return false;
        if (identityType != null ? !identityType.equals(pojo.identityType) : pojo.identityType != null) return false;
        if (townAddr != null ? !townAddr.equals(pojo.townAddr) : pojo.townAddr != null) return false;
        if (commPostalcode != null ? !commPostalcode.equals(pojo.commPostalcode) : pojo.commPostalcode != null) return false;
        if (commProvince != null ? !commProvince.equals(pojo.commProvince) : pojo.commProvince != null) return false;
        if (commCity != null ? !commCity.equals(pojo.commCity) : pojo.commCity != null) return false;
        if (commAddr != null ? !commAddr.equals(pojo.commAddr) : pojo.commAddr != null) return false;
        if (villageAddr != null ? !villageAddr.equals(pojo.villageAddr) : pojo.villageAddr != null) return false;
        if (companyName != null ? !companyName.equals(pojo.companyName) : pojo.companyName != null) return false;
        if (personCharge != null ? !personCharge.equals(pojo.personCharge) : pojo.personCharge != null) return false;
        if (companyAddr != null ? !companyAddr.equals(pojo.companyAddr) : pojo.companyAddr != null) return false;
        if (uniteNumber != null ? !uniteNumber.equals(pojo.uniteNumber) : pojo.uniteNumber != null) return false;
        if (startWeek != null ? !startWeek.equals(pojo.startWeek) : pojo.startWeek != null) return false;
        if (validWeek != null ? !validWeek.equals(pojo.validWeek) : pojo.validWeek != null) return false;
        if (freezeStatus != null ? !freezeStatus.equals(pojo.freezeStatus) : pojo.freezeStatus != null) return false;
        if (beforeFreezeStatus != null ? !beforeFreezeStatus.equals(pojo.beforeFreezeStatus) : pojo.beforeFreezeStatus != null) return false;
        if (commDistrict != null ? !commDistrict.equals(pojo.commDistrict) : pojo.commDistrict != null) return false;
        if (town != null ? !town.equals(pojo.town) : pojo.town != null) return false;
        if (isDiscount != null ? !isDiscount.equals(pojo.isDiscount) : pojo.isDiscount != null) return false;
        if (isSubStore != null ? !isSubStore.equals(pojo.isSubStore) : pojo.isSubStore != null) return false;
        if (activeStatus != null ? !activeStatus.equals(pojo.activeStatus) : pojo.activeStatus != null) return false;
        if (firstNameKana != null ? !firstNameKana.equals(pojo.firstNameKana) : pojo.firstNameKana != null) return false;
        if (lastNameKana != null ? !lastNameKana.equals(pojo.lastNameKana) : pojo.lastNameKana != null) return false;
        if (bankType != null ? !bankType.equals(pojo.bankType) : pojo.bankType != null) return false;
        if (building != null ? !building.equals(pojo.building) : pojo.building != null) return false;
        if (payType != null ? !payType.equals(pojo.payType) : pojo.payType != null) return false;
        if (bankCode != null ? !bankCode.equals(pojo.bankCode) : pojo.bankCode != null) return false;
        if (bankKana != null ? !bankKana.equals(pojo.bankKana) : pojo.bankKana != null) return false;
        if (customerLevel != null ? !customerLevel.equals(pojo.customerLevel) : pojo.customerLevel != null) return false;
        if (nationality != null ? !nationality.equals(pojo.nationality) : pojo.nationality != null) return false;
        if (recommendStore != null ? !recommendStore.equals(pojo.recommendStore) : pojo.recommendStore != null) return false;
        if (shopType != null ? !shopType.equals(pojo.shopType) : pojo.shopType != null) return false;
        if (titleRemark != null ? !titleRemark.equals(pojo.titleRemark) : pojo.titleRemark != null) return false;
        if (memberStar != null ? !memberStar.equals(pojo.memberStar) : pojo.memberStar != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (linkNo != null ? linkNo.hashCode() : 0);
        result = 31 * result + (recommendNo != null ? recommendNo.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (petName != null ? petName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (papertype != null ? papertype.hashCode() : 0);
        result = 31 * result + (papernumber != null ? papernumber.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (bankaddress != null ? bankaddress.hashCode() : 0);
        result = 31 * result + (bankcard != null ? bankcard.hashCode() : 0);
        result = 31 * result + (bankbook != null ? bankbook.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (faxtele != null ? faxtele.hashCode() : 0);
        result = 31 * result + (mobiletele != null ? mobiletele.hashCode() : 0);
        result = 31 * result + (officetele != null ? officetele.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalcode != null ? postalcode.hashCode() : 0);
        result = 31 * result + (exitDate != null ? exitDate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (isstore != null ? isstore.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (checkNo != null ? checkNo.hashCode() : 0);
        result = 31 * result + (checkDate != null ? checkDate.hashCode() : 0);
        result = 31 * result + (createNo != null ? createNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (deadlineDate != null ? deadlineDate.hashCode() : 0);
        result = 31 * result + (pbNo != null ? pbNo.hashCode() : 0);
        result = 31 * result + (pbNo1 != null ? pbNo1.hashCode() : 0);
        result = 31 * result + (pbNo2 != null ? pbNo2.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (miUserName != null ? miUserName.hashCode() : 0);
        result = 31 * result + (spouseName != null ? spouseName.hashCode() : 0);
        result = 31 * result + (spouseIdno != null ? spouseIdno.hashCode() : 0);
        result = 31 * result + (bankCity != null ? bankCity.hashCode() : 0);
        result = 31 * result + (bankProvince != null ? bankProvince.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (linkNum != null ? linkNum.hashCode() : 0);
        result = 31 * result + (pendingLinkNum != null ? pendingLinkNum.hashCode() : 0);
        result = 31 * result + (recommendNum != null ? recommendNum.hashCode() : 0);
        result = 31 * result + (pendingRecommendNum != null ? pendingRecommendNum.hashCode() : 0);
        result = 31 * result + (activeTime != null ? activeTime.hashCode() : 0);
        result = 31 * result + (memberType != null ? memberType.hashCode() : 0);
        result = 31 * result + (oriCard != null ? oriCard.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (changeStatus != null ? changeStatus.hashCode() : 0);
        result = 31 * result + (subStoreStatus != null ? subStoreStatus.hashCode() : 0);
        result = 31 * result + (subRecommendStore != null ? subRecommendStore.hashCode() : 0);
        result = 31 * result + (subStoreCheckDate != null ? subStoreCheckDate.hashCode() : 0);
        result = 31 * result + (identityType != null ? identityType.hashCode() : 0);
        result = 31 * result + (townAddr != null ? townAddr.hashCode() : 0);
        result = 31 * result + (commPostalcode != null ? commPostalcode.hashCode() : 0);
        result = 31 * result + (commProvince != null ? commProvince.hashCode() : 0);
        result = 31 * result + (commCity != null ? commCity.hashCode() : 0);
        result = 31 * result + (commAddr != null ? commAddr.hashCode() : 0);
        result = 31 * result + (villageAddr != null ? villageAddr.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (personCharge != null ? personCharge.hashCode() : 0);
        result = 31 * result + (companyAddr != null ? companyAddr.hashCode() : 0);
        result = 31 * result + (uniteNumber != null ? uniteNumber.hashCode() : 0);
        result = 31 * result + (startWeek != null ? startWeek.hashCode() : 0);
        result = 31 * result + (validWeek != null ? validWeek.hashCode() : 0);
        result = 31 * result + (freezeStatus != null ? freezeStatus.hashCode() : 0);
        result = 31 * result + (beforeFreezeStatus != null ? beforeFreezeStatus.hashCode() : 0);
        result = 31 * result + (commDistrict != null ? commDistrict.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (isDiscount != null ? isDiscount.hashCode() : 0);
        result = 31 * result + (isSubStore != null ? isSubStore.hashCode() : 0);
        result = 31 * result + (activeStatus != null ? activeStatus.hashCode() : 0);
        result = 31 * result + (firstNameKana != null ? firstNameKana.hashCode() : 0);
        result = 31 * result + (lastNameKana != null ? lastNameKana.hashCode() : 0);
        result = 31 * result + (bankType != null ? bankType.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (bankCode != null ? bankCode.hashCode() : 0);
        result = 31 * result + (bankKana != null ? bankKana.hashCode() : 0);
        result = 31 * result + (customerLevel != null ? customerLevel.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (recommendStore != null ? recommendStore.hashCode() : 0);
        result = 31 * result + (shopType != null ? shopType.hashCode() : 0);
        result = 31 * result + (titleRemark != null ? titleRemark.hashCode() : 0);
        result = 31 * result + (memberStar != null ? memberStar.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("linkNo").append("='").append(getLinkNo()).append("', ");
        sb.append("recommendNo").append("='").append(getRecommendNo()).append("', ");
        sb.append("cardType").append("='").append(getCardType()).append("', ");
        sb.append("firstName").append("='").append(getFirstName()).append("', ");
        sb.append("lastName").append("='").append(getLastName()).append("', ");
        sb.append("petName").append("='").append(getPetName()).append("', ");
        sb.append("sex").append("='").append(getSex()).append("', ");
        sb.append("birthday").append("='").append(getBirthday()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("papertype").append("='").append(getPapertype()).append("', ");
        sb.append("papernumber").append("='").append(getPapernumber()).append("', ");
        sb.append("bank").append("='").append(getBank()).append("', ");
        sb.append("bankaddress").append("='").append(getBankaddress()).append("', ");
        sb.append("bankcard").append("='").append(getBankcard()).append("', ");
        sb.append("bankbook").append("='").append(getBankbook()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("', ");
        sb.append("faxtele").append("='").append(getFaxtele()).append("', ");
        sb.append("mobiletele").append("='").append(getMobiletele()).append("', ");
        sb.append("officetele").append("='").append(getOfficetele()).append("', ");
        sb.append("countryCode").append("='").append(getCountryCode()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("postalcode").append("='").append(getPostalcode()).append("', ");
        sb.append("exitDate").append("='").append(getExitDate()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("isstore").append("='").append(getIsstore()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("checkNo").append("='").append(getCheckNo()).append("', ");
        sb.append("checkDate").append("='").append(getCheckDate()).append("', ");
        sb.append("createNo").append("='").append(getCreateNo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("deadlineDate").append("='").append(getDeadlineDate()).append("', ");
        sb.append("pbNo").append("='").append(getPbNo()).append("', ");
        sb.append("pbNo1").append("='").append(getPbNo1()).append("', ");
        sb.append("pbNo2").append("='").append(getPbNo2()).append("', ");
        sb.append("active").append("='").append(getActive()).append("', ");
        sb.append("miUserName").append("='").append(getMiUserName()).append("', ");
        sb.append("spouseName").append("='").append(getSpouseName()).append("', ");
        sb.append("spouseIdno").append("='").append(getSpouseIdno()).append("', ");
        sb.append("bankCity").append("='").append(getBankCity()).append("', ");
        sb.append("bankProvince").append("='").append(getBankProvince()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("linkNum").append("='").append(getLinkNum()).append("', ");
        sb.append("pendingLinkNum").append("='").append(getPendingLinkNum()).append("', ");
        sb.append("recommendNum").append("='").append(getRecommendNum()).append("', ");
        sb.append("pendingRecommendNum").append("='").append(getPendingRecommendNum()).append("', ");
        sb.append("activeTime").append("='").append(getActiveTime()).append("', ");
        sb.append("memberType").append("='").append(getMemberType()).append("', ");
        sb.append("oriCard").append("='").append(getOriCard()).append("', ");
        sb.append("flag").append("='").append(getFlag()).append("', ");
        sb.append("changeStatus").append("='").append(getChangeStatus()).append("', ");
        sb.append("subStoreStatus").append("='").append(getSubStoreStatus()).append("', ");
        sb.append("subRecommendStore").append("='").append(getSubRecommendStore()).append("', ");
        sb.append("subStoreCheckDate").append("='").append(getSubStoreCheckDate()).append("', ");
        sb.append("identityType").append("='").append(getIdentityType()).append("', ");
        sb.append("townAddr").append("='").append(getTownAddr()).append("', ");
        sb.append("commPostalcode").append("='").append(getCommPostalcode()).append("', ");
        sb.append("commProvince").append("='").append(getCommProvince()).append("', ");
        sb.append("commCity").append("='").append(getCommCity()).append("', ");
        sb.append("commAddr").append("='").append(getCommAddr()).append("', ");
        sb.append("villageAddr").append("='").append(getVillageAddr()).append("', ");
        sb.append("companyName").append("='").append(getCompanyName()).append("', ");
        sb.append("personCharge").append("='").append(getPersonCharge()).append("', ");
        sb.append("companyAddr").append("='").append(getCompanyAddr()).append("', ");
        sb.append("uniteNumber").append("='").append(getUniteNumber()).append("', ");
        sb.append("startWeek").append("='").append(getStartWeek()).append("', ");
        sb.append("validWeek").append("='").append(getValidWeek()).append("', ");
        sb.append("freezeStatus").append("='").append(getFreezeStatus()).append("', ");
        sb.append("beforeFreezeStatus").append("='").append(getBeforeFreezeStatus()).append("', ");
        sb.append("commDistrict").append("='").append(getCommDistrict()).append("', ");
        sb.append("town").append("='").append(getTown()).append("', ");
        sb.append("isDiscount").append("='").append(getIsDiscount()).append("', ");
        sb.append("isSubStore").append("='").append(getIsSubStore()).append("', ");
        sb.append("activeStatus").append("='").append(getActiveStatus()).append("', ");
        sb.append("firstNameKana").append("='").append(getFirstNameKana()).append("', ");
        sb.append("lastNameKana").append("='").append(getLastNameKana()).append("', ");
        sb.append("bankType").append("='").append(getBankType()).append("', ");
        sb.append("building").append("='").append(getBuilding()).append("', ");
        sb.append("payType").append("='").append(getPayType()).append("', ");
        sb.append("bankCode").append("='").append(getBankCode()).append("', ");
        sb.append("bankKana").append("='").append(getBankKana()).append("', ");
        sb.append("customerLevel").append("='").append(getCustomerLevel()).append("', ");
        sb.append("nationality").append("='").append(getNationality()).append("', ");
        sb.append("recommendStore").append("='").append(getRecommendStore()).append("', ");
        sb.append("shopType").append("='").append(getShopType()).append("', ");
        sb.append("memberStar").append("='").append(getMemberStar()).append("', ");
        sb.append("titleRemark").append("='").append(getTitleRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

	@OneToMany(cascade=CascadeType.ALL,mappedBy="jmiMember",fetch=FetchType.LAZY)
    @JsonIgnore
	public Set<JmiAddrBook> getJmiAddrBooks() {
		return jmiAddrBooks;
	}

	public void setJmiAddrBooks(Set<JmiAddrBook> jmiAddrBooks) {
		this.jmiAddrBooks = jmiAddrBooks;
	}

    @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY) 
    @PrimaryKeyJoinColumn
    @JsonIgnore
	public JmiLinkRef getJmiLinkRef() {
		return jmiLinkRef;
	}

	public void setJmiLinkRef(JmiLinkRef jmiLinkRef) {
		this.jmiLinkRef = jmiLinkRef;
	}

    @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY) 
    @PrimaryKeyJoinColumn
    @JsonIgnore
	public JmiRecommendRef getJmiRecommendRef() {
		return jmiRecommendRef;
	}

	public void setJmiRecommendRef(JmiRecommendRef jmiRecommendRef) {
		this.jmiRecommendRef = jmiRecommendRef;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)   
    @JoinColumn(name="USER_CODE")  
    @JsonIgnore
	public JsysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(JsysUser sysUser) {
		this.sysUser = sysUser;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="jmiMember",fetch=FetchType.LAZY)
    @JsonIgnore
	public Set<JbdUserValidList> getJbdUserValidList() {
		return jbdUserValidList;
	}

	public void setJbdUserValidList(Set<JbdUserValidList> jbdUserValidList) {
		this.jbdUserValidList = jbdUserValidList;
	}

    
    @Column(name="CL_ADDRESS")
	public String getClAddress() {
		return clAddress;
	}

	public void setClAddress(String clAddress) {
		this.clAddress = clAddress;
	}

    
    @Column(name="FIRST_NAME_PY")
	public String getFirstNamePy() {
		return firstNamePy;
	}

	public void setFirstNamePy(String firstNamePy) {
		this.firstNamePy = firstNamePy;
	}

    
    @Column(name="LAST_NAME_PY")
	public String getLastNamePy() {
		return lastNamePy;
	}

	public void setLastNamePy(String lastNamePy) {
		this.lastNamePy = lastNamePy;
	}

    @Column(name="EC_MALL_PHONE")
	public String getEcMallPhone() {
		return ecMallPhone;
	}

	public void setEcMallPhone(String ecMallPhone) {
		this.ecMallPhone = ecMallPhone;
	}

    @Column(name="EC_MALL_STATUS")
	public String getEcMallStatus() {
		return ecMallStatus;
	}

	public void setEcMallStatus(String ecMallStatus) {
		this.ecMallStatus = ecMallStatus;
	}

}
