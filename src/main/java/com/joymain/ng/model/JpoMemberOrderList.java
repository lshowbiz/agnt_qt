package com.joymain.ng.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="JPO_MEMBER_ORDER_LIST")

@XmlRootElement
public class JpoMemberOrderList extends BaseObject implements Serializable {
    private Long molId;
    private JpoMemberOrder jpoMemberOrder;
    private JpmProductSaleTeamType jpmProductSaleTeamType;

    private BigDecimal price;
    private BigDecimal pv;
    private int qty;
    private BigDecimal weight;
    private BigDecimal volume;
    private String productType;
    private Long coin;
    
    private String productName;
    private List<JpmProductSaleImage> imageLinkList;
    
    private Integer productConfigAmount;
    private String desc;
    
    // modify gw 2014-11-12  发货标志及发货时间
    private String shipped;
    private Date shippedTime;
    
    //modify gw 2014-11-28 新加三个字段:
    //WAREHOUSE_OPERATION 仓内作业（0001：已接单,0002：已接单,0003：已拣货,00013：已扫描,0005：已打包,0006：已发货）
    private String warehouseOperation;
    //TRACKING_SINGLE 物流跟踪单号
    private String trackingSingle;
    private List<String> trackingSingleList = new ArrayList<String>();
    //CONFIRM_RECEIPT 确认收货(0未收货，1部分收货，2全部收货）
    private String confirmReceipt;
    private String proNo;
    
    private String notChangeProductFlag;//不可换货的标志位
    
    
//    //订单配置信息
//    private Set<JpmMemberConfig> jpmMemberConfig = new HashSet<JpmMemberConfig>(0);

    @Transient
    public String getNotChangeProductFlag() {
		return notChangeProductFlag;
	}

	public void setNotChangeProductFlag(String notChangeProductFlag) {
		this.notChangeProductFlag = notChangeProductFlag;
	}

	@Transient
    public String getProductName() {
    	if(null!=jpmProductSaleTeamType){
    		productName = jpmProductSaleTeamType.getJpmProductSaleNew().getProductName();
    	}
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
    @Transient
    public List<JpmProductSaleImage> getImageLinkList() {
    	if(null!=jpmProductSaleTeamType&&null!=jpmProductSaleTeamType.getJpmProductSaleNew()){
    		imageLinkList = jpmProductSaleTeamType.getJpmProductSaleNew().getJpmProductSaleImageList();
    	}
		return imageLinkList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpo_seq")
	@SequenceGenerator(name = "jpo_seq", sequenceName = "SEQ_PO", allocationSize = 1)
	@Column(name="MOL_ID", unique=true, nullable=false, precision=10, scale=0) 
    public Long getMolId() {
        return this.molId;
    }
    
	
	public void setMolId(Long molId) {
        this.molId = molId;
    }
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    @JsonBackReference
	public JpmProductSaleTeamType getJpmProductSaleTeamType() {
		return jpmProductSaleTeamType;
	}

	public void setJpmProductSaleTeamType(
			JpmProductSaleTeamType jpmProductSaleTeamType) {
		this.jpmProductSaleTeamType = jpmProductSaleTeamType;
	}
	
	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name = "MO_ID")
	 @JsonIgnore
	public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}
	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy="jpoMemberOrderList",fetch=FetchType.EAGER)
