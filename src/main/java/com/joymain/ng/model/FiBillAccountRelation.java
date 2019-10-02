package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

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
/**
 * 快钱商户号对应区域关系
 * @author Administrator
 *
 */
@Entity
@Table(name="FI_BILL_ACCOUNT_RELATION")

@XmlRootElement
public class FiBillAccountRelation extends BaseObject implements Serializable {
    
	private Long relationId;
    //private String billAccountCode;
	private FiBillAccount fiBillAccount;
	
    private String province;
    private String city;
    private String district;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="RELATION_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getRelationId() {
        return this.relationId;
    }
    
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }
//    
//    @Column(name="BILL_ACCOUNT_CODE", nullable=false, length=50)
//    public String getBillAccountCode() {
//        return this.billAccountCode;
//    }
//    
//    public void setBillAccountCode(String billAccountCode) {
//        this.billAccountCode = billAccountCode;
//    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BILL_ACCOUNT_CODE")
    @JsonBackReference
    public FiBillAccount getFiBillAccount() {
		return fiBillAccount;
	}

	public void setFiBillAccount(FiBillAccount fiBillAccount) {
		this.fiBillAccount = fiBillAccount;
	}
    
	
    @Column(name="PROVINCE", length=20)
    public String getProvince() {
        return this.province;
    }   

	public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=20)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="DISTRICT", length=20)
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiBillAccountRelation pojo = (FiBillAccountRelation) o;

        //if (billAccountCode != null ? !billAccountCode.equals(pojo.billAccountCode) : pojo.billAccountCode != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        //result = (billAccountCode != null ? billAccountCode.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("relationId").append("='").append(getRelationId()).append("', ");
        //sb.append("billAccountCode").append("='").append(getBillAccountCode()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
