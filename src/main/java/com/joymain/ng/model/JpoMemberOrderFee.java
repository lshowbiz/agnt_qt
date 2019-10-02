package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="JPO_MEMBER_ORDER_FEE")

@XmlRootElement
public class JpoMemberOrderFee extends BaseObject implements Serializable {
    private Long mofId;
    private JpoMemberOrder jpoMemberOrder;
    private BigDecimal fee;
    private String feeType;
    private String detailType;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
	@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="MOF_ID", unique=true, nullable=false, precision=10, scale=0)    
    public Long getMofId() {
        return this.mofId;
    }
    
    public void setMofId(Long mofId) {
        this.mofId = mofId;
    }
    

    
    @Column(name="FEE", nullable=false, precision=18)
    public BigDecimal getFee() {
        return this.fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    
    @Column(name="FEE_TYPE", nullable=false, length=2)
    public String getFeeType() {
        return this.feeType;
    }
    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    
    @Column(name="DETAIL_TYPE", nullable=false, length=10)
    public String getDetailType() {
        return this.detailType;
    }
    
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MO_ID")
	@JsonIgnore
	public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoMemberOrderFee pojo = (JpoMemberOrderFee) o;

        if (mofId != null ? !mofId.equals(pojo.mofId) : pojo.mofId != null) return false;
        if(jpoMemberOrder.getMoId()!=null ? !(jpoMemberOrder.getMoId()==pojo.jpoMemberOrder.getMoId()) : pojo.jpoMemberOrder.getMoId()!=null ) return false;
        if (fee != null ? !fee.equals(pojo.fee) : pojo.fee != null) return false;
        if (feeType != null ? !feeType.equals(pojo.feeType) : pojo.feeType != null) return false;
        if (detailType != null ? !detailType.equals(pojo.detailType) : pojo.detailType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (mofId != null ? mofId.hashCode() : 0);
        result = 31 * result + (jpoMemberOrder.getMoId() != null ? jpoMemberOrder.getMoId().hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (feeType != null ? feeType.hashCode() : 0);
        result = 31 * result + (detailType != null ? detailType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("mofId").append("='").append(getMofId()).append("', ");
     
        sb.append("fee").append("='").append(getFee()).append("', ");
        sb.append("feeType").append("='").append(getFeeType()).append("', ");
        sb.append("detailType").append("='").append(getDetailType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }


}
