package com.joymain.ng.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;



@Entity
@Table(name="JMI_ADDR_BOOK")


public class JmiAddrBook extends BaseObject implements Serializable {
    private Long fabId;
    private JmiMember jmiMember;
    private String firstName;
    private String lastName;
    private String province;
    private String city;
    private String address;
    private String postalcode;
    private String phone;
    private String email;
    private String mobiletele;
    private String district;
    private String town;
    private String building;
    private String isDefault;

    @Column(name="IS_DEFAULT")
    public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="FAB_ID", unique=true, nullable=false, precision=10, scale=0)   
    public Long getFabId() {
        return this.fabId;
    }
    
    public void setFabId(Long fabId) {
        this.fabId = fabId;
    }

    @Column(name="FIRST_NAME", nullable=false, length=100)
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name="LAST_NAME", nullable=false, length=100)
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name="PROVINCE", nullable=false, length=20)
    
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", nullable=false, length=20)
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="ADDRESS", nullable=false, length=500)
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="POSTALCODE", nullable=false, length=10)
    
    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    
    @Column(name="PHONE", length=30)
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="EMAIL", length=30)
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="MOBILETELE", length=20)
    
    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    
    @Column(name="DISTRICT", length=20)
    
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    @Column(name="TOWN", length=20)
    
    public String getTown() {
        return this.town;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    
    @Column(name="BUILDING", length=500)
    
    public String getBuilding() {
        return this.building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiAddrBook pojo = (JmiAddrBook) o;

        if (firstName != null ? !firstName.equals(pojo.firstName) : pojo.firstName != null) return false;
        if (lastName != null ? !lastName.equals(pojo.lastName) : pojo.lastName != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (postalcode != null ? !postalcode.equals(pojo.postalcode) : pojo.postalcode != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (mobiletele != null ? !mobiletele.equals(pojo.mobiletele) : pojo.mobiletele != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (town != null ? !town.equals(pojo.town) : pojo.town != null) return false;
        if (building != null ? !building.equals(pojo.building) : pojo.building != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (postalcode != null ? postalcode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobiletele != null ? mobiletele.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fabId").append("='").append(getFabId()).append("', ");
        sb.append("firstName").append("='").append(getFirstName()).append("', ");
        sb.append("lastName").append("='").append(getLastName()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("postalcode").append("='").append(getPostalcode()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("mobiletele").append("='").append(getMobiletele()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("town").append("='").append(getTown()).append("', ");
        sb.append("building").append("='").append(getBuilding()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToOne
    @JoinColumn(name = "USER_CODE", nullable = false,  updatable = false)
    @JsonIgnore
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

}
