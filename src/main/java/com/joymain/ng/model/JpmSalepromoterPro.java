package com.joymain.ng.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_SALEPROMOTER_PRO")

@XmlRootElement
public class JpmSalepromoterPro extends BaseObject implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long spno;
    private String prono;
    private Long pttid;
    private JpmSalePromoter jpmSalePromoter;
    private Long pronum;
    
    
    @Id      @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="SPNO", nullable=false, precision=10, scale=0)
    public Long getSpno() {
        return this.spno;
    }
    
    public void setSpno(Long spno) {
        this.spno = spno;
    }
    
    @Column(name="PRO_NUM")
	public Long getPronum() {
		return pronum;
	}


	public void setPronum(Long pronum) {
		this.pronum = pronum;
	}
    @Column(name="PRONO", length=20)
    public String getProno() {
        return this.prono;
    }
    
    public void setProno(String prono) {
        this.prono = prono;
    }
    
    @Column(name="PTTID", precision=22, scale=0)
    public Long getPttid() {
        return this.pttid;
    }
    
    public void setPttid(Long pttid) {
        this.pttid = pttid;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmSalepromoterPro pojo = (JpmSalepromoterPro) o;

        if (spno != null ? !spno.equals(pojo.spno) : pojo.spno != null) return false;
        if (prono != null ? !prono.equals(pojo.prono) : pojo.prono != null) return false;
        if (pttid != null ? !pttid.equals(pojo.pttid) : pojo.pttid != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (spno != null ? spno.hashCode() : 0);
        result = 31 * result + (prono != null ? prono.hashCode() : 0);
        result = 31 * result + (pttid != null ? pttid.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("spno").append("='").append(getSpno()).append("', ");
        sb.append("prono").append("='").append(getProno()).append("', ");
        sb.append("pttid").append("='").append(getPttid()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToOne(cascade=CascadeType.PERSIST)  
    @JoinColumn(name = "spno")
    @JsonIgnore
	public JpmSalePromoter getJpmSalePromoter() {
		return jpmSalePromoter;
	}

	public void setJpmSalePromoter(JpmSalePromoter jpmSalePromoter) {
		this.jpmSalePromoter = jpmSalePromoter;
	}

    
    
}
