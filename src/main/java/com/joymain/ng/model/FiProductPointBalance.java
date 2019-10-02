package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;
/**
 * 产品积分业务实体
 * @author hdg 2017-01-03
 *
 */
@Entity
@Table(name="FI_PRODUCT_POINT_BALANCE")

@XmlRootElement
public class FiProductPointBalance extends BaseObject implements Serializable {
    private Long fbbId;
    private String userCode;
    private BigDecimal balance;
    private String productPointType;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BANKBOOK")
	@SequenceGenerator(name = "SEQ_BANKBOOK", sequenceName = "SEQ_BANKBOOK", allocationSize = 1)
    @Column(name="FBB_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getFbbId() {
        return this.fbbId;
    }
    
    public void setFbbId(Long fbbId) {
        this.fbbId = fbbId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=200)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="BALANCE", nullable=false, precision=18, scale=4)
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Column(name="PRODUCT_POINT_TYPE", nullable=false, length=10)
    public String getProductPointType() {
		return productPointType;
	}

	public void setProductPointType(String productPointType) {
		this.productPointType = productPointType;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiProductPointBalance pojo = (FiProductPointBalance) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (balance != null ? !balance.equals(pojo.balance) : pojo.balance != null) return false;
        if (productPointType != null ? !productPointType.equals(pojo.productPointType) : pojo.productPointType != null) return false;

        return true;
    }

    

	public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (productPointType != null ? productPointType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fbbId").append("='").append(getFbbId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("balance").append("='").append(getBalance()).append("', ");
        sb.append("productPointType").append("='").append(getProductPointType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
