package com.joymain.ng.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_PRODUCT_SALE_IMAGE")

@XmlRootElement
public class JpmProductSaleImage extends BaseObject implements Serializable,Comparable<JpmProductSaleImage>{
    private Long imageId;
//    private Long uniNo;
    private String imageLink;
    private String imageType;
    private Long imageOrder;
    private String status;
    private JpmProductSaleNew jpmProductSaleNew;

    @Id      @Column(name="IMAGE_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    @JsonIgnore
    public Long getImageId() {
        return this.imageId;
    }
    
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
    
    
    @ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "uni_no")
    @JsonIgnore
    public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}

	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

	@Column(name="IMAGE_LINK", length=500)
    
    public String getImageLink() {
        return this.imageLink;
    }
    
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    
    @Column(name="IMAGE_TYPE", length=3)
    
    public String getImageType() {
        return this.imageType;
    }
    
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    
    @Column(name="IMAGE_ORDER", precision=3, scale=0)
    
    public Long getImageOrder() {
        return this.imageOrder;
    }
    
    public void setImageOrder(Long imageOrder) {
        this.imageOrder = imageOrder;
    }
    
    @Column(name="STATUS", length=1)
    @JsonIgnore
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmProductSaleImage pojo = (JpmProductSaleImage) o;

        
//        if (uniNo != null ? !uniNo.equals(pojo.uniNo) : pojo.uniNo != null) return false;
        if (imageLink != null ? !imageLink.equals(pojo.imageLink) : pojo.imageLink != null) return false;
        if (imageType != null ? !imageType.equals(pojo.imageType) : pojo.imageType != null) return false;
        if (imageOrder != null ? !imageOrder.equals(pojo.imageOrder) : pojo.imageOrder != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (jpmProductSaleNew != null ? jpmProductSaleNew.hashCode() : 0);
        result = 31 * result + (imageLink != null ? imageLink.hashCode() : 0);
        result = 31 * result + (imageType != null ? imageType.hashCode() : 0);
        result = 31 * result + (imageOrder != null ? imageOrder.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("imageId").append("='").append(getImageId()).append("', ");
        sb.append("uniNo").append("='").append(this.getJpmProductSaleNew()).append("', ");
        sb.append("imageLink").append("='").append(getImageLink()).append("', ");
        sb.append("imageType").append("='").append(getImageType()).append("', ");
        sb.append("imageOrder").append("='").append(getImageOrder()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    /**
     * 实现排序接口的方法，按图片类型正顺序（小图、中图、大图）
     */
	@Override
	public int compareTo(JpmProductSaleImage obj) {
		return Integer.parseInt(this.imageType)-Integer.parseInt(obj.imageType);
	}

}
