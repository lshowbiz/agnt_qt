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
@Table(name="JAL_CHARACTER_CODING")

@XmlRootElement
public class JalCharacterCoding extends BaseObject implements Serializable {
    private Long characterId;
    private String characterCode;
    private String characterName;
    private String allowedUser;

    @Id  @DocumentId   @Column(name="CHARACTER_ID" )
    public Long getCharacterId() {
        return this.characterId;
    }
    
    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }
    
    @Column(name="CHARACTER_CODE", length=10)
    
    public String getCharacterCode() {
        return this.characterCode;
    }
    
    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }
    
    @Column(name="CHARACTER_NAME", length=100)
    
    public String getCharacterName() {
        return this.characterName;
    }
    
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    
    @Column(name="ALLOWED_USER", length=500)
    
    public String getAllowedUser() {
        return this.allowedUser;
    }
    
    public void setAllowedUser(String allowedUser) {
        this.allowedUser = allowedUser;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalCharacterCoding pojo = (JalCharacterCoding) o;

        if (characterCode != null ? !characterCode.equals(pojo.characterCode) : pojo.characterCode != null) return false;
        if (characterName != null ? !characterName.equals(pojo.characterName) : pojo.characterName != null) return false;
        if (allowedUser != null ? !allowedUser.equals(pojo.allowedUser) : pojo.allowedUser != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (characterCode != null ? characterCode.hashCode() : 0);
        result = 31 * result + (characterName != null ? characterName.hashCode() : 0);
        result = 31 * result + (allowedUser != null ? allowedUser.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("characterId").append("='").append(getCharacterId()).append("', ");
        sb.append("characterCode").append("='").append(getCharacterCode()).append("', ");
        sb.append("characterName").append("='").append(getCharacterName()).append("', ");
        sb.append("allowedUser").append("='").append(getAllowedUser()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
