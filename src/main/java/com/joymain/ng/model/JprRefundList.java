package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JPR_REFUND_LIST")

@XmlRootElement
public class JprRefundList extends BaseObject implements Serializable {
    private Long prlId;
   // private String roNo;
    //private Long productId;
    private JpmProductSaleTeamType jpmProductSaleTeamType;
    private Long price;
    private Long qty;
    private Long pv;
    private Long molId;
    private String erpProductNo;
    private Long erpPrice;
    
    private JprRefund jprRefund;

    @Id      @Column(name="PRL_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getPrlId() {
        return this.prlId;
    }
    
    public void setPrlId(Long prlId) {
        this.prlId = prlId;
    }
    
    /*@Column(name="RO_NO", nullable=false, length=20)
    public String getRoNo() {
        return this.roNo;
    }
    
    public void setRoNo(String roNo) {
        this.roNo = roNo;
    }
    
    @Column(name="PRODUCT_ID", nullable=false, precision=10, scale=0)
    public Long getProductId() {
        return this.productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }*/
    
    @Column(name="PRICE", nullable=false, precision=18)
    public Long getPrice() {
        return this.price;
    }
    
    public void setPrice(Long price) {
        this.price = price;
    }
    
    @Column(name="QTY", nullable=false, precision=5, scale=0)
    public Long getQty() {
        return this.qty;
    }
    
    public void setQty(Long qty) {
        this.qty = qty;
    }
    
    @Column(name="PV", nullable=false, precision=18)
    public Long getPv() {
        return this.pv;
    }
    
    public void setPv(Long pv) {
        this.pv = pv;
    }
    
    @Column(name="MOL_ID", precision=10, scale=0)
    public Long getMolId() {
        return this.molId;
    }
    
    public void setMolId(Long molId) {
        this.molId = molId;
    }
    
    @Column(name="ERP_PRODUCT_NO", length=30)
    public String getErpProductNo() {
        return this.erpProductNo;
    }
    
    public void setErpProductNo(String erpProductNo) {
        this.erpProductNo = erpProductNo;
    }
    
    @Column(name="ERP_PRICE", precision=18)
    public Long getErpPrice() {
        return this.erpPrice;
    }
    
    public void setErpPrice(Long erpPrice) {
        this.erpPrice = erpPrice;
    }

    @ManyToOne(fetch=FetchType.LAZY)  
    @JoinColumn(name = "ro_no")
    @JsonIgnore
    public JprRefund getJprRefund() {
		return jprRefund;
	}

	public void setJprRefund(JprRefund jprRefund) {
		this.jprRefund = jprRefund;
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
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JprRefundList pojo = (JprRefundList) o;

       // if (roNo != null ? !roNo.equals(pojo.roNo) : pojo.roNo != null) return false;
       // if (productId != null ? !productId.equals(pojo.productId) : pojo.productId != null) return false;
        if(jpmProductSaleTeamType.getPttId()!=null ? !(jpmProductSaleTeamType.getPttId()==pojo.jpmProductSaleTeamType.getPttId()) : pojo.jpmProductSaleTeamType.getPttId()!=null ) return false;

        if (price != null ? !price.equals(pojo.price) : pojo.price != null) return false;
        if (qty != null ? !qty.equals(pojo.qty) : pojo.qty != null) return false;
        if (pv != null ? !pv.equals(pojo.pv) : pojo.pv != null) return false;
        if (molId != null ? !molId.equals(pojo.molId) : pojo.molId != null) return false;
        if (erpProductNo != null ? !erpProductNo.equals(pojo.erpProductNo) : pojo.erpProductNo != null) return false;
        if (erpPrice != null ? !erpPrice.equals(pojo.erpPrice) : pojo.erpPrice != null) return false;
        if (jprRefund.getRoNo() != null ? !jprRefund.getRoNo().equals(pojo.jprRefund.getRoNo()) : pojo.jprRefund.getRoNo() != null) return false;

        
        return true;
    }

    public int hashCode() {
        int result = 0;
       // result = (roNo != null ? roNo.hashCode() : 0);
       //result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (jpmProductSaleTeamType.getPttId() != null ? jpmProductSaleTeamType.getPttId().hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (pv != null ? pv.hashCode() : 0);
        result = 31 * result + (molId != null ? molId.hashCode() : 0);
        result = 31 * result + (erpProductNo != null ? erpProductNo.hashCode() : 0);
        result = 31 * result + (erpPrice != null ? erpPrice.hashCode() : 0);
        result = 31 * result + (jprRefund.getRoNo() != null ? jprRefund.getRoNo().hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("prlId").append("='").append(getPrlId()).append("', ");
        //sb.append("roNo").append("='").append(getRoNo()).append("', ");
        //sb.append("productId").append("='").append(getProductId()).append("', ");
        sb.append("price").append("='").append(getPrice()).append("', ");
        sb.append("qty").append("='").append(getQty()).append("', ");
        sb.append("pv").append("='").append(getPv()).append("', ");
        sb.append("molId").append("='").append(getMolId()).append("', ");
        sb.append("erpProductNo").append("='").append(getErpProductNo()).append("', ");
        sb.append("erpPrice").append("='").append(getErpPrice()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
