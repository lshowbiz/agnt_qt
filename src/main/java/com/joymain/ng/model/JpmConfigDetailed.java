package com.joymain.ng.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "JPM_CONFIG_DETAILED")
@XmlRootElement
public class JpmConfigDetailed extends BaseObject implements Serializable
{
    
    private Long configdetailedNo;
    
    private String subNo;
    
    private String subName;
    
    private Long subAmount;
    
    private Date createtime;
    
    private String status;
    
    private Long specNo;
    
    private Long price;
    
    private String isMustSelected;
    
    private Long idf;
    
    //图片名称
    private String picName;
    
    //图片地址
    private String picUrl;
    
    private String isCheck;
    
    //是否主料
    private String isMainMaterial;
    
    //备注
    private String remark;
    
    //单价
    private Long unitPrice;
    
    //可选图片集合
    private List<JpmWineTemplatePicture> jpmWineTemplatePictureList = new ArrayList<JpmWineTemplatePicture>();
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys")
    @SequenceGenerator(name = "seq_sys", sequenceName = "SEQ_SYS", allocationSize = 1)
     @Id      @Column(name="CONFIGDETAILED_NO", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getConfigdetailedNo()
    {
        return this.configdetailedNo;
    }
    
    public void setConfigdetailedNo(Long configdetailedNo)
    {
        this.configdetailedNo = configdetailedNo;
    }
    @Transient
    public Long getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    @Column(name = "REMARK", length = 200)
    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Transient
    public String getIsCheck()
    {
        return isCheck;
    }

    public void setIsCheck(String isCheck)
    {
        this.isCheck = isCheck;
    }

    @Transient
    public String getPicName()
    {
        return picName;
    }

    public void setPicName(String picName)
    {
        this.picName = picName;
    }

    @Transient
    public String getPicUrl()
    {
        return picUrl;
    }

    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    @Transient
    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureList()
    {
        return jpmWineTemplatePictureList;
    }

    public void setJpmWineTemplatePictureList(List<JpmWineTemplatePicture> jpmWineTemplatePictureList)
    {
        this.jpmWineTemplatePictureList = jpmWineTemplatePictureList;
    }

    @Column(name = "IDF", length = 1)
    public Long getIdf()
    {
        return idf;
    }

    public void setIdf(Long idf)
    {
        this.idf = idf;
    }

    @Column(name = "IS_MAIN_MATERIAL", length = 1)
    public String getIsMainMaterial()
    {
        return isMainMaterial;
    }

    public void setIsMainMaterial(String isMainMaterial)
    {
        this.isMainMaterial = isMainMaterial;
    }

    @Column(name = "IS_MUST_SELECTED", length = 1)
    public String getIsMustSelected()
    {
        return this.isMustSelected;
    }
    
    public void setIsMustSelected(String isMustSelected)
    {
        this.isMustSelected = isMustSelected;
    }
    
    @Column(name = "PRICE", precision = 22, scale = 0)
    public Long getPrice()
    {
        return this.price;
    }
    
    public void setPrice(Long price)
    {
        this.price = price;
    }
    
    @Column(name = "SPEC_NO", length = 10)
    public Long getSpecNo()
    {
        return specNo;
    }
    
    public void setSpecNo(Long specNo)
    {
        this.specNo = specNo;
    }
    
    @Column(name = "SUB_NO", length = 32)
    public String getSubNo()
    {
        return this.subNo;
    }
    
    public void setSubNo(String subNo)
    {
        this.subNo = subNo;
    }
    
    @Column(name = "SUB_NAME", length = 200)
    public String getSubName()
    {
        return this.subName;
    }
    
    public void setSubName(String subName)
    {
        this.subName = subName;
    }
    
    @Column(name = "SUB_AMOUNT", precision = 10, scale = 0)
    public Long getSubAmount()
    {
        return this.subAmount;
    }
    
    public void setSubAmount(Long subAmount)
    {
        this.subAmount = subAmount;
    }
    
    @Column(name = "CREATETIME", length = 7)
    public Date getCreatetime()
    {
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }
    
    @Column(name = "STATUS", length = 2)
    public String getStatus()
    {
        return this.status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        
        JpmConfigDetailed pojo = (JpmConfigDetailed) o;
        
        if (configdetailedNo != null
            ? !configdetailedNo.equals(pojo.configdetailedNo) : pojo.configdetailedNo != null)
            return false;
        if (subNo != null ? !subNo.equals(pojo.subNo) : pojo.subNo != null)
            return false;
        if (subName != null ? !subName.equals(pojo.subName) : pojo.subName != null)
            return false;
        if (subAmount != null ? !subAmount.equals(pojo.subAmount) : pojo.subAmount != null)
            return false;
        if (createtime != null ? !createtime.equals(pojo.createtime) : pojo.createtime != null)
            return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null)
            return false;
        
        return true;
    }
    
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (configdetailedNo != null ? configdetailedNo.hashCode() : 0);
        result = 31 * result + (subNo != null ? subNo.hashCode() : 0);
        result = 31 * result + (subName != null ? subName.hashCode() : 0);
        result = 31 * result + (subAmount != null ? subAmount.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        
        return result;
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());
        
        sb.append(" [");
        sb.append("configdetailedNo").append("='").append(getConfigdetailedNo()).append("', ");
        sb.append("subNo").append("='").append(getSubNo()).append("', ");
        sb.append("subName").append("='").append(getSubName()).append("', ");
        sb.append("subAmount").append("='").append(getSubAmount()).append("', ");
        sb.append("createtime").append("='").append(getCreatetime()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("]");
        
        return sb.toString();
    }
    
}
