package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="JFI_AMOUNT_DETAIL")

@XmlRootElement
public class JfiAmountDetails extends BaseObject implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long quotaId;
    private String memberOrderNo;
    private String money;
    private String userCode;
    private Date createTime;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fi")
	@SequenceGenerator(name = "seq_fi", sequenceName = "SEQ_FI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=12, scale=0)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="QUOTA_ID", precision=12, scale=0)
    public Long getQuotaId() {
        return this.quotaId;
    }
    
    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
    }
    
    @Column(name="MEMBER_ORDER_NO", length=20)
    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }
    
    @Column(name="MONEY", length=256)
    public String getMoney() {
        return this.money;
    }
    
    public void setMoney(String money) {
        this.money = money;
    }
    
    @Column(name="USER_CODE", length=10)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JfiAmountDetails pojo = (JfiAmountDetails) o;

        if (quotaId != null ? !quotaId.equals(pojo.quotaId) : pojo.quotaId != null) return false;
        if (memberOrderNo != null ? !memberOrderNo.equals(pojo.memberOrderNo) : pojo.memberOrderNo != null) return false;
        if (money != null ? !money.equals(pojo.money) : pojo.money != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (quotaId != null ? quotaId.hashCode() : 0);
        result = 31 * result + (memberOrderNo != null ? memberOrderNo.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("quotaId").append("='").append(getQuotaId()).append("', ");
        sb.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("', ");
        sb.append("money").append("='").append(getMoney()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
