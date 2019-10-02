package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name="JMI_LINK_REF", uniqueConstraints = @UniqueConstraint(columnNames="TREE_INDEX") )

@XmlRootElement
public class JmiLinkRef extends BaseObject implements Serializable {
    private String userCode;
    private Long layerId;
    private Long treeNo;
    private String treeIndex;
    private BigDecimal departmentPv;
    private BigDecimal num;
    private JmiMember jmiMember;
    private JmiMember linkJmiMember;

    @OneToOne(mappedBy="jmiLinkRef",fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LINK_NO", nullable = false,  updatable = false)
	public JmiMember getLinkJmiMember() {
		return linkJmiMember;
	}

	public void setLinkJmiMember(JmiMember linkJmiMember) {
		this.linkJmiMember = linkJmiMember;
	}

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
    
    @Column(name="DEPARTMENT_PV", precision=22, scale=0)
    
    public BigDecimal getDepartmentPv() {
        return this.departmentPv;
    }
    
    public void setDepartmentPv(BigDecimal departmentPv) {
        this.departmentPv = departmentPv;
    }
    
    @Column(name="NUM", precision=22, scale=0)
    
    public BigDecimal getNum() {
        return this.num;
    }
    
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiLinkRef pojo = (JmiLinkRef) o;

        if (layerId != null ? !layerId.equals(pojo.layerId) : pojo.layerId != null) return false;
        if (treeNo != null ? !treeNo.equals(pojo.treeNo) : pojo.treeNo != null) return false;
        if (treeIndex != null ? !treeIndex.equals(pojo.treeIndex) : pojo.treeIndex != null) return false;
        if (departmentPv != null ? !departmentPv.equals(pojo.departmentPv) : pojo.departmentPv != null) return false;
        if (num != null ? !num.equals(pojo.num) : pojo.num != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (layerId != null ? layerId.hashCode() : 0);
        result = 31 * result + (treeNo != null ? treeNo.hashCode() : 0);
        result = 31 * result + (treeIndex != null ? treeIndex.hashCode() : 0);
        result = 31 * result + (departmentPv != null ? departmentPv.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("layerId").append("='").append(getLayerId()).append("', ");
        sb.append("treeNo").append("='").append(getTreeNo()).append("', ");
        sb.append("treeIndex").append("='").append(getTreeIndex()).append("', ");
        sb.append("departmentPv").append("='").append(getDepartmentPv()).append("', ");
        sb.append("num").append("='").append(getNum()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
