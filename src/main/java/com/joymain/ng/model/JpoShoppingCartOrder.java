package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="JPO_SHOPPING_CART_ORDER")

@XmlRootElement
public class JpoShoppingCartOrder extends BaseObject implements Serializable {
    private Long scId;
    private String orderType;
    private String isShipments;
    private String teamType;
    private JsysUser sysUser;
    private String companyCode;
    private Set<JpoShoppingCartOrderList> jpoShoppingCartOrderList= new HashSet<JpoShoppingCartOrderList>(0);
    private String isCheck;//是否进行购买，1为是，0为否
    private String isMobile;//是否为手机客户端购买，1为是，0为否

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
	@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="SC_ID", unique=true, nullable=false, precision=10, scale=0)   
    public Long getScId() {
        return this.scId;
    }
    
    public void setScId(Long scId) {
        this.scId = scId;
    }
    
    @Column(name="ORDER_TYPE", nullable=false, length=2)
    
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    @Column(name="IS_SHIPMENTS", length=2)
    
    public String getIsShipments() {
        return this.isShipments;
    }
    
    public void setIsShipments(String isShipments) {
        this.isShipments = isShipments;
    }
    
    @Column(name="TEAM_TYPE", nullable=false, length=10)
    
    public String getTeamType() {
        return this.teamType;
    }
    
    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "USER_CODE")
    @JsonIgnore
	public JsysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(JsysUser sysUser) {
		this.sysUser = sysUser;
	}
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    @OneToMany(cascade = CascadeType.ALL,mappedBy="jpoShoppingCartOrder",fetch=FetchType.LAZY)
	public Set<JpoShoppingCartOrderList> getJpoShoppingCartOrderList() {
		return jpoShoppingCartOrderList;
	}

	public void setJpoShoppingCartOrderList(
			Set<JpoShoppingCartOrderList> jpoShoppingCartOrderList) {
		this.jpoShoppingCartOrderList = jpoShoppingCartOrderList;
	}
	@Column(name="IS_CHECK", nullable=false, length=2)
	
	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	@Column(name="IS_MOBILE", nullable=false, length=2)
	public String getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoShoppingCartOrder pojo = (JpoShoppingCartOrder) o;
        if (scId != null ? !scId.equals(pojo.scId) : pojo.scId != null) return false;
        if (orderType != null ? !orderType.equals(pojo.orderType) : pojo.orderType != null) return false;
        if (isShipments != null ? !isShipments.equals(pojo.isShipments) : pojo.isShipments != null) return false;
        if (teamType != null ? !teamType.equals(pojo.teamType) : pojo.teamType != null) return false;
    
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result +  (scId != null ? scId.hashCode() : 0);
        result = 31 * result +  (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (isShipments != null ? isShipments.hashCode() : 0);
        result = 31 * result + (teamType != null ? teamType.hashCode() : 0);
     
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("scId").append("='").append(getScId()).append("', ");
        sb.append("orderType").append("='").append(getOrderType()).append("', ");
        sb.append("isShipments").append("='").append(getIsShipments()).append("', ");
        sb.append("teamType").append("='").append(getTeamType()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }





}
