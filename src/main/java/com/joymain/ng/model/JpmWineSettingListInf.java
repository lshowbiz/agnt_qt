package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "JPM_WINE_SETTING_LIST_INF")
@XmlRootElement
public class JpmWineSettingListInf extends BaseObject implements Serializable {
    private Long idf;

    private String materialNo;

    private Integer qty;

    private String sdate;

    private String edate;

    private String memo;

    private Float lossRatio;

    private String isMainMaterial;

    private String isSendMaterial;

    private String isDelegateOut;

    private String isFeatureItem;

    private String picName;

    private JpmWineSettingInf jpmWineSettingInf = new JpmWineSettingInf();

    @Id
    @SequenceGenerator(name = "seq_pm", sequenceName = "SEQ_PM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pm")
    @Column(name = "IDF", unique = true, nullable = false, precision = 10, scale = 0)
    @DocumentId
    public Long getIdf() {
        return this.idf;
    }

    public void setIdf(Long idf) {
        this.idf = idf;
    }

    @Column(name = "MATERIAL_NO", length = 20)
    public String getMaterialNo() {
        return this.materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    @Column(name = "QTY", precision = 5, scale = 0)
    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Column(name = "SDATE", length = 10)
    public String getSdate() {
        return this.sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    @Column(name = "EDATE", length = 10)
    public String getEdate() {
        return this.edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    @Column(name = "MEMO", length = 500)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "LOSS_RATIO", precision = 18, scale = 5)
    public Float getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(Float lossRatio) {
        this.lossRatio = lossRatio;
    }

    @Column(name = "IS_MAIN_MATERIAL", length = 1)
    public String getIsMainMaterial() {
        return isMainMaterial;
    }

    public void setIsMainMaterial(String isMainMaterial) {
        this.isMainMaterial = isMainMaterial;
    }

    @Column(name = "IS_SEND_MATERIAL", length = 1)
    public String getIsSendMaterial() {
        return isSendMaterial;
    }

    public void setIsSendMaterial(String isSendMaterial) {
        this.isSendMaterial = isSendMaterial;
    }

    @Column(name = "IS_DELEGATE_OUT", length = 1)
    public String getIsDelegateOut() {
        return isDelegateOut;
    }

    public void setIsDelegateOut(String isDelegateOut) {
        this.isDelegateOut = isDelegateOut;
    }

    @Column(name = "IS_FEATURE_ITEM", length = 1)
    public String getIsFeatureItem() {
        return isFeatureItem;
    }

    public void setIsFeatureItem(String isFeatureItem) {
        this.isFeatureItem = isFeatureItem;
    }

    @Column(name = "PIC_NAME", length = 100)
    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SETTING_ID")
    public JpmWineSettingInf getJpmWineSettingInf() {
        return jpmWineSettingInf;
    }

    public void setJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf) {
        this.jpmWineSettingInf = jpmWineSettingInf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((edate == null) ? 0 : edate.hashCode());
        result = prime * result + ((idf == null) ? 0 : idf.hashCode());
        result = prime * result + ((isDelegateOut == null) ? 0 : isDelegateOut.hashCode());
        result = prime * result + ((isFeatureItem == null) ? 0 : isFeatureItem.hashCode());
        result = prime * result + ((isMainMaterial == null) ? 0 : isMainMaterial.hashCode());
        result = prime * result + ((isSendMaterial == null) ? 0 : isSendMaterial.hashCode());
        result = prime * result + ((jpmWineSettingInf == null) ? 0 : jpmWineSettingInf.hashCode());
        result = prime * result + ((lossRatio == null) ? 0 : lossRatio.hashCode());
        result = prime * result + ((materialNo == null) ? 0 : materialNo.hashCode());
        result = prime * result + ((memo == null) ? 0 : memo.hashCode());
        result = prime * result + ((picName == null) ? 0 : picName.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JpmWineSettingListInf other = (JpmWineSettingListInf) obj;
        if (edate == null) {
            if (other.edate != null)
                return false;
        } else if (!edate.equals(other.edate))
            return false;
        if (idf == null) {
            if (other.idf != null)
                return false;
        } else if (!idf.equals(other.idf))
            return false;
        if (isDelegateOut == null) {
            if (other.isDelegateOut != null)
                return false;
        } else if (!isDelegateOut.equals(other.isDelegateOut))
            return false;
        if (isFeatureItem == null) {
            if (other.isFeatureItem != null)
                return false;
        } else if (!isFeatureItem.equals(other.isFeatureItem))
            return false;
        if (isMainMaterial == null) {
            if (other.isMainMaterial != null)
                return false;
        } else if (!isMainMaterial.equals(other.isMainMaterial))
            return false;
        if (isSendMaterial == null) {
            if (other.isSendMaterial != null)
                return false;
        } else if (!isSendMaterial.equals(other.isSendMaterial))
            return false;
        if (jpmWineSettingInf == null) {
            if (other.jpmWineSettingInf != null)
                return false;
        } else if (!jpmWineSettingInf.equals(other.jpmWineSettingInf))
            return false;
        if (lossRatio == null) {
            if (other.lossRatio != null)
                return false;
        } else if (!lossRatio.equals(other.lossRatio))
            return false;
        if (materialNo == null) {
            if (other.materialNo != null)
                return false;
        } else if (!materialNo.equals(other.materialNo))
            return false;
        if (memo == null) {
            if (other.memo != null)
                return false;
        } else if (!memo.equals(other.memo))
            return false;
        if (picName == null) {
            if (other.picName != null)
                return false;
        } else if (!picName.equals(other.picName))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (sdate == null) {
            if (other.sdate != null)
                return false;
        } else if (!sdate.equals(other.sdate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JpmWineSettingListInf [idf=" + idf + ", materialNo=" + materialNo + ", qty=" + qty + ", sdate=" + sdate + ", edate=" + edate + ", memo=" + memo + ", lossRatio=" + lossRatio + ", isMainMaterial=" + isMainMaterial + ", isSendMaterial=" + isSendMaterial + ", isDelegateOut=" + isDelegateOut + ", isFeatureItem=" + isFeatureItem + ", picName=" + picName + ", jpmWineSettingInf=" + jpmWineSettingInf + "]";
    }
}
