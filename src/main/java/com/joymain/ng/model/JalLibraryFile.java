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
@Table(name="JAL_LIBRARY_FILE")

@XmlRootElement
public class JalLibraryFile extends BaseObject implements Serializable {
    private Long fileId;
    private String fileName;
    private Long columnId;
    private String fileUrl;
    private String fileField1;
    private String fileField2;
    private String fileField3;
    private String columnName;

    @Id      @Column(name="FILE_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getFileId() {
        return this.fileId;
    }
    
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
    
    @Column(name="FILE_NAME", length=300)
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    @Column(name="COLUMN_ID", precision=12, scale=0)
    public Long getColumnId() {
        return this.columnId;
    }
    
    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }
    
    @Column(name="FILE_URL", length=300)
    public String getFileUrl() {
        return this.fileUrl;
    }
    
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    @Column(name="FILE_FIELD1", length=300)
    public String getFileField1() {
        return this.fileField1;
    }
    
    public void setFileField1(String fileField1) {
        this.fileField1 = fileField1;
    }
    
    @Column(name="FILE_FIELD2", length=300)
    public String getFileField2() {
        return this.fileField2;
    }
    
    public void setFileField2(String fileField2) {
        this.fileField2 = fileField2;
    }
    
    @Column(name="FILE_FIELD3", length=300)
    public String getFileField3() {
        return this.fileField3;
    }
    
    public void setFileField3(String fileField3) {
        this.fileField3 = fileField3;
    }
    
    @Column(name="COLUMN_NAME", length=50)
    public String getColumnName() {
        return this.columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalLibraryFile pojo = (JalLibraryFile) o;

        if (fileName != null ? !fileName.equals(pojo.fileName) : pojo.fileName != null) return false;
        if (columnId != null ? !columnId.equals(pojo.columnId) : pojo.columnId != null) return false;
        if (fileUrl != null ? !fileUrl.equals(pojo.fileUrl) : pojo.fileUrl != null) return false;
        if (fileField1 != null ? !fileField1.equals(pojo.fileField1) : pojo.fileField1 != null) return false;
        if (fileField2 != null ? !fileField2.equals(pojo.fileField2) : pojo.fileField2 != null) return false;
        if (fileField3 != null ? !fileField3.equals(pojo.fileField3) : pojo.fileField3 != null) return false;
        if (columnName != null ? !columnName.equals(pojo.columnName) : pojo.columnName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (columnId != null ? columnId.hashCode() : 0);
        result = 31 * result + (fileUrl != null ? fileUrl.hashCode() : 0);
        result = 31 * result + (fileField1 != null ? fileField1.hashCode() : 0);
        result = 31 * result + (fileField2 != null ? fileField2.hashCode() : 0);
        result = 31 * result + (fileField3 != null ? fileField3.hashCode() : 0);
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("fileId").append("='").append(getFileId()).append("', ");
        sb.append("fileName").append("='").append(getFileName()).append("', ");
        sb.append("columnId").append("='").append(getColumnId()).append("', ");
        sb.append("fileUrl").append("='").append(getFileUrl()).append("', ");
        sb.append("fileField1").append("='").append(getFileField1()).append("', ");
        sb.append("fileField2").append("='").append(getFileField2()).append("', ");
        sb.append("fileField3").append("='").append(getFileField3()).append("', ");
        sb.append("columnName").append("='").append(getColumnName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
