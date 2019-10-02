package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.CascadeType;
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

@Entity
@Table(name="JPO_SHOPPING_CART_ORDER_LIST")

@XmlRootElement
public class JpoShoppingCartOrderList extends BaseObject implements Serializable {
    private Long sclId;
    private JpoShoppingCartOrder jpoShoppingCartOrder;
    private JpmProductSaleTeamType jpmProductSaleTeamType;
    private int qty;
    /**0购买，1强制购买，2失效*/
    private String productStatus;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
	@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="SCL_ID", unique=true, nullable=false, precision=10, scale=0)  
    public Long getSclId() {
        return this.sclId;
    }
    
    public void setSclId(Long sclId) {
        this.sclId = sclId;
    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "SC_ID")
    @JsonIgnore
	public JpoShoppingCartOrder getJpoShoppingCartOrder() {
		return jpoShoppingCartOrder;
	}
 
	public void setJpoShoppingCartOrder(JpoShoppingCartOrder jpoShoppingCartOrder) {
		this.jpoShoppingCartOrder = jpoShoppingCartOrder;
	}
	
	   @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name = "PRODUCT_ID")
	public JpmProductSaleTeamType getJpmProductSaleTeamType() {
		return jpmProductSaleTeamType;
	}

	public void setJpmProductSaleTeamType(
			JpmProductSaleTeamType jpmProductSaleTeamType) {
		this.jpmProductSaleTeamType = jpmProductSaleTeamType;
	}




    
    @Column(name="QTY", nullable=false, precision=5, scale=0)
    
    public int getQty() {
        return this.qty;
    }
    
    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    /** 0购买，1强制购买，2失效*/
    @Column(name="PRODUCT_STATUS", nullable=false, length=2)
    public String getProductStatus() {
        return this.productStatus;
    }
    
    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoShoppingCartOrderList pojo = (JpoShoppingCartOrderList) o;
        if(jpoShoppingCartOrder.getScId()!=null ? !(jpoShoppingCartOrder.getScId()==pojo.jpoShoppingCartOrder.getScId()) : pojo.jpoShoppingCartOrder.getScId()!=null ) return false;
        if(jpmProductSaleTeamType.getPttId()!=null ? !(jpmProductSaleTeamType.getPttId()==pojo.jpmProductSaleTeamType.getPttId()) : pojo.jpmProductSaleTeamType.getPttId()!=null ) return false;
        if (sclId != null ? !sclId.equals(pojo.sclId) : pojo.sclId != null) return false;
        if (productStatus != null ? !productStatus.equals(pojo.productStatus) : pojo.productStatus != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (jpmProductSaleTeamType.getPttId() != null ? jpmProductSaleTeamType.getPttId().hashCode() : 0);
        result = 31 * result + (jpoShoppingCartOrder.getScId() != null ? jpoShoppingCartOrder.getScId().hashCode() : 0);
        result = 31 * result + (sclId != null ? sclId.hashCode() : 0);
        result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sclId").append("='").append(getSclId()).append("', ");
    
        sb.append("qty").append("='").append(getQty()).append("', ");
        sb.append("productStatus").append("='").append(getProductStatus()).append("'");
        sb.append("]");
      
        return sb.toString();
    }




}
