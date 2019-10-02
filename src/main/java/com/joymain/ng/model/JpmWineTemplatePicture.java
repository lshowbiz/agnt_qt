package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JPM_WINE_TEMPLATE_PICTURE")

@XmlRootElement
public class JpmWineTemplatePicture extends BaseObject implements Serializable {
    private Long idf;
//    private String subNo;
    private String pictureName;
    private String picturePath;
    private Date createTime;
    private Long pictureSize;
    private String pictureExt;
    private String isInvalid;
    private String subName;
    
    private String pictureNameSrc;

    private String pictureNameDist;
    
    private JpmProductWineTemplateSub jpmProductWineTemplateSub;

    @Id      @Column(name="IDF", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getIdf() {
        return this.idf;
    }
    
    public void setIdf(Long idf) {
        this.idf = idf;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="SUB_NO")
    @JsonIgnore
    public JpmProductWineTemplateSub getJpmProductWineTemplateSub()
    {
        return jpmProductWineTemplateSub;
    }

    public void setJpmProductWineTemplateSub(JpmProductWineTemplateSub jpmProductWineTemplateSub)
    {
        this.jpmProductWineTemplateSub = jpmProductWineTemplateSub;
    }

//    @Column(name="SUB_NO", length=32)
//    public String getSubNo() {
//        return this.subNo;
//    }
//    
//    public void setSubNo(String subNo) {
//        this.subNo = subNo;
//    }
    
    @Column(name="PICTURE_NAME_SRC", length=100)
    public String getPictureNameSrc()
    {
        return pictureNameSrc;
    }

    public void setPictureNameSrc(String pictureNameSrc)
    {
        this.pictureNameSrc = pictureNameSrc;
    }

    @Column(name="PICTURE_NAME_DIST", length=100)
    public String getPictureNameDist()
    {
        return pictureNameDist;
    }

    public void setPictureNameDist(String pictureNameDist)
    {
        this.pictureNameDist = pictureNameDist;
    }

    @Column(name="PICTURE_NAME", length=100)
    public String getPictureName() {
        return this.pictureName;
    }
    
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
    
    @Column(name="PICTURE_PATH", length=500)
    public String getPicturePath() {
        return this.picturePath;
    }
    
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="PICTURE_SIZE", precision=22, scale=0)
    public Long getPictureSize() {
        return this.pictureSize;
    }
    
    public void setPictureSize(Long pictureSize) {
        this.pictureSize = pictureSize;
    }
    
    @Column(name="PICTURE_EXT", length=10)
    public String getPictureExt() {
        return this.pictureExt;
    }
    
    public void setPictureExt(String pictureExt) {
        this.pictureExt = pictureExt;
    }
    
    @Column(name="IS_INVALID", length=1)
    public String getIsInvalid() {
        return this.isInvalid;
    }
    
    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }
    
    @Column(name="SUB_NAME", length=200)
    public String getSubName() {
        return this.subName;
    }
    
    public void setSubName(String subName) {
        this.subName = subName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmWineTemplatePicture pojo = (JpmWineTemplatePicture) o;

//        if (subNo != null ? !subNo.equals(pojo.subNo) : pojo.subNo != null) return false;
        if (pictureName != null ? !pictureName.equals(pojo.pictureName) : pojo.pictureName != null) return false;
        if (picturePath != null ? !picturePath.equals(pojo.picturePath) : pojo.picturePath != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (pictureSize != null ? !pictureSize.equals(pojo.pictureSize) : pojo.pictureSize != null) return false;
        if (pictureExt != null ? !pictureExt.equals(pojo.pictureExt) : pojo.pictureExt != null) return false;
        if (isInvalid != null ? !isInvalid.equals(pojo.isInvalid) : pojo.isInvalid != null) return false;
        if (subName != null ? !subName.equals(pojo.subName) : pojo.subName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
//        result = (subNo != null ? subNo.hashCode() : 0);
        result = 31 * result + (pictureName != null ? pictureName.hashCode() : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (pictureSize != null ? pictureSize.hashCode() : 0);
        result = 31 * result + (pictureExt != null ? pictureExt.hashCode() : 0);
        result = 31 * result + (isInvalid != null ? isInvalid.hashCode() : 0);
        result = 31 * result + (subName != null ? subName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("idf").append("='").append(getIdf()).append("', ");
//        sb.append("subNo").append("='").append(getSubNo()).append("', ");
        sb.append("pictureName").append("='").append(getPictureName()).append("', ");
        sb.append("picturePath").append("='").append(getPicturePath()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("pictureSize").append("='").append(getPictureSize()).append("', ");
        sb.append("pictureExt").append("='").append(getPictureExt()).append("', ");
        sb.append("isInvalid").append("='").append(getIsInvalid()).append("', ");
        sb.append("subName").append("='").append(getSubName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
