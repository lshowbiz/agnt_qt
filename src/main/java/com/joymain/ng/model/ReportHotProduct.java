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
import javax.persistence.UniqueConstraint;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
/**
 * 每个月的售出商品的数量
 * @author Administrator
 *
 */
@Entity
@Table(name="REPORT_HOT_PRODUCT")

@XmlRootElement
public class ReportHotProduct extends BaseObject implements Serializable {
    
	private Long reportId;
    private Long timeId;//时间维度
    private String provinceId;
    private String cityId;
    private String productNo;
    private Long productNum;
    private String productName;

    @Id      @Column(name="REPORT_ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getReportId() {
        return this.reportId;
    }
    
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
    
    @Column(name="TIME_ID", nullable=false, precision=22, scale=0)
    public Long getTimeId() {
        return this.timeId;
    }
    
    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }
    
    @Column(name="PROVINCE_ID", nullable=false, length=20)
    public String getProvinceId() {
        return this.provinceId;
    }
    
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    
    @Column(name="CITY_ID", nullable=false, length=30)
    public String getCityId() {
        return this.cityId;
    }
    
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    
    @Column(name="PRODUCT_NO", length=20)
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="PRODUCT_NUM", precision=12, scale=0)
    public Long getProductNum() {
        return this.productNum;
    }
    
    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }
    
    @Column(name="PRODUCT_NAME", length=200)
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportHotProduct pojo = (ReportHotProduct) o;

        if (timeId != null ? !timeId.equals(pojo.timeId) : pojo.timeId != null) return false;
        if (provinceId != null ? !provinceId.equals(pojo.provinceId) : pojo.provinceId != null) return false;
        if (cityId != null ? !cityId.equals(pojo.cityId) : pojo.cityId != null) return false;
        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (productNum != null ? !productNum.equals(pojo.productNum) : pojo.productNum != null) return false;
        if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (timeId != null ? timeId.hashCode() : 0);
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (productNum != null ? productNum.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("reportId").append("='").append(getReportId()).append("', ");
        sb.append("timeId").append("='").append(getTimeId()).append("', ");
        sb.append("provinceId").append("='").append(getProvinceId()).append("', ");
        sb.append("cityId").append("='").append(getCityId()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("productNum").append("='").append(getProductNum()).append("', ");
        sb.append("productName").append("='").append(getProductName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
