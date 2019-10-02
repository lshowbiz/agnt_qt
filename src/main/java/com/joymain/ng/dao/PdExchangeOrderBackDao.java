package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.JpmProduct;
import com.joymain.ng.model.PdExchangeOrderBack;

/**
 * An interface that provides a data management interface to the PdExchangeOrderBack table.
 */
public interface PdExchangeOrderBackDao extends GenericDao<PdExchangeOrderBack, Long> {
       
    //保存一张换货单里面的原订单的商品
    void savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack);

	List<PdExchangeOrderBack> getPdExchangeOrderBacks();

	PdExchangeOrderBack getPdExchangeOrderBack(Long uniNo);

	void removePdExchangeOrderBack(Long uniNo);
    

}