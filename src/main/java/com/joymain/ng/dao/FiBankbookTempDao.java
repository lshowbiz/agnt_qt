package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FiBankbookTemp;

/**
 * An interface that provides a data management interface to the FiBankbookTemp table.
 */
public interface FiBankbookTempDao extends GenericDao<FiBankbookTemp, Long> {

	/**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode, final String bankbookType);
	
	public void saveFiBankbookTemp(FiBankbookTemp fiBankbookTemp);

}