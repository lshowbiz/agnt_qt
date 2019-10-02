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
//
//@XmlRootElement
//public class JpmConfigDetailedId extends BaseObject implements Serializable {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 3021295687323610260L;
//	
//	private Long configdetailedNo;
////    private Long configNo;
//    private String subNo;
//    private String subName;
//    private Long subAmount;
//    private Date createtime;
//    private String status;
////    private String productNo;
////    private String productName;
////    private Long productAmount;
////    private String productTemplateNo;
//    private Long specNo;
//    private Long price;
//    private String isMustSelected;
//
//
//    @Column(name="CONFIGDETAILED_NO", precision=10, scale=0)
//    public Long getConfigdetailedNo() {
//        return this.configdetailedNo;
//    }
//    
//    public void setConfigdetailedNo(Long configdetailedNo) {
//        this.configdetailedNo = configdetailedNo;
//    }
//
//    @Column(name="IS_MUST_SELECTED", length=1)
//    public String getIsMustSelected() {
//        return this.isMustSelected;
//    }
//    
//    public void setIsMustSelected(String isMustSelected) {
//        this.isMustSelected = isMustSelected;
//    }
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
////    @Column(name="CONFIG_NO", precision=10, scale=0)
////    public Long getConfigNo() {
////        return this.configNo;
////    }
////    
////    public void setConfigNo(Long configNo) {
////        this.configNo = configNo;
////    }
//    
//    @Column(name="SPEC_NO", length=10)
//    public Long getSpecNo()
//    {
//        return specNo;
//    }
//
//    public void setSpecNo(Long specNo)
//    {
//        this.specNo = specNo;
//    }
//
//    @Column(name="SUB_NO", length=32)
//    public String getSubNo() {
//        return this.subNo;
//    }
////    @Column(name="PRODUCT_TEMPLATE_NO", length=200)
////    public String getProductTemplateNo()
////    {
////        return productTemplateNo;
////    }
////
////    public void setProductTemplateNo(String productTemplateNo)
////    {
////        this.productTemplateNo = productTemplateNo;
////    }
//
//    public void setSubNo(String subNo) {
//        this.subNo = subNo;
//    }
//
//    @Column(name="SUB_NAME", length=200)
//    public String getSubName() {
//        return this.subName;
//    }
//    
//    public void setSubName(String subName) {
//        this.subName = subName;
//    }
//
//    @Column(name="SUB_AMOUNT", precision=10, scale=0)
//    public Long getSubAmount() {
//        return this.subAmount;
//    }
//    
//    public void setSubAmount(Long subAmount) {
//        this.subAmount = subAmount;
//    }
//
//    @Column(name="CREATETIME", length=7)
//    public Date getCreatetime() {
//        return this.createtime;
//    }
//    
//    public void setCreatetime(Date createtime) {
//        this.createtime = createtime;
//    }
//
//    @Column(name="STATUS", length=2)
//    public String getStatus() {
//        return this.status;
//    }
//    
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
////    @Column(name="PRODUCT_NO", length=20)
////    public String getProductNo() {
////        return this.productNo;
////    }
////    
////    public void setProductNo(String productNo) {
////        this.productNo = productNo;
////    }
////
////    @Column(name="PRODUCT_NAME", length=200)
////    public String getProductName() {
////        return this.productName;
////    }
////    
////    public void setProductName(String productName) {
////        this.productName = productName;
////    }
//
////    @Column(name="PRODUCT_AMOUNT", precision=22, scale=0)
////    public Long getProductAmount() {
////        return this.productAmount;
////    }
////    
////    public void setProductAmount(Long productAmount) {
////        this.productAmount = productAmount;
////    }
//
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        JpmConfigDetailedId pojo = (JpmConfigDetailedId) o;
//
//        if (configdetailedNo != null ? !configdetailedNo.equals(pojo.configdetailedNo) : pojo.configdetailedNo != null) return false;
////        if (configNo != null ? !configNo.equals(pojo.configNo) : pojo.configNo != null) return false;
//        if (subNo != null ? !subNo.equals(pojo.subNo) : pojo.subNo != null) return false;
//        if (subName != null ? !subName.equals(pojo.subName) : pojo.subName != null) return false;
//        if (subAmount != null ? !subAmount.equals(pojo.subAmount) : pojo.subAmount != null) return false;
//        if (createtime != null ? !createtime.equals(pojo.createtime) : pojo.createtime != null) return false;
//        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
////        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
////        if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null) return false;
////        if (productAmount != null ? !productAmount.equals(pojo.productAmount) : pojo.productAmount != null) return false;
//
//        return true;
//    }
//
//    public int hashCode() {
//        int result = 0;
//        result = 31 * result + (configdetailedNo != null ? configdetailedNo.hashCode() : 0);
////        result = (configNo != null ? configNo.hashCode() : 0);
//        result = 31 * result + (subNo != null ? subNo.hashCode() : 0);
//        result = 31 * result + (subName != null ? subName.hashCode() : 0);
//        result = 31 * result + (subAmount != null ? subAmount.hashCode() : 0);
//        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
//        result = 31 * result + (status != null ? status.hashCode() : 0);
////        result = 31 * result + (productNo != null ? productNo.hashCode() : 0);
////        result = 31 * result + (productName != null ? productName.hashCode() : 0);
////        result = 31 * result + (productAmount != null ? productAmount.hashCode() : 0);
//
//        return result;
//    }
//
//    public String toString() {
//        StringBuffer sb = new StringBuffer(getClass().getSimpleName());
//
//        sb.append(" [");
//        sb.append("configdetailedNo").append("='").append(getConfigdetailedNo()).append("', ");
////        sb.append("configNo").append("='").append(getConfigNo()).append("', ");
//        sb.append("subNo").append("='").append(getSubNo()).append("', ");
//        sb.append("subName").append("='").append(getSubName()).append("', ");
//        sb.append("subAmount").append("='").append(getSubAmount()).append("', ");
//        sb.append("createtime").append("='").append(getCreatetime()).append("', ");
//        sb.append("status").append("='").append(getStatus()).append("', ");
////        sb.append("productNo").append("='").append(getProductNo()).append("', ");
////        sb.append("productName").append("='").append(getProductName()).append("', ");
////        sb.append("productAmount").append("='").append(getProductAmount()).append("'");
//        sb.append("]");
//      
//        return sb.toString();
//    }
//
//}
