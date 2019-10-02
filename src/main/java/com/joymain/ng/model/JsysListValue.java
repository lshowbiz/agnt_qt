package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JSYS_LIST_VALUE")

@XmlRootElement
public class JsysListValue extends BaseObject implements Serializable {
    private Long valueId;
    //private Long keyId;
    private String valueCode;
    private String valueTitle;
    private String exCompanyCode;
    private Long orderNo;
    private JsysListKey jsysListKey; 
    
    @Id  @Column(name="VALUE_ID") @DocumentId    
    public Long getValueId() {
        return this.valueId;
    }
    
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
    
    @Column(name="VALUE_CODE", nullable=false, length=50)
    
    public String getValueCode() {
        return this.valueCode;
    }
    
    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }
    
    @Column(name="VALUE_TITLE", nullable=false, length=150)
    
    public String getValueTitle() {
        return this.valueTitle;
    }
    
    public void setValueTitle(String valueTitle) {
        this.valueTitle = valueTitle;
    }
    
    @Column(name="EX_COMPANY_CODE", length=200)
    
    public String getExCompanyCode() {
        return this.exCompanyCode;
    }
    
    public void setExCompanyCode(String exCompanyCode) {
        this.exCompanyCode = exCompanyCode;
    }
    
    @Column(name="ORDER_NO", precision=8, scale=0)
    
    public Long getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="key_Id")
	@JsonIgnore
    public JsysListKey getJsysListKey() {
		return jsysListKey;
	}

	public void setJsysListKey(JsysListKey jsysListKey) {
		this.jsysListKey = jsysListKey;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysListValue pojo = (JsysListValue) o;

       // if (keyId != null ? !keyId.equals(pojo.keyId) : pojo.keyId != null) return false;
        if (valueCode != null ? !valueCode.equals(pojo.valueCode) : pojo.valueCode != null) return false;
        if (valueTitle != null ? !valueTitle.equals(pojo.valueTitle) : pojo.valueTitle != null) return false;
        if (exCompanyCode != null ? !exCompanyCode.equals(pojo.exCompanyCode) : pojo.exCompanyCode != null) return false;
        if (orderNo != null ? !orderNo.equals(pojo.orderNo) : pojo.orderNo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        //result = (keyId != null ? keyId.hashCode() : 0);
        result = 31 * result + (valueCode != null ? valueCode.hashCode() : 0);
        result = 31 * result + (valueTitle != null ? valueTitle.hashCode() : 0);
        result = 31 * result + (exCompanyCode != null ? exCompanyCode.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("valueId").append("='").append(getValueId()).append("', ");
       // sb.append("keyId").append("='").append(getKeyId()).append("', ");
        sb.append("valueCode").append("='").append(getValueCode()).append("', ");
        sb.append("valueTitle").append("='").append(getValueTitle()).append("', ");
        sb.append("exCompanyCode").append("='").append(getExCompanyCode()).append("', ");
        sb.append("orderNo").append("='").append(getOrderNo()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
