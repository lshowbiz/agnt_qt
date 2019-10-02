package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "JPM_PRODUCT")
@XmlRootElement
public class JpmProduct extends BaseObject implements Serializable {
	private String productNo;
	private String productCategory;
	private String productName;
	private String unitNo;
	private String smNo;
	private String remark;
	private String productDesc;
	private String combineFlag;
	private String lockFlag;
	private String productStyle;
	private String productSize;
	private String productProvider;
	private String statisticsCategory;
	
	//add by lihao erpProductNo and erpPrice
	private String erpProductNo;
	private BigDecimal erpPrice;
	
	
	@Column(name = "ERP_PRODUCT_NO", length = 200)
	public String getErpProductNo() {
		return erpProductNo;
	}

	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
	}
	
	@Column(name = "ERP_PRICE", precision=18)
	public BigDecimal getErpPrice() {
		return erpPrice;
	}

	public void setErpPrice(BigDecimal erpPrice) {
		this.erpPrice = erpPrice;
	}

	private Set<JpmProductCombination> jpmProductCombinations = new HashSet<JpmProductCombination>();

	@OneToMany(mappedBy = "jpmProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<JpmProductCombination> getJpmProductCombinations() {
		return jpmProductCombinations;
	}

	public void setJpmProductCombinations(Set<JpmProductCombination> jpmProductCombinations) {
		this.jpmProductCombinations = jpmProductCombinations;
	}

	@Id
	@Column(name = "PRODUCT_NO", unique = true, nullable = false, length = 20)
	@DocumentId
	public String getProductNo() {
		return this.productNo;
	}

	
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@Column(name = "PRODUCT_CATEGORY", length = 10)
	public String getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@Column(name = "PRODUCT_NAME", length = 200)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "UNIT_NO", length = 10)
	public String getUnitNo() {
		return this.unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	@Column(name = "SM_NO", length = 2)
	public String getSmNo() {
		return this.smNo;
	}

	public void setSmNo(String smNo) {
		this.smNo = smNo;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "PRODUCT_DESC", length = 500)
	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@Column(name = "COMBINE_FLAG", length = 1)
	public String getCombineFlag() {
		return this.combineFlag;
	}

	public void setCombineFlag(String combineFlag) {
		this.combineFlag = combineFlag;
	}

	@Column(name = "LOCK_FLAG", length = 1)
	public String getLockFlag() {
		return this.lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	@Column(name = "PRODUCT_STYLE", length = 10)
	public String getProductStyle() {
		return this.productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	@Column(name = "PRODUCT_SIZE", length = 10)
	public String getProductSize() {
		return this.productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	@Column(name = "PRODUCT_PROVIDER")
	public String getProductProvider() {
		return this.productProvider;
	}

	public void setProductProvider(String productProvider) {
		this.productProvider = productProvider;
	}

	@Column(name = "STATISTICS_CATEGORY", length = 1000)
	public String getStatisticsCategory() {
		return this.statisticsCategory;
	}

	public void setStatisticsCategory(String statisticsCategory) {
		this.statisticsCategory = statisticsCategory;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		JpmProduct pojo = (JpmProduct) o;

		if (productCategory != null ? !productCategory.equals(pojo.productCategory) : pojo.productCategory != null)
			return false;
		if (productName != null ? !productName.equals(pojo.productName) : pojo.productName != null)
			return false;
		if (unitNo != null ? !unitNo.equals(pojo.unitNo) : pojo.unitNo != null)
			return false;
		if (smNo != null ? !smNo.equals(pojo.smNo) : pojo.smNo != null)
			return false;
		if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null)
			return false;
		if (productDesc != null ? !productDesc.equals(pojo.productDesc) : pojo.productDesc != null)
			return false;
		if (combineFlag != null ? !combineFlag.equals(pojo.combineFlag) : pojo.combineFlag != null)
			return false;
		if (lockFlag != null ? !lockFlag.equals(pojo.lockFlag) : pojo.lockFlag != null)
			return false;
		if (productStyle != null ? !productStyle.equals(pojo.productStyle) : pojo.productStyle != null)
			return false;
		if (productSize != null ? !productSize.equals(pojo.productSize) : pojo.productSize != null)
			return false;
		if (productProvider != null ? !productProvider.equals(pojo.productProvider) : pojo.productProvider != null)
			return false;
		if (statisticsCategory != null ? !statisticsCategory.equals(pojo.statisticsCategory) : pojo.statisticsCategory != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		result = (productCategory != null ? productCategory.hashCode() : 0);
		result = 31 * result + (productName != null ? productName.hashCode() : 0);
		result = 31 * result + (unitNo != null ? unitNo.hashCode() : 0);
		result = 31 * result + (smNo != null ? smNo.hashCode() : 0);
		result = 31 * result + (remark != null ? remark.hashCode() : 0);
		result = 31 * result + (productDesc != null ? productDesc.hashCode() : 0);
		result = 31 * result + (combineFlag != null ? combineFlag.hashCode() : 0);
		result = 31 * result + (lockFlag != null ? lockFlag.hashCode() : 0);
		result = 31 * result + (productStyle != null ? productStyle.hashCode() : 0);
		result = 31 * result + (productSize != null ? productSize.hashCode() : 0);
		result = 31 * result + (productProvider != null ? productProvider.hashCode() : 0);
		result = 31 * result + (statisticsCategory != null ? statisticsCategory.hashCode() : 0);

		return result;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

		sb.append(" [");
		sb.append("productNo").append("='").append(getProductNo()).append("', ");
		sb.append("productCategory").append("='").append(getProductCategory()).append("', ");
		sb.append("productName").append("='").append(getProductName()).append("', ");
		sb.append("unitNo").append("='").append(getUnitNo()).append("', ");
		sb.append("smNo").append("='").append(getSmNo()).append("', ");
		sb.append("remark").append("='").append(getRemark()).append("', ");
		sb.append("productDesc").append("='").append(getProductDesc()).append("', ");
		sb.append("combineFlag").append("='").append(getCombineFlag()).append("', ");
		sb.append("lockFlag").append("='").append(getLockFlag()).append("', ");
		sb.append("productStyle").append("='").append(getProductStyle()).append("', ");
		sb.append("productSize").append("='").append(getProductSize()).append("', ");
		sb.append("productProvider").append("='").append(getProductProvider()).append("', ");
		sb.append("statisticsCategory").append("='").append(getStatisticsCategory()).append("'");
		sb.append("]");

		return sb.toString();
	}

}
