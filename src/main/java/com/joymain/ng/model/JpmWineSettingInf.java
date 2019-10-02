package com.joymain.ng.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "JPM_WINE_SETTING_INF")
@XmlRootElement
public class JpmWineSettingInf extends BaseObject implements Serializable {

    private Long settingId;

    private Long productId;

    private String productName;

    private Integer productQty;

    private String unitNo;

    private Integer status;

    private Integer resultcode;

    private String resultdescription;

    private Date createTime;

    private Set<JpmWineSettingListInf> jpmWineSettingListInfSet = new HashSet<JpmWineSettingListInf>();

    @Id
    @SequenceGenerator(name = "seq_pm", sequenceName = "SEQ_PM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pm")
    @Column(name = "SETTING_ID", unique = true, nullable = false, precision = 10, scale = 0)
    @DocumentId
    public Long getSettingId() {
        return this.settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    @Column(name = "PRODUCT_ID", precision = 10, scale = 0)
    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Column(name = "PRODUCT_NAME", length = 200)
    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "PRODUCT_QTY", precision = 5, scale = 0)
    public Integer getProductQty() {
        return this.productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    @Column(name = "UNIT_NO", length = 10)
    public String getUnitNo() {
        return this.unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    @Column(name = "STATUS", precision = 5, scale = 0)
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "RESULTCODE", precision = 5, scale = 0)
    public Integer getResultcode() {
        return this.resultcode;
    }

    public void setResultcode(Integer resultcode) {
        this.resultcode = resultcode;
    }

    @Column(name = "RESULTDESCRIPTION", length = 500)
    public String getResultdescription() {
        return this.resultdescription;
    }

    public void setResultdescription(String resultdescription) {
        this.resultdescription = resultdescription;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME", length = 7, insertable = false, updatable = false)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? new Date() : createTime;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jpmWineSettingInf")
    public Set<JpmWineSettingListInf> getJpmWineSettingListInfSet() {
        return jpmWineSettingListInfSet;
    }

    public void setJpmWineSettingListInfSet(Set<JpmWineSettingListInf> jpmWineSettingListInfSet) {
        this.jpmWineSettingListInfSet = jpmWineSettingListInfSet;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        JpmWineSettingInf pojo = (JpmWineSettingInf) o;

        if (productId != null ? !productId.equals(pojo.productId) : pojo.productId != null)
            return false;
        if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null)
            return false;
        if (productQty != null ? !productQty.equals(pojo.productQty) : pojo.productQty != null)
            return false;
        if (unitNo != null ? !unitNo.equals(pojo.unitNo) : pojo.unitNo != null)
            return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null)
            return false;
        if (resultcode != null ? !resultcode.equals(pojo.resultcode) : pojo.resultcode != null)
            return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null)
            return false;
        if (resultdescription != null ? !resultdescription.equals(pojo.resultdescription) : pojo.resultdescription != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productQty != null ? productQty.hashCode() : 0);
        result = 31 * result + (unitNo != null ? unitNo.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (resultcode != null ? resultcode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (resultdescription != null ? resultdescription.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("settingId").append("='").append(getSettingId()).append("', ");
        sb.append("productId").append("='").append(getProductId()).append("', ");
        sb.append("productName").append("='").append(getProductName()).append("', ");
        sb.append("productQty").append("='").append(getProductQty()).append("', ");
        sb.append("unitNo").append("='").append(getUnitNo()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("resultcode").append("='").append(getResultcode()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("resultdescription").append("='").append(getResultdescription()).append("'");
        sb.append("]");

        return sb.toString();
    }

}
