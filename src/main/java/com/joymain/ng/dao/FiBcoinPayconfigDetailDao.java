package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FiBcoinPayconfigDetail;

/**
 * An interface that provides a data management interface to the FiBcoinPayconfigDetail table.
 */
public interface FiBcoinPayconfigDetailDao extends GenericDao<FiBcoinPayconfigDetail, Long> {

	public FiBcoinPayconfigDetail getFiBcoinPayconfigDetailsByProductNo(String configId, String productNo);
	
	public FiBcoinPayconfigDetail getFiBcoinPayconfigDetailByProductId(String productId);
}