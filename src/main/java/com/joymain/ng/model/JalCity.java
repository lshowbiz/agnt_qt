package com.joymain.ng.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;


@Entity
@Table(name="JAL_CITY")

@XmlRootElement
public class JalCity extends BaseObject implements Serializable {
    private Long cityId;
    private JalStateProvince jalStateProvince;
    private String cityCode;
    private String cityName;
    private Set<JalDistrict> jalDistricts = new HashSet<JalDistrict>(0);

    @Id      @Column(name="CITY_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getCityId() {
        return this.cityId;
    }

	public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Column(name="CITY_CODE", length=30)
    
    public String getCityCode() {
        return this.cityCode;
    }
    
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    
    @Column(name="CITY_NAME", length=200)
    
    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalCity pojo = (JalCity) o;

        if (cityCode != null ? !cityCode.equals(pojo.cityCode) : pojo.cityCode != null) return false;
        if (cityName != null ? !cityName.equals(pojo.cityName) : pojo.cityName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("cityId").append("='").append(getCityId()).append("', ");
        sb.append("cityCode").append("='").append(getCityCode()).append("', ");
        sb.append("cityName").append("='").append(getCityName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="jalCity")
    public Set<JalDistrict> getJalDistricts() {
		return jalDistricts;
	}

	public void setJalDistricts(Set<JalDistrict> jalDistricts) {
		this.jalDistricts = jalDistricts;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="STATE_PROVINCE_ID")
	@JsonIgnore 
	public JalStateProvince getJalStateProvince() {
		return jalStateProvince;
	}

	public void setJalStateProvince(JalStateProvince jalStateProvince) {
		this.jalStateProvince = jalStateProvince;
	}

}
