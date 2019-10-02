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

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_PRODUCT_SALE_RELATED")

@XmlRootElement
public class JpmProductSaleRelated extends BaseObject implements Serializable {
    private Long id;
//    private Long uniNo;
//    private Long relationUniNo;
    private String status;
    private JpmProductSaleNew jpmProductSaleNew;
    private JpmProductSaleNew relationJpmProductSaleNew;

    @Id      @Column(name="ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
   
    @ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "uni_no")
    public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}

	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

	
	@ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "RELATION_UNI_NO")
    public JpmProductSaleNew getRelationJpmProductSaleNew() {
		return relationJpmProductSaleNew;
	}

	public void setRelationJpmProductSaleNew(
			JpmProductSaleNew relationJpmProductSaleNew) {
		this.relationJpmProductSaleNew = relationJpmProductSaleNew;
	}

	@Column(name="STATUS", length=1)
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmProductSaleRelated pojo = (JpmProductSaleRelated) o;

//        if (uniNo != null ? !uniNo.equals(pojo.uniNo) : pojo.uniNo != null) return false;
//        if (relationUniNo != null ? !relationUniNo.equals(pojo.relationUniNo) : pojo.relationUniNo != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = ( getJpmProductSaleNew()!= null ? getJpmProductSaleNew().hashCode() : 0);
//        result = 31 * result + (relationUniNo != null ? relationUniNo.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("uniNo").append("='").append(getJpmProductSaleNew()).append("', ");
//        sb.append("relationUniNo").append("='").append(getRelationUniNo()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
