package com.joymain.ng.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("serial")
@Entity
@Table(name = "JPO_MEMBER_ORDER", uniqueConstraints = @UniqueConstraint(columnNames = "MEMBER_ORDER_NO"))
@XmlRootElement
public class JpoMemberOrder extends BaseObject implements RowMapper<JpoMemberOrder>,Serializable,Cloneable {
    private Long moId;
    private String companyCode;
    private String countryCode;
    private String memberOrderNo;
    private String orderType;
    private JsysUser sysUser;  
    private String userSpCode;
    private BigDecimal amount;
    private BigDecimal amount2;
    private BigDecimal pvAmt;
    /**重消物流费*/
    private BigDecimal consumerAmount;
    private String payMode;
    private String orderUserCode;
    private Date orderTime;
    private String status;
    private Date checkTime;
    private String checkUserCode;
    private Date checkDate;
//  private Clob remark; Modify By WuCF 修改成String类型
    private String remark;
    private String pickup;
    private Date submitTime;
    private String submitUserCode;
    private String submitStatus;
    private String locked;
    private String firstName;
    private String lastName;
    private String province;
    private String city;
    private String address;
    private String postalcode;
    private String phone;
    private String email;
    private String mobiletele;
    private String district;
    private String isPay;
    private String locked1;
    private String locked2;
    private String isSpecial;
    private String checkDateUserCode;
    private String town;
    private String productType;
    private Long motId;
    private String companyPay;
    private String payCode;
    private String shippingPay;
    private Date shippingDay;
    private String building;
    private String shippingSpecial;
    private String shippingCompany;
    private String resendSpNo;
    private String payByCoin;
    private BigDecimal discountAmount;
    private BigDecimal discountPvAmt;
    private String transactionNumber;
    private String payByJj;
    private BigDecimal jjAmount;
    private String isShipping;
    private String isRetreatOrder="0";
    private String isRetreatOrder2="0";
    private String isShipments;
    private String isMobile;//是否为手机客户端购买，1为是，0为否
    private String bdPeriod;
    private String teamCode;//团队标识
    private String isFree;
    
    // Modify By WuCF 20150410 生态家套餐订购
    private String productFlag;
    private Integer stj_price;
    private String stj_lock;
    private String stj_group;
    private Integer stj_movie;
    
    private String exchangeFlag;
    private String paymentType;//Modify By WuCF20160811 支付平台 空:PC端  非空：手机端
	
	private Set<JpoMemberOrderList> jpoMemberOrderList = new HashSet<JpoMemberOrderList>(0);
    private Set<JpoMemberOrderFee> jpoMemberOrderFee = new HashSet<JpoMemberOrderFee>(0);
    
//  //订单配置信息
//  private Set<JpmMemberConfig> jpmMemberConfigList = new HashSet<JpmMemberConfig>(0);
  
	 //商品总数量
	 private Integer productAmount;
	  
	 //已配置商品数量
	 private Integer productConfigAmount;
	  
	 // private Set jprRefund = new HashSet(0);
	 //配置状态
	 private String configStatus;
	  
	 //订单系统状态
	 private Long statusSysMo;
	  
	 //商户号
	 private String saleNo;
	  
	 //是否已推送
	 private Long isSended;
  
    // modify fx 2015-08-06 inter_ok_delivery
	private String interOkDelivery;
    
	//全额支付
	private Integer isFullPay;
	
	private String selfHelpExchange;// modify by fu 2016-03-28 自助换货:Y允许自助换货，N或空表示不允许自助换货
	
	 //add by hdg 2017-01-03 
    private String payByProduct;				//产品积分支付
    private BigDecimal productPointAmount;		//产品积分总金额
    
