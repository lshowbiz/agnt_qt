package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

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
@Table(name="LINKMAN_GOODS")

@XmlRootElement
public class LinkmanGoods extends BaseObject implements Serializable {
    private Long id;
    private String linkmanDemandid;
    private String lcId;
    private String linkmanId;
    private String buyGoods;
    private Date buyTime;
    private String buyQuantity;
    private String productExperience;
    private String usingFeedback;
    private String userCode;

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) 
    @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="LINKMAN_DEMANDID", length=32)
    public String getLinkmanDemandid() {
        return this.linkmanDemandid;
    }
    
    public void setLinkmanDemandid(String linkmanDemandid) {
        this.linkmanDemandid = linkmanDemandid;
    }
    
    @Column(name="LC_ID", length=32)
    public String getLcId() {
        return this.lcId;
    }
    
    public void setLcId(String lcId) {
        this.lcId = lcId;
    }
    
    @Column(name="LINKMAN_ID", length=32)
    public String getLinkmanId() {
        return this.linkmanId;
    }
    
    public void setLinkmanId(String linkmanId) {
        this.linkmanId = linkmanId;
    }
    
    @Column(name="BUY_GOODS", length=500)
    public String getBuyGoods() {
        return this.buyGoods;
    }
    
    public void setBuyGoods(String buyGoods) {
        this.buyGoods = buyGoods;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BUY_TIME", length=7)
    public Date getBuyTime() {
        return this.buyTime;
    }
    
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }
    
    @Column(name="BUY_QUANTITY", length=10)
    public String getBuyQuantity() {
        return this.buyQuantity;
    }
    
    public void setBuyQuantity(String buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
    
    @Column(name="PRODUCT_EXPERIENCE", length=1000)
    public String getProductExperience() {
        return this.productExperience;
    }
    
    public void setProductExperience(String productExperience) {
        this.productExperience = productExperience;
    }
    
    @Column(name="USING_FEEDBACK", length=1000)
    public String getUsingFeedback() {
        return this.usingFeedback;
    }
    
    public void setUsingFeedback(String usingFeedback) {
        this.usingFeedback = usingFeedback;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkmanGoods pojo = (LinkmanGoods) o;

        if (linkmanDemandid != null ? !linkmanDemandid.equals(pojo.linkmanDemandid) : pojo.linkmanDemandid != null) return false;
        if (lcId != null ? !lcId.equals(pojo.lcId) : pojo.lcId != null) return false;
        if (linkmanId != null ? !linkmanId.equals(pojo.linkmanId) : pojo.linkmanId != null) return false;
        if (buyGoods != null ? !buyGoods.equals(pojo.buyGoods) : pojo.buyGoods != null) return false;
        if (buyTime != null ? !buyTime.equals(pojo.buyTime) : pojo.buyTime != null) return false;
        if (buyQuantity != null ? !buyQuantity.equals(pojo.buyQuantity) : pojo.buyQuantity != null) return false;
        if (productExperience != null ? !productExperience.equals(pojo.productExperience) : pojo.productExperience != null) return false;
        if (usingFeedback != null ? !usingFeedback.equals(pojo.usingFeedback) : pojo.usingFeedback != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (linkmanDemandid != null ? linkmanDemandid.hashCode() : 0);
        result = 31 * result + (lcId != null ? lcId.hashCode() : 0);
        result = 31 * result + (linkmanId != null ? linkmanId.hashCode() : 0);
        result = 31 * result + (buyGoods != null ? buyGoods.hashCode() : 0);
        result = 31 * result + (buyTime != null ? buyTime.hashCode() : 0);
        result = 31 * result + (buyQuantity != null ? buyQuantity.hashCode() : 0);
        result = 31 * result + (productExperience != null ? productExperience.hashCode() : 0);
        result = 31 * result + (usingFeedback != null ? usingFeedback.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("linkmanDemandid").append("='").append(getLinkmanDemandid()).append("', ");
        sb.append("lcId").append("='").append(getLcId()).append("', ");
        sb.append("linkmanId").append("='").append(getLinkmanId()).append("', ");
        sb.append("buyGoods").append("='").append(getBuyGoods()).append("', ");
        sb.append("buyTime").append("='").append(getBuyTime()).append("', ");
        sb.append("buyQuantity").append("='").append(getBuyQuantity()).append("', ");
        sb.append("productExperience").append("='").append(getProductExperience()).append("', ");
        sb.append("usingFeedback").append("='").append(getUsingFeedback()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
