package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;
import com.joymain.ng.util.data.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="JFI_CHANJET_LOG")

@XmlRootElement
public class JfiChanjetLog extends BaseObject implements Serializable {
	
    private Long logId;
    private String version;
    private String companyCode;
    private String userCode;
    private String merchantId;
    private String signtype;
    private String businessId;
    private String platidtfy;
    private String orderId;
    private String orderDate;
    private String orderAmount;
    private String orderTime;
    private String expireTime;
    private String deviceId;
    private String detailId;
    private String detailTime;
    private String bankId;
    private String bankDealId;
    private String amount;
    private String amtType;
    private String payResult;
    private String errCode;
    private String errMsg;
    private String signMsg;
    private String dataType;
    private String inc;
    private String returnMsg;
    private String url;
    private Date createTime;

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
    
    
    @Column(name="VERSION", length=50)
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    @Column(name="COMPANY_CODE", length=2)
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
    
    @Column(name="MERCHANT_ID", nullable=false, length=50)
    public String getMerchantId() {
        return this.merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    @Column(name="SIGNTYPE", length=2)
    public String getSigntype() {
        return this.signtype;
    }
    
    public void setSigntype(String signtype) {
        this.signtype = signtype;
    }
    
    @Column(name="BUSINESS_ID", length=50)
    public String getBusinessId() {
        return this.businessId;
    }
    
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    
    @Column(name="PLATIDTFY", length=50)
    public String getPlatidtfy() {
        return this.platidtfy;
    }
    
    public void setPlatidtfy(String platidtfy) {
        this.platidtfy = platidtfy;
    }
    
    @Column(name="ORDER_ID", length=50)
    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    @Column(name="ORDER_DATE", length=50)
    public String getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    @Column(name="ORDER_AMOUNT", length=50)
    public String getOrderAmount() {
        return this.orderAmount;
    }
    
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    @Column(name="ORDER_TIME", length=50)
    public String getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    
    @Column(name="EXPIRE_TIME", length=50)
    public String getExpireTime() {
        return this.expireTime;
    }
    
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
    
    @Column(name="DEVICE_ID", length=50)
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    @Column(name="DETAIL_ID", length=50)
    public String getDetailId() {
        return this.detailId;
    }
    
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
    
    @Column(name="DETAIL_TIME", length=50)
    public String getDetailTime() {
        return this.detailTime;
    }
    
    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }
    
    @Column(name="BANK_ID", length=50)
    public String getBankId() {
        return this.bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    
    @Column(name="BANK_DEAL_ID", length=50)
    public String getBankDealId() {
        return this.bankDealId;
    }
    
    public void setBankDealId(String bankDealId) {
        this.bankDealId = bankDealId;
    }
    
    @Column(name="AMOUNT", length=50)
    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    @Column(name="AMT_TYPE", length=2)
    public String getAmtType() {
        return this.amtType;
    }
    
    public void setAmtType(String amtType) {
        this.amtType = amtType;
    }
    
    @Column(name="PAY_RESULT", length=2)
    public String getPayResult() {
        return this.payResult;
    }
    
    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }
    
