package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JMI_PRIZE_TOURISM")

@XmlRootElement
public class JmiPrizeTourism extends BaseObject implements Serializable {
	private Long prizeTouismId;
    private String userCode;
    private String cardname;
    private String cardid;
    private String phone;
    private String province;
    private String city;
    private String area;
    private String address;
    private String beforeyearpoints;
    private String height;
    private String weight;
    private String clothessize;
    private String avoidcertainfood;
    private String acceptanceaddress;
    private Long passStar;
    private String peertype;
    private String peersex;
    private String peercardname;
    private String peercardid;
    private String peerphone;
    private String peerprovince;
    private String peercity;
    private String peerarea;
    private String peeraddress;
    private String peerheight;
    private String peerweight;
    private String peerclothessize;
    private String peeravoidcertainfood;
    private String ispeer;

    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="PRIZETOUISMID", unique=true, nullable=false, precision=10, scale=0)
    public Long getPrizeTouismId() {
		return prizeTouismId;
	}
 
	public void setPrizeTouismId(Long prizeTouismId) {
		this.prizeTouismId = prizeTouismId;
	}

	@Id      @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="CARDNAME", length=50)
    public String getCardname() {
        return this.cardname;
    }
    
    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
    
    @Column(name="CARDID", length=20)
    public String getCardid() {
        return this.cardid;
    }
    
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }
    
    @Column(name="PHONE", length=20)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="PROVINCE", length=10)
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="CITY", length=10)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="AREA", length=10)
    public String getArea() {
        return this.area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    @Column(name="ADDRESS", length=200)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="BEFOREYEARPOINTS", length=20)
    public String getBeforeyearpoints() {
        return this.beforeyearpoints;
    }
    
    public void setBeforeyearpoints(String beforeyearpoints) {
        this.beforeyearpoints = beforeyearpoints;
    }
    
    @Column(name="HEIGHT", length=20)
    public String getHeight() {
        return this.height;
    }
    
    public void setHeight(String height) {
        this.height = height;
    }
    
    @Column(name="WEIGHT", length=20)
    public String getWeight() {
        return this.weight;
    }
    
    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    @Column(name="CLOTHESSIZE", length=10)
    public String getClothessize() {
        return this.clothessize;
    }
    
    public void setClothessize(String clothessize) {
        this.clothessize = clothessize;
    }
    
    @Column(name="AVOIDCERTAINFOOD", length=20)
    public String getAvoidcertainfood() {
        return this.avoidcertainfood;
    }
    
    public void setAvoidcertainfood(String avoidcertainfood) {
        this.avoidcertainfood = avoidcertainfood;
    }
    
    @Column(name="ACCEPTANCEADDRESS", length=300)
    public String getAcceptanceaddress() {
        return this.acceptanceaddress;
    }
    
    public void setAcceptanceaddress(String acceptanceaddress) {
        this.acceptanceaddress = acceptanceaddress;
    }
    
    @Column(name="PASS_STAR", precision=2, scale=0)
    public Long getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Long passStar) {
        this.passStar = passStar;
    }
    
    @Column(name="PEERTYPE", length=10)
    public String getPeertype() {
        return this.peertype;
    }
    
    public void setPeertype(String peertype) {
        this.peertype = peertype;
    }
    
    @Column(name="PEERSEX", length=10)
    public String getPeersex() {
        return this.peersex;
    }
    
    public void setPeersex(String peersex) {
        this.peersex = peersex;
    }
    
    @Column(name="PEERCARDNAME", length=50)
    public String getPeercardname() {
        return this.peercardname;
    }
    
    public void setPeercardname(String peercardname) {
        this.peercardname = peercardname;
    }
    
    @Column(name="PEERCARDID", length=20)
    public String getPeercardid() {
        return this.peercardid;
    }
    
    public void setPeercardid(String peercardid) {
        this.peercardid = peercardid;
    }
    
    @Column(name="PEERPHONE", length=20)
    public String getPeerphone() {
        return this.peerphone;
    }
    
    public void setPeerphone(String peerphone) {
        this.peerphone = peerphone;
    }
    
    @Column(name="PEERPROVINCE", length=10)
    public String getPeerprovince() {
        return this.peerprovince;
    }
    
    public void setPeerprovince(String peerprovince) {
        this.peerprovince = peerprovince;
    }
    
    @Column(name="PEERCITY", length=10)
    public String getPeercity() {
        return this.peercity;
    }
    
    public void setPeercity(String peercity) {
        this.peercity = peercity;
    }
    
    @Column(name="PEERAREA", length=10)
    public String getPeerarea() {
        return this.peerarea;
    }
    
    public void setPeerarea(String peerarea) {
        this.peerarea = peerarea;
    }
    
    @Column(name="PEERADDRESS", length=200)
    public String getPeeraddress() {
        return this.peeraddress;
    }
    
    public void setPeeraddress(String peeraddress) {
        this.peeraddress = peeraddress;
    }
    
    @Column(name="PEERHEIGHT", length=20)
    public String getPeerheight() {
        return this.peerheight;
    }
    
    public void setPeerheight(String peerheight) {
        this.peerheight = peerheight;
    }
    
    @Column(name="PEERWEIGHT", length=20)
    public String getPeerweight() {
        return this.peerweight;
    }
    
    public void setPeerweight(String peerweight) {
        this.peerweight = peerweight;
    }
    
    @Column(name="PEERCLOTHESSIZE", length=10)
    public String getPeerclothessize() {
        return this.peerclothessize;
    }
    
    public void setPeerclothessize(String peerclothessize) {
        this.peerclothessize = peerclothessize;
    }
    
    @Column(name="PEERAVOIDCERTAINFOOD", length=20)
    public String getPeeravoidcertainfood() {
        return this.peeravoidcertainfood;
    }
    
    public void setPeeravoidcertainfood(String peeravoidcertainfood) {
        this.peeravoidcertainfood = peeravoidcertainfood;
    }
    
    @Column(name="ISPEER", length=2)
    public String getIspeer() {
        return this.ispeer;
    }
    
    public void setIspeer(String ispeer) {
        this.ispeer = ispeer;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiPrizeTourism pojo = (JmiPrizeTourism) o;

        if (cardname != null ? !cardname.equals(pojo.cardname) : pojo.cardname != null) return false;
        if (cardid != null ? !cardid.equals(pojo.cardid) : pojo.cardid != null) return false;
        if (phone != null ? !phone.equals(pojo.phone) : pojo.phone != null) return false;
        if (province != null ? !province.equals(pojo.province) : pojo.province != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (area != null ? !area.equals(pojo.area) : pojo.area != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (beforeyearpoints != null ? !beforeyearpoints.equals(pojo.beforeyearpoints) : pojo.beforeyearpoints != null) return false;
        if (height != null ? !height.equals(pojo.height) : pojo.height != null) return false;
        if (weight != null ? !weight.equals(pojo.weight) : pojo.weight != null) return false;
        if (clothessize != null ? !clothessize.equals(pojo.clothessize) : pojo.clothessize != null) return false;
        if (avoidcertainfood != null ? !avoidcertainfood.equals(pojo.avoidcertainfood) : pojo.avoidcertainfood != null) return false;
        if (acceptanceaddress != null ? !acceptanceaddress.equals(pojo.acceptanceaddress) : pojo.acceptanceaddress != null) return false;
        if (passStar != null ? !passStar.equals(pojo.passStar) : pojo.passStar != null) return false;
        if (peertype != null ? !peertype.equals(pojo.peertype) : pojo.peertype != null) return false;
        if (peersex != null ? !peersex.equals(pojo.peersex) : pojo.peersex != null) return false;
        if (peercardname != null ? !peercardname.equals(pojo.peercardname) : pojo.peercardname != null) return false;
        if (peercardid != null ? !peercardid.equals(pojo.peercardid) : pojo.peercardid != null) return false;
        if (peerphone != null ? !peerphone.equals(pojo.peerphone) : pojo.peerphone != null) return false;
        if (peerprovince != null ? !peerprovince.equals(pojo.peerprovince) : pojo.peerprovince != null) return false;
        if (peercity != null ? !peercity.equals(pojo.peercity) : pojo.peercity != null) return false;
        if (peerarea != null ? !peerarea.equals(pojo.peerarea) : pojo.peerarea != null) return false;
        if (peeraddress != null ? !peeraddress.equals(pojo.peeraddress) : pojo.peeraddress != null) return false;
        if (peerheight != null ? !peerheight.equals(pojo.peerheight) : pojo.peerheight != null) return false;
        if (peerweight != null ? !peerweight.equals(pojo.peerweight) : pojo.peerweight != null) return false;
        if (peerclothessize != null ? !peerclothessize.equals(pojo.peerclothessize) : pojo.peerclothessize != null) return false;
        if (peeravoidcertainfood != null ? !peeravoidcertainfood.equals(pojo.peeravoidcertainfood) : pojo.peeravoidcertainfood != null) return false;
        if (ispeer != null ? !ispeer.equals(pojo.ispeer) : pojo.ispeer != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (cardname != null ? cardname.hashCode() : 0);
        result = 31 * result + (cardid != null ? cardid.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (beforeyearpoints != null ? beforeyearpoints.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (clothessize != null ? clothessize.hashCode() : 0);
        result = 31 * result + (avoidcertainfood != null ? avoidcertainfood.hashCode() : 0);
        result = 31 * result + (acceptanceaddress != null ? acceptanceaddress.hashCode() : 0);
        result = 31 * result + (passStar != null ? passStar.hashCode() : 0);
        result = 31 * result + (peertype != null ? peertype.hashCode() : 0);
        result = 31 * result + (peersex != null ? peersex.hashCode() : 0);
        result = 31 * result + (peercardname != null ? peercardname.hashCode() : 0);
        result = 31 * result + (peercardid != null ? peercardid.hashCode() : 0);
        result = 31 * result + (peerphone != null ? peerphone.hashCode() : 0);
        result = 31 * result + (peerprovince != null ? peerprovince.hashCode() : 0);
        result = 31 * result + (peercity != null ? peercity.hashCode() : 0);
        result = 31 * result + (peerarea != null ? peerarea.hashCode() : 0);
        result = 31 * result + (peeraddress != null ? peeraddress.hashCode() : 0);
        result = 31 * result + (peerheight != null ? peerheight.hashCode() : 0);
        result = 31 * result + (peerweight != null ? peerweight.hashCode() : 0);
        result = 31 * result + (peerclothessize != null ? peerclothessize.hashCode() : 0);
        result = 31 * result + (peeravoidcertainfood != null ? peeravoidcertainfood.hashCode() : 0);
        result = 31 * result + (ispeer != null ? ispeer.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("cardname").append("='").append(getCardname()).append("', ");
        sb.append("cardid").append("='").append(getCardid()).append("', ");
        sb.append("phone").append("='").append(getPhone()).append("', ");
        sb.append("province").append("='").append(getProvince()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("area").append("='").append(getArea()).append("', ");
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("beforeyearpoints").append("='").append(getBeforeyearpoints()).append("', ");
        sb.append("height").append("='").append(getHeight()).append("', ");
        sb.append("weight").append("='").append(getWeight()).append("', ");
        sb.append("clothessize").append("='").append(getClothessize()).append("', ");
        sb.append("avoidcertainfood").append("='").append(getAvoidcertainfood()).append("', ");
        sb.append("acceptanceaddress").append("='").append(getAcceptanceaddress()).append("', ");
        sb.append("passStar").append("='").append(getPassStar()).append("', ");
        sb.append("peertype").append("='").append(getPeertype()).append("', ");
        sb.append("peersex").append("='").append(getPeersex()).append("', ");
        sb.append("peercardname").append("='").append(getPeercardname()).append("', ");
        sb.append("peercardid").append("='").append(getPeercardid()).append("', ");
        sb.append("peerphone").append("='").append(getPeerphone()).append("', ");
        sb.append("peerprovince").append("='").append(getPeerprovince()).append("', ");
        sb.append("peercity").append("='").append(getPeercity()).append("', ");
        sb.append("peerarea").append("='").append(getPeerarea()).append("', ");
        sb.append("peeraddress").append("='").append(getPeeraddress()).append("', ");
        sb.append("peerheight").append("='").append(getPeerheight()).append("', ");
        sb.append("peerweight").append("='").append(getPeerweight()).append("', ");
        sb.append("peerclothessize").append("='").append(getPeerclothessize()).append("', ");
        sb.append("peeravoidcertainfood").append("='").append(getPeeravoidcertainfood()).append("', ");
        sb.append("ispeer").append("='").append(getIspeer()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
