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

/**
 * 省
 * @author Administrator
 *
 */
@Entity
@Table(name="JAL_STATE_PROVINCE")

@XmlRootElement
public class JalStateProvince extends BaseObject implements Serializable {
    private Long stateProvinceId;
    private JalCountry jalCountry;
    private String stateProvinceCode;
    private String stateProvinceName;
    private Set<JalCity> jalCities = new HashSet<JalCity>(0);

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COUNTRY_ID")
    @JsonIgnore
    public JalCountry getJalCountry() {
		return jalCountry;
	}

	public void setJalCountry(JalCountry jalCountry) {
		this.jalCountry = jalCountry;
	}

	@Id      @Column(name="STATE_PROVINCE_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getStateProvinceId() {
        return this.stateProvinceId;
    }
    
    public void setStateProvinceId(Long stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    @Column(name="STATE_PROVINCE_CODE", nullable=false, length=30)
    
    public String getStateProvinceCode() {
        return this.stateProvinceCode;
    }
    
    public void setStateProvinceCode(String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }
    
    @Column(name="STATE_PROVINCE_NAME", nullable=false, length=150)
    
    public String getStateProvinceName() {
        return this.stateProvinceName;
    }
    
    public void setStateProvinceName(String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalStateProvince pojo = (JalStateProvince) o;

        if (stateProvinceCode != null ? !stateProvinceCode.equals(pojo.stateProvinceCode) : pojo.stateProvinceCode != null) return false;
        if (stateProvinceName != null ? !stateProvinceName.equals(pojo.stateProvinceName) : pojo.stateProvinceName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (stateProvinceCode != null ? stateProvinceCode.hashCode() : 0);
        result = 31 * result + (stateProvinceName != null ? stateProvinceName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("stateProvinceId").append("='").append(getStateProvinceId()).append("', ");
        sb.append("stateProvinceCode").append("='").append(getStateProvinceCode()).append("', ");
        sb.append("stateProvinceName").append("='").append(getStateProvinceName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="jalStateProvince")
	public Set<JalCity> getJalCities() {
		return jalCities;
	}

	public void setJalCities(Set<JalCity> jalCities) {
		this.jalCities = jalCities;
	}

}
