package com.joymain.ng.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;


@Entity
@Table(name="JAL_COUNTRY")

@XmlRootElement
public class JalCountry extends BaseObject implements Serializable {
    private Long countryId;
    private String countryCode;
    private String countryName;
    private String companyCode;
    private Set<JalStateProvince> jalStateProvinces = new HashSet<JalStateProvince>(0);

    @Id      @Column(name="COUNTRY_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
    
    @Column(name="COUNTRY_CODE", nullable=false, length=2)
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    @Column(name="COUNTRY_NAME", nullable=false, length=150)
    
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalCountry pojo = (JalCountry) o;

        if (countryCode != null ? !countryCode.equals(pojo.countryCode) : pojo.countryCode != null) return false;
        if (countryName != null ? !countryName.equals(pojo.countryName) : pojo.countryName != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("countryId").append("='").append(getCountryId()).append("', ");
        sb.append("countryCode").append("='").append(getCountryCode()).append("', ");
        sb.append("countryName").append("='").append(getCountryName()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="jalCountry")
    @OrderBy(value = "stateProvinceName ASC")
	public Set<JalStateProvince> getJalStateProvinces() {
		return jalStateProvinces;
	}

	public void setJalStateProvinces(Set<JalStateProvince> jalStateProvinces) {
		this.jalStateProvinces = jalStateProvinces;
	}

}