    @Column(name="ERR_CODE", length=50)
    public String getErrCode() {
        return this.errCode;
    }
    
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    
    @Column(name="ERR_MSG", length=50)
    public String getErrMsg() {
        return this.errMsg;
    }
    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
    @Column(name="SIGN_MSG", length=500)
    public String getSignMsg() {
        return this.signMsg;
    }
    
    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }
    
    @Column(name="DATA_TYPE", length=2)
    public String getDataType() {
        return this.dataType;
    }
    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    @Column(name="INC", length=2)
    public String getInc() {
        return this.inc;
    }
    
    public void setInc(String inc) {
        this.inc = inc;
    }
    
    @Column(name="RETURN_MSG", length=50)
    public String getReturnMsg() {
        return this.returnMsg;
    }
    
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    
    @Column(name="URL", length=1000)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JfiChanjetLog pojo = (JfiChanjetLog) o;

        if (version != null ? !version.equals(pojo.version) : pojo.version != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (merchantId != null ? !merchantId.equals(pojo.merchantId) : pojo.merchantId != null) return false;
        if (signtype != null ? !signtype.equals(pojo.signtype) : pojo.signtype != null) return false;
        if (businessId != null ? !businessId.equals(pojo.businessId) : pojo.businessId != null) return false;
        if (platidtfy != null ? !platidtfy.equals(pojo.platidtfy) : pojo.platidtfy != null) return false;
        if (orderId != null ? !orderId.equals(pojo.orderId) : pojo.orderId != null) return false;
        if (orderDate != null ? !orderDate.equals(pojo.orderDate) : pojo.orderDate != null) return false;
        if (orderAmount != null ? !orderAmount.equals(pojo.orderAmount) : pojo.orderAmount != null) return false;
        if (orderTime != null ? !orderTime.equals(pojo.orderTime) : pojo.orderTime != null) return false;
        if (expireTime != null ? !expireTime.equals(pojo.expireTime) : pojo.expireTime != null) return false;
        if (deviceId != null ? !deviceId.equals(pojo.deviceId) : pojo.deviceId != null) return false;
        if (detailId != null ? !detailId.equals(pojo.detailId) : pojo.detailId != null) return false;
        if (detailTime != null ? !detailTime.equals(pojo.detailTime) : pojo.detailTime != null) return false;
        if (bankId != null ? !bankId.equals(pojo.bankId) : pojo.bankId != null) return false;
        if (bankDealId != null ? !bankDealId.equals(pojo.bankDealId) : pojo.bankDealId != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (amtType != null ? !amtType.equals(pojo.amtType) : pojo.amtType != null) return false;
        if (payResult != null ? !payResult.equals(pojo.payResult) : pojo.payResult != null) return false;
        if (errCode != null ? !errCode.equals(pojo.errCode) : pojo.errCode != null) return false;
        if (errMsg != null ? !errMsg.equals(pojo.errMsg) : pojo.errMsg != null) return false;
        if (signMsg != null ? !signMsg.equals(pojo.signMsg) : pojo.signMsg != null) return false;
        if (dataType != null ? !dataType.equals(pojo.dataType) : pojo.dataType != null) return false;
        if (inc != null ? !inc.equals(pojo.inc) : pojo.inc != null) return false;
        if (returnMsg != null ? !returnMsg.equals(pojo.returnMsg) : pojo.returnMsg != null) return false;
        if (url != null ? !url.equals(pojo.url) : pojo.url != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (version != null ? version.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (signtype != null ? signtype.hashCode() : 0);
        result = 31 * result + (businessId != null ? businessId.hashCode() : 0);
        result = 31 * result + (platidtfy != null ? platidtfy.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderAmount != null ? orderAmount.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (expireTime != null ? expireTime.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (detailId != null ? detailId.hashCode() : 0);
        result = 31 * result + (detailTime != null ? detailTime.hashCode() : 0);
        result = 31 * result + (bankId != null ? bankId.hashCode() : 0);
        result = 31 * result + (bankDealId != null ? bankDealId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (amtType != null ? amtType.hashCode() : 0);
        result = 31 * result + (payResult != null ? payResult.hashCode() : 0);
        result = 31 * result + (errCode != null ? errCode.hashCode() : 0);
        result = 31 * result + (errMsg != null ? errMsg.hashCode() : 0);
        result = 31 * result + (signMsg != null ? signMsg.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (inc != null ? inc.hashCode() : 0);
        result = 31 * result + (returnMsg != null ? returnMsg.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("logId").append("='").append(getLogId()).append("', ");
        sb.append("version").append("='").append(getVersion()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("merchantId").append("='").append(getMerchantId()).append("', ");
        sb.append("signtype").append("='").append(getSigntype()).append("', ");
        sb.append("businessId").append("='").append(getBusinessId()).append("', ");
        sb.append("platidtfy").append("='").append(getPlatidtfy()).append("', ");
        sb.append("orderId").append("='").append(getOrderId()).append("', ");
        sb.append("orderDate").append("='").append(getOrderDate()).append("', ");
        sb.append("orderAmount").append("='").append(getOrderAmount()).append("', ");
        sb.append("orderTime").append("='").append(getOrderTime()).append("', ");
        sb.append("expireTime").append("='").append(getExpireTime()).append("', ");
        sb.append("deviceId").append("='").append(getDeviceId()).append("', ");
        sb.append("detailId").append("='").append(getDetailId()).append("', ");
        sb.append("detailTime").append("='").append(getDetailTime()).append("', ");
        sb.append("bankId").append("='").append(getBankId()).append("', ");
        sb.append("bankDealId").append("='").append(getBankDealId()).append("', ");
        sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("amtType").append("='").append(getAmtType()).append("', ");
        sb.append("payResult").append("='").append(getPayResult()).append("', ");
        sb.append("errCode").append("='").append(getErrCode()).append("', ");
        sb.append("errMsg").append("='").append(getErrMsg()).append("', ");
        sb.append("signMsg").append("='").append(getSignMsg()).append("', ");
        sb.append("dataType").append("='").append(getDataType()).append("', ");
        sb.append("inc").append("='").append(getInc()).append("', ");
        sb.append("returnMsg").append("='").append(getReturnMsg()).append("', ");
        sb.append("url").append("='").append(getUrl()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
