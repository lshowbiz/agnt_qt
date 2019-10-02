package com.joymain.ng.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "JPM_WINE_ORDER_LIST_INTERFACE")
@XmlRootElement
public class JpmWineOrderListInterface extends BaseObject implements Serializable {

    private Long idf;//主键（自动生成）

    private String productId;//商品编码 √

    private Long qty;//发货数量  √

    private Double price;//商品单价 √

    private String creceiptcorpid;//发货单号   √

    private String vreceiveaddress;//收货地址

    private String dconsigndate;//发货日期 √

    private Double invpv = 0d;//PV

    private JpmWineOrderInterface jpmWineOrderInterface = new JpmWineOrderInterface();

    @Id
    @SequenceGenerator(name = "seq_pm", sequenceName = "SEQ_PM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pm")
    @Column(name = "IDF", unique = true, nullable = false, precision = 22, scale = 0)
    @DocumentId
    public Long getIdf() {
        return this.idf;
    }

    public void setIdf(Long idf) {
        this.idf = idf;
    }

    @Column(name = "PRODUCT_ID", length = 50)
    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name = "QTY", precision = 5, scale = 0)
    public Long getQty() {
        return this.qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    @Column(name = "PRICE", precision = 10, scale = 3)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "CRECEIPTCORPID", length = 50)
    public String getCreceiptcorpid() {
        return creceiptcorpid;
    }

    public void setCreceiptcorpid(String creceiptcorpid) {
        this.creceiptcorpid = creceiptcorpid;
    }

    @Column(name = "VRECEIVEADDRESS", length = 500)
    public String getVreceiveaddress() {
        return vreceiveaddress;
    }

    public void setVreceiveaddress(String vreceiveaddress) {
        this.vreceiveaddress = vreceiveaddress;
    }

    @Column(name = "DCONSIGNDATE", length = 500)
    public String getDconsigndate() {
        return dconsigndate;
    }

    public void setDconsigndate(String dconsigndate) {
        this.dconsigndate = dconsigndate;
    }

    @Column(name = "INV_PV", precision = 18, scale = 5)
    public Double getInvpv() {
        return invpv;
    }

    public void setInvpv(Double invpv) {
        this.invpv = invpv;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MO_ID")
    @JsonIgnore
    public JpmWineOrderInterface getJpmWineOrderInterface() {
        return jpmWineOrderInterface;
    }

    public void setJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface) {
        this.jpmWineOrderInterface = jpmWineOrderInterface;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creceiptcorpid == null) ? 0 : creceiptcorpid.hashCode());
        result = prime * result + ((dconsigndate == null) ? 0 : dconsigndate.hashCode());
        result = prime * result + ((idf == null) ? 0 : idf.hashCode());
        result = prime * result + ((invpv == null) ? 0 : invpv.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((vreceiveaddress == null) ? 0 : vreceiveaddress.hashCode());
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
        JpmWineOrderListInterface other = (JpmWineOrderListInterface) obj;
        if (creceiptcorpid == null) {
            if (other.creceiptcorpid != null)
                return false;
        } else if (!creceiptcorpid.equals(other.creceiptcorpid))
            return false;
        if (dconsigndate == null) {
            if (other.dconsigndate != null)
                return false;
        } else if (!dconsigndate.equals(other.dconsigndate))
            return false;
        if (idf == null) {
            if (other.idf != null)
                return false;
        } else if (!idf.equals(other.idf))
            return false;
        if (invpv == null) {
            if (other.invpv != null)
                return false;
        } else if (!invpv.equals(other.invpv))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (vreceiveaddress == null) {
            if (other.vreceiveaddress != null)
                return false;
        } else if (!vreceiveaddress.equals(other.vreceiveaddress))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JpmWineOrderListInterface [idf=" + idf + ", productId=" + productId + ", qty=" + qty + ", price=" + price + ", creceiptcorpid=" + creceiptcorpid + ", vreceiveaddress=" + vreceiveaddress + ", dconsigndate=" + dconsigndate + ", invpv=" + invpv + ", jpmWineOrderInterface=" + jpmWineOrderInterface + "]";
    }

}
