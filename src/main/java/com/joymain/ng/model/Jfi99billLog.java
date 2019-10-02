package com.joymain.ng.model;

import java.io.Serializable;
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

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.search.annotations.DocumentId;

import com.joymain.ng.util.data.CustomDateSerializer;

@Entity
@Table(name="JFI_99BILL_LOG")

@XmlRootElement
public class Jfi99billLog extends BaseObject implements Serializable {
    private Long logId;
    private String version;
    private String companyCode;
    private String userCode;
    private String merchantAcctId;
    private String billLanguage;
    private String signType;
    private String payType;
    private String bankId;
    private String orderId;
    private String orderTime;
    private String orderAmount;
    private String dealId;
    private String bankDealId;
    private String dealTime;
    private String payAmount;
    private String fee;
    private String ext1;
    private String ext2;
    private String payResult;
    private String errCode;
    private String signMsg;
    private String url;
    private String verifySignResult;
    private String inc;
    private String referer;
    private String returnMsg;
    private Date createTime;
    private String ip;
    private String dataType;
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="LOG_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    @Column(name="VERSION", length=4000)
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="USER_CODE", nullable=false, length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="MERCHANT_ACCT_ID", length=4000)
    public String getMerchantAcctId() {
        return this.merchantAcctId;
    }
    
    public void setMerchantAcctId(String merchantAcctId) {
        this.merchantAcctId = merchantAcctId;
    }
    
    @Column(name="BILL_LANGUAGE", length=4000)
    public String getBillLanguage() {
        return this.billLanguage;
    }
    
    public void setBillLanguage(String billLanguage) {
        this.billLanguage = billLanguage;
    }
    
    @Column(name="SIGN_TYPE", length=4000)
    public String getSignType() {
        return this.signType;
    }
    
    public void setSignType(String signType) {
        this.signType = signType;
    }
    
    @Column(name="PAY_TYPE", length=4000)
    public String getPayType() {
        return this.payType;
    }
    
    public void setPayType(String payType) {
        this.payType = payType;
    }
    
    @Column(name="BANK_ID", length=4000)
    public String getBankId() {
        return this.bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    
    @Column(name="ORDER_ID", length=4000)
    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    @Column(name="ORDER_TIME", length=4000)
    public String getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    
    @Column(name="ORDER_AMOUNT", length=4000)
    public String getOrderAmount() {
        return this.orderAmount;
    }
    
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    @Column(name="DEAL_ID", length=4000)
    public String getDealId() {
        return this.dealId;
    }
    
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }
    
    @Column(name="BANK_DEAL_ID", length=4000)
    public String getBankDealId() {
        return this.bankDealId;
    }
    
    public void setBankDealId(String bankDealId) {
        this.bankDealId = bankDealId;
    }
    
    @Column(name="DEAL_TIME", length=4000)
    public String getDealTime() {
        return this.dealTime;
    }
    
    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
    
    @Column(name="PAY_AMOUNT", length=4000)
    public String getPayAmount() {
        return this.payAmount;
    }
    
    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
    
    @Column(name="FEE", length=4000)
    public String getFee() {
        return this.fee;
    }
    
    public void setFee(String fee) {
        this.fee = fee;
    }
    
    @Column(name="EXT1", length=4000)
    public String getExt1() {
        return this.ext1;
    }
    
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }
    
    @Column(name="EXT2", length=4000)
    public String getExt2() {
        return this.ext2;
    }
    
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }
    
    @Column(name="PAY_RESULT", length=4000)
    public String getPayResult() {
        return this.payResult;
    }
    
    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }
    
    @Column(name="ERR_CODE", length=4000)
    public String getErrCode() {
        return this.errCode;
    }
    
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    
    @Column(name="SIGN_MSG", length=4000)
    public String getSignMsg() {
        return this.signMsg;
    }
    
    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }
    
    @Column(name="URL", length=4000)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="VERIFY_SIGN_RESULT", length=4000)
    public String getVerifySignResult() {
        return this.verifySignResult;
    }
    
    public void setVerifySignResult(String verifySignResult) {
        this.verifySignResult = verifySignResult;
    }
    
    @Column(name="INC", nullable=false, length=1)
    public String getInc() {
        return this.inc;
    }
    
    public void setInc(String inc) {
        this.inc = inc;
    }
    
    @Column(name="REFERER", length=4000)
    public String getReferer() {
        return this.referer;
    }
    
    public void setReferer(String referer) {
        this.referer = referer;
    }
    
    @Column(name="RETURN_MSG", nullable=false, length=2)
    public String getReturnMsg() {
        return this.returnMsg;
    }
    
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
//    @Temporal(TemporalType.DATE) 
    @Column(name="CREATE_TIME", length=7)
    @JsonSerialize(using =CustomDateSerializer.class)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="IP", length=33)
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    @Column(name="DATA_TYPE", nullable=false, length=1)
    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jfi99billLog pojo = (Jfi99billLog) o;

        if (version != null ? !version.equals(pojo.version) : pojo.version != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (merchantAcctId != null ? !merchantAcctId.equals(pojo.merchantAcctId) : pojo.merchantAcctId != null) return false;
        if (billLanguage != null ? !billLanguage.equals(pojo.billLanguage) : pojo.billLanguage != null) return false;
        if (signType != null ? !signType.equals(pojo.signType) : pojo.signType != null) return false;
        if (payType != null ? !payType.equals(pojo.payType) : pojo.payType != null) return false;
        if (bankId != null ? !bankId.equals(pojo.bankId) : pojo.bankId != null) return false;
        if (orderId != null ? !orderId.equals(pojo.orderId) : pojo.orderId != null) return false;
        if (orderTime != null ? !orderTime.equals(pojo.orderTime) : pojo.orderTime != null) return false;
        if (orderAmount != null ? !orderAmount.equals(pojo.orderAmount) : pojo.orderAmount != null) return false;
        if (dealId != null ? !dealId.equals(pojo.dealId) : pojo.dealId != null) return false;
        if (bankDealId != null ? !bankDealId.equals(pojo.bankDealId) : pojo.bankDealId != null) return false;
        if (dealTime != null ? !dealTime.equals(pojo.dealTime) : pojo.dealTime != null) return false;
        if (payAmount != null ? !payAmount.equals(pojo.payAmount) : pojo.payAmount != null) return false;
        if (fee != null ? !fee.equals(pojo.fee) : pojo.fee != null) return false;
        if (ext1 != null ? !ext1.equals(pojo.ext1) : pojo.ext1 != null) return false;
        if (ext2 != null ? !ext2.equals(pojo.ext2) : pojo.ext2 != null) return false;
        if (payResult != null ? !payResult.equals(pojo.payResult) : pojo.payResult != null) return false;
        if (errCode != null ? !errCode.equals(pojo.errCode) : pojo.errCode != null) return false;
        if (signMsg != null ? !signMsg.equals(pojo.signMsg) : pojo.signMsg != null) return false;
        if (url != null ? !url.equals(pojo.url) : pojo.url != null) return false;
        if (verifySignResult != null ? !verifySignResult.equals(pojo.verifySignResult) : pojo.verifySignResult != null) return false;
        if (inc != null ? !inc.equals(pojo.inc) : pojo.inc != null) return false;
        if (referer != null ? !referer.equals(pojo.referer) : pojo.referer != null) return false;
        if (returnMsg != null ? !returnMsg.equals(pojo.returnMsg) : pojo.returnMsg != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (ip != null ? !ip.equals(pojo.ip) : pojo.ip != null) return false;
        if (dataType != null ? !dataType.equals(pojo.dataType) : pojo.dataType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (version != null ? version.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (merchantAcctId != null ? merchantAcctId.hashCode() : 0);
        result = 31 * result + (billLanguage != null ? billLanguage.hashCode() : 0);
        result = 31 * result + (signType != null ? signType.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (bankId != null ? bankId.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (orderAmount != null ? orderAmount.hashCode() : 0);
        result = 31 * result + (dealId != null ? dealId.hashCode() : 0);
        result = 31 * result + (bankDealId != null ? bankDealId.hashCode() : 0);
        result = 31 * result + (dealTime != null ? dealTime.hashCode() : 0);
        result = 31 * result + (payAmount != null ? payAmount.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (ext1 != null ? ext1.hashCode() : 0);
        result = 31 * result + (ext2 != null ? ext2.hashCode() : 0);
        result = 31 * result + (payResult != null ? payResult.hashCode() : 0);
        result = 31 * result + (errCode != null ? errCode.hashCode() : 0);
        result = 31 * result + (signMsg != null ? signMsg.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (verifySignResult != null ? verifySignResult.hashCode() : 0);
        result = 31 * result + (inc != null ? inc.hashCode() : 0);
        result = 31 * result + (referer != null ? referer.hashCode() : 0);
        result = 31 * result + (returnMsg != null ? returnMsg.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("logId").append("='").append(getLogId()).append("', ");
        sb.append("version").append("='").append(getVersion()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("merchantAcctId").append("='").append(getMerchantAcctId()).append("', ");
        sb.append("billLanguage").append("='").append(getBillLanguage()).append("', ");
        sb.append("signType").append("='").append(getSignType()).append("', ");
        sb.append("payType").append("='").append(getPayType()).append("', ");
        sb.append("bankId").append("='").append(getBankId()).append("', ");
        sb.append("orderId").append("='").append(getOrderId()).append("', ");
        sb.append("orderTime").append("='").append(getOrderTime()).append("', ");
        sb.append("orderAmount").append("='").append(getOrderAmount()).append("', ");
        sb.append("dealId").append("='").append(getDealId()).append("', ");
        sb.append("bankDealId").append("='").append(getBankDealId()).append("', ");
        sb.append("dealTime").append("='").append(getDealTime()).append("', ");
        sb.append("payAmount").append("='").append(getPayAmount()).append("', ");
        sb.append("fee").append("='").append(getFee()).append("', ");
        sb.append("ext1").append("='").append(getExt1()).append("', ");
        sb.append("ext2").append("='").append(getExt2()).append("', ");
        sb.append("payResult").append("='").append(getPayResult()).append("', ");
        sb.append("errCode").append("='").append(getErrCode()).append("', ");
        sb.append("signMsg").append("='").append(getSignMsg()).append("', ");
        sb.append("url").append("='").append(getUrl()).append("', ");
        sb.append("verifySignResult").append("='").append(getVerifySignResult()).append("', ");
        sb.append("inc").append("='").append(getInc()).append("', ");
        sb.append("referer").append("='").append(getReferer()).append("', ");
        sb.append("returnMsg").append("='").append(getReturnMsg()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("ip").append("='").append(getIp()).append("', ");
        sb.append("dataType").append("='").append(getDataType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
