package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;


@Entity
@Table(name="JPM_PRODUCT_SALE_NEW")

@XmlRootElement
public class JpmProductSaleNew extends BaseObject implements Serializable {
    private Long uniNo;
    private String productNo;
    private String companyCode;
    private String productName;
    private BigDecimal whoPrice;
    private BigDecimal discountPrice;
    private BigDecimal weight;
    private BigDecimal volume;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private String status;
    private String confirm;
    private String remark;
    private String productDesc;//描述
    private String changeabledFlag;
    private Long storageCordon;
    private Date startOnSale;
    private Date endOnSale;
    private String shipStrategy;
    private String hotFlag;
    private Long sortFlag;
    private String isHidden;

    private Set<JpmProductSaleImage> jpmProductSaleImages = new TreeSet(); 
	private Set<JpmProductSaleRelated> jpmProductSaleRelateds = new HashSet(); 
	private Set<JpmProductSaleTeamType> jpmProductSaleTeamTypes = new HashSet(); 
	
	private JpmProduct jpmProduct = new JpmProduct();
	
	private String briefIntroduction;//简介
	private String healthKnowledge;//健康知识
	private String productSpecification;//规格
	
	private String isRecommend;//是否推荐
	
	//物流费策略
	private String logisticsStrategy;
    
    @Id      @Column(name="UNI_NO", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    
    @Column(name="LOGISTICS_STRATEGY", length=20)
    public String getLogisticsStrategy() {
		return logisticsStrategy;
	}

	public void setLogisticsStrategy(String logisticsStrategy) {
		this.logisticsStrategy = logisticsStrategy;
	}
    
    @Column(name="PRODUCT_NO", length=20)
    
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="PRODUCT_NAME", length=200)
    
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @Column(name="WHO_PRICE", precision=18)
    
    public BigDecimal getWhoPrice() {
        return this.whoPrice;
    }
    
    public void setWhoPrice(BigDecimal whoPrice) {
        this.whoPrice = whoPrice;
    }
    
    @Column(name="DISCOUNT_PRICE", precision=18)
    
    public BigDecimal getDiscountPrice() {
        return this.discountPrice;
    }
    
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
    
    @Column(name="WEIGHT", precision=10)
    
    public BigDecimal getWeight() {
        return this.weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    @Column(name="VOLUME", precision=15, scale=6)
    
    public BigDecimal getVolume() {
        return this.volume;
    }
    
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
    
    @Column(name="LENGTH", precision=10)
    
    public BigDecimal getLength() {
        return this.length;
    }
    
    public void setLength(BigDecimal length) {
        this.length = length;
    }
    
    @Column(name="WIDTH", precision=10)
    
    public BigDecimal getWidth() {
        return this.width;
    }
    
    public void setWidth(BigDecimal width) {
        this.width = width;
    }
    
    @Column(name="HEIGHT", precision=10)
    
    public BigDecimal getHeight() {
        return this.height;
    }
    
    public void setHeight(BigDecimal height) {
        this.height = height;
    }
    
    @Column(name="STATUS", length=1)
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="CONFIRM", length=1)
    
