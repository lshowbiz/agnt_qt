package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.InwIntegration;

/**
 * An interface that provides a data management interface to the InwIntegration table.
 */
public interface InwIntegrationDao extends GenericDao<InwIntegration, Long> {

	/**
	 * 在扣除积分之前,首先进行放重复提交的校验
	 * @author 2014-06-10
	 * @param uniqueCode 唯一码
	 * @return boolean
	 */
	public boolean getCheckExist(String uniqueCode);

}