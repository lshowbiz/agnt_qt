package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="JPO_PRODUCT_NUM_LIMIT")

@XmlRootElement
public class JpoProductNumLimit extends BaseObject implements Serializable {
	private Long id;
    private String productNo;
    private String productName;
    private Integer num;
    
    private Date startime;
    private Date endtime;

    @Id      
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
	@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="ID", unique=true, nullable=false, precision=10, scale=0)    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="STARTIME", nullable=false, length=17)
    public Date getStartime() {
        return this.startime;
    }
    
    public void setStartime(Date startime) {
        this.startime = startime;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ENDTIME",  length=7)
    public Date getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    
    @Column(name="PRODUCT_NO", length=20)
    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @Column(name="PRODUCT_NAME", length=100)
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @Column(name="NUM", precision=5, scale=0)
    public Integer getNum() {
        return this.num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoProductNumLimit pojo = (JpoProductNumLimit) o;

        if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
        if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null) return false;
        if (num != null ? !num.equals(pojo.num) : pojo.num != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (productNo != null ? productNo.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("productNo").append("='").append(getProductNo()).append("', ");
        sb.append("productName").append("='").append(getProductName()).append("', ");
        sb.append("num").append("='").append(getNum()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
