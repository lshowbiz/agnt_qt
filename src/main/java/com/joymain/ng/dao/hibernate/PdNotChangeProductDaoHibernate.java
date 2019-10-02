package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.PdNotChangeProduct;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.PdNotChangeProductDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("pdNotChangeProductDao")
public class PdNotChangeProductDaoHibernate extends GenericDaoHibernate<PdNotChangeProduct, Long> implements PdNotChangeProductDao {

    public PdNotChangeProductDaoHibernate() {
        super(PdNotChangeProduct.class);
    }

	@Override
	public List<PdNotChangeProduct> getNotChangedProducts() {
		return null;
		
	}

	@Override
	public PdNotChangeProduct getPdNotChangeProduct(String productNo) {
		PdNotChangeProduct pdNotChangeProduct = null;
		StringBuffer hqlBuf = new StringBuffer("select pncp from PdNotChangeProduct pncp where 1=1 ");
		if(!StringUtil.isEmpty(productNo)){							 
				hqlBuf.append(" and pncp.productNo='");
				hqlBuf.append(productNo);
				hqlBuf.append("' ");
		}
		log.info("hql--->>>" + hqlBuf.toString());
		List<PdNotChangeProduct> list = this.getSession().createQuery(hqlBuf.toString()).list();
		if(list!=null && list.size()>=1){
			pdNotChangeProduct = (PdNotChangeProduct)list.get(0);
		}
		return pdNotChangeProduct;
		
	}

	@Override
	public void savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct) {
		this.save(pdNotChangeProduct);
	}

	@Override
	public boolean getIsNotChangeProduct(String productNo) {
		if(!StringUtil.isEmpty(productNo)){
			PdNotChangeProduct pdNotChangeProduct =  getPdNotChangeProduct(productNo);
			if(pdNotChangeProduct != null){
				return true;
			}
			return false;
		}
		return false;
	}

}
