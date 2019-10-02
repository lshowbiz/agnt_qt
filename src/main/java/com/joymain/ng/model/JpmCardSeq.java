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
import javax.persistence.Version;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JPM_CARD_SEQ")

@XmlRootElement
public class JpmCardSeq extends BaseObject implements Serializable {
    private Long id;
    private String cardNo;
    private String memberOrderNo;
    private String password;
    private String userCode;
    private Date createTime;
    private String state;
    private Long molId;
    private String grade;
    private Integer version=new Integer(0);

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys")
	@SequenceGenerator(name = "seq_sys", sequenceName = "SEQ_SYS", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)     
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="CARD_NO", length=30)
    public String getCardNo() {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    
    @Column(name="MEMBER_ORDER_NO", length=20)
    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }
    
    @Column(name="PASSWORD", length=30)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="STATE", length=1)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="MOL_ID", precision=10, scale=0)
    public Long getMolId() {
        return this.molId;
    }
    
    public void setMolId(Long molId) {
        this.molId = molId;
    }
    

    @Column(name="GRADE", length=1)
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmCardSeq pojo = (JpmCardSeq) o;

        if (version != null ? !version.equals(pojo.version) : pojo.version != null) return false;
        if (cardNo != null ? !cardNo.equals(pojo.cardNo) : pojo.cardNo != null) return false;
        if (memberOrderNo != null ? !memberOrderNo.equals(pojo.memberOrderNo) : pojo.memberOrderNo != null) return false;
        if (password != null ? !password.equals(pojo.password) : pojo.password != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (state != null ? !state.equals(pojo.state) : pojo.state != null) return false;
        if (molId != null ? !molId.equals(pojo.molId) : pojo.molId != null) return false;
        if (grade != null ? !grade.equals(pojo.grade) : pojo.grade != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (version != null ? version.hashCode() : 0);
        result = 31 * result + (cardNo != null ? cardNo.hashCode() : 0);
        result = 31 * result + (memberOrderNo != null ? memberOrderNo.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (molId != null ? molId.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("version").append("='").append(getVersion()).append("', ");
        sb.append("cardNo").append("='").append(getCardNo()).append("', ");
        sb.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("', ");
        sb.append("password").append("='").append(getPassword()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("state").append("='").append(getState()).append("', ");
        sb.append("molId").append("='").append(getMolId()).append("', ");
        sb.append("grade").append("='").append(getGrade()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @Version
    @Column(name="VERSION", precision=8, scale=0)
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
