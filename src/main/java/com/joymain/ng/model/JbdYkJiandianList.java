package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="JBD_YK_JIANDIAN_LIST")

@XmlRootElement
public class JbdYkJiandianList extends BaseObject implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private Long id;
    private Integer wweek;
    private String userCode;
    private String petName;
    private String userType;
    private Integer memberLevel;
    private BigDecimal ykRefMoney;
    private Date comTime;
    private String reuserCode;
    private Integer nlevel;
    
    @Id
   	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bd")
   	@SequenceGenerator(name = "seq_bd", sequenceName = "SEQ_BD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0)     
	public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="W_WEEK", precision=22, scale=0)
	public Integer getWweek() {
		return wweek;
	}
	
	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}
	
	@Column(name="USER_CODE", nullable=false, length=20)
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Column(name="PET_NAME", nullable=false, length=200)
	public String getPetName() {
		return petName;
	}
	
	public void setPetName(String petName) {
		this.petName = petName;
	}
	
	@Column(name="USER_TYPE", nullable=false, length=4)
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Column(name="MEMBER_LEVEL", precision=22, scale=0)
	public Integer getMemberLevel() {
		return memberLevel;
	}
	
	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
	
	@Column(name="YK_REF_MONEY", precision=22, scale=0)
	public BigDecimal getYkRefMoney() {
		return ykRefMoney;
	}
	
	public void setYkRefMoney(BigDecimal ykRefMoney) {
		this.ykRefMoney = ykRefMoney;
	}
	
	@Column(name="COM_TIME", length=7)
	public Date getComTime() {
		return comTime;
	}
	
	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}
	
	@Column(name="REUSER_CODE", nullable=false, length=20)
	public String getReuserCode() {
		return reuserCode;
	}

	public void setReuserCode(String reuserCode) {
		this.reuserCode = reuserCode;
	}

	@Column(name="NLEVEL", precision=22, scale=0)
	public Integer getNlevel() {
		return nlevel;
	}
	
	public void setNlevel(Integer nlevel) {
		this.nlevel = nlevel;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdYkJiandianList pojo = (JbdYkJiandianList) o;

        if (wweek != null ? !wweek.equals(pojo.wweek) : pojo.wweek != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (petName != null ? !petName.equals(pojo.petName) : pojo.petName != null) return false;
        if (userType != null ? !userType.equals(pojo.userType) : pojo.userType != null) return false;
        if (memberLevel != null ? !memberLevel.equals(pojo.memberLevel) : pojo.memberLevel != null) return false;
        if (ykRefMoney != null ? !ykRefMoney.equals(pojo.ykRefMoney) : pojo.ykRefMoney != null) return false;
        if (comTime != null ? !comTime.equals(pojo.comTime) : pojo.comTime != null) return false;
        if (reuserCode != null ? !reuserCode.equals(pojo.reuserCode) : pojo.reuserCode != null) return false;
        if (nlevel != null ? !nlevel.equals(pojo.nlevel) : pojo.nlevel != null) return false;
        
        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (wweek != null ? wweek.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (petName != null ? petName.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (memberLevel != null ? memberLevel.hashCode() : 0);
        result = 31 * result + (ykRefMoney != null ? ykRefMoney.hashCode() : 0);
        result = 31 * result + (comTime != null ? comTime.hashCode() : 0);
        result = 31 * result + (reuserCode != null ? reuserCode.hashCode() : 0);
        result = 31 * result + (nlevel != null ? nlevel.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("wweek").append("='").append(getWweek()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("petName").append("='").append(getPetName()).append("', ");
        
        sb.append("userType").append("='").append(getUserType()).append("', ");
        sb.append("memberLevel").append("='").append(getMemberLevel()).append("', ");
        sb.append("ykRefMoney").append("='").append(getYkRefMoney()).append("', ");
        
        sb.append("comTime").append("='").append(getComTime()).append("', ");
        sb.append("reuserCode").append("='").append(getReuserCode()).append("', ");
        sb.append("nlevel").append("='").append(getNlevel());
        sb.append("]");
      
        return sb.toString();
    }
}
