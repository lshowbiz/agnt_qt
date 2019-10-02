package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_SALE_PROMOTER")

@XmlRootElement
public class JpmSalePromoter extends BaseObject implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long spno;
    private Date startime;
    private Date endtime;
    private Long discount;
    private Long spNum;
    private String presentno;
    private String presentname;
    private String presentlimit;
    private BigDecimal amount = new BigDecimal(0);
    private Long pv;
    private String isorder;
    private Long integral;
    private String ispresent;
    private Long reintegral;
    private String teamno;
    private String ordertype;
    private String companyno;
    private String saleType;
    private String descr;
    private String isactiva;
    private String appendpresent;
    private String isiter;
    private String userlevel;
    private String preordertype;
    private Integer orderpronum;
    private Set<JpmSalepromoterPro> saleProSet;
    
    @Id      @Column(name="SPNO", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getSpno() {
        return this.spno;
    }
    
    public void setSpno(Long spno) {
        this.spno = spno;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="STARTIME", nullable=false, length=7)
    public Date getStartime() {
        return this.startime;
    }
    
    public void setStartime(Date startime) {
        this.startime = startime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="ENDTIME", nullable=false, length=7)
    public Date getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    
    @Column(name="DISCOUNT", precision=18)
    public Long getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(Long discount) {
        this.discount = discount;
    }
    
    @Column(name="SP_NUM", precision=5, scale=0)
    public Long getSpNum() {
        return this.spNum;
    }
    
    public void setSpNum(Long spNum) {
        this.spNum = spNum;
    }
    
    @Column(name="PRESENTNO", length=20)
    public String getPresentno() {
        return this.presentno;
    }
    
    public void setPresentno(String presentno) {
        this.presentno = presentno;
    }
    
    @Column(name="PRESENTNAME", length=50)
    public String getPresentname() {
        return this.presentname;
    }
    
    public void setPresentname(String presentname) {
        this.presentname = presentname;
    }
    
    @Column(name="PRESENTLIMIT", length=100)
    public String getPresentlimit() {
        return this.presentlimit;
    }
    
    public void setPresentlimit(String presentlimit) {
        this.presentlimit = presentlimit;
    }
    
    @Column(name="AMOUNT", precision=18)
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Column(name="PV", precision=10, scale=0)
    public Long getPv() {
        return this.pv;
    }
    
    public void setPv(Long pv) {
        this.pv = pv;
    }
    
    @Column(name="ISORDER", length=1)
    public String getIsorder() {
        return this.isorder;
    }
    
    public void setIsorder(String isorder) {
        this.isorder = isorder;
    }
    
    @Column(name="INTEGRAL", precision=18)
    public Long getIntegral() {
        return this.integral;
    }
    
    public void setIntegral(Long integral) {
        this.integral = integral;
    }
    
    @Column(name="ISPRESENT", length=1)
    public String getIspresent() {
        return this.ispresent;
    }
    
    public void setIspresent(String ispresent) {
        this.ispresent = ispresent;
    }
    
    @Column(name="REINTEGRAL", precision=18)
    public Long getReintegral() {
        return this.reintegral;
    }
    
    public void setReintegral(Long reintegral) {
        this.reintegral = reintegral;
    }
    
    @Column(name="TEAMNO", length=300)
    public String getTeamno() {
        return this.teamno;
    }
    
    public void setTeamno(String teamno) {
        this.teamno = teamno;
    }
    
    @Column(name="ORDERTYPE", length=20)
    public String getOrdertype() {
        return this.ordertype;
    }
    
    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }
    
    @Column(name="COMPANYNO", length=100)
    public String getCompanyno() {
        return this.companyno;
    }
    
    public void setCompanyno(String companyno) {
        this.companyno = companyno;
    }
    
    @Column(name="SALE_TYPE", length=1)
    public String getSaleType() {
        return this.saleType;
    }
    
    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
    
    @Column(name="DESCR", length=200)
    public String getDescr() {
        return this.descr;
    }
    
    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    @Column(name="ISACTIVA", length=1)
    public String getIsactiva() {
        return this.isactiva;
    }
    
    public void setIsactiva(String isactiva) {
        this.isactiva = isactiva;
    }
    
    @Column(name="APPENDPRESENT", length=200)
    public String getAppendpresent() {
        return this.appendpresent;
    }
    
    public void setAppendpresent(String appendpresent) {
        this.appendpresent = appendpresent;
    }
    
    @Column(name="ISITER", length=1)
    public String getIsiter() {
        return this.isiter;
    }
    
    public void setIsiter(String isiter) {
        this.isiter = isiter;
    }
    
    @Column(name="USERLEVEL", length=50)
    public String getUserlevel() {
        return this.userlevel;
    }
    
    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }
    
    @Column(name="PREORDERTYPE", length=50)
    public String getPreordertype() {
        return this.preordertype;
    }
    
    public void setPreordertype(String preordertype) {
        this.preordertype = preordertype;
    }
    
    @Column(name="ORDERPRONUM", precision=22, scale=0)
    public Integer getOrderpronum() {
        return this.orderpronum;
    }
    
    public void setOrderpronum(Integer orderpronum) {
        this.orderpronum = orderpronum;
    }
     
    @OneToMany(mappedBy = "jpmSalePromoter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<JpmSalepromoterPro> getSaleProSet() {
		return saleProSet;
	}

	public void setSaleProSet(Set<JpmSalepromoterPro> saleProSet) {
		this.saleProSet = saleProSet;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmSalePromoter pojo = (JpmSalePromoter) o;

        if (startime != null ? !startime.equals(pojo.startime) : pojo.startime != null) return false;
        if (endtime != null ? !endtime.equals(pojo.endtime) : pojo.endtime != null) return false;
        if (discount != null ? !discount.equals(pojo.discount) : pojo.discount != null) return false;
        if (spNum != null ? !spNum.equals(pojo.spNum) : pojo.spNum != null) return false;
        if (presentno != null ? !presentno.equals(pojo.presentno) : pojo.presentno != null) return false;
        if (presentname != null ? !presentname.equals(pojo.presentname) : pojo.presentname != null) return false;
        if (presentlimit != null ? !presentlimit.equals(pojo.presentlimit) : pojo.presentlimit != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (pv != null ? !pv.equals(pojo.pv) : pojo.pv != null) return false;
        if (isorder != null ? !isorder.equals(pojo.isorder) : pojo.isorder != null) return false;
        if (integral != null ? !integral.equals(pojo.integral) : pojo.integral != null) return false;
        if (ispresent != null ? !ispresent.equals(pojo.ispresent) : pojo.ispresent != null) return false;
        if (reintegral != null ? !reintegral.equals(pojo.reintegral) : pojo.reintegral != null) return false;
        if (teamno != null ? !teamno.equals(pojo.teamno) : pojo.teamno != null) return false;
        if (ordertype != null ? !ordertype.equals(pojo.ordertype) : pojo.ordertype != null) return false;
        if (companyno != null ? !companyno.equals(pojo.companyno) : pojo.companyno != null) return false;
        if (saleType != null ? !saleType.equals(pojo.saleType) : pojo.saleType != null) return false;
        if (descr != null ? !descr.equals(pojo.descr) : pojo.descr != null) return false;
        if (isactiva != null ? !isactiva.equals(pojo.isactiva) : pojo.isactiva != null) return false;
        if (appendpresent != null ? !appendpresent.equals(pojo.appendpresent) : pojo.appendpresent != null) return false;
        if (isiter != null ? !isiter.equals(pojo.isiter) : pojo.isiter != null) return false;
        if (userlevel != null ? !userlevel.equals(pojo.userlevel) : pojo.userlevel != null) return false;
        if (preordertype != null ? !preordertype.equals(pojo.preordertype) : pojo.preordertype != null) return false;
        if (orderpronum != null ? !orderpronum.equals(pojo.orderpronum) : pojo.orderpronum != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (startime != null ? startime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (spNum != null ? spNum.hashCode() : 0);
        result = 31 * result + (presentno != null ? presentno.hashCode() : 0);
        result = 31 * result + (presentname != null ? presentname.hashCode() : 0);
        result = 31 * result + (presentlimit != null ? presentlimit.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (pv != null ? pv.hashCode() : 0);
        result = 31 * result + (isorder != null ? isorder.hashCode() : 0);
        result = 31 * result + (integral != null ? integral.hashCode() : 0);
        result = 31 * result + (ispresent != null ? ispresent.hashCode() : 0);
        result = 31 * result + (reintegral != null ? reintegral.hashCode() : 0);
        result = 31 * result + (teamno != null ? teamno.hashCode() : 0);
        result = 31 * result + (ordertype != null ? ordertype.hashCode() : 0);
        result = 31 * result + (companyno != null ? companyno.hashCode() : 0);
        result = 31 * result + (saleType != null ? saleType.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (isactiva != null ? isactiva.hashCode() : 0);
        result = 31 * result + (appendpresent != null ? appendpresent.hashCode() : 0);
        result = 31 * result + (isiter != null ? isiter.hashCode() : 0);
        result = 31 * result + (userlevel != null ? userlevel.hashCode() : 0);
        result = 31 * result + (preordertype != null ? preordertype.hashCode() : 0);
        result = 31 * result + (orderpronum != null ? orderpronum.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("spno").append("='").append(getSpno()).append("', ");
        sb.append("startime").append("='").append(getStartime()).append("', ");
        sb.append("endtime").append("='").append(getEndtime()).append("', ");
        sb.append("discount").append("='").append(getDiscount()).append("', ");
        sb.append("spNum").append("='").append(getSpNum()).append("', ");
        sb.append("presentno").append("='").append(getPresentno()).append("', ");
        sb.append("presentname").append("='").append(getPresentname()).append("', ");
        sb.append("presentlimit").append("='").append(getPresentlimit()).append("', ");
        sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("pv").append("='").append(getPv()).append("', ");
        sb.append("isorder").append("='").append(getIsorder()).append("', ");
        sb.append("integral").append("='").append(getIntegral()).append("', ");
        sb.append("ispresent").append("='").append(getIspresent()).append("', ");
        sb.append("reintegral").append("='").append(getReintegral()).append("', ");
        sb.append("teamno").append("='").append(getTeamno()).append("', ");
        sb.append("ordertype").append("='").append(getOrdertype()).append("', ");
        sb.append("companyno").append("='").append(getCompanyno()).append("', ");
        sb.append("saleType").append("='").append(getSaleType()).append("', ");
        sb.append("descr").append("='").append(getDescr()).append("', ");
        sb.append("isactiva").append("='").append(getIsactiva()).append("', ");
        sb.append("appendpresent").append("='").append(getAppendpresent()).append("', ");
        sb.append("isiter").append("='").append(getIsiter()).append("', ");
        sb.append("userlevel").append("='").append(getUserlevel()).append("', ");
        sb.append("preordertype").append("='").append(getPreordertype()).append("', ");
        sb.append("orderpronum").append("='").append(getOrderpronum()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
