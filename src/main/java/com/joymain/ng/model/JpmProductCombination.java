package com.joymain.ng.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name = "JPM_PRODUCT_COMBINATION", schema = "JECS_CN")
@XmlRootElement
public class JpmProductCombination extends BaseObject implements Serializable {

	private Long jpcId;
	private JpmProduct jpmProduct;
	private JpmProduct subJpmProduct;
	private Long qty;

	@Id
	@Column(name = "JPC_ID", unique = true, nullable = false, precision = 10, scale = 0)
	@DocumentId
	public Long getJpcId() {
		return this.jpcId;
	}

	public void setJpcId(Long jpcId) {
		this.jpcId = jpcId;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_NO")
	public JpmProduct getJpmProduct() {
		return jpmProduct;
	}

	public void setJpmProduct(JpmProduct jpmProduct) {
		this.jpmProduct = jpmProduct;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "SUB_PRODUCT_NO")
	public JpmProduct getSubJpmProduct() {
		return subJpmProduct;
	}

	public void setSubJpmProduct(JpmProduct subJpmProduct) {
		this.subJpmProduct = subJpmProduct;
	}

	@Column(name = "QTY", precision = 10, scale = 0)
	public Long getQty() {
		return this.qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		JpmProductCombination pojo = (JpmProductCombination) o;
		//if (productNo != null ? !productNo.equals(pojo.productNo) : pojo.productNo != null) return false;
		//if (subProductNo != null ? !subProductNo.equals(pojo.subProductNo) : pojo.subProductNo != null)  return false;
		if (qty != null ? !qty.equals(pojo.qty) : pojo.qty != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = 0;
		//result = (productNo != null ? productNo.hashCode() : 0);
		//result = 31 * result + (subProductNo != null ? subProductNo.hashCode() : 0);
		result = 31 * result + (qty != null ? qty.hashCode() : 0);

		return result;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

		sb.append(" [");
		sb.append("jpcId").append("='").append(getJpcId()).append("', ");
	//	sb.append("productNo").append("='").append(getProductNo()).append("', ");
	//	sb.append("subProductNo").append("='").append(getSubProductNo()).append("', ");
		sb.append("qty").append("='").append(getQty()).append("'");
		sb.append("]");

		return sb.toString();
	}

}
