package com.joymain.ng.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.dao.PdExchangeOrderDetailDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("pdExchangeOrderDetailDao")
public class PdExchangeOrderDetailDaoHibernate extends GenericDaoHibernate<PdExchangeOrderDetail, Long> implements PdExchangeOrderDetailDao {

	public PdExchangeOrderDetailDaoHibernate() {
		super(PdExchangeOrderDetail.class);
	}

	@Override
	public List<PdExchangeOrderDetail> getPdExchangeOrderDetails() {
		return getJdbcTemplate().queryForList("from PdExchangeOrderDetail", PdExchangeOrderDetail.class);
		
	}

	@Override
	public PdExchangeOrderDetail getPdExchangeOrderDetail(Long uniNo) {
		// TODO Auto-generated method stub
		return (PdExchangeOrderDetail) this.getSession().get(PdExchangeOrderDetail.class, uniNo);
	}

	@Override
	public void savePdExchangeOrderDetail(
			PdExchangeOrderDetail pdExchangeOrderDetail) {
		this.save(pdExchangeOrderDetail);
		
	}

	@Override
	public void removePdExchangeOrderDetail(Long uniNo) {
		this.getSession().delete(getPdExchangeOrderDetail(uniNo));
		
	}

	@Override
	public PdExchangeOrderDetail getDonationPdExchangeOrderDetail(
			String eoNo,String productNo,BigDecimal price,BigDecimal pv) {
		
		//return (JpmProductSaleNew) this.getObjectByHqlQuery("from JpmProductSaleNew jpsn where jpsn.uniNo='"+uniNo+"' ");
			
		return (PdExchangeOrderDetail)this.getObjectByHqlQuery("from PdExchangeOrderDetail peod where peod.isDonation = 'Y' and peod.pdExchangeOrder.eoNo='" + eoNo 
					+ "' and peod.productNo = '" + productNo + "' and peod.price = " + price + " and peod.pv = " + pv    );
		
	}

    
}
