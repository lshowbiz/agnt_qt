package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="FI_SECURITY_DEPOSIT")

@XmlRootElement
public class FiSecurityDeposit extends BaseObject implements Serializable {
    private Long fsdId;
    private String userCode;
    private String userName;
    private String tel;
    private String email;
    private BigDecimal balance;
    private String dept;

    @Id      @Column(name="FSD_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getFsdId() {
        return this.fsdId;
    }
    
    public void setFsdId(Long fsdId) {
        this.fsdId = fsdId;
    }
    
    @Column(name="USER_CODE", nullable=false, length=40)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="USER_NAME", length=40)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="TEL", length=40)
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Column(name="EMAIL", length=80)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="BALANCE", precision=18, scale=4)
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Column(name="DEPT", length=20)
    public String getDept() {
        return this.dept;
    }
    
    public void setDept(String dept) {
        this.dept = dept;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiSecurityDeposit pojo = (FiSecurityDeposit) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;
        if (tel != null ? !tel.equals(pojo.tel) : pojo.tel != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        //if (balance != null ? !balance.equals(pojo.balance) : pojo.balance != null) return false;
        if (dept != null ? !dept.equals(pojo.dept) : pojo.dept != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        //result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fsdId").append("='").append(getFsdId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("tel").append("='").append(getTel()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
       // sb.append("balance").append("='").append(getBalance()).append("', ");
        sb.append("dept").append("='").append(getDept()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
