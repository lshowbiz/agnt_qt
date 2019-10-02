package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JAL_DISTRICT")

@XmlRootElement
public class JalDistrict extends BaseObject implements Serializable {
    private Long districtId;
    private JalCity jalCity;
    private String districtCode;
    private String districtName;
    private String postalcode;

    @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="CITY_ID")
    	@JsonIgnore 
    public JalCity getJalCity() {
		return jalCity;
	}

	public void setJalCity(JalCity jalCity) {
		this.jalCity = jalCity;
	}

	@Id      @Column(name="DISTRICT_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getDistrictId() {
        return this.districtId;
    }
    
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }
    

    
    @Column(name="DISTRICT_CODE", length=30)
    
    public String getDistrictCode() {
        return this.districtCode;
    }
    
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    
    @Column(name="DISTRICT_NAME", length=200)
    
    public String getDistrictName() {
        return this.districtName;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    
    @Column(name="POSTALCODE", length=10)
    
    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalDistrict pojo = (JalDistrict) o;

        if (districtCode != null ? !districtCode.equals(pojo.districtCode) : pojo.districtCode != null) return false;
        if (districtName != null ? !districtName.equals(pojo.districtName) : pojo.districtName != null) return false;
        if (postalcode != null ? !postalcode.equals(pojo.postalcode) : pojo.postalcode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (districtCode != null ? districtCode.hashCode() : 0);
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        result = 31 * result + (postalcode != null ? postalcode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("districtId").append("='").append(getDistrictId()).append("', ");
        sb.append("districtCode").append("='").append(getDistrictCode()).append("', ");
        sb.append("districtName").append("='").append(getDistrictName()).append("', ");
        sb.append("postalcode").append("='").append(getPostalcode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