//    public Set<JpmMemberConfig> getJpmMemberConfig() {
//        return jpmMemberConfig;
//    }
//
//    public void setJpmMemberConfig(Set<JpmMemberConfig> jpmMemberConfig) {
//        this.jpmMemberConfig = jpmMemberConfig;
//    }
    @Transient
    public Integer getProductConfigAmount()
    {
        return productConfigAmount;
    }

    public void setProductConfigAmount(Integer productConfigAmount)
    {
        this.productConfigAmount = productConfigAmount;
    }

    @Column(name="PRICE", nullable=false, precision=18)
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Column(name="PV", nullable=false, precision=18)
    
    public BigDecimal getPv() {
        return this.pv;
    }
    
    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    
    @Column(name="QTY", nullable=false, precision=5, scale=0)
    
    public int getQty() {
        return this.qty;
    }
    
    public void setQty(int qty) {
        this.qty = qty;
    }
    
    @Column(name="WEIGHT", precision=10)
    
    public BigDecimal getWeight() {
        return this.weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    @Column(name="VOLUME", precision=10)
    
    public BigDecimal getVolume() {
        return this.volume;
    }
    
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
    
    @Column(name="PRODUCT_TYPE", length=20)
    
    public String getProductType() {
        return this.productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    @Column(name="COIN", precision=18)
    
    public Long getCoin() {
        return this.coin;
    }
    
    public void setCoin(Long coin) {
        this.coin = coin;
    }
      
    @Column(name="SHIPPED", length=50)
    public String getShipped() {
		return shipped;
	}

	public void setShipped(String shipped) {
		this.shipped = shipped;
	}

    @Column(name="SHIPPED_TIME", length=7)
	public Date getShippedTime() {
		return shippedTime;
	}

	public void setShippedTime(Date shippedTime) {
		this.shippedTime = shippedTime;
	}
	
    @Column(name="WAREHOUSE_OPERATION", length=50)
	public String getWarehouseOperation() {
		return warehouseOperation;
	}

	public void setWarehouseOperation(String warehouseOperation) {
		this.warehouseOperation = warehouseOperation;
	}
	
    @Column(name="TRACKING_SINGLE", length=1000)
	public String getTrackingSingle() {
		return trackingSingle;
	}

	public void setTrackingSingle(String trackingSingle) {
		this.trackingSingle = trackingSingle;
	}
	
	@Transient
    public List<String> getTrackingSingleList() {
		return trackingSingleList;
	}

	public void setTrackingSingleList(List<String> trackingSingleList) {
		this.trackingSingleList = trackingSingleList;
	}

	public void setImageLinkList(List<JpmProductSaleImage> imageLinkList) {
		this.imageLinkList = imageLinkList;
	}

	@Column(name="CONFIRM_RECEIPT", length=50)
	public String getConfirmReceipt() {
		return confirmReceipt;
	}

	public void setConfirmReceipt(String confirmReceipt) {
		this.confirmReceipt = confirmReceipt;
	}

	@Transient
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name="PRODUCT_NO", length=50)
	public String getProNo() {
		if(null !=jpmProductSaleTeamType && 
				null !=jpmProductSaleTeamType.getJpmProductSaleNew()){
			proNo = jpmProductSaleTeamType.getJpmProductSaleNew().getProductNo();
    	}
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoMemberOrderList pojo = (JpoMemberOrderList) o;
        
        if (molId != null ? !molId.equals(pojo.molId) : pojo.molId != null) return false;
        if (price != null ? !price.equals(pojo.price) : pojo.price != null) return false;
        if (pv != null ? !pv.equals(pojo.pv) : pojo.pv != null) return false;
        if(jpmProductSaleTeamType.getPttId()!=null ? !(jpmProductSaleTeamType.getPttId()==pojo.jpmProductSaleTeamType.getPttId()) : pojo.jpmProductSaleTeamType.getPttId()!=null ) return false;
        if(jpoMemberOrder.getMoId()!=null ? !(jpoMemberOrder.getMoId()==pojo.jpoMemberOrder.getMoId()) : pojo.jpoMemberOrder.getMoId()!=null ) return false;
        	
        
        if (weight != null ? !weight.equals(pojo.weight) : pojo.weight != null) return false;
        if (volume != null ? !volume.equals(pojo.volume) : pojo.volume != null) return false;
        if (productType != null ? !productType.equals(pojo.productType) : pojo.productType != null) return false;
        if (coin != null ? !coin.equals(pojo.coin) : pojo.coin != null) return false;

        if (shipped != null ? !shipped.equals(pojo.shipped) : pojo.shipped != null) return false;
        if (shippedTime != null ? !shippedTime.equals(pojo.shippedTime) : pojo.shippedTime != null) return false;

        if (warehouseOperation != null ? !warehouseOperation.equals(pojo.warehouseOperation) : pojo.warehouseOperation != null) return false;
        if (trackingSingle != null ? !trackingSingle.equals(pojo.trackingSingle) : pojo.trackingSingle != null) return false;
        if (confirmReceipt != null ? !confirmReceipt.equals(pojo.confirmReceipt) : pojo.confirmReceipt != null) return false;

        return true;
    }
	
    public int hashCode() {
        int result = 0;      
        result = 31 * result + (molId!=null ? molId.hashCode():0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (pv != null ? pv.hashCode() : 0);
        result = 31 * result + (jpmProductSaleTeamType.getPttId() != null ? jpmProductSaleTeamType.getPttId().hashCode() : 0);
        result = 31 * result + (jpoMemberOrder.getMoId() != null ? jpoMemberOrder.getMoId().hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (coin != null ? coin.hashCode() : 0);

        result = 31 * result + (shipped != null ? shipped.hashCode() : 0);
        result = 31 * result + (shippedTime != null ? shippedTime.hashCode() : 0);

        result = 31 * result + (warehouseOperation != null ? warehouseOperation.hashCode() : 0);
        result = 31 * result + (trackingSingle != null ? trackingSingle.hashCode() : 0);
        result = 31 * result + (confirmReceipt != null ? confirmReceipt.hashCode() : 0);
        
        return result;
    }
    
   

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("molId").append("='").append(getMolId()).append("', ");
        sb.append("price").append("='").append(getPrice()).append("', ");
        sb.append("pv").append("='").append(getPv()).append("', ");
        sb.append("qty").append("='").append(getQty()).append("', ");
        sb.append("weight").append("='").append(getWeight()).append("', ");
        sb.append("volume").append("='").append(getVolume()).append("', ");
        sb.append("productType").append("='").append(getProductType()).append("', ");
        sb.append("coin").append("='").append(getCoin()).append("'");
        
        sb.append("shipped").append("='").append(getShipped()).append("'");
        sb.append("shippedTime").append("='").append(getShippedTime()).append("'");
        
        sb.append("warehouseOperation").append("='").append(getWarehouseOperation()).append("'");
        sb.append("trackingSingle").append("='").append(getTrackingSingle()).append("'");
        sb.append("confirmReceipt").append("='").append(getConfirmReceipt()).append("'");


        sb.append("]");
      
        return sb.toString();
    }

}
