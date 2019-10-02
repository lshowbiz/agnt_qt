package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.PdNotChangeProduct;

/**
 * An interface that provides a data management interface to the PdNotChangeProduct table.
 */
public interface PdNotChangeProductDao extends GenericDao<PdNotChangeProduct, Long> {
		
	 /**
     * 查询不可换货的商品
     * @param productNo
     * @return 可以换货的商品
     */
	List<PdNotChangeProduct> getNotChangedProducts();
	
	PdNotChangeProduct getPdNotChangeProduct(String productNo); 
	
	void savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct);
	
	//根据商品编号判断该商品是否是不可换商品
	boolean getIsNotChangeProduct(String productNo);
}