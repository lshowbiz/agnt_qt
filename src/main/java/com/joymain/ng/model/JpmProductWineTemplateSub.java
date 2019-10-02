package com.joymain.ng.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "JPM_PRODUCT_WINE_TEMPLATE_SUB")
@XmlRootElement
public class JpmProductWineTemplateSub extends BaseObject implements Serializable {
    private String subNo;

    //    private Long productTemplateNo;
    private String subName;

    private Long price;

    private String specification;

    private Long num;

    private String unit;

    private Long lossRatio;

    private String isMainMaterial;

    private String isSendMaterial;

    private String isDelegateOut;

    private String isFeatureItem;

    private String isMustSelected;

    private String isDefaultSelected;

    private String isNumChange;

    private Long numMax;

    private Long numMin;

    private String productName;

    private String productNo;

    private String isInvalid;

    private JpmProductWineTemplate jpmProductWineTemplate;

    private Set<JpmWineTemplatePicture> jpmWineTemplatePicture = new HashSet<JpmWineTemplatePicture>(0);

    @Id
    @Column(name = "SUB_NO", unique = true, nullable = false, length = 32)
    @DocumentId
    public String getSubNo() {
        return this.subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jpmProductWineTemplateSub", fetch = FetchType.EAGER)
    public Set<JpmWineTemplatePicture> getJpmWineTemplatePicture() {
        return jpmWineTemplatePicture;
    }

    public void setJpmWineTemplatePicture(Set<JpmWineTemplatePicture> jpmWineTemplatePicture) {
        this.jpmWineTemplatePicture = jpmWineTemplatePicture;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_TEMPLATE_NO")
    @JsonIgnore
    public JpmProductWineTemplate getJpmProductWineTemplate() {
        return jpmProductWineTemplate;
    }

    public void setJpmProductWineTemplate(JpmProductWineTemplate jpmProductWineTemplate) {
        this.jpmProductWineTemplate = jpmProductWineTemplate;
    }

    //    @Column(name="PRODUCT_TEMPLATE_NO", precision=22, scale=0)
    //    public Long getProductTemplateNo() {
    //        return this.productTemplateNo;
    //    }
    //    
    //    public void setProductTemplateNo(Long productTemplateNo) {
    //        this.productTemplateNo = productTemplateNo;
    //    }

    @Column(name = "SUB_NAME", length = 200)
    public String getSubName() {
        return this.subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Column(name = "PRICE", precision = 22, scale = 0)
    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Column(name = "SPECIFICATION", length = 50)
    public String getSpecification() {
        return this.specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Column(name = "NUM", precision = 22, scale = 0)
    public Long getNum() {
        return this.num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Column(name = "UNIT", length = 10)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "LOSS_RATIO", precision = 22, scale = 5)
    public Long getLossRatio() {
        return this.lossRatio;
    }

    public void setLossRatio(Long lossRatio) {
        this.lossRatio = lossRatio;
    }

    @Column(name = "IS_MAIN_MATERIAL", length = 1)
    public String getIsMainMaterial() {
        return this.isMainMaterial;
    }

    public void setIsMainMaterial(String isMainMaterial) {
        this.isMainMaterial = isMainMaterial;
    }

    @Column(name = "IS_SEND_MATERIAL", length = 1)
    public String getIsSendMaterial() {
        return this.isSendMaterial;
    }

    public void setIsSendMaterial(String isSendMaterial) {
        this.isSendMaterial = isSendMaterial;
    }

    @Column(name = "IS_DELEGATE_OUT", length = 1)
    public String getIsDelegateOut() {
        return this.isDelegateOut;
    }

    public void setIsDelegateOut(String isDelegateOut) {
        this.isDelegateOut = isDelegateOut;
    }

    @Column(name = "IS_FEATURE_ITEM", length = 1)
    public String getIsFeatureItem() {
        return this.isFeatureItem;
    }

    public void setIsFeatureItem(String isFeatureItem) {
        this.isFeatureItem = isFeatureItem;
    }

    @Column(name = "IS_MUST_SELECTED", length = 1)
    public String getIsMustSelected() {
        return this.isMustSelected;
    }

    public void setIsMustSelected(String isMustSelected) {
        this.isMustSelected = isMustSelected;
    }

    @Column(name = "IS_DEFAULT_SELECTED", length = 1)
    public String getIsDefaultSelected() {
        return this.isDefaultSelected;
    }

    public void setIsDefaultSelected(String isDefaultSelected) {
        this.isDefaultSelected = isDefaultSelected;
    }

    @Column(name = "IS_NUM_CHANGE", length = 1)
    public String getIsNumChange() {
        return this.isNumChange;
    }

    public void setIsNumChange(String isNumChange) {
        this.isNumChange = isNumChange;
    }

    @Column(name = "NUM_MAX", precision = 22, scale = 0)
    public Long getNumMax() {
        return this.numMax;
    }

    public void setNumMax(Long numMax) {
        this.numMax = numMax;
    }

    @Column(name = "NUM_MIN", precision = 22, scale = 0)
    public Long getNumMin() {
        return this.numMin;
    }

    public void setNumMin(Long numMin) {
        this.numMin = numMin;
    }

    @Column(name = "PRODUCT_NAME", length = 200)
    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "PRODUCT_NO", length = 20)
    public String getProductNo() {
        return this.productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Column(name = "IS_INVALID", length = 1)
    public String getIsInvalid() {
        return this.isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        JpmProductWineTemplateSub pojo = (JpmProductWineTemplateSub) o;

        //        if (productTemplateNo != null ? !productTemplateNo.equals(pojo.productTemplateNo) : pojo.productTemplateNo != null) return false;
        if (subName != null ? !subName.equals(pojo.subName) : pojo.subName != null)
            return false;
        if (price != null ? !price.equals(pojo.price) : pojo.price != null)
            return false;
        if (specification != null ? !specification.equals(pojo.specification) : pojo.specification != null)
            return false;
        if (num != null ? !num.equals(pojo.num) : pojo.num != null)
            return false;
        if (unit != null ? !unit.equals(pojo.unit) : pojo.unit != null)
            return false;
        if (lossRatio != null ? !lossRatio.equals(pojo.lossRatio) : pojo.lossRatio != null)
            return false;
        if (isMainMaterial != null ? !isMainMaterial.equals(pojo.isMainMaterial) : pojo.isMainMaterial != null)
            return false;
        if (isSendMaterial != null ? !isSendMaterial.equals(pojo.isSendMaterial) : pojo.isSendMaterial != null)
            return false;
        if (isDelegateOut != null ? !isDelegateOut.equals(pojo.isDelegateOut) : pojo.isDelegateOut != null)
            return false;
        if (isFeatureItem != null ? !isFeatureItem.equals(pojo.isFeatureItem) : pojo.isFeatureItem != null)
            return false;
        if (isMustSelected != null ? !isMustSelected.equals(pojo.isMustSelected) : pojo.isMustSelected != null)
            return false;
        if (isDefaultSelected != null ? !isDefaultSelected.equals(pojo.isDefaultSelected) : pojo.isDefaultSelected != null)
            return false;
        if (isNumChange != null ? !isNumChange.equals(pojo.isNumChange) : pojo.isNumChange != null)
            return false;
        if (numMax != null ? !numMax.equals(pojo.numMax) : pojo.numMax != null)
            return false;
        if (numMin != null ? !numMin.equals(pojo.numMin) : pojo.numMin != null)
            return false;
        if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null)
            return false;
        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null)
            return false;
        if (isInvalid != null ? !isInvalid.equals(pojo.isInvalid) : pojo.isInvalid != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        //        result = (productTemplateNo != null ? productTemplateNo.hashCode() : 0);
        result = 31 * result + (subName != null ? subName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (specification != null ? specification.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (lossRatio != null ? lossRatio.hashCode() : 0);
        result = 31 * result + (isMainMaterial != null ? isMainMaterial.hashCode() : 0);
        result = 31 * result + (isSendMaterial != null ? isSendMaterial.hashCode() : 0);
        result = 31 * result + (isDelegateOut != null ? isDelegateOut.hashCode() : 0);
        result = 31 * result + (isFeatureItem != null ? isFeatureItem.hashCode() : 0);
        result = 31 * result + (isMustSelected != null ? isMustSelected.hashCode() : 0);
        result = 31 * result + (isDefaultSelected != null ? isDefaultSelected.hashCode() : 0);
        result = 31 * result + (isNumChange != null ? isNumChange.hashCode() : 0);
        result = 31 * result + (numMax != null ? numMax.hashCode() : 0);
        result = 31 * result + (numMin != null ? numMin.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (isInvalid != null ? isInvalid.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("subNo").append("='").append(getSubNo()).append("', ");
        //        sb.append("productTemplateNo").append("='").append(getProductTemplateNo()).append("', ");
        sb.append("subName").append("='").append(getSubName()).append("', ");
        sb.append("price").append("='").append(getPrice()).append("', ");
        sb.append("specification").append("='").append(getSpecification()).append("', ");
        sb.append("num").append("='").append(getNum()).append("', ");
        sb.append("unit").append("='").append(getUnit()).append("', ");
        sb.append("lossRatio").append("='").append(getLossRatio()).append("', ");
        sb.append("isMainMaterial").append("='").append(getIsMainMaterial()).append("', ");
        sb.append("isSendMaterial").append("='").append(getIsSendMaterial()).append("', ");
        sb.append("isDelegateOut").append("='").append(getIsDelegateOut()).append("', ");
        sb.append("isFeatureItem").append("='").append(getIsFeatureItem()).append("', ");
        sb.append("isMustSelected").append("='").append(getIsMustSelected()).append("', ");
        sb.append("isDefaultSelected").append("='").append(getIsDefaultSelected()).append("', ");
        sb.append("isNumChange").append("='").append(getIsNumChange()).append("', ");
        sb.append("numMax").append("='").append(getNumMax()).append("', ");
        sb.append("numMin").append("='").append(getNumMin()).append("', ");
        sb.append("productName").append("='").append(getProductName()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("isInvalid").append("='").append(getIsInvalid()).append("'");
        sb.append("]");

        return sb.toString();
    }

}
