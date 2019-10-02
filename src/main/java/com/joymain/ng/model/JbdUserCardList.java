package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;


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
@Table(name="JBD_USER_CARD_LIST")

@XmlRootElement
public class JbdUserCardList extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private Integer wweek;
    private String oldCard;
    private String newCard;
    private String updateType;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bd")
	@SequenceGenerator(name = "seq_bd", sequenceName = "SEQ_BD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=10, scale=0)   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    

    
    
    @Column(name="OLD_CARD", length=2)
    public String getOldCard() {
        return this.oldCard;
    }
    
    public void setOldCard(String oldCard) {
        this.oldCard = oldCard;
    }
    
    @Column(name="NEW_CARD", length=2)
    public String getNewCard() {
        return this.newCard;
    }
    
    public void setNewCard(String newCard) {
        this.newCard = newCard;
    }
    
    @Column(name="UPDATE_TYPE", length=1)
    public String getUpdateType() {
        return this.updateType;
    }
    
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdUserCardList pojo = (JbdUserCardList) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (oldCard != null ? !oldCard.equals(pojo.oldCard) : pojo.oldCard != null) return false;
        if (newCard != null ? !newCard.equals(pojo.newCard) : pojo.newCard != null) return false;
        if (updateType != null ? !updateType.equals(pojo.updateType) : pojo.updateType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (oldCard != null ? oldCard.hashCode() : 0);
        result = 31 * result + (newCard != null ? newCard.hashCode() : 0);
        result = 31 * result + (updateType != null ? updateType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("oldCard").append("='").append(getOldCard()).append("', ");
        sb.append("newCard").append("='").append(getNewCard()).append("', ");
        sb.append("updateType").append("='").append(getUpdateType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @Column(name="W_WEEK", precision=22, scale=0)
	public Integer getWweek() {
		return wweek;
	}

	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}

}
