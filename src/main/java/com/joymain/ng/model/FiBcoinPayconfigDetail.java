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
@Table(name="FI_BCOIN_PAYCONFIG_DETAIL")

@XmlRootElement
public class FiBcoinPayconfigDetail extends BaseObject implements Serializable {
    private Long detailId;
    private String productNo;
    private int sumQuantity;
    private int nowQuantity;

    @Id      @Column(name="DETAIL_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getDetailId() {
        return this.detailId;
    }
    
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    
    @Column(name="PRODUCT_NO", nullable=false, length=20)
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="SUM_QUANTITY", nullable=false, precision=10, scale=0)
    public int getSumQuantity() {
        return this.sumQuantity;
    }
    
    public void setSumQuantity(int sumQuantity) {
        this.sumQuantity = sumQuantity;
    }
    
    @Column(name="NOW_QUANTITY", precision=10, scale=0)
    public int getNowQuantity() {
        return this.nowQuantity;
    }
    
    public void setNowQuantity(int nowQuantity) {
        this.nowQuantity = nowQuantity;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiBcoinPayconfigDetail pojo = (FiBcoinPayconfigDetail) o;

        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        //if (sumQuantity != null ? !sumQuantity.equals(pojo.sumQuantity) : pojo.sumQuantity != null) return false;
        //if (nowQuantity != null ? !nowQuantity.equals(pojo.nowQuantity) : pojo.nowQuantity != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (productNo != null ? productNo.hashCode() : 0);
       // result = 31 * result + (sumQuantity != null ? sumQuantity.hashCode() : 0);
       // result = 31 * result + (nowQuantity != null ? nowQuantity.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("detailId").append("='").append(getDetailId()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("sumQuantity").append("='").append(getSumQuantity()).append("', ");
        sb.append("nowQuantity").append("='").append(getNowQuantity()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
