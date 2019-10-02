package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JMI_RECOMMEND_REF", uniqueConstraints = @UniqueConstraint(columnNames="TREE_INDEX") )

@XmlRootElement
public class JmiRecommendRef extends BaseObject implements Serializable {
    private String userCode;
    private Long layerId;
    private Long treeNo;
    private String treeIndex;
    private JmiMember jmiMember;
    private JmiMember recommendJmiMember;

	@Id    
    @Column(name="USER_CODE", unique=true, nullable=false, length=20)   
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    

    
    @Column(name="LAYER_ID", nullable=false, precision=12, scale=0)
    
    public Long getLayerId() {
        return this.layerId;
    }
    
    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }
    
    @Column(name="TREE_NO", precision=12, scale=0)
    
    public Long getTreeNo() {
        return this.treeNo;
    }
    
    public void setTreeNo(Long treeNo) {
        this.treeNo = treeNo;
    }
    
    @Column(name="TREE_INDEX", unique=true, nullable=false, length=4000)
    
    public String getTreeIndex() {
        return this.treeIndex;
    }
    
    public void setTreeIndex(String treeIndex) {
        this.treeIndex = treeIndex;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiRecommendRef pojo = (JmiRecommendRef) o;

        if (layerId != null ? !layerId.equals(pojo.layerId) : pojo.layerId != null) return false;
        if (treeNo != null ? !treeNo.equals(pojo.treeNo) : pojo.treeNo != null) return false;
        if (treeIndex != null ? !treeIndex.equals(pojo.treeIndex) : pojo.treeIndex != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (layerId != null ? layerId.hashCode() : 0);
        result = 31 * result + (treeNo != null ? treeNo.hashCode() : 0);
        result = 31 * result + (treeIndex != null ? treeIndex.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("layerId").append("='").append(getLayerId()).append("', ");
        sb.append("treeNo").append("='").append(getTreeNo()).append("', ");
        sb.append("treeIndex").append("='").append(getTreeIndex()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @OneToOne(mappedBy="jmiRecommendRef",fetch=FetchType.LAZY) 
    @PrimaryKeyJoinColumn
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "RECOMMEND_NO", nullable = false,  updatable = false)
	public JmiMember getRecommendJmiMember() {
		return recommendJmiMember;
	}

	public void setRecommendJmiMember(JmiMember recommendJmiMember) {
		this.recommendJmiMember = recommendJmiMember;
	}

}
