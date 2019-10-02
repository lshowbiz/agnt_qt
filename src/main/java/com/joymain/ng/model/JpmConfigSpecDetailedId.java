//package com.joymain.ng.model;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.xml.bind.annotation.XmlRootElement;
//
//@Embeddable
//@XmlRootElement
//public class JpmConfigSpecDetailedId extends BaseObject implements Serializable
//{
//    private Long specNo;
//    
//    // private Long configNo;
//    private String productTemplateNo;
//    
//    private String productTemplateName;
//    
//    private Long complateAmount;
//    
//    private Long complateWeight;
//    
//    private Date createTime;
//    
//    private Long price;
//    
//    @Column(name = "SPEC_NO", precision = 10, scale = 0)
//    public Long getSpecNo()
//    {
//        return this.specNo;
//    }
//    
//    public void setSpecNo(Long specNo)
//    {
//        this.specNo = specNo;
//    }
//    
//    // @Column(name="CONFIG_NO", nullable=false, precision=10, scale=0)
//    // public Long getConfigNo() {
//    // return this.configNo;
//    // }
//    //    
//    // public void setConfigNo(Long configNo) {
//    // this.configNo = configNo;
//    // }
//    
//    @Column(name = "PRICE", precision = 22, scale = 0)
//    public Long getPrice()
//    {
//        return this.price;
//    }
//    
//    public void setPrice(Long price)
//    {
//        this.price = price;
//    }
//    
//    @Column(name = "PRODUCT_TEMPLATE_NO", nullable = false, length = 32)
//    public String getProductTemplateNo()
//    {
//        return this.productTemplateNo;
//    }
//    
//    public void setProductTemplateNo(String productTemplateNo)
//    {
//        this.productTemplateNo = productTemplateNo;
//    }
//    
//    @Column(name = "PRODUCT_TEMPLATE_NAME", length = 100)
//    public String getProductTemplateName()
//    {
//        return this.productTemplateName;
//    }
//    
//    public void setProductTemplateName(String productTemplateName)
//    {
//        this.productTemplateName = productTemplateName;
//    }
//    
//    @Column(name = "COMPLATE_AMOUNT", precision = 10, scale = 0)
//    public Long getComplateAmount()
//    {
//        return this.complateAmount;
//    }
//    
//    public void setComplateAmount(Long complateAmount)
//    {
//        this.complateAmount = complateAmount;
//    }
//    
//    @Column(name = "COMPLATE_WEIGHT", precision = 10, scale = 0)
//    public Long getComplateWeight()
//    {
//        return this.complateWeight;
//    }
//    
//    public void setComplateWeight(Long complateWeight)
//    {
//        this.complateWeight = complateWeight;
//    }
//    
//    @Column(name = "CREATE_TIME", length = 7)
//    public Date getCreateTime()
//    {
//        return this.createTime;
//    }
//    
//    public void setCreateTime(Date createTime)
//    {
//        this.createTime = createTime;
//    }
//    
//    public boolean equals(Object o)
//    {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        
//        JpmConfigSpecDetailedId pojo = (JpmConfigSpecDetailedId) o;
//        
//        if (specNo != null ? !specNo.equals(pojo.specNo) : pojo.specNo != null)
//            return false;
//        // if (configNo != null ? !configNo.equals(pojo.configNo) :
//        // pojo.configNo != null) return false;
//        if (productTemplateNo != null
//            ? !productTemplateNo.equals(pojo.productTemplateNo) : pojo.productTemplateNo != null)
//            return false;
//        if (productTemplateName != null
//            ? !productTemplateName.equals(pojo.productTemplateName)
//            : pojo.productTemplateName != null)
//            return false;
//        if (complateAmount != null
//            ? !complateAmount.equals(pojo.complateAmount) : pojo.complateAmount != null)
//            return false;
//        if (complateWeight != null
//            ? !complateWeight.equals(pojo.complateWeight) : pojo.complateWeight != null)
//            return false;
//        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null)
//            return false;
//        
//        return true;
//    }
//    
//    public int hashCode()
//    {
//        int result = 0;
//        result = 31 * result + (specNo != null ? specNo.hashCode() : 0);
//        // result = (configNo != null ? configNo.hashCode() : 0);
//        result = 31 * result + (productTemplateNo != null ? productTemplateNo.hashCode() : 0);
//        result = 31 * result + (productTemplateName != null ? productTemplateName.hashCode() : 0);
//        result = 31 * result + (complateAmount != null ? complateAmount.hashCode() : 0);
//        result = 31 * result + (complateWeight != null ? complateWeight.hashCode() : 0);
//        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
//        
//        return result;
//    }
//    
//    public String toString()
//    {
//        StringBuffer sb = new StringBuffer(getClass().getSimpleName());
//        
//        sb.append(" [");
//        sb.append("specNo").append("='").append(getSpecNo()).append("', ");
//        // sb.append("configNo").append("='").append(getConfigNo()).append("', ");
//        sb.append("productTemplateNo").append("='").append(getProductTemplateNo()).append("', ");
//        sb.append("productTemplateName")
//            .append("='")
//            .append(getProductTemplateName())
//            .append("', ");
//        sb.append("complateAmount").append("='").append(getComplateAmount()).append("', ");
//        sb.append("complateWeight").append("='").append(getComplateWeight()).append("', ");
//        sb.append("createTime").append("='").append(getCreateTime()).append("'");
//        sb.append("]");
//        
//        return sb.toString();
//    }
//    
//}
