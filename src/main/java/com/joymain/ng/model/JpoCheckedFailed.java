package com.joymain.ng.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Embeddable
@Table(name="JPO_CHECKED_FAILED")

@XmlRootElement
@Entity
public class JpoCheckedFailed extends BaseObject implements Serializable {
	
    private Long FId;
   
    private JpoMemberOrder jpoMemberOrder;
    
    private JsysUser operatorSysuser; 
    
    private String dataType;


    
    @Id      
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
	@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1) 
    @Column(name="F_ID", nullable=false, precision=10, scale=0)
    public Long getFId() {
        return this.FId;
    }
    
    public void setFId(Long FId) {
        this.FId = FId;
    }

    @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name = "MO_ID")
	 @JsonIgnore
     public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OPERATOR_SYSUSER")
	@JsonIgnore
	public JsysUser getOperatorSysuser() {
		return operatorSysuser;
	}

	public void setOperatorSysuser(JsysUser operatorSysuser) {
		this.operatorSysuser = operatorSysuser;
	}


    @Column(name="DATA_TYPE", nullable=false, length=2)
    public String getDataType() {
        return this.dataType;
    }
    
	public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoCheckedFailed pojo = (JpoCheckedFailed) o;

        if (FId != null ? !FId.equals(pojo.FId) : pojo.FId != null) return false;
        if (operatorSysuser != null ? !operatorSysuser.equals(pojo.operatorSysuser) : pojo.operatorSysuser != null) return false;
        if (dataType != null ? !dataType.equals(pojo.dataType) : pojo.dataType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (FId != null ? FId.hashCode() : 0);
        result = 31 * result + (operatorSysuser != null ? operatorSysuser.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("FId").append("='").append(getFId()).append("', ");
        sb.append("operatorSysuser").append("='").append(getOperatorSysuser()).append("', ");
        sb.append("dataType").append("='").append(getDataType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
