package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="PD_LOGISTICS_BASE_DETAIL")

@XmlRootElement
public class PdLogisticsBaseDetail extends BaseObject implements Serializable {
    private Long detailId;
    private String numId;
    private String erp_goods_bn;//erp_goods_bn
    private String product_no;//PRODUCT_NO product_no
    private String qty;//--------------------------qty
    private Date createTime;
    private String otherOne;
    private String otherTwo;
    private String combination_product_no;//modify gw 2015-05-07 套餐所属编码
    
    private PdLogisticsBaseNum  pdLogisticsBaseNum;

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
    @Column(name="DETAIL_ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId  
    public Long getDetailId() {
        return this.detailId;
    }
    
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    
    @Column(name="NUM_ID", length=20)
    public String getNumId() {
        return this.numId;
    }
    
    public void setNumId(String numId) {
        this.numId = numId;
    }
    
    @Column(name="ERP_GOODS_BN", length=100)
    public String getErp_goods_bn() {
        return this.erp_goods_bn;
    }
    
    public void setErp_goods_bn(String erp_goods_bn) {
        this.erp_goods_bn = erp_goods_bn;
    }
    
    @Column(name="PRODUCT_NO", length=50)
    public String getProduct_no() {
        return this.product_no;
    }
    
    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }
    
    @Column(name="QTY", length=30)
    public String getQty() {
        return this.qty;
    }
    
    public void setQty(String qty) {
        this.qty = qty;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="OTHER_ONE", length=200)
    public String getOtherOne() {
        return this.otherOne;
    }
    
    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }
    
    @Column(name="OTHER_TWO", length=200)
    public String getOtherTwo() {
        return this.otherTwo;
    }
    
    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }
    
    @Column(name="COMBINATION_PRODUCT_NO", length=50)
    public String getCombination_product_no() {
		return combination_product_no;
	}

	public void setCombination_product_no(String combinationProductNo) {
		combination_product_no = combinationProductNo;
	}

	@ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "num_id")
    @JsonIgnore
    public PdLogisticsBaseNum getPdLogisticsBaseNum() {
		return pdLogisticsBaseNum;
	}

	public void setPdLogisticsBaseNum(PdLogisticsBaseNum pdLogisticsBaseNum) {
		this.pdLogisticsBaseNum = pdLogisticsBaseNum;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdLogisticsBaseDetail pojo = (PdLogisticsBaseDetail) o;

        if (numId != null ? !numId.equals(pojo.numId) : pojo.numId != null) return false;
        if (erp_goods_bn != null ? !erp_goods_bn.equals(pojo.erp_goods_bn) : pojo.erp_goods_bn != null) return false;
        if (product_no != null ? !product_no.equals(pojo.product_no) : pojo.product_no != null) return false;
        if (qty != null ? !qty.equals(pojo.qty) : pojo.qty != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (otherOne != null ? !otherOne.equals(pojo.otherOne) : pojo.otherOne != null) return false;
        if (otherTwo != null ? !otherTwo.equals(pojo.otherTwo) : pojo.otherTwo != null) return false;
        if (combination_product_no != null ? !combination_product_no.equals(pojo.combination_product_no) : pojo.combination_product_no != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (numId != null ? numId.hashCode() : 0);
        result = 31 * result + (erp_goods_bn != null ? erp_goods_bn.hashCode() : 0);
        result = 31 * result + (product_no != null ? product_no.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (otherOne != null ? otherOne.hashCode() : 0);
        result = 31 * result + (otherTwo != null ? otherTwo.hashCode() : 0);
        result = 31 * result + (combination_product_no != null ? combination_product_no.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("detailId").append("='").append(getDetailId()).append("', ");
        sb.append("numId").append("='").append(getNumId()).append("', ");
        sb.append("erp_goods_bn").append("='").append(getErp_goods_bn()).append("', ");
        sb.append("product_no").append("='").append(getProduct_no()).append("', ");
        sb.append("qty").append("='").append(getQty()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("otherOne").append("='").append(getOtherOne()).append("', ");
        sb.append("otherTwo").append("='").append(getOtherTwo()).append("'");
        sb.append("combination_product_no").append("='").append(getCombination_product_no()).append("'");

        sb.append("]");
      
        return sb.toString();
    }

}
