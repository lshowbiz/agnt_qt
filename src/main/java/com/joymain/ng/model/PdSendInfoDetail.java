package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.sql.Clob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="PD_SEND_INFO_DETAIL")

@XmlRootElement
public class PdSendInfoDetail extends BaseObject implements Serializable {
    private Long uniNo;
    private String siNo;
    private String productNo;
    private Long price;
    private Long qty;
    private Long acceptQty;
    private Long weight;
    private String trackingNo;
    private String labelCode;

    private PdSendInfo pdSendInfo;
    
    @Id      @Column(name="UNI_NO", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    
    @Column(name="SI_NO", length=20)
    public String getSiNo() {
        return this.siNo;
    }
    
    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }
    
    @Column(name="PRODUCT_NO", nullable=false, length=20)
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="PRICE", nullable=false, precision=18)
    public Long getPrice() {
        return this.price;
    }
    
    public void setPrice(Long price) {
        this.price = price;
    }
    
    @Column(name="QTY", nullable=false, precision=10, scale=0)
    public Long getQty() {
        return this.qty;
    }
    
    public void setQty(Long qty) {
        this.qty = qty;
    }
    
    @Column(name="ACCEPT_QTY", precision=10, scale=0)
    public Long getAcceptQty() {
        return this.acceptQty;
    }
    
    public void setAcceptQty(Long acceptQty) {
        this.acceptQty = acceptQty;
    }
    
    @Column(name="WEIGHT", precision=18)
    public Long getWeight() {
        return this.weight;
    }
    
    public void setWeight(Long weight) {
        this.weight = weight;
    }
    
    @Column(name="TRACKING_NO", length=200)
    public String getTrackingNo() {
        return this.trackingNo;
    }
    
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
    
    @Column(name="LABEL_CODE")
    public String getLabelCode() {
        return this.labelCode;
    }
    
    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }
    
    @ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "si_no")
    @JsonIgnore
    public PdSendInfo getPdSendInfo() {
		return pdSendInfo;
	}

	public void setPdSendInfo(PdSendInfo pdSendInfo) {
		this.pdSendInfo = pdSendInfo;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdSendInfoDetail pojo = (PdSendInfoDetail) o;

        if (siNo != null ? !siNo.equals(pojo.siNo) : pojo.siNo != null) return false;
        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (price != null ? !price.equals(pojo.price) : pojo.price != null) return false;
        if (qty != null ? !qty.equals(pojo.qty) : pojo.qty != null) return false;
        if (acceptQty != null ? !acceptQty.equals(pojo.acceptQty) : pojo.acceptQty != null) return false;
        if (weight != null ? !weight.equals(pojo.weight) : pojo.weight != null) return false;
        if (trackingNo != null ? !trackingNo.equals(pojo.trackingNo) : pojo.trackingNo != null) return false;
        if (labelCode != null ? !labelCode.equals(pojo.labelCode) : pojo.labelCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (siNo != null ? siNo.hashCode() : 0);
        result = 31 * result + (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (acceptQty != null ? acceptQty.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (trackingNo != null ? trackingNo.hashCode() : 0);
        result = 31 * result + (labelCode != null ? labelCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("uniNo").append("='").append(getUniNo()).append("', ");
        sb.append("siNo").append("='").append(getSiNo()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("price").append("='").append(getPrice()).append("', ");
        sb.append("qty").append("='").append(getQty()).append("', ");
        sb.append("acceptQty").append("='").append(getAcceptQty()).append("', ");
        sb.append("weight").append("='").append(getWeight()).append("', ");
        sb.append("trackingNo").append("='").append(getTrackingNo()).append("', ");
        sb.append("labelCode").append("='").append(getLabelCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
