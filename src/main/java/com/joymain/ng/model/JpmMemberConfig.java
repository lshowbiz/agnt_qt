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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_MEMBER_CONFIG")

@XmlRootElement
public class JpmMemberConfig extends BaseObject implements Serializable {
    
    private Long configNo;
    /**
     * 公司编码
     */
    private String companyCode;
    /**
     * 用户编码
     */
    private String userCode;
    
    /**
     * 订单编号
     */
    private Long moId;
    
    /**
     * 订单明细编号
     */
    private Long molId;
    private Long amount;
    private Long completeAmount;
    
    /**
     * 状态
     * 0:未提交
     * 1：已提交
     * 2：支付成功
     */
    private String status;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 总价格
     */
    private BigDecimal price;
    
    private Long sysNo;
    
    private Date createTime;
    
    private String productName;
    
    /**
     * 剩余重量
     */
    private Integer oddWeight;
    
    /**
     * 是否允许支付
     * false：不允许
     * true：允许
     */
    private boolean payment;
    
    private Set<JpmConfigSpecDetailed> jpmConfigSpecDetailed = new HashSet<JpmConfigSpecDetailed>(0);
    
    private JpmProductSaleTeamType jpmProductSaleTeamType;
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys")
    @SequenceGenerator(name = "seq_sys", sequenceName = "SEQ_SYS", allocationSize = 1)
    @Id      @Column(name="CONFIG_NO", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getConfigNo() {
        return this.configNo;
    }
    
    public void setConfigNo(Long configNo) {
        this.configNo = configNo;
    }
    
    @Transient
    public boolean getPayment()
    {
        return payment;
    }

    public void setPayment(boolean payment)
    {
        this.payment = payment;
    }

    @Transient
    public Integer getOddWeight()
    {
        return oddWeight;
    }

    public void setOddWeight(Integer oddWeight)
    {
        this.oddWeight = oddWeight;
    }

    @Column(name="PRICE", precision=10)
    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @Column(name="WEIGHT", precision=10)
    public BigDecimal getWeight()
    {
        return weight;
    }

    public void setWeight(BigDecimal weight)
    {
        this.weight = weight;
    }

    @OneToMany(cascade=CascadeType.ALL,mappedBy="jpmMemberConfig",fetch=FetchType.LAZY)
    public Set<JpmConfigSpecDetailed> getJpmConfigSpecDetailed() {
        return jpmConfigSpecDetailed;
    }

    public void setJpmConfigSpecDetailed(Set<JpmConfigSpecDetailed> jpmConfigSpecDetailed) {
        this.jpmConfigSpecDetailed = jpmConfigSpecDetailed;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    @JsonBackReference
    public JpmProductSaleTeamType getJpmProductSaleTeamType() {
        return jpmProductSaleTeamType;
    }

    public void setJpmProductSaleTeamType(
            JpmProductSaleTeamType jpmProductSaleTeamType) {
        this.jpmProductSaleTeamType = jpmProductSaleTeamType;
    }
    
    @Column(name="PRODUCT_NAME", length=200)
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
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
    
    @Column(name="MO_ID", precision=10, scale=0)
    public Long getMoId() {
        return this.moId;
    }
    
    public void setMoId(Long moId) {
        this.moId = moId;
    }
    
    @Column(name="MOL_ID", precision=10, scale=0)
    public Long getMolId()
    {
        return molId;
    }

    public void setMolId(Long molId)
    {
        this.molId = molId;
    }

    @Column(name="AMOUNT", precision=10, scale=0)
    public Long getAmount() {
        return this.amount;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    @Column(name="COMPLETEAMOUNT", precision=10, scale=0)
    public Long getCompleteAmount() {
        return this.completeAmount;
    }
    
    public void setCompleteAmount(Long completeAmount) {
        this.completeAmount = completeAmount;
    }
    
    @Column(name="SYS_NO", precision=2, scale=0)
    public Long getSysNo() {
        return this.sysNo;
    }
    
    public void setSysNo(Long sysNo) {
        this.sysNo = sysNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATETIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="STATUS", length=2)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmMemberConfig pojo = (JpmMemberConfig) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (moId != null ? !moId.equals(pojo.moId) : pojo.moId != null) return false;
        if (molId != null ? !molId.equals(pojo.molId) : pojo.molId != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (completeAmount != null ? !completeAmount.equals(pojo.completeAmount) : pojo.completeAmount != null) return false;
        if (sysNo != null ? !sysNo.equals(pojo.sysNo) : pojo.sysNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (moId != null ? moId.hashCode() : 0);
        result = 31 * result + (molId != null ? molId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (completeAmount != null ? completeAmount.hashCode() : 0);
        result = 31 * result + (sysNo != null ? sysNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("configNo").append("='").append(getConfigNo()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("moId").append("='").append(getMoId()).append("', ");
        sb.append("molId").append("='").append(getMolId()).append("', ");
        sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("completeamount").append("='").append(getCompleteAmount()).append("', ");
        sb.append("sysNo").append("='").append(getSysNo()).append("', ");
        sb.append("createtime").append("='").append(getCreateTime()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
