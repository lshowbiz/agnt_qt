package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

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

@Entity
@Table(name="JAL_CHARACTER_VALUE")

@XmlRootElement
public class JalCharacterValue extends BaseObject implements Serializable {
    private Long valueId;
    private Long keyId;
    private String characterValue;
    private String characterCode;

    @Id  @DocumentId    @Column(name="VALUE_ID")
    public Long getValueId() {
        return this.valueId;
    }
    
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
    
    @Column(name="KEY_ID", precision=22, scale=0)
    
    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    
    @Column(name="CHARACTER_VALUE", length=2000)
    
    public String getCharacterValue() {
        return this.characterValue;
    }
    
    public void setCharacterValue(String characterValue) {
        this.characterValue = characterValue;
    }
    
    @Column(name="CHARACTER_CODE", length=10)
    
    public String getCharacterCode() {
        return this.characterCode;
    }
    
    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalCharacterValue pojo = (JalCharacterValue) o;

        if (keyId != null ? !keyId.equals(pojo.keyId) : pojo.keyId != null) return false;
        if (characterValue != null ? !characterValue.equals(pojo.characterValue) : pojo.characterValue != null) return false;
        if (characterCode != null ? !characterCode.equals(pojo.characterCode) : pojo.characterCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (keyId != null ? keyId.hashCode() : 0);
        result = 31 * result + (characterValue != null ? characterValue.hashCode() : 0);
        result = 31 * result + (characterCode != null ? characterCode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("valueId").append("='").append(getValueId()).append("', ");
        sb.append("keyId").append("='").append(getKeyId()).append("', ");
        sb.append("characterValue").append("='").append(getCharacterValue()).append("', ");
        sb.append("characterCode").append("='").append(getCharacterCode()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
