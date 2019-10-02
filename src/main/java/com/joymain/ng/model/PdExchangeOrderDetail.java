package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="PD_EXCHANGE_ORDER_DETAIL")

@XmlRootElement
public class PdExchangeOrderDetail extends BaseObject implements Serializable {
    private Long uniNo;
    private String productNo;
    private BigDecimal price;
    private Long qty;
//    private String eoNo;
    private BigDecimal pv;
    private String erpProductNo;
    private BigDecimal erpPrice;
    private String notChangeProductFlag;//不可换货的标志位
    private String productSource;//换货商品的来源，"O"（Original）表示来自原订单，"N"(New)表示来源于选择新商品
	private PdExchangeOrder pdExchangeOrder;
	/*
       * 由于活力杯产品特殊性，需开发如下功能：
		会员自助换货时，选择活力杯，系统自动送滤芯。该功能仅针对会员自助换货环节，后台换货流程不变；

		产品编码：
		P05080100101CN0 活力杯  
		P05090100101CN0  滤芯（活力杯赠品）
       */
	private String isDonation;	//赠品标志位，为Y表示为赠品，为空或N为非赠品

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "EO_NO")
	@JsonIgnore
    public PdExchangeOrder getPdExchangeOrder() {
		return pdExchangeOrder;
	}

	public void setPdExchangeOrder(PdExchangeOrder pdExchangeOrder) {
		this.pdExchangeOrder = pdExchangeOrder;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
	@Column(name="UNI_NO", unique=true, nullable=false, precision=10, scale=0)     
    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    
    @Column(name="PRODUCT_NO", nullable=false, length=20)
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="NOT_CHANGE_PRODUCT_FLAG", nullable=false, length=1)
    public String getNotChangeProductFlag() {
		return notChangeProductFlag;
	}

	public void setNotChangeProductFlag(String notChangeProductFlag) {
		this.notChangeProductFlag = notChangeProductFlag;
	}
	
	@Column(name="IS_DONATION", nullable=true, length=1)
	public String getIsDonation() {
		return isDonation;
	}

	public void setIsDonation(String isDonation) {
		this.isDonation = isDonation;
	}

	@Column(name="PRICE", nullable=false, precision=12)
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Column(name="PRODUCT_SOURCE", nullable=false, length=1)
    public String getProductSource() {
		return productSource;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}
    
    @Column(name="QTY", nullable=false, precision=10, scale=0)
    public Long getQty() {
        return this.qty;
    }
    
    public void setQty(Long qty) {
        this.qty = qty;
    }

    /*
    @Column(name="EO_NO", length=17)
    public String getEoNo() {
        return this.eoNo;
    }
    
    public void setEoNo(String eoNo) {
        this.eoNo = eoNo;
    }
    */
    
    @Column(name="PV", precision=12)
    public BigDecimal getPv() {
        return this.pv;
    }
    
    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    
    @Column(name="ERP_PRODUCT_NO", length=30)
    public String getErpProductNo() {
        return this.erpProductNo;
    }
    
    public void setErpProductNo(String erpProductNo) {
        this.erpProductNo = erpProductNo;
    }
    
    @Column(name="ERP_PRICE", precision=18)
    public BigDecimal getErpPrice() {
        return this.erpPrice;
    }
    
    public void setErpPrice(BigDecimal erpPrice) {
        this.erpPrice = erpPrice;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdExchangeOrderDetail pojo = (PdExchangeOrderDetail) o;

        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (price != null ? !price.equals(pojo.price) : pojo.price != null) return false;
        if (qty != null ? !qty.equals(pojo.qty) : pojo.qty != null) return false;
        if (pdExchangeOrder.getEoNo() != null ? !pdExchangeOrder.getEoNo().equals(pojo.pdExchangeOrder.getEoNo()) : pojo.pdExchangeOrder.getEoNo() != null) return false;
        if (pv != null ? !pv.equals(pojo.pv) : pojo.pv != null) return false;
        if (erpProductNo != null ? !erpProductNo.equals(pojo.erpProductNo) : pojo.erpProductNo != null) return false;
        if (erpPrice != null ? !erpPrice.equals(pojo.erpPrice) : pojo.erpPrice != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (pdExchangeOrder.getEoNo() != null ? pdExchangeOrder.getEoNo().hashCode() : 0);
        result = 31 * result + (pv != null ? pv.hashCode() : 0);
        result = 31 * result + (erpProductNo != null ? erpProductNo.hashCode() : 0);
        result = 31 * result + (productSource != null ? productSource.hashCode() : 0);
        result = 31 * result + (erpPrice != null ? erpPrice.hashCode() : 0);
        result = 31 * result + (isDonation != null ? isDonation.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("uniNo").append("='").append(getUniNo()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("price").append("='").append(getPrice()).append("', ");
        sb.append("qty").append("='").append(getQty()).append("', ");
        sb.append("eoNo").append("='").append(pdExchangeOrder.getEoNo()).append("', ");
        sb.append("pv").append("='").append(getPv()).append("', ");
        sb.append("erpProductNo").append("='").append(getErpProductNo()).append("', ");
        sb.append("productSource").append("='").append(getProductSource()).append("', ");
        sb.append("erpPrice").append("='").append(getErpPrice()).append("'");
        sb.append("erpPrice").append("='").append(getIsDonation()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
