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
@Table(name="LINKMAN_CATEGORY")

@XmlRootElement
public class LinkmanCategory extends BaseObject implements Serializable {
    private Long id;
    private String name;
    private String loginUserNo;
    private String userCode;

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) 
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="NAME", length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkmanCategory pojo = (LinkmanCategory) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (loginUserNo != null ? !loginUserNo.equals(pojo.loginUserNo) : pojo.loginUserNo != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (loginUserNo != null ? loginUserNo.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("loginUserNo").append("='").append(getLoginUserNo()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
