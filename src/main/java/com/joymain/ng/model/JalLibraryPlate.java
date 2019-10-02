package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
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
@Table(name="JAL_LIBRARY_PLATE")

@XmlRootElement
public class JalLibraryPlate extends BaseObject implements Serializable {
    private Long plateId;
    private String plateName;
    private String plateRemark;
    private Integer plateIndex;

    @Id      @Column(name="PLATE_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getPlateId() {
        return this.plateId;
    }
    
    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }
    
    @Column(name="PLATE_NAME", nullable=false, length=300)
    public String getPlateName() {
        return this.plateName;
    }
    
    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }
    
    @Column(name="PLATE_REMARK", length=300)
    public String getPlateRemark() {
        return this.plateRemark;
    }
    
    public void setPlateRemark(String plateRemark) {
        this.plateRemark = plateRemark;
    }

    @Column(name="PLATE_INDEX", precision=2, scale=0)
    public Integer getPlateIndex() {
		return plateIndex;
	}

	public void setPlateIndex(Integer plateIndex) {
		this.plateIndex = plateIndex;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalLibraryPlate pojo = (JalLibraryPlate) o;

        if (plateName != null ? !plateName.equals(pojo.plateName) : pojo.plateName != null) return false;
        if (plateRemark != null ? !plateRemark.equals(pojo.plateRemark) : pojo.plateRemark != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (plateName != null ? plateName.hashCode() : 0);
        result = 31 * result + (plateRemark != null ? plateRemark.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("plateId").append("='").append(getPlateId()).append("', ");
        sb.append("plateName").append("='").append(getPlateName()).append("', ");
        sb.append("plateRemark").append("='").append(getPlateRemark()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
