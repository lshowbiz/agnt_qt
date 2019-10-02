package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name="PD_SHIP_FEE")

@XmlRootElement
public class PdShipFee extends BaseObject implements RowMapper<PdShipFee>, Serializable {
    private String psfId;
    private String provinceName;
    private BigDecimal fee;

    @Id      @Column(name="PSF_ID", unique=true, nullable=false, length=32) @DocumentId    
    public String getPsfId() {
        return this.psfId;
    }
    
    public void setPsfId(String psfId) {
        this.psfId = psfId;
    }
    
    @Column(name="PROVINCE_NAME", nullable=false, length=20)
    public String getProvinceName() {
        return this.provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
    @Column(name="FEE", nullable=false, precision=5)
    public BigDecimal getFee() {
        return this.fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdShipFee pojo = (PdShipFee) o;

        if (provinceName != null ? !provinceName.equals(pojo.provinceName) : pojo.provinceName != null) return false;
        if (fee != null ? !fee.equals(pojo.fee) : pojo.fee != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (provinceName != null ? provinceName.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("psfId").append("='").append(getPsfId()).append("', ");
        sb.append("provinceName").append("='").append(getProvinceName()).append("', ");
        sb.append("fee").append("='").append(getFee()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

	@Override
	public PdShipFee mapRow(ResultSet rs, int arg1) throws SQLException {
		PdShipFee fee= new PdShipFee();
		
		fee.setFee(rs.getBigDecimal("fee"));
		fee.setProvinceName(rs.getString("province_name"));
		fee.setPsfId(rs.getString("psf_id"));
		
		return fee;
	}

}
