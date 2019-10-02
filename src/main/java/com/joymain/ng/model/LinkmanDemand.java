
package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="LINKMAN_DEMAND")

@XmlRootElement
public class LinkmanDemand extends BaseObject implements Serializable {
    private Long id;
    private String linkmanId;
    private String customerDemand;
    private String customersWishes;
    private String interests;
    private String goodSports;
    private String favoritwActivity;
    private Date registerTime;
    private String meetDemandMeasure;
    private Date executionTime;
    private String whetherExecution;
    private String whetherMeetDemand;
    private String suitableProducts;
    private String other;
    private String userCode;
    private String lcId;
    //客户需求表新加的字段
    private String buyGoods;//所购商品
    private Date buyTime;//购买时间
    private String buyQuantity;//购买数量
    private String productExperience;//产品体验
    private String usingFeedback;//客户反馈
    
    @Id     
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="LINKMAN_ID", length=32)
    public String getLinkmanId() {
        return this.linkmanId;
    }
    
    public void setLinkmanId(String linkmanId) {
        this.linkmanId = linkmanId;
    }
    
    @Column(name="CUSTOMER_DEMAND", length=600)
    public String getCustomerDemand() {
        return this.customerDemand;
    }
    
    public void setCustomerDemand(String customerDemand) {
        this.customerDemand = customerDemand;
    }
    
    @Column(name="CUSTOMERS_WISHES", length=600)
    public String getCustomersWishes() {
        return this.customersWishes;
    }
    
    public void setCustomersWishes(String customersWishes) {
        this.customersWishes = customersWishes;
    }
    
    @Column(name="INTERESTS", length=600)
    public String getInterests() {
        return this.interests;
    }
    
    public void setInterests(String interests) {
        this.interests = interests;
    }
    
    @Column(name="GOOD_SPORTS", length=500)
    public String getGoodSports() {
        return this.goodSports;
    }
    
    public void setGoodSports(String goodSports) {
        this.goodSports = goodSports;
    }
    
    @Column(name="FAVORITW_ACTIVITY", length=500)
    public String getFavoritwActivity() {
        return this.favoritwActivity;
    }
    
    public void setFavoritwActivity(String favoritwActivity) {
        this.favoritwActivity = favoritwActivity;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="REGISTER_TIME", length=7)
    public Date getRegisterTime() {
        return this.registerTime;
    }
    
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
    
    @Column(name="MEET_DEMAND_MEASURE", length=800)
    public String getMeetDemandMeasure() {
        return this.meetDemandMeasure;
    }
    
    public void setMeetDemandMeasure(String meetDemandMeasure) {
        this.meetDemandMeasure = meetDemandMeasure;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXECUTION_TIME", length=7)
    public Date getExecutionTime() {
        return this.executionTime;
    }
    
    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }
    
    @Column(name="WHETHER_EXECUTION", length=3)
    public String getWhetherExecution() {
        return this.whetherExecution;
    }
    
    public void setWhetherExecution(String whetherExecution) {
        this.whetherExecution = whetherExecution;
    }
    
    @Column(name="WHETHER_MEET_DEMAND", length=3)
    public String getWhetherMeetDemand() {
        return this.whetherMeetDemand;
    }
    
    public void setWhetherMeetDemand(String whetherMeetDemand) {
        this.whetherMeetDemand = whetherMeetDemand;
    }
    
    @Column(name="SUITABLE_PRODUCTS", length=800)
    public String getSuitableProducts() {
        return this.suitableProducts;
    }
    
    public void setSuitableProducts(String suitableProducts) {
        this.suitableProducts = suitableProducts;
    }
    
    @Column(name="OTHER", length=100)
    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
    
    @Column(name="USER_CODE", length=20)
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name="LC_ID",length=32)
	public String getLcId() {
		return lcId;
	}
	
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}
	
	@Column(name="BUY_GOODS",length=500)
	public String getBuyGoods() {
		return buyGoods;
	}

	public void setBuyGoods(String buyGoods) {
		this.buyGoods = buyGoods;
	}
	
	@Temporal(TemporalType.DATE)
    @Column(name="BUY_TIME", length=7)
	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	@Column(name="BUY_QUANTITY",length=10)
	public String getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(String buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	@Column(name="PRODUCT_EXPERIENCE",length=1000)
	public String getProductExperience() {
		return productExperience;
	}

	public void setProductExperience(String productExperience) {
		this.productExperience = productExperience;
	}

	@Column(name="USING_FEEDBACK",length=1000)
	public String getUsingFeedback() {
		return usingFeedback;
	}

	public void setUsingFeedback(String usingFeedback) {
		this.usingFeedback = usingFeedback;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkmanDemand pojo = (LinkmanDemand) o;

        if (linkmanId != null ? !linkmanId.equals(pojo.linkmanId) : pojo.linkmanId != null) return false;
        if (customerDemand != null ? !customerDemand.equals(pojo.customerDemand) : pojo.customerDemand != null) return false;
        if (customersWishes != null ? !customersWishes.equals(pojo.customersWishes) : pojo.customersWishes != null) return false;
        if (interests != null ? !interests.equals(pojo.interests) : pojo.interests != null) return false;
        if (goodSports != null ? !goodSports.equals(pojo.goodSports) : pojo.goodSports != null) return false;
        if (favoritwActivity != null ? !favoritwActivity.equals(pojo.favoritwActivity) : pojo.favoritwActivity != null) return false;
        if (registerTime != null ? !registerTime.equals(pojo.registerTime) : pojo.registerTime != null) return false;
        if (meetDemandMeasure != null ? !meetDemandMeasure.equals(pojo.meetDemandMeasure) : pojo.meetDemandMeasure != null) return false;
        if (executionTime != null ? !executionTime.equals(pojo.executionTime) : pojo.executionTime != null) return false;
        if (whetherExecution != null ? !whetherExecution.equals(pojo.whetherExecution) : pojo.whetherExecution != null) return false;
        if (whetherMeetDemand != null ? !whetherMeetDemand.equals(pojo.whetherMeetDemand) : pojo.whetherMeetDemand != null) return false;
        if (suitableProducts != null ? !suitableProducts.equals(pojo.suitableProducts) : pojo.suitableProducts != null) return false;
        if (other != null ? !other.equals(pojo.other) : pojo.other != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (lcId != null ? !lcId.equals(pojo.lcId) : pojo.lcId != null) return false;
        
        if (buyGoods != null ? !buyGoods.equals(pojo.buyGoods) : pojo.buyGoods != null) return false;
        if (buyTime != null ? !buyTime.equals(pojo.buyTime) : pojo.buyTime != null) return false;
        if (buyQuantity != null ? !buyQuantity.equals(pojo.buyQuantity) : pojo.buyQuantity != null) return false;
        if (productExperience != null ? !productExperience.equals(pojo.productExperience) : pojo.productExperience != null) return false;
        if (usingFeedback != null ? !usingFeedback.equals(pojo.usingFeedback) : pojo.usingFeedback != null) return false;


        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (linkmanId != null ? linkmanId.hashCode() : 0);
        result = 31 * result + (customerDemand != null ? customerDemand.hashCode() : 0);
        result = 31 * result + (customersWishes != null ? customersWishes.hashCode() : 0);
        result = 31 * result + (interests != null ? interests.hashCode() : 0);
        result = 31 * result + (goodSports != null ? goodSports.hashCode() : 0);
        result = 31 * result + (favoritwActivity != null ? favoritwActivity.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        result = 31 * result + (meetDemandMeasure != null ? meetDemandMeasure.hashCode() : 0);
        result = 31 * result + (executionTime != null ? executionTime.hashCode() : 0);
        result = 31 * result + (whetherExecution != null ? whetherExecution.hashCode() : 0);
        result = 31 * result + (whetherMeetDemand != null ? whetherMeetDemand.hashCode() : 0);
        result = 31 * result + (suitableProducts != null ? suitableProducts.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (lcId != null ? lcId.hashCode() : 0);
        
        result = 31 * result + (buyGoods != null ? buyGoods.hashCode() : 0);
        result = 31 * result + (buyTime != null ? buyTime.hashCode() : 0);
        result = 31 * result + (buyQuantity != null ? buyQuantity.hashCode() : 0);
        result = 31 * result + (productExperience != null ? productExperience.hashCode() : 0);
        result = 31 * result + (usingFeedback != null ? usingFeedback.hashCode() : 0);


        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("linkmanId").append("='").append(getLinkmanId()).append("', ");
        sb.append("customerDemand").append("='").append(getCustomerDemand()).append("', ");
        sb.append("customersWishes").append("='").append(getCustomersWishes()).append("', ");
        sb.append("interests").append("='").append(getInterests()).append("', ");
        sb.append("goodSports").append("='").append(getGoodSports()).append("', ");
        sb.append("favoritwActivity").append("='").append(getFavoritwActivity()).append("', ");
        sb.append("registerTime").append("='").append(getRegisterTime()).append("', ");
        sb.append("meetDemandMeasure").append("='").append(getMeetDemandMeasure()).append("', ");
        sb.append("executionTime").append("='").append(getExecutionTime()).append("', ");
        sb.append("whetherExecution").append("='").append(getWhetherExecution()).append("', ");
        sb.append("whetherMeetDemand").append("='").append(getWhetherMeetDemand()).append("', ");
        sb.append("suitableProducts").append("='").append(getSuitableProducts()).append("', ");
        sb.append("other").append("='").append(getOther()).append("'");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("lcId").append("='").append(getLcId()).append("'");
        
        sb.append("buyGoods").append("='").append(getBuyGoods()).append("'");
        sb.append("buyTime").append("='").append(getBuyTime()).append("'");
        sb.append("buyQuantity").append("='").append(getBuyQuantity()).append("'");
        sb.append("productExperience").append("='").append(getProductExperience()).append("'");
        sb.append("usingFeedback").append("='").append(getUsingFeedback()).append("'");
        
        sb.append("]");
      
        return sb.toString();
    }

}
