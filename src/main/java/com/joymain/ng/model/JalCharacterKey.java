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
@Table(name="JAL_CHARACTER_KEY")

@XmlRootElement
public class JalCharacterKey extends BaseObject implements Serializable {
    private Long keyId;
    private String characterKey;
    private String keyDesc;
    private Long typeId;
    private String usedFlag;

    @Id  @DocumentId    @Column(name="KEY_ID")
    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    
    @Column(name="CHARACTER_KEY", length=150)
    
    public String getCharacterKey() {
        return this.characterKey;
    }
    
    public void setCharacterKey(String characterKey) {
        this.characterKey = characterKey;
    }
    
    @Column(name="KEY_DESC", length=500)
    
    public String getKeyDesc() {
        return this.keyDesc;
    }
    
    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }
    
    @Column(name="TYPE_ID", precision=12, scale=0)
    
    public Long getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    
    @Column(name="USED_FLAG", length=1)
    
    public String getUsedFlag() {
        return this.usedFlag;
    }
    
    public void setUsedFlag(String usedFlag) {
        this.usedFlag = usedFlag;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalCharacterKey pojo = (JalCharacterKey) o;

        if (characterKey != null ? !characterKey.equals(pojo.characterKey) : pojo.characterKey != null) return false;
        if (keyDesc != null ? !keyDesc.equals(pojo.keyDesc) : pojo.keyDesc != null) return false;
        if (typeId != null ? !typeId.equals(pojo.typeId) : pojo.typeId != null) return false;
        if (usedFlag != null ? !usedFlag.equals(pojo.usedFlag) : pojo.usedFlag != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (characterKey != null ? characterKey.hashCode() : 0);
        result = 31 * result + (keyDesc != null ? keyDesc.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (usedFlag != null ? usedFlag.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("keyId").append("='").append(getKeyId()).append("', ");
        sb.append("characterKey").append("='").append(getCharacterKey()).append("', ");
        sb.append("keyDesc").append("='").append(getKeyDesc()).append("', ");
        sb.append("typeId").append("='").append(getTypeId()).append("', ");
        sb.append("usedFlag").append("='").append(getUsedFlag()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
