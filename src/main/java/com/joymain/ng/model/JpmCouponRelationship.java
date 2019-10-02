package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="JPM_COUPON_RELATIONSHIP")

@XmlRootElement
public class JpmCouponRelationship extends BaseObject implements Serializable {
    private Long id;
    private Long cpId;
    private Long uniNo;
    private Date createTime;
    private String createUserCode;
    
    private JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
    private JpmCouponInfo jpmCouponInfo = new JpmCouponInfo();

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PM")
	@SequenceGenerator(name = "SEQ_PM", sequenceName = "SEQ_PM", allocationSize = 1)
	@Column(name="ID", unique=true, nullable=false)  
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="CP_ID", nullable=false, precision=22, scale=0)
    public Long getCpId() {
        return this.cpId;
    }
    
    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }
    
    @Column(name="UNI_NO", nullable=false, precision=22, scale=0)
    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="CREATE_USER_CODE", nullable=false, length=20)
    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmCouponRelationship pojo = (JpmCouponRelationship) o;

        if (cpId != null ? !cpId.equals(pojo.cpId) : pojo.cpId != null) return false;
        if (uniNo != null ? !uniNo.equals(pojo.uniNo) : pojo.uniNo != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (cpId != null ? cpId.hashCode() : 0);
        result = 31 * result + (uniNo != null ? uniNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("cpId").append("='").append(getCpId()).append("', ");
        sb.append("uniNo").append("='").append(getUniNo()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("createUserCode").append("='").append(getCreateUserCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }
    
    @ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "uni_no")
    @JsonIgnore
	public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}

	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

	@ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "cp_id")
    @JsonIgnore
	public JpmCouponInfo getJpmCouponInfo() {
		return jpmCouponInfo;
	}

	public void setJpmCouponInfo(JpmCouponInfo jpmCouponInfo) {
		this.jpmCouponInfo = jpmCouponInfo;
	}
}
