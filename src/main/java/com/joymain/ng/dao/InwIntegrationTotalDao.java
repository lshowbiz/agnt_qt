package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.InwIntegrationTotal;

/**
 * An interface that provides a data management interface to the InwIntegrationTotal table.
 */
public interface InwIntegrationTotalDao extends GenericDao<InwIntegrationTotal, Long> {

	/**
     * 用户减掉积分的操作
     * @author yxzz  2014-06-09
     * @param userCode 会员编号
     * @param integratotal 积分
     * @param uniqueCode 唯一码
     * @return boolean
     */
	boolean minusIntegrationTotal(String userCode, String integratotal,String uniqueCode);
	public InwIntegrationTotal getInwIntegrationTotal(String userCode);

}