    public String getConfirm() {
        return this.confirm;
    }
    
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    
    @Column(name="REMARK", length=500)
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="PRODUCT_DESC", length=500)
    
    public String getProductDesc() {
        return this.productDesc;
    }
    
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    
    @Column(name="CHANGEABLED_FLAG", length=1)
    
    public String getChangeabledFlag() {
        return this.changeabledFlag;
    }
    
    public void setChangeabledFlag(String changeabledFlag) {
        this.changeabledFlag = changeabledFlag;
    }
    
    @Column(name="STORAGE_CORDON", precision=10, scale=0)
    
    public Long getStorageCordon() {
        return this.storageCordon;
    }
    
    public void setStorageCordon(Long storageCordon) {
        this.storageCordon = storageCordon;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="START_ON_SALE", length=7)
    
    public Date getStartOnSale() {
        return this.startOnSale;
    }
    
    public void setStartOnSale(Date startOnSale) {
        this.startOnSale = startOnSale;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="END_ON_SALE", length=7)
    
    public Date getEndOnSale() {
        return this.endOnSale;
    }
    
    public void setEndOnSale(Date endOnSale) {
        this.endOnSale = endOnSale;
    }
    
    @Column(name="SHIP_STRATEGY", length=20)
    
    public String getShipStrategy() {
        return this.shipStrategy;
    }
    
    public void setShipStrategy(String shipStrategy) {
        this.shipStrategy = shipStrategy;
    }
    
    @Column(name="HOT_FLAG", length=5)
    
    public String getHotFlag() {
        return this.hotFlag;
    }
    
    public void setHotFlag(String hotFlag) {
        this.hotFlag = hotFlag;
    }
    
    @Column(name="SORT_FLAG", precision=10, scale=0)
    
    public Long getSortFlag() {
        return this.sortFlag;
    }
    
    public void setSortFlag(Long sortFlag) {
        this.sortFlag = sortFlag;
    }
    
    @Column(name="IS_HIDDEN", length=1)
    
    public String getIsHidden() {
        return this.isHidden;
    }
    
    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmProductSaleNew pojo = (JpmProductSaleNew) o;

        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null) return false;
        if (whoPrice != null ? !whoPrice.equals(pojo.whoPrice) : pojo.whoPrice != null) return false;
        if (discountPrice != null ? !discountPrice.equals(pojo.discountPrice) : pojo.discountPrice != null) return false;
        if (weight != null ? !weight.equals(pojo.weight) : pojo.weight != null) return false;
        if (volume != null ? !volume.equals(pojo.volume) : pojo.volume != null) return false;
        if (length != null ? !length.equals(pojo.length) : pojo.length != null) return false;
        if (width != null ? !width.equals(pojo.width) : pojo.width != null) return false;
        if (height != null ? !height.equals(pojo.height) : pojo.height != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (confirm != null ? !confirm.equals(pojo.confirm) : pojo.confirm != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (productDesc != null ? !productDesc.equals(pojo.productDesc) : pojo.productDesc != null) return false;
        if (changeabledFlag != null ? !changeabledFlag.equals(pojo.changeabledFlag) : pojo.changeabledFlag != null) return false;
        if (storageCordon != null ? !storageCordon.equals(pojo.storageCordon) : pojo.storageCordon != null) return false;
        if (startOnSale != null ? !startOnSale.equals(pojo.startOnSale) : pojo.startOnSale != null) return false;
        if (endOnSale != null ? !endOnSale.equals(pojo.endOnSale) : pojo.endOnSale != null) return false;
        if (shipStrategy != null ? !shipStrategy.equals(pojo.shipStrategy) : pojo.shipStrategy != null) return false;
        if (hotFlag != null ? !hotFlag.equals(pojo.hotFlag) : pojo.hotFlag != null) return false;
        if (sortFlag != null ? !sortFlag.equals(pojo.sortFlag) : pojo.sortFlag != null) return false;
        if (isHidden != null ? !isHidden.equals(pojo.isHidden) : pojo.isHidden != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (whoPrice != null ? whoPrice.hashCode() : 0);
        result = 31 * result + (discountPrice != null ? discountPrice.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (confirm != null ? confirm.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (productDesc != null ? productDesc.hashCode() : 0);
        result = 31 * result + (changeabledFlag != null ? changeabledFlag.hashCode() : 0);
        result = 31 * result + (storageCordon != null ? storageCordon.hashCode() : 0);
        result = 31 * result + (startOnSale != null ? startOnSale.hashCode() : 0);
        result = 31 * result + (endOnSale != null ? endOnSale.hashCode() : 0);
        result = 31 * result + (shipStrategy != null ? shipStrategy.hashCode() : 0);
        result = 31 * result + (hotFlag != null ? hotFlag.hashCode() : 0);
        result = 31 * result + (sortFlag != null ? sortFlag.hashCode() : 0);
        result = 31 * result + (isHidden != null ? isHidden.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("uniNo").append("='").append(getUniNo()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("productName").append("='").append(getProductName()).append("', ");
        sb.append("whoPrice").append("='").append(getWhoPrice()).append("', ");
        sb.append("discountPrice").append("='").append(getDiscountPrice()).append("', ");
        sb.append("weight").append("='").append(getWeight()).append("', ");
        sb.append("volume").append("='").append(getVolume()).append("', ");
        sb.append("length").append("='").append(getLength()).append("', ");
        sb.append("width").append("='").append(getWidth()).append("', ");
        sb.append("height").append("='").append(getHeight()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("confirm").append("='").append(getConfirm()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("productDesc").append("='").append(getProductDesc()).append("', ");
        sb.append("changeabledFlag").append("='").append(getChangeabledFlag()).append("', ");
        sb.append("storageCordon").append("='").append(getStorageCordon()).append("', ");
        sb.append("startOnSale").append("='").append(getStartOnSale()).append("', ");
        sb.append("endOnSale").append("='").append(getEndOnSale()).append("', ");
        sb.append("shipStrategy").append("='").append(getShipStrategy()).append("', ");
        sb.append("hotFlag").append("='").append(getHotFlag()).append("', ");
        sb.append("sortFlag").append("='").append(getSortFlag()).append("', ");
        sb.append("isHidden").append("='").append(getIsHidden()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    
    @OneToMany(mappedBy = "jpmProductSaleNew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   
	public Set<JpmProductSaleImage> getJpmProductSaleImages() {
		return jpmProductSaleImages;
	}


	public void setJpmProductSaleImages(
			Set<JpmProductSaleImage> jpmProductSaleImages) {
		this.jpmProductSaleImages = jpmProductSaleImages;
	}


    @OneToMany(mappedBy = "jpmProductSaleNew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
	public Set<JpmProductSaleRelated> getJpmProductSaleRelateds() {
		return jpmProductSaleRelateds;
	}


	public void setJpmProductSaleRelateds(
			Set<JpmProductSaleRelated> jpmProductSaleRelateds) {
		this.jpmProductSaleRelateds = jpmProductSaleRelateds;
	}


    @OneToMany(mappedBy = "jpmProductSaleNew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
	public Set<JpmProductSaleTeamType> getJpmProductSaleTeamTypes() {
		return jpmProductSaleTeamTypes;
	}


	public void setJpmProductSaleTeamTypes(
			Set<JpmProductSaleTeamType> jpmProductSaleTeamTypes) {
		this.jpmProductSaleTeamTypes = jpmProductSaleTeamTypes;
	}

    @ManyToOne
    @JoinColumn(name = "PRODUCT_NO", nullable = false,  updatable = false,insertable=false)
	public JpmProduct getJpmProduct() {
		return jpmProduct;
	}

	public void setJpmProduct(JpmProduct jpmProduct) {
		this.jpmProduct = jpmProduct;
	}

	@Column(name="BRIEF_INTRODUCTION", length=500)
	public String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	@Column(name="HEALTH_KNOWLEDGE", length=500)
	public String getHealthKnowledge() {
		return healthKnowledge;
	}

	public void setHealthKnowledge(String healthKnowledge) {
		this.healthKnowledge = healthKnowledge;
	}

	@Column(name="PRODUCT_SPECIFICATION", length=500)
	public String getProductSpecification() {
		return productSpecification;
	}

	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}   	
	
	@Transient
	public List<JpmProductSaleImage> getJpmProductSaleImageList() {
		List list = new ArrayList<JpmProductSaleImage>(); 
		Set<JpmProductSaleImage> returnSet = new HashSet<JpmProductSaleImage>();
		Set<JpmProductSaleImage> set = this.getJpmProductSaleImages();
		for(JpmProductSaleImage jpsi : set){
			if("1".equals(jpsi.getStatus())){//如果未审核，则提供默认图片
				if("1".equals(jpsi.getImageType())){
					jpsi.setImageLink("nopic1.jpg");
				}else if("2".equals(jpsi.getImageType())){
					jpsi.setImageLink("nopic2.jpg");
				}else if("3".equals(jpsi.getImageType())){
					jpsi.setImageLink("nopic3.jpg");
				}
			}
			returnSet.add(jpsi);
		}
		
		//如果不存在图片，则添加默认的图片
		JpmProductSaleImage jps = null;
		if(set.size()==0){
			jps = new JpmProductSaleImage();
			jps.setImageOrder(Long.parseLong("1"));
			jps.setImageType("1");
			jps.setImageLink("nopic1.jpg");
			returnSet.add(jps);
			
			jps = new JpmProductSaleImage();
			jps.setImageOrder(Long.parseLong("2"));
			jps.setImageType("2");
			jps.setImageLink("nopic2.jpg");
			returnSet.add(jps);
			
			jps = new JpmProductSaleImage();
			jps.setImageOrder(Long.parseLong("3"));
			jps.setImageType("3");
			jps.setImageLink("nopic3.jpg");
			returnSet.add(jps);
		}
		
		list.addAll(returnSet);
		Collections.sort(list);
		return list;
	}

	@Column(name="IS_RECOMMEND", length=1)
	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}   
	
	
}
