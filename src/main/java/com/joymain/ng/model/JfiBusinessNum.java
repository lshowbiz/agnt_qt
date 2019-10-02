package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JFI_BUSINESS_NUM",schema="JECS")

@XmlRootElement
public class JfiBusinessNum extends BaseObject implements Serializable {
    private Long businessId;
    private Long paymentCompany;
    private String merchantId;
    private String merchantName;
    private Long merchantType;
    private String password;
    private String password2;
    private String busicode;
    private String address;
    private Long maxMoney;
    private Long merchantStatus;
    private String createName;
    private String createTime;
    private String operateName;
    private String operateTime;

    @Id      @Column(name="BUSINESS_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getBusinessId() {
        return this.businessId;
    }
    
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    
    @Column(name="PAYMENT_COMPANY", precision=2, scale=0)
    public Long getPaymentCompany() {
        return this.paymentCompany;
    }
    
    public void setPaymentCompany(Long paymentCompany) {
        this.paymentCompany = paymentCompany;
    }
    
    @Column(name="MERCHANT_ID", length=128)
    public String getMerchantId() {
        return this.merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    @Column(name="MERCHANT_NAME", length=256)
    public String getMerchantName() {
        return this.merchantName;
    }
    
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    
    @Column(name="MERCHANT_TYPE", precision=2, scale=0)
    public Long getMerchantType() {
        return this.merchantType;
    }
    
    public void setMerchantType(Long merchantType) {
        this.merchantType = merchantType;
    }
    
    @Column(name="PASSWORD", length=1024)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="PASSWORD2", length=1024)
    public String getPassword2() {
        return this.password2;
    }
    
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    @Column(name="BUSICODE", length=256)
    public String getBusicode() {
        return this.busicode;
    }
    
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    
    @Column(name="ADDRESS", length=256)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="MAX_MONEY", precision=12, scale=0)
    public Long getMaxMoney() {
        return this.maxMoney;
    }
    
    public void setMaxMoney(Long maxMoney) {
        this.maxMoney = maxMoney;
    }
    
    @Column(name="MERCHANT_STATUS", precision=2, scale=0)
    public Long getMerchantStatus() {
        return this.merchantStatus;
    }
    
    public void setMerchantStatus(Long merchantStatus) {
        this.merchantStatus = merchantStatus;
    }
    
    @Column(name="CREATE_NAME", length=256)
    public String getCreateName() {
        return this.createName;
    }
    
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    
    @Column(name="CREATE_TIME", length=256)
    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="OPERATE_NAME", length=256)
    public String getOperateName() {
        return this.operateName;
    }
    
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }
    
    @Column(name="OPERATE_TIME", length=256)
    public String getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JfiBusinessNum pojo = (JfiBusinessNum) o;

        if (paymentCompany != null ? !paymentCompany.equals(pojo.paymentCompany) : pojo.paymentCompany != null) return false;
        if (merchantId != null ? !merchantId.equals(pojo.merchantId) : pojo.merchantId != null) return false;
        if (merchantName != null ? !merchantName.equals(pojo.merchantName) : pojo.merchantName != null) return false;
        if (merchantType != null ? !merchantType.equals(pojo.merchantType) : pojo.merchantType != null) return false;
        if (password != null ? !password.equals(pojo.password) : pojo.password != null) return false;
        if (password2 != null ? !password2.equals(pojo.password2) : pojo.password2 != null) return false;
        if (busicode != null ? !busicode.equals(pojo.busicode) : pojo.busicode != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (maxMoney != null ? !maxMoney.equals(pojo.maxMoney) : pojo.maxMoney != null) return false;
        if (merchantStatus != null ? !merchantStatus.equals(pojo.merchantStatus) : pojo.merchantStatus != null) return false;
        if (createName != null ? !createName.equals(pojo.createName) : pojo.createName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (operateName != null ? !operateName.equals(pojo.operateName) : pojo.operateName != null) return false;
        if (operateTime != null ? !operateTime.equals(pojo.operateTime) : pojo.operateTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (paymentCompany != null ? paymentCompany.hashCode() : 0);
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (merchantName != null ? merchantName.hashCode() : 0);
        result = 31 * result + (merchantType != null ? merchantType.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (password2 != null ? password2.hashCode() : 0);
        result = 31 * result + (busicode != null ? busicode.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (maxMoney != null ? maxMoney.hashCode() : 0);
        result = 31 * result + (merchantStatus != null ? merchantStatus.hashCode() : 0);
        result = 31 * result + (createName != null ? createName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (operateName != null ? operateName.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("businessId").append("='").append(getBusinessId()).append("', ");
        sb.append("paymentCompany").append("='").append(getPaymentCompany()).append("', ");
        sb.append("merchantId").append("='").append(getMerchantId()).append("', ");
        sb.append("merchantName").append("='").append(getMerchantName()).append("', ");
        sb.append("merchantType").append("='").append(getMerchantType()).append("', ");
        sb.append("password").append("='").append(getPassword()).append("', ");
        sb.append("password2").append("='").append(getPassword2()).append("', ");
        sb.append("busicode").append("='").append(getBusicode()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("maxMoney").append("='").append(getMaxMoney()).append("', ");
        sb.append("merchantStatus").append("='").append(getMerchantStatus()).append("', ");
        sb.append("createName").append("='").append(getCreateName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("operateName").append("='").append(getOperateName()).append("', ");
        sb.append("operateTime").append("='").append(getOperateTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
