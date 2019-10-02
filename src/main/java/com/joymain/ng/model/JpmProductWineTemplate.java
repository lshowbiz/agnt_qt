package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_PRODUCT_WINE_TEMPLATE")

@XmlRootElement
public class JpmProductWineTemplate extends BaseObject implements Serializable {
    private Long productTemplateNo;
    private String productNo;
    private String productName;
    private String isDefault;
    private String isInvalid;
    private String memo;
    private Date createTime;
    private String productTemplateName;
    private Long parentQty;
    
    private Set<JpmProductWineTemplateSub> jpmProductWineTemplateSub = new HashSet<JpmProductWineTemplateSub>(0);

    @Id      @Column(name="PRODUCT_TEMPLATE_NO", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getProductTemplateNo() {
        return this.productTemplateNo;
    }
    
    public void setProductTemplateNo(Long productTemplateNo) {
        this.productTemplateNo = productTemplateNo;
    }
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="jpmProductWineTemplate",fetch=FetchType.EAGER)
    public Set<JpmProductWineTemplateSub> getJpmProductWineTemplateSub()
    {
        return jpmProductWineTemplateSub;
    }

    public void setJpmProductWineTemplateSub(Set<JpmProductWineTemplateSub> jpmProductWineTemplateSub)
    {
        this.jpmProductWineTemplateSub = jpmProductWineTemplateSub;
    }
    
    @Column(name="PARENT_QTY", precision=22, scale=0)
    public Long getParentQty()
    {
        return parentQty;
    }

    public void setParentQty(Long parentQty)
    {
        this.parentQty = parentQty;
    }

    @Column(name="PRODUCT_NO", length=20)
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="PRODUCT_NAME", length=200)
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @Column(name="IS_DEFAULT", length=1)
    public String getIsDefault() {
        return this.isDefault;
    }
    
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
    
    @Column(name="IS_INVALID", length=1)
    public String getIsInvalid() {
        return this.isInvalid;
    }
    
    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }
    
    @Column(name="MEMO", length=500)
    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="PRODUCT_TEMPLATE_NAME", length=100)
    public String getProductTemplateName() {
        return this.productTemplateName;
    }
    
    public void setProductTemplateName(String productTemplateName) {
        this.productTemplateName = productTemplateName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmProductWineTemplate pojo = (JpmProductWineTemplate) o;

        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null) return false;
        if (isDefault != null ? !isDefault.equals(pojo.isDefault) : pojo.isDefault != null) return false;
        if (isInvalid != null ? !isInvalid.equals(pojo.isInvalid) : pojo.isInvalid != null) return false;
        if (memo != null ? !memo.equals(pojo.memo) : pojo.memo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (productTemplateName != null ? !productTemplateName.equals(pojo.productTemplateName) : pojo.productTemplateName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (isDefault != null ? isDefault.hashCode() : 0);
        result = 31 * result + (isInvalid != null ? isInvalid.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (productTemplateName != null ? productTemplateName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("productTemplateNo").append("='").append(getProductTemplateNo()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("productName").append("='").append(getProductName()).append("', ");
        sb.append("isDefault").append("='").append(getIsDefault()).append("', ");
        sb.append("isInvalid").append("='").append(getIsInvalid()).append("', ");
        sb.append("memo").append("='").append(getMemo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("productTemplateName").append("='").append(getProductTemplateName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
