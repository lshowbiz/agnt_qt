package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.sql.Clob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="JBD_BOUNS_DEDUCT")

@XmlRootElement
public class JbdBounsDeduct extends BaseObject implements Serializable {
    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String companyCode;
    private String userCode;
    private String summary;
    private String type;
    private BigDecimal money;
    private BigDecimal remainMoney;
    private BigDecimal deductMoney;
    private String status;
    private String checkerCode;
    private String checkerName;
    private Date checkTime;
    private String deducterCode;
    private String deducterName;
    private Date deductTime;
    private String createCode;
    private String createName;
    private Date createTime;
    private String treatedType;
    private Integer treatedWeek;
    private String treatedNo;

    @Id     
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) 
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="W_MONTH", precision=8, scale=0)
    public Integer getWmonth() {
		return wmonth;
	}

	public void setWmonth(Integer wmonth) {
		this.wmonth = wmonth;
	}

    @Column(name="W_WEEK", precision=8, scale=0)
	public Integer getWweek() {
		return wweek;
	}

	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}

	@Column(name="W_YEAR", precision=8, scale=0)
	public Integer getWyear() {
		return wyear;
	}

	public void setWyear(Integer wyear) {
		this.wyear = wyear;
	}


    

    

    
    @Column(name="COMPANY_CODE", length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="SUMMARY")
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    @Column(name="TYPE", length=10)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="MONEY", precision=22, scale=0)
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    @Column(name="REMAIN_MONEY", precision=22, scale=0)
    public BigDecimal getRemainMoney() {
        return this.remainMoney;
    }
    
    public void setRemainMoney(BigDecimal remainMoney) {
        this.remainMoney = remainMoney;
    }
    
    @Column(name="DEDUCT_MONEY", precision=22, scale=0)
    public BigDecimal getDeductMoney() {
        return this.deductMoney;
    }
    
    public void setDeductMoney(BigDecimal deductMoney) {
        this.deductMoney = deductMoney;
    }
    
    @Column(name="STATUS", length=2)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="CHECKER_CODE", length=20)
    public String getCheckerCode() {
        return this.checkerCode;
    }
    
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }
    
    @Column(name="CHECKER_NAME", length=60)
    public String getCheckerName() {
        return this.checkerName;
    }
    
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    @Column(name="CHECK_TIME", length=7)
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    
    @Column(name="DEDUCTER_CODE", length=20)
    public String getDeducterCode() {
        return this.deducterCode;
    }
    
    public void setDeducterCode(String deducterCode) {
        this.deducterCode = deducterCode;
    }
    
    @Column(name="DEDUCTER_NAME", length=60)
    public String getDeducterName() {
        return this.deducterName;
    }
    
    public void setDeducterName(String deducterName) {
        this.deducterName = deducterName;
    }
    @Column(name="DEDUCT_TIME", length=7)
    public Date getDeductTime() {
        return this.deductTime;
    }
    
    public void setDeductTime(Date deductTime) {
        this.deductTime = deductTime;
    }
    
    @Column(name="CREATE_CODE", length=20)
    public String getCreateCode() {
        return this.createCode;
    }
    
    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }
    
    @Column(name="CREATE_NAME", length=60)
    public String getCreateName() {
        return this.createName;
    }
    
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="TREATED_TYPE", length=1)
    public String getTreatedType() {
        return this.treatedType;
    }
    
    public void setTreatedType(String treatedType) {
        this.treatedType = treatedType;
    }
    
    @Column(name="TREATED_WEEK", precision=8, scale=0)
    public Integer getTreatedWeek() {
        return this.treatedWeek;
    }
    
    public void setTreatedWeek(Integer treatedWeek) {
        this.treatedWeek = treatedWeek;
    }
    
    @Column(name="TREATED_NO", length=10)
    public String getTreatedNo() {
        return this.treatedNo;
    }
    
    public void setTreatedNo(String treatedNo) {
        this.treatedNo = treatedNo;
    }
    


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdBounsDeduct pojo = (JbdBounsDeduct) o;

        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (summary != null ? !summary.equals(pojo.summary) : pojo.summary != null) return false;
        if (type != null ? !type.equals(pojo.type) : pojo.type != null) return false;
        if (money != null ? !money.equals(pojo.money) : pojo.money != null) return false;
        if (remainMoney != null ? !remainMoney.equals(pojo.remainMoney) : pojo.remainMoney != null) return false;
        if (deductMoney != null ? !deductMoney.equals(pojo.deductMoney) : pojo.deductMoney != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (checkerCode != null ? !checkerCode.equals(pojo.checkerCode) : pojo.checkerCode != null) return false;
        if (checkerName != null ? !checkerName.equals(pojo.checkerName) : pojo.checkerName != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (deducterCode != null ? !deducterCode.equals(pojo.deducterCode) : pojo.deducterCode != null) return false;
        if (deducterName != null ? !deducterName.equals(pojo.deducterName) : pojo.deducterName != null) return false;
        if (deductTime != null ? !deductTime.equals(pojo.deductTime) : pojo.deductTime != null) return false;
        if (createCode != null ? !createCode.equals(pojo.createCode) : pojo.createCode != null) return false;
        if (createName != null ? !createName.equals(pojo.createName) : pojo.createName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (treatedType != null ? !treatedType.equals(pojo.treatedType) : pojo.treatedType != null) return false;
        if (treatedWeek != null ? !treatedWeek.equals(pojo.treatedWeek) : pojo.treatedWeek != null) return false;
        if (treatedNo != null ? !treatedNo.equals(pojo.treatedNo) : pojo.treatedNo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (remainMoney != null ? remainMoney.hashCode() : 0);
        result = 31 * result + (deductMoney != null ? deductMoney.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (checkerCode != null ? checkerCode.hashCode() : 0);
        result = 31 * result + (checkerName != null ? checkerName.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (deducterCode != null ? deducterCode.hashCode() : 0);
        result = 31 * result + (deducterName != null ? deducterName.hashCode() : 0);
        result = 31 * result + (deductTime != null ? deductTime.hashCode() : 0);
        result = 31 * result + (createCode != null ? createCode.hashCode() : 0);
        result = 31 * result + (createName != null ? createName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (treatedType != null ? treatedType.hashCode() : 0);
        result = 31 * result + (treatedWeek != null ? treatedWeek.hashCode() : 0);
        result = 31 * result + (treatedNo != null ? treatedNo.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("summary").append("='").append(getSummary()).append("', ");
        sb.append("type").append("='").append(getType()).append("', ");
        sb.append("money").append("='").append(getMoney()).append("', ");
        sb.append("remainMoney").append("='").append(getRemainMoney()).append("', ");
        sb.append("deductMoney").append("='").append(getDeductMoney()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("checkerCode").append("='").append(getCheckerCode()).append("', ");
        sb.append("checkerName").append("='").append(getCheckerName()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("deducterCode").append("='").append(getDeducterCode()).append("', ");
        sb.append("deducterName").append("='").append(getDeducterName()).append("', ");
        sb.append("deductTime").append("='").append(getDeductTime()).append("', ");
        sb.append("createCode").append("='").append(getCreateCode()).append("', ");
        sb.append("createName").append("='").append(getCreateName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("treatedType").append("='").append(getTreatedType()).append("', ");
        sb.append("treatedWeek").append("='").append(getTreatedWeek()).append("', ");
        sb.append("treatedNo").append("='").append(getTreatedNo()).append("', ");
        sb.append("]");
      
        return sb.toString();
    }

}
