package com.joymain.ng.dao;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JpmProduct;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.util.data.CommonRecord;

/**
 * An interface that provides a data management interface to the PdExchangeOrderDetail table.
 */
public interface PdExchangeOrderDetailDao extends GenericDao<PdExchangeOrderDetail, Long> {

	List<PdExchangeOrderDetail> getPdExchangeOrderDetails();

	PdExchangeOrderDetail getPdExchangeOrderDetail(Long uniNo);

	void savePdExchangeOrderDetail(PdExchangeOrderDetail pdExchangeOrderDetail);

	void removePdExchangeOrderDetail(Long uniNo);

	PdExchangeOrderDetail getDonationPdExchangeOrderDetail(
			String eoNo,String productNo,BigDecimal price,BigDecimal pv);

	
	
}