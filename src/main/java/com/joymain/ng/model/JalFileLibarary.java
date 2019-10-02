package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.sql.Clob;
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
@Table(name="JAL_FILE_LIBARARY")

@XmlRootElement
public class JalFileLibarary extends BaseObject implements Serializable {
    private Long FId;
    private String category;
    private String typeId;
    private String name;
    private String remark;
    private String author;
    private String fileName;
    private String fileSize;
    private String url;
    private String logoImg;
    private String notes;
    private Long status;
    private String createUserCode;
    private String createName;
    private Date createTime;

    @Id      @Column(name="F_ID", unique=true, nullable=false, precision=12, scale=0) @DocumentId    
    public Long getFId() {
        return this.FId;
    }
    
    public void setFId(Long FId) {
        this.FId = FId;
    }
    @Column(name="CATEGORY", nullable=false, length=20)
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name="TYPE_ID", nullable=false, length=20)
    public String getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    
    @Column(name="NAME", nullable=false, length=300)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="REMARK", length=300)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="AUTHOR", length=300)
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    @Column(name="FILE_NAME", length=300)
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name="FILE_SIZE", length=20)
    public String getFileSize() {
        return this.fileSize;
    }
    
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    
    @Column(name="URL", length=300)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="LOGO_IMG", length=300)
    public String getLogoImg() {
        return this.logoImg;
    }
    
    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }
    
    @Column(name="NOTES")
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
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
    //@Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JalFileLibarary pojo = (JalFileLibarary) o;

        if (typeId != null ? !typeId.equals(pojo.typeId) : pojo.typeId != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (author != null ? !author.equals(pojo.author) : pojo.author != null) return false;
        if (fileSize != null ? !fileSize.equals(pojo.fileSize) : pojo.fileSize != null) return false;
        if (url != null ? !url.equals(pojo.url) : pojo.url != null) return false;
        if (logoImg != null ? !logoImg.equals(pojo.logoImg) : pojo.logoImg != null) return false;
        if (notes != null ? !notes.equals(pojo.notes) : pojo.notes != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (createUserCode != null ? !createUserCode.equals(pojo.createUserCode) : pojo.createUserCode != null) return false;
        if (createName != null ? !createName.equals(pojo.createName) : pojo.createName != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (logoImg != null ? logoImg.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createUserCode != null ? createUserCode.hashCode() : 0);
        result = 31 * result + (createName != null ? createName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("FId").append("='").append(getFId()).append("', ");
        sb.append("typeId").append("='").append(getTypeId()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("author").append("='").append(getAuthor()).append("', ");
        sb.append("fileSize").append("='").append(getFileSize()).append("', ");
        sb.append("url").append("='").append(getUrl()).append("', ");
        sb.append("logoImg").append("='").append(getLogoImg()).append("', ");
        sb.append("notes").append("='").append(getNotes()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("createUserCode").append("='").append(getCreateUserCode()).append("', ");
        sb.append("createName").append("='").append(getCreateName()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
