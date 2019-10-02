package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.PdShipFee;

/**
 * An interface that provides a data management interface to the PdShipFee table.
 */
public interface PdShipFeeDao extends GenericDao<PdShipFee, String> {
	/**
	 * get PdShipFee by province
	 * @param province
	 * @return PdShipFee
	 */
	public PdShipFee getPdShipFeeByProvince(String province);
}