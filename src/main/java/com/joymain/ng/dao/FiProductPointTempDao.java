package com.joymain.ng.dao;

import com.joymain.ng.model.FiProductPointTemp;

/**
 * An interface that provides a data management interface to the FiBankbookTemp table.
 */
public interface FiProductPointTempDao extends GenericDao<FiProductPointTemp, Long> {

	/**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode, final String productPointType);
	
	public void saveFiProductPointTemp(FiProductPointTemp fiProductPointTemp);

}