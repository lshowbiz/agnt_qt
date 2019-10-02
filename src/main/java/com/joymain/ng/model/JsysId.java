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
import javax.persistence.Version;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JSYS_ID")

@XmlRootElement
public class JsysId extends BaseObject implements Serializable {
    private Long id;
    private Long version;
    private String idName;
    private Long idValue;
    private String idCode;
    private Long dateFormat;
    private String dateValue;
    private String prefix;
    private String postfix;
    private Long idLength;
    private Long fixed;
    private String infix;
    private String seqTable;

    @Id      @Column(name="ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    @Version
    @Column(name="VERSION", precision=12, scale=0)
    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
    
    @Column(name="ID_NAME", nullable=false, length=60)
    public String getIdName() {
        return this.idName;
    }
    
    public void setIdName(String idName) {
        this.idName = idName;
    }
    
    @Column(name="ID_VALUE", precision=12, scale=0)
    public Long getIdValue() {
        return this.idValue;
    }
    
    public void setIdValue(Long idValue) {
        this.idValue = idValue;
    }
    
    @Column(name="ID_CODE", length=30)
    public String getIdCode() {
        return this.idCode;
    }
    
    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
    
    @Column(name="DATE_FORMAT", precision=12, scale=0)
    public Long getDateFormat() {
        return this.dateFormat;
    }
    
    public void setDateFormat(Long dateFormat) {
        this.dateFormat = dateFormat;
    }
    
    @Column(name="DATE_VALUE", length=10)
    public String getDateValue() {
        return this.dateValue;
    }
    
    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
    
    @Column(name="PREFIX", length=30)
    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    @Column(name="POSTFIX", length=30)
    public String getPostfix() {
        return this.postfix;
    }
    
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
    
    @Column(name="ID_LENGTH", precision=12, scale=0)
    public Long getIdLength() {
        return this.idLength;
    }
    
    public void setIdLength(Long idLength) {
        this.idLength = idLength;
    }
    
    @Column(name="FIXED", precision=12, scale=0)
    public Long getFixed() {
        return this.fixed;
    }
    
    public void setFixed(Long fixed) {
        this.fixed = fixed;
    }
    
    @Column(name="INFIX", length=30)
    public String getInfix() {
        return this.infix;
    }
    
    public void setInfix(String infix) {
        this.infix = infix;
    }
    
    @Column(name="SEQ_TABLE", length=30)
    public String getSeqTable() {
        return this.seqTable;
    }
    
    public void setSeqTable(String seqTable) {
        this.seqTable = seqTable;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysId pojo = (JsysId) o;

        if (version != null ? !version.equals(pojo.version) : pojo.version != null) return false;
        if (idName != null ? !idName.equals(pojo.idName) : pojo.idName != null) return false;
        if (idValue != null ? !idValue.equals(pojo.idValue) : pojo.idValue != null) return false;
        if (idCode != null ? !idCode.equals(pojo.idCode) : pojo.idCode != null) return false;
        if (dateFormat != null ? !dateFormat.equals(pojo.dateFormat) : pojo.dateFormat != null) return false;
        if (dateValue != null ? !dateValue.equals(pojo.dateValue) : pojo.dateValue != null) return false;
        if (prefix != null ? !prefix.equals(pojo.prefix) : pojo.prefix != null) return false;
        if (postfix != null ? !postfix.equals(pojo.postfix) : pojo.postfix != null) return false;
        if (idLength != null ? !idLength.equals(pojo.idLength) : pojo.idLength != null) return false;
        if (fixed != null ? !fixed.equals(pojo.fixed) : pojo.fixed != null) return false;
        if (infix != null ? !infix.equals(pojo.infix) : pojo.infix != null) return false;
        if (seqTable != null ? !seqTable.equals(pojo.seqTable) : pojo.seqTable != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (version != null ? version.hashCode() : 0);
        result = 31 * result + (idName != null ? idName.hashCode() : 0);
        result = 31 * result + (idValue != null ? idValue.hashCode() : 0);
        result = 31 * result + (idCode != null ? idCode.hashCode() : 0);
        result = 31 * result + (dateFormat != null ? dateFormat.hashCode() : 0);
        result = 31 * result + (dateValue != null ? dateValue.hashCode() : 0);
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        result = 31 * result + (postfix != null ? postfix.hashCode() : 0);
        result = 31 * result + (idLength != null ? idLength.hashCode() : 0);
        result = 31 * result + (fixed != null ? fixed.hashCode() : 0);
        result = 31 * result + (infix != null ? infix.hashCode() : 0);
        result = 31 * result + (seqTable != null ? seqTable.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("version").append("='").append(getVersion()).append("', ");
        sb.append("idName").append("='").append(getIdName()).append("', ");
        sb.append("idValue").append("='").append(getIdValue()).append("', ");
        sb.append("idCode").append("='").append(getIdCode()).append("', ");
        sb.append("dateFormat").append("='").append(getDateFormat()).append("', ");
        sb.append("dateValue").append("='").append(getDateValue()).append("', ");
        sb.append("prefix").append("='").append(getPrefix()).append("', ");
        sb.append("postfix").append("='").append(getPostfix()).append("', ");
        sb.append("idLength").append("='").append(getIdLength()).append("', ");
        sb.append("fixed").append("='").append(getFixed()).append("', ");
        sb.append("infix").append("='").append(getInfix()).append("', ");
        sb.append("seqTable").append("='").append(getSeqTable()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
