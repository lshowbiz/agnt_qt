package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_PRODUCT_SALE_TEAM_TYPE")

@XmlRootElement
public class JpmProductSaleTeamType extends BaseObject implements Serializable {
    private Long pttId;
//    private Long uniNo;
//    private String teamCode;
    private String orderType;
    private String companyCode;
    private BigDecimal price;
    private BigDecimal pv;
    private String state;
    private String province;

    private JpmProductSaleNew jpmProductSaleNew;
    private JmiMemberTeam jmiMemberTeam;
    
    private String isHidden;
    
    private String productName;  //具体的商品名称
    private String productDesc;	//具体的商品描述
    
    @Transient
    public String getProductDesc() {
    	if(null!=jpmProductSaleNew){
    		productDesc=jpmProductSaleNew.getProductDesc();
    	}
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@Transient
    public String getProductName() {
    	if(null!=jpmProductSaleNew){
    		productName=jpmProductSaleNew.getProductName();
    	}
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Id      @Column(name="PTT_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getPttId() {
        return this.pttId;
    }
    
    public void setPttId(Long pttId) {
        this.pttId = pttId;
    }
     
    
    
   

	@ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "UNI_NO")
    public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}

	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}
	@ManyToOne(cascade=CascadeType.PERSIST)  
	@JoinColumn(name="TEAM_CODE")
	public JmiMemberTeam getJmiMemberTeam() {
		return jmiMemberTeam;
	}

	public void setJmiMemberTeam(JmiMemberTeam jmiMemberTeam) {
		this.jmiMemberTeam = jmiMemberTeam;
	}

	@Column(name="ORDER_TYPE", length=3)    
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="PRICE", precision=18)
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Column(name="PV", precision=10)
    
    public BigDecimal getPv() {
        return this.pv;
    }
    
    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    
    @Column(name="STATE", length=1)
    
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="IS_HIDDEN", length=1)
    public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}
    
    
    @Column(name="PROVINCE", length=6)
    
    public String getProvince() {
        return this.province;
    }
    
   

	public void setProvince(String province) {
        this.province = province;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmProductSaleTeamType pojo = (JpmProductSaleTeamType) o;

//        if (uniNo != null ? !uniNo.equals(pojo.uniNo) : pojo.uniNo != null) return false;
//        if (teamCode != null ? !teamCode.equals(pojo.teamCode) : pojo.teamCode != null) return false;
        if (orderType != null ? !orderType.equals(pojo.orderType) : pojo.orderType != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (price != null ? !price.equals(pojo.price) : pojo.price != null) return false;
        if (pv != null ? !pv.equals(pojo.pv) : pojo.pv != null) return false;
        if (state != null ? !state.equals(pojo.state) : pojo.state != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (jpmProductSaleNew != null ? jpmProductSaleNew.hashCode() : 0);
//        result = 31 * result + (teamCode != null ? teamCode.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (pv != null ? pv.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("pttId").append("='").append(getPttId()).append("', ");
        sb.append("uniNo").append("='").append(getJpmProductSaleNew()).append("', ");
//        sb.append("teamCode").append("='").append(getTeamCode()).append("', ");
        sb.append("orderType").append("='").append(getOrderType()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("price").append("='").append(getPrice()).append("', ");
        sb.append("pv").append("='").append(getPv()).append("', ");
        sb.append("state").append("='").append(getState()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("'");
        sb.append("]");
      
        return sb.toString();
    }



}
