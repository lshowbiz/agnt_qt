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
import java.math.BigDecimal;

@Entity
@Table(name="JBD_BONUS_FUND")

@XmlRootElement
public class JbdBonusFund extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private BigDecimal money;
    private Date createTime;
    private Integer wweek;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bd")
	@SequenceGenerator(name = "seq_bd", sequenceName = "SEQ_BD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)      
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="MONEY", precision=22, scale=0)
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="W_WEEK", precision=22, scale=0)
    public Integer getWweek() {
		return wweek;
	}

	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdBonusFund pojo = (JbdBonusFund) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (money != null ? !money.equals(pojo.money) : pojo.money != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (wweek != null ? !wweek.equals(pojo.wweek) : pojo.wweek != null) return false;

        return true;
    }


	public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (wweek != null ? wweek.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("money").append("='").append(getMoney()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("WWeek").append("='").append(getWweek()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
