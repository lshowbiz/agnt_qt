package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JAL_LIBRARY_COLUMN")

@XmlRootElement
public class JalLibraryColumn extends BaseObject implements Serializable {
    private Long columnId;
    private String columnName;
    private Long plateId;
    private String columnImgs;
    private String columnField1;
    private Long status;
    private String createUserCode;
    private String createName;
    private Date createTime;
    private String plateName;

    @Id      @Column(name="COLUMN_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getColumnId() {
        return this.columnId;
    }
    
    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }
    
    @Column(name="COLUMN_NAME", nullable=false, length=300)
    public String getColumnName() {
        return this.columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    @Column(name="PLATE_ID", nullable=false, precision=12, scale=0)
    public Long getPlateId() {
        return this.plateId;
    }
    
    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }
    
    @Column(name="COLUMN_IMGS", length=300)
    public String getColumnImgs() {
        return this.columnImgs;
    }
    
    public void setColumnImgs(String columnImgs) {
        this.columnImgs = columnImgs;
    }
    
    @Column(name="COLUMN_FIELD1", length=300)
    public String getColumnField1() {
        return this.columnField1;
    }
    
    public void setColumnField1(String columnField1) {
        this.columnField1 = columnField1;
    }
    
    @Column(name="STATUS", precision=2, scale=0)
    public Long getStatus() {
        return this.status;
    }
    
    public void setStatus(Long status) {
        this.status = status;
    }
    
    @Column(name="CREATE_USER_CODE", length=20)
    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    
    @Column(name="CREATE_NAME", length=300)
    public String getCreateName() {
        return this.createName;
    }
    
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="PLATE_NAME", length=300)
    public String getPlateName() {
        return this.plateName;
    }
    
    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalLibraryColumn pojo = (JalLibraryColumn) o;

        if (columnName != null ? !columnName.equals(pojo.columnName) : pojo.columnName != null) return false;
        if (plateId != null ? !plateId.equals(pojo.plateId) : pojo.plateId != null) return false;
        if (columnImgs != null ? !columnImgs.equals(pojo.columnImgs) : pojo.columnImgs != null) return false;
        if (columnField1 != null ? !columnField1.equals(pojo.columnField1) : pojo.columnField1 != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null) return false;
        if (createName != null ? !createName.equals(pojo.createName) : pojo.createName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (plateName != null ? !plateName.equals(pojo.plateName) : pojo.plateName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (columnName != null ? columnName.hashCode() : 0);
        result = 31 * result + (plateId != null ? plateId.hashCode() : 0);
        result = 31 * result + (columnImgs != null ? columnImgs.hashCode() : 0);
        result = 31 * result + (columnField1 != null ? columnField1.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);
        result = 31 * result + (createName != null ? createName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (plateName != null ? plateName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("columnId").append("='").append(getColumnId()).append("', ");
        sb.append("columnName").append("='").append(getColumnName()).append("', ");
        sb.append("plateId").append("='").append(getPlateId()).append("', ");
        sb.append("columnImgs").append("='").append(getColumnImgs()).append("', ");
        sb.append("columnField1").append("='").append(getColumnField1()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("createUserCode").append("='").append(getCreateUserCode()).append("', ");
        sb.append("createName").append("='").append(getCreateName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("plateName").append("='").append(getPlateName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
