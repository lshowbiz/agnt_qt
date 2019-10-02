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
@Table(name="FOUNDATION_ITEM")

@XmlRootElement
public class FoundationItem extends BaseObject implements Serializable {
	
    private Long fiId;
    private String name;
    private BigDecimal amount;
    private String status;
    private String remark;
    private String field1;//图片链接
    private String field2;//排序字段
    private String unit;
    private String prompt;
    private String type;

    @Id      @Column(name="FI_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getFiId() {
        return this.fiId;
    }
    
    public void setFiId(Long fiId) {
        this.fiId = fiId;
    }
    
    @Column(name="NAME", nullable=false, length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="AMOUNT", nullable=false, precision=18, scale=4)
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Column(name="STATUS", nullable=false, length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="REMARK", length=300)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="FIELD1", length=50)
    public String getField1() {
        return this.field1;
    }
    
    public void setField1(String field1) {
        this.field1 = field1;
    }
    
    @Column(name="FIELD2", length=50)
    public String getField2() {
        return this.field2;
    }
    
    public void setField2(String field2) {
        this.field2 = field2;
    }
    
    @Column(name="UNIT", nullable=false, length=20)
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    @Column(name="PROMPT", length=200)
    public String getPrompt() {
        return this.prompt;
    }
    
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
    @Column(name="TYPE", nullable=false, length=2)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoundationItem pojo = (FoundationItem) o;

        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        //if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (field1 != null ? !field1.equals(pojo.field1) : pojo.field1 != null) return false;
        if (field2 != null ? !field2.equals(pojo.field2) : pojo.field2 != null) return false;
        if (unit != null ? !unit.equals(pojo.unit) : pojo.unit != null) return false;
        if (prompt != null ? !prompt.equals(pojo.prompt) : pojo.prompt != null) return false;
        if (type != null ? !type.equals(pojo.type) : pojo.type != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (name != null ? name.hashCode() : 0);
        //result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (field1 != null ? field1.hashCode() : 0);
        result = 31 * result + (field2 != null ? field2.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (prompt != null ? prompt.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fiId").append("='").append(getFiId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        //sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("field1").append("='").append(getField1()).append("', ");
        sb.append("field2").append("='").append(getField2()).append("', ");
        sb.append("unit").append("='").append(getUnit()).append("', ");
        sb.append("prompt").append("='").append(getPrompt()).append("', ");
        sb.append("type").append("='").append(getType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
