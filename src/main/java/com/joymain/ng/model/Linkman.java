
package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
/**
 * 客户实体
 * @author Administrator
 *
 */
@Entity
@Table(name="LINKMAN")

@XmlRootElement
public class Linkman extends BaseObject implements Serializable {
    private Long id;
    private String name;
    private String sex;
    private String mobilephone;
    private String fax;
    private String email;
    private Long qq;
    private String msn;
    private String weibo;
    private String company;
    private String companyAddress;
    private String companyPhone;
    private String country;
    private String province;
    private String city;
    private Long post;
    private String familyAddress;
    private String familyPhone;
    private Date nextContactTime;
    private String remark;
    private Long customerSource;
    private Long linkmanStatus;//客户类型
    private Long heat;
    private String lcId;//客户分组
    private String district;
    private Date birthday;
    private Long age;
    private String loginUserNo;
    private String userCode;
    private String customerFocus;//特别关注(Y表示重点客户,N或者空表示普通客户)
    private String microMessage;

    @Id   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="NAME", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="SEX", length=1)
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="MOBILEPHONE", length=20)
    public String getMobilephone() {
        return this.mobilephone;
    }
    
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    
    @Column(name="FAX", length=20)
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    @Column(name="EMAIL", length=20)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="QQ", precision=11, scale=0)
    public Long getQq() {
        return this.qq;
    }
    
    public void setQq(Long qq) {
        this.qq = qq;
    }
    
    @Column(name="MSN", length=32)
    public String getMsn() {
        return this.msn;
    }
    
    public void setMsn(String msn) {
        this.msn = msn;
    }
    
    @Column(name="WEIBO", length=80)
    public String getWeibo() {
        return this.weibo;
    }
    
    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }
    
    @Column(name="COMPANY", length=100)
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    @Column(name="COMPANY_ADDRESS", length=200)
    public String getCompanyAddress() {
        return this.companyAddress;
    }
    
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    
    @Column(name="COMPANY_PHONE", length=20)
    public String getCompanyPhone() {
        return this.companyPhone;
    }
    
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }
    
    @Column(name="COUNTRY", length=20)
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
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
    
    @Column(name="POST", precision=6, scale=0)
    public Long getPost() {
        return this.post;
    }
    
    public void setPost(Long post) {
        this.post = post;
    }
    
    @Column(name="FAMILY_ADDRESS", length=200)
    public String getFamilyAddress() {
        return this.familyAddress;
    }
    
    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }
    
    @Column(name="FAMILY_PHONE", length=20)
    public String getFamilyPhone() {
        return this.familyPhone;
    }
    
    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="NEXT_CONTACT_TIME", length=7)
    public Date getNextContactTime() {
        return this.nextContactTime;
    }
    
    public void setNextContactTime(Date nextContactTime) {
        this.nextContactTime = nextContactTime;
    }
    
    @Column(name="REMARK", length=500)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="CUSTOMER_SOURCE", precision=22, scale=0)
    public Long getCustomerSource() {
        return this.customerSource;
    }
    
    public void setCustomerSource(Long customerSource) {
        this.customerSource = customerSource;
    }
    
    @Column(name="LINKMAN_STATUS", precision=22, scale=0)
    public Long getLinkmanStatus() {
        return this.linkmanStatus;
    }
    
    public void setLinkmanStatus(Long linkmanStatus) {
        this.linkmanStatus = linkmanStatus;
    }
    
    @Column(name="HEAT", precision=22, scale=0)
    public Long getHeat() {
        return this.heat;
    }
    
    public void setHeat(Long heat) {
        this.heat = heat;
    }
    
    @Column(name="LC_ID", length=32)
    public String getLcId() {
        return this.lcId;
    }
    
    public void setLcId(String lcId) {
        this.lcId = lcId;
    }
    
    @Column(name="DISTRICT", length=20)
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDAY", length=7)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="AGE", precision=22, scale=0)
    public Long getAge() {
        return this.age;
    }
    
    public void setAge(Long age) {
        this.age = age;
    }
    
    @Column(name="LOGIN_USER_NO", length=50)
    public String getLoginUserNo() {
        return this.loginUserNo;
    }
    
    public void setLoginUserNo(String loginUserNo) {
        this.loginUserNo = loginUserNo;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="CUSTOMER_FOCUS", length=2)
	public String getCustomerFocus() {
		return customerFocus;
	}

	public void setCustomerFocus(String customerFocus) {
		this.customerFocus = customerFocus;
	}

	@Column(name="MICRO_MESSAGE", length=80)
	public String getMicroMessage() {
		return microMessage;
	}

	public void setMicroMessage(String microMessage) {
		this.microMessage = microMessage;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Linkman pojo = (Linkman) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (sex != null ? !sex.equals(pojo.sex) : pojo.sex != null) return false;
        if (mobilephone != null ? !mobilephone.equals(pojo.mobilephone) : pojo.mobilephone != null) return false;
        if (fax != null ? !fax.equals(pojo.fax) : pojo.fax != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (qq != null ? !qq.equals(pojo.qq) : pojo.qq != null) return false;
        if (msn != null ? !msn.equals(pojo.msn) : pojo.msn != null) return false;
        if (weibo != null ? !weibo.equals(pojo.weibo) : pojo.weibo != null) return false;
        if (company != null ? !company.equals(pojo.company) : pojo.company != null) return false;
        if (companyAddress != null ? !companyAddress.equals(pojo.companyAddress) : pojo.companyAddress != null) return false;
        if (companyPhone != null ? !companyPhone.equals(pojo.companyPhone) : pojo.companyPhone != null) return false;
        if (country != null ? !country.equals(pojo.country) : pojo.country != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (post != null ? !post.equals(pojo.post) : pojo.post != null) return false;
        if (familyAddress != null ? !familyAddress.equals(pojo.familyAddress) : pojo.familyAddress != null) return false;
        if (familyPhone != null ? !familyPhone.equals(pojo.familyPhone) : pojo.familyPhone != null) return false;
        if (nextContactTime != null ? !nextContactTime.equals(pojo.nextContactTime) : pojo.nextContactTime != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (customerSource != null ? !customerSource.equals(pojo.customerSource) : pojo.customerSource != null) return false;
        if (linkmanStatus != null ? !linkmanStatus.equals(pojo.linkmanStatus) : pojo.linkmanStatus != null) return false;
        if (heat != null ? !heat.equals(pojo.heat) : pojo.heat != null) return false;
        if (lcId != null ? !lcId.equals(pojo.lcId) : pojo.lcId != null) return false;
        if (district != null ? !district.equals(pojo.district) : pojo.district != null) return false;
        if (birthday != null ? !birthday.equals(pojo.birthday) : pojo.birthday != null) return false;
        if (age != null ? !age.equals(pojo.age) : pojo.age != null) return false;
        if (loginUserNo != null ? !loginUserNo.equals(pojo.loginUserNo) : pojo.loginUserNo != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (customerFocus != null ? !customerFocus.equals(pojo.customerFocus) : pojo.customerFocus != null) return false;
        if (microMessage != null ? !microMessage.equals(pojo.microMessage) : pojo.microMessage != null) return false;
        
        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (msn != null ? msn.hashCode() : 0);
        result = 31 * result + (weibo != null ? weibo.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (companyAddress != null ? companyAddress.hashCode() : 0);
        result = 31 * result + (companyPhone != null ? companyPhone.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (familyAddress != null ? familyAddress.hashCode() : 0);
        result = 31 * result + (familyPhone != null ? familyPhone.hashCode() : 0);
        result = 31 * result + (nextContactTime != null ? nextContactTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (customerSource != null ? customerSource.hashCode() : 0);
        result = 31 * result + (linkmanStatus != null ? linkmanStatus.hashCode() : 0);
        result = 31 * result + (heat != null ? heat.hashCode() : 0);
        result = 31 * result + (lcId != null ? lcId.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (loginUserNo != null ? loginUserNo.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (customerFocus != null ? customerFocus.hashCode() : 0);
        result = 31 * result + (microMessage != null ? microMessage.hashCode() : 0);
        
        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("sex").append("='").append(getSex()).append("', ");
        sb.append("mobilephone").append("='").append(getMobilephone()).append("', ");
        sb.append("fax").append("='").append(getFax()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("qq").append("='").append(getQq()).append("', ");
        sb.append("msn").append("='").append(getMsn()).append("', ");
        sb.append("weibo").append("='").append(getWeibo()).append("', ");
        sb.append("company").append("='").append(getCompany()).append("', ");
        sb.append("companyAddress").append("='").append(getCompanyAddress()).append("', ");
        sb.append("companyPhone").append("='").append(getCompanyPhone()).append("', ");
        sb.append("country").append("='").append(getCountry()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("post").append("='").append(getPost()).append("', ");
        sb.append("familyAddress").append("='").append(getFamilyAddress()).append("', ");
        sb.append("familyPhone").append("='").append(getFamilyPhone()).append("', ");
        sb.append("nextContactTime").append("='").append(getNextContactTime()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("customerSource").append("='").append(getCustomerSource()).append("', ");
        sb.append("linkmanStatus").append("='").append(getLinkmanStatus()).append("', ");
        sb.append("heat").append("='").append(getHeat()).append("', ");
        sb.append("lcId").append("='").append(getLcId()).append("', ");
        sb.append("district").append("='").append(getDistrict()).append("', ");
        sb.append("birthday").append("='").append(getBirthday()).append("', ");
        sb.append("age").append("='").append(getAge()).append("', ");
        sb.append("loginUserNo").append("='").append(getLoginUserNo()).append("'");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("customerFocus").append("='").append(getCustomerFocus()).append("'");
        sb.append("microMessage").append("='").append(getMicroMessage()).append("'");
        
        sb.append("]");
      
        return sb.toString();
    }

}