    private BigDecimal orderAmount;		//订单总金额 不会再变
    private String payByCp;				//是否代金券支付
    private BigDecimal cpValue;			//实际使用代金券金额
    private Long userCpId;//Modify By WuCF 20170524 订单所使用的会员代金券主键，但不对应表中字段
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
	@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="MO_ID", unique=true, nullable=false, precision=10, scale=0)  
    public Long getMoId() {
        return this.moId;
    }
    
    public void setMoId(Long moId) {
        this.moId = moId;
    }
    

	@Column(name = "ISFULL_PAY", length = 2)
	public Integer getIsFullPay() {
		return isFullPay;
	}

	public void setIsFullPay(Integer isFullPay) {
		this.isFullPay = isFullPay;
	}
	
	
    @Column(name="STATUS_SYS_MO")
	public Long getStatusSysMo() {
		return statusSysMo;
	}

	public void setStatusSysMo(Long statusSysMo) {
		this.statusSysMo = statusSysMo;
	}

	@Column(name="EXCHANGE_FLAG")
	public String getExchangeFlag() {
		return exchangeFlag;
	}

	public void setExchangeFlag(String exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}
	@Column(name="SALE_NO", length=20)
    public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	@Column(name="ISSENDED",length=2)
	public Long getIsSended() {
		return isSended;
	}

	public void setIsSended(Long isSended) {
		this.isSended = isSended;
	}

    @Column(name="CONFIG_STATUS", length=2)
    public String getConfigStatus() {
        return this.configStatus;
    }
    
    public void setConfigStatus(String configStatus) {
        this.configStatus = configStatus;
    }
    
    @Transient
    public Integer getProductConfigAmount()
    {
        return productConfigAmount;
    }

    public void setProductConfigAmount(Integer productConfigAmount)
    {
        this.productConfigAmount = productConfigAmount;
    }

    @Transient
    public Integer getProductAmount()
    {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount)
    {
        this.productAmount = productAmount;
    }

    @Column(name="COMPANY_CODE", length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="COUNTRY_CODE", length=2)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    @Column(name="MEMBER_ORDER_NO", unique=true, length=20)
    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }
    
    @Column(name="ORDER_TYPE", length=2)
    
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_CODE")
	@JsonIgnore
	public JsysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(JsysUser sysUser) {
		this.sysUser = sysUser;
	}

    @Column(name="USER_SP_CODE", length=20)
    
    public String getUserSpCode() {
        return this.userSpCode;
    }
    
    public void setUserSpCode(String userSpCode) {
        this.userSpCode = userSpCode;
    }
    
    @Column(name="AMOUNT", precision=18)
    
    public BigDecimal getAmount() {
    	if(this.amount==null){
    		this.amount = BigDecimal.ZERO;
    	}
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Column(name="AMOUNT2", precision=18)
    
    public BigDecimal getAmount2() {
    	if(this.amount2==null){
    		this.amount2 = BigDecimal.ZERO;
    	}
        return this.amount2;
    }
    
    public void setAmount2(BigDecimal amount2) {
        this.amount2 = amount2;
    }
    
    @Column(name="PV_AMT", precision=18)
    
    public BigDecimal getPvAmt() {
    	if(this.pvAmt==null){
    		this.pvAmt = BigDecimal.ZERO;
    	}
        return this.pvAmt;
    }
    
    public void setPvAmt(BigDecimal pvAmt) {
        this.pvAmt = pvAmt;
    }
    
    @Column(name="CONSUMER_AMOUNT", precision=18)
    
    public BigDecimal getConsumerAmount() {
    	if(this.consumerAmount==null){
    		this.consumerAmount = BigDecimal.ZERO;
    	}
        return this.consumerAmount;
    }
    /**重消物流费*/
    public void setConsumerAmount(BigDecimal consumerAmount) {
        this.consumerAmount = consumerAmount;
    }
    
    @Column(name="PAY_MODE", length=1)
    
    public String getPayMode() {
        return this.payMode;
    }
    
    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }
    
    @Column(name="ORDER_USER_CODE", length=20)
    
    public String getOrderUserCode() {
        return this.orderUserCode;
    }
    
    public void setOrderUserCode(String orderUserCode) {
        this.orderUserCode = orderUserCode;
    }
   
    @Column(name="ORDER_TIME", length=7)
    public Date getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    
    @Column(name="STATUS", length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
  
    @Column(name="CHECK_TIME", length=7)
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    
    @Column(name="CHECK_USER_CODE", length=20)
    public String getCheckUserCode() {
        return this.checkUserCode;
    }
    
    public void setCheckUserCode(String checkUserCode) {
        this.checkUserCode = checkUserCode;
    }
   

    @Column(name="CHECK_DATE", length=7)
    public Date getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    
    @Column(name="REMARK")
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="PICKUP", length=5)
    public String getPickup() {
        return this.pickup;
    }
    
    public void setPickup(String pickup) {
        this.pickup = pickup;
    }
   
    @Column(name="SUBMIT_TIME", length=7)
    public Date getSubmitTime() {
        return this.submitTime;
    }
    
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
    
    @Column(name="SUBMIT_USER_CODE", length=20)
    public String getSubmitUserCode() {
        return this.submitUserCode;
    }
    
    public void setSubmitUserCode(String submitUserCode) {
        this.submitUserCode = submitUserCode;
    }
    
    @Column(name="SUBMIT_STATUS", length=1)
    public String getSubmitStatus() {
        return this.submitStatus;
    }
    
    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }
    
    @Column(name="LOCKED", length=1)
    public String getLocked() {
        return this.locked;
    }
    
    public void setLocked(String locked) {
        this.locked = locked;
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
    
    @Column(name="PHONE", length=30)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="EMAIL", length=40)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="MOBILETELE", length=20)
    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    
    @Column(name="DISTRICT", length=20)
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    @Column(name="IS_PAY", length=1)
    public String getIsPay() {
        return this.isPay;
    }
    
    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }
    
    @Column(name="LOCKED1", length=1)
    public String getLocked1() {
        return this.locked1;
    }
    
    public void setLocked1(String locked1) {
        this.locked1 = locked1;
    }
    
    @Column(name="LOCKED2", length=1)
    public String getLocked2() {
        return this.locked2;
    }
    
    public void setLocked2(String locked2) {
        this.locked2 = locked2;
    }
    
    @Column(name="IS_SPECIAL", length=20)
    public String getIsSpecial() {
        return this.isSpecial;
    }
    
    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }
    
    @Column(name="CHECK_DATE_USER_CODE", length=20)
    public String getCheckDateUserCode() {
        return this.checkDateUserCode;
    }
    
    public void setCheckDateUserCode(String checkDateUserCode) {
        this.checkDateUserCode = checkDateUserCode;
    }
    
    @Column(name="TOWN", length=20)
    public String getTown() {
        return this.town;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    
    @Column(name="PRODUCT_TYPE", length=20)
    public String getProductType() {
        return this.productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    @Column(name="MOT_ID", precision=10, scale=0)
    public Long getMotId() {
        return this.motId;
    }
    
    public void setMotId(Long motId) {
        this.motId = motId;
    }
    
    @Column(name="COMPANY_PAY", length=2)
    public String getCompanyPay() {
        return this.companyPay;
    }
    
    public void setCompanyPay(String companyPay) {
        this.companyPay = companyPay;
    }
    
    @Column(name="PAY_CODE", length=100)
    public String getPayCode() {
        return this.payCode;
    }
    
    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }
    
    @Column(name="SHIPPING_PAY", length=1)
    public String getShippingPay() {
        return this.shippingPay;
    }
    
    public void setShippingPay(String shippingPay) {
        this.shippingPay = shippingPay;
    }
  
    @Column(name="SHIPPING_DAY", length=7)
    public Date getShippingDay() {
        return this.shippingDay;
    }
    
    public void setShippingDay(Date shippingDay) {
        this.shippingDay = shippingDay;
    }
    
    @Column(name="BUILDING", length=500)
    public String getBuilding() {
        return this.building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    @Column(name="SHIPPING_SPECIAL", length=2)
    public String getShippingSpecial() {
        return this.shippingSpecial;
    }
    
    public void setShippingSpecial(String shippingSpecial) {
        this.shippingSpecial = shippingSpecial;
    }
    
    @Column(name="SHIPPING_COMPANY", length=20)
    public String getShippingCompany() {
        return this.shippingCompany;
    }
    
    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }
    
    @Column(name="RESEND_SP_NO", length=20)
    public String getResendSpNo() {
        return this.resendSpNo;
    }
    
    public void setResendSpNo(String resendSpNo) {
        this.resendSpNo = resendSpNo;
    }
    
    @Column(name="PAY_BY_COIN", length=2)
    public String getPayByCoin() {
        return this.payByCoin;
    }
    
    public void setPayByCoin(String payByCoin) {
        this.payByCoin = payByCoin;
    }
    
    @Column(name="DISCOUNT_AMOUNT", precision=18)
    public BigDecimal getDiscountAmount() {
    	if(this.discountAmount==null){
    		this.discountAmount=BigDecimal.ZERO;
    	}
        return this.discountAmount;
    }
    
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    @Column(name="DISCOUNT_PV_AMT", precision=18)
    public BigDecimal getDiscountPvAmt() {
    	if(this.discountPvAmt==null){
    		this.discountPvAmt=BigDecimal.ZERO;
    	}
        return this.discountPvAmt;
    }
    
    public void setDiscountPvAmt(BigDecimal discountPvAmt) {
        this.discountPvAmt = discountPvAmt;
    }
    
    @Column(name="TRANSACTION_NUMBER", length=20)
    public String getTransactionNumber() {
        return this.transactionNumber;
    }
    
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
    
    @Column(name="PAY_BY_JJ", length=2)
    public String getPayByJj() {
        return this.payByJj;
    }
    
    public void setPayByJj(String payByJj) {
        this.payByJj = payByJj;
    }
    
    @Column(name="JJ_AMOUNT", precision=18)
    public BigDecimal getJjAmount() {
    	if(this.jjAmount==null){
    		this.jjAmount=BigDecimal.ZERO;
    	}
        return this.jjAmount;
    }
    
    public void setJjAmount(BigDecimal jjAmount) {
        this.jjAmount = jjAmount;
    }
    
    @Column(name="IS_SHIPPING", length=2)
    public String getIsShipping() {
        return this.isShipping;
    }
    
    public void setIsShipping(String isShipping) {
        this.isShipping = isShipping;
    }
    
    @Column(name="IS_RETREAT_ORDER", length=2)
    public String getIsRetreatOrder() {
        return this.isRetreatOrder;
    }
    
    public void setIsRetreatOrder(String isRetreatOrder) {
        this.isRetreatOrder = isRetreatOrder;
    }
    
    @Column(name="IS_RETREAT_ORDER2", length=2)
    public String getIsRetreatOrder2() {
        return this.isRetreatOrder2;
    }
    
    public void setIsRetreatOrder2(String isRetreatOrder2) {
        this.isRetreatOrder2 = isRetreatOrder2;
    }
    
    @Column(name="IS_SHIPMENTS", length=2)
    public String getIsShipments() {
        return this.isShipments;
    }
	@Column(name="ORDER_AMOUNT", precision=18)
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	@Column(name="PAY_BY_CP", length=2)
	public String getPayByCp() {
		return payByCp;
	}

	public void setPayByCp(String payByCp) {
		this.payByCp = payByCp;
	}

	@Column(name="CP_VALUE", precision=18)
	public BigDecimal getCpValue() {
		return cpValue;
	}

	public void setCpValue(BigDecimal cpValue) {
		this.cpValue = cpValue;
	}
    
    public void setIsShipments(String isShipments) {
        this.isShipments = isShipments;
    }
    
    @Column(name="IS_MOBILE",nullable=false, length=2)
	public String getIsMobile() {
		return isMobile;
	}
    /**
     * @param isMobile
     */
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	
	  
	@Column(name="TEAM_CODE", length=10)
	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	@Transient
    public String getBdPeriod() {
		return bdPeriod;
	}

	public void setBdPeriod(String bdPeriod) {
		this.bdPeriod = bdPeriod;
	}
	

	@OneToMany(cascade=CascadeType.ALL,mappedBy="jpoMemberOrder",fetch = FetchType.EAGER)
	public Set<JpoMemberOrderList> getJpoMemberOrderList() {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		return jpoMemberOrderList;
	}

	public void setJpoMemberOrderList(Set<JpoMemberOrderList> jpoMemberOrderList) {
		this.jpoMemberOrderList = jpoMemberOrderList;
	}
	
	@OneToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST},mappedBy="jpoMemberOrder",fetch=FetchType.LAZY)
	public Set<JpoMemberOrderFee> getJpoMemberOrderFee() {
		return jpoMemberOrderFee;
	}

	public void setJpoMemberOrderFee(Set<JpoMemberOrderFee> jpoMemberOrderFee) {
		this.jpoMemberOrderFee = jpoMemberOrderFee;
	}
	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="jpoMemberOrder",fetch=FetchType.EAGER)
//	public Set<JpmMemberConfig> getJpmMemberConfigList() {
//	    return jpmMemberConfigList;
//	}
//
//	public void setJpmMemberConfigList(Set<JpmMemberConfig> jpmMemberConfigList) {
//	    this.jpmMemberConfigList = jpmMemberConfigList;
//	}
	@Transient
	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
/*	 @OneToMany(cascade=CascadeType.ALL,mappedBy="jpoMemberOrder",fetch=FetchType.LAZY)
	public Set getJprRefund() {
		return jprRefund;
	}

	public void setJprRefund(Set jprRefund) {
		this.jprRefund = jprRefund;
	}*/
	
	@Column(name = "INTER_OK_DELIVERY", length = 20)
	public String getInterOkDelivery() {
		return interOkDelivery;
	}

	public void setInterOkDelivery(String interOkDelivery) {
		this.interOkDelivery = interOkDelivery;
	}

	@Column(name = "SELF_HELP_EXCHANGE", length = 20)
    public String getSelfHelpExchange() {
		return selfHelpExchange;
	}

	public void setSelfHelpExchange(String selfHelpExchange) {
		this.selfHelpExchange = selfHelpExchange;
	}

	@Column(name="PAY_BY_PRODUCT", length=2)
	public String getPayByProduct() {
		return payByProduct;
	}

	public void setPayByProduct(String payByProduct) {
		this.payByProduct = payByProduct;
	}

	@Column(name="PRODUCT_AMT", precision=18)
	public BigDecimal getProductPointAmount() {
		return productPointAmount;
	}

	public void setProductPointAmount(BigDecimal productPointAmount) {
		this.productPointAmount = productPointAmount;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoMemberOrder pojo = (JpoMemberOrder) o;
        if (moId != null ? !moId.equals(pojo.moId) : pojo.moId != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (countryCode != null ? !countryCode.equals(pojo.countryCode) : pojo.countryCode != null) return false;
        if (memberOrderNo != null ? !memberOrderNo.equals(pojo.memberOrderNo) : pojo.memberOrderNo != null) return false;
        if (orderType != null ? !orderType.equals(pojo.orderType) : pojo.orderType != null) return false;
    
        if (userSpCode != null ? !userSpCode.equals(pojo.userSpCode) : pojo.userSpCode != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (pvAmt != null ? !pvAmt.equals(pojo.pvAmt) : pojo.pvAmt != null) return false;
        if (consumerAmount != null ? !consumerAmount.equals(pojo.consumerAmount) : pojo.consumerAmount != null) return false;
        if (payMode != null ? !payMode.equals(pojo.payMode) : pojo.payMode != null) return false;
        if (orderUserCode != null ? !orderUserCode.equals(pojo.orderUserCode) : pojo.orderUserCode != null) return false;
        if (orderTime != null ? !orderTime.equals(pojo.orderTime) : pojo.orderTime != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (checkUserCode != null ? !checkUserCode.equals(pojo.checkUserCode) : pojo.checkUserCode != null) return false;
        if (checkDate != null ? !checkDate.equals(pojo.checkDate) : pojo.checkDate != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (pickup != null ? !pickup.equals(pojo.pickup) : pojo.pickup != null) return false;
        if (submitTime != null ? !submitTime.equals(pojo.submitTime) : pojo.submitTime != null) return false;
        if (submitUserCode != null ? !submitUserCode.equals(pojo.submitUserCode) : pojo.submitUserCode != null) return false;
        if (submitStatus != null ? !submitStatus.equals(pojo.submitStatus) : pojo.submitStatus != null) return false;
        if (locked != null ? !locked.equals(pojo.locked) : pojo.locked != null) return false;
        if (firstName != null ? !firstName.equals(pojo.firstName) : pojo.firstName != null) return false;
        if (lastName != null ? !lastName.equals(pojo.lastName) : pojo.lastName != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (postalcode != null ? !postalcode.equals(pojo.postalcode) : pojo.postalcode != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (mobiletele != null ? !mobiletele.equals(pojo.mobiletele) : pojo.mobiletele != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (isPay != null ? !isPay.equals(pojo.isPay) : pojo.isPay != null) return false;
        if (locked1 != null ? !locked1.equals(pojo.locked1) : pojo.locked1 != null) return false;
        if (locked2 != null ? !locked2.equals(pojo.locked2) : pojo.locked2 != null) return false;
        if (isSpecial != null ? !isSpecial.equals(pojo.isSpecial) : pojo.isSpecial != null) return false;
        if (checkDateUserCode != null ? !checkDateUserCode.equals(pojo.checkDateUserCode) : pojo.checkDateUserCode != null) return false;
        if (town != null ? !town.equals(pojo.town) : pojo.town != null) return false;
        if (productType != null ? !productType.equals(pojo.productType) : pojo.productType != null) return false;
        if (motId != null ? !motId.equals(pojo.motId) : pojo.motId != null) return false;
        if (companyPay != null ? !companyPay.equals(pojo.companyPay) : pojo.companyPay != null) return false;
        if (payCode != null ? !payCode.equals(pojo.payCode) : pojo.payCode != null) return false;
        if (shippingPay != null ? !shippingPay.equals(pojo.shippingPay) : pojo.shippingPay != null) return false;
        if (shippingDay != null ? !shippingDay.equals(pojo.shippingDay) : pojo.shippingDay != null) return false;
        if (building != null ? !building.equals(pojo.building) : pojo.building != null) return false;
        if (shippingSpecial != null ? !shippingSpecial.equals(pojo.shippingSpecial) : pojo.shippingSpecial != null) return false;
        if (shippingCompany != null ? !shippingCompany.equals(pojo.shippingCompany) : pojo.shippingCompany != null) return false;
        if (resendSpNo != null ? !resendSpNo.equals(pojo.resendSpNo) : pojo.resendSpNo != null) return false;
        if (payByCoin != null ? !payByCoin.equals(pojo.payByCoin) : pojo.payByCoin != null) return false;
        if (discountAmount != null ? !discountAmount.equals(pojo.discountAmount) : pojo.discountAmount != null) return false;
        if (discountPvAmt != null ? !discountPvAmt.equals(pojo.discountPvAmt) : pojo.discountPvAmt != null) return false;
        if (transactionNumber != null ? !transactionNumber.equals(pojo.transactionNumber) : pojo.transactionNumber != null) return false;
        if (payByJj != null ? !payByJj.equals(pojo.payByJj) : pojo.payByJj != null) return false;
        if (jjAmount != null ? !jjAmount.equals(pojo.jjAmount) : pojo.jjAmount != null) return false;
        if (isShipping != null ? !isShipping.equals(pojo.isShipping) : pojo.isShipping != null) return false;
        if (isRetreatOrder != null ? !isRetreatOrder.equals(pojo.isRetreatOrder) : pojo.isRetreatOrder != null) return false;
        if (isShipments != null ? !isShipments.equals(pojo.isShipments) : pojo.isShipments != null) return false;
        if (interOkDelivery != null ? !interOkDelivery.equals(pojo.interOkDelivery) : pojo.interOkDelivery != null)return false;
        if (selfHelpExchange != null ? !selfHelpExchange.equals(pojo.selfHelpExchange) : pojo.selfHelpExchange != null)return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result +(moId != null ? moId.hashCode() : 0);
        result = 31 * result +(companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (memberOrderNo != null ? memberOrderNo.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
  
        result = 31 * result + (userSpCode != null ? userSpCode.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (pvAmt != null ? pvAmt.hashCode() : 0);
        result = 31 * result + (consumerAmount != null ? consumerAmount.hashCode() : 0);
        result = 31 * result + (payMode != null ? payMode.hashCode() : 0);
        result = 31 * result + (orderUserCode != null ? orderUserCode.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (checkUserCode != null ? checkUserCode.hashCode() : 0);
        result = 31 * result + (checkDate != null ? checkDate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (pickup != null ? pickup.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (submitUserCode != null ? submitUserCode.hashCode() : 0);
        result = 31 * result + (submitStatus != null ? submitStatus.hashCode() : 0);
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalcode != null ? postalcode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobiletele != null ? mobiletele.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (isPay != null ? isPay.hashCode() : 0);
        result = 31 * result + (locked1 != null ? locked1.hashCode() : 0);
        result = 31 * result + (locked2 != null ? locked2.hashCode() : 0);
        result = 31 * result + (isSpecial != null ? isSpecial.hashCode() : 0);
        result = 31 * result + (checkDateUserCode != null ? checkDateUserCode.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (motId != null ? motId.hashCode() : 0);
        result = 31 * result + (companyPay != null ? companyPay.hashCode() : 0);
        result = 31 * result + (payCode != null ? payCode.hashCode() : 0);
        result = 31 * result + (shippingPay != null ? shippingPay.hashCode() : 0);
        result = 31 * result + (shippingDay != null ? shippingDay.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (shippingSpecial != null ? shippingSpecial.hashCode() : 0);
        result = 31 * result + (shippingCompany != null ? shippingCompany.hashCode() : 0);
        result = 31 * result + (resendSpNo != null ? resendSpNo.hashCode() : 0);
        result = 31 * result + (payByCoin != null ? payByCoin.hashCode() : 0);
        result = 31 * result + (discountAmount != null ? discountAmount.hashCode() : 0);
        result = 31 * result + (discountPvAmt != null ? discountPvAmt.hashCode() : 0);
        result = 31 * result + (transactionNumber != null ? transactionNumber.hashCode() : 0);
        result = 31 * result + (payByJj != null ? payByJj.hashCode() : 0);
        result = 31 * result + (jjAmount != null ? jjAmount.hashCode() : 0);
        result = 31 * result + (isShipping != null ? isShipping.hashCode() : 0);
        result = 31 * result + (isRetreatOrder != null ? isRetreatOrder.hashCode() : 0);
        result = 31 * result + (isShipments != null ? isShipments.hashCode() : 0);
		result = 31 * result + (interOkDelivery != null ? interOkDelivery.hashCode() : 0);
		result = 31 * result + (selfHelpExchange != null ? selfHelpExchange.hashCode() : 0);

        
        return result;
    }

    @Override
	public String toString() {
		return "JpoMemberOrder [moId=" + moId + ", companyCode=" + companyCode
				+ ", countryCode=" + countryCode + ", memberOrderNo="
				+ memberOrderNo + ", orderType=" + orderType + ", sysUser="
				+ sysUser + ", userSpCode=" + userSpCode + ", amount=" + amount
				+ ", pvAmt=" + pvAmt + ", consumerAmount=" + consumerAmount
				+ ", payMode=" + payMode + ", orderUserCode=" + orderUserCode
				+ ", orderTime=" + orderTime + ", status=" + status
				+ ", checkTime=" + checkTime + ", checkUserCode="
				+ checkUserCode + ", checkDate=" + checkDate + ", remark="
				+ remark + ", pickup=" + pickup + ", submitTime=" + submitTime
				+ ", submitUserCode=" + submitUserCode + ", submitStatus="
				+ submitStatus + ", locked=" + locked + ", firstName="
				+ firstName + ", lastName=" + lastName + ", province="
				+ province + ", city=" + city + ", address=" + address
				+ ", postalcode=" + postalcode + ", phone=" + phone
				+ ", email=" + email + ", mobiletele=" + mobiletele
				+ ", district=" + district + ", isPay=" + isPay + ", locked1="
				+ locked1 + ", locked2=" + locked2 + ", isSpecial=" + isSpecial
				+ ", checkDateUserCode=" + checkDateUserCode + ", town=" + town
				+ ", productType=" + productType + ", motId=" + motId
				+ ", companyPay=" + companyPay + ", payCode=" + payCode
				+ ", shippingPay=" + shippingPay + ", shippingDay="
				+ shippingDay + ", building=" + building + ", shippingSpecial="
				+ shippingSpecial + ", shippingCompany=" + shippingCompany
				+ ", resendSpNo=" + resendSpNo + ", payByCoin=" + payByCoin
				+ ", discountAmount=" + discountAmount + ", discountPvAmt="
				+ discountPvAmt + ", transactionNumber=" + transactionNumber
				+ ", payByJj=" + payByJj + ", jjAmount=" + jjAmount
				+ ", isShipping=" + isShipping + ", isRetreatOrder="
				+ isRetreatOrder + ", isShipments=" + isShipments
				+ ", isMobile=" + isMobile + ", bdPeriod=" + bdPeriod
				+ ", teamCode=" + teamCode + ", isFree=" + isFree
				+ ", productFlag=" + productFlag + ", stj_price=" + stj_price
				+ ", stj_lock=" + stj_lock + ", stj_group=" + stj_group
				+ ", stj_movie=" + stj_movie + ", jpoMemberOrderList="
				+ jpoMemberOrderList + ", jpoMemberOrderFee="
				+ jpoMemberOrderFee + ", productAmount=" + productAmount
				+ ", productConfigAmount=" + productConfigAmount
				+ ", configStatus=" + configStatus + ", statusSysMo="
				+ statusSysMo + ", saleNo=" + saleNo + ", isSended=" + isSended
				+ ", interOkDelivery=" + interOkDelivery + ", selfHelpExchange=" + selfHelpExchange + "]";
	}

    @Override
    public JpoMemberOrder mapRow(ResultSet arg0, int arg1) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Column(name="PRODUCTFLAG", length=10)
	public String getProductFlag() {
		return productFlag;
	}

	public void setProductFlag(String productFlag) {
		this.productFlag = productFlag;
	}

	public Integer getStj_price() {
		return stj_price;
	}
	
	@Column(name="STJ_PRICE")
	public void setStj_price(Integer stj_price) {
		this.stj_price = stj_price;
	}
	
	public String getStj_lock() {
		return stj_lock;
	}
	
	@Column(name="STJ_LOCK", length=2)
	public void setStj_lock(String stj_lock) {
		this.stj_lock = stj_lock;
	}

	public String getStj_group() {
		return stj_group;
	}
	
	@Column(name="STJ_GROUP")
	public void setStj_group(String stj_group) {
		this.stj_group = stj_group;
	}

	public Integer getStj_movie() {
		return stj_movie;
	}
	/**
	 * 1为已提取
	 * @param stj_movie
	 */
	@Column(name="STJ_MOVIE")
	public void setStj_movie(Integer stj_movie) {
		this.stj_movie = stj_movie;
	}

	@Column(name="PAYMENT_TYPE", length=3)
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		JpoMemberOrder o = null;  
        try {  
            o = (JpoMemberOrder) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
	}
	@Transient
	public Long getUserCpId() {
		return userCpId;
	}

	public void setUserCpId(Long userCpId) {
		this.userCpId = userCpId;
	}
}
